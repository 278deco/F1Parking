package fr.f1parking.ui.interfaces;

import java.io.File;

import fr.f1parking.Main;
import fr.f1parking.ui.Coordinator;
import fr.f1parking.ui.animation.Animation;
import fr.f1parking.ui.helpers.CSSHelper;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.util.Duration;

public class MenuInterface implements IInterface {

	private Button exit;

	private Scene scene_menue;

	private Animation leftAnimation, rightAnimation;

	public MenuInterface(final Coordinator c) {

		leftAnimation = new Animation();
		rightAnimation = new Animation();

		GridPane root_menue = new GridPane(); // contains column used to divide
		this.scene_menue = new Scene(root_menue, c.getWIDTH(), c.getHEIGHT()); //Create the scene for the menu with root
		
		GridPane center_gridpane = new GridPane(); // contains buttons center pos
		root_menue.setStyle("-fx-background-color: #333333ff");

		// middle row used in center_gridpane
		RowConstraints top_logo = new RowConstraints();
		top_logo.setPercentHeight(22);
		RowConstraints top = new RowConstraints();
		top.setPercentHeight(22);
		RowConstraints middle = new RowConstraints();
		middle.setPercentHeight(22);
		RowConstraints bottom = new RowConstraints();
		bottom.setPercentHeight(22);

		// left column
		ColumnConstraints left_transition = new ColumnConstraints();// left transition in the menu
		left_transition.setPrefWidth(c.getWIDTH() / 3);
		File left_background_file = new File("datas/img/left_bg.png");
		Image right_Img = new Image(left_background_file.toURI().toString(), c.getWIDTH() / 3, c.getHEIGHT(), false,
				true);
		BackgroundImage left_bImg = new BackgroundImage(right_Img, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background right_bground = new Background(left_bImg);
		FlowPane left_container = new FlowPane();
		left_container.setBackground(right_bground);
		left_container.setPrefSize(c.getWIDTH() / 3, c.getHEIGHT());
		root_menue.add(left_container, 0, 0);

		// right column
		ColumnConstraints right_transition = new ColumnConstraints(); // right transition in the menu
		right_transition.setPrefWidth(c.getWIDTH() / 3);
		FlowPane right_container = new FlowPane();

		File right_background_file = new File("datas/img/right_bg.png");
		Image right_img = new Image(right_background_file.toURI().toString(), c.getWIDTH() / 3, c.getHEIGHT(), false,
				true);
		BackgroundImage right_bImg = new BackgroundImage(right_img, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background bGround = new Background(right_bImg);
		right_container.setBackground(bGround);
		right_container.setPrefSize(c.getWIDTH() / 3, c.getHEIGHT());
		root_menue.add(right_container, 2, 0);

		ColumnConstraints front_menu = new ColumnConstraints();
		front_menu.setPrefWidth(c.getWIDTH() / 3);

		/*
		 * CENTER COLUMN (Logo + Buttons)
		 */
		
		FlowPane top_logo_center =  new FlowPane();
		top_logo_center.setAlignment(Pos.CENTER);
		top_logo_center.setPrefHeight(c.getHEIGHT() / 4);
		top_logo_center.setPadding(new Insets(5, 0, 0, 0));
		
		top_logo_center.getChildren().add(new ImageView(new Image(c.getLogoFile().toURI().toString(), 180, 180, false, true)));

		FlowPane top_button_center = createCenterFlowPane(c.getHEIGHT());
		
		Button start_game = new Button("Lancer le jeu");
		CSSHelper.setButtonStyle(start_game, 200, 60);
		CSSHelper.setButtonOnHover(this.scene_menue, start_game, 200, 60);
		start_game.setOnAction(event -> {
			c.change_scene(1);
		});

		top_button_center.getChildren().add(start_game);

		FlowPane center = createCenterFlowPane(c.getHEIGHT());

		Button highscore = new Button("Tableau des scores");
		CSSHelper.setButtonStyle(highscore, 200, 60);
		CSSHelper.setButtonOnHover(this.scene_menue, highscore, 200, 60);
		
		highscore.setOnAction(event -> {
			c.change_scene(3);
		});
		center.getChildren().add(highscore);

		FlowPane bottom_center = createCenterFlowPane(c.getHEIGHT());
		
		Button exit = new Button("Quitter");
		CSSHelper.setButtonStyle(exit, 200, 60);
		CSSHelper.setButtonOnHover(this.scene_menue, exit, 200, 60);

		exit.setOnAction(event -> {
			Main.stopProgram();
		});
		
		bottom_center.getChildren().add(exit);

		// add flowpane/button to middle
		center_gridpane.add(top_logo_center,1,0);
		center_gridpane.add(top_button_center, 1, 1);
		center_gridpane.add(center, 1, 2);
		center_gridpane.add(bottom_center, 1, 3);

		leftAnimation.randomizeImage();
		rightAnimation.randomizeImage();

		// transition and flowpane inside
		TranslateTransition translateTransitionLeft = createNewAnimation(leftAnimation, 750, -500);

		FlowPane leftTransitionContainer = new FlowPane(leftAnimation.getAnimationView());
		leftTransitionContainer.setAlignment(Pos.TOP_LEFT);
		leftTransitionContainer.setAlignment(Pos.CENTER);

		left_container.getChildren().add(leftTransitionContainer);

		TranslateTransition translateTransitionRight = createNewAnimation(rightAnimation, 750, -500);

		FlowPane rightTransitionContainer = new FlowPane(rightAnimation.getAnimationView());
		rightTransitionContainer.setAlignment(Pos.TOP_LEFT);
		rightTransitionContainer.setAlignment(Pos.CENTER);
		right_container.getChildren().add(rightTransitionContainer);

		root_menue.add(center_gridpane, 1, 0);
		center_gridpane.getRowConstraints().addAll(top_logo, top, middle, bottom);
		root_menue.getColumnConstraints().addAll(left_transition, front_menu, right_transition);

		translateTransitionLeft.play();
		translateTransitionRight.play();
	}

	public FlowPane createCenterFlowPane(int height) {
		final FlowPane ret = new FlowPane();
		ret.setAlignment(Pos.CENTER);
		ret.setPrefHeight(height / 4);
		
		return ret;
	}
	
	public TranslateTransition createNewAnimation(Animation animClass, int y0, int y1) {
		final TranslateTransition t = new TranslateTransition(Duration.seconds(1), animClass.getAnimationView());
		t.setFromY(y0);
		t.setToY(y1);
		t.setInterpolator(Interpolator.LINEAR);
		t.setOnFinished(event -> {
			animClass.randomizeImage();
			t.setDuration(Duration.seconds(animClass.getCar_speed()));
			t.play();
		});

		return t;
	}

	public Button getExit() {
		return exit;
	}

	public void setExit(Button exit) {
		this.exit = exit;
	}

	@Override
	public Scene getInterface() {
		return this.scene_menue;
	}
}
