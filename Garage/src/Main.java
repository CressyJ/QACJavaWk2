public class Main {
	public static void main(String[] args) {
		//create a garage
		Garage g = new Garage();
		
		//make sure it's empty and display as such
		g.emptyGarage();
		//g.listGarage();
		
		//create a car and add it to the garage
		Vehicle v = new Car("Vauxhall", "Corsa", 15000, 120, 1, 3);
		g.addVehicle(v);
		
		//check that it's in there
		//g.listGarage();
		
		//remove all cars
		g.removeVehicleByType("car");
		
		//check that they're gone
		//g.listGarage();
		
		//add our previous car back in
		g.addVehicle(v);
		
		//now make a bike
		g.addVehicle(new Bike("Kawasaki", "Ninja", 25000, 180, 5, 1000));
		//now add a van
		g.addVehicle(new Van("Ford", "Transit", 2000, 100, 10, 2000));
		//and another car
		g.addVehicle(new Car("Toyota", "Supra", 45000, 160, 3, 2));
		
		//list everything
		//g.listGarage();
		
		//fix each car in turn, by the index
		//g.fixVehicle(0);
		//g.fixVehicle(1);
		//g.fixVehicle(2);
		for(int i = 0; i < g.countVehicles(); i++) g.fixVehicle(i);
		
		System.out.println("------------------------\nRemoving cars.");
		g.removeVehicleByType("car");
		g.listGarage();
	}
}