package com.cruizo.controllers;


import com.cruizo.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import com.cruizo.Utilities;

public class AuthPageController implements Initializable {
 
 
    
 
    @FXML
    private Button loginButton;

    @FXML
    private PasswordField password;
    
    

    @FXML
    private TextField userNameTextField;

    
    
   @FXML
    private void login(ActionEvent event) throws IOException {
    // Get username and password inputs
    String username = userNameTextField.getText().trim();
    String pwd = password.getText().trim();

    // Define in-built accounts
    String[][] accounts = {
        {"Ayan", "password1"},
        {"Ali", "password2"},
        {"Muneeb", "password3"},
        {"Hadi", "password4"}
    };

    // Check for empty fields
    if (username.isEmpty() || pwd.isEmpty()) {
      Utilities.showAlert(Alert.AlertType.WARNING, "Warning", "Please fill in both username and password.");
    
        return;
    }

    // Validate login credentials
    boolean isValid = false;
    for (String[] account : accounts) {
        if (account[0].equals(username) && account[1].equals(pwd)) {
            isValid = true;
            break; 
       }
    }

    // Show appropriate message
    if (isValid) {
       Utilities.showAlert(Alert.AlertType.INFORMATION, "Success", "Login Successful! Welcome, " + username + "!");
//        root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
//         stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//         scene = new Scene(root);
//         stage.setScene(scene);
//         stage.show();

        Utilities.setRoot("HomePage");


         
// Navigate to the next page or perform another action
    } else {
       Utilities.showAlert(Alert.AlertType.ERROR, "Error", "Invalid username or password.");
    }
}



    @Override
    public void initialize(URL url, ResourceBundle rb) {
         loginButton.setDefaultButton(true); // pressing Enter will act as login button action
    }
}
