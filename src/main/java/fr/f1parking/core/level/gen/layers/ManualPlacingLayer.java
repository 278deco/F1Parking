package fr.f1parking.core.level.gen.layers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.f1parking.core.entities.Entity;
import fr.f1parking.core.entities.EntityPlayer;
import fr.f1parking.core.entities.placement.Coordinate;
import fr.f1parking.core.entities.placement.Direction;
import fr.f1parking.core.exceptions.IllegalCoordinatesDefinitionException;
import fr.f1parking.core.exceptions.MultiplePlayerDefinitionException;
import fr.f1parking.core.exceptions.NoEntityDefinitionException;
import fr.f1parking.core.exceptions.UnattainableMethodException;
import fr.f1parking.core.exceptions.UndefinedPlayerException;
import fr.f1parking.core.helpers.DeplacementHelper;
import fr.f1parking.core.level.gen.IGenerator;
import fr.f1parking.core.level.gen.ILayer;
import fr.f1parking.core.level.objects.GridBox;

public class ManualPlacingLayer implements ILayer {

	private List<Entity> entitiesList;
	private EntityPlayer player;
	
	private IGenerator generator;
	private boolean isPrecalculated;
	private int entityCountExceptPlayer;
	
	private ManualPlacingLayer(Builder builder) {
		this.player = builder.getplayer();
		this.entitiesList = builder.getentities();

		this.generator = null;
		this.isPrecalculated = false;
		this.entityCountExceptPlayer = -1;
	}
	
	public void setGenerator(IGenerator generator) {
		if(this.generator != null && this.generator.equals(generator)) return;
		if(this.generator == null) {
			this.isPrecalculated = false;
			this.entityCountExceptPlayer = -1;
			
			this.generator = generator;
		}
	}
	
	public ManualPlacingLayer precalculate() {
		if(generator == null) throw new NullPointerException("Cannot precaculate the layer if no generator has been set");
		
		boolean isPlayerPresent = false;
		for(byte y = 0; y < IGenerator.GRID_SIZE; y++) {
			for(byte x = 0; x < IGenerator.GRID_SIZE; x++) {
				if(generator.getPos(x, y) != 0) {
					byte[] infos = decodeGeneratorValues(generator.getPos(x, y));
					this.entityCountExceptPlayer+= 1;
					
					if(infos[0] == 2) { 
						if(!isPlayerPresent) isPlayerPresent = true; 
						else throw new MultiplePlayerDefinitionException("Two player have been defined by the generator", Optional.empty());
						this.entityCountExceptPlayer-=1; 
					}
					
					Coordinate res = DeplacementHelper.getXYValueForDirection(infos[1], Direction.of(infos[2]));
					if((x+res.getX() < 0 || x+res.getX() > IGenerator.GRID_SIZE) || (y+res.getY() < 0 || y+res.getY() > IGenerator.GRID_SIZE)) 
						throw new IllegalCoordinatesDefinitionException("Out of bound coordinates");
				}
			}
		}
		
		if(!isPlayerPresent) throw new UndefinedPlayerException("Player must be defined");
		if(this.entityCountExceptPlayer == -1) throw new NoEntityDefinitionException("No entities were found");
		
		this.isPrecalculated = true;
		return this;
	}
	
	/**
	 * Generate the grid for the game by using values given by a generator and by {@code precalculate} method
	 * entities parameter's size must be equal to {@code getEntityCount} method
	 * entities order will be applied row by row in the grid (first entity in the list will be the first entity encounter)
	 * @return an new grid map
	 */
	public GridBox[][] generateGrid() {
		if(generator == null) throw new NullPointerException("Cannot generate a grid if no generator has been set");
		if(!isPrecalculated || entityCountExceptPlayer == -1) throw new UnattainableMethodException("Cannot generate a grid without calculating it");
		
		final List<Coordinate> filledBox = new ArrayList<>();
		final GridBox[][] map = new GridBox[IGenerator.GRID_SIZE][IGenerator.GRID_SIZE];
		
		int entityIndex = 0;
		
		for(byte y = 0; y < IGenerator.GRID_SIZE; y++) {
			for(byte x = 0; x < IGenerator.GRID_SIZE; x++) {
				byte posNb = generator.getPos(x, y);
				byte[] infos = decodeGeneratorValues(posNb);
				
				if(posNb != 0) {
					List<GridBox> tempGridBox = new ArrayList<>();
					
					for(byte i = 0; i < infos[1]; i++) {
						
						tempGridBox.add(new GridBox()
								.addNewEntity(infos[0] == 1 ? this.entitiesList.get(entityIndex).getId() : this.player.getId(), i == 0));
					}
										
					for(byte i = infos[1]; i > 0; i--) {
						Coordinate res = DeplacementHelper.getDynamicXYValueForDirection(infos[1], i, Direction.of(infos[2]));
						byte calcY = (byte)(y+res.getY());
						byte calcX = (byte)(x+res.getX());
						map[calcY][calcX] = tempGridBox.get(infos[1]-i)
								.addNewNeighbor(getNeighborList(tempGridBox, tempGridBox.get(infos[1]-i)));
						
						filledBox.add(new Coordinate(calcX, calcY));
					}
					
					if(infos[0] == 1) entityIndex+=1;
				}else {
					if(!filledBox.contains(new Coordinate(x, y))) map[y][x] = new GridBox();
				}
			}
		}
		
		return map;
	}
	
	public Optional<Integer> getEntityCount() {
		return entityCountExceptPlayer == -1 ? Optional.empty() : Optional.of(entityCountExceptPlayer);
	}
	
	private List<GridBox> getNeighborList(List<GridBox> neighbor, GridBox instance) {
		List<GridBox> temp = new ArrayList<>(neighbor);
		temp.remove(instance);
		return temp;
	}
	
	private byte[] decodeGeneratorValues(byte value) {
		byte obj = (byte)((value>>6) & 0x3);
		byte size = (byte)((value & 0x3f) >> 4);
		byte rot = (byte)(value & 0xf);
		
		return new byte[] {obj,size,rot};
	}
	
	public static ManualPlacingLayer.Builder builder() {
		return new ManualPlacingLayer.Builder();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		return obj instanceof ManualPlacingLayer ? isEqual((ManualPlacingLayer)obj) : false;
	}
	
	private boolean isEqual(ManualPlacingLayer mpl) {
		return mpl.entitiesList != null && this.entitiesList != null
				&& mpl.player != null && this.player != null
				&& mpl.entitiesList.equals(this.entitiesList)
				&& mpl.player.equals(this.player);
	}
	
	public static class Builder {
		private List<Entity> entities;
		private EntityPlayer player;
		
		private Builder() {
			this.entities = new ArrayList<>();
		}
		
		public Builder entities(List<Entity> entities) {
			this.entities = entities;
			return this;
		}
		
		public Builder entities(Entity... entities) {
			for(Entity e : entities) this.entities.add(e);
			return this;
		}
		
		public Builder player(EntityPlayer player) {
			this.player = player;
			return this;
		}
		
		public List<Entity> getentities() {
			return this.entities;
		}
		
		public EntityPlayer getplayer() {
			return this.player;
		}
		
		public ManualPlacingLayer build() {
			return new ManualPlacingLayer(this);
		}
	}
	
}
