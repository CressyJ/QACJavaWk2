public class TempReader {
	private final int LOWER_BOUND = 60;
	private final int UPPER_BOUND = 90;
	
	public boolean idealTemp(int temp, boolean isSummer) {
		//make sure the temperature is higher than the lower bound
		if(temp >= LOWER_BOUND) {
			//adjust the temperature down ten degrees to allow for the higher upper bound of summer
			if(isSummer) temp -= 10;
			//then if we're within bounds, return true
			if(temp <= UPPER_BOUND) return true;
		}
		
		return false;
	}
}