package fr.f1parking;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.f1parking.core.io.IOHandler;
import fr.f1parking.core.level.MapLoader;
import fr.f1parking.core.maps.Number1Map;
import fr.f1parking.core.maps.Number2Map;
import fr.f1parking.ui.Coordinator;
import javafx.application.Application;
import javafx.application.Platform;

public class Main {
	
	private static final Logger LOGGER = LogManager.getLogger(Main.class);
	
	public static void main(String[] args) {
		IOHandler.getInstance();
		
		MapLoader.getInstance().addNewMap(new Number1Map(), new Number2Map());
		
		Application.launch(Coordinator.class, args);
	}
	
	public static void stopProgram() {
		LOGGER.info("Shutting down program...");
		
		IOHandler.getInstance().saveAllFiles();
		Platform.exit();
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
}
