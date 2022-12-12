package fr.f1parking.core.io.json;

import java.io.IOException;

import fr.f1parking.core.io.JSONFile;

public class JSONHighscore extends JSONFile {

	public JSONHighscore(String path) {
		super(path, "highscore.json");
	}
	
	public long getHighScore(String mapName) {
		if(getData().containsKey(mapName)) return (long)getData().get(mapName);
		return -1;
	}
	
	public String getFormattedHighScore(String mapName) {
		final long score = getHighScore(mapName);
		return score == -1 ? "Non d\u00e9fini" : String.valueOf(score);
	}
	
	/**
	 * Add a new score to JSON file
	 * <p>The function check if a previous highscore has been set 
	 * and if it's greater than {@code score} <br>
	 * If {@code score} is lower than the previous highscore the function will return true
	 * and the new highscore will be set <br>
	 * If no highscore has been found, the score will become the highscore</p>
	 * 
	 * @param mapName the name of the map 
	 * @param score the score of the player
	 * @return if the highscore has been beaten
	 */
	public boolean addNewScore(String mapName, long score) {
		boolean change = false;
		
		if(getData().containsKey(mapName)) {
			if(getHighScore(mapName) > score) {
				getData().replace(mapName, score);
				change = true;
			}
		}else {
			getData().put(mapName, score);
			change = true;
		}
		
		if(change) try {
				saveFile();
			} catch (IOException e) {
				LOGGER.warn("An error occured while saving highscore file",e);
			}
		
		
		return change;
	}

	@Override
	public void reviewFormat() {
		
	}
	
	@Override
	public void preSave() {
		
	}

}
