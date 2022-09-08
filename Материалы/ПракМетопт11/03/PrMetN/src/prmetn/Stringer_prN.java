package prmetn;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.font.*;
import javax.swing.*;
import java.util.*;

public class Stringer_prN {
        public static int[] mist_calc = new int [20];
        static Color tempColor;
        public static int[] mist_choice = new int [20];
        static int lastErrorStep = -1;
        static int xStart = 0;
        static Graphics2D g2d;
        static String atext1, atext2, atext3="";
        static int ai;
        static int xAnswer, yAnswer;
        public static boolean canGo = true;
        static Font[] fonts = {
                new Font("Dialog", 1, 14), // основной текст
                new Font("Dialog", 1, 11), // глваный индекс
                new Font("Dialog", 1, 8) // мелкие индексы
        };
        static FontRenderContext[] frcs = new FontRenderContext[3];
        static Font reservedFont = fonts[0];
        static Font commentFont = new Font("Dialog", 1, 12);
        static Color reservedColor;
        static Color commentColor = new Color(0,0,90);

        public static double fieldValues[] = new double[10];
        public static int choiceValue = 0; // 0 - nothing, 1 - first, 2 - second, ...

        static JPanel panel = null;
        static JTextField[] fields = new JTextField[10];
        static JRadioButton[] buttons = new JRadioButton[10];
        static ButtonGroup buttonGroup = new ButtonGroup();
        static OwnListener ownListener = new OwnListener();

        public static int userAnswer = 0;

        public static void setFontSizes(int a, int b, int c) {
                fonts[0] = new Font("Dialog", 1, a);
                fonts[1] = new Font("Dialog", 1, b);
                fonts[2] = new Font("Dialog", 1, c);
                reservedFont = fonts[0];
        }

        public static void init() {
                lastErrorStep = -1;
        }

        public static void setGraphics(Graphics g) {
                g2d = (Graphics2D) g;
                for (int i = 0; i < 3; ++i) {
                        g2d.setFont(fonts[i]);
                        frcs[i] = g2d.getFontRenderContext();
                }
                for (int i = 0; i < 10; ++i) {
                        if (fields[i] == null) {
                                continue;
                        }
                        panel.remove(fields[i]);
                        fields[i] = null;
                }
                for (int i = 0; i < 10; ++i) {
                        if (buttons[i] == null) {
                                continue;
                        }
                        panel.remove(buttons[i]);
                        buttonGroup.remove(buttons[i]);
                        buttons[i] = null;
                }
        }

        public static void setPanel(JPanel p) {
                panel = p;
        }

        public static int drawString(String s, int x, int y) {
                xStart = x;
                return drawSubstring(s+"n{}", x, y, 0);
        }
        public static boolean tryUpdate() {
                for (int i = 0; i < 10; ++i) {
                        if (fields[i] == null) {
                                continue;
                        }
                        try {
                                fieldValues[i] = Double.parseDouble(fields[i].getText());
                        }
                        catch (NumberFormatException e) {
                                return false;
                        }
                }

                for (int i = 0; i < 10; ++i) {
                        if (buttons[i] == null) {
                                continue;
                        }
                        if (buttons[i].isSelected()) {
                                choiceValue = i + 1;
                        }
                }

                for (int i = 0; i < 10; ++i) {
                        if (fields[i] == null) {
                                continue;
                        }
                        panel.remove(fields[i]);
                        fields[i] = null;
                }
                for (int i = 0; i < 10; ++i) {
                        if (buttons[i] == null) {
                                continue;
                        }
                        panel.remove(buttons[i]);
                        buttonGroup.remove(buttons[i]);
                        buttons[i] = null;
                }

                return true;
        }

        public static boolean update() {
                for (int i = 0; i < 10; ++i) {
                        if (fields[i] == null) {
                                continue;
                        }
                        panel.remove(fields[i]);
                        fields[i] = null;
                }
                for (int i = 0; i < 10; ++i) {
                        if (buttons[i] == null) {
                                continue;
                        }
                        panel.remove(buttons[i]);
                        buttonGroup.remove(buttons[i]);
                        buttons[i] = null;
                }

                return true;
        }



