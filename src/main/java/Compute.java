import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// This class computes the answer of a expression. It is all static methods.
// This version works for expressions with or without spaces. It also works
// for negative sign. (using & for negative sign).
// InfixConversion and Compute combined together.
// it does not do Preconditions nor postconditions =P
public class Compute {

    public static String computeAnswer(String equation) {
        Stack<String> numberStack = new Stack<>();
        String operand1, operand2, strResult, nextString;
        double op1, op2, result;

        StringTokenizer strToken = new StringTokenizer(equation);

        while (strToken.hasMoreTokens()) {
            nextString = strToken.nextToken();

            if (isNumber(nextString)) {
                numberStack.push(nextString);
            }

            if (nextString.equals("&")) {
                operand1 = (String) numberStack.pop();
                op1 = Double.parseDouble(operand1);
                result = -1 * op1;
                numberStack.push(Double.toString(result));
            }

            if (isSymbol(nextString)) {
                operand2 = (String) numberStack.pop();
                operand1 = (String) numberStack.pop();
                op1 = Double.parseDouble(operand1);
                op2 = Double.parseDouble(operand2);

                switch (nextString) {
                    case "+":
                        result = op1 + op2;
                        break;
                    case "-":
                        result = op1 - op2;
                        break;
                    case "*":
                        result = op1 * op2;
                        break;
                    case "/":
                        result = op1 / op2;
                        break;
                    default:
                        throw new IllegalArgumentException("Illegal");
                }


                numberStack.push(Double.toString(result));
            }//if

        }//while
        strResult = (String) numberStack.pop();

        return strResult;


    }//computeAnswer


    public static String convert(String infix) {
        String nextString, postfixStr = "";
        Stack<String> symbolStack = new Stack<>();

        String delimit = "+-*/()&";
        StringTokenizer tk = new StringTokenizer(infix, delimit, true);

        while (tk.hasMoreTokens()) {
            nextString = tk.nextToken().trim();
            if (nextString.equals("("))
                symbolStack.push(nextString);
            else if (nextString.equals("")) {
            } else if (nextString.equals("&"))
                symbolStack.push(nextString);
            else if (isNumber(nextString)) {
                postfixStr = postfixStr + nextString + " ";
                while (!symbolStack.empty() && (symbolStack.peek()).equals("&"))
                    postfixStr = postfixStr + symbolStack.pop() + " ";
            } else if (isSymbol(nextString)) {
                while (!symbolCondition(nextString, symbolStack)) {
                    postfixStr = postfixStr + symbolStack.pop() + " ";
                }
                symbolStack.push(nextString);
            } else {
                while (!topisLeftParent(symbolStack)) {
                    postfixStr = postfixStr + symbolStack.pop() + " ";
                }
                symbolStack.pop();
            }
        }
        while (!symbolStack.empty()) {
            postfixStr = postfixStr + symbolStack.pop() + " ";
        }

        return postfixStr;
    }


    public static boolean isNumber(String next) {
        try {
            Integer.parseInt(next);
            return true;
        } catch (NumberFormatException e) {
            try {
                Double.parseDouble(next);
                return true;
            } catch (NumberFormatException f) {
                return false;
            }
        }
    }


    public static boolean topisLeftParent(Stack symbolStack) {
        return ((symbolStack.peek()).equals("("));
    }


    public static boolean isSymbol(String next) {
        if (next.equals("+") || next.equals("-") || next.equals("*") || next.equals("/"))
            return true;
        return false;
    }


    public static boolean symbolCondition(String next, Stack symbolStack) {
        if (symbolStack.empty())
            return true;
        else if (topisLeftParent(symbolStack))
            return true;
        else if (isLowerPrecedence(next, symbolStack))
            return true;
        return false;
    }


    public static boolean isLowerPrecedence(String next, Stack symbolStack) {
        if (next.equals("*") || next.equals("/")) {
            if ((symbolStack.peek()).equals("+") || (symbolStack.peek()).equals("-"))
                return true;
        }
        return false;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter an expression, fag. Put space in between");
        System.out.println("eg. 1 + 2 * 3 - 4");
        System.out.println("eg. ( 1 + 2 ) * 3 / ( 4 - 5) ");
        String tempInput = in.readLine();
        String output;
        output = convert(tempInput);
        System.out.println("Postfix: " + output);

        try {
            System.out.println("Answer: " + computeAnswer(output));
        } catch (Exception e) {
            System.out.print("error");
        }

    }


}//Compute