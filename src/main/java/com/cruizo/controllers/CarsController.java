package com.cruizo.controllers;

import com.cruizo.App;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import com.cruizo.models.Car;
import javafx.scene.control.ComboBox;

import com.cruizo.data.CarsData;
import com.cruizo.data.CustomersData;
import com.cruizo.models.Customer;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

public class CarsController implements Initializable {

    @FXML
    private TableView<Car> CarTable;

    @FXML
    private TableColumn<Car, String> categoryColumn;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private TableColumn<Car, String> makeColumn;

    @FXML
    private TextField makeTextField;

    @FXML
    private TableColumn<Car, String> modelColumn;

    @FXML
    private TextField modelTextField;

    @FXML
    private TableColumn<Car, Double> priceColumn;

    @FXML
    private TextField priceTextField;

    @FXML
    private TableColumn<Car, String> registrationColumn;
    
    @FXML
    private TableColumn<Car, String> statusColumn;

    @FXML
    private TextField registrationTextField;

    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Setting Options of Category ComboBox 
        ObservableList<String> options = FXCollections.observableArrayList(
                "Hatchback", "Sedan", "SUV"
        );
        categoryComboBox.setItems(options);

        
        
        CarTable.setItems(CarsData.getInstance().getCars());

        makeColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("make"));
        modelColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("category"));
        registrationColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("registrationNumber"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Car, Double>("pricePerDay"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("status"));

    }

    @FXML
    void addCar(ActionEvent event) {
        
         String make = makeTextField.getText();
        String model = modelTextField.getText();
        String registrationNumber = registrationTextField.getText();
        String priceStr = priceTextField.getText();
        String category = categoryComboBox.getValue();
        Double price;


        // Validate inputs
        if (make.isEmpty() || model.isEmpty() || registrationNumber.isEmpty() || priceStr.isEmpty()|| category== null ){
            App.showError("Error: All fields are required!"); //This will show a dialogue box
            return;
        }
        
        try {
            price=Double.parseDouble(priceStr);   
        } catch (NumberFormatException e) {
            App.showError("Invalid Amount"); //This will show a dialogue box
            return;
        }


        CarsData.getInstance().addCar(new Car(make, model, category, registrationNumber, price));
    }
    
    public void switchToHomepage() throws IOException {
        App.setRoot("HomePage");
    }
    
    
}
