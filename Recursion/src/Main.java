public class Main {
	public static void main(String[] args) {
		System.out.println(comboBreaker("hello"));
		System.out.println(comboBreaker("zzzz"));
		System.out.println(comboBreaker("Mississipi"));
	}

//This function calls the actually recursive function, which needs the counter input to work
	public static String comboBreaker(String input) {
		return comboBreaker(input, 0);
	}
	
//Using the wonders of method overloading, the user never needs to know that we're using an integer counter to step through the String
	public static String comboBreaker(String input, int counter) {
		//Ensure that the counter isn't out of bounds for the String
		if (counter > 0 && counter < input.length())
			//Compare the previous letter to the current letter
			if(input.substring(counter-1, counter).equalsIgnoreCase(input.substring(counter, counter+1)))
				//if they're the same, put a hyphen between the two letters that are the same
				input = input.substring(0, counter) + "-" + input.substring(counter);
		
		//If we're at or over the end of the String, return the result
		if (counter >= input.length()) return input;
		//Otherwise, step through to the next letter and check again
		return comboBreaker(input, ++counter);
	}
}