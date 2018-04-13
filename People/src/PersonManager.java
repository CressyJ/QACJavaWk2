import java.util.ArrayList;

//A singleton class holding an ArrayList of Persons
public class PersonManager {
	//only one of these exists, so this reference variable is static
	private static PersonManager pm;
	//... As is this list of Persons
	private static ArrayList<Person> people;
	
	//static init block to create the one list, although due to the singleton
	//nature of the class, this could be in the constructor
	static{
		people = new ArrayList<Person>();
	}
	
	
	//private constructor so it can't be used to try to create multiple objects
	private PersonManager() { }
	
	//static access function that creates an object if one doesn't exist
	public static PersonManager getPersonManager() {
		if(pm == null) pm = new PersonManager();
		return pm;
	}
	
	//add a person by passing an object reference
	public void addPerson(Person p) {
		people.add(p);
	}
	
	//add a person by filling in the constructor details
	public void addPerson(String name, int age, String jobTitle) {
		people.add(new Person(name, age, jobTitle));
	}
	
	//search the list for somebody with a matching name
	public void getPersonByName(String name) {
		for(Person p : people) {
			if(p.getName() == name) {
				System.out.println(p.toString());
				return;
			}
		}
		
		//if the name doesn't match, let them know
		System.out.println(name + " not found.");
	}
	
	//just output the full Person list
	public void listEverybody() {
		for(Person p : people) System.out.println(p.toString());
	}
}