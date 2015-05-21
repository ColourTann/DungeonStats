package dungeon;

import item.Item.TreasureType;

import java.util.ArrayList;

import dungeon.BossChat.BossSpeech;
import dungeon.BossChat.DelayEffect;
import dungeon.BossChat.Func;
import dungeon.BossChat.PostFunc;
import dungeon.BossChat.*;
import dungeon.Dungeon.CardType;
import dungeon.Dungeon.TerrainType;
import dungeon.DungeonLayout.TileDetails;
import dungeon.DungeonLayout.TileLocation;
import dungeon.Hand.DungeonCard;
import dungeon.Hand.DungeonCardType;
import dungeon.Objective.ObjectiveType;
import dungeon.Tile.TileName;
import dungeon.TurnLimitAction.ActionType;
import fighter.Fighter.Trait;
import fighter.monster.Monster;
import fighter.monster.MonsterFactory;
import fighter.monster.MonsterFactory.Region;
import json.Json;

public class Adventure {

	static CardType[] exploreTemplate = new CardType[]{CardType.TILE, CardType.TILE, CardType.TILE, CardType.TILE, CardType.TREASURE};
	
	String name, description, icon;
	float mapX, mapY;
	ArrayList<Dungeon> dungeons;
	@SuppressWarnings("unchecked")
	public Adventure(String name, String description, String icon, float aAdvX2, float aAdvY2, ArrayList<Dungeon> dungeons) {
		this.name=name; this.description=description; this.icon=icon;
		this.mapX=aAdvX2; this.mapY=aAdvY2;
		this.dungeons=(ArrayList<Dungeon>) dungeons.clone();
	}

