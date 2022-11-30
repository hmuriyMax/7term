class Fraction {

    public Fraction() {
        numerator = 0;
        denominator = 1;
    }

    public Fraction(int num, int denum) throws Exception {
        numerator = num;
        denominator = num == 0 && denum == 0 ? 1 : denum;
        if (denominator == 0) {
            throw new Exception("неправильная дробь");
        }
    }

    public Fraction(float num) throws Exception {
        int intP = (int) num;
        float realP = num - intP;
        realP *= 10000;
        numerator = (int) realP;
        denominator = 10000;
        MakeEasy();
    }

    public void MakeEasy() throws Exception {
        int Cgcd = gcd(Math.abs(numerator), Math.abs(denominator));
        if (Cgcd == 0) {
            throw new Exception("деление на ноль");
        }
        numerator /= Cgcd;
        denominator /= Cgcd;
    }

    public int GetDenominator() {
        return denominator;
    }

    public int GetNumerator() {
        return numerator;
    }

    private int numerator;
    private int denominator;
    private int gcd(int a, int b) {
        if (b == 0)
            return a;
        else
            return gcd(b, a % b);
    }

}

class FracOper {
    static Fraction Sum(Fraction lhs, Fraction rhs) throws Exception {
        int denom = lhs.GetDenominator() * rhs.GetDenominator();
        int numer = lhs.GetNumerator() * rhs.GetDenominator() + rhs.GetNumerator() * lhs.GetDenominator();
        Fraction res = new Fraction(numer, denom);
        res.MakeEasy();
        return res;
    }

    static Fraction Min(Fraction lhs, Fraction rhs) throws Exception {
        int denom = lhs.GetDenominator() * rhs.GetDenominator();
        int numer = lhs.GetNumerator() * rhs.GetDenominator() - rhs.GetNumerator() * lhs.GetDenominator();
        Fraction res = new Fraction(numer, denom);
        res.MakeEasy();
        return res;
    }

    static Fraction Neg(Fraction lhs) throws Exception {
        return new Fraction(-lhs.GetNumerator(), lhs.GetDenominator());
    }

    static Fraction Mult(Fraction lhs, Fraction rhs) throws Exception {
        int denom = lhs.GetDenominator() * rhs.GetDenominator();
        int numer = lhs.GetNumerator() * rhs.GetNumerator();
        Fraction res = new Fraction(numer, denom);
        res.MakeEasy();
        return res;
    }

    static Fraction Div(Fraction lhs, Fraction rhs) throws Exception {
        int denom = lhs.GetDenominator() * rhs.GetNumerator();
        int numer = lhs.GetNumerator() * rhs.GetDenominator();
        Fraction res = new Fraction(numer, denom);
        res.MakeEasy();
        return res;
    }

    static boolean IsEqual(Fraction lhs, Fraction rhs) {
        return lhs.GetNumerator() == rhs.GetNumerator()
                && lhs.GetDenominator() == rhs.GetDenominator();
    }

    static String ToString(Fraction c) {
        return c.GetNumerator() + "/" + c.GetDenominator();
    }
};
