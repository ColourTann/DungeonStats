package fighter.player;

import java.io.Serializable;
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
	static int aFrame=102;
	static HeroChat[] aChats;




	public static void init(){

		//TODO Add in frame numbers

		heroName=SkillType.Chump;
		aHP=5;
		aFrame=14;
		aChats=HeroChat.chumpChats;
		addClass();

		aFrame=103;
		heroName=SkillType.Cat_Burglar;
		aHP=5;
		traits=new Trait[]{Trait.Covetous};
		aChats=HeroChat.catChats;
		addClass();

		heroName=SkillType.Bruiser;
		aHP=5;
		traits = new Trait[]{Trait.Spikey};
		aChats=HeroChat.bruiserChats;
		addClass();

		heroName=SkillType.Mime;
		aHP=5;
		aChats=HeroChat.mimeChats;
		addClass();

		heroName=SkillType.Apprentice;
		aHP=5;
		aChats=HeroChat.apprenticeChats;
		traits=new Trait[]{Trait.Talented};
		addClass();

		heroName=SkillType.Ranger;
		aHP=6;
		traits=new Trait[]{Trait.Ranged};
		aChats=HeroChat.chumpChats;
		addClass();

		heroName=SkillType.Barbarian;
		aHP=6;
		traits = new Trait[]{Trait.Deathwish};
		aChats=HeroChat.barbariChats;
		addClass();

		heroName=SkillType.Shapeshifter;
		aHP=6;
		traits=null;
		aChats=HeroChat.chumpChats;
		traits=new Trait[]{Trait.Wild};
		addClass();

		heroName=SkillType.Alchemist;
		aHP=5;
		traits=new Trait[]{Trait.Blessed};
		aChats=HeroChat.chumpChats;
		addClass();

		heroName=SkillType.Most_Holy_Grail_Knight;
		aHP=9;
		traits = new Trait[]{Trait.Retribution};
		aChats=HeroChat.chumpChats;
		addClass();

		heroName=SkillType.Troubador;
		aHP=8;
		traits = new Trait[]{Trait.SpellSword};
		aChats=HeroChat.chumpChats;
		addClass();

		heroName=SkillType.Cartomancer;
		traits = new Trait[]{Trait.Cunning};
		aHP=8;
		aChats=HeroChat.chumpChats;
		addClass();

		heroName=SkillType.Mathemagician;
		aHP=8;
		traits = new Trait[]{Trait.Bisect};
		aChats=HeroChat.chumpChats;
		addClass();

		heroName=SkillType.Artificer;
		traits = new Trait[]{Trait.Inventive};
		aHP=9;
		aChats=HeroChat.chumpChats;
		addClass();
		//		heroName=SkillType.Shapeshifter;
		//		aHP=6;
		//		traits=null;
		//		addClass();



	}

	public static void addClass(){
		heroes.add(new Hero(heroName.toString(), aHP, traits, Skill.get(heroName).getCards(false), aFrame, aChats));
		aFrame++;
		heroName=null;
		aHP=0;
		traits=null;
		aChats=null;
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
