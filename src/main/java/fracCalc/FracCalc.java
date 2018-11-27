/*
 *
 * @author Mr. Rasmussen
 */

package fracCalc;
import java.util.*;
//Naomi Bashkansky P.2
//The program accepts numbers and operators and prints out the reduced,
//never improper answer of the expression. Keeps on accepting lines of input until
//the user enters "quit"
	/*Pre: User types in a number made of either an int; an int, a /, and an int;
		or an int, an _, an int, a slash, and an int. Then, as many times as the user wants: 
		a space, an operator, another space, another number. When done typing expression,
		user enters. (e.g. -1_1/2 + 1/4 * 1/2). User keeps entering such lines of input
		until the user enters in "quit".
	  Post: Prints the result of the expression (e.g. -5/8) calculated left to right
	*/
public class FracCalc {

    public static void main(String[] args) {
        // Gets user input and prints answer from produceAnswer
    	Scanner console = new Scanner(System.in);
    	String input = "";
    	String answer = "";
    	System.out.println("Type quit to exit. ");
    	System.out.print("Input: ");
		input = console.nextLine();
    	while (!input.equals("quit")) {
    		answer = produceAnswer(input);
	    	System.out.println(answer);
	    	System.out.print("Input: ");
    		input = console.nextLine();
    	}
    	console.close();
    }
    
    public static String produceAnswer(String input) {
        // Separates the numbers and the operators,
    	//and returns the result of the expression reduced and never improper.
    	//Returns error if divide by 0 or if input is in an invalid format
    	int firstIndexSpace = input.indexOf(' ');
    	String first = input.substring(0, firstIndexSpace);
    	input = input.substring(firstIndexSpace + 1);
    	char operator = input.charAt(0);
    	int secondIndexSpace = input.indexOf(' ');
    	input = input.substring(secondIndexSpace + 1);
    	String second = input.substring(0);
    	
    	if (!(valid(first, second, operator))) {
    		if (input.indexOf(' ') == -1) {
    			return "ERROR: Input is in an invalid format.";
    		} else {
    			return multipleOps(input, first, operator);
    		}
    	}
    	
    	boolean underscore = false;
    	boolean slash = false;
    	for (int index = 0; index < first.length(); index++) {
    		if (first.charAt(index) == '_') {
    			underscore = true;
    		} else if (first.charAt(index) == '/') {
    			slash = true;
    		}
    	}
    	int indexU = first.indexOf('_');
		int indexS = first.indexOf('/');
		int whole1;
		int numerator1;
		int denominator1;
    	if (underscore && slash) {
    		whole1 = Integer.parseInt(first.substring(0, indexU));
    		numerator1 = Integer.parseInt(first.substring(indexU+1, indexS));
    		if (whole1 < 0) {
    			numerator1 *= -1;
    		}
    		denominator1 = Integer.parseInt(first.substring(indexS+1));
    		numerator1 += whole1 * denominator1;
    	} else if (!underscore && slash) {
    		numerator1 = Integer.parseInt(first.substring(0, indexS));
    		denominator1 = Integer.parseInt(first.substring(indexS+1));
    	} else {
    		denominator1 = 1;
    		numerator1 = Integer.parseInt(first);
    	}
    	
    	underscore = false;
    	slash = false;
    	for (int index = 0; index < second.length(); index++) {
    		if (second.charAt(index) == '_') {
    			underscore = true;
    		} else if (second.charAt(index) == '/') {
    			slash = true;
    		}
    	}
    	int whole2;
    	int numerator2;
    	int denominator2;
    	indexU = second.indexOf('_');
		indexS = second.indexOf('/');
    	if (underscore && slash) {
    		whole2 = Integer.parseInt(second.substring(0, indexU));
    		numerator2 = Integer.parseInt(second.substring(indexU+1, indexS));
    		if (whole2 < 0) {
    			numerator2 *= -1;
    		}
    		denominator2 = Integer.parseInt(second.substring(indexS+1));
    		numerator2 += whole2 * denominator2;
    	} else if (!underscore && slash) {
    		numerator2 = Integer.parseInt(second.substring(0, indexS));
    		denominator2 = Integer.parseInt(second.substring(indexS+1));
    	} else {
    		numerator2 = Integer.parseInt(second);
    		denominator2 = 1;
    	}
    	
    	if (denominator1 == 0 || denominator2 == 0 || operator == '/' && numerator2 == 0) {
    		return "ERROR: Cannot divide by zero.";
    	}
    	
    	int answerNumerator;
    	int answerDenominator = denominator1 * denominator2;
    	if (operator == '+') {
    		answerNumerator = numerator1 * denominator2 + numerator2 * denominator1;
    	} else if (operator == '-') {
    		answerNumerator = numerator1 * denominator2 - numerator2 * denominator1;
    	} else if (operator == '*') {
    		answerNumerator = numerator1 * numerator2;
    	} else {
    		answerNumerator = numerator1 * denominator2;
    		answerDenominator = denominator1 * numerator2;
    	}
    	
    	if (answerDenominator == 1 || answerNumerator % answerDenominator == 0) {
    		return "" + (answerNumerator / answerDenominator);
    	} 
    	int multiplier = 1;
    	if (answerNumerator < 0) {
    		multiplier *= -1;
    		answerNumerator *= -1;
    	}
    	if (answerDenominator < 0) {
    		multiplier *= -1;
    		answerDenominator *= -1;
    	}
    	for (int i = 2; i <= Math.min(answerNumerator, answerDenominator); i++) {
    		while (answerNumerator % i == 0 && answerDenominator % i == 0) {
    			answerNumerator /= i;
    			answerDenominator /= i;
    		}
    	}
    	if (answerNumerator < answerDenominator) {
    		return "" + (answerNumerator * multiplier) + '/' + answerDenominator;
    	}
    	int whole = 0;
    	while (answerNumerator > answerDenominator) {
    		answerNumerator -= answerDenominator;
    		whole += 1;
    	}
    	return "" + (whole * multiplier) + '_' + answerNumerator + '/' + answerDenominator;
    }
    
