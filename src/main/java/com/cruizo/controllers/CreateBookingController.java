/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cruizo.controllers;

import com.cruizo.App;
import com.cruizo.data.BookingsData;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import com.cruizo.data.CustomersData;
import com.cruizo.data.CarsData;
import com.cruizo.models.Booking;
import com.cruizo.models.Car;
import com.cruizo.models.Customer;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import com.cruizo.Utilities;


/**
 *
 * @author ALI
 */
public class CreateBookingController implements Initializable {

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

    @FXML
    private DatePicker startDate;

    @FXML
    private DatePicker endDate;
    
    @FXML
    private ListView<String> selectedCarListView;

    @FXML
    private ListView<String> selectedCustomerListView;
    
    @FXML
    private Label gstLabel;

    @FXML
    private Label rentalAmountLabel;

    @FXML
    private Label totalAmountLabel;

    @FXML
    private Label rentalDurationLable;

    ObservableList<Customer> customersList;
    ObservableList<Car> carsList;
    
    private Customer selectedCustomer;
    private Car selectedCar;
    
    private Double calculatedRentalAmount;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        customersList = FXCollections.observableArrayList(CustomersData.getInstance().getUsers());
        carsList = FXCollections.observableArrayList(CarsData.getInstance().getCars());
        
        // Filtering out rented Cars
        carsList.setAll(
                        CarsData.getInstance().getCars().stream()
                        .filter(x -> x.status.equals("Available"))
                        .collect(Collectors.toList())
        );

        // Initializing Customer Table    
        CustomerTable.setItems(customersList);
        FullNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("fullName"));
        LicenseNumberColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("licenseNumber"));

        // Initializing Car Table
        CarTable.setItems(carsList);
        CarModelColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("model"));
        CarRegistrationColumn.setCellValueFactory(new PropertyValueFactory<Car, String>("registrationNumber"));

//        Disable past dates from start and end Date Picker
        startDate.setDayCellFactory(picker -> new DateCell() {

            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                // Disable past dates
                if (date.isBefore(LocalDate.now()) || (endDate.getValue() != null && date.isAfter(endDate.getValue()))) {
                    setDisable(true);
                }

            }
        });

        endDate.setDayCellFactory(picker -> new DateCell() {

            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                // Disable past dates
                if (date.isBefore(startDate.getValue() != null ? startDate.getValue() : LocalDate.now())) {
                    setDisable(true);
                }
            }
        });

    }

    public void switchToHomepage() throws IOException {
        App.setRoot("HomePage");
    }
    public void switchToConfirmation() throws IOException {
        App.setRoot("Confirmation");
    }

    @FXML
    public void filterCustomersList() {
        String searchQuery = CustomerField.getText();

        customersList.setAll(
                CustomersData.getInstance().getUsers().stream()
                        .filter(x -> x.fullname.toLowerCase().startsWith(searchQuery.toLowerCase()))
                        .collect(Collectors.toList())
        );

    }

    @FXML
    public void filterCarsList() {

        String searchQuery = CarField.getText();

        carsList.setAll(
                CarsData.getInstance().getCars().stream()
                        .filter(x -> x.model.toLowerCase().startsWith(searchQuery.toLowerCase()))
                        .collect(Collectors.toList())
        );

    }

    
     
    public void selectCustomer(){
        
        Customer selectedCustomer=CustomerTable.getSelectionModel().getSelectedItem();        
        if(selectedCustomer !=null ){
            this.selectedCustomer= selectedCustomer;
            selectedCustomerListView.getItems().setAll(this.selectedCustomer.fullname,this.selectedCustomer.phoneNumber,this.selectedCustomer.email,this.selectedCustomer.licenseNumber);        
            calculateBookingAmount();
        }
        
            
    }
    
    public void selectCar(){
        
        Car selectedCar=CarTable.getSelectionModel().getSelectedItem();
        
        if(selectedCar !=null ){
            
                this.selectedCar=selectedCar;
                selectedCarListView.getItems().setAll(this.selectedCar.make,this.selectedCar.model,this.selectedCar.category,this.selectedCar.registrationNumber);
                calculateBookingAmount();
        }
            
    }
   
    
    public void calculateRentalDays(){
       
        if (startDate != null && endDate != null && startDate.getValue() !=null && endDate.getValue() !=null) {

            LocalDate startDate = this.startDate.getValue();
            LocalDate endDate = this.endDate.getValue();

            Long daysDifference = ChronoUnit.DAYS.between(startDate, endDate)+ 1;
            
            rentalDurationLable.setText(Long.toString(daysDifference));
            
            calculateBookingAmount();
         
        }               
            

        

    }
    
    private void calculateBookingAmount(){
         if (selectedCustomer!=null && selectedCar!=null && rentalDurationLable.getText()!=null && !(rentalDurationLable.getText().isEmpty()) ) {

            Double rentalAmount=selectedCar.pricePerDay*Integer.parseInt(rentalDurationLable.getText());
            Double totalRentalAmount=rentalAmount+(rentalAmount*0.13);
            
            rentalAmountLabel.setText(Double.toString(rentalAmount));
            gstLabel.setText(Double.toString(rentalAmount*0.13));
            totalAmountLabel.setText(Double.toString(totalRentalAmount));
            
            calculatedRentalAmount=totalRentalAmount;
        }
    }
    
   
    @FXML
    public void confirmBooking() throws IOException {
    if (selectedCustomer != null && selectedCar != null && rentalDurationLable.getText() != null && !(rentalDurationLable.getText().isEmpty())) {
        
        // Create the new booking object
        Booking newBooking = new Booking(selectedCustomer, selectedCar, startDate.getValue(), endDate.getValue(), calculatedRentalAmount);
        
        // Add the new booking to the booking data
        BookingsData.getInstance().addBooking(newBooking);
        
        // Get the booking ID from the created booking object
        String bookingId = newBooking.getBookingId();  // Accessing the bookingId of the booking
        System.out.println(bookingId);
        // Filtering out  booked/rented cars from the available cars list
        carsList.setAll(
            carsList.stream()
            .filter(x -> !(x.registrationNumber.equals(selectedCar.registrationNumber)))
            .collect(Collectors.toList())
        );
        
        

        // Show a confirmation dialog with the booking ID
        Utilities.showAlert(
    Alert.AlertType.INFORMATION, 
    "Booking Confirmation", 
    "Your booking has been confirmed. Booking ID: " + bookingId
);
        
        
//        Alert confirmationAlert = new Alert(AlertType.INFORMATION, "Your booking has been confirmed. Booking ID: " + bookingId, ButtonType.OK);
//        confirmationAlert.setTitle("Booking Confirmation");
//        confirmationAlert.setHeaderText("Booking Confirmed!");
//        confirmationAlert.showAndWait();

        // Navigate to Homepage
        switchToHomepage();
        
    } else {
       Utilities.showAlert(Alert.AlertType.ERROR, "Error", "Please fill out all the fields");

    }
}
}
