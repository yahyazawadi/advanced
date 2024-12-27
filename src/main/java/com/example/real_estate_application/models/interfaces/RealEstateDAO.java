package com.example.real_estate_application.models.interfaces;

import com.example.real_estate_application.models.RealEstate;
import javafx.stage.Stage;

public interface RealEstateDAO {
    public void save(RealEstate realEstate);
    public void update(RealEstate realEstate);


    public void delete(int id);

    public RealEstate getRealEstateById(int id);

    void start(Stage primaryStage);
}
