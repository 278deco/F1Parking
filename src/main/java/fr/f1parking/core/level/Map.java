package fr.f1parking.core.level;

import fr.f1parking.core.level.objects.GridBox;

public class Map {
	
	protected static final int GRID_SIZE = 6;
	
	protected GridBox[][] gridMap;
	
	private Map(Map.Builder builder) {
//		for(int y = 0; y < GRID_SIZE; y++) {
//			for(int x = 0; x < GRID_SIZE; x++) {
//				gridMap[y][x] = new GridBox();
//			}
//		}
	}
	
	public static Map.Builder builder() {
		return new Map.Builder();
	}
	
	public static Map.Builder builder(long seed) {
		return new Map.Builder(seed);
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Map && areEquals((Map)obj);
	}
	
	private boolean areEquals(Map map) {
		return map.gridMap == this.gridMap;
	}
	
	public static final class Builder {
		
		private GridBox[][] gridMap;
		
		private int entityNumber;
		private int generatorDepth;
		private long seed;
		
		private Builder() {
			this.seed = 0L;
		}
		
		private Builder(long seed) {
			this.seed = seed;
		}
		
		
		public Map build() {
			return new Map(this);
		}
		
		
		
	}

}