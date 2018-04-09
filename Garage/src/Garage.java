import java.util.ArrayList;
import java.util.Iterator;

public class Garage {
	ArrayList<Vehicle> vehicles;
	Iterator<Vehicle> vehiclesIterator;
	
	public Garage() {
		vehicles = new ArrayList<Vehicle>();
		vehiclesIterator = vehicles.iterator();
	}

//adding vehicles
	public void addVehicle(Vehicle v) {
		vehicles.add(v);
	}
	
//remove all vehicles
	public void emptyGarage() {
		vehicles.clear();
	}
	
//remove a vehicle
	public void removeVehicle(int index) {
		if(index < vehicles.size()) vehicles.remove(index);
		else System.out.println("No vehicle at this index.");
	}

//return the size of the garage
	public int countVehicles() {
		return vehicles.size();
	}
	
//remove vehicle by class - has to be car, bike or van
	public void removeVehicleByType(String type) {
		vehiclesIterator = vehicles.iterator();
		
		while(vehiclesIterator.hasNext()) {
			if(type.toLowerCase().equals("car") && vehiclesIterator.next().getClass() == Car.class) {
				vehiclesIterator.remove();
			}
			else if(type.toLowerCase().equals("bike") && vehiclesIterator.next().getClass() == Bike.class) {
				vehiclesIterator.remove();
			}
			else if(type.toLowerCase().equals("van") && vehiclesIterator.next().getClass() == Van.class) {
				vehiclesIterator.remove();
			}
		}
	}
	
//use the toString() method of each vehicle to produce a readout
	public void listGarage() {
		if(vehicles.size() == 0) {
			System.out.println("Garage is empty.");
			return;
		}
		Vehicle v;
		//for(v : vehicles) {
		for(int i = 0; i < vehicles.size(); i++) {
			v = vehicles.get(i);
			System.out.print("\nIndex: " + i + "; " + v.toString() + "\n");
		}
	}
	
//like the above, but for a given vehicle
	public void listVehicle(Vehicle v) {
		int i = vehicles.indexOf(v);
		System.out.print("\nIndex: " + i + "; " + v.toString() + "\n");
	}
	
//if there's an index given, get the vehicle then do the call
	private int calculateRepairBill(int index) {
		Vehicle v = vehicles.get(index);
		return v.calculateBill();
	}
	
//Use whichever of the above is more appropriate to the means of input
	public void fixVehicle(int index) {
		listVehicle(vehicles.get(index));
		System.out.println("Repair Bill: £" + calculateRepairBill(index));
	}
	public void fixVehicle(Vehicle v) {
		listVehicle(v);
		System.out.println("Repair Bill: £" + v.calculateBill());
	}
}