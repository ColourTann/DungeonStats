package item;

import item.Item.UnlockedBy;

import java.awt.event.TextEvent;
import java.util.ArrayList;
import java.util.HashMap;

import json.Json;
import cards.Skill;
import cards.Skill.SkillType;
import fighter.Fighter.Trait;

public class Item {

	public enum TreasureType{MEGA_CHEST("MEGA CHEST"), GOLD_COIN("Gold Coin"), Large_Chest("Large Chest"), Massive_Gem("Massive Gem"), Sapphire_Ring("Sapphire Ring"), Orb_of_Nosiness("Orb of Nosiness"), Gold_Ore("Gold Ore");
	String nice;
	TreasureType(String nice){
		this.nice=nice;
	}

	public String toString(){
		return nice;
	}
	}

	public enum UnlockedBy{Base, Smith, Leather, Wood, Curio}

	static ArrayList<Item> items = new ArrayList<Item>();
	static ArrayList<Item>[] levels = new ArrayList[6];
	enum EquipmentType{armour, shield, helmet, weapon, other};
	enum Sound{equip_coin_single, equip_coin_double, equip_robes, equip_coin_bag, equip_staff, bling, equip_coin_chest, equip_leather_helm, equip_horned_helm, equip_winged_helm, equip_scalemail, equip_chainmail, equip_platemail, equip_wooden_shield, equip_buckler, equip_club, equip_mace, equip_axe, equip_potion, equip_cloth_hat, equip_knife, equip_bow, equip_sword, equip_silver_shield, equip_great_shield, equip_leather_armour};
	public String name;
	String description;
	int frameNumber;
	int equipment;
	EquipmentType type;
	int equipFrame; int equipBackFrame;
	int level;
	UnlockedBy unlockedBy;
	int unlockLevel;
	int glory;
	int spawnCount;
	int randomPool;
	int keepHair;
	int offsetX, offsetY;
	Sound sound;
	Trait[] traits;
	Skill[] skills;
	int health;

	public Item(String name, 
			String description, 
			int frameNumber, 
			int equipment, 
			EquipmentType type,
			int equipFrame, int equipBackFrame,
			int level,
			UnlockedBy unlockedBy,
			int unlockLevel,
			int glory,
			int spawnCount,
			int randomPool,
			int keepHair,
			int offsetX, int offsetY,
			Sound sound,
			Skill[] skills,
			Trait[] traits,
			int health
			){
		this.name=name;
		this.description=description;
		this.frameNumber=frameNumber;
		this.equipment=equipment;
		this.type=type==null?EquipmentType.other:type;
		this.equipFrame=equipFrame; this.equipBackFrame= equipBackFrame;
		this.level=level;
		this.unlockedBy=unlockedBy;
		this.unlockLevel=unlockLevel;
		this.glory=glory;
		this.spawnCount=spawnCount;
		this.randomPool=randomPool;
		this.keepHair=keepHair;
		this.offsetX=offsetX; this.offsetY=offsetY;
		this.sound=sound;
		this.skills=skills;
		this.traits=traits;
		this.health=health;

	}
	static String aName;
	static String aDesc;
	static int aFrameNumber;
	static int aEquipment;
	static EquipmentType aType;
	static int aEquipFrame;
	static int aEquipBackFrame;
	static int aLevel;
	static UnlockedBy aUnlock;
	static int aUnlockLevel;
	static int aGlory;
	static int aSpawnCount;
	static int aRandomPool;
	static int aKeepHair;
	static int aHealth;
	static int aOffsetX, aOffsetY;
	static Sound aSound;
	static Skill[] aSkills;
	static Trait[] aTraits;

