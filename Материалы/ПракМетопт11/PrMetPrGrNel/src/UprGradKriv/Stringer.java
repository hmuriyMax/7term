package UprGradKriv;

import java.awt.*;
import java.awt.geom.*;
import java.awt.font.*;
import javax.swing.*;
import java.util.*;

public class Stringer {
        static Graphics2D g2d;
        static Font[] fonts = {
                new Font("SansSerif", 1, 14),	// �������� �����
                new Font("SansSerif", 1, 11),   // ������� ������
                new Font("SansSerif", 1, 11)     // ������ �������
        };
        static FontRenderContext[] frcs = new FontRenderContext[3];

        public static void setGraphics(Graphics g) {
                g2d = (Graphics2D) g;
                for (int i = 0; i < 3; ++i) {
                        g2d.setFont(fonts[i]);
                        frcs[i] = g2d.getFontRenderContext();
                }
        }

        public static void drawString(String s, int x, int y) {
                drawSubstring(s, x, y, 0);
        }

        private static int drawSubstring(String s, int x, int y, int sy) {
//		System.out.println(s);
                String text, text1, text2;
                char sym;
                int i, dy, iEnd, index = 0;
                Rectangle2D r;
                int fontIndex = 0;

                if (sy == 1 || sy == -1) {
                        fontIndex = 1;
                }
                else if (sy != 0) {
                        fontIndex = 2;
                }

                while(true) {
                        i = s.indexOf("{", index);
                        if (i == -1) i = s.length() + 1;
                        if (index >= i - 1) return x;
                        text = s.substring(index, i - 1);
                        g2d.setFont(fonts[fontIndex]);
                        g2d.drawString(text, x, y);
                        r = fonts[fontIndex].getStringBounds(text, frcs[fontIndex]);
                        x += r.getWidth();
                        if (i == s.length() + 1) return x;
                        sym = s.charAt(i - 1);
                        iEnd = getEnd(s, i + 1);//s.indexOf("}", i);
                        index = iEnd + 1;
                        if (sym == '_') {
                                text1 = s.substring(i + 1, iEnd);
                                dy = 5;
                                x = drawSubstring(text1, x, y + dy, sy + 1);
                        }
                        else if (sym == '^') {
                                text1 = s.substring(i + 1, iEnd);
                                dy = -5;
                                x = drawSubstring(text1, x, y + dy, sy + 1);
                        }
                        else if (sym == '&') {
                                text1 = s.substring(i + 1, iEnd);
                                i = iEnd + 2; //{f}{g}
                                iEnd = getEnd(s, i);
                                index = iEnd + 1;
                                text2 = s.substring(i, iEnd);
                                dy = 5;
                                int x1 = drawSubstring(text1, x, y + dy, sy + 1);
                                x = drawSubstring(text2, x, y - dy, sy + 1);
                                if (x1 > x) x = x1;
                        }
                }
        }
        private static int getEnd(String s, int from) {
                int j, i = s.indexOf("}", from);
                // nSkip = count {
                int nSkip = 0, iSkip = from;
                do {
                        j = s.indexOf("{", iSkip);
                        if (j > i || j == -1) break;
                        iSkip = j + 1;
                        nSkip++;
                } while (true);

                from=i+1;
                for (int k = 0; k < nSkip; ++k) {
                        i = s.indexOf("}", from);
                        from = i + 1;
                }

                //System.out.println("{" + nSkip + "}");
                return i;
        }
}
