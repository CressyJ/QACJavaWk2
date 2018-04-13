//A simple person class
public class Person {
	private String name, occupation;
	private int age;
	
//Default constructor in case I like the long method of creating a Person
	public Person() { }
	
//Parameter constructor for when I'm feeling efficient
	public Person(String name, String occupation, int age) {
		setName(name);
		setOccupation(occupation);
		setAge(age);
	}
	
//set methods
	public void setName(String name)				{ this.name = name; }
	public void setOccupation(String occupation)	{ this.occupation = occupation; }
	public void setAge(int age)						{ this.age = age; }	
	
//get methods
	public String getName()			{ return name; }
	public String getOccupation()	{ return occupation; }
	public int getAge()				{ return age; }
	
	//Producing a formatted string for the console
	@Override
	public String toString() {
		return name + ": " + occupation + "\nAge: " + age + " years";
	}
	
	//produce a comma-separated line
	public String toCSV() {
		return name + "," + occupation + "," + age;
	}
}