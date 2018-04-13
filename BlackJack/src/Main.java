public class Main {
	public static void main(String[] args) {
		Scorer s = new Scorer();
		
		System.out.println(s.blackjack(22, 22));
		System.out.println(s.blackjack(21, 22));
		System.out.println(s.blackjack(22, 21));
		System.out.println(s.blackjack(21, 21));
		System.out.println(s.blackjack(21, 20));
		System.out.println(s.blackjack(19, 20));
	}
}