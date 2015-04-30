package fighter.player;


import java.util.ArrayList;
import java.util.List;

import json.Json;
import cards.Card;
import cards.Skill;
import fighter.Fighter;
import fighter.Fighter.Trait;

public class Hero extends Fighter{
	int frame;
	public Hero(String name, int health, Trait[] traits, List<Card> cards, int aFrame){
		super(name, health, traits);
		this.cards=cards;
		this.frame=aFrame;
		this.traits=traits;
	}
	
	public String toJson(){
		String output="";
		output+="\""+name.replaceAll("_", " ")+"\" : {\n";
		output+="\"health\" : "+getHP()+",\n";		
		if(frame>102)output+= Json.addKey("frame", frame, true);
		if(traits!=null){
			output+="\"Traits\" : [\n";
			for(int i=0;i<traits.length;i++){
				Trait t = traits[i];
				output+=t.toJson();
				if(traits.length>i+1)output+=",";
				output+="\n";
			}
			output+="],\n";
		}
		if(cards!=null){
			output+=Json.startArray("BattleCards");
			for(int i=0;i<cards.size();i++){
				output+=Json.enclose();
				output+=cards.get(i).toJson();
				output+=Json.endEnclose(true);
				if(i==cards.size()-1){
					output=Json.removeComma(output);
				}
			}
			output+=Json.endArray(true);
		}

		output+="}";
		output=Json.removeComma(output);
		output+=Json.endEnclose(true);
		return output;
	}
}
