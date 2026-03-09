package com.airtribe.meditrack.interfaces;

public interface Payable {

    double calculateBill();

    default void printPaymentMessage() {
        System.out.println("Payment processed successfully.");
    }
}