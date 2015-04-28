package dungeon;

import item.Item.TreasureType;
import dungeon.Tile.TileName;
import fighter.monster.Monster;
import json.Json;

public class Hand {
	DungeonCard[] cards;
	public Hand(DungeonCard[] cards) {
		this.cards=cards;
	}
	
	public String toJson(){
		String output="";
		output+=Json.startArray("StartingHand");
		for(int i=0;i<cards.length;i++){
			DungeonCard dc = cards[i];
			output += dc.toJson();
			if(i<cards.length-1) output = Json.addComma(output);
		}
		output += Json.endArray(true);
		return output;
	}
	
	public enum DungeonCardType{TILE, TREASURE, MONSTER}
	public static class DungeonCard{
		DungeonCardType type;
		String subtype;
		public DungeonCard(DungeonCardType type, TileName name) {
			setup(type, name.toString());
		}
		public DungeonCard(DungeonCardType type, TreasureType name) {
			setup(type, name.toString());
		}
		public DungeonCard(DungeonCardType type, String name) {
			setup(type, name);
		}
		
		public void setup(DungeonCardType type, String subtype){
			this.type=type;
			this.subtype=subtype;
		}
		public String toJson(){
			String output="";
			output+=Json.enclose();
			output+=Json.addKey("type", type.toString(), true);
			output+=Json.addKey("subType", subtype, false);
			output+=Json.endEnclose(false);
			return output;
		}
	}
	
}
