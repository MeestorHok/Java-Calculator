public abstract class Token {
    public static boolean isOperator(char character) {
        return character == '^' || character == '%'
                || character == '*' || character == '/'
                || character == '+' || character == '-'
                || character == '!' || character == '|'
                || character == '&' || character == '<'
                || character == '>' || character == '=';
    }

    public static boolean isFunction(String item) {
        return item.equals("sin") || item.equals("cos")
                || item.equals("tan") || item.equals("sec")
                || item.equals("csc") || item.equals("cot")
                || item.equals("asin") || item.equals("atan")
                || item.equals("acos") || item.equals("sqrt")
                || item.equals("log") || item.equals("ln");
    }

    public static boolean isNamedNumber(String item) {
        return item.equals("pi") || item.equals("e");
    }

    public static double getNamedNumber(String number) {
        double value = 0;
        switch(number){
            case "pi":
                value = Math.PI;
                break;
            case "e":
                value = Math.E;
                break;
            default:
                break;
        }
        return value;
    }

    // 1 = right, -1 = left associativity
    public static int getAssociativity(char operator) {
        return (operator == '^' || operator == '!') ? 1 : -1;
    }

    // Precedences from https://en.wikipedia.org/wiki/Order_of_operations
    public static int getPrecedence(char operator) {
        int precedence;
        switch(operator) {
            case '^':
            case '!':
                precedence = 7;
                break;
            case '%':
            case '*':
            case '/':
                precedence = 6;
                break;
            case '+':
            case '-':
                precedence = 5;
                break;
            case '<':
            case '>':
                precedence = 4;
                break;
            case '=':
                precedence = 3;
                break;
            case '&':
                precedence = 2;
                break;
            case '|':
                precedence = 1;
                break;
            default:
                precedence = 0;
                break;
        }
        return precedence;
    }

    public static int numParameters(String operation) {
        return (operation.equals("!") || isFunction(operation)) ? 1 : 2;
    }

    public static double evaluate(String operation, double a) {
        double value = 0;
        switch(operation) {
            case "!":
                value = (a == 0) ? 1 : 0;
                break;
            case "sin":
                value = Math.sin(a);
                break;
            case "cos":
                value = Math.cos(a);
                break;
            case "tan":
                value = Math.tan(a);
                break;
            case "sec":
                value = 1.0 / Math.cos(a);
                break;
            case "csc":
                value = 1.0 / Math.sin(a);
                break;
            case "cot":
                value = 1.0 / Math.tan(a);
                break;
            case "asin":
                value = Math.asin(a);
                break;
            case "acos":
                value = Math.acos(a);
                break;
            case "atan":
                value = Math.atan(a);
                break;
            case "sqrt":
                value = Math.sqrt(a);
                break;
            case "ln":
                value = Math.log(a);
                break;
            case "log":
                value = Math.log10(a);
                break;
            default:
                break;
        }
        return value;
    }

    public static double evaluate(char operator, double b, double a) {
        double value = 0;
        switch(operator) {
            case '^':
                value = Math.pow(a, b);
                break;
            case '%':
                value = a % b;
                break;
            case '&':
                value = (a != 0 && b != 0) ? 1 : 0;
                break;
            case '|':
                value = (a != 0 || b != 0) ? 1 : 0;
                break;
            case '=':
                value = (a == b) ? 1 : 0;
                break;
            case '<':
                value = (a < b) ? 1 : 0;
                break;
            case '>':
                value = (a > b) ? 1 : 0;
                break;
            case '*':
                value = a * b;
                break;
            case '/':
                value = a / b;
                break;
            case '+':
                value = a + b;
                break;
            case '-':
                value = a - b;
                break;
            default:
                break;
        }
        return value;
    }
}
