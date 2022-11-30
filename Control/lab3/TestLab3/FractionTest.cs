using lab3;

namespace TestLab3
{
    [TestClass]
    public class TestFraction
    {
        Fraction num1, num2;

        public void setUp() { }

        public void tearDown() { }

        [TestMethod]
        public void TestConstructor()
        {
            int num = 1, dnm = 3;
            num2 = new Fraction(num, dnm);
            Assert.AreEqual(num2.GetNumerator(), num);
            Assert.AreEqual(num2.GetDenominator(), dnm);
        }


        [TestMethod]
        public void TestFloat()
        {
            num1 = new Fraction(0.5F);
            Assert.IsTrue(FracOper.IsEqual(num1, new Fraction(1, 2)));
        }

        [TestMethod]
        public void TestEqualityCheck()
        {
            num1 = new Fraction(1, 2);
            num2 = new Fraction(1, 2);
            Assert.IsTrue(FracOper.IsEqual(num1, num2));
        }

        [TestMethod]
        public void TestBinaryMinus()
        {
            num1 = new Fraction(1, 2);
            num2 = new Fraction(-1, 2);
            Assert.IsTrue(FracOper.IsEqual(num2, FracOper.Neg(num1)));
        }

        [TestMethod]
        public void TestMinus()
        {
            num1 = new Fraction(0, 0);
            num2 = new Fraction(5, 3);
            Assert.IsTrue(FracOper.IsEqual(FracOper.Neg(num2), FracOper.Min(num1, num2)));
        }

    
        public void TestMultiply()
        {
            num1 = new Fraction(2, 3);
            num2 = new Fraction(4, 5);
            Assert.IsTrue(FracOper.IsEqual(new Fraction(8, 15), FracOper.Mult(num1, num2)));
        }

    
        public void TestDivision()
        {
            num1 = new Fraction(-7, 22);
            num2 = new Fraction(4, 5);
            Assert.IsTrue(FracOper.IsEqual(num1, FracOper.Mult(FracOper.Div(num1, num2), num2)));
        }

    
        public void TestEasy()
        {
            num1 = new Fraction(2, 6);
            num2 = new Fraction(1, 3);
            num1.MakeEasy();
            Assert.IsTrue(FracOper.IsEqual(num1, num2));
        }
    }
}