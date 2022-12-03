package fr.f1parking.core.io.json;

import java.util.HashMap;
import java.util.Map;

import fr.f1parking.core.io.JSONFile;

public class JSONTextures extends JSONFile {
	
	private final Map<String, String> carsMap;
	private final Map<String, String> trucksMap;
	
	@SuppressWarnings("unchecked")
	public JSONTextures(String path) {
		super(path, "textures.json");
		
		//Load all the textures
		if(getData().containsKey("cars")) carsMap = (HashMap<String, String>) getData().get("cars");
		else carsMap = new HashMap<>();
		
		if(getData().containsKey("trucks")) trucksMap = (HashMap<String, String>) getData().get("trucks");
		else trucksMap = new HashMap<>();
	}

	public boolean isValidTextureID(String ID) {
		return carsMap.containsKey(ID) || trucksMap.containsKey(ID);
	}
	
	public String getCarTexture(String carID) {
		if(carsMap.containsKey(carID)) {
			return carsMap.get(carID);
		}
		throw new NullPointerException("The car's texture doesn't exist !");
	}
	
	public String getTruckTexture(String truckID) {
		if(trucksMap.containsKey(truckID)) {
			return trucksMap.get(truckID);
		}
		throw new NullPointerException("The truck's texture doesn't exist !");
	}

	@Override
	public void preSave() {
		
	}

}
