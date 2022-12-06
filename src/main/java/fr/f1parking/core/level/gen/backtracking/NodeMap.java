package fr.f1parking.core.level.gen.backtracking;

import java.util.List;
import java.util.UUID;

import fr.f1parking.core.entities.Entity;
import fr.f1parking.core.entities.EntityPlayer;
import fr.f1parking.core.helpers.RendererHelper;
import fr.f1parking.core.level.AbstractMap;
import fr.f1parking.core.level.gen.IGenerator;
import fr.f1parking.core.level.gen.ILayer;

public class NodeMap extends AbstractMap {

	public NodeMap(IGenerator generator, ILayer layer) {
		this.id = UUID.randomUUID();
		this.generator = generator;
		this.layer = layer;
		
		generateMap();
	}
	
	@Override
	protected void generateMap() {
		layer.setGenerator(this.generator);
		layer.precalculate();
		this.defineMap(layer.generateGrid());
	}
	
	//ONLY FOR TEST PURPOSE
		public void testRender(List<Entity> e, EntityPlayer pl) {
			System.out.println(RendererHelper.renderMap("Test", getMap(), e, pl));
		}

}