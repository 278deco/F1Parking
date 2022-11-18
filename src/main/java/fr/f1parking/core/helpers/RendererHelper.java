package fr.f1parking.core.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import fr.f1parking.core.entities.Entity;
import fr.f1parking.core.entities.EntityPlayer;
import fr.f1parking.core.level.gen.IGenerator;
import fr.f1parking.core.level.objects.GridBox;

public class RendererHelper {

	public static String renderMap(String name, GridBox[][] map, List<Entity> entitiesPNJ, EntityPlayer pl) {
		entitiesPNJ.add(pl);
		return renderMap(name, map, entitiesPNJ);
	}
	
	public static String renderMap(String name, GridBox[][] map, List<Entity> entities) {
		final List<Entity> copy = new ArrayList<>(entities);
		
		StringBuilder sb = new StringBuilder("--- "+name+" ---\n\n");
		for(byte y = 0; y < IGenerator.GRID_SIZE; y++) {
			for(byte x = 0; x < IGenerator.GRID_SIZE; x++) {
				Entity temp = getEntityMatchingID(copy, map[y][x].getEntityID());
				sb.append("| "+(temp == null ? map[y][x].getEntityID() : temp.getTextureName())+" | ");
			}
			sb.setLength(sb.length()-1);
			sb.append("\n");
		}
		return sb.toString();
	}
	
		
	private static Entity getEntityMatchingID(List<Entity> entities, UUID id) {
		Entity res = null;
		for(Entity e : entities) {
			if(e.getId().equals(id)) res = e;
		}
		
		return res;
	}
}
