/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cruizo;

import java.io.IOException;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import com.cruizo.App;
import javafx.fxml.FXMLLoader;


public class Utilities {

    private Utilities() {
    
    }
    
    public static void showAlert(Alert.AlertType alertType, String title, String message) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
    
    public static void setRoot(String fxml) throws IOException {

    Parent root = loadFXML(fxml);
    App.scene.setRoot(root);
    
    // Get the stage and dynamically resize it based on the root's size
    Stage stage = (Stage) App.scene.getWindow();
//    Match the preferred size of the scene.
    stage.sizeToScene();
    
    }
    
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    
    
    
    
}
