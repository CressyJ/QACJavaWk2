import java.util.Scanner;

//Singleton class that handles actual game logic
public class GameLoop {
	private static GameLoop game;
	private Scanner sc;
	//including the three below so that lines in the switch logic aren't stretched incredibly wide
	private ActorManager am;
	private Player p1;
	private Map m;

//initialise the Scanner and world map for in-game use
	private GameLoop() { 
		sc = new Scanner(System.in);
		am = ActorManager.getActorManager();
		p1 = am.getPlayer();
		m = Map.getMap();
		m.generateWorld();
		//no need to populate a variable if there isn't anything further to be done with the actor
		//as Actors add themselves to the Actor Manager automatically
		Item goal = new Item("Treasure", -1, "With this, you can now leave!", true);
		//randomly generate 20 trees
		for (int i = 0; i < 20; i ++) {
			new Item("Tree", (int)(Math.random() * 99), "A gnarly old tree that's somehow taken root in the swamp.",false);
		}
		NPC baddie = new NPC("Swamp Monster", (int)(Math.random()*99), "The dreaded monster of Barren Moor", 1, 1);
		//The Swamp Monster has the goal
		goal.interact(baddie);
	}
	
//Singleton pattern GameLoop access method
	public static GameLoop getGameLoop() {
		if (game == null) game = new GameLoop();
		return game;
	}
	
//Very big function that handles input - this could be broken down so that like inputs feed another function
//A key example being movement
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
		
		//boolean for common use to say when an action hasn't worked and output accordingly.
		boolean result = false;
		//boolean to say whether the NPCs should be processed
		boolean processNPCs = false;
		
		switch(input1.toLowerCase()) {
		
		case "":
			//This gets called occasionally on the teleport command, so this is a handler for empty input that just skips it
			break;
		
		case "a":
		case "attack":
			if(input2.isEmpty()) {
				if(!am.npcInLocation(p1.getLocation())) System.out.println("You practice your attacking, making a splash but achieving nothing.");
				else {
					if(am.attackNPCs()) System.out.println("You've attacked everything nearby!");
					else System.out.println("There's nothing to have been gained with this attack.");
				}
			} else {
				if(input2.equals("player")) System.out.println("Your self-loathing gets the better of you, but you came out on top.");
				else if(am.getActor(input2) != null) {
					//Ensure that there is something with the name we want in the area to attack
					if (am.getActor(input2, p1.getLocation()) != null) {
						//run the attack and, if unsuccessful, because it can't be attacked properly, let the player know
						if(!am.attackNPC(input2))
							System.out.println("You take a swing at " + input2 + " but it doesn't seem to mind.");
					}
					else System.out.println("There's no " + input2 + " to battle here!");
				}
			}
			processNPCs = true;
			break;
			
		case "c":
		case "compass":
			//this needs formatting so that we don't have a very long decimal figure
			if(p1.hasInventory("Compass")) {
				if (!p1.hasInventory("Treasure")) {
					int playerLocation = p1.getLocation();
					int goalLocation;
					//if there are multiple swamp monsters this should work as long as they're instantiated after the one with the treasure
					if (am.getActor("Treasure").getLocation() != -1) goalLocation = am.getActor("Treasure").getLocation();
					else goalLocation = am.getActor("Swamp Monster").getLocation();
					System.out.println("The compass isn't giving you a direction, but it reads: " + String.format("%.2f", Map.getMap().distanceBetweenTwoPoints(playerLocation, goalLocation)));
				}
				else System.out.println("The compass points to the edge of the swamp.");
			}
			else System.out.println("You fumble through your inventory, failing to find a compass...");
			processNPCs = true;
			break;
		
		case "d":
		case "drop":
			if (input2.isEmpty()) {
				result = p1.dropInventory("");
				if (result) System.out.println("You've dropped everything.");
				else System.out.println("There's nothing to drop!");
			}
			else {
				result = p1.dropInventory(input2);
				if (result) System.out.println("You've dropped " + input2 + ".");
				else System.out.println("You've not got a " + input2 + " to drop.");
			}
			break;
		
		case "e":
		case "east":
			result = p1.move(1);
			if (result) System.out.println("You wade east.");
			else{
				if(p1.hasInventory("Treasure")) {
					System.out.println("With the treasure in hand, your path clears and you escape the swamp!");
					output = true;
				}
				else System.out.println("As keen as you are to escape, you're not doing it to the east.");
			}
			processNPCs = true;
			break;
		
		case "g":
		case "get":
			if (input2.isEmpty()) {
				result = am.collectAll(p1);
				if (result) System.out.println("You've gotten everything in the area.");
				else System.out.println("There's nothing to get here");
			}
			else {
				result = am.collectItem(p1, input2);
				if (result) System.out.println("You got the " + input2 + "!");
				else System.out.println("There's no " + input2 + " that you can get here.");
			}
			break;
			
		case "h":
		case "help":
			System.out.println("Command List:");
			System.out.println("[a]ttack\t\t\t\t- Attack everything nearby, or specify a specific character to attack it.");
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
			p1.listInventory();
			break;
			
		case "l":
		case "look":
			if (!input2.isEmpty()) {
				result = am.describeActor(input2, p1.getLocation());
				if (!result) System.out.println("There is no " + input2 + " here!");
				break;
			}
			System.out.println(m.getLocation(p1.getLocation()).getDescription());
			System.out.println(am.listActors(p1.getLocation()));
			break;
			
		case "m":
		case "map":
			m.printMap();
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
				
				result = p1.move(direction);
				if (result) System.out.println("You wade in your chosen direction.");
				else{
					if(p1.hasInventory("Treasure")) {
						System.out.println("With the treasure in hand, your path clears and you escape the swamp!");
						output = true;
					}
					else System.out.println("As keen as you are to escape, you're not doing it that way.");
				}
			}
			processNPCs = true;
			break;
			
