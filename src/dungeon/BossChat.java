package dungeon;

import fighter.Fighter.Trait;
import json.Json;

public class BossChat {
	public enum Trigger{FirstKill, FifthKill, Second_Turn, ComingToAttack, attacked_early, kill, SecondKill, FourthKill, SixthTurn, EleventhTurn, ThirdKill, FirstTurn, NinthTurn, ThirdTurn, ZerothTurn}
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
			int delayType, DelayEffect delayEffect,
			PostEffect[] postEffects) {
		this.trigger=trigger;
		this.speeches=speeches;
		this.postFunc=postFunc;
		this.killed=-1; this.turns=-1;
		switch(trigger){
		case ComingToAttack:
			break;
		case EleventhTurn:
			this.turns=11;
			break;
		case ZerothTurn:
			this.turns=0;
			break;
		case FifthKill:
			this.killed=5;
			break;
		case FirstKill:
			this.killed=1;
			break;
		case FirstTurn:
			this.turns=0;
			break;
		case FourthKill:
			this.killed=4;
			break;
		case NinthTurn:
			this.turns=9;
			break;
		case SecondKill:
			this.killed=2;
			break;
		case Second_Turn:
			this.turns=2;
			break;
		case SixthTurn:
			this.turns=6;
			break;
		case ThirdKill:
			this.killed=3;
			break;
		case ThirdTurn:
			this.turns=3;
			break;
		case attacked_early:
			break;

		case kill:
			break;
		default:
			break;
		
		}
		
		this.delayType=delayType; this.delayEffect=delayEffect;
		this.postEffects=postEffects;
	}
	
	public String toJson(){
		String output ="";
		output += Json.startList(trigger.toString());
		if(postEffects!=null){
			output+=Json.startArray("traits");
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
		else if(postFunc!=null){
			output += Json.addKey("postFunc", postFunc.toString(), true);
		}
		if(trigger==Trigger.attacked_early){
			output += Json.addKey("block", true, true);
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
