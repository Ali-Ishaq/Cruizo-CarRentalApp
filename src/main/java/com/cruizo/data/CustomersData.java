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
                new Customer("Ali", "Ishaq", "ali@gmail.com", "03324920472", "KF235089"),
                new Customer("Muneeb", "Ahsen", "muneeb@gmail.com", "03328920472", "KZ255089"),
                new Customer("Abdul", "Hadi", "hadi@gmail.com", "03324920572", "KS235689"),
                new Customer("Ayan", "Humayon", "ayan@gmail.com", "03324922472", "KR236989"),
                new Customer("Ayan", "Humayon", "ayan@gmail.com", "03324922472", "KR236989"),
                new Customer("Imran", "Ahmed", "imran.ahmed@example.com", "03351234567", "KR236990"),
                new Customer("Sana", "Ahmed", "sana.ahmed@example.com", "03451234567", "KR236991"),
                new Customer("Rohit", "Sharma", "rohit.sharma@example.com", "03241234567", "KR236992"),
                new Customer("Fatima", "Khan", "fatima.khan@example.com", "03361324567", "KR236993"),
                new Customer("Zaid", "Iqbal", "zaid.iqbal@example.com", "03341234567", "KR236994"),
                new Customer("Kiran", "Choudhury", "kiran.choudhury@example.com", "03351412345", "KR236995"),
                new Customer("Saim", "Ayub", "saim.ayub@example.com", "03461234567", "KR236996"),
                new Customer("Ayesha", "Rehman", "ayesha.rehman@example.com", "03341523456", "KR236997"),
                new Customer("Ankit", "Patel", "ankit.patel@example.com", "03451234567", "KR236998"),
                new Customer("Rashid", "Siddiqui", "rashid.siddiqui@example.com", "03361342123", "KR236999"),
                new Customer("Priya", "Rani", "priya.rani@example.com", "03561234567", "KR237000")
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
