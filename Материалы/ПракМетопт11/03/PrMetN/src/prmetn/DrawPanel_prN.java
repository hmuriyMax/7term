package prmetn;

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
import java.text.*;

public class DrawPanel_prN extends JPanel {
  private Color ColorFon=new Color(247,247,255);//���� ����
//������
  private Color ColorPanelFon=new Color(255,255,255);//���� ���� �������
  private Color ColorPanelPamka=new Color(157,187,255);//���� ����������
//�������(����. "����������� �����������" � �.�.)
  private Color ColorSubTitle=new Color(180,0,60);//����
  private Font FontSubTitle=new java.awt.Font("Dialog",3,12);//�����
//���� "�������"
  private Color ColorSolutionText=new Color(0,0,80);
  private Font FontSolutionText=new java.awt.Font("Dialog",1,14);
  private Color ColorSolutionNumber=new Color(180,0,60);
  private Font FontSolutionNumber=new Font("Dialog",3,16);
//���� "�������" "��������"
  private Color ColorFormulaText=new Color(0,0,80);
  private Font FontFormulaText=new java.awt.Font("Dialog",1,12);
//���� "����������� �����������"
  private Color ColorGraphicXY=new Color(50,50,50);
  public Color ColorGraphicLine=new Color(0,0,150);
  private Font FontGraphicXY=new java.awt.Font("Dialog",0,11);
  private Font FontGraphicText=new java.awt.Font("Dialog",0,10);
//  private Font numFont = new java.awt.Font("Dialog", 0, 11);
  public Color Colorhelp=new Color(10,50,140);

        public static int t=0,xOff = 5, yOff = 1, xLen = 340, yLen = 270;
        public static int xCen = 60, yCen = 240;
        public int fll;
        public static  int M=20;
        public static double scale = 50;
        static double[] x1s = {	0.0, -0.6, 0.8, -0.5, -0.5, 0.2, 0.2, 0.1, 0.3, -0.3, -0.6, 0.8};
        static double[] x2s = {	0.8, -0.8, 0.6, 0.3, 0.3, 0.2, -0.2, 0.1, 0.1, 0.1, -0.8, -0.6};
        static double[] scales = {140, 130, 150,150,150,150,150,150,150,180,150,150};
        static int xCens[] = {220, 170,40, 220,220, 220, 220, 250, 250, 240, 175, 150};
        static int yCens[] = {150, 30,150, 130,130, 60, 170, 100, 110, 90, 40, 120};
        static double[] aArr = {8,  8, 10, 5, 8,3, 15, 10, 12, 14, 9, 13};
        static double[] bArr = {0.6, -2.0/5,-0.5, 0.1,-0.2, 0.1, 0.1, 0.3, 0.3, 0.4, -0.5, 0.4};
        static double[] cArr = {12,  6, 4, 8, 3,20, 4, 10, 8, 15, 5, 10};
        static double[] dArr = {0.4, 0.6,0.5, 0.1, -0.1, 0.6, -0.6, 0.3, 0.4, 0.1, 0.7, 0.5};
        public double[] Vektor = {1000,  500, 250, 125, 62.5,31.25, 15.625, 7.8125, 3.90625, 1.953125, 0.9765625, 0.48828125, 0.244140625, 0.1220703125 , 0.06103515625, 0.030517578125, 0.0152587890625, 0.00762939453125, 0.003814697265625, 0.0019073486328125, 0.00095367431640625, 0.000476837158203125, 0.0002384185791015625, 0.00011920928955078125 ,0.000059604644775390625, 0.0000298023223876953125 ,0.00001490116119384765625 ,0.000007450580596923828125, 0.0000037252902984619140625 ,0.00000186264514923095703125 , 0.000000931322574615478515625};

        //public double[] Vektor = {100,  50, 25, 12.5, 6.25,3.125, 1.5625, 0.78125, 0.390625, 0.1953125, 0.09765625, 0.048828125, 0.0244140625, 0.01220703125 , 0.006103515625, 0.0030517578125, 0.00152587890625, 0.000762939453125, 0.0003814697265625, 0.00019073486328125, 0.000095367431640625, 0.0000476837158203125 , 0.00002384185791015625, 0.000011920928955078125 ,0.0000059604644775390625, 0.00000298023223876953125 ,0.000001490116119384765625 ,0.0000007450580596923828125, 0.00000037252902984619140625 ,0.000000186264514923095703125 , 0.0000000931322574615478515625};
       static int variant;
        public static QuadFunc_prN f=new QuadFunc_prN(1,0,1,0);
        public static int step, iter, k ;
        public static int nextStep;
        public static int nPoints, totalPoints;
        public static String c = "lopata";
        public static String b = "lopata";
        public static double xEnd, yEnd;
        public static double[] x1 = new double[300];
        public static double[] x2 = new double[300];
        public static String[] cs = new String[300];
        public static String[] bs = new String[300];
        public static String[] steps = new String[300];
        public static int[] realsteps = new int[300];
        public static int count, cc;
        public double m = 1000;

        public static boolean[] incPoints = new boolean[300];
        static{setFunc(0);}
 public static void setPar(int y){
  t=y;
 }
 public static void setFunc(int v) {
  count=0;
  variant = 0;
  xCen = xCens[v];
  yCen = yCens[v];
  xEnd = -bArr[v];
  yEnd = -dArr[v];
  step = 1;
  k = 0;
  x1[0] = x1s[v];
  x2[0] = x2s[v];
  f = new QuadFunc_prN(aArr[v], bArr[v], cArr[v], dArr[v]);
  f.setx1x2(x1[0], x2[0]);
  nPoints = 1;
  while (step != -1) {
    go();
    cs[count] = c;
    bs[count] = b;
    realsteps[count] = step;
    int z = k;
    if (((step ==14)&& nextStep != -1)||((step ==23)&& nextStep != -1)||((step ==32)&& nextStep != -1)||
        ((step ==41)&& nextStep != -1)||((step ==50)&& nextStep != -1)||((step ==59)&& nextStep != -1)||
        ((step ==68)&& nextStep != -1)||((step ==77)&& nextStep != -1)||((step ==86)&& nextStep != -1)||
        ((step ==95)&& nextStep != -1)||((step ==104)&& nextStep != -1)||((step ==113)&& nextStep != -1)||
        ((step ==122)&& nextStep != -1)||((step ==131)&& nextStep != -1)||((step ==140)&& nextStep != -1)||
        ((step ==149)&& nextStep != -1)||((step ==158)&& nextStep != -1)||((step ==167)&& nextStep != -1)||
        ((step ==176)&& nextStep != -1)||((step ==185)&& nextStep != -1)||((step ==194)&& nextStep != -1)||
        ((step ==203)&& nextStep != -1)||((step ==212)&& nextStep != -1)||((step ==221)&& nextStep != -1)||
        ((step ==230)&& nextStep != -1)||((step ==239)&& nextStep != -1)||((step ==248)&& nextStep != -1)||
        ((step ==257)&& nextStep != -1)||((step ==266)&& nextStep != -1)||((step ==275)&& nextStep != -1)||
         ((step ==285)&& nextStep != -1)||((step ==293)&& nextStep != -1)
        )
    z = k+1;
    steps[count] = "" + z + "." + step + ".";
    incPoints[count] = false;
    if (step == 12 ||step == 21 ||step == 30 ||step == 39||step == 48 ||step == 57||
        step == 66 ||step == 75 ||step == 84 ||step == 93||step == 102 ||step == 111||
        step == 120 ||step == 129 ||step == 138 ||step == 147||step == 156 ||step == 165||
        step == 174 ||step == 183 ||step == 192 ||step == 201||step == 210 ||step == 219||
         step == 228 ||step == 237 ||step == 246 ||step == 255||step == 264 ||step == 273||
        step == 282 ||step == 291 ||step == 300 ||step == 309
        ) {
                                x1[nPoints] = f.x1New(k);
                                x2[nPoints] = f.x2New(k);
                                nPoints++;
                                incPoints[count] = true;
                        }
                        step = nextStep;
                        count++;
                }
                step = 1;
                k = 0;
                f.setx1x2(x1[0], x2[0]);
                totalPoints = 1;
                cc = 0;
                setScale(scales[v]);
        }
        public static void moveCenter(int sx, int sy) {
                xCen += sx;
                yCen += sy;
                LevelLines_prN.init(f, xOff, yOff, xLen, yLen, scale, xCen, yCen,
                                                new Color(0,0,150));
        }
/////////////////////////////////////////////
        public static void setScale(double sc) {
                scale = sc;
                LevelLines_prN.init(f, xOff, yOff, xLen, yLen, scale, xCen, yCen, new Color(0,0,150));
        }
////////////////////////
        public static void go() {
                switch (step) {
    ///////////////   1  ////////////////////////////////////////////
                        case 1:
                          c = "  ����� x^{0} = (" + f.x1Str() + "; " + f.x2Str() +
                              "), \u03b5_{1} = " + f.eps1Str()+">0"  +
                              ", \u03BC^{0} = "+f.Vektor[0]+", M - ����������  ����� " ;
                           b = " ��������. ������ ����� ������ ������� f(x).    ���������:n{}"+
                               "\u03BC^{0} ���������� ��� ������� �� ������� ������, ��� ����� ������� n{}"+
                              "������� ������� �(x^{0}), � � ���� ����������� �������� ���������� \u03BC^{0} = 10^{4}." ;
                              nextStep = step + 1;
                          break;
                        case 2:
                          c = "  ������ �������� \u2207f(x) = " + f.gradientStr() + "";
                          b = "";
                          nextStep = step + 1;
                          break;
                        case 3:
                          c = "  ������ ������� �����";
                          b = "H(x) = #{f_{x_{1}x_{1}}}{f_{x_{1}x_{2}}}{f_{x_{2}x_{1}}}{f_{x_{2}x_{2}}} = " + f.gesseStr();
                          nextStep = step + 1;
                          break;
                       case 4:
                         c = "  ������� k = "+k+", \u03BC^{k} = \u03BC^{"+k+"} = "+f.Vektor[0]+"";
                         b = "";
                         nextStep = step + 1;
                         break;

                        case 5:
                          c = "  �������� �������� � ����� x^{" + k + "}:";
                          b = "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                          nextStep = step + 1;
                          break;

                        case 6:
                         // b = "�������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:";
                          if (f.gNorm() > f.eps1)
                          {
                             //c = "�������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:";
                            c ="  �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                            f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                             nextStep = step + 1;
                              b="";
                           }
                          else
                           {   if (f.gNorm() <= f.eps1)
                               {
                               c= "  �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                               "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                              f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                               "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                               "}e{} - ������� ����������� � �������.";
                               nextStep =-1;
                               b="";
                               }

                           }

                           break;
                         case 7:
                        if ( k < M )
                        { c = " �������� ���������� ������� k \u2265 M: k= " + k + " < "+"M "+"n{}" +
                           " ������� �� �����������, ������ ��������� � ���������� ����.";
                        b = "";
                          nextStep = step + 1;
                        }
                        else

                        {   c= " �������� ���������� ������� k \u2265 M: k = " + k + " \u2265 "+ M +" = M  " +"n{}"+
                            " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
                              "}e{} - ������� ����������� � �������.";
                              nextStep =-1;
                              b="";
                          }
                       break;

                        case 8:
                          c = "  ��������� ������� H(x^{" + k + "}) = " + f.hStr();
                          b="";
                          nextStep = step + 1;
                          break;

                        case 9:
                         c = "  ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k +"}*E = " +f.h_plusStr(0);
                         b="";
                         nextStep = step + 1;
                         break;

                        case 10:
                          c = "  ��������� �������� �������� �������, ���������� ��";
                         // b = "H^{-1}(x^{" + k + "}) = " + f.h_1Str()+"n{}"+
                         b = "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(0);
                          nextStep = step + 1;
                          break;

                        case 11:
                          b = " d_{" + k + "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(0) + "#{" +
                              f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                            c = "  ������ ���������:";
                          nextStep = step + 1;
                          break;

                        case 12:
                          c = "  ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" +
                              k + "} = " +
                             "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                             "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                         b="";
                              nextStep = step + 1;
                              totalPoints++;
                              break;


                        case 13:
                       c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): ";
                       if (f.dFuncNorm_a() < f.dFuncNorm_b()) {
                          b = "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k + "}) "+".n{}"+
                              " ������� �����������, ������� �������� k = k+1 = "+k+"+1 = "+(k+1)+" ,\u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+f.Vektor[0]/2 +
                             " n{}� ��������� � ���� 5.";
                           nextStep = step + 1;
                        //k++;
                          // f.setx1x2(f.x1New(), f.x2New());

                       }
                       else {
                         b = "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                         " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                             " n{}� ��������� � ���� 9.";
                         //f.setx1x2(f.x1New(), f.x2New());
                        nextStep = -1;
                        // k++;
                       }
                       break;
    ///////    2               /////////////////// ����� ������//////////////////////////////////////

                     case 14:
                   k++;
                      f.setx1x2(f.x1New(k), f.x2New(k));

                       c = "  �������� �������� � ����� x^{" + k + "}:n{}"+
                         "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                    b="";
                      nextStep = step + 1;
                      //b="";
                        break;
                      case 15:
                        //k++;
                          if (f.gNorm() > f.eps1)
                          {
                              c ="  �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                            f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                            b="";
                             nextStep = step + 1;

                           }
                          else
                          {   if (f.gNorm() <= f.eps1)
                               {
                               c= "  �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                               "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                              f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                               "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                               "}e{} - ������� ����������� � �������.";
                               nextStep =-1;
                                b="";
                           }

                           }

                           break;
                         case 16:
                         if ( k < M )
                         { c = "  �������� ���������� ������� k \u2265 M: k= " + k + " < "+ M +"n{}" +
                          " ������� �� �����������, ������ ��������� � ���������� ����.";
                          b = "";
                          nextStep = step + 1;}
                        else

