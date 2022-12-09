package fr.f1parking.ui;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.f1parking.ui.interfaces.GameInterface;
import fr.f1parking.ui.interfaces.HightscoreInterface;
import fr.f1parking.ui.interfaces.IntroInterface;
import fr.f1parking.ui.interfaces.MapSelectionInterface;
import fr.f1parking.ui.interfaces.MenuInterface;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Coordinator extends Application {

	private final Logger LOGGER = LogManager.getLogger(Coordinator.class);

	// application dimension
	private int WIDTH = 1280;
	private int HEIGHT = 720;

	private File logoFile;

	private GameInterface game_interface;
	private MapSelectionInterface map_selection_interface;
	private MenuInterface menue_interface;
	private HightscoreInterface hightscore_interface;
	private IntroInterface intro_interface;

	private Stage primaryStage;

	private int scene_indicator;

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;

		logoFile = new File("datas/img/logo.png");
		Image icon = new Image(logoFile.toURI().toString(), 0.2 * WIDTH, 0.2 * HEIGHT, false, true);

		menue_interface = new MenuInterface(this);
		map_selection_interface = new MapSelectionInterface(this);
		game_interface = new GameInterface(this);
		hightscore_interface = new HightscoreInterface(this);
		intro_interface = new IntroInterface(this);

		primaryStage.getIcons().add(icon);

		primaryStage.setTitle("(Un)park the F1");
		primaryStage.setScene(intro_interface.getInterface());
		primaryStage.setResizable(false);
		primaryStage.show();

	}
	
	/**
	 * Change the scene of the main window
	 * 
	 * @param i - the index of the new scene
	 */
	public void change_scene(int i) {
		this.scene_indicator = i;
		switch (i) {
		case 1:
			primaryStage.setScene(game_interface.getInterface());
			break;
		case 2:
			primaryStage.setScene(menue_interface.getInterface());
			break;
		case 3:
			hightscore_interface.refresh(this);
			primaryStage.setScene(hightscore_interface.getInterface());
			break;
		case 4:
			primaryStage.setScene(intro_interface.getInterface());
		case 5:
			primaryStage.setScene(map_selection_interface.getInterface());
			break;
		}

	}
	
	/**
	 * Load a new map in the game interface
	 * 
	 * @param mapId - the map's id
	 */
	public void loadSelectedMap(int mapId) {
		this.game_interface.setupBackGame(mapId);
	}

	public Logger getLogger() {
		return LOGGER;
	}

	public File getLogoFile() {
		return this.logoFile;
	}

	public int getScene_indicator() {
		return scene_indicator;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public int getWIDTH() {
		return WIDTH;
	}

	public int getHEIGHT() {
		return HEIGHT;
	}
}
