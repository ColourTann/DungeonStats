package dungeon;

import fighter.Fighter.Trait;
import json.Json;

public class BossChat {
	public enum Trigger{blah, FirstKill, flib, intruder, FifthKill, Second_Turn, four_left, last_turn, ComingToAttack, attacked_early, kill, Entrance, SecondKill, FourthKill, SixthTurn, EleventhTurn, ThirdKill, FirstTurn, NinthTurn, ThirdTurn}
	public enum PostFunc{FinishBossChat, StartingRoom, MoveToBoard, FireDemonMoveToBoard, Chase, FailDungeon}
	public enum DelayEffect{APPEAR}
	
	Trigger trigger;
	BossSpeech[] speeches;
	PostFunc postFunc; 
	int killed, turns; 
	int delayType; DelayEffect delayEffect;
	PostEffect[] postEffects;
	public BossChat(Trigger trigger, 
			BossSpeech[] speeches, 
			PostFunc postFunc, 
			int killed, int turns, 
			int delayType, DelayEffect delayEffect,
			PostEffect[] postEffects) {
		this.trigger=trigger;
		this.speeches=speeches;
		this.postFunc=postFunc;
		this.killed=killed; this.turns=turns;
		this.delayType=delayType; this.delayEffect=delayEffect;
		this.postEffects=postEffects;
	}
	
	public String toJson(){
		String output ="";
		output += Json.startList(trigger.toString());
		
		
		
//		if(traitsToRemove!=null){
//			result += Json.startArray("traits");
//			for(int i=0;i<traitsToRemove.length;i++){
//				result += Json.enclose();
//				result += Json.addKey("trait", traitsToRemove[i].toString(), true);
//				result += Json.addKey("value", false, false);
//				result += Json.endEnclose(i<traitsToRemove.length-1);
//			}			
//			result += Json.endArray(true);
//		}
		if(postEffects!=null){
			output+=Json.startArray("Traits");
			for(int i=0;i<postEffects.length;i++){
				output+=postEffects[i].toJson();
				if(i==postEffects.length-1){
					output=Json.removeComma(output);
				}
			}
			output+=Json.endArray(true);
		}
		if(postFunc==PostFunc.FireDemonMoveToBoard){
			output += Json.addKey("postFunc", PostFunc.MoveToBoard.toString(), true);
			output += Json.addKey("block", true, true);
		}
		else if(postFunc==PostFunc.Chase){
			output += Json.addKey("postFunc", PostFunc.StartingRoom.toString(), true);
			output += Json.startArray("postArgs");
			output += "true\n";
			output += Json.endArray(true);
		}
		else if(trigger==Trigger.attacked_early){
			output += Json.addKey("block", true, true);
		}
		else if(postFunc!=null){
			output += Json.addKey("postFunc", postFunc.toString(), true);
		}

		if(turns>-1) output += Json.addKey("turn", turns, true);
		if(killed>-1) output += Json.addKey("killed", killed, true);
		if(delayEffect!=null) output += Json.addKey("delay"+delayType, delayEffect.toString(), true);
		output += Json.startArray("lines");
		for(int i=0;i<speeches.length;i++){
			BossSpeech bs = speeches[i];
			output += Json.enclose();
			output += bs.toJson();
			output += Json.endEnclose(i<speeches.length-1);
		}
		output += Json.endArray(false);
		output += Json.endList(false);
		return output;
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
	
	public static class PostEffect{
		Trait trait; boolean set;
		public PostEffect(Trait t, boolean set) {
			this.trait=t; this.set=set;
		}
		public String toJson(){
			String output = "";
			output+=Json.enclose();
			output+=Json.addKey("trait", trait.toString(), true);
			output+=Json.addKey("value", set, false);
			output+=Json.endEnclose(true);
			return output;
		}
	}
}
