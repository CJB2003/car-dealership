package com.pluralsight;

public abstract class Contract {
    protected String date;
    protected String customerName;
    protected String customerEmail;
    protected String vehicleSold;
    protected double totalPrice;
    protected double monthlyPay;

    public Contract(String date, String customerName, String customerEmail, String vehicleSold) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    public abstract double getMonthlyPay();

    public abstract double getTotalPrice();

    /// Parsing info from vehicleSold to get vehiclePrice which is the 7th index of the array
    public double getVehiclePrice() {
        String[] vParts = vehicleSold.split("\\|");
        return Double.parseDouble(vParts[7]);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(String vehicleSold) {
        this.vehicleSold = vehicleSold;
    }
}
