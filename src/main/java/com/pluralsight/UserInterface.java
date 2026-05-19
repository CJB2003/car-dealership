package com.pluralsight;

import java.time.LocalDate;
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
                    10) Sell/Lease a Vehicle
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
                case 10 -> processSellLeaseVehicleRequest();
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
                    break;
                }
            }

            if (removeVehicle == null) {
                System.out.println("\nVehicle VIN " + userVin + " could not be found within inventory. Try again.\n");
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

    /// Using the same while loop structure so user can retry if needed
    public void processSellLeaseVehicleRequest() {
        System.out.print("Enter the VIN number of the vehicle: ");
        int vin = Integer.parseInt(myScanner.nextLine());

        while (true) {
            Vehicle sellLeaseV = null;
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                if (vehicle.getVin() == vin) {
                    sellLeaseV = vehicle;
                    break;
                }
            }

            if (sellLeaseV == null) {
                System.out.println("\nVehicle VIN " + vin + " could not be found within inventory. Try again.\n");
                continue;
            }

            System.out.println("Vehicle found!");
            System.out.printf("%d | %d | %s | %s | %s | %s | %d | %.2f%n\n", sellLeaseV.getVin(), sellLeaseV.getYear(), sellLeaseV.getMake(),
                    sellLeaseV.getModel(), sellLeaseV.getVehicleType(), sellLeaseV.getColor(), sellLeaseV.getOdometer(), sellLeaseV.getPrice());

            System.out.println("Is this the correct vehicle? (Y/N)");
            String userChoice = myScanner.nextLine();

            if (!userChoice.equalsIgnoreCase("y")) {
                System.out.println("Your transaction has been cancelled.");
                return;
            }

            String date = String.valueOf(LocalDate.now());

            System.out.print("Please enter your name: ");
            String userName = myScanner.nextLine();

            System.out.print("Please enter your email: ");
            String userEmail = myScanner.nextLine();

            String vehicleSold = String.format("\n%d|%d|%s|%s|%s|%s|%d|%.2f",
                    sellLeaseV.getVin(), sellLeaseV.getYear(), sellLeaseV.getMake(), sellLeaseV.getModel(),
                    sellLeaseV.getVehicleType(), sellLeaseV.getColor(), sellLeaseV.getOdometer(), sellLeaseV.getPrice());

            System.out.print("\nWill this be a sale or lease?\n");
            String userSaleLease = myScanner.nextLine().toUpperCase();

            Contract contract = null;

            if (userSaleLease.equalsIgnoreCase("Sale")) {
                System.out.print("Would you like to finance? (Y/N): ");
                boolean finance = myScanner.nextLine().equalsIgnoreCase("y");

                contract = new SalesContract(date, userName, userEmail, vehicleSold, finance);
            }
            /// Workbook stated user can't lease vehicle over 3 years old
            else if (userSaleLease.equalsIgnoreCase("Lease")) {

                int currentYear = LocalDate.now().getYear();
                int vehicleAge = currentYear - sellLeaseV.getYear();

                if (vehicleAge > 3) {
                    System.out.println("\nSorry! We can't lease vehicles over 3 years old.\n");
                    return;
                }

                contract = new LeaseContract(date, userName, userEmail, vehicleSold);

            }
            else {
                System.out.println("Invalid choice. Please enter sale or lease.\n");
            }

            ContractFileManager contractFM = new ContractFileManager();
            contractFM.saveContract(contract);

            /// Removes the vehicle from the inventory
            dealership.removeVehicle(sellLeaseV);
            DealershipFileManager dealershipFM = new DealershipFileManager();
            dealershipFM.saveDealership(dealership);
            System.out.println("Vehicle removed from inventory!\n");

            System.out.println("CONTRACT SUMMARY");
            System.out.println("Customer: " + userName + " | " + userEmail);
            System.out.printf("Vehicle: %s | %s", sellLeaseV.getMake(), sellLeaseV.getModel());
            System.out.printf("Total Price: $%,.2f", contract.getTotalPrice());
            System.out.printf("Monthly Payment: $%,.2f", contract.getMonthlyPay());

            break;
        }
    }
}
