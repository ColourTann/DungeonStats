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
	static int aFrame=102;
	static HeroChat[] aChats;
	
	static HeroChat[] defaultChats = new HeroChat[]{
		new HeroChat("Monster", new String[]{
				"Grrr! I hate {MONSTERS}!!", 
				"That {MONSTER} is going to get it",
				"Yeah, I THINK I can take it..", 
				"Oh god not a {MONSTER}", 
				"Well lets get this over with",
				"BANZAAAAAIIIIII", 
				"{MONSTERS} drop loot, right?"
		}),
		new HeroChat("Treasure", new String[]{
				"SHINY", 
				"That looks a bit like a {TREASURE}",
				"Is that.. {TREASURE}!", 
				"THIS is why I'm a dungeoneer!", 
				"Om nyom nyom",
				"Ooh gimme", 
				"{TREASURE}? {TREASURE}!", 
				"Cha-CHING!"
		}),
		new HeroChat("Random", new String[]{
				"On we go..", 
				"A dungeoneer's life is never dull..",
				"I think I'm lost #DUNGEONEERING", 
				"Eyes closed this time.. #YOLO", 
				"The Guild isn't paying me enough for this", 
				"Maybe over here", 
				"I hope this place has a tavern"
		}),
		new HeroChat("Explored", new String[]{
				"This looks vaguely familiar",
				"Hm.. was I already here?"
		}),
		new HeroChat("Unexplored", new String[]{
		}),
		new HeroChat("NoMove", new String[]{
				"I've got nowhere to go..."
		}),
		new HeroChat("Idle", new String[]{
				"Come on! Do SOMETHING!", 
				"So.. bored", 
				"Why can't I move now? Oh yeah you're playing those cards..",
				"...", 
				"zzzzZZZ", 
				"*sigh*", 
				"How about finding me something easy.. like a rubber ducky?", 
				"I wish I was back at my nice warm room in the Guild", 
				"*YAWN*", 
				"Are you AFK?? Don't leave me here!"
		}),
		new HeroChat("Poked", new String[]{
				"Ow - that hurt!", 
				"Please stop clicking me", 
				"What if I were to poke YOU instead!?",
				"Stop that", 
				"The rulebook clearly says that clicking me doesn't achieve anything", 
				"Cut it out!"
		}),
		new HeroChat("CardFit", new String[]{
				"I don't think that can fit anywhere right now..", 
				"And just where would that {CARDNAME} fit?",
				"There's nowhere that can fit that {CARDNAME}", 
				"That won't fit anywhere!", 
				"There's no space for that {CARDNAME}!"
		}),
	};	
	public static void init(){
		
		//TODO Add in frame numbers
		
		heroName=SkillType.Chump;
		aHP=5;
		aChats=defaultChats;
		addClass();
		
		heroName=SkillType.Cat_Burglar;
		aHP=5;
		traits=new Trait[]{Trait.Covetous};
		aChats=defaultChats;
		addClass();
		
		heroName=SkillType.Henchman;
		aHP=5;
		aChats=defaultChats;
		addClass();
		
		heroName=SkillType.Mime;
		aHP=5;
		aChats=defaultChats;
		addClass();
		
		heroName=SkillType.Apprentice;
		aHP=5;
		aChats=defaultChats;
		addClass();
		
		heroName=SkillType.Ranger;
		aHP=6;
		traits=new Trait[]{Trait.Accurate, Trait.Ranged};
		aChats=defaultChats;
		addClass();
		
		heroName=SkillType.Barbarian;
		aHP=6;
		traits = new Trait[]{Trait.Deathwish};
		aChats=defaultChats;
		addClass();
		
		heroName=SkillType.Shapeshifter;
		aHP=6;
		traits=null;
		aChats=defaultChats;
		addClass();
		
		heroName=SkillType.Alchemist;
		aHP=5;
		traits=new Trait[]{Trait.Blessed};
		aChats=defaultChats;
		addClass();
		
		heroName=SkillType.Most_Holy_Knight_Templar;
		aHP=8;
		traits = new Trait[]{Trait.Retribution};
		aChats=defaultChats;
		addClass();
		
		heroName=SkillType.Troubador;
		aHP=7;
		traits = new Trait[]{Trait.SpellSword};
		aChats=defaultChats;
		addClass();
		
		heroName=SkillType.Cartomancer;
		traits = new Trait[]{Trait.Cunning};
		aHP=7;
		aChats=defaultChats;
		addClass();
		
		heroName=SkillType.Mathemagician;
		aHP=7;
		aChats=defaultChats;
		addClass();
		
		heroName=SkillType.Artificer;
		traits = new Trait[]{Trait.VitaSuit};
		aHP=7;
		aChats=defaultChats;
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
