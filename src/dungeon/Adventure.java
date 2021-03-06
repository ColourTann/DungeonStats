package dungeon;

import item.Item.TreasureType;

import java.awt.List;
import java.util.ArrayList;

import dungeon.BossChat.*;
import dungeon.Dungeon.CardType;
import dungeon.Dungeon.TerrainType;
import dungeon.DungeonLayout.FountainType;
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
	float trophyX, trophyY;
	String trophyName;
	Region region;
	ArrayList<Dungeon> dungeons;
	int preReqs, numUnlocks;;
	@SuppressWarnings("unchecked")
	public Adventure(String name, String description, String icon, float aAdvX2, float aAdvY2, float trophyX, float trophyY, String trophyName, ArrayList<Dungeon> dungeons, int preReqs, int numUnlocks, Region region) {
		this.name=name; this.description=description; this.icon=icon;
		this.mapX=aAdvX2; this.mapY=aAdvY2;
		this.trophyX=trophyX; this.trophyY=trophyY; this.trophyName=trophyName;
		this.dungeons=(ArrayList<Dungeon>) dungeons.clone();
		this.preReqs=preReqs;
		this.region=region;
		this.numUnlocks=numUnlocks;
	}

	public String toJson(){
		String output="";
		boolean finale = icon.equalsIgnoreCase("finale");
		output+= Json.startList(name);
		output+= Json.addKey("Description", description, true);
		output+= Json.addKey("Icon", icon, true);
		
		if(finale) output+=Json.addKey("IconType", "large", true);
		
		output+= Json.addKey("region", region.toString(), true);
		output+= Json.addKey("mapX", mapX+"", true);
		output+= Json.addKey("mapY", mapY+"", true);
		if(finale){
			output+= Json.addKey("journalPassageComplete", 5, true);
		}
		if(numUnlocks>0)output += Json.addKey("numUnlocks", numUnlocks, true);
		if(preReqs>0)output+=Json.addKey("prereqsComplete", preReqs, true);
		if(!finale){
		output+=Json.addKey("trophy", trophyName, true);
		output+=Json.addKey("trophyX", trophyX, true);
		output+=Json.addKey("trophyY", trophyY, true);
		}
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

		output+=Json.startArray("Regions");

		output+=Json.enclose();
		output+=Json.addKey("name", "Stone", true);
		output+=Json.addKey("journal_passage", 1, true);
		output+=Json.addKey("x", 0, true);
		output+=Json.addKey("y", 0, false);
		output+=Json.endEnclose(true);

		output+=Json.enclose();
		output+=Json.addKey("name", "Jungle", true);
		output+=Json.addKey("journal_passage", 2, true);
		output+=Json.addKey("x", -1157, true);
		output+=Json.addKey("y", 0, false);
		output+=Json.endEnclose(true);

		output+=Json.enclose();
		output+=Json.addKey("name", "Mines", true);
		output+=Json.addKey("journal_passage", 3, true);
		output+=Json.addKey("x", 0, true);
		output+=Json.addKey("y", -684, false);
		output+=Json.endEnclose(true);
		
		output+=Json.enclose();
		output+=Json.addKey("name", "Finale", true);
		output+=Json.addKey("journal_passage", 4, true);
		output+=Json.addKey("noregion", true, true);
		output+=Json.addKey("x", 0, true);
		output+=Json.addKey("y", 0, false);
		output+=Json.endEnclose(false);

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

	public static String trophyLocations(){
		String output="";

		for(Adventure a:adventures){
			System.out.println(a.trophyX+":"+a.trophyY);
		}

		return output;
	}

	static String aName, aDescription; 
	static TerrainType aTerrainType;
	static String aBoss, aBossName; static BossChat[] aBossChats;
	static TileName aStartingTile; static Hand aStartingHand; static DungeonLayout aLayout;
	static Objective[] aObjectives; static int aTurnLimit; static TurnLimitAction[] aTurnLimitActions;
	static ArrayList<Monster> aMonsters; 
	static boolean aTutorial;
	static String aAdvName, aAdvDescription, aAdvIcon;
	static float aAdvX, aAdvY;
	static float aTrophyX, aTrophyY;
	static String aTrophyName; 
	static int aPrereqs;
	static Region currentRegion;
	static int aNumUnlocks;
	static ArrayList<Dungeon> aDungeons = new ArrayList<>();
	private static CardType[] aDrawRate;

	public static void setup(){	

		//STONE ZONE//
		currentRegion=Region.tutorial;
		
		// RAT ADVENTURE //

		ArrayList<Monster> ratList1 = MonsterFactory.getMonsters(new String[]{"Nasty Rat", "Giant Bat"});
		ArrayList<Monster> ratList2 = MonsterFactory.getMonsters(new String[]{"Nasty Rat", "Giant Bat", "Gnoll", "Rat Man"});

		aAdvName="Rats? How original!";
		//aAdvDescription="Delve into the basement and defeat the mighty... rats";
		aAdvIcon="stone_hatch";
		aNumUnlocks=3;
		aAdvX=1;
		aAdvY=.25f;
		aTrophyName="Rat King's Tail";
		aTrophyX= -2;
		aTrophyY= -1;
		aName="Squeak squeak";
		aDescription="Everyone's gotta start somewhere";
		
		aTerrainType=TerrainType.stone;
		aTutorial=true;
		aBoss="Rat King";
		aBossName="Rat King";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.Second_Turn, new BossSpeech[]{
						new BossSpeech("Squeak squeak squeak"),
				}),
				new BossChat(Trigger.SecondKill, new BossSpeech[]{
						new BossSpeech("Squeak!"),	
				}
						),
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
				new Objective(ObjectiveType.Defeat, "ANY", 3),
		};
		aTurnLimit=-1;
		aMonsters=ratList1;
		addDungeon();

		aName="Rat Pack";
		aDescription="Defeat the rat king!";
		
		aTerrainType=TerrainType.stone;
		aBoss="Rat King";
		aBossName="Rat King";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.ZerothTurn, new BossSpeech[]{
						new BossSpeech("Squeak!!"),
				}, PostFunc.StartingRoom
						),
						new BossChat(Trigger.SecondKill, new BossSpeech[]{
								new BossSpeech("*chitter chitter*")
						}
								),
								new BossChat(Trigger.FourthKill, new BossSpeech[]{
										new BossSpeech("OI!- uh i mean SQUEAK!!")
								}
										)
		};
		aStartingTile=TileName.room_steps_nesw;
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("nes"), -1, -3, "Rat Man", null, new TileDetails(false, true, true, -1, -3, false)),
				new TileLocation(Tile.get("s"), -1, -5, "BOSS", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("nesw"), 0, -2, "Nasty Rat", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("nws"), 1, -3, "Nasty Rat", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("nesw"), 0, -4, "Rat Man", null, new TileDetails(false, true, false, 0, 0, false)),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1, true)
		};
		aTurnLimit=-1;
		aMonsters=ratList2;
		addDungeon();
		createAdventure();
		
		
		currentRegion=Region.stone;
		

		// EMBRO ADVENTURE //
		ArrayList<Monster> embroList= MonsterFactory.getMonsters(new String[]{"Fire Imp", "Scary Spider", "Goblin", "Ghost", "Zombie", "Gnoll", "Bear Owl", "Fire Elemental", "Bandito"});
		aAdvName="Firelord";
		//aAdvDescription="Defeat the evil demon, Embro";
		aAdvIcon="stone_fire_cave";
		aAdvX=.2f;
		aAdvY=.8f;
		aTrophyName="Embro's Towel";
		aTrophyX=0.5f;
		aTrophyY=0;
		aName="Fiery Foray";
		aDescription="Scope out Embro's defences";
		
		aTerrainType=TerrainType.stone;
		aBoss="Embro";
		aBossName="Embro";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.ZerothTurn, new BossSpeech[]{
						new BossSpeech("HEY! What are you doing in my Evil Entrance Hall?"),
						new BossSpeech("Oh well, my imps will make short work of you!"),
				} 
						),
						new BossChat(Trigger.SecondKill, new BossSpeech[]{
								new BossSpeech("You're not supposed to be able to beat them!"),
								new BossSpeech("I should have invested in some better security..."),	
						}
								),
		};
		aStartingTile=Tile.get("ulr");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(TileName.room_semicircle_nes, -1, -2, "Fire Imp", null, new TileDetails(FountainType.Clairvoyance)),
				new TileLocation(TileName.room_semicircle_nsw, 1, -2, "Fire Imp", null, null),
				new TileLocation(TileName.room_semicircle_esw, 0, -3, "Gnoll", null, null)
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "ANY", 3),
		};
		aTurnLimit=-1;
		aMonsters=embroList;
		addDungeon();

		aName="Scorching Spoils";
		aDescription="Loot all of Embro's treasure";
		
		aTerrainType=TerrainType.stone;
		aBoss="Embro";
		aBossName="Embro";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.FirstTurn, new BossSpeech[]{
						new BossSpeech("HEY! Get out of my treasure chamber!"),
				}
						),
		};
		aStartingTile=TileName.room_steps_nesw;
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(TileName.room_semicircle_nes, -2, 0, "Ghost", TreasureType.Large_Chest, null),
				new TileLocation(TileName.room_collapse_esw, 0, -2, "Zombie", TreasureType.Large_Chest, null),
				new TileLocation(TileName.room_semicircle_nsw, 2, 0, "Fire Elemental", TreasureType.Large_Chest, null)
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Collect, TreasureType.Large_Chest.toString(), 3, true)
		};
		aTurnLimit=-1;
		aMonsters=embroList;
		addDungeon();

		aName="Bath Bomb";
		aDescription="Defeat Embro";
		
		aTerrainType=TerrainType.stone;
		aBoss="Embro";
		aBossName="Embro";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.ZerothTurn, new BossSpeech[]{
						new BossSpeech("WHO DARES INVADE THE HOT, HOT LAIR OF EMBRO, LORD OF FLAME?!"),
						new BossSpeech("Right in the middle of bath-time too. Look at this puddle!"),
						new BossSpeech("MINIONS! DESTROY THEM! BRING ME THEIR BONES!"),
				}, PostFunc.StartingRoom ),

				new BossChat(Trigger.Second_Turn, new BossSpeech[]{new BossSpeech("Argh, I have to dry off or I won't be my fiery best."),new BossSpeech("You'd better wait {TURNS_LEFT} more turns before fighting me or it won't be fair!"),}),
				new BossChat(Trigger.SixthTurn, new BossSpeech[]{new BossSpeech("MINIONS! Bring me more towels!."),new BossSpeech("I need to be ready for my big fight in 6 turns"),}),
				new BossChat(Trigger.EleventhTurn, new BossSpeech[]{new BossSpeech("Almost dry now! I'm coming for you next turn!"),}),
				new BossChat(Trigger.ComingToAttack, new BossSpeech[]{new BossSpeech("**WHOOSH** Finally dry! Now I'm going to get you!"),}, 
						PostFunc.FireDemonMoveToBoard,new PostEffect[]{new PostEffect(Trait.Damp, false)}),
						new BossChat(Trigger.attacked_early, new BossSpeech[]{new BossSpeech("Hey! I'm not ready yet!"),}),
						new BossChat(Trigger.ThirdKill, new BossSpeech[]{new BossSpeech("HAR HAR! YOU THINK I'LL MISS THAT {LAST_KILL_TYPE}?"),new BossSpeech("NO! HE WAS MY LEAST FAVORITE MINION!"),}),
						new BossChat(Trigger.FifthKill, new BossSpeech[]{
								new BossSpeech("*sigh* As usual my minions are bumbling fools.."),
								new BossSpeech("If only those sharks with spears attached to their heads had arrived."),
								new BossSpeech("Oh well. SEND MORE RUBBER DUCKIES!"),
						} 
								),
		};
		aStartingTile=Tile.get("u");
		aTurnLimitActions= new TurnLimitAction[]{(
				new TurnLimitAction(ActionType.BossChat, new String[]{"\""+Trigger.ComingToAttack.toString()+"\""}, "Embro attacks")
				)};
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(TileName.room_torture_s, 0, -5, "BOSS", null, null),
				new TileLocation(Tile.get("rd"), -2, -4, "", null, new TileDetails(FountainType.Clairvoyance)),
				new TileLocation(TileName.room_round_s, 2, -3, "", TreasureType.MEGA_CHEST, null),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1, true)
		};
		aTurnLimit=12;
		aMonsters=embroList;
		addDungeon();
		createAdventure(); 

		// MIMIC ADVENTURE //

		ArrayList<Monster> mimicList= MonsterFactory.getMonsters(new String[]{
				"Fire Imp", "Scary Spider", "Goblin", 
				"Ghost", "Skeleton", "Gnoll", 
				"Bear Owl", "Fire Elemental", "Bandito"});

		aAdvName="A Victimless Crime";
		//aAdvDescription="Loot all the treasure";
		aAdvIcon="stone_chest";
		aAdvX=1.25f;
		aAdvY=1.2f;
		aTrophyName= "Mimic Splinters";
		aTrophyX= -1;
		aTrophyY= -1;
		aName="Lovely loot";
		aDescription="Chests full of treasure!";
		
		aTerrainType=TerrainType.stone;
		aBoss="Mimic Queen";
		aBossName="Mimic Queen";
		aBossChats= new BossChat[]{};
		aStartingTile=Tile.get("ulr");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("news"), 0, -1, "", null, new TileDetails(FountainType.Decay)),
				new TileLocation(TileName.room_semicircle_nes, -1, -2, "Zombie", TreasureType.Large_Chest, null),
				new TileLocation(TileName.room_semicircle_nsw, 1, -2, "Bear Owl", TreasureType.Large_Chest, null),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Collect, "Large Chest", 2, true),
		};
		aTurnLimit=-1;
		aMonsters=mimicList;
		addDungeon();

		aName="Terrible Truth";
		aDescription="Get strong enough to beat the mimic before she catches you!";
		
		aTerrainType=TerrainType.stone; 
		aBoss="Mimic Queen";
		aBossName="Mimic Queen";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.ZerothTurn, new BossSpeech[]{
						new BossSpeech("You're the one who looted my grandparents!?"),
						new BossSpeech("DIE, evil adventurer!")
				}, PostFunc.Chase
						),
		};
		aStartingTile=Tile.get("news");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("udlr"), 4, 0, "", null, new TileDetails(FountainType.Clairvoyance)),
				new TileLocation(Tile.get("udlr"), 2, 1, "Mimic", null, null),
				new TileLocation(Tile.get("udlr"), 2, -1, "Mimic", null, null),
				new TileLocation(Tile.get("ew"), -1, 0, "", null, new TileDetails(false, true, true, -5, 0, false)),
				new TileLocation(Tile.get("ew"), -2, 0, "", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("ew"), -3, 0, "", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("ew"), -4, 0, "", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("r"), -5, 0, "BOSS", null, new TileDetails(false, true, false, 0, 0, false)),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1)
		};
		aTurnLimit=-1;
		aMonsters=mimicList;
		addDungeon();
		createAdventure();

		// GOBLIN ADVENTURE //

		ArrayList<Monster> goblinList= MonsterFactory.getMonsters(new String[]{
				"Goblin", "Scary Spider", "Nasty Rat", 
				"Skeleton", "Rat Man", "Gnoll", 
				"Bear Owl", "Bandito", "Scorpion"});

		aAdvName="Gobliiins";
		//aAdvDescription="Loot all the treasure";
		aAdvIcon="stone_skull_cave";
		aAdvX=2;
		aAdvY=.25f;
		aTrophyName="Goblin Teeth";
		aTrophyX=-1.5f;
		aTrophyY=0;
		aName="Goblin Menace";
		aDescription="Take out the guards";
		
		aTerrainType=TerrainType.stone;
		aBoss="Orc Warlord";
		aBossName="Orc Warlord";
		aBossChats= new BossChat[]{};
		aStartingTile=TileName.room_collapse_esw;
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(TileName.room_semicircle_nes, -2, 0, "Goblin", null, new TileDetails(false, true, true, -1, 1, false)),
				new TileLocation(TileName.room_steps_nesw, -1, 1, "Goblin", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(TileName.room_collapse_new, 0, 2, "Goblin", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(TileName.corr_crushed_ne, -2, 2, "Gnoll", null, new TileDetails(false, true, false, 0, 0, false)),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "ANY", 4),
		};
		aTurnLimit=-1;
		aMonsters=goblinList;
		addDungeon();

		aName="Orcs!";
		aDescription="Defeat the big orc";
		
		aTerrainType=TerrainType.stone;
		aBoss="Orc Warlord";
		aBossName="Orc Warlord";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.ZerothTurn, new BossSpeech[]{
						new BossSpeech("Oh crap it's one of those do-gooders from the ivory league."),
						new BossSpeech("I knew I should have been more careful"),}, PostFunc.StartingRoom),

						new BossChat(Trigger.FirstTurn, new BossSpeech[]{new BossSpeech("Wait a sec you're from the guild of dungoneering?"),
								new BossSpeech("Take them out with the trash, minions"),}),

								new BossChat(Trigger.SecondKill, new BossSpeech[]{new BossSpeech("Hey stop killing my monsters!")}),

								new BossChat(Trigger.FourthKill, new BossSpeech[]{new BossSpeech("Oi! I mean it!")}),

								new BossChat(Trigger.attacked_early, new BossSpeech[]{new BossSpeech("This'll be over quick, human!")}),
		};
		aStartingTile=TileName.room_collapse_new;
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(TileName.room_semicircle_nes, -1, -2, "Gnoll", null, new TileDetails(false, true, true, 0, -3, false)),
				new TileLocation(Tile.get("udlr"), 0, -2, "", null, new TileDetails(FountainType.Stupidity)),
				new TileLocation(TileName.room_semicircle_nsw, 1, -2, "Gnoll", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(TileName.room_semicircle_esw, 0, -3, "BOSS", null, new TileDetails(false, true, false, 0, 0, false))
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1, true)
		};
		aTurnLimit=-1;
		aMonsters=goblinList;
		addDungeon();
		createAdventure();

		// BLACK KNIGHT ADVENTURE //

		ArrayList<Monster> knightList= MonsterFactory.getMonsters(new String[]{
				"Goblin", "Fire Imp", "Nasty Rat", 
				"Mimic", "Skeleton", "Gnoll", 
				"Bear Owl", "Bandito", "Scorpion"});


		aAdvName="None Shall Pass";
		//aAdvDescription="Loot all the treasure";
		aAdvIcon="stone_castle";
		aAdvX=2.15f;
		aAdvY=1;
		aTrophyName="Black Knight's Helm";
		aTrophyX= 0;
		aTrophyY= -1;
		aName="Test your mettle";
		aDescription="Defeat a Skeleton!";
		
		aTerrainType=TerrainType.stone;
		aBoss="The Black Knight";
		aBossName="The Black Knight";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.ZerothTurn, new BossSpeech[]{
						new BossSpeech("Let's see what you can do, adventurer!")
				} 
						)
		};
		aStartingTile=Tile.get("drl");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("url"), 0, 2, "", null, new TileDetails(FountainType.Decay)),
				new TileLocation(Tile.get("dlu"), 1, 1, "", null, new TileDetails(FountainType.Blindness)),
				new TileLocation(Tile.get("ul"), 1, 2, "Skeleton", null, null),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "Skeleton", 1),
		};
		aTurnLimit=-1;
		aMonsters=knightList;
		addDungeon();

		aName="Flee!";
		aDescription="Defeat a minotaur";
		
		aTerrainType=TerrainType.stone;
		aBoss="Minotaur";
		aBossName="Minotaur";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.ZerothTurn, new BossSpeech[]{new BossSpeech("Grah! Black Knight says kill!"),new BossSpeech("Last guy from the ivory league lasted 10 seconds."), },PostFunc.Chase),
		};
		aStartingTile=Tile.get("udl");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("udl"), -1, 1, "", null, new TileDetails(FountainType.Power)),
				new TileLocation(Tile.get("ur"), 0, 1, "", null, null),
				new TileLocation(Tile.get("lu"), 1, 1, "", null, null),
				new TileLocation(Tile.get("dur"), 1, 0, "boss", null, null)
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1),
		};
		aTurnLimit=-1;
		aMonsters=knightList;
		addDungeon();

		aName="Dark duel";
		aDescription="Defeat the Black Knight!";
		
		aTerrainType=TerrainType.stone;
		aBoss="The Black Knight";
		aBossName="The Black Knight";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.ZerothTurn, new BossSpeech[]{new BossSpeech("A worthy challenger at last!")}, PostFunc.StartingRoom),
				new BossChat(Trigger.FirstKill, new BossSpeech[]{new BossSpeech("Good technique!")}),
				new BossChat(Trigger.SecondKill, new BossSpeech[]{new BossSpeech("Impressive form!")}),
				new BossChat(Trigger.ThirdKill, new BossSpeech[]{new BossSpeech("Getting some last minute practice in?")} 
						),
						new BossChat(Trigger.NinthTurn, new BossSpeech[]{
								new BossSpeech("Come on, I'm getting impatient!")
						} 
								),
								new BossChat(Trigger.ComingToAttack, new BossSpeech[]{
										new BossSpeech("Here I come, worthy challenger")
								}, PostFunc.FireDemonMoveToBoard 
										),
										new BossChat(Trigger.attacked_early, new BossSpeech[]{
												new BossSpeech("You're eager, I like it!"),
										}),
		};
		aStartingTile=TileName.room_collapse_new;
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(TileName.room_collapse_esw, 0, -3, "BOSS", null, null),
				new TileLocation(TileName.room_round_s, 2, -3, "", TreasureType.MEGA_CHEST, null),
				new TileLocation(TileName.room_round_s, -2, -3, "", TreasureType.MEGA_CHEST, null)
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1, true)
		};
		aTurnLimitActions= new TurnLimitAction[]{(
				new TurnLimitAction(ActionType.BossChat, new String[]{"\""+Trigger.ComingToAttack.toString()+"\""}, "Duel")
				)};
		aTurnLimit=8;
		aMonsters=knightList;
		addDungeon();
		createAdventure();

		// LICH ADVENTURE //

		ArrayList<Monster> lichst= MonsterFactory.getMonsters(new String[]{
				"Scary Spider", "Giant Bat", "Fire Imp", 
				"Skeleton", "Mimic", "Ghost", 
				"Fire Elemental", "Scorpion", "Shade"});

		aAdvName="Evil Lich!";
		//aAdvDescription="Loot all the treasure";
		aAdvIcon="stone_skull_tomb";
		aAdvX=2;
		aAdvY=2;
		aTrophyName="Ashes of the Lich";
		aTrophyX= -0.5f;
		aTrophyY= 0;


		aName="Curious Crypt";
		aDescription="Get to the ritual chamber in time!";
		
		aTerrainType=TerrainType.stone;
		aBoss="Lich";
		aBossName="Evil Lich";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.FirstTurn, new BossSpeech[]{new BossSpeech("First we need to grind the goblin skulls")}),
				new BossChat(Trigger.Second_Turn, new BossSpeech[]{new BossSpeech("Next mince 30 spider legs")}),
				new BossChat(Trigger.ThirdTurn, new BossSpeech[]{new BossSpeech("Then uhh... oh blast where did I leave my toads")}),
				new BossChat(Trigger.FifthTurn, new BossSpeech[]{new BossSpeech("Ah there we are. The hind legs of a newborn toad.")}),
				new BossChat(Trigger.SixthTurn, new BossSpeech[]{new BossSpeech("The tears of an orphan")}),
				new BossChat(Trigger.NinthTurn, new BossSpeech[]{new BossSpeech("Into the frying pan with a knob of butter")}),
				new BossChat(Trigger.kill, new BossSpeech[]{
						new BossSpeech("That smells perfect!"),
						new BossSpeech("Muahaha! Back to my true self again!")
				}, PostFunc.FailDungeon)
		};
		aStartingTile=Tile.get("dlr");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("ur"), -1, 1, "", TreasureType.Large_Chest, null),
				new TileLocation(Tile.get("udr"), 0, 2, "Giant Bat", null, null),
				new TileLocation(Tile.get("udl"), 1, 2, "", null, new TileDetails(FountainType.Combustion)),
				new TileLocation(Tile.get("udr"), -1, 3, "", null, new TileDetails(FountainType.Decay)),
				new TileLocation(Tile.get("udr"), -1, 4, "Mimic", null, null),
				new TileLocation(Tile.get("udl"), 1, 4, "Skeleton", null, null),
				new TileLocation(Tile.get("udlr"), 0, 5, "Fire Elemental", null, null),
				new TileLocation(Tile.get("u"), 0, 6, "", null, new TileDetails(false, false, false, 0, 0, true)),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Arrive, "objective", 1, true),
		};
		aTurnLimit=10;
		aTurnLimitActions= new TurnLimitAction[]{(
				new TurnLimitAction(ActionType.BossChat, new String[]{"\""+Trigger.kill+"\""}, "Ritual completes")
				)};
		aMonsters=lichst;
		addDungeon();

		aName="Shady Summoning";
		aDescription="Destroy the Evil Lich";
		
		aTerrainType=TerrainType.stone;
		aBoss="Lich";
		aBossName="Evil Lich";
		aBossChats= new BossChat[]{};
		aStartingTile=TileName.corr_crushed_ne;
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(TileName.room_steps_nesw, 3, -3, "BOSS", null, new TileDetails(false, true, true, 3, -3, false)),
				new TileLocation(TileName.room_cavern_nw, 2, 0, "", TreasureType.MEGA_CHEST, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(TileName.room_steps_nesw, 2, -2, "Sorceress", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(TileName.room_steps_nesw, 3, -2, "Sorceress", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(TileName.room_steps_nesw, 4, -2, "Sorceress", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(TileName.room_steps_nesw, 2, -3, "Sorceress", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(TileName.room_steps_nesw, 4, -3, "Sorceress", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(TileName.room_steps_nesw, 3, -4, "", TreasureType.MEGA_CHEST, new TileDetails(false, true, false, 0, 0, false)),

		});

		aBossChats= new BossChat[]{
				new BossChat(Trigger.ZerothTurn, new BossSpeech[]{new BossSpeech("Hey! I'm not that evil!")}, PostFunc.StartingRoom),
				new BossChat(Trigger.FirstTurn, new BossSpeech[]{new BossSpeech("I've filed all my papers with the Ivory League of Explorers!")}),
				new BossChat(Trigger.FirstKill, new BossSpeech[]{new BossSpeech("What're you doing!? You're the evil one!")}),
				new BossChat(Trigger.ThirdKill, new BossSpeech[]{new BossSpeech("Look it was just one orphanage, everyone makes mistakes!")}),
				new BossChat(Trigger.SixthTurn, new BossSpeech[]{new BossSpeech("Come on, I deserve a second chance.")}),
				new BossChat(Trigger.attacked_early, new BossSpeech[]{new BossSpeech("No fair, the ritual was almost complete!")}),
				new BossChat(Trigger.ComingToAttack, new BossSpeech[]{new BossSpeech("Aha! Now the ritual is complete."),
						new BossSpeech("And by the way it was actually TWO orphanages muahahaha")}, PostFunc.FireDemonMoveToBoard, new PostEffect[]{new PostEffect(Trait.Halfbaked, false)})
		};
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1, true),
		};
		aTurnLimitActions= new TurnLimitAction[]{(
				new TurnLimitAction(ActionType.BossChat, new String[]{"\""+Trigger.ComingToAttack+"\""}, "The ritual is complete")
				)};
		aTurnLimit=10;
		aMonsters=MonsterFactory.getMonsters(Region.stone, 3);
		addDungeon();
		createAdventure();

		// EYE-BEAST ADVENTURE //

		ArrayList<Monster> eyeblist= MonsterFactory.getMonsters(new String[]{
				"Scary Spider", "Giant Bat", "Gray Ooze", 
				"Snake", "Mimic", "Ghost", 
				"Minotaur", "Bear Owl", "Shade"});

		aAdvName="Nosy Eye";
		//aAdvDescription="Loot all the treasure";
		aAdvIcon="stone_temple";
		aAdvX=3f;
		aAdvY=1.25f;
		aTrophyName= "Jar of Eyeballs";
		aTrophyX= 1;
		aTrophyY=-1;
		aPrereqs=6;
		aNumUnlocks=3;
		aName="Ocular Rift";
		aDescription="Find your way into the eye-beast's lair";
		
		aTerrainType=TerrainType.stone;
		aBoss="Eye Beast";
		aBossName="Eye Beast";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.ZerothTurn, new BossSpeech[]{new BossSpeech("Finally I can get back to snooping on the neighbours with the orb")}),
				new BossChat(Trigger.FirstTurn, new BossSpeech[]{new BossSpeech("Wow, Glothnar certainly seems to be doing well for herself")}),
				new BossChat(Trigger.ThirdTurn, new BossSpeech[]{new BossSpeech("What's this? A visitor for Glothnar?")}),
				new BossChat(Trigger.FourthTurn, new BossSpeech[]{new BossSpeech("Oh no! An affair while Gaorok is out!")}),
				new BossChat(Trigger.FifthTurn, new BossSpeech[]{new BossSpeech("I should stop watching")}),
				new BossChat(Trigger.SeventhTurn, new BossSpeech[]{new BossSpeech("I really should stop watching")}),
		};
		aStartingTile=Tile.get("uld");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("drl"), -1, -1, "Gray Ooze", TreasureType.GOLD_COIN),
				new TileLocation(Tile.get("url"), -2, 1, "Mimic", null, new TileDetails(FountainType.Heroism)),
				new TileLocation(Tile.get("lr"), -3, 0, "", null, new TileDetails(FountainType.Decay)),
				new TileLocation(Tile.get("ur"), -4, 1, "", null, new TileDetails(FountainType.Blindness)),
				new TileLocation(Tile.get("dr"), -4, -1, "", null, new TileDetails(FountainType.Stupidity)),
				new TileLocation(Tile.get("udlr"), -4, 0, "Minotaur", TreasureType.Sapphire_Ring, null),
				new TileLocation(Tile.get("r"), -5, 0, "", null, new TileDetails(false, false, false, 0, 0, true)),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Arrive, "objective", 1, true),
		};
		aTurnLimit=-1;
		aMonsters=eyeblist;
		addDungeon();

		aName="Staring Match";
		aDescription="Steal the orb of nosiness";
		
		aTerrainType=TerrainType.stone;
		aBoss="Eye Beast";
		aBossName="Eye Beast";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.ZerothTurn, new BossSpeech[]{
						new BossSpeech("How daaare you interrupt me during my evening entertainment"),
						new BossSpeech("You thought you could steal my priiize?")}, PostFunc.StartingRoomNoPickup),
						new BossChat(Trigger.FirstTurn, new BossSpeech[]{new BossSpeech("I knew your were coming!")}),
						new BossChat(Trigger.ThirdTurn, new BossSpeech[]{new BossSpeech("Get out or I turn you to stone")}),
						new BossChat(Trigger.SixthTurn, new BossSpeech[]{new BossSpeech("Tick tock hero!")}),
						new BossChat(Trigger.kill, new BossSpeech[]{
								new BossSpeech("Out of tiiime"),
								new BossSpeech("*stare*"),
								new BossSpeech("*stare*"),
								new BossSpeech("*blink*"),
								new BossSpeech("Oops, uhh..."),
								new BossSpeech("Ah this should be it"),
								new BossSpeech("*STAAAARE*")}, PostFunc.FailDungeon),
								new BossChat(Trigger.attacked_early, new BossSpeech[]{new BossSpeech("Hey!")})
		};
		aStartingTile=TileName.room_collapse_new;
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("d"), -1, -3, "BOSS", TreasureType.Orb_of_Nosiness, new TileDetails(false, true, true, -1, -3, false)),
				new TileLocation(TileName.room_round_s, 1, -3, "", TreasureType.Large_Chest, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(TileName.corr_rubble_e, -2, -1, "", TreasureType.MEGA_CHEST, new TileDetails(false, true, false, 0, 0, false)),

		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Collect, TreasureType.Orb_of_Nosiness.toString(), 1, true)
		};
		aTurnLimit=8;
		aTurnLimitActions= new TurnLimitAction[]{
				new TurnLimitAction(ActionType.BossChat, new String[]{"\""+Trigger.kill.toString()+"\""}, "Petrified"),
		};
		aMonsters=eyeblist;
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
		currentRegion=Region.jungle;
		
		// SAPPHIRE ADVENTURE //

		ArrayList<Monster> sapphireList= MonsterFactory.getMonsters(new String[]{
				"Plague Rat", "Pixies", "Leggy Spider", 
				"Hilly Gnoll", "Lizardman", "ManEating Plant",
				"Air Elemental", "Dire Scorpion", "Owl Bear"});


		aAdvName="The Sapphire of Tlaloc";
		//aAdvDescription="Loot all the treasure";
		aAdvIcon="jungle_gem";
		aAdvX=-.75f;
		aAdvY=1.2f;
		aTrophyName= "Sapphire";
		aTrophyX= 1.5f;
		aTrophyY= 0;
		aName="Grab";
		aDescription="Steal the sapphire";
		
		aTerrainType=TerrainType.jungle;
		aBoss="Eye Beast";
		aBossName="Eye Beast";
		aBossChats= new BossChat[]{};
		aStartingTile=Tile.get("jul");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("judr"), -1, -1, "Pixies", null, new TileDetails(FountainType.Power)),
				new TileLocation(Tile.get("js"), 0, -3, "", TreasureType.Massive_Gem, null),
				new TileLocation(Tile.get("jsnew"), 0, -2, "Lizardman", null, null),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Collect, TreasureType.Massive_Gem.toString(), 1),
		};
		aTurnLimit=-1;
		aMonsters=sapphireList;
		addDungeon();

		aName="Run";
		aDescription="Get out before the roof collapses!";
		
		aTerrainType=TerrainType.jungle;
		aBoss="Eye Beast";
		aBossName="Eye Beast";
		aBossChats= new BossChat[]{};
		aStartingTile=Tile.get("jdlr");
		aLayout= new DungeonLayout(new TileLocation[]{

				new TileLocation(Tile.get("judlr"), 0, 1, "", null, null),
				new TileLocation(Tile.get("judl"), 1, 1, "", null, new TileDetails(FountainType.Blindness)),
				new TileLocation(Tile.get("jr"), -2, 1, "", TreasureType.Large_Chest, null),
				new TileLocation(Tile.get("judlr"), 0, 3, "Owl Bear", null, null),
				new TileLocation(Tile.get("jur"), -1, 3, "Lizardman", null, null),
				new TileLocation(Tile.get("jul"), 1, 3, "Lizardman", null, null),
				new TileLocation(Tile.get("ju"), 0, 4, "", null, new TileDetails(false, false, false, 0, 0, true)),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Arrive, "objective", 1, true)
		};
		aTurnLimit=9;
		aTurnLimitActions= new TurnLimitAction[]{
				new TurnLimitAction(ActionType.FailDungeon, new String[]{}, "Cave collapse")
		};
		aMonsters=sapphireList;
		addDungeon();
		createAdventure();


		// MEDUSA ADVENTURE //

		ArrayList<Monster> medusaList= MonsterFactory.getMonsters(new String[]{
				"Bloodstarved Bat", "Pixies", "Leggy Spider", 
				"Poisonous Snake", "Lizardman", "ManEating Plant",
				"Jungle Shaman", "Dire Scorpion", "Owl Bear"});

		aAdvName="Snakes on a Plain";
		aAdvIcon="jungle_snake";
		aAdvX=-1.5f;
		aAdvY=.5f;
		aTrophyName= "Snakeskin Belt";
		aTrophyX= 2.25f;
		aTrophyY= -1;
		aName="Why'd it have to be snakes?";
		aDescription="I hate snakes";
		
		aTerrainType=TerrainType.jungle;
		aBoss="Medusa";
		aBossName="Medusa";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.FirstKill, new BossSpeech[]{new BossSpeech("How dare you interloper!")}),
				new BossChat(Trigger.SecondKill, new BossSpeech[]{new BossSpeech("At least the Ivory Leaguers give me fair warning!")}),
		};
		aStartingTile=Tile.get("jnw");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("jrd"), -1, -2, "Poisonous Snake", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jdl"), 1, -2, "Poisonous Snake", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jdlr"), 0, -2, "Poisonous Snake", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jrd"), -2, -2, "Poisonous Snake", null, new TileDetails(false, true, false, 0, 0, false)),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "ANY", 3),
		};
		aTurnLimit=-1;
		aMonsters=medusaList;
		addDungeon();

		aName="Stony Stare";
		aDescription="Defeat Medusa before you get turned to stone!";
		
		aTerrainType=TerrainType.jungle;
		aBoss="Medusa";
		aBossName="Medusa";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.ZerothTurn, new BossSpeech[]{new BossSpeech("I will have my vengeance!")}, PostFunc.StartingRoom),
				new BossChat(Trigger.ThirdTurn, new BossSpeech[]{new BossSpeech("Look me in the eye, adventurer!")}),
				new BossChat(Trigger.SixthTurn, new BossSpeech[]{new BossSpeech("Almost out of time!")}),
				new BossChat(Trigger.kill, new BossSpeech[]{new BossSpeech("SsssSSSS!"),}, PostFunc.FailDungeon),
				new BossChat(Trigger.attacked_early, new BossSpeech[]{new BossSpeech("Good! A proper fight!")})
		};
		aStartingTile=Tile.get("jnew");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("jsew"), 0, -3, "BOSS", null, new TileDetails(false, true, true, 0, -3, false)),
				new TileLocation(Tile.get("judr"), -1, -1, "Bloodstarved Bat", TreasureType.Large_Chest, null),
				new TileLocation(Tile.get("jdr"), -2, -3, "Jungle Shaman", TreasureType.MEGA_CHEST, null),
				new TileLocation(Tile.get("jdl"), 2, -3, "Dire Scorpion", null, new TileDetails(FountainType.Knowledge)),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1, true)
		};
		aTurnLimit=10;
		aTurnLimitActions=new TurnLimitAction[]{
				new TurnLimitAction(ActionType.BossChat, new String[]{"\""+Trigger.kill.toString()+"\""}, "Petrified"),
		};
		aMonsters=medusaList;
		addDungeon();
		createAdventure();

		// ETTIN ADVENTURE //

		ArrayList<Monster> ettinList= MonsterFactory.getMonsters(new String[]{
				"Bloodstarved Bat", "Pixies", "Leggy Spider", 
				"Poisonous Snake", "Lizardman", "ManEating Plant",
				"Jungle Shaman", "Dire Scorpion", "Owl Bear"});

		aAdvName="Double Trouble";
		aAdvIcon="jungle_mailbox";
		aAdvX=-1.75f;
		aAdvY=1.5f;
		aTrophyName="Ettin Shampoo";
		aTrophyX=-2.5f;
		aTrophyY= 0;


		aName="Venture deeper";
		aDescription="Into the jungle in search of treasure";
		
		aTerrainType=TerrainType.jungle;
		aBoss="Ettin";
		aBossName="Ettin";
		aBossChats= new BossChat[]{

		};
		aStartingTile=Tile.get("julr");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("judr"), -1, -1, "", null, new TileDetails(FountainType.Heroism)),
				new TileLocation(Tile.get("jdlr"), 0, -2, "Jungle Shaman", null, null),
				new TileLocation(Tile.get("jdlr"), 1, -2, "Jungle Shaman", null, null),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "Jungle Shaman", 2),
		};
		aTurnLimit=-1;
		aMonsters=ettinList;
		addDungeon();

		aName="Eatin' Ettin";
		aDescription="He's ganging up on you!";
		
		aTerrainType=TerrainType.jungle;
		aBoss="Ettin";
		aBossName="Ettin";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.ZerothTurn, new BossSpeech[]{
						new BossSpeech("I'm gonna eat your arms!"),
						new BossSpeech("I'm gonna eat your legs!")
				}, PostFunc.StartingRoom),
				new BossChat(Trigger.Second_Turn, new BossSpeech[]{new BossSpeech("Don't make us come find you!")}),
				new BossChat(Trigger.FourthTurn, new BossSpeech[]{new BossSpeech("I'm hungry!"), new BossSpeech("I want his eyes!")}),
				new BossChat(Trigger.ComingToAttack, new BossSpeech[]{new BossSpeech("We're gonna eat you! DIE!!")}, PostFunc.FireDemonMoveToBoard),
				new BossChat(Trigger.attacked_early, new BossSpeech[]{new BossSpeech("Dinner's here!")})
		};
		aStartingTile=Tile.get("jne");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("jdl"), 2, -2, "BOSS", null, new TileDetails(false, true, false, 2, -2, false)),
				new TileLocation(Tile.get("jul"), 2, 0, "", TreasureType.MEGA_CHEST, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("js"), 0, -2, "", TreasureType.MEGA_CHEST, new TileDetails(false, true, false, 0, 0, false)),

		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1, true),
		};
		aTurnLimit=6;
		aTurnLimitActions= new TurnLimitAction[]{(
				new TurnLimitAction(ActionType.BossChat, new String[]{"\""+Trigger.ComingToAttack.toString()+"\""}, "Ettin attacks")
				)};
		aMonsters=ettinList;
		addDungeon();
		createAdventure();

		// CHIMERA ADVENTURE //

		ArrayList<Monster> chimeraList= MonsterFactory.getMonsters(new String[]{
				"Bloodstarved Bat", "Plague Rat", "Leggy Spider", 
				"Poisonous Snake", "Hilly Gnoll", "ManEating Plant",
				"Worm", "Dire Scorpion", "Owl Bear"});

		aAdvName="All Mixed Up";
		aAdvIcon="jungle_temple";
		aAdvX=-2;
		aAdvY=2;
		aTrophyName= "Chimera Venom";
		aTrophyX= -3;
		aTrophyY= -1;
		aName="Wild Life";
		aDescription="Defend yourself!";
		
		aTerrainType=TerrainType.jungle;
		aBoss="Chimera";
		aBossName="Chimera";
		aBossChats= new BossChat[]{};
		aStartingTile=Tile.get("jlud");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("jr"), -2, 0, "Poisonous Snake", null, new TileDetails(false, true, true, -2, 0, false)),
				new TileLocation(Tile.get("jdrl"), -1, -1, "Plague Rat", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jr"), -2, -1, "", TreasureType.Large_Chest , new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jur"), -1, 1, "Worm", null, new TileDetails(false, true, false, 0, 0, false)),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "ANY", 3),
		};
		aTurnLimit=-1;
		aMonsters=chimeraList;
		addDungeon();

		aName="Awakened";
		aDescription="Defeat the chimera!";
		
		aTerrainType=TerrainType.jungle;
		aBoss="Chimera";
		aBoss="Chimera";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.ZerothTurn, new BossSpeech[]{
						new BossSpeech("Screeeeee!"),
				}, PostFunc.Chase 
						),
		};
		aStartingTile=Tile.get("judlr");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("jr"), -5, 0, "BOSS", null, new TileDetails(false, true, true, -5, 0, false)),
				new TileLocation(Tile.get("jlr"), -4, 0, "", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jlr"), -3, 0, "", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jlr"), -2, 0, "", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jlr"), -1, 0, "", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("judlr"), 1, 1, "", null, new TileDetails(FountainType.Decay)),
				new TileLocation(Tile.get("judlr"), 1, -1, "", null, new TileDetails(FountainType.Blindness)),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1)
		};
		aTurnLimit=-1;
		aMonsters=chimeraList;
		addDungeon();
		createAdventure();

		// OGRE ADVENTURE //

		ArrayList<Monster> ogreList= MonsterFactory.getMonsters(new String[]{
				"Bloodstarved Bat", "Jungle Warrior", "Frenzied Goblin", 
				"Harpy", "Hilly Gnoll", "Rat Berserker",
				"Worm", "Gargoyle", "Owl Bear"});

		aAdvName="Ogre Menace";
		aAdvIcon="jungle_keepout";
		aAdvX=-2.5f;
		aAdvY=1;
		aTrophyName= "Ogre's Favourite Rock";
		aTrophyX= 2.5f;
		aTrophyY= 0;
		aName="Assassination";
		aDescription="Defeat the ogre while he's still sleepy";
		
		aTerrainType=TerrainType.jungle;
		aBoss="Ogre";
		aBossName="Ogre";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.ZerothTurn, new BossSpeech[]{new BossSpeech("Yaaawn!")}, PostFunc.StartingRoom),
				new BossChat(Trigger.ThirdTurn, new BossSpeech[]{new BossSpeech("Such a late night...")}),
				new BossChat(Trigger.SixthTurn, new BossSpeech[]{new BossSpeech("*brushes teeth*")}),
				new BossChat(Trigger.ComingToAttack, new BossSpeech[]{new BossSpeech("Hey what are you doing here!?"),}, PostFunc.FireDemonMoveToBoard, new PostEffect[]{new PostEffect(Trait.Sleepy, false)}),
				new BossChat(Trigger.attacked_early, new BossSpeech[]{new BossSpeech("*snooze* huh- what?"),
				})
		};
		aStartingTile=Tile.get("jnwe");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("jsew"), 0, -3, "BOSS", null, null),
				new TileLocation(Tile.get("jw"), 2, -1, "Harpy", TreasureType.Large_Chest, null),
				new TileLocation(Tile.get("je"), -2, -1, "Gargoyle", TreasureType.MEGA_CHEST, null),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1, true),
		};
		aTurnLimit=10;
		aTurnLimitActions= new TurnLimitAction[]{(
				new TurnLimitAction(ActionType.BossChat, new String[]{"\""+Trigger.ComingToAttack.toString()+"\""}, "Ogre wakes")
				)};
		aMonsters=ogreList;
		addDungeon();
		createAdventure();


		// DRAGON ADVENTURE //

		ArrayList<Monster> dragonList= MonsterFactory.getMonsters(new String[]{
				"Pixies", "Jungle Warrior", "Frenzied Goblin", 
				"Harpy", "Poisonous Snake", "Rat Berserker",
				"Worm", "Gargoyle", "Jungle Shaman"});

		aAdvName="Dragon's Hoard";
		aAdvIcon="jungle_volcano";
		aAdvX=-3;
		aAdvY=2;
		aTrophyName= "Dragon Claw";
		aTrophyX= -3.5f;
		aTrophyY= 0;
		aPrereqs=12;
		aNumUnlocks=3;
		aName="Heist";
		aDescription="Steal 3 chests";
		
		aTerrainType=TerrainType.jungle;
		aBoss="Dragon";
		aBossName="Dragon";
		aBossChats= new BossChat[]{};
		aStartingTile=Tile.get("jew");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("judl"), 2, -1, "Jungle Warrior", TreasureType.Large_Chest, new TileDetails(false, true, true, 2, -1, false)),
				new TileLocation(Tile.get("judr"), -2, -1, "Pixies", TreasureType.Large_Chest, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("judlr"), 0, -2, "ManEating Plant", TreasureType.Large_Chest, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jdw"), 0, -3, "Gargoyle", TreasureType.Large_Chest, new TileDetails(false, true, false, 0, 0, false)),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Collect, TreasureType.Large_Chest.toString(), 3, true),
		};
		aTurnLimit=-1;
		aMonsters=dragonList;
		addDungeon();

		aName="Revenge";
		aDescription="Defeat the angry Dragon!";
		
		aTerrainType=TerrainType.jungle;
		aBoss="Dragon";
		aBossName="Dragon";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.ZerothTurn, new BossSpeech[]{
						new BossSpeech("Here for the golden Sun Idol?"),
						new BossSpeech("Just like the last 5 losers from the Ivory League!"),
						new BossSpeech("You will pay for your greed!"),
				}, PostFunc.Chase
						)
		};
		aStartingTile=Tile.get("jnews");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("jw"), 6, 0, "BOSS", null, new TileDetails(false, true, true, 6, 0, false)),
				new TileLocation(Tile.get("js"), 5, -1, "", TreasureType.GOLD_COIN, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jwen"), 5, 0, "", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jwe"), 4, 0, "", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jn"), 3, 1, "", TreasureType.GOLD_COIN, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jwes"), 3, 0, "", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jwe"), 2, 0, "", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("js"), 5, -1, "", TreasureType.GOLD_COIN, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jwen"), 1, 0, "", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("jnews"), -3, -1, "", TreasureType.MEGA_CHEST, new TileDetails(false, true, false, 0, 0, false)),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1),
		};
		aTurnLimit=-1;
		aMonsters=dragonList;
		addDungeon();
		createAdventure();



		//MINES//
		currentRegion=Region.mines;

		ArrayList<Monster> trollList= MonsterFactory.getMonsters(new String[]{
				"Miner", "Digger",
				"Rotting Corpse", "Blind Worm",
				"Cursed Mummy", "Earth Elemental"});

		
		// TROLL ADVENTURE //

		aAdvName="Mine Troll";
		aAdvIcon="mines_rope_shaft";
		aAdvX=1f;
		aAdvY=-.5f;
		aTrophyName="Troll's Tankard";
		aTrophyX=-2f;
		aTrophyY=1;
		aName="Tools down";
		aDescription="Disrupt the mining operation";
		
		aTerrainType=TerrainType.mines;
		aBoss="Miner";
		aBossName="Miner";
		aStartingTile=Tile.get("murd");
		aBossChats= new BossChat[]{
				new BossChat(Trigger.ZerothTurn, new BossSpeech[]{
						new BossSpeech("We got an intruder guys."),
						new BossSpeech("Take him out!")
				}
						)
		};
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("mulr"), 0, 2, "Miner", null, new TileDetails(false, true, true, 0, 2, false)),
				new TileLocation(Tile.get("mlud"), 2, 0, "Miner", null, new TileDetails(false, true, false, 0, 0, false)),				
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "Miner", 3),
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.getMonsters(new String[]{"Miner"});
		addDungeon();

		aName="Troll attack!";
		aDescription="The miners set a troll on you";
		
		aTerrainType=TerrainType.mines;
		aBoss="Troll";
		aBossName="Troll";
		aStartingTile=Tile.get("muldr");
		aBossChats= new BossChat[]{
				new BossChat(Trigger.ZerothTurn, new BossSpeech[]{new BossSpeech("Troll smash!"),}, PostFunc.Chase),};
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("ml"), 6, 0, "BOSS", null, new TileDetails(false, true, true, 6, 0, false)),
				new TileLocation(Tile.get("mlr"), 5, 0, "", null, new TileDetails(false, true, false, 0, 0, false)),				
				new TileLocation(Tile.get("mlr"), 4, 0, "", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("mlr"), 3, 0, "", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("mlr"), 2, 0, "", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("mlr"), 1, 0, "", null, new TileDetails(false, true, false, 0, 0, false)),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", 1),
		};
		aTurnLimit=-1;
		aMonsters=trollList;
		addDungeon();
		createAdventure();

		// CYCLOPS ADVENTURE //

		ArrayList<Monster> cyclopsList= MonsterFactory.getMonsters(new String[]{
				"Digger", "Infected Slime", "Albino Goblin",
				"Rotting Corpse", "Blind Worm", "Wailing Ghost",
				"Cursed Mummy", "Genii", "Vampire"});
		
		
		aAdvName="Deep in the mines";
		aAdvIcon="mines_cave";
		aAdvX=2f;
		aAdvY=-.5f;
		aTrophyName="Cyclops' Monocle";
		aTrophyX=-1.5f;
		aTrophyY=-2f;
		aName="Mine Patrol";
		aDescription="Defeat the Cyclops";
		
		aTerrainType=TerrainType.mines;
		aBoss="Cyclops";
		aBossName="Cyclops";
		aStartingTile=Tile.get("muld");
		aBossChats= new BossChat[]{
				new BossChat(Trigger.ZerothTurn, new BossSpeech[]{
						new BossSpeech("A snack?"),
						new BossSpeech("You'll never get past me!")
				}, PostFunc.StartingRoom
						)
		};
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("mur"), -3, 2, "BOSS", null, new TileDetails(false, true, true, -3, 2, false)),
				new TileLocation(Tile.get("mr"), -3, 0, "Vampire", TreasureType.MEGA_CHEST, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("mudlr"), -1, 1, "", TreasureType.Large_Chest, new TileDetails(false, true, false, 0, 0, false)),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1, true),
		};
		aTurnLimit=-1;
		aMonsters=cyclopsList;
		addDungeon();
		createAdventure();

		// SENTRY ADVENTURE //

		ArrayList<Monster> sentryList= MonsterFactory.getMonsters(new String[]{
				"Dwarvern Explosives", "Infected Slime", "Albino Goblin",
				"Spider Drill", "Blind Worm", "Wailing Ghost",
				"Cave Troll", "Genii", "Vampire"});
		
		aAdvName="Target Acquired";
		aAdvIcon="mines_hut";
		aAdvX=1.5f;
		aAdvY=-1f;
		aTrophyName="The Sentry's Gunpowder";
		aTrophyX=-1f;
		aTrophyY=1f;
		aName="Steal it all!";
		aDescription="Grab some piles of gold ore";
		
		aTerrainType=TerrainType.mines;
		aBoss="Dwarf Sentry";
		aBossName="Dwarf Sentry";
		aStartingTile=Tile.get("muldr");
		aBossChats= new BossChat[]{
				new BossChat(Trigger.FirstKill, new BossSpeech[]{
						new BossSpeech("INTRUDER DETECTED"),
						new BossSpeech("SETTING UP SECONDARY PERIMETER")
				}
						)
		};
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("mu"), 0, 2, "", TreasureType.Gold_Ore, new TileDetails(FountainType.Clairvoyance)),
				new TileLocation(Tile.get("mu"), 1, 2, "Rust Monster", TreasureType.Gold_Ore, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("mu"), -1, 2, "Gelatinous Cube", TreasureType.Gold_Ore, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("mudlr"), -1, 1, "Clattering Bones", TreasureType.Gold_Ore, new TileDetails(false, true, false, 0, 0, false)),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Collect, TreasureType.Gold_Ore.toString(), 3, true),
		};
		aTurnLimit=-1;
		aMonsters=sentryList;
		addDungeon();

		aName="Get out alive";
		aDescription="Defeat the sentry before it shoots!";
		
		aTerrainType=TerrainType.mines;
		aBoss="Dwarf Sentry";
		aBossName="Dwarf Sentry";
		aStartingTile=Tile.get("muld");
		aBossChats= new BossChat[]{
				new BossChat(Trigger.ZerothTurn, new BossSpeech[]{
						new BossSpeech("DROP THE GOLD AND PUT YOUR HANDS UP"),
						new BossSpeech("THEN I WILL DESTROY YOU")
				}, PostFunc.StartingRoom
						),
						new BossChat(Trigger.SecondKill, new BossSpeech[]{
								new BossSpeech("YOU ARE ONLY MAKING IT WORSE FOR YOURSELF!")
						}
								),
								new BossChat(Trigger.attacked_early, new BossSpeech[]{
										new BossSpeech("SERVOS NOT CHARGED!"),
								}),
								new BossChat(Trigger.kill, new BossSpeech[]{
										new BossSpeech("TARGET ACQUIRED, FIRING!")
								}, PostFunc.FailDungeon
										)

		};
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("mdr"), -3, 0, "BOSS", null, new TileDetails(false, true, true, -3, 0, false)),
				new TileLocation(Tile.get("mdlr"), 0, -2, "", TreasureType.GOLD_COIN, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("mulr"), 0, 2, "Digger", TreasureType.Large_Chest, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("mudlr"), -1, 1, "Wailing Ghost", TreasureType.MEGA_CHEST, new TileDetails(false, true, false, 0, 0, false)),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", 1, true),
		};
		aTurnLimit=9;
		aTurnLimitActions= new TurnLimitAction[]{(
				new TurnLimitAction(ActionType.BossChat, new String[]{"\""+Trigger.kill.toString()+"\""}, "Sentry shoots")
				)};
		aMonsters=sentryList;
		addDungeon();
		createAdventure();

		// MECHA ADVENTURE //

		ArrayList<Monster> mechaList= MonsterFactory.getMonsters(new String[]{
				"Dwarvern Explosives", "Rust Monster", "Albino Goblin",
				"Spider Drill", "Clattering Bones", "Wailing Ghost",
				"Cave Troll", "Nymph", "Vampire"});
		
		
		aAdvName="Mechanical Menace";
		aAdvIcon="mines_cart";
		aAdvX=2.5f;
		aAdvY=-1f;
		aTrophyName="Mecha's Spring";
		aTrophyX=-0.5f;
		aTrophyY=-2;

		aName="Deeper and deeper!";
		aDescription="Venturing deeper into the mines you run into vampires!";
		
		aTerrainType=TerrainType.mines;
		aBoss="Dwarf Mecha";
		aBossName="Dwarf Mecha";
		aStartingTile=Tile.get("murd");
		aBossChats= new BossChat[]{
		};
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("mul"), 3, 1, "Vampire", null, new TileDetails(false, true, true, 1, -3, false)),
				new TileLocation(Tile.get("muld"), 3, 0, "", TreasureType.Large_Chest, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("mdl"), 3, -1, "Vampire", null, null),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "Vampire", 2),
		};
		aTurnLimit=-1;
		aMonsters=mechaList;
		addDungeon();

		aName="Caught!";
		aDescription="";
		
		aTerrainType=TerrainType.mines;
		aBoss="Dwarf Mecha";
		aBossName="Dwarf Mecha";
		aStartingTile=Tile.get("muld");
		aBossChats= new BossChat[]{
				new BossChat(Trigger.ZerothTurn, new BossSpeech[]{
						new BossSpeech("Get out of here"),
						new BossSpeech("You've been causing loads of trouble!")
				}, PostFunc.Chase
						),
		};
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("md"), 1, -4, "BOSS", null, new TileDetails(null)),
				new TileLocation(Tile.get("mud"), 1, -3, "", null, new TileDetails(null)),
				new TileLocation(Tile.get("mul"), 1, -2, "", null, new TileDetails(null)),
				new TileLocation(Tile.get("mrd"), 0, -2, "", null, new TileDetails(null)),
				new TileLocation(Tile.get("mud"), 0, -1, "", null, new TileDetails(null)),
				new TileLocation(Tile.get("mudlr"), 1, 1, "", null, new TileDetails(FountainType.Decay)),

		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", 1),
		};
		aTurnLimit=-1;
		aMonsters=mechaList;
		addDungeon();

		createAdventure();

		//DWARF MASTERPIECE ADVENTURE//

		ArrayList<Monster> masterpieceList= MonsterFactory.getMonsters(new String[]{
				"Dwarvern Explosives", "Digger", "Albino Goblin",
				"Spider Drill", "Clattering Bones", "Wailing Ghost",
				"Cave Troll", "Earth Elemental", "Vampire"});
		
		aAdvName="Marvelous Masterpiece";
		aAdvIcon="mines_factory";
		aAdvX=1f;
		aAdvY=-1.5f;
		aTrophyName="MegaDrill Cog";
		aTrophyX=0;
		aTrophyY=1;
		aName="Workshop infiltration";
		aDescription="Get into the secret workshop";
		
		aTerrainType=TerrainType.mines;
		aBoss="Dwarf Masterpiece";
		aBossName="Dwarf Masterpiece";
		aStartingTile=Tile.get("murd");
		aBossChats= new BossChat[]{
				new BossChat(Trigger.FirstKill, new BossSpeech[]{
						new BossSpeech("They're trying to get in!"),
						new BossSpeech("Don't let them in the workshop")
				}
						),
						new BossChat(Trigger.SecondKill, new BossSpeech[]{
								new BossSpeech("You fools, don't you know how long this has taken to build?"),
						}
								)
		};
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("mudr"), 2, 0, "", TreasureType.Large_Chest, new TileDetails(false, true, true, 2, 0, false)),
				new TileLocation(Tile.get("mudlr"), 2, -1, "Gelatinous Cube", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("mudlr"), 2, 1, "Clattering Bones", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("mlr"), 3, 0, "Earth Elemental", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("ml"), 4, 0, "", null, new TileDetails(false, true, false, 0, 0, true)),

		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Arrive, "objective", 1, true),
		};
		aTurnLimit=-1;
		aMonsters=masterpieceList;
		addDungeon();

		aName="Demolition";
		aDescription="Destroy the masterpiece";
		
		aTerrainType=TerrainType.mines;
		aBoss="Dwarf Masterpiece";
		aBossName="Dwarf Masterpiece";
		aStartingTile=Tile.get("muld");
		aBossChats= new BossChat[]{
				new BossChat(Trigger.ZerothTurn, new BossSpeech[]{
						new BossSpeech("They're here!"),
						new BossSpeech("Destroy them before they get to me!")
				}, PostFunc.StartingRoom
						),
						new BossChat(Trigger.ThirdTurn, new BossSpeech[]{
								new BossSpeech("Their torch is running out!"),
								new BossSpeech("Once they're out of light they're screwed!")
						}
								),
								new BossChat(Trigger.SixthTurn, new BossSpeech[]{
										new BossSpeech("Just delay them a little more"),
								}
										),
										new BossChat(Trigger.attacked_early, new BossSpeech[]{
												new BossSpeech("Now you die!"),
										}),
										new BossChat(Trigger.kill, new BossSpeech[]{
												new BossSpeech("Out of time, adventurer. You have no choice in the dark."),
										}, PostFunc.FailDungeon
												)
		};
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("mudr"), -3, 0, "BOSS", null, new TileDetails(false, true, true, -3, 0, false)),
				new TileLocation(Tile.get("mudlr"), -2, -1, "Blind Worm", TreasureType.Large_Chest, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("mudlr"), -2, 1, "Cave Troll", TreasureType.MEGA_CHEST, new TileDetails(false, true, false, 0, 0, false)),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", 1, true),
		};
		aTurnLimit=10;
		aTurnLimitActions= new TurnLimitAction[]{(
				new TurnLimitAction(ActionType.BossChat, new String[]{"\""+Trigger.kill.toString()+"\""}, "Torch runs out")
				)};
		aMonsters=masterpieceList;
		addDungeon();
		createAdventure();

		//DWARF MAGNATE ADVENTURE//





		aAdvName="Magnate Mogul";
		aAdvIcon="mines_office";
		aAdvX=2f;
		aAdvY=-1.5f;
		aTrophyName="Magante's Stock Cert";
		aTrophyX=-.5f;
		aTrophyY=-2;
		aPrereqs=18;
		aName="Security";
		aDescription="Take out the guardians of the magnate";
		
		aTerrainType=TerrainType.mines;
		aBoss="Dwarf Magnate";
		aBossName="Dwarf Magnate";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.FirstKill, new BossSpeech[]{
						new BossSpeech("Perimeter breached"),
						new BossSpeech("It's ok, the security team should be able to sort it out"),
				}
						),
						new BossChat(Trigger.SecondKill, new BossSpeech[]{
								new BossSpeech("Ah they were disposable"),
								new BossSpeech("There's still a nasty troll"),
						}
								),
		};
		aStartingTile=Tile.get("md");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("mud"), 0, 1, "Digger", null, new TileDetails(false, true, true, 0, 1, false)),
				new TileLocation(Tile.get("mud"), 0, 2, "Miner", null, null),
				new TileLocation(Tile.get("mu"), 0, 3, "Cave Troll", TreasureType.GOLD_COIN, null),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "ANY", 3)
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.getMonsters(Region.mines, 3);
		addDungeon();


		aName="Get inside";
		aDescription="Get into the magnate's boardroom";
		
		aTerrainType=TerrainType.mines;
		aBoss="Dwarf Magnate";
		aBossName="Dwarf Magnate";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.ZerothTurn, new BossSpeech[]{
						new BossSpeech("Don't let them in the boardroom"),
						new BossSpeech("We're having a shareholder's meeting"),
				}
						),
						new BossChat(Trigger.SecondKill, new BossSpeech[]{
								new BossSpeech("I think I need to invest in some better protection"),
						}
								),
		};
		aStartingTile=Tile.get("mudr");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("mdlu"), 5, 0, "", null, new TileDetails(false, true, true, 5, 0, true)),
				new TileLocation(Tile.get("mul"), 5, 1, "", null, null),
				new TileLocation(Tile.get("mdl"), 5, -1, "", null, null),
				new TileLocation(Tile.get("mudlr"), 4, 0, "Cursed Mummy", null, null),
				new TileLocation(Tile.get("mulr"), 4, 1, "Cave Troll", null, null),
				new TileLocation(Tile.get("mdlr"), 4, -1, "Earth Elemental", null, null),
				new TileLocation(Tile.get("mudlr"), 2, 0, "Miner", null, null),
				new TileLocation(Tile.get("mur"), 1, 1, "Clattering Bones", TreasureType.Large_Chest, null),
				new TileLocation(Tile.get("mdr"), 1, -1, "Miner", null, null),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Arrive, "objective", 1, true)
		};
		aTurnLimit=-1;
		aMonsters=MonsterFactory.getMonsters(Region.mines, 3);
		addDungeon();

		aName="Win big";
		aDescription="Kill the dwarf magnate before you get trapped!";
		
		aTerrainType=TerrainType.mines;
		aBoss="Dwarf Magnate";
		aBossName="Dwarf Magnate";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.ZerothTurn, new BossSpeech[]{
						new BossSpeech("You will never stop us!"),
						new BossSpeech("We will take over the world and you are too weak to stand against us!")
				}, PostFunc.StartingRoom
						),
						new BossChat(Trigger.ThirdTurn, new BossSpeech[]{
								new BossSpeech("Once the death ray is complete, we will be unstoppable"),
						}
								),
								new BossChat(Trigger.SixthTurn, new BossSpeech[]{
										new BossSpeech("Tertiary subsystems coming online..."),
								}
										),
										new BossChat(Trigger.attacked_early, new BossSpeech[]{
												new BossSpeech("But I never got to use my death-ray"),
										}),
										new BossChat(Trigger.kill, new BossSpeech[]{
												new BossSpeech("Fire the death ray! Muahahaha!"),
										}, PostFunc.FailDungeon
												)
		};
		aStartingTile=Tile.get("mudr");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("mudrl"), 6, 0, "BOSS", null, new TileDetails(false, true, true, 6, 0, false)),
				new TileLocation(Tile.get("mlr"), 1, 1, "Miner", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("mudlr"), 2, 0, "Digger", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("mulr"), 3, 1, "Blind Worm", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("mlr"), 3, -1, "Gelatinous Cube", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("mudlr"), 4, 0, "Cave Troll", null, new TileDetails(false, true, false, 0, 0, false)),
				new TileLocation(Tile.get("mdlr"), 5, -1, "Earth Elemental", null, new TileDetails(false, true, false, 0, 0, false)),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "BOSS", -1, true)
		};
		aTurnLimit=10;
		aTurnLimitActions= new TurnLimitAction[]{(
				new TurnLimitAction(ActionType.BossChat, new String[]{"\""+Trigger.kill.toString()+"\""}, "Death ray")
				)};
		aMonsters=MonsterFactory.getMonsters(Region.mines, 3);
		addDungeon();
		createAdventure();
		
		currentRegion=Region.finale;
		ArrayList<Monster> finaleList= MonsterFactory.getMonsters(new String[]{
				"Albino Goblin", "Rust Monster", "Dwarvern Explosives", 
				"Spider Drill", "Clattering Bones", "Blind Worm",
				"Nymph", "Vampire", "Genii"});
		
		
		aAdvName="The Big Plan";
		//aAdvDescription="Delve into the basement and defeat the mighty... rats";
		aAdvIcon="finale";
		aAdvX=.25f;
		aAdvY=2f;
		aNumUnlocks=0;
		aPrereqs=19;
		aName="Rough them up";
		aDescription="Defeat some old faces";
		
		aTerrainType=TerrainType.stone;
		aBoss="Eye Beast";
		aBossName="Eye Beast";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.ZerothTurn, new BossSpeech[]{new BossSpeech("Where is magnate? He was supposed to be here already."),new BossSpeech("Oh no! It's that evil guild again! I'm calling the Ivory League on you!"),}),
				new BossChat(Trigger.FirstTurn, new BossSpeech[]{new BossSpeech("Is that Cecil Smythe? I need you to get help, that guild is here!"),}),
				new BossChat(Trigger.FirstKill, new BossSpeech[]{new BossSpeech("They're on a rampage! Hold me, Medusa!"),}),
		};
		aStartingTile=Tile.get("ulr");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("lr"), 0, -3, "The Black Knight", TreasureType.Large_Chest, null),
				new TileLocation(Tile.get("udr"), -2, -3, "Eye Beast", null, null),
				new TileLocation(Tile.get("lrd"), -1, -4, "Medusa", null, null),
				new TileLocation(Tile.get("udl"), 1, -2, "Clattering Bones", null, new TileDetails(FountainType.Power)),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Defeat, "The Black Knight", 1, true),
				new Objective(ObjectiveType.Defeat, "Eye Beast", 1, true),
				new Objective(ObjectiveType.Defeat, "Medusa", 1, true),
		};
		aTurnLimit=10;
		aTurnLimitActions= new TurnLimitAction[]{(
				new TurnLimitAction(ActionType.FailDungeon, new String[]{}, "Ivory League arrives")
				)};
		aMonsters=finaleList;
		addDungeon();
		
		
		aName="Operation: Ivory League sucks";
		aDescription="Plant the evidence";
		
		aTerrainType=TerrainType.stone;
		aBoss="Dragon";
		aBossName="Dragon";
		aBossChats= new BossChat[]{
				new BossChat(Trigger.ZerothTurn, new BossSpeech[]{new BossSpeech("Oh god they killed embro! And now they're coming for us!"),}),
				new BossChat(Trigger.FirstTurn, new BossSpeech[]{new BossSpeech("Come on Ivory League, I don't wanna die!"),}),
		};
		aStartingTile=Tile.get("uld");
		aLayout= new DungeonLayout(new TileLocation[]{
				new TileLocation(Tile.get("lrd"), -1, -1, "", TreasureType.GOLD_COIN, new TileDetails(FountainType.Blindness)),
				new TileLocation(Tile.get("lru"), -1, 1, "", null, new TileDetails(FountainType.Stupidity)),
				new TileLocation(Tile.get("rd"), -4, -1, "Troll", null, null),
				new TileLocation(Tile.get("lru"), -3, 1, "Dragon", null, null),
				new TileLocation(Tile.get("rd"), -4, 1, "Ogre", null, null),
				new TileLocation(Tile.get("lrd"), -3, -1, "Ettin", null, null),
				new TileLocation(Tile.get("udl"), -4, 0, "Angry Bunny", null, null),
				new TileLocation(Tile.get("r"), -5, 0, "", null, new TileDetails(false,false,false,0,0,true)),
		});
		aObjectives = new Objective[]{
				new Objective(ObjectiveType.Arrive, "objective", -1, true),
		};
		aTurnLimit=10;
		aTurnLimitActions= new TurnLimitAction[]{(
				new TurnLimitAction(ActionType.FailDungeon, new String[]{}, "Ivory League arrives")
				)};
		aMonsters=finaleList;
		addDungeon();
		
		createAdventure();
	}

	private static void addDungeon() {
		aDungeons.add(new Dungeon(
				aName, aDescription,
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
		aName=""; aDescription="";
		aTerrainType=null;
		aBoss=""; aBossName=""; aBossChats=null;
		aStartingTile=null; aStartingHand=null; aLayout=null;
		aObjectives=null; aTurnLimit=0; aTurnLimitActions=null;
		aMonsters=null; aDrawRate=new CardType[]{CardType.TILE, CardType.TILE, CardType.TILE, CardType.MONSTER, CardType.MONSTER, CardType.MONSTER, CardType.TREASURE};
		aTutorial=false;
	}

	private static ArrayList<Adventure> adventures = new ArrayList<>();
	private static void createAdventure(){
		adventures.add(new Adventure(aAdvName, aAdvDescription, aAdvIcon, aAdvX, aAdvY, aTrophyX, aTrophyY, aTrophyName, aDungeons, aPrereqs, aNumUnlocks, currentRegion));
		for(int i=0;i<aDungeons.size();i++){
			aDungeons.get(i).setFinal(i==aDungeons.size()-1);
		}
		resetAdventure();
	}

	private static void resetAdventure() {
		aAdvName=""; aAdvDescription=""; aAdvIcon=""; aAdvX=0; aAdvY=0; aTrophyName="unset"; aTrophyX=0; aTrophyY=0; aDungeons.clear(); aPrereqs=0; aNumUnlocks=1;
	}	
	
	public static String adventureDetails(){
		int grandTotal=0;
		String output="";
		
		for(Adventure a: adventures){
			int totalReward=0;
			output+=a.name+" [";
			for(Dungeon d:a.dungeons){
				totalReward+=d.reward;
				output+=d.name+"("+d.reward+")||";
			}
			grandTotal+=totalReward;
			output=output.substring(0, output.length()-2);
			
			output+="]";
			output += " ("+totalReward+") Total ("+grandTotal+")\n";
		}
		
		return output;
	}
	
}
