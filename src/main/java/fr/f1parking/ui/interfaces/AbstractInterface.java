package fr.f1parking.ui.interfaces;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.f1parking.ui.Coordinator;
import javafx.scene.Scene;

public abstract class AbstractInterface {
	
	protected Logger LOGGER = LogManager.getLogger(AbstractInterface.class);
	
	protected Scene sceneInterface;
	
	public abstract void refreshScene(Coordinator c);
	
	public Scene getInterface(Coordinator c) {
		this.refreshScene(c);
		return this.sceneInterface;
	}
}