        private static int drawSubstring(String s, int x, int y, int sy) {
                String text, text1, text2, text3, text4;
                char sym;
                int i, dy, iEnd, index = 0, fieldIndex = 0;
                Rectangle2D r, rr;
                int fontIndex = 0;
                boolean[] adj =  {true, true, true, true, true};
                boolean first = true;

                if (sy == 1 || sy == -1) {
                        fontIndex = 1;
                }
                else if (sy != 0) {
                        fontIndex = 2;
                }
                while (true) {
                        if ((s.indexOf("#", index) != -1 && s.indexOf("#", index) < s.indexOf("n{}", index)) ||
                                 (s.indexOf("%", index) != -1 && s.indexOf("%", index) < s.indexOf("n{}", index)) ||
                                 (s.indexOf("/{", index) != -1 && s.indexOf("/{", index) < s.indexOf("n{}", index))) {
                                r = fonts[fontIndex].getStringBounds(s, frcs[fontIndex]);
                                if (!adj[sy]) {
                                        y += r.getHeight() / 2 + 2;
                                        adj[sy] = true;
                                }

                        }

                        i = s.indexOf("{", index);
                        if (i == -1) {
                                i = s.length() + 1;
                        }
                        if (index > i - 1) {
                                return x;
                        }
                        text = s.substring(index, i - 1);
                        g2d.setFont(fonts[fontIndex]);
                        r = fonts[fontIndex].getStringBounds(text, frcs[fontIndex]);
                        g2d.drawString(text, x, y);
                        x += r.getWidth();
                        if (i == s.length() + 1) {
                                return x;
                        }
                        sym = s.charAt(i - 1);//System.out.println(sym);
                        iEnd = getEnd(s, i + 1); //s.indexOf("}", i);
                        index = iEnd + 1;
                        if (sym == 'n') {
                                y += r.getHeight() + 3;
                                x = xStart;
                                if(adj[sy]&& !first) y += r.getHeight();
                                first = false;
                                adj[sy] = false;
                        }
                        else if (sym == 'c') {
                                text1 = s.substring(i + 1, iEnd);
                                reservedFont = fonts[fontIndex];
                                fonts[fontIndex] = commentFont;
                                g2d.setFont(commentFont);
                                reservedColor = g2d.getColor();
                                g2d.setColor(commentColor);
                                x = drawSubstring(text1, x, y, sy);

                                fonts[fontIndex] = reservedFont;
                                g2d.setFont(reservedFont);
                                g2d.setColor(reservedColor);

                        }
                        else if (sym == 'r') {
                                text1 = s.substring(i + 1, iEnd);
                                tempColor = g2d.getColor();
                                g2d.setColor(Color.red);
                        }
                        else if (sym == 'e') {
                                g2d.setColor(tempColor);

                        }

                        else if (sym == '_') {
                                text1 = s.substring(i + 1, iEnd);
                                dy = 5;
                                x = drawSubstring(text1, x, y + dy, sy + 1);
                        }
                        else if (sym == '^') {
                                text1 = s.substring(i + 1, iEnd);
                                dy = -5;
                                x = drawSubstring(text1, x, y + dy, sy + 1);
                        }
                        else if(sym == '/') {
                                text1 = s.substring(i + 1, iEnd);
                                i = iEnd + 2; //{f}{g}
                                iEnd = getEnd(s, i);
                                index = iEnd + 1;
                                text2 = s.substring(i, iEnd);
                                dy = 5;
                                int xx1 = drawSubstring(text1, x, y - (int) r.getHeight() / 2 - 2, sy);
                                int xTemp = x;
                                x = drawSubstring(text2, x, y + (int) r.getHeight() / 2 + 2, sy);
                                if (xx1 > x) {
                                        x = xx1;
                                }
                                g2d.drawLine(x, y - (int) r.getHeight() / 2+3, xTemp, y - (int) r.getHeight() / 2+3);

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
                                if (x1 > x) {
                                        x = x1;
                                }
                        }
                        else if (sym == '#') {
                                text1 = s.substring(i + 1, iEnd);
                                i = iEnd + 2; //{f}{g}
                                iEnd = getEnd(s, i);
                                index = iEnd + 1;
                                text2 = s.substring(i, iEnd);

                                int ys1 = y - (int) r.getHeight() / 2 - (int) r.getHeight() + 5;
                                int ys2 = y - (int) r.getHeight() / 2 + (int) r.getHeight() + 5;

                                QuadCurve2D.Double sh = new QuadCurve2D.Double(x, ys1, x - 4,
                                        (ys1 + ys2) / 2, x, ys2);
                                g2d.draw(sh);

                                int x1 = drawSubstring(text1, x + 5,
                                                                           y - (int) r.getHeight() / 2 - 2, sy);
                                x = drawSubstring(text2, x + 5, y + (int) r.getHeight() / 2 + 2,
                                                                  sy);
                                if (x1 > x) {
                                        x = x1;
                                }
                                x += 4;

                                if (s.charAt(index) == '{') {
                                        i = iEnd + 2; //{f}{g}{h}{j}
                                        iEnd = getEnd(s, i);
                                        index = iEnd + 1;
                                        text3 = s.substring(i, iEnd);

                                        i = iEnd + 2; //{f}{g}{h}{j}
                                        iEnd = getEnd(s, i);
                                        index = iEnd + 1;
                                        text4 = s.substring(i, iEnd);

                                        x1 = drawSubstring(text3, x + 5,
                                                                           y - (int) r.getHeight() / 2 - 2, sy);
                                        x = drawSubstring(text4, x + 5,
                                                                          y + (int) r.getHeight() / 2 + 2, sy);
                                        if (x1 > x) {
                                                x = x1;
                                        }
                                }

                                sh = new QuadCurve2D.Double(x, ys1, x + 5, (ys1 + ys2) / 2, x,
                                                                                        ys2);
                                g2d.draw(sh);
                                x += 6;

                        }
                        else if (sym == '?') {
                                text1 = s.substring(i + 1, iEnd);
                                fieldIndex = Integer.parseInt(text1);
                                JTextField f = new JTextField();
                                fields[fieldIndex] = f;
                                f.setBounds(x, y - (int) r.getHeight() + 1+50, 60,
                                                        (int) r.getHeight() + 3);
                                f.setFont(fonts[fontIndex]);
                                f.setText("");
                                f.setForeground(g2d.getColor());
                                                                f.setBackground(Color.white);
                                f.addActionListener(ownListener);
                                f.addFocusListener(ownListener);
                                panel.add(f);
                                x += 62;
                        }
                        else if (sym == '%') {
                                fieldIndex = 0; //Integer.parseInt(text1);

                                text2 = s.substring(i + 1, iEnd);
                                JRadioButton b = new JRadioButton(text2);
                                buttons[fieldIndex] = b;
                                fieldIndex++;
                                b.setFont(fonts[fontIndex]);
                                b.setForeground(g2d.getColor());
                                b.setBackground(Color.white);
                                b.addActionListener(ownListener);
                                rr = fonts[fontIndex].getStringBounds(text2, frcs[fontIndex]);
                                b.setBounds(x, y - (int) r.getHeight() * 3 / 2 + 1+50,
                                                        (int) rr.getWidth() + 25, (int) r.getHeight() + 4);
                                panel.add(b);
                                buttonGroup.add(b);
                                int xx = x + b.getWidth() + 2;

                                i = iEnd + 2; //{f}{g}{h}{j}
                                iEnd = getEnd(s, i);
                                index = iEnd + 1;
                                text2 = s.substring(i, iEnd);

                                b = new JRadioButton(text2);
                                buttons[fieldIndex] = b;
                                fieldIndex++;
                                b.setFont(fonts[fontIndex]);
                                b.setForeground(g2d.getColor());
                                b.setBackground(Color.white);
                                b.addActionListener(ownListener);
                                rr = fonts[fontIndex].getStringBounds(text2, frcs[fontIndex]);
                                b.setBounds(x, y + 5 - (int) rr.getHeight() / 2+50,
                                                        (int) rr.getWidth() + 25, (int) r.getHeight() + 4);
                                panel.add(b);
                                buttonGroup.add(b);
                                x += b.getWidth() + 2;
                                if (xx > x) {
                                        x = xx;
                                        //			}
                                }
                        }
                        else if (sym == '!') {
                                canGo = false;
                                atext1 = s.substring(i + 1, iEnd);
                                i = iEnd + 2; //{f}{g}
                                iEnd = getEnd(s, i);
                                index = iEnd + 1;
                                atext2 = s.substring(i, iEnd);
                                if (atext1.trim().toLowerCase().equals("field")) {
                                        i = iEnd + 2; //{f}{g}
                                        iEnd = getEnd(s, i);
                                        index = iEnd + 1;
                                        atext3 = s.substring(i, iEnd);
                                }
                                xAnswer = x;
                                yAnswer = y;
                                ai++;
                        }
                }
        }

