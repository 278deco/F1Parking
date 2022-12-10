package fr.f1parking.ui.interfaces;

import java.io.File;

import fr.f1parking.ui.Coordinator;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;

public class IntroInterface extends AbstractInterface {

	private MediaPlayer mediaplayer;

	public IntroInterface(final Coordinator c) {

		Group root = new Group();

		Media intro_video = new Media(new File("datas/video-files/game_intro.mp4").toURI().toString());
		mediaplayer = new MediaPlayer(intro_video);
		if (c.getScene_indicator() == 4) {
			mediaplayer.play();
		} else {
			mediaplayer.stop();
		}

		mediaplayer.setOnEndOfMedia(() -> {
		//mediaplayer.stop();
			c.change_scene(2);
		});
		
		// mediaplayer.setAutoPlay(true);
		MediaView view = new MediaView(mediaplayer);
		root.setOnMouseClicked(event -> {
			mediaplayer.stop();

			c.change_scene(2);
		});
		
		Text skipText = new Text("Cliquez pour passer l'introduction...");
		skipText.setStyle("-fx-font-size: 9px; -fx-fill: white");
		skipText.setTranslateX(10);
		skipText.setTranslateY(c.getHeight()-10);
		
		root.getChildren().addAll(view, skipText);

		this.sceneInterface = new Scene(root, 1280, 720);

	}
	
	@Override
	public void refreshScene(Coordinator c) {
		mediaplayer.setAutoPlay(true);
	}

}
