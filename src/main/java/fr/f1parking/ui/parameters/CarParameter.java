package fr.f1parking.ui.parameters;

import java.util.List;

import fr.f1parking.core.io.IOHandler;
import fr.f1parking.ui.objects.Parameter;
import fr.f1parking.utils.Tuple;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class CarParameter extends Parameter {

	private final ComboBox<String> carComboBox;
	
	//The list of textures is never regenerated while the program is running, so we can save them here without any problems
	private final List<String> carIDNames;
	private final List<String> carDisplayableNames;
	
	private String tempPlayerCar;
	
	public CarParameter() {
		super("Voiture du joueur");
		carComboBox = new ComboBox<>();
		carComboBox.setPrefWidth(250);
		
		final Tuple<List<String>, List<String>> result = IOHandler.getInstance().getTexturesFile().getCarNameList();
		this.carIDNames = result.getValueA();
		this.carDisplayableNames = result.getValueB();
		
		carComboBox.getItems().addAll(this.carDisplayableNames);
		carComboBox.getSelectionModel().select(carIDNames.indexOf(IOHandler.getInstance().getConfiguration().getPlayerCar()));
		
		carComboBox.setOnAction(event -> {
			if(!this.unsavedChange) {
				this.unsavedChange=true;
				parameterName.setStyle("-fx-font-weight: bold; -fx-font-size: 30px; -fx-fill: #ff6347");
			}
			
			this.tempPlayerCar = carIDNames.get(carComboBox.getSelectionModel().getSelectedIndex());
		});
		
		final FlowPane carComboBoxPane = new FlowPane(carComboBox);
		carComboBoxPane.setAlignment(Pos.CENTER_RIGHT);
		carComboBoxPane.setPadding(new Insets(0, 15, 0, 0));
		
		content.add(carComboBoxPane, 1, 0);
		GridPane.setHalignment(carComboBoxPane, HPos.RIGHT);
	}
	
	@Override
	public void save() {
		if(unsavedChange) {
			IOHandler.getInstance().getConfiguration().setPlayerCar(this.tempPlayerCar);
			this.tempPlayerCar = null;
			this.unsavedChange = false;
		}
	}

	@Override
	public void refresh() { 
		carComboBox.getSelectionModel().select(carIDNames.indexOf(IOHandler.getInstance().getConfiguration().getPlayerCar()));
		parameterName.setStyle("-fx-font-weight: bold; -fx-font-size: 30px; -fx-fill: white");
	}

}
