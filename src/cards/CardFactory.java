package cards;

import java.lang.instrument.ClassFileTransformer;
import java.util.ArrayList;
import java.util.HashMap;

import json.Json;
import cards.CardFactory.Action.ActionType;
import cards.CardFactory.Action.DamageType;
import cards.Skill.SkillType;
import fighter.player.Warrior;

public class CardFactory {

	public enum Icon{
		Default_icon, STAT_STRENGTH, STAT_MAGIC, STAT_DREAD, STAT_HEALTH
	}

	public enum BonusVsSpecies{
		Undead
	}

	public enum BonusVsMechanic{
		Unblockable
	}
	
	public enum ActionEffectCondition{
		damageDealt
	}

	private static String cName;
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
	private static int aBonusAmount;

	private static ActionType raType;
	private static int raEffect;
	
	private static ArrayList<Card> cards=new ArrayList<>();

	public enum HeroClass{Warrior}
	private static HashMap<HeroClass, ArrayList<Card>> heroMap = new HashMap<>();
	public static void setupClass(HeroClass type){
		heroMap.put(type, new ArrayList<>());
		resetAction();
		resetCard();

		switch(type){
		case Warrior:
			cName= "Hit";
			cDescription = "1 physical damage";
			cDescSize=22;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Hit";
			cDescription = "1 physical damage";
			cDescSize=22;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);

			cName= "Crack";
			cDescription = "1 physical damage, unblockable";
			cDescSize=22;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			aUnblockable=true;
			addAction();
			addCard(type);

			cName= "Rush";
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
			cDescription = "2 physical damage";
			cDescSize=24;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addAction();
			addCard(type);

			cName= "Clang!";
			cDescription = "Block all physical damage";
			cDescSize=22;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Block;
			aDamageType=DamageType.Physical;
			addAction();
			addCard(type);

			break;
		}

	}
	
	private static HashMap<SkillType, ArrayList<Card>> skillMap = new HashMap<>();
	
	public static void setupSkill(SkillType type){
		skillMap.put(type, new ArrayList<>());
		resetAction();
		resetCard();
		
		switch (type){
		case Arcane:
			break;
		case Armour:
			break;
		case Blade:
			break;
		case Crush:
			break;
		case Fire:
			break;
		case Growth:
			break;
		case Holy:
			break;
		case Swift:
			break;
	
		case Feral:
			cName= "Bite";
			cDescription = "1 physical damage, unblockable";
			cDescSize=22;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			aUnblockable=true;
			addAction();
			addCard(type);
			
			cName= "Bite";
			cDescription = "1 physical damage, unblockable";
			cDescSize=22;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			aUnblockable=true;
			addAction();
			addCard(type);
			
			cName= "Charge";
			cDescription = "2 phsyical damage, block 1 magic";
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
			cDescription = "2 phsyical damage, block 1 magic";
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
			
			break;
		case Flame:

			cName= "Spark";
			cDescription = "1 magical damage";
			cDescSize=24;
			cIcon=Icon.STAT_MAGIC;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			addAction();
			addCard(type);
			
			cName= "Flare";
			cDescription = "1 magical damage, unblockable";
			cDescSize=22;
			cIcon=Icon.STAT_MAGIC;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=1;
			aUnblockable=true;
			addAction();
			addCard(type);
			
			cName= "Fiery Weapon";
			cDescription = "1 magical damage, 1 physical damage";
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
			cDescription = "2 magical damage";
			cDescSize=24;
			cIcon=Icon.STAT_MAGIC;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			addCard(type);
			
			cName= "Blaze";
			cDescription = "3 magical damage";
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
			cDescription = "1 physical damage";
			cDescSize=24;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);
			
			cName= "Neurotoxin";
			cDescription = "1 magical damage: enemy discards 1 card";
			cDescSize=19;
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
			cDescription = "1 magical damage: +2 hp";
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
			cDescription = "2 magic damage, block 2 damage";
			cDescSize=22;
			cIcon=Icon.STAT_MAGIC;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addCard(type);
			
			cName= "Entangle";
			cDescription = "2 magic damage, block 2 damage";
			cDescSize=22;
			cIcon=Icon.STAT_MAGIC;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Magical;
			aEffect=2;
			addAction();
			aActionType=ActionType.Block;
			aDamageType=DamageType.Physical;
			aEffect=2;
			addCard(type);
			break;
		case Rage:
			
			cName= "Anger";
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
			cDescription = "2 physical damage, unblockable";
			cDescSize=22;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			aUnblockable=true;
			addAction();
			addCard(type);
			
			cName= "Wrath";
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
			cDescription = "1 physical damage";
			cDescSize=24;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);
			
			cName= "Strike";
			cDescription = "1 physical damage";
			cDescSize=24;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=1;
			addAction();
			addCard(type);
			
			cName= "Parry";
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
			cDescription = "2 physical damage, unblockable";
			cDescSize=22;
			cIcon=Icon.STAT_STRENGTH;
			aActionType=ActionType.Attack;
			aDamageType=DamageType.Physical;
			aEffect=2;
			aUnblockable=true;
			addAction();
			addCard(type);
			
			cName= "Swipe";
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
		}
	}

	

	static void resetAction(){
		aActionType=null;
		aDamageType=null;
		aQuick=false;
		aUnblockable=false;
		aEffect=0;
		aEffectCondition=null;
		aBonusAmount=0;
		aBonusMechanic=null;
		aBonusSpecies=null;
		resultActions.clear();
	}
	
	static void addAction(){
		cActions.add(new Action(aActionType, aDamageType, aUnblockable, aQuick, aEffectCondition, aEffect, aBonusMechanic, aBonusSpecies, aBonusAmount, resultActions==null?null:(ArrayList<ResultAction>) resultActions.clone()));		
		resetAction();
	}
	
	static void resetResultAction(){
		raEffect=0;
		raType=null;
	}
	
	static void addResultAction(){
		resultActions.add(new ResultAction(raType, raEffect));
		resetResultAction();
	}

	static void resetCard(){
		cName=null;
		cDescription=null;
		cDescSize=0;
		cIcon=null;
		cActions.clear();
	}

	private static void addCard(SkillType type) {
		skillMap.get(type).add(makeCard());
	}
	
	private static void addCard(HeroClass type){
		heroMap.get(type).add(makeCard());
	}
	
	private static Card makeCard(){
		Card c=new Card(cName, cDescription, cDescSize, cIcon, cActions==null?null:(ArrayList<Action>) cActions.clone());
		resetCard();
		return c;
	}

	public static String jsonAllSkills(){
		String output= "";
		output+=Json.enclose();
		output+=Json.startList("BattleCards");
		output+=Json.startList("Skills");
		output+=jsonSkills(SkillType.Nature);
		output=Json.removeComma(output);
		output+=Json.endList();
		output=Json.removeComma(output);
		output+=Json.endList();
		output=Json.removeComma(output);
		output+=Json.endEnclose();
		output=Json.removeComma(output);
		return output;
	}
	
	public static String jsonSkills(SkillType type){
		String output="";

		setupSkill(type);
		output+=Json.startArray(type.toString());

		for(Card c:skillMap.get(type)){
			output+=Json.enclose();
			output+=c.toJson();
			output+=Json.endEnclose();
		}
		output=Json.removeComma(output);
		output+=Json.endArray();
		return output;
	}
	
	public static String jsonAllClasses(){
		String output="";

		output+=Json.enclose();
		output+=Json.startList("Classes");

		setupClass(HeroClass.Warrior);

		output+=Json.startArray("Warrior");
		for(Card c:heroMap.get(HeroClass.Warrior)){
			output+=Json.enclose();
			output+=c.toJson();
			output+=Json.endEnclose();
		}
		output=Json.removeComma(output);
		output+=Json.endArray();
		output=Json.removeComma(output);
		output+=Json.endList();
		output=Json.removeComma(output);
		output+=Json.endEnclose();
		output=Json.removeComma(output);


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
		int bonusAmount;
		ArrayList<ResultAction> resultActions;
		public Action(ActionType aType, DamageType dtType, boolean unblockable, boolean quick, ActionEffectCondition aEffectCondition, int effect, BonusVsMechanic bonusVsMechanic, BonusVsSpecies bonusVsSpecies, int bonusAmount, ArrayList<ResultAction> resultActions){
			this.aType=aType;
			this.dType=dtType;
			this.unblockable=unblockable;
			this.quick=quick;
			this.effect=effect;
			this.effectCondition=aEffectCondition;
			this.bonusVsMechanic=bonusVsMechanic;
			this.bonusVsSpecies=bonusVsSpecies;
			this.bonusAmount=bonusAmount;
			this.resultActions=resultActions;
		}

		public enum ActionType{
			Attack, Heal, Block, Discard, TakeDamage
		}
		public enum DamageType{
			Physical, Magical
		}
		public String toJson() {
			String output="";

			if(aType!=null)output+=Json.addKey("type", aType.toString());
			if(dType!=null)output+=Json.addKey("damageType", dType.toString());
			if(effect>0){
				switch(aType){
				case Attack:
					output+=Json.addKey("damage", effect);
					break;
				case Block:
					output+=Json.addKey("damage", effect);
					break;
				case Discard:
					output+=Json.addKey("quantity", effect);
					break;
				case Heal:
					output+=Json.addKey("quantity", effect);
					break;
				default:
					break;	
				}
			}
			if(aEffectCondition!=null) output+=Json.addKey("quantityCondition", effectCondition.toString());
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
			output=Json.removeComma(output);
			return output;
		}
	}
	
	static class ResultAction{
		ActionType type;
		int actionEffect;
		public ResultAction(ActionType type, int actionEffect){
			this.type=type;
			this.actionEffect=actionEffect;
		}
		public String toJson(){
			String output="";
			output+=Json.enclose();
			output+=Json.addKey("type", type.toString());
			output+=Json.addKey("quantity", actionEffect);
			output=Json.removeComma(output);
			output+=Json.endEnclose();
			return output;
		}
	}
}
