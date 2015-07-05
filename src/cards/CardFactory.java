package cards;

import java.util.ArrayList;
import java.util.HashMap;

import json.Json;
import cards.CardFactory.Action.ActionType;
import cards.CardFactory.Action.DamageType;
import cards.CardFactory.ResultAction.ResultActionEffectType;
import cards.Skill.SkillType;

public class CardFactory {


	public enum BonusVsSpecies{
		Undead
	}

	public enum BonusVsMechanic{
		unblockable
	}

	public enum BonusVsSituation{
		BelowHalf
	}



	public enum ActionEffectCondition{
		damageDealt, damageBlocked, Hand, damageTaken
	}

	private static String cName;
	private static float cStrength;
	private static String cDescription;
	private static int cDescSize;
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
	private static BonusVsSituation aBonusSituation;


	private static ActionType raType;
	private static int raRounds;
	private static DamageType raDamageType;
	private static ResultActionEffectType raEffectType;
	private static int raEffect;

	private static ArrayList<Card> cards=new ArrayList<>();
	private static SkillType cImage;





	public static void setupSkill(SkillType type){

		resetAction();
		resetCard();

		switch (type){

		/*
		 * 
		 * CLASSES
		 * 
		 */


		case Chump:
			cName= "Eyes closed punch";
			cStrength=1f;
			cDescription = "1 physical damage";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Eyes closed punch";
			cStrength=1f;
			cDescription = "1 physical damage";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Eyes closed punch";
			cStrength=1f;
			cDescription = "1 physical damage";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Lucky hit";
			cStrength=2f;
			cDescription = "2 physical damage";
			cDescSize=24;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Cower";
			cStrength=1f;
			cDescription = "Block 1 damage";
			cDescSize=22;
			aActionType=ActionType.Block;
			aDamageType=DamageType.Either;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Cower";
			cStrength=1f;
			cDescription = "Block 1 damage";
			cDescSize=22;
			aActionType=ActionType.Block;
			aDamageType=DamageType.Either;
			aEffect=1;
			addAction();
			addCard(type);
			break;

		case Bruiser:
			cName= "Oi!";
			cStrength=1f;
			cDescription = "1 physical damage";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Oi!";
			cStrength=1f;
			cDescription = "1 physical damage";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "How do you like this?";
			cStrength=1.3f;
			cDescription = "1 physical damage [unblockable]";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			aUnblockable=true;
			addAction();
			addCard(type);

			cName= "Get outta here";
			cStrength=1.8f;
			cDescription = "1 physical damage, block 1 physical";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Nice try, chump";
			cStrength=1.3f;
			cDescription = "Block 2 magic damage";
			cDescSize=22;
			aActionType=ActionType.Block;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "You tryin' something?";
			cStrength=1.3f;
			cDescription = "Block 2 physical damage";
			cDescSize=22;
			aActionType=ActionType.Block;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			addCard(type);
			break;

		case Cat_Burglar:
			cName= "Pounce";
			cStrength=1.4f;
			cDescription = "1 physical damage [unblockable]";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aUnblockable=true;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Scratch";
			cStrength=1.4f;
			cDescription = "1 physical damage [quick]";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aQuick=true;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Throw cat";
			cStrength=2f;
			cDescription = "2 physical damage";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Throw cat";
			cStrength=2f;
			cDescription = "2 physical damage";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Catnip";
			cStrength=1f;
			cDescription = "Next physical attack get +1 damage";
			cDescSize=22;
			aActionType=ActionType.NextAttack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Catnip";
			cStrength=1f;
			cDescription = "Next physical attack get +1 damage";
			cDescSize=22;
			aActionType=ActionType.NextAttack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);
			break;
		case Ranger:

			cName= "Wooden arrow";
			cStrength=1f;
			cDescription = "1 physical damage";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Pierce";
			cStrength=1.4f;
			cDescription = "1 physical damage [unblockable]";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aUnblockable=true;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Flame arrow";
			cStrength=2f;
			cDescription = "2 magic damage";
			cDescSize=24;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Steelhead arrow";
			cStrength=2f;
			cDescription = "2 physical damage";
			cDescSize=24;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Camouflage";
			cStrength=2f;
			cDescription = "Block 1 any, +1 to next physical attack";
			cDescSize=24;
			aActionType=ActionType.Block;
			aDamageType=DamageType.Either;
			aEffect=1;
			addAction();
			aActionType=ActionType.NextAttack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Camouflage";
			cStrength=2f;
			cDescription = "Block 1 any, +1 to next physical attack";
			cDescSize=24;
			aActionType=ActionType.Block;
			aDamageType=DamageType.Either;
			aEffect=1;
			addAction();
			aActionType=ActionType.NextAttack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			break;
			
		case Barbarian:

			cName= "Slam";
			cStrength=1f;
			cDescription = "2 phsycial damage, -1 hp";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			aActionType=ActionType.TakeDamage;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Slam";
			cStrength=1f;
			cDescription = "2 phsycial damage, -1 hp";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			aActionType=ActionType.TakeDamage;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Howl";
			cStrength=2f;
			cDescription = "3 phsycial damage, -1 hp";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=3;
			addAction();
			aActionType=ActionType.TakeDamage;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Assault";
			cStrength=2.4f;
			cDescription = "2 physical damage, block 1 magic";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Overpower";
			cStrength=2.4f;
			cDescription = "2 phsycial damage [unblockable]";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			aUnblockable=true;
			addAction();
			addCard(type);


			cName= "Withstand";
			cStrength=3f;
			cDescription = "Cannot go below 1hp this turn or next";
			cDescSize=24;
			aActionType=ActionType.Withstand;
			aEffect=2;
			addAction();
			addCard(type);
			break;

		case Apprentice:

			cName= "Sparks";
			cStrength=1.4f;
			cDescription = "1 magic damage [unblockable]";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aUnblockable=true;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Sparks";
			cStrength=1.4f;
			cDescription = "1 magic damage [unblockable]";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aUnblockable=true;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Zap";
			cStrength=1.5f;
			cDescription = "1 magic damage [quick]";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			aQuick=true;
			addAction();
			addCard(type);

			cName= "Fire Shield";
			cStrength=1.5f;
			cDescription = "1 magic damage, block 1 magic";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Fire Shield";
			cStrength=1.5f;
			cDescription = "1 magic damage, block 1 magic";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Intuition";
			cStrength=1f;
			cDescription = "Draw 2 cards";
			cDescSize=24;
			aActionType=ActionType.Draw;
			aEffect=2;
			addAction();
			addCard(type);
			break;	
		case Mime:


			cName= "Copycat";
			cStrength=1.5f;
			cDescription = "1 magic damage: Copy top enemy card to your hand";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			raType=ActionType.Copy;
			raEffect=1;
			addResultAction();
			addAction();
			addCard(type);

			cName= "Stage-presence";
			cStrength=1.5f;
			cDescription = "1 magic damage: discard one of the enemy's top 2 cards";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			raType=ActionType.Scry;
			raEffect=2;
			addResultAction();
			addAction();
			addCard(type);

			cName= "Stage-presence";
			cStrength=1.5f;
			cDescription = "1 magic damage: discard one of the enemy's top 2 cards";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			raType=ActionType.Scry;
			raEffect=2;
			addResultAction();
			addAction();
			addCard(type);

			cName= "Bull-horns";
			cStrength=1.8f;
			cDescription = "1 physical damage, block 1 physical";
			cDescSize=24;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Imaginary box";
			cStrength=1.8f;
			cDescription = "Block 1 physical, +1 next magic attack";
			cDescSize=24;
			aActionType=ActionType.Block;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			aActionType=ActionType.NextAttack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Imaginary Cannon";
			cStrength=2f;
			cDescription = "2 magic damage";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			addCard(type);

			break;

		case Alchemist:
			cName= "Flask of Acid";
			cStrength=2f;
			cDescription = "2 magic damage";
			cDescSize=24;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Flask of Acid";
			cStrength=2f;
			cDescription = "2 magic damage";
			cDescSize=24;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Flask of Healing";
			cStrength=2.3f;
			cDescription = "+1 HP";
			cDescSize=24;
			aActionType=ActionType.Heal;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Flask of Healing";
			cStrength=2.3f;
			cDescription = "+1 HP";
			cDescSize=24;
			aActionType=ActionType.Heal;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Flask of Lightning";
			cStrength=1.4f;
			cDescription = "1 magic damage [quick]";
			cDescSize=24;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			aQuick=true;
			addAction();
			addCard(type);

			cName= "Flask of Pixie Tears";	
			cDescription = "Block 2 magic, +2 to next magic attack";
			cDescSize=22;
			cStrength=3.3f;
			aActionType=ActionType.Block;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			aActionType=ActionType.NextAttack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			addCard(type);
			break;

		case Shapeshifter:

			cName= "Regenerate";
			cStrength=2f;
			cDescription = "+2 HP";
			cDescSize=22;
			aActionType=ActionType.Heal;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Aspect of the panther";
			cStrength=1.2f;
			cDescription = "1 magic damage [unblockable]";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			aUnblockable=true;
			addAction();
			addCard(type);

			cName= "Aspect of the bull";
			cStrength=2f;
			cDescription = "2 physical damage";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Aspect of the bull";
			cStrength=2f;
			cDescription = "2 physical damage";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Aspect of the dragon";
			cStrength=2.2f;
			cDescription = "1 magic damage, block 2 magic";
			cDescSize=24;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Barkskin";
			cStrength=2.8f;
			cDescription = "Block 3 physical";
			cDescSize=22;
			aActionType=ActionType.Block;
			aDamageType=DamageType.Physical;
			aEffect=3;
			addAction();
			addCard(type);
			break;


		case Troubador:

			cName= "Ice Blast";
			cStrength=1.4f;
			cDescription = "1 magic damage [unblockable]";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aUnblockable=true;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Spike";
			cStrength=2.4f;
			cDescription = "2 physical damage [quick]";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aQuick=true;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Parry";
			cStrength=3.5f;
			cDescription = "2 physical damage, block 2 physical";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Leap";
			cStrength=3.5f;
			cDescription = "2 physical damage, block 2 magic";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Arcane Swipe";
			cStrength=2f;
			cDescription = "1 physical damage, 1 magic damage";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Arcane Swipe";
			cStrength=2f;
			cDescription = "1 physical damage, 1 magic damage";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			addCard(type);			

			break;
		case Most_Holy_Grail_Knight:

			cName= "Divine Hammer";
			cStrength=1.5f;
			cDescription = "1 physical damage: +1 hp";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			raType=ActionType.Heal;
			raEffect=1;
			addResultAction();
			addAction();
			addCard(type);

			cName= "Divine Hammer";
			cStrength=1.5f;
			cDescription = "1 physical damage: +1 hp";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			raType=ActionType.Heal;
			raEffect=1;
			addResultAction();
			addAction();
			addCard(type);

			cName= "Absolution";
			cStrength=2.5f;
			cDescription = "1 magic damage: +2 hp";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			raType=ActionType.Heal;
			raEffect=2;
			addResultAction();
			addAction();
			addCard(type);

			cName= "Divine Strike";
			cStrength=2.3f;
			cDescription = "2 physical damage [unblockable]";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			aUnblockable=true;
			addAction();
			addCard(type);

			cName= "Fervor";
			cStrength=1f;
			cDescription = "2 physical damage, -1 HP";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			aActionType=ActionType.TakeDamage;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Flagellate";
			cStrength=1f;
			cDescription = "+3 hp, -1 hp";
			cDescSize=22;
			aActionType=ActionType.Heal;
			aEffect=3;
			addAction();
			aActionType=ActionType.TakeDamage;
			aEffect=1;
			addAction();
			addCard(type);

			break;

		case Cartomancer:
			cName= "Misprint";
			cStrength=2f;
			cDescription = "2 magic damage";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Card Storm";
			cStrength=4f;
			cDescription = "1 magic damage per card in hand, discard 4 cards";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffectCondition=ActionEffectCondition.Hand;
			addAction();
			aActionType=ActionType.SelfDiscard;
			aEffect=4;
			addAction();
			addCard(type);

			cName= "Card Flick";
			cStrength=1.5f;
			cDescription = "1 physical damage [quick] [unblockable]";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			aUnblockable=true;
			aQuick=true;
			addAction();
			addCard(type);

			cName= "Paper Shield";
			cStrength=2f;
			cDescription = "Block 1 physical per card in hand";
			cDescSize=22;
			aActionType=ActionType.Block;
			aDamageType=DamageType.Physical;
			aEffectCondition=ActionEffectCondition.Hand;
			addAction();
			addCard(type);

			cName= "Counterplay";
			cStrength=2.3f;
			cDescription = "1 magic damage, block 2 magic damage";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Heart of the Cards";
			cStrength=2f;
			cDescription = "Draw a card, +1 hp";
			cDescSize=22;
			aActionType=ActionType.Heal;
			aEffect=1;
			addAction();
			aActionType=ActionType.Draw;
			aEffect=1;
			addAction();
			addCard(type);
			break;

		case Mathemagician:

			cName= "Card Counting";
			cStrength=3f;
			cDescription = "2 magic damage: look at top 2 enemy cards, discard 1";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			raType=ActionType.Scry;
			raEffect=2;
			addResultAction();
			addAction();
			addCard(type);

			cName= "Calculate";
			cStrength=2.5f;
			cDescription = "+1 hp, +2 next magic damage";
			cDescSize=22;
			aActionType=ActionType.Heal;
			aEffect=1;
			addAction();
			aActionType=ActionType.NextAttack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Explain Theorem";
			cStrength=2f;
			cDescription = "1 magic damage, enemy becomes stupid";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			raType=ActionType.Stupidity;
			raEffect=1;
			addResultAction();
			addAction();
			addCard(type);
			
			cName= "Mental Maths";
			cStrength=2.8f;
			cDescription = "2 magic damage, quick";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			aQuick=true;
			addAction();
			addCard(type);
			
			cName= "Measure Trajectory";
			cStrength=2f;
			cDescription = "Block 2 any, +1 hp";
			cDescSize=22;
			aActionType=ActionType.Block;
			aDamageType=DamageType.Either;
			aEffect=2;
			addAction();
			aActionType=ActionType.Heal;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Proof";
			cStrength=1.4f;
			cDescription = "1 magic damage [unblockable]";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			aUnblockable=true;
			addAction();
			addCard(type);

			break;

		case Artificer:

			cName= "Zap-o-matic";
			cStrength=3.1f;
			cDescription = "2 magic damage [unblockable], +1 hp";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aUnblockable=true;
			aEffect=2;
			addAction();
			aActionType=ActionType.Heal;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Spike-o-shield";
			cStrength=2.4f;
			cDescription = "Block 2 physical, 1 physical damage";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			addCard(type);
			
			cName= "Static Field";
			cStrength=2.4f;
			cDescription = "Block 1 magic, 2 magic damage";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			addCard(type);
			
			cName= "Brain-Enchane Serum";
			cStrength=1.9f;
			cDescription = "Draw 2 cards, +1 hp";
			cDescSize=22;
			aActionType=ActionType.Draw;
			aEffect=2;
			addAction();
			aActionType=ActionType.Heal;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Reflecting Ray";
			cStrength=3f;
			cDescription = "1 magic damage per damage taken this turn";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffectCondition=ActionEffectCondition.damageTaken;
			addAction();
			addCard(type);

			cName= "Thought Extractor";
			cStrength=2.5f;
			cDescription = "2 magic damage: Copy top enemy card to your hand";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			raType=ActionType.Copy;
			raEffect=1;
			addResultAction();
			addAction();
			addCard(type);


			break;

			/*
			 * 
			 * PLAYER SKILLS
			 * 
			 */


		case Arcane:

			cName= "Mind Strike";
			cStrength=2;
			cDescription = "1 magic damage, draw a card";
			cDescSize=20;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			aActionType=ActionType.Draw;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Focus";
			cStrength=2.5f;
			cDescription = "+1 on next magic attack, +1 hp, draw a card";
			cDescSize=20;
			aActionType=ActionType.NextAttack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			aActionType=ActionType.Heal;
			aEffect=1;
			addAction();
			aActionType=ActionType.Draw;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Arcane Barrage";
			cStrength=3;
			cDescription = "3 magic damage [unblockable]";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=3;
			aUnblockable=true;
			addAction();
			addCard(type);
			
			cName= "Cerebrate";
			cStrength=2.5f;
			cDescription = "+3 hp, draw 2 cards";
			cDescSize=20;
			aActionType=ActionType.Heal;
			aEffect=3;
			addAction();
			aActionType=ActionType.Draw;
			aEffect=2;
			addAction();
			addCard(type);

			break;
		case Armour:

			cName= "Repel";
			cStrength=2;
			cDescription = "Block 3 physical damage";
			cDescSize=19;
			aActionType=ActionType.Block;
			aDamageType=DamageType.Physical;
			aEffect=3;
			addAction();
			addCard(type);

			cName= "Counter";
			cStrength=2.5f;
			cDescription = "2 physical damage, block 1 physical dmg";
			cDescSize=19;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Brace";
			cStrength=3;
			cDescription = "Block all, +1 card";
			cDescSize=24;
			aActionType=ActionType.Block;
			aDamageType=DamageType.Either;
			addAction();
			aActionType=ActionType.Draw;
			aEffect=1;
			addAction();
			addCard(type);
			
			cName= "Shield Wall";
			cStrength=2.5f;
			cDescription = "block all physical dmg, +2 to next physical attack";
			cDescSize=19;
			aActionType=ActionType.Block;
			aDamageType=DamageType.Physical;
			addAction();
			aActionType=ActionType.NextAttack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			addCard(type);

			break;
		case Blade:

			cName= "Slice";
			cStrength=2;
			cDescription = "2 physical damage";
			cDescSize=24;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Dice";
			cStrength=2.5f;
			cDescription = "2 physical damage, +1 vs unblockable";
			cDescSize=20;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aBonusMechanic=BonusVsMechanic.unblockable;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Eviscerate";
			cStrength=3;
			cDescription = "3 physical damage, block 1 magic";
			cDescSize=20;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=3;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();			
			addCard(type);
			
			cName= "Bleed";
			cStrength=3;
			cDescription = "2 physical damage: 1 physical damage each turn";
			cDescSize=19;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			raType=ActionType.Effect;
			raEffectType=ResultActionEffectType.Bleed;
			raDamageType=DamageType.Physical;
			raEffect=1;
			raRounds=-1;
			addResultAction();
			addAction();
			addCard(type);
			break;
			
		case Crush:

			cName= "Bash";
			cStrength=2;
			cDescription = "1 physical damage, block 1 physical";
			cDescSize=24;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Slam";
			cStrength=2.5f;
			cDescription = "2 physical damage [unblockable]";
			cDescSize=20;
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
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=3;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();			
			addCard(type);
			
			cName= "Demolish";
			cStrength=3;
			cDescription = "4 physical damage, block 2 physical";
			cDescSize=20;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=4;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();			
			addCard(type);
			break;

		case Fire:

			cName= "Fireblast";
			cStrength=2;
			cDescription = "2 magic damage";
			cDescSize=24;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Flamelash";
			cStrength=2.5f;
			cDescription = "2 magic damage [quick]";
			cDescSize=24;
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
			
			cName= "Firestorm";
			cStrength=3;
			cDescription = "4 magic damage [unblockable]";
			cDescSize=19;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aUnblockable=true;
			aEffect=4;
			addAction();
			addCard(type);

			break;
		case Growth:

			cName= "Rekindle";
			cStrength=2;
			cDescription = "1 magic damage: +1 hp";
			cDescSize=24;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			aActionType=ActionType.Heal;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Restore";
			cStrength=2.5f;
			cDescription = "1 magic damage: if successful +2 hp";
			cDescSize=24;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			raType=ActionType.Heal;
			raEffect=2;
			addResultAction();
			addAction();
			addCard(type);

			cName= "Refresh";
			cStrength=2;
			cDescription = "Your hp becomes 6 [quick]";
			cDescSize=24;
			aActionType=ActionType.SetHP;
			aEffect=6;
			aQuick=true;
			addAction();
			addCard(type);
			
			cName= "Rejuventate";
			cStrength=2.5f;
			cDescription = "2 magic damage: if successful +3 hp";
			cDescSize=24;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			raType=ActionType.Heal;
			raEffect=3;
			addResultAction();
			addAction();
			addCard(type);

			
			break;
		case Holy:

			cName= "Holy Seal";
			cStrength=2;
			cDescription = "Block 1 any, +1 hp per dmg blocked";
			cDescSize=20;
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
			cDescription = "Block 2 damage, +1 hp per dmg blocked";
			cDescSize=20;
			aActionType=ActionType.Block;
			aDamageType=DamageType.Either;
			aEffect=2;
			addAction();
			aActionType=ActionType.Heal;
			aEffectCondition=ActionEffectCondition.damageBlocked;
			addAction();
			addCard(type);
			
			cName= "Divine Shield";
			cStrength=3;
			cDescription = "3 magic damage, block all magic";
			cDescSize=20;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=3;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Magical;
			addAction();
			addCard(type);

			break;
		case Swift:

			cName= "Shift";
			cStrength=2;
			cDescription = "1 physical damage [quick], draw a card";
			cDescSize=19;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aQuick=true;
			aEffect=1;
			addAction();
			aActionType=ActionType.Draw;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Interrupt";
			cStrength=2.5f;
			cDescription = "1 physical damage [quick], block 2 magic";
			cDescSize=20;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aQuick=true;
			aEffect=1;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();			
			addCard(type);

			cName= "Fleet-footed";
			cStrength=3;
			cDescription = "2 physical damage [quick], [unblockable]";
			cDescSize=20;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aQuick=true;
			aUnblockable=true;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Haste";
			cStrength=3;
			cDescription = "Block all, draw 2 cards";
			cDescSize=20;
			aActionType=ActionType.Block;
			aDamageType=DamageType.Either;
			addAction();
			aActionType=ActionType.Draw;
			aEffect=2;
			addAction();
			addCard(type);
			
			break;

			/*
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * ENEMY SKILLS T1
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 */

		case Feral:
			cName= "Bite";
			cStrength=1.2f;
			cDescription = "1 physical damage [unblockable]";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			aUnblockable=true;
			addAction();
			addCard(type);

			cName= "Bite";
			cStrength=1.2f;
			cDescription = "1 physical damage [unblockable]";
			cDescSize=22;
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
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Darkness";
			cStrength=1.3f;
			cDescription = "1 magic damage, block 1 magical";
			cDescSize=20;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Acrid Fog";
			cStrength=2.3f;
			cDescription = "2 magic damage, block 1 magical";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Shadow Spear";
			cStrength=2.4f;
			cDescription = "2 magic damage [unblockable]";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			aUnblockable=true;
			addAction();
			addCard(type);

			cName= "Curse";
			cStrength=2.5f;
			cDescription = "2 magic damage: enemy discards 1 card";
			cDescSize=19;
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
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Flare";
			cStrength=1.2f;
			cDescription = "1 magic damage [unblockable]";
			cDescSize=22;
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
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Blaze";
			cStrength=3f;
			cDescription = "3 magic damage";
			cDescSize=24;
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
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Neurotoxin";
			cStrength=1.2f;
			cDescription = "1 physical damage: enemy discards 1 card";
			cDescSize=20;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
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
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			raType=ActionType.Heal;
			raEffect=2;
			addResultAction();
			addAction();
			addCard(type);


			cName= "Acid Spit";
			cStrength=3f;
			cDescription = "1 physical damage, 2 magic damage";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Restore";
			cStrength=3.7f;
			cDescription = "2 magic damage: +3 hp";
			cDescSize=24;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			raType=ActionType.Heal;
			raEffect=3;
			addResultAction();
			addAction();
			addCard(type);
			break;
		case Irritable:

			cName= "Anger";
			cStrength=0.1f;
			cDescription = "1 physical damage, -1hp";
			cDescSize=22;
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
			cDescription = "2 physical damage, -1 hp";
			cDescSize=22;
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
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			aActionType=ActionType.TakeDamage;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Body Slam";
			cStrength=2.5f;
			cDescription = "3 physical damage, -1 hp";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=3;
			addAction();
			aActionType=ActionType.TakeDamage;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Body Slam";
			cStrength=2.5f;
			cDescription = "3 physical damage, -1 hp";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=3;
			addAction();
			aActionType=ActionType.TakeDamage;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			break;
		case Spooky:

			cName= "Spook";
			cStrength=1.3f;
			cDescription = "1 magic damage, block 1 physical";
			cDescSize=22;
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
			addCard(type);

			cName="Uhh..";
			cDescription="You scratch your head";
			cDescSize=24;
			addCard(type);

			cName="Uhh..";
			cDescription="You scratch your head";
			cDescSize=24;
			addCard(type);

			cName="Uhh..";
			cDescription="You scratch your head";
			cDescSize=24;
			addCard(type);

			cName="Uhh..";
			cDescription="You scratch your head";
			cDescSize=24;
			addCard(type);

			break;
		case Armed:
			cName= "Strike";
			cStrength=1f;
			cDescription = "1 physical damage";
			cDescSize=24;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Head blow";
			cStrength=1.2f;
			cDescription = "1 physical damage: enemy discards a card";
			cDescSize=24;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			raType=ActionType.Discard;
			raEffect=1;
			addResultAction();
			addAction();
			addCard(type);

			cName= "Parry";
			cStrength=2.3f;
			cDescription = "2 physical damage, block 1 physical";
			cDescSize=20;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Stagger";
			cStrength=2.5f;
			cDescription = "2 physical damage: enemy discards a card";
			cDescSize=24;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			raType=ActionType.Discard;
			raEffect=1;
			addResultAction();
			addAction();
			addCard(type);

			cName= "Swipe";
			cStrength=3.8f;
			cDescription = "3 physical damage, block 2 physical";
			cDescSize=19;
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

			/*
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * ENEMY SKILLS T2
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 */

		case Rage:
			cName= "Lunge";
			cStrength=2.3f;
			cDescription = "2 physical damage unblockable";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			aUnblockable=true;
			addAction();
			addCard(type);
			
			cName= "Thrash";
			cStrength=2.4f;
			cDescription = "3 physical damage, take 1 damage";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=3;
			addAction();
			aActionType=ActionType.TakeDamage;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Thrash";
			cStrength=2.4f;
			cDescription = "3 physical damage, take 1 damage";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=3;
			addAction();
			aActionType=ActionType.TakeDamage;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Maul";
			cStrength=3.9f;
			cDescription = "4 physical damage, take 1 damage";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=4;
			addAction();
			aActionType=ActionType.TakeDamage;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);
			break;
			
