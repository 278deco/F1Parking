package fr.f1parking.ui;

import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


import java.io.File;

public class Menue_Interface {

    private Button exit;


    private Scene scene_menue;

    public Menue_Interface (final Coordinator c){


        HBox root_menue = new HBox(); //contains all the Vbox used in this scene


        //Vbox used for transition


        VBox left_transition = new VBox(); //left transition in the menu
        left_transition.setPrefWidth(c.getWIDTH()/3);
        File test = new File("src/resources/img/pFerrari.png");
        Image test_image = new Image(test.toURI().toString(),0.2*c.getWIDTH(),0.2*c.getHEIGHT(),false,true);
        ImageView test_imageview = new ImageView(test_image);
        Label test_text = new Label("ok");
        PathTransition left_trans = new PathTransition();
        Circle circle = new Circle(100);
    /*left_trans.setNode(test_imageview);
    left_trans.setDuration(Duration.seconds(5));
    left_trans.setPath(circle);
    left_trans.setCycleCount(PathTransition.INDEFINITE);
    left_transition.getChildren().add(left_trans);
    left_trans.play();
     */
        VBox right_transition = new VBox(); //right transition in the menue
        right_transition.setPrefWidth(c.getWIDTH()/3);




        VBox front_menu = new VBox();//Vbox used to place buttons
        front_menu.setPrefWidth(c.getHEIGHT()/3);
        front_menu.setSpacing(50);



        FlowPane top_center = new FlowPane(); //used to stack button start game
        top_center.setAlignment(Pos.CENTER);
        top_center.setPadding(new Insets(20,20,20,20));
        top_center.setPrefHeight(c.getHEIGHT()/3);


        Button start_game = new Button("start");
        start_game.setAlignment(Pos.TOP_CENTER);
        start_game.setPadding(new Insets(30,30,30,30));
        start_game.setOnAction(event ->{
            c.change_scene(1);

        });
        btnHover(start_game);

        top_center.getChildren().add(start_game);


        FlowPane center = new FlowPane();
        center.setAlignment(Pos.CENTER);
        center.setPadding(new Insets(20,20,20,20));
        center.setPrefHeight(c.getHEIGHT()/3);


        Button highscore = new Button("highscore");
        highscore.setAlignment(Pos.CENTER);
        highscore.setPadding(new Insets(30,30,30,30));
        center.getChildren().add(highscore);
        btnHover(highscore);


        FlowPane bottom_center = new FlowPane();
        bottom_center.setAlignment(Pos.CENTER);
        bottom_center.setPadding(new Insets(20,20,20,20));
        bottom_center.setPrefHeight(c.getHEIGHT()/3);


        Button exit = new Button("exit");
        exit.setAlignment(Pos.BOTTOM_CENTER);
        exit.setPadding(new Insets(30,30,30,30));
        exit.setOnAction(event -> {
            Platform.exit();
        });
        btnHover(exit);




        bottom_center.getChildren().add(exit);
        front_menu.getChildren().addAll(top_center, center, bottom_center);
        root_menue.getChildren().addAll(left_transition, front_menu, right_transition);
        scene_menue = new Scene(root_menue,c.getWIDTH(), c.getHEIGHT());
    }
    public void btnHover(Button btn) {
        btn.hoverProperty( ).addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                btn.setStyle("-fx-background-color: #003a9e; -fx-text-fill: white; -fx-font-size: 20px;");
                btn.setEffect(new javafx.scene.effect.DropShadow(10, Color.web("#000000")));
                scene_menue.setCursor(javafx.scene.Cursor.HAND);
            } else {
                btn.setStyle(null);
                btn.setEffect(null);
                scene_menue.setCursor(javafx.scene.Cursor.DEFAULT);
            }});
    }



    public Button getExit() {
        return exit;
    }

    public void setExit(Button exit) {
        this.exit = exit;
    }
    public Scene getScene_menue() {
        return scene_menue;
    }
}





