//A class for non-player characters
public class NPC extends Character{	
	//use this to store where the NPC was last, so that we can tell if we're standing still
	private int lastLocation;
	//use this to store whether the last thing the NPC did was attack the player
	private boolean hasAttacked;
	//created so that the NPC can lose interest in chasing you
	private int coolDown;
	
	public NPC(String name, int locationID, String description, int maxHealth, int damage) {
		super(name, locationID, description, maxHealth, maxHealth);
		this.damage = damage;
		ActorManager.getActorManager().addNPC(this);
		lastLocation = locationID;
		hasAttacked = false;
		coolDown = -1;
	}
	
	//The NPC is interacted with by being attacked by the player
	public boolean interact(Actor interactor) {
		boolean result = false;
		
		if (interactor.getClass() != Player.class) return result;
		
		Player temp = (Player)interactor;
		
		//If the NPC is alive, he can be attacked by the player
		if(isAlive) {
			//take damage
			health -= temp.getDamage();
			//Output a message to let you know how your attack went
			System.out.println("You hit " + getName() + " for " + temp.getDamage() + "!");
			//Make sure we're alive
			if(!checkHealth()) {
				//If we're dead, say as much and drop any inventory items, with a corresponding message
				System.out.println(getName() + " falls down dead!");
				if(dropInventory("")) System.out.println(getName() + " dropped something...");
			}
			result = true;
		} else System.out.println("You hit the dead " + getName() + " just for fun. Sick, really.");
		
		return result;
	}
	
	//Procedure to let the NPC act independently of the world
	public void behave() {
		//don't "behave" if you're dead
		if (!isAlive) return;
		
		//if(coolDown > 0) 
		
		//if the player is in the same Location as the actor, attack him
		if(ActorManager.getActorManager().getPlayer().getLocation() == getLocation()) {
			//if the NPC has walked somewhere and the player has walked in on it
			//give the player a breather chance and make the enemy turn first
			if(lastLocation != getLocation() && !hasAttacked) {
				System.out.println(getName() + " turns to face you.");
				hasAttacked = true;
				coolDown = 3;
			}
			else {
				ActorManager.getActorManager().getPlayer().interact(this);
				hasAttacked = true;
				coolDown = 3;
			}
			lastLocation = getLocation();
		}
		//if not, walk in a random direction for one block
		else {
			//update where the NPC was last
			lastLocation = getLocation();
			
			int direction;
			
			//only chase the player for up to three turns
			if(coolDown != 0) {
				//move towards the player if he's in the adjacent Location
				if (Map.getMap().getLocation(ActorManager.getActorManager().getPlayer().getLocation()).getNorth() == getLocation()) direction = 2;
				else if (Map.getMap().getLocation(ActorManager.getActorManager().getPlayer().getLocation()).getSouth() == getLocation()) direction = 0;
				else if (Map.getMap().getLocation(ActorManager.getActorManager().getPlayer().getLocation()).getEast() == getLocation()) direction = 3;
				else if (Map.getMap().getLocation(ActorManager.getActorManager().getPlayer().getLocation()).getWest() == getLocation()) direction = 1;
				//otherwise randomly pick a number between 0 and 3 (inclusive) to feed to the move function
				else direction = (int)(Math.random() * 4);
				coolDown--;
			}
			//if you've evaded the NPC, just randomly wander about
			else {
				direction = (int)(Math.random() * 4);
				coolDown = -1;
			}
			
			move(direction);
			
			//if you've walked, you've not attacked
			hasAttacked = false;
			
			//announce your arrival in the player's Location if you've met him
			if(ActorManager.getActorManager().getPlayer().getLocation() == getLocation()) {
				System.out.println(getName() + " wades into your location.");
				//If the NPC has waded into your location, it's ready to attack
				hasAttacked = true;
			}			
		}
	}
}