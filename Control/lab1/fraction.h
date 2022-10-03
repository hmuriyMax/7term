#pragma once
#include <sstream>

int gcd(int a, int b) {
    if (b == 0)
        return b;
    else
        return gcd(b, a % b);
}

class Fraction {
public:
    Fraction() {
        numerator = 0;
        denominator = 1;
    }

    Fraction(int num, int denom) {
        numerator = num;
        denominator = num == 0 && denom == 0 ? 1 : denom;
        if (denominator == 0) {
            throw "неправильная дробь";
        }
    }

    Fraction(float num) {
        int intp = int(trunc(num));
        float realp = num - intp;
        realp *= 10000;
        numerator = int(realp);
        denominator = 10000;
        MakeEasy();
    }

    void MakeEasy() {
        int Cgcd = gcd(numerator, denominator);
        numerator /= Cgcd;
        denominator /= Cgcd;
    }

    int GetDenominator() const {
        return denominator;
    }
    int GetNumerator() const {
        return numerator;
    }

private:
    int numerator;
    int denominator;
};

Fraction operator+ (Fraction lhs, Fraction rhs) {
    int denom = lhs.GetDenominator() * rhs.GetDenominator();
    int numer = lhs.GetNumerator() * rhs.GetDenominator() + rhs.GetNumerator() * lhs.GetDenominator();
    Fraction res(numer, denom);
    res.MakeEasy();
    return res;
}

Fraction operator- (Fraction lhs, Fraction rhs) {
    int denom = lhs.GetDenominator() * rhs.GetDenominator();
    int numer = lhs.GetNumerator() * rhs.GetDenominator() - rhs.GetNumerator() * lhs.GetDenominator();
    Fraction res(numer, denom);
    res.MakeEasy();
    return res;
}

Fraction operator- (Fraction lhs) {
    return Fraction(-lhs.GetNumerator(), lhs.GetDenominator());
}

Fraction operator* (Fraction lhs, Fraction rhs) {
    int denom = lhs.GetDenominator() * rhs.GetDenominator();
    int numer = lhs.GetNumerator() * rhs.GetNumerator();
    Fraction res(numer, denom);
    res.MakeEasy();
    return res;
}

Fraction operator/ (Fraction lhs, Fraction rhs) {
    int denom = lhs.GetDenominator() * rhs.GetNumerator();
    int numer = lhs.GetNumerator() * rhs.GetDenominator();
    Fraction res(numer, denom);
    res.MakeEasy();
    return res;
}

bool operator== (Fraction lhs, Fraction rhs) {
    return lhs.GetNumerator() == rhs.GetNumerator()
        && lhs.GetDenominator() == rhs.GetDenominator();
}

std::wstring ToString(const Fraction& c) {
    std::wostringstream ss;
    ss << c.GetNumerator() + '/' + c.GetDenominator();
    return ss.str();
}
