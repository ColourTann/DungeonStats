package dungeon;

import dungeon.BossChat.Trigger;
import json.Json;

public class TurnLimitAction {
	public enum ActionType{BossChat}
	ActionType action; Trigger[] args; String description;
	public TurnLimitAction(ActionType action, Trigger[] args, String description){
		this.action=action; this.args=args; this.description=description;
	}
	public String toJson(){
		String output="";
		output+=Json.addKey("action", action.toString(), true);
		output+=Json.startArray("args");
		for(int i=0;i<args.length;i++){
			output+="\""+args[i].toString()+"\"";
			if(i<args.length-1) output+=",";
			output+="\n";
		}
		output+=Json.endArray(true);
		output+=Json.addKey("resetTimer", false, true);
		output+=Json.addKey("resetTurns", 0, true);
		output+=Json.addKey("description", description, false);
		return output;
	}
}
