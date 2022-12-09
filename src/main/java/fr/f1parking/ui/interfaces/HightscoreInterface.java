package fr.f1parking.ui.interfaces;

import fr.f1parking.core.io.IOHandler;
import fr.f1parking.core.level.MapLoader;
import fr.f1parking.ui.Coordinator;
import fr.f1parking.ui.helpers.CSSHelper;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class HightscoreInterface implements IInterface {

    private Scene highscoreScene;

    private final VBox highscoreBox;
    
    public HightscoreInterface(final Coordinator c){
    	VBox root = new VBox();
		this.highscoreScene = new Scene(root, c.getWIDTH(), c.getHEIGHT()); //Define the game scene
		
		root.setStyle("-fx-background-color: #333333ff");

		/*
		 * TITLE & EXIT BUTTON
		 */
		
		final GridPane header = new GridPane();
		header.setMinHeight(75);
			
		final RowConstraints heightCons = new RowConstraints();
		heightCons.setMinHeight(75);
		
		final ColumnConstraints leftColCons = new ColumnConstraints();
		leftColCons.setPercentWidth(60);
		final ColumnConstraints rightColCons = new ColumnConstraints();
		rightColCons.setPercentWidth(40);
		
		header.getRowConstraints().add(heightCons);
		header.getColumnConstraints().addAll(leftColCons, rightColCons);
		
		final Text headerLabel = new Text("Tableau des scores");
		headerLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 30px; -fx-fill: white");
		
		header.add(headerLabel, 0, 0);
		GridPane.setValignment(headerLabel, VPos.CENTER);
		GridPane.setHalignment(headerLabel, HPos.RIGHT);
		
		Button exit = new Button("Retour au menu");
		CSSHelper.setButtonStyle(exit, 200, 30);
		CSSHelper.setButtonOnHover(this.highscoreScene, exit, 200, 30);

		exit.setOnAction(event -> {
			c.change_scene(2);
		});
		
		final FlowPane exitPane = new FlowPane(exit);
		exitPane.setAlignment(Pos.CENTER_RIGHT);
		exitPane.setPadding(new Insets(0, 15, 0, 0));
		
		header.add(exitPane, 1, 0);
		
		/*
		 * Page Content
		 */
		
		highscoreBox = new VBox();
		
		final ScrollPane scrollPane = new ScrollPane(highscoreBox);
    	scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
    	scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollPane.setPrefHeight(c.getHEIGHT());
		scrollPane.setFitToWidth(true);
    	
		highscoreBox.setStyle("-fx-background-color: #333333ff");
		highscoreBox.setMinSize(scrollPane.getWidth(), c.getHEIGHT());
		highscoreBox.setSpacing(50D);
		
		root.getChildren().addAll(header, scrollPane);
		
		addNewHighscore(highscoreBox, c);
	}
    
    private void addNewHighscore(VBox root, Coordinator c) {
    	final RowConstraints heightCons = new RowConstraints();
		heightCons.setMinHeight(75);
		
		final ColumnConstraints leftColCons = new ColumnConstraints();
		leftColCons.setPercentWidth(60);
		final ColumnConstraints rightColCons = new ColumnConstraints();
		rightColCons.setPercentWidth(40);
		
    	for(int i = 0; i < MapLoader.getInstance().getMapNumber(); i++) {
    		GridPane content = new GridPane();
    		String indexMapName = MapLoader.getInstance().getMap(i).getName();
    		
    		final Text mapName = new Text();
    		mapName.setStyle("-fx-font-weight: bold; -fx-font-size: 30px; -fx-fill: white");

    		final Text mapScore = new Text();
    		mapScore.setStyle("-fx-font-weight: bold; -fx-font-size: 25px; -fx-fill: white");
    		
    		content.getRowConstraints().add(heightCons);
    		content.getColumnConstraints().addAll(leftColCons, rightColCons);
    		content.setStyle("-fx-background-color: #18181f");
    		
    		mapName.setText(indexMapName);
    		
    		final FlowPane mapNamePane = new FlowPane(mapName);
    		mapNamePane.setAlignment(Pos.CENTER_LEFT);
    		mapNamePane.setPadding(new Insets(0, 0, 0, 15));
    		
    		content.add(mapNamePane, 0, 0);
    		
    		mapScore.setText("Score : "+IOHandler.getInstance().getHighscoreFile().getFormattedHighScore(indexMapName));
    		
    		final FlowPane mapScorePane = new FlowPane(mapScore);
    		mapScorePane.setAlignment(Pos.CENTER_RIGHT);
    		mapScorePane.setPadding(new Insets(0, 15, 0, 0));
    		
    		
    		content.add(mapScorePane, 1, 0);
    		GridPane.setHalignment(mapScorePane, HPos.RIGHT);
    		
    		root.getChildren().add(content);
    	}
    }
    
    public void refresh(Coordinator c) {
    	this.highscoreBox.getChildren().clear();
    	
		this.addNewHighscore(this.highscoreBox, c);
	}

    @Override
    public Scene getInterface() {
    	return this.highscoreScene;
    }
}
