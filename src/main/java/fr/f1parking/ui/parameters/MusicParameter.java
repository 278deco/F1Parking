package fr.f1parking.ui.parameters;

import fr.f1parking.core.io.IOHandler;
import fr.f1parking.ui.objects.Parameter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class MusicParameter extends Parameter {

	private final Slider slider;
	
	private int tempMusicVolume;
	
	public MusicParameter() {
		super("Musique du jeu");
		slider = new Slider(0, 100, 0);
		
		final Text sliderText = new Text();
		sliderText.setStyle("-fx-fill: white; -fx-font-size: 12px;");
		
		slider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!unsavedChange) {
					parameterName.setStyle("-fx-font-weight: bold; -fx-font-size: 30px; -fx-fill: #ff6347");
					unsavedChange = true;
				}
			}
		});
		
		slider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				sliderText.setText(String.valueOf((int)slider.getValue()));
				tempMusicVolume = (int)slider.getValue();
			}
		});
		
		final HBox sliderPane = new HBox();
		sliderPane.setAlignment(Pos.CENTER_RIGHT);
		sliderPane.setPadding(new Insets(0, 15, 0, 0));
		sliderPane.setSpacing(10);
		
		sliderPane.getChildren().addAll(sliderText, slider);
		
		content.add(sliderPane, 1, 0);
		GridPane.setHalignment(sliderPane, HPos.RIGHT);
	}

	@Override
	public void save() {
		if(unsavedChange) {
			IOHandler.getInstance().getConfiguration().setMusicVolume(tempMusicVolume);
			tempMusicVolume = 0;
			this.unsavedChange = false;
		}
	}
	
	@Override
	public void refresh() { 
		slider.setValue(IOHandler.getInstance().getConfiguration().getMusicVolume()*100D);
		parameterName.setStyle("-fx-font-weight: bold; -fx-font-size: 30px; -fx-fill: white");
	}

}
