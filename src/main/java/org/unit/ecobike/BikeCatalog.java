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

    /**
     * using for test
     * @param resource - name of a file
     * @return path of a resource file
     */
    static BikeCatalog fromResource(String resource) {
        return fromFile(BikeCatalog.class.getClassLoader().getResource(resource).getPath());
    }

    /**
     * add new bike to a bike catalog
     * @param bike - parameter, which contain object for adding to a bike catalog
     */
    void addBike(Bike bike);

    /**
     * provide a search by brand from bike catalog
     * @param brand - parameter, which is using for search
     * @return list of bikes of a given brand
     */
    List<Bike> searchByBrand (String brand);

    /**
     * provide a search of folding bikes by parameters
     * @param brand - name of a brand
     * @param wheelsSize - size of the wheels
     * @param gears - count of gears
     * @param weight - weight of bike
     * @param hasLights - compatible with head and tail lights
     * @param color -color of bike
     * @return list of folding bikes with given parameters
     */
    List<FoldingBike> searchFoldingBikeByParameters(String brand, double wheelsSize, int gears, int weight, Boolean hasLights, String color);

    /**
     * provide a search of E-Bikes by parameters
     * @param brand - name of a brand
     * @param maxSpeed - maximum speed of bike
     * @param weight - weight of bike
     * @param hasLights - compatible with head and tail lights
     * @param batteryCapacity - battery capacity of bike
     * @param color - color of bike
     * @return list of E-Bikes with given parameters
     */
    List<EBike> searchEBikeByParameters(String brand, int maxSpeed, int weight, Boolean hasLights, int batteryCapacity, String color);

    /**
     * provide a search of speedelec bikes by parameters
     * @param brand - name of a brand
     * @param maxSpeed - maximum speed of bike
     * @param weight - weight of bike
     * @param hasLights - compatible with head and tail lights
     * @param batteryCapacity - battery capacity of bike
     * @param color - color of bike
     * @return list of speedelec bikes with given parameters
     */
    List<Speedelec> searchSpeedelecByParameters(String brand, int maxSpeed, int weight, Boolean hasLights, int batteryCapacity, String color);

    /**
     * saves all changes
     */
    void save();

    /**
     * check if we have unsaved changes
     * @return flag "true" or "false"
     */
    boolean hasUnsavedData();
}
