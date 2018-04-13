public class Scorer {
	public int blackjack(int a, int b) {
		//if both are over 21, return 0, both lose
		if(a > 21 && b > 21) return 0;
		
		//if one is over, then the other can't be, so that wins
		if(a > 21) return b;
		if(b > 21) return a;
		
		//If a wins, return it, else return b
		if(a > b) return a;
		return b;
	}
}