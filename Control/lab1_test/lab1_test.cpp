#include "pch.h"
#include "CppUnitTest.h"
#include "../lab1/complex.h"
#include "../lab1/fraction.h"

using namespace Microsoft::VisualStudio::CppUnitTestFramework;

namespace lab1test
{
	TEST_CLASS(TestComplex)
	{
	public:
		Complex num1, num2;

		TEST_CLASS_INITIALIZE(ClassInitialize) {
		}

		TEST_METHOD(TestConstructor)
		{
			float rp = 1, ip = 3;
			num2 = Complex(rp, ip);
			Assert::AreEqual(num2.GetImagPart(), ip);
			Assert::AreEqual(num2.GetRealPart(), rp);
		}

		TEST_METHOD(TestEqualityCheck) {
			num1 = Complex(1, 2);
			num2 = Complex(1, 2);
			Assert::IsTrue(num1 == num2);
		}

		TEST_METHOD(TestBinaryMinus) {
			num1 = Complex(1, 2);
			num2 = Complex(-1, -2);
			Assert::AreEqual(num2, -num1);
		}

		TEST_METHOD(TestMinus) {
			num1 = Complex(0, 0);
			num2 = Complex(5, 3);
			Assert::AreEqual(-num2, num1 - num2);
		}

		TEST_METHOD(TestMultiply) {
			num1 = Complex(2, 3);
			num2 = Complex(4, 5);
			Assert::AreEqual(Complex(-7, 22), num1 * num2);
		}

		TEST_METHOD(TestDivision) {
			num1 = Complex(-7, 22);
			num2 = Complex(4, 5);
			Assert::AreEqual(num1, num1 / num2 * num2);
		}

		TEST_METHOD(TestConj) {
			num1 = Complex(6, -7);
			num2 = Complex(6, 7);
			Assert::AreEqual(num1, ~num2);
		}

		TEST_CLASS_CLEANUP(TestClassInfo) {
			return;
		}
	};

	TEST_CLASS(TestFrac)
	{
	public:
		Fraction num1, num2;

		TEST_CLASS_INITIALIZE(ClassInitialize) {
		}

		TEST_METHOD(TestConstructor)
		{
			int num = 1, dnm = 3;
			num2 = Fraction(num, dnm);
			Assert::AreEqual(num2.GetNumerator(), num);
			Assert::AreEqual(num2.GetDenominator(), dnm);
		}

		TEST_METHOD(TestEqualityCheck) {
			num1 = Fraction(1, 2);
			num2 = Fraction(1, 2);
			Assert::IsTrue(num1 == num2);
		}

		TEST_METHOD(TestBinaryMinus) {
			num1 = Fraction(1, 2);
			num2 = Fraction(-1, 2);
			Assert::AreEqual(num2, -num1);
		}

		TEST_METHOD(TestMinus) {
			num1 = Fraction(0, 0);
			num2 = Fraction(5, 3);
			Assert::AreEqual(-num2, num1 - num2);
		}

		TEST_METHOD(TestMultiply) {
			num1 = Fraction(2, 3);
			num2 = Fraction(4, 5);
			Assert::AreEqual(Fraction(8, 15), num1 * num2);
		}

		TEST_METHOD(TestDivision) {
			num1 = Fraction(-7, 22);
			num2 = Fraction(4, 5);
			Assert::AreEqual(num1, num1 / num2 * num2);
		}

		TEST_METHOD(TestEasy) {
			num1 = Fraction(2, 6);
			num2 = Fraction(1, 3);
			num1.MakeEasy();
			Assert::AreEqual(num1, num2);
		}

		TEST_CLASS_CLEANUP(TestClassInfo) {
			return;
		}
	};
}