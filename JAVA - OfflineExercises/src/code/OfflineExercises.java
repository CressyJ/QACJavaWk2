package code;

public class OfflineExercises {

	// Given a string, return a string where
	// for every char in the original,
	// there are two chars.

	// doubleChar("The") → "TThhee"
	// doubleChar("AAbb") → "AAAAbbbb"
	// doubleChar("Hi-There") → "HHii--TThheerree"

	public String doubleChar(String input) {
		String output = "";
		
		for(int i = 0; i < input.length(); i++) output += input.substring(i, i+1) + input.substring(i, i+1); 
		
		return output;
	}
	
	//
	// A sandwich is two pieces of bread with something in between. Return the
	// string that is between the first and last appearance of "bread" in the
	// given string, or return the empty string "" if there are not two pieces
	// of bread.

	// getSandwich("breadjambread") → "jam"
	// getSandwich("xxbreadjambreadyy") → "jam"
	// getSandwich("xxbreadyy") → ""

	public String getSandwich(String input) {
		String output = "";
		String lookingFor = "bread";
		String temp = "";
		
		/* Could literally just be
		 * return input.substring(input.indexOf(lookingFor) + lookingFor.length(), input.lastIndexOf(lookingFor));
		 */
		
		//find the first instance of lookingFor
		for(int i = 0; i <= (input.length() - lookingFor.length()); i++) {
			if(input.substring(i, i + lookingFor.length()).equals(lookingFor)) {
				temp = input.substring(i + lookingFor.length());
				break;
			}
		}
		if (temp.isEmpty()) return output;
		if (temp.length() < lookingFor.length()) return output;	
		
		//find the last instance of lookingFor
		for(int i = 0; i <= (temp.length() - lookingFor.length()); i++){
			if(temp.substring(i, i + lookingFor.length()).equals(lookingFor)) {
				temp = temp.substring(0, i);
				break;
			}
		}
		
		output = temp;
		
		return output;
	}

	// Given three ints, a b c, one of them is small, one is medium and one is
	// large. Return true if the three values are evenly spaced, so the
	// difference between small and medium is the same as the difference between
	// medium and large.

	// evenlySpaced(2, 4, 6) → true
	// evenlySpaced(4, 6, 2) → true
	// evenlySpaced(4, 6, 3) → false

	public boolean evenlySpaced(int a, int b, int c) {
		boolean outcome = false;
		int temp = 0;
		
		//order the provided integers so that the order is a < b < c
		if (b > c) {
			temp = c;
			c = b;
			b = temp;
		}
		if (a > b) {
			temp = a;
			a = b;
			b = temp;
		}
		if (a > c) {
			temp = a;
			a = c;
			c = temp;
		}
		
		outcome = ((c - b) == (b - a)) ? true : false;
		
		return outcome;
	}

	// Given a string and an int n, return a string made of the first and last n
	// chars from the string. The string length will be at least n.

	// nTwice("Hello", 2) → "Helo"
	// nTwice("Chocolate", 3) → "Choate"
	// nTwice("Chocolate", 1) → "Ce"

	public String nTwice(String input, int a) {
		return input.substring(0, a) + input.substring(input.length() - a);
	}

	// Given a string, return true if it ends in "ly".

	// endsLy("oddly") → true
	// endsLy("y") → false
	// endsLy("oddy") → false

	public boolean endsly(String input) {
		return input.endsWith("ly");
	}

	// Given a string, return recursively a "cleaned" string where adjacent
	// chars that are the same have been reduced to a single char. So "yyzzza"
	// yields "yza".

	// stringClean("yyzzza") → "yza"
	// stringClean("abbbcdd") → "abcd"
	// stringClean("Hello") → "Helo"
	public String stringClean(String input) {
		String output = "";
		boolean processed = false;
		
		//search through from the second letter to see if it's the same as the previous one
		for(int i = 1; i < input.length(); i++) {
			if(input.substring(i-1, i).equals(input.substring(i, i+1))){
				output = input.substring(0, i-1);
				input = output + input.substring(i);
				processed = true;
				break;
			}
		}
		
		//if no letter is the same as the previous, then output what was given
		//otherwise run through again with the processed input
		if(processed) output = stringClean(input); 
		else output = input;
		
		return output;
	}

	// The fibonacci sequence is a famous bit of mathematics, and it happens to
	// have a recursive definition. The first two values in the sequence are 0
	// and 1 (essentially 2 base cases). Each subsequent value is the sum of the
	// previous two values, so the whole sequence is: 0, 1, 1, 2, 3, 5, 8, 13,
	// 21 and so on. Define a recursive fibonacci(n) method that returns the nth
	// fibonacci number, with n=0 representing the start of the sequence.

	// fibonacci(0) → 0
	// fibonacci(1) → 1
	// fibonacci(2) → 1

	public int fibonacci(int input) {
		if(input <= 1) return input;
		
		return fibonacci(input-1) + fibonacci(input-2);
	}

	// We have a number of bunnies and each bunny has two big floppy ears. We
	// want to compute the total number of ears across all the bunnies
	// recursively (without loops or multiplication).
	//
	// bunnyEars(0) → 0
	// bunnyEars(1) → 2
	// bunnyEars(2) → 4

	public int bunnyEars(int input) {
		if(input == 0) return input;
		
		return 2 + bunnyEars(input-1);
	}

}