		case Demonic:
			
			cName= "Sweeping Blast";
			cStrength=2.3f;
			cDescription = "2 magic damage, block 1 magic";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			addCard(type);
			
			cName= "Mind Blast";
			cStrength=3f;
			cDescription = "3 magic damage";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=3;
			addAction();
			addCard(type);
			
			cName= "Dark Pact";
			cStrength=3.2f;
			cDescription = "3 magic damage, block 1 magic";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=3;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Fiery Burst";
			cStrength=4f;
			cDescription = "4 magic damage";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=4;
			addAction();
			addCard(type);
			break;


		case Sorcery:
			cName= "Dark Strike";
			cStrength=2.3f;
			cDescription = "2 magic damage: +1 hp";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			raType=ActionType.Heal;
			raEffect=1;
			addResultAction();
			addAction();
			addCard(type);

			cName= "Chaos";
			cStrength=3.3f;
			cDescription = "3 magic damage: opponent discards a card";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=3;
			raType=ActionType.Discard;
			raEffect=1;
			addResultAction();
			addAction();
			addCard(type);

			cName= "Black Strike";
			cStrength=3.5f;
			cDescription = "3 magic damage: next card is hidden";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=3;
			raType=ActionType.Effect;
			raEffectType=ResultActionEffectType.Conceal;
			addResultAction();
			addAction();
			addCard(type);

