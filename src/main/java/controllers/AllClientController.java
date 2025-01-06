package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import main.models.Customer;
import main.imp.CustomerDOAImp;

import java.util.List;

public class AllClientController {
    public HBox bottomHBox;
    public TableColumn checkboxColumn;
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

    private final CustomerDOAImp customerService = new CustomerDOAImp();
    private ObservableList<Customer> customerList;

    @FXML
    public void initialize() {
        setupTable();
        makeTableEditable();
        loadClients();
        setupSearch();
        setupDeleteButton();
    }

    private void setupTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        propertyTypeColumn.setCellValueFactory(new PropertyValueFactory<>("propertyType"));
        budgetColumn.setCellValueFactory(new PropertyValueFactory<>("budget"));
        notesColumn.setCellValueFactory(new PropertyValueFactory<>("notes"));
    }

    private void makeTableEditable() {
        clientTable.setEditable(true);

        fullNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        fullNameColumn.setOnEditCommit(event -> {
            Customer customer = event.getRowValue();
            customer.setFullName(event.getNewValue());
            customerService.update(customer);
        });

        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setOnEditCommit(event -> {
            Customer customer = event.getRowValue();
            customer.setEmail(event.getNewValue());
            customerService.update(customer);
        });

        phoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneColumn.setOnEditCommit(event -> {
            Customer customer = event.getRowValue();
            customer.setPhoneNumber(event.getNewValue());
            customerService.update(customer);
        });

        locationColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        locationColumn.setOnEditCommit(event -> {
            Customer customer = event.getRowValue();
            customer.setLocation(event.getNewValue());
            customerService.update(customer);
        });

        propertyTypeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        propertyTypeColumn.setOnEditCommit(event -> {
            Customer customer = event.getRowValue();
            customer.setPropertyType(event.getNewValue());
            customerService.update(customer);
        });

        budgetColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        budgetColumn.setOnEditCommit(event -> {
            Customer customer = event.getRowValue();
            customer.setBudget(event.getNewValue());
            customerService.update(customer);
        });

        notesColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        notesColumn.setOnEditCommit(event -> {
            Customer customer = event.getRowValue();
            customer.setNotes(event.getNewValue());
            customerService.update(customer);
        });
    }

    private void loadClients() {
        List<Customer> customers = customerService.getAllCustomers();
        customerList = FXCollections.observableArrayList(customers);
        clientTable.setItems(customerList);
    }

    private void setupSearch() {
        FilteredList<Customer> filteredData = new FilteredList<>(customerList, p -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(customer -> {
                if (newValue == null || newValue.isEmpty()) return true;
                String lowerCaseFilter = newValue.toLowerCase();

                if (customer.getFullName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (customer.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (customer.getPhoneNumber().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });

        clientTable.setItems(filteredData);
    }

    private void setupDeleteButton() {
        deleteClientButton.setOnAction(event -> {
            Customer selectedCustomer = clientTable.getSelectionModel().getSelectedItem();
            if (selectedCustomer != null) {
                customerService.delete(selectedCustomer);
                customerList.remove(selectedCustomer);
            }
        });
    }
}