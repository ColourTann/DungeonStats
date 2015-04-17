package item;

import java.awt.event.TextEvent;
import java.util.ArrayList;
import java.util.HashMap;

import json.Json;
import cards.Skill;
import cards.Skill.SkillType;
import fighter.Fighter.Trait;

public class Item {

	public enum UnlockedBy{Base, Leather, Smith, Curio, Wood}

	static ArrayList<Item> items = new ArrayList<Item>();
	static ArrayList<Item>[] levels = new ArrayList[6];
	enum EquipmentType{armour, shield, helmet, weapon, other};
	enum Sound{equip_coin_single, equip_coin_double, equip_robes, equip_coin_bag, equip_staff, bling, equip_coin_chest, equip_leather_helm, equip_horned_helm, equip_winged_helm, equip_scalemail, equip_chainmail, equip_platemail, equip_wooden_shield, equip_buckler, equip_club, equip_mace, equip_axe, equip_potion, equip_cloth_hat, equip_knife, equip_bow, equip_sword, equip_silver_shield, equip_great_shield, equip_leather_armour};
	public String name;
	String description;
	int frameNumber;
	int equipment;
	EquipmentType type;
	int equipFrame;
	int level;
	UnlockedBy unlockedBy;
	int unlockLevel;
	int glory;
	int spawnCount;
	int randomPool;
	int keepHair;
	Sound sound;
	Trait[] traits;
	Skill[] skills;
	int health;