			cName= "Pure Magic";
			cStrength=4f;
			cDescription = "4 magic damage";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=4;
			addAction();
			addCard(type);
			break;
			
		case Venom:

			cName= "Venomous Bite";
			cStrength=2.3f;
			cDescription = "2 phsyical damage [unblockable]";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			aUnblockable=true;
			addAction();
			addCard(type);

			cName= "Spider Bite";
			cStrength=2.5f;
			cDescription = "2 phsyical damage: opponent discards a card";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			raType=ActionType.Discard;
			raEffect=1;
			addResultAction();
			addAction();
			addCard(type);

			cName= "Acid Spit";
			cStrength=3f;
			cDescription = "2 phsyical damage, 1 magic damage";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			addCard(type);
			
			cName= "Disease";
			cStrength=4;
			cDescription = "2 physical damage: 1 physical damage each turn";
			cDescSize=19;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			raType=ActionType.Effect;
			raEffectType=ResultActionEffectType.Poison;
			raDamageType=DamageType.Physical;
			raEffect=1;
			raRounds=-1;
			addResultAction();
			addAction();
			addCard(type);


			break;

		case Ghoulish:

			cName= " Pain";
			cStrength=2;
			cDescription = "2 magic damage";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Torment";
			cStrength=3.2f;
			cDescription = "3 magic damage, block 1 physical";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=3;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Siphon";
			cStrength=3.8f;
			cDescription = "2 magic damage, +1 HP/damage dealt";
			cDescSize=21;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			aActionType=ActionType.Heal;
			aEffectCondition= ActionEffectCondition.damageDealt;
			addAction();
			addCard(type);

