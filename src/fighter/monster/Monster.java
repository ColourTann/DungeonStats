package fighter.monster;

import json.Json;
import cards.Card;
import cards.Skill;
import fighter.Fighter;
import fighter.monster.MonsterFactory.MSound;
import fighter.monster.MonsterFactory.Region;


public class Monster extends Fighter{




	public enum Species{undead, demonic, beast}

	private String plural;
	private String description;
	private Species species;
	public int frameNumber;
	public int level;
	public int randomPool;
	private MSound sound;
	private Skill[] skills;
	public String name;
	public Region region;
	BoardChat[] boardChat;
	public Monster(String name, String plural, Region region, Species species, String description, 
			int frameNumber, int level, int health, int randomPool, 
			MSound sound, Trait[] traits, Skill[] skills,
			BoardChat[] boardChat){
		super(name, health, traits);
		this.name=name;
		this.plural=plural;
		this.frameNumber=frameNumber;
		this.level=level;
		this.description=description;
		this.frameNumber=frameNumber;
		this.level=level;
		this.randomPool=randomPool;
		this.sound=sound;
		this.skills=skills;
		this.region=region;
		this.boardChat=boardChat;
		setupDeck();
	}

	private void setupDeck() {
		for(Skill s:skills){
			for(Card c:s.getCards(traits!=null&&traits.length>1&&traits[0]==Trait.Skilled)){
				cards.add(c);
			}
		}
	}

	public String toJson(){

		String output="";
		output+="\""+name+"\" : {\n";
		output+="\"Plural\" : \""+plural+"\",\n";
		if(species!=null)output+="\"Species\" : \""+species+"\",\n";
		if(region!=null)output+="\"Region\" : [\""+region+"\"],\n";
		output+="\"description\" : \""+description+"\",\n";
		output+="\"frameNumber\" : "+frameNumber+",\n";
		if(level>-1) output+="\"dread\" : "+level+",\n";
		output+="\"health\" : "+getHP()+",\n";
		output+="\"RandomPool\" : "+randomPool+",\n";
		if(sound!=null) output+="\"sound\" : \""+sound+"\",\n";
		if(boardChat!=null){
			output+=Json.startList("BoardChat");
			for(int i=0;i<boardChat.length;i++){
				output+=boardChat[i].toJson();
				if(i<boardChat.length-1) output=Json.addComma(output);
			}
			output+=Json.endList(true);
		}
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
