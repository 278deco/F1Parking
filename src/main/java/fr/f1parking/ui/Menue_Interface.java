package fr.f1parking.ui;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.io.File;

import static java.lang.Long.MAX_VALUE;

public class Menue_Interface {

    private Button exit;


    private Scene scene_menue;

    private Animation animation;

    private int animation_duration;

    private String color;

    private int position;

    public Menue_Interface (final Coordinator c){

        animation = new Animation(c.getCarManager().getCar_list());

        GridPane root_menue = new GridPane(); //contains column used to divide
        GridPane center_gridpane = new GridPane(); // contains buttons center pos
        root_menue.setStyle("-fx-background-color: #333333ff");


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
        File left_background_file = new File("src/resources/img/left_brackground.png");
        Image right_Img = new Image(left_background_file.toURI().toString(),c.getWIDTH()/3, c.getHEIGHT(), false, true);
        BackgroundImage left_bImg = new BackgroundImage(right_Img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background right_bground = new Background(left_bImg);
        FlowPane left_container = new FlowPane();
        left_container.setBackground(right_bground);
        left_container.setPrefSize(c.getWIDTH()/3, c.getHEIGHT());
        root_menue.add(left_container, 0,0);





        // right column
        ColumnConstraints right_transition = new ColumnConstraints(); //right transition in the menue
        right_transition.setPrefWidth(c.getWIDTH()/3);
        FlowPane right_container = new FlowPane();
        right_container.setPrefSize(c.getWIDTH()/3, c.getHEIGHT());
        File right_background_file = new File("src/resources/img/rect31.png");
        Image right_img = new Image(right_background_file.toURI().toString(),c.getWIDTH()/3, c.getHEIGHT(), false, true);
        BackgroundImage right_bImg = new BackgroundImage(right_img,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bGround = new Background(right_bImg);
        right_container.setBackground(bGround);
        root_menue.add(right_container, 2, 0);




        // front column
        ColumnConstraints front_menu = new ColumnConstraints();//Vbox used to place buttons
        front_menu.setPrefWidth(c.getWIDTH()/3);
        FlowPane top_center = new FlowPane(); //used to stack button start game
        top_center.setAlignment(Pos.CENTER);
        top_center.setPadding(new Insets(20,20,20,20));
        top_center.setPrefHeight(c.getHEIGHT()/3);
        Button start_game = new Button("start");
        button_style(start_game);
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
        button_style(highscore);
        btnHover(highscore);
        highscore.setOnAction(event -> {
            c.change_scene(3);
        });
        center.getChildren().add(highscore);


        FlowPane bottom_center = new FlowPane();
        bottom_center.setAlignment(Pos.CENTER);
        bottom_center.setPadding(new Insets(20,20,20,20));
        bottom_center.setPrefHeight(c.getHEIGHT()/3);


        Button exit = new Button("exit");
        button_style(exit);

        exit.setOnAction(event -> {
            Platform.exit();
        });
        btnHover(exit);

        //add flowpane/button to middle
        center_gridpane.add(top_center, 1, 0);
        center_gridpane.add(center,1, 1);
        center_gridpane.add(bottom_center, 1,2);


        animation.randomizeImage();


        // transition and flowpane inside
        TranslateTransition menue_translatetransition = new TranslateTransition(Duration.seconds(1), animation.getAnimationView());
        menue_translatetransition.setFromY(750);
        menue_translatetransition.setToY(-500);
        menue_translatetransition.setInterpolator(Interpolator.LINEAR);
        menue_translatetransition.setOnFinished(event -> {
            animation.randomizeImage();
            menue_translatetransition.setDuration(Duration.seconds(animation.getCar_speed()));
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
                btn.setStyle("-fx-background-color: #424040; -fx-text-fill: white; -fx-font-size: 20px;-fx-background-radius: 15px");
                btn.setEffect(new javafx.scene.effect.DropShadow(10, Color.web("#000000")));
                scene_menue.setCursor(javafx.scene.Cursor.HAND);
            } else {
                btn.setStyle(null);
                btn.setEffect(null);
                btn.setStyle("-fx-background-color: #B2BABB ;-fx-background-radius: 15px");
                scene_menue.setCursor(javafx.scene.Cursor.DEFAULT);
            }});
    }

    public Button button_style (Button bouton){
        bouton.setPrefSize(150, 60);
        bouton.setAlignment(Pos.CENTER);
        bouton.setStyle("-fx-background-color: #B2BABB ;-fx-background-radius: 15px");

        return bouton;
    }

   /*
    public int car_position (int position){
        if (animation.getCar_position() > 10){
            position = 1;
        }else{
            position = 2;
        }
        return position;

    }

    */




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