	public Item(String name, 
			String description, 
			int frameNumber, 
			int equipment, 
			EquipmentType type,
			int equipFrame,
			int level,
			UnlockedBy unlockedBy,
			int unlockLevel,
			int glory,
			int spawnCount,
			int randomPool,
			int keepHair,
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
		this.equipFrame=equipFrame;
		this.level=level;
		this.unlockedBy=unlockedBy;
		this.unlockLevel=unlockLevel;
		this.glory=glory;
		this.spawnCount=spawnCount;
		this.randomPool=randomPool;
		this.keepHair=keepHair;
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
	static int aLevel;
	static UnlockedBy aUnlock;
	static int aUnlockLevel;
	static int aGlory;
	static int aSpawnCount;
	static int aRandomPool;
	static int aKeepHair;
	static int aHealth;
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
		aRandomPool=1;
		aSound=Sound.equip_coin_single;
		add();

		aName="Silver Coins";
		aDesc="A handful of silver scattered around";
		aGlory=2;
		aLevel=2;
		aRandomPool=1;
		aSound=Sound.equip_coin_double;
		add();

		aName="Gold Pouch";
		aDesc="A pouch bulging with coins";
		aLevel=3;
		aGlory=3;
		aRandomPool=1;
		aSound=Sound.equip_coin_bag;
		add();

		aName="Candlstick";
		aDesc="Does this match the set we have in the guild?";
		aLevel=4;
		aGlory=4;
		aRandomPool=0;
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
		aLevel=5;
		aGlory=6;
		aRandomPool=0;
		aSound=Sound.bling;
		add();

		aName="Large Chest";
		aDesc="A heavy wooden chest. What could be inside?";
		aRandomPool=0;
		aLevel=5;
		aSound=Sound.equip_coin_chest;
		add();

		aName="MEGA CHEST";
		aFrameNumber--; //SPECIAL RULE, same sprite?//
		aDesc="Ohh! What's in the box?!";
		aRandomPool=0;
		aSpawnCount=3;
		aLevel=5;
		aSound=Sound.equip_coin_chest;
		add();

		aName="Skull Cap";
		aDesc="A simple leather cap. Might warm you up.";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=3;
		aEquipFrame=11;
		aUnlockLevel=0;
		aRandomPool=1;
		aSound=Sound.equip_leather_helm;
		aSkills=new Skill[]{Skill.armour.asLevel(2)};
		add();

		aName="Horned Helm";
		aDesc="Spiky. Menacing. Stylish!";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=5;
		aEquipFrame=12;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_horned_helm;
		aSkills=new Skill[]{Skill.armour.asLevel(2), Skill.crush.asLevel(2)};
		add();

		aName="Winged Fury";
		aDesc="It gleams in the dark. Could it be magical?";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=5;
		aEquipFrame=13;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_winged_helm;
		aSkills=new Skill[]{Skill.armour.asLevel(2), Skill.swift.asLevel(2)};
		add();

		aName="Scale Mail";
		aDesc="Layered scales. Works for dragons, right?";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=4;
		aEquipFrame=15;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_scalemail;
		aSkills=new Skill[]{Skill.armour.asLevel(1)};
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
		add();

		aName="Doom Plate";
		aDesc="Awkward, but looks like it will keep you safe";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=5;
		aEquipFrame=17;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_platemail;
		aSkills=new Skill[]{Skill.armour.asLevel(3)};
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
		aSkills=new Skill[]{Skill.armour.asLevel(2)};
		add();

		aName="Duelling Buckler";
		aDesc="Buckles onto your arm, not under pressure!";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=3;
		aEquipFrame=19;
		aUnlockLevel=-5;
		aRandomPool=1;
		aSound=Sound.equip_buckler;
		aSkills=new Skill[]{Skill.armour.asLevel(1)};
		aTraits=new Trait[]{Trait.Tenacious};
		add();

		aName="Heater Shield";
		aDesc="Doesn't give you fire magic unfortunately";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=4;
		aEquipFrame=20;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_buckler;
		aSkills=new Skill[]{Skill.armour.asLevel(2)};
		aTraits=new Trait[]{Trait.Tenacious};
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
		add();

		aName="Mace";
		aDesc="Good for self-defence";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=3;
		aEquipFrame=23;
		aUnlockLevel=-5;
		aRandomPool=1;
		aSound=Sound.equip_mace;
		aSkills=new Skill[]{Skill.crush.asLevel(2)};
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
		add();

		aName="Battle Axe";
		aDesc="Time to CLEAVE them to pieces!";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=5;
		aEquipFrame=25;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_axe;
		aSkills=new Skill[]{Skill.blade.asLevel(2), Skill.crush.asLevel(2)};
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
		aLevel=3;
		aUnlockLevel=0;
		aRandomPool=1;
		aSound=Sound.equip_robes;
		aSkills=new Skill[]{Skill.arcane.asLevel(1), Skill.growth.asLevel(1), Skill.fire.asLevel(1)};
		add();

		aName="Wizard's Hat";
		aDesc="Is there a rabbit inside?";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=3;
		aEquipFrame=32;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_cloth_hat;
		aSkills=new Skill[]{Skill.arcane.asLevel(2), Skill.fire.asLevel(1)};
		add();

		aName="Feathered Cap";
		aDesc="Where are my merry Men?!";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=4;
		aEquipFrame=31;
		aUnlockLevel=-5;
		aRandomPool=0;
		aKeepHair=1;
		aSound=Sound.equip_cloth_hat;
		aSkills=new Skill[]{Skill.swift.asLevel(2)};
		aTraits=new Trait[]{Trait.Tenacious};
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
		add();

		aName="Green Bow";
		aDesc="Poison? Elves sure can be MEAN.";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=3;
		aEquipFrame=29;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_bow;
		aSkills=new Skill[]{Skill.swift.asLevel(2), Skill.blade.asLevel(1)};
		add();

		aName="Mind Staff";
		aDesc="Tell me, what's on your mind?";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=5;
		aEquipFrame=28;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_club;
		aSkills=new Skill[]{Skill.arcane.asLevel(3)};
		add();

		aName="Winged Staff";
		aDesc="It hums with power";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=4;
		aEquipFrame=34;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_club;
		aSkills=new Skill[]{Skill.crush.asLevel(1), Skill.arcane.asLevel(2)};
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
		aSkills=new Skill[]{Skill.holy.asLevel(1), Skill.swift.asLevel(1)};
		add();

		aName="Voodoo Mask";
		aDesc="Voodoo! Who do? You do!";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=2;
		aEquipFrame=39;
		aUnlockLevel=-5;
		aRandomPool=1;
		aKeepHair=1;
		aSound=Sound.equip_leather_helm;
		aSkills=new Skill[]{Skill.fire.asLevel(1), Skill.holy.asLevel(1)};
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
		add();

		aName="Fire Shield";
		aDesc="A dull red glow surrounds it.";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=5;
		aEquipFrame=36;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_silver_shield;
		aSkills=new Skill[]{Skill.armour.asLevel(2), Skill.fire.asLevel(2)};
		add();

		aName="Eyeball Charm";
		aDesc="I spy with my little eye!";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=1;
		aEquipFrame=42;
		aUnlockLevel=-5;
		aRandomPool=1;
		aSound=Sound.equip_great_shield;
		aSkills=new Skill[]{Skill.arcane.asLevel(1), Skill.holy.asLevel(1)};
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
		aTraits=new Trait[]{Trait.Tenacious};
		add();

		aName="Wolf Pelt";
		aDesc="Awooooo!";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=3;
		aEquipFrame=46;
		aUnlockLevel=-5;
		aRandomPool=1;
		aSound=Sound.equip_leather_armour;
		aSkills=new Skill[]{Skill.swift.asLevel(2)};
		add();

		aName="Dragon Scale";
		aDesc="So scratchy, how do they sleep in this?";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=5;
		aEquipFrame=47; 
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_chainmail;
		aSkills=new Skill[]{Skill.armour.asLevel(3)};
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
		//aSkills=new Skill[]{Skill.armour.asLevel(1), Skill.crush.asLevel(1)};
		aHealth=1;
		add();

		aName="Shimmering Cloak";
		aDesc="Gleams like the night sky";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=2;
		aEquipFrame=48;
		aUnlockLevel=0;
		aRandomPool=1;
		aSound=Sound.equip_club;
		aSkills=new Skill[]{Skill.arcane.asLevel(1), Skill.swift.asLevel(1)};
		add();

		aName="Elven Plate";
		aDesc="Masterfully crafted";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=5;
		aEquipFrame=49;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_chainmail;
		aSkills=new Skill[]{Skill.armour.asLevel(2), Skill.swift.asLevel(1)};
		add();

		aName="Coat of Thorns";
		aDesc="Banned from hugs";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=4;
		aEquipFrame=50;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_leather_armour;
		aSkills=new Skill[]{Skill.growth.asLevel(1), Skill.armour.asLevel(2)};
		add();

		aName="Bone Armour";
		aDesc="Chilly";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=3;
		aEquipFrame=51;
		aUnlockLevel=-5;
		aRandomPool=1;
		aSound=Sound.equip_leather_armour;
		aSkills=new Skill[]{Skill.arcane.asLevel(2), Skill.armour.asLevel(1)};
		add();

		aName="Bark Vest";
		aDesc="Tree-hugger";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=2;
		aEquipFrame=52;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_leather_armour;
		aSkills=new Skill[]{Skill.growth.asLevel(1), Skill.armour.asLevel(1)};
		add();

		aName="Padded Vest";
		aDesc="You feel safe";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=2;
		aEquipFrame=53;
		aUnlockLevel=-5;
		aRandomPool=1;
		aSound=Sound.equip_club;
		aSkills= new Skill[]{Skill.armour.asLevel(1), Skill.arcane.asLevel(1)};
		//aHealth=1;
		add();

		aName="Wolf Hat";
		aDesc="Grrrr";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=3;
		aEquipFrame=45;
		aUnlockLevel=-5;
		aRandomPool=0;
		aKeepHair=1;
		aSound=Sound.equip_leather_helm;
		aSkills= new Skill[]{Skill.growth.asLevel(2)};
		add();

		aName="Mail Coif";
		aDesc="Too cold!";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=2;
		aEquipFrame=54;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_winged_helm;
		aSkills= new Skill[]{Skill.armour.asLevel(2)};
		add();

		aName="Spikey Hat";
		aDesc="Charge!";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=2;
		aEquipFrame=55;
		aUnlockLevel=-5;
		aRandomPool=1;
		aSound=Sound.equip_horned_helm;
		aSkills=new Skill[]{Skill.blade.asLevel(2)};
		add();

		aName="Solider's Helmet";
		aDesc="Bog-standerd";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=3;
		aEquipFrame=56;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_horned_helm;
		aSkills= new Skill[]{Skill.armour.asLevel(2)};
		add();

		aName="Norse Helmet";
		aDesc="verja hqfdi";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=4;
		aEquipFrame=57;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_horned_helm;
		aSkills= new Skill[]{Skill.swift.asLevel(1), Skill.armour.asLevel(2)};
		add();

		aName="Elven Helmet";
		aDesc="The smart choice";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=5;
		aEquipFrame=58;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_horned_helm;
		aSkills= new Skill[]{Skill.armour.asLevel(2), Skill.growth.asLevel(2)};
		add();

		aName="Cooking Pot";
		aDesc="Lunkhead";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=2;
		aEquipFrame=59;
		aUnlockLevel=0;
		aRandomPool=1;
		aSound=Sound.equip_horned_helm;
		aSkills= new Skill[]{Skill.fire.asLevel(1), Skill.crush.asLevel(1)};
		add();

		aName="Fez";
		aDesc="Thank'y'ver'much!";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=1;
		aEquipFrame=60;
		aUnlockLevel=-5;
		aRandomPool=0;
		aKeepHair=1;
		aSound=Sound.equip_cloth_hat;
		aSkills=new Skill[]{Skill.arcane.asLevel(1), Skill.fire.asLevel(1)};
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
		add();

		aName="Daisy Chain";
		aDesc="How sweet";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=2;
		aEquipFrame=62;
		aUnlockLevel=-5;
		aRandomPool=0;
		aKeepHair=1;
		aSound=Sound.equip_cloth_hat;
		aSkills= new Skill[]{Skill.growth.asLevel(1)};
		add();

		aName="Masquerade Mask";
		aDesc="Mysterious";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=1;
		aEquipFrame=63;
		aUnlockLevel=-5;
		aRandomPool=1;
		aKeepHair=1;
		aSound=Sound.equip_cloth_hat;
		aSkills= new Skill[]{Skill.swift.asLevel(1), Skill.arcane.asLevel(1)};
		add();

		aName="Cultist Hood";
		aDesc="Password?";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=3;
		aEquipFrame=64;
		aUnlockLevel=-5;
		aRandomPool=1;
		aSound=Sound.equip_cloth_hat;
		aSkills=new Skill[]{Skill.holy.asLevel(2)};
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
		aSkills= new Skill[]{Skill.fire.asLevel(1), Skill.arcane.asLevel(1)};
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
		add();

		aName="Femur";
		aDesc="Rattle rattle";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=1;
		aEquipFrame=67;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_club;
		aSkills= new Skill[]{Skill.crush.asLevel(1), Skill.growth.asLevel(1)};
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
		add();

		aName="Cutlass";
		aDesc="Good for cuttling";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=4;
		aEquipFrame=69;
		aUnlockLevel=-5;
		aRandomPool=1;
		aSound=Sound.equip_sword;
		aSkills= new Skill[]{Skill.swift.asLevel(1), Skill.blade.asLevel(2)};
		add();

		aName="Scimitar";
		aDesc="Stab slice slice";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=4;
		aEquipFrame=70;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_sword;
		aSkills= new Skill[]{Skill.armour.asLevel(1), Skill.blade.asLevel(2)};
		add();

		aName="Cleaver";
		aDesc="The butcher needs one of these!";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=3;
		aEquipFrame=71;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_axe;
		aSkills= new Skill[]{Skill.blade.asLevel(2)};
		add();

		aName="Demon Claw";
		aDesc="Still warm";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=5;
		aEquipFrame=72;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_club;
		aSkills= new Skill[]{Skill.blade.asLevel(2), Skill.crush.asLevel(2)};
		add();

		aName="Brass Knuckles";
		aDesc="Fighting dirty";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=1;
		aEquipFrame=73;
		aUnlockLevel=-5;
		aRandomPool=1;
		aSound=Sound.equip_sword;
		aSkills=new Skill[]{Skill.swift.asLevel(1), Skill.crush.asLevel(1)};
		add();

		aName="Hand-axe";
		aDesc="Handy!";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=2;
		aEquipFrame=74;
		aUnlockLevel=-5;
		aRandomPool=1;
		aSound=Sound.equip_axe;
		aSkills= new Skill[]{Skill.blade.asLevel(2)};
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
		add();

		aName="Arcane Wand";
		aDesc="Swoosh";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=3;
		aEquipFrame=76;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_staff;
		aSkills= new Skill[]{Skill.arcane.asLevel(2)};
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
		add();

		aName="Crossbow";
		aDesc="So satisfying";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=2;
		aEquipFrame=78;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_bow;
		aSkills= new Skill[]{Skill.swift.asLevel(2)};
		add();

		aName="Gnarled Oak";
		aDesc="Creaks and groans";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=2;
		aEquipFrame=79;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_staff;
		aSkills= new Skill[]{Skill.growth.asLevel(2)};
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
		aSkills= new Skill[]{Skill.blade.asLevel(2), Skill.armour.asLevel(1)};
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
		add();

		aName="Trident";
		aDesc="Could eat a big lunch with this";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=5;
		aEquipFrame=83;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_staff;
		aSkills= new Skill[]{Skill.arcane.asLevel(1), Skill.blade.asLevel(2)};
		add();

		aName="Broadsword";
		aDesc="Don't have to aim much";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=4;
		aEquipFrame=84;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_sword;
		aSkills= new Skill[]{Skill.crush.asLevel(1), Skill.blade.asLevel(2)};
		add();

		aName="Owl Familiar";
		aDesc="You recognise this one";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=5;
		aEquipFrame=85;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_cloth_hat;
		aSkills= new Skill[]{Skill.arcane.asLevel(2), Skill.holy.asLevel(2)};
		add();

		aName="Dusty Tome";
		aDesc="The pages shine";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=5;
		aEquipFrame=86;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_cloth_hat;
		aSkills= new Skill[]{Skill.holy.asLevel(2), Skill.crush.asLevel(1)};
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
		add();

		aName="Horseshoe";
		aDesc="Who leaves one shoe behind?";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=2;
		aEquipFrame=88;
		aUnlockLevel=-5;
		aRandomPool=1;
		aSound=Sound.equip_buckler;
		aSkills=new Skill[]{Skill.arcane.asLevel(1), Skill.holy.asLevel(1)};
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
		add();

		aName="Spiked Shield";
		aDesc="Just try and hit me!";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=5;
		aEquipFrame=90;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_silver_shield;
		aSkills= new Skill[]{Skill.armour.asLevel(2), Skill.blade.asLevel(1)};
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
		aSkills=new Skill[]{Skill.armour.asLevel(1), Skill.crush.asLevel(1)};
		add();

		aName="Net";
		aDesc="Gotcha!";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=2;
		aEquipFrame=92;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_cloth_hat;
		aSkills= new Skill[]{Skill.swift.asLevel(1)};
		add();

		aName="Swirling Orb";
		aDesc="Bubble bubble";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=3;
		aEquipFrame=93;
		aUnlockLevel=-5;
		aRandomPool=1;
		aSound=Sound.equip_coin_single;
		aSkills=new Skill[]{Skill.fire.asLevel(2)};
		add();

		aName="Dead Lizard Charm";
		aDesc="Someone told you these were magical!";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=1;
		aEquipFrame=94;
		aUnlockLevel=-5;
		aRandomPool=1;
		aSound=Sound.equip_leather_helm;
		aSkills=new Skill[]{Skill.arcane.asLevel(1)};
		add();

		aName="Scroll of Souls";
		aDesc="Don't read it!";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=2;
		aEquipFrame=95;
		aUnlockLevel=-5;
		aRandomPool=0;
		aSound=Sound.equip_cloth_hat;
		aSkills= new Skill[]{Skill.arcane.asLevel(1), Skill.holy.asLevel(1)};
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
		add();

		aName="Wooden Stool";
		aDesc="What's this supposed to be for?";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=1;
		aEquipFrame=97;
		aUnlockLevel=0;
		aRandomPool=0;
		aSound=Sound.equip_club;
		aSkills= new Skill[]{Skill.crush.asLevel(1)};
		add();








		//		aName="placeholder";
		//		aDesc="";
		//		aEquipment=1;
		//		aType=EquipmentType.weapon;
		//		aLevel=1;
		//		aEquipFrame=36;
		//		aRandomPool=0;
		//		aSound=Sound.equip_club;
		//		aSkills=null;
		//		add();

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
	}

