import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		PaintWizard pw = PaintWizard.getPaintWizard();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter room dimensions: ");
		System.out.print("Width: ");
		int width = sc.nextInt();
		
		System.out.print("Depth: ");
		int depth = sc.nextInt();
		
		System.out.print("Height: ");
		int height = sc.nextInt();
	
		//outputs wastage figures for both the cheapest and the least wasteful for a given cuboid room size
		System.out.println("Cheapest paint for room of " + width + "x" +
		depth + "x" + height + " is... " + pw.cheapestPaint(width, depth, height));
		System.out.println("Whilst the least wasteful is... " + pw.leastWastefulPaint(width, depth, height));
		
		sc.close();
	}
}