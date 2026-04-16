import java.util.Stack;
import java.util.Scanner;

public class InfixConverter {

    // Function to check precedence
    static int precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }

    // Function to convert infix to postfix
    static String infixToPostfix(String exp) {

        String result = "";
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {

            char c = exp.charAt(i);

            // If operand, add to result
            if (Character.isLetterOrDigit(c)) {
                result += c;
            }

            // If '(' push to stack
            else if (c == '(') {
                stack.push(c);
            }

            // If ')' pop until '('
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result += stack.pop();
                }
                stack.pop();
            }

            // Operator encountered
            else {
                while (!stack.isEmpty() &&
                       precedence(c) <= precedence(stack.peek())) {

                    result += stack.pop();
                }
                stack.push(c);
            }
        }

        // Pop remaining operators
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    // Function to reverse string
    static String reverse(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        return sb.toString();
    }

    // Function to convert infix to prefix
    static String infixToPrefix(String exp) {

        StringBuilder input = new StringBuilder(exp);
        input.reverse();

        // Replace brackets
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                input.setCharAt(i, ')');
            } else if (input.charAt(i) == ')') {
                input.setCharAt(i, '(');
            }
        }

        String postfix = infixToPostfix(input.toString());

        return reverse(postfix);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Infix Expression: ");
        String infix = sc.nextLine();

        String postfix = infixToPostfix(infix);
        String prefix = infixToPrefix(infix);

        System.out.println("Postfix Expression: " + postfix);
        System.out.println("Prefix Expression: " + prefix);

        sc.close();
    }
}