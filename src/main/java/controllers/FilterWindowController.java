package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class FilterWindowController {

    @FXML
    private ChoiceBox<String> priceMenu;

    @FXML
    private ChoiceBox<String> typeMenu;

    private FilterListener filterListener;

    @FXML
    public void initialize() {

        priceMenu.getItems().addAll("Under $100,000", "$100,000 - $500,000", "Above $500,000");
        typeMenu.getItems().addAll("detachedhouse","apartment","plot of land");
    }

    public void setFilterListener(FilterListener listener) {
        this.filterListener = listener;
    }

    @FXML
    private void applyFilter() {
        String selectedPrice = priceMenu.getValue();
        String selectedType = typeMenu.getValue();

        if (filterListener != null) {
            filterListener.onFilterApplied(selectedPrice, selectedType);
        }


        Stage stage = (Stage) priceMenu.getScene().getWindow();
        stage.close();
    }

    public interface FilterListener {
        void onFilterApplied(String priceRange, String propertyType);
    }
}
