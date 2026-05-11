package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private final Scanner myScanner = new Scanner(System.in);
    private Dealership dealership;

    ///Empty Constructor
    public UserInterface() {

    }

    ///Initialize method
    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager();
        this.dealership = fileManager.getDealership();
    }

    ///Menu display for user experience
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

            int userCommand = 0;
            boolean isValid = false;

            //Validation while loop to check whether the user input was a number or not.
            while (!isValid) {
                System.out.print("Selection: ");
                String userInput = myScanner.nextLine();

                try {
                    userCommand = Integer.parseInt(userInput);
                    isValid = true;
                } catch (NumberFormatException e) {
                    System.out.println("\nYou did not select a number. Try again!\n");
                }
            }

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

    ///Display vehicles in a nice format
    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("\nNo vehicles found.");
            return;
        }
        System.out.println();
        System.out.printf("%-5s | %-4s | %-6s | %-10s | %-6s | %-8s | %-8s | %s%n", "VIN", "YEAR", "MAKE", "MODEL", "VEHICLE-TYPE", "COLOR", "ODOMETER", "PRICE");
        System.out.println("-".repeat(82));

        for (Vehicle vehicle : vehicles) {
            System.out.printf("%-5d | %-4d | %-6s | %-10s | %-12s | %-8s | %-8d | %.2f%n", vehicle.getVin(), vehicle.getYear(),
                    vehicle.getMake(), vehicle.getModel(), vehicle.getVehicleType(), vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice());
        }
        System.out.println();
    }

    ///Prompt user for min and max price to find vehicles within price range
    private void processGetByPriceRequest() {
        System.out.print("\nEnter a minimum price: ");
        double minPrice = Double.parseDouble(myScanner.nextLine());
        System.out.print("Enter a maximum price: ");
        double maxPrice = Double.parseDouble(myScanner.nextLine());

        ArrayList<Vehicle> vehiclePrice = dealership.getVehiclesByPrice(minPrice, maxPrice);
        displayVehicles(vehiclePrice);
    }
    ///Prompt user for make and model of car
    private void processGetByMakeModelRequest() {
        System.out.print("\nEnter the vehicle make: ");
        String userMake = myScanner.nextLine();
        System.out.print("Enter the vehicle userModel: ");
        String userModel = myScanner.nextLine();

        ArrayList<Vehicle> vMakeModel = dealership.getVehiclesByMakeModel(userMake, userModel);
        displayVehicles(vMakeModel);
    }
    ///Prompt user for min year and max year
    private void processGetByYearRequest() {
        System.out.print("\nEnter minimum vehicle year: ");
        int minYear = Integer.parseInt(myScanner.nextLine());
        System.out.print("Enter maximum vehicle year: ");
        int maxYear = Integer.parseInt(myScanner.nextLine());

        ArrayList<Vehicle> vYear = dealership.getVehiclesByYear(minYear, maxYear);
        displayVehicles(vYear);
    }
    ///Prompt user for car color
    private void processGetByColorRequest() {
        System.out.print("\nEnter a vehicle color: ");
        String userColor = myScanner.nextLine();

        ArrayList<Vehicle> vColor = dealership.getVehiclesByColor(userColor);
        displayVehicles(vColor);
    }
    ///Prompt user for min miles and max miles
    private void processGetByMileageRequest() {
        System.out.print("\nEnter minimum mileage: ");
        int minMiles = Integer.parseInt(myScanner.nextLine());
        System.out.print("Enter maximum mileage: ");
        int maxMiles = Integer.parseInt(myScanner.nextLine());

        ArrayList<Vehicle> vMileage = dealership.getVehiclesByMileage(minMiles, maxMiles);
        displayVehicles(vMileage);
    }
    ///Prompt user for vehicle type
    private void processGetByVehicleTypeRequest() {
        System.out.print("\nEnter a vehicle type: ");
        String userVType = myScanner.nextLine();

        ArrayList<Vehicle> vType = dealership.getVehiclesByType(userVType);
        displayVehicles(vType);
    }
    ///Displays all vehicles upon user's choice
    private void processGetAllVehiclesRequest() {
        ArrayList<Vehicle> getAllV = dealership.getAllVehicles();
        displayVehicles(getAllV);
    }
    ///Prompts user for all necessary values to add a vehicle, adds data to inventory array, saving it to file
    private void processAddVehicleRequest() {

        System.out.print("\nEnter a VIN number: ");
        int vin = Integer.parseInt(myScanner.nextLine());

        System.out.print("Enter a year: ");
        int year = Integer.parseInt(myScanner.nextLine());

        System.out.print("Enter vehicle make: ");
        String make = myScanner.nextLine();

        System.out.print("Enter vehicle model: ");
        String model = myScanner.nextLine();

        System.out.print("Enter vehicle type (Car, Truck, SUV, Sedan): ");
        String vehicleType = myScanner.nextLine();

        System.out.print("Enter color: ");
        String color = myScanner.nextLine();

        System.out.print("Enter mileage: ");
        int miles = Integer.parseInt(myScanner.nextLine());

        System.out.print("Enter vehicle price: ");
        double price = Double.parseDouble(myScanner.nextLine());

        Vehicle newVehicle = new Vehicle(vin, year, make, model, vehicleType, color, miles, price);

        dealership.addVehicle(newVehicle);

        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        dealershipFileManager.saveDealership(dealership);

        System.out.println("Vehicle has been added successfully!\n");
    }
    ///Prompts user for VIN, Checks by VIN if vehicle exists in inventory, if so asks for confirmation before removing
    private void processRemoveVehicleRequest() {

        while (true) {
            System.out.print("Enter the VIN of the vehicle you want to remove: ");
            int userVin = Integer.parseInt(myScanner.nextLine());

            Vehicle removeVehicle = null;
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                if (vehicle.getVin() == userVin) {
                    removeVehicle = vehicle;
                }
            }

            if (removeVehicle == null) {
                System.out.println("\nVehicle VIN " + userVin + " could not be found within inventory. Try again");
                continue;
            }

            System.out.println("Vehicle has been found!\n");
            System.out.printf("%d | %d | %s | %s | %s | %s | %d | %.2f%n\n", removeVehicle.getVin(), removeVehicle.getYear(), removeVehicle.getMake(),
                    removeVehicle.getModel(), removeVehicle.getVehicleType(), removeVehicle.getColor(), removeVehicle.getOdometer(), removeVehicle.getPrice());

            System.out.print("Confirm (Y/N): ");
            String userConfirm = myScanner.nextLine();

            if (!userConfirm.equalsIgnoreCase("y")) {
                System.out.println("Vehicle removal has been cancelled.\n");
                return;
            }

            dealership.removeVehicle(removeVehicle);

            DealershipFileManager dealershipFileManager = new DealershipFileManager();
            dealershipFileManager.saveDealership(dealership);

            System.out.println("Vehicle has been removed successfully!\n");
            break;
        }
    }
}
