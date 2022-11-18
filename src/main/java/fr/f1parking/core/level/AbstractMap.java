package fr.f1parking.core.level;

import java.util.UUID;

import fr.f1parking.core.entities.placement.Coordinate;
import fr.f1parking.core.level.gen.IGenerator;
import fr.f1parking.core.level.objects.GridBox;

public abstract class AbstractMap {
	
	private GridBox[][] gridMap;
	protected UUID id;
	
	protected IGenerator generator;	

	protected abstract void generateMap();

	public String getID() {
		return this.id.toString();
	}
	
	protected boolean defineMap(GridBox[][] map) {
		if(gridMap == null) {
			gridMap = map;
			return true;
		}
		return false;
	}
	
	protected GridBox get(Coordinate coord) {
		return gridMap[coord.getY()][coord.getX()];
	}
	
	protected void set(Coordinate coord, GridBox box) {
		gridMap[coord.getY()][coord.getX()] = box;
	}
	
	protected boolean isEmpty(Coordinate coord) {
		return !gridMap[coord.getY()][coord.getX()].isEntityPresent();
	}
	
	/**
	 * <strong>Recommended for use inside the map class</strong>
	 * <p> Return the map instance represented by a two-dimensional array </br>
	 *  The array will now be cloned or copied, so any modification performed will
	 * modify the current map</p>
	 * @return a GridBox array
	 */
	protected GridBox[][] getMap() {
		return this.gridMap;
	}
	
	/**
	 * <strong>Recommended for use outside of the map class</strong>
	 * <p> Return the map instance represented by a two-dimensional array </br>
	 * The array returned is a clone of the map so any modification perfomed won't
	 * modify the current map</p>
	 * @return a GridBox Array
	 */
	protected GridBox[][] getMapCopy() {
		final GridBox[][] copy = new GridBox[this.gridMap.length][];
		for(int y = 0; y < this.gridMap.length; y++) copy[y] = this.gridMap[y].clone();
		
		return copy;
	}
	
}
