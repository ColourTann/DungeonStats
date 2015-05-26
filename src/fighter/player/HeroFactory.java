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
	
	static HeroChat[] chumpChats = new HeroChat[]{
		new HeroChat("Monster", new String[]{
				"Who fills a dungeon with {MONSTERS}!?", 
				"This way looks sa- WHOAH!",
				"Yeah, I THINK I can take it..", 
				"Oh god not a {MONSTER}", 
				"Well lets get this over with",
				"Two years in Chump school for this?", 
				"{MONSTERS} drop loot, right?"
		}),
		new HeroChat("Treasure", new String[]{
				"SHINY", 
				"That looks a bit like a {TREASURE}",
				"Is that.. {TREASURE}!", 
				"THIS is why I'm a dungeoneer!", 
				"Oooohh Yeah!",
				"Ooh gimme", 
				"{TREASURE}? {TREASURE}!", 
				"Cha-CHING!"
		}),
		new HeroChat("Random", new String[]{
				"On we go..", 
				"A dungeoneer's life is never dull..",
				"I think I'm lost", 
				"Eyes closed this time", 
				"The Guild isn't paying me enough for this", 
				"Maybe over here", 
				"I hope this place has a tavern",
				"This way looks safe",
				"You could make this more fun you know",
				"Why didn't I bring my 10 foot pole?"
		}),
		new HeroChat("Explored", new String[]{
				"This looks vaguely familiar",
				"Hm.. was I already here?",
				"I feel like I'm going in circles"
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
	
	static HeroChat[] catChats = new HeroChat[]{
		new HeroChat("Monster", new String[]{
				"{MONSTER}? Purr-fect", 
				"Oh no, more claw-ful {MONSTERS}",
				"This is going to be a cat-astrophe",
				"Another a-paw-ling {MONSTER}", 
				"Are you kitten me?",
				"You've got a bad cat-titude, {MONSTER}!", 
				"Fur-ward to victory!",
				"I've got a bad feline about this"
		}),
		new HeroChat("Treasure", new String[]{
				"That looks purr-fect!", 
				"{TREASURE}? Paw-some!",
				"I hope {TREASURE} is paw-table", 
				"Fur-ward to shiny loot!", 
				"{TREASURE}? Pre-paws-terous!", 
				"More loot paw-lease!",
				"My purr-ecious!"
		}),
		new HeroChat("Random", new String[]{
				"MEOW!", 
				"I think I've lost my ap-purr-tite",
				"Is this really the best ap-purr-oach?",
				"Let me tail you what to play next turn",
				"I can't paw-sibly take any more", 
				"I feel fur-tunate to be in THIS guild",
				"Lets stay paws-itive...",
				"Meow?"
		}),
		new HeroChat("Explored", new String[]{
				"Have I been here? I fur-get",
				"Paw-don me, was I here already?",
				"This looks a bit fur-miliar"
		}),
		new HeroChat("Unexplored", new String[]{
		}),
		new HeroChat("NoMove", new String[]{
				"I've got meow-where to go..."
		}),
		new HeroChat("Idle", new String[]{
				"...", 
				"zzzzZZZ", 
				"*licks hands*", 
				"Um. Meow?", 
				"Did you paws the game?", 
				"*YAWN*", 
				"Oh purr-fect. They've left me here.",
				"Cat puns actually freak meowt"
		}),
		new HeroChat("Poked", new String[]{
				"Me-OW!", 
				"That's in-fur-iating!", 
				"I purr-omise revenge!",
				"You are making me catty", 
				"Not amewsed."
		}),
		new HeroChat("CardFit", new String[]{
				"Can't fit that {CARDNAME} right meow"
		}),
	};	
	
	static HeroChat[] mimeChats = new HeroChat[]{
		new HeroChat("Monster", new String[]{
				"[wiggling horn-fingers]", 
				"[waving a pretend sword]",
				"[wild, panicked gesturing]",
				"[over-the-top worried face]",
				"[confidently goose-stepping forth]",
				"[pointing BEHIND YOU]",
				"[hides behind own hands]"
		}),
		new HeroChat("Treasure", new String[]{
				"[overly happy face]", 
				"[counting out some coins]", 
				"[wiggly fingers]",
				"[over-the-top OOOOOOHHH face]",
				"[cartwheeling]",
				"[joyful skipping]"
		}),
		new HeroChat("Random", new String[]{
				"[peering into the far distance]", 
				"[climbing an imaginary ladder]",
				"[pretending to row a boat]", 
				"[walking down some imaginary stairs]", 
				"[overly despondent slow walk]", 
				"[tip-toeing forward INCREDIBLY slowly]", 
				"[chin-scratching]",
				"[looks around while scratching head]"
		}),
		new HeroChat("Explored", new String[]{
				"[slow eyebrow raise]",
				"[frantically looking all around]"
		}),
		new HeroChat("Unexplored", new String[]{
		}),
		new HeroChat("NoMove", new String[]{
				"[shrugs]"
		}),
		new HeroChat("Idle", new String[]{
				"[imaginary glass wall]", 
				"[trapped in a box]", 
				"[hands on hips, frowning]",
				"[arms folded, foot tapping]", 
				"[overly dramatic yawn]"
		}),
		new HeroChat("Poked", new String[]{
				"[falls over DEAD! no not really]", 
				"[startled look]", 
				"Hey, cut that out! [slams hand over mouth]",
				"[looks right at YOU, frowning]", 
				"[dodging imaginary arrows]", 
				"[waggles finger at YOU]"
		}),
		new HeroChat("CardFit", new String[]{
				"[shrugs]"
		}),
	};	
	
	static HeroChat[] barbariChats = new HeroChat[]{
		new HeroChat("Monster", new String[]{
				"I'm getting too old for this",
				"{NAME} SMASH!!", 
				"{MONSTER}? I'm definitely too old for this", 
				"{MONSTER}? That's more like it!", 
				"RRAAAAAUUUUGGGHH!!! *coughing fit*", 
				"In my day {MONSTERS} were much nicer"
		}),
		new HeroChat("Treasure", new String[]{
				"{TREASURE}!", 
				"I'm getting too old f-{TREASURE}!", 
				"More to carry? Oh my back",
				"Something for the retirement fund", 
				"{TREASURE}? Well well"
		}),
		new HeroChat("Random", new String[]{
				"I'm getting too old for this...", 
				"Where are my spectacles?",
				"Oh my old bones", 
				"Shouldn't there be more battling and shouting? I am BARBARIAN you know",
				"... and then I slew the last of the magical ponycorns. You see back then ...",
				"How about a sit down and a nice cuppa?",
				"I suppose I could PRACTICE my battle screams in this empty room",
				"I used to be town guard you know, until I took an arrow in the knee"
		}),
		new HeroChat("Explored", new String[]{
				"Was I here before? Hm, I forget",
				"You know what would help? A map"
		}),
		new HeroChat("Unexplored", new String[]{
		}),
		new HeroChat("NoMove", new String[]{
				"I'm just going to lie down here for a bit"
		}),
		new HeroChat("Idle", new String[]{
				"zzzzZZZZ", 
				"Finally a bit of peace and quiet", 
				"I suppose I'll just wait here for a bit",
				"Let's see, how does it go. GRRRRaaaarrrrggggGGGHHH!!! Yes, yes", 
				"How about finding me something easy.. like a rubber ducky?"
		}),
		new HeroChat("Poked", new String[]{
				"What? Huh? Was I asleep?",
				"Hey! Respect your elders",
				"One does not simply poke {NAME}!",
				"That's it! Time to unleash my barbarian RAGE!! *racking cough*",
				"Ouch! Right in the old knee wound"
		}),
		new HeroChat("CardFit", new String[]{
				"That won't fit anywhere!"
		}),
	};	
	public static void init(){
		
		//TODO Add in frame numbers
		
		heroName=SkillType.Chump;
		aHP=5;
		aChats=chumpChats;
		addClass();
		
		heroName=SkillType.Cat_Burglar;
		aHP=5;
		traits=new Trait[]{Trait.Covetous};
		aChats=catChats;
		addClass();
		
		heroName=SkillType.Brusier;
		aHP=5;
		aChats=chumpChats;
		addClass();
		
		heroName=SkillType.Mime;
		aHP=5;
		aChats=mimeChats;
		addClass();
		
		heroName=SkillType.Apprentice;
		aHP=5;
		aChats=chumpChats;
		addClass();
		
		heroName=SkillType.Ranger;
		aHP=6;
		traits=new Trait[]{Trait.Accurate, Trait.Ranged};
		aChats=chumpChats;
		addClass();
		
		heroName=SkillType.Barbarian;
		aHP=6;
		traits = new Trait[]{Trait.Deathwish};
		aChats=barbariChats;
		addClass();
		
		heroName=SkillType.Shapeshifter;
		aHP=6;
		traits=null;
		aChats=chumpChats;
		addClass();
		
		heroName=SkillType.Alchemist;
		aHP=5;
		traits=new Trait[]{Trait.Blessed};
		aChats=chumpChats;
		addClass();
		
		heroName=SkillType.Most_Holy_Grail_Knight;
		aHP=8;
		traits = new Trait[]{Trait.Retribution};
		aChats=chumpChats;
		addClass();
		
		heroName=SkillType.Troubador;
		aHP=7;
		traits = new Trait[]{Trait.SpellSword};
		aChats=chumpChats;
		addClass();
		
		heroName=SkillType.Cartomancer;
		traits = new Trait[]{Trait.Cunning};
		aHP=7;
		aChats=chumpChats;
		addClass();
		
		heroName=SkillType.Mathemagician;
		aHP=7;
		aChats=chumpChats;
		addClass();
		
		heroName=SkillType.Artificer;
		traits = new Trait[]{Trait.VitaSuit};
		aHP=7;
		aChats=chumpChats;
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
