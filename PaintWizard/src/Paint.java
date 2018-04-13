public class Paint {
	private String name;
	private float cost;
	private int volume, coverage;
	
//constructors
	public Paint() { }
	public Paint(String name, float cost, int volume, int coverage) {
		setName(name);
		setCost(cost);
		setVolume(volume);
		setCoverage(coverage);
	}
	
//set methods
	public void setName(String name) { this.name = name; }
	public void setCost(float cost) { this.cost = cost; }
	public void setVolume(int volume) { this.volume = volume; }
	public void setCoverage(int coverage) { this.coverage = coverage; }
	
//get methods
	public String getName() { return name; }
	public float getCost() { return cost; }
	public int getVolume() { return volume; }
	public int getCoverage() { return coverage; }
	
//calculating cost based on coverage
	public float getCoverageCost(int wallArea) {
		float price = 0.0f;
		
		//System.out.println(wallArea);
		//add up the number of tins it costs to cover the area specified
		while(wallArea > 0) {
			price += cost;
			wallArea -= (coverage*volume);
			//System.out.println(wallArea);
		}
		
		return price;
	}
	
//calculating paint left over based on coverage
	public float getWastage(int wallArea) {
		float wastage = 0;
		
		//work out how much extra wall area you could paint
		while(wallArea > 0) wallArea -= (coverage*volume);
		
		//then convert the wall area to litres by dividing it by the coverage and subtract that from the volume to get a rough number of litres
		wastage -= (float)wallArea/coverage;
		
		return wastage;
	}
}