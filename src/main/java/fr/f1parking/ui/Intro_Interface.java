package fr.f1parking.ui;

import javafx.scene.Group;
import javafx.scene.Scene;

import java.io.File;
import java.util.Scanner;

import javafx.scene.layout.HBox;
import javafx.scene.media.*;


public class Intro_Interface {

    private Scene intro_scene;

    private MediaPlayer mediaplayer;

    private boolean out_intro;

    public Intro_Interface(final Coordinator f){

        Group root = new Group();

        out_intro = false;

        Media intro_video = new Media(new File("src/resources/mp3_files/F1 2020 intro _FLUTE_.mp4").toURI().toString());
        mediaplayer = new MediaPlayer(intro_video);
        mediaplayer.play();
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
