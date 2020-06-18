package org.unit.ecobike;

import org.unit.ecobike.model.Bike;
import org.unit.ecobike.model.EBike;
import org.unit.ecobike.model.FoldingBike;
import org.unit.ecobike.model.Speedelec;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileBasedBikeCatalog implements BikeCatalog {

    private final List<Bike> bikes;
    private final Path path;
    private boolean hasUnsavedData;

    public FileBasedBikeCatalog(Path path) {
        try {
            Stream<String> lines = Files.lines(path);
            bikes = lines.map(Bike::parse)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException("File " + path + " doesn't exist.");
        }
        this.path = path;
    }


    @Override
    public List<Bike> getAll() {
        return bikes;
    }

    @Override
    public void addBike(Bike bike) {
        bikes.add(bike);
        hasUnsavedData = true;
    }

    @Override
    public List<Bike> searchByBrand(String brand) {
        return bikes
                .stream()
                .filter(bike -> {
                    return bike.getBrand().equals(brand);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<FoldingBike> searchFoldingBikeByParameters(String brand, double wheelsSize, int gears, int weight, Boolean hasLights, String color) {
        return getAll()
                .stream()
                .filter(bike -> bike instanceof FoldingBike)
                .map(bike -> (FoldingBike) bike)
                .filter(foldingBike -> {
                    if (foldingBike.getBrand().equals(brand)) {
                        if (foldingBike.getWheelsSize() == wheelsSize || wheelsSize == 0) {
                            if (foldingBike.getGears() == gears || gears == 0) {
                                if (foldingBike.getWeight() == weight || weight == 0) {
                                    if (foldingBike.isHasLights() == hasLights || hasLights == null) { // is this correct? default == true
                                        return foldingBike.getColor().equals(color) || color.isEmpty();
                                    }
                                }
                            }
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<EBike> searchEBikeByParameters(String brand, int maxSpeed, int weight, Boolean hasLights, int batteryCapacity, String color) {
        return getAll()
                .stream()
                .filter(bike -> bike instanceof EBike)
                .map(bike -> (EBike) bike)
                .filter(EBike -> {
                    if (EBike.getBrand().equals(brand)) {
                        if (EBike.getMaxSpeed() == maxSpeed || maxSpeed == 0) {
                            if (EBike.getWeight() == weight || weight == 0) {
                                if (EBike.isHasLights() == hasLights || hasLights == null) {
                                    if (EBike.getBatteryCapacity() == batteryCapacity || batteryCapacity == 0) {
                                        return EBike.getColor().equals(color) || color.isEmpty();
                                    }
                                }
                            }
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Speedelec> searchSpeedelecByParameters(String brand, int maxSpeed, int weight, Boolean hasLights, int batteryCapacity, String color) {
        return getAll()
                .stream()
                .filter(bike -> bike instanceof Speedelec)
                .map(bike -> (Speedelec) bike)
                .filter(speedelec -> {
                    if (speedelec.getBrand().equals(brand)) {
                        if (speedelec.getMaxSpeed() == maxSpeed || maxSpeed == 0) {
                            if (speedelec.getWeight() == weight || weight == 0) {
                                if (speedelec.isHasLights() == hasLights || hasLights == null) {
                                    if (speedelec.getBatteryCapacity() == batteryCapacity || batteryCapacity == 0) {
                                        if (speedelec.getColor().equals(color) || color.isEmpty())
                                            return true;
                                    }
                                }
                            }
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void save() {
        List<String> strings = bikes
                .stream()
                .map(Bike::serialize)
                .collect(Collectors.toList());
        try {
            Files.write(path, strings);
            hasUnsavedData = false;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean hasUnsavedData() {
        return hasUnsavedData;
    }


}
