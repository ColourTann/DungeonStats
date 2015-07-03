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
	HeroChat[] chats;
	public Hero(String name, int health, Trait[] traits, List<Card> cards, int aFrame, HeroChat[] chats){
		super(name, health, traits);
		this.cards=cards;
		this.frame=aFrame;
		this.traits=traits;
		this.chats=chats;
	}
	
	public String toJson(){
		String output="";
		output+="\""+name.replaceAll("_", " ")+"\" : {\n";
		output+="\"health\" : "+getHP()+",\n";		
		output+= Json.addKey("frame", frame, true);
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
		if(chats!=null){
			output+=Json.startList("Chat");
			for(int i=0;i<chats.length;i++){
				HeroChat ch = chats[i];
				output+=ch.toJson();
				if(i<chats.length-1){
					output=Json.addComma(output);
				}
			}
			output+=Json.endList(true);
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