	public String toJson(){
		String output="";
		output+= Json.startList(name);
		output+= Json.addKey("Description", description, true);
		output+= Json.addKey("Icon", icon, true);
		output+= Json.addKey("mapX", mapX+"", true);
		output+= Json.addKey("mapY", mapY+"", true);
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
	static Objective[] aObjectives; static int aTurnLimit; static TurnLimitAction[] aTurnLimitActions;
	static ArrayList<Monster> aMonsters; 
	static boolean aTutorial;
	static String aAdvName, aAdvDescription, aAdvIcon;
	static float aAdvX, aAdvY;


	static ArrayList<Dungeon> aDungeons = new ArrayList<>();
	private static CardType[] aDrawRate;

	public static void setup(){	

		// RAT ADVENTURE //

		aAdvName="Rats? How original!";
		//aAdvDescription="Delve into the basement and defeat the mighty... rats";
		aAdvIcon="stone_hatch";
		aAdvX=1;
		aAdvY=-.5f;

//		aName="Traversal test";
//		aDescription="Hope it 's fun";
//		aReward=100;
//		aTerrainType=TerrainType.stone;
//		aBoss="Rat King Cole";
//		aBossName="Rat King Cole";
//		aBossChats= new BossChat[]{
//		};
//		aStartingTile=Tile.get("udr");
//		aLayout= new DungeonLayout(new TileLocation[]{
//				new TileLocation(Tile.get("l"), 7, 0, "", null, new TileDetails(true, true, true, 5, 0, true)),
//				new TileLocation(Tile.get("udrl"), 6, 0, "Angry Bunny", null, new TileDetails(true, true, false, 0, 0, false)),
//				new TileLocation(Tile.get("lr"), 1, 1, "Gnoll", null, new TileDetails(true, true, false, 0, 0, false)),
//				new TileLocation(Tile.get("udlr"), 2, 0, "Ghost", null, new TileDetails(true, true, false, 0, 0, false)),
//				new TileLocation(Tile.get("ulr"), 3, 1, "Shade", null, new TileDetails(true, true, false, 0, 0, false)),
//				new TileLocation(Tile.get("lr"), 3, -1, "Sorceress", null, new TileDetails(true, true, false, 0, 0, false)),
//				new TileLocation(Tile.get("udlr"), 4, 0, "Bear Owl", null, new TileDetails(true, true, false, 0, 0, false)),
//				new TileLocation(Tile.get("dlr"), 5, -1, "Bandito", null, new TileDetails(true, true, false, 0, 0, false)),
//		});
//		aObjectives = new Objective[]{
//				new Objective(ObjectiveType.Arrive, "objective", -1)
//		};
//		aTurnLimit=10;
//		aTurnLimitActions= new TurnLimitAction[]{(
//				new TurnLimitAction(ActionType.FailDungeon, new String[]{}, "Trapped")
//				)};
//		aMonsters=MonsterFactory.stoneMonsters;
//		addDungeon();
//		createAdventure();
		
		aName="Squeak squeak";
		aDescription="Everyone's gotta start somewhere";
		aReward=100;
		aTerrainType=TerrainType.stone;
		aTutorial=true;
		aBoss="Rat Man";
		aBossName="Rat King Cole";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.Second_Turn, new BossSpeech[]{
						new BossSpeech("Squeak squeak squeak", Func.emote, false),
				}, PostFunc.FinishBossChat, 
				-1, 2, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.SecondKill, new BossSpeech[]{
						new BossSpeech("Squeak!", Func.emote, false),	
				}, PostFunc.FinishBossChat, 
				2, -1, 1, DelayEffect.APPEAR, null),
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
				new TileLocation(Tile.get("nes"), -2, -1, "Nasty Rat", null, null),
				new TileLocation(Tile.get("nes"), -1, -2, "Nasty Rat", null, null),
				new TileLocation(Tile.get("nsw"), 1, -2, "Nasty Rat", null, null),
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
				new BossChat(Trigger.Entrance, new BossSpeech[]{
						new BossSpeech("Squeak!!", Func.emote, false),
				}, PostFunc.StartingRoom, 
				-1, 0, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.SecondKill, new BossSpeech[]{
						new BossSpeech("*chitter chitter*", Func.emote, false),
				}, PostFunc.FinishBossChat, 
				2, -1, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.FourthKill, new BossSpeech[]{
						new BossSpeech("SQUEAK!!", Func.emote, false),
				}, PostFunc.FinishBossChat, 
				4, -1, 1, DelayEffect.APPEAR, null),
		};
		aStartingTile=TileName.room_steps_nesw;
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("nes"), -1, -3, "Rat Man", null, new TileDetails(true, true, true, -1, -3, false)),
				new TileLocation(Tile.get("s"), -1, -5, "BOSS", null, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(Tile.get("nesw"), 0, -2, "Nasty Rat", null, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(Tile.get("nws"), 1, -3, "Nasty Rat", null, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(Tile.get("nesw"), 0, -4, "Rat Man", null, new TileDetails(true, true, false, 0, 0, false)),
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
		aAdvY=.5f;

		aName="Fiery Foray";
		aDescription="Scope out Embro's defences";
		aReward=100;
		aTerrainType=TerrainType.stone;
		aBoss="Fire Demon";
		aBossName="Embro";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.Entrance, new BossSpeech[]{
						new BossSpeech("HEY! What are you doing in my Evil Entrance Hall?", Func.emote, false),
						new BossSpeech("Oh well, my imps will make short work of you!", Func.emote, false),
				}, PostFunc.FinishBossChat, 
				-1, 1, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.SecondKill, new BossSpeech[]{
						new BossSpeech("You're not supposed to be able to beat them!", Func.emote, false),
						new BossSpeech("I should have invested in some better security...", Func.emote, false),	
				}, PostFunc.FinishBossChat, 
				2, -1, 1, DelayEffect.APPEAR, null),
		};
		aStartingTile=TileName.corr_grate_n;
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(TileName.room_semicircle_nes, -1, -2, "Fire Imp", null, null),
				new TileLocation(TileName.room_semicircle_nsw, 1, -2, "Fire Imp", null, null),
				new TileLocation(TileName.room_semicircle_esw, 0, -3, "Gnoll", null, null)
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
				new BossChat(Trigger.Entrance, new BossSpeech[]{
						new BossSpeech("HEY! Get out of my treasure chamber!", Func.emote, false),
				}, PostFunc.FinishBossChat, 
				-1, 1, 1, DelayEffect.APPEAR, null),
		};
		aStartingTile=TileName.room_steps_nesw;
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(TileName.room_semicircle_nes, -2, 0, "Fire Imp", TreasureType.Large_Chest, null),
				new TileLocation(TileName.room_collapse_esw, 0, -2, "Zombie", TreasureType.Large_Chest, null),
				new TileLocation(TileName.room_semicircle_nsw, 2, 0, "Fire Elemental", TreasureType.Large_Chest, null)
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Collect, TreasureType.Large_Chest.toString(), 3)
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
				new BossChat(Trigger.Entrance, new BossSpeech[]{
						new BossSpeech("WHO DARES INVADE THE HOT, HOT LAIR OF EMBRO, LORD OF FLAME?!", Func.emote, false),
						new BossSpeech("Right in the middle of bath-time too. Look at this puddle!", Func.emote, false),
						new BossSpeech("MINIONS! DESTROY THEM! BRING ME THEIR BONES!", Func.emote, false),
				}, PostFunc.StartingRoom, 
				-1, 0, 1, DelayEffect.APPEAR, null),

				new BossChat(Trigger.Second_Turn, new BossSpeech[]{
						new BossSpeech("Argh, I have to dry off or I won't be my fiery best.", Func.emote, false),
						new BossSpeech("You'd better wait {TURNS_LEFT} more turns before fighting me or it won't be fair!", Func.emote, false),
				}, PostFunc.FinishBossChat, 
				-1, 2, 1, DelayEffect.APPEAR, null),

				new BossChat(Trigger.SixthTurn, new BossSpeech[]{
						new BossSpeech("MINIONS! Bring me more towels!.", Func.emote, false),
						new BossSpeech("I need to be ready for my big fight in 6 turns", Func.emote, false),
				}, PostFunc.FinishBossChat, 
				-1, 6, 1, DelayEffect.APPEAR, null),

				new BossChat(Trigger.EleventhTurn, new BossSpeech[]{
						new BossSpeech("Almost dry now! I'm coming for you next turn!", Func.emote, false),
				}, PostFunc.FinishBossChat, 
				-1, 11, 1, DelayEffect.APPEAR, null),

				new BossChat(Trigger.ComingToAttack, new BossSpeech[]{
						new BossSpeech("**WHOOSH** Finally dry! Now I'm going to get you!", Func.emote, false),
				}, PostFunc.FireDemonMoveToBoard, 
				-1, -1, 1, DelayEffect.APPEAR, new PostEffect[]{
						new PostEffect(Trait.Damp, false)
				}),

				new BossChat(Trigger.attacked_early, new BossSpeech[]{
						new BossSpeech("Hey! I'm not ready yet!", Func.emote, false),
				}, null, 
				-1, -1, 1, null, null),

				new BossChat(Trigger.ThirdKill, new BossSpeech[]{
						new BossSpeech("HAR HAR! YOU THINK I'LL MISS THAT {LAST_KILL_TYPE}?", Func.emote, false),
						new BossSpeech("NO! HE WAS MY LEAST FAVORITE MINION!", Func.emote, false),
				}, PostFunc.FinishBossChat, 
				3, -1, 1, DelayEffect.APPEAR, null),

				new BossChat(Trigger.FifthKill, new BossSpeech[]{
						new BossSpeech("*sigh* As usual my minions are bumbling fools..", Func.emote, false),
						new BossSpeech("If only those sharks with spears attached to their heads had arrived.", Func.emote, false),
						new BossSpeech("Oh well. SEND MORE RUBBER DUCKIES!", Func.emote, false),
				}, PostFunc.FinishBossChat, 
				5, -1, 1, DelayEffect.APPEAR, null),
		};
		aStartingTile=TileName.corr_grate_n;
		aTurnLimitActions= new TurnLimitAction[]{(
				new TurnLimitAction(ActionType.BossChat, new String[]{"\""+Trigger.ComingToAttack.toString()+"\""}, "Embro attacks")
				)};
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(TileName.room_torture_s, 0, -5, "BOSS", null, null),
				new TileLocation(TileName.room_round_s, 2, -3, "", TreasureType.MEGA_CHEST, null),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1)
		};
		aTurnLimit=12;
		aMonsters=MonsterFactory.getMonsters(Region.Stone, 3);
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
		aStartingTile=Tile.get("ulr");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(TileName.room_semicircle_nes, -1, -2, "Zombie", TreasureType.Large_Chest, null),
				new TileLocation(TileName.room_semicircle_nsw, 1, -2, "Bear Owl", TreasureType.Large_Chest, null),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Collect, "Large Chest", 2),
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.stoneMonsters;
		addDungeon();

		aName="Terrible Truth";
		aDescription="Get strong enough to beat the mimic before he catches you!";
		aReward=100;
		aTerrainType=TerrainType.stone; 
		aBoss="Mimic";
		aBossName="Mimic";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.Entrance, new BossSpeech[]{
						new BossSpeech("You're the one who looted my grandparents!?", Func.emote, false),
						new BossSpeech("DIE, evil adventurer!", Func.emote, false)
				}, PostFunc.Chase, 
				-1, 0, 1, DelayEffect.APPEAR, null),
		};
		aStartingTile=Tile.get("news");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("ew"), -1, 0, "", null, new TileDetails(true, true, true, -5, 0, false)),
				new TileLocation(Tile.get("ew"), -2, 0, "", null, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(Tile.get("ew"), -3, 0, "", null, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(Tile.get("ew"), -4, 0, "", null, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(TileName.corr_rubble_e, -5, 0, "BOSS", null, new TileDetails(true, true, false, 0, 0, false)),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1)
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.getMonsters(Region.Stone, 3);
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
				new TileLocation(TileName.room_semicircle_nes, -2, 0, "Goblin", null, new TileDetails(true, true, true, -1, 1, false)),
				new TileLocation(TileName.room_steps_nesw, -1, 1, "Goblin", null, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(TileName.room_collapse_new, 0, 2, "Goblin", null, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(TileName.corr_crushed_ne, -2, 2, "Goblin", null, new TileDetails(true, true, false, 0, 0, false)),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "Goblin", 3),
		};
		aTurnLimit=7;
		aTurnLimitActions= new TurnLimitAction[]{(
				new TurnLimitAction(ActionType.HeroHealth, new String[]{"-2"}, "Lunchtime")
				)};
		aMonsters=MonsterFactory.noMonsters;
		addDungeon();

		aName="Knives and Orcs";
		aDescription="Defeat the big orc";
		aReward=100;
		aTerrainType=TerrainType.stone;
		aBoss="Orc Grunt";
		aBossName="Orc Grunt";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.Entrance, new BossSpeech[]{
						new BossSpeech("GRAK and GRIK! Defeat this interloper!", Func.emote, false)
				}, PostFunc.StartingRoom, 
				-1, 0, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.SecondKill, new BossSpeech[]{
						new BossSpeech("Stop killing my monsters!", Func.emote, false)
				}, PostFunc.FinishBossChat, 
				2, -1, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.FourthKill, new BossSpeech[]{
						new BossSpeech("Stop it! I mean it!", Func.emote, false)
				}, PostFunc.FinishBossChat, 
				4, -1, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.attacked_early, new BossSpeech[]{
						new BossSpeech("This'll be over quick, human!", Func.emote, false)
				}, PostFunc.FinishBossChat, 
				-1, -1, 1, DelayEffect.APPEAR, null),
		};
		aStartingTile=TileName.room_collapse_new;
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(TileName.room_semicircle_nes, -1, -2, "Goblin", null, new TileDetails(true, true, true, 0, -3, false)),
				new TileLocation(TileName.room_semicircle_nsw, 1, -2, "Goblin", null, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(TileName.room_semicircle_esw, 0, -3, "BOSS", null, new TileDetails(true, true, false, 0, 0, false))
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1)
		};
		aTurnLimit=7;
		aTurnLimitActions= new TurnLimitAction[]{(
				new TurnLimitAction(ActionType.HeroHealth, new String[]{"-2"}, "Lunchtime")
				)};
		aMonsters=MonsterFactory.getMonsters(Region.Stone, 2);
		addDungeon();
		createAdventure();

		// BLACK KNIGHT ADVENTURE //

		aAdvName="Black Knight";
		//aAdvDescription="Loot all the treasure";
		aAdvIcon="stone_castle";
		aAdvX=2.5f;
		aAdvY=-1;

		aName="Test your mettle";
		aDescription="Defeat the combatants";
		aReward=100;
		aTerrainType=TerrainType.stone;
		aBoss="The Black Knight";
		aBossName="The Black Knight";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.FirstTurn, new BossSpeech[]{
						new BossSpeech("Let's see what you can do, adventurer!", Func.emote, false)
				}, PostFunc.FinishBossChat, 
				-1, 0, 1, DelayEffect.APPEAR, null)
		};
		aStartingTile=TileName.room_collapse_new;
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("sew"), -1, -2, "Zombie", null, null),
				new TileLocation(Tile.get("sew"), 1, -2, "Skeleton", null, null),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "Zombie", 1),
				new Objective(ObjectiveType.Defeat, "Skeleton", 1),
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.getMonsters(Region.Stone, 1);
		addDungeon();

		aName="In the thick of it";
		aDescription="Defeat the combatants";
		aReward=100;
		aTerrainType=TerrainType.stone;
		aBoss="The Black Knight";
		aBossName="The Black Knight";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.FirstTurn, new BossSpeech[]{
						new BossSpeech("Try your luck with these!", Func.emote, false)
				}, PostFunc.FinishBossChat, 
				-1, 0, 1, DelayEffect.APPEAR, null)
		};
		aStartingTile=TileName.room_collapse_new;
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(TileName.room_semicircle_nes, -1, -2, "Bandito", null, null),
				new TileLocation(TileName.room_semicircle_nsw, 1, -2, "Mummy", null, null),
				new TileLocation(TileName.corr_rubble_e, -2, -1, "", TreasureType.MEGA_CHEST, null)
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "Bandito", 1),
				new Objective(ObjectiveType.Defeat, "Mummy", 1),
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.getMonsters(Region.Stone, 2);
		addDungeon();

		aName="Dark duel";
		aDescription="Defeat the Black Knight!";
		aReward=100;
		aTerrainType=TerrainType.stone;
		aBoss="The Black Knight";
		aBossName="The Black Knight";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.Entrance, new BossSpeech[]{
						new BossSpeech("A worthy challenger at last!", Func.emote, false)
				}, PostFunc.StartingRoom, 
				-1, 0, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.FirstKill, new BossSpeech[]{
						new BossSpeech("Good technique!", Func.emote, false)
				}, PostFunc.FinishBossChat, 
				1, -1, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.SecondKill, new BossSpeech[]{
						new BossSpeech("Impressive form!", Func.emote, false)
				}, PostFunc.FinishBossChat, 
				2, -1, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.ThirdKill, new BossSpeech[]{
						new BossSpeech("Getting some last minute practice in?", Func.emote, false)
				}, PostFunc.FinishBossChat, 
				3, -1, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.NinthTurn, new BossSpeech[]{
						new BossSpeech("Come on, I'm getting impatient!", Func.emote, false)
				}, PostFunc.FinishBossChat, 
				-1, 9, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.ComingToAttack, new BossSpeech[]{
						new BossSpeech("Here I come, worthy challenger", Func.emote, false)
				}, PostFunc.FireDemonMoveToBoard, 
				-1, -1, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.attacked_early, new BossSpeech[]{
						new BossSpeech("You're eager, I like it!", Func.emote, false),
				}, null, 
				-1, -1, 1, null, null),
		};
		aStartingTile=TileName.room_collapse_new;
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(TileName.room_collapse_esw, 0, -3, "BOSS", null, null),
				new TileLocation(TileName.room_round_s, 2, -3, "", TreasureType.MEGA_CHEST, null),
				new TileLocation(TileName.room_round_s, -2, -3, "", TreasureType.MEGA_CHEST, null)
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1)
		};
		aTurnLimitActions= new TurnLimitAction[]{(
				new TurnLimitAction(ActionType.BossChat, new String[]{"\""+Trigger.ComingToAttack.toString()+"\""}, "Duel")
				)};
		aTurnLimit=8;
		aMonsters=MonsterFactory.getMonsters(Region.Stone, 3);
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
				new TileLocation(TileName.room_steps_nesw, 3, -3, "BOSS", null, new TileDetails(true, true, true, 3, -3, false)),
				new TileLocation(TileName.room_cavern_nw, 2, 0, "", TreasureType.MEGA_CHEST, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(TileName.room_steps_nesw, 2, -2, "Sorceress", null, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(TileName.room_steps_nesw, 3, -2, "Sorceress", null, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(TileName.room_steps_nesw, 4, -2, "Sorceress", null, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(TileName.room_steps_nesw, 2, -3, "Sorceress", null, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(TileName.room_steps_nesw, 4, -3, "Sorceress", null, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(TileName.room_steps_nesw, 3, -4, "", TreasureType.MEGA_CHEST, new TileDetails(true, true, false, 0, 0, false)),
				
		});
		
//		half-baked trait/
		
		aBossChats= new BossChat[]{
				new BossChat(Trigger.Entrance, new BossSpeech[]{
						new BossSpeech("Hey! I'm not that evil!", Func.emote, false)
				}, PostFunc.StartingRoom, 
				-1, 0, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.FifthKill, new BossSpeech[]{
						new BossSpeech("What're you doing!? You're the evil one!", Func.emote, false)
				}, PostFunc.FinishBossChat, 
				1, -1, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.ThirdKill, new BossSpeech[]{
						new BossSpeech("Look it was just one orphanage, everyone makes mistakes!", Func.emote, false)
				}, PostFunc.FinishBossChat, 
				-1, 3, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.SixthTurn, new BossSpeech[]{
						new BossSpeech("Come on, I deserve a second chance.", Func.emote, false)
				}, PostFunc.FinishBossChat, 
				-1, 6, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.attacked_early, new BossSpeech[]{
						new BossSpeech("No fair, the ritual was almost complete!", Func.emote, false)
				}, PostFunc.FinishBossChat, 
				-1, -1, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.ComingToAttack, new BossSpeech[]{
						new BossSpeech("Aha! Now the ritual is complete.", Func.emote, false),
						new BossSpeech("And by the way it was actually TWO orphanages muahahaha", Func.emote, false)
				}, PostFunc.FireDemonMoveToBoard, 
				-1, -1, 1, DelayEffect.APPEAR, new PostEffect[]{
						new PostEffect(Trait.Halfbaked, false)
				})
		};
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1),
		};
		aTurnLimitActions= new TurnLimitAction[]{(
				new TurnLimitAction(ActionType.BossChat, new String[]{"\""+Trigger.ComingToAttack+"\""}, "The ritual is complete")
				)};
		aTurnLimit=10;
		aMonsters=MonsterFactory.getMonsters(Region.Stone, 3);
		addDungeon();
		createAdventure();

		// EYE-BEAST ADVENTURE //

		aAdvName="The Eye Beast";
		//aAdvDescription="Loot all the treasure";
		aAdvIcon="stone_temple";
		aAdvX=1.25f;
		aAdvY=1.5f;

		aName="Curious Crypt";
		aDescription="Get to the crypt";
		aReward=100;
		aTerrainType=TerrainType.stone;
		aBoss="Eye Beast";
		aBossName="Eye Beast";
		aBossChats= new BossChat[]{};
		aStartingTile=Tile.get("n");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(TileName.room_round_s, 0, -4, "", null, new TileDetails(true, false, false, 0, 0, true)),
				new TileLocation(TileName.room_steps_nesw, 0, -3, "Shade", null, null),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Arrive, "objective", 1),
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.getMonsters(Region.Stone, 2);
		addDungeon();

		aName="Staring match";
		aDescription="BEHOLD! The Eye Beast! (too much?)";
		aReward=100;
		aTerrainType=TerrainType.stone;
		aBoss="Eye Beast";
		aBossName="Eye Beast";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.Entrance, new BossSpeech[]{
						new BossSpeech("I seeeeee youuuu", Func.emote, false)
				}, PostFunc.StartingRoom, 
				-1, 0, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.ThirdTurn, new BossSpeech[]{
						new BossSpeech("Tiiick tooock herooo", Func.emote, false)
				}, PostFunc.FinishBossChat, 
				-1, 3, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.SixthTurn, new BossSpeech[]{
						new BossSpeech("Sssoooonnn", Func.emote, false)
				}, PostFunc.FinishBossChat, 
				-1, 6, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.kill, new BossSpeech[]{
						new BossSpeech("Out of tiiime", Func.emote, false),
						new BossSpeech("*stare*", Func.emote, false),
						new BossSpeech("*stare*", Func.emote, false),
						new BossSpeech("*blink*", Func.emote, false),
						new BossSpeech("Oops, uhh...", Func.emote, false),
						new BossSpeech("Ah this should be it", Func.emote, false),
						new BossSpeech("*STAAAARE*", Func.emote, false)
				}, PostFunc.FailDungeon, 
				-1, -1, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.attacked_early, new BossSpeech[]{
						new BossSpeech("Hey!", Func.emote, false)
				}, PostFunc.FinishBossChat, 
				-1, -1, 1, DelayEffect.APPEAR, null)
		};
		aStartingTile=TileName.room_collapse_new;
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(TileName.room_torture_s, -1, -3, "BOSS", null, new TileDetails(true, true, true, -1, -3, false)),
				new TileLocation(TileName.room_round_s, 1, -3, "", TreasureType.Large_Chest, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(TileName.corr_rubble_e, -2, -1, "", TreasureType.MEGA_CHEST, new TileDetails(true, true, false, 0, 0, false)),
				
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1)
		};
		aTurnLimit=8;
		aTurnLimitActions= new TurnLimitAction[]{
				new TurnLimitAction(ActionType.BossChat, new String[]{"\""+Trigger.kill.toString()+"\""}, "Petrified"),
				};
		aMonsters=MonsterFactory.getMonsters(Region.Stone, 3);
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
		aAdvIcon="jungle_gem";
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
				new TileLocation(Tile.get("js"), 0, -4, "", TreasureType.Massive_Gem, null),
				new TileLocation(Tile.get("jsnew"), 0, -3, "Gargoyle", null, null),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Collect, TreasureType.Massive_Gem.toString(), 1),
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.getMonsters(Region.Jungle, 2);
		addDungeon();

		aName="Run";
		aDescription="Get out before the roof collapses!";
		aReward=100;
		aTerrainType=TerrainType.jungle;
		aBoss="Eye Beast";
		aBossName="Eye Beast";
		aBossChats= new BossChat[]{};
		aStartingTile=Tile.get("jnew");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("js"), -1, -3, "", null, new TileDetails(true, false, false, 0, 0, true)),
				new TileLocation(Tile.get("judlr"), -1, -2, "Owl Bear", null, new TileDetails(true, false, false, 0, 0, false)),
				new TileLocation(Tile.get("jdr"), -2, -1, "", TreasureType.MEGA_CHEST, null),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Arrive, "objective", 1)
		};
		aTurnLimit=6;
		aTurnLimitActions= new TurnLimitAction[]{
				new TurnLimitAction(ActionType.FailDungeon, new String[]{}, "Cave collapse")
		};
		aMonsters=MonsterFactory.getMonsters(Region.Jungle, 2);
		addDungeon();
		createAdventure();


		// MEDUSA ADVENTURE //

		aAdvName="Medusa Adventure";
		aAdvIcon="jungle_snake";
		aAdvX=-2;
		aAdvY=0;

		aName="Snakes";
		aDescription="I hate snakes";
		aReward=100;
		aTerrainType=TerrainType.jungle;
		aBoss="Medusa";
		aBossName="Medusa";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.Entrance, new BossSpeech[]{
						new BossSpeech("How dare you interloper!", Func.emote, false)
				}, PostFunc.FinishBossChat, 
				1, -1, 1, DelayEffect.APPEAR, null),
		};
		aStartingTile=Tile.get("jnw");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("jur"), -2, 0, "Poisonous Snake", null, new TileDetails(true, true, true, 0, -2, false)),
				new TileLocation(Tile.get("jlr"), -1, -2, "Poisonous Snake", null, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jd"), 1, -2, "Poisonous Snake", null, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jdl"), 0, -2, "Poisonous Snake", null, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jrd"), -2, -2, "Poisonous Snake", null, new TileDetails(true, true, false, 0, 0, false)),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "Poisonous Snake", 3),
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.getMonsters(Region.Jungle, 1);;
		addDungeon();

		aName="Stony Stare";
		aDescription="Defeat Medusa before you get turned to stone!";
		aReward=100;
		aTerrainType=TerrainType.jungle;
		aBoss="Medusa";
		aBossName="Medusa";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.Entrance, new BossSpeech[]{
						new BossSpeech("I will have my vengeance!", Func.emote, false)
				}, PostFunc.StartingRoom, 
				-1, 0, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.ThirdTurn, new BossSpeech[]{
						new BossSpeech("Look me in the eye, adventurer!", Func.emote, false)
				}, PostFunc.FinishBossChat, 
				-1, 3, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.SixthTurn, new BossSpeech[]{
						new BossSpeech("Almost out of time!", Func.emote, false)
				}, PostFunc.FinishBossChat, 
				-1, 6, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.kill, new BossSpeech[]{
						new BossSpeech("SsssSSSS!", Func.emote, false),
				}, PostFunc.FailDungeon, 
				-1, -1, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.attacked_early, new BossSpeech[]{
						new BossSpeech("Good! A proper fight!", Func.emote, false)
				}, PostFunc.FinishBossChat, 
				-1, -1, 1, DelayEffect.APPEAR, null)
		};
		aStartingTile=Tile.get("jnew");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("jsew"), 0, -3, "BOSS", null, new TileDetails(true, true, true, 0, -3, false)),
				new TileLocation(Tile.get("je"), -2, 0, "", TreasureType.MEGA_CHEST, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jdr"), -2, -3, "", TreasureType.MEGA_CHEST, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jdl"), 2, -3, "", TreasureType.MEGA_CHEST, new TileDetails(true, true, false, 0, 0, false)),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1)
		};
		aTurnLimit=10;
		aTurnLimitActions=new TurnLimitAction[]{
				new TurnLimitAction(ActionType.BossChat, new String[]{"\""+Trigger.kill.toString()+"\""}, "Petrified"),
		};
		aMonsters=MonsterFactory.jungleMonsters;
		addDungeon();
		createAdventure();

		// ETTIN ADVENTURE //

		aAdvName="Ettin Adventure";
		//aAdvDescription="Loot all the treasure";
		aAdvIcon="jungle_mailbox";
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
						new BossSpeech("I'm gonna eat your arms!", Func.emote, false),
						new BossSpeech("I'm gonna eat your legs!", Func.emote, false)
				}, PostFunc.StartingRoom, 
				0, 0, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.attacked_early, new BossSpeech[]{
						new BossSpeech("We're gonna eat you! DIE!!", Func.emote, false)
				}, PostFunc.FinishBossChat, 
				-1, -1, 1, DelayEffect.APPEAR, null)
		};
		aStartingTile=Tile.get("jne");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("jdl"), 2, -2, "BOSS", null, new TileDetails(true, true, false, 2, -2, false)),
				new TileLocation(Tile.get("jul"), 2, 0, "", TreasureType.MEGA_CHEST, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(Tile.get("js"), 0, -2, "", TreasureType.MEGA_CHEST, new TileDetails(true, true, false, 0, 0, false)),
				
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1),
		};
		aTurnLimit=8;
		aTurnLimitActions= new TurnLimitAction[]{(
				new TurnLimitAction(ActionType.HeroHealth, new String[]{"-2"}, "Lunchtime")
				)};
		aMonsters=MonsterFactory.jungleMonsters;
		addDungeon();
		createAdventure();
		
		// CHIMERA ADVENTURE //

		aAdvName="Chimera Adventure";
		//aAdvDescription="Loot all the treasure";
		aAdvIcon="jungle_temple";
		aAdvX=-1;
		aAdvY=-1;

		aName="Wild Life";
		aDescription="Defend yourself!";
		aReward=100;
		aTerrainType=TerrainType.jungle;
		aBoss="Chimera";
		aBossName="Chimera";
		aBossChats= new BossChat[]{};
		aStartingTile=Tile.get("jlud");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("jr"), -2, 0, "Poisonous Snake", null, new TileDetails(true, true, true, -2, 0, false)),
				new TileLocation(Tile.get("jdrl"), -1, -1, "Plague Rat", null, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jr"), -2, -1, "", TreasureType.Large_Chest , new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jur"), -1, 1, "Dire Scorpion", null, new TileDetails(true, true, false, 0, 0, false)),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "ANY", 3),
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.noMonsters;
		addDungeon();

		aName="Awakened";
		aDescription="Defeat the chimera!";
		aReward=100;
		aTerrainType=TerrainType.jungle;
		aBoss="Chimera";
		aBoss="Chimera";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.Entrance, new BossSpeech[]{
						new BossSpeech("Screeeeee!", Func.emote, false),
				}, PostFunc.Chase, 
				-1, 0, 1, DelayEffect.APPEAR, null),
		};
		aStartingTile=Tile.get("judlr");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("jr"), -5, 0, "BOSS", null, new TileDetails(true, true, true, -5, 0, false)),
				new TileLocation(Tile.get("jlr"), -1, 0, "", null, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jlr"), -2, 0, "", null, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jlr"), -3, 0, "", null, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jlr"), -4, 0, "", null, new TileDetails(true, true, false, 0, 0, false)),
				
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
		aAdvIcon="jungle_keepout";
		aAdvX=-2;
		aAdvY=-1;

		aName="Assassination";
		aDescription="Defeat the ogre while he's still sleepy";
		aReward=100;
		aTerrainType=TerrainType.jungle;
		aBoss="Ogre";
		aBossName="Ogre";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.Entrance, new BossSpeech[]{
						new BossSpeech("Yaaawn!", Func.emote, false)
				}, PostFunc.StartingRoom,
				-1, 0, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.ThirdTurn, new BossSpeech[]{
						new BossSpeech("Such a late night...", Func.emote, false)
				}, PostFunc.FinishBossChat,
				-1, 3, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.SixthTurn, new BossSpeech[]{
						new BossSpeech("*brushes teeth*", Func.emote, false)
				}, PostFunc.FinishBossChat,
				-1, 6, 1, DelayEffect.APPEAR, null),
				new BossChat(Trigger.ComingToAttack, new BossSpeech[]{
						new BossSpeech("Hey what are you doing here!?", Func.emote, false),
				}, PostFunc.FireDemonMoveToBoard, 
				-1, -1, 1, DelayEffect.APPEAR, new PostEffect[]{
						new PostEffect(Trait.Sleepy, false)
				}),
				new BossChat(Trigger.attacked_early, new BossSpeech[]{
						new BossSpeech("*snooze* huh- what?", Func.emote, false),
				}, null, 
				-1, -1, 1, null, null)
		};
		aStartingTile=Tile.get("jnwe");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("jsew"), 0, -3, "BOSS", null, null),
				new TileLocation(Tile.get("jw"), 2, -1, "Hilly Gnoll", TreasureType.Large_Chest, null),
				new TileLocation(Tile.get("je"), -2, -1, "Dire Scorpion", TreasureType.MEGA_CHEST, null),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1),
		};
		aTurnLimit=10;
		aTurnLimitActions= new TurnLimitAction[]{(
				new TurnLimitAction(ActionType.BossChat, new String[]{"\""+Trigger.ComingToAttack.toString()+"\""}, "Ogre wakes")
				)};
		aMonsters=MonsterFactory.jungleMonsters;
		addDungeon();
		createAdventure();
		
		
		// DRAGON ADVENTURE //

		aAdvName="Dragon Adventure";
		//aAdvDescription="Loot all the treasure";
		aAdvIcon="jungle_volcano";
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
				new TileLocation(Tile.get("judl"), 2, -1, "Jungle Warrior", TreasureType.Large_Chest, new TileDetails(true, true, true, 2, -1, false)),
				new TileLocation(Tile.get("judr"), -2, -1, "Pixies", TreasureType.Large_Chest, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(Tile.get("judlr"), 0, -2, "Man-Eating Plant", TreasureType.Large_Chest, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jdw"), 0, -3, "Gargoyle", TreasureType.Large_Chest, new TileDetails(true, true, false, 0, 0, false)),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Collect, TreasureType.Large_Chest.toString(), 3),
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.noMonsters;
		addDungeon();
		
		aName="Revenge";
		aDescription="Defeat the angry Dragon!";
		aReward=100;
		aTerrainType=TerrainType.jungle;
		aBoss="Dragon";
		aBossName="Dragon";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.Entrance, new BossSpeech[]{
						new BossSpeech("You will pay for your greed!", Func.emote, false)
				}, PostFunc.Chase, 
				-1, 0, 1, DelayEffect.APPEAR, null)
		};
		aStartingTile=Tile.get("jnews");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("jw"), 6, 0, "BOSS", null, new TileDetails(true, true, true, 6, 0, false)),
				new TileLocation(Tile.get("js"), 5, -1, "", TreasureType.GOLD_COIN, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jwen"), 5, 0, "", null, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jwe"), 4, 0, "", null, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jn"), 3, 1, "", TreasureType.GOLD_COIN, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jwes"), 3, 0, "", null, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jwe"), 2, 0, "", null, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(Tile.get("js"), 5, -1, "", TreasureType.GOLD_COIN, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jwen"), 1, 0, "", null, new TileDetails(true, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jnews"), -3, -1, "", TreasureType.MEGA_CHEST, new TileDetails(true, true, false, 0, 0, false)),
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
				aObjectives, aTurnLimit, aTurnLimitActions,
				aMonsters, aDrawRate,
				aTutorial
				));
		resetDungeon();		
	}

	private static void resetDungeon() {
		aName=""; aDescription=""; aReward=0;
		aTerrainType=null;
		aBoss=""; aBossName=""; aBossChats=null;
		aStartingTile=null; aStartingHand=null; aLayout=null;
		aObjectives=null; aTurnLimit=0; aTurnLimitActions=null;
		aMonsters=null; aDrawRate=new CardType[]{CardType.TILE, CardType.TILE, CardType.TILE, CardType.MONSTER, CardType.MONSTER, CardType.MONSTER, CardType.TREASURE};
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
