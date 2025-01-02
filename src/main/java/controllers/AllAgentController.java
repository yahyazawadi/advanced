package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import main.models.Agents;
import main.services.AgentsDOIAImp;

import java.util.List;

public class AllAgentController {

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Agents> agentTable;

    @FXML
    private TableColumn<Agents, String> idColumn;

    @FXML
    private TableColumn<Agents, String> firstNameColumn;

    @FXML
    private TableColumn<Agents, String> lastNameColumn;

    @FXML
    private TableColumn<Agents, String> emailColumn;

    @FXML
    private TableColumn<Agents, String> phoneColumn;

    @FXML
    private TableColumn<Agents, String> dateCreatedColumn;

    @FXML
    private TableColumn<Agents, String> roleColumn;

    @FXML
    private TableColumn<Agents, String> companyNameColumn;

    @FXML
    private TableColumn<Agents, String> companyAddressColumn;

    @FXML
    private Button deleteAgent;

    private AgentsDOIAImp agentsService = new AgentsDOIAImp();
    private ObservableList<Agents> agentsList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupTable();
        makeTableEditable();
        loadAgents();
        setupSearch();
        setupDeleteButton();
    }

    private void setupTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        dateCreatedColumn.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        companyNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        companyAddressColumn.setCellValueFactory(new PropertyValueFactory<>("companyAddress"));

        makeTableEditable();
    }

    private void loadAgents() {
        List<Agents> agents = agentsService.getAllAgents();
        agentsList.setAll(agents);
        agentTable.setItems(agentsList);
    }

    private void makeTableEditable() {
        agentTable.setEditable(true);

        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameColumn.setOnEditCommit(event -> {
            Agents agent = event.getRowValue();
            agent.setFirstName(event.getNewValue());
            agentsService.update(agent);
        });

        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setOnEditCommit(event -> {
            Agents agent = event.getRowValue();
            agent.setLastName(event.getNewValue());
            agentsService.update(agent);
        });

        emailColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        emailColumn.setOnEditCommit(event -> {
            Agents agent = event.getRowValue();
            agent.setEmail(event.getNewValue());
            agentsService.update(agent);
        });

        phoneColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneColumn.setOnEditCommit(event -> {
            Agents agent = event.getRowValue();
            agent.setPhoneNumber(event.getNewValue());
            agentsService.update(agent);
        });
    }

    private void setupSearch() {
        FilteredList<Agents> filteredData = new FilteredList<>(agentsList, p -> true);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(agent -> {
                if (newValue == null || newValue.isEmpty()) return true;
                String lowerCaseFilter = newValue.toLowerCase();

                return agent.getFirstName().toLowerCase().contains(lowerCaseFilter) ||
                        agent.getLastName().toLowerCase().contains(lowerCaseFilter) ||
                        agent.getEmail().toLowerCase().contains(lowerCaseFilter) ||
                        agent.getPhoneNumber().toLowerCase().contains(lowerCaseFilter);
            });
        });

        agentTable.setItems(filteredData);
    }

    private void setupDeleteButton() {
        deleteAgent.setOnAction(event -> deleteSelectedAgent());
    }

    private void deleteSelectedAgent() {
        Agents selectedAgent = agentTable.getSelectionModel().getSelectedItem();
        if (selectedAgent != null) {
            agentsService.delete(selectedAgent);
            agentsList.remove(selectedAgent);
            agentTable.refresh();
        } else {
            showAlert("No Selection", "Please select an agent to delete.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}