package fighter;

import java.text.BreakIterator;
import java.util.ArrayList;

import javax.swing.plaf.ActionMapUIResource;

import cards.Card;

public abstract class Fighter {
	private ArrayList<Card> cards = new ArrayList<>();
	protected String name;

	public enum Trait{
		Tenacious, Brittle, Meaty, Respite, Fury, Burn, BonusHP;

		public String toJson() {
			return "\""+this+"\"";
		}
	}

	protected Trait[] traits;
	public Fighter(String name){
		this.name=name;
	}
	public void addCard(Card c){
		cards.add(c);
	}
	public float getStrength(boolean wordy){
		resetBonuses();
		float totalStrength=0;
		float numberOfCards=0;
		for(Card c:cards){
			totalStrength+=c.getStrength();
			numberOfCards+=c.getBlock()?0:1;
		}
		float bonusHP=0;
		float bonusAverageStrength=0;
		float flatBonusStrength=0;
		if(traits!=null){
			
			for(Trait t:traits){
				switch (t){
				
				case Tenacious:
					bonusHP+=.5f;
					break;
				case Brittle:
					bonusHP-=.15f;
					break;
				case Meaty:
					bonusHP-=getHP()/3.5f;
					break;
				case Respite:
					bonusHP+=getHP()/5f;
					break;
				case Fury:
					bonusAverageStrength+=.4f;
					break;
				case Burn:
					flatBonusStrength=1;
					
					
				}
			}
			
		}
		if(wordy){
			startPrint();
			print(name);
			print("Power: "+getStrength(false)+(flatBonusStrength>0?(" ["+flatBonusStrength+"]"):("")));
			print("HP: "+getHP()+(bonusHP>0?("("+bonusHP+")"):""));
			print("Total power: "+truncate(totalStrength));
			print("Avg power: "+truncate((totalStrength/numberOfCards))+(bonusAverageStrength>0?"("+bonusAverageStrength+")":""));
			print("Total cards: "+(int)numberOfCards+"("+(int)cards.size()+")");
			endPrint();
		}
		
		return truncate(((totalStrength/numberOfCards)+bonusAverageStrength)*(getHP()+bonusHP)+flatBonusStrength);
	}

	public float truncate(float f){
		return (int)(f*100)/100f;
	}
	
	public void resetBonuses(){

	}



	private void startPrint() {
		System.out.println(base);	
	}

	private void endPrint() {
		System.out.println(new StringBuilder(base).reverse());	
		System.out.println();
	}

	private static String base = "/--------------------------\\";
	public void print(String s){
		s="| "+s;
		for(int i=s.length();i<base.length()-1;i++){
			s+=" ";
		}
		s+="|";
		System.out.println(s);
	}
	public abstract int getHP();

}
