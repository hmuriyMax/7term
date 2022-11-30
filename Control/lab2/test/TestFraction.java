import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
//import org.junit.runners.PA

public class TestFraction {
    Fraction num1, num2;

    @Before
    public void setUp() {}

    @After
    public void tearDown() {}

    @Test
    public void TestConstructor() throws Exception {
        int num = 1, dnm = 3;
        num2 = new Fraction(num, dnm);
        Assert.assertEquals(num2.GetNumerator(), num);
        Assert.assertEquals(num2.GetDenominator(), dnm);
    }


    @Test
    public void TestFloat() throws Exception {
        num1 = new Fraction(0.5F);
        Assert.assertTrue(FracOper.IsEqual(num1, new Fraction(1, 2)));
    }

    @Test
    public void TestEqualityCheck() throws Exception {
        num1 = new Fraction(1, 2);
        num2 = new Fraction(1, 2);
        Assert.assertTrue(FracOper.IsEqual(num1, num2));
    }

    @Test
    public void TestBinaryMinus() throws Exception {
        num1 = new Fraction(1, 2);
        num2 = new Fraction(-1, 2);
        Assert.assertTrue(FracOper.IsEqual(num2, FracOper.Neg(num1)));
    }

    @Test
    public void TestMinus() throws Exception {
        num1 = new Fraction(0, 0);
        num2 = new Fraction(5, 3);
        Assert.assertTrue(FracOper.IsEqual(FracOper.Neg(num2), FracOper.Min(num1, num2)));
    }

    @Test
    public void TestMultiply() throws Exception {
        num1 = new Fraction(2, 3);
        num2 = new Fraction(4, 5);
        Assert.assertTrue(FracOper.IsEqual(new Fraction(8, 15), FracOper.Mult(num1, num2)));
    }

    @Test
    public void TestDivision() throws Exception {
        num1 = new Fraction(-7, 22);
        num2 = new Fraction(4, 5);
        Assert.assertTrue(FracOper.IsEqual(num1, FracOper.Mult(FracOper.Div(num1, num2), num2)));
    }

    @Test
    public void TestEasy() throws Exception {
        num1 = new Fraction(2, 6);
        num2 = new Fraction(1, 3);
        num1.MakeEasy();
        Assert.assertTrue(FracOper.IsEqual(num1, num2));
    }
}
