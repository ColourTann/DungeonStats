package fighter;
import item.Item;

import java.util.ArrayList;

import cards.MSkill;
import fighter.Fighter.*;
import fighter.monster.Monster;
import fighter.monster.Monster.Species;


public class MonsterFactory {
	
	public static ArrayList<Monster> monsters= new ArrayList<Monster>();
	
	
	public static MSkill feral = new MSkill("Feral", new float[]{
			1.2f,
			1.2f,
			2.3f,
			2.3f,
			3});
	
	public static MSkill spooky = new MSkill("Spooky", new float[]{
			1.3f,
			1.8f,
			1.8f,
			2.6f,
			3.5f});
	
	public static MSkill rage = new MSkill("Rage", new float[]{
			0.1f,
			1.3f,
			1.3f,
			2.3f,
			3.3f});
	
	public static MSkill stupidity= new MSkill("Stupidity", new float[]{
			0,
			0,
			0,
			0,
			0});
	
	public static MSkill weapon = new MSkill("Weapon Skill", new float[]{
			1,
			1,
			2.3f,	
			2.3f,
			3.8f});
	
	public static MSkill flame = new MSkill("Flame", new float[]{
			1,
			1.2f,
			2,
			2,
			3});
	
	public static MSkill nature = new MSkill("Nature", new float[]{
			1,
			1.2f,
			2,
			3.5f,
			3.5f});
	
	public static MSkill death= new MSkill("Death", new float[]{
			1,
			1.5f,
			2,
			2.8f,
			3});
	
	public static MSkill ferocious = new MSkill("Ferocious", new float[]{
			2.5f,
			3.5f,
			4});
	
	public static MSkill demonic = new MSkill("Demonic", new float[]{
			1.8f,
			2.8f,
			4});
	
	public static MSkill sorcery = new MSkill("Sorcery", new float[]{
			2.2f,
			2.5f,
			4});
	
	
	
	
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
	static Trait[] traits;
	static MSkill[] skills;
	
