package fr.f1parking.ui;
import java.io.File;

import fr.f1parking.ui.interfaces.GameInterface;
import fr.f1parking.ui.interfaces.HightscoreInterface;
import fr.f1parking.ui.interfaces.IntroInterface;
import fr.f1parking.ui.interfaces.MenuInterface;
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

    private GameInterface game_interface;

    private MenuInterface menue_interface;

    private HightscoreInterface hightscore_interface;

    private IntroInterface intro_interface;
    
    private Stage primaryStage;

    private int scene_indicator;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        
        menue_interface = new MenuInterface(this);
        game_interface = new GameInterface(this);
        hightscore_interface = new HightscoreInterface(this);
        intro_interface = new IntroInterface(this);


        File icon_file = new File("datas/img/logo.png");
        Image icon = new Image(icon_file.toURI().toString(),0.2*WIDTH,0.2*HEIGHT,false,true);
        primaryStage.getIcons().add(icon);


        primaryStage.setTitle("Unpark the F1");
        primaryStage.setScene(menue_interface.getInterface());
        primaryStage.setResizable(false);
        primaryStage.show();

    }
    public void change_scene(int i){
        scene_indicator = i;
        switch (i){
            case 1:
                primaryStage.setScene(game_interface.getInterface());
                break;
            case 2:
                primaryStage.setScene(menue_interface.getInterface());
                break;
            case 3:
                primaryStage.setScene(hightscore_interface.getInterface());
                break;
            case 4:
                primaryStage.setScene(intro_interface.getInterface());
        }

    }

    public int getScene_indicator(){ 
    	return scene_indicator;
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
