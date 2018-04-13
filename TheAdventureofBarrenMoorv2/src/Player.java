//The player class
public class Player extends Character {	
//parameter-less constructor, as the player is a constant state
	public Player() {
		super("Player", (int)(Math.random()*99), "The hero of this particular tale!", 5, 5);
	}
	
//The player is interacted with by being attacked by NPCs
	public boolean interact(Actor interactor) {
		boolean result = false;
		
		if (interactor.getClass() != NPC.class) return result;
		
		NPC temp = (NPC)interactor;
		
		//If we're alive the NPC can attack us
		if(isAlive) {
			//take the hit and let the player know what's happened
			health -= temp.getDamage();
			System.out.println(temp.getName() + " hits you for " + temp.getDamage() + "!");
			//check to see how that went and, if we're dead, let the player know.
			if(!checkHealth()) System.out.println("You fall down dead.");
			result = true;
		}
		
		return result;
	}
}