{   c= " �������� ���������� ������� k \u2265 M: k = " + k + " \u2265 "+ M +" = M  " +"n{}"+
    " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
      "}e{} - ������� ����������� � �������.";
      nextStep =-1;
      b="";
  }


                                             break;
                        case 17:
                        c = "  ��������� ������� H(x^{" + k + "}) = " + f.hStr();
                        b="";
                        nextStep = step + 1;

                        break;
                      case 18:
                      c = "  ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k +"}*E = " +f.h_plusStr(1);
                      b="";
                      nextStep = step + 1;
                      break;

                      case 19:
                      c = "  ��������� �������� �������� �������, ���������� ��";

                      // b = "H^{-1}(x^{" + k + "}) = " + f.h_1Str()+"n{}"+
                       b = "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(1);
                        nextStep = step + 1;
                        break;

                      case 20:
                      b = " d_{" + k + "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(1) + "#{" +
                       f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                        c = "  ������ ���������:";
                        nextStep = step + 1;
                       break;

                        case 21:
                        c = "  ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" + k + "} = " +
                         "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                         "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                          b="";
                           nextStep = step + 1;
                           totalPoints++;
                          // nextStep =-1;
                           break;

                         case 22:
                         // b = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): ";
                           if (f.dFuncNorm_a() < f.dFuncNorm_b()) {
                            c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                            "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k + "}) "+".n{}"+
                            " ������� �����������, ������� �������� k = k+1 = "+k+"+1 = "+(k+1)+" ,\u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+f.Vektor[1]/2 +
                            " n{}� ��������� � ���� 5.";
                             nextStep = step + 1;
                            //  k++;
                              }
                               else {
                              c = "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                               " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                                " n{}� ��������� � ���� 9.";
                               //f.setx1x2(f.x1New(), f.x2New());
                               nextStep = -1;
                              //k++;
                             }
                           //   nextStep = -1;
                             break;

     /////////////////////////////////////////////////////

     //////////             3              //////////////// ����� ������//////////////////////////////////////

                    case 23:
                     k++;
                      f.setx1x2(f.x1New(k), f.x2New(k));
                      c = "  �������� �������� � ����� x^{" + k + "}:n{}"+
                        "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                     b="";
                     nextStep = step + 1;

                       break;
                     case 24:
                       // k++;
                         if (f.gNorm() > f.eps1)
                         {
                             c ="  �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                               "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                           f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                           b="";
                            nextStep = step + 1;

                          }
                         else
                          {   if (f.gNorm() <= f.eps1)
                              {
                              c= "  �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                              "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                             f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                              "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                              "}e{} - ������� ����������� � �������.";
                            b="";
                              nextStep =-1;
                              }

                          }

                          break;
                        case 25:
                        if ( k < M )
                        { c = "  �������� ���������� ������� k \u2265 M: k= " + k + " < "+ M +"n{}" +
                         " ������� �� �����������, ������ ��������� � ���������� ����.";
                         b = "";
                         nextStep = step + 1;}
                       else

                  {   c= " �������� ���������� ������� k \u2265 M: k = " + k + " \u2265 "+ M +" = M  " +"n{}"+
                      " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
                        "}e{} - ������� ����������� � �������.";
                        nextStep =-1;
                        b="";
                    }


                                            break;
                       case 26:
                       c = "  ��������� ������� H(x^{" + k + "}) = " + f.hStr();
                       b="";
                       nextStep = step + 1;

                       break;
                     case 27:
                     c = "  ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k +"}*E = " +f.h_plusStr(2);
                     b="";
                     nextStep = step + 1;
                     break;

                     case 28:
                     c = "  ��������� �������� �������� �������, ���������� ��";

                     // b = "H^{-1}(x^{" + k + "}) = " + f.h_1Str()+"n{}"+
                      b = "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(2);
                       nextStep = step + 1;
                       break;

                     case 29:
                     b = " d_{" + k + "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(2) + "#{" +
                      f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                       c = "  ������ ���������:";
                       nextStep = step + 1;
                      break;

                       case 30:
                       c = "  ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" + k + "} = " +
                        "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                        "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                         b="";
                          nextStep = step + 1;
                          totalPoints++;
                         // nextStep =-1;
                          break;

                        case 31:
                         c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): ";
                          if (f.dFuncNorm_a() < f.dFuncNorm_b()) {
                           b = "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k + "}) "+".n{}"+
                           " ������� �����������, ������� �������� k = k+1 = "+ k +"+1 = "+(k+1)+" ,\u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+f.Vektor[2]/2 +
                           " n{}� ��������� � ���� 5.";
                            nextStep = step + 1;
                            // k++;
                             }
                              else {
                              b = "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                              " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                               " n{}� ��������� � ���� 9.";
                              //f.setx1x2(f.x1New(), f.x2New());
                              nextStep = -1;
                             //  k++;
                            }
                            // nextStep = -1;
                            break;

    /////////////////////////////////////////////////////
    //////////                        4                  //////////////// ����� ������//////////////////////////////////////

                       case 32:
                         k++;
                         f.setx1x2(f.x1New(k), f.x2New(k));
                         c = "  �������� �������� � ����� x^{" + k + "}:n{}"+
                           "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                       b="";
                        nextStep = step + 1;

                          break;
                        case 33:

                            if (f.gNorm() > f.eps1)
                            {
                                c ="  �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                  "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                              f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                          b="";
                               nextStep = step + 1;

                             }
                            else
                             {   if (f.gNorm() <= f.eps1)
                                 {
                                 c= "  �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                 "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                                f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                                 "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                 "}e{} - ������� ����������� � �������.";
                             b="";
                                 nextStep =-1;
                                 }

                             }

                             break;
                           case 34:
                           if ( k < M )
                           { c = "  �������� ���������� ������� k \u2265 M: k= " + k + " < "+ M +"n{}" +
                            " ������� �� �����������, ������ ��������� � ���������� ����.";
                            b = "";
                            nextStep = step + 1;}
                          else

                {   c= " �������� ���������� ������� k \u2265 M: k = " + k + " \u2265 "+ M +" = M  " +"n{}"+
                    " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
                      "}e{} - ������� ����������� � �������.";
                      nextStep =-1;
                      b="";
                  }


                                               break;
                          case 35:
                          c = "  ��������� ������� H(x^{" + k + "}) = " + f.hStr();
                          b="";
                          nextStep = step + 1;

                          break;
                        case 36:
                        c = "  ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k +"}*E = " +f.h_plusStr(3);
                        b="";
                        nextStep = step + 1;
                        break;

                        case 37:
                        c = "  ��������� �������� �������� �������, ���������� �� n{}"+

                        // b = "H^{-1}(x^{" + k + "}) = " + f.h_1Str()+"n{}"+
                         "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(3);
                     b="";
                          nextStep = step + 1;
                          break;

                        case 38:
                        c = "  ������ ���������:n{}"+
                           " d_{" + k + "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(3) + "#{" +
                         f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                     b="";
                         // c = "  ������ ���������:";
                          nextStep = step + 1;
                         break;

                          case 39:
                          c = "  ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" + k + "} = " +
                           "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                           "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                            b="";
                             nextStep = step + 1;
                             totalPoints++;
                            // nextStep =-1;
                             break;

                           case 40:
                           // c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k+ "}): ";
                             if (f.dFuncNorm_a() < f.dFuncNorm_b()) {
                              c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k+ "}):n{} "+
                                  "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k + "}) "+".n{}"+
                              " ������� �����������, ������� �������� k = k+1 = "+(k)+"+1 = "+(k+1)+" ,\u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+f.Vektor[3]/2 +
                              " n{}� ��������� � ���� 5.";
                          b="";
                               nextStep = step + 1;
                               // k++;
                                }
                                 else {
                                 c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k+ "}):n{} "+
                                     "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                                 " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                                  " n{}� ��������� � ���� 9.";
                              b="";
                                 //f.setx1x2(f.x1New(), f.x2New());
                                 nextStep = -1;
                                //  k++;
                               }
                                //nextStep = -1;
                               break;

       /////////////////////////////////////////////////////
       ///////                 5             /////////////////// ����� ������//////////////////////////////////////

                             case 41:
                               k++;
                               f.setx1x2(f.x1New(k), f.x2New(k));
                               c = "  �������� �������� � ����� x^{" + k + "}:n{}"+
                                 "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                             b="";
                              nextStep = step + 1;

                                break;
                              case 42:

                                  if (f.gNorm() > f.eps1)
                                  {
                                      c ="  �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k+ "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                        "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                                    f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                                     b="";
                                     nextStep = step + 1;


                                   }
                                  else
                                   {   if (f.gNorm() <= f.eps1)
                                       {
                                       c= "  �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                       "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                                      f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                                       "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                       "}e{} - ������� ����������� � �������.";
                                    b="";
                                       nextStep =-1;
                                       }

                                   }

                                   break;
                                 case 43:
                                 if ( k < M )
                                 { c = "  �������� ���������� ������� k \u2265 M: k= " + k + " < "+ M +"n{}" +
                                  " ������� �� �����������, ������ ��������� � ���������� ����.";
                                  b = "";
                                  nextStep = step + 1;}
                                else

       {   c= " �������� ���������� ������� k \u2265 M: k = " + k + " \u2265 "+ M +" = M  " +"n{}"+
           " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
             "}e{} - ������� ����������� � �������.";
             nextStep =-1;
             b="";
         }

                                                     break;
                                case 44:
                                c = "��������� ������� H(x^{" + k + "}) = " + f.hStr();
                                b="";
                                nextStep = step + 1;

                                break;
                              case 45:
                              c = "  ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k+"}*E = " +f.h_plusStr(4);
                              b="";
                              nextStep = step + 1;
                              break;

                              case 46:
                              c = "  ��������� �������� �������� �������, ���������� �� n{}"+
                             "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(4);
                           b="";
                                nextStep = step + 1;
                                break;

                              case 47:
                              c = "  ������ ���������: n{}"+
                                  " d_{" + k+ "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(4) + "#{" +
                               f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                              b="";
                                nextStep = step + 1;
                               break;

                                case 48:
                                c = "  ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" + k + "} = " +
                                 "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                                 "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                                  b="";
                                   nextStep = step + 1;
                                   totalPoints++;
                                  // nextStep =-1;
                                   break;

                                 case 49:
                                 // c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): ";
                                   if (f.dFuncNorm_a() < f.dFuncNorm_b()) {
                                     c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}):n{} "+
                                      "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k+ "}) "+".n{}"+
                                    " ������� �����������, ������� �������� k = k+1 = "+ k +"+1 = "+(k+1)+" ,\u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+f.Vektor[4]/2 +
                                    " n{}� ��������� � ���� 5.";
                                b="";
                                     nextStep = step + 1;
                                     // k++;
                                      }
                                       else {
                                       c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                          "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                                       " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                                        " n{}� ��������� � ���� 9.";
                                    b="";
                                       //f.setx1x2(f.x1New(), f.x2New());
                                       nextStep = -1;
                                      //  k++;
                                     }
                                     // nextStep = -1;
                                     break;

             /////////////////////////////////////////////////////
             ///              6                   /////////////////////// ����� ������//////////////////////////////////////

                                   case 50:
                                     k++;
                                     f.setx1x2(f.x1New(k), f.x2New(k));
                                     c = "  �������� �������� � ����� x^{" + k + "}:n{}"+
                                       "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                                   b="";
                                    nextStep = step + 1;

                                      break;
                                    case 51:

                                        if (f.gNorm() > f.eps1)
                                        {
                                            c ="  �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                              "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                                          f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                                      b="";
                                           nextStep = step + 1;

                                         }
                                        else
                                         {   if (f.gNorm() <= f.eps1)
                                             {
                                             c= "  �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                             "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                                            f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                                             "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                             "}e{} - ������� ����������� � �������.";
                                            b="";
                                             nextStep =-1;
                                             }

                                         }

                                         break;
                                       case 52:
                                       if ( k < M )
                                       { c = "  �������� ���������� ������� k \u2265 M: k= " + k + " < "+ M +"n{}" +
                                        " ������� �� �����������, ������ ��������� � ���������� ����.";
                                        b = "";
                                        nextStep = step + 1;}
                                      else

 {   c= " �������� ���������� ������� k \u2265 M: k = " + k + " \u2265 "+ M +" = M  " +"n{}"+
     " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
       "}e{} - ������� ����������� � �������.";
       nextStep =-1;
       b="";
   }

                                                           break;
                                      case 53:
                                      c = "  ��������� ������� H(x^{" + k + "}) = " + f.hStr();
                                      b="";
                                      nextStep = step + 1;

                                      break;
                                    case 54:
                                    c = "  ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k +"}*E = " +f.h_plusStr(5);
                                    b="";
                                    nextStep = step + 1;
                                    break;

                                    case 55:
                                    c = "  ��������� �������� �������� �������, ���������� �� n{}"+

                                    "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(5);
                                b="";
                                      nextStep = step + 1;
                                      break;

                                    case 56:
                                    c = "  ������ ���������: n{}"+
                                        " d_{" + k + "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(5) + "#{" +
                                     f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                                     // c = "  ������ ���������:";
                                     b="";
                                      nextStep = step + 1;
                                     break;

                                      case 57:
                                      c = "  ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" +
                                       k + "} = " +
                                       "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                                       "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                                        b="";
                                         nextStep = step + 1;
                                         totalPoints++;
                                        // nextStep =-1;
                                         break;

                                       case 58:
                                      //  c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): ";
                                         if (f.dFuncNorm_a() < f.dFuncNorm_b()) {
                                           c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                            "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k + "}) "+".n{}"+
                                          " ������� �����������, ������� �������� k = k+1 = "+ k +"+1 = "+(k+1)+" ,n{}\u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+
                                          f.Vektor[5]/2 + " � ��������� � ���� 5.";
                                      b="";
                                           nextStep = step + 1;
                                           // k++;
                                            }
                                             else {
                                              c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                              "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                                             " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                                              " n{}� ��������� � ���� 9.";
                                          b="";
                                             //f.setx1x2(f.x1New(), f.x2New());
                                             nextStep = -1;
                                            //  k++;
                                           }
                                           //nextStep = -1;
                                           break;

                   /////////////////////////////////////////////////////

                   //////////         7                //////////////// ����� ������//////////////////////////////////////

                                                     case 59:
                                                       k++;
                                                       f.setx1x2(f.x1New(k), f.x2New(k));
                                                       c = "  �������� �������� � ����� x^{" + k + "}:n{}"+
                                                         "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                                                     b="";
                                                      nextStep = step + 1;

                                                        break;
                                                      case 60:

                                                          if (f.gNorm() > f.eps1)
                                                          {
                                                              c ="  �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                                                            f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                                                        b="";
                                                             nextStep = step + 1;

                                                           }
                                                          else
                                                           {   if (f.gNorm() <= f.eps1)
                                                               {
                                                               c= "  �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                               "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                                                              f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                                                               "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                                               "}e{} - ������� ����������� � �������.";
                                                           b="";
                                                               nextStep =-1;
                                                               }

                                                           }

                                                           break;
                                                         case 61:
                                                         if ( k < M )
                                                         { c = "  �������� ���������� ������� k \u2265 M: k= " + k + " < "+ M +"n{}" +
                                                          " ������� �� �����������, ������ ��������� � ���������� ����.";
                                                          b = "";
                                                          nextStep = step + 1;}
                                                        else

           {   c= " �������� ���������� ������� k \u2265 M: k = " + k + " \u2265 "+ M +" = M  " +"n{}"+
               " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
                 "}e{} - ������� ����������� � �������.";
                 nextStep =-1;
                 b="";
             }

                                                                             break;
                                                        case 62:
                                                        c = "   ��������� ������� H(x^{" + k + "}) = " + f.hStr();
                                                        b="";
                                                        nextStep = step + 1;

                                                        break;
                                                      case 63:
                                                      c = "  ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k +"}*E = " +f.h_plusStr(6);
                                                      b="";
                                                      nextStep = step + 1;
                                                      break;

                                                      case 64:
                                                      c = "  ��������� �������� �������� �������, ���������� �� n{}"+

                                                    // b = "H^{-1}(x^{" + k + "}) = " + f.h_1Str()+"n{}"+
                                                       "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(6);
                                                   b="";
                                                        nextStep = step + 1;
                                                        break;

                                                      case 65:
                                                      c = "  ������ ���������:n{}"+
                                                          " d_{" + k + "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(6) + "#{" +
                                                       f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                                                   b="";
                                                      //  c = "  ������ ���������:";
                                                        nextStep = step + 1;
                                                       break;

                                                        case 66:
                                                        c = "  ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" +
                                                         k+ "} = " +
                                                         "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                                                         "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                                                          b="";
                                                           nextStep = step + 1;
                                                           totalPoints++;
                                                          // nextStep =-1;
                                                           break;

                                                         case 67:
                                                         // c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): ";
                                                           if (f.dFuncNorm_a() < f.dFuncNorm_b()) {
                                                             c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k + "}) "+".n{}"+
                                                            " ������� �����������, ������� �������� k = k+1 = "+(k)+"+1 = "+(k+1)+" , n{}\u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+f.Vektor[6]/2 +
                                                            "� ��������� � ���� 5.";
                                                        b="";
                                                             nextStep = step + 1;
                                                              //k++;
                                                              }
                                                               else {
                                                                c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                   "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                                                               " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                                                                " n{}� ��������� � ���� 9.";
                                                            b="";
                                                               //f.setx1x2(f.x1New(), f.x2New());
                                                               nextStep = -1;
                                                              //  k++;
                                                             }
                                                            // nextStep = -1;
                                                             break;

                                     /////////////////////////////////////////////////////
     ///          8                 /////////////////////// ����� ������//////////////////////////////////////

                                     case 68:
                                     k++;
                                     f.setx1x2(f.x1New(k), f.x2New(k));
                                      c = "  �������� �������� � ����� x^{" + k+ "}:n{}"+
                                        "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                                    b="";
                                        nextStep = step + 1;

                                          break;
                                         case 69:

                                           if (f.gNorm() > f.eps1)
                                           {
                                              c ="   �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                               "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                                                f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                                            b="";
                                                  nextStep = step + 1;

                                           }
                                                   else
                                                   {   if (f.gNorm() <= f.eps1)
                                                    {
                                                    c= "   �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                     "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                                                     f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                                                     "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                                      "}e{} - ������� ����������� � �������.";
                                                 // b="";
                                                      nextStep =-1;
                                                      }

                                                      }

                                              break;
                                              case 70:
                                              if ( k < M )
                                              { c = "   �������� ���������� ������� k \u2265 M: k= " + k+ " < "+ M +"n{}" +
                                              " ������� �� �����������, ������ ��������� � ���������� ����.";
                                               b = "";
                                               nextStep = step + 1;}
                                             else

                   {   c= " �������� ���������� ������� k \u2265 M: k = " + k + " \u2265 "+ M +" = M  " +"n{}"+
                       " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
                         "}e{} - ������� ����������� � �������.";
                         nextStep =-1;
                         b="";
                     }


                                               break;
                                               case 71:
                                               c = "   ��������� ������� H(x^{" + k+ "}) = " + f.hStr();
                                               b="";
                                                nextStep = step + 1;

                                                 break;
                                                  case 72:
                                                 c = "  ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k +"}*E = " +f.h_plusStr(7);
                                                 b="";
                                                 nextStep = step + 1;
                                                 break;

                                                 case 73:
                                                 c = "  ��������� �������� �������� �������, ���������� �� n{}"+
                                               "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(7);
                                             b="";
                                                    nextStep = step + 1;
                                                      break;

                                                 case 74:
                                                  c = "  ������ ���������:n{}"+
                                                      " d_{" + k + "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(7) + "#{" +
                                                   f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                                                    b = "";
                                                    nextStep = step + 1;
                                                    break;

                                                   case 75:
                                                  c = "  ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" +
                                                  k + "} = " +
                                                  "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                                                  "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                                                    b="";
                                                   nextStep = step + 1;
                                                   totalPoints++;
                                                   // nextStep =-1;
                                                    break;

                                                    case 76:
                                                // c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): ";
                                        if (f.dFuncNorm_a() < f.dFuncNorm_b())
                                                {
                                              c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                  "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k + "}) "+".n{}"+
                                            " ������� �����������, ������� �������� k = k+1 = "+ k +"+1 = "+(k+1)+" ,n{}\u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+f.Vektor[7]/2 +
                                          " � ��������� � ���� 5.";
                                           b="";
                                         nextStep = step + 1;
                                          //k++;
                                        }
                                         else {
                                            c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                              "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                                          " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                                          " n{}� ��������� � ���� 9.";
                                          //f.setx1x2(f.x1New(), f.x2New());
                                          b="";
                                            nextStep = -1;
                                              //  k++;
                                              }
                                                // nextStep = -1;
                                                  break;

                  /////////////////////////////////////////////////////
                 ////////         9            ////////////////// ����� ������//////////////////////////////////////

                              case 77:
                                k++;
                                f.setx1x2(f.x1New(k), f.x2New(k));
                                 c = "  �������� �������� � ����� x^{" + k + "}:n{}"+
                                    "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                                b="";
                                    nextStep = step + 1;

                                      break;
                                       case 78:

                                         if (f.gNorm() > f.eps1)
                                          {
                                        c ="   �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                         "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                                         f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                                     b="";
                                          nextStep = step + 1;

                                         }
                                          else
                                          {   if (f.gNorm() <= f.eps1)
                                           {
                                            c= "   �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                             "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                                             f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                                             "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                              "}e{} - ������� ����������� � �������.";
                                          b="";
                                               nextStep =-1;
                                         }

                                         }

                                          break;
                                         case 79:
                                        if ( k < M )
                                        { c = "  �������� ���������� ������� k \u2265 M: k= " + k + " < "+ M+"n{}" +
                                         " ������� �� �����������, ������ ��������� � ���������� ����.";
                                          b = "";
                                           nextStep = step + 1;}
                                         else

                       {   c= " �������� ���������� ������� k \u2265 M: k = " + k + " \u2265 "+ M +" = M  " +"n{}"+
                           " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
                             "}e{} - ������� ����������� � �������.";
                             b="";
                             nextStep =-1;

                         }


                                           break;
                                            case 80:
                                        c = "  ��������� ������� H(x^{" + k + "}) = " + f.hStr();
                                        b="";
                                      nextStep = step + 1;

                                    break;
                                  case 81:
                                  c = "  ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k +"}*E = " +f.h_plusStr(8);
                                  b="";
                                  nextStep = step + 1;
                                  break;

                                  case 82:
                                  c = "  ��������� �������� �������� �������, ���������� �� n{}"+

                                  // b = "H^{-1}(x^{" + k + "}) = " + f.h_1Str()+"n{}"+
                                 "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(8);
                             b="";
                                  nextStep = step + 1;
                                  break;

                                   case 83:
                                   c = "  ������ ���������: n{}"+
                                       " d_{" + k + "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(8) + "#{" +
                                   f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                                   b="";
                                     nextStep = step + 1;
                                   break;

                                    case 84:
                                   c = "  ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" +
                                   k + "} = " +
                                   "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                                   "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                                    b="";
                                     nextStep = step + 1;
                                      totalPoints++;
                                      // nextStep =-1;
                                        break;

                                         case 85:
                                  // c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): ";
                                    if (f.dFuncNorm_a() < f.dFuncNorm_b()) {
                                   c = "   ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                       "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k + "}) "+".n{}"+
                                    " ������� �����������, ������� �������� k = k+1 = "+(k)+"+1 = "+(k+1)+" , n{}\u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+f.Vektor[8]/2 +
                                    " � ��������� � ���� 5.";
                                b="";
                                    nextStep = step + 1;
                                  //k++;
                                   }
                                    else {
                                       c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                       "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                                     " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                                      " n{}� ��������� � ���� 9.";
                                  b="";
                                       //f.setx1x2(f.x1New(), f.x2New());
                                        nextStep = -1;
                                         //  k++;
                                          }
                                           // nextStep = -1;
                                             break;

                  /////////////////////////////////////////////////////
             // ������ �����
                 ////////         10            ////////////////// ����� ������//////////////////////////////////////

                                             case 86:
                                               k++;
                                               f.setx1x2(f.x1New(k), f.x2New(k));
                                                c = "   �������� �������� � ����� x^{" + k+ "}:n{}"+
                                                   "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                                               b="";
                                                   nextStep = step + 1;

                                                     break;
                                                      case 87:

                                                        if (f.gNorm() > f.eps1)
                                                         {
                                                       c ="   �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                        "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                                                        f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                                                    b="";
                                                         nextStep = step + 1;

                                                        }
                                                         else
                                                         {   if (f.gNorm() <= f.eps1)
                                                          {
                                                           c= "   �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                            "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                                                            f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                                                            "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                                             "}e{} - ������� ����������� � �������.";
                                                         b="";
                                                              nextStep =-1;
                                                        }

                                                        }

                                                         break;
                                                        case 88:
                                                       if ( k < M )
                                                       { c = "   �������� ���������� ������� k \u2265 M: k= " + k + " < "+ M +"n{}" +
                                                        " ������� �� �����������, ������ ��������� � ���������� ����.";
                                                         b = "";
                                                          nextStep = step + 1;}
                                                        else

                        {   c= " �������� ���������� ������� k \u2265 M: k = " + k + " \u2265 "+ M +" = M  " +"n{}"+
                            " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
                              "}e{} - ������� ����������� � �������.";
                               b="";
                              nextStep =-1;

                          }


                                                          break;
                                                           case 89:
                                                       c = "   ��������� ������� H(x^{" + k + "}) = " + f.hStr();
                                                       b="";
                                                     nextStep = step + 1;

                                                   break;
                                                 case 90:
                                                 c = "   ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k +"}*E = " +f.h_plusStr(9);
                                                 b="";
                                                 nextStep = step + 1;
                                                 break;

                                                 case 91:
                                                 c = "   ��������� �������� �������� �������, ���������� �� n{}"+

                                                 // b = "H^{-1}(x^{" + k + "}) = " + f.h_1Str()+"n{}"+
                                                "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(9);
                                            b="";
                                                 nextStep = step + 1;
                                                 break;

                                                  case 92:
                                                   c = "   ������ ���������: n{}"+
                                                      " d_{" + k + "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(9) + "#{" +
                                                  f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                                                  b="";
                                                    nextStep = step + 1;
                                                  break;

                                                   case 93:
                                                  c = "   ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" +
                                                  k + "} = " +
                                                  "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                                                  "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                                                   b="";
                                                    nextStep = step + 1;
                                                     totalPoints++;
                                                     // nextStep =-1;
                                                       break;

                                                        case 94:
                                                  //c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): ";
                                                   if (f.dFuncNorm_a() < f.dFuncNorm_b()) {
                                                   c = "   ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                      "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k + "}) "+".n{}"+
                                                   " ������� �����������, ������� �������� k = k+1 = "+k+"+1 = "+(k+1)+" ,n{}\u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+f.Vektor[9]/2 +
                                                   " � ��������� � ���� 5.";
                                               b="";
                                                   nextStep = step + 1;
                                                //k++;
                                                  }
                                                   else {
                                                     c = "   ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                     "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                                                    " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                                                     " � ��������� � ���� 9.";
                                                 b="";
                                                      //f.setx1x2(f.x1New(), f.x2New());
                                                     //  nextStep = -1;
                                                        //  k++;
                                                         }

                                                            break;

 /////////////////////////////////////////////////////
 ////////         11           ////////////////// ����� ������//////////////////////////////////////

                                   case 95:
                                        k++;
                                        f.setx1x2(f.x1New(k), f.x2New(k));
                                         c = "   �������� �������� � ����� x^{" + k+ "}:n{}"+
                                            "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                                        b="";
                                            nextStep = step + 1;

                                              break;
                                               case 96:

                                                 if (f.gNorm() > f.eps1)
                                                  {
                                                c ="      �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                 "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                                                 f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                                             b="";
                                                  nextStep = step + 1;

                                                 }
                                                  else
                                                  {   if (f.gNorm() <= f.eps1)
                                                   {
                                                    c= "      �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                     "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                                                     f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                                                     "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                                      "}e{} - ������� ����������� � �������.";
                                                  b="";
                                                       nextStep =-1;
                                                 }

                                                 }

                                                  break;

                                                case 97:
                                  if ( k < M )
                           { c = "        �������� ���������� ������� k \u2265 M: k= " + k + " < "+ M + "n{}" +
                              " ������� �� �����������, ������ ��������� � ���������� ����.";
                           b = "";
                             nextStep = step + 1;
                           }
                           else

                           {   c= "       �������� ���������� ������� k \u2265 M: k = " + k + " < "+ M +" = M  " +"n{}"+
                               " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                 "}e{} - ������� ����������� � �������.";
                                  b="";
                                 nextStep =-1;

                             }



                                                   break;
                                                  case 98:
                                                c = "       ��������� ������� H(x^{" + k + "}) = " + f.hStr();
                                                b="";
                                              nextStep = step + 1;

                                            break;
                                        case 99:
                                          c = "       ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k +"}*E = " +f.h_plusStr(10);
                                          b="";
                                          nextStep = step + 1;
                                          break;

                                          case 100:
                                          c = "       ��������� �������� �������� �������, ���������� �� n{}"+

                                    "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(10);
                                     b="";
                                          nextStep = step + 1;

                                          break;

                                           case 101:
                                            c = "       ������ ���������: n{}"+
                                               " d_{" + k + "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(10) + "#{" +
                                           f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                                           b="";
                                             nextStep = step + 1;
                                           break;

                                            case 102:
                                           c = "       ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" + k + "} = " +
                                           "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                                           "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                                            b="";
                                             nextStep = step + 1;
                                              totalPoints++;
                                              // nextStep =-1;
                                                break;

                                                case 103:
                                           //c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): ";
                                            if (f.dFuncNorm_a() < f.dFuncNorm_b()) {
                                            c = "        ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                               "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k + "}) "+".n{}"+
                                            " ������� �����������, ������� �������� k = k+1 = "+k+"+1 = "+(k+1)+" ,n{}\u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+f.Vektor[10]/2 +
                                            " � ��������� � ���� 5.";
                                        b="";
                                            nextStep = step + 1;
                                         //k++;
                                           }
                                            else {
                                              c = "        ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                              "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                                             " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                                              " n{}� ��������� � ���� 9.";
                                          b="";
                                               //f.setx1x2(f.x1New(), f.x2New());
                                                nextStep = -1;
                                                 //  k++;
                                                  }
                                                 //  nextStep = -1;
                                                     break;

