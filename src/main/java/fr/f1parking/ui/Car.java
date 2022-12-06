package fr.f1parking.ui;

import java.io.File;

import javafx.scene.image.Image;

public class Car {


    private String car_name;

    private String path;

    private Image image;

    public Car(String car_name, String path) {
        this.car_name = car_name;
        this.path = path;

        this.image = new Image(new File(getPath()).toURI().toString(), 500, 500, true, false);
    }

    public Image getCarImage() {
        return this.image;
    }

    public String getCar_name() {
        return car_name;
    }

    public String getPath() {
        return path;
    }


}
