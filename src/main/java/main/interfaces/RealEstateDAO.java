package main.interfaces;

import main.models.RealEstate;
import javafx.stage.Stage;

public interface RealEstateDAO {
    public void save(RealEstate realEstate);
    public void update(RealEstate realEstate);


    public void delete(int id);

    public RealEstate getRealEstateById(int id);

    void start(Stage primaryStage);
}
