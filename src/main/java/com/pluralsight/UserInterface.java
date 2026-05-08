package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private final Scanner myScanner = new Scanner(System.in);
    private Dealership dealership;

    //Empty Constructor
    public UserInterface() {

    }

    //Initialize method
    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager();
        this.dealership = fileManager.getDealership();
    }

    //Display method
    public void display() {
        this.init();

        boolean menuOpen = true;

        while(menuOpen){
            System.out.println(
                    """
                    1) Search by Price
                    2) Search by Make and Model
                    3) Search by Year
                    4) Search by Color
                    5) Search by Mileage
                    6) Search by Vehicle Type
                    7) All Vehicles
                    8) Add a Vehicle
                    9) Remove a Vehicle
                    0) EXIT
                    """
            );
            int userCommand = Integer.parseInt(myScanner.nextLine());

            switch(userCommand) {
                case 1 -> processGetByPriceRequest();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByVehicleTypeRequest();
                case 7 -> processGetAllVehiclesRequest();
                case 8 -> processAddVehicleRequest();
                case 9 -> processRemoveVehicleRequest();
                case 0 -> menuOpen = false;
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void displayVehicles(ArrayList<Vehicles> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }

        System.out.printf("%-6s | %-5s | %-10s | %-10s | %-6s | %-8s | %-8s | %s%n", );

    }

    private void processGetByPriceRequest() {

    }
    private void processGetByMakeModelRequest() {

    }
    private void processGetByYearRequest() {

    }
    private void processGetByColorRequest() {

    }
    private void processGetByMileageRequest() {

    }
    private void processGetByVehicleTypeRequest() {

    }
    private void processGetAllVehiclesRequest() {

    }
    private void processAddVehicleRequest() {

    }
    private void processRemoveVehicleRequest() {

    }
}
