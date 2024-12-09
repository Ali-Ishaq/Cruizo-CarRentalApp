/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.cruizo.controllers;

import com.cruizo.App;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author abdul
 */
public class HomePageController implements Initializable {
    private Label labelTest;
    @FXML 
    private Button CustomerButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    private void switchToCustomers(ActionEvent event) throws IOException {
        
        App.setRoot("Customers"); 
    }
    
    @FXML
    private void switchToCars(ActionEvent event) throws IOException {
        App.setRoot("Cars"); 
    }
    @FXML
    private void switchToCreateBooking(ActionEvent event) throws IOException {
        App.setRoot("CreateBooking"); 
    }
    
    @FXML
    private void switchToExistingBookings(ActionEvent event) throws IOException {
        App.setRoot("ExistingBookings"); 
    }
    
    @FXML
     public void switchToAuthPage(ActionEvent event) throws IOException{
        Alert a1 = new Alert(Alert.AlertType.CONFIRMATION);
        a1.setTitle("Logout Confirmation");
        a1.setHeaderText("Are you sure you want to log out?");
        a1.setContentText("Any unsaved progress will be lost.");
        
            a1.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                System.out.println("User chose to log out.");
               
                try {
                    App.setRoot("AuthPage");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                System.out.println("User canceled logout.");
            }
        });
//        App.setRoot("AuthPage"); 
    }
}
