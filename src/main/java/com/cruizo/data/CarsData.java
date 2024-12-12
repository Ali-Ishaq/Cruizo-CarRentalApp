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

    private CarsData() {
        cars = FXCollections.observableArrayList(
                new Car("Toyota", "Corolla", "Sedan", "PK637482", 3200.0),
                new Car("Honda", "Civic X", "Sedan", "PS798966", 4500.0),
                new Car("Suzuki", "Alto", "Hatchback", "PS234438", 2500.0),
                new Car("Nissan", "Altima", "Sedan", "PA453729", 5000.0),
                new Car("Hyundai", "Elantra", "Sedan", "PK679123", 3900.0),
                new Car("Kia", "Sportage", "SUV", "PK123489", 6500.0),
                new Car("Mazda", "CX-5", "SUV", "PS987654", 6800.0),
                new Car("BMW", "X5", "SUV", "PB564738", 8000.0),
                new Car("Mercedes", "C-Class", "Sedan", "PB123456", 9300.0),
                new Car("Audi", "A4", "Sedan", "PA876543", 8800.0),
                new Car("Ford", "F-150", "SUV", "PC234567", 5500.0),
                new Car("Chevrolet", "Tahoe", "SUV", "PC876543", 6700.0),
                new Car("Tesla", "Model S", "Sedan", "PT123456", 1200.0),
                new Car("Volkswagen", "Golf", "Hatchback", "PV234567", 4200.0),
                new Car("Lexus", "RX 350", "SUV", "PL987654", 15000.0)
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
