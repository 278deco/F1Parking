package fr.f1parking.core.maps;

import java.util.ArrayList;
import java.util.List;

import fr.f1parking.core.entities.Entity;
import fr.f1parking.core.entities.EntityPlayer;
import fr.f1parking.core.level.gen.IGenerator;
import fr.f1parking.core.level.gen.ILayer;

public abstract class IMap {
	
	protected final List<Entity> entities = new ArrayList<>();
	protected final EntityPlayer player = new EntityPlayer();
	
	public abstract String getName();
	
	public abstract IGenerator getGenerator();
	
	public abstract ILayer getLayer();
	
	public List<Entity> getEntities() {
		return entities;
	}
	
	public EntityPlayer getPlayer() {
		return player;
	}
}