	public static void setup(){
		reset();
		aFrameNumber=0;


		aName="Gold Coin";
		aDesc="A single coin lying in the dirt";
		aGlory=1;
		aLevel=1;
		aUnlockLevel=0;
		aRandomPool=1;
		aSound=Sound.equip_coin_single;
		add();

		aName="Silver Coins";
		aDesc="A handful of silver scattered around";
		aGlory=1;
		aLevel=1;
		aUnlockLevel=0;
		aRandomPool=1;
		aSound=Sound.equip_coin_double;
		add();

		aName="Gold Pouch";
		aDesc="A pouch bulging with coins";
		aLevel=2;
		aGlory=2;
		aUnlockLevel=0;
		aRandomPool=1;
		aSound=Sound.equip_coin_bag;
		add();

		aName="Candlestick";
		aDesc="Does this match the set we have in the guild?";
		aLevel=3;
		aGlory=3;
		aUnlockLevel=0;
		aRandomPool=1;
		aSound=Sound.equip_staff;
		add();

		aName="Sapphire Ring";
		aDesc="Mounted with a huge sapphire, fit for a princess";
		aLevel=5;
		aGlory=5;
		aRandomPool=0;
		aSound=Sound.bling;
		add();

		aName="Massive Gem";
		aDesc="A gem as big as your head. Gotta have it!";
		aLevel=3;
		aGlory=5;
		aRandomPool=0;
		aUnlockLevel=0;
		aSound=Sound.bling;
		add();

		aName="Large Chest";
		aDesc="A heavy wooden chest. What could be inside?";
		aRandomPool=0;
		aSpawnCount=3;
		aLevel=2;
		aSound=Sound.equip_coin_chest;
		add();

		aName="MEGA CHEST";
		aFrameNumber--; //SPECIAL RULE, same sprite?//
		aDesc="Ohh! What's in the box?!";
		aRandomPool=0;
		aSpawnCount=3;
		aLevel=3;
		aSound=Sound.equip_coin_chest;
		add();

		aName="Skull Cap";
		aDesc="A simple leather cap. Might warm you up.";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=2;
		aEquipFrame=11;
		aUnlockLevel=1;
		aUnlock=UnlockedBy.Leather;
		aRandomPool=1;
		aKeepHair=2;
		aSound=Sound.equip_leather_helm;
		aSkills=new Skill[]{Skill.armour.asLevel(2)};
		aOffsetX=111; aOffsetY=68;
		add();

		aName="Horned Helm";
		aDesc="Spiky. Menacing. Stylish!";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=3;
		aEquipFrame=12;
		aUnlockLevel=2;
		aUnlock=UnlockedBy.Smith;
		aRandomPool=0;
		aKeepHair=2;
		aSound=Sound.equip_horned_helm;
		aSkills=new Skill[]{Skill.blade.asLevel(1)};
		aTraits=new Trait[]{Trait.Fury};
		aOffsetX=112; aOffsetY=68;
		add();

		aName="Winged Fury";
		aDesc="It gleams in the dark. Could it be magical?";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=3;
		aEquipFrame=13;
		aUnlockLevel=3;
		aUnlock=UnlockedBy.Smith;
		aRandomPool=0;
		aKeepHair=2;
		aSound=Sound.equip_winged_helm;
		aSkills=new Skill[]{Skill.holy.asLevel(1)};
		aTraits=new Trait[]{Trait.Retribution};
		aOffsetX=113; aOffsetY=89;
		add();

		aName="Scale Mail";
		aDesc="Layered scales. Works for dragons, right?";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=3;
		aEquipFrame=15;
		aUnlockLevel=1;
		aUnlock=UnlockedBy.Smith;
		aRandomPool=0;
		aSound=Sound.equip_scalemail;
		aSkills= new Skill[]{Skill.armour.asLevel(1)};
		aHealth=2;
		aOffsetX=115; aOffsetY=170;
		add();

		aName="Red Mail";
		aDesc="A shirt of linked rings. Seems comfortable";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=3;
		aEquipFrame=16;
		aUnlockLevel=0;
		aRandomPool=1;
		aSound=Sound.equip_chainmail;
		aSkills=new Skill[]{Skill.armour.asLevel(1)};
		aHealth=1;
		aOffsetX=114; aOffsetY=170;
		add();

		aName="Doom Plate";
		aDesc="Awkward, but looks like it will keep you safe";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=3;
		aEquipFrame=17;
		aUnlockLevel=2;
		aUnlock=UnlockedBy.Smith;
		aRandomPool=0;
		aSound=Sound.equip_platemail;
		aSkills=new Skill[]{Skill.armour.asLevel(1)};
		aTraits=new Trait[]{Trait.Bulwark};
		aOffsetX=115; aOffsetY=169;
		add();

		aName="Wooden Shield";
		aDesc="Better than nothing!";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=3;
		aEquipFrame=18;
		aUnlockLevel=0;
		aRandomPool=0;
		aSound=Sound.equip_wooden_shield;
		aSkills=new Skill[]{Skill.armour.asLevel(1)};
		aTraits=new Trait[]{Trait.Tenacious};
		aOffsetX=70; aOffsetY=175;
		add();

		aName="Duelling Buckler";
		aDesc="Buckles onto your arm, not under pressure!";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=3;
		aEquipFrame=19;
		aUnlockLevel=3;
		aUnlock=UnlockedBy.Leather;
		aRandomPool=1;
		aSound=Sound.equip_buckler;
		aSkills=new Skill[]{Skill.armour.asLevel(2), Skill.swift.asLevel(1)};
		aTraits=new Trait[]{Trait.Tenacious};
		aOffsetX=71; aOffsetY=175;
		add();

		aName="Heater Shield";
		aDesc="Doesn't give you fire magic unfortunately";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=3;
		aEquipFrame=20;
		aUnlockLevel=1;
		aUnlock=UnlockedBy.Wood;
		aRandomPool=0;
		aSound=Sound.equip_buckler;
		aSkills=new Skill[]{Skill.fire.asLevel(2)};
		aHealth=1;
		aOffsetX=75; aOffsetY=176;
		add();

		aName="Kite Shield";
		aDesc="A huge shield. Can you lift it?";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=5;
		aEquipFrame=21;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_buckler;
		aSkills=new Skill[]{Skill.armour.asLevel(3)};
		aOffsetX=77; aOffsetY=172;
		add();

		aName="Club";
		aDesc="A heavy wooden club. ME SMASH NOW!";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=2;
		aEquipFrame=22;
		aUnlockLevel=0;
		aRandomPool=1;
		aSound=Sound.equip_club;
		aSkills=new Skill[]{Skill.crush.asLevel(1), Skill.growth.asLevel(1)};
		aOffsetX=176; aOffsetY=140;
		add();

		aName="Mace";
		aDesc="Good for self-defence";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=3;
		aEquipFrame=23;
		aUnlockLevel=1;
		aUnlock=UnlockedBy.Smith;
		aRandomPool=1;
		aSound=Sound.equip_mace;
		aSkills=new Skill[]{Skill.crush.asLevel(2), Skill.blade.asLevel(1)};
		aOffsetX=181; aOffsetY=142;
		add();

		aName="Sword";
		aDesc="Simple. Effective. BORING";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=3;
		aEquipFrame=24;
		aUnlockLevel=0;
		aRandomPool=1;
		aSound=Sound.equip_sword;
		aSkills=new Skill[]{Skill.blade.asLevel(2)};
		aOffsetX=170; aOffsetY=146;
		add();

		aName="Battle Axe";
		aDesc="Time to CLEAVE them to pieces!";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=3;
		aEquipFrame=25;
		aUnlockLevel=3;
		aUnlock=UnlockedBy.Smith;
		aRandomPool=0;
		aSound=Sound.equip_axe;
		aSkills=new Skill[]{Skill.blade.asLevel(3), Skill.crush.asLevel(2)};
		aOffsetX=179; aOffsetY=143;
		add();

		aName="Elixir";
		aDesc="Good for all that ails ya!";
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_potion;
		add();

		aName="Health Potion";
		aDesc="Definitely not snake oil!";
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_potion;
		add();

		aName="Mage Robes";
		aDesc="It looks like a dress, but more wizardy";
		aEquipment=1;
		aType=EquipmentType.armour;
		aEquipFrame=30;
		aLevel=2;
		aUnlockLevel=1;
		aUnlock=UnlockedBy.Leather;
		aRandomPool=1;
		aSound=Sound.equip_robes;
		aSkills=new Skill[]{Skill.arcane.asLevel(1), Skill.fire.asLevel(1), Skill.holy.asLevel(1)};
		aOffsetX=114; aOffsetY=182;
		add();

		aName="Wizard's Hat";
		aDesc="Is there a rabbit inside?";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=3;
		aEquipFrame=32;
		aUnlockLevel=-5;
		aRandomPool=0;
		aKeepHair=2;
		aSound=Sound.equip_cloth_hat;
		aSkills=new Skill[]{Skill.arcane.asLevel(2), Skill.fire.asLevel(1)};
		aOffsetX=108; aOffsetY=50;
		add();

		aName="Feathered Cap";
		aDesc="Where are my merry Men?!";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=3;
		aEquipFrame=31;
		aUnlockLevel=0;
		aUnlock=UnlockedBy.Base;
		aRandomPool=0;
		aKeepHair=1;
		aSound=Sound.equip_cloth_hat;
		aSkills=new Skill[]{Skill.growth.asLevel(2)};
		aOffsetX=110; aOffsetY=41;
		add();

		aName="Stiletto";
		aDesc="And not the shoe";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=2;
		aEquipFrame=27;
		aUnlockLevel=0;
		aRandomPool=1;
		aSound=Sound.equip_knife;
		aSkills=new Skill[]{Skill.swift.asLevel(1), Skill.blade.asLevel(1)};
		aOffsetX=175; aOffsetY=162;
		add();

		aName="Cursed Bow";
		aDesc="Poison? Elves sure can be MEAN.";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=3;
		aEquipFrame=29;
		aUnlockLevel=2;
		aUnlock=UnlockedBy.Wood;
		aRandomPool=0;
		aSound=Sound.equip_bow;
		aSkills= new Skill[]{Skill.swift.asLevel(2)};
		aTraits=new Trait[]{Trait.Ranged, Trait.Meaty};
		aOffsetX=156; aOffsetY=168;
		add();

		aName="Mind Staff";
		aDesc="Tell me, what's on your mind?";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=3;
		aEquipFrame=28;
		aUnlockLevel=2;
		aUnlock=UnlockedBy.Wood;
		aRandomPool=0;
		aSound=Sound.equip_club;
		aSkills=new Skill[]{Skill.arcane.asLevel(1), Skill.fire.asLevel(1)};
		aTraits=new Trait[]{Trait.Wise};
		aOffsetX=176; aOffsetY=146;
		add();

		aName="Winged Staff";
		aDesc="It hums with power";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=3;
		aEquipFrame=34;
		aUnlockLevel=1;
		aUnlock=UnlockedBy.Curio;
		aRandomPool=0;
		aSound=Sound.equip_club;
		aSkills=new Skill[]{Skill.holy.asLevel(2), Skill.arcane.asLevel(1)};
		aOffsetX=176; aOffsetY=148;
		add();

		aName="Toxic Mace";
		aDesc="Nasty, nasty thing";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=3;
		aEquipFrame=35;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_mace;
		aSkills=new Skill[]{Skill.crush.asLevel(2), Skill.arcane.asLevel(1)};
		aOffsetX=180; aOffsetY=133;
		add();

		aName="Twig";
		aDesc="ALL FEAR THE MIGHTY TWIG";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=1;
		aEquipFrame=36;
		aUnlockLevel=0;
		aRandomPool=1;
		aSound=Sound.equip_club;
		aSkills=new Skill[]{Skill.crush.asLevel(1)};
		aOffsetX=175; aOffsetY=159;
		add();

		aName="Fork";
		aDesc="Is it time for second breakfast?";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=1;
		aEquipFrame=37;
		aUnlockLevel=0;
		aRandomPool=1;
		aSound=Sound.equip_sword;
		aSkills=new Skill[]{Skill.blade.asLevel(1)};
		aOffsetX=174; aOffsetY=165;
		add();

		aName="Sparkly Headband";
		aDesc="SPARKLES!!";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=2;
		aEquipFrame=38;
		aUnlockLevel=0;
		aRandomPool=1;
		aKeepHair=1;
		aSound=Sound.equip_leather_helm;
		aSkills=new Skill[]{Skill.holy.asLevel(1), Skill.fire.asLevel(1)};
		aOffsetX=109; aOffsetY=78;
		add();

		aName="Voodoo Mask";
		aDesc="Voodoo! Who do? You do!";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=2;
		aEquipFrame=39;
		aUnlockLevel=3;
		aUnlock=UnlockedBy.Wood;
		aRandomPool=1;
		aKeepHair=1;
		aSound=Sound.equip_leather_helm;
		aSkills=new Skill[]{Skill.holy.asLevel(2)};
		aHealth=1;
		aOffsetX=110; aOffsetY=89;
		add();

		aName="Paper Crown";
		aDesc="We having a party?";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=1;
		aEquipFrame=40;
		aUnlockLevel=0;
		aRandomPool=1;
		aKeepHair=1;
		aSound=Sound.equip_cloth_hat;
		aSkills=new Skill[]{Skill.holy.asLevel(1)};
		aOffsetX=90; aOffsetY=49;
		add();

		aName="Fire Shield";
		aDesc="A dull red glow surrounds it.";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=5;
		aEquipFrame=41;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_silver_shield;
		aSkills=new Skill[]{Skill.armour.asLevel(2), Skill.fire.asLevel(2)};
		aOffsetX=70; aOffsetY=179;
		add();

		aName="Eyeball Charm";
		aDesc="I spy with my little eye!";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=1;
		aEquipFrame=42;
		aUnlockLevel=2;
		aUnlock=UnlockedBy.Curio;
		aRandomPool=1;
		aSkills=new Skill[]{Skill.arcane.asLevel(2)};
		aSound=Sound.equip_great_shield;
		aOffsetX=67; aOffsetY=185;
		add();

		aName="Ruffled Shirt";
		aDesc="For the fancy adventurer";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=1;
		aEquipFrame=43;
		aUnlockLevel=0;
		aRandomPool=1;
		aSound=Sound.equip_cloth_hat;
		aSkills=new Skill[]{Skill.swift.asLevel(1)};
		aOffsetX=113; aOffsetY=168;
		add();

		aName="Straitjacket";
		aDesc="We're not crazy, the rest of the world is!";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=1;
		aEquipFrame=44;
		aUnlockLevel=0;
		aRandomPool=1;
		aSound=Sound.equip_leather_armour;
		aSkills=null;
		//aTraits=new Trait[]{Trait.Tenacious};
		aHealth=1;
		aOffsetX=110; aOffsetY=169;
		add();

		aName="Wolf Pelt";
		aDesc="Awooooo!";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=3;
		aEquipFrame=46;
		aEquipBackFrame=100;
		aUnlockLevel=1;
		aUnlock=UnlockedBy.Leather;
		aRandomPool=1;
		aSound=Sound.equip_leather_armour;
		aSkills=new Skill[]{Skill.growth.asLevel(2)};
		aHealth=1;
		aOffsetX=110; aOffsetY=169;
		add();

		aName="Dragon Scale";
		aDesc="So scratchy, how do they sleep in this?";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=3;
		aEquipFrame=47; 
		aUnlockLevel=3;
		aUnlock=UnlockedBy.Leather;
		aRandomPool=0;
		aSound=Sound.equip_chainmail;
		aSkills=new Skill[]{Skill.armour.asLevel(3), Skill.fire.asLevel(2)};
		aOffsetX=113; aOffsetY=170;
		add();

		aName="Tattered Mail";
		aDesc="Worn and comfortable";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=2;
		aEquipFrame=98; 
		aUnlockLevel=0;
		aRandomPool=1;
		aSound=Sound.equip_chainmail;
		aHealth=1;
		aSkills=new Skill[]{Skill.armour.asLevel(1), Skill.stupidity.asLevel(1)};
		aOffsetX=113; aOffsetY=169;
		//aHealth=1;
		add();

		aName="Shimmering Cloak";
		aDesc="Gleams like the night sky";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=2;
		aEquipFrame=48;
		aEquipBackFrame=99;
		aUnlockLevel=0;
		aRandomPool=1;
		aSound=Sound.equip_club;
		aSkills=new Skill[]{Skill.arcane.asLevel(1), Skill.fire.asLevel(1)};
		aOffsetX=113; aOffsetY=181;
		add();

		aName="Elven Plate";
		aDesc="Masterfully crafted";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=3;
		aEquipFrame=49;
		aUnlockLevel=3;
		aUnlock=UnlockedBy.Curio;
		aRandomPool=0;
		aSound=Sound.equip_chainmail;
		aSkills=new Skill[]{Skill.growth.asLevel(1)};
		aHealth=2;
		aOffsetX=112; aOffsetY=169;
		add();

		aName="Coat of Thorns";
		aDesc="Banned from hugs";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=3;
		aEquipFrame=50;
		aUnlockLevel=3;
		aUnlock=UnlockedBy.Wood;
		aRandomPool=0;
		aSound=Sound.equip_leather_armour;
		aSkills=new Skill[]{Skill.growth.asLevel(1), Skill.armour.asLevel(2)};
		aTraits=new Trait[]{Trait.Spikey};
		aOffsetX=113; aOffsetY=168;
		add();

		aName="Bone Armour";
		aDesc="Chilly";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=3;
		aEquipFrame=51;
		aUnlockLevel=0;
		aRandomPool=1;
		aSound=Sound.equip_leather_armour;
		aHealth=1;
		aSkills=new Skill[]{Skill.arcane.asLevel(1)};
		aOffsetX=113; aOffsetY=169;
		add();

		aName="Bark Vest";
		aDesc="Tree-hugger";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=1;
		aEquipFrame=52;
		aUnlockLevel=2;
		aUnlock=UnlockedBy.Wood;
		aRandomPool=0;
		aSound=Sound.equip_leather_armour;
		aSkills=new Skill[]{Skill.growth.asLevel(1), Skill.stupidity.asLevel(1)};
		aTraits=new Trait[]{Trait.Tenacious};
		aOffsetX=114; aOffsetY=170;
		add();

		aName="Padded Vest";
		aDesc="You feel safe";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=3;
		aEquipFrame=53;
		aUnlockLevel=2;
		aUnlock=UnlockedBy.Leather;
		aRandomPool=1;
		aSound=Sound.equip_club;
		aSkills= new Skill[]{Skill.armour.asLevel(1), Skill.arcane.asLevel(2)};
		aOffsetX=113; aOffsetY=169;
		add();

		aName="Wolf Hat";
		aDesc="Grrrr";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=2;
		aEquipFrame=45;
		aUnlockLevel=3;
		aUnlock=UnlockedBy.Leather;
		aRandomPool=0;
		aKeepHair=1;
		aSound=Sound.equip_leather_helm;
		aTraits=new Trait[]{Trait.Fury};
		aOffsetX=109; aOffsetY=48;
		add();

		aName="Mail Coif";
		aDesc="Too cold!";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=2;
		aEquipFrame=54;
		aUnlockLevel=0;
		aEquipBackFrame=101;
		aRandomPool=1;
		aSound=Sound.equip_winged_helm;
		aHealth=2;
		aSkills= new Skill[]{Skill.stupidity.asLevel(2)};
		aOffsetX=110; aOffsetY=100;
		add();

		aName="Spiky Hat";
		aDesc="Charge!";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=2;
		aEquipFrame=55;
		aUnlockLevel=2;
		aUnlock=UnlockedBy.Smith;
		aRandomPool=1;
		aKeepHair=2;
		aSound=Sound.equip_horned_helm;
		aSkills=new Skill[]{Skill.armour.asLevel(1), Skill.blade.asLevel(2)};
		aOffsetX=113; aOffsetY=61;
		add();

		aName="Soldier's Helmet";
		aDesc="Bog-standerd";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=1;
		aEquipFrame=56;
		aUnlockLevel=1;
		aUnlock=UnlockedBy.Smith;
		aRandomPool=0;
		aKeepHair=2;
		aSound=Sound.equip_horned_helm;
		aHealth=1;
		aOffsetX=111; aOffsetY=61;
		add();

		aName="Norse Helmet";
		aDesc="verja hqfdi";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=2;
		aEquipFrame=57;
		aUnlockLevel=-5;
		//aUnlock=UnlockedBy.Leather;
		aRandomPool=0;
		aKeepHair=2;
		aSound=Sound.equip_horned_helm;
		aSkills= new Skill[]{Skill.swift.asLevel(2), Skill.armour.asLevel(1)};
		aOffsetX=110; aOffsetY=88;
		add();

		aName="Elven Helmet";
		aDesc="The smart choice";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=2;
		aEquipFrame=58;
		aUnlockLevel=3;
		aUnlock=UnlockedBy.Curio;
		aRandomPool=0;
		aKeepHair=2;
		aSound=Sound.equip_horned_helm;
		aSkills= new Skill[]{Skill.growth.asLevel(2)};
		aHealth=1;
		aOffsetX=111; aOffsetY=54;
		add();

		aName="Cooking Pot";
		aDesc="Lunkhead";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=2;
		aEquipFrame=59;
		aUnlockLevel=0;
		aRandomPool=1;
		aKeepHair=2;
		aSound=Sound.equip_horned_helm;
		aSkills= new Skill[]{Skill.fire.asLevel(1), Skill.crush.asLevel(1)};
		aOffsetX=100; aOffsetY=48;
		add();

		aName="Fez";
		aDesc="Thank'y'ver'much!";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=2;
		aEquipFrame=60;
		aUnlockLevel=2;
		aUnlock=UnlockedBy.Leather;
		aRandomPool=0;
		aKeepHair=1;
		aSound=Sound.equip_cloth_hat;
		aSkills=new Skill[]{Skill.arcane.asLevel(2)};
		aTraits=new Trait[]{Trait.Wise};
		aOffsetX=73; aOffsetY=58;
		add();

		aName="Pigeon Nest";
		aDesc="Coo";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=1;
		aEquipFrame=61;
		aUnlockLevel=0;
		aRandomPool=0;
		aKeepHair=1;
		aSound=Sound.equip_cloth_hat;
		aSkills= new Skill[]{Skill.growth.asLevel(1)};
		aOffsetX=76; aOffsetY=52;
		add();

		aName="Daisy Chain";
		aDesc="How sweet";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=2;
		aEquipFrame=62;
		aUnlockLevel=1;
		aUnlock=UnlockedBy.Curio;
		aRandomPool=0;
		aKeepHair=1;
		aSound=Sound.equip_cloth_hat;
		aSkills= new Skill[]{Skill.growth.asLevel(1)};
		aHealth=1;
		aOffsetX=109; aOffsetY=80;
		add();

		aName="Masquerade Mask";
		aDesc="Mysterious";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=3;
		aEquipFrame=63;
		aUnlockLevel=1;
		aUnlock=UnlockedBy.Curio;
		aRandomPool=1;
		aKeepHair=1;
		aSound=Sound.equip_cloth_hat;
		aSkills= new Skill[]{Skill.arcane.asLevel(1),Skill.holy.asLevel(2)};
		aOffsetX=111; aOffsetY=87;
		add();

		aName="Cultist Hood";
		aDesc="Password?";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=2;
		aEquipFrame=64;
		aEquipBackFrame=102;
		aUnlockLevel=2;
		aUnlock=UnlockedBy.Curio;
		aRandomPool=1;
		aSound=Sound.equip_cloth_hat;
		aSkills=new Skill[]{Skill.holy.asLevel(2), Skill.fire.asLevel(1)};
		aOffsetX=112; aOffsetY=99;
		add();

		aName="Bone Helmet";
		aDesc="Makes you look scary!";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=3;
		aEquipFrame=65;
		aUnlockLevel=0;
		aRandomPool=0;
		aKeepHair=1;
		aSound=Sound.equip_cloth_hat;
		aSkills= new Skill[]{Skill.arcane.asLevel(2)};
		aOffsetX=112; aOffsetY=55;
		add();

		aName="Heavy Visor";
		aDesc="You can barely see!";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=4;
		aEquipFrame=66;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_horned_helm;
		aSkills= new Skill[]{Skill.armour.asLevel(2)};
		aOffsetX=112; aOffsetY=89;
		add();

		aName="Troll Femur";
		aDesc="Rattle rattle";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=1;
		aEquipFrame=67;
		aUnlockLevel=1;
		aUnlock=UnlockedBy.Curio;
		aRandomPool=0;
		aSound=Sound.equip_club;
		aSkills= new Skill[]{Skill.stupidity.asLevel(1), Skill.crush.asLevel(2), Skill.growth.asLevel(1)};
		aOffsetX=174; aOffsetY=160;
		add();

		aName="Rapier";
		aDesc="Slice slice stab";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=4;
		aEquipFrame=68;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_sword;
		aSkills= new Skill[]{Skill.blade.asLevel(1), Skill.swift.asLevel(2)};
		aOffsetX=178; aOffsetY=155;
		add();

		aName="Cutlass";
		aDesc="Good for cuttling";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=2;
		aEquipFrame=69;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_sword;
		aSkills= new Skill[]{Skill.blade.asLevel(1)};
		aTraits= new Trait[]{Trait.Ferocious};
		aOffsetX=179; aOffsetY=157;
		add();

		aName="Scimitar";
		aDesc="Stab slice slice";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=2;
		aEquipFrame=70;
		aUnlockLevel=1;
		aUnlock=UnlockedBy.Smith;
		aRandomPool=1;
		aSound=Sound.equip_sword;
		aSkills= new Skill[]{Skill.blade.asLevel(2)};
		aOffsetX=179; aOffsetY=157;
		add();

		aName="Cleaver";
		aDesc="The butcher needs one of these!";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=1;
		aUnlock=UnlockedBy.Smith;
		aEquipFrame=71;
		aUnlockLevel=3;
		aRandomPool=0;
		aSound=Sound.equip_axe;
		aSkills= new Skill[]{Skill.blade.asLevel(1), Skill.stupidity.asLevel(1)};
		aTraits=new Trait[]{Trait.Fury};
		aOffsetX=179; aOffsetY=164;
		add();

		aName="Demon Claw";
		aDesc="Still warm";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=1;
		aEquipFrame=72;
		aUnlockLevel=2;
		aUnlock=UnlockedBy.Curio;
		aRandomPool=0;
		aSound=Sound.equip_club;
		aSkills= new Skill[]{Skill.blade.asLevel(2)};
		aOffsetX=168; aOffsetY=187;
		add();

		aName="Brass Knuckles";
		aDesc="Fighting dirty";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=1;
		aEquipFrame=73;
		aUnlockLevel=2;
		aUnlock=UnlockedBy.Smith;
		aRandomPool=1;
		aSound=Sound.equip_sword;
		aSkills=new Skill[]{Skill.crush.asLevel(2)};
		aOffsetX=166; aOffsetY=171;
		add();
		
		aName="Hand-axe";
		aDesc="Handy!";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=1;
		aEquipFrame=74;
		aUnlockLevel=1;
		aRandomPool=1;
		aUnlock=UnlockedBy.Wood;
		aSound=Sound.equip_axe;
		aSkills= new Skill[]{Skill.blade.asLevel(1), Skill.crush.asLevel(1)};
		aOffsetX=182; aOffsetY=157;
		add();

		aName="Poison Dagger";
		aDesc="That's cheating!";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=4;
		aEquipFrame=75;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_sword;
		aSkills= new Skill[]{Skill.blade.asLevel(1), Skill.swift.asLevel(2)};
		aOffsetX=175; aOffsetY=166;
		add();

		aName="Arcane Wand";
		aDesc="Swoosh";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=1;
		aEquipFrame=76;
		aUnlockLevel=1;
		aUnlock=UnlockedBy.Wood;
		aRandomPool=0;
		aSound=Sound.equip_staff;
		aSkills= new Skill[]{Skill.growth.asLevel(1), Skill.arcane.asLevel(1)};
		aOffsetX=170; aOffsetY=170;
		add();

		aName="Crystal Wand";
		aDesc="Spikey!";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=4;
		aEquipFrame=77;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_staff;
		aSkills= new Skill[]{Skill.arcane.asLevel(2), Skill.blade.asLevel(1)};
		aOffsetX=170; aOffsetY=168;
		add();

		aName="Crossbow";
		aDesc="So satisfying";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=2;
		aEquipFrame=78;
		aUnlockLevel=1;
		aUnlock=UnlockedBy.Wood;
		aRandomPool=0;
		aSound=Sound.equip_bow;
		aSkills= new Skill[]{Skill.swift.asLevel(2)};
		aOffsetX=168; aOffsetY=185;
		add();

		aName="Gnarled Oak";
		aDesc="Creaks and groans";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=1;
		aEquipFrame=79;
		aUnlockLevel=3;
		aUnlock=UnlockedBy.Wood;
		aRandomPool=0;
		aSound=Sound.equip_staff;
		aSkills= new Skill[]{Skill.growth.asLevel(2), Skill.holy.asLevel(1)};
		aOffsetX=174; aOffsetY=149;
		add();

		aName="Halberd";
		aDesc="When a spear just isn't enough";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=4;
		aEquipFrame=80;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_staff;
		aSkills= new Skill[]{Skill.blade.asLevel(2), Skill.swift.asLevel(2)};
		aOffsetX=179; aOffsetY=146;
		add();

		aName="Spear";
		aDesc="Poke poke";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=3;
		aEquipFrame=81;
		aUnlockLevel=0;
		aRandomPool=0;
		aSound=Sound.equip_staff;
		aSkills= new Skill[]{Skill.swift.asLevel(2)};
		aOffsetX=174; aOffsetY=147;
		add();

		aName="Fire Staff";
		aDesc="Ow ow hot";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=4;
		aEquipFrame=82;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_staff;
		aSkills= new Skill[]{Skill.fire.asLevel(2), Skill.crush.asLevel(1)};
		aOffsetX=178; aOffsetY=144;
		add();

		aName="Trident";
		aDesc="Could eat a big lunch with this";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=2;
		aEquipFrame=83;
		aUnlockLevel=3;
		aUnlock=UnlockedBy.Wood;
		aRandomPool=0;
		aSound=Sound.equip_staff;
		aSkills= new Skill[]{Skill.blade.asLevel(3)};
		aOffsetX=176; aOffsetY=143;
		add();

		aName="Broadsword";
		aDesc="Don't have to aim much";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=2;
		aEquipFrame=84;
		aUnlockLevel=3;
		aUnlock=UnlockedBy.Smith;
		aRandomPool=0;
		aSound=Sound.equip_sword;
		aSkills= new Skill[]{Skill.crush.asLevel(1)};
		aTraits=new Trait[]{Trait.Ferocious};
		aOffsetX=176; aOffsetY=134;
		add();

		aName="Owl Familiar";
		aDesc="You recognise this one";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=3;
		aEquipFrame=85;
		aUnlockLevel=2;
		aUnlock=UnlockedBy.Curio;
		aRandomPool=0;
		aSound=Sound.equip_cloth_hat;
		aSkills= new Skill[]{Skill.holy.asLevel(2)};
		aTraits=new Trait[]{Trait.Wise};
		aOffsetX=62; aOffsetY=168;
		add();

		aName="Leather-bound Tome";
		aDesc="Smells like boots";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=1;
		aEquipFrame=86;
		aUnlockLevel=1;
		aUnlock=UnlockedBy.Leather;
		aRandomPool=0;
		aSound=Sound.equip_cloth_hat;
		aSkills=new Skill[]{Skill.fire.asLevel(1), Skill.arcane.asLevel(1)};
		aOffsetX=53; aOffsetY=183;
		add();

		aName="Glyph";
		aDesc="Probably means something cool!";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=2;
		aEquipFrame=87;
		aUnlockLevel=0;
		aRandomPool=1;
		aSound=Sound.equip_buckler;
		aSkills=new Skill[]{Skill.holy.asLevel(1), Skill.arcane.asLevel(1)};
		aOffsetX=59; aOffsetY=191;
		add();

		aName="Horseshoe";
		aDesc="Who leaves one shoe behind?";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=1;
		aEquipFrame=88;
		aUnlockLevel=3;
		aUnlock=UnlockedBy.Curio;
		aRandomPool=1;
		aSound=Sound.equip_buckler;
		aSkills=new Skill[]{Skill.holy.asLevel(1)};
		aHealth=1;
		aOffsetX=59; aOffsetY=184;
		add();

		aName="War Horn";
		aDesc="drengskapr";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=3;
		aEquipFrame=89;
		aUnlockLevel=0;
		aRandomPool=0;
		aSound=Sound.equip_buckler;
		aSkills= new Skill[]{Skill.holy.asLevel(1), Skill.growth.asLevel(1), Skill.fire.asLevel(1)};
		aOffsetX=62; aOffsetY=193;
		add();

		aName="Spiked Shield";
		aDesc="Just try and hit me!";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=3;
		aEquipFrame=90;
		aUnlockLevel=2;
		aUnlock=UnlockedBy.Wood;
		aRandomPool=0;
		aSound=Sound.equip_silver_shield;
		aSkills= new Skill[]{Skill.armour.asLevel(1), Skill.blade.asLevel(2)};
		aTraits= new Trait[]{Trait.Spikey};
		aOffsetX=74; aOffsetY=178;
		add();

		aName="Wooden Board";
		aDesc="This looks almost useful";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=1;
		aEquipFrame=91;
		aUnlockLevel=0;
		aRandomPool=1;
		aSound=Sound.equip_wooden_shield;
		aSkills=new Skill[]{Skill.armour.asLevel(1)};
		aOffsetX=72; aOffsetY=181;
		add();

		aName="Net";
		aDesc="Gotcha!";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=1;
		aEquipFrame=92;
		aUnlockLevel=3;
		aUnlock=UnlockedBy.Leather;
		aRandomPool=0;
		aSound=Sound.equip_cloth_hat;
		aSkills= new Skill[]{Skill.swift.asLevel(3), Skill.stupidity.asLevel(1)};
		aOffsetX=60; aOffsetY=197;
		add();

		aName="Swirling Orb";
		aDesc="Bubble bubble";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=2;
		aEquipFrame=93;
		aUnlockLevel=3;
		aUnlock=UnlockedBy.Curio;
		aRandomPool=1;
		aSound=Sound.equip_coin_single;
		aSkills=new Skill[]{Skill.fire.asLevel(2)};
		aHealth=1;
		aOffsetX=61; aOffsetY=177;
		add();

		aName="Dead Lizard Charm";
		aDesc="Someone told you these were magical!";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=1;
		aEquipFrame=94;
		aUnlockLevel=2;
		aUnlock=UnlockedBy.Leather;
		aRandomPool=1;
		aSound=Sound.equip_leather_helm;
		aSkills=new Skill[]{Skill.arcane.asLevel(2)};
		aOffsetX=69; aOffsetY=188;
		add();

		aName="Scroll of Souls";
		aDesc="Don't read it!";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=2;
		aEquipFrame=95;
		aUnlockLevel=2;
		aUnlock=UnlockedBy.Leather;
		aRandomPool=0;
		aSound=Sound.equip_cloth_hat;
		aSkills= new Skill[]{Skill.stupidity.asLevel(1), Skill.holy.asLevel(2), Skill.arcane.asLevel(2)};
		aOffsetX=67; aOffsetY=181;
		add();

		aName="Cuppa";
		aDesc="Oooh that's better";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=1;
		aEquipFrame=96;
		aUnlockLevel=0;
		aRandomPool=0;
		aSound=Sound.equip_club;
		aSkills= new Skill[]{Skill.fire.asLevel(1)};
		aOffsetX=52; aOffsetY=181;
		add();

		aName="Wooden Stool";
		aDesc="What's this supposed to be for?";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=1;
		aEquipFrame=97;
		aUnlockLevel=0;
		aRandomPool=0;
		aSound=Sound.equip_club;
		aSkills= new Skill[]{Skill.stupidity.asLevel(1), Skill.crush.asLevel(1), Skill.armour.asLevel(1)};
		aOffsetX=67; aOffsetY=178;
		add();

		aFrameNumber+=2*3; //fountains aren't items//
		
		aName="Orb of Nosiness";
		aDesc="Hot gossip!";
		aLevel=3;
		aGlory=3;
		aRandomPool=0;
		aSound=Sound.equip_staff;
		add();
		
		aName="Key";
		aDesc="A key item!";
		aLevel=3;
		aGlory=3;
		aRandomPool=0;
		aSound=Sound.equip_staff;
		add();
		
		aName="Gold Ore";
		aDesc="Kerching!";
		aLevel=3;
		aGlory=3;
		aRandomPool=0;
		aSound=Sound.equip_staff;
		add();
		
		aName="Golden Sun Idol";
		aDesc="Looks heavy";
		aLevel=3;
		aGlory=3;
		aRandomPool=0;
		aSound=Sound.equip_staff;
		add();
		
		aName="Stock certificates";
		aDesc="It's just paperwork?";
		aLevel=3;
		aGlory=3;
		aRandomPool=0;
		aSound=Sound.equip_staff;
		add();
		
		setupLevels();
	}

