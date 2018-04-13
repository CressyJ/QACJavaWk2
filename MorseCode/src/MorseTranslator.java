import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//Class that speaks Morse Code
public class MorseTranslator {
	static private Map<String, String> morseMap;
	static private Map<String, String> alphabetMap;
	
	static {
		morseMap = new HashMap<String, String>();
		morseMap.put(".-", "a");
		morseMap.put("-...", "b");
		morseMap.put("-.-.", "c");
		morseMap.put("-..", "d");
		morseMap.put(".", "e");
		morseMap.put("..-.", "f");
		morseMap.put("--.", "g");
		morseMap.put("....", "h");
		morseMap.put("..", "i");
		morseMap.put(".---", "j");
		morseMap.put("-.-", "k");
		morseMap.put(".-..", "l");
		morseMap.put("--", "m");
		morseMap.put("-.", "n");
		morseMap.put("---", "o");
		morseMap.put(".--.", "p");
		morseMap.put("--.-", "q");
		morseMap.put(".-.", "r");
		morseMap.put("...", "s");
		morseMap.put("-", "t");
		morseMap.put("..-", "u");
		morseMap.put("...-", "v");
		morseMap.put(".--", "w");
		morseMap.put("-..-", "x");
		morseMap.put("-.--", "y");
		morseMap.put("--..", "z");
		morseMap.put("-----", "0");
		morseMap.put(".----", "1");
		morseMap.put("..---", "2");
		morseMap.put("...--", "3");
		morseMap.put("....-", "4");
		morseMap.put(".....", "5");
		morseMap.put("-....", "6");
		morseMap.put("--...", "7");
		morseMap.put("---..", "8");
		morseMap.put("----.", "9");
		morseMap.put(".-.-.-", ".");
		morseMap.put("--..--", ",");
		morseMap.put("..--..", "?");
		
		alphabetMap = new HashMap<String, String>();
		Iterator<Map.Entry<String, String>> it = morseMap.entrySet().iterator();
		
		while(it.hasNext()) {
			Map.Entry<String, String> e = it.next();
			alphabetMap.put(e.getValue(), e.getKey());
		}

	}
	
	public String readMorse(String input) {
		String output = "";
		
		//Break input into words
		String[] words = input.split(" / ");
		//System.out.println(words[0]);
		
		for(int i = 0; i < words.length; i++) {
			String[] letters = words[i].split(" ");
			for(int j = 0; j < letters.length; j++) {
				output += morseMap.get(letters[j]);
			}
			output += " ";
		}
		
		//return words;
		return output;
	}
	
	public String writeMorse(String input) {
		String output = "";
		
		input = input.toLowerCase();
		
		for(int i = 0; i < input.length(); i++) {
			if(input.substring(i, i+1).equals(" ")) output += "/ ";
			else {
				output += alphabetMap.get(input.substring(i, i+1));
				if (i+1 != input.length()) output += " ";
			}
		}
		
		return output;
	}
}