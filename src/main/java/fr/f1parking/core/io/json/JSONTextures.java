package fr.f1parking.core.io.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.f1parking.core.io.JSONFile;
import fr.f1parking.ui.render.Texture;
import fr.f1parking.utils.Tuple;

public class JSONTextures extends JSONFile {
	
	private final Map<String, Texture> carsMap;
	private final Map<String, Texture> trucksMap;
	
	public final Texture MISSING_TEXTURE;
	
	@SuppressWarnings("unchecked")
	public JSONTextures(String path) {
		super(path, "textures.json");
		
		this.MISSING_TEXTURE = new Texture("missingTexture", "Missing Texture", "datas/img", "missing_texture.png");
		
		carsMap = new HashMap<>();
		trucksMap = new HashMap<>();
		
		//Load all the textures
		if(getData().containsKey("cars")) {
			for(Map.Entry<String, Object> entry : ((HashMap<String, Object>) getData().get("cars")).entrySet()) {
				try {
					final Map<String, String> textureValues = (HashMap<String, String>)entry.getValue();
					carsMap.put(entry.getKey(), new Texture(entry.getKey(), (String)textureValues.get("display_name"), (String)textureValues.get("path"), (String)textureValues.get("file")));
					
				}catch(Exception e) { 
					LOGGER.warn("Invalid entry for a car texture (ID: "+entry.getKey()+"). Skipping to next entry.");
				}
			}
		}
		if(getData().containsKey("trucks")) {
			for(Map.Entry<String, Object> entry : ((HashMap<String, Object>) getData().get("trucks")).entrySet()) {
				try {
					final Map<String, String> textureValues = (HashMap<String, String>)entry.getValue();
					trucksMap.put(entry.getKey(), new Texture(entry.getKey(), (String)textureValues.get("display_name"), (String)textureValues.get("path"), (String)textureValues.get("file")));
					
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
			return carsMap.get(carID).copy();
		}
		LOGGER.warn("Couldn't find requested car's texture (name: "+carID+")");
		return MISSING_TEXTURE;
	}
	
	public Texture getTruckTexture(String truckID) {
		if(trucksMap.containsKey(truckID)) {
			return trucksMap.get(truckID).copy();
		}
		LOGGER.warn("Couldn't find requested truck's texture (name: "+truckID+")");
		return MISSING_TEXTURE;
	}
	
	
	public String getRandomCarID(int randomNumber) {
		final List<String> keyList = new ArrayList<>(carsMap.keySet());
		
		return keyList.get((int)Math.round(((randomNumber%100)/100D) * (keyList.size()-1)));
	}
	
	public String getRandomTrucksID(int randomNumber) {
		final List<String> keyList = new ArrayList<>(trucksMap.keySet());
		
		return keyList.get((int)Math.round(((randomNumber%100)/100D) * (keyList.size()-1)));
	}
	
	public Texture getRandomCarTexture(int randomNumber) {
		return getCarTexture(getRandomCarID(randomNumber));
	}
	
	public Texture getRandomTrucksTexture(int randomNumber) {
		return getTruckTexture(getRandomTrucksID(randomNumber));
	}
	
	public Tuple<List<String>, List<String>> getCarNameList() {
		final List<String> idName = new ArrayList<>();
		final List<String> displayName = new ArrayList<>();
		
		for(Texture texture : this.carsMap.values()) {
			idName.add(texture.getTextureName());
			displayName.add(texture.getDisplayableName());
		}
		
		return new Tuple<List<String>, List<String>>(idName, displayName);
	}

	@Override
	public void preSave() {
		
	}

}