/////////////////////////////////////////////////////


 ////////         12            ////////////////// ����� ������//////////////////////////////////////

                        case 104:
                              k++;
                              f.setx1x2(f.x1New(k), f.x2New(k));
                               c = "      �������� �������� � ����� x^{" + k + "}:n{}"+
                                  "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                              b="";
                                  nextStep = step + 1;

                                    break;
                                     case 105:

                                       if (f.gNorm() > f.eps1)
                                        {
                                      c ="      �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                       "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                                       f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                                   b="";
                                        nextStep = step + 1;

                                       }
                                        else
                                        {   if (f.gNorm() <= f.eps1)
                                         {
                                          c= "      �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                           "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                                           f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                                           "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                            "}e{} - ������� ����������� � �������.";
                                        b="";
                                             nextStep =-1;
                                       }

                                       }

                                        break;
                                       case 106:
                                      if ( k < M )
                                      { c = "       �������� ���������� ������� k \u2265 M: k= " + k + " < "+ M +"n{}" +
                                       " ������� �� �����������, ������ ��������� � ���������� ����.";
                                        b = "";
                                         nextStep = step + 1;}
                                       else
                                       {   c= "       �������� ���������� ������� k \u2265 M: k = " + k + " < "+ M +" = M  " +"n{}"+
                                " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                  "}e{} - ������� ����������� � �������.";
                                   b="";
                                  nextStep =-1;

                              }


                                         break;
                                          case 107:
                                      c = "      ��������� ������� H(x^{" + k + "}) = " + f.hStr();
                                      b="";
                                    nextStep = step + 1;

                                  break;
                                case 108:
                                c = "      ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k +"}*E = " +f.h_plusStr(11);
                                b="";
                                nextStep = step + 1;
                                break;

                                case 109:
                                c = "     ��������� �������� �������� �������, ���������� �� n{}"+

                                // b = "H^{-1}(x^{" + k + "}) = " + f.h_1Str()+"n{}"+
                                 "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(11);
                               b="";
                                nextStep = step + 1;
                                break;

                                 case 110:
                                  c = "     ������ ���������: n{}"+
                                     " d_{" + k + "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(11) + "#{" +
                                 f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                                 b="";
                                   nextStep = step + 1;
                                 break;

                                  case 111:
                                 c = "      ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" +
                                 k + "} = " +
                                 "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                                 "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                                  b="";
                                   nextStep = step + 1;
                                    totalPoints++;
                                    // nextStep =-1;
                                      break;

                                       case 112:
                                 //c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): ";
                                  if (f.dFuncNorm_a() < f.dFuncNorm_b()) {
                                c = "      ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k + "}) "+".n{}"+
                                  " ������� �����������, ������� �������� k = k+1 = "+(k)+"+1 = "+(k+1)+" ,n{}\u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+f.Vektor[11]/2 +
                                  " � ��������� � ���� 5.";
                              b="";
                                  nextStep = step + 1;
                               // k++;
                                 }
                                  else {
                                    c = "       ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                    "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                                   " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                                    " n{}� ��������� � ���� 9.";
                                b="";
                                     //f.setx1x2(f.x1New(), f.x2New());
                                      nextStep = -1;
                                       //  k++;
                                        }
                                        //  nextStep = -1;
                                           break;

 /////////////////////////////////////////////////////

 ////////         13            ////////////////// ����� ������//////////////////////////////////////

                            case 113:
                              k++;
                              f.setx1x2(f.x1New(k), f.x2New(k));
                               c = "     �������� �������� � ����� x^{" + k + "}:n{}"+
                                  "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                              b="";
                                  nextStep = step + 1;

                                    break;
                                     case 114:

                                       if (f.gNorm() > f.eps1)
                                        {
                                      c ="     �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                       "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                                       f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                                   b="";
                                        nextStep = step + 1;

                                       }
                                        else
                                        {   if (f.gNorm() <= f.eps1)
                                         {
                                          c= "     �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                           "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                                           f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                                           "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                            "}e{} - ������� ����������� � �������.";
                                        b="";
                                             nextStep =-1;
                                       }

                                       }

                                        break;
                                       case 115:
                                      if ( k < M )
                                      { c = "       �������� ���������� ������� k \u2265 M: k= " + k + " < "+ M +"n{}" +
                                       " ������� �� �����������, ������ ��������� � ���������� ����.";
                                        b = "";
                                         nextStep = step + 1;}
                                       else
                                       {   c= "       �������� ���������� ������� k \u2265 M: k = " + k + " < "+ M +" = M  " +"n{}"+
                              " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                "}e{} - ������� ����������� � �������.";
                                    b="";
                                nextStep =-1;

                                     }


                                         break;
                                          case 116:
                                      c = "    ��������� ������� H(x^{" + k + "}) = " + f.hStr();
                                      b="";
                                    nextStep = step + 1;

                                  break;
                                case 117:
                                c = "       ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k +"}*E = " +f.h_plusStr(12);
                                b="";
                                nextStep = step + 1;
                                break;

                                case 118:
                                c = "     ��������� �������� �������� �������, ���������� �� n{}"+

                                // b = "H^{-1}(x^{" + k + "}) = " + f.h_1Str()+"n{}"+
                                 "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(12);
                             b="";
                                nextStep = step + 1;
                                break;

                                 case 119:
                                  c = "     ������ ���������: n{}"+
                                     " d_{" + k + "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(12) + "#{" +
                                 f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                                 b="";
                                   nextStep = step + 1;
                                 break;

                                  case 120:
                                 c = "     ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" +
                                 k + "} = " +
                                 "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                                 "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                                  b="";
                                   nextStep = step + 1;
                                    totalPoints++;
                                    // nextStep =-1;
                                      break;

                                       case 121:
                                // c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): ";
                                  if (f.dFuncNorm_a() < f.dFuncNorm_b()) {
                                 c = "      ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                  "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k + "}) "+".n{}"+
                                  " ������� �����������, ������� �������� k = k+1 = "+(k)+"+1 = "+(k+1)+" , n{}\u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+f.Vektor[12]/2 +
                                  " � ��������� � ���� 5.";
                              b="";
                                  nextStep = step + 1;
                                //k++;
                                 }
                                  else {
                                   c = "     ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                    "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                                   " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                                    " n{}� ��������� � ���� 9.";
                                b="";
                                     //f.setx1x2(f.x1New(), f.x2New());
                                      nextStep = -1;
                                       //  k++;
                                        }
                                         // nextStep = -1;
                                           break;

/////////////////////////////////////////////////////

////////         14            ////////////////// ����� ������//////////////////////////////////////

                                     case 122:
                                      k++;
                                                                        f.setx1x2(f.x1New(k), f.x2New(k));
                                                                         c = "      �������� �������� � ����� x^{" + k + "}:n{}"+
                                                                            "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                                                                        b="";
                                                                            nextStep = step + 1;

                                      break;
                                      case 123:

                                                                                 if (f.gNorm() > f.eps1)
                                                                                  {
                                                                                c ="        �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                 "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                                                                                 f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                                                                             b="";
                                                                                  nextStep = step + 1;

                                                                                 }
                                                                                  else
                                                                                  {   if (f.gNorm() <= f.eps1)
                                                                                   {
                                                                                    c= "      �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                     "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                                                                                     f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                                                                                     "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                                                                      "}e{} - ������� ����������� � �������.";
                                                                                  b="";
                                                                                       nextStep =-1;
                                                                                 }

                                                                                 }

                                      break;
                                      case 124:
                                                                                if ( k < M )
                                                                                { c = "      �������� ���������� ������� k \u2265 M: k= " + k + " < "+ M +"n{}" +
                                                                                 " ������� �� �����������, ������ ��������� � ���������� ����.";
                                                                                  b = "";
                                                                                   nextStep = step + 1;}
                                                                                 else

       {   c= " �������� ���������� ������� k \u2265 M: k = " + k + " \u2265 "+ M +" = M  " +"n{}"+
           " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
             "}e{} - ������� ����������� � �������.";
             b="";
             nextStep =-1;

         }


                                                 break;
                                               case 125:
                                                                                c = "      ��������� ������� H(x^{" + k + "}) = " + f.hStr();
                                                                                b="";
                                                                              nextStep = step + 1;

                                                                            break;
                                                                          case 126:
                                                                          c = "     ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k +"}*E = " +f.h_plusStr(13);
                                                                          b="";
                                                                          nextStep = step + 1;
                                                                          break;

                                                                          case 127:
                                                                          c = "    ��������� �������� �������� �������, ���������� �� n{}"+

                                                                          // b = "H^{-1}(x^{" + k + "}) = " + f.h_1Str()+"n{}"+
                                                                          "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(13);
                                                                      b="";
                                                                          nextStep = step + 1;
                                                                          break;

                                                                           case 128:
                                                                           c = "     ������ ���������: n{}"+
                                                                               " d_{" + k + "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(13) + "#{" +
                                                                           f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                                                                            b="";
                                                                             nextStep = step + 1;
                                                                           break;

                                                                            case 129:
                                                                           c = "     ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" +
                                                                           k + "} = " +
                                                                           "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                                                                           "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                                                                            b="";
                                                                             nextStep = step + 1;
                                                                              totalPoints++;
                                                                              // nextStep =-1;
                                                                                break;

                                                                                 case 130:
                                                                           //c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): ";
                                                                            if (f.dFuncNorm_a() < f.dFuncNorm_b()) {
                                                                           c = "      ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                           "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k + "}) "+".n{}"+
                                                                            " ������� �����������, ������� �������� k = k+1 = "+(k)+"+1 = "+(k+1)+" ,n{}\u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+f.Vektor[13]/2 +
                                                                            " � ��������� � ���� 5.";
                                                                        b="";
                                                                            nextStep = step + 1;
                                                                         // k++;
                                                                           }
                                                                            else {
                                                                             c = "     ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                                 "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                                                                             " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                                                                              " n{}� ��������� � ���� 9.";
                                                                          b="";
                                                                               //f.setx1x2(f.x1New(), f.x2New());
                                                                                nextStep = -1;
                                                                                 //  k++;
                                                                                  }
                                                                                   // nextStep = -1;
                                                                                     break;

 /////////////////////////////////////////////////////
 ////////         15            ////////////////// ����� ������//////////////////////////////////////

                           case 131:
                              k++;
                              f.setx1x2(f.x1New(k), f.x2New(k));
                               c = "     �������� �������� � ����� x^{" + k + "}:n{}"+
                                  "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                              b="";
                                  nextStep = step + 1;

                                    break;
                                     case 132:

                                       if (f.gNorm() > f.eps1)
                                        {
                                      c ="     �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                       "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                                       f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                                   b="";
                                        nextStep = step + 1;

                                       }
                                        else
                                        {   if (f.gNorm() <= f.eps1)
                                         {
                                          c= "     �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                           "\u2225\u2207f(x^{" + k+ "})\u2225 = "+f.gNormStr() + " \u2264 " +
                                           f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                                           "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                            "}e{} - ������� ����������� � �������.";
                                        b="";
                                             nextStep =-1;
                                       }

                                       }

                                        break;
                                       case 133:
                                      if ( k< M )
                                      { c = "     �������� ���������� ������� k \u2265 M: k= " + k + " < "+ M +"n{}" +
                                       " ������� �� �����������, ������ ��������� � ���������� ����.";
                                        b = "";
                                         nextStep = step + 1;}
                                       else

      {   c= " �������� ���������� ������� k \u2265 M: k = " + k + " \u2265 "+ M +" = M  " +"n{}"+
          " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
            "}e{} - ������� ����������� � �������.";
            b="";
            nextStep =-1;

        }



                                         break;
                                          case 134:
                                      c = "    ��������� ������� H(x^{" + k + "}) = " + f.hStr();
                                      b="";
                                    nextStep = step + 1;

                                  break;
                                case 135:
                                c = "      ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k +"}*E = " +f.h_plusStr(14);
                                b="";
                                nextStep = step + 1;
                                break;

                                case 136:
                                c = "      ��������� �������� �������� �������, ���������� �� n{}"+

                                // b = "H^{-1}(x^{" + k + "}) = " + f.h_1Str()+"n{}"+
                               "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(14);
                           b="";
                                nextStep = step + 1;
                                break;

                                 case 137:
                                c = "       ������ ���������: n{}"+
                                     " d_{" + k + "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(14) + "#{" +
                                 f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                                 b="";
                                   nextStep = step + 1;
                                 break;

                                  case 138:
                                 c = "       ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" +
                                 k + "} = " +
                                 "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                                 "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                                  b="";
                                   nextStep = step + 1;
                                    totalPoints++;
                                    // nextStep =-1;
                                      break;

                                       case 139:
                                // c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): ";
                                  if (f.dFuncNorm_a() < f.dFuncNorm_b()) {
                                 c = "        ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                     "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k + "}) "+".n{}"+
                                  " ������� �����������, ������� �������� k = k+1 = "+(k)+"+1 = "+(k+1)+" , n{} \u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+f.Vektor[14]/2 +
                                  " � ��������� � ���� 5.";
                              b="";
                                  nextStep = step + 1;
                               // k++;
                                 }
                                  else {
                                    c = "      ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                       "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                                   " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                                    " n{}� ��������� � ���� 9.";
                                b="";
                                     //f.setx1x2(f.x1New(), f.x2New());
                                      nextStep = -1;
                                       //  k++;
                                        }
                                         // nextStep = -1;
                                           break;

