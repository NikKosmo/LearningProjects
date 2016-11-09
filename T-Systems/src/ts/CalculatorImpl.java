package ts;

/*
 * this class implements shunting-yard algorithm
 * 
 * evaluate method splits input String in Strings [], 
 * which consists of numbers, operators and brackets as they go
 * 
 * Then it uses 
 * convertToRPN method to convert String [] from infix notation to Reverse Polish Notation
 * 
 * And then it uses
 * RPNtoDouble to calculate expression
 * 
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CalculatorImpl implements Calculator {

    // Operators Map
    private static final Map<String, Integer> OPERATORS = new HashMap<>();
    static {
	// token, precedence
	OPERATORS.put("+", 0);
	OPERATORS.put("-", 0);
	OPERATORS.put("*", 1);
	OPERATORS.put("/", 1);
    }

    // check if token is operator
    private boolean isOperator(String token) {
	return OPERATORS.containsKey(token);
    }

    // compare precedence of operators
    private final int comparePrecedence(String token1, String token2) {
	return OPERATORS.get(token1) - OPERATORS.get(token2);
    }

    public static void main(String[] args) throws IOException {

	// Read from console, save it to String
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String input = br.readLine();

	Calculator c = new CalculatorImpl();
	System.out.println(c.evaluate(input));

    }

    @Override
    public String evaluate(String statement) {
	String answer = null;

	try {
	    String[] input = splitInput(statement);
	    String[] inputRPN = convertToRPN(input);
	    double result = calculateRPN(inputRPN);

	    // round result if needed
	    answer = String.valueOf(new BigDecimal(result).setScale(4, RoundingMode.DOWN).doubleValue());
	} catch (Exception e) {
	    System.out.println(e);
	    return null;
	}

	return answer;
    }
    
    private String[] splitInput(String input) {
	
	// TO DO:
	// right representation of negative numbers. "(" + "-" + "n" + ")" should be "(" + "-n" + ")"
	// transform (")" + "n") to ")*n" same for ("(" + "n")
	
	return input.split("(?<=[\\-+*/=()])|(?=[()\\-+*/=])");
    }

    // convert from infix to RPN
    private String[] convertToRPN(String[] inputTokens) {
	ArrayList<String> out = new ArrayList<String>();
	Stack<String> stack = new Stack<String>();

	for (String token : inputTokens) {
	    if (isOperator(token)) {
		while (!stack.empty() && isOperator(stack.peek())) {
		    if (comparePrecedence(token, stack.peek()) <= 0) {
			out.add(stack.pop());
			continue;
		    }
		    break;
		}
		stack.push(token);
	    }

	    // if token is a left bracket
	    else if (token.equals("(")) {
		stack.push(token);
	    }
	    // if token is a right bracket
	    else if (token.equals(")")) {
		while (!stack.empty() && !stack.peek().equals("(")) {
		    out.add(stack.pop());
		}
		stack.pop();
	    }

	    // if token is a number
	    else {
		out.add(token);
	    }
	}
	while (!stack.empty()) {
	    out.add(stack.pop());
	}
	String[] output = new String[out.size()];
	return out.toArray(output);
    }

    private double calculateRPN(String[] tokens) throws ArithmeticException, NumberFormatException {
	Stack<String> stack = new Stack<String>();

	// For each token
	for (String token : tokens) {
	    // If the token is a value push it onto the stack
	    if (!isOperator(token)) {
		stack.push(token);
	    } else {
		// Token is an operator: pop top two entries
		Double d2 = Double.valueOf(stack.pop());
		Double d1 = Double.valueOf(stack.pop());

		stack.push(String.valueOf(calculateSimple(d1, d2, token)));
	    }
	}

	return Double.valueOf(stack.pop());
    }

    private double calculateSimple(double d1, double d2, String sign) throws ArithmeticException {
	if (sign.charAt(0) == '*') {
	    return d1 * d2;
	} else if (sign.charAt(0) == '/') {
	    if (d2 == 0)
		throw new ArithmeticException();
	    return d1 / d2;
	} else if (sign.charAt(0) == '+') {
	    return d1 + d2;
	} else if (sign.charAt(0) == '-') {
	    return d1 - d2;
	} else {
	    // this statement will never be reached
	    return 0.0;
	}

    }

}
