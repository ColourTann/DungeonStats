package dungeon;

import json.Json;

public class BossChat {
	public enum Trigger{blah, first_kill, flib, intruder, fifth_kill, second_turn, four_left, last_turn, coming_soon, attacked_early}
	public enum PostFunc{FinishBossChat, StartingRoom, MoveToBoard, FireDemonMoveToBoard, Chase}
	public enum DelayEffect{APPEAR}
	
	Trigger trigger;
	BossSpeech[] speeches;
	PostFunc postFunc; 
	int killed, turns; 
	int delayType; DelayEffect delayEffect;
	public BossChat(Trigger trigger, 
			BossSpeech[] speeches, 
			PostFunc postFunc, 
			int killed, int turns, 
			int delayType, DelayEffect delayEffect) {
		this.trigger=trigger;
		this.speeches=speeches;
		this.postFunc=postFunc;
		this.killed=killed; this.turns=turns;
		this.delayType=delayType; this.delayEffect=delayEffect;		
	}
	
	public String toJson(){
		String result ="";
		result += Json.startList(trigger.toString());
		
		if(postFunc==PostFunc.FireDemonMoveToBoard){
			result += Json.addKey("postFunc", PostFunc.MoveToBoard.toString(), true);
			result += Json.addKey("block", true, true);
			result += Json.startArray("traits");
			result += Json.enclose();
			result += Json.addKey("trait", "Damp", true);
			result += Json.addKey("value", false, false);
			result += Json.endEnclose(false);
			result += Json.endArray(true);
		}
		else if(postFunc==PostFunc.Chase){
			result += Json.addKey("postFunc", PostFunc.StartingRoom.toString(), true);
			result += Json.addKey("postArgs", "[true]", true);
		}
		else if(trigger==Trigger.attacked_early){
			result += Json.addKey("block", true, true);
		}
		else if(postFunc!=null){
			result += Json.addKey("postFunc", postFunc.toString(), true);
		}

		if(turns>-1) result += Json.addKey("turn", turns, true);
		if(killed>-1) result += Json.addKey("killed", killed, true);
		if(delayEffect!=null) result += Json.addKey("delay"+delayType, delayEffect.toString(), true);
		result += Json.startArray("lines");
		for(int i=0;i<speeches.length;i++){
			BossSpeech bs = speeches[i];
			result += Json.enclose();
			result += bs.toJson();
			result += Json.endEnclose(i<speeches.length-1);
		}
		result += Json.endArray(false);
		result += Json.endList(false);
		return result;
	}
	
	
	public enum Func{emote}
	public static class BossSpeech{
		String text;
		Func func;
		boolean weirdArgs;
		public BossSpeech(String text, Func func, boolean weirdArgs) {
			this.text=text;
			this.func=func;
			this.weirdArgs=weirdArgs;
		}
		public String toJson(){
			String result ="";
			result += Json.addKey("TEXT", text, true);
			result += Json.addKey("FUNC", func.toString(), false);
			if(weirdArgs){
				result += Json.startArray("ARGS", true);
				result += "false\n";
				result += "]\n";
			}
			
			return result;
		}
	}
}
