/*
 *
 * @author Mr. Rasmussen
 */

package fracCalc;
import java.util.*;
//Naomi Bashkansky P.2
//Checkpoint 3 accepts two numbers and an operator and prints out the answer of the
//expression, though not necessarily as a reduced, mixed fraction
	/*Pre: User types in a number made of either an int; an int, a /, and an int;
		or an int, an _, an int, a slash, and an int. Then, a space, an operator, another
		space, another number, and enters. User keeps typing such a line until "quit"
		is entered. (e.g. -1_1/2 + 1/4)
	  Post: Prints the result of the expression
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
        // Separates the first number, the operator, and the second number,
    	//and returns the result of the expression (though not necessarily simplified)
    	int firstIndexSpace = input.indexOf(" ");
    	String first = input.substring(0, firstIndexSpace);
    	input = input.substring(firstIndexSpace + 1);
    	char operator = input.charAt(0);
    	String second = input.substring(2);
    	
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
        
    	return "" + answerNumerator + "/" + answerDenominator;
    }
}