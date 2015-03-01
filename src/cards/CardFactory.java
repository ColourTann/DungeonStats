package cards;

import java.util.ArrayList;
import java.util.HashMap;

import json.Json;
import cards.CardFactory.Action.ActionType;
import cards.CardFactory.Action.DamageType;
import cards.CardFactory.ResultAction.ResultActionEffectType;
import cards.Skill.SkillType;

public class CardFactory {

	public enum Icon{
		Default_icon, STAT_STRENGTH, STAT_MAGIC, STAT_DREAD, STAT_HEALTH, STAT_ARMOUR, STAT_SPEED
	}

	public enum BonusVsSpecies{
		Undead
	}

	public enum BonusVsMechanic{
		unblockable
	}

	public enum ActionEffectCondition{
		damageDealt, damageBlocked
	}

	private static String cName;
	private static float cStrength;
	private static String cDescription;
	private static int cDescSize;
	private static Icon cIcon;
	private static ArrayList<Action> cActions= new ArrayList<>();

	private static ArrayList<ResultAction> resultActions= new ArrayList<>();

	private static ActionType aActionType;
	private static DamageType aDamageType; 
	private static boolean aQuick;
	private static boolean aUnblockable;
	private static ActionEffectCondition aEffectCondition;
	private static int aEffect;
	private static BonusVsSpecies aBonusSpecies;
	private static BonusVsMechanic aBonusMechanic;


	private static ActionType raType;
	private static int raRounds;
	private static DamageType raDamageType;
	private static ResultActionEffectType raEffectType;
	private static int raEffect;

	private static ArrayList<Card> cards=new ArrayList<>();

	

	

