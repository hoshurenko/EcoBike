package org.unit.ecobike;

import org.unit.ecobike.model.Bike;
import org.unit.ecobike.model.EBike;
import org.unit.ecobike.model.FoldingBike;
import org.unit.ecobike.model.Speedelec;

import java.util.List;
import java.util.Scanner;

import static org.unit.ecobike.BikeCatalog.fromFile;

public class Application {
    private final BikeCatalog bikeCatalog;
    private final Scanner scanner = new Scanner(System.in);


    public Application(BikeCatalog bikeCatalog) {
        this.bikeCatalog = bikeCatalog;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new RuntimeException("First argument must be a path to BikeCatalog file.");
        }
        BikeCatalog bikeCatalog = fromFile(args[0]);
        new Application(bikeCatalog).run();
    }

    public void run() {
        List<Bike> bikes = bikeCatalog.getAll();
        while (true) {
            println("1 - Show the entire EcoBike catalog\n" +
                    "2 – Add a new folding bike\n" +
                    "3 – Add a new speedelec\n" +
                    "4 – Add a new e-bike\n" +
                    "5 – Find the first item of a particular brand\n" +
                    "6 – Write to file\n" +
                    "7 – Stop the program ");
            String optionString = scanner.next();
            int option;
            try {
                option = Integer.parseInt(optionString); // thrown where illegal type
            } catch (RuntimeException e) {
//                e.printStackTrace();
                println("Incorrect input! Try again.");
                continue;
            }

            switch (option) {
                case 1: //show the entire EcoBike catalog
                    bikes.forEach(System.out::println);
                    break;
                case 2: // add a new folding bike
                    addNewFoldingBike();
                    break;
                case 3: // add a new speedelec
                    addNewSpeedelec();
                    break;
                case 4: // add a new E-BIKE
                    addNewEBike();
                    break;
                case 5: // find the first item of a particular brand
                    search();
                    break;
                case 6: // write to file
                    bikeCatalog.save();
                    break;
                case 7: // stop the program
                    stopTheProgram();
                    return;
                default:
                    println("Option " + option + " is not supported!");
                    break;
            }
        }
    }

    private void println(String message) {
        System.out.println(message);
    }

    private void stopTheProgram() {
        if (bikeCatalog.hasUnsavedData()) {
            while (true) {

            }
        }
        while (true) {
            println("Do you want to save changes? (Y/N):");
            String exitOption = scanner.next();
            if (exitOption.toUpperCase().equals("Y")) {
                bikeCatalog.save();
                println("Changes saved. \nBye.");
                return;
            } else if (exitOption.toUpperCase().equals("N")) {
                println("You exit the program without saving changes! \nBye.");
                return;
            } else {
                println("Incorrect input. Please, enter \" y/Y \" or \" n/N \"");
            }
        }
    }

    private void search() {
        println("Enter a brand: ");
        String brand = scanner.next();
        List<Bike> bikeList = bikeCatalog.searchByBrand(brand);
        try {
            Bike bike = bikeList.get(0);
            String[] bikeFields = bike.toString().split(";");
            if (bikeFields[0].contains("FOLDING BIKE")) {
                println("Enter the size of the wheels (in inch) or ENTER to skip: ");
                String wheelsSizeString = scanner.next();
                int wheelsSize = wheelsSizeString.isEmpty() ? 0 : Integer.parseInt(wheelsSizeString);
                println("Enter the number of gears or ENTER to skip: ");
                String gearsString = scanner.next();
                int gears = gearsString.isEmpty() ? 0 : Integer.parseInt(gearsString);
                println("Enter the weight of the bike (in grams) or ENTER to skip: ");
                String weightString = scanner.next();
                int weight = weightString.isEmpty() ? 0 : Integer.parseInt(weightString);
                println("Enter the availability of lights at front and back (TRUE/FALSE) or ENTER to skip: ");
                String hasLightsString = scanner.next();
                Boolean hasLights = hasLightsString.isEmpty() ? null : Boolean.parseBoolean(hasLightsString); // is this OK?
                println("Enter a color or ENTER to skip: ");
                String colorString = scanner.next();
                String color = colorString.isEmpty() ? "" : colorString;
                List<FoldingBike> foldingBikes = bikeCatalog.searchFoldingBikeByParameters(brand, wheelsSize, gears, weight, hasLights, color);
                System.out.println(foldingBikes.get(0)); // select first item of a particular brand
            } else if (bikeFields[0].contains("E-BIKE")) {
                println("Enter the maximum speed (in km/h)  or ENTER to skip: ");
                String maxSpeedString = scanner.next();
                int maxSpeed = maxSpeedString.isEmpty() ? 0 : Integer.parseInt(maxSpeedString);
                println("Enter the weight of the bike (in grams) or ENTER to skip: ");
                String weightString = scanner.next();
                int weight = weightString.isEmpty() ? 0 : Integer.parseInt(weightString);
                println("Enter the availability of lights at front and back (TRUE/FALSE) or ENTER to skip: ");
                String hasLightsString = scanner.next();
                Boolean hasLights = hasLightsString.isEmpty() || Boolean.parseBoolean(hasLightsString); // is this OK?
                println("Enter the battery capacity (in mAh) or ENTER to skip: ");
                String batteryCapacityString = scanner.next();
                int batteryCapacity = batteryCapacityString.isEmpty() ? 0 : Integer.parseInt(batteryCapacityString);
                println("Enter a color or ENTER to skip: ");
                String colorString = scanner.next();
                String color = colorString.isEmpty() ? "" : colorString;
                List<EBike> Ebikes = bikeCatalog.searchEBikeByParameters(brand, maxSpeed, weight, hasLights, batteryCapacity, color);
                System.out.println(Ebikes.get(0)); // select first item of a particular brand
            } else if (bikeFields[0].contains("SPEEDELEC")) {
                println("Enter the maximum speed (in km/h)  or ENTER to skip: ");
                String maxSpeedString = scanner.next();
                int maxSpeed = maxSpeedString.isEmpty() ? 0 : Integer.parseInt(maxSpeedString);
                println("Enter the weight of the bike (in grams) or ENTER to skip: ");
                String weightString = scanner.next();
                int weight = weightString.isEmpty() ? 0 : Integer.parseInt(weightString);
                println("Enter the availability of lights at front and back (TRUE/FALSE) or ENTER to skip: ");
                String hasLightsString = scanner.next();
                Boolean hasLights = hasLightsString.isEmpty() || Boolean.parseBoolean(hasLightsString); // is this OK?
                println("Enter the battery capacity (in mAh) or ENTER to skip: ");
                String batteryCapacityString = scanner.next();
                int batteryCapacity = batteryCapacityString.isEmpty() ? 0 : Integer.parseInt(batteryCapacityString);
                println("Enter a color or ENTER to skip: ");
                String colorString = scanner.next();
                String color = colorString.isEmpty() ? "" : colorString;
                List<Speedelec> speedelecs = bikeCatalog.searchSpeedelecByParameters(brand, maxSpeed, weight, hasLights, batteryCapacity, color);
                System.out.println(speedelecs.get(0)); // select first item of a particular brand
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            println("Incorrect input! Try again. " + e.getMessage());
        }
    }

    private void addNewFoldingBike() {
        try {
            println("Enter a brand of bike: ");
            String brand = scanner.next();
            println("Enter the size of the wheels (in inch): ");
            int wheelsSize = scanner.nextInt();
            println("Enter the number of gears: ");
            int gears = scanner.nextInt();
            println("Enter the weight of the bike (in grams): ");
            int weight = scanner.nextInt();
            println("Enter the availability of lights at front and back (TRUE/FALSE): ");
            Boolean hasLights = scanner.nextBoolean();
            println("Enter a color: ");
            String color = scanner.next();
            println("Enter the price: ");
            int price = scanner.nextInt();
            Bike foldingBike = new FoldingBike(brand, wheelsSize, gears, weight, hasLights, color, price);
            bikeCatalog.addBike(foldingBike);
            println("Done! The new Folding Bike was added!");
        } catch (RuntimeException e) {
            e.printStackTrace();
            println("Incorrect input! Try again. " + e.getMessage());
        }
    }

    private void addNewSpeedelec() { //SPEEDELEC Booster; 35; 10900; false; 13200; green; 1279
        try {
            println("Enter a brand of bike: ");
            String brand = scanner.next();
            println("Enter the maximum (in km/h): ");
            int maxSpeed = scanner.nextInt();
            println("Enter the weight of the bike (in grams): ");
            int weight = scanner.nextInt();
            println("Enter the availability of lights at front and back (TRUE/FALSE): ");
            Boolean hasLights = scanner.nextBoolean();
            println("Enter the battery capacity (in mAh): ");
            int batteryCapacity = scanner.nextInt();
            println("Enter a color: ");
            String color = scanner.next();
            println("Enter the price: ");
            int price = scanner.nextInt();
            Bike speedeklecBike = new Speedelec(brand, maxSpeed, weight, hasLights, batteryCapacity, color, price);
            bikeCatalog.addBike(speedeklecBike);
            println("Done! The new Speedelec was added!");
        } catch (RuntimeException e) {
            println("Incorrect input! Try again.");
        }
    }

    private void addNewEBike() { //E-BIKE Lankeleisi; 65; 24200; false; 10000; black; 2399
        try {
            println("Enter a brand of bike: ");
            String brand = scanner.next();
            println("Enter the maximum (in km/h): ");
            int maxSpeed = scanner.nextInt();
            println("Enter the weight of the bike (in grams): ");
            int weight = scanner.nextInt();
            println("Enter the availability of lights at front and back (TRUE/FALSE): ");
            Boolean hasLights = scanner.nextBoolean();
            println("Enter the battery capacity (in mAh): ");
            int batteryCapacity = scanner.nextInt();
            println("Enter a color: ");
            String color = scanner.next();
            println("Enter the price: ");
            int price = scanner.nextInt();
            Bike eBike = new EBike(brand, maxSpeed, weight, hasLights, batteryCapacity, color, price);
            bikeCatalog.addBike(eBike);
            println("Done! The new E-BIKE was added!");
        } catch (RuntimeException e) {
            println("Incorrect input! Try again.");
        }
    }
}