/////////////////////////////////////////////////////
////////         16            ////////////////// ����� ������//////////////////////////////////////

                                                                   case 140:
                                                                        k++;
                                                                        f.setx1x2(f.x1New(k), f.x2New(k));
                                                                         c = "      �������� �������� � ����� x^{" + k + "}:n{}"+
                                                                            "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                                                                        b="";
                                                                            nextStep = step + 1;

                                                                              break;
                                                                               case 141:

                                                                                 if (f.gNorm() > f.eps1)
                                                                                  {
                                                                                c ="       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                 "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                                                                                 f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                                                                             b="";
                                                                                  nextStep = step + 1;

                                                                                 }
                                                                                  else
                                                                                  {   if (f.gNorm() <= f.eps1)
                                                                                   {
                                                                                    c= "       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                     "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                                                                                     f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                                                                                     "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                                                                      "}e{} - ������� ����������� � �������.";
                                                                                  b="";
                                                                                       nextStep =-1;
                                                                                 }

                                                                                 }

                                                                                  break;
                                                                                 case 142:
                                                                                if ( k < M )
                                                                                { c = "      �������� ���������� ������� k \u2265 M: k= " + k + " < "+ M +"n{}" +
                                                                                 " ������� �� �����������, ������ ��������� � ���������� ����.";
                                                                                  b = "";
                                                                                   nextStep = step + 1;}
                                                                                 else

       {   c= " �������� ���������� ������� k \u2265 M: k = " + k + " \u2265 "+ M +" = M  " +"n{}"+
           " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
             "}e{} - ������� ����������� � �������.";
             b="";
             nextStep =-1;

         }



                                                                                   break;
                                                                                    case 143:
                                                                                c = "       ��������� ������� H(x^{" + k + "}) = " + f.hStr();
                                                                                b="";
                                                                              nextStep = step + 1;

                                                                            break;
                                                                          case 144:
                                                                          c = "       ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k +"}*E = " +f.h_plusStr(15);
                                                                          b="";
                                                                          nextStep = step + 1;
                                                                          break;

                                                                          case 145:
                                                                          c = "       ��������� �������� �������� �������, ���������� �� n{}"+

                                                                          // b = "H^{-1}(x^{" + k + "}) = " + f.h_1Str()+"n{}"+
                                                                          "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(15);
                                                                      b="";
                                                                          nextStep = step + 1;
                                                                          break;

                                                                           case 146:
                                                                            c = "       ������ ���������: n{}"+
                                                                               " d_{" + k + "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(15) + "#{" +
                                                                           f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                                                                           b="";
                                                                             nextStep = step + 1;
                                                                           break;

                                                                            case 147:
                                                                           c = "        ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" +
                                                                           k + "} = " +
                                                                           "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                                                                           "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                                                                            b="";
                                                                             nextStep = step + 1;
                                                                              totalPoints++;
                                                                              // nextStep =-1;
                                                                                break;

                                                                                 case 148:
                                                                          // c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): ";
                                                                            if (f.dFuncNorm_a() < f.dFuncNorm_b()) {
                                                                          c = "        ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                               "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k + "}) "+".n{}"+
                                                                            " ������� �����������, ������� �������� k = k+1 = "+(k)+"+1 = "+(k+1)+" , n{} \u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+f.Vektor[15]/2 +
                                                                            " � ��������� � ���� 5.";
                                                                        b="";
                                                                            nextStep = step + 1;
                                                                         // k++;
                                                                           }
                                                                            else {
                                                                             c = "       ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                                 "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                                                                             " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                                                                              " n{}� ��������� � ���� 9.";
                                                                          b="";
                                                                               //f.setx1x2(f.x1New(), f.x2New());
                                                                                nextStep = -1;
                                                                                 //  k++;
                                                                                  }
                                                                                  //  nextStep = -1;
                                                                                     break;
  /////////////            17          ////////////////////////////////////////
////////                   ////////////////// ����� ������//////////////////////////////////////

                                                                   case 149:
                                                                        k++;
                                                                        f.setx1x2(f.x1New(k), f.x2New(k));
                                                                         c = "      �������� �������� � ����� x^{" + k + "}:n{}"+
                                                                            "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                                                                        b="";
                                                                            nextStep = step + 1;

                                                                              break;
                                                                               case 150:

                                                                                 if (f.gNorm() > f.eps1)
                                                                                  {
                                                                                c ="       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                 "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                                                                                 f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                                                                             b="";
                                                                                  nextStep = step + 1;

                                                                                 }
                                                                                  else
                                                                                  {   if (f.gNorm() <= f.eps1)
                                                                                   {
                                                                                    c= "       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                     "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                                                                                     f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                                                                                     "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                                                                      "}e{} - ������� ����������� � �������.";
                                                                                  b="";
                                                                                       nextStep =-1;
                                                                                 }

                                                                                 }

                                                                                  break;
                                                                                 case 151:
                                                                                if ( k < M )
                                                                                { c = "      �������� ���������� ������� k \u2265 M: k= " + k + " < "+ M +"n{}" +
                                                                                 " ������� �� �����������, ������ ��������� � ���������� ����.";
                                                                                  b = "";
                                                                                   nextStep = step + 1;}
                                                                                 else

      {   c= " �������� ���������� ������� k \u2265 M: k = " + k + " \u2265 "+ M +" = M  " +"n{}"+
          " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
            "}e{} - ������� ����������� � �������.";
            b="";
            nextStep =-1;

        }


                                                                                   break;
                                                                                    case 152:
                                                                                c = "       ��������� ������� H(x^{" + k + "}) = " + f.hStr();
                                                                                b="";
                                                                              nextStep = step + 1;

                                                                            break;
                                                                          case 153:
                                                                          c = "       ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k +"}*E = " +f.h_plusStr(16);
                                                                          b="";
                                                                          nextStep = step + 1;
                                                                          break;

                                                                          case 154:
                                                                          c = "       ��������� �������� �������� �������, ���������� �� n{}"+

                                                                          // b = "H^{-1}(x^{" + k + "}) = " + f.h_1Str()+"n{}"+
                                                                          "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(16);
                                                                      b="";
                                                                          nextStep = step + 1;
                                                                          break;

                                                                           case 155:
                                                                            c = "       ������ ���������: n{}"+
                                                                               " d_{" + k + "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(16) + "#{" +
                                                                           f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                                                                           b="";
                                                                             nextStep = step + 1;
                                                                           break;

                                                                            case 156:
                                                                           c = "        ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" +
                                                                           k + "} = " +
                                                                           "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                                                                           "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                                                                            b="";
                                                                             nextStep = step + 1;
                                                                              totalPoints++;
                                                                              // nextStep =-1;
                                                                                break;

                                                                                 case 157:
                                                                          // c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): ";
                                                                            if (f.dFuncNorm_a() < f.dFuncNorm_b()) {
                                                                          c = "        ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                               "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k + "}) "+".n{}"+
                                                                            " ������� �����������, ������� �������� k = k+1 = "+(k)+"+1 = "+(k+1)+" , n{} \u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+f.Vektor[16]/2 +
                                                                            " � ��������� � ���� 5.";
                                                                        b="";
                                                                            nextStep = step + 1;
                                                                         // k++;
                                                                           }
                                                                            else {
                                                                             c = "       ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                                 "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                                                                             " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                                                                              " n{}� ��������� � ���� 9.";
                                                                          b="";
                                                                               //f.setx1x2(f.x1New(), f.x2New());
                                                                                nextStep = -1;
                                                                                 //  k++;
                                                                                  }
                                                                                  //  nextStep = -1;
                                                                                     break;
  /////////////////////////////////////////////////////
////////         18            ////////////////// ����� ������//////////////////////////////////////

                                                                   case 158:
                                                                        k++;
                                                                        f.setx1x2(f.x1New(k), f.x2New(k));
                                                                         c = "      �������� �������� � ����� x^{" + k + "}:n{}"+
                                                                            "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                                                                        b="";
                                                                            nextStep = step + 1;

                                                                              break;
                                                                               case 159:

                                                                                 if (f.gNorm() > f.eps1)
                                                                                  {
                                                                                c ="       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                 "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                                                                                 f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                                                                             b="";
                                                                                  nextStep = step + 1;

                                                                                 }
                                                                                  else
                                                                                  {   if (f.gNorm() <= f.eps1)
                                                                                   {
                                                                                    c= "       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                     "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                                                                                     f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                                                                                     "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                                                                      "}e{} - ������� ����������� � �������.";
                                                                                  b="";
                                                                                       nextStep =-1;
                                                                                 }

                                                                                 }

                                                                                  break;
                                                                                 case 160:
                                                                                if ( k < M )
                                                                                { c = "      �������� ���������� ������� k \u2265 M: k= " + k + " < "+ M +"n{}" +
                                                                                 " ������� �� �����������, ������ ��������� � ���������� ����.";
                                                                                  b = "";
                                                                                   nextStep = step + 1;}
                                                                                 else

      {   c= " �������� ���������� ������� k \u2265 M: k = " + k + " \u2265 "+ M +" = M  " +"n{}"+
          " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
            "}e{} - ������� ����������� � �������.";
            b="";
            nextStep =-1;

        }


                                                                                   break;
                                                                                    case 161:
                                                                                c = "       ��������� ������� H(x^{" + k + "}) = " + f.hStr();
                                                                                b="";
                                                                              nextStep = step + 1;

                                                                            break;
                                                                          case 162:
                                                                          c = "       ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k +"}*E = " +f.h_plusStr(17);
                                                                          b="";
                                                                          nextStep = step + 1;
                                                                          break;

                                                                          case 163:
                                                                          c = "       ��������� �������� �������� �������, ���������� �� n{}"+

                                                                          // b = "H^{-1}(x^{" + k + "}) = " + f.h_1Str()+"n{}"+
                                                                          "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(17);
                                                                      b="";
                                                                          nextStep = step + 1;
                                                                          break;

                                                                           case 164:
                                                                            c = "       ������ ���������: n{}"+
                                                                               " d_{" + k + "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(17) + "#{" +
                                                                           f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                                                                           b="";
                                                                             nextStep = step + 1;
                                                                           break;

                                                                            case 165:
                                                                           c = "        ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" +
                                                                           k + "} = " +
                                                                           "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                                                                           "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                                                                            b="";
                                                                             nextStep = step + 1;
                                                                              totalPoints++;
                                                                              // nextStep =-1;
                                                                                break;

                                                                                 case 166:
                                                                          // c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): ";
                                                                            if (f.dFuncNorm_a() < f.dFuncNorm_b()) {
                                                                          c = "        ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                               "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k + "}) "+".n{}"+
                                                                            " ������� �����������, ������� �������� k = k+1 = "+(k)+"+1 = "+(k+1)+" , n{} \u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+f.Vektor[17]/2 +
                                                                            " � ��������� � ���� 5.";
                                                                        b="";
                                                                            nextStep = step + 1;
                                                                         // k++;
                                                                           }
                                                                            else {
                                                                             c = "       ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                                 "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                                                                             " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                                                                              " n{}� ��������� � ���� 9.";
                                                                          b="";
                                                                               //f.setx1x2(f.x1New(), f.x2New());
                                                                                nextStep = -1;
                                                                                 //  k++;
                                                                                  }
                                                                                  //  nextStep = -1;
                                                                                     break;
  /////////////////////////////////////////////////////
////////         19            ////////////////// ����� ������//////////////////////////////////////

                                                                   case 167:
                                                                        k++;
                                                                        f.setx1x2(f.x1New(k), f.x2New(k));
                                                                         c = "      �������� �������� � ����� x^{" + k + "}:n{}"+
                                                                            "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                                                                        b="";
                                                                            nextStep = step + 1;

                                                                              break;
                                                                               case 168:

                                                                                 if (f.gNorm() > f.eps1)
                                                                                  {
                                                                                c ="       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                 "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                                                                                 f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                                                                             b="";
                                                                                  nextStep = step + 1;

                                                                                 }
                                                                                  else
                                                                                  {   if (f.gNorm() <= f.eps1)
                                                                                   {
                                                                                    c= "       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                     "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                                                                                     f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                                                                                     "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                                                                      "}e{} - ������� ����������� � �������.";
                                                                                  b="";
                                                                                       nextStep =-1;
                                                                                 }

                                                                                 }

                                                                                  break;
                                                                                 case 169:
                                                                                if ( k < M )
                                                                                { c = "      �������� ���������� ������� k \u2265 M: k= " + k + " < "+ M +"n{}" +
                                                                                 " ������� �� �����������, ������ ��������� � ���������� ����.";
                                                                                  b = "";
                                                                                   nextStep = step + 1;}
                                                                                 else

        {   c= " �������� ���������� ������� k \u2265 M: k = " + k + " \u2265 "+ M +" = M  " +"n{}"+
            " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
              "}e{} - ������� ����������� � �������.";
              b="";
              nextStep =-1;

          }



                                                                                   break;
                                                                                    case 170:
                                                                                c = "       ��������� ������� H(x^{" + k + "}) = " + f.hStr();
                                                                                b="";
                                                                              nextStep = step + 1;

                                                                            break;
                                                                          case 171:
                                                                          c = "       ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k +"}*E = " +f.h_plusStr(18);
                                                                          b="";
                                                                          nextStep = step + 1;
                                                                          break;

                                                                          case 172:
                                                                          c = "       ��������� �������� �������� �������, ���������� �� n{}"+

                                                                          // b = "H^{-1}(x^{" + k + "}) = " + f.h_1Str()+"n{}"+
                                                                          "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(18);
                                                                      b="";
                                                                          nextStep = step + 1;
                                                                          break;

                                                                           case 173:
                                                                            c = "       ������ ���������: n{}"+
                                                                               " d_{" + k + "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(18) + "#{" +
                                                                           f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                                                                           b="";
                                                                             nextStep = step + 1;
                                                                           break;

                                                                            case 174:
                                                                           c = "        ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" +
                                                                           k + "} = " +
                                                                           "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                                                                           "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                                                                            b="";
                                                                             nextStep = step + 1;
                                                                              totalPoints++;
                                                                              // nextStep =-1;
                                                                                break;

                                                                                 case 175:
                                                                          // c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): ";
                                                                            if (f.dFuncNorm_a() < f.dFuncNorm_b()) {
                                                                          c = "        ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                               "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k + "}) "+".n{}"+
                                                                            " ������� �����������, ������� �������� k = k+1 = "+(k)+"+1 = "+(k+1)+" , n{} \u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+f.Vektor[18]/2 +
                                                                            " � ��������� � ���� 5.";
                                                                        b="";
                                                                            nextStep = step + 1;
                                                                         // k++;
                                                                           }
                                                                            else {
                                                                             c = "       ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                                 "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                                                                             " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                                                                              " n{}� ��������� � ���� 9.";
                                                                          b="";
                                                                               //f.setx1x2(f.x1New(), f.x2New());
                                                                                nextStep = -1;
                                                                                 //  k++;
                                                                                  }
                                                                                  //  nextStep = -1;
                                                                                     break;
  /////////////////////////////////////////////////////
