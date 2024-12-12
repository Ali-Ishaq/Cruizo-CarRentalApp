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
