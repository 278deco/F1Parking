package fr.f1parking.core.level.gen;

import java.util.List;
import java.util.Optional;

import fr.f1parking.core.entities.Entity;
import fr.f1parking.core.level.objects.GridBox;

public interface ILayer {
	
	public void setGenerator(IGenerator generator);
	
	public ILayer precalculate();	
	public GridBox[][] generateGrid();

	Optional<Integer> getEntityCount();
	Optional<List<Entity>> getEntities();
	
}
