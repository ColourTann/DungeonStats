package item;

import java.awt.event.TextEvent;
import java.util.ArrayList;

import cards.MonsteSkill;
import fighter.Fighter.Trait;
import fighter.player.Skill;
import fighter.player.Skill.SkillType;

public class Item {
	static ArrayList<Item> items = new ArrayList<Item>();
	static ArrayList<Item>[] levels = new ArrayList[6];
	enum EquipmentType{armour, shield, helmet, weapon};
	enum Sound{equip_coin_single, equip_coin_double, equip_robes, equip_coin_bag, equip_staff, bling, equip_coin_chest, equip_leather_helm, equip_horned_helm, equip_winged_helm, equip_scalemail, equip_chainmail, equip_platemail, equip_wooden_shield, equip_buckler, equip_club, equip_mace, equip_axe, equip_potion, equip_cloth_hat, equip_knife, equip_bow, equip_sword, equip_silver_shield, equip_great_shield, equip_leather_armour};
	String name;
	String description;
	int frameNumber;
	int equipment;
	EquipmentType type;
	int equipFrame;
	int level;
	int glory;
	int randomPool;
	int keepHair;
	Sound sound;
	Trait[] traits;
	Skill[] skills;

	public Item(String name, 
			String description, 
			int frameNumber, 
			int equipment, 
			EquipmentType type,
			int equipFrame,
			int level,
			int glory,
			int randomPool,
			int keepHair,
			Sound sound,
			Skill[] skills,
			Trait[] traits
			){
		this.name=name;
		this.description=description;
		this.frameNumber=frameNumber;
		this.equipment=equipment;
		this.type=type;
		this.equipFrame=equipFrame;
		this.level=level;
		this.glory=glory;
		this.randomPool=randomPool;
		this.keepHair=keepHair;
		this.sound=sound;
		this.skills=skills;
		this.traits=traits;


	}
	static String aName;
	static String aDesc;
	static int aFrameNumber;
	static int aEquipment;
	static EquipmentType aType;
	static int aEquipFrame;
	static int aLevel;
	static int aGlory;
	static int aRandomPool;
	static int aKeepHair;
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
		aRandomPool=0;
		aSound=Sound.equip_coin_single;
		add();

		aName="Silver Coins";
		aDesc="A handful of silver scattered around";
		aGlory=2;
		aLevel=2;
		aRandomPool=0;
		aSound=Sound.equip_coin_double;
		add();

		aName="Gold Pouch";
		aDesc="A pouch bulging with coins";
		aLevel=3;
		aGlory=3;
		aRandomPool=0;
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
		aFrameNumber--; //SPECIAL RULE, same sprite?//
		aDesc="A heavy wooden chest. What could be inside?";
		aRandomPool=0;
		aLevel=5;
		aSound=Sound.equip_coin_chest;
		add();

		aName="MEGA CHEST";
		aDesc="Ohh! What's in the box?!";
		aRandomPool=0;
		aLevel=5;
		aSound=Sound.equip_coin_chest;
		add();

		aName="Skull Cap";
		aDesc="A simple leather cap. Might warm you up.";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=3;
		aEquipFrame=11;
		aRandomPool=0;
		aSound=Sound.equip_leather_helm;
		aSkills=null;
		add();

		aName="Horned Helm";
		aDesc="Spiky. Menacing. Stylish!";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=5;
		aEquipFrame=12;
		aRandomPool=0;
		aSound=Sound.equip_horned_helm;
		aSkills=null;
		add();

		aName="Winged Fury";
		aDesc="It gleams in the dark. Could it be magical?";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=5;
		aEquipFrame=13;
		aRandomPool=0;
		aSound=Sound.equip_winged_helm;
		aSkills=null;
		add();

		aName="Scale Mail";
		aDesc="Layered scales. Works for dragons, right?";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=4;
		aEquipFrame=15;
		aRandomPool=0;
		aSound=Sound.equip_scalemail;
		aSkills=null;
		add();

		aName="Red Mail";
		aDesc="A shirt of linked rings. Seems comfortable";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=3;
		aEquipFrame=16;
		aRandomPool=1;
		aSound=Sound.equip_chainmail;
		aSkills=new Skill[]{Skill.armour.asLevel(2)};
		add();

		aName="Doom Plate";
		aDesc="Awkward, but looks like it will keep you safe";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=5;
		aEquipFrame=17;
		aRandomPool=0;
		aSound=Sound.equip_platemail;
		aSkills=null;
		add();