	public static void setupSkill(SkillType type){
		
		resetAction();
		resetCard();

		switch (type){
		case Arcane:

			cName= "Force Shield";
			cStrength=2;
			cDescription = "Block 1 dmg, +1 card per dmg blocked";
			cDescSize=20;
			cIcon=Icon.STAT_ARMOUR;
			aActionType=ActionType.Block;
			aDamageType=DamageType.Either;
			aEffect=1;
			addAction();
			aActionType=ActionType.Draw;
			aEffectCondition=ActionEffectCondition.damageBlocked;
			addAction();
			addCard(type);

			cName= "Focus";
			cStrength=2.5f;
			cDescription = "+2 on next magic attack, +1 hp";
			cDescSize=20;
			cIcon=Icon.STAT_MAGIC;
			aActionType=ActionType.NextAttack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			aActionType=ActionType.Heal;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Arcane Barrage";
			cStrength=3;
			cDescription = "3 magic damage, [unblockable]";
			cDescSize=22;
			cIcon=Icon.STAT_ARMOUR;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=3;
			aUnblockable=true;
			addAction();
			addCard(type);

			break;
		case Armour:

			cName= "Advance";
			cStrength=2;
			cDescription = "1 physical damage, block 1 damage";
			cDescSize=20;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Either;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Barge";
			cStrength=2.5f;
			cDescription = "Block 2 physical dmg, +1 to next physical attack";
			cDescSize=19;
			cIcon=Icon.STAT_ARMOUR;
			aActionType=ActionType.Block;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			aActionType=ActionType.NextAttack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Brace";
			cStrength=3;
			cDescription = "Block all, +1 card";
			cDescSize=24;
			cIcon=Icon.STAT_ARMOUR;
			aActionType=ActionType.Block;
			aDamageType=DamageType.Either;
			addAction();
			aActionType=ActionType.Draw;
			aEffect=1;
			addAction();
			addCard(type);

			break;
		case Blade:

			cName= "Slice";
			cStrength=2;
			cDescription = "2 physical damage";
			cDescSize=24;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Dice";
			cStrength=2.5f;
			cDescription = "2 physical damage, +2 vs unblockable";
			cDescSize=20;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aBonusMechanic=BonusVsMechanic.unblockable;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Eviscerate";
			cStrength=3;
			cDescription = "3 physical damage, block 1 magic";
			cDescSize=20;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=3;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();			
			addCard(type);
			break;

		case Crush:

			cName= "Bash";
			cStrength=2;
			cDescription = "2 physical damage";
			cDescSize=24;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Slam";
			cStrength=2.5f;
			cDescription = "2 physical damage, [unblockable]";
			cDescSize=20;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aUnblockable=true;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Smash";
			cStrength=3;
			cDescription = "3 physical damage, block 1 physical";
			cDescSize=20;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=3;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();			
			addCard(type);
			break;

		case Fire:

			cName= "Fireblast";
			cStrength=2;
			cDescription = "2 magic damage";
			cDescSize=24;
			cIcon=Icon.STAT_MAGIC;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Flamelash";
			cStrength=2.5f;
			cDescription = "2 magic damage [quick]";
			cDescSize=24;
			cIcon=Icon.STAT_MAGIC;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aQuick=true;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Ignite";
			cStrength=3;
			cDescription = "1 magic damage: 1 magic damage each turn";
			cDescSize=19;
			cIcon=Icon.STAT_MAGIC;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			raType=ActionType.Effect;
			raEffectType=ResultActionEffectType.Fire;
			raDamageType=DamageType.Magical;
			raEffect=1;
			raRounds=-1;
			addResultAction();
			addAction();
			addCard(type);

			break;
		case Growth:
			break;
		case Holy:

			cName= "Holy Seal";
			cStrength=2;
			cDescription = "Block 1 damage, +1 hp per dmg blocked";
			cDescSize=20;
			cIcon=Icon.STAT_HEALTH;
			aActionType=ActionType.Block;
			aDamageType=DamageType.Either;
			aEffect=1;
			addAction();
			aActionType=ActionType.Heal;
			aEffectCondition=ActionEffectCondition.damageBlocked;
			addAction();
			addCard(type);

			cName= "Smite";
			cStrength=2.5f;
			cDescription = "2 magic damage, block 1 magic dmg";
			cDescSize=22;
			cIcon=Icon.STAT_ARMOUR;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Blinding Light";
			cStrength=3;
			cDescription = "2 magic damage, +1 hp";
			cDescSize=24;
			cIcon=Icon.STAT_MAGIC;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			aActionType=ActionType.Heal;
			aEffect=1;
			addAction();
			addCard(type);

			break;
		case Swift:

			cName= "Shift";
			cStrength=2;
			cDescription = "1 physical damage, [quick], +1 vs unblockable";
			cDescSize=19;
			cIcon=Icon.STAT_SPEED;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aQuick=true;
			aEffect=1;
			addAction();
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aQuick=true;
			aBonusMechanic=BonusVsMechanic.unblockable;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Interrupt";
			cStrength=2.5f;
			cDescription = "1 physical damage, [quick], block all magic";
			cDescSize=20;
			cIcon=Icon.STAT_SPEED;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aQuick=true;
			aEffect=1;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Magical;
			addAction();			
			addCard(type);

			cName= "Fleet-footed";
			cStrength=3;
			cDescription = "2 physical damage, [quick], [unblockable]";
			cDescSize=20;
			cIcon=Icon.STAT_SPEED;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aQuick=true;
			aUnblockable=true;
			aEffect=2;
			addAction();
			addCard(type);

			break;

		case Feral:
			cName= "Bite";
			cStrength=1.2f;
			cDescription = "1 physical damage, [unblockable]";
			cDescSize=22;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			aUnblockable=true;
			addAction();
			addCard(type);

			cName= "Bite";
			cStrength=1.2f;
			cDescription = "1 physical damage, [unblockable]";
			cDescSize=22;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			aUnblockable=true;
			addAction();
			addCard(type);

			cName= "Charge";
			cStrength=2.3f;
			cDescription = "2 physical damage, block 1 magic";
			cDescSize=22;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Charge";
			cStrength=2.3f;
			cDescription = "2 physical damage, block 1 magic";
			cDescSize=22;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Savage";
			cStrength=3f;
			cDescription = "3 physical damage";
			cDescSize=22;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=3;
			addAction();
			addCard(type);

			break;
		case Death:

			cName= "Pain";
			cStrength=1f;
			cDescription = "1 magic damage";
			cDescSize=24;
			cIcon=Icon.STAT_DREAD;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Darkness";
			cStrength=1.3f;
			cDescription = "1 magic damage, block 1 magical";
			cDescSize=20;
			cIcon=Icon.STAT_DREAD;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Shadow Strike";
			cStrength=2f;
			cDescription = "1 magic damage, 1 physical damage";
			cDescSize=22;
			cIcon=Icon.STAT_DREAD;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Shadow Spear";
			cStrength=2.6f;
			cDescription = "2 magic damage, [unblockable]";
			cDescSize=22;
			cIcon=Icon.STAT_DREAD;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			aUnblockable=true;
			addAction();
			addCard(type);

			cName= "Curse";
			cStrength=3f;
			cDescription = "2 magic damage: enemy discards 1 card";
			cDescSize=19;
			cIcon=Icon.STAT_DREAD;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			raType=ActionType.Discard;
			raEffect=1;
			addResultAction();
			addAction();
			addCard(type);

			break;
		case Flame:

			cName= "Spark";
			cStrength=1f;
			cDescription = "1 magic damage";
			cDescSize=24;
			cIcon=Icon.STAT_MAGIC;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Flare";
			cStrength=1.2f;
			cDescription = "1 magic damage, [unblockable]";
			cDescSize=22;
			cIcon=Icon.STAT_MAGIC;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			aUnblockable=true;
			addAction();
			addCard(type);

			cName= "Fiery Weapon";
			cStrength=2f;
			cDescription = "1 magic damage, 1 physical damage";
			cDescSize=22;
			cIcon=Icon.STAT_MAGIC;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Fireball";
			cStrength=2f;
			cDescription = "2 magic damage";
			cDescSize=24;
			cIcon=Icon.STAT_MAGIC;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Blaze";
			cStrength=3f;
			cDescription = "3 magic damage";
			cDescSize=24;
			cIcon=Icon.STAT_MAGIC;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=3;
			addAction();
			addCard(type);

			break;
		case Nature:

			cName= "Claw";
			cStrength=1f;
			cDescription = "1 physical damage";
			cDescSize=24;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Neurotoxin";
			cStrength=1.2f;
			cDescription = "1 magic damage: enemy discards 1 card";
			cDescSize=20;
			cIcon=Icon.STAT_MAGIC;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			raType=ActionType.Discard;
			raEffect=1;
			addResultAction();
			addAction();
			addCard(type);

			cName= "Restore";
			cStrength=2f;
			cDescription = "1 magic damage: +2 hp";
			cDescSize=24;
			cIcon=Icon.STAT_HEALTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			raType=ActionType.Heal;
			raEffect=2;
			addResultAction();
			addAction();
			addCard(type);


			cName= "Entangle";
			cStrength=3.5f;
			cDescription = "2 magic damage, block 2 damage";
			cDescSize=22;
			cIcon=Icon.STAT_MAGIC;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Either;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Entangle";
			cStrength=3.5f;
			cDescription = "2 magic damage, block 2 damage";
			cDescSize=22;
			cIcon=Icon.STAT_MAGIC;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Either;
			aEffect=2;
			addAction();
			addCard(type);
			break;
		case Rage:

			cName= "Anger";
			cStrength=0.1f;
			cDescription = "1 physical damage, take 1 damage";
			cDescSize=22;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			aActionType=ActionType.TakeDamage;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Headbutt";
			cStrength=1.3f;
			cDescription = "2 physical damage, take 1 damage";
			cDescSize=22;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			aActionType=ActionType.TakeDamage;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Headbutt";
			cStrength=1.3f;
			cDescription = "2 physical damage, take 1 damage";
			cDescSize=22;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			aActionType=ActionType.TakeDamage;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Clobber";
			cStrength=2.3f;
			cDescription = "2 physical damage, [unblockable]";
			cDescSize=22;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			aUnblockable=true;
			addAction();
			addCard(type);

			cName= "Wrath";
			cStrength=3.3f;
			cDescription = "3 physical damage, block 1 magic";
			cDescSize=22;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=3;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			addCard(type);

			break;
		case Spooky:

			cName= "Spook";
			cStrength=1.3f;
			cDescription = "1 magic damage, block 1 physical";
			cDescSize=22;
			cIcon=Icon.STAT_MAGIC;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Drain";
			cStrength=1.8f;
			cDescription = "1 magic damage, +1 HP/damage dealt";
			cDescSize=21;
			cIcon=Icon.STAT_MAGIC;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			aActionType=ActionType.Heal;
			aEffectCondition= ActionEffectCondition.damageDealt;
			addAction();
			addCard(type);

			cName= "Drain";
			cStrength=1.8f;
			cDescription = "1 magic damage, +1 HP/damage dealt";
			cDescSize=21;
			cIcon=Icon.STAT_MAGIC;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			aActionType=ActionType.Heal;
			aEffectCondition= ActionEffectCondition.damageDealt;
			addAction();
			addCard(type);

			cName= "Swoop";
			cStrength=2.6f;
			cDescription = "2 magic damage, block 2 physical";
			cDescSize=22;
			cIcon=Icon.STAT_MAGIC;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Siphon";
			cStrength=3.5f;
			cDescription = "2 magic damage, +1 HP/damage dealt";
			cDescSize=22;
			cIcon=Icon.STAT_MAGIC;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			aActionType=ActionType.Heal;
			aEffectCondition= ActionEffectCondition.damageDealt;
			addAction();
			addCard(type);

			break;
		case Stupidity:

			cName="Uhh..";
			cDescription="You scratch your head";
			cDescSize=24;
			cIcon=Icon.STAT_DREAD;
			addCard(type);

			cName="Uhh..";
			cDescription="You scratch your head";
			cDescSize=24;
			cIcon=Icon.STAT_DREAD;
			addCard(type);

			cName="Uhh..";
			cDescription="You scratch your head";
			cDescSize=24;
			cIcon=Icon.STAT_DREAD;
			addCard(type);

			cName="Uhh..";
			cDescription="You scratch your head";
			cDescSize=24;
			cIcon=Icon.STAT_DREAD;
			addCard(type);

			cName="Uhh..";
			cDescription="You scratch your head";
			cDescSize=24;
			cIcon=Icon.STAT_DREAD;
			addCard(type);

			break;
		case Weapon:
			cName= "Strike";
			cStrength=1f;
			cDescription = "1 physical damage";
			cDescSize=24;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Strike";
			cStrength=1f;
			cDescription = "1 physical damage";
			cDescSize=24;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Parry";
			cStrength=2.3f;
			cDescription = "2 physical damage, block 1 physical";
			cDescSize=20;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Clash";
			cStrength=2.3f;
			cDescription = "2 physical damage, [unblockable]";
			cDescSize=22;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			aUnblockable=true;
			addAction();
			addCard(type);

			cName= "Swipe";
			cStrength=3.8f;
			cDescription = "3 physical damage, block 2 physical";
			cDescSize=19;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=3;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			addCard(type);

			break;
		case Demonic:
			break;
		case Ferocious:
			break;
		case Sorcery:
			break;
		case Warrior:
			cName= "Hit";
			cStrength=1f;
			cDescription = "1 physical damage";
			cDescSize=22;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Hit";
			cStrength=1f;
			cDescription = "1 physical damage";
			cDescSize=22;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Crack";
			cStrength=1.5f;
			cDescription = "1 physical damage, [unblockable]";
			cDescSize=22;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			aUnblockable=true;
			addAction();
			addCard(type);

			cName= "Rush";
			cStrength=2f;
			cDescription = "1 physical damage, block 1 magical";
			cDescSize=22;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Whack";
			cStrength=2f;
			cDescription = "2 physical damage";
			cDescSize=24;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Clang!";
			cStrength=1f;
			cDescription = "Block all physical damage";
			cDescSize=22;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Block;
			aDamageType=DamageType.Physical;
			addAction();
			addCard(type);

			break;
			
		case Wizard:
			
			cName= "Bolt";
			cStrength=1f;
			cDescription = "1 magic damage";
			cDescSize=22;
			cIcon=Icon.STAT_MAGIC;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Bolt";
			cStrength=1f;
			cDescription = "1 magic damage";
			cDescSize=22;
			cIcon=Icon.STAT_MAGIC;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Zap";
			cStrength=1.5f;
			cDescription = "1 magic damage, [quick]";
			cDescSize=22;
			cIcon=Icon.STAT_MAGIC;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			aQuick=true;
			addAction();
			addCard(type);

			cName= "Stonefist";
			cStrength=2f;
			cDescription = "1 physical damage, block 1 physical";
			cDescSize=22;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Blast";
			cStrength=2f;
			cDescription = "2 magic damage";
			cDescSize=24;
			cIcon=Icon.STAT_MAGIC;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Phase";
			cStrength=1f;
			cDescription = "Block 2 damage";
			cDescSize=22;
			cIcon=Icon.STAT_ARMOUR;
			aActionType=ActionType.Block;
			aDamageType=DamageType.Either;
			aEffect=2;
			addAction();
			addCard(type);
			
			break;
		default:
			break;
		}
	}



