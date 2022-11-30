using lab3;

Complex c1 = new Complex(1, 1);
Complex c2 = new Complex(12, 13.5);
Console.WriteLine(ComOper.ToString(ComOper.Sum(c1, c2)));
Console.WriteLine(ComOper.ToString(ComOper.Min(c1, c2)));
Console.WriteLine(ComOper.ToString(ComOper.Mul(c1, c2)));
Console.WriteLine(ComOper.ToString(ComOper.Div(c1, c2)));