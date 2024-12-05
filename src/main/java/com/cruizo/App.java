package com.cruizo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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
        stage.setScene(scene);
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

}