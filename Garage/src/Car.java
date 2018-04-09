public class Car extends Vehicle{
	int doors;
	
//default constructor, for just in case
	public Car() { }
	
	public Car(String make, String model, int value, int topSpeed, int age, int doors) {
		//set all the basics
		super(make, model, value, topSpeed, age);
		setDoors(doors);
	}
	
//set methods
	public void setDoors(int doors) { this.doors = doors; }
	
//get methods
	public int getDoors() { return doors; }
	
//The repair bill of a car is based on its value, top speed and age
	@Override
	public int calculateBill() {
		int bill = value/100;
		
		bill += topSpeed * 2;
		if(age > 1) bill /= age;
		
		return bill;
	}
	
//add the door count to the vehicle toString() method
	@Override
	public String toString() {
		String output = super.toString();
		output += "\nDoors:\t\t" + doors;
		return output;
	}
}