		case "n":
		case "north":
			result = p1.move(0);
			if (result) System.out.println("You wade north.");
			else{
				if(p1.hasInventory("Treasure")) {
					System.out.println("With the treasure in hand, your path clears and you escape the swamp!");
					output = true;
				}
				else System.out.println("As keen as you are to escape, you're not doing it to the north.");
			}
			processNPCs = true;
			break;
		
		case "q":
		case "quit":
			output = true;
			sc.close();
			break;
			
		case "s":
		case "south":
			result = p1.move(2);
			if (result) System.out.println("You wade south.");
			else{
				if(p1.hasInventory("Treasure")) {
					System.out.println("With the treasure in hand, your path clears and you escape the swamp!");
					output = true;
				}
				else System.out.println("As keen as you are to escape, you're not doing it to the south.");
			}
			processNPCs = true;
			break;
		
			//This is a cheat/hidden command, so I'm not worried that it advances the game by a process when it shouldn't
		case "t":
		case "teleport":
			int destination;
			if(!input2.isEmpty()) destination = Integer.parseInt(input2);
			else {
				System.out.print("Enter a destination: ");
				destination = sc.nextInt();
			}
			if (destination >= 0 && destination <= 99) {
				p1.setLocation(destination);
				System.out.println("The cheating player is teleported to grid " + destination + ".");
				break;
			}
			else System.out.println("You'll need to input a valid destination to teleport!");
			break;
			
		case "w":
		case "west":
			result = p1.move(3);
			if (result) System.out.println("You wade west.");
			else{
				if(p1.hasInventory("Treasure")) {
					System.out.println("With the treasure in hand, your path clears and you escape the swamp!");
					output = true;
				}
				else System.out.println("As keen as you are to escape, you're not doing it to the west.");
			}
			processNPCs = true;
			break;
			
		default:
			System.out.println("\"" + input + "\" is an unknown command.\nType \"help\" for command list.");
		}
		
		if (processNPCs) {
			am.runNPCs();
			if(!p1.checkHealth()) {
				output = true;
				System.out.println("\nYour journey at an end, this swamp is your tomb...\nTry again, perhaps?\n");
			}
		}
		
		return output;
	}
}
