package controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NewPasswordController {

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private Label passwordHintLabel;

    @FXML
    private Button btnSubmit;

    // Commented out original variables and database logic

    //private final String DB_URL = "jdbc:mysql://localhost:3306/AgentDatabase";
    //private final String DB_USER = "root";
    //private final String DB_PASSWORD = "Y@12217432Y@";
    //private String userEmail;

    @FXML
    public void initialize() {
        // Commented out password validation logic
        // newPasswordField.textProperty().addListener((observable, oldValue, newValue) -> {
        //     if (newValue.isEmpty()) {
        //         passwordHintLabel.setText("");
        //     } else if (!isPasswordValid(newValue)) {
        //         passwordHintLabel.setText("Invalid password: Min 8 chars, 1 uppercase.");
        //         passwordHintLabel.setTextFill(Color.RED);
        //     } else {
        //         passwordHintLabel.setText("Valid password.");
        //         passwordHintLabel.setTextFill(Color.GREEN);
        //     }
        // });

        // Make the submit button load the login page
        btnSubmit.setOnAction(event -> handleSubmitPassword());
    }

    private void handleSubmitPassword() {
        loadLoginPage("/fxml/login.fxml"); // Load the login page after password submission
    }

    // Commented out password validation method
    // private boolean isPasswordValid(String password) {
    //     return password.length() >= 8 && password.matches(".*[A-Z].*");
    // }

    // Commented out database logic and alert methods
    // private void handleSubmitPassword() {
    //     String newPassword = newPasswordField.getText();
    //     if (newPassword.isEmpty()) {
    //         showAlert(Alert.AlertType.ERROR, "Validation Error", "Please enter a new password.");
    //         return;
    //     }
    //     if (!isPasswordValid(newPassword)) {
    //         showAlert(Alert.AlertType.ERROR, "Validation Error", "Password must be at least 8 characters long and contain at least one uppercase letter.");
    //         return;
    //     }
    //     try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
    //         String updatePasswordQuery = "UPDATE agent SET password = ? WHERE email = ?";
    //         try (PreparedStatement updatePasswordStmt = connection.prepareStatement(updatePasswordQuery)) {
    //             updatePasswordStmt.setString(1, newPassword);
    //             updatePasswordStmt.setString(2, userEmail);
    //             int rowsUpdated = updatePasswordStmt.executeUpdate();
    //             if (rowsUpdated > 0) {
    //                 showAlert(Alert.AlertType.INFORMATION, "Success", "Password has been updated successfully.");
    //             } else {
    //                 showAlert(Alert.AlertType.ERROR, "Error", "Failed to update the password.");
    //             }
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //         showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred while accessing the database.");
    //     }
    // }

    // Commented out showAlert method
    // private void showAlert(Alert.AlertType alertType, String title, String message) {
    //     Alert alert = new Alert(alertType);
    //     alert.setTitle(title);
    //     alert.setContentText(message);
    //     alert.showAndWait();
    // }

    // Commented out setter for userEmail
    // public void setUserEmail(String email) {
    //     this.userEmail = email;
    // }

    // Load the login page
    private void loadLoginPage(String fxmlFile) {
        try {
            // Load the FXML content
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Node newPage = loader.load();

            // Create a new scene with the loaded content
            Scene newScene = new Scene((Parent) newPage);

            // Get the current stage and set the new scene
            Stage currentStage = (Stage) btnSubmit.getScene().getWindow();
            currentStage.setScene(newScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load " + fxmlFile);
        }
    }
}
