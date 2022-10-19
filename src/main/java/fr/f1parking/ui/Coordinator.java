package fr.f1parking.ui;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;

public class Coordinator extends Application {

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    //application dimension
    private int WIDTH = 600;
    private int HEIGHT = 700;

    private Game_Interface game_interface;

    private Menue_Interface menue_interface;

    private Hightscore_Interface hightscore_interface;

    private Stage primaryStage;


    @Override
    public void start(Stage primaryStage) throws Exception {
        menue_interface = new Menue_Interface(this);
        game_interface = new Game_Interface(this);
        hightscore_interface = new Hightscore_Interface(this);

        File icon_file = new File("src/resources/img/Park_The_F1;png");
        Image icon = new Image(icon_file.toURI().toString(),0.2*WIDTH,0.2*HEIGHT,false,true);
        primaryStage.getIcons().add(icon);


        primaryStage.setTitle("unpark the f1");
        primaryStage.setScene(menue_interface.getScene_menue());
        primaryStage.setResizable(false);
        primaryStage.show();

    }
    public void change_scene(int i){
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
        }

    }
}
