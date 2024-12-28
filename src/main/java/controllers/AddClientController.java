package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.services.CustomerDOIAImp;
import main.models.Customer;

public class AddClientController {

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

    private final CustomerDOIAImp customerDOIAImp;

    public AddClientController() {
        this.customerDOIAImp = new CustomerDOIAImp();
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

        // Create a Client object
        Customer customer= new Customer();
        customer.setFullName(fullName);
        customer.setEmail(email);
        customer.setPhoneNumber(phone);
        customer.setLocation(location);
        customer.setPropertyType(propertyType);
        customer.setBudget(budget);
        customer.setNotes(notes);

       customerDOIAImp.save(customer);

        clearFields();
    }

    private void clearFields() {
        fullNameField.clear();
        emailField.clear();
        phoneField.clear();
        locationField.setValue(null);
        propertyTypeField.setValue(null);
        budgetField.clear();
        notesField.clear();
    }
}
