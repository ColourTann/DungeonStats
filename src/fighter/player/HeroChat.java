package fighter.player;

import json.Json;

public class HeroChat {
	public String label;
	public String[] chats;
	public HeroChat(String label, String[] chats) {
		this.label=label; this.chats=chats;
	}
	public String toJson(){
		String output="";
		
		output+=Json.startArray(label);
		for(int i=0;i<chats.length;i++){
			String chat = chats[i];
			output+="\""+chat+"\"";
			if(i<chats.length-1){
				output=Json.addComma(output);
			}
		}
		output+=Json.endArray(false);
		
		return output;
	}
}
