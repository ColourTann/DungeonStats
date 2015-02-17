package cards;

import java.util.Arrays;

public class Skill {
	
	
	
	
	
	private float[] strengths;
	public Skill(float[] strengths){
		this.strengths=strengths;
	}
	public float[] getStrengths(int level){
		if(level>=strengths.length){
			System.out.println("Skill doesn't contain "+level);
			return null;
		}
		return Arrays.copyOfRange(strengths, 0, level);
	}
}