		aName="Wooden Shield";
		aDesc="Better than nothing!";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=2;
		aEquipFrame=18;
		aRandomPool=0;
		aSound=Sound.equip_wooden_shield;
//		aSkills=new Skill[]{Skill.armour.asLevel(1)};
//		aTraits=new Trait[]{Trait.Tenacious};
		add();

		aName="Duelling Buckler";
		aDesc="Buckles onto your arm, not under pressure!";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=3;
		aEquipFrame=19;
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
		aRandomPool=0;
		aSound=Sound.equip_buckler;
		aSkills=null;
		add();

		aName="Kite Shield";
		aDesc="A huge shield. Can you lift it?";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=5;
		aEquipFrame=21;
		aRandomPool=0;
		aSound=Sound.equip_buckler;
		aSkills=null;
		add();

		aName="Club";
		aDesc="A heavy wooden club. ME SMASH NOW!";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=2;
		aEquipFrame=22;
		aRandomPool=0;
		aSound=Sound.equip_club;
		aSkills=null;
		add();

		aName="Mace";
		aDesc="Good for self-defence";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=3;
		aEquipFrame=23;
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
		aRandomPool=0;
		aSound=Sound.equip_axe;
		aSkills=null;
		add();

		aName="Elixir";
		aDesc="Good for all that ails ya!";
		aRandomPool=0;
		aSound=Sound.equip_potion;
		add();

		aName="Health Potion";
		aDesc="Definitely not snake oil!";
		aRandomPool=0;
		aSound=Sound.equip_potion;
		add();

		aName="Mage Robes";
		aDesc="It looks like a dress, but more wizardy";
		aEquipment=1;
		aType=EquipmentType.armour;
		aEquipFrame=30;
		aLevel=3;
		aRandomPool=0;
		aSound=Sound.equip_robes;
		aSkills=null;
		add();

		aName="Wizard's Hat";
		aDesc="Is there a rabbit inside?";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=4;
		aEquipFrame=32;
		aRandomPool=0;
		aSound=Sound.equip_cloth_hat;
		aSkills=null;
		add();

		aName="Feathered Cap";
		aDesc="Where are my merry Men?!";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=4;
		aEquipFrame=31;
		aRandomPool=0;
		aKeepHair=1;
		aSound=Sound.equip_cloth_hat;
		aSkills=null;
		add();

		aName="Stiletto";
		aDesc="And not the shoe";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=2;
		aEquipFrame=27;
		aRandomPool=1;
		aSound=Sound.equip_knife;
		aSkills=new Skill[]{Skill.swift.asLevel(1), Skill.blade.asLevel(1)};
		add();

		aName="Green Bow";
		aDesc="Poison? Elves sure can be MEAN.";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=4;
		aEquipFrame=29;
		aRandomPool=0;
		aSound=Sound.equip_bow;
		aSkills=null;
		add();

		aName="Mind Staff";
		aDesc="Tell me, what's on your mind?";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=5;
		aEquipFrame=28;
		aRandomPool=0;
		aSound=Sound.equip_club;
		aSkills=null;
		add();

		aName="Winged Staff";
		aDesc="It hums with power";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=4;
		aEquipFrame=34;
		aRandomPool=0;
		aSound=Sound.equip_club;
		aSkills=null;
		add();

		aName="Toxic Mace";
		aDesc="Nasty, nasty thing";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=4;
		aEquipFrame=35;
		aRandomPool=0;
		aSound=Sound.equip_mace;
		aSkills=null;
		add();

		aName="Twig";
		aDesc="ALL FEAR THE MIGHTY TWIG";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=1;
		aEquipFrame=36;
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
		aRandomPool=1;
		aSound=Sound.equip_sword;
		aSkills=new Skill[]{Skill.blade.asLevel(1)};
		add();

		aName="Sparkly Headband";
		aDesc="SPARKLES!!";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=3;
		aEquipFrame=38;
		aRandomPool=1;
		aKeepHair=1;
		aSound=Sound.equip_leather_helm;
		aSkills=new Skill[]{Skill.swift.asLevel(2)};
		add();

		aName="Voodoo Mask";
		aDesc="Voodoo! Who do? You do!";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=2;
		aEquipFrame=39;
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
		aRandomPool=0;
		aSound=Sound.equip_silver_shield;
		aSkills=null;
		add();

		aName="Eyeball Charm";
		aDesc="I spy with my little eye!";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=1;
		aEquipFrame=42;
		aRandomPool=0;
		aSound=Sound.equip_great_shield;
		aSkills=null;
		add();

		aName="Ruffled Shirt";
		aDesc="For the fancy adventurer";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=1;
		aEquipFrame=43;
		aRandomPool=1;
		aSound=Sound.equip_cloth_hat;
		aSkills=new Skill[]{Skill.swift.asLevel(1)};
		add();

