package fr.f1parking.ui.interfaces;

import java.io.File;

import fr.f1parking.Main;
import fr.f1parking.ui.Coordinator;
import fr.f1parking.ui.helpers.CSSHelper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CongratulationModal extends AbstractInterface {

	private static final int WIDTH = 500;
	private static final int HEIGHT = 400;
	
	private final Stage modal;

	private final Text congratLabel, congratHighLabel;

	public CongratulationModal(Coordinator c) {
		this.modal = new Stage();
		this.modal.initModality(Modality.APPLICATION_MODAL);
		this.modal.initOwner(c.getPrimaryStage());
		this.modal.setTitle(c.getWindowName());
		this.modal.getIcons().add(new Image(c.getLogoFile().toURI().toString(), 0.2 * c.getWidth(), 0.2 * c.getHeight(), false, true));

		RowConstraints topRow = new RowConstraints();
		topRow.setPercentHeight(15);
		
		RowConstraints centerRow = new RowConstraints();
		centerRow.setPercentHeight(60);
		
		RowConstraints bottomRow = new RowConstraints();
		bottomRow.setPercentHeight(25);
		
		GridPane root = new GridPane();
		this.sceneInterface = new Scene(root, WIDTH, HEIGHT);
		
		root.setAlignment(Pos.CENTER);
		root.getRowConstraints().addAll(topRow, centerRow, bottomRow);
		
		/*
		 * Panes definition
		 */
		
		HBox top = new HBox();
		top.setAlignment(Pos.CENTER);
		top.setPadding(new Insets(10,0,0,0));
		
		FlowPane center = new FlowPane();
		center.setAlignment(Pos.CENTER);
		
		HBox bottom = new HBox();
		bottom.setAlignment(Pos.CENTER);
		bottom.setSpacing(50);
		
		/*
		 * TOP
		 */
		
		TextFlow congratFlow = new TextFlow();
		
		
		congratLabel = new Text();
		congratHighLabel = new Text();
		congratFlow.getChildren().addAll(new Text("Bien jou\u00e9 ! Vous avez fait sortir la Formule 1 en "), this.congratLabel, new Text(" coup(s) ! \n"), this.congratHighLabel);
		
		congratHighLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
		congratLabel.setStyle("-fx-font-weight: bold");
		congratFlow.setStyle("-fx-font-size: 14px; -fx-text-alignment: center");

		/*
		 * CENTER
		 */
		
		center.getChildren().add(new ImageView(new Image(new File("datas/img/congratulation.jpg").toURI().toString(), 360, 260, false, true)));
		
		/*
		 * BOTTOM
		 */
		
		Button return_menu = new Button("Retour \u00e0 la s\u00e9lection");
		CSSHelper.setButtonStyle(return_menu, 220, 30);
		CSSHelper.setButtonOnHover(this.sceneInterface, return_menu, 220, 30);

		return_menu.setOnAction(event1 -> {
			c.change_scene(5);
			this.modal.close();
		});

		Button exit = new Button("Quitter");
		CSSHelper.setButtonStyle(exit, 100, 30);
		CSSHelper.setButtonOnHover(this.sceneInterface, exit, 100, 30);

		exit.setOnAction(event -> {
			Main.stopProgram();
		});

		top.getChildren().add(congratFlow);
		bottom.getChildren().addAll(return_menu, exit);
		
		root.add(top, 0, 0);
		root.add(center, 0, 1);
		root.add(bottom, 0, 2);
		
		this.modal.setScene(this.sceneInterface);
	}

	public void setPlayerScore(int playerMoveCount, boolean isNewHighscore) {	
		this.congratLabel.setText(String.valueOf(playerMoveCount));
		if(isNewHighscore) this.congratHighLabel.setText("Nouveau record !");
	}
	
	public void show() {
		this.modal.show();
	}

	@Override
	public void refreshScene(Coordinator c) { }
}
