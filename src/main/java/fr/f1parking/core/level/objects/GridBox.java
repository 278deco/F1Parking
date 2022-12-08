package fr.f1parking.core.level.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GridBox {
	
	private final List<GridBox> occupiedNeighbor;
	private boolean rootBox;
	
	private UUID entityID;

	public GridBox() {
		this.occupiedNeighbor = new ArrayList<>();
	}
	
	public GridBox addNewEntity(UUID entity, boolean isRoot) {
		this.entityID = entity;
		this.rootBox = isRoot;
		return this;
	}
	
	public GridBox addNewEntity(UUID entity) {
		return addNewEntity(entity, false);
	}
	
	public GridBox addNewNeighbor(GridBox... neighborBoxs) {
		for(GridBox box : neighborBoxs) this.occupiedNeighbor.add(box);
		return this;
	}
	
	public GridBox addNewNeighbor(List<GridBox> neighborBoxs) {
		for(GridBox box : neighborBoxs) this.occupiedNeighbor.add(box);
		return this;
	}
	
	public void clearEntity(UUID id) {
		if(this.entityID.compareTo(id) == 0) {
			this.entityID = null;
			this.occupiedNeighbor.clear();
		}else throw new IllegalArgumentException("The box doesn't contains giving UUID "+ id.toString());
	}
	
	public UUID getEntityID() {
		return this.entityID;
	}
	
	/**
	 * Says if the GridBox is the root of the entity, meaning
	 * if the GridBox is where the entity is driven (Generally the root of a car is it back)
	 * @return if the box is the root of the entity
	 */
	public boolean isRootBox() {
		return rootBox;
	}
	
	public boolean isEntityPresent() {
		return this.entityID != null;
	}
	
	public List<GridBox> getOccupiedNeighbor() {
		return this.occupiedNeighbor;
	}
	
	@Override
	public String toString() {
		return "GridBox: {Ep="+isEntityPresent()+",E="+getEntityID()+",R="+isRootBox()+"}";
	}
}
