package fr.f1parking.core.helpers;

import java.util.List;
import java.util.UUID;

import fr.f1parking.core.entities.Entity;
import fr.f1parking.core.entities.EntityPlayer;
import fr.f1parking.core.entities.placement.Coordinate;
import fr.f1parking.core.entities.placement.Direction;
import fr.f1parking.core.level.gen.IGenerator;
import fr.f1parking.core.level.objects.GridBox;

public class MapHelper {

	/**
	 * Check if a map contains the given entity
	 * 
	 * @param map - the map to be checked
	 * @param e - the entity
	 * @return the coordinates of the entity if present else null coordinates
	 */
	public static Coordinate doesMapContains(GridBox[][] map, Entity e) {
		Coordinate ret = null;
		byte y = 0;
		byte x = 0;
		
		while(y < IGenerator.GRID_SIZE && ret == null) {
			if(map[y][x].isEntityPresent() && map[y][x].isRootBox() && map[y][x].getEntityID().compareTo(e.getId()) == 0) {
				ret = new Coordinate(x, y);
			}
			
			if(x == IGenerator.GRID_SIZE-1) {
				x = 0;
				y+= 1;
			}else x+=1;
		}
		
		return ret;
	}
	
	/**
	 * Check if the player has finished the map using his position and his next movement
	 * 
	 * @param choosedDir - the movement chose by the player
	 * @param playerCoord - the coordinates of the player
	 * @param size - the size of the entity (usually 2 for the player)
	 * @return
	 */
	public static boolean isPlayerFinished(Direction choosedDir, Coordinate playerCoord, int size) {
		return playerCoord.equals(new Coordinate(IGenerator.GRID_SIZE-size, (IGenerator.GRID_SIZE/2)-1)) && choosedDir == Direction.EAST;
	}
	
	/**
	 * Return the entity object if found in the entity list
	 * Work if the ID is equal to player's id
	 * 
	 * @param npcEntities - the entities list (only contains npc)
	 * @param player - the player object
	 * @param id - the id of the unknown entity
	 
	 * @return the entity object if found
	 */
	public static Entity getEntityMatchingID(List<Entity> npcEntities, EntityPlayer player, UUID id) {
		Entity res = null;
		for(Entity e : npcEntities) {
			if(e.getId().equals(id)) res = e;
		}
		
		if(player.getId().equals(id)) res = player;
		
		return res;
	}
}
