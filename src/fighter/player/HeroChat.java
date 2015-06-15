package fighter.player;

import json.Json;

public class HeroChat {
	public String label;
	public String[] chats;
	public HeroChat(String label, String[] chats) {
		this.label=label; this.chats=chats;
	}
	public String toJson(){
		String output="";
		
		output+=Json.startArray(label);
		for(int i=0;i<chats.length;i++){
			String chat = chats[i];
			output+="\""+chat+"\"";
			if(i<chats.length-1){
				output=Json.addComma(output);
			}
		}
		output+=Json.endArray(false);
		
		return output;
	}
	
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

	static HeroChat[] apprenticeChats = new HeroChat[]{
		new HeroChat("Monster", new String[]{
				"More {MONSTERS}? Simply dreadful.",
				"Oh the majestic {MONSTER}. A fine specimen too!",
				"This never used to happen in finishing school!",
				"Dulce et decorum est pro patria mori, as they say. But for one's Guild?",
				"Fortes fortuna iuvat, indeed!",
				"By Jove, if it isn't a {MONSTER}",
				"I don't suppose we could simply exchange pleasantries instead of more boorish dueling?",
				"They really have let this place go haven't they. {MONSTERS} everywhere."
		}),
		new HeroChat("Treasure", new String[]{
				  "Where has my manservant gotten to? Am I to carry all this myself?",
                  "{TREASURE}! Nulli secundus, indeed",
                  "Oh bother, another {TREASURE} to weigh me down",
                  "Ad praesens ova cras pullis sunt meliora, as I always say!",
                  "More specie? Perhaps we can renovate that worn-down old guild with this",
                  "Good grief! Who leaves {TREASURE} just sitting on the ground like this",
                  "{TREASURE}? Simply vulgar!"
		}),
		new HeroChat("Random", new String[]{
				"Capital, old bean, and jolly good show too!",
                "Well well well, let's see then",
                "Which way? This way! Alea iacta est, you know",
                "Oh no, not more bothersome trudging about",
                "I never thought I'd miss that grubby old guild room",
                "Onwards, companions! Oh that's right it's just me",
                "I hope this place has decent library, at least",
                "Perchance I can write about this in my journal",
                "Gosh! That awful smell!"
		}),
		new HeroChat("Explored", new String[]{
				 "One finds oneself doing more aimless meandering than one would prefer",
                 "I hope YOU know what we should be doing with ourselves in here",
                 "Hopelessly lost! If only daddy were here to fix all this"
		}),
		new HeroChat("Unexplored", new String[]{
		}),
		new HeroChat("NoMove", new String[]{
                "I suppose we'll just stand right here then, shall we?"
		}),
		new HeroChat("Idle", new String[]{
                "You must come see our chateau in the summertime. Simply fabulous!",
                "... so there we were enjoying our brandy after dinner when all of a sudden Lady Ludwig burst into ...",
                "You wouldn't believe the absolute oafs they have let into this guild!",
                "Perhaps they have simply abandoned me here? Most egregious.",
                "Lets all just stand around, shall we?",
                "I am starting to believe I'm not part of the dramatis personae here."

		}),
		new HeroChat("Poked", new String[]{
				 "Darling, what is it?",
                 "Would you mind holding off on the incessant poking? I'm trying to think",
                 "By Jove, you wouldn't do that if I had my manservant here to teach you a lesson!",
                 "It seems they'll let ANYONE become a guild master, these days",
                 "oof. Dum spiro spero, I suppose",
                 "OW! I was trying to record my most precious thoughts in my private journal you oaf!"
		}),
		new HeroChat("CardFit", new String[]{
                "There's nowhere for that to go right now, is there old bean?"
		}),
	};
	
	static HeroChat[] bruiserChats = new HeroChat[]{
		new HeroChat("Monster", new String[]{
				  "OI {MONSTER}!",
                  "Summink to PUNCH!",
                  "You what?",
                  "Ere what d'you fink you're lookin at {MONSTER}",
                  "I'll bash you!",
                  "What is you lookin' at!? I'll 'ave you",
                  "No offense but, {MONSTERS} is well ugly, innit",
					"Watch it {MONSTER} or I'll have me bruvver bash ya"
		}),
		new HeroChat("Treasure", new String[]{
			    "That's well shiny",
                "Give us that {TREASURE}",
                "Ah yeah give us that {TREASURE}!",
                "Havin' that",
                "Send me that!",
				"Minted",
				"Proper blingin'",
                "{TREASURE}? I'm havin' that mate"
		}),
		new HeroChat("Random", new String[]{
				 "Give us something to bash, mate!",
                 "What you got there? More cards?",
                 "You wot? This is well dumb",
                 "Lets bounce",
                 "What's this? There's nuffink here an' all",
                 "No offense, but give us somethin' to do yeah?",
                 "More nothin', innit"
		}),
		new HeroChat("Explored", new String[]{
				"Ah no I wuz here before and all",
                "D'you even know what yer doin' mate?",
                "This is right stupid"
		}),
		new HeroChat("Unexplored", new String[]{
		}),
		new HeroChat("NoMove", new String[]{
				"I'm proper stuck 'ere"
		}),
		new HeroChat("Idle", new String[]{
                "No but yeah, knowhaddimean?",
				"Nuff said, mate",
				"This is proper borin'"
		}),
		new HeroChat("Poked", new String[]{
                "Am I bovvered?",
                "Am I bovvered though?",
                "Look at my face. Is it bovvered?",
                "Arks me if I'm bovvered!",
                "Look, face, bovvered?",
				"I AIN'T BOVVERED!!"
		}),
		new HeroChat("CardFit", new String[]{
                "That won't fit nowhere!"
		}),
	};
	
}
