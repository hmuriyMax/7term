public class Main {
    public static void main(String[] args) {
        Complex c1 = new Complex(1, 1);
        Complex c2 = new Complex(12, 13.5);
        System.out.println(ComOper.ToString(ComOper.Sum(c1, c2)));
        System.out.println(ComOper.ToString(ComOper.Min(c1, c2)));
        System.out.println(ComOper.ToString(ComOper.Mul(c1, c2)));
        System.out.println(ComOper.ToString(ComOper.Div(c1, c2)));
    }
}