		aName="Straightjacket";
		aDesc="We're not crazy, the rest of the world is!";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=1;
		aEquipFrame=44;
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
		aRandomPool=0;
		aSound=Sound.equip_leather_armour;
		aSkills=null;
		add();

		aName="Dragon Scale";
		aDesc="So scratchy, how do they sleep in this?";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=5;
		aEquipFrame=47; 
		aRandomPool=0;
		aSound=Sound.equip_chainmail;
		aSkills=null;
		add();

		aName="Tattered Mail";
		aDesc="Worn and comfortable";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=2;
		aEquipFrame=0; //ERROR// 
		aRandomPool=1;
		aSound=Sound.equip_chainmail;
		aSkills=new Skill[]{Skill.armour.asLevel(1), Skill.crush.asLevel(1)};
		add();

		aName="Shimmering Cloak";
		aDesc="Gleams like the night sky";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=3;
		aEquipFrame=48;
		aRandomPool=1;
		aSound=Sound.equip_club;
		aSkills=new Skill[]{Skill.arcane.asLevel(2)};
		add();

		aName="Elven Plate";
		aDesc="Masterfully crafted";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=5;
		aEquipFrame=49;
		aRandomPool=0;
		aSound=Sound.equip_chainmail;
		aSkills=null;
		add();

		aName="Coat of Thorns";
		aDesc="Banned from hugs";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=4;
		aEquipFrame=50;
		aRandomPool=0;
		aSound=Sound.equip_leather_armour;
		aSkills=null;
		add();

		aName="Bone Armour";
		aDesc="Chilly";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=4;
		aEquipFrame=51;
		aRandomPool=0;
		aSound=Sound.equip_leather_armour;
		aSkills=null;
		add();

		aName="Bark Vest";
		aDesc="Tree-hugger";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=2;
		aEquipFrame=52;
		aRandomPool=0;
		aSound=Sound.equip_leather_armour;
		aSkills=null;
		add();

		aName="Padded Vest";
		aDesc="You feel safe";
		aEquipment=1;
		aType=EquipmentType.armour;
		aLevel=2;
		aEquipFrame=53;
		aRandomPool=1;
		aSound=Sound.equip_club;
		aTraits= new Trait[]{Trait.BonusHP};
		add();

		aName="Wolf Hat";
		aDesc="Grrrr";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=3;
		aEquipFrame=45;
		aRandomPool=0;
		aKeepHair=1;
		aSound=Sound.equip_leather_helm;
		aSkills=null;
		add();

		aName="Mail Coif";
		aDesc="Too cold!";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=3;
		aEquipFrame=54;
		aRandomPool=0;
		aSound=Sound.equip_winged_helm;
		aSkills=null;
		add();

		aName="Spikey hat";
		aDesc="Charge!";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=2;
		aEquipFrame=55;
		aRandomPool=1;
		aSound=Sound.equip_horned_helm;
		aSkills=new Skill[]{Skill.armour.asLevel(1), Skill.blade.asLevel(1)};
		add();

		aName="Solider's Helmet";
		aDesc="Bog-standerd";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=3;
		aEquipFrame=56;
		aRandomPool=0;
		aSound=Sound.equip_horned_helm;
		aSkills=null;
		add();

		aName="Norse Helmet";
		aDesc="verja hqfdi";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=4;
		aEquipFrame=57;
		aRandomPool=0;
		aSound=Sound.equip_horned_helm;
		aSkills=null;
		add();

		aName="Elven Helmet";
		aDesc="The smart choice";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=5;
		aEquipFrame=58;
		aRandomPool=0;
		aSound=Sound.equip_horned_helm;
		aSkills=null;
		add();

		aName="Cooking Pot";
		aDesc="Lunkhead";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=1;
		aEquipFrame=59;
		aRandomPool=1;
		aSound=Sound.equip_horned_helm;
		aSkills= new Skill[]{Skill.fire.asLevel(1)};
		
		add();

		aName="Fez";
		aDesc="Thank'y'ver'much!";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=2;
		aEquipFrame=60;
		aRandomPool=0;
		aKeepHair=1;
		aSound=Sound.equip_cloth_hat;
		//aSkills=new Skill[]{Skill.arcane.asLevel(1), Skill.fire.asLevel(1)};
		add();

		aName="Pigeon Nest";
		aDesc="Coo";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=1;
		aEquipFrame=61;
		aRandomPool=0;
		aKeepHair=1;
		aSound=Sound.equip_cloth_hat;
		aSkills=null;
		add();

