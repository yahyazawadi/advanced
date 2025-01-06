package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import main.interfaces.DashBoardAdminDOA;
import main.imp.DashBoardAdminDAOImp;

import java.net.URL;
import java.util.ResourceBundle;

public class DashBoardAdminController implements Initializable {

    @FXML
    private Text clientsCounter;

    @FXML
    private Text apartmentsCounter;

    @FXML
    private Text landsCounter;

    @FXML
    private Text homesCounter;

    @FXML
    private Text agentsCounter;

    @FXML
    private Text agreementsCounter;

    private DashBoardAdminDOA dashboardService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dashboardService = new DashBoardAdminDAOImp();

        updateClientsCount();
        updateApartmentsCount();
        updateLandsCount();
        updateHomesCount();
        updateAgentsCount();
        updateAgreementsCount();
    }

    private void updateClientsCount() {
        try {
            int clientsCount = dashboardService.getClientsCount();
            clientsCounter.setText(String.valueOf(clientsCount));
        } catch (Exception e) {
            e.printStackTrace();
            clientsCounter.setText("Error");
        }
    }

    private void updateApartmentsCount() {
        try {
            int apartmentsCount = dashboardService.getApartmentCount();
            apartmentsCounter.setText(String.valueOf(apartmentsCount));
        } catch (Exception e) {
            e.printStackTrace();
            apartmentsCounter.setText("Error");
        }
    }

    private void updateLandsCount() {
        try {
            int landsCount = dashboardService.getLandCount();
            landsCounter.setText(String.valueOf(landsCount));
        } catch (Exception e) {
            e.printStackTrace();
            landsCounter.setText("Error");
        }
    }

    private void updateHomesCount() {
        try {
            int homesCount = dashboardService.getHomeCount();
            homesCounter.setText(String.valueOf(homesCount));
        } catch (Exception e) {
            e.printStackTrace();
            homesCounter.setText("Error");
        }
    }

    private void updateAgentsCount() {
        try {
            int agentsCount = dashboardService.getAgentsCount();
            agentsCounter.setText(String.valueOf(agentsCount));
        } catch (Exception e) {
            e.printStackTrace();
            agentsCounter.setText("Error");
        }
    }

    private void updateAgreementsCount() {
        try {
            int agreementsCount = dashboardService.getAgreementsCount();
            agreementsCounter.setText(String.valueOf(agreementsCount));
        } catch (Exception e) {
            e.printStackTrace();
            agreementsCounter.setText("Error");
        }
    }
}
