package fighter;

import java.util.ArrayList;

import javax.swing.plaf.ActionMapUIResource;

import cards.Card;

public abstract class Fighter {
	private ArrayList<Card> cards = new ArrayList<>();
	String name;
	public Fighter(String name){
		this.name=name;
	}
	public void addCard(Card c){
		cards.add(c);
	}
	public float getStrength(boolean wordy){
		float totalStrength=0;
		float numberOfCards=0;
		for(Card c:cards){
			totalStrength+=c.getStrength();
			numberOfCards+=c.getBlock()?0:1;
		}
		if(wordy){
			
			
			startPrint();
			print("Stats for "+name);
			print("Power: "+getStrength(false));
			print("HP: "+getHP());
			print("Total power: "+totalStrength);
			print("Avg power: "+(totalStrength/numberOfCards));
			print("Total cards: "+(int)numberOfCards+"("+(int)cards.size()+")");
			endPrint();
			
		}
		
		return (totalStrength/numberOfCards)*getHP();
	}
	
	private void startPrint() {
		System.out.println(base);	
	}
	
	private void endPrint() {
		System.out.println(new StringBuilder(base).reverse());	
		System.out.println();
	}
	
	private static String base = "/-----------------------\\";
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
