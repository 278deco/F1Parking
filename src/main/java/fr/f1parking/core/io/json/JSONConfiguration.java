package fr.f1parking.core.io.json;

import fr.f1parking.core.io.IOHandler;
import fr.f1parking.core.io.JSONFile;
import fr.f1parking.ui.render.Texture;

public class JSONConfiguration extends JSONFile {

	public JSONConfiguration(String path) {
		super(path, "parameter.json");
	}

	public double getMusicVolume() {
		if(getData().containsKey("music_volume")) {
			return ((long)getData().get("music_volume"))/100D;
		}
		return 1D;
	}
	
	public double getSoundVolume() {
		if(getData().containsKey("sound_volume")) {
			return ((long)getData().get("sound_volume"))/100D;
		}
		return 1D;
	}
	
	public String getPlayerCar() {
		if(getData().containsKey("player_car")) {
			return (String)getData().get("player_car");
		}
		return "redbull"; //Default car
	}
	
	public Texture getPlayerCarTexture() {
		return IOHandler.getInstance().getTexturesFile().getCarTexture(getPlayerCar());
	}
	
	public void setMusicVolume(int percentage) {
		getData().put("music_volume", percentage);
	}
	
	public void setSoundVolume(int percentage) {
		getData().put("sound_volume", percentage);
	}
	
	public void setPlayerCar(String carId) {
		if(IOHandler.getInstance().getTexturesFile().isValidTextureID(carId)) {
			getData().put("player_car", carId);
		}
	}
	
	@Override
	public void preSave() {
		getData().putIfAbsent("player_car", "redbull_f1");
		getData().putIfAbsent("music_volume", 100);
		getData().putIfAbsent("sound_volume", 100);
	}

}
