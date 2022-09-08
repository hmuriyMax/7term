package sravnenie;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
import java.awt.*;
import javax.swing.*;
public class LevelLines_prN
 {
        private static FunctionContainer_prN fc_m;
        private static Graphics2D g2d;
        private static int xOff, yOff, width, height, xCen, yCen;
        private static Color color;
        private static double scale;
        private static double[][] funcValues, funcMinValues, funcMaxValues;

        public static void init(FunctionContainer_prN fc_m_, int xOff_, int yOff_, int width_, int height_, double scale_,
                                          int xCen_, int yCen_, Color color_) {
                fc_m = fc_m_;
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

        public static void drawLevelLineScreen_m(double x1, double x2) {
                drawLevelLine_m((x1 - xOff - xCen) / scale, -(x2 - yOff - yCen) / scale);
        }

        public static void drawLevelLine_m(double x1, double x2) {
                double value = fc_m.func(x1, x2);
                g2d.setColor(color);
                for (int i = 0; i < height; ++i) {
                        for (int j = 0; j < width; ++j) {
                                double curMinValue = funcMinValues [i] [j];
                                double curMaxValue = funcMaxValues [i] [j];
                                if (value < curMinValue || value > curMaxValue) continue;
                                int x = j + xOff;
                                int y = yOff + i;
                                g2d.drawLine(x, y, x, y);
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
                double a1 = ( -xCen - 0.5) / scale;
                double a2 = (width - xCen - 0.5) / scale;
                double b1 = -(height - yCen - 0.5) / scale;
                double b2 = (yCen + 0.5) / scale;
                double dx = 1.0 / scale; //(a2 - a1) / (width + 1);
                double dy = 1.0 / scale; //(b2 - b1) / (height + 1);
                double curValue;
                y = b2;
                for (int i = 0; i < height + 1; ++i) {
                        x = a1;
                        for (int j = 0; j < width + 1; ++j) {
                                curValue = fc_m.func(x, y);
                                funcValues[i][j] = curValue;
                                x += dx;
                        }
                        y -= dy;
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
