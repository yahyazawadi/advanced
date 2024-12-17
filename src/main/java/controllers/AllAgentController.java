package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;

//import java.sql.*;

public class AllAgentController {
/*
    @FXML
    private TextField searchField;

    @FXML
    private TableView<Agent> agentTable;

    @FXML
    private TableColumn<Agent, String> idColumn;

    @FXML
    private TableColumn<Agent, String> firstNameColumn;

    @FXML
    private TableColumn<Agent, String> lastNameColumn;

    @FXML
    private TableColumn<Agent, String> emailColumn;

    @FXML
    private TableColumn<Agent, String> phoneColumn;

    @FXML
    private TableColumn<Agent, String> dateCreatedColumn;

    @FXML
    private TableColumn<Agent, String> roleColumn;

    @FXML
    private TableColumn<Agent, String> companyNameColumn;

    @FXML
    private TableColumn<Agent, String> companyAddressColumn;

    @FXML
    private TableColumn<Agent, String> statusColumn;

    @FXML
    private Button deleteAgentButton;

    private ObservableList<Agent> agentList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupEditableColumns();
        loadAgentData();
    }

    private void loadAgentData() {
        String sql = "SELECT * FROM agent";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            agentList.clear();

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone_number");
                String dateCreated = resultSet.getString("date_created");
                String role = resultSet.getString("role");
                String companyName = resultSet.getString("company_name");
                String companyAddress = resultSet.getString("company_address");
                String status = resultSet.getString("status");

                agentList.add(new Agent(id, firstName, lastName, email, phone, dateCreated, role, companyName, companyAddress, status));
            }

            agentTable.setItems(agentList);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error loading agent data from database.");
        }
    }

    private void setupEditableColumns() {
        // Enable cell editing
        agentTable.setEditable(true);

        setupEditableColumn(firstNameColumn, "first_name");
        setupEditableColumn(lastNameColumn, "last_name");
        setupEditableColumn(emailColumn, "email");
        setupEditableColumn(phoneColumn, "phone_number");
        setupEditableColumn(roleColumn, "role");
        setupEditableColumn(companyNameColumn, "company_name");
        setupEditableColumn(companyAddressColumn, "company_address");
        setupEditableColumn(statusColumn, "status");
    }

    private void setupEditableColumn(TableColumn<Agent, String> column, String dbField) {
        column.setCellFactory(TextFieldTableCell.forTableColumn());
        column.setOnEditCommit(event -> {
            Agent agent = event.getRowValue();
            String newValue = event.getNewValue();
            updateAgentInDatabase(agent.getId(), dbField, newValue);
            switch (dbField) {
                case "first_name" -> agent.setFirstName(newValue);
                case "last_name" -> agent.setLastName(newValue);
                case "email" -> agent.setEmail(newValue);
                case "phone_number" -> agent.setPhoneNumber(newValue);
                case "role" -> agent.setRole(newValue);
                case "company_name" -> agent.setCompanyName(newValue);
                case "company_address" -> agent.setCompanyAddress(newValue);
                case "status" -> agent.setStatus(newValue);
            }
        });
    }

    private void updateAgentInDatabase(String id, String field, String value) {
        String sql = "UPDATE agent SET " + field + " = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, value);
            preparedStatement.setString(2, id);
            int rowsUpdated = preparedStatement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Database updated successfully: " + field + " = " + value);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating database.");
        }
    }

    @FXML
    private void handleDeleteAgent(MouseEvent event) {
        Agent selectedAgent = agentTable.getSelectionModel().getSelectedItem();
        if (selectedAgent != null) {
            deleteAgentFromDatabase(selectedAgent);
        } else {
            System.out.println("No agent selected for deletion.");
        }
    }

    private void deleteAgentFromDatabase(Agent agent) {
        String sql = "DELETE FROM agent WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, agent.getId());
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Agent deleted successfully.");
                agentList.remove(agent); // Remove from TableView
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting agent from database.");
        }
    }

    @FXML
    private void handleSearch() {
        String searchTerm = searchField.getText().toLowerCase();
        ObservableList<Agent> filteredList = FXCollections.observableArrayList();

        for (Agent agent : agentList) {
            if (agent.getFirstName().toLowerCase().contains(searchTerm) ||
                    agent.getLastName().toLowerCase().contains(searchTerm) ||
                    agent.getEmail().toLowerCase().contains(searchTerm) ||
                    agent.getRole().toLowerCase().contains(searchTerm)) {
                filteredList.add(agent);
            }
        }

        agentTable.setItems(filteredList);
    }*/
}
