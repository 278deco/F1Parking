package fr.f1parking.core.level.gen;

import fr.f1parking.core.level.objects.GridBox;

public interface ILayer {
	
	public void setGenerator(IGenerator generator);
	
	public ILayer precalculate();	
	public GridBox[][] generateGrid();
	
}
