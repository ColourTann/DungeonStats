package dungeon;

import json.Json;

public class Objective {
	public enum ObjectiveType{Defeat, Collect, Arrive}
	ObjectiveType type; String target; int quantity; boolean highlight;
	public Objective(ObjectiveType type, String target, int quantity){
		this(type, target, quantity, false);
	}
	
	public Objective(ObjectiveType type, String target, int quantity, boolean highlight){
		this.type=type;
		this.target=target;
		this.quantity=quantity;
		this.highlight=highlight;
	}
	
	public String toJson(){
		String output = "";
		output += Json.enclose();
		output += Json.addKey("type", type.toString(), true);
		if(quantity>=0) output += Json.addKey("quantity", quantity, true);
		if(highlight) output += Json.addKey("highlight", true, true);
		output += Json.addKey("target", target, false);
		output += Json.endEnclose(false);
		return output;
	}
}
