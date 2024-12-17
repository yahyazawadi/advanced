package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

//import javax.mail.*;
//import javax.mail.internet.*;
//import java.sql.*;
import java.net.Authenticator;
import java.sql.*;
import java.util.*;
import java.util.Properties;

public class SendEmailController {

    @FXML
    private TextField emailField;

    @FXML
    private Label statusLabel;

    private final String senderEmail = "yousefshubib@gmail.com";
    private final String senderPassword = "hrmg keat urut plhi";

    private Connection connectToDatabase() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/AgentDatabase";
        String username = "root";
        String password = "Y@12217432Y@";
        return DriverManager.getConnection(url, username, password);
    }

    @FXML
    private void handleSendEmail() {
        String recipientEmail = emailField.getText().trim();

        if (recipientEmail.isEmpty()) {
            statusLabel.setText("Please enter a valid email address.");
            return;
        }

        try (Connection conn = connectToDatabase()) {
            if (isEmailRegistered(conn, recipientEmail)) {
                String verificationCode = generateVerificationCode();
                saveVerificationToken(conn, recipientEmail, verificationCode);
                //sendEmail(recipientEmail, verificationCode);
                statusLabel.setText("Verification code sent successfully!");
                statusLabel.setTextFill(javafx.scene.paint.Color.web("#12a3c6"));
            } else {
                statusLabel.setText("This email is not registered.");
            }
        } catch (Exception e) {
            statusLabel.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean isEmailRegistered(Connection conn, String email) throws SQLException {
        String query = "SELECT email FROM agent WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    private void saveVerificationToken(Connection conn, String email, String token) throws SQLException {
        String query = "INSERT INTO verification_tokens (email, token) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, token);
            stmt.executeUpdate();
        }
    }

    private String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    /*private void sendEmail(String recipientEmail, String verificationCode) throws MessagingException {
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

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        message.setSubject("Password Reset Verification Code");
        message.setText("Your verification code is: " + verificationCode);

        Transport.send(message);
    }*/
}
