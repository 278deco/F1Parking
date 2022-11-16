package fr.f1parking.ui;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.File;

public class Menue_Interface {

    private Button exit;


    private Scene scene_menue;

    private Animation animation;

    private int animation_duration;

    public Menue_Interface (final Coordinator c){

        animation = new Animation(c.getCarManager().getCar_list());

        GridPane root_menue = new GridPane(); //contains column used to divide
        GridPane center_gridpane = new GridPane(); // contains buttons center pos


        //middle row used in center_gridpane
        RowConstraints top = new RowConstraints();
        top.setPercentHeight(30);
        RowConstraints middle = new RowConstraints();
        middle.setPercentHeight(30);
        RowConstraints bottom = new RowConstraints();
        bottom.setPercentHeight(30);



        //left column
        ColumnConstraints left_transition = new ColumnConstraints();//left transition in the menu
        left_transition.setPrefWidth(c.getWIDTH()/3);

        FlowPane left_container = new FlowPane();
        left_container.setPrefSize(c.getWIDTH()/3, c.getHEIGHT());

        left_container.setStyle("-fx-background-color: #79443b");
        root_menue.add(left_container, 0,0);





        // right column
        ColumnConstraints right_transition = new ColumnConstraints(); //right transition in the menue
        right_transition.setPrefWidth(c.getWIDTH()/3);
        FlowPane right_container = new FlowPane();
        right_container.setPrefSize(c.getWIDTH()/3, c.getHEIGHT());


        right_container.setStyle("-fx-background-color: #003a9e");
        root_menue.add(right_container, 2, 0);




        // front column
        ColumnConstraints front_menu = new ColumnConstraints();//Vbox used to place buttons
        front_menu.setPrefWidth(c.getWIDTH()/3);


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
        //exit.setStyle("-fx-background-color:#003a9e; -fx-text-fill :white; -fx-font-size : 20px");
        exit.setOnAction(event -> {
            Platform.exit();
        });
        btnHover(exit);

        //add flowpane to middle
        center_gridpane.add(top_center, 1, 0);
        center_gridpane.add(center,1, 1);
        center_gridpane.add(bottom_center, 1,2);

       // File file = new File("src/resources/img/palfa_romeo.png");
        //Image image = new Image(file.toURI().toString(), c.getWIDTH(),0.4* c.getHEIGHT(),true,false);
        //animation_imageView = new ImageView(image);
       // animation_imageView.setImage();
        animation.randomizeImage();


        // transition and flowpane inside
        TranslateTransition menue_translatetransition = new TranslateTransition(Duration.seconds(3), animation.getAnimationView());
        menue_translatetransition.setFromY(c.getHEIGHT());
        menue_translatetransition.setToY(-300);
        menue_translatetransition.setInterpolator(Interpolator.LINEAR);
        menue_translatetransition.setOnFinished(event -> {
            animation.randomizeImage();
            menue_translatetransition.play();
        });
        FlowPane imageview_container = new FlowPane(animation.getAnimationView());
        imageview_container.setAlignment(Pos.TOP_LEFT);
        imageview_container.setAlignment(Pos.CENTER);
        right_container.getChildren().add(imageview_container);






        bottom_center.getChildren().add(exit);
        root_menue.add(center_gridpane, 1,0);
        center_gridpane.getRowConstraints().addAll(top, middle, bottom);
        root_menue.getColumnConstraints().addAll(left_transition,front_menu, right_transition);
        menue_translatetransition.play();



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





