package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//import java.sql.*;

public class NewPasswordController {

    @FXML 
    private PasswordField newPasswordField;

    @FXML
    private Label passwordHintLabel;

    @FXML
    private Button btnSubmit;

    private final String DB_URL = "jdbc:mysql://localhost:3306/AgentDatabase";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "Y@12217432Y@";

    private String userEmail;

    @FXML
    public void initialize() {
        newPasswordField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                passwordHintLabel.setText("");
            } else if (!isPasswordValid(newValue)) {
                passwordHintLabel.setText("Invalid password: Min 8 chars, 1 uppercase.");
                passwordHintLabel.setTextFill(Color.RED);
            } else {
                passwordHintLabel.setText("Valid password.");
                passwordHintLabel.setTextFill(Color.GREEN);
            }
        });

        btnSubmit.setOnAction(event -> handleSubmitPassword());
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 8 && password.matches(".*[A-Z].*");
    }

    private void handleSubmitPassword() {
        String newPassword = newPasswordField.getText();

        if (newPassword.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please enter a new password.");
            return;
        }

        if (!isPasswordValid(newPassword)) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Password must be at least 8 characters long and contain at least one uppercase letter.");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String updatePasswordQuery = "UPDATE agent SET password = ? WHERE email = ?";
            try (PreparedStatement updatePasswordStmt = connection.prepareStatement(updatePasswordQuery)) {
                updatePasswordStmt.setString(1, newPassword);
                updatePasswordStmt.setString(2, userEmail);
                int rowsUpdated = updatePasswordStmt.executeUpdate();

                if (rowsUpdated > 0) {
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Password has been updated successfully.");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Failed to update the password.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred while accessing the database.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setUserEmail(String email) {
        this.userEmail = email;
    }
}
