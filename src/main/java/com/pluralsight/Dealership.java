package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Dealership {

    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<Vehicle>();
    }

    //Method for finding vehicles by a price range
    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max) {

        ArrayList<Vehicle> vPrice = new ArrayList<>();
        for (Vehicle vehicle : this.inventory) {
            if (vehicle.getPrice() >= min && vehicle.getPrice() <= max) {
                vPrice.add(vehicle);
            }
        }
        return vPrice;
    }

    //Method for finding vehicles by make and model, takes in make and model and compares through array list
    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) {

        ArrayList<Vehicle> makeModel = new ArrayList<>();
        for (Vehicle vehicle : this.inventory) {
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)) {
                makeModel.add(vehicle);
            }
        }
        return makeModel;
    }
    //Method for finding vehicles by year with a range
    public ArrayList<Vehicle> getVehiclesByYear(int max, int min) {

        ArrayList<Vehicle> year = new ArrayList<>();
        for (Vehicle vehicle : this.inventory) {
            if (vehicle.getYear() >= min && vehicle.getYear() <= max) {
                year.add(vehicle);
            }
        }
        return year;
    }

    //Method for finding vehicles by color
    public ArrayList<Vehicle> getVehiclesByColor(String color) {

        ArrayList<Vehicle> vColor = new ArrayList<>();
        for (Vehicle vehicle : this.inventory) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                vColor.add(vehicle);
            }
        }
        return vColor;
    }

    //Method for finding vehicles by mileage
    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max) {
        return null;
    }

    //Method for finding vehicles by type
    public ArrayList<Vehicle> getVehiclesByType(String vehicleType) {
        return null;
    }

    //Returns all vehicles in array list
    public ArrayList<Vehicle> getAllVehicles() {
        return this.inventory;
    }

    //Method for adding vehicles
    public void addVehicle(Vehicle vehicle) {

    }

    //Method for removing vehicles
    public void removeVehicle(Vehicle vehicle) {

    }
}
