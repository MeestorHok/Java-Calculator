public class TestClass2 {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        System.out.println(calculator.evaluate("pi"));
        System.out.println(calculator.evaluate("e"));
        System.out.println(calculator.evaluate("5/0"));
        System.out.println(calculator.evaluate("sin(1 + 3) * 4 - pi"));
        System.out.println(calculator.evaluate("2 / (5 ^ 2)"));
        System.out.println(calculator.evaluate("146 % 20"));
        System.out.println(calculator.evaluate("e^2/pi"));
        System.out.println(calculator.evaluate("sqrt(9)"));
        System.out.println(calculator.evaluate("1.5 / 44.0"));
        System.out.println(calculator.evaluate("cos(pi/2)"));
        System.out.println(calculator.evaluate("tan(pi/2)"));
        System.out.println(calculator.evaluate("sec(pi/2)"));
        System.out.println(calculator.evaluate("csc(pi/2)"));
        System.out.println(calculator.evaluate("cot(pi/2)"));
        System.out.println(calculator.evaluate("asin(0.7)"));
        System.out.println(calculator.evaluate("acos(0.66)"));
        System.out.println(calculator.evaluate("atan(45)"));
        System.out.println(calculator.evaluate("ln(e)"));
        System.out.println(calculator.evaluate("log(100)"));
    }
}
