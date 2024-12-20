package com.cruizo;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import com.cruizo.Utilities;

/**
 * JavaFX App
 */

public class App extends Application {

     static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        
        scene = new Scene(Utilities.loadFXML("AuthPage"),1080,576);
        Image icon = new Image(getClass().getResource("Cruizo.png").toExternalForm());
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.setTitle("Cruizo Car Rental App");
        
         // Handle close request to show confirmation alert
         
        stage.setOnCloseRequest(event -> {
            event.consume(); // Prevent default close operation
            showConfirmationAlert(stage);
        });
        
        stage.show();
    }


    

    public static void main(String[] args) {
        launch();
    }
    
    private void showConfirmationAlert(Stage stage) {
        // Create a confirmation alert
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.setContentText("Make sure to save your work before exiting.");

        // Show the alert and wait for a response
        alert.showAndWait();

        if (alert.getResult()== ButtonType.OK) {
            stage.close(); // Close the application if OK is clicked
        }
    }

}