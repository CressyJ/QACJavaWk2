import java.util.ArrayList;
import java.util.ListIterator;

public class Main {
	public static void main(String[] args) {
		System.out.println(getNumPrimes(3000000));
	}
	
	public static int getNumPrimes(int input) {		
		//We know that 0 and 1 don't count, whilst 2 and 3 are prime, so return based on this simple knowledge
		if(input == 0) return input;
		if(input <= 3 && input >= 1) return input-1;
		//Likewise, start off having counted 2, 3 and 5 before we get going
		int output = 3;
		if(input < 7) return output;
		
		ArrayList<Integer> primes = new ArrayList<Integer>(); 
		ListIterator<Integer> it;
		//not including 2 or 5 as these numbers are being ruled out through other means 
		//primes.add(2);
		primes.add(3);
		//primes.add(5);
		
		//We start from 7 to avoid the % 5 issue I'd otherwise have; we're also skipping even numbers for obvious reasons
		for(int i = 7; i <= input; i+=2) {
			//rule out multiples of 5
			if (i % 5 == 0) continue;
			
			it = primes.listIterator();

			//with what's left check it against all previously added prime numbers
			while(it.hasNext()) {
				int check = it.next();
				if(i % check == 0) break;
				if(!it.hasNext()) {
					primes.add(i);
					output++;
					break;
				}
			}
		}
		
		return output;
	}
	
//Attempt that is a bit too messy and inefficient
	/*
	public static void main(String[] args) {
		int input = 3000000;
		
		for(int i = 0; i < input; i++) {
			//String output = isPrime(i) ? i + " is prime\n" : "";
			int output = 0;
			output += isPrime(i);
		
			System.out.print(output);
		}
	}
	
	public static int isPrime(int input) {
		int outcome = 1;
		
		//I know for a fact that 1, 2 and 3 are prime numbers, so don't check those
		if(input > 3) {
			//Even numbers over two obviously aren't prime, so discount those
			if(input % 2 != 0) {
				//check only odd numbers, as we've discounted evens already
				//check the input only up to just under a third, as we're already checking divide by 3
				for (int i = 3; i < ((input / 3) - 1); i+=2) {
					if (input % i == 0) {
						outcome = 0;
						break;
					}
				}
			} else outcome = 0;
		}
		
		return outcome;
	}*/
}