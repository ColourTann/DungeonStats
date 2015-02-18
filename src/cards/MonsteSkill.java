package cards;

import java.util.Arrays;

public class MonsteSkill {
	
	
	
	
	
	private float[] strengths;
	public MonsteSkill(float[] strengths){
		this.strengths=strengths;
	}
	public float[] getStrengths(int level){
		if(level>strengths.length){
			System.out.println("Skill doesn't contain "+level);
			return null;
		}
		if(level==strengths.length)return strengths;
		return Arrays.copyOfRange(strengths, 0, level);
	}
}
