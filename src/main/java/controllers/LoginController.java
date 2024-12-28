package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.MainApp;

import java.io.IOException;

public class LoginController {

    @FXML
    private Hyperlink forgetpassword; // Change Label to Hyperlink
    @FXML private TextField tv_email;
    @FXML private PasswordField tv_password;
    @FXML private TextField tv_password_visible;
    @FXML private Button btn_login;
    @FXML private Button btn_show_password;

    @FXML
    public void initialize() {
        // Attach event handler for forgetpassword (Hyperlink)
        forgetpassword.setOnAction(this::handleForgetPassword);
    }

    // Handle forget password click
    public void handleForgetPassword(ActionEvent actionEvent) {
        loadNewPage("/fxml/SendEmail.fxml"); // Correct FXML file path for password reset page
    }

    // Load a new page in a new window
    public void loadNewPage(String fxmlFile) {
        try {
            // Load the FXML content
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent newPage = loader.load();

            // Create a new scene with the loaded content
            Scene newScene = new Scene(newPage);

            // Get the current stage and set the new scene
            Stage currentStage = (Stage) forgetpassword.getScene().getWindow();
            currentStage.setScene(newScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load " + fxmlFile);
        }
    }

    // Handle Login action
    @FXML
    public void handleLogin() {
        String username = tv_email.getText();
        String password = tv_password.isVisible() ? tv_password.getText() : tv_password_visible.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill in both fields.");
            return;
        }

        try {
            Stage currentStage = (Stage) btn_login.getScene().getWindow(); // Get current stage (login popup)

            // Handle admin and agent login logic
            if ("admin".equals(username) && "admin".equals(password)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Welcome, Admin!");
                currentStage.close(); // Close the login popup
                MainApp.showAdminDashboard(); // Load Admin Dashboard
            } else if ("agent".equals(username) && "agent".equals(password)) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Welcome, Agent!");
                currentStage.close(); // Close the login popup
                MainApp.showAgentDashboard(); // Load Agent Dashboard
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Invalid username or password.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load the dashboard.");
        }
    }

    // Show password when the button is clicked
    @FXML
    public void showPassword() {
        tv_password_visible.setText(tv_password.getText());
        tv_password_visible.setVisible(true);
        tv_password.setVisible(false);
    }

    // Hide password when the button is clicked
    @FXML
    public void hidePassword() {
        tv_password.setText(tv_password_visible.getText());
        tv_password.setVisible(true);
        tv_password_visible.setVisible(false);
    }

    // Utility method to show alerts
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
