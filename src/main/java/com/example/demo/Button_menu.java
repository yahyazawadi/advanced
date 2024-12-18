package com.example.demo;



import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

public class Button_menu {

    @FXML
    private Button contentButton;

    @FXML
    public void initialize() {
        // إنشاء قائمة الخيارات (منيو)
        ContextMenu contextMenu = new ContextMenu();

        // إضافة عناصر إلى المنيو
        MenuItem option1 = new MenuItem("Option 1");
        MenuItem option2 = new MenuItem("Option 2");
        MenuItem option3 = new MenuItem("Option 3");

        // إضافة العناصر إلى ContextMenu
        contextMenu.getItems().addAll(option1, option2, option3);

        // ربط الزر بحدث الضغط بزر الفأرة الأيمن لإظهار المنيو
        contentButton.setOnContextMenuRequested(event -> {
            contextMenu.show(contentButton, event.getScreenX(), event.getScreenY());
        });
    }
}
