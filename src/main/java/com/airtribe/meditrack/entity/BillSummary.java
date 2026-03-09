package com.airtribe.meditrack.entity;

public final class BillSummary {

    private final String billId;
    private final double amount;
    private final double tax;
    private final double total;

    public BillSummary(String billId, double amount, double tax) {
        this.billId = billId;
        this.amount = amount;
        this.tax = tax;
        this.total = amount + tax;
    }

    public String getBillId() { return billId; }
    public double getAmount() { return amount; }
    public double getTax() { return tax; }
    public double getTotal() { return total; }
}