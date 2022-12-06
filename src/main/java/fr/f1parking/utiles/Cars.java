package fr.f1parking.utiles;

import fr.f1parking.ui.Car;

import java.util.ArrayList;
import java.util.List;

public class Cars {


        private List<Car> car_list;



        public Cars(String path) {
                car_list = new ArrayList<>();
                car_list.add(new Car("palpha_romeo", path+"palpha_romeo.png"));
                car_list.add(new Car(" palpha_tauri", path+"phalpha_tauri.png"));
                car_list.add(new Car(" pAlpine-1", path+"pAlpine-1.png"));
                car_list.add(new Car(" pAlpine-2", path+"pAlpine-2.png"));
                car_list.add(new Car("pAston_Martin", path+"pAston_Martin.png"));
                car_list.add(new Car("pFerrari", path+"pFerrari.png"));
                car_list.add(new Car("phass-22", path+"phass-22.png"));
                car_list.add(new Car("pmc_laren_2022", path+"pmg_laren_2022.png"));
                car_list.add(new Car("pMercedes", path+"pMercedes.png"));
                car_list.add(new Car("predbull", path+"predbull.png"));
                car_list.add(new Car("pWilliams", path+"pWilliams.png"));
        }
        public List<Car> getCar_list() {
                return car_list;
        }




}
