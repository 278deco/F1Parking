package fr.f1parking.ui.interfaces;

import fr.f1parking.ui.Coordinator;
import javafx.scene.Scene;

public class HightscoreInterface implements IInterface {


    private Scene highscoreScene;

    public HightscoreInterface(final Coordinator e){




    }
    
    @Override
    public Scene getInterface() {
    	return this.highscoreScene;
    }
}
