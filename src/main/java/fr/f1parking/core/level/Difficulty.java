package fr.f1parking.core.level;

/**
 * Define the difficulty of a level
 * Set a minimum and a maximum of Entity (except the Player)
 * Minimum Bound -> inclusive
 * Maximum Bound -> exclusive
 * @author 278deco
 * @version 1.0
 */
public enum Difficulty {
	
	HARD(12, 16),
	MEDIUM(8, HARD.getMinBound()),
	EASY(4, MEDIUM.getMinBound());
	
	private int minBound;
	private int maxBound;
	private Difficulty(int min, int max) {
		this.minBound = min;
		this.maxBound = max;
	}
	
	public int getMinBound() {
		return this.minBound;
	}
	
	public int getMaxBound() {
		return this.maxBound;
	}
}
