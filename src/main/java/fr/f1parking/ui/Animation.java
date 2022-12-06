package fr.f1parking.ui;

import java.util.Random;

import fr.f1parking.core.io.IOHandler;
import fr.f1parking.ui.render.Texture;
import javafx.scene.image.ImageView;

public class Animation {

    private ImageView animation_imageView;


    private int car_speed;

    private int car_position;

    public Animation() {
        this.animation_imageView = new ImageView();
    }

    public void randomizeImage() {
        // car randomizer

        Random nr = new Random();
        Texture texture = IOHandler.getInstance().getTexturesFile().getRandomCarTexture(nr.nextInt(100000));
        animation_imageView.setImage(texture.copy().loadImage(500, 500));

        this.car_speed = nr.nextInt(1, 8);
        this.car_position = nr.nextInt(10, 20);

    }

    public ImageView getAnimationView() {
        return this.animation_imageView;
    }
    public int getCar_speed() {return this.car_speed;}
    public int getCar_position() {return this.car_position;}
}



