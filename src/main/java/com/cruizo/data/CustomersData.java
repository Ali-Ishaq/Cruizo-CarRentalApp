/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cruizo.data;

import javafx.collections.ObservableList;
import com.cruizo.models.Customer;
import javafx.collections.FXCollections;

/**
 *
 * @author ALI
 * 
 */



//Implement Singleton Class for global data management

public class CustomersData {
    
    

    // Declare a private static instance variable
    private static CustomersData instance;

    // Declare your data here, such as Customer data or any other global data
    private ObservableList<Customer> users;
    
    // Private constructor to prevent instantiation from outside
    private CustomersData() {
        // Initialize your data
        users = FXCollections.observableArrayList(
                new Customer("Ali","Ishaq","ali@gmail.com","03324920472","KF235089"),
                new Customer("Muneeb","Ahsen","muneeb@gmail.com","03328920472","KZ255089"),
                new Customer("Abdul","Hadi","hadi@gmail.com","03324920572","KS235689"),
                new Customer("Ayan","Humayon","ayan@gmail.com","03324922472","KR236989")
        );

    }

    // Provide a public static method to get the single instance of the class
    public static CustomersData getInstance() {
        if (instance == null) {
            instance = new CustomersData();
        }
        return instance;
    }

    // Methods to access and modify the data
    public ObservableList<Customer> getUsers() {
        return users;
    }

    public void addUser(Customer user) {
        users.add(user);
    }

    public void removeUser(Customer user) {
        users.remove(user);
    }

    
}
