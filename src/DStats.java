import fighter.Fighter.*;
import item.Item;
import item.Item.UnlockedBy;
import java.util.ArrayList;
import room.Room;
import cards.CardFactory;
import cards.Skill;
import cards.Skill.SkillType;
import dungeon.Adventure;
import dungeon.Dungeon;
import dungeon.Tile;
import fighter.monster.Monster;
import fighter.monster.MonsterFactory;
import fighter.monster.Monster.*;
import fighter.player.Hero;
import fighter.player.HeroFactory;

public class DStats {
	public static void main(String[] args) {
		setup();
		System.out.println(Adventure.jsonAdventures());
//		System.out.println(CardFactory.jsonAllSkills(true));
//		Item.printAllUnlocks();
//		Item.printAll();
//		Item.printItemTypeAmounts();
//		System.out.println(Item.analyseTier(0));
//		System.out.println(Item.analyseUnlock(UnlockedBy.Leather));
		
//		for(int i=1;i<=5;i++) Item.analyseLevel(i);
//		Item.analyseLevel(2, true, true);
		
//		MonsterFactory.jsonAll();
//		MonsterFactory.printAll();
//		MonsterFactory.sortMonsters();
//		printStrengths(3);
		

//		System.out.println(Room.JsonAllRooms());
//		System.out.println(HeroFactory.toJson());
//		System.out.println(HeroFactory.analyseClasses());
	}

	public static void setup(){
		Skill.setupMap();
		Item.setup();
		CardFactory.makeAllCards();
		MonsterFactory.setup();
		Room.setupRooms();
		HeroFactory.init();
		Tile.setup();
		Adventure.setup();
		
	}
}