		aName="Daisy Chain";
		aDesc="How sweet";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=2;
		aEquipFrame=62;
		aRandomPool=0;
		aKeepHair=1;
		aSound=Sound.equip_cloth_hat;
		aSkills=null;
		add();

		aName="Masquerade Mask";
		aDesc="Mysterious";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=2;
		aEquipFrame=63;
		aRandomPool=0;
		aKeepHair=1;
		aSound=Sound.equip_cloth_hat;
		aSkills=null;
		add();

		aName="Cultist Hood";
		aDesc="Password?";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=3;
		aEquipFrame=64;
		aRandomPool=1;
		aSound=Sound.equip_cloth_hat;
		aSkills=new Skill[]{Skill.holy.asLevel(2)};
		add();

		aName="Bone Helmet";
		aDesc="Makes you look scary!";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=2;
		aEquipFrame=65;
		aRandomPool=0;
		aKeepHair=1;
		aSound=Sound.equip_cloth_hat;
		aSkills=null;
		add();

		aName="Heavy Visor";
		aDesc="You can barely see!";
		aEquipment=1;
		aType=EquipmentType.helmet;
		aLevel=4;
		aEquipFrame=66;
		aRandomPool=0;
		aSound=Sound.equip_horned_helm;
		aSkills=null;
		add();

		aName="Femur";
		aDesc="Rattle rattle";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=2;
		aEquipFrame=67;
		aRandomPool=0;
		aSound=Sound.equip_club;
		add();

		aName="Rapier";
		aDesc="Slice slice stab";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=4;
		aEquipFrame=68;
		aRandomPool=0;
		aSound=Sound.equip_sword;
		aSkills=null;
		add();

		aName="Cutlass";
		aDesc="Good for cuttling";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=4;
		aEquipFrame=69;
		aRandomPool=0;
		aSound=Sound.equip_sword;
		aSkills=null;
		add();

		aName="Scimitar";
		aDesc="Stab slice slice";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=4;
		aEquipFrame=70;
		aRandomPool=0;
		aSound=Sound.equip_sword;
		aSkills=null;
		add();

		aName="Cleaver";
		aDesc="The butcher needs one of these!";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=3;
		aEquipFrame=71;
		aRandomPool=0;
		aSound=Sound.equip_axe;
		aSkills=null;
		add();

		aName="Demon Claw";
		aDesc="Still warm";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=5;
		aEquipFrame=72;
		aRandomPool=0;
		aSound=Sound.equip_club;
		aSkills=null;
		add();

		aName="Brass Knuckles";
		aDesc="Fighting dirty";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=2;
		aEquipFrame=73;
		aRandomPool=1;
		aSound=Sound.equip_sword;
		aSkills=new Skill[]{Skill.swift.asLevel(1), Skill.crush.asLevel(1)};
		add();

		aName="Hand-axe";
		aDesc="Handy!";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=3;
		aEquipFrame=74;
		aRandomPool=0;
		aSound=Sound.equip_axe;
		aSkills=null;
		add();

		aName="Poison Dagger";
		aDesc="That's cheating!";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=4;
		aEquipFrame=75;
		aRandomPool=0;
		aSound=Sound.equip_sword;
		aSkills=null;
		add();

		aName="Arcane Wand";
		aDesc="Swoosh";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=3;
		aEquipFrame=76;
		aRandomPool=0;
		aSound=Sound.equip_staff;
		aSkills=null;
		add();

		aName="Crystal Wand";
		aDesc="Spikey!";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=4;
		aEquipFrame=77;
		aRandomPool=0;
		aSound=Sound.equip_staff;
		aSkills=null;
		add();

		aName="Crossbow";
		aDesc="So satisfying";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=3;
		aEquipFrame=78;
		aRandomPool=0;
		aSound=Sound.equip_bow;
		aSkills=null;
		add();

		aName="Gnarled Oak";
		aDesc="Creaks and groans";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=3;
		aEquipFrame=79;
		aRandomPool=0;
		aSound=Sound.equip_staff;
		aSkills=null;
		add();

		aName="Halberd";
		aDesc="When a spear just isn't enough";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=4;
		aEquipFrame=80;
		aRandomPool=0;
		aSound=Sound.equip_staff;
		aSkills=null;
		add();

		aName="Spear";
		aDesc="Poke poke";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=3;
		aEquipFrame=81;
		aRandomPool=0;
		aSound=Sound.equip_staff;
		aSkills=null;
		add();

