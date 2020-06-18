package org.unit.ecobike;

import org.junit.jupiter.api.Test;
import org.unit.ecobike.model.Bike;
import org.unit.ecobike.model.FoldingBike;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileBasedBikeCatalogTest {

    @Test
    void getAll() {
        assertEquals(
                1000, BikeCatalog.fromResource("ecobike.txt").getAll().size());
    }

    @Test
    void addBike() {
        BikeCatalog bikeCatalog = BikeCatalog.fromResource("ecobike.txt");
        Bike testBike = new FoldingBike("Trek", 35, 10900, 13200, false, "green", 1279);
        bikeCatalog.addBike(testBike);
        List<Bike> allBikes = bikeCatalog.getAll();
        assertTrue(allBikes.contains(testBike));

//        boolean containsBike = allBikes
//                .stream()
//                .anyMatch(bike -> testBike.equals(bike));
    }



}