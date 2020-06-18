package org.unit.ecobike.model;

import java.util.Objects;

public class EBike extends Bike{
    private int maxSpeed;
    private int batteryCapacity;
    private String light;

    public EBike(String brand, int maxSpeed, int weight, boolean hasLights, int batteryCapacity, String color, int price) {
        super(brand, weight, hasLights, color, price);
        this.maxSpeed = maxSpeed;
        this.batteryCapacity = batteryCapacity;
        if (hasLights) {
            light = "head/tail";
        } else light = "no head/tail";
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EBike eBike = (EBike) o;
        return super.equals(eBike) &&
                maxSpeed == eBike.maxSpeed &&
                batteryCapacity == eBike.batteryCapacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxSpeed, batteryCapacity);
    }

    @Override
    public String serialize() {
        return "E-BIKE " +
                brand + "; " +
                maxSpeed + "; " +
                weight + "; " +
                hasLights + "; " +
                batteryCapacity + "; " +
                color + "; " +
                price;
    }

    @Override
    public String toString() {
        return "E-BIKE " +
                brand +
                " with " + batteryCapacity + " mAh battery and " +
                light + " light.";
    }
}