	static void resetAction(){
		aActionType=null;
		aDamageType=null;
		aQuick=false;
		aUnblockable=false;
		aEffect=0;
		aEffectCondition=null;
		aBonusMechanic=null;
		aBonusSpecies=null;
		resultActions.clear();
	}

	static void addAction(){
		cActions.add(new Action(aActionType, aDamageType, aUnblockable, aQuick, aEffectCondition, aEffect, aBonusMechanic, aBonusSpecies, resultActions==null?null:(ArrayList<ResultAction>) resultActions.clone()));		
		resetAction();
	}

	static void resetResultAction(){
		raEffect=0;
		raType=null;
		raRounds=0;
		raDamageType=null;
		raEffectType=null;
	}

	static void addResultAction(){
		resultActions.add(new ResultAction(raType, raEffectType, raDamageType, raEffect, raRounds));
		resetResultAction();
	}

	static void resetCard(){
		cName=null;
		cStrength=0;
		cDescription=null;
		cDescSize=0;
		cIcon=null;
		cActions.clear();
	}

	private static void addCard(SkillType type) {
		Skill.get(type).addCard(makeCard());
	}

	private static Card makeCard(){
		Card c=new Card(cName, cStrength, cDescription, cDescSize, cIcon, cActions==null?null:(ArrayList<Action>) cActions.clone());
		resetCard();
		return c;
	}
	
