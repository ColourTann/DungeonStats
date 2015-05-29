package dungeon;

import java.util.HashMap;

public class Tile {
	public enum TileName{
		room_cavern_nw, corr_grate_n, room_semicircle_nes, room_semicircle_nsw, 
		room_semicircle_esw, room_steps_nesw, room_collapse_esw, room_torture_s, 
		room_round_s, corr_regular_ew, corr_rubble_e, corr_crushed_ne, room_collapse_new, 
		room_cages_ns, room_throne_ne, room_waterfall_w, corr_rubble_ew, room_throne_sw, room_throne_es, 
		corr_deadend1_n, corr_deadend1_e, corr_corner1_ne, room_deadend2_s, corr_bridgeflip_ns, corr_corner2_es, corr_tee1_nes, 
		room_deadend3_w, corr_corner3_nw, corr_regular2_ew, corr_tee4_new, corr_corner4_sw, corr_tee5_nsw, 
		corr_tee6_esw, corr_cross16_nesw, corr_boulders_n, room_pits_e, corr_boxcorner_ne, room_tavern_s, corr_bucket_ns, 
		room_mess_es, room_supply_nes, room_pits_w, corr_railcorner_nw, corr_pittee_new, corr_pitgear_sw, corr_bouldertee_nsw, corr_regulartee_esw, corr_pillar4_nesw};
		
		
		static HashMap<Byte, TileName> tiles = new HashMap<>();
		public static void setup(){
			byte b = 0b001;
			tiles.put(b, TileName.corr_grate_n);
			tiles.put((byte) (b|0b10000), TileName.corr_deadend1_n);
			tiles.put((byte) (b|0b100000), TileName.corr_boulders_n);
			b+=1;
			tiles.put(b, TileName.corr_rubble_e);
			tiles.put((byte) (b|0b10000), TileName.corr_deadend1_e);
			tiles.put((byte) (b|0b100000), TileName.room_pits_e);
			b+=1;
			tiles.put(b, TileName.corr_crushed_ne);
			tiles.put((byte) (b|0b10000), TileName.corr_corner1_ne);
			tiles.put((byte) (b|0b100000), TileName.corr_boxcorner_ne);
			b+=1;
			tiles.put(b, TileName.room_round_s);
			tiles.put((byte) (b|0b10000), TileName.room_deadend2_s);
			tiles.put((byte) (b|0b100000), TileName.room_tavern_s);
			b+=1;
			tiles.put(b, TileName.room_cages_ns);
			tiles.put((byte) (b|0b10000), TileName.corr_bridgeflip_ns);
			tiles.put((byte) (b|0b100000), TileName.corr_bucket_ns);
			b+=1;
			tiles.put(b, TileName.room_throne_es);
			tiles.put((byte) (b|0b10000), TileName.corr_corner2_es);
			tiles.put((byte) (b|0b100000), TileName.room_mess_es);
			b+=1;
			tiles.put(b, TileName.room_semicircle_nes);
			tiles.put((byte) (b|0b10000), TileName.corr_tee1_nes);
			tiles.put((byte) (b|0b100000), TileName.room_supply_nes);
			b+=1;
			tiles.put(b, TileName.room_waterfall_w);
			tiles.put((byte) (b|0b10000), TileName.room_deadend3_w);
			tiles.put((byte) (b|0b100000), TileName.room_pits_w);
			b+=1;
			tiles.put(b, TileName.room_cavern_nw);
			tiles.put((byte) (b|0b10000), TileName.corr_corner3_nw);
			tiles.put((byte) (b|0b100000), TileName.corr_railcorner_nw);
			b+=1;
			tiles.put(b, TileName.corr_rubble_ew);
			tiles.put((byte) (b|0b10000), TileName.corr_regular2_ew);
			tiles.put((byte) (b|0b100000), TileName.corr_regular_ew);
			b+=1;
			tiles.put(b, TileName.room_collapse_new);
			tiles.put((byte) (b|0b10000), TileName.corr_tee4_new);
			tiles.put((byte) (b|0b100000), TileName.corr_pittee_new);
			b+=1;
			tiles.put(b, TileName.room_throne_sw);
			tiles.put((byte) (b|0b10000), TileName.corr_corner4_sw);
			tiles.put((byte) (b|0b100000), TileName.corr_pitgear_sw);
			b+=1;
			tiles.put(b, TileName.room_semicircle_nsw);
			tiles.put((byte) (b|0b10000), TileName.corr_tee5_nsw);
			tiles.put((byte) (b|0b100000), TileName.corr_bouldertee_nsw);
			b+=1;
			tiles.put(b, TileName.room_collapse_esw);
			tiles.put((byte) (b|0b10000), TileName.corr_tee6_esw);
			tiles.put((byte) (b|0b100000), TileName.corr_regulartee_esw);
			b+=1;
			tiles.put(b, TileName.room_steps_nesw);			
			tiles.put((byte) (b|0b10000), TileName.corr_cross16_nesw);
			tiles.put((byte) (b|0b100000), TileName.corr_pillar4_nesw);
		}
		
		public static TileName get(String directions){
			directions=directions.toLowerCase();
			byte b = 0b0;
			if(directions.contains("j")) b|=0b10000;
			if(directions.contains("m")) b|=0b100000;
			if(directions.contains("n")||directions.contains("u")) b|=0b0001;
			if(directions.contains("e")||directions.contains("r")) b|=0b0010;
			if(directions.contains("s")||directions.contains("d")) b|=0b0100;
			if(directions.contains("w")||directions.contains("l")) b|=0b1000;
			return tiles.get(b);
		}
}
