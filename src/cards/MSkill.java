package cards;

import java.util.Arrays;

import fighter.player.Skill;
import fighter.player.Skill.SkillType;

public class MSkill {
	

	
	
	private float[] strengths;
	private int level=0;
	String name;
	public MSkill(String name, float[] strengths){
		this.name=name;
		this.strengths=strengths;
	}
	
	public MSkill asLevel(int level){
		MSkill result = new MSkill(name, strengths);
		result.level=level;
		return result;
	}
	
	public float[] getStrengths(){
		if(level>strengths.length){
			System.out.println("Skill doesn't contain "+level);
			return null;
		}
		if(level==strengths.length)return strengths;
		return Arrays.copyOfRange(strengths, 0, level);
	}

	public String toJson() {
		return "\""+name+"\" : "+level;
	}
}