	private static void setupLevels() {
		for(int i=0;i<levels.length;i++){
			levels[i]= new ArrayList<Item>();
		}
		for(Item i:items){
			if(i.level<=0)continue;
			levels[i.level].add(i);
		}
		ArrayList<Item> sortedItems = new ArrayList<>();
		for(Item item:items){
			boolean added=false;
			for(int i=0;i<sortedItems.size();i++){
				Item indexItem=sortedItems.get(i);
				if(item.level<indexItem.level){
					added=true;
					sortedItems.add(i, item);
					break;
				}
			}
			if(!added)sortedItems.add(item);
		}
		items=sortedItems;
	}

	public static void add(){
		if(aName=="placeholder")return;

			items.add(new Item(
					aName, 
					aDesc, 
					aFrameNumber, 
					aEquipment, 
					aType, 
					aEquipFrame, aEquipBackFrame,
					aLevel,
					aUnlock==null?UnlockedBy.Base:aUnlock,
							aUnlockLevel,
							aGlory, 
							aSpawnCount, 
							aRandomPool, 
							aKeepHair,
							aOffsetX, aOffsetY,
							aSound, 
							aSkills, 
							aTraits, 
							aHealth));
		
		aFrameNumber++;
		reset();
	}

	private static void reset(){
		aName="";
		aDesc="";
		aEquipment=-1;
		aType=null;
		aEquipFrame=-1; aEquipBackFrame=-1;
		aLevel=-1;
		aUnlock=null;
		aUnlockLevel=-5;
		aGlory=-1;
		aRandomPool=-1;
		aHealth=0;
		aKeepHair=0;
		aSpawnCount=0;
		aOffsetX=0; aOffsetY=0;
		aSound=null;
		aSkills=null;
		aTraits=null;
	}

