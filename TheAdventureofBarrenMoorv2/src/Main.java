public class Main {
	public static void main(String[] args) {
		//so the game can be quit at any time
		boolean quit = false;
		
		System.out.println("Welcome to the Barren Moor!");
		System.out.println("Type \"help\" for command list.");
		
		while(!quit) {
			quit = GameLoop.getGameLoop().processInput();
		}
		
		System.out.println("Thank you for playing!");
	}
}