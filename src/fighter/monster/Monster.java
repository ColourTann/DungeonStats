package fighter.monster;

import java.util.ArrayList;

import cards.Card;
import cards.Skill;
import fighter.Fighter;
import fighter.Fighter.Trait;
import fighter.monster.MonsterFactory.MSound;


public class Monster extends Fighter{




	public enum Species{undead, demonic, beast}

	private String plural;
	private String description;
	private Species species;
	private int frameNumber;
	private int level;
	private int health;
	private int randomPool;
	private MSound sound;
	private Skill[] skills;
	public Monster(String name, String plural, Species species, String description, 
			int frameNumber, int level, int health, int randomPool, 
			MSound sound, Trait[] traits, Skill[] skills){
		super(name);
		this.plural=plural;
		this.frameNumber=frameNumber;
		this.level=level;
		this.description=description;
		this.frameNumber=frameNumber;
		this.level=level;
		this.health=health;
		this.randomPool=randomPool;
		this.sound=sound;
		this.traits=traits;
		this.skills=skills;

		setupDeck();
	}



	private void setupDeck() {
		for(Skill s:skills){
			for(Card c:s.getCards()){
				cards.add(c);
			}
		}
	}



	@Override
	public int getHP() {
		return health;
	}

	public String toJson(){

		String output="";
		output+="\""+name+"\" : {\n";
		output+="\"Plural\" : \""+plural+"\",\n";
		if(species!=null)output+="\"Species\" : \""+species+"\",\n";
		output+="\"description\" : \""+description+"\",\n";
		output+="\"frameNumber\" : "+frameNumber+",\n";
		if(level>0) output+="\"dread\" : "+level+",\n";
		output+="\"health\" : "+health+",\n";
		output+="\"RandomPool\" : "+randomPool+",\n";
		if(sound!=null) output+="\"sound\" : \""+sound+"\",\n";
				
		if(skills!=null){
			output+="\"Skills\" : {\n";
			for(int i=0;i<skills.length;i++){
				Skill s = skills[i];
				output+=s.toJson();
				if(skills.length>i+1)output+=",";
				output+="\n";
			}
			output+="},\n";
		}
		if(traits!=null){
			output+="\"Traits\" : [\n";
			for(int i=0;i<traits.length;i++){
				Trait t = traits[i];
				output+=t.toJson();
				if(traits.length>i+1)output+=",";
				output+="\n";
			}
			output+="],\n";
		}
		

		output+="}";
		StringBuilder sb = new StringBuilder(output);
		sb.deleteCharAt(sb.lastIndexOf(","));

		return sb.toString();

	}

}
