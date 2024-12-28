package com.cruizo.controllers;

import com.cruizo.App;
import com.cruizo.Utilities;
import com.cruizo.data.CarsData;
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
import com.cruizo.models.Car;
import java.util.stream.Collectors;
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

    @FXML
    private TextField searchField;

    ObservableList<Customer> customerList;

    // An ObservableList is a type of list that can be observed for changes. This means it provides a way to track changes made to the list, such as adding, removing, or modifying elements. It is a part of the javafx.collections package and is commonly used in JavaFX applications to allow for automatic UI updates when the data in the list changes.        
//    public static ObservableList<User> CustomersData;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        customerList = FXCollections.observableArrayList(CustomersData.getInstance().getUsers());

        UserTable.setItems(customerList);

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
        Utilities.showAlert(Alert.AlertType.ERROR, "Error", "Error: All fields are required!");  
        return;  
    }  

    // Validate first name and last name
    if (!firstName.matches("[a-zA-Z\\s]+") || !lastName.matches("[a-zA-Z\\s]+")) {  
        Utilities.showAlert(Alert.AlertType.ERROR, "Error", "Error: Names can only contain letters and spaces!");  
        return;  
    }  

    // Validate phone number (Pakistani)
    if (!phoneNumer.matches("03[0-9]{9}")) {  
        Utilities.showAlert(Alert.AlertType.ERROR, "Error", "Error: Phone number must be a valid Pakistani number starting with 03 and 11 digits long!");  
        return;  
    }  

    // Validate email  
    if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {  
        Utilities.showAlert(Alert.AlertType.ERROR, "Error", "Error: Please enter a valid email address!");  
        return;  
    }  

        Customer newCustomer=new Customer(firstName, lastName, email, phoneNumer, licenseNumber);
        CustomersData.getInstance().addUser(newCustomer);
        customerList.add(newCustomer);
        Utilities.showAlert(
    Alert.AlertType.INFORMATION, 
    "New Customer Added", 
    "The New Customer " + firstName + " " + lastName + " has been added in the List: ");
    }

    @FXML
    public void searchCustomers() {
        String searchQuery = searchField.getText();

        customerList.setAll(
                CustomersData.getInstance().getUsers().stream()
                        .filter(x -> x.fullname.toLowerCase().startsWith(searchQuery.toLowerCase()) || x.phoneNumber.startsWith(searchQuery) || x.licenseNumber.toLowerCase().startsWith(searchQuery.toLowerCase()))
                        .collect(Collectors.toList())
        );
    }

    @FXML
    public void removeCustomer() {

        Customer customer = UserTable.getSelectionModel().getSelectedItem();

        if (customer != null) {

            CustomersData.getInstance().removeUser(customer);

            customerList.setAll(
                    customerList.stream()
                            .filter(x -> !(x.getLicenseNumber().equals(customer.getLicenseNumber())))
                            .collect(Collectors.toList())
            );
        } 
        else {

            Utilities.showAlert(Alert.AlertType.ERROR, "Error", "Please select a customer first");

        }
    }

    public void switchToHomepage() throws IOException {
        Utilities.setRoot("HomePage");
    }
}
