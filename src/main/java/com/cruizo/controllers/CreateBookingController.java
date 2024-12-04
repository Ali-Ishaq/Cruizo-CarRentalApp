/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cruizo.controllers;

import com.cruizo.App;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import com.cruizo.data.CustomersData;
import com.cruizo.data.CarsData;
import com.cruizo.models.Car;
import com.cruizo.models.Customer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
/**
 *
 * @author ALI
 */
public class CreateBookingController implements Initializable{
   

    @FXML
    private TableView<Car> CarTable;

    @FXML
    private TableView<Customer> CustomerTable;
    
    @FXML
    private TextField CarField;
    
    @FXML
    private TextField CustomerField;
    
      @FXML
    private TableColumn<Car, String> CarModelColumn;

    @FXML
    private TableColumn<Car, String> CarRegistrationColumn;

    @FXML
    private TableColumn<Customer, String> FullNameColumn;

    @FXML
    private TableColumn<Customer, String> LicenseNumberColumn;



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Initializing Customer Table
        CustomerTable.setItems(CustomersData.getInstance().getUsers());
        FullNameColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("firstName"));
        LicenseNumberColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("licenseNumber"));
        
        // Initializing Car Table
        CarTable.setItems(CarsData.getInstance().getCars());
        CarModelColumn.setCellValueFactory(new PropertyValueFactory<Car,String>("model"));
        CarRegistrationColumn.setCellValueFactory(new PropertyValueFactory<Car,String>("registrationNumber"));
    
    }
    
    
    public void switchToHomepage() throws IOException {
        App.setRoot("HomePage");
    }
    
    
    



}
