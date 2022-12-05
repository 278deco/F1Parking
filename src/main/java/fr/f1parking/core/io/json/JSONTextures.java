package fr.f1parking.core.io.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.f1parking.core.io.JSONFile;
import fr.f1parking.ui.render.Texture;

public class JSONTextures extends JSONFile {
	
	private final Map<String, Texture> carsMap;
	private final Map<String, Texture> trucksMap;
	
	@SuppressWarnings("unchecked")
	public JSONTextures(String path) {
		super(path, "textures.json");
		
		carsMap = new HashMap<>();
		trucksMap = new HashMap<>();
		
		//Load all the textures
		if(getData().containsKey("cars")) {
			for(Map.Entry<String, Object> entry : ((HashMap<String, Object>) getData().get("cars")).entrySet()) {
				try {
					final Map<String, String> textureValues = (HashMap<String, String>)entry.getValue();
					carsMap.put(entry.getKey(), new Texture(entry.getKey(), (String)textureValues.get("path"), (String)textureValues.get("file")));
					
				}catch(Exception e) { 
					LOGGER.warn("Invalid entry for a car texture (ID: "+entry.getKey()+"). Skipping to next entry.");
				}
			}
		}
		
		if(getData().containsKey("trucks")) {
			for(Map.Entry<String, Object> entry : ((HashMap<String, Object>) getData().get("trucks")).entrySet()) {
				try {
					final Map<String, String> textureValues = (HashMap<String, String>)entry.getValue();
					trucksMap.put(entry.getKey(), new Texture(entry.getKey(), (String)textureValues.get("path"), (String)textureValues.get("file")));
					
				}catch(Exception e) { 
					LOGGER.warn("Invalid entry for a truck texture (ID: "+entry.getKey()+"). Skipping to next entry.");
				}
			}
		}
	}

	public boolean isValidTextureID(String ID) {
		return carsMap.containsKey(ID) || trucksMap.containsKey(ID);
	}
	
	public Texture getCarTexture(String carID) {
		if(carsMap.containsKey(carID)) {
			return carsMap.get(carID);
		}
		throw new NullPointerException("The car's texture doesn't exist !");
	}
	
	public Texture getTruckTexture(String truckID) {
		if(trucksMap.containsKey(truckID)) {
			return trucksMap.get(truckID);
		}
		throw new NullPointerException("The truck's texture doesn't exist !");
	}
	
	
	public String getRandomCarID(int randomNumber) {
		final List<String> keyList = new ArrayList<>(carsMap.keySet());
		
		return keyList.get(Math.round(((randomNumber%100)/100) * keyList.size()));
	}
	
	public String getRandomTrucksID(int randomNumber) {
		final List<String> keyList = new ArrayList<>(trucksMap.keySet());
		
		return keyList.get(Math.round(((randomNumber%100)/100) * keyList.size()));
	}
	
	public Texture getRandomCarTexture(int randomNumber) {
		return getCarTexture(getRandomCarID(randomNumber));
	}
	
	public Texture getRandomTrucksTexture(int randomNumber) {
		return getTruckTexture(getRandomTrucksID(randomNumber));
	}

	@Override
	public void preSave() {
		
	}

}