	public static void setup(){
		
		reset();
		
		name = "Rubber Ducky";
		plural= "Rubber Duckies";
		description = "OK this doesn't look dangerous AT ALL";
		frameNumber = 2;
		level = 1;
		health = 3;
		randomPool=0;
		sound = MSound.rubber_ducky;
		traits = null;
		skills = new MSkill[]{feral.asLevel(2),stupidity.asLevel(1)};
		make();
		
		name = "Giant Bat";
		plural= "Giant Bats";
		species=Species.beast;
		description = "I'm not a vampire, leave me alone!";
		frameNumber = 12;
		level = 1;
		health = 4;
		randomPool=1;
		sound = MSound.giant_bat;
		traits = null;
		skills = new MSkill[]{feral.asLevel(2), spooky.asLevel(3)};
		make();
		
		name = "Nasty Rat";
		plural= "Nasty Rats";
		species=Species.beast;
		description = "Not just a rat, oh no, this one's nasty.";
		frameNumber = 39;
		level = 1;
		health = 5;
		randomPool=1;
		traits = null;
		skills = new MSkill[]{feral.asLevel(2), nature.asLevel(2)};
		make();
		
		name = "Goblin";
		plural= "Goblins";
		description = "Welcome to da club. Hehe geddit?";
		frameNumber = 15;
		level = 1;
		health = 6;
		randomPool=1;
		sound = MSound.goblin;
		traits = null;
		skills = new MSkill[]{rage.asLevel(3), weapon.asLevel(2)};
		make();
		
		name = "Scary Spider";
		plural= "Scary Spiders";
		species=Species.beast;
		description = "Woah - EIGHT dungeoneers!";
		frameNumber = 13;
		level = 1;
		health = 4;
		randomPool=1;
		sound = MSound.scary_spider;
		traits = null;
		skills = new MSkill[]{nature.asLevel(2), spooky.asLevel(3)};
		make();
		
		name = "Fire Imp";
		plural= "Fire Imps";
		species= Species.demonic;
		description = "You may feel a burning sensation.";
		frameNumber = 40;
		level = 1;
		health = 5;
		randomPool=1;
		traits = null;
		skills = new MSkill[]{flame.asLevel(4), stupidity.asLevel(1)};
		make();
		
		name = "Gray Ooze";
		plural= "Gray Oozes";
		description = "Gloopy stains on your armour a guarantee";
		frameNumber = 3;
		level = 1;
		health = 5;
		randomPool=1;
		sound = MSound.gray_ooze;
		traits = null;
		skills = new MSkill[]{death.asLevel(1), spooky.asLevel(2), flame.asLevel(2)};
		make();
		
		name = "Ghost";
		plural= "Ghosts";
		species=Species.undead;
		description = "BOO! Hey - were you scared?";
		frameNumber = 11;
		level = 2;
		health = 6;
		randomPool=1;
		sound = MSound.ghost;
		traits = new Trait[]{Trait.Tenacious};
		skills = new MSkill[]{spooky.asLevel(4), death.asLevel(2)};
		make();
		
		name = "Snake";
		plural= "Snakes";
		species=Species.beast;
		species=Species.beast;
		description = "";
		frameNumber = 28;
		level = 2;
		health = 7;
		randomPool=1;
		traits = null;
		skills = new MSkill[]{nature.asLevel(3), death.asLevel(1), feral.asLevel(4)};
		make();
		
		name = "Rat Man";
		plural= "Rat Men";
		description = "Squeak squeak!";
		frameNumber = 42;
		level = 2;
		health = 8;
		randomPool=1;
		traits = null;
		skills = new MSkill[]{feral.asLevel(3), weapon.asLevel(3), rage.asLevel(3)};
		make();
		
		name = "Skeleton";
		plural= "Skeletons";
		species=Species.undead;
		description = "Once a hoplite, always a hoplite";
		frameNumber = 1;
		level = 2;
		health = 8;
		randomPool=1;
		sound = MSound.skeleton;;
		traits = new Trait[]{Trait.Brittle};
		skills = new MSkill[]{rage.asLevel(3), spooky.asLevel(3), weapon.asLevel(3)};
		make();
		
		name = "Zombie";
		plural= "Zombies";
		description = "*groan* *drool* I waaas like you .. once";
		frameNumber = 5;
		level = 2;
		health = 11;
		randomPool=1;
		sound = MSound.giant_bat;
		traits = new Trait[]{Trait.Meaty};
		skills = new MSkill[]{death.asLevel(3), rage.asLevel(3), spooky.asLevel(3)};
		make();
		
		
		name = "Gnoll";
		plural= "Gnolls";
		description = "Need to write this";
		frameNumber = 31;
		level = 2;
		health = 7;
		randomPool=1;
		traits = new Trait[]{Trait.Respite};
		skills = new MSkill[]{nature.asLevel(3), weapon.asLevel(3), feral.asLevel(2)};
		make();
		
		name = "Mummy";
		plural= "Mummies";
		species=Species.undead;
		description = "Never ever caught without any toilet paper";
		frameNumber = 4;
		level = 3;
		health = 10;
		randomPool=1;
		sound = MSound.mummy;
		traits = new Trait[]{Trait.Brittle};
		skills = new MSkill[]{spooky.asLevel(4), rage.asLevel(3), death.asLevel(3)};
		make();
		
		name = "Bear-Owl";
		plural= "Bear-Owls";
		description = "A bit top-heavy";
		frameNumber = 45;
		level = 3;
		health = 8;
		randomPool=1;
		traits = new Trait[]{Trait.Fury};
		skills = new MSkill[]{nature.asLevel(3), feral.asLevel(2), weapon.asLevel(3)};
		make();
		
		name = "Shade";
		plural= "Shades";
		species=Species.undead;
		description = "Probably just my own shadow..";
		frameNumber = 25;
		level = 3;
		health = 8;
		randomPool=1;
		traits = new Trait[]{Trait.Tenacious};
		skills = new MSkill[]{spooky.asLevel(4), death.asLevel(4)};
		make();
		
		
		name = "Fire Elemental";
		plural= "Fire Elementals";
		species=Species.demonic;
		description = "Hot hot hot!";
		frameNumber = 20;
		level = 3;
		health = 10;
		randomPool=1;
		traits = new Trait[]{Trait.Burn};
		skills = new MSkill[]{flame.asLevel(4), rage.asLevel(4)};
		make();
		
		name = "Scorpion";
		plural= "Scorpions";
		species=Species.undead;
		description = "Snap snap";
		frameNumber = 43;
		level = 3;
		health = 9;
		randomPool=1;
		traits = null;
		skills = new MSkill[]{weapon.asLevel(4), nature.asLevel(4), feral.asLevel(4)};
		make();
		
		name = "Bandito";
		plural= "Banditos";
		description = "¡La bolsa o la vida!";
		frameNumber = 6;
		level = 3;
		health = 7;
		randomPool=1;
		traits = null;
		skills = new MSkill[]{weapon.asLevel(5)};
		make();
		
//		name = "";
//		plural= "s";
//		description = "";
//		frameNumber = ;
//		level = ;
//		health = ;
//		randomPool=1;
//		sound = MSound.giant_bat;
//		traits = null;
//		skills = new MSkill[]{};
//		make();
		
	}
	
	
	
	private static void make(){
		monsters.add(new Monster(name, plural, species, description, frameNumber, level, health, randomPool, sound, traits, skills));
		reset();
	}
	
	private static void reset(){
		name="unset";
		species=null;
		plural="unset";
		description="unset";
		frameNumber=0;
		level=0;
		health=0;
		randomPool=0;
		sound=null;
		traits=null;
		skills=null;
	}
	
	public static void printAll(){
		for(Monster m: monsters) m.getStrength(true);
	}

	public static void jsonAll() {
		System.out.println("{\n\"Monsters\":{");
		for(int index=0;index<monsters.size();index++){
			Monster m=monsters.get(index);
			System.out.println(m.toJson()+(monsters.size()>index+1?",":""));
		}
		System.out.println("}");
		System.out.println("}");
	}
}
