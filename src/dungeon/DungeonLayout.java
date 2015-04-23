package dungeon;

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
		public TileLocation(TileName name, int x, int y, String monster, String treasure) {
			this.name=name;
			this.x=x;
			this.y=y;
			this.monster=monster;
			this.treasure=treasure;
		}
		public String toJson(){
			String output="";
			output += Json.enclose();
			output+=Json.addKey("TILE", name.toString(), true);
			output+=Json.addKey("x", x, true);
			output+=Json.addKey("y", y, true);
			output+=Json.addKey("MONSTER", monster, true);
			output+=Json.addKey("TREASURE", treasure, false);
			output += Json.endEnclose(false);
			return output;
		}
	}



}

