//abstract class that all in-game actors and items inherit from
abstract public class Actor {
	protected int locationID;
	protected String name, description;
	
//All parameter-less constructors will want to do this
	public Actor(){
		locationID = -1;
		setName("");
		setDescription("");
		//add the actor to the actor manager
		ActorManager.getActorManager().addActor(this);
	}
	
//to save on code, a parameter constructor
	public Actor(String name, int locationID, String description) {
		this.name = name;
		this.locationID = locationID;
		this.description = description;
		//add the actor to the actor manager
		ActorManager.getActorManager().addActor(this);
	}
	
//to be dealt with by the classes
	abstract public boolean interact(Actor interactor);
	
//set methods
	public boolean setLocation(int newLocation) {
		//we won't set the location to be higher than the current highest ID
		if(newLocation > Location.getMaxID()) return false;
		
		locationID = newLocation;
		
		return true;
	}
	
	public void setName(String newName)					{ name = newName; }
	public void setDescription(String newDescription)	{ description = newDescription; }
	
//get methods
	public int getLocation() 		{ return locationID; }
	public String getName()			{ return name; }
	public String getDescription() 	{ return description; }
}