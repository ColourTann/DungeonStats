package fighter.player;

import cards.Skill;
import cards.Skill.SkillType;
import fighter.Fighter;

public abstract class Hero extends Fighter{
	//public static Skill playerSkill = new Skill(SkillType.Warrior, new float[]{2,2.5f,3});
	private int level;
	public Hero(String name){
		super(name);
		level=1;
		setupBasicDeck();
	}
	protected abstract void setupBasicDeck();
	public void levelUp(){
		level++;
	}
	public int getHP(){
		return level + 4;
	}
}
