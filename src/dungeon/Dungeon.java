package dungeon;

import java.util.ArrayList;

import json.Json;
import dungeon.Tile.TileName;
import fighter.monster.Monster;

public class Dungeon {
	public enum TerrainType{stone, jungle, mines}
	String name, description; int reward;
	TerrainType terrainType;
	String boss, bossName; BossChat[] bossChats;
	TileName startingTile; Hand startingHand; DungeonLayout layout;
	Objective[] objectives; int turnLimit; TurnLimitAction[] turnLimitActions;
	ArrayList<Monster> monsters;
	boolean tutorial;
	public Dungeon(String name, String description, int reward,
			TerrainType terrainType, 
			String boss, String bossName, BossChat[] bossChat,
			TileName startingTile, Hand startingHand, DungeonLayout layout,
			Objective[] objectives, int turnLimit, TurnLimitAction[] turnLimitActions,
			ArrayList<Monster> monsters,
			boolean tutorial
			) {
		this.name=name; this.description=description; this.reward=reward;
		this.terrainType=terrainType;
		this.boss=boss; this.bossName=bossName; this.bossChats=bossChat;
		this.startingTile = startingTile; this.startingHand=startingHand; this.layout=layout;
		this.objectives=objectives; this.turnLimit=turnLimit; this.turnLimitActions=turnLimitActions;
		this.monsters=monsters;
		this.tutorial=tutorial;
	}
	
	public String toJson(){
		String output="";
		output+=Json.enclose();
		output += Json.startList(name);
		output += Json.addKey("Description", description, true);
		output += Json.addKey("Dungeon", terrainType.toString(), true);
		output += Json.addKey("Reward", reward, true);
		output += Json.addKey("Boss", boss, true);
		output += Json.addKey("BossName", bossName, true);
		output += Json.startList("BossChat");
		for(int i=0;i<bossChats.length;i++){
			BossChat bc = bossChats[i];
			output+=bc.toJson();
			if(i<bossChats.length-1){
				output=Json.addComma(output);
			}
		}
		output += Json.endEnclose(true); //bosschat end
		if(turnLimit>0){
			output += Json.addKey("TurnLimit", turnLimit, true);
			output += Json.addKey("TurnLimitStart", 0, true);
		}
		if(turnLimitActions!=null){
			output+=Json.startArray("TurnLimitActions");
			for(int i=0;i<turnLimitActions.length;i++){
				output+=Json.enclose();
				output+=turnLimitActions[i].toJson();
				output+=Json.endEnclose(i<turnLimitActions.length-1);
			}
			output+=Json.endArray(true);
		}
		output += Json.addKey("StartingTile", startingTile.toString(), true);
		if(startingHand!=null) output += startingHand.toJson();
		if(tutorial){
			output += Json.startArray("AutoTurnSequence");
			output+="0,\n1,\n0";
			output += Json.endArray(true);
			output += Json.startList("TurnMessages");
			output += Json.addKey("turn 0", "I'll play your first turn. It's easy!", true);
			output += Json.addKey("turn 1", "Now you have a go!", false);
			output += Json.endList(true);
		}
		output += Json.startArray("Objectives");
		for(int i=0;i<objectives.length;i++){
			Objective o = objectives[i];
			output += o.toJson();
			if(i<objectives.length-1) output = Json.addComma(output);
		}
		output += Json.endArray(true);
		output += layout.toJson();
		output += Json.startArray("Monsters");
		for(int i=0;i<monsters.size();i++){
			Monster m = monsters.get(i);
			output += "\""+m.name+"\"\n";
			if(i<monsters.size()-1) output = Json.addComma(output);
		}
		output += Json.endArray(false);
		output += Json.endEnclose(false); //this dungeon
		output += Json.endEnclose(false); //main enclose
		return output;
	}	
}
