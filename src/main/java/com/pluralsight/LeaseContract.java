package com.pluralsight;

public class LeaseContract extends Contract{
    private double vehiclePrice;
    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(String date, String customerName, String customerEmail, String vehicleSold, double expectedEndingValue, double leaseFee) {
        super(date, customerName, customerEmail, vehicleSold);
        this.vehiclePrice = getVehiclePrice();
        this.expectedEndingValue = this.vehiclePrice * 0.5;
        this.leaseFee = this.vehiclePrice * 0.07;
    }

    /// All leases are financed at 4.0% for 36 months, returns monthly formula
    @Override
    public double getMonthlyPay() {
        double monthlyRate = 0.04 / 12;
        int numberOfPayments = 36;

        return getTotalPrice() * (monthlyRate * Math.pow(1 + monthlyRate, numberOfPayments)) / (Math.pow(1 + monthlyRate, numberOfPayments) - 1);
    }

    /// Total price of the lease
    @Override
    public double getTotalPrice() {
        return (this.vehiclePrice - expectedEndingValue) + leaseFee;
    }

    /// Getters and Setters
    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }
}
