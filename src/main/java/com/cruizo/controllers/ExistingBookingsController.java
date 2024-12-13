/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cruizo.controllers;

import com.cruizo.App;
import com.cruizo.Utilities;
import com.cruizo.data.BookingsData;
import com.cruizo.models.Booking;
import java.io.IOException;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author ALI
 */
public class ExistingBookingsController implements  Initializable{
    
    @FXML
    private TableView<Booking> bookingsTableView;

    @FXML
    private TableColumn<Booking, Double> bookingAmountTableColumn;

    @FXML
    private TableColumn<Booking, String> bookingIdTableColumn;


    @FXML
    private TableColumn<Booking, LocalDate> endDateTableColumn;

    @FXML
    private TableColumn<Booking, String> licenseTableColumn;

    @FXML
    private TableColumn<Booking, String> makeTableColumn;

    @FXML
    private TableColumn<Booking, String> modelTableColumn;

    @FXML
    private TableColumn<Booking, String> nameTableColumn;

    @FXML
    private TableColumn<Booking, String> phoneTableColumn;

    @FXML
    private TableColumn<Booking, String> registrationTableColumn;

    @FXML
    private TableColumn<Booking, LocalDate> startDateTableColumn;
    
    @FXML
    private TextField searchField;
    
    ObservableList<Booking>bookings;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        bookings=FXCollections.observableArrayList(BookingsData.getInstance().getBookings());
        
        bookingsTableView.setItems(bookings);
        
        bookingIdTableColumn.setCellValueFactory(new PropertyValueFactory<Booking,String>("bookingId"));
        
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<Booking,String>("fullName"));
        phoneTableColumn.setCellValueFactory(new PropertyValueFactory<Booking,String>("phoneNumber"));
        licenseTableColumn.setCellValueFactory(new PropertyValueFactory<Booking,String>("licenseNumber"));
        makeTableColumn.setCellValueFactory(new PropertyValueFactory<Booking,String>("make"));
        modelTableColumn.setCellValueFactory(new PropertyValueFactory<Booking,String>("model"));
        registrationTableColumn.setCellValueFactory(new PropertyValueFactory<Booking,String>("registrationNumber"));
        
        startDateTableColumn.setCellValueFactory(new PropertyValueFactory<Booking,LocalDate>("startDate"));
        endDateTableColumn.setCellValueFactory(new PropertyValueFactory<Booking,LocalDate>("endDate"));
        bookingAmountTableColumn.setCellValueFactory(new PropertyValueFactory<Booking,Double>("bookingAmount"));
        
        
    
    
    
    
    }
    
    @FXML
    public void removeBooking(){
        
        Booking booking = bookingsTableView.getSelectionModel().getSelectedItem();
        
        if(booking!=null){
            
            BookingsData.getInstance().removeBooking(booking);
           
            bookings.setAll(bookings.stream()
                    .filter(x->!(x.getBookingId().equals(booking.getBookingId())))
                    .collect(Collectors.toList())
            );

            
        }else{        
            Utilities.showAlert(Alert.AlertType.ERROR, "Error", "Please select a booking first");
        }
        
    }
    
    
    @FXML
    public void searchBooking(){
        String searchQuery= searchField.getText();
        
        bookings.setAll(
                
                BookingsData.getInstance().getBookings().stream()
                        .filter(x->x.getBookingId().startsWith(searchQuery))
                        .collect(Collectors.toList())
        );
    }
    
    @FXML
    public void switchToHomepage() throws IOException {
        App.setRoot("HomePage");
    }
    
}

