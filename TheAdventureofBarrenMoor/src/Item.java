//A class for items in the game
public class Item extends Actor{
	protected boolean canBeCollected;
	
//By default items can't be collected
	public Item() {
		canBeCollected = false;
	}
	
//A parameter constructor
	public Item(String name, int locationID, String description, boolean isCollectable) {
		super(name, locationID, description);
		canBeCollected = isCollectable;
	}
	
//set methods
	public void setCollectable(boolean isCollectable) { canBeCollected = isCollectable; }
	
//get methods
	public boolean getIsCollectable() { return canBeCollected; }
	
//interactions for items just extend to whether or not it can be collected
	public boolean interact(Actor interactor) {
		if(interactor.getClass() != Player.class) return false;
		if(!canBeCollected) return false;
		
		Character c = (Character)interactor;
		//when collected, the location is -1
		setLocation(-1);
		c.addInventory(this);
		
		return true;
	}
}