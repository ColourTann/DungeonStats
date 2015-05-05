package dungeon;

import json.Json;

public class Objective {
	public enum ObjectiveType{Defeat, Collect, Arrive}
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
		if(quantity>=0) output += Json.addKey("quantity", quantity, true);
		output += Json.addKey("target", target, false);
		output += Json.endEnclose(false);
		return output;
	}
}
