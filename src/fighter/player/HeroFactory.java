package fighter.player;

import java.util.ArrayList;

import json.Json;
import cards.CardFactory;
import cards.Skill;
import cards.Skill.SkillType;
import fighter.Fighter.Trait;

public class HeroFactory {
	
	
	static ArrayList<Hero> heroes = new ArrayList<>();
	static SkillType heroName;
	static int aHP=0;
	static Trait[] traits= null;
	public static void init(){
		heroName=SkillType.Chump;
		aHP=5;
		traits=null;
		addClass();
		
		heroName=SkillType.Warrior;
		aHP=5;
		traits=null;
		addClass();
		
		heroName=SkillType.Rogue;
		aHP=5;
		traits=new Trait[]{Trait.Covetous};
		addClass();
		
		heroName=SkillType.SpellBlade;
		aHP=6;
		traits=new Trait[]{Trait.ArcaneBlade};
		addClass();
		
		heroName=SkillType.Archer;
		aHP=6;
		traits=new Trait[]{Trait.Fast, Trait.Ranged};
		addClass();
		
		heroName=SkillType.Magician;
		aHP=5;
		traits=null;
		addClass();
		
		heroName=SkillType.Cleric;
		aHP=4;
		traits=new Trait[]{Trait.Blessed};
		addClass();
		
		heroName=SkillType.Druid;
		aHP=6;
		traits=null;
		addClass();
		
		heroName=SkillType.Wizard;
		aHP=6;
		traits=null;
		addClass();
		
		
		
	}
	
	public static void addClass(){
		heroes.add(new Hero(heroName.toString(), aHP, traits, Skill.get(heroName).getCards(false)));
		heroName=null;
		aHP=0;
		traits=null;
	}
	
	public static String toJson(){
		String output="";
		output+=Json.enclose();
		output+=Json.startList("Classes");
		for(int i=0;i<heroes.size();i++){
			output+=heroes.get(i).toJson();
			if(i==heroes.size()-1){
				output=Json.removeComma(output);
			}
		}
		
		output+=Json.endList();
		output=Json.removeComma(output);
		output+=Json.endEnclose();
		output=Json.removeComma(output);

		
		
		return output;
	}

	public static String analyseClasses() {
		String s="";
		for(Hero h:heroes){
			s+=h.getStrength(true);
		}
		System.out.println(s);
		return s;
	}
}
