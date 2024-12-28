module main.MainApp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires java.sql; // For database connectivity
    requires mysql.connector.java;
    // Optional libraries (remove if unused)
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.desktop;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires jdk.internal.le;
    requires java.naming;    // Allow JavaFX to access controllers and main app using reflection
    opens com.example.real_estate_group14 to javafx.fxml;
    opens controllers to javafx.fxml;
    opens main.models to org.hibernate.orm.core;
    // Export the main package so it can be used externally
    exports main;
    exports main.interfaces;
    exports main.services;
    exports main.models;
}
