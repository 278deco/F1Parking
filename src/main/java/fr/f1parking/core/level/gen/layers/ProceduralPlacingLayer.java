package fr.f1parking.core.level.gen.layers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import fr.f1parking.core.entities.Car;
import fr.f1parking.core.entities.Entity;
import fr.f1parking.core.entities.EntityPlayer;
import fr.f1parking.core.entities.Truck;
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
import fr.f1parking.core.level.gen.backtracking.SeedSelector;
import fr.f1parking.core.level.objects.GridBox;

public class ProceduralPlacingLayer implements ILayer {

	private List<Entity> entitiesList;
	private EntityPlayer player;
	
	private SeedSelector selector;
	
	private IGenerator generator;
	private boolean isPrecalculated;
	private int entityCountExceptPlayer;
	
	private ProceduralPlacingLayer(ProceduralPlacingLayer.Builder b) {
		this.player = b.getplayer();
		
		this.selector = b.getselector();
		
		this.generator = null;
		this.isPrecalculated = false;
		this.entityCountExceptPlayer = -1;
		this.entitiesList = new ArrayList<>();
	}
	
	@Override
	public void setGenerator(IGenerator generator) {
		if(this.generator != null && this.generator.equals(generator)) return;
		if(this.generator == null) {
			this.isPrecalculated = false;
			this.entityCountExceptPlayer = -1;
			
			this.generator = generator;
		}
	}

	@Override
	public ILayer precalculate() {
		if(generator == null) throw new NullPointerException("Cannot precaculate the layer if no generator has been set");
		
		boolean isPlayerPresent = false;
		final List<Coordinate> occupiedCoords = new ArrayList<>();
		
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
					
					Coordinate result = null;
					for(byte i = infos[1]; i > 0; i--) {
						result = new Coordinate(x,y).add(DeplacementHelper.getDynamicXYValueForDirection(infos[1], i, Direction.of(infos[2])));
						
						if(occupiedCoords.contains(result)) throw new IllegalCoordinatesDefinitionException("Coordinates already used (Coords: "+result+ ", "
								+ "Obj[type: "+infos[0]+", size: "+infos[1]+", dir: "+Direction.of(infos[2])+"])");
						else occupiedCoords.add(result);
					}
					
					if(((result.getX()) < 0 || result.getX() > IGenerator.GRID_SIZE) || ((result.getY()) < 0 || result.getY() > IGenerator.GRID_SIZE)) 
						throw new IllegalCoordinatesDefinitionException("Out of bound coordinates (Coords: "+result+", Origin: "+new Coordinate(x,y)
								+ ", Obj[type: "+infos[0]+", size: "+infos[1]+", dir: "+Direction.of(infos[2])+"])");
				}
			}
		}
		
		if(!isPlayerPresent) throw new UndefinedPlayerException("Player must be defined");
		if(this.entityCountExceptPlayer == -1) throw new NoEntityDefinitionException("No entities were found");
		
		this.isPrecalculated = true;
		return this;
	}
	
	@Override
	public GridBox[][] generateGrid() {
		if(generator == null) throw new NullPointerException("Cannot generate a grid if no generator has been set");
		if(!isPrecalculated || entityCountExceptPlayer == -1) throw new UnattainableMethodException("Cannot generate a grid without calculating it");
		
		final List<Coordinate> filledBox = new ArrayList<>();
		final GridBox[][] map = new GridBox[IGenerator.GRID_SIZE][IGenerator.GRID_SIZE];

		for(byte y = 0; y < IGenerator.GRID_SIZE; y++) {
			for(byte x = 0; x < IGenerator.GRID_SIZE; x++) {
				byte posNb = generator.getPos(x, y);
				byte[] infos = decodeGeneratorValues(posNb);
				
				if(posNb != 0) {
					List<GridBox> tempGridBox = new ArrayList<>();
					
					Optional<Entity> entity = Optional.empty();
					if(infos[0] == 1) entity = Optional.of(generateEntity(infos[1], Direction.of(infos[2]), new Coordinate(x,y)));
					
					for(byte i = 0; i < infos[1]; i++) {
						if(infos[0] == 1 && entity.isPresent()) tempGridBox.add(new GridBox().addNewEntity(entity.get().getId(), i == 0));
						else if(infos[0] == 2) tempGridBox.add(new GridBox().addNewEntity(this.player.getId(), i == 0));
						else throw new NullPointerException("Couldn't find the right entity when generating the map (Coords: "+new Coordinate(x,y)+", "
								+ "Obj[type: "+infos[0]+", size: "+infos[1]+", dir: "+Direction.of(infos[2])+"])");
					}
								
					for(byte i = infos[1]; i > 0; i--) {
						Coordinate res = DeplacementHelper.getDynamicXYValueForDirection(infos[1], i, Direction.of(infos[2]));
						byte calcY = (byte)(y+res.getY());
						byte calcX = (byte)(x+res.getX());
						map[calcY][calcX] = tempGridBox.get(infos[1]-i)
								.addNewNeighbor(getNeighborList(tempGridBox, tempGridBox.get(infos[1]-i)));
						
						filledBox.add(new Coordinate(calcX, calcY));
					}
					
				}else {
					if(!filledBox.contains(new Coordinate(x, y))) map[y][x] = new GridBox();
				}
			}
		}
		
		return map;
	}
	
	@Override
	public Optional<Integer> getEntityCount() {
		return entityCountExceptPlayer == -1 ? Optional.empty() : Optional.of(entityCountExceptPlayer);
	}
	
	@Override
	public Optional<List<Entity>> getEntities() {
		return (!isPrecalculated || entityCountExceptPlayer == -1) ? Optional.empty() : Optional.of(Collections.unmodifiableList(this.entitiesList));
	}
	
	private Entity generateEntity(byte size, Direction dir, Coordinate coords) {	
		Entity ret = (size == 2) ? new Car(dir, selector.getEntityTexture(size, coords)) : new Truck(dir, selector.getEntityTexture(size, coords));
		this.entitiesList.add(ret);
		
		return ret;
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
	
	public static ProceduralPlacingLayer.Builder builder() {
		return new ProceduralPlacingLayer.Builder();
	}
	
	public static final class Builder {
		
		private EntityPlayer player;
		
		private SeedSelector selector;
		
		private Builder() { }
		
		public Builder player(EntityPlayer player) {
			this.player = player;
			return this;
		}
		
		public Builder seedSelector(SeedSelector select) {
			this.selector = select;	
			return this;
		}
		
		public ProceduralPlacingLayer build() {
			return new ProceduralPlacingLayer(this);
		}
		
		public EntityPlayer getplayer() {
			return this.player;
		}
		
		private SeedSelector getselector() {
			return this.selector;
		}
	}

}
