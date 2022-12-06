package fr.f1parking.ui;


import java.util.List;
import java.util.Random;

import javafx.scene.image.ImageView;


public class Animation {

    private ImageView animation_imageView;


    private int car_speed;
    private List<Car> carList;

    private int car_position;

    public Animation(List<Car> carList) {
        this.animation_imageView = new ImageView();
        this.carList = carList;
    }





    public void randomizeImage() {
        // car randomizer

        Random nr = new Random();
        int idImg = nr.nextInt(carList.size());
        animation_imageView.setImage(carList.get(idImg).getCarImage());

        this.car_speed = nr.nextInt(1, 8);
        this.car_position = nr.nextInt(10, 20);

    }

    public ImageView getAnimationView() {
        return this.animation_imageView;
    }
    public int getCar_speed() {return this.car_speed;}
    public int getCar_position() {return this.car_position;}
}



