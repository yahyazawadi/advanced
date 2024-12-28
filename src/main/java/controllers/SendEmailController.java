package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javax.mail.*;
import javax.mail.internet.*;
import java.sql.*;
import java.util.*;
import org.mindrot.jbcrypt.BCrypt;

public class SendEmailController {

    @FXML
    private TextField emailField, verificationCodeField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private Label statusLabel, verificationStatusLabel, passwordHintLabel;

    @FXML
    private Button btnSubmit;

    private String verificationCode;
    private String userEmail;

    private final String senderEmail = "yousefshubib@gmail.com";
    private final String senderPassword = "hrmg keat urut plhi";

    private final String DB_URL = "jdbc:mysql://localhost:3306/AgentDatabase";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "Y@12217432Y@";

    @FXML
    public void initialize() {
        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            statusLabel.setText("");
        });

        verificationCodeField.textProperty().addListener((observable, oldValue, newValue) -> {
            verificationStatusLabel.setText("");
        });

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
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 8 && password.matches(".*[A-Z].*");
    }

    @FXML
    private void handleSendEmail() {
        String email = emailField.getText().trim();
        if (email.isEmpty()) {
            statusLabel.setText("Please enter a valid email.");
            return;
        }

        if (isEmailRegistered(email)) {
            verificationCode = generateVerificationCode();
            sendEmail(email, verificationCode);

            userEmail = email;
            statusLabel.setText("Verification code sent to " + email);
            statusLabel.setTextFill(Color.GREEN);
        } else {
            statusLabel.setText("This email is not registered.");
            statusLabel.setTextFill(Color.RED);
        }
    }

    private boolean isEmailRegistered(String email) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT email FROM NewAgent WHERE email = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, email);
                ResultSet rs = stmt.executeQuery();
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String generateVerificationCode() {
        return String.valueOf((int) (Math.random() * 900000) + 100000);
    }

    private void sendEmail(String recipientEmail, String verificationCode) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Password Reset Verification Code");
            message.setText("Your verification code is: " + verificationCode);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            statusLabel.setText("Failed to send email.");
            statusLabel.setTextFill(Color.RED);
        }
    }

    @FXML
    private void handleSubmitCode() {
        String enteredCode = verificationCodeField.getText().trim();
        if (enteredCode.isEmpty()) {
            verificationStatusLabel.setText("Please enter the verification code.");
            return;
        }

        if (enteredCode.equals(verificationCode)) {
            verificationStatusLabel.setText("Code verified successfully!");
            verificationStatusLabel.setTextFill(Color.GREEN);
        } else {
            verificationStatusLabel.setText("Invalid code. Please try again.");
            verificationStatusLabel.setTextFill(Color.RED);
        }
    }

    @FXML
    private void handleSubmitNewPassword() {
        String newPassword = newPasswordField.getText().trim();

        if (newPassword.isEmpty()) {
            passwordHintLabel.setText("Password cannot be empty.");
            passwordHintLabel.setTextFill(Color.RED);
            return;
        }

        if (!isPasswordValid(newPassword)) {
            passwordHintLabel.setText("Password must contain at least 8 characters and 1 uppercase letter.");
            passwordHintLabel.setTextFill(Color.RED);
            return;
        }

        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String updatePasswordQuery = "UPDATE NewAgent SET password = ? WHERE email = ?";
            try (PreparedStatement updatePasswordStmt = connection.prepareStatement(updatePasswordQuery)) {
                updatePasswordStmt.setString(1, hashedPassword);
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
            showAlert(Alert.AlertType.ERROR, "Database Error", "An error occurred while updating the password.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
