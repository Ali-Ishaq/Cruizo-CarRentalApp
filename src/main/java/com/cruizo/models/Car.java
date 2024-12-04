/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cruizo.models;

/**
 *
 * @author ALI
 */
public class Car {
    String make;
    String model;
    String category;
    String registrationNumber;
    Double pricePerDay;

    public Car(String make, String model, String category, String registrationNumber, Double pricePerDay) {
        this.make = make;
        this.model = model;
        this.category = category;
        this.registrationNumber = registrationNumber;
        this.pricePerDay = pricePerDay;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getCategory() {
        return category;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }
    
    
}
