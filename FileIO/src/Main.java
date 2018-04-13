import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

public class Main {
	public static void main(String[] args) {
		String filePath = "C:\\Users\\Admin\\James\\FileIO\\";
		String outputFile = filePath + "output.csv";
		
		ArrayList<Person> people = new ArrayList<Person>();
		people.add(new Person("Dave", "Teacher", 34));
		people.add(new Person("Max", "IT Contractor", 25));
		people.add(new Person("Millie", "Child", 9));
		people.add(new Person("Jurgen", "Manager", 40));
		people.add(new Person("Irene", "Singer", 19));
		
		String success = outputList(outputFile, people) ? "File output successfully." : "File not output.";
		System.out.println(success);
		
		ArrayList<Person> intake = new ArrayList<Person>();
		
		success = inputList(outputFile, intake) ? "File read successfully." : "File not read.";
		System.out.println(success);
		
		ListIterator<Person> i = intake.listIterator();
		
		while(i.hasNext()) {
			//used to ensure that I'm calling toString on the right object
			Person temp = i.next();
			System.out.println(temp.toString());
			System.out.println("====================");
		}
	}
	
/* To output using BufferedWriter requires the following:
 * import java.io.BufferedWriter;
 * import java.io.FileWriter;
 * import java.io.IOException;
 * 
 * BufferedWriter doesn't use \n or other escape characters, so including them isn't much help,
 * as it may instead output characters representing them
 */
	public static boolean outputList(String outputFile, ArrayList<Person> input) {
		if(input.isEmpty()) return false;
		
		//Try/catch block required for BufferedWriter to work
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
			//Used to iterate through with some protection
			ListIterator<Person> i = input.listIterator();
			
			while(i.hasNext()) {
				//used to ensure that I'm calling toString on the right object
				Person temp = i.next();
				//outputs exactly the string given to it
				bw.write(temp.toCSV());
				//Required to put a new line in the output file
				bw.newLine();
			}
			
			//included to avoid memory leaks
			bw.close();
			
		} catch(IOException ioe) {
			//This is my guess on what the exception is likely to be
			//I could instead print the exception details with the commented line
			System.out.println("Incorrect file name.");
			//ioe.printStackTrace();
			
			return false;
		}
		
		//only returns if the try block is attempted successfully
		return true;
	}
	
/* To input using BufferedReader requires the following:
 * import java.io.BufferedReader;
 * import java.io.FileReader;
 * import java.io.IOException;
 * 
 * The easiest use of BufferedReader is to take in text line by line and then
 * split the read line by a simple regex to get an array in a simple format
 */
	public static boolean inputList(String inputFile, ArrayList<Person> output) {
		//Used to stave off any file reading issues
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			String inputLine;
			//required to store the split inputLine, which must be three parameters
			String[] inputArray = new String[3];
			
			//read each line in turn
			while((inputLine = br.readLine()) != null) {
				//split the read line by commas
				inputArray = inputLine.split(",");
				//as we know the format, use the input string array to populate the constructor
				output.add(new Person(inputArray[0], inputArray[1], Integer.parseInt(inputArray[2])));
			}
			
			//included to avoid memory leaks
			br.close();
		} catch(IOException ioe) {
			System.out.println("Read error.");
			return false;
		}
		
		return true;
	}
}