	public static String jsonAll(){
		String output = "{\n\"Treasures\":{";
		for(int index=0;index<items.size();index++){
			Item i=items.get(index);
			output+=i.toJson()+(items.size()>index+1?",":"");
		}
		output+="}\n}";
		return output;
	}

	public static void printItemTypeAmounts(){
		for(int i=1;i<levels.length;i++){
			System.out.println("Level "+(i)+": "+levels[i].size());
		}
		HashMap<EquipmentType, Integer> values = new HashMap<>();
		for(EquipmentType ex:EquipmentType.values()){
			values.put(ex, 0);
		}

		for(Item i:items){
			EquipmentType ttt = i.type;
			System.out.println(i.type);
			values.put(ttt, values.get(ttt)+1);
		}
		System.out.println(values);

	}

	public static void printLevel(int level){
		for(Item i:levels[level]){
			System.out.println(i);
		}
	}

	public static void analyseLevel(int level, boolean printAll, boolean onlyActive){
		System.out.println("-------------");
		System.out.println("Analysing level "+level);
		System.out.println("-------------");
		System.out.println();
		System.out.println("Equipment Slots");
		System.out.println();
		for(EquipmentType type:EquipmentType.values()){
			System.out.print(type+": ");
			System.out.println(countSlot(level, type, false, onlyActive));
			if(printAll){
				countSlot(level, type, printAll, onlyActive);
				System.out.println();
			}
		}
		System.out.println("-------------");
		System.out.println();
		System.out.println("Skills");
		System.out.println();
		for(SkillType type:SkillType.values()){
			System.out.print(type+": ");
			System.out.println(countSkill(level, type, false, onlyActive));
			if(printAll){
				countSkill(level, type, printAll, onlyActive);
				System.out.println();
			}
		}
		System.out.println("-------------");


	}

