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
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;



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
    
    @FXML
    public Button changeGST;
    
    @FXML
    public Label GSTValue;

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
        Utilities.setRoot("HomePage");
    }
    public void switchToConfirmation() throws IOException {
        Utilities.setRoot("Confirmation");
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
    // method overloaded in case of gst changing
    private void calculateBookingAmount(String gst) {
    if (selectedCustomer != null && selectedCar != null && rentalDurationLable.getText() != null && !(rentalDurationLable.getText().isEmpty())) {
        try {
            // Parse the rental duration
           
            // Get GST percentage as a String, e.g., "13" for 13%
            String gstPercentageStr = gst;  // Assuming gstLabel holds the GST percentage as String
            
            // Convert GST String to Integer, then to Decimal (e.g., "13" -> 0.13)
            int gstPercentage = Integer.parseInt(gstPercentageStr);
            double gstDecimal = gstPercentage / 100.0;

           Double rentalAmount=selectedCar.pricePerDay*Integer.parseInt(rentalDurationLable.getText());
            Double totalRentalAmount=rentalAmount+(rentalAmount*gstDecimal);
            
            rentalAmountLabel.setText(Double.toString(rentalAmount));
            gstLabel.setText(Double.toString(rentalAmount*gstDecimal));
            totalAmountLabel.setText(Double.toString(totalRentalAmount));

            // Store the calculated total amount
            calculatedRentalAmount = totalRentalAmount;
        } catch (NumberFormatException e) {
            Utilities.showAlert(Alert.AlertType.ERROR, "Error", "Invalid number format in GST or rental duration");
        }
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
        
    

        // Navigate to Homepage
        switchToHomepage();
        
    } else {
       Utilities.showAlert(Alert.AlertType.ERROR, "Error", "Please fill out all the fields");

    }
}
   @FXML
private void changeGST(ActionEvent event) throws IOException {
    
   
    try {
        // Load the FXML file for the dialog
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cruizo/ChangeGST.fxml"));
        Parent root = loader.load();

        // Create a new stage for the modal dialog
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Change GST");
        dialogStage.initModality(javafx.stage.Modality.APPLICATION_MODAL); // Set modality to block other windows
        dialogStage.initOwner(((Stage) changeGST.getScene().getWindow())); // Set the owner of the dialog
        
        // Create a new scene for the dialog
        Scene dialogScene = new Scene(root);
        dialogStage.setScene(dialogScene);

        // Access the controller to pass any required data (optional)
        ChangeGSTController controller = loader.getController();
//        controller.setCurrentGSTLabel(gstLabel.getText()); // Example: pass the current GST value

        // Show the dialog and wait until it's closed
        dialogStage.showAndWait();

        // After closing the dialog, fetch the updated GST value if changed
        String newGST = controller.getUpdatedGST();
        if (newGST != null) {
            // Update the GST label and recalculate the booking amount
            GSTValue.setText("GST " + "(" + newGST + "%" + ") :");
            calculateBookingAmount(newGST);
        }

    } catch (IOException e) {
        e.printStackTrace();
        Utilities.showAlert(Alert.AlertType.ERROR, "Error", "Failed to load ChangeGST.fxml");
    }
}

}
