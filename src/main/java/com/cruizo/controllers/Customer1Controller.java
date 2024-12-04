package com.cruizo.controllers;

import com.cruizo.App;
import javafx.event.ActionEvent;
import com.cruizo.models.User;
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

public class Customer1Controller implements Initializable {
    
    @FXML
    private Button Logout;
    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField LastNameTextField;

    @FXML
    private TextField ageTextField;

    @FXML
    private TableView<User> UserTable;
    @FXML
    private TableColumn<User, String> firstNameColumn;

    @FXML
    private TableColumn<User, String> lastNameColumn;

    @FXML
    private TableColumn<User, Integer> ageColumn;
    
    // An ObservableList is a type of list that can be observed for changes. This means it provides a way to track changes made to the list, such as adding, removing, or modifying elements. It is a part of the javafx.collections package and is commonly used in JavaFX applications to allow for automatic UI updates when the data in the list changes.        
//    public static ObservableList<User> CustomersData;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // populating User data 
//            CustomersData=FXCollections.observableArrayList(
//                new User("Ali","Ishaq",19),
//                new User("Ali","Ishaq",19),
//                new User("Ali","Ishaq",19)
//        );
        UserTable.setItems(CustomersData.getInstance().getUsers());

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("FirstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("LastName"));
        ageColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("Age"));
    }

    public void addUser(ActionEvent e) {

        String firstName = firstNameTextField.getText();
        String lastName = LastNameTextField.getText();
        String ageText = ageTextField.getText();
        Integer age;

        // Validate inputs
        if (firstName.isEmpty() || lastName.isEmpty() || ageText.isEmpty()) {
            System.out.println("Error: All fields are required!");
            return;
        }

        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException ex) {
            System.out.println("Error: Invalid age input.");
            return;
        }

        if (age < 18) {
            System.out.println("Error: Age must be 18 or older.");
            return;
        }

        CustomersData.getInstance().addUser(new User(firstName, lastName, age));

    }

    public void switchToHomepage() throws IOException {
        App.setRoot("HomePage");
    }
}