	public static int countSkill(int level, Skill.SkillType type, boolean printAll, boolean onlyActive){
		int result=0;



		for(Item i:levels[level]){
			if(i.skills==null)continue;
			if(onlyActive&&i.randomPool==0)continue;
			for(Skill ps:i.skills){
				if(ps.type==type){
					if(printAll)System.out.print("|");
					result+=ps.level;
					if(printAll)System.out.print(i+"|  ");
				}
			}
		}

		if(printAll&&result>0)System.out.println();
		return result;
	}

	public static int countSlot(int level, EquipmentType type, boolean printAll, boolean onlyActive){
		int result=0;
		for(Item i:levels[level]){
			if(onlyActive&&i.randomPool==0)continue;
			if(i.type==type){
				if(printAll)System.out.print("|");
				result++;
				if(printAll)System.out.print(i+"|  ");
			}
		}
		if(printAll&&result>0)System.out.println();
		return result;
	}

	String toJson(){
		String output="";
		output+="\""+name+"\" : {\n";
		output+="\"description\" : \""+description+"\",\n";
		output+="\"frameNumber\" : "+frameNumber+",\n";
		if(equipment>0){
			output+="\"isEquipment\" : "+equipment+",\n";
			
		}
		output+=Json.addKey("rarity", unlockLevel, true);
		if(type!=null) output+="\"equipType\" : \""+type+"\",\n";
		if(equipFrame>=0) output+="\"equipFrame\" : "+equipFrame+",\n";
		if(equipBackFrame>0) output += Json.addKey("equipBackFrame", equipBackFrame, true);
		if(level>0) output+="\"hope\" : "+level+",\n";
		if(glory>0) output+="\"glory\" : "+glory+",\n";
		if(spawnCount>0) output+="\"spawnCount\" : "+spawnCount+",\n";
		output+="\"RandomPool\" : "+0+",\n";
		//if(unlockedBy!=null)output+=Json.addKey("UnlockedBy", unlockedBy.toString(), true);
		//output+=Json.addKey("UnlockLevel", unlockLevel, true);
		if(keepHair>0) output+="\"keepHair\" : "+keepHair+",\n";
		if(sound!=null) output+="\"sound\" : \""+sound+"\",\n";
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
		if(health>0) output+=Json.addKey("bonusHP", health, true);
		if(offsetX!=0){
			output+=Json.addKey("x", offsetX, true);
			output+=Json.addKey("y", offsetY, true);
		}
		output+="}";
		StringBuilder sb = new StringBuilder(output);
		sb.deleteCharAt(sb.lastIndexOf(","));

		return sb.toString();
	}

