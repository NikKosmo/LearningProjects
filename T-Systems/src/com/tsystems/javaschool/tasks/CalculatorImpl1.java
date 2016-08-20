package com.tsystems.javaschool.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class CalculatorImpl1 implements Calculator {

    public static void main(String[] args) throws IOException {

	// Read from console, save it to String
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	String str = br.readLine();

	Calculator c = new CalculatorImpl1();
	System.out.println(c.evaluate(str));

    }

    @Override
    public String evaluate(String statement) {
	
	StringBuilder sb = new StringBuilder(statement);
	System.out.println(sb);
	int start = 0;
	int pos;
	while(true){
	    pos = sb.indexOf("(", start);
	    if (pos == -1) break;
	    if (sb.indexOf("(", pos + 1) !=-1 
		    && sb.indexOf("(", pos + 1) < sb.indexOf(")", pos +1)) {
		start = sb.indexOf("(", pos + 1);
	    } else
	    {
		sb.replace(pos, 1 + sb.indexOf(")", pos +1), expressionCalculate(sb.substring(pos + 1, sb.indexOf(")", pos +1))));
		start = 0;
		System.out.println(sb);
	    }
	}

	// TODO Auto-generated method stub
	return expressionCalculate(sb.toString());
    }

    private String expressionCalculate(String expression) {
	ArrayList<Double> numbers = new ArrayList<Double>();
	LinkedList<Character> signs = new LinkedList<Character>();

	// Parse double numbers from expression.
	String[] nums = expression.split("[-+*/]");
	for (String str : nums) {
	    numbers.add(Double.parseDouble(str));
	}

	// Parse arifmetic signs from expression.
	for (char c : expression.toCharArray()) {
	    if ("[-+*/]".contains("" + c)) {
		signs.add(c);
	    }
	}
	System.out.println(numbers);
	System.out.println(signs);

	// Cycle through signs list, first '*' and '/'
	ListIterator<Character> iterSigns = signs.listIterator();
	ArrayList<Double> numBuffer = new ArrayList<>();

	int position = 0;
	char c;
	Double result = null;
	while (iterSigns.hasNext()) {
	    c = iterSigns.next();
	    if (c == '*' || c == '/') {
		if (result == null) {
		    result = arifmeticAction(numbers.get(position), 
			    numbers.get(position + 1), c);
		} else {
		    result = arifmeticAction(result, numbers.get(position + 1), c);
		}
		if (!iterSigns.hasNext())
		    numBuffer.add(result);
		iterSigns.remove();
	    } else {
		if (result == null) {
		    numBuffer.add(numbers.get(position));
		} else {
		    numBuffer.add(result);
		    result = null;
		}
		if (!iterSigns.hasNext())
		    numBuffer.add(numbers.get(position+1));

	    }
	    position++;
	}

//	System.out.println(signs);
//	System.out.println(numbers);
//	System.out.println(numBuffer);

	ListIterator<Character> newIterSigns = signs.listIterator();
	position = 0;
	result = null;
	while (newIterSigns.hasNext()) {
	    c = newIterSigns.next();
	    if (result == null) {
		result = arifmeticAction(numBuffer.get(position), 
			numBuffer.get(position + 1), c);
	    } else {
		result = arifmeticAction(result, numBuffer.get(position + 1), c);
	    }
	    position++;

	}
	return result.toString();
    }

    private double arifmeticAction(double a, double b, char c) {
	double result = 0.0;
	switch (c) {
	case '*':
	    result = a * b;
	    break;
	case '/':
	    result = a / b;
	    break;
	case '+':
	    result = a + b;
	    break;
	case '-':
	    result = a - b;
	    break;
	}
	return result;

    }

}
