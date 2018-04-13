//The most unnecessarily complex hello world
public class Main {
	public static void main(String[] args) {
		String output;
		
		//This didn't work
		//if (!args[0].isEmpty())	output = args[0];
		//else
		
		output = "Hello World!";
		
		//System.out.println("Hello World!");
		//System.out.println(output);
		System.out.println(helloWorld(output));
	}
	
	public static String helloWorld(String input) {
		//System.out.println(input);
		
		return input;
	}
}