	static int length = 35;
	public String toString(){
		String result ="\n/";
		for(int i=0;i<length-1;i++){
			result += "-";
		}
		result +="\\\n";
		String line = "|"+name;

		result +=	finishLine(line);
		line="|Level "+level+", Tier "+unlockLevel;

		result +=finishLine(line);;
		if(skills!=null){
			line= "|Skills: ";
			for(int i=0;i<skills.length;i++){
				Skill s = skills[i];
				line += s.toString();
				if(i+1<skills.length)line += ", ";
			}
			result+= finishLine(line);
		}
		if(traits!=null){
			line = "|Traits: ";
			for(int i=0;i<traits.length;i++){
				Trait t = traits[0];
				line += t.toString();
				if(i+1<traits.length)line += ", ";
			}
			result+= finishLine(line);
		}
		if(health!=0){
			line = "|Bonus Health: "+health;
			result +=finishLine(line);
		}
		result+="\\";
		for(int i=0;i<length-1;i++){
			result += "-";
		}
		result +="/";

		return result;
	}

	private String finishLine(String line) {
		while(line.length()<length){
			line+=" ";
		}
		line+="|\n";
		return line;
	}

	public static String analyseTier(int tier) {
		String result = "Tier "+tier+"\n";


		ArrayList<Item> tierItems = new ArrayList<>();
		for(Item i:items){
			if(i.unlockLevel==tier){
				tierItems.add(i);
			}
		}
		result += "Total items: "+tierItems.size()+"\n";

		HashMap<SkillType, Integer> skillMap = new HashMap<>();
		for(SkillType playerSkill:Skill.playerSkills){
			skillMap.put(playerSkill, 0);
		}
		for(Item i:tierItems){
			if(i.skills==null)continue;
			for(Skill sk:i.skills){

				skillMap.put(sk.type, skillMap.get(sk.type)+sk.level);
			}
		}
		result += tierItems+"\n";
		result += skillMap.toString();

		return result;
	}

	public static String analyseUnlock(UnlockedBy unlockType) {
		ArrayList<Item> tierItems = new ArrayList<>();
		for(Item i:items){
			if(i.unlockedBy!=unlockType)continue;
			boolean added=false;
			for(int index=0;index<tierItems.size();index++){
				Item indexItem=tierItems.get(index);
				if(i.unlockLevel<indexItem.unlockLevel){
					added=true;
					tierItems.add(index, i);
					break;
				}
			}
			if(!added) tierItems.add(i);
		}
		String result = unlockType+" items:\n";

		for(Item i: tierItems){
			result+=i.toString()+"\n";
		}

		return result;
	}

	public static void printAllUnlocks() {
		for(UnlockedBy ulb:UnlockedBy.values()){
			for(int level=0;level<=3;level++){
				System.out.println(ulb+": "+level);
				System.out.println("[");
				for(int i=0;i<items.size();i++){
					Item item = items.get(i);
					if(item.unlockedBy==ulb&&item.unlockLevel==level){
						System.out.println("\""+item.name+"\""+ (i<items.size()-1?",":""));
					}
				}
				System.out.println("]");
			}
		}
	}
}
