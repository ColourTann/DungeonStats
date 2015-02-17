package fighter.player;

import cards.Card;

public class Wizard extends Hero{
	public Wizard(){
		super("Wizard");
	}
	@Override
	protected void setupBasicDeck() {
		addCard(new Card(1));
		addCard(new Card(1));
		addCard(new Card(2));
		addCard(new Card(1.5f));
		addCard(new Card(1.5f));
		addCard(new Card(1,true));
	}

}
