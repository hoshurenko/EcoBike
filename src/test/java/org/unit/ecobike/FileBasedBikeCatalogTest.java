package org.unit.ecobike;

import org.junit.jupiter.api.Test;
import org.unit.ecobike.model.Bike;
import org.unit.ecobike.model.EBike;
import org.unit.ecobike.model.FoldingBike;
import org.unit.ecobike.model.Speedelec;

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

    @Test
    void searchBikeByBrand() { //SPEEDELEC Booster; 35; 10900; false; 13200; green; 1279
        BikeCatalog bikeCatalog = BikeCatalog.fromResource("ecobike.txt");
        List<Bike> bikes = bikeCatalog.searchByBrand("Booster");
        String brand = bikes.get(0).getBrand();
        Bike testBike = new Speedelec("Booster", 35, 10900, false, 13200, "green", 1279);
        assertEquals(testBike.getBrand(), brand);
    }

    @Test
    void searchFoldingBikeByParameters() { // FOLDING BIKE Benetti; 24; 27; 11400; false; rose; 1009
        BikeCatalog bikeCatalog = BikeCatalog.fromResource("ecobike.txt");
        FoldingBike foldingBike = bikeCatalog.searchFoldingBikeByParameters("Benetti", 24, 27, 11400, false, "rose").get(0);
        Bike testBike = new FoldingBike("Benetti", 24, 27, 11400, false, "rose", 1009);
        assertEquals(testBike, foldingBike);
    }

    @Test
    void searchSpeedelecByParameters() { // SPEEDELEC Booster; 35; 10900; false; 13200; green; 1279
        BikeCatalog bikeCatalog = BikeCatalog.fromResource("ecobike.txt");
        Speedelec speedelec = bikeCatalog.searchSpeedelecByParameters("Booster", 35, 10900, false, 13200, "green").get(0);
        Bike testBike = new Speedelec("Booster", 35, 10900, false, 13200, "green", 1279);
        assertEquals(testBike, speedelec);
    }

    @Test
    void searchEBikeByParameters() { // E-BIKE Lankeleisi; 65; 24200; false; 10000; black; 2399
        BikeCatalog bikeCatalog = BikeCatalog.fromResource("ecobike.txt");
        EBike eBike = bikeCatalog.searchEBikeByParameters("Lankeleisi", 65, 24200, false, 10000, "black").get(0);
        Bike testBike = new EBike("Lankeleisi", 65, 24200, false, 10000, "black", 2399);
        assertEquals(testBike, eBike);
    }


}