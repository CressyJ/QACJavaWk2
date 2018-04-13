public class Main {
	public static void main(String[] args) {
		String input1 = "Tom and Max";
		String input2 = "Tom and Max";
		
		System.out.println("Finding the longest common sequence between:");
		System.out.println(input1);
		System.out.println(input2);
		
		System.out.print("It is: \"");
		
		//System.out.println(longestCommonSequence("one", "two"));
		System.out.print(longestCommonSequence(input1, input2) + "\"\n");
	}
	
	public static String longestCommonSequence(String input1, String input2) {
		String output = "";
		String temp = "";
		
		for (int endMarker = 1; endMarker <= input2.length(); endMarker++) {
			for (int startMarker = 0; startMarker < endMarker; startMarker++) {
				temp = input2.substring(startMarker, endMarker);
				//System.out.println(temp);
				if(output.length() >= temp.length()) continue;
				
				if(input1.contains(temp)){
					if(temp.length() > output.length()) output = temp;
					//System.out.println(output);
				}
			}
			/*if (output.length() >= input2.substring(endMarker).length()) {
				//System.out.println(input2.substring(endMarker));
				break;
			}*/
		}
		
		return output;
	}
}