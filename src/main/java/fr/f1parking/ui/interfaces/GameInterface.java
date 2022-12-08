package fr.f1parking.ui.interfaces;

import java.util.ArrayList;
import java.util.List;

import fr.f1parking.core.entities.Car;
import fr.f1parking.core.entities.Entity;
import fr.f1parking.core.entities.EntityPlayer;
import fr.f1parking.core.entities.Truck;
import fr.f1parking.core.entities.placement.Coordinate;
import fr.f1parking.core.entities.placement.Direction;
import fr.f1parking.core.helpers.DeplacementHelper;
import fr.f1parking.core.helpers.MapHelper;
import fr.f1parking.core.helpers.RendererHelper;
import fr.f1parking.core.io.IOHandler;
import fr.f1parking.core.level.Map;
import fr.f1parking.core.level.gen.IGenerator;
import fr.f1parking.core.level.gen.generators.ManualPlacingGenerator;
import fr.f1parking.core.level.gen.layers.ManualPlacingLayer;
import fr.f1parking.core.level.objects.GridBox;
import fr.f1parking.ui.Coordinator;
import fr.f1parking.ui.helpers.CSSHelper;
import fr.f1parking.ui.helpers.GameInterfaceHelper;
import fr.f1parking.ui.objects.GameFlowPane;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameInterface implements IInterface {
	
	private CongratulationModal congratulationModal;
	private Dialog<String> errorDeplacementBox;
	
	//Front
	private Scene game_scene;
	private GridPane game_root;
	
	private List<GameFlowPane> gameMapUI;
	private FlowPane selectedEntityPane;
	private final Button buttonZ, buttonQ, buttonS, buttonD;
	
	//Back
	private Map gameMap;
	private List<Entity> npcEntities;
	private EntityPlayer player;
	
	private int playerMoveCount = 0;
	
	private void initDialogBox(Coordinator coord) {
		errorDeplacementBox = new Dialog<>();
		
		errorDeplacementBox.setTitle("Erreur");
		errorDeplacementBox.getDialogPane().getButtonTypes().add(new ButtonType("C'est compris !", ButtonData.OK_DONE));
		((Stage)errorDeplacementBox.getDialogPane().getScene().getWindow())
			.getIcons().add(new Image(coord.getLogoFile().toURI().toString(), 0.2 * coord.getWIDTH(), 0.2 * coord.getHEIGHT(), false, true));
	}
	
	public GameInterface(final Coordinator d) {
		this.congratulationModal = new CongratulationModal(d);

		this.gameMapUI = new ArrayList<>();
		this.selectedEntityPane = null;
		
		initDialogBox(d);
		setupBackGame();
	
		// initialize scene

		GridPane first_gridpane = new GridPane();
		this.game_scene = new Scene(first_gridpane, d.getWIDTH(), d.getHEIGHT()); //Define the game scene
		
		first_gridpane.setStyle("-fx-background-color: #333333ff");
		ColumnConstraints first_gridpane_left_column = new ColumnConstraints();
		first_gridpane_left_column.setPercentWidth(20);
		ColumnConstraints first_gridpane_center_column = new ColumnConstraints();
		first_gridpane_center_column.setPercentWidth(60);
		ColumnConstraints first_gridpane_right_column = new ColumnConstraints();
		first_gridpane_right_column.setPercentWidth(20);

		RowConstraints first_gridpane_top_row = new RowConstraints();
		first_gridpane_top_row.setPercentHeight(5);
		RowConstraints first_gridpane_center_row = new RowConstraints();
		first_gridpane_center_row.setPercentHeight(90);
		RowConstraints first_gridpane_bottom_row = new RowConstraints();
		first_gridpane_bottom_row.setPercentHeight(5);

		//VBox to set button to the left

		VBox buttonPane = new VBox();
		buttonPane.setAlignment(Pos.CENTER);

		// column used in left gridpane

		ColumnConstraints left = new ColumnConstraints();
		left.setPercentWidth(33);
		ColumnConstraints center = new ColumnConstraints();
		center.setPercentWidth(33);
		ColumnConstraints right = new ColumnConstraints();
		right.setPercentWidth(33);

		// row used in left gridpane

		RowConstraints left_top = new RowConstraints();
		left_top.setPercentHeight(50);
		RowConstraints left_center = new RowConstraints();
		left_center.setPercentHeight(20);
		RowConstraints left_bottom = new RowConstraints();
		left_bottom.setPercentHeight(30);

		first_gridpane.add(buttonPane, 0, 1);
		
		first_gridpane.getColumnConstraints().addAll(first_gridpane_left_column, first_gridpane_center_column,
				first_gridpane_right_column);
		first_gridpane.getRowConstraints().addAll(first_gridpane_top_row, first_gridpane_center_row,
				first_gridpane_bottom_row);

		// initialize inside game
		game_root = new GridPane();
		//game_root.setGridLinesVisible(true);
		game_root.setAlignment(Pos.CENTER);
		ColumnConstraints game_column1 = new ColumnConstraints();
		// game_column1.setPrefWidth(flowtest.getWidth()/6);
		game_column1.setPercentWidth(16);
		ColumnConstraints game_colun2 = new ColumnConstraints();
		// game_colun2.setPrefWidth(flowtest.getWidth()/6);
		game_colun2.setPercentWidth(16);
		ColumnConstraints game_colun3 = new ColumnConstraints();
		// game_colun3.setPrefWidth(flowtest.getWidth()/6);
		game_colun3.setPercentWidth(16);
		ColumnConstraints game_colun4 = new ColumnConstraints();
		// game_colun4.setPrefWidth(flowtest.getWidth()/6);
		game_colun4.setPercentWidth(16);
		ColumnConstraints game_colun5 = new ColumnConstraints();
		// game_colun5.setPrefWidth(flowtest.getWidth()/6);
		game_colun5.setPercentWidth(16);
		ColumnConstraints game_colun6 = new ColumnConstraints();
		// game_colun6.setPrefWidth(flowtest.getWidth()/6);
		game_colun6.setPercentWidth(16);
		game_root.setStyle("-fx-background-color: #6c42f5");

		// row matrix

		RowConstraints game_rowA = new RowConstraints();

		game_rowA.setPercentHeight(16);
		RowConstraints game_rowB = new RowConstraints();

		game_rowB.setPercentHeight(16);
		RowConstraints game_rowC = new RowConstraints();

		game_rowC.setPercentHeight(16);
		RowConstraints game_rowD = new RowConstraints();

		game_rowD.setPercentHeight(16);
		RowConstraints game_rowE = new RowConstraints();

		game_rowE.setPercentHeight(16);
		RowConstraints game_rowF = new RowConstraints();

		game_rowF.setPercentHeight(16);
		game_root.getRowConstraints().addAll(game_rowA, game_rowB, game_rowC, game_rowD, game_rowE, game_rowF);
		game_root.getColumnConstraints().addAll(game_column1, game_colun2, game_colun3, game_colun4, game_colun5,
				game_colun6);
		first_gridpane.add(game_root, 1, 1);

		/*
		 * BUTTONS
		 */
		
		buttonZ = new Button("Z");
		buttonZ.setOnAction(event -> { onMovingButtonClick(Direction.NORTH); });
		CSSHelper.setButtonStyle(buttonZ, 80, 80);
		CSSHelper.setButtonOnHover(this.game_scene, buttonZ, 80, 80);
		
		buttonPane.getChildren().add(buttonZ);

		HBox movingButtonRow = new HBox(); // The row containing Q, S, D
		movingButtonRow.setAlignment(Pos.CENTER);
		movingButtonRow.setPadding(new Insets(5));
		movingButtonRow.setSpacing(5);
		
		buttonQ = new Button("Q");
		buttonQ.setOnAction(event -> { onMovingButtonClick(Direction.WEST); });
		CSSHelper.setButtonStyle(buttonQ, 80, 80);
		CSSHelper.setButtonOnHover(this.game_scene, buttonQ, 80, 80);
		
		buttonS = new Button("S");
		buttonS.setOnAction(event -> { onMovingButtonClick(Direction.SOUTH); });
		CSSHelper.setButtonStyle(buttonS, 80, 80);
		CSSHelper.setButtonOnHover(this.game_scene, buttonS, 80, 80);

		buttonD = new Button("D");
		buttonD.setOnAction(event -> { onMovingButtonClick(Direction.EAST); });
		CSSHelper.setButtonStyle(buttonD, 80, 80);
		CSSHelper.setButtonOnHover(this.game_scene, buttonD, 80, 80);

		movingButtonRow.getChildren().addAll(buttonQ, buttonS, buttonD);
		
		buttonPane.getChildren().add(movingButtonRow);

		Button exit = new Button("Retour au menu");
		CSSHelper.setButtonStyle(exit, 200, 30);
		CSSHelper.setButtonOnHover(this.game_scene, exit, 200, 30);

		exit.setAlignment(Pos.CENTER);
		exit.setOnAction(event -> {
			d.change_scene(2);
		});
		
		first_gridpane.add(exit, 2, 1);
		GridPane.setHalignment(exit, HPos.CENTER);
		GridPane.setValignment(exit, VPos.TOP);
		
		displayGame(game_root);
		
		this.game_scene.setOnKeyPressed(event -> {
			switch(event.getCode()) {
				case Z:
					if(selectedEntityPane != null)
						onMovingButtonClick(Direction.NORTH);
					break;
				case Q:
					if(selectedEntityPane != null)
						onMovingButtonClick(Direction.WEST);
					break;
				case S:
					if(selectedEntityPane != null)
						onMovingButtonClick(Direction.SOUTH);
					break;
				case D:
					if(selectedEntityPane != null)
						onMovingButtonClick(Direction.EAST);
					break;
				default:
					break;
			}
		});
	}

	private void setupBackGame() {
		this.player = new EntityPlayer();
		this.npcEntities = new ArrayList<>();
		npcEntities.add(new Truck(Direction.EAST, IOHandler.getInstance().getTexturesFile().getTruckTexture("truck1")));
		npcEntities.add(new Truck(Direction.EAST, IOHandler.getInstance().getTexturesFile().getTruckTexture("truck2")));
		npcEntities.add(new Truck(Direction.NORTH, IOHandler.getInstance().getTexturesFile().getTruckTexture("truck4")));
		npcEntities.add(new Truck(Direction.NORTH, IOHandler.getInstance().getTexturesFile().getTruckTexture("truck3")));
		npcEntities.add(new Car(Direction.EAST, IOHandler.getInstance().getTexturesFile().getCarTexture("alpineBlue")));
		
		
		ManualPlacingGenerator generator = ManualPlacingGenerator.builder()
				.placement(0, 2, 2, 2, Direction.EAST) // Player
				.placement(0, 0, 1, 3, Direction.EAST) // Truck 1
				.placement(3, 0, 1, 3, Direction.EAST) // Truck 2
				.placement(3, 4, 1, 3, Direction.NORTH) // Truck 3
				.placement(4, 4, 1, 2, Direction.EAST) // Car 1
				.placement(5, 3, 1, 3, Direction.NORTH) // Truck 4
				.build();
		
		ManualPlacingLayer layer = ManualPlacingLayer.builder()
				.entities(npcEntities).player(player)
				.build();
		
		this.gameMap = Map.builder()
				.generator(generator)
				.layer(layer)
				.name("Niveau 4")
				.build();
		
//		this.gameTree = Tree.builder()
//				.seed(new RandomSeed())
//				.difficulty(Difficulty.EASY)
//				.build();
//		
//		this.gameTree.generateMap();
	}
	
	private void displayGame(GridPane gamePane) {
		final GridBox[][] gridMap = this.gameMap.getMapCopy();
		
		for(int y = 0; y < IGenerator.GRID_SIZE; y++) {
			for(int x = 0; x < IGenerator.GRID_SIZE; x++) {
				final FlowPane pane = new FlowPane();
				pane.setAlignment(Pos.CENTER);
				pane.setStyle("-fx-background-color: black, white; -fx-background-insets: 0, 3 ;");
				
				if(gridMap[y][x].isEntityPresent()) {
					final Entity e = MapHelper.getEntityMatchingID(this.npcEntities, this.player, gridMap[y][x].getEntityID());
					
					final ImageView iv = new ImageView(e.getTexture().loadImage(100, 100));
					iv.setRotate(e.getFacingDirection().getRotation());
					
					pane.getChildren().add(iv);
					addEntityAction(pane);
					
					this.gameMapUI =  GameInterfaceHelper.addPaneToRightGameFlowPane(this.gameMapUI, e, pane);
				}
				
				gamePane.add(pane, x, y);
				
			}
		}
	}
	
	private void onMovingButtonClick(Direction movement) {
		if(selectedEntityPane == null) {
			errorDeplacementBox.setContentText("Merci de s\u00e9lectionner le v\u00e9hicule \u00e0 d\u00e9placer !");
			errorDeplacementBox.showAndWait();
			return;
		}
		final Entity e = MapHelper.getEntityMatchingID(this.npcEntities, this.player, GameInterfaceHelper.getRightFlowPane(this.gameMapUI, selectedEntityPane).getEntityId());
		
		if(e.equals(this.player) && MapHelper.isPlayerFinished(movement, MapHelper.doesMapContains(this.gameMap.getMapCopy(), e), this.player.getSize())) {
			congratulationModal.addMoveCount(this.playerMoveCount);
			congratulationModal.show();
		}else {
			if(this.gameMap.moveEntity(e, movement)) {
				this.playerMoveCount+=1;
				
				gameMapUI.clear();
				selectRightDirectionButton(Direction.NULL, null);
				selectedEntityPane = null;
				displayGame(this.game_root);
			}else {
				errorDeplacementBox.setContentText("Ce d\u00e9placement n'est pas possible pour ce v\u00e9hicule !");
				errorDeplacementBox.showAndWait();
			}
		}
	}
	
	private void addEntityAction(FlowPane pane) {
		pane.setOnMouseClicked(event -> {
			if(event.getButton() != MouseButton.PRIMARY) return;
			
			if(selectedEntityPane != pane) {
				if(selectedEntityPane != null) 
					for(FlowPane flowPane : GameInterfaceHelper.getRightFlowPane(this.gameMapUI, selectedEntityPane).getFlowplaneList()) 
						flowPane.setStyle("-fx-background-color: black, white; -fx-background-insets: 0,3;");
			
				final GameFlowPane gameFlowPane = GameInterfaceHelper.getRightFlowPane(this.gameMapUI, pane);
				final Entity e = MapHelper.getEntityMatchingID(this.npcEntities, this.player, gameFlowPane.getEntityId());
				
				for(int i = 0; i < gameFlowPane.getFlowplaneList().size(); i++)
					gameFlowPane.getFlowplaneList().get(i).setStyle("-fx-background-color: red, white; -fx-background-insets: 0, "+calculateInsets(i, gameFlowPane.getFlowplaneList().size(), e.getFacingDirection())+" ;");
				
				selectedEntityPane = pane;
				selectRightDirectionButton(e.getFacingDirection(), MapHelper.doesMapContains(this.gameMap.getMapCopy(), e));
			}else {
				for(FlowPane flowPane : GameInterfaceHelper.getRightFlowPane(this.gameMapUI, pane).getFlowplaneList()) 
					flowPane.setStyle("-fx-background-color: black, white; -fx-background-insets: 0,3;");
				selectedEntityPane = null;
				selectRightDirectionButton(Direction.NULL, null);
			}
		}); 
	}
	
	private void selectRightDirectionButton(Direction entityDir, Coordinate entityPos) {
		if(entityDir == Direction.NORTH || entityDir == Direction.SOUTH) {
			buttonZ.setDisable(DeplacementHelper.isAValidMovement(entityPos, entityDir, Direction.NORTH) ? false : true); 
			buttonS.setDisable(DeplacementHelper.isAValidMovement(entityPos, entityDir, Direction.SOUTH) ? false : true);
			
			buttonQ.setDisable(true); buttonD.setDisable(true);
		}else if(entityDir == Direction.WEST || entityDir == Direction.EAST) {
			buttonQ.setDisable(DeplacementHelper.isAValidMovement(entityPos, entityDir, Direction.WEST) ? false : true); 
			buttonD.setDisable(DeplacementHelper.isAValidMovement(entityPos, entityDir, Direction.EAST) ? false : true);

			buttonZ.setDisable(true); buttonS.setDisable(true);
		}else {
			buttonZ.setDisable(false); buttonS.setDisable(false);
			buttonQ.setDisable(false); buttonD.setDisable(false);
		}
	}
	
	private String calculateInsets(int index, int size, Direction facing) {
		if(facing == Direction.EAST || facing ==Direction.WEST) {
			if(index == 0) return "3 0 3 3";
			else if(index == size-1) return "3 3 3 0";
			else return "3 0 3 0";
		}else {
			if(index == 0) return "3 3 0 3";
			else if(index == size-1) return "0 3 3 3";
			else return "0 3 0 3";
		}
	}
	
	@Override
	public Scene getInterface() {
		return this.game_scene;
	}
}
