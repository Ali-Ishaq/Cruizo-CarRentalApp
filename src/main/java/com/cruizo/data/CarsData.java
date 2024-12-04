/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cruizo.data;

import javafx.collections.ObservableList;
import com.cruizo.models.Car;
import javafx.collections.FXCollections;

/**
 *
 * @author ALI
 */
public class CarsData {
    
    private static CarsData instance;
    private ObservableList<Car> cars;
    
    private CarsData(){
        cars=FXCollections.observableArrayList(
            new Car("Toyota", "Corolla", "Sedan", "PK637482", 1800.0),
            new Car("Honda", "Civic X", "Sedan", "PS798966", 1900.0),
            new Car("Suzuki", "Alto", "Hatchback", "PS234438", 1200.0)
        
        );
    }
    
    public static CarsData getInstance() {
        if (instance == null) {
            instance = new CarsData();
        }
        return instance;
    }
    
     // Methods to access and modify the data
    public ObservableList<Car> getCars() {
        return cars;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void removeCar(Car car) {
        cars.remove(car);
    }
    
    
}
