package controllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SendEmailController {

    @FXML
    private TextField emailField;

    @FXML
    private Label statusLabel;

    // Commented out original methods and variables

    //private final String senderEmail = "yousefshubib@gmail.com";
    //private final String senderPassword = "hrmg keat urut plhi";

    //private Connection connectToDatabase() throws SQLException {
    //    String url = "jdbc:mysql://localhost:3306/AgentDatabase";
    //    String username = "root";
    //    String password = "Y@12217432Y@";
    //    return DriverManager.getConnection(url, username, password);
    //}

    @FXML
    private void handleSendEmail() {
        loadNewPage("/fxml/NewPassword.fxml"); // Correct FXML file path for new password page
    }

    // Commented out original helper methods

    //private boolean isEmailRegistered(Connection conn, String email) throws SQLException {
    //    String query = "SELECT email FROM agent WHERE email = ?";
    //    try (PreparedStatement stmt = conn.prepareStatement(query)) {
    //        stmt.setString(1, email);
    //        ResultSet rs = stmt.executeQuery();
    //        return rs.next();
    //    }
    //}

    //private void saveVerificationToken(Connection conn, String email, String token) throws SQLException {
    //    String query = "INSERT INTO verification_tokens (email, token) VALUES (?, ?)";
    //    try (PreparedStatement stmt = conn.prepareStatement(query)) {
    //        stmt.setString(1, email);
    //        stmt.setString(2, token);
    //        stmt.executeUpdate();
    //    }
    //}

    //private String generateVerificationCode() {
    //    Random random = new Random();
    //    int code = 100000 + random.nextInt(900000);
    //    return String.valueOf(code);
    //}

    //private void sendEmail(String recipientEmail, String verificationCode) throws MessagingException {
    //    Properties properties = new Properties();
    //    properties.put("mail.smtp.auth", "true");
    //    properties.put("mail.smtp.starttls.enable", "true");
    //    properties.put("mail.smtp.host", "smtp.gmail.com");
    //    properties.put("mail.smtp.port", "587");

    //    Session session = Session.getInstance(properties, new Authenticator() {
    //        @Override
    //        protected PasswordAuthentication getPasswordAuthentication() {
    //            return new PasswordAuthentication(senderEmail, senderPassword);
    //        }
    //    });

    //    Message message = new MimeMessage(session);
    //    message.setFrom(new InternetAddress(senderEmail));
    //    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
    //    message.setSubject("Password Reset Verification Code");
    //    message.setText("Your verification code is: " + verificationCode);

    //    Transport.send(message);
    //}

    // Load a new page in a new window
    public void loadNewPage(String fxmlFile) {
        try {
            // Load the FXML content
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Node newPage = loader.load();

            // Create a new scene with the loaded content
            Scene newScene = new Scene((Parent) newPage);

            // Get the current stage and set the new scene
            Stage currentStage = (Stage) statusLabel.getScene().getWindow();
            currentStage.setScene(newScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load " + fxmlFile);
        }
    }
}
