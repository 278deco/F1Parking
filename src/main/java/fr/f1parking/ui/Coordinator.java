package fr.f1parking.ui;
import java.io.File;

import fr.f1parking.utiles.Cars;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Coordinator extends Application {

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    //application dimension
    private int WIDTH = 1280;
    private int HEIGHT = 720;

    private Game_Interface game_interface;

    private MenuInterface menue_interface;

    private Hightscore_Interface hightscore_interface;

    private Intro_Interface intro_interface;

    private Animation animation;

    private Stage primaryStage;

    private Cars carsList;


    private int scene_indicator;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        carsList = new Cars("src/resources/img/");

        menue_interface = new MenuInterface(this);
        game_interface = new Game_Interface(this);
        hightscore_interface = new Hightscore_Interface(this);
        intro_interface = new Intro_Interface(this);


        File icon_file = new File("src/resources/img/Park_The_F1.png");
        Image icon = new Image(icon_file.toURI().toString(),0.2*WIDTH,0.2*HEIGHT,false,true);
        primaryStage.getIcons().add(icon);


        primaryStage.setTitle("Unpark the F1");
        primaryStage.setScene(intro_interface.getIntro_scene());
        primaryStage.setResizable(false);
        primaryStage.show();

    }
    public void change_scene(int i){
        scene_indicator = i;
        switch (i){
            case 1:
                primaryStage.setScene(game_interface.getGame_scene());
                break;
            case 2:
                primaryStage.setScene(menue_interface.getScene_menue());
                break;
            case 3:
                primaryStage.setScene(hightscore_interface.getHightscore_scene());
                break;
            case 4:
                primaryStage.setScene(intro_interface.getIntro_scene());
        }

    }

    public Cars getCarManager() {
        return carsList;
    }
    public int getScene_indicator(){ return scene_indicator;}
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
