using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lab3
{
    public class Fraction
    {
        public Fraction()
        {
            numerator = 0;
            denominator = 1;
        }

        public Fraction(int num, int denum)
        {
            numerator = num;
            denominator = num == 0 && denum == 0 ? 1 : denum;
            if (denominator == 0)
                throw new Exception("неправильная дробь");
        }

        public Fraction(float num)
        {
            int intP = (int) num;
            float realP = num - intP;
            realP *= 10000;
            numerator = (int) realP;
            denominator = 10000;
            MakeEasy();
        }

        public void MakeEasy()
        {
            int Cgcd = gcd(Math.Abs(numerator), Math.Abs(denominator));
            if (Cgcd == 0) {
                throw new Exception("деление на ноль");
            }
            numerator /= Cgcd;
            denominator /= Cgcd;
        }

        public int GetDenominator()
        {
            return denominator;
        }

        public int GetNumerator()
        {
            return numerator;
        }

        private int numerator;
        private int denominator;
        private int gcd(int a, int b)
        {
            if (b == 0)
                return a;
            else
                return gcd(b, a % b);
        }

    }

    public class FracOper
    {
        static public Fraction Sum(Fraction lhs, Fraction rhs)
        {
            int denom = lhs.GetDenominator() * rhs.GetDenominator();
            int numer = lhs.GetNumerator() * rhs.GetDenominator() + rhs.GetNumerator() * lhs.GetDenominator();
            Fraction res = new Fraction(numer, denom);
            res.MakeEasy();
            return res;
        }

        static public Fraction Min(Fraction lhs, Fraction rhs)
        {
            int denom = lhs.GetDenominator() * rhs.GetDenominator();
            int numer = lhs.GetNumerator() * rhs.GetDenominator() - rhs.GetNumerator() * lhs.GetDenominator();
            Fraction res = new Fraction(numer, denom);
            res.MakeEasy();
            return res;
        }

        static public Fraction Neg(Fraction lhs) 
        {
            return new Fraction(-lhs.GetNumerator(), lhs.GetDenominator());
        }

        static public Fraction Mult(Fraction lhs, Fraction rhs)
        {
            int denom = lhs.GetDenominator() * rhs.GetDenominator();
            int numer = lhs.GetNumerator() * rhs.GetNumerator();
            Fraction res = new Fraction(numer, denom);
            res.MakeEasy();
            return res;
        }

        static public Fraction Div(Fraction lhs, Fraction rhs) 
        {
            int denom = lhs.GetDenominator() * rhs.GetNumerator();
            int numer = lhs.GetNumerator() * rhs.GetDenominator();
            Fraction res = new Fraction(numer, denom);
            res.MakeEasy();
            return res;
        }

        static public bool IsEqual(Fraction lhs, Fraction rhs)
        {
            return lhs.GetNumerator() == rhs.GetNumerator()
                && lhs.GetDenominator() == rhs.GetDenominator();
        }

        static public String ToString(Fraction c)
        {
            return c.GetNumerator() + "/" + c.GetDenominator();
        }
    }
}
