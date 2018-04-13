public class PaintWizard {
	private static Paint[] options;
	private static PaintWizard pw;
	
//create our paints by default
	static {
		options = new Paint[3];
		options[0] = new Paint("CheapoMax", 19.99f, 20, 10);
		options[1] = new Paint("AverageJoes", 17.99f, 15, 11);
		options[2] = new Paint("DuluxourousPaints", 25f, 10, 20);
	}
	
//Private constructor
	private PaintWizard() { }
	
//static get method to enforce Singleton pattern
	public static PaintWizard getPaintWizard() {
		if(pw == null) pw = new PaintWizard();
		return pw;
	}
	
//method to calculate what the cheapest paint per room, which assumes you have a cuboid room
	public String cheapestPaint(int roomWidth, int roomDepth, int roomHeight) {
		String cheapest = "";
		
		//calculate the area of walls that needs painting
		int coverage = (2*(roomWidth*roomHeight)) + (2*(roomDepth*roomHeight));
		
		//calculate what the cost of each paint option is based on tins required to cover the area
		float cheapoCost = options[0].getCoverageCost(coverage);
		//System.out.println(cheapoCost);
		float averageCost = options[1].getCoverageCost(coverage);
		//System.out.println(averageCost);
		float duluxCost = options[2].getCoverageCost(coverage);
		//System.out.println(duluxCost);
		
		//If either of the first two checked are cheaper than both of the others, name it, otherwise it must be the last
		if(cheapoCost < averageCost && cheapoCost < duluxCost) {
			cheapest = options[0].getName() + " at £" + cheapoCost;
			//now add the wastage figure to the string
			cheapest += " with " + String.format("%.3f", options[0].getWastage(coverage)) + "l left over";
		}
		else if(averageCost < cheapoCost && averageCost < duluxCost) {
			cheapest = options[1].getName() + " at £" + averageCost;
			//now add the wastage figure to the string
			cheapest += " with " + String.format("%.3f", options[1].getWastage(coverage)) + "l left over";
		}
		else{
			cheapest = options[2].getName() + " at £" + duluxCost;
			//now add the wastage figure to the string
			cheapest += " with " + String.format("%.3f", options[2].getWastage(coverage)) + "l left over";
		}
		
		return cheapest;
	}
	
//method to calculate the least remaining paint off of the above logic
	public String leastWastefulPaint(int roomWidth, int roomDepth, int roomHeight) {
		String result = "";
		
		//calculate the area of walls that needs painting
		int coverage = (2*(roomWidth*roomHeight)) + (2*(roomDepth*roomHeight));
		
		//calculate the wastage based on the above area
		float cheapoWaste = options[0].getWastage(coverage);
		float averageWaste = options[1].getWastage(coverage);
		float duluxWaste = options[2].getWastage(coverage);
		
		//then use a simple comparison like above
		if(cheapoWaste < averageWaste && cheapoWaste < duluxWaste) result += options[0].getName() + " with " + String.format("%.3f", cheapoWaste) + "l left over.";
		else if(averageWaste < cheapoWaste && averageWaste < duluxWaste) result += options[1].getName() + " with " + String.format("%.3f", averageWaste) + "l left over.";
		else result += options[2].getName() + " with " + String.format("%.3f", duluxWaste) + "l left over.";
		
		return result;
	}
}