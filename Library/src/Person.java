import java.util.ArrayList;
import java.util.ListIterator;

//a class that owns a list of items
public class Person {
	private String name;
	private ArrayList<Item> itemsBorrowed;
	
//Constructors
	//This constructor isn't public so that a person can't be created without a name
	private Person(){
		itemsBorrowed = new ArrayList<Item>();
	}
	
	//This parameter constructor calls the above
	public Person(String name) {
		this();
		setName(name);
	}
	
//get methods
	public String getName() { return name; }
	
//set methods
	public void setName(String name) { this.name = name; }
	
//borrowing methods
	//We'll make sure that the item actually exists in the Library class
	public void borrowItem(Item i) { itemsBorrowed.add(i); }
	
	//Here we need to make sure we have the item and, if we do, remove it from our list and hand it over
	public Item returnItem(int itemID) {
		Item toHandIn = null;
		
		ListIterator<Item> i = itemsBorrowed.listIterator();
		
		while(i.hasNext()) {
			Item temp = i.next();
			
			if(temp.getID() == itemID) {
				toHandIn = temp;
				i.remove();
				break;
			}
		}
		
		return toHandIn;
	}
	
	//return all items
	public ArrayList<Item> returnAll(){
		@SuppressWarnings("unchecked")
		ArrayList<Item> output = (ArrayList<Item>)itemsBorrowed.clone();
		
		itemsBorrowed.clear();
		
		return output;
	}
}
