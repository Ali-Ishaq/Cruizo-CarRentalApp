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
import com.cruizo.Utilities;

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

        Utilities.setRoot("Customers");
    }

    @FXML
    private void switchToCars(ActionEvent event) throws IOException {
        Utilities.setRoot("Cars");
    }

    @FXML
    private void switchToCreateBooking(ActionEvent event) throws IOException {
        Utilities.setRoot("CreateBooking");
    }

    @FXML
    private void switchToExistingBookings(ActionEvent event) throws IOException {
        Utilities.setRoot("ExistingBookings");
    }

    @FXML
    public void switchToAuthPage(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setHeaderText("Are you sure you want to log out?");
        alert.setContentText("Any unsaved progress will be lost.");

        // Show the alert and wait for the user's response
        alert.showAndWait();

        // Check the user's response
        if (alert.getResult() == ButtonType.OK) {
            // User clicked OK, log them out and switch to the AuthPage
            System.out.println("User chose to log out.");
            try {
                Utilities.setRoot("AuthPage"); // Switch to the AuthPage scene
            } catch (IOException ex) {
                ex.printStackTrace(); // Print error to console if the scene switching fails
            }
        } else {
            // User canceled the logout action
            System.out.println("User canceled logout.");
        }

    }
}
