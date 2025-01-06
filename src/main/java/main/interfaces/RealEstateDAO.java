package main.interfaces;

import main.models.RealEstate;
import javafx.stage.Stage;

import java.util.List;

public interface RealEstateDAO {
    public void save(RealEstate realEstate);
    public void update(RealEstate realEstate);
    public List<RealEstate> getAllRealEstates();

    public void delete(int id);

    public RealEstate getRealEstateById(int id);

    void start(Stage primaryStage);


    List<RealEstate> searchRealEstate(String var1);

    List<RealEstate> filterRealEstates(String var1, String var2);
}
