package fr.f1parking.core.io;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.f1parking.core.io.json.JSONConfiguration;
import fr.f1parking.core.io.json.JSONHighscore;
import fr.f1parking.core.io.json.JSONTextures;

public class IOHandler {

	private static final Logger LOGGER = LogManager.getLogger(IOHandler.class);
	
	private static IOHandler instance;
	
	private final File pathToSaveable;
	
	private final JSONConfiguration configuration;
	private final JSONTextures textures;
	private final JSONHighscore highscore;
	
	private IOHandler() {
		this.pathToSaveable = new File("datas");
		if(this.pathToSaveable.mkdirs()) LOGGER.info("Directory "+this.pathToSaveable.getAbsolutePath()+" has been successfully created !");
		else LOGGER.info("Directory "+this.pathToSaveable.getAbsolutePath()+" has been successfully loaded !");
		
		this.configuration = new JSONConfiguration(this.pathToSaveable.getAbsolutePath());
		this.textures = new JSONTextures(this.pathToSaveable.getAbsolutePath());
		this.highscore = new JSONHighscore(this.pathToSaveable.getAbsolutePath());
	}
	
	public boolean saveAllFiles() {
		LOGGER.info("Saving 3 files...");
		
		try {
			this.configuration.saveFile();
			this.textures.saveFile();
			this.highscore.saveFile();
			
		} catch (IOException e) {
			LOGGER.warn("Couldn't save the file properly",e);
			return false;
		}
		
		LOGGER.info("Successfully saved 3 files");
		return true;
	}
	
	public JSONHighscore getHighscoreFile() {
		return this.highscore;
	}
	
	public JSONTextures getTexturesFile() {
		return this.textures;
	}
	
	public JSONConfiguration getConfiguration() {
		return this.configuration;
	}
	
	public static IOHandler getInstance() {
		if(instance == null) instance = new IOHandler();
		return instance;
	}
	
}
