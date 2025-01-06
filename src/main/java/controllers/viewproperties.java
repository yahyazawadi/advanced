package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

import java.io.IOException;

public class viewproperties {

    @FXML
    private Button editButton; // Fixed fx:id name (use 'editButton')
    @FXML
    private ScrollPane contentArea; // fx:id for the ScrollPane

    /**
     * Initializes the controller after FXML is loaded.
     */
    @FXML
    public void initialize() {
        // Attach event handler for editButton
    }

    /**
     * Handles the Edit button click and loads the EditAndDelete.fxml page.
     */
    public void handleEditButtonAction(ActionEvent actionEvent) {
        loadPage("/fxml/EditAndDelete.fxml"); // Correct FXML file path
    }

    /**
     * Loads any FXML page into the content area dynamically.
     *
     * @param fxmlFile The path to the FXML file.
     */
    public void loadPage(String fxmlFile) {
        try {
            // Load FXML content
            Node content = FXMLLoader.load(getClass().getResource(fxmlFile));

            // Set the loaded content as the ScrollPane's content
            contentArea.setContent(content);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load " + fxmlFile);
        }
    }
}
