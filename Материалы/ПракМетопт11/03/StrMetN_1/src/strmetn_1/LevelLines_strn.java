package strmetn_1;
import java.awt.*;
import javax.swing.*;

public class LevelLines_strn {
        private static FunctionContainer_strn fc;
        private static Graphics2D g2d;
        private static int xOff, yOff, width, height, xCen, yCen;
        private static Color color;
        private static double scale;
        private static double[][] funcValues, funcMinValues, funcMaxValues;

        public static void init(FunctionContainer_strn fc_, int xOff_, int yOff_, int width_, int height_, double scale_,
                                          int xCen_, int yCen_, Color color_) {
                fc = fc_;
                xOff = xOff_;
                yOff = yOff_;
                width = width_;
                height = height_;
                scale = scale_;
                xCen = xCen_;
                yCen = yCen_;
                color = color_;
                funcValues = new double[height + 1][width + 1];
                funcMinValues = new double[height][width];
                funcMaxValues = new double[height][width];
                fillArrays();
        }

        public static void setGraphics(Graphics g) {
                g2d = (Graphics2D) g;
        }

        public static void drawLevelLine(double x1, double x2) {
                x1 = (x1 - xOff - xCen) / scale;
                x2 = -(x2 - yOff - yCen) / scale;
                double value = fc.func(x1, x2);
                Color ColorGrafic = new Color(0,0,150);//цвет grafika
                g2d.setColor(ColorGrafic);
                for (int i = 0; i < height; ++i) {
                        for (int j = 0; j < width; ++j) {
                        //    curValue = funcValues [i] [j];
                                double curMinValue = funcMinValues [i] [j];
                                double curMaxValue = funcMaxValues [i] [j];
                                if (value < curMinValue || value > curMaxValue) continue;
                                int x = j + xOff;
                                int y = yOff + height - i - 1;
                                g2d.drawLine(x, y, x, y);
//                funcColorLines [i * 400 + j] = marker;
                        }
                }
}

        private static double minOf4(double a, double b, double c, double d) {
                double min = a;
                if (b < min) {
                        min = b;
                }
                if (c < min) {
                        min = c;
                }
                if (d < min) {
                        min = d;
                }
                return min;
        }

        private static double maxOf4(double a, double b, double c, double d) {
                double max = a;
                if (b > max) {
                        max = b;
                }
                if (c > max) {
                        max = c;
                }
                if (d > max) {
                        max = d;
                }
                return max;
        }

        private static void fillArrays() {
                double x, y;
                // width, xCen, scale; a1 = -xCen/scale; a2 = (width-xCen)/scale;
                double a1 = ( -xCen - 0.5) / scale;
                double a2 = (width - xCen - 0.5) / scale;
                double b1 = ( -yCen - 0.5) / scale;
                double b2 = (height - yCen - 0.5) / scale;

                double dx = 1.0 / scale; //(a2 - a1) / (width + 1);
                double dy = 1.0 / scale; //(b2 - b1) / (height + 1);
                double curValue;

                y = b1;
                for (int i = 0; i < height + 1; ++i) {
                        x = a1;
                        for (int j = 0; j < width + 1; ++j) {
                                curValue = fc.func(x, y);
                                funcValues[i][j] = curValue;
                                x += dx;
                        }
                        y += dy;
                }
                for (int i = 0; i < height; ++i) {
                        for (int j = 0; j < width; ++j) {
                                funcMinValues[i][j] = minOf4
                                        (
                                        funcValues[i][j],
                                        funcValues[i + 1][j],
                                        funcValues[i][j + 1],
                                        funcValues[i + 1][j + 1]
                                        );
                                funcMaxValues[i][j] = maxOf4
                                        (
                                        funcValues[i][j],
                                        funcValues[i + 1][j],
                                        funcValues[i][j + 1],
                                        funcValues[i + 1][j + 1]
                                        );
                        }
                }

        }

}
