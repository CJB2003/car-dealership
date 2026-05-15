package com.pluralsight;

public class SalesContract extends Contract{

    private double vehiclePrice;
    private double salesTax;
    private double recordingFee;
    private boolean finance;

    /// Constructor
    public SalesContract(String date, String customerName, String customerEmail, String vehicleSold,
                         double vehiclePrice, double salesTax, double recordingFee, boolean finance) {
        super(date, customerName, customerEmail, vehicleSold);
        this.vehiclePrice = vehiclePrice;
        this.salesTax = salesTax;
        this.recordingFee = recordingFee;
        this.finance = finance;
    }

    /// Processing fee returns 295 if price is less than 10000 & 495 if greater
    private double getProcessingFee() {
        if (this.vehiclePrice < 10000) {
            return 295;
        } else {
            return 495;
        }
    }

    ///
    @Override
    public double getMonthlyPay() {
        return 0;
    }


    /**
     * Total price is calculated by vehicle price combined with vehicle price * sales tax
     * plus recording and processing fees
     */
    @Override
    public double getTotalPrice() {
        return this.vehiclePrice + (this.vehiclePrice * this.salesTax)
                + this.recordingFee + getProcessingFee();
    }
}
