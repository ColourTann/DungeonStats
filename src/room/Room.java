package room;

import item.Item;

import java.util.ArrayList;

import json.Json;
import cards.Skill.SkillType;

public class Room {
	String name;
	String frame;
	ArrayList<String> items = new ArrayList<>();
	ArrayList<SkillType> classes = new ArrayList<>();
	int cost;
	
	public Room(String name, String frame, ArrayList<String> items, ArrayList<SkillType> classes, int cost){
		this.name=name;
		this.frame=frame;
		this.items=items;
		this.classes=classes;
		this.cost=cost;
	}
	
	StringBuilder sb = new StringBuilder();
	public String toJson(){
		sb.append(Json.startList(name));
		sb.append(Json.addKey("frame", frame, true));
		if(cost!=0)sb.append(Json.addKey("cost", cost, true));
		sb.append(Json.addKey("guild", true, true));
		if(items.size()>0){
			sb.append(Json.startArray("items", false));
			for(int i=0;i<items.size();i++){
				sb.append("\""+items.get(i)+"\"");
				if(i+1<items.size()){
					sb.append(",");
				}
			}
			sb.append(Json.endArray(true));
		}
		if(classes.size()>0){
			sb.append(Json.startArray("classes", false));
			for(int i=0;i<classes.size();i++){
				
				sb.append("\""+classes.get(i)+"\"");
				if(i+1<classes.size()){
					sb.append(",");
				}
			}
			sb.append(Json.endArray(true));
		}
		sb.append(Json.endList(true));
		return sb.toString();
	}
	
	static ArrayList<Room> rooms = new ArrayList<>();
	static String aName;
	static String aFrame;
	static ArrayList<String> aItems = new ArrayList<>();
	static ArrayList<SkillType> aClasses = new ArrayList<>();
	static int aCost;
	public static void setupRooms(){
		aName="Hall";
		aClasses.add(SkillType.Chump);
		aCost=0;
		aFrame="guild_hall_nesw";
		aItems.add("Gold Coin");
		aItems.add("Silver Coins");
		aItems.add("Gold Pouch");
		aItems.add("Dead Lizard Charm");
		aItems.add("Swirling Orb");
		aItems.add("Wooden Board");
		aItems.add("Duelling Buckler");
		aItems.add("Mace");
		aItems.add("Sword");
		aItems.add("Stiletto");
		aItems.add("Twig");
		aItems.add("Fork");
		aItems.add("Sparkly Headband");
		aItems.add("Voodoo Mask");
		aItems.add("Paper Crown");
		aItems.add("Ruffled Shirt");
		aItems.add("Straitjacket");
		aItems.add("Wolf Pelt");
		aItems.add("Tattered Mail");
		aItems.add("Shimmering Cloak");
		aItems.add("Padded Vest");
		aItems.add("Cooking Pot");
		aItems.add("Cultist Hood");
		aItems.add("Horseshoe");		
		addRoom();
		
		//PHYSICAL//
		
		aName="Rogue's Den";
		aClasses.add(SkillType.Cat_Burglar);
		aCost=100;
		aFrame="guild_storeroom_nesw";
		addRoom();
		
		aName="Barracks";
		aClasses.add(SkillType.Bruiser);
		aCost=100;
		aFrame="guild_barracks_nesw";
		addRoom();
		
		aName="Fletcher";
		aClasses.add(SkillType.Ranger);
		aCost=1000;
		aFrame="guild_archery_range_nesw";
		addRoom();
		
		aName="Tavern";
		aFrame="guild_tavern_nesw";
		aClasses.add(SkillType.Barbarian);
		aCost=1000;
		addRoom();
		
		//MAGICAL//
		
		aName="Library";
		aClasses.add(SkillType.Shapeshifter);
		aCost=100;
		aFrame="guild_library_nesw";
		addRoom();
		
		aName="Chapel";
		aClasses.add(SkillType.Alchemist);
		aCost=100;
		aFrame="guild_graveyard_nesw";
		addRoom();

		aName="Standing Stones";
		aClasses.add(SkillType.Shapeshifter);
		aCost=1000;
		aFrame="guild_garden_nesw";
		addRoom();
		
		aName="Magic College";
		aClasses.add(SkillType.Apprentice);
		aCost=1000;
		aFrame="guild_magestower_nesw";
		addRoom();
		
		//items//
		
		aName="Blacksmith";
		aItems.add("Mail Coif");
		aItems.add("Brass Knuckles");
		aCost=100;
		aFrame="guild_smithy_nesw";
		addRoom();
		
		aName="Leatherworker";
		aItems.add("Skull Cap");
		aItems.add("Shimmering Cloak");
		aFrame="guild_practiceyard_nesw";
		aCost=100;
		addRoom();
		
		aName="Armoury";
		aItems.add("Red Mail");
		aItems.add("Green Bow");
		aFrame="guild_armory_nesw";
		aCost=1000;
		addRoom();
		
		aName="Apothecary";
		aItems.add("Fez");
		aItems.add("Toxic Bow");
		aFrame="guild_apothecary_nesw";
		aCost=1000;
		addRoom();
	}
	
	@SuppressWarnings("unchecked")
	static void addRoom(){
		rooms.add(new Room(aName, aFrame, (ArrayList<String>) aItems.clone(), (ArrayList<SkillType>) aClasses.clone(), aCost));
		aName="";
		aFrame="";
		aItems.clear();
		aClasses.clear();
		aCost=-999;
	}
	
	
	public static String JsonAllRooms(){
		StringBuilder sb = new StringBuilder();
		sb.append(Json.enclose());
		sb.append(Json.startList("RoomData"));
		for(int i=0;i<rooms.size();i++){
			sb.append(rooms.get(i).toJson());
			sb.deleteCharAt(sb.length()-5);
		}
		
		sb.deleteCharAt(sb.length()-2);
		sb.append(Json.endList(true));
		sb.deleteCharAt(sb.length()-2);
		sb.append(Json.endEnclose(true));
		sb.deleteCharAt(sb.length()-2);
		return sb.toString();
	}
	
}
