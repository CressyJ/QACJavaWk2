//The player class
public class Player extends Character {	
//parameter-less constructor, as the player is a constant state
	public Player() {
		super("Player", 32, "The hero of this particular tale!", 100, 100);
	}
	
	public boolean interact(Actor interactor) {
		return true;
	}
}
