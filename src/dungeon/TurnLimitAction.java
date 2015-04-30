package dungeon;

import json.Json;

public class TurnLimitAction {
	public enum ActionType{BossChat, HeroHealth, FailDungeon}
	ActionType action; String[] args; String description;
	public TurnLimitAction(ActionType action, String[] args, String description){
		this.action=action; this.args=args; this.description=description;
	}
	public String toJson(){
		String output="";
		output+=Json.addKey("action", action.toString(), true);
		output+=Json.startArray("args");
		for(int i=0;i<args.length;i++){
			output+=args[i];
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