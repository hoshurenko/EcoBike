package org.unit.ecobike;

import org.unit.ecobike.model.Bike;
import org.unit.ecobike.model.EBike;
import org.unit.ecobike.model.FoldingBike;
import org.unit.ecobike.model.Speedelec;

import java.nio.file.Paths;
import java.util.List;

public interface BikeCatalog {

    /**
     * @return all bikes in the EcoBike catalog
     */
    List<Bike> getAll();

    /**
     * @param file - path to a file, which contains bike catalog
     * @return bike catalog
     */
    static BikeCatalog fromFile(String file) {
        return new FileBasedBikeCatalog(Paths.get(file));
    }

    static BikeCatalog fromResource(String resource) {
        return fromFile(BikeCatalog.class.getClassLoader().getResource(resource).getPath());
    }

    void addBike(Bike bike);
    List<Bike> searchByBrand (String brand);
    List<FoldingBike> searchFoldingBikeByParameters(String brand, double wheelsSize, int gears, int weight, Boolean hasLights, String color);
    List<EBike> searchEBikeByParameters(String brand, int maxSpeed, int weight, Boolean hasLights, int batteryCapacity, String color);
    List<Speedelec> searchSpeedelecByParameters(String brand, int maxSpeed, int weight, Boolean hasLights, int batteryCapacity, String color);

    /**
     * saves all changes
     */
    void save();
    boolean hasUnsavedData();
}
