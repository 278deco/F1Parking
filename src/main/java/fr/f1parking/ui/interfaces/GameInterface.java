package fr.f1parking.ui.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import fr.f1parking.core.entities.Entity;
import fr.f1parking.core.entities.EntityPlayer;
import fr.f1parking.core.entities.placement.Coordinate;
import fr.f1parking.core.entities.placement.Direction;
import fr.f1parking.core.helpers.DeplacementHelper;
import fr.f1parking.core.helpers.MapHelper;
import fr.f1parking.core.io.IOHandler;
import fr.f1parking.core.level.Map;
import fr.f1parking.core.level.MapLoader;
import fr.f1parking.core.level.gen.IGenerator;
import fr.f1parking.core.level.objects.GridBox;
import fr.f1parking.core.maps.IMap;
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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class GameInterface extends AbstractInterface {
	
	private CongratulationModal congratulationModal;
	private Dialog<String> errorDeplacementBox;
	
	//Front
	private final GridPane game_root;
	private final Button buttonZ, buttonQ, buttonS, buttonD;
	private final Text titleLabel;
	
	private final List<GameFlowPane> gameMapUI;
	private FlowPane selectedEntityPane;
	
	//Back
	private Map gameMap;
	private List<Entity> npcEntities;
	private EntityPlayer player;
	
	private int playerMoveCount = 0;
	
	private void initDialogBox(Coordinator coord) {
		errorDeplacementBox = new Dialog<>();
		
		errorDeplacementBox.setTitle(coord.getWindowName()+" - Erreur");
		errorDeplacementBox.getDialogPane().getButtonTypes().add(new ButtonType("C'est compris !", ButtonData.OK_DONE));
		((Stage)errorDeplacementBox.getDialogPane().getScene().getWindow())
			.getIcons().add(new Image(coord.getLogoFile().toURI().toString(), 0.2 * coord.getWidth(), 0.2 * coord.getHeight(), false, true));
	}
	
	public GameInterface(final Coordinator d) {
		this.congratulationModal = new CongratulationModal(d);

		this.gameMapUI = new ArrayList<>();
		this.selectedEntityPane = null;
		
		initDialogBox(d);
	
		// initialize scene

		GridPane first_gridpane = new GridPane();
		//first_gridpane.setGridLinesVisible(true);
		this.sceneInterface = new Scene(first_gridpane, d.getWidth(), d.getHeight()); //Define the game scene
		
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
		ColumnConstraints gameColumExitCons = new ColumnConstraints();
		gameColumExitCons.setPercentWidth(0.5);
		
		game_root.setStyle("-fx-background-color: #404082");

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
				game_colun6, gameColumExitCons);
		
		//Exit indicator
		final FlowPane exitFlow = new FlowPane();
		exitFlow.setStyle("-fx-background-color: green"); 
		game_root.add(exitFlow, IGenerator.GRID_SIZE, (int)(IGenerator.GRID_SIZE-1)/2);
		
		first_gridpane.add(game_root, 1, 1);

		/*
		 * TITLE (rule)
		 */
		
		final Text objTitleLabel = new Text("Objectif : ");
		objTitleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 25px; -fx-fill: white");
		
		titleLabel = new Text("Sortir la F1 de la grille !");
		titleLabel.setStyle("-fx-font-size: 25px; -fx-fill: white");
		
		TextFlow titleFlow = new TextFlow(objTitleLabel,titleLabel);
		titleFlow.setTextAlignment(TextAlignment.CENTER);
		
		first_gridpane.add(titleFlow, 1, 0);
		GridPane.setHalignment(titleFlow, HPos.CENTER);
		
		/*
		 * BUTTONS
		 */
		
		buttonZ = new Button("Z");
		buttonZ.setOnAction(event -> { onMovingButtonClick(Direction.NORTH); });
		CSSHelper.setButtonStyle(buttonZ, 80, 80);
		CSSHelper.setButtonOnHover(this.sceneInterface, buttonZ, 80, 80);
		
		buttonPane.getChildren().add(buttonZ);

		HBox movingButtonRow = new HBox(); // The row containing Q, S, D
		movingButtonRow.setAlignment(Pos.CENTER);
		movingButtonRow.setPadding(new Insets(5));
		movingButtonRow.setSpacing(5);
		
		buttonQ = new Button("Q");
		buttonQ.setOnAction(event -> { onMovingButtonClick(Direction.WEST); });
		CSSHelper.setButtonStyle(buttonQ, 80, 80);
		CSSHelper.setButtonOnHover(this.sceneInterface, buttonQ, 80, 80);
		
		buttonS = new Button("S");
		buttonS.setOnAction(event -> { onMovingButtonClick(Direction.SOUTH); });
		CSSHelper.setButtonStyle(buttonS, 80, 80);
		CSSHelper.setButtonOnHover(this.sceneInterface, buttonS, 80, 80);

		buttonD = new Button("D");
		buttonD.setOnAction(event -> { onMovingButtonClick(Direction.EAST); });
		CSSHelper.setButtonStyle(buttonD, 80, 80);
		CSSHelper.setButtonOnHover(this.sceneInterface, buttonD, 80, 80);

		movingButtonRow.getChildren().addAll(buttonQ, buttonS, buttonD);
		
		buttonPane.getChildren().add(movingButtonRow);

		Button exit = new Button("Retour \u00e0 la s\u00e9lection");
		CSSHelper.setButtonStyle(exit, 220, 30);
		CSSHelper.setButtonOnHover(this.sceneInterface, exit, 220, 30);

		exit.setAlignment(Pos.CENTER);
		exit.setOnAction(event -> {
			d.change_scene(5);
		});
		
		first_gridpane.add(exit, 2, 1);
		GridPane.setHalignment(exit, HPos.CENTER);
		GridPane.setValignment(exit, VPos.TOP);
		
		this.sceneInterface.setOnKeyPressed(event -> {
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

	public void setupBackGame(int mapId) {
		
		IMap loadingMap = MapLoader.getInstance().getMap(mapId);
		
		this.npcEntities = loadingMap.getEntities();
		this.player = loadingMap.getPlayer();
		
		this.gameMap = Map.builder()
				.generator(loadingMap.getGenerator())
				.layer(loadingMap.getLayer())
				.name(loadingMap.getName())
				.build();
		
		displayGame(game_root);
	}
	
	private void displayGame(GridPane gamePane) {
		final GridBox[][] gridMap = this.gameMap.getMapCopy();
		final List<UUID> alreadyAdded = new ArrayList<>();
		
		for(int y = 0; y < IGenerator.GRID_SIZE; y++) {
			for(int x = 0; x < IGenerator.GRID_SIZE; x++) {
				final FlowPane pane = new FlowPane();
				pane.setAlignment(Pos.CENTER);
				pane.setStyle("-fx-background-color: black, white; -fx-background-insets: 0, 3 ;");
				
				if(gridMap[y][x].isEntityPresent()) {
					if(!alreadyAdded.contains(gridMap[y][x].getEntityID())) {
						final Entity e = MapHelper.getEntityMatchingID(this.npcEntities, this.player, gridMap[y][x].getEntityID());
						final ImageView iv = new ImageView(e.getTexture().loadImage(100, 100));
						final Coordinate overflow = getRightOverflow(e);
						
						iv.setRotate(e.getFacingDirection().getRotation());
						pane.getChildren().add(iv);
						pane.setOnMouseClicked(event -> {
							entityPaneSelection(pane);
						}); 
						
						this.gameMapUI.add(new GameFlowPane(e.getId(), pane));
						
						gamePane.add(pane, x, y, overflow.getX(), overflow.getY());
						alreadyAdded.add(e.getId());
					}
				}else {
					gamePane.add(pane, x, y);
				}
				
			}
		}
	}
	
	private void displayGame(GridPane gamePane, Entity e) {
		this.displayGame(gamePane);
		if(e != null) {
			GameFlowPane gameFp = GameInterfaceHelper.getRightFlowPane(this.gameMapUI, e);
			this.entityPaneSelection(gameFp.getPane());
		}
	}
	
	private Coordinate getRightOverflow(Entity e) {
		if(e.getFacingDirection() == Direction.WEST || e.getFacingDirection() == Direction.EAST) {
			return new Coordinate(e.getSize(), 1);
		}else if(e.getFacingDirection() == Direction.NORTH || e.getFacingDirection() == Direction.SOUTH) {
			return new Coordinate(1, e.getSize());
		}
		return new Coordinate(1, 1);	
	}
	
	private void onMovingButtonClick(Direction movement) {
		if(selectedEntityPane == null) {
			errorDeplacementBox.setContentText("Merci de s\u00e9lectionner le v\u00e9hicule \u00e0 d\u00e9placer !");
			errorDeplacementBox.showAndWait();
			return;
		}
		final Entity e = MapHelper.getEntityMatchingID(this.npcEntities, this.player, GameInterfaceHelper.getRightFlowPane(this.gameMapUI, selectedEntityPane).getEntityId());
		
		if(e.equals(this.player) && MapHelper.isPlayerFinished(movement, MapHelper.doesMapContains(this.gameMap.getMapCopy(), e), this.player.getSize())) {
			final boolean high = IOHandler.getInstance().getHighscoreFile().addNewScore(this.gameMap.getName(), this.playerMoveCount);
			congratulationModal.setPlayerScore(this.playerMoveCount, high);
			congratulationModal.show();
		}else {
			if(this.gameMap.moveEntity(e, movement)) {
				this.playerMoveCount+=1;
				
				gameMapUI.clear();
				selectRightDirectionButton(Direction.NULL, null);
				selectedEntityPane = null;
				displayGame(this.game_root, e);
			}else {
				errorDeplacementBox.setContentText("Ce d\u00e9placement n'est pas possible pour ce v\u00e9hicule !");
				errorDeplacementBox.showAndWait();
			}
		}
	}
	
	private void entityPaneSelection(FlowPane pane) {
		if(selectedEntityPane != pane) {
			if(selectedEntityPane != null) 
				selectedEntityPane.setStyle("-fx-background-color: black, white; -fx-background-insets: 0,3;");
		
			final GameFlowPane gameFlowPane = GameInterfaceHelper.getRightFlowPane(this.gameMapUI, pane);
			final Entity e = MapHelper.getEntityMatchingID(this.npcEntities, this.player, gameFlowPane.getEntityId());
			
			pane.setStyle("-fx-background-color: red, white; -fx-background-insets: 0, 3");
			
			selectedEntityPane = pane;
			selectRightDirectionButton(e.getFacingDirection(), MapHelper.doesMapContains(this.gameMap.getMapCopy(), e));
		}else {
			selectedEntityPane.setStyle("-fx-background-color: black, white; -fx-background-insets: 0,3;");
			selectedEntityPane = null;
			selectRightDirectionButton(Direction.NULL, null);
		}
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

	@Override
	public void refreshScene(Coordinator c) {
		titleLabel.setText("Sortir la F1 "+
				IOHandler.getInstance().getTexturesFile().getCarTexture(IOHandler.getInstance().getConfiguration().getPlayerCar()).getDisplayableName()+
				" de la grille !");
	}
}
