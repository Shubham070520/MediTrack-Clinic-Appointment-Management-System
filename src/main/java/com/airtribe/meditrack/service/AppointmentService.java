package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Appointment;
import com.airtribe.meditrack.enums.AppointmentStatus;
import com.airtribe.meditrack.exception.AppointmentNotFoundException;
import com.airtribe.meditrack.util.DataStore;

import java.util.List;
import java.util.stream.Collectors;

public class AppointmentService {

    private DataStore<Appointment> appointmentStore = new DataStore<>();

    // CREATE appointment
    public void createAppointment(Appointment appointment) {
        appointmentStore.add(appointment.getId(), appointment);
        System.out.println("Appointment created successfully.");
    }

    // VIEW all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentStore.getAll();
    }

    // CANCEL appointment
    public void cancelAppointment(String appointmentId) {

        Appointment appointment = appointmentStore.get(appointmentId);

        if (appointment == null) {
            throw new AppointmentNotFoundException(
                    "Appointment not found with ID: " + appointmentId
            );
        }

        appointment.cancel();
        System.out.println("Appointment cancelled successfully.");
    }

    // FIND appointment by ID
    public Appointment getAppointmentById(String id) {

        Appointment appointment = appointmentStore.get(id);

        if (appointment == null) {
            throw new AppointmentNotFoundException(
                    "Appointment not found with ID: " + id
            );
        }

        return appointment;
    }

    // STREAM BONUS FEATURE
    // Get all confirmed appointments
    public List<Appointment> getConfirmedAppointments() {

        return appointmentStore.getAll()
                .stream()
                .filter(a -> a.getStatus() == AppointmentStatus.CONFIRMED)
                .collect(Collectors.toList());
    }
}