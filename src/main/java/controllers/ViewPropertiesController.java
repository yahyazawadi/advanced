package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import main.models.RealEstate;
import main.imp.RealEstateDAOImp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public class ViewPropertiesController {
    @FXML
    public AnchorPane todeleteArea;
    @FXML
    private GridPane contentArea;
    private Button backButton;
    private RealEstateDAOImp realEstateDAO = new RealEstateDAOImp();

    @FXML
    public void initialize() {
        // عرض البيانات عند تهيئة الصفحة
        contentArea.setHgap(20);  // زيادة المسافة بين الأعمدة
        contentArea.setVgap(20);  // زيادة المسافة بين الصفوف
        displayRealEstateCards();
    }

    /**
     * Initializes the controller after FXML is loaded.
     */


    /**
     * Handles the Edit button click and loads the edit.fxml page.
     */


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
            contentArea.getContentBias();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load " + fxmlFile);
        }
    }
    private void displayRealEstateCards() {
        // استرجاع جميع العقارات من قاعدة البيانات
        List<RealEstate> realEstates = realEstateDAO.getAllRealEstates();

        // تفريغ الشبكة
        contentArea.getChildren().clear();

        int row = 0;
        int col = 0;

        for (RealEstate realEstate : realEstates) {
            // إنشاء بطاقة
            AnchorPane card = createCard(realEstate);

            // إضافتها إلى الشبكة
            contentArea.add(card, col, row);

            col++;
            if (col == 3) { // عدد الأعمدة (يمكن تغييره)
                col = 0;
                row++;
            }
        }
    }

    private AnchorPane createCard(RealEstate realEstate) {
        AnchorPane card = new AnchorPane();
        card.setStyle("-fx-background-color: #FFFFFF; -fx-border-color: #CCCCCC; -fx-border-width: 1; -fx-padding: 10;");
        card.setPrefWidth(380);
        card.setPrefHeight(300);  // زيادة الارتفاع قليلاً

        // الصورة (تأخذ عرض البطاقة بالكامل)
        ImageView imageView = new ImageView();
        imageView.setFitWidth(360);  // تعيين عرض الصورة داخل حدود البطاقة
        imageView.setFitHeight(180); // تحديد ارتفاع الصورة
        if (realEstate.getImag() != null) {
            Image image = new Image(new ByteArrayInputStream(realEstate.getImag()));
            imageView.setImage(image);
        } else {
            imageView.setImage(new Image(getClass().getResource("/images/placeholder.png").toExternalForm()));
        }
        imageView.setLayoutX(10);  // تعيين الصورة بحيث تأخذ المسافة من الجوانب
        imageView.setLayoutY(0);  // وضع الصورة في الجزء العلوي من البطاقة

        // العنوان (يظهر أولًا)
        Label titleLabel = new Label(realEstate.getNameOfProperty());
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333333;");
        titleLabel.setLayoutX(10);  // تحديد المسافة من الجوانب
        titleLabel.setLayoutY(imageView.getLayoutY() + imageView.getFitHeight() + 10); // وضع العنوان أسفل الصورة

        // الوصف (يظهر ثانيًا)
        Label descriptionLabel = new Label(realEstate.getDescription());
        descriptionLabel.setWrapText(true);
        descriptionLabel.setPrefWidth(360);  // عرض الوصف داخل حدود البطاقة
        descriptionLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #777777;");
        descriptionLabel.setLayoutX(10);
        descriptionLabel.setLayoutY(titleLabel.getLayoutY() + titleLabel.getHeight() + 15);  // إضافة مسافة بين العنوان والوصف

        // السعر (يظهر ثالثًا)
        Label priceLabel = new Label("Price: $" + realEstate.getPrice());
        priceLabel.setStyle("-fx-text-fill: #009688; -fx-font-size: 14px; -fx-font-weight: bold;");
        priceLabel.setLayoutX(10);
        priceLabel.setLayoutY(descriptionLabel.getLayoutY() + descriptionLabel.getHeight() + 15);  // إضافة مسافة بين الوصف والسعر

        // إضافة المكونات إلى البطاقة
        card.getChildren().addAll(imageView, titleLabel, descriptionLabel, priceLabel);

        // إضافة معالج الحدث لجعل البطاقة قابلة للضغط
        card.setOnMouseClicked(event -> {
            openPropertyDetail(realEstate);
        });

        return card;
    }


    private void openPropertyDetail(RealEstate realEstate) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PropertyDetail.fxml"));
            Parent detailView = loader.load();

            // تمرير بيانات العقار
            loader.<PropertyDetailController>getController().setRealEstate(realEstate);
            // Update the contentArea with the new detail view
           // contentArea.getChildren().clear();
            todeleteArea.getChildren().add(detailView);
            // تحديث محتوى الصفحة
            todeleteArea.getChildren().setAll(detailView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}