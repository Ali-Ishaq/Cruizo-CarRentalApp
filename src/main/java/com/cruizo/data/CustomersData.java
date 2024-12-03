/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cruizo.data;

import javafx.collections.ObservableList;
import com.cruizo.models.User;
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

    // Declare your data here, such as User data or any other global data
    private ObservableList<User> users;
    
    // Private constructor to prevent instantiation from outside
    private CustomersData() {
        // Initialize your data
        users = FXCollections.observableArrayList(
                 new User("Ali","Ishaq",19),
                new User("Muneeb","Ahsen",19),
                new User("Abdul","Hadi",19),
                new User("Ayan","Humayon",19)
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
    public ObservableList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    
}
