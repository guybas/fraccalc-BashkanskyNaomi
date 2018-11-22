/*
 *
 * @author Mr. Rasmussen
 */

package fracCalc;
import java.util.*;
//Naomi Bashkansky P.2
//Checkpoint 2 prints the second number as 3 integers: the whole number, the numerator,
//and the denominator
	/*Pre: User types in a number made of either an int; an int, a /, and an int;
		or an int, an _, an int, a slash, and an int. Then, a space, an operator, another
		space, another number, and enters. User keeps typing such a line until "quit"
		is entered. (e.g. 1_1/2 + 1/4)
	  Post: Prints the second number as a whole, a denominator, and a numerator
	 	(whole:0 numerator:1 denominator:4)
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
    	//and parses both operands while only returning the second one
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
    		denominator1 = Integer.parseInt(first.substring(indexS+1));
    	} else if (!underscore && slash) {
    		whole1 = 0;
    		numerator1 = Integer.parseInt(first.substring(0, indexS));
    		denominator1 = Integer.parseInt(first.substring(indexS+1));
    	} else {
    		whole1 = Integer.parseInt(first);
    		numerator1 = 0;
    		denominator1 = 1;
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
    		denominator2 = Integer.parseInt(second.substring(indexS+1));
    	} else if (!underscore && slash) {
    		whole2 = 0;
    		numerator2 = Integer.parseInt(second.substring(0, indexS));
    		denominator2 = Integer.parseInt(second.substring(indexS+1));
    	} else {
    		whole2 = Integer.parseInt(second);
    		numerator2 = 0;
    		denominator2 = 1;
    	}    	
        return "whole:" + whole2 + " numerator:" + numerator2 + " denominator:" + denominator2;
    }
}