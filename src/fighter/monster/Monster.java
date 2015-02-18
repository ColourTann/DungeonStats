package fighter.monster;

import cards.Card;
import cards.Skill;
import fighter.Fighter;


public class Monster extends Fighter{

	
	public static Skill feral = new Skill(new float[]{
			1.2f,
			1.2f,
			2.3f,
			2.3f,
			3});
	
	public static Skill spooky = new Skill(new float[]{
			1.3f,
			1.8f,
			1.8f,
			2.6f,
			3.5f});
	
	public static Skill rage = new Skill(new float[]{
			0.1f,
			1.3f,
			1.3f,
			2.3f,
			3.3f});
	
	public static Skill stupidity= new Skill(new float[]{
			0,
			0,
			0,
			0,
			0});
	
	public static Skill weapon = new Skill(new float[]{
			1,
			1,
			2.3f,	
			2.3f,
			3.8f});
	
	public static Skill flame = new Skill(new float[]{
			1,
			1.2f,
			2,
			2,
			3});
	
	public static Skill nature = new Skill(new float[]{
			1,
			1.2f,
			2,
			3.5f,
			3.5f});
	
	public static Skill death= new Skill(new float[]{
			1,
			1.5f,
			2,
			2.8f,
			3});
	
	public static Skill ferocious = new Skill(new float[]{
			2.5f,
			3.5f,
			4});
	
	public static Skill demonic = new Skill(new float[]{
			1.8f,
			2.8f,
			4});
	
	public static Skill sorcery = new Skill(new float[]{
			2.2f,
			2.5f,
			4});
	
	public enum Species{undead, demonic, beast}
	
	private int hp;
	
	public Monster(String name, int hp, float[]... skills){
		super(name);
		setup(hp, skills);
	}
	
	public Monster(String name, int hp, Trait[] traits, float[]... skills){
		super(name);
		setup(hp, skills);
	}
	
	public Monster(String name, Species species, int hp, Trait[] traits, float[]... skills){
		super(name);
		setup(hp, skills);
		setup(traits);
	}
	
	
	
	
	public void setup(int hp, float[]... skills){
		this.hp=hp;
		for(float cardStats[]:skills){
			for(float cardStrength:cardStats){
				addCard(new Card(cardStrength));
			}
		}
	}
	
	public void setup(Trait[] traits){
		this.traits=traits;
	}

	@Override
	public int getHP() {
		return hp;
	}

}