////////         20            ////////////////// ����� ������//////////////////////////////////////

                                                                   case 176:
                                                                        k++;
                                                                        f.setx1x2(f.x1New(k), f.x2New(k));
                                                                         c = "      �������� �������� � ����� x^{" + k + "}:n{}"+
                                                                            "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                                                                        b="";
                                                                            nextStep = step + 1;

                                                                              break;
                                                                               case 177:

                                                                                 if (f.gNorm() > f.eps1)
                                                                                  {
                                                                                c ="       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                 "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                                                                                 f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                                                                             b="";
                                                                                  nextStep = step + 1;

                                                                                 }
                                                                                  else
                                                                                  {   if (f.gNorm() <= f.eps1)
                                                                                   {
                                                                                    c= "       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                     "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                                                                                     f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                                                                                     "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                                                                      "}e{} - ������� ����������� � �������.";
                                                                                  b="";
                                                                                       nextStep =-1;
                                                                                 }

                                                                                 }

                                                                                  break;
                                                                                 case 178:
                                                                                if ( k < M )
                                                                                { c = "      �������� ���������� ������� k \u2265 M: k= " + k + " < "+ M +"n{}" +
                                                                                 " ������� �� �����������, ������ ��������� � ���������� ����.";
                                                                                  b = "";
                                                                                   nextStep = step + 1;}
                                                                                 else

     {   c= " �������� ���������� ������� k \u2265 M: k = " + k + " \u2265 "+ M +" = M  " +"n{}"+
         " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
           "}e{} - ������� ����������� � �������.";
           b="";
           nextStep =-1;

       }



                                                                                   break;
                                                                                    case 179:
                                                                                c = "       ��������� ������� H(x^{" + k + "}) = " + f.hStr();
                                                                                b="";
                                                                              nextStep = step + 1;

                                                                            break;
                                                                          case 180:
                                                                          c = "       ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k +"}*E = " +f.h_plusStr(19);
                                                                          b="";
                                                                          nextStep = step + 1;
                                                                          break;

                                                                          case 181:
                                                                          c = "       ��������� �������� �������� �������, ���������� �� n{}"+

                                                                          // b = "H^{-1}(x^{" + k + "}) = " + f.h_1Str()+"n{}"+
                                                                          "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(19);
                                                                      b="";
                                                                          nextStep = step + 1;
                                                                          break;

                                                                           case 182:
                                                                            c = "       ������ ���������: n{}"+
                                                                               " d_{" + k + "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(19) + "#{" +
                                                                           f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                                                                           b="";
                                                                             nextStep = step + 1;
                                                                           break;

                                                                            case 183:
                                                                           c = "        ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" +
                                                                           k + "} = " +
                                                                           "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                                                                           "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                                                                            b="";
                                                                             nextStep = step + 1;
                                                                              totalPoints++;
                                                                              // nextStep =-1;
                                                                                break;

                                                                                 case 184:
                                                                          // c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): ";
                                                                            if (f.dFuncNorm_a() < f.dFuncNorm_b()) {
                                                                          c = "        ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                               "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k + "}) "+".n{}"+
                                                                            " ������� �����������, ������� �������� k = k+1 = "+(k)+"+1 = "+(k+1)+" , n{} \u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+f.Vektor[19]/2 +
                                                                            " � ��������� � ���� 5.";
                                                                        b="";
                                                                            nextStep = step + 1;
                                                                         // k++;
                                                                           }
                                                                            else {
                                                                             c = "       ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                                 "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                                                                             " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                                                                              " n{}� ��������� � ���� 9.";
                                                                          b="";
                                                                               //f.setx1x2(f.x1New(), f.x2New());
                                                                                nextStep = -1;
                                                                                 //  k++;
                                                                                  }
                                                                                  //  nextStep = -1;
                                                                                     break;
  /////////////////////////////////////////////////////
////////         21           ////////////////// ����� ������//////////////////////////////////////

                                                                   case 185:
                                                                        k++;
                                                                        f.setx1x2(f.x1New(k), f.x2New(k));
                                                                         c = "      �������� �������� � ����� x^{" + k + "}:n{}"+
                                                                            "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                                                                        b="";
                                                                            nextStep = step + 1;

                                                                              break;
                                                                               case 186:

                                                                                 if (f.gNorm() > f.eps1)
                                                                                  {
                                                                                c ="       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                 "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                                                                                 f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                                                                             b="";
                                                                                  nextStep = step + 1;

                                                                                 }
                                                                                  else
                                                                                  {   if (f.gNorm() <= f.eps1)
                                                                                   {
                                                                                    c= "       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                     "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                                                                                     f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                                                                                     "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                                                                      "}e{} - ������� ����������� � �������.";
                                                                                  b="";
                                                                                       nextStep =-1;
                                                                                 }

                                                                                 }

                                                                                  break;
                                                                                 case 187:
                                                                                if ( k < M )
                                                                                { c = "      �������� ���������� ������� k \u2265 M: k= " + k + " < "+ M +"n{}" +
                                                                                 " ������� �� �����������, ������ ��������� � ���������� ����.";
                                                                                  b = "";
                                                                                   nextStep = step + 1;}
                                                                                 else

      {   c= " �������� ���������� ������� k \u2265 M: k = " + k + " \u2265 "+ M +" = M  " +"n{}"+
          " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
            "}e{} - ������� ����������� � �������.";
            b="";
            nextStep =-1;

        }



                                                                                   break;
                                                                                    case 188:
                                                                                c = "       ��������� ������� H(x^{" + k + "}) = " + f.hStr();
                                                                                b="";
                                                                              nextStep = step + 1;

                                                                            break;
                                                                          case 189:
                                                                          c = "       ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k +"}*E = " +f.h_plusStr(20);
                                                                          b="";
                                                                          nextStep = step + 1;
                                                                          break;

                                                                          case 190:
                                                                          c = "       ��������� �������� �������� �������, ���������� �� n{}"+

                                                                          // b = "H^{-1}(x^{" + k + "}) = " + f.h_1Str()+"n{}"+
                                                                          "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(20);
                                                                      b="";
                                                                          nextStep = step + 1;
                                                                          break;

                                                                           case 191:
                                                                            c = "       ������ ���������: n{}"+
                                                                               " d_{" + k + "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(20) + "#{" +
                                                                           f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                                                                           b="";
                                                                             nextStep = step + 1;
                                                                           break;

                                                                            case 192:
                                                                           c = "        ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" +
                                                                           k + "} = " +
                                                                           "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                                                                           "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                                                                            b="";
                                                                             nextStep = step + 1;
                                                                              totalPoints++;
                                                                              // nextStep =-1;
                                                                                break;

                                                                                 case 193:
                                                                          // c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): ";
                                                                            if (f.dFuncNorm_a() < f.dFuncNorm_b()) {
                                                                          c = "        ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                               "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k + "}) "+".n{}"+
                                                                            " ������� �����������, ������� �������� k = k+1 = "+(k)+"+1 = "+(k+1)+" , n{} \u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+f.Vektor[20]/2 +
                                                                            " � ��������� � ���� 5.";
                                                                        b="";
                                                                            nextStep = step + 1;
                                                                        //  k++;
                                                                           }
                                                                            else {
                                                                             c = "       ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                                 "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                                                                             " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                                                                              " n{}� ��������� � ���� 9.";
                                                                          b="";
                                                                               //f.setx1x2(f.x1New(), f.x2New());
                                                                                nextStep = -1;
                                                                                 //  k++;
                                                                                  }
                                                                                    //nextStep = -1;
                                                                                     break;

////////         22           ////////////////// ����� ������//////////////////////////////////////

                                                                   case 194:
                                                                        k++;
                                                                        f.setx1x2(f.x1New(k), f.x2New(k));
                                                                         c = "      �������� �������� � ����� x^{" + k + "}:n{}"+
                                                                            "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                                                                        b="";
                                                                            nextStep = step + 1;

                                                                              break;
                                                                               case 195:

                                                                                 if (f.gNorm() > f.eps1)
                                                                                  {
                                                                                c ="       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                 "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                                                                                 f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                                                                             b="";
                                                                                  nextStep = step + 1;

                                                                                 }
                                                                                  else
                                                                                  {   if (f.gNorm() <= f.eps1)
                                                                                   {
                                                                                    c= "       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                     "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                                                                                     f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                                                                                     "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                                                                      "}e{} - ������� ����������� � �������.";
                                                                                  b="";
                                                                                       nextStep =-1;
                                                                                 }

                                                                                 }

                                                                                  break;
                                                                                 case 196:
                                                                                if ( k < M )
                                                                                { c = "      �������� ���������� ������� k \u2265 M: k= " + k + " < "+ M +"n{}" +
                                                                                 " ������� �� �����������, ������ ��������� � ���������� ����.";
                                                                                  b = "";
                                                                                   nextStep = step + 1;}
                                                                                 else

      {   c= " �������� ���������� ������� k \u2265 M: k = " + k + " \u2265 "+ M +" = M  " +"n{}"+
          " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
            "}e{} - ������� ����������� � �������.";
            b="";
            nextStep =-1;

        }



                                                                                   break;
                                                                                    case 197:
                                                                                c = "       ��������� ������� H(x^{" + k + "}) = " + f.hStr();
                                                                                b="";
                                                                              nextStep = step + 1;

                                                                            break;
                                                                          case 198:
                                                                          c = "       ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k +"}*E = " +f.h_plusStr(21);
                                                                          b="";
                                                                          nextStep = step + 1;
                                                                          break;

                                                                          case 199:
                                                                          c = "       ��������� �������� �������� �������, ���������� �� n{}"+

                                                                          // b = "H^{-1}(x^{" + k + "}) = " + f.h_1Str()+"n{}"+
                                                                          "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(21);
                                                                      b="";
                                                                          nextStep = step + 1;
                                                                          break;

                                                                           case 200:
                                                                            c = "       ������ ���������: n{}"+
                                                                               " d_{" + k + "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(21) + "#{" +
                                                                           f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                                                                           b="";
                                                                             nextStep = step + 1;
                                                                           break;

                                                                            case 201:
                                                                           c = "        ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" +
                                                                           k + "} = " +
                                                                           "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                                                                           "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                                                                            b="";
                                                                             nextStep = step + 1;
                                                                              totalPoints++;
                                                                              // nextStep =-1;
                                                                                break;

                                                                                 case 202:
                                                                          // c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): ";
                                                                            if (f.dFuncNorm_a() < f.dFuncNorm_b()) {
                                                                          c = "        ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                               "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k + "}) "+".n{}"+
                                                                            " ������� �����������, ������� �������� k = k+1 = "+(k)+"+1 = "+(k+1)+" , n{} \u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+f.Vektor[21]/2 +
                                                                            " � ��������� � ���� 5.";
                                                                        b="";
                                                                            nextStep = step + 1;
                                                                        //  k++;
                                                                           }
                                                                            else {
                                                                             c = "       ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                                 "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                                                                             " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                                                                              " n{}� ��������� � ���� 9.";
                                                                          b="";
                                                                               //f.setx1x2(f.x1New(), f.x2New());
                                                                                nextStep = -1;
                                                                                 //  k++;
                                                                                  }
                                                                                    //nextStep = -1;
  break;
////////         23           ////////////////// ����� ������//////////////////////////////////////

                                                                   case 203:
                                                                        k++;
                                                                        f.setx1x2(f.x1New(k), f.x2New(k));
                                                                         c = "      �������� �������� � ����� x^{" + k + "}:n{}"+
                                                                            "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                                                                        b="";
                                                                            nextStep = step + 1;

                                                                              break;
                                                                               case 204:

                                                                                 if (f.gNorm() > f.eps1)
                                                                                  {
                                                                                c ="       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                 "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                                                                                 f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                                                                             b="";
                                                                                  nextStep = step + 1;

                                                                                 }
                                                                                  else
                                                                                  {   if (f.gNorm() <= f.eps1)
                                                                                   {
                                                                                    c= "       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                     "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                                                                                     f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                                                                                     "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                                                                      "}e{} - ������� ����������� � �������.";
                                                                                  b="";
                                                                                       nextStep =-1;
                                                                                 }

                                                                                 }

                                                                                  break;
                                                                                 case 205:
                                                                                if ( k < M )
                                                                                { c = "      �������� ���������� ������� k \u2265 M: k= " + k + " < "+ M +"n{}" +
                                                                                 " ������� �� �����������, ������ ��������� � ���������� ����.";
                                                                                  b = "";
                                                                                   nextStep = step + 1;}
                                                                                 else

       {   c= " �������� ���������� ������� k \u2265 M: k = " + k + " \u2265 "+ M +" = M  " +"n{}"+
           " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
             "}e{} - ������� ����������� � �������.";
             b="";
             nextStep =-1;

         }



                                                                                   break;
                                                                                    case 206:
                                                                                c = "       ��������� ������� H(x^{" + k + "}) = " + f.hStr();
                                                                                b="";
                                                                              nextStep = step + 1;

                                                                            break;
                                                                          case 207:
                                                                          c = "       ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k +"}*E = " +f.h_plusStr(22);
                                                                          b="";
                                                                          nextStep = step + 1;
                                                                          break;

                                                                          case 208:
                                                                          c = "       ��������� �������� �������� �������, ���������� �� n{}"+

                                                                          // b = "H^{-1}(x^{" + k + "}) = " + f.h_1Str()+"n{}"+
                                                                          "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(22);
                                                                      b="";
                                                                          nextStep = step + 1;
                                                                          break;

                                                                           case 209:
                                                                            c = "       ������ ���������: n{}"+
                                                                               " d_{" + k + "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(22) + "#{" +
                                                                           f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                                                                           b="";
                                                                             nextStep = step + 1;
                                                                           break;

                                                                            case 210:
                                                                           c = "        ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" +
                                                                           k + "} = " +
                                                                           "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                                                                           "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                                                                            b="";
                                                                             nextStep = step + 1;
                                                                              totalPoints++;
                                                                              // nextStep =-1;
                                                                                break;

                                                                                 case 211:
                                                                          // c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): ";
                                                                            if (f.dFuncNorm_a() < f.dFuncNorm_b()) {
                                                                          c = "        ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                               "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k + "}) "+".n{}"+
                                                                            " ������� �����������, ������� �������� k = k+1 = "+(k)+"+1 = "+(k+1)+" , n{} \u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+f.Vektor[22]/2 +
                                                                            " � ��������� � ���� 5.";
                                                                        b="";
                                                                            nextStep = step + 1;
                                                                         // k++;
                                                                           }
                                                                            else {
                                                                             c = "       ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                                 "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                                                                             " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                                                                              " n{}� ��������� � ���� 9.";
                                                                          b="";
                                                                               //f.setx1x2(f.x1New(), f.x2New());
                                                                                nextStep = -1;
                                                                                 //  k++;
                                                                                  }
                                                                                  //  nextStep = -1;
   break;
////////         24           ////////////////// ����� ������//////////////////////////////////////

                                                                   case 212:
                                                                        k++;
                                                                        f.setx1x2(f.x1New(k), f.x2New(k));
                                                                         c = "      �������� �������� � ����� x^{" + k + "}:n{}"+
                                                                            "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                                                                        b="";
                                                                            nextStep = step + 1;

                                                                              break;
                                                                               case 213:

                                                                                 if (f.gNorm() > f.eps1)
                                                                                  {
                                                                                c ="       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                 "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                                                                                 f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                                                                             b="";
                                                                                  nextStep = step + 1;

                                                                                 }
                                                                                  else
                                                                                  {   if (f.gNorm() <= f.eps1)
                                                                                   {
                                                                                    c= "       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                     "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                                                                                     f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                                                                                     "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                                                                      "}e{} - ������� ����������� � �������.";
                                                                                  b="";
                                                                                       nextStep =-1;
                                                                                 }

                                                                                 }

                                                                                  break;
                                                                                 case 214:
                                                                                if ( k < M )
                                                                                { c = "      �������� ���������� ������� k \u2265 M: k= " + k + " < "+ M +"n{}" +
                                                                                 " ������� �� �����������, ������ ��������� � ���������� ����.";
                                                                                  b = "";
                                                                                   nextStep = step + 1;}
                                                                                 else

      {   c= " �������� ���������� ������� k \u2265 M: k = " + k + " \u2265 "+ M +" = M  " +"n{}"+
          " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
            "}e{} - ������� ����������� � �������.";
            b="";
            nextStep =-1;

        }


                                                                                   break;
                                                                                    case 215:
                                                                                c = "       ��������� ������� H(x^{" + k + "}) = " + f.hStr();
                                                                                b="";
                                                                              nextStep = step + 1;

                                                                            break;
                                                                          case 216:
                                                                          c = "       ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k +"}*E = " +f.h_plusStr(23);
                                                                          b="";
                                                                          nextStep = step + 1;
                                                                          break;

                                                                          case 217:
                                                                          c = "       ��������� �������� �������� �������, ���������� �� n{}"+

                                                                          // b = "H^{-1}(x^{" + k + "}) = " + f.h_1Str()+"n{}"+
                                                                          "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(23);
                                                                      b="";
                                                                          nextStep = step + 1;
                                                                          break;

                                                                           case 218:
                                                                            c = "       ������ ���������: n{}"+
                                                                               " d_{" + k + "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(23) + "#{" +
                                                                           f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                                                                           b="";
                                                                             nextStep = step + 1;
                                                                           break;

                                                                            case 219:
                                                                           c = "        ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" +
                                                                           k + "} = " +
                                                                           "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                                                                           "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                                                                            b="";
                                                                             nextStep = step + 1;
                                                                              totalPoints++;
                                                                              // nextStep =-1;
                                                                                break;

                                                                                 case 220:
                                                                          // c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): ";
                                                                            if (f.dFuncNorm_a() < f.dFuncNorm_b()) {
                                                                          c = "        ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                               "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k + "}) "+".n{}"+
                                                                            " ������� �����������, ������� �������� k = k+1 = "+(k)+"+1 = "+(k+1)+" , n{} \u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+f.Vektor[23]/2 +
                                                                            " � ��������� � ���� 5.";
                                                                        b="";
                                                                            nextStep = step + 1;
                                                                        //  k++;
                                                                           }
                                                                            else {
                                                                             c = "       ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                                 "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                                                                             " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                                                                              " n{}� ��������� � ���� 9.";
                                                                          b="";
                                                                               //f.setx1x2(f.x1New(), f.x2New());
                                                                                nextStep = -1;
                                                                                 //  k++;
                                                                                  }
                                                                                  //  nextStep = -1;
 break;
