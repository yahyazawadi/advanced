//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

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

    public FilterWindowController() {
    }

    @FXML
    public void initialize() {
        this.priceMenu.getItems().addAll(new String[]{"Under $100,000", "$100,000 - $500,000", "Above $500,000"});
        this.typeMenu.getItems().addAll(new String[]{"detachedhouse", "apartment", "plot of land"});
    }

    public void setFilterListener(FilterListener listener) {
        this.filterListener = listener;
    }

    @FXML
    private void applyFilter() {
        String selectedPrice = (String)this.priceMenu.getValue();
        String selectedType = (String)this.typeMenu.getValue();
        if (this.filterListener != null) {
            this.filterListener.onFilterApplied(selectedPrice, selectedType);
        }

        Stage stage = (Stage)this.priceMenu.getScene().getWindow();
        stage.close();
    }

    public interface FilterListener {
        void onFilterApplied(String var1, String var2);
    }
}
