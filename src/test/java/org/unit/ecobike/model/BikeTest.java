package org.unit.ecobike.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BikeTest {

    @Test
    void parse() {
        System.out.println(Bike.parse("SPEEDELEC Booster; 35; 10900; false; 13200; green; 1279"));
        System.out.println(Bike.parse("E-BIKE Lankeleisi; 65; 24200; false; 10000; black; 2399"));
        System.out.println(Bike.parse("FOLDING BIKE Benetti; 24; 27; 11400; false; rose; 1009"));

    }

    @Test
    void serializeSpedelecBike() {
        System.out.println(new Speedelec("Booster", 35, 10900, false, 13200, "green", 1279).serialize());
        assertEquals("SPEEDELEC Booster; 35; 10900; false; 13200; green; 1279",
                new Speedelec("Booster", 35, 10900, false, 13200, "green", 1279).serialize());
    }

    @Test
    void serializeEBike() {
        assertEquals("E-BIKE Lankeleisi; 65; 24200; false; 10000; black; 2399",
                new EBike("Lankeleisi", 65, 24200, false, 10000, "black", 2399).serialize());
    }

    @Test
    void serializeFoldingBike() {
        assertEquals("FOLDING BIKE Benetti; 24; 27; 11400; false; rose; 1009",
                new FoldingBike("Benetti", 24, 27, 11400, false, "rose", 1009).serialize());
    }

    @Test
    void parseSpeedelecBike() {
        assertEquals(
                new Speedelec("Booster", 35, 10900, false, 13200, "green", 1279),
                Bike.parse("SPEEDELEC Booster; 35; 10900; false; 13200; green; 1279"));

    }

    @Test
    void parseEBike() {
        assertEquals(
                new EBike("Lankeleisi", 65, 24200, false, 10000, "black", 2399),
                Bike.parse("E-BIKE Lankeleisi; 65; 24200; false; 10000; black; 2399"));
    }

    @Test
    void parseFoldingBike() {
        assertEquals(
                new FoldingBike("Benetti", 24, 27, 11400, false, "rose", 1009),
                Bike.parse("FOLDING BIKE Benetti; 24; 27; 11400; false; rose; 1009"));
    }
}