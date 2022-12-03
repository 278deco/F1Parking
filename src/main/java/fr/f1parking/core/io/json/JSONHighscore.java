package fr.f1parking.core.io.json;

import fr.f1parking.core.io.JSONFile;

public class JSONHighscore extends JSONFile {

	public JSONHighscore(String path) {
		super(path, "highscore.json");
	}

	@Override
	public void preSave() {
		
	}

}