	public static void add(){
		if(aName=="placeholder")return;
		items.add(new Item(
				aName, 
				aDesc, 
				aFrameNumber, 
				aEquipment, 
				aType, 
				aEquipFrame, 
				aLevel,
				aUnlock,
				aUnlockLevel,
				aGlory, 
				aSpawnCount, 
				aRandomPool, 
				aKeepHair, 
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
		aEquipFrame=-1;
		aLevel=-1;
		aUnlock=null;
		aUnlockLevel=-5;
		aGlory=-1;
		aRandomPool=-1;
		aHealth=0;
		aKeepHair=0;
		aSpawnCount=0;
		aSound=null;
		aSkills=null;
		aTraits=null;
	}

	public static void printAll(){
		System.out.println("{\n\"Treasures\":{");
		for(int index=0;index<items.size();index++){
			Item i=items.get(index);
			System.out.println(i.toJson()+(items.size()>index+1?",":""));
		}
		System.out.println("}");
		System.out.println("}");
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
		if(equipment>0) output+="\"isEquipment\" : "+equipment+",\n";
		if(type!=null) output+="\"equipType\" : \""+type+"\",\n";
		if(equipFrame>=0) output+="\"equipFrame\" : "+equipFrame+",\n";
		if(level>0) output+="\"hope\" : "+level+",\n";
		if(glory>0) output+="\"glory\" : "+glory+",\n";
		if(spawnCount>0) output+="\"spawnCount\" : "+spawnCount+",\n";
		//if(randomPool>0) output+="\"RandomPool\" : "+randomPool+",\n";
		output+="\"RandomPool\" : "+0+",\n";
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
		if(health>0) output+=Json.addKey("bonusHP", health);

		output+="}";
		StringBuilder sb = new StringBuilder(output);
		sb.deleteCharAt(sb.lastIndexOf(","));

		return sb.toString();
	}

	public String toString(){
		String result = name;
		if(skills!=null){
			result += " (";
			for(Skill s:skills){
				result += s.toString();
				result += ", ";
			}
			result = result.substring(0, result.length()-2);
			result +=")";
		}
		if(traits!=null){
			result += " (";
			for(Trait t:traits){
				result += t.toString();
				result += ", ";
			}
			result = result.substring(0, result.length()-2);
			result +=")";
		}
		return result;
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
}
