package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;

public class LoginController {

    @FXML private TextField tv_email;
    @FXML private PasswordField tv_password;
    @FXML private TextField tv_password_visible;
    @FXML private Button btn_login;
    @FXML private Button btn_show_password;

    private final String DB_URL = "jdbc:mysql://localhost:3306/AgentDatabase";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "Y@12217432Y@";

    @FXML
    public void handleLogin() {
        String emailOrUsername = tv_email.getText();
        String password = tv_password.isVisible() ? tv_password.getText() : tv_password_visible.getText();

      if (emailOrUsername.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill in both fields.");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM agent WHERE (email = ? OR id = ?) AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, emailOrUsername);
            statement.setString(2, emailOrUsername);
            statement.setString(3, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Login successful!");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Invalid email/username or password.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Database connection failed.");
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
}}
