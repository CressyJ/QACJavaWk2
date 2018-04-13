public class Main {
	public static void main(String[] args) {
		tempReaderOutput(59, true);
		tempReaderOutput(60, true);
		tempReaderOutput(100, true);
		tempReaderOutput(100, false);
	}
	
	public static void tempReaderOutput(int temp, boolean isSummer) {
		TempReader tr = new TempReader();
		
		String output;
		
		output = tr.idealTemp(temp, isSummer) ? "Ideal temperature" : "Poor conditions";
		System.out.println(output);
	}
}