import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class ParameterizeTest {
    private final float valueA;
    private final int num;
    private final int denom;

    public ParameterizeTest(float valueA, int valueB, int expected) {
        this.valueA = valueA;
        this.num = valueB;
        this.denom = expected;
    }

    @Parameterized.Parameters(name = "{index}:Construct {0} = {1}/{2}")
    public static Iterable<Object[]> dataForTest() {
        return Arrays.asList(new Object[][]{
                {0.5F, 1, 2},
        });
    }

    @Test
    public void TestConstruct() throws Exception {
        Assert.assertTrue(FracOper.IsEqual(new Fraction(valueA), new Fraction(num, denom)));
    }
}
