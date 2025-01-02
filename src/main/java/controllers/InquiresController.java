package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import main.models.Inquiry;
import main.services.InquiryDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class InquiresController {

    @FXML
    private TableView<Inquiry> receivedInquiriesTable;

    @FXML
    private TextField searchField;

    private InquiryDAOImpl inquiryDAO;

    @FXML
    public void initialize() {
        inquiryDAO = new InquiryDAOImpl();
        loadInquiries();
    }

    private void loadInquiries() {
        List<Inquiry> inquiries = inquiryDAO.getAllInquiries();
        ObservableList<Inquiry> data = FXCollections.observableArrayList(inquiries);
        receivedInquiriesTable.setItems(data);
    }

    @FXML
    private void searchInquiries() {
        String keyword = searchField.getText().toLowerCase();
        List<Inquiry> inquiries = inquiryDAO.getAllInquiries();
        ObservableList<Inquiry> filteredData = FXCollections.observableArrayList();

        for (Inquiry inquiry : inquiries) {
            if (inquiry.getClientName().toLowerCase().contains(keyword)) {
                filteredData.add(inquiry);
            }
        }
        receivedInquiriesTable.setItems(filteredData);
    }
}
