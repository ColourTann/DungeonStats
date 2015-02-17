package cards;

public class Card {
	private float strength;
	private boolean block;
	public Card(float strength){
		this.strength=strength;
	}
	public Card(float strength, boolean block){
		this.strength=strength;
		this.block=block;
	}
	public float getStrength(){
		return strength;
	}
	public boolean getBlock(){
		return block;
	}
}
