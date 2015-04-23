package dungeon;

import json.Json;

public class Objective {
	public enum ObjectiveType{Defeat, Collect}
	ObjectiveType type; String target; int quantity;
	public Objective(ObjectiveType type, String target, int quantity){
		this.type=type;
		this.target=target;
		this.quantity=quantity;
	}
	
	public String toJson(){
		String output = "";
		output += Json.enclose();
		output += Json.addKey("type", type.toString(), true);
		output += Json.addKey("target", target, true);
		output += Json.addKey("quantity", quantity, false);
		output += Json.endEnclose(false);
		return output;
	}
}
