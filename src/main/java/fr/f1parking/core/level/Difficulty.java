package fr.f1parking.core.level;

/**
 * Define the difficulty of a level
 * Set a minimum and a maximum of Entity (except the Player)
 * Minimum Bound -> inclusive
 * Maximum Bound -> exclusive
 * Truck Percentage -> Number of truck across the number of vehicles placed (except player's car)
 * Generation Depth -> Number of moved entity before telling the generation is ready
 * @author 278deco
 * @version 1.0
 */
public enum Difficulty {
	
	HARD(11, 13, 55, 12),
	MEDIUM(9, HARD.getMinBound(), 35, 10),
	EASY(6, MEDIUM.getMinBound(),20, 7);
	
	private int minBound;
	private int maxBound;
	private int truckPercentage;
	private int generationDepth;
	private Difficulty(int min, int max, int percen, int depth) {
		this.minBound = min;
		this.maxBound = max;
		this.truckPercentage = percen;
		this.generationDepth = depth;
	}
	
	public int getMinBound() {
		return this.minBound;
	}
	
	public int getMaxBound() {
		return this.maxBound;
	}
	
	public int getGenerationDepth() {
		return generationDepth;
	}
	
	public int getTruckPercentage() {
		return truckPercentage;
	}
}
