package com.pluralsight;

public class SalesContract extends Contract{
    private double vehiclePrice;
    private double salesTax;
    private double recordingFee;
    private double processingFee;
    private boolean finance;

    /// Constructor, calculating inside of constructor so no need to pass them through
    public SalesContract(String date, String customerName, String customerEmail, String vehicleSold, boolean finance) {
        super(date, customerName, customerEmail, vehicleSold);

        this.vehiclePrice = getVehiclePrice();
        this.salesTax = this.vehiclePrice * 0.05;
        this.recordingFee = 100;
        this.processingFee = getProcessingFee();
        this.finance = finance;
    }

    /// Processing fee returns 295 if price is less than 10000 & 495 if greater
    double getProcessingFee() {
        if (this.vehiclePrice < 10000) {
            return 295;
        } else {
            return 495;
        }
    }

    /**
     * If price is greater than 10k, monthly rate is 4.25% annually for 48 months.
     * Otherwise, it's at 5.25% for 24 months
     */
    @Override
    public double getMonthlyPay() {
        if(!finance) {
            return 0;
        }

        double monthlyRate;
        int numberOfPayments;

        if (getTotalPrice() > 10000) {
            monthlyRate = 0.0425 / 12;
            numberOfPayments = 48;
        }
        else {
            monthlyRate = 0.0525 / 12;
            numberOfPayments = 24;
        }
        /// Returns monthly pay formula
        return getTotalPrice() * (monthlyRate * Math.pow(1 + monthlyRate, numberOfPayments)) / (Math.pow(1 + monthlyRate, numberOfPayments) - 1);
    }


    /**
     * Total price is calculated by vehicle price combined with vehicle price * sales tax
     * plus recording and processing fees. Fixed the calculation
     */
    @Override
    public double getTotalPrice() {
        return this.vehiclePrice + salesTax + this.recordingFee + getProcessingFee();
    }

    /// Getters and setters
    public double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinance() {
        return finance;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }
}
