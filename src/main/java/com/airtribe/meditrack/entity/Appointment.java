package com.airtribe.meditrack.entity;

import com.airtribe.meditrack.enums.AppointmentStatus;

public class Appointment extends medicalEntity {

    private Patient patient;
    private Doctor doctor;
    private AppointmentStatus status;

    public Appointment(String id, Patient patient, Doctor doctor) {
        super(id);
        this.patient = patient.clone(); // deep copy
        this.doctor = doctor;
        this.status = AppointmentStatus.PENDING;
    }

    public void confirm() { status = AppointmentStatus.CONFIRMED; }
    public void cancel() { status = AppointmentStatus.CANCELLED; }

    public AppointmentStatus getStatus() { return status; }

    @Override
    public void display() {
        System.out.println("Appointment: " +
                patient.getName() + " with Dr. " +
                doctor.getName() + " | Status: " + status);
    }
}