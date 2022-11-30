using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab3
{
    public class Complex
    {
        private double real_part;
        private double imaginary;

        public Complex()
        {
            real_part = 0.0;
            imaginary = 0.0;
        }

        public Complex(double real, double imag)
        {
            real_part = real;
            imaginary = imag;
        }
        public double GetRealPart()
        {
            return real_part;
        }
        public double GetImagPart()
        {
            return imaginary;
        }
    }

    public class ComOper
    {
        static public Complex Sum(Complex lhs, Complex rhs)
        {
            return new Complex(lhs.GetRealPart() + rhs.GetRealPart(), lhs.GetImagPart() + rhs.GetImagPart());
        }

        static public Complex Neg(Complex lhs)
        {
            return new Complex(-lhs.GetRealPart(), -lhs.GetImagPart());
        }

        static public Complex Min(Complex lhs, Complex rhs)
        {
            return Sum(lhs, (Neg(rhs)));
        }

        static public Complex Mul(Complex lhs, Complex rhs)
        {
            return new Complex(lhs.GetRealPart() * rhs.GetRealPart() - lhs.GetImagPart() * rhs.GetImagPart(),
                    lhs.GetRealPart() * rhs.GetImagPart() + lhs.GetImagPart() * rhs.GetRealPart());
        }

        static public Complex Conj(Complex lhs)
        {
            return new Complex(lhs.GetRealPart(), -lhs.GetImagPart());
        }

        static public Complex Div(Complex lhs, Complex rhs)
        {
            double x1 = lhs.GetRealPart();
            double x2 = rhs.GetRealPart();
            double y1 = lhs.GetImagPart();
            double y2 = rhs.GetImagPart();
            return new Complex((x1 * x2 + y1 * y2) / (x2 * x2 + y2 * y2),
                    (x2 * y1 - x1 * y2) / (x2 * x2 + y2 * y2));
        }

        static public bool IsEqual(Complex lhs, Complex rhs)
        {
            return lhs.GetRealPart() == rhs.GetRealPart() && lhs.GetImagPart() == rhs.GetImagPart();
        }

        static public String ToString(Complex c)
        {
            String res = "";
            res += c.GetRealPart() + " + i*" + c.GetImagPart();
            return res;
        }
    }
}
