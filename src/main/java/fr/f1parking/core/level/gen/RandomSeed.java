package fr.f1parking.core.level.gen;

import java.security.SecureRandom;

public class RandomSeed {
	
	private long seed;
	private final RandomSeedByte byteWeight;
	
	public RandomSeed() {
		this.byteWeight = RandomSeedByte.BYTE_32;
		this.seed = generateUseableSeed(new SecureRandom().generateSeed(byteWeight.getWeight()), new SecureRandom().generateSeed(byteWeight.getWeight()));
	}
	
	public RandomSeed(RandomSeedByte byteWeight) {
		if(byteWeight == RandomSeedByte.NOT_GENERATED) throw new IllegalArgumentException("The byte weight cannot be zero");
		this.byteWeight = byteWeight;
		this.seed = generateUseableSeed(new SecureRandom().generateSeed(byteWeight.getWeight()), new SecureRandom().generateSeed(byteWeight.getWeight()));
	}
	
	public RandomSeed(long seed) {
		this.byteWeight = RandomSeedByte.NOT_GENERATED;
		this.seed = seed;
	}
	
	private long generateUseableSeed(byte[] lowByte, byte[] highByte) {
		long ret = 0;
		
		for(int i = 0; i < lowByte.length; i++) ret = (ret<<8) + ((highByte[i]*highByte[i] ^ lowByte[i]) & 255);
		
		return ret;
	}
	
	private static byte[] generateByteSeed(long seed) {
		byte[] ret = new byte[Byte.SIZE];
		
		for(int i = Byte.SIZE-1; i >= 0; i--) {
			ret[i] = (byte)(seed & 255);
			seed >>= 8;	
		}
		
		return ret;
	}
	
	public void setSeed(long seed) {
		this.setSeed(generateByteSeed(seed));
	}
	
	public void setSeed(byte[] byteSeed) {
		this.seed =  generateUseableSeed(new SecureRandom(byteSeed).generateSeed(this.byteWeight.weight), new SecureRandom(byteSeed).generateSeed(this.byteWeight.weight));
	}
	
	public long getSeed() {
		return seed;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof RandomSeed) && ((RandomSeed)obj).seed == this.seed;
	}
	
	@Override
	public String toString() {
		return "Random Seed ("+this.byteWeight+"): "+getSeed();
	}
	
	public enum RandomSeedByte {
		NOT_GENERATED(0),
		BYTE_32(32),
		BYTE_64(64);

		private int weight;
		RandomSeedByte(int w) {
			this.weight = w;
		}
		
		public int getWeight() {
			return weight;
		}
		
		@Override
		public String toString() {
			return "Weight: "+this.name()+"["+this.weight*8+"b]";
		}
	}
}