////////         25           ////////////////// ����� ������//////////////////////////////////////

                                                                   case 221:
                                                                        k++;
                                                                        f.setx1x2(f.x1New(k), f.x2New(k));
                                                                         c = "      �������� �������� � ����� x^{" + k + "}:n{}"+
                                                                            "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                                                                        b="";
                                                                            nextStep = step + 1;

                                                                              break;
                                                                               case 222:

                                                                                 if (f.gNorm() > f.eps1)
                                                                                  {
                                                                                c ="       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                 "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                                                                                 f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                                                                             b="";
                                                                                  nextStep = step + 1;

                                                                                 }
                                                                                  else
                                                                                  {   if (f.gNorm() <= f.eps1)
                                                                                   {
                                                                                    c= "       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                     "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                                                                                     f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                                                                                     "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                                                                      "}e{} - ������� ����������� � �������.";
                                                                                  b="";
                                                                                       nextStep =-1;
                                                                                 }

                                                                                 }

                                                                                  break;
                                                                                 case 223:
                                                                                if ( k < M )
                                                                                { c = "      �������� ���������� ������� k \u2265 M: k= " + k + " < "+ M +"n{}" +
                                                                                 " ������� �� �����������, ������ ��������� � ���������� ����.";
                                                                                  b = "";
                                                                                   nextStep = step + 1;}
                                                                                 else

      {   c= " �������� ���������� ������� k \u2265 M: k = " + k + " \u2265 "+ M +" = M  " +"n{}"+
          " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
            "}e{} - ������� ����������� � �������.";
            b="";
            nextStep =-1;

        }



                                                                                   break;
                                                                                    case 224:
                                                                                c = "       ��������� ������� H(x^{" + k + "}) = " + f.hStr();
                                                                                b="";
                                                                              nextStep = step + 1;

                                                                            break;
                                                                          case 225:
                                                                          c = "       ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k +"}*E = " +f.h_plusStr(24);
                                                                          b="";
                                                                          nextStep = step + 1;
                                                                          break;

                                                                          case 226:
                                                                          c = "       ��������� �������� �������� �������, ���������� �� n{}"+

                                                                          // b = "H^{-1}(x^{" + k + "}) = " + f.h_1Str()+"n{}"+
                                                                          "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(24);
                                                                      b="";
                                                                          nextStep = step + 1;
                                                                          break;

                                                                           case 227:
                                                                            c = "       ������ ���������: n{}"+
                                                                               " d_{" + k + "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(24) + "#{" +
                                                                           f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                                                                           b="";
                                                                             nextStep = step + 1;
                                                                           break;

                                                                            case 228:
                                                                           c = "        ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" +
                                                                           k + "} = " +
                                                                           "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                                                                           "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                                                                            b="";
                                                                             nextStep = step + 1;
                                                                              totalPoints++;
                                                                              // nextStep =-1;
                                                                                break;

  case 229:
                                                                          // c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): ";
                                                                            if (f.dFuncNorm_a() < f.dFuncNorm_b()) {
                                                                          c = "        ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                               "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k + "}) "+".n{}"+
                                                                            " ������� �����������, ������� �������� k = k+1 = "+(k)+"+1 = "+(k+1)+" , n{} \u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+f.Vektor[24]/2 +
                                                                            " � ��������� � ���� 5.";
                                                                        b="";
                                                                            nextStep = step + 1;
                                                                         // k++;
                                                                           }
                                                                            else {
                                                                             c = "       ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                                 "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                                                                             " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                                                                              " n{}� ��������� � ���� 9.";
                                                                          b="";
                                                                               //f.setx1x2(f.x1New(), f.x2New());
                                                                                nextStep = -1;
                                                                                 //  k++;
                                                                                  }
                                 // nextStep = -1;
