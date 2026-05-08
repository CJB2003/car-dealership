package com.pluralsight;

import java.util.ArrayList;

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

    public ArrayList<Vehicle> getVehiclesByPrice() {
        return null;
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return this.inventory;
    }

    public void ArrayList<Vehicle> addVehicle(Vehicle vehicle) {
        this.inventory.add(vehicle);
    }


}
