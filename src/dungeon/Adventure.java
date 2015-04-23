package dungeon;

import java.util.ArrayList;

import dungeon.BossChat.BossSpeech;
import dungeon.BossChat.DelayEffect;
import dungeon.BossChat.Func;
import dungeon.BossChat.PostFunc;
import dungeon.BossChat.Trigger;
import dungeon.Dungeon.TerrainType;
import dungeon.DungeonLayout.TileLocation;
import dungeon.Hand.DungeonCard;
import dungeon.Hand.DungeonCardType;
import dungeon.Objective.ObjectiveType;
import dungeon.Tile.TileName;
import fighter.monster.Monster;
import fighter.monster.MonsterFactory;
import json.Json;

public class Adventure {
	
	String name, description, icon;
	int mapX, mapY;
	ArrayList<Dungeon> dungeons;
	@SuppressWarnings("unchecked")
	public Adventure(String name, String description, String icon, int mapX, int mapY, ArrayList<Dungeon> dungeons) {
		this.name=name; this.description=description; this.icon=icon;
		this.mapX=mapX; this.mapY=mapY;
		this.dungeons=(ArrayList<Dungeon>) dungeons.clone();
	}

	public String toJson(){
		String output="";
		output+= Json.startList(name);
		output+= Json.addKey("Description", description, true);
		output+= Json.addKey("Icon", icon, true);
		output+= Json.addKey("mapX", mapX, true);
		output+= Json.addKey("mapY", mapY, true);
		output+= Json.startArray("Quests"); 
		for(int i=0;i<dungeons.size();i++){
			Dungeon d = dungeons.get(i);
			output += d.toJson();
			if(i<dungeons.size()-1) output = Json.addComma(output);
		}
		output += Json.endArray(false);
		output += Json.endList(false);
		return output;
	}
	
	
	public static String jsonAdventures(){
		String output = "";
		output+=Json.enclose();
		output+=Json.startList("QuestLines");
		for(int i=0;i<adventures.size();i++){
			Adventure a = adventures.get(i);
			output += a.toJson();
			if(i<adventures.size()-1)output=Json.addComma(output);
		}
		output+=Json.endList(false);
		output+=Json.endEnclose(false);
		return output;
	}
	
	static String aName, aDescription; static int aReward;
	static TerrainType aTerrainType;
	static String aBoss, aBossName; static BossChat[] aBossChats;
	static TileName aStartingTile; static Hand aStartingHand; static DungeonLayout aLayout;
	static Objective[] aObjectives; static int aTurnLimit;
	static ArrayList<Monster> aMonsters; 
	static String aAdvName, aAdvDescription, aAdvIcon;
	static int aAdvX, aAdvY;
	
	
	static ArrayList<Dungeon> aDungeons = new ArrayList<>();
	public static void setup(){
		aName="Fiery Foray";
		aDescription="Scope out Embro's defences";
		aReward=100;
		aTerrainType=TerrainType.stone;
		aBoss="Fire Demon";
		aBossName="Embro";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.blah, new BossSpeech[]{
						new BossSpeech("HEY! What are you doing in my Evil Entrance Hall?", Func.emote, false),
						new BossSpeech("Oh well, my imps will make short work of you!", Func.emote, false),
				}, PostFunc.FinishBossChat, 
				-1, 1, 1, DelayEffect.APPEAR),
				new BossChat(Trigger.flib, new BossSpeech[]{
						new BossSpeech("You're not supposed to be able to beat them!", Func.emote, false),
						new BossSpeech("I should have invested in some better security...", Func.emote, false),	
				}, PostFunc.FinishBossChat, 
				2, -1, 1, DelayEffect.APPEAR),
		};
		aStartingTile=TileName.corr_grate_n;
		aStartingHand = new Hand(new DungeonCard[]{
				new DungeonCard(DungeonCardType.TILE, "corr_thin_nesw"),
				new DungeonCard(DungeonCardType.TREASURE, "Gold Coin"),
				new DungeonCard(DungeonCardType.MONSTER, "Rubber Ducky"),
				new DungeonCard(DungeonCardType.TILE, ""),
				new DungeonCard(DungeonCardType.TILE, ""),
		});
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(TileName.room_semicircle_nes, -1, -2, "Fire Imp", ""),
				new TileLocation(TileName.room_semicircle_nsw, 1, -2, "Fire Imp", ""),
				new TileLocation(TileName.room_semicircle_esw, 0, -3, "Gnoll", "")
		});
		aObjectives = new Objective[]{
			new Objective(ObjectiveType.Defeat, "Fire Imp", 2),
			new Objective(ObjectiveType.Defeat, "Gnoll", 1)
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.stoneMonsters;
		addDungeon();
		
		aName="Scorching Spoils";
		aDescription="Loot all of Embro's treasure";
		aReward=100;
		aTerrainType=TerrainType.stone;
		aBoss="Fire Demon";
		aBossName="Embro";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.blah, new BossSpeech[]{
						new BossSpeech("HEY! Get out of my treasure chamber!", Func.emote, false),
				}, PostFunc.FinishBossChat, 
				-1, 1, 1, DelayEffect.APPEAR),
		};
		aStartingTile=TileName.room_steps_nesw;
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(TileName.room_semicircle_nes, -2, 0, "Fire Imp", "MEGA CHEST"),
				new TileLocation(TileName.room_collapse_esw, 1, -2, "Zombie", "MEGA CHEST"),
				new TileLocation(TileName.room_semicircle_nsw, 0, -3, "Fire Elemental", "MEGA CHEST")
		});
		aObjectives = new Objective[]{
			new Objective(ObjectiveType.Collect, "MEGA CHEST", 3)
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.stoneMonsters;
		addDungeon();
		
		aAdvName="Embro";
		aAdvDescription="Defeat the evil demo, Embro";
		aAdvIcon="stone_fire_cave";
		aAdvX=1;
		aAdvY=1;
		createAdventure();
		
		
	}

	private static void addDungeon() {
		aDungeons.add(new Dungeon(
				aName, aDescription, aReward,
				aTerrainType,
				aBoss,aBossName, aBossChats,
				aStartingTile, aStartingHand, aLayout,
				aObjectives, aTurnLimit,
				aMonsters
				));
		resetDungeon();		
	}
	
	private static void resetDungeon() {
		aName=""; aDescription=""; aReward=0;
		aTerrainType=null;
		aBoss=""; aBossName=""; aBossChats=null;
		aStartingTile=null; aStartingHand=null; aLayout=null;
		aObjectives=null; aTurnLimit=0;
		aMonsters=null;
	}

	private static ArrayList<Adventure> adventures = new ArrayList<>();
	private static void createAdventure(){
		adventures.add(new Adventure(aAdvName, aAdvDescription, aAdvIcon, aAdvX, aAdvY, aDungeons));
		resetAdventure();
	}

	private static void resetAdventure() {
		aAdvName=""; aAdvDescription=""; aAdvIcon=""; aAdvX=0; aAdvY=0; aDungeons.clear();
	}	
}
