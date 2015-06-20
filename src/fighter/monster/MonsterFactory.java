package fighter.monster;
import java.util.ArrayList;
import java.util.HashMap;

import cards.Skill;
import cards.Skill.SkillType;
import dungeon.Dungeon.TerrainType;
import fighter.Fighter.*;
import fighter.monster.BoardChat.ChatType;
import fighter.monster.Monster.Species;


public class MonsterFactory {

	public static ArrayList<Monster> stoneMonsters= new ArrayList<>();
	public static ArrayList<Monster> jungleMonsters= new ArrayList<>();
	public static ArrayList<Monster> noMonsters= new ArrayList<>();

	public static ArrayList<Monster> monsters= new ArrayList<Monster>();

	public enum Region{Stone, Jungle, Mines}

	public enum MSound{
		giant_bat, goblin, scary_spider, gray_ooze, ghost, skeleton, mummy, rubber_ducky
	}

	static String name;
	static Species species;
	static String plural;
	static String description;
	static int frameNumber;
	static int level;
	static int health;
	static int randomPool;
	static MSound sound;
	static Region region;
	static Trait[] traits;
	static Skill[] skills;
	static BoardChat[] boardChat;
	public static void setup(){

		reset();


		//tutorial / debug //

		name = "Debug Ducky";
		plural= "Debug Duckies";
		description = "Haha! I will defeat you with this ne---CRASH";
		frameNumber = 2;
		level = 0;
		health = 10;
		randomPool=0;
		sound = MSound.rubber_ducky;
		//traits = new Trait[]{Trait.Predictable};
		skills = new Skill[]{Skill.get(SkillType.Irritable).asLevel(5)};
		make();

		name = "Rubber Ducky";
		plural= "Rubber Duckies";
		description = "OK this doesn't look dangerous AT ALL";
		frameNumber = 2;
		level = 0;
		health = 4;
		randomPool=0;
		sound = MSound.rubber_ducky;
		//traits = new Trait[]{};
		skills = new Skill[]{Skill.get(SkillType.Irritable).asLevel(2), Skill.get(SkillType.Stupidity).asLevel(1)};
		make();


		//ZONE 1//

		

		name = "Nasty Rat";
		plural= "Nasty Rats";
		region=Region.Stone;
		species=Species.beast;
		description = "Not just a rat, oh no, this one's nasty.";
		frameNumber = 39;
		level = 1;
		health = 5;
		randomPool=1;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Feral).asLevel(2), Skill.get(SkillType.Nature).asLevel(2)};
		make();
		
		name = "Goblin";
		plural= "Goblins";
		region=Region.Stone;
		description = "Welcome to da club. Hehe geddit?";
		frameNumber = 15;
		level = 1;
		health = 6;
		randomPool=1;
		sound = MSound.goblin;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Irritable).asLevel(3), Skill.get(SkillType.Armed).asLevel(2)};
		make();
		
		name = "Fire Imp";
		plural= "Fire Imps";
		region=Region.Stone;
		species= Species.demonic;
		description = "You may feel a burning sensation.";
		frameNumber = 40;
		level = 1;
		health = 5;
		randomPool=1;
		sound = MSound.goblin;
		skills = new Skill[]{Skill.get(SkillType.Flame).asLevel(3), Skill.get(SkillType.Stupidity).asLevel(1)};
		make();

		name = "Giant Bat";
		plural= "Giant Bats";
		region=Region.Stone;
		species=Species.beast;
		description = "I'm not a vampire, leave me alone!";
		frameNumber = 12;
		level = 1;
		health = 4;
		randomPool=1;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Feral).asLevel(2), Skill.get(SkillType.Spooky).asLevel(3)};
		make();
		
		name = "Scary Spider";
		plural= "Scary Spiders";
		region=Region.Stone;
		species=Species.beast;
		description = "Woah - EIGHT dungeoneers!";
		frameNumber = 13;
		level = 1;
		health = 6;
		randomPool=1;
		sound = MSound.scary_spider;
		traits = new Trait[]{Trait.PhysicalVuln};
		skills = new Skill[]{Skill.get(SkillType.Nature).asLevel(2), Skill.get(SkillType.Spooky).asLevel(3)};
		make();

		name = "Gray Ooze";
		plural= "Gray Oozes";
		region=Region.Stone;
		description = "Gloopy stains on your armour a guarantee";
		frameNumber = 3;
		level = 1;
		health = 7;
		randomPool=1;
		sound = MSound.gray_ooze;
		traits = new Trait[]{Trait.MagicalVuln};
		skills = new Skill[]{Skill.get(SkillType.Death).asLevel(1), Skill.get(SkillType.Spooky).asLevel(2), Skill.get(SkillType.Flame).asLevel(2)};
		make();

		//L2//

		name = "Rat Man";
		plural= "Rat Men";
		region=Region.Stone;
		description = "Squeak squeak!";
		frameNumber = 42;
		level = 2;
		health = 6;
		randomPool=1;
		traits = null;
		sound= MSound.giant_bat;
		skills = new Skill[]{Skill.get(SkillType.Feral).asLevel(2), Skill.get(SkillType.Armed).asLevel(3), Skill.get(SkillType.Irritable).asLevel(3)};
		make();
		
		name = "Zombie";
		plural= "Zombies";
		region=Region.Stone;
		description = "*groan* *drool* I waaas like you .. once";
		frameNumber = 5;
		level = 2;
		health = 9;
		randomPool=1;
		sound = MSound.skeleton;
		traits = new Trait[]{Trait.Meaty};
		skills = new Skill[]{Skill.get(SkillType.Death).asLevel(3), Skill.get(SkillType.Irritable).asLevel(3), Skill.get(SkillType.Spooky).asLevel(3)};
		make();
		
		name = "Gnoll";
		plural= "Gnolls";
		region=Region.Stone;
		description = "What are you looking at??";
		frameNumber = 31;
		level = 2;
		health = 6;
		randomPool=1;
		sound= MSound.goblin;
		traits = new Trait[]{Trait.Fury};
		skills = new Skill[]{Skill.get(SkillType.Nature).asLevel(2), Skill.get(SkillType.Armed).asLevel(2), Skill.get(SkillType.Irritable).asLevel(3)};
		make();
		
		name = "Skeleton";
		plural= "Skeletons";
		region=Region.Stone;
		species=Species.undead;
		description = "Once a hoplite, always a hoplite";
		frameNumber = 1;
		level = 2;
		health = 8;
		randomPool=1;
		sound = MSound.skeleton;;
		traits = new Trait[]{Trait.Brittle};
		skills = new Skill[]{Skill.get(SkillType.Irritable).asLevel(2), Skill.get(SkillType.Spooky).asLevel(3), Skill.get(SkillType.Armed).asLevel(3)};
		make();
		
		name = "Snake";
		plural= "Snakes";
		region=Region.Stone;
		species=Species.beast;
		description = "SSSsssssssss";
		frameNumber = 28;
		level = 2;
		health = 7;
		randomPool=1;
		sound= MSound.scary_spider;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Nature).asLevel(3), Skill.get(SkillType.Death).asLevel(2), Skill.get(SkillType.Feral).asLevel(4)};
		make();
		
		name = "Ghost";
		plural= "Ghosts";
		region=Region.Stone;
		species=Species.undead;
		description = "BOO! Hey - were you scared?";
		frameNumber = 11;
		level = 2;
		health = 5;
		randomPool=1;
		sound = MSound.ghost;
		traits = new Trait[]{Trait.Tenacious};
		skills = new Skill[]{Skill.get(SkillType.Spooky).asLevel(4), Skill.get(SkillType.Death).asLevel(3)};
		make();

		name = "Mimic";
		plural= "Mimics";
		region=Region.Stone;
		description = "Maybe it still has treasure inside";
		frameNumber = 37;
		level = 2;
		health = 7;
		randomPool=0;
		sound = MSound.giant_bat;
		//traits = new Trait[]{};
		skills = new Skill[]{Skill.get(SkillType.Death).asLevel(3), Skill.get(SkillType.Feral).asLevel(3), Skill.get(SkillType.Irritable).asLevel(3)};
		make();

		
		//L3//

		name = "Mummy";
		plural= "Mummies";
		region=Region.Stone;
		species=Species.undead;
		description = "Never ever caught without any toilet paper";
		frameNumber = 4;
		level = 3;
		health = 9;
		randomPool=1;
		sound = MSound.mummy;
		traits = new Trait[]{Trait.Brittle};
		skills = new Skill[]{Skill.get(SkillType.Spooky).asLevel(4), Skill.get(SkillType.Irritable).asLevel(3), Skill.get(SkillType.Death).asLevel(3)};
		make();

		name = "Bear Owl";
		plural= "Bear Owls";
		region=Region.Stone;
		description = "A bit top-heavy";
		frameNumber = 36;
		level = 3;
		health = 7;
		randomPool=1;
		sound= MSound.goblin;
		traits = new Trait[]{Trait.Fury};
		skills = new Skill[]{Skill.get(SkillType.Nature).asLevel(3), Skill.get(SkillType.Feral).asLevel(2), Skill.get(SkillType.Armed).asLevel(3)};
		make();

		name = "Shade";
		plural= "Shades";
		region=Region.Stone;
		species=Species.undead;
		description = "Probably just my own shadow..";
		frameNumber = 25;
		level = 3;
		health = 7;
		randomPool=1;
		sound= MSound.ghost;
		traits = new Trait[]{Trait.Tenacious};
		skills = new Skill[]{Skill.get(SkillType.Spooky).asLevel(4), Skill.get(SkillType.Death).asLevel(5)};
		make();
		
		name = "Scorpion";
		plural= "Scorpions";
		region=Region.Stone;
		species=Species.undead;
		description = "Snap snap";
		frameNumber = 43;
		level = 3;
		health = 8;
		randomPool=1;
		sound= MSound.skeleton;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Armed).asLevel(4), Skill.get(SkillType.Nature).asLevel(4), Skill.get(SkillType.Feral).asLevel(4)};
		make();
		
		name = "Fire Elemental";
		plural= "Fire Elementals";
		region=Region.Stone;
		species=Species.demonic;
		description = "Hot hot hot!";
		frameNumber = 20;
		level = 3;
		health = 8;
		randomPool=1;
		sound= MSound.ghost;
		traits = new Trait[]{Trait.Burn};
		skills = new Skill[]{Skill.get(SkillType.Flame).asLevel(4), Skill.get(SkillType.Death).asLevel(4)};
		make();		

		name = "Bandito";
		plural= "Banditos";
		region=Region.Stone;
		description = "¡La bolsa o la vida!";
		frameNumber = 6;
		level = 3;
		health = 7;
		randomPool=1;
		sound= MSound.goblin;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Armed).asLevel(5)};
		make();

		name = "Minotaur";
		plural= "Minotaurs";
		region=Region.Stone;
		description = "Seeing red";
		frameNumber = 18;
		level = 3;
		health = 10;
		traits = new Trait[]{Trait.Fury, Trait.Meaty};
		randomPool=0;
		sound = MSound.giant_bat;
		skills = new Skill[]{Skill.get(SkillType.Irritable).asLevel(5), Skill.get(SkillType.Armed).asLevel(4)};
		boardChat=new BoardChat[]{
				new BoardChat(ChatType.Monster, new String[]{
						"Scram!", 
				}),

				new BoardChat(ChatType.Treasure, new String[]{
						"Give back Black Knight's stuff!", 
				}),

				new BoardChat(ChatType.Hero, new String[]{
				"I have you now!"
						}),

				new BoardChat(ChatType.Random, new String[]{
						"Gotta get stupid adventurer",
						"Want a promotion!",
				})
		};
		make();

		
		//BOSSES//
		
		
		
		name = "Rat King Cole";
		plural= "Rat Kings Cole";
		description = "Squeak squeak!";
		frameNumber = 62;
		level = 3;
		health = 8;
		randomPool=0;
		traits = null;
		sound= MSound.giant_bat;
		skills = new Skill[]{Skill.get(SkillType.Feral).asLevel(3), Skill.get(SkillType.Armed).asLevel(3), Skill.get(SkillType.Irritable).asLevel(3)};
		boardChat=new BoardChat[]{
				new BoardChat(ChatType.Monster, new String[]{
						"Squeak squeak!", 
				}),

				new BoardChat(ChatType.Treasure, new String[]{
						"Squeeeak", 
				}),

				new BoardChat(ChatType.Hero, new String[]{
				"SQUEAK!"}),

				new BoardChat(ChatType.Random, new String[]{
						"Chitter chitter", 
				})
		};
		make();

		name = "Mimic Queen";
		plural= "Mimic Queens";
		description = "Wow she looks angry!";
		frameNumber = 64;
		level = 4;
		health = 11;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = new Trait[]{Trait.Brittle};
		skills = new Skill[]{Skill.get(SkillType.Death).asLevel(5), Skill.get(SkillType.Feral).asLevel(4), Skill.get(SkillType.Irritable).asLevel(5)};
		boardChat=new BoardChat[]{
				new BoardChat(ChatType.Monster, new String[]{
						"Grahh! Get outta my way!", 
				}),

				new BoardChat(ChatType.Treasure, new String[]{
						"Chomp chomp", 
				}),

				new BoardChat(ChatType.Hero, new String[]{
				"Vengeance is mine!"}),

				new BoardChat(ChatType.Random, new String[]{
						"I'm coming for you, murderer!",
						"You can run but you can't hide",
						"I know exactly where you might be",
						"I can move two spaces every turn, you can't escape me!"
				})
		};
		make();

		name = "Orc Warlord";
		plural= "Orc Warlords";
		description = "ORCZ iz da best! *sigh* Must I do the voice?";
		frameNumber = 19;
		level = 4;
		health = 11;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = new Trait[]{Trait.Predictable};
		skills = new Skill[]{Skill.get(SkillType.Armed).asLevel(5), Skill.get(SkillType.Irritable).asLevel(5), Skill.get(SkillType.Stupidity).asLevel(2)};
		make();
		
		name = "Embro";
		plural= "Embrosia";
		description = "OW! This card is burning hot!";
		frameNumber = 8;
		level = 4;
		health = 7;
		randomPool=0;
		sound = MSound.skeleton;
		traits = new Trait[]{Trait.Skilled, Trait.Damp};
		skills = new Skill[]{Skill.get(SkillType.Armed).asLevel(5), Skill.get(SkillType.Flame).asLevel(5), Skill.get(SkillType.Irritable).asLevel(5)};
		boardChat=new BoardChat[]{
				new BoardChat(ChatType.Monster, new String[]{
						"OUT OF MY WAY MINION!", 
						"GET OUT OF MY SIGHT {MONSTER}",
						"BURN {MONSTER}, BURN! You only gave him hope!", 
						"Remind me not to stock any more {MONSTER}s..", 
						"Oh look another useless {MONSTER}",
						"RROOAAARRRGH!!", 
				"*sigh* more useless minions.."}),

				new BoardChat(ChatType.Treasure, new String[]{
						"Who left this here?!", 
						"{TREASURE}? Where did that come from?",
						"{TREASURE}! Back into my loot box with you!", 
						"Look at this clutter!", 
				"Just as I get peckish.. a {TREASURE}! *om nyom*"}),

				new BoardChat(ChatType.Hero, new String[]{
						"THERE YOU ARE! I HOPE YOU LIKE BURNING", 
						"TIME TO BURN!!!", 
				"NOW I HAVE YOU"}),

				new BoardChat(ChatType.Random, new String[]{
						"Does this make me a wandering monster?", 
						"How come I don't know the way around my own dungeon!?", 
						"This dungeon makes no sense!",
						"I think I'm getting closer!", 
						"If only I had a NOSE I would sniff them out!!", 
						"If only I could hold a map without burning it!", 
						"Hmm...", "I can hear you .. somewhere!"})

		};
		make();

		name = "The Black Knight";
		plural= "Black Knights";
		description = "I'll bite you!";
		frameNumber = 46;
		level = 4 ;
		health = 10;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = new Trait[]{Trait.MagicalVuln};
		skills = new Skill[]{Skill.get(SkillType.Armed).asLevel(5), Skill.get(SkillType.Death).asLevel(5)};
		make();

		name = "Lich";
		plural= "Lichen";
		description = "Don't let him cast Lich Itch on you!";
		frameNumber = 29;
		level = 4;
		health = 8;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = new Trait[]{Trait.Halfbaked};
		skills = new Skill[]{Skill.get(SkillType.Spooky).asLevel(5), Skill.get(SkillType.Death).asLevel(5)};
		make();

		name = "Eye Beast";
		plural= "Eye Beasts";
		description = "Is it looking at me?";
		frameNumber = 47;
		level = 4 ;
		health = 11;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = new Trait[]{Trait.PhysicalVuln};
		skills = new Skill[]{Skill.get(SkillType.Death).asLevel(5), Skill.get(SkillType.Flame).asLevel(5)};
		make();

		//ZONE 2//

	

		name = "Plague Rat";
		plural= "Plague Rats";
		region=Region.Jungle;
		species=Species.beast;
		description = "Not just a rat, oh no, this one's nasty.";
		frameNumber = 63;
		level = 1;
		health = 6;
		randomPool=1;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Feral).asLevel(2), Skill.get(SkillType.Venom).asLevel(2)};
		make();

		name = "Leggy Spider";
		plural= "Scary Spiders";
		region=Region.Jungle;
		species=Species.beast;
		description = "Woah - EIGHT dungeoneers!";
		frameNumber = 13;
		level = 1;
		health = 7;
		randomPool=1;
		sound = MSound.scary_spider;
		traits = new Trait[]{Trait.PhysicalVuln};
		skills = new Skill[]{Skill.get(SkillType.Venom).asLevel(3), Skill.get(SkillType.Spooky).asLevel(2)};
		make();
		
		name= "Bloodstarved Bat";
		plural= "Bloodstarved Bats";
		region=Region.Jungle;
		species=Species.beast;
		description = "I'm not a vampire, leave me alone!";
		frameNumber = 12;
		level = 1;
		health = 7;
		randomPool=1;
		sound = MSound.giant_bat;
		traits = new Trait[]{Trait.Predictable};
		skills = new Skill[]{Skill.get(SkillType.Rage).asLevel(2), Skill.get(SkillType.Spooky).asLevel(2)};
		make();
		
		name = "Pixies";
		plural= "Pixieseseses";
		region=Region.Jungle;
		description = "Pick on the little guys why don't you?";
		frameNumber = 41;
		level = 1;
		health = 6;
		randomPool=1;
		sound = MSound.giant_bat;
		traits = new Trait[]{Trait.Tenacious};
		skills = new Skill[]{Skill.get(SkillType.Demonic).asLevel(2), Skill.get(SkillType.Nature).asLevel(2)};
		make();
		
		name = "Frenzied Goblin";
		plural= "Frenzied Goblins";
		region=Region.Jungle;
		description = "Welcome to da club. Hehe geddit?";
		frameNumber = 15;
		level = 1;
		health = 6;
		randomPool=1;
		sound = MSound.goblin;
		traits = new Trait[]{Trait.Fury};
		skills = new Skill[]{Skill.get(SkillType.Rage).asLevel(3), Skill.get(SkillType.Armed).asLevel(2)};
		make();

		name = "Jungle Warrior";
		plural= "Jungle Warriors";
		region=Region.Jungle;
		description = "Welcome to the jungle";
		frameNumber = 55;
		level = 1 ;
		health = 7;
		randomPool=1;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Burly).asLevel(2), Skill.get(SkillType.Nature).asLevel(3)};
		make();

		
		name = "Poisonous Snake";
		plural= "Poisonous Snakes";
		region=Region.Jungle;
		species=Species.beast;
		description = "SSSsssssssss";
		frameNumber = 28;
		level = 2;
		health = 8;
		randomPool=1;
		sound= MSound.scary_spider;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Venom).asLevel(3), Skill.get(SkillType.Nature).asLevel(3)};
		make();

		name = "Rat Berserker";
		plural= "Rat Berserkers";
		region=Region.Jungle;
		description = "Squeak squeak!";
		frameNumber = 42;
		level = 2;
		health = 10;
		randomPool=1;
		traits = new Trait[]{Trait.Predictable};
		sound= MSound.giant_bat;
		skills = new Skill[]{Skill.get(SkillType.Rage).asLevel(3), Skill.get(SkillType.Armed).asLevel(3)};
		make();

		name = "Hilly Gnoll";
		plural= "Hilly Gnolls";
		region=Region.Jungle;
		description = "What are you looking at??";
		frameNumber = 31;
		level = 2;
		health = 8;
		randomPool=1;
		sound= MSound.goblin;
		traits = new Trait[]{Trait.Fury};
		skills = new Skill[]{Skill.get(SkillType.Burly).asLevel(2), Skill.get(SkillType.Armed).asLevel(2), Skill.get(SkillType.Irritable).asLevel(1)};
		make();

		name = "Lizardman";
		plural= "Lizardmen";
		region=Region.Jungle;
		description = "Fresh from Innsmouth";
		frameNumber = 30;
		level = 2;
		health = 8;
		randomPool=1;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Nature).asLevel(3), Skill.get(SkillType.Burly).asLevel(2)};
		make();

		name = "Harpy";
		plural= "Harpies";
		region=Region.Jungle;
		description = "Good friends with the guitary";
		frameNumber = 26;
		level = 2;
		health = 8;
		randomPool=1;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Demonic).asLevel(2), Skill.get(SkillType.Feral).asLevel(3)};
		make();

		name = "Man-Eating Plant";
		plural= "Man-Eating Plants";
		region=Region.Jungle;
		description = "CZRCHHHH!!";
		frameNumber = 56;
		level = 2 ;
		health = 8;
		randomPool=1;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Venom).asLevel(4), Skill.get(SkillType.Stupidity).asLevel(2)};
		make();

		name = "Owl Bear";
		plural= "Owl Bears";
		region=Region.Jungle;
		description = "Sqoarrrrrwk";
		frameNumber = 35;
		level = 3;
		health = 8;
		randomPool=1;
		sound = MSound.giant_bat;
		traits = new Trait[]{Trait.Fury};
		skills = new Skill[]{Skill.get(SkillType.Rage).asLevel(2), Skill.get(SkillType.Nature).asLevel(2)};
		make();

		name = "Dire Scorpion";
		plural= "Scorpions";
		region=Region.Jungle;
		species=Species.undead;
		description = "Snap snap";
		frameNumber = 43;
		level = 3;
		health = 8;
		randomPool=1;
		sound= MSound.skeleton;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Burly).asLevel(2), Skill.get(SkillType.Nature).asLevel(2)};
		make();

		name = "Jungle Shaman";
		plural= "Jungle Shamen";
		region=Region.Jungle;
		description = "Ommmmm";
		frameNumber = 54;
		level = 3;
		health = 8;
		randomPool=1;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Sorcery).asLevel(2), Skill.get(SkillType.Flame).asLevel(3)};
		make();

		name = "Air Elemental";
		plural= "Air Elementals";
		region=Region.Jungle;
		description = "needs description";
		frameNumber = 23;
		level = 3;
		health = 9;
		randomPool=1;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Sorcery).asLevel(2), Skill.get(SkillType.Irritable).asLevel(3)};
		make();

		name = "Gargoyle";
		plural= "Gargoyles";
		region=Region.Jungle;
		description = "No, not a big bat, a gargoyle!";
		frameNumber = 10;
		level = 3;
		health = 10;
		randomPool=1;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Demonic).asLevel(2), Skill.get(SkillType.Feral).asLevel(2)};
		make();

		name = "Worm";
		plural= "Worms";
		region=Region.Jungle;
		description = "Loves paprika";
		frameNumber = 14;
		level = 3;
		health = 9;
		randomPool=1;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Venom).asLevel(2), Skill.get(SkillType.Ghoulish).asLevel(1), Skill.get(SkillType.Stupidity).asLevel(2)};
		make();

		//BOSSES//

		name = "Medusa";
		plural= "Medusas";
		description = "Likes to gorge on goujons";
		frameNumber = 45;
		level = 4;
		health = 8;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Demonic).asLevel(2), Skill.get(SkillType.Venom).asLevel(3), Skill.get(SkillType.Ghoulish).asLevel(2)};
		make();


		name = "Ettin";
		plural= "Ettins";
		description = "You can't beat both of us!";
		frameNumber = 24;
		level = 4;
		health = 11;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Rage).asLevel(3), Skill.get(SkillType.Demonic).asLevel(2), Skill.get(SkillType.Stupidity).asLevel(1)};
		make();

		name = "Chimera";
		plural= "Chimerae";
		description = "Used to have a squirrel's head too";
		frameNumber = 52;
		level = 4 ;
		health = 10;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Venom).asLevel(2), Skill.get(SkillType.Rage).asLevel(2), Skill.get(SkillType.Feral).asLevel(2)};
		boardChat=new BoardChat[]{
				new BoardChat(ChatType.Monster, new String[]{
						"*MUNCH MUNCH*", 
				}),

				new BoardChat(ChatType.Treasure, new String[]{
						"Craww!", 
				}),

				new BoardChat(ChatType.Hero, new String[]{
				"*ROARRR!"}),

				new BoardChat(ChatType.Random, new String[]{
						"Bleat bleat!",
						"Sssssss!",
						"Roar!"
				})
		};
		make();

		name = "Ogre";
		plural= "Ogres";
		description = "Impossible to push over";
		frameNumber = 50;
		level = 4 ;
		health = 10;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = new Trait[]{Trait.Sleepy};
		skills = new Skill[]{Skill.get(SkillType.Rage).asLevel(2), Skill.get(SkillType.Burly).asLevel(3), Skill.get(SkillType.Stupidity).asLevel(2)};
		make();

		name = "Dragon";
		plural= "Dragons";
		description = "Very grumpy in the morning";
		frameNumber = 53;
		level = 4;
		health = 11;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Flame).asLevel(5), Skill.get(SkillType.Demonic).asLevel(2), Skill.get(SkillType.Sorcery).asLevel(2)};
		boardChat=new BoardChat[]{
				new BoardChat(ChatType.Monster, new String[]{
						"Tasty!", 
				}),

				new BoardChat(ChatType.Treasure, new String[]{
						"Mine! All MINE!", 
				}),

				new BoardChat(ChatType.Hero, new String[]{
				"Now I have you!"}),

				new BoardChat(ChatType.Random, new String[]{
						"Stupid adventurer",
						"You think you can come here and steal me treasure?",
						"You will pay for you greed!",
						"I'm coming for you",
						"You can't escape",
						"I almost know my way around",
				})
		};
		make();

		//ZONE 3//
		/*
		 * 	Miners, slime, goblin, fire imp, dwarvern minibot, rust monster,
			worm, zombie, ghost, skeleton, mummy, dwarvern sentry
			cave troll, golem, minotaur,  earth elemental, genii, gelaticube
		 */

		name = "Miner";
		plural= "Miners";
		region=Region.Mines;
		description = "needs description";
		frameNumber = 58;
		level = 1;
		health = 7;
		randomPool=1;
		sound = MSound.giant_bat;
		traits = new Trait[]{Trait.Fury};
		skills = new Skill[]{Skill.get(SkillType.Burly).asLevel(3), Skill.get(SkillType.Rage).asLevel(2)};
		make();

		name = "Infected Slime";
		plural= "Infected Slimes";
		region=Region.Mines;
		description = "needs description";
		frameNumber = 3;
		level = 1;
		health = 9;
		randomPool=1;
		sound = MSound.giant_bat;
		traits = new Trait[]{Trait.Meaty};
		skills = new Skill[]{Skill.get(SkillType.Ghoulish).asLevel(2), Skill.get(SkillType.Venom).asLevel(3)};
		make();

		name = "Albino Goblin";
		plural= "Albino Goblin";
		region=Region.Mines;
		description = "needs description";
		frameNumber = 15;
		level = 1;
		health = 9;
		randomPool=1;
		sound = MSound.giant_bat;
		traits = new Trait[]{Trait.Tenacious};
		skills = new Skill[]{Skill.get(SkillType.Rage).asLevel(2), Skill.get(SkillType.Irritable).asLevel(1), Skill.get(SkillType.Ghoulish).asLevel(2)};
		make();

		name = "Sizzling Imp";
		plural= "Sizzling Imps";
		region=Region.Mines;
		species= Species.demonic;
		description = "needs description";
		frameNumber = 40;
		level = 1;
		health = 8;
		randomPool=1;
		sound = MSound.goblin;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Demonic).asLevel(2), Skill.get(SkillType.Sorcery).asLevel(1)};
		make();

		name = "Rust Monster";
		plural= "Rust Monsters";
		region=Region.Mines;
		description = "Rusts the iron right out of your blood!";
		frameNumber = 33;
		level = 1;
		health = 7;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Venom).asLevel(2), Skill.get(SkillType.Burly).asLevel(1)};
		make();

		name = "Digger";
		plural= "Diggers";
		region=Region.Mines;
		description = "needs description";
		frameNumber = 59;
		level = 1;
		health = 9;
		randomPool=1;
		sound = MSound.giant_bat;
		traits = new Trait[]{Trait.Brittle};
		skills = new Skill[]{Skill.get(SkillType.Burly).asLevel(3), Skill.get(SkillType.Rage).asLevel(2)};
		make();

		//L2//


		name = "Blind Worm";
		plural= "Blind Worms";
		region=Region.Mines;
		description = "needs description";
		frameNumber = 14;
		level = 2;
		health = 9;
		randomPool=1;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Demonic).asLevel(2), Skill.get(SkillType.Ghoulish).asLevel(3), Skill.get(SkillType.Stupidity).asLevel(1)};
		make();

		name = "Rotting Corpse";
		plural= "Rotting Corpse";
		region=Region.Mines;
		description = "*needs description";
		frameNumber = 5;
		level = 2;
		health = 12;
		randomPool=1;
		sound = MSound.skeleton;
		traits = new Trait[]{Trait.Meaty};
		skills = new Skill[]{Skill.get(SkillType.Burly).asLevel(2), Skill.get(SkillType.Ghoulish).asLevel(2), Skill.get(SkillType.Rage).asLevel(2)};
		make();

		name = "Wailing Ghost";
		plural= "Wailing Ghosts";
		region=Region.Mines;
		species=Species.undead;
		description = "needs description";
		frameNumber = 11;
		level = 2;
		health = 8;
		randomPool=1;
		sound = MSound.ghost;
		traits = new Trait[]{Trait.Tenacious};
		skills = new Skill[]{Skill.get(SkillType.Ghoulish).asLevel(3), Skill.get(SkillType.Demonic).asLevel(2), Skill.get(SkillType.Sorcery).asLevel(2)};
		make();

		name = "Clattering Bones";
		plural= "Clattering Bones";
		region=Region.Mines;
		species=Species.undead;
		description = "needs description";
		frameNumber = 1;
		level = 2;
		health = 10;
		randomPool=1;
		sound = MSound.skeleton;;
		traits = new Trait[]{Trait.Brittle};
		skills = new Skill[]{Skill.get(SkillType.Demonic).asLevel(2), Skill.get(SkillType.Ghoulish).asLevel(3), Skill.get(SkillType.Burly).asLevel(2)};
		make();

		name = "Water Elemental";
		plural= "Water Elementals";
		region=Region.Mines;
		description = "needs description";
		frameNumber = 21;
		level = 2;
		health = 11;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = new Trait[]{Trait.Meaty};
		skills = new Skill[]{Skill.get(SkillType.Sorcery).asLevel(3), Skill.get(SkillType.Burly).asLevel(2)};
		make();

		name = "Gelatinous Cube";
		plural= "Gelatinous Cubes";
		region=Region.Mines;
		description = "Lemon flavour";
		frameNumber = 34;
		level = 2;
		health = 8;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = new Trait[]{Trait.Tenacious};
		skills = new Skill[]{Skill.get(SkillType.Venom).asLevel(2), Skill.get(SkillType.Demonic).asLevel(2)};
		make();

		//L3//

		name = "Cursed Mummy";
		plural= "Cursed Mummies";
		region=Region.Mines;
		species=Species.undead;
		description = "needs description";
		frameNumber = 4;
		level = 3;
		health = 13;
		randomPool=1;
		sound = MSound.mummy;
		traits = new Trait[]{Trait.Brittle};
		skills = new Skill[]{Skill.get(SkillType.Ghoulish).asLevel(4), Skill.get(SkillType.Irritable).asLevel(3), Skill.get(SkillType.Demonic).asLevel(1)};
		make();

		name = "Cave Troll";
		plural= "Cave Trolls";
		region=Region.Mines;
		species=Species.undead;
		description = "needs description";
		frameNumber = 60;
		level = 3;
		health = 9;
		randomPool=1;
		sound = MSound.mummy;
		traits = new Trait[]{Trait.Fury};
		skills = new Skill[]{Skill.get(SkillType.Rage).asLevel(4), Skill.get(SkillType.Irritable).asLevel(2), Skill.get(SkillType.Burly).asLevel(3)};
		make();

		name = "Earth Elemental";
		plural= "Earth Elementals";
		description = "needs description";
		region=Region.Mines;
		frameNumber = 22;
		level = 3;
		health = 9;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Burly).asLevel(3), Skill.get(SkillType.Demonic).asLevel(2)};
		make();

		name = "Genii";
		plural= "Genies";
		description = "needs description";
		region=Region.Mines;
		frameNumber = 9;
		level = 3;
		health = 9;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Sorcery).asLevel(3), Skill.get(SkillType.Demonic).asLevel(2)};
		make();

		name = "Vampire";
		plural= "Vampires";
		description = "You can't kill me!";
		region=Region.Mines;
		frameNumber = 16;
		level = 3;
		health = 9;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Ghoulish).asLevel(3), Skill.get(SkillType.Demonic).asLevel(3)};
		make();

		name = "Nymph";
		plural= "Nymphs";
		description = "It takes years of meditation to grow these";
		region=Region.Mines;
		frameNumber = 32;
		level = 3;
		health = 8;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Sorcery).asLevel(3), Skill.get(SkillType.Venom).asLevel(3)};
		make();

		//BOSSES//

		name = "Cyclops";
		plural= "Cyclopseses";
		description = "I spy with my little eye.. FOOD";
		frameNumber = 7;
		level = 4;
		health = 10;
		randomPool=0;
		sound = MSound.giant_bat;