		aName="Fire Staff";
		aDesc="Ow ow hot";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=4;
		aEquipFrame=82;
		aRandomPool=0;
		aSound=Sound.equip_staff;
		aSkills=null;
		add();

		aName="Trident";
		aDesc="Could eat a big lunch with this";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=4;
		aEquipFrame=83;
		aRandomPool=0;
		aSound=Sound.equip_staff;
		aSkills=null;
		add();

		aName="Broadsword";
		aDesc="Don't have to aim much";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=4;
		aEquipFrame=84;
		aRandomPool=0;
		aSound=Sound.equip_sword;
		aSkills=null;
		add();

		aName="Owl Familiar";
		aDesc="You recognise this one";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=5;
		aEquipFrame=85;
		aRandomPool=0;
		aSound=Sound.equip_cloth_hat;
		aSkills=null;
		add();

		aName="Dusty Tome";
		aDesc="The pages shine";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=4;
		aEquipFrame=86;
		aRandomPool=0;
		aSound=Sound.equip_cloth_hat;
		aSkills=null;
		add();

		aName="Glyph";
		aDesc="Probably means something cool!";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=2;
		aEquipFrame=87;
		aRandomPool=1;
		aSound=Sound.equip_buckler;
		aSkills=new Skill[]{Skill.fire.asLevel(1), Skill.arcane.asLevel(1)};
		add();

		aName="Horseshoe";
		aDesc="Who leaves one shoe behind?";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=2;
		aEquipFrame=88;
		aRandomPool=1;
		aSound=Sound.equip_buckler;
		aSkills=new Skill[]{Skill.arcane.asLevel(1), Skill.holy.asLevel(1)};
		add();

		aName="War Horn";
		aDesc="drengskapr";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=4;
		aEquipFrame=89;
		aRandomPool=0;
		aSound=Sound.equip_buckler;
		aSkills=null;
		add();

		aName="Spiked Shield";
		aDesc="Just try and hit me!";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=4;
		aEquipFrame=90;
		aRandomPool=0;
		aSound=Sound.equip_silver_shield;
		aSkills=null;
		add();

		aName="Wooden Board";
		aDesc="This looks almost useful";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=1;
		aEquipFrame=91;
		aRandomPool=1;
		aSound=Sound.equip_wooden_shield;
		aSkills=new Skill[]{Skill.armour.asLevel(1)};
		add();

		aName="Net";
		aDesc="Gotcha!";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=2;
		aEquipFrame=92;
		aRandomPool=0;
		aSound=Sound.equip_cloth_hat;
		aSkills=null;
		add();

		aName="Swirling Orb";
		aDesc="Bubble bubble";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=3;
		aEquipFrame=93;
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
		aRandomPool=1;
		aSound=Sound.equip_leather_helm;
		aSkills=new Skill[]{Skill.arcane.asLevel(1)};
		add();

		aName="Scroll of Souls";
		aDesc="Don't read it!";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=3;
		aEquipFrame=95;
		aRandomPool=0;
		aSound=Sound.equip_cloth_hat;
		aSkills=null;
		add();

		aName="Cuppa";
		aDesc="Oooh that's better";
		aEquipment=1;
		aType=EquipmentType.shield;
		aLevel=1;
		aEquipFrame=96;
		aRandomPool=0;
		aSound=Sound.equip_club;
		aSkills=null;
		add();

		aName="Wooden Stool";
		aDesc="What's this supposed to be for?";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=1;
		aEquipFrame=97;
		aRandomPool=0;
		aSound=Sound.equip_club;
		aSkills=null;
		add();








		aName="placeholder";
		aDesc="";
		aEquipment=1;
		aType=EquipmentType.weapon;
		aLevel=1;
		aEquipFrame=36;
		aRandomPool=0;
		aSound=Sound.equip_club;
		aSkills=null;
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
	}

	public static void add(){
		if(aName=="placeholder")return;
		items.add(new Item(aName, aDesc, aFrameNumber, aEquipment, aType, aEquipFrame, aLevel, aGlory, aRandomPool, aKeepHair, aSound, aSkills, aTraits));
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
		aGlory=-1;
		aRandomPool=-1;
		aKeepHair=0;
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

	public static void printNumbers(){
		for(int i=1;i<levels.length;i++){
			System.out.println("Level "+(i)+": "+levels[i].size());
		}
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
		if(randomPool>0) output+="\"RandomPool\" : "+randomPool+",\n";
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
			for(int i=0;i<skills.length;i++){
				Trait t = traits[i];
				output+=t.toJson();
				if(skills.length>i+1)output+=",";
				output+="\n";
			}
			output+="],\n";
		}

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
}
