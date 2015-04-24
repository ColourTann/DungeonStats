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
		
		//TODO Add in frame numbers
		
		heroName=SkillType.Chump;
		aHP=5;
		traits=null;
		addClass();
		
		heroName=SkillType.Henchman;
		aHP=5;
		traits=null;
		addClass();
		
		heroName=SkillType.CatBurglar;
		aHP=5;
		traits=new Trait[]{Trait.Covetous};
		addClass();
		
		heroName=SkillType.Mime;
		aHP=5;
		addClass();
		
		heroName=SkillType.Apprentice;
		aHP=5;
		traits=null;
		addClass();
		
		heroName=SkillType.Alchemist;
		aHP=5;
		traits=new Trait[]{Trait.Blessed};
		addClass();
		
		heroName=SkillType.Shapeshifter;
		aHP=6;
		traits=null;
		addClass();
		
		heroName=SkillType.Barbarian;
		aHP=6;
		traits = new Trait[]{Trait.Deathwish};
		addClass();
		
		heroName=SkillType.Ranger;
		aHP=6;
		traits=new Trait[]{Trait.Accurate, Trait.Ranged};
		addClass();
		
		heroName=SkillType.Troubador;
		aHP=1;
		addClass();
		
		heroName=SkillType.Templar;
		aHP=1;
		addClass();
		
		heroName=SkillType.Cartomancer;
		aHP=1;
		addClass();
		
		heroName=SkillType.Mathemagician;
		aHP=1;
		addClass();
		
		heroName=SkillType.Artificer;
		aHP=1;
		addClass();
//		heroName=SkillType.Shapeshifter;
//		aHP=6;
//		traits=null;
//		addClass();
		
		
		
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
		
		output+=Json.endList(true);
		output=Json.removeComma(output);
		output+=Json.endEnclose(true);
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
