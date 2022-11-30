import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class TestComplex {
    Complex num1, num2;
    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void TestConstructor()
    {
        double rp = 1, ip = 3;
        num2 = new Complex(rp, ip);
        Assert.assertEquals(num2.GetImagPart(), ip, 0);
        Assert.assertEquals(num2.GetRealPart(), rp, 0);
    }

    @Test
    public void TestEqualityCheck() {
        num1 = new Complex(1, 2);
        num2 = new Complex(1, 2);
        Assert.assertTrue(ComOper.IsEqual(num1, num2));
    }

    @Test
    public void TestBinaryMinus() {
        num1 = new Complex(1, 2);
        num2 = new Complex(-1, -2);
        Assert.assertTrue(ComOper.IsEqual(num2, ComOper.Neg(num1)));
    }

    @Test
    public void TestMinus() {
        num1 = new Complex();
        num2 = new Complex(5, 3);
        Assert.assertTrue(ComOper.IsEqual(ComOper.Neg(num2), ComOper.Min(num1, num2)));
    }

    @Test
    public void TestMultiply() {
        num1 = new Complex(2, 3);
        num2 = new Complex(4, 5);
        Assert.assertTrue(ComOper.IsEqual(new Complex(-7, 22), ComOper.Mul(num1, num2)));
    }

    @Test
    public void TestDivision() {
        num1 = new Complex(-7, 22);
        num2 = new Complex(4, 5);
        Assert.assertTrue(ComOper.IsEqual(num1, ComOper.Mul(ComOper.Div(num1, num2), num2)));
    }

    @Test(timeout = 1)
    public void TestConj(){
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException ignored) {}
        num1 = new Complex(6, -7);
        num2 = new Complex(6, 7);
        Assert.assertTrue(ComOper.IsEqual(num1, ComOper.Conj(num2)));
    }
}
