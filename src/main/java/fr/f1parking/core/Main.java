package fr.f1parking.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.f1parking.core.io.IOHandler;
import fr.f1parking.core.level.Difficulty;
import fr.f1parking.core.level.gen.RandomSeed;
import fr.f1parking.core.level.gen.backtracking.Tree;
import fr.f1parking.ui.Coordinator;
import javafx.application.Application;

public class Main {
	
//	private static EntityPlayer player;
//	private static List<Entity> entities;
//	private static Map map;
	
	private static Tree treeMap;
	
	private static final Logger LOGGER = LogManager.getLogger(Main.class);
	
	public static void main(String[] args) {
		IOHandler.getInstance();
//		
//		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				LOGGER.info("Shutting down program...");
//				IOHandler.getInstance().saveAllFiles();
//			}
//		}, "Shutdown Thread"));
//		
//		generateTreeMap();
//		
//		treeMap.testRender();
		
		Application.launch(Coordinator.class, args);
		
//		
//		LOGGER.info(map.moveEntity(player, Direction.WEST));
//		
//		map.testRender(entities, player);
//		
//		LOGGER.info(map.moveEntity(player, Direction.WEST));
//		
//		map.testRender(entities, player);
//		
//		LOGGER.info(map.moveEntity(player, Direction.WEST));
//		
//		map.testRender(entities, player);
//		
//		LOGGER.info(map.moveEntity(player, Direction.EAST));
//		
//		map.testRender(entities, player);
//		
//		LOGGER.info(map.moveEntity(player, Direction.EAST));
//				
//		map.testRender(entities, player);
//		
//		LOGGER.info(map.moveEntity(entities.get(1), Direction.SOUTH));
//				
//		map.testRender(entities, player);
	}
	
	public static void generateTreeMap() {
		treeMap = Tree.builder()
			.seed(new RandomSeed())
			.difficulty(Difficulty.EASY)
			.build();
		
		treeMap.generateMap();
	}
	
	public static byte byteGen(byte obj, byte size, byte rot) {
		return (byte)((obj<<6)+(size<<4)+rot);
	}
	
	public static byte[] decodeGeneratorValues(byte value) {
		byte obj = (byte)((value>>6) & 0x3);
		byte size = (byte)((value & 0x3f) >> 4);
		byte rot = (byte)(value & 0xf);
		
		return new byte[] {obj,size,rot};
	}
	
//	public static void generateMap() {
//		player = new EntityPlayer();
//		entities = new ArrayList<>();
//		generateEntities(entities);
//		
//		ManualPlacingGenerator generator = ManualPlacingGenerator.builder()
//				.placement(4, 2, 2, 2, Direction.EAST)
//				.placement(0, 0, 1, 2, Direction.EAST)
//				.placement(2, 2, 1, 3, Direction.NORTH)
//				.build();
//		
//		ManualPlacingLayer layer = ManualPlacingLayer.builder()
//				.entities(entities).player(player)
//				.build();
//		
//		map = Map.builder()
//				.generator(generator)
//				.layer(layer)
//				.name("Test Map")
//				.build();
//		
//	}
//	
//	private static void generateEntities(List<Entity> eList) {
//		eList.add(new Car(Direction.EAST, EntitiesTexture.CAR_1_TEXTURE));
//		eList.add(new Truck(Direction.NORTH, EntitiesTexture.TRUCK_1_TEXTURE));
//	}
}
