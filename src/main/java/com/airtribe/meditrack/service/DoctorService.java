package com.airtribe.meditrack.service;

import com.airtribe.meditrack.entity.Doctor;
import com.airtribe.meditrack.entity.Patient;
import com.airtribe.meditrack.enums.Specialization;
import com.airtribe.meditrack.util.CSVUtil;
import com.airtribe.meditrack.util.DataStore;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DoctorService {

    private DataStore<Doctor> doctorStore = new DataStore<>();

    public void addDoctor(Doctor doctor) {
        doctorStore.add(doctor.getId(), doctor);
    }

    public List<Doctor> getAllDoctors() {
        return doctorStore.getAll();
    }

    // Stream: filter doctors by specialization
    public List<Doctor> findDoctorsBySpecialization(Specialization specialization) {

        return doctorStore.getAll()
                .stream()
                .filter(d -> d.getSpecialization() == specialization)
                .collect(Collectors.toList());
    }

    // Stream: average consultation fee
    public double getAverageConsultationFee() {

        return doctorStore.getAll()
                .stream()
                .mapToDouble(Doctor::getConsultationFee)
                .average()
                .orElse(0);
    }

    // Stream: group doctors by specialization
    public Map<Specialization, List<Doctor>> groupDoctorsBySpecialization() {

        return doctorStore.getAll()
                .stream()
                .collect(Collectors.groupingBy(Doctor::getSpecialization));
    }

    public Doctor getDoctorById(String id) {
        return doctorStore.getAll()
                .stream()
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void loadDoctorsFromCSV(String filePath) {

        List<String> lines = CSVUtil.readFromCSV(filePath);

        for (String line : lines) {

            String[] parts = line.split(",");

            Doctor doctor = new Doctor(
                    parts[0], // id
                    parts[1], // name
                    Integer.parseInt(parts[2]), // age
                    Specialization.valueOf(parts[3].toUpperCase()), // specialization
                    Double.parseDouble(parts[4]) // fee
            );
            doctorStore.add(doctor.getId(), doctor);
        }
    }
}