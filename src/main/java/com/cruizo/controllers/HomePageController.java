/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.cruizo.controllers;

import com.cruizo.App;
import java.io.IOException;
import javafx.fxml.FXMLLoader;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
}
