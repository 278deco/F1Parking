package fr.f1parking.core.level;

import java.util.UUID;

import fr.f1parking.core.entities.Entity;
import fr.f1parking.core.entities.placement.Coordinate;
import fr.f1parking.core.entities.placement.Direction;
import fr.f1parking.core.exceptions.UndefinedPlayerException;
import fr.f1parking.core.helpers.DeplacementHelper;
import fr.f1parking.core.helpers.MapHelper;
import fr.f1parking.core.level.gen.IGenerator;
import fr.f1parking.core.level.gen.ILayer;
import fr.f1parking.core.level.objects.GridBox;

public class Map extends AbstractMap {
	
	private String name;
	
	private Map(Map.Builder builder) {
		this.id = builder.getid();
		this.name = builder.getname();
		this.generator = builder.getgenerator();
		this.layer = builder.getlayer();
		
		generateMap();
	}
	
	@Override
	protected void generateMap() {
		layer.setGenerator(this.generator);
		layer.precalculate();
		this.defineMap(layer.generateGrid());
	}

	/**
	 * Function used to move an entity contained on the grid
	 * 
	 * @param e - the entity to be moved
	 * @param dir - the direction where the entity will move
	 * @return if the action has been correctly performed
	 */
	public boolean moveEntity(Entity e, Direction dir) {
		if(!DeplacementHelper.isAValidDirection(e.getFacingDirection(), dir)) return false;
		final Coordinate coords = MapHelper.doesMapContains(getMap(), e);
		if(coords == null) {
			throw new UndefinedPlayerException("The entity doesn't exist in this grid");
		}else {
			final Coordinate[] coordsValues = DeplacementHelper.getEntityMovingBoxes(coords, dir, e.getFacingDirection(), (byte)e.getSize());
			if(!coordsValues[1].withinInterval(new Coordinate(0,0), new Coordinate(IGenerator.GRID_SIZE-1, IGenerator.GRID_SIZE-1))) {
				return false;
			}else {
				if(isEmpty(coordsValues[1])) {
					set(coordsValues[1], new GridBox().addNewEntity(e.getId())); //Create the new grid containing the entity
					set(coordsValues[2], new GridBox()); //Delete the old grid containing the entity
					if(coordsValues[3] != null) set(coordsValues[3], new GridBox().addNewEntity(e.getId())); //If present, delete the old root value

					set(coordsValues[0], new GridBox().addNewEntity(e.getId(), true)); //Create the new root for the object
					
					return true;
				}
				return false;
			}
		}
	}
	
	/**
	 * Get the name of the map
	 * 
	 * @return map's name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Used to create a new instance of Map using it builder
	 * 
	 * @return map's builder
	 */
	public static Map.Builder builder() {
		return new Map.Builder();
	}
	
	/**
	 * Compare two maps and tell if they are equals or not
	 * <p> Points of comparaison : <br>
	 * The generator used <br>
	 * The layer used <br>
	 * The name of the map</p>
	 * 
	 * @return if the two maps are equals
	 */
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Map && areEquals((Map)obj);
	}
	
	private boolean areEquals(Map map) {
		return this.generator == map.generator &&
			this.layer == map.layer &&
			this.name == map.name;
	}
	
	public static final class Builder {
		
		private IGenerator generator;
		private ILayer layer;
		private UUID id;
		private String name;
		
		private Builder() {
			this.id = UUID.randomUUID();
		}
		
		/**
		 * Add the desired map's name to the builder
		 * 
		 * @param name - the map's name
		 * @return this instance of Builder
		 */
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		/**
		 * Add the desired generator to the builder
		 * 
		 * @param gen - the map's generator
		 * @return this instance of Builders
		 */
		public Builder generator(IGenerator gen) {
			this.generator = gen;
			return this;
		}
		
		/**
		 * Add the desired layer to the builder
		 * 
		 * @param layer - the map's layer
		 * @return this instance of Builder
		 */
		public Builder layer(ILayer layer) {
			this.layer = layer;
			return this;
		}
		
		/**
		 * Build a new Map
		 * 
		 * @return an new instance of Map
		 * @see fr.f1parking.core.level.Map
		 */
		public Map build() {
			return new Map(this);
		}
		
		private UUID getid() {
			return this.id;
		}

		private String getname() {
			return this.name;
		}
		
		private IGenerator getgenerator() {
			return this.generator;
		}
		
		private ILayer getlayer() {
			return this.layer;
		}
	}

}