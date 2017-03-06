import structures.MyStack;
import structures.MyQueue;

public class Calculator {
    private MyStack<String> stack;
    private MyStack<Double> valueStack;
    private MyQueue<String> queue;

    public Calculator() {
        purgeTheJets(); // create a stack and queue
    }

    private String moveNextToken(String expression) throws InvalidExpressionException {
        int index = 0;
        String token = "";
        char item = expression.charAt(0);

        // Get full length of the number if necessary
        while (Character.isDigit(item) || item == '.') {
            token += item;
            index++;
            if (index < expression.length())
                item = expression.charAt(index);
            else
                break;
        }

        // if it was a number
        if (index != 0) {
            queue.enqueue(token);
            return expression.substring(index);
        }

        // If it is a function operator (sin, cos, tan, ln, log, ...) OR magic numbers (e, pi)
        while (Character.isAlphabetic(item)) {
            token += item;
            index++;
            if (index < expression.length())
                item = expression.charAt(index);
            else
                break;
        }

        // if it was a function or magic number
        if (index != 0) {
            if (token.equals("pi") || token.equals("e")) {
                queue.enqueue(token);
            } else {
                stack.push(token);
            }
            return expression.substring(index);
        }

        // If it is an operator
        if (Token.isOperator(item)) {
            while (!stack.isEmpty() // stack isn't empty
                    &&  Token.isOperator(stack.peek().charAt(0)) // item on stack is an operator
                    && ((Token.getPrecedence(stack.peek().charAt(0)) >= Token.getPrecedence(item) // lower or equal precedence and
                            && Token.getAssociativity(item) == -1) // left associative or
                        || (Token.getPrecedence(stack.peek().charAt(0)) > Token.getPrecedence(item) // lower precedence and
                            && Token.getAssociativity(item) == 1))) { // right associative
                queue.enqueue(stack.pop()); // move the operator from the stack to the queue
            }
            stack.push(Character.toString(item));
            return expression.substring(index + 1);
        }

        // If left bracket
        if (item == '(') {
            stack.push(Character.toString(item));
            return expression.substring(index + 1);
        }

        // If right bracket
        if (item == ')') {
            // Check if parentheses are valid
            if (stack.isEmpty())
                throw new InvalidExpressionException("Mismatched Parentheses.");

            while (!stack.peek().equals("(")) { // keep going until we find left bracket
                queue.enqueue(stack.pop()); // move an operator from the stack to the queue

                // Check if parentheses are valid
                if (stack.isEmpty())
                    throw new InvalidExpressionException("Mismatched Parentheses.");
            }

            stack.pop(); // remove left parenthesis

            if (!stack.isEmpty() && Token.isFunction(stack.peek())) { // if next item is a function call
                queue.enqueue(stack.pop()); // move it to the queue
            }

            return expression.substring(index + 1);
        }

        // Only reaches this if unknown token
        throw new InvalidExpressionException("Unknown Syntax.");
    }

    private void toPostfix(String infix) throws InvalidExpressionException {
        // Tokenize expression and build output
        while (infix.length() > 0) {
            infix = moveNextToken(infix);
        }

        // Cleanup leftover items in stack
        while (!stack.isEmpty()) {
            if (stack.peek().equals("("))
                throw new InvalidExpressionException("Mismatched Parentheses.");
            if (!Token.isOperator(stack.peek().charAt(0)))
                throw new InvalidExpressionException("Invalid Expression.");

            queue.enqueue(stack.pop());
        }
    }

    public String evaluate(String expression) {
        purgeTheJets(); // guarantee the stack and queue start empty (in case the last evaluation had an error)
        try {
            toPostfix(expression.toLowerCase().replace(" ", ""));
            return evaluate();
        } catch (InvalidExpressionException e) {
            return e.getMessage();
        }
    }

    private String evaluate() throws InvalidExpressionException {
        while (!queue.isEmpty()) {
            String item = queue.dequeue();

            if (Token.isOperator(item.charAt(0)) || Token.isFunction(item)) { // Operators and Functions
                int inputs = Token.numParameters(item); // should we use one or two numbers to calculate?

                if (inputs == 1) { // if we just need one number
                    if (valueStack.getSize() < 1) // Ensure it has been ordered properly
                        throw new InvalidExpressionException("Invalid Ordering.");

                    // evaluate it
                    valueStack.push(Token.evaluate(item, valueStack.pop()));
                } else if (inputs == 2) { // if we nned 2 numbers
                    if (valueStack.getSize() < 2) // Ensure it has been ordered properly
                        throw new InvalidExpressionException("Invalid Ordering.");

                    // evaluate it
                    valueStack.push(Token.evaluate(item.charAt(0), valueStack.pop(), valueStack.pop()));
                }
            } else if (Token.isNamedNumber(item)) { // named number
                valueStack.push(Token.getNamedNumber(item));
            } else { // number
                valueStack.push(Double.parseDouble(item));
            }
        }

        if (valueStack.getSize() != 1)
            throw new InvalidExpressionException("Invalid Ordering.");

        return valueStack.pop().toString();
    }

    private void purgeTheJets() {
        if (stack == null || !stack.isEmpty())
            stack = new MyStack<>();
        if (valueStack == null || !valueStack.isEmpty())
            valueStack = new MyStack<>();
        if (queue == null || !queue.isEmpty())
            queue = new MyQueue<>();
    }
}