//		traits = new Trait[]{Trait.Bulwark, Trait.Fury};
		skills = new Skill[]{Skill.get(SkillType.Burly).asLevel(4), Skill.get(SkillType.Venom).asLevel(2)};
		make();

		name = "Dwarf Sentry";
		plural= "Dwarf Sentries";
		description = "needs description";
		frameNumber = 67;
		level = 4;
		health = 10;
		randomPool=0;
		sound = MSound.giant_bat;
//		traits = new Trait[]{Trait.Bulwark, Trait.Fury};
		skills = new Skill[]{Skill.get(SkillType.Burly).asLevel(3), Skill.get(SkillType.Ghoulish).asLevel(2)};
		make();

		name = "Dwarf Masterpiece";
		plural= "Dwarf Masterpieces";
		description = "needs description";
		frameNumber = 65;
		level = 4;
		health = 10;
		randomPool=0;
		sound = MSound.giant_bat;
//		traits = new Trait[]{Trait.Bulwark, Trait.Fury};
		skills = new Skill[]{Skill.get(SkillType.Burly).asLevel(3), Skill.get(SkillType.Sorcery).asLevel(3)};
		make();

		name = "Dwarf Mecha";
		plural= "Dwarf Mecha";
		description = "needs description";
		frameNumber = 61;
		level = 4;
		health = 10;
		randomPool=0;
		sound = MSound.giant_bat;
