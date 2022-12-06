package fr.f1parking.ui;

import java.io.File;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;


public class Intro_Interface {

    private Scene intro_scene;

    private MediaPlayer mediaplayer;

    private boolean out_intro;

    public Intro_Interface(final Coordinator f){

        Group root = new Group();

        out_intro = false;

        Media intro_video = new Media(new File("src/resources/mp3_files/F1 2020 intro _FLUTE_.mp4").toURI().toString());
        mediaplayer = new MediaPlayer(intro_video);
        if (f.getScene_indicator() == 4){
            mediaplayer.play();
        }else {
            mediaplayer.stop();
        }

       // Scanner keyboard = new Scanner(System.in);





        mediaplayer.setOnEndOfMedia(()->{
            out_intro = true;


        });
        if (out_intro){
            mediaplayer.stop();
            f.change_scene(2);

        }

        mediaplayer.setAutoPlay(true);
        MediaView view = new MediaView(mediaplayer);
        root.setOnMouseClicked(event -> {
            mediaplayer.stop();

            f.change_scene(2);
        });
        root.getChildren().add( view);

        intro_scene = new Scene(root, 1280,720 );


    }
    public Scene getIntro_scene() {
        return intro_scene;
    }

}