	public static void makeAllCards(){
		for(SkillType t: SkillType.values()){
			setupSkill(t);
		}
	}
	
	public static void checkDuplicates(){
		//check for duplicates//
		
				ArrayList<String> strings = new ArrayList<>();
				for(Card c:cards){
					if(strings.contains(c.name)){
						System.out.println(c.name);
					}
					else strings.add(c.name);
				}
	}

	public static String jsonAllSkills(boolean player){
		String output= "";
		output+=Json.enclose();
		output+=Json.startList("BattleCards");
		output+=Json.startList("Skills");
		for(SkillType type:player?Skill.playerSkills:Skill.monsterSkills){
			output+=jsonSkills(type);	
		}
		output=Json.removeComma(output);
		output+=Json.endList();
		output=Json.removeComma(output);
		output+=Json.endList();
		output=Json.removeComma(output);
		output+=Json.endEnclose();
		output=Json.removeComma(output);
		return output;
	}
	
	public static String jsonAllClasses(){
		String output= "";
		output+=Json.enclose();
		output+=Json.startList("Classes");
		for(SkillType type:Skill.heroDecks){
			output+=jsonSkills(type);	
		}
		output=Json.removeComma(output);
		output+=Json.endList();
		output=Json.removeComma(output);
		output+=Json.endEnclose();
		output=Json.removeComma(output);
		return output;
	}

