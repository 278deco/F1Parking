package fr.f1parking.core.level.gen.backtracking;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.f1parking.core.entities.EntityPlayer;
import fr.f1parking.core.level.Difficulty;
import fr.f1parking.core.level.gen.RandomSeed;
import fr.f1parking.core.level.gen.generators.ProceduralPlacingGenerator;
import fr.f1parking.core.level.gen.layers.ProceduralPlacingLayer;

public class Tree {
	private static final Logger LOGGER = LogManager.getLogger(Tree.class);
	
	private final EntityPlayer player;
	
	private final RandomSeed seed;
	private final Difficulty difficulty;
	
	private ProceduralPlacingGenerator generator;
	private ProceduralPlacingLayer layer;
	
	private SeedSelector selector;
	private int selectorVersion;
	
	private final List<NodeMap> mapsList;
	
	private Tree(Builder builder) {
		this.player = new EntityPlayer();
		
		this.seed = builder.getseed();
		this.difficulty = builder.getdifficulty();
		this.selectorVersion = 0;
		
		generateElements();
		
		this.mapsList = new ArrayList<>();
	}
	
	private void generateElements() {

		this.selector = new SeedSelector(this.seed, this.difficulty, this.selectorVersion);

		this.generator = ProceduralPlacingGenerator.builder()
				.seedSelector(selector)
				.build();
		this.layer = ProceduralPlacingLayer.builder()
				.seedSelector(selector)
				.player(player)
				.build();
	}
	
	public void generateMap() {
		boolean running = true;
		
		NodeMap map = null;
		while(running) {
			running = false;
			
			try {
				map = new NodeMap(this.generator, this.layer);
			}catch(Exception e) {
				LOGGER.info("Error occured while generating map (V: "+this.selectorVersion+")",e);
				this.selectorVersion+=1;
				generateElements();
				
				running = true;
			}
		}
		
		mapsList.add(map);
	}

	//ONLY FOR TEST PURPOSE
	public void testRender() {
		this.mapsList.get(0).testRender(this.layer.getEntities().get(), this.player);
	}
	
	public static Tree.Builder builder() {
		return new Tree.Builder();
	}
	
	public static final class Builder {
		
		private RandomSeed seed;
		private Difficulty diff;
		
		private Builder() {}
		
		public Builder seed(RandomSeed seed) {
			this.seed = seed;
			return this;
		}
		
		public Builder difficulty(Difficulty difficulty) {
			this.diff = difficulty;
			return this;
		}
		
		public Tree build() {
			return new Tree(this);
		}
		
		private RandomSeed getseed() {
			return seed;
		}
		
		private Difficulty getdifficulty() {
			return diff;
		}
	}
}
