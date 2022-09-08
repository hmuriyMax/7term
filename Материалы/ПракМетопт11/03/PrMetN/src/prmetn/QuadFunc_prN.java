package prmetn;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
import java.text.*;
public class QuadFunc_prN implements FunctionContainer_prN {
        static NumberFormat nf = NumberFormat.getInstance();
        public double a = 0.0;
        public double b = 0.0;
        public double c = 0.0;
        public double d = 0.0;
        public double x1 = 0.0;
        public double x2 = 0.0;
        public double eps1 = 0.0001;
       // public double eps2 = 0.00001;
       // public double Ny = 1000;
       public static int k=0;
         public double m = 1000;
        public int t;
         public static  int M=20;
        public static int nextStep;
        public double[] Vektor = {1000,  500, 250, 125, 62.5,31.25, 15.625, 7.8125, 3.90625, 1.953125, 0.9765625, 0.48828125, 0.244140625, 0.1220703125 , 0.06103515625, 0.030517578125, 0.0152587890625, 0.00762939453125, 0.003814697265625, 0.0019073486328125, 0.00095367431640625, 0.000476837158203125, 0.0002384185791015625, 0.00011920928955078125 ,0.000059604644775390625, 0.0000298023223876953125 ,0.00001490116119384765625 ,0.000007450580596923828125, 0.0000037252902984619140625 ,0.00000186264514923095703125 , 0.000000931322574615478515625};

      //  public double[] Vektor = {100,  50, 25, 12.5, 6.25,3.125, 1.5625, 0.78125, 0.390625, 0.1953125, 0.09765625, 0.048828125, 0.0244140625, 0.01220703125 , 0.006103515625, 0.0030517578125, 0.00152587890625, 0.000762939453125, 0.0003814697265625, 0.00019073486328125, 0.000095367431640625, 0.0000476837158203125, 0.00002384185791015625, 0.000011920928955078125 ,0.0000059604644775390625, 0.00000298023223876953125 ,0.000001490116119384765625 ,0.0000007450580596923828125, 0.00000037252902984619140625 ,0.000000186264514923095703125 , 0.0000000931322574615478515625};

        public QuadFunc_prN() {nf.setMaximumFractionDigits(3);}
        public QuadFunc_prN(double a, double b, double c, double d) {
                this.a = a;
                this.b = b;
                this.c = c;
                this.d = d;
                nf.setMaximumFractionDigits(3);
        }
        public void setx1x2(double x1, double x2) {
                this.x1 = x1;
                this.x2 = x2;
        }
      /*  public void seteps1eps2(double eps1, double eps2) {
                this.eps1 = eps1;
              //  this.eps2 = eps2;
        }*/

        public String normalize(double d) {
                d = (int)(d*1000000)*1.0/1000000;
                String s;
                if(d == (int)d) {s = String.valueOf((int)d);return s;}
                else s = String.valueOf(d);
                if(s.length() <= 1) return s;
                while (s.charAt(s.length() - 1) == '0'){
                  s = s.substring(0, s.length() - 1);
                }
                return s;
        }

        public double g1() {
                return 4 * a * Math.pow(x1 + b, 3);
        }

        public double g2() {
                return 4 * c * Math.pow(x2 + d, 3);
        }

        public double gNorm() {
                double gr1 = g1();
                double gr2 = g2();
                return Math.sqrt(gr1*gr1 + gr2*gr2);
        }
 /*       public double Ny() {
             double gr1 = g1();
             double gr2 = g2();
             return Math.sqrt(gr1*gr1 + gr2*gr2);
     }*/

/////////////////////////////////////////////////////
        public double[][] h() {
                double[][] matrix = new double[2][2];
                matrix[0][0] = (12 * a * (x1 + b) * (x1 + b));//a=8
                matrix[0][1] = 0;
                matrix[1][0] = 0;
                matrix[1][1] = (12 * c * (x2 + d) * (x2 + d));//c=12
                return matrix;
        }


 /*       public double[][] h_1() {
                double[][] matrix = new double[2][2];
                double m = 1.0 / (144 * a * c * (x1 + b) * (x1 + b) * (x2 + d) * (x2 + d));
                matrix[0][0] = m * 12 * c * (x2 + d) * (x2 + d);
                matrix[0][1] = 0;
                matrix[1][0] = 0;
                matrix[1][1] = m * 12 * a * (x1 + b) * (x1 + b);
                return matrix;
        }*/

    /* public double NyS() {
                         double  Ny;
                         double r_new = func(x1New(), x2New());
                         double  r2_alt =  func();
                         if(r_new < r2_alt ) {Ny=m/2;}
                           else {Ny=2*m;}
                        return Ny;
        }*/

 //////////////////////// �������  H(x)+Ny*E//////////////////////////////////////////////
        public double[][] h_plus(int k) {
                        double[][] matrix = new double[2][2];

                          matrix[0][0] =( 12 * a * (x1 + b) * (x1 + b))+Vektor[k];
                          matrix[0][1] = 0;
                          matrix[1][0] = 0;
                          matrix[1][1] = (12 * c * (x2 + d) * (x2 + d)) + Vektor[k];

                        return matrix;
                }
 //////////////////////// �������  [H(x)+Ny*E]^{-1}//////////////////////////////////////////////

        public double[][] h_1_plus(int k) {
                        double[][] matrix = new double[2][2];
                        double   m_plus,m1_plus;

                           m_plus  =  ((12 * c * (x2 + d) * (x2 + d) )+Vektor[k]);
                          m1_plus =  ((12 * a * (x1 + b) * (x1 + b))+Vektor[k]);

                          if((m_plus*m1_plus)>0)
                           {
                             matrix[0][0] = m_plus/(m_plus*m1_plus);
                             matrix[0][1] = 0;
                             matrix[1][0] = 0;
                             matrix[1][1] = m1_plus /(m_plus * m1_plus);
                           }

                        return matrix;
                }