    public static boolean valid(String first, String second, char operator) {
    	//Checks for invalid format
    	for (int i = 0; i < first.length(); i++) {
    		char c = first.charAt(i);
    		if (!(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || 
    				c == '6' || c == '7' || c == '8' || c == '9' || c == '/' || c == '_' || 
    				c == '-')) {
    			return false;
    		}
    	}
    	for (int i = 0; i < second.length(); i++) {
    		char c = second.charAt(i);
    		if (!(c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || 
    				c == '6' || c == '7' || c == '8' || c == '9' || c == '/' || c == '_' || 
    				c == '-')) {
    			return false;
    		}
    	}    	
    	if (!(operator == '+' || operator == '-' || operator == '*' || operator == '/')) {
    		return false;
    	}
    	return true;
    }
    
    public static String multipleOps(String input, String first, char operator) {
    	//Handles arbitrarily many values and operators, calculated left to right
    	int nextIndexSpace = input.indexOf(' ');
	    String nextNum = input.substring(0, nextIndexSpace);
	    String newNum = first;
	    newNum = produceAnswer(newNum + " " + operator + " " + nextNum);
		while (input.indexOf(' ') != -1) {
			nextIndexSpace = input.indexOf(' ');
			input = input.substring(nextIndexSpace + 1);
			operator = input.charAt(0);
			input = input.substring(2);
			nextIndexSpace = input.indexOf(' ');
			if (nextIndexSpace != -1) {
				nextNum = input.substring(0, nextIndexSpace);
			} else {
				nextNum = input.substring(0);
			}
			newNum = produceAnswer(newNum + " " + operator + " " + nextNum);
		}
		return newNum;
    }
}