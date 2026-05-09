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

    //Menu display for user experience
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
            System.out.print("Selection: ");
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

    //Display vehicles in a nice format
    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }
        System.out.println();
        System.out.printf("%-5s | %-4s | %-6s | %-10s | %-6s | %-8s | %-8s | %s%n", "VIN", "YEAR", "MAKE", "MODEL", "VEHICLE-TYPE", "COLOR", "ODOMETER", "PRICE");
        System.out.println("-".repeat(82));

        for (Vehicle vehicle : vehicles) {
            System.out.printf("%-5s | %-4s | %-6s | %-10s | %-12s | %-8s | %-8s | %.2f%n", vehicle.getVin(), vehicle.getYear(),
                    vehicle.getMake(), vehicle.getModel(), vehicle.getVehicleType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice());
        }
        System.out.println();
    }

    //Prompt user for min and max price to find vehicles within price range
    private void processGetByPriceRequest() {
        System.out.print("Enter a minimum price: ");
        double minPrice = myScanner.nextDouble();
        System.out.println("Enter a maximum price: ");
        double maxPrice = myScanner.nextDouble();

        ArrayList<Vehicle> vehiclePrice = dealership.getVehiclesByPrice(minPrice, maxPrice);
        displayVehicles(vehiclePrice);
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
        ArrayList<Vehicle> getAllV = dealership.getAllVehicles();
        displayVehicles(getAllV);
    }
    private void processAddVehicleRequest() {

    }
    private void processRemoveVehicleRequest() {

    }
}
