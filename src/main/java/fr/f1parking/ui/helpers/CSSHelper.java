package fr.f1parking.ui.helpers;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class CSSHelper {

	public static Button setButtonStyle(Button button, int sizeX, int sizeY) {
		button.setPrefSize(sizeX, sizeY);
		button.setAlignment(Pos.CENTER);
		button.setStyle("-fx-background-color: #B2BABB ;-fx-background-radius: 15px; -fx-font-size: 15px ");
		return button;
	}
	
	

	public static void setButtonOnHover(Scene scene, Button btn, int sizeX, int sizeY) {
		btn.hoverProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				btn.setStyle(
						"-fx-background-color: #424040; -fx-text-fill: white; -fx-font-size: 20px;-fx-background-radius: 20px");
				btn.setEffect(new javafx.scene.effect.DropShadow(10, Color.web("#000000")));
				scene.setCursor(javafx.scene.Cursor.HAND);
			} else {
				btn.setStyle(null);
				btn.setEffect(null);
				btn.setPrefSize(sizeX, sizeY);
				btn.setStyle("-fx-background-color: #B2BABB ;-fx-background-radius: 15px; -fx-font-size: 15px ");
				scene.setCursor(javafx.scene.Cursor.DEFAULT);
			}
		});
	}
	
}
