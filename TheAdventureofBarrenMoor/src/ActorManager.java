import java.util.ArrayList;
import java.util.ListIterator;

//Another Singleton class, this time managing all of the actors
public class ActorManager {
	private static ActorManager am;
	private static Player p1;
	private static ArrayList<Actor> actors;
	
//initialise the array list
	static { actors = new ArrayList<Actor>(); }
	
//Private constructor, as per usual
	private ActorManager() { }
	
//static get method to set the class up
	public static ActorManager getActorManager() {
		if (am == null) am = new ActorManager();
		return am;
	}
	
//return a player, creating one if necessary
	public Player getPlayer() {
		if (p1 == null) {
			p1 = new Player();
			Item compass = new Item("Compass", -1, "A magic compass, which points to your hearts true desire.", true);
			compass.interact(p1);
		}
		
		return p1;
	}
	
//simply add an actor
	public void addActor(Actor a) {
		actors.add(a);
	}

//Actor collection functions
	public boolean collectAll(Actor collector) {
		boolean outcome = false;
		
		ArrayList<Actor> templist = new ArrayList<Actor>();
		
		//literally just loop through all items in a location and collect them all until there's no more to get
		while(getActor(collector.getLocation()) != null) {
			//obviously we can't collect it if it isn't an item in the first place
			if(getActor(collector.getLocation()).getClass() == Item.class) {
				Item temp = (Item)getActor(collector.getLocation());
				//now it's an item, can it be collected?
				if(temp.getIsCollectable()) {
					temp.interact(collector);
					//as long as at least one thing has been collected, this is a success
					outcome = true;
				}
			}
			
			//potentially risky here, but so that this doesn't loop infinitely we'll remove each actor from the search to iterate through
			templist.add(getActor(collector.getLocation()));
			actors.remove(getActor(collector.getLocation()));
		}
		
		//then add them back in again when the search is over
		actors.addAll(templist);
		
		return outcome;
	}
	
	public boolean collectItem(Actor collector, String name) {
		boolean outcome = false;
				
		//Same idea, but we're looking for a specific item name
		while(getActor(name, collector.getLocation()) != null) {
			//obviously we can't collect it if it isn't an item in the first place
			if(getActor(name, collector.getLocation()).getClass() == Item.class) {
				Item temp = (Item)getActor(name, collector.getLocation());
				//now it's an item, can it be collected?
				if(temp.getIsCollectable()) {
					temp.interact(collector);
					//as long as at least one thing has been collected, this is a success
					outcome = true;
				}
			}
		}
		
		return outcome;
	}
	
//functions for getting what actors are in what location
	private Actor getActor(int atLocation) {
		Actor temp;
		ListIterator<Actor> i = actors.listIterator();
		
		while(i.hasNext()) {
			temp = i.next();
			if (temp.getLocation() == atLocation) return temp;
		}
		
		return null;
	}
	
	private Actor getActor(String name, int atLocation) {
		Actor temp;
		ListIterator<Actor> i = actors.listIterator();
		
		while(i.hasNext()) {
			temp = i.next();
			if (temp.getLocation() == atLocation && temp.getName().toLowerCase().equals(name.toLowerCase())) return temp;
		}
		
		return null;
	}
	
	//this one is public so that we can find the treasure location for the compass
	public Actor getActor(String name) {
		Actor temp;
		ListIterator<Actor> i = actors.listIterator();
		
		while(i.hasNext()) {
			temp = i.next();
			if (temp.getName().toLowerCase().equals(name.toLowerCase())) return temp;
		}
		
		return null;
	}
	
//function to list everything in a location
	public String listActors(int locationID) {
		String output = "";
		
		Actor temp;
		ArrayList<Actor> tempList = new ArrayList<Actor>();
		
		//literally just loop through all items in a location and collect them all until there's no more to get
		while(getActor(locationID) != null) {						
			//potentially risky here, but so that this doesn't loop infinitely we'll remove each actor from the search to iterate through
			tempList.add(getActor(locationID));
			actors.remove(getActor(locationID));
		}
		
		ListIterator<Actor> i = tempList.listIterator();
		
		output += "In your immediate area there are the following...\n";
		
		while(i.hasNext()) {
			temp = i.next();
			if(!temp.getName().equals("Player")) output += temp.getName() + "\n";
		}
		
		actors.addAll(tempList);
		
		return output;
	}
	
//function to describe an actor
	public boolean describeActor(String name, int locationID) {
		boolean outcome = false;
		
		Actor temp = getActor(name, locationID);
		if(temp != null) {
			System.out.println(temp.getDescription());
			outcome = true;
		}
		
		return outcome;
	}
}