break;
                          ////////         26           ////////////////// ����� ������//////////////////////////////////////

                                                                                             case 230:
                                                                                                  k++;
                                                                                                  f.setx1x2(f.x1New(k), f.x2New(k));
                                                                                                   c = "      �������� �������� � ����� x^{" + k + "}:n{}"+
                                                                                                      "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                                                                                                  b="";
                                                                                                      nextStep = step + 1;

                                                                                                        break;
                                                                                                         case 231:

                                                                                                           if (f.gNorm() > f.eps1)
                                                                                                            {
                                                                                                          c ="       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                                           "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                                                                                                           f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                                                                                                       b="";
                                                                                                            nextStep = step + 1;

                                                                                                           }
                                                                                                            else
                                                                                                            {   if (f.gNorm() <= f.eps1)
                                                                                                             {
                                                                                                              c= "       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                                               "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                                                                                                               f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                                                                                                               "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                                                                                                "}e{} - ������� ����������� � �������.";
                                                                                                            b="";
                                                                                                                 nextStep =-1;
                                                                                                           }

                                                                                                           }

                                                                                                            break;
                                                                                                           case 232:
                                                                                                          if ( k < M )
                                                                                                          { c = "      �������� ���������� ������� k \u2265 M: k= " + k + " < "+ M +"n{}" +
                                                                                                           " ������� �� �����������, ������ ��������� � ���������� ����.";
                                                                                                            b = "";
                                                                                                             nextStep = step + 1;}
                                                                                                           else

       {   c= " �������� ���������� ������� k \u2265 M: k = " + k + " \u2265 "+ M +" = M  " +"n{}"+
           " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
             "}e{} - ������� ����������� � �������.";
             b="";
             nextStep =-1;

         }


                                                                                                             break;
                                                                                                              case 233:
                                                                                                          c = "       ��������� ������� H(x^{" + k + "}) = " + f.hStr();
                                                                                                          b="";
                                                                                                        nextStep = step + 1;

                                                                                                      break;
                                                                                                    case 234:
                                                                                                    c = "       ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k +"}*E = " +f.h_plusStr(25);
                                                                                                    b="";
                                                                                                    nextStep = step + 1;
                                                                                                    break;

                                                                                                    case 235:
                                                                                                    c = "       ��������� �������� �������� �������, ���������� �� n{}"+

                                                                                                    // b = "H^{-1}(x^{" + k + "}) = " + f.h_1Str()+"n{}"+
                                                                                                    "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(25);
                                                                                                b="";
                                                                                                    nextStep = step + 1;
                                                                                                    break;

                                                                                                     case 236:
                                                                                                      c = "       ������ ���������: n{}"+
                                                                                                         " d_{" + k + "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(25) + "#{" +
                                                                                                     f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                                                                                                     b="";
                                                                                                       nextStep = step + 1;
                                                                                                     break;

                                                                                                      case 237:
                                                                                                     c = "        ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" +
                                                                                                     k + "} = " +
                                                                                                     "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                                                                                                     "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                                                                                                      b="";
                                                                                                       nextStep = step + 1;
                                                                                                        totalPoints++;
                                                                                                        // nextStep =-1;
                                                                                                          break;

                      case 238:
                                                                                                    // c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): ";
                                                                                                      if (f.dFuncNorm_a() < f.dFuncNorm_b()) {
                                                                                                    c = "        ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                                                         "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k + "}) "+".n{}"+
                                                                                                      " ������� �����������, ������� �������� k = k+1 = "+(k)+"+1 = "+(k+1)+" , n{} \u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+f.Vektor[25]/2 +
                                                                                                      " � ��������� � ���� 5.";
                                                                                                  b="";
                                                                                                      nextStep = step + 1;
                                                                                                   // k++;
                                                                                                     }
                                                                                                      else {
                                                                                                       c = "       ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                                                           "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                                                                                                       " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                                                                                                        " n{}� ��������� � ���� 9.";
                                                                                                    b="";
                                                                                                         //f.setx1x2(f.x1New(), f.x2New());
                                                                                                          nextStep = -1;
                                                                                                           //  k++;
                                                                                                            }
                                                                                                             // nextStep = -1;
                      break;
                      ////////         27           ////////////////// ����� ������//////////////////////////////////////

                                                                                        case 239:
                                                                                             k++;
                                                                                             f.setx1x2(f.x1New(k), f.x2New(k));
                                                                                              c = "      �������� �������� � ����� x^{" + k + "}:n{}"+
                                                                                                 "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                                                                                             b="";
                                                                                                 nextStep = step + 1;

                                                                                                   break;
                                                                                                    case 240:

                                                                                                      if (f.gNorm() > f.eps1)
                                                                                                       {
                                                                                                     c ="       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                                      "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                                                                                                      f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                                                                                                  b="";
                                                                                                       nextStep = step + 1;

                                                                                                      }
                                                                                                       else
                                                                                                       {   if (f.gNorm() <= f.eps1)
                                                                                                        {
                                                                                                         c= "       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                                          "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                                                                                                          f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                                                                                                          "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                                                                                           "}e{} - ������� ����������� � �������.";
                                                                                                       b="";
                                                                                                            nextStep =-1;
                                                                                                      }

                                                                                                      }

                                                                                                       break;
                                                                                                      case 241:
                                                                                                     if ( k < M )
                                                                                                     { c = "      �������� ���������� ������� k \u2265 M: k= " + k + " < "+ M +"n{}" +
                                                                                                      " ������� �� �����������, ������ ��������� � ���������� ����.";
                                                                                                       b = "";
                                                                                                        nextStep = step + 1;}
                                                                                                      else

      {   c= " �������� ���������� ������� k \u2265 M: k = " + k + " \u2265 "+ M +" = M  " +"n{}"+
          " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
            "}e{} - ������� ����������� � �������.";
            b="";
            nextStep =-1;

        }



                                                                                                        break;
                                                                                                         case 242:
                                                                                                     c = "       ��������� ������� H(x^{" + k + "}) = " + f.hStr();
                                                                                                     b="";
                                                                                                   nextStep = step + 1;

                                                                                                 break;
                                                                                               case 243:
                                                                                               c = "       ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k +"}*E = " +f.h_plusStr(26);
                                                                                               b="";
                                                                                               nextStep = step + 1;
                                                                                               break;

                                                                                               case 244:
                                                                                               c = "       ��������� �������� �������� �������, ���������� �� n{}"+

                                                                                               // b = "H^{-1}(x^{" + k + "}) = " + f.h_1Str()+"n{}"+
                                                                                               "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(26);
                                                                                           b="";
                                                                                               nextStep = step + 1;
                                                                                               break;

                                                                                                case 245:
                                                                                                 c = "       ������ ���������: n{}"+
                                                                                                    " d_{" + k + "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(26) + "#{" +
                                                                                                f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                                                                                                b="";
                                                                                                  nextStep = step + 1;
                                                                                                break;

                                                                                                 case 246:
                                                                                                c = "        ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" +
                                                                                                k + "} = " +
                                                                                                "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                                                                                                "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                                                                                                 b="";
                                                                                                  nextStep = step + 1;
                                                                                                   totalPoints++;
                                                                                                   // nextStep =-1;
                                                                                                     break;

                                                                                                      case 247:
                                                                                               // c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): ";
                                                                                                 if (f.dFuncNorm_a() < f.dFuncNorm_b()) {
                                                                                               c = "        ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                                                    "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k + "}) "+".n{}"+
                                                                                                 " ������� �����������, ������� �������� k = k+1 = "+(k)+"+1 = "+(k+1)+" , n{} \u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+f.Vektor[26]/2 +
                                                                                                 " � ��������� � ���� 5.";
                                                                                             b="";
                                                                                                 nextStep = step + 1;
                                                                                              // k++;
                                                                                                }
                                                                                                 else {
                                                                                                  c = "       ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                                                      "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                                                                                                  " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                                                                                                   " n{}� ��������� � ���� 9.";
                                                                                               b="";
                                                                                                    //f.setx1x2(f.x1New(), f.x2New());
                                                                                                     nextStep = -1;
                                                                                                      //  k++;
                                                                                                       }
                                                                                                        // nextStep = -1;
                                                                                                          break;
        ////////         28          ////////////////// ����� ������//////////////////////////////////////

                                                                                                                                                                     case 248:
                                                                                                                                                                          k++;
                                                                                                                                                                          f.setx1x2(f.x1New(k), f.x2New(k));
                                                                                                                                                                           c = "      �������� �������� � ����� x^{" + k + "}:n{}"+
                                                                                                                                                                              "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                                                                                                                                                                          b="";
                                                                                                                                                                              nextStep = step + 1;

                                                                                                                                                                                break;
                                                                                                                                                                                 case 249:

                                                                                                                                                                                   if (f.gNorm() > f.eps1)
                                                                                                                                                                                    {
                                                                                                                                                                                  c ="       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                                                                                                                   "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                                                                                                                                                                                   f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                                                                                                                                                                               b="";
                                                                                                                                                                                    nextStep = step + 1;

                                                                                                                                                                                   }
                                                                                                                                                                                    else
                                                                                                                                                                                    {   if (f.gNorm() <= f.eps1)
                                                                                                                                                                                     {
                                                                                                                                                                                      c= "       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                                                                                                                       "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                                                                                                                                                                                       f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                                                                                                                                                                                       "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                                                                                                                                                                        "}e{} - ������� ����������� � �������.";
                                                                                                                                                                                    b="";
                                                                                                                                                                                         nextStep =-1;
                                                                                                                                                                                   }

                                                                                                                                                                                   }

                                                                                                                                                                                    break;
                                                                                                                                                                                   case 250:
                                                                                                                                                                                  if ( k < M )
                                                                                                                                                                                  { c = "      �������� ���������� ������� k \u2265 M: k= " + k + " < "+ M +"n{}" +
                                                                                                                                                                                   " ������� �� �����������, ������ ��������� � ���������� ����.";
                                                                                                                                                                                    b = "";
                                                                                                                                                                                     nextStep = step + 1;}
                                                                                                                                                                                   else

      {   c= " �������� ���������� ������� k \u2265 M: k = " + k + " \u2265 "+ M +" = M  " +"n{}"+
          " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
            "}e{} - ������� ����������� � �������.";
            b="";
            nextStep =-1;

        }



                                                                                                                                                                                     break;
                                                                                                                                                                                      case 251:
                                                                                                                                                                                  c = "       ��������� ������� H(x^{" + k + "}) = " + f.hStr();
                                                                                                                                                                                  b="";
                                                                                                                                                                                nextStep = step + 1;

                                                                                                                                                                              break;
                                                                                                                                                                            case 252:
                                                                                                                                                                            c = "       ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k +"}*E = " +f.h_plusStr(27);
                                                                                                                                                                            b="";
                                                                                                                                                                            nextStep = step + 1;
                                                                                                                                                                            break;

                                                                                                                                                                            case 253:
                                                                                                                                                                            c = "       ��������� �������� �������� �������, ���������� �� n{}"+

                                                                                                                                                                            // b = "H^{-1}(x^{" + k + "}) = " + f.h_1Str()+"n{}"+
                                                                                                                                                                            "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(27);
                                                                                                                                                                        b="";
                                                                                                                                                                            nextStep = step + 1;
                                                                                                                                                                            break;

                                                                                                                                                                             case 254:
                                                                                                                                                                              c = "       ������ ���������: n{}"+
                                                                                                                                                                                 " d_{" + k + "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(27) + "#{" +
                                                                                                                                                                             f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                                                                                                                                                                             b="";
                                                                                                                                                                               nextStep = step + 1;
                                                                                                                                                                             break;

                                                                                                                                                                              case 255:
                                                                                                                                                                             c = "        ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" +
                                                                                                                                                                             k + "} = " +
                                                                                                                                                                             "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                                                                                                                                                                             "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                                                                                                                                                                              b="";
                                                                                                                                                                               nextStep = step + 1;
                                                                                                                                                                                totalPoints++;
                                                                                                                                                                                // nextStep =-1;
                                                                                                                                                                                  break;

                                                                                                                                                                                   case 256:
                                                                                                                                                                            // c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): ";
                                                                                                                                                                              if (f.dFuncNorm_a() < f.dFuncNorm_b()) {
                                                                                                                                                                            c = "        ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                                                                                                                                 "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k + "}) "+".n{}"+
                                                                                                                                                                              " ������� �����������, ������� �������� k = k+1 = "+(k)+"+1 = "+(k+1)+" , n{} \u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+f.Vektor[27]/2 +
                                                                                                                                                                              " � ��������� � ���� 5.";
                                                                                                                                                                          b="";
                                                                                                                                                                              nextStep = step + 1;
                                                                                                                                                                           // k++;
                                                                                                                                                                             }
                                                                                                                                                                              else {
                                                                                                                                                                               c = "       ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                                                                                                                                   "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                                                                                                                                                                               " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                                                                                                                                                                                " n{}� ��������� � ���� 9.";
                                                                                                                                                                            b="";
                                                                                                                                                                                 //f.setx1x2(f.x1New(), f.x2New());
                                                                                                                                                                                  nextStep = -1;
                                                                                                                                                                                   //  k++;
                                                                                                                                                                                    }
                                                                                                                                                                                     // nextStep = -1;
                                                                                                                                                                                       break;
   ////////         29           ////////////////// ����� ������//////////////////////////////////////

                                                                                                                                                                                                                                                  case 257:
                                                                                                                                                                                                                                                       k++;
                                                                                                                                                                                                                                                       f.setx1x2(f.x1New(k), f.x2New(k));
                                                                                                                                                                                                                                                        c = "      �������� �������� � ����� x^{" + k + "}:n{}"+
                                                                                                                                                                                                                                                           "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                                                                                                                                                                                                                                                       b="";
                                                                                                                                                                                                                                                           nextStep = step + 1;

                                                                                                                                                                                                                                                             break;
                                                                                                                                                                                                                                                              case 258:

                                                                                                                                                                                                                                                                if (f.gNorm() > f.eps1)
                                                                                                                                                                                                                                                                 {
                                                                                                                                                                                                                                                               c ="       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                                                                                                                                                                                                "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                                                                                                                                                                                                                                                                f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                                                                                                                                                                                                                                                            b="";
                                                                                                                                                                                                                                                                 nextStep = step + 1;

                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                 else
                                                                                                                                                                                                                                                                 {   if (f.gNorm() <= f.eps1)
                                                                                                                                                                                                                                                                  {
                                                                                                                                                                                                                                                                   c= "       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                                                                                                                                                                                                    "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                                                                                                                                                                                                                                                                    f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                                                                                                                                                                                                                                                                    "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                                                                                                                                                                                                                                                     "}e{} - ������� ����������� � �������.";
                                                                                                                                                                                                                                                                 b="";
                                                                                                                                                                                                                                                                      nextStep =-1;
                                                                                                                                                                                                                                                                }

                                                                                                                                                                                                                                                                }

                                                                                                                                                                                                                                                                 break;
                                                                                                                                                                                                                                                                case 259:
                                                                                                                                                                                                                                                               if ( k < M )
                                                                                                                                                                                                                                                               { c = "      �������� ���������� ������� k \u2265 M: k= " + k + " < "+ M +"n{}" +
                                                                                                                                                                                                                                                                " ������� �� �����������, ������ ��������� � ���������� ����.";
                                                                                                                                                                                                                                                                 b = "";
                                                                                                                                                                                                                                                                  nextStep = step + 1;}
                                                                                                                                                                                                                                                                else

      {   c= " �������� ���������� ������� k \u2265 M: k = " + k + " \u2265 "+ M +" = M  " +"n{}"+
          " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
            "}e{} - ������� ����������� � �������.";
            b="";
            nextStep =-1;

        }


                                                                                                                                                                                                                                                                  break;
                                                                                                                                                                                                                                                                   case 260:
                                                                                                                                                                                                                                                               c = "       ��������� ������� H(x^{" + k + "}) = " + f.hStr();
                                                                                                                                                                                                                                                               b="";
                                                                                                                                                                                                                                                             nextStep = step + 1;

                                                                                                                                                                                                                                                           break;
                                                                                                                                                                                                                                                         case 261:
                                                                                                                                                                                                                                                         c = "       ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k +"}*E = " +f.h_plusStr(28);
                                                                                                                                                                                                                                                         b="";
                                                                                                                                                                                                                                                         nextStep = step + 1;
                                                                                                                                                                                                                                                         break;

                                                                                                                                                                                                                                                         case 262:
                                                                                                                                                                                                                                                         c = "       ��������� �������� �������� �������, ���������� �� n{}"+

                                                                                                                                                                                                                                                         // b = "H^{-1}(x^{" + k + "}) = " + f.h_1Str()+"n{}"+
                                                                                                                                                                                                                                                         "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(28);
                                                                                                                                                                                                                                                     b="";
                                                                                                                                                                                                                                                         nextStep = step + 1;
                                                                                                                                                                                                                                                         break;

                                                                                                                                                                                                                                                          case 263:
                                                                                                                                                                                                                                                           c = "       ������ ���������: n{}"+
                                                                                                                                                                                                                                                              " d_{" + k + "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(28) + "#{" +
                                                                                                                                                                                                                                                          f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                                                                                                                                                                                                                                                          b="";
                                                                                                                                                                                                                                                            nextStep = step + 1;
                                                                                                                                                                                                                                                          break;

                                                                                                                                                                                                                                                           case 264:
                                                                                                                                                                                                                                                          c = "        ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" +
                                                                                                                                                                                                                                                          k + "} = " +
                                                                                                                                                                                                                                                          "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                                                                                                                                                                                                                                                          "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                                                                                                                                                                                                                                                           b="";
                                                                                                                                                                                                                                                            nextStep = step + 1;
                                                                                                                                                                                                                                                             totalPoints++;
                                                                                                                                                                                                                                                             // nextStep =-1;
                                                                                                                                                                                                                                                               break;

                                                                                                                                                                                                                                                                case 265:
                                                                                                                                                                                                                                                         // c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): ";
                                                                                                                                                                                                                                                           if (f.dFuncNorm_a() < f.dFuncNorm_b()) {
                                                                                                                                                                                                                                                         c = "        ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                                                                                                                                                                                                              "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k + "}) "+".n{}"+
                                                                                                                                                                                                                                                           " ������� �����������, ������� �������� k = k+1 = "+(k)+"+1 = "+(k+1)+" , n{} \u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+f.Vektor[28]/2 +
                                                                                                                                                                                                                                                           " � ��������� � ���� 5.";
                                                                                                                                                                                                                                                       b="";
                                                                                                                                                                                                                                                           nextStep = step + 1;
                                                                                                                                                                                                                                                       //  k++;
                                                                                                                                                                                                                                                          }
                                                                                                                                                                                                                                                           else {
                                                                                                                                                                                                                                                            c = "       ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                                                                                                                                                                                                                "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                                                                                                                                                                                                                                                            " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                                                                                                                                                                                                                                                             " n{}� ��������� � ���� 9.";
                                                                                                                                                                                                                                                         b="";
                                                                                                                                                                                                                                                              //f.setx1x2(f.x1New(), f.x2New());
                                                                                                                                                                                                                                                               nextStep = -1;
                                                                                                                                                                                                                                                                //  k++;
                                                                                                                                                                                                                                                                 }
                                                                                                                                                                                                                                                                 //  nextStep = -1;
                                                                                                                                                                                                                                                                    break;
                                                                                   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                                                                                                                                                                                                                  case 266:
                                                                                                                                                                                                                                                                       k++;
                                                                                                                                                                                                                                                                       f.setx1x2(f.x1New(k), f.x2New(k));
                                                                                                                                                                                                                                                                        c = "      �������� �������� � ����� x^{" + k + "}:n{}"+
                                                                                                                                                                                                                                                                           "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                                                                                                                                                                                                                                                                       b="";
                                                                                                                                                                                                                                                                           nextStep = step + 1;

                                                                                                                                                                                                                                                                             break;
                                                                                                                                                                                                                                                                              case 267:

                                                                                                                                                                                                                                                                                if (f.gNorm() > f.eps1)
                                                                                                                                                                                                                                                                                 {
                                                                                                                                                                                                                                                                               c ="       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                                                                                                                                                                                                                "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                                                                                                                                                                                                                                                                                f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                                                                                                                                                                                                                                                                            b="";
                                                                                                                                                                                                                                                                                 nextStep = step + 1;

                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                 else
                                                                                                                                                                                                                                                                                 {   if (f.gNorm() <= f.eps1)
                                                                                                                                                                                                                                                                                  {
                                                                                                                                                                                                                                                                                   c= "       �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                                                                                                                                                                                                                    "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                                                                                                                                                                                                                                                                                    f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                                                                                                                                                                                                                                                                                    "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                                                                                                                                                                                                                                                                     "}e{} - ������� ����������� � �������.";
                                                                                                                                                                                                                                                                                 b="";
                                                                                                                                                                                                                                                                                      nextStep =-1;
                                                                                                                                                                                                                                                                                }

                                                                                                                                                                                                                                                                                }

                                                                                                                                                                                                                                                                                 break;
                                                                                                                                                                                                                                                                                case 268:
                                                                                                                                                                                                                                                                               if ( k < M )
                                                                                                                                                                                                                                                                               { c = "      �������� ���������� ������� k \u2265 M: k= " + k + " < "+ M +"n{}" +
                                                                                                                                                                                                                                                                                " ������� �� �����������, ������ ��������� � ���������� ����.";
                                                                                                                                                                                                                                                                                 b = "";
                                                                                                                                                                                                                                                                                  nextStep = step + 1;}
                                                                                                                                                                                                                                                                                else

     {   c= " �������� ���������� ������� k \u2265 M: k = " + k + " \u2265 "+ M +" = M  " +"n{}"+
         " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
           "}e{} - ������� ����������� � �������.";
           b="";
           nextStep =-1;

       }



                                                                                                                                                                                                                                                                                  break;
                                                                                                                                                                                                                                                                                   case 269:
                                                                                                                                                                                                                                                                               c = "       ��������� ������� H(x^{" + k + "}) = " + f.hStr();
                                                                                                                                                                                                                                                                               b="";
                                                                                                                                                                                                                                                                             nextStep = step + 1;

                                                                                                                                                                                                                                                                           break;
                                                                                                                                                                                                                                                                         case 270:
                                                                                                                                                                                                                                                                         c = "       ��������� ������� H(x^{" + k + "})+\u03BC^{"+ k +"}*E = " +f.h_plusStr(29);
                                                                                                                                                                                                                                                                         b="";
                                                                                                                                                                                                                                                                         nextStep = step + 1;
                                                                                                                                                                                                                                                                         break;

                                                                                                                                                                                                                                                                         case 271:
                                                                                                                                                                                                                                                                         c = "       ��������� �������� �������� �������, ���������� �� n{}"+

                                                                                                                                                                                                                                                                         // b = "H^{-1}(x^{" + k + "}) = " + f.h_1Str()+"n{}"+
                                                                                                                                                                                                                                                                         "  ���������� ����:       [H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} = " + f.h_1_plusStr(29);
                                                                                                                                                                                                                                                                     b="";
                                                                                                                                                                                                                                                                         nextStep = step + 1;
                                                                                                                                                                                                                                                                         break;

                                                                                                                                                                                                                                                                          case 272:
                                                                                                                                                                                                                                                                           c = "       ������ ���������: n{}"+
                                                                                                                                                                                                                                                                              " d_{" + k + "} = -[H(x^{" + k + "})+\u03BC^{"+ k +"}*E]^{-1} \u2207f(x^{" + k + "}) = - " + f.h_1_plusStr(29) + "#{" +
                                                                                                                                                                                                                                                                          f.g1Str() + "}{" + f.g2Str() + "} = " + "#{" + f.d1Str() + "}{" + f.d2Str() + "}";
                                                                                                                                                                                                                                                                          b="";
                                                                                                                                                                                                                                                                            nextStep = step + 1;
                                                                                                                                                                                                                                                                          break;

                                                                                                                                                                                                                                                                           case 273:
                                                                                                                                                                                                                                                                          c = "        ������� ����� x^{" + (k + 1) + "} = x^{" + k + "} + d^{" +
                                                                                                                                                                                                                                                                          k + "} = " +
                                                                                                                                                                                                                                                                          "#{" + f.x1Str() + "}{" + f.x2Str() + "} + #{" + f.d1Str() + "}{" + f.d2Str() + "} = " +
                                                                                                                                                                                                                                                                          "#{"+ f.x1NewStr()+"}{"+f.x2NewStr()+"}";
                                                                                                                                                                                                                                                                           b="";
                                                                                                                                                                                                                                                                            nextStep = step + 1;
                                                                                                                                                                                                                                                                             totalPoints++;
                                                                                                                                                                                                                                                                             // nextStep =-1;
                                                                                                                                                                                                                                                                               break;

                                                                                                                                                                                                                                                                                case 274:
                                                                                                                                                                                                                                                                         // c = "  ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): ";
                                                                                                                                                                                                                                                                           if (f.dFuncNorm_a() < f.dFuncNorm_b()) {
                                                                                                                                                                                                                                                                         c = "        ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                                                                                                                                                                                                                              "  f(x^{" + (k + 1) + "}) = " + f.dFuncNormStr_a() + " < "+ f.dFuncNormStr_b() + " = f(x^{" + k + "}) "+".n{}"+
                                                                                                                                                                                                                                                                           " ������� �����������, ������� �������� k = k+1 = "+(k)+"+1 = "+(k+1)+" , n{} \u03BC^{"+(k+1)+"} = \u03BC^{"+k+"}/2 = "+f.Vektor[29]/2 +
                                                                                                                                                                                                                                                                           " � ��������� � ���� 5.";
                                                                                                                                                                                                                                                                       b="";
                                                                                                                                                                                                                                                                           nextStep = step + 1;
                                                                                                                                                                                                                                                                       //  k++;
                                                                                                                                                                                                                                                                          }
                                                                                                                                                                                                                                                                           else {
                                                                                                                                                                                                                                                                            c = "       ��������� ���������� ������� f(x^{" + (k + 1) + "}) < f(x^{" + k + "}): n{}"+
                                                                                                                                                                                                                                                                                "  f(x^{" + (k + 1) + "}) = " +f.dFuncNormStr_a() + " > "+ f.dFuncNormStr_b() + " = f(x^{" + k + "})"+".n{}" +
                                                                                                                                                                                                                                                                            " ������� �� �����������, ������� �������� \u03BC^{"+k+"} = 2 * \u03BC^{"+k+"}" +
                                                                                                                                                                                                                                                                             " n{}� ��������� � ���� 9.";
                                                                                                                                                                                                                                                                         b="";
                                                                                                                                                                                                                                                                              //f.setx1x2(f.x1New(), f.x2New());
                                                                                                                                                                                                                                                                               nextStep = -1;
                                                                                                                                                                                                                                                                                //  k++;
                                                                                                                                                                                                                                                                                 }
                                                                                                                                                                                                                                                                                //   nextStep = -1;
                                                                                                                                                                                                                                                                                    break;
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                                                                                                                                                                                                                                                                  case 275:
                                                                                                                                                                                                                                                                                        k++;
                                                                                                                                                                                                                                                                                        f.setx1x2(f.x1New(k), f.x2New(k));
                                                                                                                                                                                                                                                                                         c = "      �������� �������� � ����� x^{" + k + "}:n{}"+
                                                                                                                                                                                                                                                                                            "\u2207f(x^{" + k + "}) = #{" + f.g1Str() + "}{" + f.g2Str() + "}";
                                                                                                                                                                                                                                                                                        b="";
                                                                                                                                                                                                                                                                                            nextStep = step + 1;

                                                                                                                                                                                                                                                                                              break;
                                                                                                                                                                                                                                                                                               case 276:

                                                                                                                                                                                                                                                                                                 if (f.gNorm() > f.eps1)
                                                                                                                                                                                                                                                                                                  {
                                                                                                                                                                                                                                                                                                c ="      �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                                                                                                                                                                                                                                 "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " > " +
                                                                                                                                                                                                                                                                                                 f.eps1Str() + "; ������� �� �����������, ��������� n{}� ���������� ����.";
                                                                                                                                                                                                                                                                                             b="";
                                                                                                                                                                                                                                                                                                  nextStep = step + 1;

                                                                                                                                                                                                                                                                                                 }
                                                                                                                                                                                                                                                                                                  else
                                                                                                                                                                                                                                                                                                  {   if (f.gNorm() <= f.eps1)
                                                                                                                                                                                                                                                                                                   {
                                                                                                                                                                                                                                                                                                    c= "      �������� ���������� �������� ��������� \u2225\u2207f(x^{" + k + "})\u2225 \u2264 \u03b5_{1}:n{}"+
                                                                                                                                                                                                                                                                                                     "\u2225\u2207f(x^{" + k + "})\u2225 = "+f.gNormStr() + " \u2264 " +
                                                                                                                                                                                                                                                                                                     f.eps1Str() + "; ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +
                                                                                                                                                                                                                                                                                                     "} = #{" + f.x1Str() + "}{" + f.x2Str() +
                                                                                                                                                                                                                                                                                                      "}e{} - ������� ����������� � �������.";
                                                                                                                                                                                                                                                                                                  b="";
                                                                                                                                                                                                                                                                                                       nextStep =-1;
                                                                                                                                                                                                                                                                                                 }

                                                                                                                                                                                                                                                                                                 }

                                                                                                                                                                                                                                                                                                  break;
                                                                                                                                                                                                                                                                                                 case 277:
                                                                                                                                                                                                                                                                                                if ( k < M )
                                                                                                                                                                                                                                                                                                { c = "       �������� ���������� ������� k \u2265 M: k= " + k + " < "+ M +"n{}" +
                                                                                                                                                                                                                                                                                                 " ������� �� �����������, ������ ��������� � ���������� ����.";
                                                                                                                                                                                                                                                                                                  b = "";
                                                                                                                                                                                                                                                                                                   nextStep = step + 1;}
                                                                                                                                                                                                                                                                                                 else

       {   c= "         �������� ���������� ������� k \u2265 M: k = " + k + " \u2265 "+ M +" = M  " +"n{}"+
           " ������� �����������,n{}������ r{}x^{*} = x_{" + (k) +"} = #{" + f.x1Str() + "}{" + f.x2Str() +
             "}e{} - ������� ����������� � �������.";
             b="";
             nextStep =-1;

         }

                                                                                                                                                                                                                                                                                             nextStep =-1;
                                                                                                                                                                                                                                                                                              break;

                            default: c = "����������� ����� �����!!";
                }
        }
        public DrawPanel_prN() {
                try {
                        jbInit();
                } catch (Exception ex) {
                        ex.printStackTrace();
                }
                Stringer_prN.setPanel(this);
        }
        void jbInit() throws Exception {
                this.setBackground(ColorFon);
                this.setLayout(null);
        }
        protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                step = realsteps[cc];
                Graphics2D g2d = (Graphics2D) g;
                g2d.translate (0, 25);
                Stringer_prN.setGraphics(g);
                LevelLines_prN.setGraphics(g);

                g.setColor(ColorPanelFon);
                g.fillRect(xOff, yOff, xLen, yLen);
                g.fillRect(xOff+xLen+18, yOff, 512-xLen+6-37+70, 69);
                g.fillRect(xOff+xLen+18, yOff+69+20, 512-xLen+6-37+70, 135-6+15);
                g.fillRect(xOff+xLen+18, yOff+69+20+135+20-6+15, 512-xLen+6-37, 26+6);
                g.fillRect(xOff, yOff+yLen+20, 536-37+70, 100);
                g.setColor(ColorPanelPamka);
                g.drawRect(xOff, yOff, xLen, yLen);
                g.drawRect(xOff+xLen+18, yOff, 512-xLen+6-37+70, 69);
                g.drawRect(xOff+xLen+18, yOff+69+20, 512-xLen+6-37+70, 135-6+15);
                g.drawRect(xOff+xLen+18, yOff+69+20+135+20-6+15, 512-xLen+6-37, 26+6);
                g.drawRect(xOff, yOff+yLen+20, 536-37+70, 100);
                g2d.setClip(xOff, yOff, xLen, yLen);
                drawAxes(g);
                g2d.setClip(null);
                g2d.setColor(ColorSolutionText);
