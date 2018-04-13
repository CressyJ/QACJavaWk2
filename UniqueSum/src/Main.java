public class Main {
	public static void main(String[] args) {
		UniqueSum us = new UniqueSum();
		
		System.out.println(us.check(1, 1, 1));
		System.out.println(us.check(1, 1, 2));
		System.out.println(us.check(1, 2, 1));
		System.out.println(us.check(2, 1, 1));
		System.out.println(us.check(1, 2, 3));
	}
}