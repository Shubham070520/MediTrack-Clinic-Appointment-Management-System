package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.util.CSVUtil;
import com.airtribe.meditrack.util.DataStore;

import java.util.ArrayList;
import java.util.List;

public class PatientService {

    private DataStore<Patient> patientStore = new DataStore<>();

    public void addPatient(Patient patient) {
        patientStore.add(patient.getId(), patient);
    }

    public List<Patient> getAllPatients() {
        return patientStore.getAll();
    }

    public void savePatientsToCSV(String filePath) {

        List<String> data = new ArrayList<>();

        for (Patient p : patientStore.getAll()) {
            data.add(p.getId() + "," + p.getName() + "," + p.getAge() + "," + p.getDisease());
        }

        CSVUtil.writeToCSV(filePath, data);
    }

    public void loadPatientsFromCSV(String filePath) {

        List<String> lines = CSVUtil.readFromCSV(filePath);

        for (String line : lines) {

            String[] parts = line.split(",");

            Patient patient = new Patient(
                    parts[0],
                    parts[1],
                    Integer.parseInt(parts[2]),
                    parts[3]
            );

            patientStore.add(patient.getId(), patient);
        }
    }

    public Patient getPatientById(String id) {
        return patientStore.getAll()
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}