        private static int getEnd(String s, int from) {
                int balance = 1;
                while (true) {
                        if (s.charAt(from) == '{') {
                                balance++;
                        }
                        else if (s.charAt(from) == '}') {
                                balance--;
                        }
                        if (balance == 0) {
                                break;
                        }
                        from++;
                }
                return from;

        }

        public static void infoEntered(AWTEvent e) {

                Component c = (Component) e.getSource();
////////////////////////////////////////////////////
                        atext2 = atext2.replace(',', ',');
                        atext3 = atext3.replace(',', ',');
                        if (atext1.trim().toLowerCase().equals("field")) {
                                if (fields[0].getText().trim() == "" || fields[1].getText().trim() == "") return;
                                double d1, d2;
                                try {
                                        d1 = Double.parseDouble(fields[0].getText());
                                        d2 = Double.parseDouble(fields[1].getText());
                                }
                                catch (NumberFormatException ex) {
                                        d1 = d2 = Double.NaN;
                                        userAnswer = 0;
                                        return;
                                }
                                if (fields[0].getText() == "" || fields[1].getText() == "") {
                                        userAnswer = 0;
                                }
                                else {
                                        userAnswer = (d1 == Double.parseDouble(atext2) && d2 == Double.parseDouble(atext3)) ? 1 : -1;
                                        if(userAnswer < 0 && lastErrorStep != DrawPanel_prN.step) {
                                                mist_calc[DrawPanel_prN.step]++;
                                                lastErrorStep = DrawPanel_prN.step;
                                        }

                                }
                        }
                        else {
                                if (!buttons[0].isSelected() && !buttons[1].isSelected()) {
                                        userAnswer = 0;return;
                                }
                                else if ( (atext2.equals("1") && buttons[0].isSelected()) ||
                                                 (atext2.equals("2") && buttons[1].isSelected())) {
                                        userAnswer = 1;
                                }
                                else {
                                        userAnswer = -1;
                                }
                                if(userAnswer < 0 && lastErrorStep != DrawPanel_prN.step) {
                                        mist_choice[DrawPanel_prN.step]++;
                                        lastErrorStep = DrawPanel_prN.step;
                                }

                        }
                g2d = (Graphics2D) panel.getGraphics(); // халтура!
                g2d.setFont(fonts[0]);
                if (userAnswer > 0) {
                        g2d.setColor(Color.white);
                        g2d.drawString("\u2588\u2588\u2588\u2588\u2588\u2588\u2588", xAnswer, yAnswer+50);
                        g2d.setColor(Color.blue);
                        Font f = g2d.getFont();
                        g2d.setFont(new Font("Dialog", 3, 14));
                        g2d.drawString("Правильно!", xAnswer, yAnswer+50);
                        g2d.setFont(f);
                }
                else if (userAnswer < 0) {
                        g2d.setColor(Color.white);
                        g2d.drawString("\u2588\u2588\u2588\u2588\u2588\u2588\u2588", xAnswer, yAnswer+50);
                                                g2d.setColor(Color.red);
                        g2d.drawString("Неправильно.", xAnswer, yAnswer+50);
                }
                if(userAnswer>0) {
                        canGo = true;
                        lastErrorStep = -1;
                }
        }
}

class OwnListener extends FocusAdapter implements ActionListener {

        public void actionPerformed(ActionEvent e) {
                Stringer_prN.infoEntered(e);

        }

        public void focusLost(FocusEvent e) {
                Stringer_prN.infoEntered(e);
        }

}
