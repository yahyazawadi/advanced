package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.services.AgentsDAOImp;
import main.interfaces.AgentsDAO;
import main.models.Agents;
import org.mindrot.jbcrypt.BCrypt;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.*;

public class ResetPassowrdController {

    @FXML
    private Button backButton;

    @FXML
    public void handleBackButton() {
        try {
            // تحميل صفحة تسجيل الدخول
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
            Parent loginPage = loader.load();

            // إنشاء مشهد جديد وتعيينه للمرحلة الحالية
            Scene loginScene = new Scene(loginPage);
            Stage currentStage = (Stage) backButton.getScene().getWindow();
            currentStage.setScene(loginScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load login page");
        }
    }

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

    private AgentsDAO agentsDAO = new AgentsDAOImp(); // استخدام Hibernate بدلاً من JDBC

    @FXML
    private VBox emailSection, codeSection, passwordSection; // معرفات الأقسام

    @FXML
    public void initialize() {
        // إخفاء الأقسام الثانية والثالثة عند البداية
        codeSection.setVisible(false);
        passwordSection.setVisible(false);

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
            //statusLabel.setText("Verification code sent to " + email);
            statusLabel.setText("Verification code sent to your email");

            statusLabel.setTextFill(Color.GREEN);


            // إخفاء القسم الأول وعرض القسم الثاني مع إبقاء القسم الأول
            codeSection.setVisible(true);
        } else {
            statusLabel.setText("This email is not registered.");
            statusLabel.setTextFill(Color.RED);
        }
    }

    private boolean isEmailRegistered(String email) {
        Agents agent = agentsDAO.getAllAgents().stream()
                .filter(a -> a.getEmail().equals(email))
                .findFirst()
                .orElse(null);
        return agent != null;
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

            // إخفاء القسم الثاني وعرض القسم الثالث مع إبقاء القسم الأول
            passwordSection.setVisible(true);
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

        // تحديث كلمة المرور باستخدام Hibernate
        agentsDAO.updatePassword(userEmail, hashedPassword);

        showAlert(Alert.AlertType.INFORMATION, "Success", "Password has been updated successfully.");
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}