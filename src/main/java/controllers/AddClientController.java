package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;

public class AddClientController {

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private ComboBox<String> locationField;

    @FXML
    private ComboBox<String> propertyTypeField;

    @FXML
    private TextField budgetField;

    @FXML
    private TextArea notesField;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/customer_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "0569315404";

    @FXML
    public void onSaveButtonClick(ActionEvent event) {
        // قراءة البيانات من الحقول
        String fullName = fullNameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String location = locationField.getValue();
        String propertyType = propertyTypeField.getValue();
        String budget = budgetField.getText().trim();
        String notes = notesField.getText().trim();

        // التحقق من أن جميع الحقول المطلوبة ممتلئة
        if (fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || location == null || propertyType == null || budget.isEmpty()) {
            System.out.println("Please fill in all required fields!");
            return;
        }

        // تنفيذ عملية الحفظ
        String sql = "INSERT INTO customer_info (full_name, email, phone_number, location, property_type, budget, notes) VALUES (?, ?, ?, ?, ?, ?, ?)";

       // try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
          /*  PreparedStatement preparedStatement = connection.prepareStatement(sql)) {


            // إعداد القيم للإدخال في قاعدة البيانات
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, location);
            preparedStatement.setString(5, propertyType);
            preparedStatement.setString(6, budget);
            preparedStatement.setString(7, notes);

            // تنفيذ عملية الإدخال
            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("All customer information saved successfully!");

                // تفريغ الحقول بعد الحفظ
                clearFields();
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error saving customer information.");
        }
    }

    // دالة لتفريغ الحقول بعد الحفظ
    private void clearFields() {
        fullNameField.clear();
        emailField.clear();
        phoneField.clear();
        locationField.setValue(null);
        propertyTypeField.setValue(null);
        budgetField.clear();
        notesField.clear();
    }*/
}}
