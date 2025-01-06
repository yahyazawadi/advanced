package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.models.Agents;
import main.imp.AgentsDAOImp;
import org.mindrot.jbcrypt.BCrypt;

public class AddAgentsController {

    public Button saveButton;
    public Button cancelButton;

    @FXML
    private TextField idField, firstNameField, lastNameField, emailField, phoneField, companyNameField, companyAddressField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label passwordHintLabel;

    @FXML
    private ChoiceBox<String> roleChoiceBox;

    private final AgentsDAOImp agentsDOIAImp;

    public AddAgentsController() {
        this.agentsDOIAImp = new AgentsDAOImp();
    }

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
    public void handleSubmit(ActionEvent event) {
        String id = idField.getText().trim();
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();
        String phone = phoneField.getText().trim();
        String role = roleChoiceBox.getValue();
        String companyName = companyNameField.getText().trim();
        String companyAddress = companyAddressField.getText().trim();

        if (id.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "All fields except company details are required.");
            return;
        }

        if (!isPasswordValid(password)) {
            showAlert(Alert.AlertType.ERROR, "Invalid Password", "Password must be at least 8 characters long and contain at least one uppercase letter.");
            return;
        }

        if (role == null || role.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Role must be selected.");
            return;
        }

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        Agents agents = new Agents();
        agents.setId(id);
        agents.setFirstName(firstName);
        agents.setLastName(lastName);
        agents.setEmail(email);
        agents.setPassword(hashedPassword);
        agents.setPhoneNumber(phone);
        agents.setRole(role);
        agents.setCompanyName(companyName);
        agents.setCompanyAddress(companyAddress);

        try {
            agentsDOIAImp.save(agents);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Agent registered successfully!");
            clearFields();
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to register agent.\n" + e.getMessage());
        }
    }

    @FXML
    public void handleClear(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
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
