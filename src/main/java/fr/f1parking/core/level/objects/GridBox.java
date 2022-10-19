package fr.f1parking.core.level.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GridBox {
	
	private final List<GridBox> occupiedNeighbor;
	public UUID entityID;
	
	public GridBox() {
		this.occupiedNeighbor = new ArrayList<>();
	}
	
	public void addNewEntity(UUID id, GridBox... neighborBoxs) {
		this.entityID = id;
		for(GridBox box : neighborBoxs) this.occupiedNeighbor.add(box);
	}
	
	public void clearEntity(UUID id) {
		if(this.entityID.compareTo(id) == 0) {
			this.entityID = null;
			this.occupiedNeighbor.clear();
		}else throw new IllegalArgumentException("The box doesn't contains giving UUID "+ id.toString());
	}
	
	public List<GridBox> getOccupiedNeighbor() {
		return occupiedNeighbor;
	}
	
}
