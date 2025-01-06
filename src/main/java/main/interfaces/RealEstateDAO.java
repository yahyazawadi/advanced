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
    public List<RealEstate> searchRealEstate(String name);
    public List<RealEstate> filterRealEstates(String priceRange, String propertyType);
}
