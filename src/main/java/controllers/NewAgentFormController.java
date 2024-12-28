package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//import java.sql.*;

public class NewAgentFormController {

    @FXML
    private TextField idField, firstNameField, lastNameField, emailField, phoneField, companyNameField, companyAddressField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label passwordHintLabel;

    @FXML
    private ChoiceBox<String> roleChoiceBox, statusChoiceBox;

    private final String DB_URL = "jdbc:mysql://localhost:3306/AgentDatabase";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "Y@12217432Y@";

    @FXML
    public void initialize() {
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                passwordHintLabel.setText("");
            } else if (!isPasswordValid(newValue)) {
                passwordHintLabel.setText("Invalid: 8+ chars with at least 1 uppercase letter.");
                passwordHintLabel.setStyle("-fx-text-fill: red;");
            } else {
                passwordHintLabel.setText("Valid password.");
                passwordHintLabel.setStyle("-fx-text-fill: green;");
            }
        });
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 8 && password.matches(".*[A-Z].*");
    }

    @FXML
    private void handleSubmit() {
        String id = idField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String phone = phoneField.getText();
        String role = roleChoiceBox.getValue();
        String companyName = companyNameField.getText();
        String companyAddress = companyAddressField.getText();
        String status = statusChoiceBox.getValue();

        if (!isPasswordValid(password)) {
            showAlert(Alert.AlertType.ERROR, "Invalid Password", "Password must be at least 8 characters long and contain at least one uppercase letter.");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO agent (id, first_name, last_name, email, password, phone_number, role, company_name, company_address, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, email);
            statement.setString(5, password);
            statement.setString(6, phone);
            statement.setString(7, role);
            statement.setString(8, companyName);
            statement.setString(9, companyAddress);
            statement.setString(10, status);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Agent registered successfully!");
                handleClear();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to register agent.");
        }
    }

    @FXML
    private void handleClear() {
        idField.clear();
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        passwordField.clear();
        phoneField.clear();
        companyNameField.clear();
        companyAddressField.clear();
        passwordHintLabel.setText("");
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
