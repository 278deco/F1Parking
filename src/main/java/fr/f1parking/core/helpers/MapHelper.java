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
	
	public static boolean isPlayerFinished(Direction choosedDir, Coordinate playerCoord, int size) {
		return playerCoord.equals(new Coordinate(IGenerator.GRID_SIZE-size, (IGenerator.GRID_SIZE/2)-1)) && choosedDir == Direction.EAST;
	}
	
	public static Entity getEntityMatchingID(List<Entity> npcEntities, EntityPlayer player, UUID id) {
		Entity res = null;
		for(Entity e : npcEntities) {
			if(e.getId().equals(id)) res = e;
		}
		
		if(player.getId().equals(id)) res = player;
		
		return res;
	}
}