             /*    public double Vektor(int k) {
                    double q = Vektor[k] / 2;
                   return q;
                 }*/
      ////////////////////////////////////////////////////////
/*            public double[][] E() {
                      double[][] matrix = new double[2][2];
                      matrix[0][0] = 1;
                      matrix[0][1] = 0;
                      matrix[1][0] = 0;
                      matrix[1][1] = 1;
                      return matrix;
              }


               public double[][] h() {
                      double[][] matrix = new double[2][2];
                      matrix[0][0] = 12 * a * (x1 + b) * (x1 + b)+Ny;
                      matrix[0][1] = 0;
                      matrix[1][0] = 0;
                      matrix[1][1] = 12 * c * (x2 + d) * (x2 + d)+Ny;
                      return matrix;
              }

              public double[][] h_1() {
                     double[][] matrix = new double[2][2];
                     double m = 1.0 / (144 * (a * (x1 + b) * (x1 + b)+Ny) * (c*(x2 + d) * (x2 + d)+Ny));
                     matrix[0][0] = m * (12 * c * (x2 + d) * (x2 + d)+Ny);
                     matrix[0][1] = 0;
                     matrix[1][0] = 0;
                     matrix[1][1] = m * (12 * a * (x1 + b) * (x1 + b)+Ny);
                     return matrix;
             }
  */

             public double d1(int k) {
             double[][]  p = h_1_plus(k);
             return   -(p[0][0] * g1());

              }

             public double d2(int k) {
             double[][]  p = h_1_plus(k);
             return   -(p[1][1] * g2());
            }


        public double x1New(int k) {
                return x1 + d1(k);
        }

        public double x2New( int k) {
                return x2 + d2(k);
        }

       /* public double dxNorm() {
                double a = x1New() - x1;
                double b = x2New() - x2;
                return Math.sqrt(a*a + b*b);
        }*/

     /*   public double dFuncNorm() {
                double a = func(x1New(), x2New());
                double b = func();

                return a-b;
        }*/
        public double dFuncNorm_a() {
                       double a = func(x1New(k), x2New(k));
                      return a;
               }
       public double dFuncNorm_b() {
                     double b = func();
                    return b;
              }


        public double func() {
                return a * Math.pow(x1 + b, 4) + c * Math.pow(x2 + d, 4);
        }

        public double func(double x1, double x2) {
                return a * Math.pow(x1 + b, 4) + c * Math.pow(x2 + d, 4);
        }

      public String gradientStr() {
                String bs = b > 0 ? " + " + normalize(b) : " - " + normalize(-b);
                String ds = d > 0 ? " + " + normalize(d) : " - " + normalize(-d);

                return "#{" + normalize(4*a) + "(x_{1}" + bs + ")^{3}}{" + normalize(4*c) + "(x_{1}" + ds + ")^{3}}";
        }

        public String g1Str() {
                return normalize(g1());
        }

        public String g2Str() {
                return normalize(g2());
        }

        public String gNormStr() {
                return normalize(gNorm());
        }
     /*   public String VektorStr() {
                return normalize(Vektor(k));
        }*/



        public String gesseStr() {
                String bs = b > 0 ? " + " + b : " - " + (-b);
                String ds = d > 0 ? " + " + d : " - " + (-d);

                return "#{" + normalize(12*a) + "(x_{1}" + bs + ")^{2}}{0}{0}{" + normalize(12*c) + "(x_{2}" + ds + ")^{2}}";
        }


        public String hStr() {
                double[][] matrix = h();
                return "#{" + normalize(matrix[0][0]) + "}{" +
                normalize(matrix[0][1]) + "}{" + normalize(matrix[1][0]) + "}{" +
                normalize(matrix[1][1]) + "}";
        }
        public String h_plusStr(int k) {

               double[][] matrix =  h_plus(k);
               return "#{" + normalize(matrix[0][0]) + "}{" +
               normalize(matrix[0][1]) + "}{" + normalize(matrix[1][0]) + "}{" +
               normalize(matrix[1][1]) + "}";
       }


       public String h_1_plusStr(int k) {
                     double[][] matrix = h_1_plus(k);
                     return "#{" + normalize(matrix[0][0]) + "}{" +
                     normalize(matrix[0][1]) + "}{" + normalize(matrix[1][0]) + "}{" +
                     normalize(matrix[1][1]) + "}";

        }

        public String d1Str() {
                return normalize(d1(k));
        }

        public String d2Str() {
                return normalize(d2(k));
        }

        public String x1NewStr() {
                return normalize(x1New(k));
        }
        public String x2NewStr() {
                return normalize(x2New(k));
        }
        public String x1Str() {
                return normalize(x1);
        }
        public String x2Str() {
                return normalize(x2);
        }
        public String eps1Str() {
                return normalize(eps1);
        }
      //  public String eps2Str() {
              //  return normalize(eps2);
       // }
      /*  public String dxNormStr() {
                return normalize(dxNorm());
        }*/
      /*  public String dFuncNormStr() {
                return normalize(dFuncNorm());
        }*/
        public String dFuncNormStr_a() {
               return normalize(dFuncNorm_a());
       }
       public String dFuncNormStr_b() {
             return normalize(dFuncNorm_b());
     }
    // public String NySStr() {
     //           return normalize(NyS());
     //   }


        public String funcStr() {
                return normalize(func());
        }
}
