#pragma once
#include <iostream>
#include <sstream>

class Complex {
private:
    float real_part;
    float imaginary;

public:
    Complex() {
        real_part = 0.0;
        imaginary = 0.0;
    }

    Complex(float real, float imag) {
        real_part = imag;
        imaginary = real;
    }
    float GetRealPart() const {
        return real_part;
    }
    float GetImagPart() const {
        return imaginary;
    }
};

Complex operator+(Complex lhs, Complex rhs) {
    return Complex(lhs.GetRealPart() + rhs.GetRealPart(), lhs.GetImagPart() + rhs.GetImagPart());
}

Complex operator-(Complex lhs) {
    return Complex(-lhs.GetRealPart(), -lhs.GetImagPart());
}

Complex operator-(Complex lhs, Complex rhs) {
    return lhs + (-rhs);
}

Complex operator*(Complex lhs, Complex rhs) {
    return Complex(lhs.GetRealPart() * rhs.GetRealPart() - lhs.GetImagPart() * rhs.GetImagPart(),
        lhs.GetRealPart() * rhs.GetImagPart() + lhs.GetImagPart() * rhs.GetRealPart());
}

Complex operator~ (Complex lhs) {
    return Complex(lhs.GetRealPart(), -lhs.GetImagPart());
}

Complex operator/ (Complex lhs, Complex rhs) {
    float x1 = lhs.GetRealPart();
    float x2 = rhs.GetRealPart();
    float y1 = lhs.GetImagPart();
    float y2 = rhs.GetImagPart();
    return Complex((x1 * x2 + y1 * y2) / (x2 * x2 + y2 * y2), (x2 * y1 - x1 * y2) / (x2 * x2 + y2 * y2));
}

bool operator== (Complex lhs, Complex rhs) {
    return lhs.GetRealPart() == rhs.GetRealPart() && lhs.GetImagPart() == rhs.GetImagPart();
}

std::wstring ToString(const Complex& c) {
    std::wostringstream ss;
    ss <<  c.GetRealPart() + 'i' + c.GetImagPart();
    return ss.str();
}
