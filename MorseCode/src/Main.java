import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		MorseTranslator fromMorse = new MorseTranslator();
		
		String input;
		input = ".... . .-.. .-.. --- / -.. .- .. .-.. -.-- / .--. .-. --- --. .-. .- -- -- . .-. / --. --- --- -.. / .-.. ..- -.-. -.- / --- -. / - .... . / -.-. .... .- .-.. .-.. . -. --. . ... / - --- -.. .- -.--";
		
		System.out.println(input);
		System.out.println("Translates to:");
		System.out.println(fromMorse.readMorse(input));
		
		System.out.println("\nWhat to say in morse?");
		Scanner sc = new Scanner(System.in);
		
		String message = sc.nextLine();
		System.out.println("\"" + message + "\" translates to:");
		System.out.println(fromMorse.writeMorse(message));
		
		sc.close();
	}
}
