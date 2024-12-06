/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cruizo.models;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author ALI
 */
public class Booking {
    
    private String bookingId;
    private Customer customer;
    private Car car;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double bookingAmount;
    
    public Booking( Customer customer, Car car, LocalDate startDate, LocalDate endDate, Double bookingAmount) {
        this.bookingId = Long.toString(new Date().getTime());
        this.customer = customer;
        this.car = car;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bookingAmount=bookingAmount;
        this.car.status="Rented";
    }

    
    public String getBookingId() {
        return bookingId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Car getCar() {
        return car;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Double getBookingAmount() {
        return bookingAmount;
    }
    
    // getters to get fields of Customer and Car classes
    
    public String getRegistrationNumber() {
        return car.getRegistrationNumber();
    }
    
    public String getModel() {
        return car.getModel();
    }
    
    public String getMake() {
        return car.getMake();
    }
    
    public String getLicenseNumber() {
        return customer.getLicenseNumber();
    }
    
    public String getPhoneNumber() {
        return customer.getPhoneNumber();
    }
    
    public String getFullName() {
        return customer.getFullName();
    }
    
    
    
    
    
    
    
}
