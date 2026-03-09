package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.interfaces.Payable;
import com.airtribe.meditrack.constants.Constants;

public class Bill implements Payable {

    private double amount;

    public Bill(double amount) {
        this.amount = amount;
    }

    @Override
    public double calculateBill() {
        return amount + (amount * Constants.TAX_RATE);
    }
}