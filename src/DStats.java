import fighter.Fighter.Trait;
import fighter.Fighter.*;

import java.util.ArrayList;




import fighter.monster.Monster;
import fighter.monster.Monster.*;
import fighter.player.Hero;
import fighter.player.Wizard;


public class DStats {
	



	public static Hero wizard= new Wizard();
	
	
	
	//level 1//
	public static Monster giantBat = new Monster("Giant Bat", Species.beast, 4, null, Monster.feral.getStrengths(2), Monster.spooky.getStrengths(3));
	public static Monster nastyRat = new Monster("Nasty Rat", Species.beast, 5, null, Monster.feral.getStrengths(2), Monster.nature.getStrengths(2));
	public static Monster goblin = new Monster("Goblin", 6, Monster.rage.getStrengths(3), Monster.weapon.getStrengths(2));
	public static Monster scarySpider= new Monster("Scary Spider", 4, Monster.nature.getStrengths(2), Monster.spooky.getStrengths(3));
	public static Monster fireImp= new Monster("Fire Imp", Species.demonic, 5, null, Monster.flame.getStrengths(4), Monster.stupidity.getStrengths(1));
	public static Monster grayOoze= new Monster("Gray Ooze", 5, Monster.death.getStrengths(1), Monster.spooky.getStrengths(2), Monster.flame.getStrengths(2));
	
	//level 2//
	public static Monster ghost = new Monster("Ghost", Species.undead, 6, new Trait[]{Trait.Tenacious}, Monster.spooky.getStrengths(4), Monster.death.getStrengths(2));
	public static Monster snake= new Monster("Snake", Species.beast, 7, null, Monster.nature.getStrengths(3), Monster.death.getStrengths(1), Monster.feral.getStrengths(4));
	public static Monster ratMan= new Monster("Rat Man", Species.beast, 8, null, Monster.feral.getStrengths(3), Monster.weapon.getStrengths(3), Monster.rage.getStrengths(3));
	public static Monster skeleton= new Monster("Skeleton", Species.undead, 8, new Trait[]{Trait.Brittle}, Monster.rage.getStrengths(2), Monster.spooky.getStrengths(3), Monster.weapon.getStrengths(3));
	public static Monster zombie= new Monster("Zombie", Species.undead, 11, new Trait[]{Trait.Meaty}, Monster.death.getStrengths(3), Monster.rage.getStrengths(3), Monster.spooky.getStrengths(3));
	public static Monster gnoll= new Monster("Gnoll", 7, new Trait[]{Trait.Respite}, Monster.nature.getStrengths(3), Monster.feral.getStrengths(2), Monster.weapon.getStrengths(3));
	public static Monster pixies;
	public static Monster kobold;
	public static Monster gelatinousCube;
		
	//level 3//
	public static Monster mummy= new Monster("Mummy", Monster.Species.undead, 10, new Trait[]{Trait.Brittle}, Monster.spooky.getStrengths(4), Monster.rage.getStrengths(3), Monster.death.getStrengths(3));
	public static Monster bearOwl= new Monster("Bear-Owl", Monster.Species.beast, 8, new Trait[]{Trait.Fury}, Monster.rage.getStrengths(4), Monster.feral.getStrengths(4), Monster.nature.getStrengths(2));
	public static Monster shade= new Monster("Shade", Monster.Species.undead, 8, new Trait[]{Trait.Tenacious}, Monster.spooky.getStrengths(4), Monster.death.getStrengths(4));
	public static Monster fireElemental= new Monster("Fire Elemental", Monster.Species.demonic, 10, new Trait[]{Trait.Burn}, Monster.flame.getStrengths(4), Monster.rage.getStrengths(4));
	public static Monster scorpion= new Monster("Scorpion", Monster.Species.beast, 9, null, Monster.weapon.getStrengths(4), Monster.nature.getStrengths(4), Monster.feral.getStrengths(3));
	public static Monster bandito= new Monster("Bandito", 7, Monster.weapon.getStrengths(5));
	public static Monster lizardMan;
	public static Monster ghoul;
	public static Monster rustMonster;
	
	//level 4?
	public static Monster worm;
	public static Monster orcGrunt;
	public static Monster harpy;
	public static Monster sorceress;
	
	public static Monster gargoyle;
	public static Monster nymph;
	public static Monster ettin;
	public static Monster waterElemental;
	public static Monster airElemental;
	
	
	//level 5?
	public static Monster golem;
	public static Monster owlBear;
	public static Monster earthElemental;
	public static Monster cyclops;
	public static Monster genii;
	public static Monster vampire;
	public static Monster minoatur;
	public static Monster lich;
	
	//boss?
	public static Monster fireDemon;
	public static Monster gorgon;
	public static Monster blackKnight;
	public static Monster angryBunny;
	public static Monster eyeBeast;
	public static Monster troll;
	public static Monster cockatrice;
	public static Monster ogre;
	public static Monster basilisk;
	public static Monster chimera;
	public static Monster deagon;
	
	
	//special
	public static Monster mimic;
	public static Monster rubberDucky;
	
	
	static ArrayList<Monster>[] monsterList= new ArrayList[8];

	public static void main(String[] args) {
		setup();
		
		
	
		printStrengths(3);
		
	}

	static void setup(){
		for(int i=0;i<monsterList.length;i++){
			monsterList[i]=new ArrayList<Monster>();
		}
		monsterList[1].add(giantBat);
		monsterList[1].add(nastyRat);
		monsterList[1].add(goblin);
		monsterList[1].add(scarySpider);
		monsterList[1].add(grayOoze);
		monsterList[1].add(fireImp);
		
		monsterList[2].add(ghost);
		monsterList[2].add(snake);
		monsterList[2].add(ratMan);
		monsterList[2].add(skeleton);
		monsterList[2].add(zombie);
		monsterList[2].add(gnoll);
		
		monsterList[3].add(mummy);
		monsterList[3].add(bearOwl);
		monsterList[3].add(shade);
		monsterList[3].add(fireElemental);
		monsterList[3].add(scorpion);
		monsterList[3].add(bandito);
	}
	
	private static void printStrengths(int level) {
		for(Monster m:monsterList[level]){
			m.getStrength(true);
		}		
	}
	
	

}
