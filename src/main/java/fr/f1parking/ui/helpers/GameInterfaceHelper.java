package fr.f1parking.ui.helpers;

import java.util.List;

import fr.f1parking.core.entities.Entity;
import fr.f1parking.ui.objects.GameFlowPane;
import javafx.scene.layout.FlowPane;

public class GameInterfaceHelper {

	/**
	 * 
	 * @param paneList
	 * @return the updated GameFlowPane list
	 */
	public static List<GameFlowPane> addPaneToRightGameFlowPane(List<GameFlowPane> paneList, Entity target, FlowPane newPane) {
		boolean ret = false;
		for(GameFlowPane gamePane : paneList) {
			if(gamePane.getEntityId().equals(target.getId())) {
				gamePane.addFlowPane(newPane);
				ret = true;
			}
		}
		if(!ret) paneList.add(new GameFlowPane(target.getId(), newPane));
		
		return paneList;
	}
	
	public static GameFlowPane getRightFlowPane(List<GameFlowPane> paneList, FlowPane selection) {
		for(GameFlowPane gamePane : paneList) if(gamePane.isFlowPanePresent(selection)) return gamePane;
		return null;
	}
	
	public static GameFlowPane getRightFlowPane(List<GameFlowPane> paneList, Entity entity) {
		for(GameFlowPane gamePane : paneList) if(gamePane.getEntityId().equals(entity.getId())) return gamePane;
		return null;
	}
	
}
