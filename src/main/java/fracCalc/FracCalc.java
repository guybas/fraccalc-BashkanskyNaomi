/*
 *
 * @author Mr. Rasmussen
 */

package fracCalc;
import java.util.*;
//Naomi Bashkansky P.2
//Checkpoint 1 prints the second number
	//Pre: number, space, operator, space, number (1_1/2 - 1/4)
	//Post: number (1/4)
public class FracCalc {

    public static void main(String[] args) {
        // 
    	Scanner console = new Scanner(System.in);
    	System.out.print("Input: ");
    	String input = console.nextLine();
    	String answer = produceAnswer(input);
    	System.out.println(answer);
    	console.close();
    }
    
    public static String produceAnswer(String input) {
        // Seperators the first number, the operator, and the second number
    	int firstIndexSpace = input.indexOf(" ");
    	String first = input.substring(0, firstIndexSpace);
    	input = input.substring(firstIndexSpace + 1);
    	String operator = input.substring(0, 1);
    	String second = input.substring(2);
        return second;
    }
}