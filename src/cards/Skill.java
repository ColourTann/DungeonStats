package cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Skill {
	public SkillType type;
	public int level=-1;
	ArrayList<Card> cards= new ArrayList<>();
	public enum SkillType{
		Fire, Arcane, Holy, Growth, Armour, Blade, Crush, Swift,
		
		Feral, Spooky, Irritable, Stupidity, Armed, Flame, Nature, Death, 
		Rage, Demonic, Sorcery, Venom, Ghoulish, Burly,
		
		Chump, Henchman, Cat_Burglar, Apprentice, Mime, Ranger, Barbarian, Alchemist, Shapeshifter, Troubador, Most_Holy_Knight_Templar, Cartomancer, Mathemagician, Artificer,  
		
		Trickster, Warrior, Crone, HealAid
	}
	
	public static SkillType[] monsterSkills=new SkillType[]{SkillType.Feral, SkillType.Spooky, SkillType.Irritable, SkillType.Stupidity, SkillType.Armed, SkillType.Flame, SkillType.Nature, SkillType.Death,
		SkillType.Rage, SkillType.Demonic, SkillType.Sorcery, SkillType.Venom, SkillType.Ghoulish, SkillType.Burly};
	public static SkillType[] playerSkills=new SkillType[]{SkillType.Fire, SkillType.Arcane, SkillType.Holy, SkillType.Armour, SkillType.Blade, SkillType.Crush, SkillType.Swift, SkillType.Growth,
		SkillType.Trickster, SkillType.Warrior, SkillType.Crone, SkillType.HealAid};
	public static SkillType[] heroDecks = new SkillType[]{SkillType.Chump, SkillType.Henchman, SkillType.Apprentice, SkillType.Alchemist, SkillType.Cat_Burglar, SkillType.Shapeshifter, SkillType.Ranger, SkillType.Barbarian};
	
	public static Skill fire= new Skill(SkillType.Fire);
	public static Skill arcane= new Skill(SkillType.Arcane);
	public static Skill holy= new Skill(SkillType.Holy);
	public static Skill growth= new Skill(SkillType.Growth);
	public static Skill armour= new Skill(SkillType.Armour);
	public static Skill blade= new Skill(SkillType.Blade);
	public static Skill crush= new Skill(SkillType.Crush);
	public static Skill swift= new Skill(SkillType.Swift);
	
//	public static Skill mFeral= new Skill(SkillType.Feral);
//	public static Skill mSpooky= new Skill(SkillType.Spooky);
//	public static Skill mRage= new Skill(SkillType.Rage);
//	public static Skill mStupid= new Skill(SkillType.Stupidity);
//	public static Skill mWeapon= new Skill(SkillType.Weapon);
//	public static Skill mFlame= new Skill(SkillType.Flame);
//	public static Skill mNature= new Skill(SkillType.Nature);
//	public static Skill mDeath= new Skill(SkillType.Death);
	
	private static HashMap<SkillType, Skill> skillMap = new HashMap<>();
	
	public static void setupMap(){
		for(SkillType type:SkillType.values()){
			skillMap.put(type, new Skill(type));
		}
	}
	
	public static Skill get(SkillType type){
		return skillMap.get(type);
	}

	public Skill(SkillType type){
		this.type=type;
	}
	
	public Skill(SkillType type, float[] strengths){
		this.type=type;
		
	}
	
	public void addCard(Card c){
		cards.add(c);
	}
	
	public Skill asLevel(int level){
		Skill ps = new Skill(type);
		ps.cards=cards;
		ps.level=level;
		return ps;
	}
	
	public float[] getStrengths(){
		if(level>cards.size()){
			System.out.println("Skill doesn't contain "+level);
			return null;
		}
		
		return null;
	}
	
	public String toJson(){
		return "\""+type+"\" : "+level;
	}
	
	public String toString(){
		return type+":"+level;
	}

	public List<Card> getCards(boolean skilled) {
		if(level==-1)level=cards.size();
		return cards.subList(skilled?2:0, level);
	}
}