	public static String jsonSkills(SkillType type){
		String output="";
		output+=Json.startArray(type.toString());
		for(Card c:Skill.get(type).getCards()){
			output+=Json.enclose();
			output+=c.toJson();
			output+=Json.endEnclose();
		}
		output=Json.removeComma(output);
		output+=Json.endArray();
		return output;
	}






	static class Action{

		ActionType aType;
		DamageType dType;
		boolean unblockable;
		boolean quick;
		int effect;
		ActionEffectCondition effectCondition;
		BonusVsMechanic bonusVsMechanic;
		BonusVsSpecies bonusVsSpecies;
		ArrayList<ResultAction> resultActions;
		public Action(ActionType aType, DamageType dtType, boolean unblockable, boolean quick, ActionEffectCondition effectCondition, int effect, BonusVsMechanic bonusVsMechanic, BonusVsSpecies bonusVsSpecies, ArrayList<ResultAction> resultActions){
			this.aType=aType;
			this.dType=dtType;
			this.unblockable=unblockable;
			this.quick=quick;
			this.effect=effect;
			this.effectCondition=effectCondition;
			this.bonusVsMechanic=bonusVsMechanic;
			this.bonusVsSpecies=bonusVsSpecies;
			this.resultActions=resultActions;
		}

		public enum ActionType{
			Attack, Heal, Block, Discard, TakeDamage, Draw, NextAttack, Effect
		}
		public enum DamageType{
			Physical("Physical"), Magical("Magical"), Either("Magical,Physical");
			String s;
			DamageType(String s){
				this.s=s;
			}
			public String toString(){
				return s;
			}
		}
		public String toJson() {
			String output="";

			if(aType!=null)output+=Json.addKey("type", aType.toString());
			if(dType!=null)output+=Json.addKey("damageType", dType.toString());
			if(effect>0){
				switch(aType){
				case Attack:
				case Block:
				case NextAttack:
				case Effect: 
				case TakeDamage:
					output+=Json.addKey("damage", effect);
					break;
				case Discard:
				case Draw:
				case Heal:
					output+=Json.addKey("quantity", effect);
					break;	
				}
			}
			if(effectCondition!=null) output+=Json.addKey("quantityCondition", effectCondition.toString());
			if(unblockable)output+=Json.addKey("unblockable", true);
			if(quick)output+=Json.addKey("quick", true);
			if(bonusVsMechanic!=null||bonusVsSpecies!=null) output+=Json.addKey("versesType", "ANY");
			if(bonusVsMechanic!=null) output+=Json.addKey("versesKeyword", bonusVsMechanic.toString());
			if(bonusVsSpecies!=null) output+=Json.addKey("versesClass", bonusVsSpecies.toString());
			if(resultActions.size()>0){
				output+=Json.startArray("resultActions");
				for(ResultAction ra:resultActions){
					output+=ra.toJson();
				}
				output=Json.removeComma(output);
				output+=Json.endArray();
			}
			return output;
		}
	}

