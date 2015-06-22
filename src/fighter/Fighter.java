package fighter;

import java.util.ArrayList;
import java.util.List;
import cards.Card;

public abstract class Fighter {
	protected List<Card> cards = new ArrayList<>();
	protected String name;
	public int level;
	int hp;
	public enum Trait{
		Tenacious, Brittle, Meaty, Respite, Fury, Burn, 
		Skilled, Damp, Blessed, Covetous, ArcaneBlade, 
		Ranged, Ferocious, Bulwark, Accurate, Wise, Spikey, 
		Deathwish, Halfbaked, Sleepy, SpellSword, Retribution, 
		Cunning, VitaSuit, Talented, Intuition, 
		MagicalVuln, PhysicalVuln,
		Predictable, Sluggish,
		
		Leader, //+1 hp for surrounding enemy
		
		NightOwl, //-1 hp if surrounded by tiles
		Loner, //-1 hp if in a dead end
		Greedy, //+1 hp if on a tile with treasure
		Aggressive, //Chase
		Thief, //moves to gold
		Wandering //random move
		;

		public String toJson() {
			return "\""+this+"\"";
		}
	}

	protected Trait[] traits;
	public Fighter(String name, int hp, Trait[] traits){
		this.name=name;
		this.hp=hp;
		this.traits=traits;
	}
	public void addCard(Card c){
		cards.add(c);
	}
	public float getStrength(boolean wordy){
		resetBonuses();
		float totalStrength=0;
		float numberOfCards=0;
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
					bonusHP-=getHP()/6f;
					break;
				case Meaty:
					bonusHP-=getHP()/3.5f;
					break;
				case Respite:
					bonusHP+=getHP()/5f;
					break;
				case Fury:
					bonusAverageStrength+=.33f;
					break;
				case Burn:
					flatBonusStrength=1.5f;
					break;
				case Covetous:
					bonusHP=.1f;
					break;
				case Deathwish:
					bonusHP=.3f;
					break;
				case Ranged:
					bonusHP=1;
					break;
				case Blessed:
					bonusHP=.3f;
					break;
				case Skilled:
					
					break;
				case Accurate:
					break;
				case ArcaneBlade:
					break;
				case Bulwark:
					bonusHP+=1.2f;
					break;
				case Cunning:
					bonusHP+=.5f;
					break;
				case Damp:
					bonusHP+=.5f;
					break;
				case Ferocious:
					break;
				case Halfbaked:
					break;
				case Retribution:
					bonusHP+=2.5f;
					break;
				case Sleepy:
					break;
				case SpellSword:
					bonusHP+=1;
					break;
				case Spikey:
					bonusHP+=1.5f;
					break;
				case VitaSuit:
					bonusHP+=1;
					break;
				case Wise:
					break;
				case Intuition:
					break;
				case MagicalVuln:
					bonusHP-=getHP()/5f;
					break;
				case PhysicalVuln:
					bonusHP-=getHP()/5f;
					break;
				case Sluggish:
					bonusHP-=getHP()/8f;
					break;
				case Talented:
					break;
				default:
					break;
				}
			}
			
		}
		
		
		for(Card c:cards){
			totalStrength+=c.strength;
			numberOfCards++;
		}
		
		if(wordy){
			startPrint();
			print(name +" ["+level+"]");
			print("Power: "+getStrength(false)+(flatBonusStrength>0?(" ["+flatBonusStrength+"]"):("")));
			print("HP: "+getHP()+(bonusHP!=0?("("+bonusHP+")"):""));
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

	public int getHP(){
		return hp;
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

}
