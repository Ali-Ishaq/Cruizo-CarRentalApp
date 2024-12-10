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

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
//        scene = new Scene(loadFXML("AuthPage"), 640, 480);
           scene = new Scene(loadFXML("AuthPage"),1080,576);
//             stage.setResizable(false);
        Image icon = new Image(getClass().getResource("Cruizo.png").toExternalForm());
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.setTitle("Cruizo Car Rental App"); // Title set
        
         // Handle close request to show confirmation alert
        stage.setOnCloseRequest(event -> {
            event.consume(); // Prevent default close operation
            showConfirmationAlert(stage);
        });
        
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
//        scene.setRoot(loadFXML(fxml)); 
//(ALI bhai ye mainay comment out kiya kiunke Homescreen ka size Authpage se inherit horha tha ab sahi chalrha)

    Parent root = loadFXML(fxml);
    scene.setRoot(root);
    
    // Get the stage and dynamically resize it based on the root's size
    Stage stage = (Stage) scene.getWindow();
    stage.sizeToScene();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
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
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage.close(); // Close the application if OK is clicked
        }
    }
    // Universal Method for displaying error on other pages
    public static void showError(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

}