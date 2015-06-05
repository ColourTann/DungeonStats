package dungeon;

import item.Item.TreasureType;
import json.Json;
import dungeon.Tile.TileName;

public class DungeonLayout {
	TileLocation[] locations;
	public DungeonLayout(TileLocation[] locations) {
		this.locations=locations;
	}
	public String toJson() {
		String output="";
		output+=Json.startArray("DungeonLayout");
		for(int i=0;i<locations.length;i++){
			TileLocation tl = locations[i];
			output += tl.toJson();
			if(i<locations.length-1) output=Json.addComma(output);
		}
		output += Json.endArray(true);
		return output;
	}
	
	public static class TileLocation{
		TileName name; int x, y; String monster, treasure;
		TileDetails details;
		public TileLocation(TileName name, int x, int y, String monster, TreasureType treasure, TileDetails details) {
			this.name=name;
			this.x=x;
			this.y=y;
			this.monster=monster;
			if(treasure!=null)this.treasure=treasure.toString();
			this.details=details;
		}
		public String toJson(){
			String output="";
			output += Json.enclose();
			output+=Json.addKey("TILE", name.toString(), true);
			output+=Json.addKey("x", x, true);
			output+=Json.addKey("y", y, true);
			if(details!=null){
				output+=details.toJson();
			}
			else{
				output+=Json.addKey("ignoreCamera", true, true);
			}
			if(monster.equalsIgnoreCase("BOSS")){
				output += Json.addKey("BossRoom", true, true);
			}
			else{
				output+=Json.addKey("MONSTER", monster, true);	
			}
			output+=Json.addKey("TREASURE", treasure==null?"":treasure, false);
			output += Json.endEnclose(false);
			return output;
		}
	}
	
	public static class TileDetails{
		boolean explored;
		boolean ignoreCamera;
		boolean camCoords;
		int x;
		int y;
		boolean objective;
		 public TileDetails(boolean explored, boolean ignoreCamera, boolean camCoords, int x, int y, boolean objective) {
			 this.objective=objective;
			 this.explored=explored;
			 this.ignoreCamera=ignoreCamera;
			 this.camCoords=camCoords; this.x=x; this.y=y;
		}
		 public String toJson(){
			 String output="";
			 if(objective) output += Json.addKey("Name", "objective", true);
			 if(explored) output+=Json.addKey("explored", true, true);
			 if(ignoreCamera) output+=Json.addKey("ignoreCamera", true, true);
			 if(camCoords){
				 output+=Json.addKey("camCoords", true, true);
				 output+=Json.addKey("camX", x, true);
				 output+=Json.addKey("camY", y, true);
			 }			 
			 return output;
		 }
	}



}

