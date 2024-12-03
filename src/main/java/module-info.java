module com.cruizo {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.cruizo to javafx.fxml;
    exports com.cruizo;
    exports com.cruizo.controllers to javafx.fxml;  // Export controllers to javafx.fxml module
    
    
    opens com.cruizo.controllers to javafx.fxml;  // Open controllers for reflection (necessary for FXML)
    // Ensure you have this if using base modules
    
    // Export the com.cruizo.controllers package to javafx.fxml
   

    // Optionally, open packages if reflection is needed


}
