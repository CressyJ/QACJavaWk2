public class Bike extends Vehicle{
	int engineSize;
	
//default constructor
	public Bike() {}
	
//parameter constructor
	public Bike(String make, String model, int value, int topSpeed, int age, int engineSize) {
		super(make, model, value, topSpeed, age);
		setEngineSize(engineSize);
	}
	
//set methods
	public void setEngineSize(int engineSize) { this.engineSize = engineSize; }
	
//get methods
	public int getEngineSize() { return engineSize; }
	
//The repair bill of a bike is based on its engine size and age
	@Override
	public int calculateBill() {
		int bill = (int)((float)engineSize * 0.5f);
		
		if(age > 0) bill *= age;		
		
		return bill;
	}
	
//add the engine size to the vehicle toString() method
	@Override
	public String toString() {
		String output = super.toString();
		output += "\nEngine Size:\t" + engineSize + "cc";
		return output;
	}
}