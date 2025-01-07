package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import main.interfaces.DashboardAgentsDOA;
import main.imp.DashBoardAgentDAOImp;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardAgentsController implements Initializable {

    @FXML
    private Text apartmentsCounter;

    @FXML
    private Text landsCounter;

    @FXML
    private Text homesCounter;

    @FXML
    private Text agreementsCounter;

    private DashboardAgentsDOA dashboardAgentsDOA;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dashboardAgentsDOA = new DashBoardAgentDAOImp();

        updateApartmentsCount();
        updateLandsCount();
        updateHomesCount();
        updateAgreementsCount();
    }

    private void updateApartmentsCount() {
        try {
            int apartmentsCount = dashboardAgentsDOA.getApartmentCount();
            apartmentsCounter.setText(String.valueOf(apartmentsCount));
        } catch (Exception e) {
            e.printStackTrace();
            apartmentsCounter.setText("Error");
        }
    }

    private void updateLandsCount() {
        try {
            int landsCount = dashboardAgentsDOA.getLandCount();
            landsCounter.setText(String.valueOf(landsCount));
        } catch (Exception e) {
            e.printStackTrace();
            landsCounter.setText("Error");
        }
    }

    private void updateHomesCount() {
        try {
            int homesCount = dashboardAgentsDOA.getHomeCount();
            homesCounter.setText(String.valueOf(homesCount));
        } catch (Exception e) {
            e.printStackTrace();
            homesCounter.setText("Error");
        }
    }

    private void updateAgreementsCount() {
        try {
            int agreementsCount = dashboardAgentsDOA.getAgreementsCount();
            agreementsCounter.setText(String.valueOf(agreementsCount));
        } catch (Exception e) {
            e.printStackTrace();
            agreementsCounter.setText("Error");
        }
    }
}
