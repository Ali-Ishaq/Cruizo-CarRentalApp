package com.cruizo.controllers;

import com.cruizo.App;
import javafx.event.ActionEvent;
import com.cruizo.models.Customer;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import com.cruizo.data.CustomersData;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class CustomersController implements Initializable {
    
    public Button Logout;
    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField LastNameTextField;

    @FXML
    private TextField emailTextField;
    
    @FXML
    private TextField phoneNumberTextField;
    
    @FXML
    private TextField licenseNumberTextField;

    @FXML
    private TableView<Customer> UserTable;
    
    @FXML
    private TableColumn<Customer, String> firstNameColumn;

    @FXML
    private TableColumn<Customer, String> lastNameColumn;

    @FXML
    private TableColumn<Customer, String> emailColumn;
    
    @FXML
    private TableColumn<Customer, String> phoneNumberColumn;
    
    @FXML
    private TableColumn<Customer, String> licenseNumberColumn;

    
    // An ObservableList is a type of list that can be observed for changes. This means it provides a way to track changes made to the list, such as adding, removing, or modifying elements. It is a part of the javafx.collections package and is commonly used in JavaFX applications to allow for automatic UI updates when the data in the list changes.        
//    public static ObservableList<User> CustomersData;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // populating Customer data 
//            CustomersData=FXCollections.observableArrayList(
//                new Customer("Ali","Ishaq",19),
//                new Customer("Ali","Ishaq",19),
//                new Customer("Ali","Ishaq",19)
//        );
        UserTable.setItems(CustomersData.getInstance().getUsers());

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("email"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("phoneNumber"));
        licenseNumberColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("licenseNumber"));
    }

    @FXML
    public void addUser(ActionEvent e) {

        String firstName = firstNameTextField.getText();
        String lastName = LastNameTextField.getText();
        String email = emailTextField.getText();
        String phoneNumer = phoneNumberTextField.getText();
        String licenseNumber = licenseNumberTextField.getText();



        // Validate inputs
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phoneNumer.isEmpty() || licenseNumber.isEmpty()) {
            System.out.println("Error: All fields are required!");
            return;
        }


        CustomersData.getInstance().addUser(new Customer(firstName, lastName, email,phoneNumer,licenseNumber ));

    }

    public void switchToHomepage() throws IOException {
        App.setRoot("HomePage");
    }
}
