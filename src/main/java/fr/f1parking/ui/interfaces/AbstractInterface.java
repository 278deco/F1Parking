package fr.f1parking.ui.interfaces;

import fr.f1parking.ui.Coordinator;
import javafx.scene.Scene;

public abstract class AbstractInterface {
	protected Scene sceneInterface;
	
	public abstract void refreshScene(Coordinator c);
	
	public Scene getInterface(Coordinator c) {
		this.refreshScene(c);
		return this.sceneInterface;
	}
}
