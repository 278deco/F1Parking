package fr.f1parking.core.level.gen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import fr.f1parking.core.level.gen.RandomSeed.RandomSeedByte;

class RandomSeedTest {

	@Test
	void test() {
		final RandomSeed definedSeed = new RandomSeed(5461684566155L);
		
		//Two seed shouldn't be able to have the same value
		//Dangerous test because a tiny probability could make two seed equals
		assertNotEquals(new RandomSeed().getSeed(), new RandomSeed().getSeed());
		
		//When a seed is set in the constructor, it set the main seed of the class with the same value
		assertEquals(definedSeed.getSeed(), 5461684566155L);
		
		//A seed defined by the user and regenerated should not be equal to the user's defined seed
		//Dangerous test because a tiny probability could make two seed equals
		final RandomSeed testedSeed = new RandomSeed(97884614689L);
		testedSeed.regenerateSeed(123456789L);
		assertNotEquals(testedSeed.getSeed(), 97884614689L);
		
		//Cannot generate a seed without a byte weight
		assertThrows(IllegalArgumentException.class, () -> new RandomSeed(RandomSeedByte.NOT_GENERATED));

		//Check if the equals function works
		assertEquals(new RandomSeed(5461684566155L).equals(definedSeed), true);

	}

}
