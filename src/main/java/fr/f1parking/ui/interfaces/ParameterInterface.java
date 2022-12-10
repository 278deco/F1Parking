package fr.f1parking.ui.interfaces;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fr.f1parking.core.io.IOHandler;
import fr.f1parking.ui.Coordinator;
import fr.f1parking.ui.helpers.CSSHelper;
import fr.f1parking.ui.objects.Parameter;
import fr.f1parking.ui.parameters.CarParameter;
import fr.f1parking.ui.parameters.MusicParameter;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ParameterInterface extends AbstractInterface {

	private final VBox parametersBox;
	
	private Dialog<String> successDialog;
	
	private final List<Parameter> parametersList;
	
	private void initDialogBox(Coordinator coord) {
		successDialog = new Dialog<>();
		
		successDialog.setTitle(coord.getWindowName()+" - Sauvegarder");
		successDialog.getDialogPane().getButtonTypes().add(new ButtonType("C'est compris !", ButtonData.OK_DONE));
		successDialog.setContentText("Les nouveaux param\u00e8tres ont bien \u00e9t\u00e9 enregistr\u00e9s.");
		((Stage)successDialog.getDialogPane().getScene().getWindow())
			.getIcons().add(new Image(coord.getLogoFile().toURI().toString(), 0.2 * coord.getWidth(), 0.2 * coord.getHeight(), false, true));
	}
	
	public ParameterInterface(Coordinator c) {
		initDialogBox(c);
		
		this.parametersList = new ArrayList<>();
		
		VBox root = new VBox();
		this.sceneInterface = new Scene(root, c.getWidth(), c.getHeight()); //Define the game scene
		
		root.setStyle("-fx-background-color: #333333ff");

		/*
		 * TITLE & EXIT BUTTON
		 */
		
		final GridPane header = new GridPane();
		header.setMinHeight(75);
			
		final RowConstraints heightCons = new RowConstraints();
		heightCons.setMinHeight(75);
		
		final ColumnConstraints leftColCons = new ColumnConstraints();
		leftColCons.setPercentWidth(55);
		final ColumnConstraints rightColCons = new ColumnConstraints();
		rightColCons.setPercentWidth(45);
		
		header.getRowConstraints().add(heightCons);
		header.getColumnConstraints().addAll(leftColCons, rightColCons);
		
		final Text headerLabel = new Text("Param\u00e8tres");
		headerLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 30px; -fx-fill: white");
		
		header.add(headerLabel, 0, 0);
		GridPane.setValignment(headerLabel, VPos.CENTER);
		GridPane.setHalignment(headerLabel, HPos.RIGHT);
		
		Button exit = new Button("Retour au menu");
		CSSHelper.setButtonStyle(exit, 200, 30);
		CSSHelper.setButtonOnHover(this.sceneInterface, exit, 200, 30);

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
		
		parametersBox = new VBox();
		
		final ScrollPane scrollPane = new ScrollPane(parametersBox);
    	scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
    	scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollPane.setPrefHeight(c.getHeight());
		scrollPane.setFitToWidth(true);
    	
		parametersBox.setStyle("-fx-background-color: #333333ff");
		parametersBox.setMinSize(scrollPane.getWidth(), c.getHeight());
		parametersBox.setSpacing(25D);
		
		/*
		 * Footer
		 */
		
		final Button saveButton = new Button("Sauvegarder");
		CSSHelper.setButtonStyle(saveButton, 150, 30);
		CSSHelper.setButtonOnHover(this.sceneInterface, saveButton, 150, 30);
		saveButton.setOnMouseClicked(event -> saveButtonAction(event));
		
		final FlowPane saveFooter = new FlowPane(saveButton);
		saveFooter.setAlignment(Pos.CENTER);
		saveFooter.setMinHeight(60);
		saveFooter.setStyle("-fx-background-color: #242120");
		
		root.getChildren().addAll(header, scrollPane, saveFooter);
		
		loadParameter();
	}
    
	private void saveButtonAction(MouseEvent event) {
		if(event.getButton() != MouseButton.PRIMARY) return;
		
		for(Parameter param : this.parametersList) {
			param.save();
		}
		
		try {
			IOHandler.getInstance().getConfiguration().saveFile();
		} catch (IOException e) {
			LOGGER.warn("An error occured while saving JSON configuration file",e);
		}
		
		this.successDialog.showAndWait();
		
		for(Parameter param : this.parametersList) {
			param.refresh();
		}
	}
	
	private void loadParameter() {
		this.parametersList.add(new CarParameter());
		this.parametersList.add(new MusicParameter());
		
		for(Parameter param : this.parametersList) {
			this.parametersBox.getChildren().add(param.getContent());
		}
	}
	
	
	@Override
	public void refreshScene(Coordinator c) {
		for(Parameter param : this.parametersList) {
			param.refresh();
		}
	}
}
