package com.pluralsight;

public class UserInterface {

    private Dealership dealership;

    public void display() {

        this.init;
    }

    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager();

        this.dealership = fileManager.getDealership();
    }
}
