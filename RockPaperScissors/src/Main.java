import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int playerWins = 0;
		int computerWins = 0;
		int totalGames = 0;
		
		int rock = 0;
		int paper = 0;
		int scissors = 0;
		
		// Take input
		
		Scanner sc = new Scanner(System.in);
		String input = "";
		boolean taken = false;
		System.out.println("Welcome to RPS");
		while (!input.toLowerCase().equals("quit") && totalGames < 10) {

			System.out.print(" Your Move: ");
			input = sc.nextLine();

			switch (input.toLowerCase()) {
			case "r":
				rock++;
				taken = true;
				break;
			case "p":
				paper++;
				taken = true;
				break;
			case "s":
				scissors++;
				taken = true;
				break;
			}

			if (taken) {
				String computerTurn = "";
				
				// Generate random number between 1 and 3
				
				int random = (int) (Math.random() * 3);
				// Random number is assigned to the input
				
				switch (random) {

				case 0:
					computerTurn = "p";
					paper++;
					break;
				case 1:
					computerTurn = "r";
					rock++;
					break;
				case 2:
					computerTurn = "s";
					scissors++;
					break;

				default:
					computerTurn = "s";
				}

				System.out.println("Their move: " + computerTurn);

				if (computerTurn.equals(input.toLowerCase()))
					System.out.println("it's a draw");
				else {
					
					// Decides the winner
					
					switch (input.toLowerCase()) {

					case "r":
						if (computerTurn.equals("p")) computerWins++;
						else playerWins++;
						break;
					case "p":
						if (computerTurn.equals("s")) computerWins++;
						else playerWins++;
						break;
					case "s":
						if (computerTurn.equals("r")) computerWins++;
						else playerWins++;
						break;

					}
					
					System.out.println("You: " + playerWins + " Them: " + computerWins);

				}

				taken = false;
				totalGames ++;
			
				
			}

		}
		sc.close();
		
		System.out.println("Total Games Played: " + totalGames);
		float pWper = (float)(playerWins / (float)totalGames) * 100f;
		System.out.println("Your Wins: " + playerWins + " " + String.format("%.00f", pWper) + "%");
		float cWper = (float)(computerWins / (float)totalGames) * 100f; 
		System.out.println("Their Wins: " + computerWins + " " + String.format("%.00f", cWper ) + "%");
		float dPer = (float)((totalGames - (computerWins + playerWins)) / (float)totalGames) * 100f; 
		System.out.println("Draw: " + (totalGames - (computerWins + playerWins)) + " " + String.format("%.00f", dPer ) + "%");
		
		String mostPopular = "";
		int mostPopularCount = 0;
		if(paper >= rock && paper >= scissors) {
			mostPopular = "Paper";
			mostPopularCount = paper;
		}
		else if(rock >= paper && rock >= scissors) {
			mostPopular = "Rock";
			mostPopularCount = rock;
		}
		else{
			mostPopular = "Scissors";
			mostPopularCount = scissors;
		}
		
		float mostPopPer = (float)(mostPopularCount / (float)(paper + scissors + rock)) * 100f;
		System.out.println(mostPopular + " was chosen most at " + mostPopularCount + " (" 
		+ String.format("%.00f", mostPopPer) + "%)");
	}
}