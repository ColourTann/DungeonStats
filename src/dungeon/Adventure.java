package dungeon;

import item.Item.TreasureType;

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
		int numUnlocks = 1;
		if(name.equals("Rats? How original!")){
			numUnlocks=3;
		}
		output += Json.addKey("numUnlocks", numUnlocks, true);
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
		output+=Json.startArray("UnlockOrder");
		for(int i=0;i<adventures.size();i++){
			Adventure a = adventures.get(i);
			output+="\""+a.name+"\""+((i<adventures.size()-1)?", ":" ");
		}
		output+=Json.endArray(true);
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
	static boolean aTutorial;
	static String aAdvName, aAdvDescription, aAdvIcon;
	static int aAdvX, aAdvY;


	static ArrayList<Dungeon> aDungeons = new ArrayList<>();

	public static void setup(){	

		// RAT ADVENTURE //

		aAdvName="Rats? How original!";
		//aAdvDescription="Delve into the basement and defeat the mighty... rats";
		aAdvIcon="stone_hatch";
		aAdvX=1;
		aAdvY=0;

		aName="Squeak squeak";
		aDescription="Everyone's gotta start somewhere";
		aReward=100;
		aTerrainType=TerrainType.stone;
		aTutorial=true;
		aBoss="Rat Man";
		aBossName="Rat King Cole";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.blah, new BossSpeech[]{
						new BossSpeech("Squeak squeak squeak", Func.emote, false),
				}, PostFunc.FinishBossChat, 
				-1, 2, 1, DelayEffect.APPEAR),
				new BossChat(Trigger.flib, new BossSpeech[]{
						new BossSpeech("Squeak!", Func.emote, false),	
				}, PostFunc.FinishBossChat, 
				2, -1, 1, DelayEffect.APPEAR),
		};
		aStartingTile=Tile.get("n");
		aStartingHand = new Hand(new DungeonCard[]{
				new DungeonCard(DungeonCardType.TILE, Tile.get("news")),
				new DungeonCard(DungeonCardType.TREASURE, TreasureType.GOLD_COIN),
				new DungeonCard(DungeonCardType.MONSTER, "Rubber Ducky"),
				new DungeonCard(DungeonCardType.TILE, ""),
				new DungeonCard(DungeonCardType.TILE, ""),
		});
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("nes"), -2, -1, "Nasty Rat", null),
				new TileLocation(Tile.get("nes"), -1, -2, "Nasty Rat", null),
				new TileLocation(Tile.get("nsw"), 1, -2, "Nasty Rat", null),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "Nasty Rat", 3),
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.noMonsters;
		addDungeon();

		aName="Rat King's Tale";
		aDescription="Defeat the rat king!";
		aReward=100;
		aTerrainType=TerrainType.stone;
		aBoss="Rat King Cole";
		aBossName="Rat King Cole";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.blah, new BossSpeech[]{
						new BossSpeech("SQUEAK!!", Func.emote, false),
				}, PostFunc.StartingRoom, 
				-1, 0, 1, DelayEffect.APPEAR),
		};
		aStartingTile=TileName.room_steps_nesw;
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("nesw"), 0, -2, "Nasty Rat", null),
				new TileLocation(Tile.get("nws"), 1, -3, "Nasty Rat", null),
				new TileLocation(Tile.get("nes"), -1, -3, "Rat Man", null),
				new TileLocation(Tile.get("nesw"), 0, -4, "Rat Man", null),
				new TileLocation(Tile.get("s"), -1, -5, "BOSS", null)
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1)
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.noMonsters;
		addDungeon();
		createAdventure();

		// EMBRO ADVENTURE //

		aAdvName="Embro";
		//aAdvDescription="Defeat the evil demon, Embro";
		aAdvIcon="stone_fire_cave";
		aAdvX=1;
		aAdvY=1;

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
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(TileName.room_semicircle_nes, -1, -2, "Fire Imp", null),
				new TileLocation(TileName.room_semicircle_nsw, 1, -2, "Fire Imp", null),
				new TileLocation(TileName.room_semicircle_esw, 0, -3, "Gnoll", null)
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "Fire Imp", 2),
				new Objective(ObjectiveType.Defeat, "Gnoll", 1)
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.noMonsters;
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
				new TileLocation(TileName.room_semicircle_nes, -2, 0, "Fire Imp", TreasureType.MEGA_CHEST),
				new TileLocation(TileName.room_collapse_esw, 0, -2, "Zombie", TreasureType.MEGA_CHEST),
				new TileLocation(TileName.room_semicircle_nsw, 2, 0, "Fire Elemental", TreasureType.MEGA_CHEST)
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Collect, "MEGA CHEST", 3)
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.noMonsters;
		addDungeon();

		aName="Evil Embers";
		aDescription="Defeat Embro";
		aReward=100;
		aTerrainType=TerrainType.stone;
		aBoss="Fire Demon";
		aBossName="Embro";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.intruder, new BossSpeech[]{
						new BossSpeech("WHO DARES INVADE THE HOT, HOT LAIR OF EMBRO, LORD OF FLAME?!", Func.emote, false),
						new BossSpeech("Right in the middle of bath-time too. Look at this puddle!", Func.emote, false),
						new BossSpeech("MINIONS! DESTROY THEM! BRING ME THEIR BONES!", Func.emote, false),
				}, PostFunc.StartingRoom, 
				-1, 0, 1, DelayEffect.APPEAR),

				new BossChat(Trigger.second_turn, new BossSpeech[]{
						new BossSpeech("Argh, I have to dry off or I won't be my fiery best.", Func.emote, false),
						new BossSpeech("You'd better wait {TURNS_LEFT} more turns before fighting me or it won't be fair!", Func.emote, false),
				}, PostFunc.FinishBossChat, 
				-1, 2, 1, DelayEffect.APPEAR),

				new BossChat(Trigger.four_left, new BossSpeech[]{
						new BossSpeech("MINIONS! Bring me more towels!.", Func.emote, false),
						new BossSpeech("I need to be ready for my big fight in 6 turns", Func.emote, false),
				}, PostFunc.FinishBossChat, 
				-1, 6, 1, DelayEffect.APPEAR),

				new BossChat(Trigger.last_turn, new BossSpeech[]{
						new BossSpeech("Almost dry now! I'm coming for you next turn!", Func.emote, false),
				}, PostFunc.FinishBossChat, 
				-1, 11, 1, DelayEffect.APPEAR),

				new BossChat(Trigger.coming_soon, new BossSpeech[]{
						new BossSpeech("**WHOOSH** Finally dry! Now I'm going to get you!", Func.emote, false),
				}, PostFunc.FireDemonMoveToBoard, 
				-1, 12, 1, DelayEffect.APPEAR),

				new BossChat(Trigger.attacked_early, new BossSpeech[]{
						new BossSpeech("Hey! I'm not ready yet!", Func.emote, false),
				}, null, 
				-1, -1, 1, null),

				new BossChat(Trigger.first_kill, new BossSpeech[]{
						new BossSpeech("HAR HAR! YOU THINK I'LL MISS THAT {LAST_KILL_TYPE}?", Func.emote, false),
						new BossSpeech("NO! HE WAS MY LEAST FAVORITE MINION!", Func.emote, false),
				}, PostFunc.FinishBossChat, 
				3, -1, 1, DelayEffect.APPEAR),

				new BossChat(Trigger.fifth_kill, new BossSpeech[]{
						new BossSpeech("*sigh* As usual my minions are bumbling fools..", Func.emote, false),
						new BossSpeech("If only those sharks with spears attached to their heads had arrived.", Func.emote, false),
						new BossSpeech("Oh well. SEND MORE RUBBER DUCKIES!", Func.emote, false),
				}, PostFunc.FinishBossChat, 
				5, -1, 1, DelayEffect.APPEAR),

		};
		aStartingTile=TileName.corr_grate_n;
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(TileName.room_torture_s, 0, -5, "BOSS", null),
				new TileLocation(TileName.room_round_s, 2, -3, "", TreasureType.MEGA_CHEST),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1)
		};
		aTurnLimit=12;
		aMonsters=MonsterFactory.stoneMonsters;
		addDungeon();
		createAdventure(); 

		// MIMIC ADVENTURE //

		aAdvName="Shiny treasure";
		//aAdvDescription="Loot all the treasure";
		aAdvIcon="stone_chest";
		aAdvX=2;
		aAdvY=0;

		aName="Lovely loot";
		aDescription="Chests full of treasure!";
		aReward=100;
		aTerrainType=TerrainType.stone;
		aBoss="Mimic";
		aBossName="Mimic";
		aBossChats= new BossChat[]{};
		aStartingTile=TileName.corr_grate_n;
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(TileName.room_semicircle_nes, -1, -2, "Zombie", TreasureType.Large_Chest),
				new TileLocation(TileName.room_semicircle_nsw, 1, -2, "Bear Owl", TreasureType.Large_Chest),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Collect, "Large Chest", 2),
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.stoneMonsters;
		addDungeon();

		aName="Terrible Truth";
		aDescription="Uhoh...";
		aReward=100;
		aTerrainType=TerrainType.stone;
		aBoss="Mimic";
		aBossName="Mimic";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.blah, new BossSpeech[]{
						new BossSpeech("You're the one who looted my grandparents!?", Func.emote, false),
						new BossSpeech("DIE, evil adventurer!", Func.emote, false)
				}, PostFunc.Chase, 
				-1, 0, 1, DelayEffect.APPEAR),
		};
		aStartingTile=Tile.get("news");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("ew"), -1, 0, "", null),
				new TileLocation(Tile.get("ewn"), -2, 0, "", null),
				new TileLocation(Tile.get("s"), -2, -1, "", TreasureType.Large_Chest),
				new TileLocation(Tile.get("ew"), -3, 0, "", null),
				new TileLocation(Tile.get("ews"), -4, 0, "", null),
				new TileLocation(Tile.get("n"), -4, 1, "", TreasureType.MEGA_CHEST),
				new TileLocation(TileName.corr_rubble_e, -5, 0, "BOSS", null),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1)
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.stoneMonsters;
		addDungeon();
		createAdventure();

		// GOBLIN ADVENTURE //

		aAdvName="Goblins!";
		//aAdvDescription="Loot all the treasure";
		aAdvIcon="stone_skull_cave";
		aAdvX=0;
		aAdvY=1;

		aName="Goblin Menace";
		aDescription="Take out the goblins";
		aReward=100;
		aTerrainType=TerrainType.stone;
		aBoss="Orc Grunt";
		aBossName="Orc Grunt";
		aBossChats= new BossChat[]{};
		aStartingTile=TileName.room_collapse_esw;
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(TileName.room_semicircle_nes, -2, 0, "Goblin", null),
				new TileLocation(TileName.room_steps_nesw, -1, 1, "Goblin", null),
				new TileLocation(TileName.room_collapse_new, 0, 2, "Goblin", null),
				new TileLocation(TileName.corr_crushed_ne, -2, 2, "Goblin", null),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "Goblin", 3),
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.stoneMonsters;
		addDungeon();

		aName="Knives and Orcs";
		aDescription="Defeat the big orc";
		aReward=100;
		aTerrainType=TerrainType.stone;
		aBoss="Orc Grunt";
		aBossName="Orc Grunt";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.blah, new BossSpeech[]{
						new BossSpeech("GRAK and GRIK! Defeat this interloper!", Func.emote, false)
				}, PostFunc.StartingRoom, 
				-1, 1, 1, DelayEffect.APPEAR)
		};
		aStartingTile=TileName.room_collapse_new;
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(TileName.room_semicircle_nes, -1, -2, "Goblin", null),
				new TileLocation(TileName.room_semicircle_nsw, 1, -2, "Goblin", null),
				new TileLocation(TileName.room_semicircle_esw, 0, -3, "BOSS", null)
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1)
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.stoneMonsters;
		addDungeon();
		createAdventure();

		// BLACK KNIGHT ADVENTURE //

		aAdvName="Black Knight";
		//aAdvDescription="Loot all the treasure";
		aAdvIcon="stone_castle";
		aAdvX=3;
		aAdvY=0;

		aName="Test your mettle";
		aDescription="Defeat the combatants";
		aReward=100;
		aTerrainType=TerrainType.stone;
		aBoss="The Black Knight";
		aBossName="The Black Knight";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.blah, new BossSpeech[]{
						new BossSpeech("Let's see what you can do, adventurer!", Func.emote, false)
				}, PostFunc.FinishBossChat, 
				-1, 1, 1, DelayEffect.APPEAR)
		};
		aStartingTile=TileName.room_collapse_new;
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(TileName.room_steps_nesw, -1, -2, "Zombie", null),
				new TileLocation(TileName.room_collapse_new, 1, -2, "Zombie", null),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "Zombie", 2),
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.noMonsters;
		addDungeon();

		aName="In the thick of it";
		aDescription="Defeat 2 banditos";
		aReward=100;
		aTerrainType=TerrainType.stone;
		aBoss="The Black Knight";
		aBossName="The Black Knight";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.blah, new BossSpeech[]{
						new BossSpeech("Try your luck with these!", Func.emote, false)
				}, PostFunc.FinishBossChat, 
				-1, 1, 1, DelayEffect.APPEAR)
		};
		aStartingTile=TileName.room_collapse_new;
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(TileName.room_semicircle_nes, -1, -2, "Bandito", null),
				new TileLocation(TileName.room_semicircle_nsw, 1, -2, "Bandito", null),
				new TileLocation(TileName.corr_rubble_e, -2, -1, "", TreasureType.MEGA_CHEST)
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "Bandito", 2)
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.noMonsters;
		addDungeon();

		aName="Dark duel";
		aDescription="Defeat the Black Knight!";
		aReward=100;
		aTerrainType=TerrainType.stone;
		aBoss="The Black Knight";
		aBossName="The Black Knight";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.blah, new BossSpeech[]{
						new BossSpeech("A worthy challenger at last!", Func.emote, false)
				}, PostFunc.StartingRoom, 
				-1, 1, 1, DelayEffect.APPEAR)
		};
		aStartingTile=TileName.room_collapse_new;
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(TileName.room_collapse_esw, 0, -3, "BOSS", null),
				new TileLocation(TileName.room_round_s, 2, -3, "", TreasureType.MEGA_CHEST),
				new TileLocation(TileName.room_round_s, -2, -3, "", TreasureType.MEGA_CHEST)
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1)
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.stoneMonsters;
		addDungeon();
		createAdventure();

		// LICH ADVENTURE //

		aAdvName="Evil Lich!";
		//aAdvDescription="Loot all the treasure";
		aAdvIcon="stone_skull_tomb";
		aAdvX=2;
		aAdvY=1;
		aName="The summoning";
		aDescription="Destroy the Evil Lich";
		aReward=100;
		aTerrainType=TerrainType.stone;
		aBoss="Lich";
		aBossName="Evil Lich";
		aBossChats= new BossChat[]{};
		aStartingTile=TileName.corr_crushed_ne;
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(TileName.room_cavern_nw, 2, 0, "", TreasureType.MEGA_CHEST),
				new TileLocation(TileName.room_steps_nesw, 2, -2, "Sorceress", null),
				new TileLocation(TileName.room_steps_nesw, 3, -2, "Sorceress", null),
				new TileLocation(TileName.room_steps_nesw, 4, -2, "Sorceress", null),
				new TileLocation(TileName.room_steps_nesw, 2, -3, "Sorceress", null),
				new TileLocation(TileName.room_steps_nesw, 4, -3, "Sorceress", null),
				new TileLocation(TileName.room_steps_nesw, 3, -4, "", TreasureType.MEGA_CHEST),
				new TileLocation(TileName.room_steps_nesw, 3, -3, "BOSS", null),
		});
		//I only burned down one orphanage

		//HAHA NOW I AM SUMMONED IT WAS TWO ORPHANAGES!
		aBossChats= new BossChat[]{
				new BossChat(Trigger.blah, new BossSpeech[]{
						new BossSpeech("Hey! I'm not that evil!", Func.emote, false)
				}, PostFunc.StartingRoom, 
				-1, 1, 1, DelayEffect.APPEAR)
		};
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1),
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.stoneMonsters;
		addDungeon();
		createAdventure();

		// EYE-BEAST ADVENTURE //

		aAdvName="The Eye Beast";
		//aAdvDescription="Loot all the treasure";
		aAdvIcon="stone_skull_cave";
		aAdvX=3;
		aAdvY=1;

		aName="Curious Crypt";
		aDescription="Get to the crypt";
		aReward=100;
		aTerrainType=TerrainType.stone;
		aBoss="Eye Beast";
		aBossName="Eye Beast";
		aBossChats= new BossChat[]{};
		aStartingTile=TileName.corr_crushed_ne;
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(TileName.room_round_s, 0, -4, "", null),
				new TileLocation(TileName.room_steps_nesw, 0, -3, "Shade", null),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "Goblin", 3),
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.stoneMonsters;
		addDungeon();

		aName="Staring match";
		aDescription="BEHOLD! The Eye Beast! (too much?)";
		aReward=100;
		aTerrainType=TerrainType.stone;
		aBoss="Eye Beast";
		aBossName="Eye Beast";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.blah, new BossSpeech[]{
						new BossSpeech("Szssszzzzssszch", Func.emote, false)
				}, PostFunc.StartingRoom, 
				-1, 1, 1, DelayEffect.APPEAR)
		};
		aStartingTile=TileName.room_collapse_new;
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(TileName.room_round_s, 1, -3, "", TreasureType.MEGA_CHEST),
				new TileLocation(TileName.corr_rubble_e, -2, -1, "", TreasureType.MEGA_CHEST),
				new TileLocation(TileName.room_torture_s, -1, -3, "BOSS", null)
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1)
		};
		aTurnLimit=10;
		aMonsters=MonsterFactory.stoneMonsters;
		addDungeon();
		createAdventure();

		/*
		 * 
		 * 
		 * JUNGLE AZTEC TEMPLE ADVENTURE QUEST LINES
		 * 
		 * 
		 * 
		 */

		// SAPPHIRE ADVENTURE //

		aAdvName="The Sapphire of Tlaloc";
		//aAdvDescription="Loot all the treasure";
		aAdvIcon="stone_skull_cave";
		aAdvX=-1;
		aAdvY=0;

		aName="Grab";
		aDescription="Steal the sapphire";
		aReward=100;
		aTerrainType=TerrainType.jungle;
		aBoss="Eye Beast";
		aBossName="Eye Beast";
		aBossChats= new BossChat[]{};
		aStartingTile=Tile.get("jn");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("js"), 0, -3, "", TreasureType.MEGA_CHEST),
				new TileLocation(Tile.get("jsnew"), 0, -2, "Gargoyle", null),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Collect, "MEGA CHEST", 1),
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.jungleMonsters;
		addDungeon();

		aName="Run";
		aDescription="Get out before the roof collapses!";
		aReward=100;
		aTerrainType=TerrainType.jungle;
		aBoss="Eye Beast";
		aBossName="Eye Beast";
		aBossChats= new BossChat[]{};
		/*aBossChats= new BossChat[]{
						new BossChat(Trigger.blah, new BossSpeech[]{
								new BossSpeech("Szssszzzzssszch", Func.emote, false)
						}, PostFunc.StartingRoom, 
						-1, 1, 1, DelayEffect.APPEAR)
				};*/
		aStartingTile=Tile.get("jnew");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("js"), -2, -1, "", TreasureType.MEGA_CHEST),
				new TileLocation(Tile.get("jns"), 0, -2, "HARPY", null),
				new TileLocation(Tile.get("js"), -1, -3, "", null)
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "HARPY", 1)
		};
		aTurnLimit=7;
		aMonsters=MonsterFactory.jungleMonsters;
		addDungeon();
		createAdventure();


		// MEDUSA ADVENTURE //

		aAdvName="Medusa Adventure";
		//aAdvDescription="Loot all the treasure";
		aAdvIcon="stone_skull_cave";
		aAdvX=-2;
		aAdvY=0;

		aName="Snakes";
		aDescription="I hate snakes";
		aReward=100;
		aTerrainType=TerrainType.jungle;
		aBoss="Medusa";
		aBossName="Medusa";
		aBossChats= new BossChat[]{};
		aStartingTile=Tile.get("jnw");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("jne"), -2, 0, "Poisonous Snake", null),
				new TileLocation(Tile.get("jes"), -1, -2, "Poisonous Snake", null),
				new TileLocation(Tile.get("jnw"), 1, 2, "Poisonous Snake", null),
				new TileLocation(Tile.get("js"), 0, -2, "Poisonous Snake", null),
				new TileLocation(Tile.get("jsw"), -2, -2, "Poisonous Snake", null),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "Poisonous Snake", 3),
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.jungleMonsters;
		addDungeon();

		aName="Stony Stare";
		aDescription="Defeat Medusa before you get turned to stone!";
		aReward=100;
		aTerrainType=TerrainType.jungle;
		aBoss="Medusa";
		aBossName="Medusa";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.blah, new BossSpeech[]{
						new BossSpeech("Something about snakes", Func.emote, false)
				}, PostFunc.StartingRoom, 
				-1, 1, 1, DelayEffect.APPEAR)
		};
		aStartingTile=Tile.get("jnew");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("je"), -2, 0, "", TreasureType.MEGA_CHEST),
				new TileLocation(Tile.get("js"), -2, -3, "", TreasureType.MEGA_CHEST),
				new TileLocation(Tile.get("js"), 2, -3, "", TreasureType.MEGA_CHEST),
				new TileLocation(Tile.get("jsew"), 0, -3, "BOSS", null)
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1)
		};
		aTurnLimit=10;
		aMonsters=MonsterFactory.jungleMonsters;
		addDungeon();
		createAdventure();

		// ETTIN ADVENTURE //

		aAdvName="Ettin Adventure";
		//aAdvDescription="Loot all the treasure";
		aAdvIcon="stone_skull_cave";
		aAdvX=-3;
		aAdvY=0;

		aName="Ettin Troubles";
		aDescription="He's ganging up on you!";
		aReward=100;
		aTerrainType=TerrainType.jungle;
		aBoss="Ettin";
		aBossName="Ettin";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.blah, new BossSpeech[]{
						new BossSpeech("Something about ettins", Func.emote, false)
				}, PostFunc.StartingRoom, 
				-1, 1, 1, DelayEffect.APPEAR)
		};
		aStartingTile=Tile.get("jne");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("jw"), 2, 0, "", TreasureType.MEGA_CHEST),
				new TileLocation(Tile.get("js"), 0, -2, "", TreasureType.MEGA_CHEST),
				new TileLocation(Tile.get("jnw"), 2, -2, "BOSS", null),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1),
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.jungleMonsters;
		addDungeon();
		createAdventure();
		
		// CHIMERA ADVENTURE //

		aAdvName="Chimera Adventure";
		//aAdvDescription="Loot all the treasure";
		aAdvIcon="stone_skull_cave";
		aAdvX=-1;
		aAdvY=-1;

		aName="Wild Life";
		aDescription="Defend yourself!";
		aReward=100;
		aTerrainType=TerrainType.jungle;
		aBoss="Chimera";
		aBossName="Chimera";
		aBossChats= new BossChat[]{};
		aStartingTile=Tile.get("jnwe");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("jne"), -2, 0, "Poisonous Snake", null),
				new TileLocation(Tile.get("jwn"), 2, 0, "Plague Rat", null),
				new TileLocation(Tile.get("js"), 1, 2, "Dire Scorpion", null),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "Plague Rat", 1),
				new Objective(ObjectiveType.Defeat, "Poisonous Snake", 1),
				new Objective(ObjectiveType.Defeat, "Dire Scorpion", 1),
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.jungleMonsters;
		addDungeon();

		aName="Awakened";
		aDescription="Defeat the chimera!";
		aReward=100;
		aTerrainType=TerrainType.jungle;
		aBoss="Chimera";
		aBoss="Chimera";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.blah, new BossSpeech[]{
						new BossSpeech("Something about chimeras", Func.emote, false)
				}, PostFunc.StartingRoom, 
				-1, 1, 1, DelayEffect.APPEAR)
		};
		aStartingTile=Tile.get("js");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("jns"), -1, 0, "", null),
				new TileLocation(Tile.get("jns"), -2, 0, "", null),
				new TileLocation(Tile.get("jns"), -3, 0, "", null),
				new TileLocation(Tile.get("jns"), -4, 0, "", null),
				new TileLocation(Tile.get("jns"), -5, 0, "BOSS", TreasureType.MEGA_CHEST),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1)
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.jungleMonsters;
		addDungeon();
		createAdventure();
		
		// OGRE ADVENTURE //

		aAdvName="Ogre Adventure";
		//aAdvDescription="Loot all the treasure";
		aAdvIcon="stone_skull_cave";
		aAdvX=-2;
		aAdvY=-1;

		aName="Assassination";
		aDescription="Defeat the ogre while he's still sleepy";
		aReward=100;
		aTerrainType=TerrainType.jungle;
		aBoss="Ogre";
		aBossName="Ogre";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.blah, new BossSpeech[]{
						new BossSpeech("Something about ogres", Func.emote, false)
				}, PostFunc.StartingRoom, 
				-1, 1, 1, DelayEffect.APPEAR)
		};
		aStartingTile=Tile.get("jnwe");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("jw"), 2, -1, "", TreasureType.MEGA_CHEST),
				new TileLocation(Tile.get("je"), -2, -1, "", TreasureType.MEGA_CHEST),
				new TileLocation(Tile.get("sew"), 0, -3, "BOSS", null),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1),
		};
		aTurnLimit=10;
		aMonsters=MonsterFactory.jungleMonsters;
		addDungeon();
		createAdventure();
		
		
		// DRAGON ADVENTURE //

		aAdvName="Dragon Adventure";
		//aAdvDescription="Loot all the treasure";
		aAdvIcon="stone_skull_cave";
		aAdvX=-3;
		aAdvY=-1;

		aName="Heist";
		aDescription="Steal 4 chests";
		aReward=100;
		aTerrainType=TerrainType.jungle;
		aBoss="Dragon";
		aBossName="Dragon";
		aBossChats= new BossChat[]{};
		aStartingTile=Tile.get("jew");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("jw"), 2, -1, "Jungle Warrior", TreasureType.MEGA_CHEST),
				new TileLocation(Tile.get("jw"), -2, -1, "Pixies", TreasureType.MEGA_CHEST),
				new TileLocation(Tile.get("jw"), 0, -2, "Man-Eating Plant", TreasureType.MEGA_CHEST),
				new TileLocation(Tile.get("jw"), 0, -3, "Gargoyle", TreasureType.MEGA_CHEST),
				new TileLocation(Tile.get("jnw"), 0, -3, "BOSS", null),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Collect, "MEGA CHEST", -1),
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.jungleMonsters;
		addDungeon();
		
		aName="Revenge";
		aDescription="Defeat the angry Dragon!";
		aReward=100;
		aTerrainType=TerrainType.jungle;
		aBoss="Dragon";
		aBossName="Dragon";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.blah, new BossSpeech[]{
						new BossSpeech("Something about dragons", Func.emote, false)
				}, PostFunc.StartingRoom, 
				-1, 1, 1, DelayEffect.APPEAR)
		};
		aStartingTile=Tile.get("jnews");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("jw"), 6, 0, "BOSS", null),
				new TileLocation(Tile.get("js"), 5, -1, "", TreasureType.GOLD_COIN),
				new TileLocation(Tile.get("jwen"), 5, 0, "", null),
				new TileLocation(Tile.get("jwe"), 4, 0, "", null),
				new TileLocation(Tile.get("jn"), 3, 1, "", TreasureType.GOLD_COIN),
				new TileLocation(Tile.get("jwes"), 3, 0, "", null),
				new TileLocation(Tile.get("jwe"), 2, 0, "", null),
				new TileLocation(Tile.get("js"), 5, -1, "", TreasureType.GOLD_COIN),
				new TileLocation(Tile.get("jwen"), 1, 0, "", null),
				new TileLocation(Tile.get("jnews"), -3, -1, "", TreasureType.MEGA_CHEST),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1),
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.jungleMonsters;
		addDungeon();
		createAdventure();
		

	}

	public TileName getTile(String directions){

		return null;
	}

	private static void addDungeon() {
		aDungeons.add(new Dungeon(
				aName, aDescription, aReward,
				aTerrainType,
				aBoss,aBossName, aBossChats,
				aStartingTile, aStartingHand, aLayout,
				aObjectives, aTurnLimit,
				aMonsters,
				aTutorial
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
		aTutorial=false;
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
