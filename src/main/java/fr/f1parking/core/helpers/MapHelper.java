package fr.f1parking.core.helpers;

import fr.f1parking.core.entities.Entity;
import fr.f1parking.core.entities.placement.Coordinate;
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
}
