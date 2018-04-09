abstract public class Vehicle {
	protected int value, topSpeed, age;
	protected String make, model;
	
//a default constructor, for just in case
	public Vehicle() { }
	
	public Vehicle(String make, String model, int value, int topSpeed, int age) {
		setMake(make);
		setModel(model);
		setValue(value);
		setTopSpeed(topSpeed);
		setAge(age);
	}
	
//set methods
	public void setMake(String make) { this.make = make; }
	public void setModel(String model) { this.model = model; }
	public void setValue(int value) { this.value = value; }
	public void setTopSpeed(int topSpeed) { this.topSpeed = topSpeed; }
	public void setAge(int age) { this.age = age; }
	
//get methods
	public String getMake() { return make; }
	public String getModel() { return model; }
	public int getValue() { return value; }
	public int getTopSpeed() { return topSpeed; }
	public int getAge() { return age; }
	
//abstract method for the child classes to fill in
	abstract public int calculateBill(); 
	
//A nicely formatted output for the vehicles
	@Override
	public String toString() {
		String output;
		output = make + " " + model + ":\nValue:\t\t£" + value
				+ "\nTop Speed:\t" + topSpeed
				+ "mph\nAge:\t\t" + age + " years";
		return output;
	}
}