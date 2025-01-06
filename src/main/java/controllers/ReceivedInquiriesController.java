package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ReceivedInquiriesController {

    private int propertyId;

    @FXML
    private Label inquiriesLabel;

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
        loadInquiries();
    }

    private void loadInquiries() {
        // Logic to fetch and display inquiries based on propertyId
        inquiriesLabel.setText("Displaying inquiries for property ID: " + propertyId);
    }
}
