package fr.f1parking.ui.interfaces;

import java.io.File;

import fr.f1parking.ui.Coordinator;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class IntroInterface implements IInterface {

	private Scene intro_scene;

	private MediaPlayer mediaplayer;

	private boolean out_intro;

	public IntroInterface(final Coordinator f) {

		Group root = new Group();

		out_intro = false;

		Media intro_video = new Media(new File("datas/video-files/game_intro.mp4").toURI().toString());
		mediaplayer = new MediaPlayer(intro_video);
		if (f.getScene_indicator() == 4) {
			mediaplayer.play();
		} else {
			mediaplayer.stop();
		}

		mediaplayer.setOnEndOfMedia(() -> {
			out_intro = true;
		//mediaplayer.stop();
			f.change_scene(2);
		});
		if (out_intro) {
			mediaplayer.stop();
			f.change_scene(2);

		}

		// mediaplayer.setAutoPlay(true);
		MediaView view = new MediaView(mediaplayer);
		root.setOnMouseClicked(event -> {
			mediaplayer.stop();

			f.change_scene(2);
		});
		root.getChildren().add(view);

		this.intro_scene = new Scene(root, 1280, 720);

	}

	@Override
	public Scene getInterface() {
		mediaplayer.setAutoPlay(true);

		return this.intro_scene;
	}

}
