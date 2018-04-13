import java.util.ArrayList;
import java.util.ListIterator;

//All characters, including the player will share this basic ruleset
abstract public class Character extends Actor {
	//Characters have an inventory and health
	protected ArrayList<Item> inventory;
	protected int maxHealth, health, damage;
	protected boolean isAlive;
	
//default constructor gives 1 health so that a character isn't dead
//both constructors initialise an empty inventory so it can be used
	public Character() {
		isAlive = true;
		maxHealth = 1;
		health = 1;
		inventory = new ArrayList<Item>();
		damage = 1;
	}
	
//parameter constructor
	public Character(String name, int locationID, String description, int maxHealth, int health) {
		super(name, locationID, description);
		this.maxHealth = maxHealth;
		this.health = health;
		damage = 1;
		
		checkHealth();
		inventory = new ArrayList<Item>();
	}
	
//set methods
	public void setMaxHealth(int maxHealth) { this.maxHealth = maxHealth; }
	public void setHealth(int health)		{ this.health = health; }
	public void setDamage(int damage)		{ this.damage = damage; }
	
//get methods
	public int getMaxHealth()	{ return maxHealth; }
	public int getHealth()		{ return health; }
	public int getDamage()		{ return damage; }
	//Overridden to add health information
	@Override
	public String getDescription() {
		String output = super.getDescription();
		
		if(!checkHealth()) output += "\nLooks pretty dead to me.";
		else if (health <= maxHealth / 4) output += "\nSuffering some serious battle damage.";
		else if (health <= maxHealth / 2) output += "\nIs worse for wear, but can keep going.";
				
		return output;
	}
	
//health checking
	public boolean checkHealth() {
		isAlive = health > 0 ? true : false;
		return isAlive;
	}
	
//inventory methods
	//show everything currently held
	public void listInventory() {
		ListIterator<Item> i = inventory.listIterator();
		
		if(!i.hasNext()) {
			System.out.println("Inventory is empty.");
			return;
		}
		
		while(i.hasNext()) {
			Item temp = i.next();
			System.out.println(temp.getName() + "\n" + temp.getDescription());
		}
	}
	
	//there's scope for an inventory capacity with this boolean return
	public boolean addInventory(Item i) {
		inventory.add(i);
		return true;
	}
	
	//just check that any of the specified item name is in the inventory and say yes or no
	public boolean hasInventory(String s) {
		boolean outcome = false;
		
		ListIterator<Item> i = inventory.listIterator();
		
		while(i.hasNext()) {
			Item temp = i.next();
			if (temp.getName().toLowerCase().equals(s.toLowerCase())) {
				outcome = true;
				break;
			}
		}
		
		return outcome;
	}
	
	//return false if the item isn't there to drop
	public boolean dropInventory(String itemToDrop) {
		if (inventory.isEmpty()) return false;
		
		ListIterator<Item> i = inventory.listIterator();
		
		//drop everything if there's no item specified
		if(itemToDrop.isEmpty()) {
			while(i.hasNext()) {
				//The item is now in the game world at the character's location
				i.next().setLocation(locationID);
				//and not in the character's inventory
				i.remove();
			}
			
			return true;
		}
		
		//otherwise iterate through to find the specific item by name
		while(i.hasNext()) {
			Item temp = i.next();
			if (temp.getName().toLowerCase().equals(itemToDrop.toLowerCase())) {
				//The item is now in the game world at the character's location
				temp.setLocation(locationID);
				//and not in the character's inventory
				i.remove();
				return true;
			}
		}
		
		return false;
	}
	
//movement methods for characters
	public boolean move(int direction) {
		boolean outcome = false;
		Location l = Map.getMap().getLocation(locationID);
		
		//check that the direction we want to head in doesn't have the -1 invalid reference
		//then head in that direction
		switch(direction) {
		case 0:
			if(l.getNorth() != -1) {
				locationID = l.getNorth();
				outcome = true;
			}
			break;
		case 1:
			if(l.getEast() != -1) {
				locationID = l.getEast();
				outcome = true;
			}
			break;
		case 2:
			if(l.getSouth() != -1) {
				locationID = l.getSouth();
				outcome = true;
			}
			break;
		case 3:
			if(l.getWest() != -1) {
				locationID = l.getWest();
				outcome = true;
			}
			break;
		}
		
		return outcome;
	}
	
	abstract public boolean interact(Actor interactor);
}