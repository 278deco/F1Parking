package fr.f1parking.ui.interfaces;

import java.io.File;

import fr.f1parking.Main;
import fr.f1parking.ui.Coordinator;
import fr.f1parking.ui.animation.Animation;
import fr.f1parking.ui.helpers.CSSHelper;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
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

public class MenuInterface extends AbstractInterface {
	
	private static final int BUTTON_NUMBER = 5;
	
	private Button exit;

	private Animation leftAnimation, rightAnimation;

	public MenuInterface(final Coordinator c) {

		leftAnimation = new Animation();
		rightAnimation = new Animation();

		GridPane root_menue = new GridPane(); // contains column used to divide
		this.sceneInterface = new Scene(root_menue, c.getWidth(), c.getHeight()); //Create the scene for the menu with root
		
		GridPane center_gridpane = new GridPane(); // contains buttons center pos
		root_menue.setStyle("-fx-background-color: #333333ff");

		// middle row used in center_gridpane
		RowConstraints logoRowCons = new RowConstraints();
		logoRowCons.setPercentHeight(12);
		RowConstraints gameButtonCons = new RowConstraints();
		gameButtonCons.setPercentHeight(9);
		RowConstraints highscoreButtonCons = new RowConstraints();
		highscoreButtonCons.setPercentHeight(9);
		RowConstraints paramButtonCons = new RowConstraints();
		paramButtonCons.setPercentHeight(9);
		RowConstraints exitButtonCons = new RowConstraints();
		exitButtonCons.setPercentHeight(9);

		// left column
		ColumnConstraints left_transition = new ColumnConstraints();// left transition in the menu
		left_transition.setPrefWidth(c.getWidth() / 3);
		File left_background_file = new File("datas/img/left_bg.png");
		Image right_Img = new Image(left_background_file.toURI().toString(), c.getWidth() / 3, c.getHeight(), false,
				true);
		BackgroundImage left_bImg = new BackgroundImage(right_Img, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background right_bground = new Background(left_bImg);
		FlowPane left_container = new FlowPane();
		left_container.setBackground(right_bground);
		left_container.setPrefSize(c.getWidth() / 3, c.getHeight());
		root_menue.add(left_container, 0, 0);

		// right column
		ColumnConstraints right_transition = new ColumnConstraints(); // right transition in the menu
		right_transition.setPrefWidth(c.getWidth() / 3);
		FlowPane right_container = new FlowPane();

		File right_background_file = new File("datas/img/right_bg.png");
		Image right_img = new Image(right_background_file.toURI().toString(), c.getWidth() / 3, c.getHeight(), false,
				true);
		BackgroundImage right_bImg = new BackgroundImage(right_img, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background bGround = new Background(right_bImg);
		right_container.setBackground(bGround);
		right_container.setPrefSize(c.getWidth() / 3, c.getHeight());
		root_menue.add(right_container, 2, 0);

		ColumnConstraints front_menu = new ColumnConstraints();
		front_menu.setPrefWidth(c.getWidth() / 3);

		/*
		 * CENTER COLUMN (Logo + Buttons)
		 */
		
		FlowPane top_logo_center =  new FlowPane();
		top_logo_center.setAlignment(Pos.CENTER);
		top_logo_center.setPrefHeight(c.getHeight() / BUTTON_NUMBER);
		//top_logo_center.setPadding(new Insets(5, 0, 0, 0));
		
		top_logo_center.getChildren().add(new ImageView(new Image(c.getLogoFile().toURI().toString(), 180, 180, false, true)));

		FlowPane gameButtonPane = createCenterFlowPane(c.getHeight());
		
		Button start_game = new Button("Lancer le jeu");
		CSSHelper.setButtonStyle(start_game, 200, 60);
		CSSHelper.setButtonOnHover(this.sceneInterface, start_game, 200, 60);
		start_game.setOnAction(event -> {
			c.change_scene(5);
		});

		gameButtonPane.getChildren().add(start_game);

		FlowPane highscoreButtonPane = createCenterFlowPane(c.getHeight());

		Button highscore = new Button("Tableau des scores");
		CSSHelper.setButtonStyle(highscore, 200, 60);
		CSSHelper.setButtonOnHover(this.sceneInterface, highscore, 200, 60);
		
		highscore.setOnAction(event -> {
			c.change_scene(3);
		});
		highscoreButtonPane.getChildren().add(highscore);

		FlowPane parameterButtonPane = createCenterFlowPane(c.getHeight());

		Button parameter = new Button("Param\u00e8tres");
		CSSHelper.setButtonStyle(parameter, 200, 60);
		CSSHelper.setButtonOnHover(this.sceneInterface, parameter, 200, 60);
		
		parameter.setOnAction(event -> {
			c.change_scene(6);
		});
		parameterButtonPane.getChildren().add(parameter);
		
		FlowPane exitButtonPane = createCenterFlowPane(c.getHeight());
		
		Button exit = new Button("Quitter");
		CSSHelper.setButtonStyle(exit, 200, 60);
		CSSHelper.setButtonOnHover(this.sceneInterface, exit, 200, 60);

		exit.setOnAction(event -> {
			Main.stopProgram();
		});
		
		exitButtonPane.getChildren().add(exit);

		// add flowpane/button to middle
		center_gridpane.add(top_logo_center,1,0);
		center_gridpane.add(gameButtonPane, 1, 1);
		center_gridpane.add(highscoreButtonPane, 1, 2);
		center_gridpane.add(parameterButtonPane, 1, 3);
		center_gridpane.add(exitButtonPane, 1, 4);

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
		center_gridpane.getRowConstraints().addAll(logoRowCons, gameButtonCons, highscoreButtonCons, paramButtonCons, exitButtonCons);
		
		root_menue.getColumnConstraints().addAll(left_transition, front_menu, right_transition);

		translateTransitionLeft.play();
		translateTransitionRight.play();
	}

	public FlowPane createCenterFlowPane(int height) {
		final FlowPane ret = new FlowPane();
		ret.setAlignment(Pos.CENTER);
		ret.setMaxHeight(height / BUTTON_NUMBER);
		
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
	public void refreshScene(Coordinator c) { }
}
