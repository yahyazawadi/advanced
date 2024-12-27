module com.example.real_estate_application {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;

    opens com.example.real_estate_application to javafx.fxml;
    opens com.example.real_estate_application.models;
    exports com.example.real_estate_application;

    requires java.sql;
    requires mysql.connector.java;
    requires java.desktop;

}