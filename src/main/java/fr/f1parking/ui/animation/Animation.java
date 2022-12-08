package fr.f1parking.ui.animation;

import java.util.Random;

import fr.f1parking.core.io.IOHandler;
import fr.f1parking.ui.render.Texture;
import javafx.scene.image.ImageView;

public class Animation {

	private final Random random;
	
    private ImageView animation_imageView;
    private int car_speed;
    private int car_position;

    public Animation() {
        this.animation_imageView = new ImageView();
        this.random = new Random();
    }

    public void randomizeImage() {
        // car randomizer

        final Texture texture = IOHandler.getInstance().getTexturesFile().getRandomCarTexture(random.nextInt(100000));
        animation_imageView.setImage(texture.loadImage(500, 500));

        this.car_speed = random.nextInt(1, 8);
        this.car_position = random.nextInt(10, 20);

    }

    public ImageView getAnimationView() {
        return this.animation_imageView;
    }
    
    public int getCar_speed() {
    	return this.car_speed;
    }
    
    public int getCar_position() {
    	return this.car_position;
    }
}