//�������
if(cc==0 || cc==2 || cc==3 || cc==4 || cc==6 || cc==11 || cc==12 || cc==14 || cc==19 ||
cc==20 || cc==22 || cc==27 || cc==28 || cc==30 || cc==35 || cc==36 || cc==38 || cc==44 ||
cc==43 || cc==46 || cc==51 || cc==52 || cc==54 || cc==59 || cc==60 || cc==62 ||
cc==67 || cc==68 ||cc==69 || cc==70 || cc==71 || cc==72 || cc==73 || cc==74 || cc==75 || cc==76||
cc==77  ||cc==78 || cc==79||cc==80 || cc==81 || cc==82 || cc==83 || cc==84 || cc==85 || cc==86 || cc==87 ||
cc==88  ||cc==89 || cc==90||cc==91 || cc==92 || cc==93 || cc==94 || cc==95 || cc==96 || cc==97 || cc==98 ||
 cc==99  ||cc==100 || cc==101||cc==102 || cc==103 || cc==104 || cc==105 || cc==106 || cc==107 || cc==108 || cc==109||
 cc==110  ||cc==111 || cc==112||cc==113 || cc==114 || cc==115 || cc==116 || cc==117 || cc==118 || cc==119 || cc==120 ||
cc==121  ||cc==122 || cc==123||cc==124 || cc==125 || cc==126 || cc==127 || cc==128 || cc==129 || cc==130 || cc==131 ||
cc==132  ||cc==133 || cc==134||cc==135 || cc==136 || cc==137 || cc==138 || cc==139 || cc==140 || cc==141 || cc==142||

 cc==143  ||cc==144 || cc==145||cc==146 || cc==147 || cc==148 || cc==149 || cc==150 || cc==151 || cc==152 || cc==153 ||
  cc==154  ||cc==155 || cc==156||cc==157 || cc==158 || cc==159 || cc==160 || cc==161 || cc==162 || cc==163 || cc==164 ||
 cc==165  ||cc==166 || cc==167||cc==168 || cc==169 || cc==170 || cc==171 || cc==172 || cc==173 || cc==174 || cc==175 ||
 cc==176  ||cc==177 || cc==178||cc==179 || cc==180 || cc==181 || cc==182 || cc==183 || cc==184 || cc==185 || cc==186 ||
cc==187  ||cc==188 || cc==189||cc==190 || cc==191 || cc==192 || cc==193 || cc==194 || cc==195 || cc==196 || cc==197||
cc==198  ||cc==199 || cc==200||cc==201 || cc==202 || cc==203 || cc==204 || cc==205 || cc==206 || cc==207 || cc==208
  ||cc>=209
 )
{
  Stringer_prN.drawString(cs[cc], 40, 308+5);
  g2d.setFont(FontSolutionNumber);
  g2d.setColor(ColorSolutionNumber);
  g2d.drawString(steps[cc], 8, 308+5);
}
if(cc==1 || cc==5 || cc==7 || cc==8 || cc==13 || cc==15 || cc==16 || cc==21 || cc==23 ||
cc==24 || cc==29 || cc==31 || cc==32 || cc==37 || cc==39 || cc==40 || cc==45 || cc==47 ||
cc==48 || cc==53 || cc==55 || cc==56 || cc==61 || cc==63 || cc==64

   )
{
  Stringer_prN.drawString(cs[cc], 40, 308+5+6);
  g2d.setFont(FontSolutionNumber);
  g2d.setColor(ColorSolutionNumber);
  g2d.drawString(steps[cc], 8, 308+5+6);
}
if(cc==9 || cc==10 || cc==17 || cc==18 || cc==25 || cc==26 || cc==33 || cc==34 || cc==41
|| cc==42 || cc==49 || cc==50 || cc==57 || cc==58 || cc==65 || cc==66
    )
{
  Stringer_prN.drawString(cs[cc], 40+10, 308+5);
  g2d.setFont(FontSolutionNumber);
  g2d.setColor(ColorSolutionNumber);
  g2d.drawString(steps[cc], 8, 308+5);
}
if(cc==0 ||  cc==4 || cc==9 || cc==10 || cc==12 || cc==17 || cc==18 || cc==20 ||
cc==25 || cc==26 || cc==28 || cc==33 || cc==34 || cc==36 || cc==41 || cc==42 ||
cc==44 || cc==49 || cc==50 || cc==52 || cc==57 || cc==58 || cc==60 || cc==65 ||
cc==66  ||cc==67 || cc==68||cc==69 || cc==70 || cc==71 || cc==72 || cc==73 || cc==74 || cc==75 || cc==76 ||
cc==77  ||cc==78 || cc==79||cc==80 || cc==81 || cc==82 || cc==83 || cc==84 || cc==85 || cc==86 || cc==87 ||
cc==88  ||cc==89 || cc==90||cc==91 || cc==92 || cc==93 || cc==94 || cc==95 || cc==96 || cc==97 || cc==98 ||
cc==99  ||cc==100 || cc==101||cc==102 || cc==103 || cc==104 || cc==105 || cc==106 || cc==107 || cc==108 || cc==109 ||
   cc==110  ||cc==111 || cc==112||cc==113 || cc==114 || cc==115 || cc==116 || cc==117 || cc==118 || cc==119 || cc==120 ||
  cc==121  ||cc==122 || cc==123||cc==124 || cc==125 || cc==126 || cc==127 || cc==128 || cc==129 || cc==130 || cc==131 ||
  cc==132  ||cc==133 || cc==134||cc==135 || cc==136 || cc==137 || cc==138 || cc==139 || cc==140 || cc==141 || cc==142 ||

  cc==143  ||cc==144 || cc==145||cc==146 || cc==147 || cc==148 || cc==149 || cc==150 || cc==151 || cc==152 || cc==153 ||
   cc==154  ||cc==155 || cc==156||cc==157 || cc==158 || cc==159 || cc==160 || cc==161 || cc==162 || cc==163 || cc==164 ||
  cc==165  ||cc==166 || cc==167||cc==168 || cc==169 || cc==170 || cc==171 || cc==172 || cc==173 || cc==174 || cc==175 ||
  cc==176  ||cc==177 || cc==178||cc==179 || cc==180 || cc==181 || cc==182 || cc==183 || cc==184 || cc==185 || cc==186 ||
 cc==187  ||cc==188 || cc==189||cc==190 || cc==191 || cc==192 || cc==193 || cc==194 || cc==195 || cc==196 || cc==197||
 cc==198  ||cc==199 || cc==200||cc==201 || cc==202 || cc==203 || cc==204 || cc==205 || cc==206 || cc==207 || cc==208
 ||cc>=209
   )
{
  g2d.setColor(ColorSolutionText);
  Stringer_prN.drawString(bs[cc], 10, 308+5+23);
}
if(cc==2 || cc==3 || cc==6 || cc==11 || cc==14 || cc==19 || cc==22 || cc==27 || cc==30 ||
cc==35 || cc==38 || cc==43 || cc==46 || cc==51 || cc==54 || cc==59 || cc==62
   )
{
  g2d.setColor(ColorSolutionText);
  Stringer_prN.drawString(bs[cc], 40, 308+5+25+6);
}
/////////////////////////////
                for (int i = 0; i <nPoints; ++i) {
                  LevelLines_prN.drawLevelLine(x1[i], x2[i]);
                }
                g2d.setClip(xOff, yOff, xLen, yLen);
                g2d.setColor(Color.red);
                int x = 0, y = 0, xLast = -1, yLast = -1;
//		x = xOff + xCen + (int)(xEnd * scale);
//		y = yOff + yCen - (int)(yEnd * scale);
//		g2d.fillOval(x-2, y-2, 5, 5);

                for (int i = 0; i < totalPoints; ++i) {
                  g2d.setColor(i == totalPoints - 1 ? Color.red : ColorSubTitle);
                  x = xOff + xCen + (int) (scale * x1[i]);
                  y = yOff + yCen - (int) (scale * x2[i]);
                  g2d.fillOval(x - 2, y - 2, 5, 5);
                  if (i > 0) {g2d.drawLine(x, y, xLast, yLast);}
                  xLast = x;
                  yLast = y;
                  Stringer_prN.drawString("x^{" + i + "}", x + 15, y+7 );
                }
                g2d.setClip(null);
                if (cc == count-1) { Stringer_prN.drawString("x^{*}=", x - 26, y +7);}
//��������� ������
                g2d.setColor(ColorFormulaText);

              sc(g, new int[]{1}, xOff+xLen+18+9, yOff+36);
                Stringer_prN.setFontSizes(12, 10, 8);
                Stringer_prN.drawString("x^{0} = (" + f.normalize(x1[0]) + "; " + f.normalize(x2[0]) + ")^{T} ", xOff+xLen+18+8, yOff+15);
                Stringer_prN.drawString("\u03b5_{1} = " + f.eps1Str(), xOff+xLen+18+8, yOff+30);
                Stringer_prN.drawString("c{- ��������� �����}", xOff+xLen+18+7+73, yOff + 15 );
                Stringer_prN.drawString("  c{-����� �����}", xOff+xLen+80, yOff + 30 );
                Stringer_prN.drawString("\u03BC^{0} = "+ m, xOff+xLen+18+8, yOff+49);
                Stringer_prN.drawString("  c{-������������� �����}", xOff+xLen+85, yOff + 47 );
                Stringer_prN.drawString(" M = "+M, xOff+xLen+18+5, yOff+65);
                Stringer_prN.drawString("  c{-���������� ��������}", xOff+xLen+68, yOff + 65 );


//�������

                sc(g, new int[]{12,21,30,39,48,57,66,75,84,93,102,111,120,129,138,147,156,165,174,183,192,201}, xOff+xLen+18+9, yOff+69+20+11);
               Stringer_prN.drawString("x^{k+1} = x^{k} + d^{k}", xOff+xLen+28+5, yOff+69+20+20);

               sc(g, new int[]{11,20,29,38,47,56,65,74,83,92,101,110,119,128,137,146,155,164,173,182,191,200}, xOff+xLen+18+9, yOff+69+20+36);
                Stringer_prN.drawString("d^{k} = -[H(x^{k})+\u03BC^{k}*E]^{-1}\u2207f(x^{k})",xOff+xLen+28+5,yOff+69+20+45);

                sc(g, new int[]{7,16,25,34,43,52,61,70,79,88,97,106,115,124,133,142,151,160,169,178,187,196,205}, xOff+xLen+18+9, yOff+69+20+130);
               Stringer_prN.drawString("k \u2265 M         (E - ��������� �������)", xOff+xLen+28+5, yOff+69+20+140);

                sc(g, new int[]{6,15,24,33,42,51,60,69,78,87,96,105,114,123,132,141,150,159,168,177,186,195,204}, xOff+xLen+18+9, yOff+69+20+61);
                Stringer_prN.drawString("||\u2207f(x^{k})|| \u2264 \u03b5_{1}", xOff+xLen+28+5, yOff+69+20+70);

                 sc(g, new int[]{13,22,31,40,49,58,67,76,85,94,103,112,121,130,139,148,157,166,175,184,193,202}, xOff+xLen+18+9, yOff+69+111);
                    Stringer_prN.drawString("f(x^{k+1}) < f(x^{k})", xOff+xLen+28+5, yOff+69+20+105);
                  Stringer_prN.drawString("\u03BC^{k+1} = \u03BC^{k}/2", xOff+xLen+140, yOff+69+20+90);
                  Stringer_prN.drawString("\u002B", xOff+xLen+120, yOff+69+20+92);
                  g.drawLine(xOff+xLen+110,yOff+69+20+100,xOff+xLen+135,yOff+69+20+90);

                  sc(g, new int[]{0}, xOff+xLen+18+9, yOff+69+111);
               Stringer_prN.drawString("\u03BC^{k+1} = 2*\u03BC^{k}", xOff+xLen+140, yOff+69+20+120);
              // g.setColor(Color.black);
                Stringer_prN.drawString("\u2013", xOff+xLen+120, yOff+69+20+115);
              g.drawLine(xOff+xLen+110,yOff+69+20+100,xOff+xLen+135,yOff+69+20+110);



// �������
                g2d.setFont(FontSubTitle);
                g2d.setColor(ColorSubTitle);
                g2d.drawString("�������: ", xOff+xLen+18+7, yOff+69+20-3);
                g2d.drawString("����������� �����������: ", xOff+7, yOff-3);
                g2d.drawString("�������: ", xOff+7, yOff+yLen+20-3);
                g2d.drawString("B�������: ", xOff+xLen+18+7, yOff-3);
                g2d.drawString("������: ",  xOff+7, -34);
                g2d.drawString("�������:", xOff+xLen+18+7, yOff+69+20+135+20-3-6+15);
//�������
                g2d.setFont(FontSolutionText);
                g2d.setColor(ColorSolutionText);
                g2d.drawString("����� ������� ������� ", 63, -34);
              //  g2d.drawString("������� ����������", 200-10, -20+5);

                g2d.drawString("������� ����������", 200-10, -20+5);

                Stringer_prN.setFontSizes(14, 12, 10);
                       Stringer_prN.drawString("f(x) = 8(x_{1} + 0.6)^{4} + 12(x_{2} + 0.4)^{4}", 246, -34);

                g2d.setColor(Color.blue);
                Stringer_prN.setFontSizes(12, 10, 8);
                g.setColor(ColorGraphicLine);
                       Stringer_prN.drawString("f(x)=8(x_{1}+0.6)^{4}+12(x_{2}+0.4)^{4}", 13, 16);
                Stringer_prN.setFontSizes(14, 11, 8);
                g2d.setFont(FontSolutionText);
                String a = ""+aArr[variant];
                String b = bArr[variant] > 0 ? " + " + bArr[variant] : " - " + (-bArr[variant]);
                String c = ""+cArr[variant];
                String d = dArr[variant] > 0 ? " + " + dArr[variant] : " - " + (-dArr[variant]);
                String s;
                s = "f(x) = " + a + "(x_{1}" + b + ")^{4} +" + c + "(x_{2}" + d + ")^{4}";
                int xx = 200;
                g2d.translate (0, -25);
                ///////////////////////////////////
      if(fll==2)
        {
          g.setColor(ColorFon);
          g.fillRect(8+5,338-35,93,20);fll=0;
          g.setColor(Colorhelp);
          g.drawRect(8+5,338-35,93,20);fll=0;
          g.setFont(FontGraphicXY);
          g.drawString("����� ��������",8+10,338-20);
        }
      if(fll==3)
        {
          g.setColor(ColorFon);
          g.fillRect(8+5+13,338-35,69,20);fll=0;
          g.setColor(Colorhelp);
          g.drawRect(8+5+13,338-35,69,20);fll=0;
          g.setFont(FontGraphicXY);
          g.drawString("����� ����",8+10+13,338-20);
        }

////////////////////////////////////////////////////

        }
        private void drawAxes(Graphics g) {
                int xc = xCen + xOff;
                int yc = yCen + yOff;
                int xe = xOff + xLen - 1;
                int ye = yOff + yLen - 1;
                g.setColor(ColorGraphicXY);
                g.drawLine(xc, yOff, xc, ye);
                g.drawLine(xOff, yc, xe, yc);
                g.drawLine(xe, yc, xe - 10, yc - 2);
                g.drawLine(xe, yc, xe - 10, yc + 2);
                g.drawLine(xc, yOff, xc + 2, yOff + 10);
                g.drawLine(xc, yOff, xc - 2, yOff + 10);
                g.setColor(ColorSolutionText);
                g.setFont(FontGraphicXY);
                Stringer_prN.setFontSizes(10, 8, 8);
                Stringer_prN.drawString("x_{1}", xe - 13, yc + 14);
                Stringer_prN.drawString("x_{2}", xc + 5, yOff + 10);
                g.setFont(FontGraphicXY);
                Stringer_prN.setFontSizes(14, 11, 8);
                double dx = scale;
                int j = 1;
                wasArrow = false;
                g.drawString("0", xc - 10, yc + 15);
                for (int x = xc + (int) scale/2; x < xe - 20; x += scale/2, j++) {
                        g.drawString("" + j/2.0, x - 5, yc + 15);
                        g.drawLine(x, yc - 3, x, yc + 3);
                }
                j = -1;
                for (int x = xc - (int) scale/2; x > xOff; x -= scale/2, j--) {
                        g.drawString("" + j/2.0, x - 5, yc + 15);
                        g.drawLine(x, yc - 3, x, yc + 3);
                }
                j = -1;
                for (int y = yc + (int) scale/2; y < ye; y += scale/2, j--) {
                        g.drawString("" + j/2.0, xc - 8-8-5, y - 3);
                        g.drawLine(xc - 3, y, xc + 3, y);
                }
                j = 1;
                for (int y = yc - (int) scale/2; y > yOff + 20; y -= scale/2, j++) {
                        g.drawString("" + j/2.0, xc - 8-8, y - 3);
                        g.drawLine(xc - 3, y, xc + 3, y);
                }
        }
        static boolean wasArrow;
   public void sc(Graphics g, int[] s, int x, int y) {
           g.setColor(ColorSolutionText);
           for(int i = 0; i < s.length;++i) {
                   if(step != s[i]) continue;
                   g.setColor(Color.red);
                   y+=5;
                   strelka(g, x, y);
                   break;
           }
   }
   public void strelka(Graphics g,int x,int y){
         if(wasArrow) return;
           wasArrow = true;
           x=x-25;
         y=y-20;
         g.setColor(Color.red);
         g.drawLine(x,y,x+19,y+19);
         g.drawLine(x,y+1,x+19,y+20);
         ///-----1------
         g.drawLine(x+10,y+20,x+19,y+20);
         g.drawLine(x+12,y+19,x+19,y+19);
         g.drawLine(x+14,y+18,x+19,y+18);
         ///-----2------
         g.drawLine(x+19,y+10,x+19,y+20);
         g.drawLine(x+18,y+12,x+18,y+20);
         g.drawLine(x+17,y+15,x+17,y+20);
   }
}
