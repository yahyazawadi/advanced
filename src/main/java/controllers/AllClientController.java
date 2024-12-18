package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

//import java.sql.ResultSet;

public class AllClientController {
    /*
    @FXML
    private TableView<Customer> clientTable;

    @FXML
    private TableColumn<Customer, Integer> idColumn;

    @FXML
    private TableColumn<Customer, String> fullNameColumn;

    @FXML
    private TableColumn<Customer, String> emailColumn;

    @FXML
    private TableColumn<Customer, String> phoneColumn;

    @FXML
    private TableColumn<Customer, String> locationColumn;

    @FXML
    private TableColumn<Customer, String> propertyTypeColumn;

    @FXML
    private TableColumn<Customer, String> budgetColumn;

    @FXML
    private TableColumn<Customer, String> notesColumn;

    @FXML
    private TextField searchField;

    @FXML
    private Button deleteClientButton;

    private ObservableList<Customer> customerList = FXCollections.observableArrayList();

    public void initialize() {
        // إعداد أعمدة الجدول
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        propertyTypeColumn.setCellValueFactory(new PropertyValueFactory<>("propertyType"));
        budgetColumn.setCellValueFactory(new PropertyValueFactory<>("budget"));
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));

        // جعل الأعمدة قابلة للتحرير
        clientTable.setEditable(true);
        fullNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        locationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        propertyTypeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        budgetColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        notesColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // معالجة التعديلات
        fullNameColumn.setOnEditCommit(event -> updateCustomer(event, "full_name"));
        emailColumn.setOnEditCommit(event -> updateCustomer(event, "email"));
        phoneColumn.setOnEditCommit(event -> updateCustomer(event, "phone_number"));
        locationColumn.setOnEditCommit(event -> updateCustomer(event, "location"));
        propertyTypeColumn.setOnEditCommit(event -> updateCustomer(event, "property_type"));
        budgetColumn.setOnEditCommit(event -> updateCustomer(event, "budget"));
        notesColumn.setOnEditCommit(event -> updateCustomer(event, "notes"));

        // تحميل البيانات من قاعدة البيانات
        loadCustomerData();

        // إعداد زر الحذف
        deleteClientButton.setOnAction(event -> deleteSelectedCustomer());

        // إعداد البحث
        searchField.textProperty().addListener((observable, oldValue, newValue) -> filterCustomerData(newValue));
    }

    private void loadCustomerData() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet resultSet = dbHandler.getCustomers();

        try {
            while (resultSet.next()) {
                customerList.add(new Customer(
                        resultSet.getInt("id"),
                        resultSet.getString("full_name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("location"),
                        resultSet.getString("property_type"),
                        resultSet.getString("budget"),
                        resultSet.getString("notes")
                ));
            }
            clientTable.setItems(customerList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private <T> void updateCustomer(TableColumn.CellEditEvent<Customer, T> event, String column) {
        Customer customer = event.getRowValue();
        T newValue = event.getNewValue();
        DatabaseHandler dbHandler = new DatabaseHandler();

        boolean success = dbHandler.updateCustomer(
                customer.getId(),
                column.equals("full_name") ? (String) newValue : customer.getFullName(),
                column.equals("email") ? (String) newValue : customer.getEmail(),
                column.equals("phone_number") ? (String) newValue : customer.getPhoneNumber(),
                column.equals("location") ? (String) newValue : customer.getLocation(),
                column.equals("property_type") ? (String) newValue : customer.getPropertyType(),
                column.equals("budget") ? (String) newValue : customer.getBudget(),
                column.equals("notes") ? (String) newValue : customer.getNotes()
        );

        if (success) {
            switch (column) {
                case "full_name" -> customer.setFullName((String) newValue);
                case "email" -> customer.setEmail((String) newValue);
                case "phone_number" -> customer.setPhoneNumber((String) newValue);
                case "location" -> customer.setLocation((String) newValue);
                case "property_type" -> customer.setPropertyType((String) newValue);
                case "budget" -> customer.setBudget((String) newValue);
                case "notes" -> customer.setNotes((String) newValue);
            }
        } else {
            clientTable.refresh();
        }
    }

    private void deleteSelectedCustomer() {
        Customer selectedCustomer = clientTable.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            DatabaseHandler dbHandler = new DatabaseHandler();
            boolean success = dbHandler.deleteCustomer(selectedCustomer.getId());
            if (success) {
                customerList.remove(selectedCustomer);
            }
        }
    }

    private void filterCustomerData(String query) {
        if (query == null || query.isEmpty()) {
            clientTable.setItems(customerList);
            return;
        }

        ObservableList<Customer> filteredList = FXCollections.observableArrayList();
        for (Customer customer : customerList) {
            if (customer.getFullName().toLowerCase().contains(query.toLowerCase()) ||
                    customer.getEmail().toLowerCase().contains(query.toLowerCase()) ||
                    customer.getPhoneNumber().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(customer);
            }
        }
        clientTable.setItems(filteredList);
    }

*/
}