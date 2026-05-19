package com.pluralsight;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {

    private final File file = new File("src/main/resources/contracts.csv");

    /// Saves a contract by APPENDING to the contracts file
    public void saveContract(Contract contract) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

            if (contract instanceof SalesContract) {
                writeSalesContract(writer, (SalesContract) contract);
            }
            else if (contract instanceof LeaseContract) {
                writeLeaseContract(writer, (LeaseContract) contract);
            }
            writer.close();

        } catch (Exception e) {
            System.out.println("Could not save contract.");
        }
    }

    /// Format: SALE|date|name|email|vin|year|make|model|type|color|odometer|price|salesTax|recordingFee|processingFee|totalPrice|financeOption|monthlyPayment
    private void writeSalesContract(BufferedWriter writer, SalesContract contract) {

        try {

        writer.write(String.format("SALE|%s|%s|%s|%s|%.2f|%.2f|%.2f|%.2f|%s|%.2f%n",
                contract.getDate(),
                contract.getCustomerName(),
                contract.getCustomerEmail(),
                contract.getVehicleSold(),
                contract.getSalesTax(),
                contract.getRecordingFee(),
                contract.getProcessingFee(),
                contract.getTotalPrice(),
                contract.isFinance() ? "YES" : "NO",
                contract.getMonthlyPay()));

        } catch (Exception e) {
            System.out.println("Could not write sale to file.");
        }
    }

    /// Format: LEASE|date|name|email|vin|year|make|model|type|color|odometer|price|expectedEndingValue|leaseFee|totalPrice|monthlyPayment
    private void writeLeaseContract(BufferedWriter writer, LeaseContract contract) throws IOException {

        try {

            writer.write(String.format("LEASE|%s|%s|%s|%s|%.2f|%.2f|%.2f|%.2f%n",
                    contract.getDate(),
                    contract.getCustomerName(),
                    contract.getCustomerEmail(),
                    contract.getVehicleSold(),
                    contract.getExpectedEndingValue(),
                    contract.getLeaseFee(),
                    contract.getTotalPrice(),
                    contract.getMonthlyPay()));

        } catch (Exception e) {
            System.out.println("Could not write lease to file.");
        }
    }
}
