//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package controllers;

import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.MainApp;
import main.models.Agents;
import main.services.AgentsDAOImp;
import org.mindrot.jbcrypt.BCrypt;

public class LoginController {
    @FXML
    private Hyperlink forgetpassword;
    @FXML
    private TextField tv_email;
    @FXML
    private PasswordField tv_password;
    @FXML
    private TextField tv_password_visible;
    @FXML
    private Button btn_login;
    @FXML
    private Button btn_show_password;
    private final AgentsDAOImp agentsService = new AgentsDAOImp();

    public LoginController() {
    }

    @FXML
    public void initialize() {
        this.forgetpassword.setOnAction(this::handleForgetPassword);
    }

    public void handleForgetPassword(ActionEvent actionEvent) {
        this.loadNewPage("/fxml/ResetPassowrd.fxml");
    }

    public void loadNewPage(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlFile));
            Parent newPage = (Parent)loader.load();
            Scene newScene = new Scene(newPage);
            Stage currentStage = (Stage)this.forgetpassword.getScene().getWindow();
            currentStage.setScene(newScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load " + fxmlFile);
        }

    }

    @FXML
    public void handleLogin() {

        String username = this.tv_email.getText().trim();
        String password = this.tv_password.isVisible() ? this.tv_password.getText().trim() : this.tv_password_visible.getText().trim();
        if (!username.isEmpty() && !password.isEmpty()) {
            try {
                if ("admin".equals(username) && "admin".equals(password)) {
                    this.showAlert(AlertType.INFORMATION, "Success", "Welcome, Admin!");
                    MainApp.showAdminDashboard();
                    return;
                }

                List<Agents> agentsList = this.agentsService.getAllAgents();
                boolean isAuthenticated = false;

                for(Agents agent : agentsList) {
                    if ((agent.getEmail().equals(username) || agent.getId().equals(username)) && BCrypt.checkpw(password, agent.getPassword())) {
                        this.showAlert(AlertType.INFORMATION, "Success", "Welcome, Agent!");
                        MainApp.showAgentDashboard();
                        isAuthenticated = true;
                        break;
                    }
                }

                if (!isAuthenticated) {
                    this.showAlert(AlertType.ERROR, "Error", "Invalid username or password.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                this.showAlert(AlertType.ERROR, "Error", "Failed to load the dashboard.");
            }

        } else {
            this.showAlert(AlertType.ERROR, "Error", "Please fill in both fields.");
        }


    }

    @FXML
    public void showPassword() {
        this.tv_password_visible.setText(this.tv_password.getText());
        this.tv_password_visible.setVisible(true);
        this.tv_password.setVisible(false);
    }

    @FXML
    public void hidePassword() {
        this.tv_password.setText(this.tv_password_visible.getText());
        this.tv_password.setVisible(true);
        this.tv_password_visible.setVisible(false);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText((String)null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
