package com.cruizo.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChangeGSTController implements Initializable {

    @FXML
    private TextField GSTTextField;

    @FXML
    private Button saveButton;



    private String updatedGST;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        saveButton.setOnAction(event -> {
            updatedGST = GSTTextField.getText();
            closeDialog();
        });

    
    }

    public void setCurrentGSTLabel(String currentGST) {
        GSTTextField.setText(currentGST); // Pre-fill with current GST value
    }

    public String getUpdatedGST() {
        return updatedGST;
    }

    private void closeDialog() {
        Stage stage = (Stage) GSTTextField.getScene().getWindow();
        stage.close();
    }
}
