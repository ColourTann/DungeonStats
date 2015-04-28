package fighter.monster;

import json.Json;

public class BoardChat {
	public enum ChatType{Monster, Treasure, Hero, Random}
	ChatType type;
	String[] chats;
	public BoardChat(ChatType type, String[] chats){
		this.type=type;
		this.chats=chats;
	}
	public String toJson(){
		String output = "";
		output += Json.startArray(type.toString());
		for(int i=0;i<chats.length;i++){
			output += "\""+chats[i]+"\""+(i<chats.length-1?",":"");
		}
		output += Json.endArray(false);
		return output;
	}
}
