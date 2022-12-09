package fr.f1parking.ui.objects;

import java.util.UUID;

import javafx.scene.layout.FlowPane;

public class GameFlowPane {
	
	private final FlowPane entityPane;
	private UUID entityId;
	
	public GameFlowPane(UUID id, FlowPane flowPane) {
		this.entityPane = flowPane;
		this.entityId = id;
	}
	
	public FlowPane getPane() {
		return this.entityPane;
	}
	
	public UUID getEntityId() {
		return entityId;
	}
	
}
