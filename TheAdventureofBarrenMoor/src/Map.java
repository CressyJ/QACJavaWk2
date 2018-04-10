//Singleton class for holding the game world, made up of Locations
public class Map {
	private static Map world;
	private static int[][] grid;
	private static Location[] locations;
	private static boolean worldGenerated;
	
//ensure that the grid exists
	static {
		//hard coded sizes for now, but this could be an input variable on starting a new game
		grid = new int[10][10];
		locations = new Location[100];
		worldGenerated = false;
	}
	
//standard Singleton stuff
	private Map() { }
	
//much like with the GameLoop, a Singleton pattern access method
	public static Map getMap() {
		if (world == null) world = new Map();
		return world;
	}
	
//generate the world. Currently hard-coded, could be randomised at some point
	public void generateWorld() {
		//only do this once
		if (worldGenerated) return;
		
		int i;		
		//generate the hundred new locations
		for(i = 0; i < 100; i++) locations[i] = new Location();
		
		i = 0;
		//assign the location IDs to the grid
		for(int k = 0; k < 10; k++) {
			for(int j = 0; j < 10; j++) {
				grid[k][j] = locations[i].getID();
				i++;
			}
		}
		
		//now fill the cardinal direction integers for the Locations within the grid
		//for an infinite grid, we'd then have special handling to set the zeroes to reference nines and vice versa
		for(int k = 0; k < 10; k++) {
			for(int j = 0; j < 10; j++) {
				//North can only be set below the top grid row
				if (k > 0) locations[grid[k][j]].setNorth(grid[k - 1][j]);
				//South can only be set above the bottom grid row
				if (k < 9) locations[grid[k][j]].setSouth(grid[k + 1][j]);
				//East can only be set left of the rightmost row
				if (j < 9) locations[grid[k][j]].setEast(grid[k][j + 1]);
				//West can only be set right of the leftmost row
				if (j > 0) locations[grid[k][j]].setWest(grid[k][j - 1]);
			}
		}
		
		worldGenerated = true;
	}
	
//for a cheat if nothing else
	public boolean printMap() {
		if (!worldGenerated) {
			System.out.println("No world generated yet!");
			return false;
		}
		
		System.out.println("World map: ");
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				//Print a P instead of the grid location number so the player knows where he is
				if(ActorManager.getActorManager().getPlayer().getLocation() == grid[i][j]) System.out.print(" P ");
				else{
					//extra padding for single digit world locations
					if(grid[i][j] < 10) System.out.print(" ");
					System.out.print(grid[i][j] + " ");
				}
			}
			System.out.println("");
		}
		
		
		return false;
	}
	
//get the Location so that it can be interrogated
	public Location getLocation(int reference) {
		return locations[reference];
	}
	
//get the distance between two points
	public double distanceBetweenTwoPoints(int location1, int location2) {
		double distance = 0.0;
		
		int[] pointA = new int[2];
		int[] pointB = new int[2];
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if (grid[i][j] == location1) {
					pointA[0] = i;
					pointA[1] = j;
				}
				if (grid[i][j] == location2) {
					pointB[0] = i;
					pointB[1] = j;
				}
			}
		}
		
		//swap these around so that the larger number is point B
		if (pointA[0] > pointB[0]) {
			int temp = pointB[0];
			pointB[0] = pointA[0];
			pointA[0] = temp;
		}
		
		if (pointA[1] > pointB[1]) {
			int temp = pointB[1];
			pointB[1] = pointA[1];
			pointA[1] = temp;
		}
		
		//then get the distances on each axis
		double distX = pointB[0] - pointA[0];
		double distY = pointB[1] - pointA[1];
		
		//then calculate that distance and scale it up by 5 to make the swamp seem larger
		if(distX == 0) distance = distY * 5.0;
		else if (distY == 0) distance = distX * 5.0;
		else distance = Math.sqrt(distX * distY) * 10.0;
		
		return distance;
	}
}
