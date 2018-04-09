public class Van extends Vehicle{
	int capacity;
	
//default constructor
	public Van() {}
	
//parameter constructor
	public Van(String make, String model, int value, int topSpeed, int age, int capacity) {
		super(make, model, value, topSpeed, age);
		setCapacity(capacity);
	}
	
//set methods
	public void setCapacity(int capacity) { this.capacity = capacity; }

//get methods
	public int getCapacity() { return capacity; }
	
//The repair bill of a van is based on its value and age
	@Override
	public int calculateBill() {
		int bill = value/4;
		
		if(age > 2) bill += (value/10)*(age-1);
		
		return bill;
	}
	
//add the capacity to the vehicle toString() method
	@Override
	public String toString() {
		String output = super.toString();
		output += "\nCapacity:\t" + capacity + "l";
		return output;
	}
}