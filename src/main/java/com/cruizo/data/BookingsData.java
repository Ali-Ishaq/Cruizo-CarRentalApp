/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cruizo.data;

import javafx.collections.ObservableList;
import com.cruizo.models.Booking;
import com.cruizo.models.Car;
import com.cruizo.models.Customer;
import java.time.LocalDate;
import java.time.Month;
import javafx.collections.FXCollections;

/**
 *
 * @author ALI
 * 
 */



//Implement Singleton Class for global data management

public class BookingsData {
    
    

    // Declare a private static instance variable
    private static BookingsData instance;

    // Declare your data here, such as Customer data or any other global data
    private ObservableList<Booking> bookings;
    
    // Private constructor to prevent instantiation from outside
    private BookingsData() {
        // Initialize your data
        bookings = FXCollections.observableArrayList(
                new Booking(new Customer("Ali", "Ishaq", "ali@gmail.com", "03345678952", "KR546188"), new Car("Audi", "A4", "Sedan", "GF6768980", 4800.0), LocalDate.of(2024, 12, 11), LocalDate.of(2024, 12, 15),5600.00)
//                new Customer("Muneeb","Ahsen","muneeb@gmail.com","03328920472","KZ255089"),
//                new Customer("Abdul","Hadi","hadi@gmail.com","03324920572","KS235689"),
//                new Customer("Ayan","Humayon","ayan@gmail.com","03324922472","KR236989")
        );

    }

    // Provide a public static method to get the single instance of the class
    public static BookingsData getInstance() {
        if (instance == null) {
            instance = new BookingsData();
        }
        return instance;
    }

    // Methods to access and modify the data
    public ObservableList<Booking> getBookings() {
        return bookings;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
        booking.getCar().setStatus("Available");
    }

    
}
