package cards;

import java.util.ArrayList;

import json.Json;
import cards.CardFactory.Action;
import cards.CardFactory.Icon;

public class Card {

	String name;
	public float strength;
	String description;
	int descSize;
	Icon icon;
	ArrayList<Action> actions;
	
	public Card(String name, float strength, String description, int descSize, Icon icon, ArrayList<Action> actions){
		this.name=name;
		this.strength=strength;
		this.description=description;
		this.descSize=descSize;
		this.icon=icon;
		this.actions=actions;
	}
	
	public String toJson(){
		String output="";
		
		output+=Json.startList(name);
		output+=Json.addKey("description", description);
		output+=Json.addKey("descSize", descSize);
		output+=Json.addKey("icon", icon.toString());
		
		
		if(actions.size()>0){
			output+=Json.startArray("Actions");
			for (int i=0;i<actions.size();i++){
				output+=Json.enclose();
				output+=actions.get(i).toJson();
				output=Json.removeComma(output);
				output+=Json.endEnclose();
			}
			output=Json.removeComma(output);
			output+=Json.endArray();
		}
		output=Json.removeComma(output);
		output+=Json.endEnclose();
		output=Json.removeComma(output);
		
		return output;
		
	}
}
