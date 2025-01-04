package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import main.models.Customer;
import main.services.CustomerDOAImp;

public class AddClientController {
    public Button saveButton;

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private ComboBox<String> locationField;

    @FXML
    private ComboBox<String> propertyTypeField;

    @FXML
    private TextField budgetField;

    @FXML
    private TextArea notesField;

    private final CustomerDOAImp customerDOIAImp;

    public AddClientController() {
        this.customerDOIAImp = new CustomerDOAImp();
    }

    @FXML
    public void onSaveButtonClick(ActionEvent event) {
        String fullName = fullNameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String location = locationField.getValue();
        String propertyType = propertyTypeField.getValue();
        String budget = budgetField.getText().trim();
        String notes = notesField.getText().trim();

        if (fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || location == null || propertyType == null || budget.isEmpty()) {
            System.out.println("Please fill in all required fields!");
            return;
        }

        Customer customer = new Customer();
        customer.setFullName(fullName);
        customer.setEmail(email);
        customer.setPhoneNumber(phone);
        customer.setLocation(location);
        customer.setPropertyType(propertyType);
        customer.setBudget(budget);
        customer.setNotes(notes);

        customerDOIAImp.save(customer);
        showSuccessMessage();
        clearFields();
    }

    private void showSuccessMessage() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Client has been successfully added");
        alert.showAndWait();
    }

    private void clearFields() {
        fullNameField.clear();
        emailField.clear();
        phoneField.clear();
        // locationField.setValue(null);
       // propertyTypeField.setValue(null);
        budgetField.clear();
        notesField.clear();
    }
}
