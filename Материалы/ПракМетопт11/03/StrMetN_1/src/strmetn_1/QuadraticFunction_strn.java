package strmetn_1;

public class QuadraticFunction_strn implements FunctionContainer_strn {
        double a11 = 0.0; // коэффициент при x1^2
        double a12 = 0.0; // коэффициент при x1*x2
        double a22 = 0.0; // коэффициент при x2^2
        double a1 = 0.0;  // коэффициент при x1
        double a2 = 0.0;  // коэффициент при x2
        double a = 0.0;   // свободный член

        public QuadraticFunction_strn() {}
        public QuadraticFunction_strn(double a11, double a12, double a22, double a1, double a2, double a) {
                this.a11 = a11;
                this.a12 = a12;
                this.a22 = a22;
                this.a1  = a1;
                this.a2  = a2;
                this.a   = a;
        }

        public double func(double x1, double x2) {
                //return a11*x1*x1 + a12*x1*x2 + a22*x2*x2* + a1*x1 + a2*x2*x2 + a;
         return a11*x1*x1+a12*x1*x2 +a22*x2*x2;
                //return 2*a11*x1*x1 + a12*x1*x2 + a22*x2*x2 ;
        }
}
