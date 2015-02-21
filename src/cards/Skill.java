package cards;

public class Skill {
	public SkillType type;
	public int level;
	
	public enum SkillType{
		Fire, Arcane, Holy, Growth, Armour, Blade, Crush, Swift,
		
		Feral, Spooky, Rage, Stupidity, Weapon, Flame, Nature, Death
		
	}
	
	public static SkillType[] monsterSkills=new SkillType[]{SkillType.Feral, SkillType.Spooky, SkillType.Rage, SkillType.Stupidity, SkillType.Weapon, SkillType.Flame, SkillType.Nature, SkillType.Death};
	public static SkillType[] playerSkills=new SkillType[]{SkillType.Fire, SkillType.Arcane, SkillType.Holy, SkillType.Growth, SkillType.Armour, SkillType.Blade, SkillType.Crush, SkillType.Swift};
	
	public static Skill fire= new Skill(SkillType.Fire);
	public static Skill arcane= new Skill(SkillType.Arcane);
	public static Skill holy= new Skill(SkillType.Holy);
	public static Skill growth= new Skill(SkillType.Growth);
	public static Skill armour= new Skill(SkillType.Armour);
	public static Skill blade= new Skill(SkillType.Blade);
	public static Skill crush= new Skill(SkillType.Crush);
	public static Skill swift= new Skill(SkillType.Swift);
	
	private Skill(SkillType type){
		this.type=type;
	}
	
	public Skill asLevel(int level){
		Skill ps = new Skill(type);
		ps.level=level;
		return ps;
	}
	
	public String toJson(){
		return "\""+type+"\" : "+level;
	}
	
	public String toString(){
		return type+":"+level;
	}
}