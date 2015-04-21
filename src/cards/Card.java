package cards;

import java.util.ArrayList;

import json.Json;
import cards.CardFactory.Action;
import cards.Skill.SkillType;

public class Card {

	String name;
	public float strength;
	String description;
	int descSize;
	ArrayList<Action> actions;
	SkillType image;
	public Card(String name, float strength, String description, int descSize, SkillType image, ArrayList<Action> actions){
		this.name=name;
		this.strength=strength;
		this.description=description;
		this.descSize=descSize;
		this.image=image;
		this.actions=actions;
	}
	
	public String toJson(){
		String output="";
		output+=Json.startList(name);
		output+=Json.addKey("description", description);
		output+=Json.addKey("descSize", descSize);
		if(image!=null)output+=Json.addKey("image", image.toString());
		
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
