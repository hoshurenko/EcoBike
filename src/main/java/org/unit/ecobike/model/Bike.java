package org.unit.ecobike.model;

import java.util.Objects;

public abstract class Bike {
    protected String brand;
    protected int weight;
    protected Boolean hasLights;
    protected String color;
    protected int price;

    public Bike(String brand, int weight, boolean hasLights, String color, int price) {
        this.brand = brand;
        this.weight = weight;
        this.hasLights = hasLights;
        this.color = color;
        this.price = price;
    }

    public Bike(Bike bike) {

    }


    public static Bike parse(String input) {
        String[] fields = input.split("; ");
        if (fields[0].contains("SPEEDELEC")) {
            String brand = fields[0].replace("SPEEDELEC ", "");
            return new Speedelec(
                    brand,
                    Integer.parseInt(fields[1]),
                    Integer.parseInt(fields[2]),
                    Boolean.parseBoolean(fields[3]),
                    Integer.parseInt(fields[4]),
                    fields[5],
                    Integer.parseInt(fields[6]));
        } else if (fields[0].contains("E-BIKE")) {
            String brand = fields[0].replace("E-BIKE ", "");
            return new EBike(
                    brand,
                    Integer.parseInt(fields[1]),
                    Integer.parseInt(fields[2]),
                    Boolean.parseBoolean(fields[3]),
                    Integer.parseInt(fields[4]),
                    fields[5],
                    Integer.parseInt(fields[6]));
        } else if (fields[0].contains("FOLDING BIKE")) {
            String brand = fields[0].replace("FOLDING BIKE ", "");
            return new FoldingBike(
                    brand,
                    Integer.parseInt(fields[1]),
                    Integer.parseInt(fields[2]),
                    Integer.parseInt(fields[3]),
                    Boolean.parseBoolean(fields[4]),
                    fields[5],
                    Integer.parseInt(fields[6]));
        } else {
            throw new RuntimeException("Couldn't parse: " + input);
        }

    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Boolean isHasLights() {
        return hasLights;
    }

    public void setHasLights(Boolean hasLights) {
        this.hasLights = hasLights;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        Bike bike = (Bike) o;
        return weight == bike.weight &&
                hasLights == bike.hasLights &&
                Double.compare(bike.price, price) == 0 &&
                Objects.equals(brand, bike.brand) &&
                Objects.equals(color, bike.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, weight, hasLights, color, price);
    }

    public abstract String serialize();

}
