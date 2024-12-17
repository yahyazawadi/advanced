package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.MainApp;

public class LoginController {

    @FXML private TextField tv_email;
    @FXML private PasswordField tv_password;
    @FXML private TextField tv_password_visible;
    @FXML private Button btn_login;
    @FXML private Button btn_show_password;

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

    @FXML
    public void showPassword() {
        tv_password_visible.setText(tv_password.getText());
        tv_password_visible.setVisible(true);
        tv_password.setVisible(false);
    }

    @FXML
    public void hidePassword() {
        tv_password.setText(tv_password_visible.getText());
        tv_password.setVisible(true);
        tv_password_visible.setVisible(false);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