//		traits = new Trait[]{Trait.Bulwark, Trait.Fury};
		skills = new Skill[]{Skill.get(SkillType.Rage).asLevel(3), Skill.get(SkillType.Burly).asLevel(2)};
		boardChat=new BoardChat[]{
				new BoardChat(ChatType.Monster, new String[]{
						"Bzzzt!", 
				}),

				new BoardChat(ChatType.Treasure, new String[]{
						"Czch-czch-czch!", 
				}),

				new BoardChat(ChatType.Hero, new String[]{
				"You're mine now!"}),

				new BoardChat(ChatType.Random, new String[]{
						"Krssssss",
						"Thunk Thunk",
						"Clomp",
						"Hooonk",
				})
		};
		make();


		name = "Troll";
		plural= "Trolls";
		description = "Really rude to people who go over his bridge";
		frameNumber = 48;
		level = 5 ;
		health = 10;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Irritable).asLevel(5)};
		boardChat=new BoardChat[]{
				new BoardChat(ChatType.Monster, new String[]{
						"Smash everything", 
				}),

				new BoardChat(ChatType.Treasure, new String[]{
						"Smash treasure", 
				}),

				new BoardChat(ChatType.Hero, new String[]{
				"Smash hero!"}),

				new BoardChat(ChatType.Random, new String[]{
						"Smash nothing",
						"Smash something soon!",
				})
		};
		make();

		name = "Dwarf Magnate";
		plural= "Dwarf Magnate";
		description = "needs description";
		frameNumber = 66;
		level = 4;
		health = 10;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = new Trait[]{Trait.Bulwark, Trait.Fury};
		skills = new Skill[]{Skill.get(SkillType.Sorcery).asLevel(4), Skill.get(SkillType.Demonic).asLevel(2)};
		make();

		//MISC MONSTERS//

		name = "Sorceress";
		plural= "Sorceresses";
		description = "She's got a wand and she's not afraid to use it";
		frameNumber = 0;
		level = 3;
		health = 5;
		randomPool=1;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Death).asLevel(3),Skill.get(SkillType.Flame).asLevel(4)};
		make();


		name = "Angry Bunny";
		plural= "Angry Bunnies";
		description = "Vicious!";
		frameNumber = 44;
		level = 4 ;
		health = 8;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Feral).asLevel(5)};
		make();


		//UNUSED MONSTERS//
		/*
		 * 
		 * name = "Golem";
		plural= "Golems";
		region=Region.Mines;
		description = "ACCESS DENIED";
		frameNumber = 17;
		level = 3;
		health = 10;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = new Trait[]{Trait.Brittle};
		skills = new Skill[]{Skill.get(SkillType.Burly).asLevel(4), Skill.get(SkillType.Sorcery).asLevel(2), Skill.get(SkillType.Stupidity).asLevel(1)};
		make();

		name = "Minotaur";
		plural= "Minotaurs";
		region=Region.Mines;
		description = "Seeing red";
		frameNumber = 18;
		level = 3;
		health = 8;
		traits = new Trait[]{Trait.Fury};
		randomPool=0;
		sound = MSound.giant_bat;
		traits = new Trait[]{Trait.Brittle};
		skills = new Skill[]{Skill.get(SkillType.Burly).asLevel(3), Skill.get(SkillType.Rage).asLevel(2)};
		make();

		name = "Cyclops";
		plural= "Cyclopseses";
		description = "I spy with my little eye.. FOOD";
		frameNumber = 7;
		level = 4;
		health = 10;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Feral).asLevel(5)};
		make();

		name = "Genii";
		plural= "Genies";
		description = "Something something deathwish";
		frameNumber = 9;
		level = 4;
		health = 8;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Flame).asLevel(5)};
		make();



		name = "Vampire";
		plural= "Vampires";
		description = "You can't kill me!";
		frameNumber = 16;
		level = 4;
		health = 10;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Spooky).asLevel(5)};
		make();

		name = "Golem";
		plural= "Golems";
		description = "ACCESS DENIED";
		frameNumber = 17;
		level = 4;
		health = 10;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Stupidity).asLevel(5)};
		make();

		name = "Minotaur";
		plural= "Minotaurs";
		description = "Seeing red";
		frameNumber = 18;
		level = 4;
		health = 5;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Irritable).asLevel(5)};
		make();



		name = "Water Elemental";
		plural= "Water Elementals";
		description = "I hope you like damp clothes";
		frameNumber = 21;
		level = 3;
		health = 7;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Irritable).asLevel(5)};
		make();



		name = "Air Elemental";
		plural= "Air Elementals";
		description = "Whoosh";
		frameNumber = 23;
		level = 4;
		health = 5;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Nature).asLevel(5)};
		make();





		name = "Ghoul";
		plural= "Ghouls";
		description = "Buhhh Guhhh Hhhhh";
		frameNumber = 27;
		level = 2;
		health = 7;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Spooky).asLevel(5)};
		make();


		name = "Nymph";
		plural= "Nymphs";
		description = "It takes years of meditation to grow these";
		frameNumber = 32;
		level = 4;
		health = 8;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Nature).asLevel(5)};
		make();

		name = "Rust Monster";
		plural= "Rust Monsters";
		description = "Rusts the iron right out of your blood!";
		frameNumber = 33;
		level = 2;
		health = 8;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Death).asLevel(5)};
		make();

		name = "Gelatinous Cube";
		plural= "Gelatinous Cubes";
		description = "Lemon flavour";
		frameNumber = 34;
		level = 2;
		health = 4;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Death).asLevel(5)};
		make();





		name = "Kobold";
		plural= "Kobolds";
		description = "Bold might be an overstatement";
		frameNumber = 38;
		level = 2;
		health = 7;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Feral).asLevel(5)};
		make();




		name = "Angry Bunny";
		plural= "Angry Bunnies";
		description = "You'll need some kind of religious explosive";
		frameNumber = 44;
		level = 5 ;
		health = 10;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Feral).asLevel(5)};
		make();









		name = "Cockatrice";
		plural= "Cockatrixen";
		description = "Don't touch it!";
		frameNumber = 49;
		level = 5 ;
		health = 10;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Death).asLevel(5)};
		make();



		name = "Basilisk";
		plural= "Basilisks";
		description = "Most powerful of all lisks";
		frameNumber = 51;
		level = 5 ;
		health = 10;
		randomPool=0;
		sound = MSound.giant_bat;
		traits = null;
		skills = new Skill[]{Skill.get(SkillType.Death).asLevel(5)};
		make();

		 */



		//
		//		name = "";
		//		plural= "s";
		//		description = "";
		//		frameNumber = ;
		//		level = ;
		//		health = ;
		//		randomPool=0;
		//		sound = MSound.giant_bat;
		//		traits = null;
		//		skills = new Skill[]{Skill..asLevel(5)};
		//		make();



		for(Monster m :monsters){
			if(m.region==null)continue;
			switch(m.region){
			case Jungle:
				jungleMonsters.add(m);
				break;
			case Stone:
				stoneMonsters.add(m);
				break;
			default:
				break;

			}

		}

	}


	public static HashMap<String, Monster> monsterHash = new HashMap<>(); // it's a graveyard bash //
	private static void make(){
		Monster m =new Monster(name, plural, region, species, description, frameNumber, level, health, randomPool, sound, traits, skills, boardChat);
		monsters.add(m);
		monsterHash.put(m.name, m);
		reset();
	}

	private static void reset(){
		name="unset";
		species=null;
		region=null;
		plural="unset";
		description="unset";
		frameNumber=0;
		level=0;
		health=0;
		randomPool=0;
		sound=null;
		traits=null;
		skills=null;
		boardChat=null;
	}

	public static void printAll(){
		for(Monster m: monsters) {
			m.getStrength(true);
		}
	}
	
	public static void printAll(Region region){
		for(Monster m: monsters) {
			if(m.region!=region)continue;
			m.getStrength(true);
		}
	}

	public static void sortMonsters(){
		ArrayList<Monster> sorted = new ArrayList<>();

		for(Monster m:monsters){
			boolean added=false;
			for(int i=0;i<sorted.size();i++){
				Monster s=sorted.get(i);
				if(m.frameNumber<s.frameNumber){
					added=true;
					sorted.add(sorted.indexOf(s), m);
				}
				if(added){
					break;
				}
			}
			if(!added){
				sorted.add(sorted.size(), m);
			}
		}
		for(Monster m:sorted){
			System.out.println(m.name);
		}
	}

	public static String jsonAll() {
		String output="{\n\"Monsters\":{";
		for(int index=0;index<monsters.size();index++){
			Monster m=monsters.get(index);
			output+=m.toJson()+(monsters.size()>index+1?",":"");
		}
		output+="}\n}";
		return output;
	}

	public static ArrayList<Monster> getMonsters(Region region, int maxLevel){
		ArrayList<Monster> result = new ArrayList<>();
		for(Monster m:MonsterFactory.monsters){
			if(m.region==region&&m.level<=maxLevel){
				result.add(m);
			}
		}
		return result;
	}

	public static ArrayList<Monster> getMonsters(String[] names){
		ArrayList<Monster> result = new ArrayList<>();
		for(String s:names){
			boolean found =false;
			for(Monster m:MonsterFactory.monsters){
				if(m.name.equals(s)){
					result.add(m);
					found=true;
					break;
				}
				
			}
			if(!found){
			System.out.println("Could not find monster "+s);
			throw new IllegalArgumentException();
			}
		}
		return result;
	}
}
