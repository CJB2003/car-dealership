package com.pluralsight;
import java.io.*;

public class DealershipFileManager {

    private final File file = new File("src/main/resources/inventory.csv");

    public Dealership getDealership() {

        Dealership dealership = null;

        try {

            BufferedReader bReader = new BufferedReader(new FileReader(file));

            String line;

            //Reading the header first and parsing
            if ((line = bReader.readLine()) != null) {

                String[] headerParts = line.split("\\|");
                String name = headerParts[0];
                String address = headerParts[1];
                String phone = headerParts[2];

                dealership = new Dealership(name, address, phone);
            }

            String vehicleInfo;
            //Parsing vehicle information
            while ((vehicleInfo = bReader.readLine()) != null) {

                String[] vehicleParts = vehicleInfo.split("\\|");

                //Assigning parts to respective variables
                int vin = Integer.parseInt(vehicleParts[0]);
                int year = Integer.parseInt(vehicleParts[1]);
                String make = vehicleParts[2];
                String model = vehicleParts[3];
                String vehicleType = vehicleParts[4];
                String color = vehicleParts[5];
                int odometer = Integer.parseInt(vehicleParts[6]);
                double price = Double.parseDouble(vehicleParts[7]);

                //Storing into vehicle object
                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);

                dealership.addVehicle(vehicle);
            }

            bReader.close();

        } catch (Exception e) {
            System.out.println("Couldn't parse inventory file.");
        }
        return dealership;
    }

    public void saveDealership(Dealership dealership) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            //Writing header
            writer.write(String.format("%s|%s|%s%n", dealership.getName(), dealership.getAddress(), dealership.getPhone()));

            //Gets all vehicles and writes them into csv file
            for (Vehicle v : dealership.getAllVehicles()) {
                writer.write(String.format("%d|%d|%s|%s|%s|%s|%d|%.2f%n", v.getVin(), v.getYear(), v.getMake(),
                        v.getModel(), v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice()));
            }
        } catch (Exception e) {
            System.out.println("Could not save to file.");
        }
    }
}
