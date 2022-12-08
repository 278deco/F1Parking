package fr.f1parking.ui.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javafx.scene.layout.FlowPane;

public class GameFlowPane {
	
	private final List<FlowPane> flowplaneList;
	private UUID entityId;
	
	public GameFlowPane(UUID id, FlowPane...flowPanes) {
		this.flowplaneList = new ArrayList<>();
		for(FlowPane pane : flowPanes) flowplaneList.add(pane);
		
		this.entityId = id;
	}
	
	public GameFlowPane(UUID id, List<FlowPane> flowPlaneList) {
		this.flowplaneList = flowPlaneList;
		this.entityId = id;
	}
	
	public void addFlowPane(FlowPane pane) {
		this.flowplaneList.add(pane);
	}
	
	public void removeFlowPane(FlowPane pane) {
		this.flowplaneList.remove(pane);
	}
	
	public boolean isFlowPanePresent(FlowPane pane) {
		return this.flowplaneList.contains(pane);
	}
	
	public List<FlowPane> getFlowplaneList() {
		return flowplaneList;
	}
	
	public UUID getEntityId() {
		return entityId;
	}
	
}
