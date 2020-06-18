package org.unit.ecobike.model;

import java.util.Objects;

public class FoldingBike extends Bike {
    private int wheelsSize;
    private int gears;
    private String light;

    public FoldingBike(String brand, int wheelsSize, int gears, int weight, Boolean hasLights, String color, int price) {
        super(brand, weight, hasLights, color, price);
        this.wheelsSize = wheelsSize;
        this.gears = gears;
        if (hasLights) {
            light = "head/tail";
        } else light = "no head/tail";
    }

    public FoldingBike(Bike bike) {
        super(bike);
    }

    public int getWheelsSize() {
        return wheelsSize;
    }

    public void setWheelsSize(int wheelsSize) {
        this.wheelsSize = wheelsSize;
    }

    public int getGears() {
        return gears;
    }

    public void setGears(int gears) {
        this.gears = gears;
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FoldingBike foldingBike = (FoldingBike) o;
        return super.equals(foldingBike) &&
                wheelsSize == foldingBike.wheelsSize &&
                gears == foldingBike.gears;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), wheelsSize, gears);
    }

    @Override
    public String serialize() {

        return "FOLDING BIKE " +
                brand + "; " +
                wheelsSize + "; " +
                gears + "; " +
                weight + "; " +
                hasLights + "; " +
                color + "; " +
                price;
    }

    @Override
    public String toString() {
        return "FOLDING BIKE " +
                brand +
                " with " + gears + " gear(s) and " +
                light + " light.";
    }
}
