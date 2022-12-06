package fr.f1parking.core.utils;

import java.util.Arrays;
import java.util.List;

import fr.f1parking.ui.render.Texture;

public class EntitiesTexture {

	public static final Texture PLAYER_TEXTURE = new Texture("t-pl", "1", null);
	
	public static final Texture CAR_1_TEXTURE = new Texture("tc-1", "2", null);
	public static final Texture CAR_2_TEXTURE = new Texture("tc-2", "3", null);
	public static final Texture CAR_3_TEXTURE = new Texture("tc-3", "4", null);
	public static final Texture CAR_4_TEXTURE = new Texture("tc-4", "5", null);
	public static final Texture CAR_5_TEXTURE = new Texture("tc-5", "6", null);
	
	public static final Texture TRUCK_1_TEXTURE = new Texture("tt-1", "7", null);
	public static final Texture TRUCK_2_TEXTURE = new Texture("tt-2", "8", null);
	public static final Texture TRUCK_3_TEXTURE = new Texture("tt-3", "9", null);
	
	public List<Texture> getCarsTexture() {
		return Arrays.asList(CAR_1_TEXTURE,CAR_2_TEXTURE,CAR_3_TEXTURE,CAR_4_TEXTURE,CAR_5_TEXTURE);
	}
	
	public List<Texture> getTrucksTexture() {
		return Arrays.asList(TRUCK_1_TEXTURE,TRUCK_2_TEXTURE,TRUCK_3_TEXTURE);
	}
	
	
}
