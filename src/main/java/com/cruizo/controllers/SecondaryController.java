package com.cruizo.controllers;

import com.cruizo.App;
import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {

    @FXML
    public void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }


}