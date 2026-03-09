package com.airtribe.meditrack.constants;

public class Constants {

    public static final double TAX_RATE = 0.18;
    public static final String PATIENT_FILE = "patients.csv";
    public static final String DOCTOR_FILE = "doctors.csv";

    static {
        System.out.println("MediTrack Configuration Loaded...");
    }

    private Constants() {}
}