	static class ResultAction{
		ActionType type;
		int actionEffect;
		ResultActionEffectType effectType;
		DamageType damageType;
		int rounds;
		public enum ResultActionEffectType{
			Fire
		}
		public ResultAction(ActionType type, ResultActionEffectType effectType, DamageType raDamageType, int actionEffect, int raRounds){
			this.type=type;
			this.actionEffect=actionEffect;
			this.rounds=raRounds;
			this.effectType=effectType;
			this.damageType=raDamageType;
			this.actionEffect=actionEffect;
		}
		public String toJson(){
			String output="";
			output+=Json.enclose();
			output+=Json.addKey("type", type.toString());
			if(effectType!=null)output+=Json.addKey("effectType", effectType.toString());
			if(damageType!=null)output+=Json.addKey("damageType", damageType.toString());
			if(rounds!=0)output+=Json.addKey("rounds", rounds);

			if(actionEffect!=0){
				switch(type){
				case Attack:
				case Block:
				case NextAttack:
				case Effect: 
				case TakeDamage:
					output+=Json.addKey("damage", actionEffect);
					break;
				case Discard:
				case Draw:
				case Heal:
					output+=Json.addKey("quantity", actionEffect);
					break;
				}
			}
			output+=Json.addKey("quantity", actionEffect);
			output=Json.removeComma(output);
			output+=Json.endEnclose();
			return output;

		}
	}
}