			cName= "Siphon";
			cStrength=3.8f;
			cDescription = "2 magic damage, +1 HP/damage dealt";
			cDescSize=21;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			aActionType=ActionType.Heal;
			aEffectCondition= ActionEffectCondition.damageDealt;
			addAction();
			addCard(type);

			break;

		case Burly:

			cName= "Beat";
			cStrength=2.3f;
			cDescription = "2 physical damage, block 1 physical";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Pummel";
			cStrength=3f;
			cDescription = "3 physical damage";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=3;
			addAction();
			addCard(type);

			cName= "Recharge";
			cStrength=3f;
			cDescription = "Block 2 physical, +2 hp";
			cDescSize=20;
			aActionType=ActionType.Block;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			aActionType=ActionType.Heal;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Relentless";
			cStrength=4f;
			cDescription = "4 physical damage";
			cDescSize=22;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=4;
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
		aBonusSituation=null;
		resultActions.clear();
	}

	static void addAction(){
		cActions.add(new Action(aActionType, aDamageType, aUnblockable, aQuick, aEffectCondition, aEffect, aBonusMechanic, aBonusSpecies, aBonusSituation, resultActions==null?null:(ArrayList<ResultAction>) resultActions.clone()));		
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
		cImage=null;
		cStrength=0;
		cDescription=null;
		cDescSize=0;
		cActions.clear();
	}

	private static void addCard(SkillType type) {
		if(type==SkillType.Flame)cImage=SkillType.Fire;
		Skill.get(type).addCard(makeCard());
	}

	private static Card makeCard(){
		Card c=new Card(cName, cStrength, cDescription, cDescSize, cImage, cActions==null?null:(ArrayList<Action>)cActions.clone());
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
		output+=Json.endList(false);
		output+=Json.endList(false);
		output+=Json.endEnclose(false);

		return output;
	}



	public static String jsonSkills(SkillType type){
		String output="";
		output+=Json.startArray(type.toString());
		for(Card c:Skill.get(type).getCards(false)){
			output+=Json.enclose();
			output+=c.toJson();
			output+=Json.endEnclose(true);
		}
		output=Json.removeComma(output);
		output+=Json.endArray(true);
		return output;
	}


	public static String cardDetails(Skill.SkillType[] types){
		String output="";
		for(SkillType st:types){
			System.out.println("----"+st+"----");
			int index=1;
			for(Card c:Skill.get(st).getCards(false)){
				System.out.println("["+index+"]: "+c.name+" -> "+c.description+" ("+c.strength+")");
				index++;
			}	
		}

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
		BonusVsSituation bonusSituation;
		ArrayList<ResultAction> resultActions;
		public Action(ActionType aType, DamageType dtType, boolean unblockable, boolean quick, ActionEffectCondition effectCondition, int effect, BonusVsMechanic bonusVsMechanic, BonusVsSpecies bonusVsSpecies, BonusVsSituation bonusSituation, ArrayList<ResultAction> resultActions){
			this.aType=aType;
			this.dType=dtType;
			this.unblockable=unblockable;
			this.quick=quick;
			this.effect=effect;
			this.effectCondition=effectCondition;
			this.bonusVsMechanic=bonusVsMechanic;
			this.bonusVsSpecies=bonusVsSpecies;
			this.bonusSituation=bonusSituation;
			this.resultActions=resultActions;
		}

		public enum ActionType{
			Attack, Heal, Block, Discard, TakeDamage, Draw, NextAttack, Effect, SetHP, Withstand, Copy, Scry, Stupidity, SelfDiscard
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

			if(aType==ActionType.Withstand){
				output+=Json.addKey("type", "Effect", true);
				output+=Json.addKey("effectType", "Trait", true);
				output+=Json.addKey("effectTrait", ActionType.Withstand.toString(), true);
				output+=Json.addKey("target", "self", true);
			}

			else if(aType!=null)output+=Json.addKey("type", aType.toString(), true);
			if(dType!=null)output+=Json.addKey("damageType", dType.toString(), true);
			if(effect>0){
				switch(aType){
				case Attack:
				case Block:
				case NextAttack:
				case Effect: 
				case TakeDamage:
					output+=Json.addKey("damage", effect, true);
					break;
				case Discard:
				case Draw:
				case Heal:
				case SetHP:
				case SelfDiscard:
					output+=Json.addKey("quantity", effect, true);
					break;	
				case Withstand:
					output+=Json.addKey("rounds", effect, true);
					break;

				}
			}
			if(effectCondition!=null) output+=Json.addKey("quantityCondition", effectCondition.toString(), true);
			if(unblockable)output+=Json.addKey("unblockable", true, true);
			if(quick)output+=Json.addKey("quick", true, true);
			if(bonusVsMechanic!=null||bonusVsSpecies!=null||bonusSituation!=null){
				output+=Json.addKey("versesType", "ANY", true);
				if(bonusVsMechanic!=null) output+=Json.addKey("versesKeyword", bonusVsMechanic.toString(), true);
				if(bonusVsSpecies!=null) output+=Json.addKey("versesClass", bonusVsSpecies.toString(), true);
				if(bonusSituation!=null) output+=Json.addKey("versusSituation", bonusSituation.toString(), true);
			}
			if(resultActions.size()>0){
				output+=Json.startArray("resultActions");
				for(ResultAction ra:resultActions){
					output+=ra.toJson();
				}
				output=Json.removeComma(output);
				output+=Json.endArray(true);
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
			Fire, StopPlayerFromPlayingPhysical, StopPlayerFromPlayingMagical, StopPlayerFromPlayingBlocks, Conceal, Stupidity, Poison, Bleed
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
			if(type==ActionType.Scry){
				output+=Json.addKey("type", "PickDiscard", true);
			}
			else if (type!=ActionType.Stupidity)output+=Json.addKey("type", type.toString(), true);
			if(effectType!=null&&
					effectType!=ResultActionEffectType.Conceal&&
					effectType!=ResultActionEffectType.Stupidity)output+=Json.addKey("effectType", effectType.toString(), true);
			if(damageType!=null)output+=Json.addKey("damageType", damageType.toString(), true);
			if(rounds!=0)output+=Json.addKey("rounds", rounds, true);

			if(type==ActionType.Effect){
				switch(effectType){
				case Conceal:
					output += Json.addKey("effectType", "Trait", true);
					output += Json.addKey("effectTrait", "Conceal", true);
					output += Json.addKey("target", "self", true);
					output += Json.addKey("rounds", 1, true);
					output += Json.addKey("ignoreFirstTick", true, true);
					break;
				case Fire: case Poison: case Bleed:
					output+=Json.addKey("damage", actionEffect, true);
					break;
				case StopPlayerFromPlayingBlocks:
					break;
				case StopPlayerFromPlayingMagical:
					break;
				case StopPlayerFromPlayingPhysical:
					break;
				default:
					break;

				}
			}
			if(actionEffect!=0){
				switch(type){
				case Attack:
				case Block:
				case NextAttack:
				case TakeDamage:
					output+=Json.addKey("damage", actionEffect, true);
					break;
				case Scry:
					output+=Json.addKey("target", "Deck", true);
					output+=Json.addKey("quantity", 1, true);
					output+=Json.addKey("range", actionEffect, true);
					break;
				case Copy:
					output+=Json.addKey("target", "Deck", true);
					output+=Json.addKey("quantity", actionEffect, true);
					break;
				case Discard:
				case Draw:
				case Heal:
					output+=Json.addKey("quantity", actionEffect, true);
					break;
				case Stupidity:
					output+=Json.addKey("type", "Skill", true);
					output+=Json.addKey("damageType", "Stupidity", true);
					output+=Json.addKey("target", "enemy", true);
					output+=Json.addKey("quantity", actionEffect, true);
					break;
				}
			}



			output=Json.removeComma(output);
			output+=Json.endEnclose(true);
			return output;

		}
	}
}
