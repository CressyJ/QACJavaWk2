import java.util.Scanner;

//Singleton class that handles actual game logic
public class GameLoop {
	private static GameLoop game;
	private Scanner sc;

//initialise the Scanner and world map for in-game use
	private GameLoop() { 
		sc = new Scanner(System.in);
		Map.getMap().generateWorld();
		//no need to populate a variable as Actors add themselves to the Actor Manager automatically
		new Item("Treasure", 56, "With this, you can now leave!", true);
	}
	
//Singleton pattern GameLoop access method
	public static GameLoop getGameLoop() {
		if (game == null) game = new GameLoop();
		return game;
	}
	
	public boolean processInput() {
		boolean output = false;
		
		System.out.print("\nYour command? ");
		
		String input = sc.nextLine();
		
		//commands can be two words
		String input1 = input;
		String input2 = "";
		//loop through to find the first and second words
		for(int i = 0; i < input.length(); i++) {
			if (input.substring(i, i+1).equals(" ")) {
				input1 = input.substring(0, i);
				input2 = input.substring(i+1);
				break;
			}
		}
		
		boolean result = false;
		
		switch(input1.toLowerCase()) {
		
		case "c":
		case "compass":
			//this needs formatting so that we don't have a very long decimal figure
			if(ActorManager.getActorManager().getPlayer().hasInventory("Compass")) {
				if (!ActorManager.getActorManager().getPlayer().hasInventory("Treasure")) {
					int playerLocation = ActorManager.getActorManager().getPlayer().getLocation();
					int goalLocation = ActorManager.getActorManager().getActor("Treasure").getLocation();
					System.out.println("The compass isn't giving you a direction, but it reads: " + Map.getMap().distanceBetweenTwoPoints(playerLocation, goalLocation));
				}
				else System.out.println("The compass points to the edge of the swamp.");
			}
			else System.out.println("You'll need a compass for that...");
			break;
		
		case "d":
		case "drop":
			if (input2.isEmpty()) {
				result = ActorManager.getActorManager().getPlayer().dropInventory("");
				if (result) System.out.println("You've dropped everything.");
				else System.out.println("There's nothing to drop!");
			}
			else {
				result = ActorManager.getActorManager().getPlayer().dropInventory(input2);
				if (result) System.out.println("You've dropped " + input2 + ".");
				else System.out.println("You've not got a " + input2 + " to drop.");
			}
			break;
		
		case "e":
		case "east":
			result = ActorManager.getActorManager().getPlayer().move(1);
			if (result) System.out.println("You wade east.");
			else{
				if(ActorManager.getActorManager().getPlayer().hasInventory("Treasure")) {
					System.out.println("With the treasure in hand, your path clears and you escape the swamp!");
					output = true;
				}
				else System.out.println("As keen as you are to escape, you're not doing it to the east.");
			}
			break;
		
		case "g":
		case "get":
			if (input2.isEmpty()) {
				result = ActorManager.getActorManager().collectAll(ActorManager.getActorManager().getPlayer());
				if (result) System.out.println("You've gotten everything in the area.");
				else System.out.println("There's nothing to get here");
			}
			else {
				result = ActorManager.getActorManager().collectItem(ActorManager.getActorManager().getPlayer(), input2);
				if (result) System.out.println("You got the " + input2 + "!");
				else System.out.println("There's no " + input2 + " to get here");
			}
			break;
			
		case "h":
		case "help":
			System.out.println("Command List:");
			System.out.println("[c]ompass\t\t\t\t- If you've still got the compass, use it to tell you how near your goal is.");
			System.out.println("[d]rop\t\t\t\t\t- Drop everything in your inventory, or specify an item to drop just it.");
			System.out.println("[g]et\t\t\t\t\t- Collect every item in the area, or specify an item to get just it.");
			System.out.println("[h]elp\t\t\t\t\t- bring up this list.");
			System.out.println("[i]nventory\t\t\t\t- list all inventory items carried.");
			System.out.println("[l]ook\t\t\t\t\t- examine the area you're in, or specify a character or item to check it out.");
			System.out.println("move [n]orth/[s]outh/[e]ast/[w]est\t- move in the specified direction.");
			System.out.println("[q]uit\t\t\t\t\t- quit the game.");
			break;
			
		case "i":
		case "inventory":
			ActorManager.getActorManager().getPlayer().listInventory();
			break;
			
		case "l":
		case "look":
			if (!input2.isEmpty()) {
				result = ActorManager.getActorManager().describeActor(input2, ActorManager.getActorManager().getPlayer().getLocation());
				if (!result) System.out.println("There is no " + input2 + " here!");
				break;
			}
			System.out.println(Map.getMap().getLocation(ActorManager.getActorManager().getPlayer().getLocation()).getDescription());
			System.out.println(ActorManager.getActorManager().listActors(ActorManager.getActorManager().getPlayer().getLocation()));
			break;
			
		case "m":
		case "map":
			Map.getMap().printMap();
			break;
			
		case "move":
			if (input2.isEmpty()) System.out.println("Move in which direction?");
			else {
				int direction = -1;
				switch(input2.toLowerCase()) {
				case "n":
				case "north":
					direction = 0;
					break;
				case "s":
				case "south":
					direction = 2;
					break;
				case "e":
				case "east":
					direction = 1;
					break;
				case "w":
				case "west":
					direction = 3;
					break;
				}
				
				result = ActorManager.getActorManager().getPlayer().move(direction);
				if (result) System.out.println("You wade in your chosen direction.");
				else{
					if(ActorManager.getActorManager().getPlayer().hasInventory("Treasure")) {
						System.out.println("With the treasure in hand, your path clears and you escape the swamp!");
						output = true;
					}
					else System.out.println("As keen as you are to escape, you're not doing it that way.");
				}
			}
			break;
			
		case "n":
		case "north":
			result = ActorManager.getActorManager().getPlayer().move(0);
			if (result) System.out.println("You wade north.");
			else{
				if(ActorManager.getActorManager().getPlayer().hasInventory("Treasure")) {
					System.out.println("With the treasure in hand, your path clears and you escape the swamp!");
					output = true;
				}
				else System.out.println("As keen as you are to escape, you're not doing it to the north.");
			}
			break;
		
		case "q":
		case "quit":
			output = true;
			sc.close();
			break;
			
		case "s":
		case "south":
			result = ActorManager.getActorManager().getPlayer().move(2);
			if (result) System.out.println("You wade south.");
			else{
				if(ActorManager.getActorManager().getPlayer().hasInventory("Treasure")) {
					System.out.println("With the treasure in hand, your path clears and you escape the swamp!");
					output = true;
				}
				else System.out.println("As keen as you are to escape, you're not doing it to the south.");
			}
			break;
			
		case "w":
		case "west":
			result = ActorManager.getActorManager().getPlayer().move(3);
			if (result) System.out.println("You wade west.");
			else{
				if(ActorManager.getActorManager().getPlayer().hasInventory("Treasure")) {
					System.out.println("With the treasure in hand, your path clears and you escape the swamp!");
					output = true;
				}
				else System.out.println("As keen as you are to escape, you're not doing it to the west.");
			}
			break;
			
		default:
			System.out.println("\"" + input + "\" is an unknown command.\nType \"help\" for command list.");
		}
		
		return output;
	}
}
