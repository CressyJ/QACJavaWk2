//A class that holds information on each navigable point of the map
public class Location {
	private static int nextid;
	private int id, north, south, east, west;
	private String description;
	
//set the latest ID to 0 so that we definitely increment from there.
	static { nextid = 0; }
	
//set the unique identifier and increment for the next location - default everything else
	public Location() {
		id = nextid++;
		north = south = east = west = -1;
		description = "You are in a featureless grey swamp, with murky grey water up to your shins. Dreary.";
	}
	
//set methods (id doesn't get one, as it's unique). Return false if the specified id hasn't been used yet
	public boolean setNorth(int input) {
		if(input >= nextid) return false;
		north = input;
		return true;
	}
	
	public boolean setSouth(int input) {
		if(input >= nextid) return false;
		south = input;
		return true;
	}
	
	public boolean setEast(int input) {
		if(input >= nextid) return false;
		east = input;
		return true;
	}
	
	public boolean setWest(int input) {
		if(input >= nextid) return false;
		west = input;
		return true;
	}
	
	//no requirement for a check when replacing a string
	public void setDescription(String newDescription) { description = newDescription; }
	
//get methods
	public int getID()		{ return id; }
	public int getNorth()	{ return north; }
	public int getSouth()	{ return south; }
	public int getEast()	{ return east; }
	public int getWest()	{ return west; }
	public String getDescription()	{ return description; }
	//specifically gives the highest current ID, rather than the next available one
	public static int getMaxID() { return nextid - 1; }
	
//method to add extra lines to a description based on game logic
	public void addDescription(String addition) {
		description += "\n" + addition;
	}
}