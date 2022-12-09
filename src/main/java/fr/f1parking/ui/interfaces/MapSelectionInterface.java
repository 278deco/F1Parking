package fr.f1parking.ui.interfaces;

import fr.f1parking.core.level.MapLoader;
import fr.f1parking.ui.Coordinator;
import fr.f1parking.ui.helpers.CSSHelper;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class MapSelectionInterface extends AbstractInterface {
	
	private static final int BUTTON_SIZE = 120;

	public MapSelectionInterface(final Coordinator c) {
	
		// initialize scene

		GridPane root = new GridPane();
		this.sceneInterface = new Scene(root, c.getWIDTH(), c.getHEIGHT()); //Define the game scene
		
		root.setStyle("-fx-background-color: #333333ff");
		ColumnConstraints sideCons = new ColumnConstraints();
		sideCons.setPercentWidth(10);
		ColumnConstraints mapColumnCons = new ColumnConstraints();
		mapColumnCons.setPercentWidth(20);

		RowConstraints topCons = new RowConstraints();
		topCons.setPercentHeight(10);
		RowConstraints mapRowCons = new RowConstraints();
		mapRowCons.setPercentHeight(20);
		RowConstraints buttonsBarCons = new RowConstraints();
		buttonsBarCons.setPercentHeight(10);

		// Define the constraints
		root.getColumnConstraints().addAll(sideCons, mapColumnCons, mapColumnCons, mapColumnCons, mapColumnCons, sideCons);
		root.getRowConstraints().addAll(topCons, mapRowCons, mapRowCons, mapRowCons, mapRowCons, buttonsBarCons);

		/*
		 * TITLE & EXIT BUTTON
		 */
		
		final Text selectionLabel = new Text("S\u00e9lection de la carte");
		selectionLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 30px; -fx-fill: white");
		
		root.add(selectionLabel, 2, 0, 2, 1);
		GridPane.setHalignment(selectionLabel, HPos.CENTER);
		
		Button exit = new Button("Retour au menu");
		CSSHelper.setButtonStyle(exit, 200, 30);
		CSSHelper.setButtonOnHover(this.sceneInterface, exit, 200, 30);

		exit.setOnAction(event -> {
			c.change_scene(2);
		});
		
		final FlowPane exitPane = new FlowPane(exit);
		exitPane.setAlignment(Pos.CENTER_RIGHT);
		exitPane.setPadding(new Insets(0, 15, 0, 0));
		
		root.add(exitPane, 4, 0, 2, 1);
		
		/*
		 * BOTTOM BUTTONS
		 */
		
		final HBox buttonBox = new HBox();
		buttonBox.setStyle("-fx-background-color: #18181f");
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setSpacing(60);
		
		final Button prevButton = new Button("Pr\u00e9c\u00e9dent");
		prevButton.setDisable(true); //TODO add new pages
		CSSHelper.setButtonStyle(prevButton, 125, 30);
		CSSHelper.setButtonOnHover(this.sceneInterface, prevButton, 125, 30);
		
		final Text pageText = new Text("1 / 1");
		pageText.setStyle("-fx-font-size: 20px; -fx-fill: white");
		
		final Button nextButton = new Button("Suivant");
		nextButton.setDisable(true); //TODO add new pages
		CSSHelper.setButtonStyle(nextButton, 125, 30);
		CSSHelper.setButtonOnHover(this.sceneInterface, nextButton, 125, 30);
		
		buttonBox.getChildren().addAll(prevButton, pageText, nextButton);
		
		root.add(buttonBox, 0, 5, 6, 1);
		
		addNewMapSelection(root, c);
		
	}
	
	private void addNewMapSelection(GridPane root, Coordinator c) {
		int x = 1;
		int y = 1;
		for(int i = 0; i < MapLoader.getInstance().getMapNumber(); i++) {
			final Button mapButton = new Button(MapLoader.getInstance().getMap(i).getName());
			setSelectionButtonStyle(mapButton);
			setSelectionButtonOnHover(this.sceneInterface, mapButton);
			
			mapButton.setOnMouseClicked(event -> {
				c.loadSelectedMap(MapLoader.getInstance().getIdMapWithName(event.getButton().name()));
				c.change_scene(1);
			});
			
			root.add(mapButton, x, y);
			GridPane.setHalignment(mapButton, HPos.CENTER);
			GridPane.setValignment(mapButton, VPos.CENTER);
			
			x+=1;
			if(x%4 == 0) {
				x = 1;
				y+=1;
			}
			if(y == 4) i = MapLoader.getInstance().getMapNumber();
		}
	}
	
	private void setSelectionButtonStyle(Button button) {
		button.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
		button.setAlignment(Pos.CENTER);
		button.setStyle("-fx-background-color: #B2BABB ;-fx-background-radius: 5px; -fx-font-size: 20px ");
	}
	
	private void setSelectionButtonOnHover(Scene scene, Button btn) {
		btn.hoverProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				btn.setStyle(
						"-fx-background-color: #424040; -fx-text-fill: white; -fx-font-size: 20px;-fx-background-radius: 10px");
				btn.setEffect(new javafx.scene.effect.DropShadow(5, Color.web("#000000")));
				scene.setCursor(javafx.scene.Cursor.HAND);
			} else {
				btn.setStyle(null);
				btn.setEffect(null);
				btn.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
				btn.setStyle("-fx-background-color: #B2BABB ;-fx-background-radius: 5px; -fx-font-size: 20px");
				scene.setCursor(javafx.scene.Cursor.DEFAULT);
			}
		});
	}
	
	@Override
	public void refreshScene(Coordinator c) { }

}
