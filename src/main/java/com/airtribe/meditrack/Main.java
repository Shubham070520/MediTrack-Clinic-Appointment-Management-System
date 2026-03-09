package com.airtribe.meditrack;

import com.airtribe.meditrack.entity.*;
import com.airtribe.meditrack.enums.Specialization;
import com.airtribe.meditrack.service.*;
import com.airtribe.meditrack.util.IdGenerator;
import com.airtribe.meditrack.constants.Constants;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        DoctorService doctorService = new DoctorService();
        PatientService patientService = new PatientService();
        AppointmentService appointmentService = new AppointmentService();

        IdGenerator idGenerator = IdGenerator.getInstance();

        // Load patients from CSV on start
        patientService.loadPatientsFromCSV(Constants.PATIENT_FILE);

        while (true) {

            System.out.println("\n===== MediTrack System =====");
            System.out.println("1. Add Doctor");
            System.out.println("2. Add Patient");
            System.out.println("3. Create Appointment");
            System.out.println("4. View Doctors");
            System.out.println("5. View Patients");
            System.out.println("6. View Appointments");
            System.out.println("7. Cancel Appointment");
            System.out.println("8. Average Doctor Fee");
            System.out.println("9. Exit");

            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter Doctor Name: ");
                    String dName = scanner.nextLine();

                    System.out.print("Enter Age: ");
                    int dAge = scanner.nextInt();

                    System.out.print("Enter Fee: ");
                    double fee = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Choose Specialization:");

                    Specialization[] specializations = Specialization.values();

                    for (int i = 0; i < specializations.length; i++) {
                        System.out.println((i + 1) + ". " + specializations[i]);
                    }

                    System.out.print("Enter choice: ");
                    int specChoice = scanner.nextInt();
                    scanner.nextLine();

                    Specialization specialization;

                    if (specChoice < 1 || specChoice > specializations.length) {
                        System.out.println("Invalid choice. Defaulting to GENERAL.");
                        specialization = Specialization.GENERAL;
                    } else {
                        specialization = specializations[specChoice - 1];
                    }

                    Doctor doctor = new Doctor(
                            idGenerator.generateId(),
                            dName,
                            dAge,
                            specialization,
                            fee
                    );

                    doctorService.addDoctor(doctor);
                    doctorService.loadDoctorsFromCSV(Constants.DOCTOR_FILE);
                    System.out.println("Doctor added successfully!");
                    break;

                case 2:

                    System.out.print("Enter Patient Name: ");
                    String pName = scanner.nextLine();

                    System.out.print("Enter Age: ");
                    int pAge = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter Disease: ");
                    String disease = scanner.nextLine();

                    Patient patient = new Patient(
                            idGenerator.generateId(),
                            pName,
                            pAge,
                            disease
                    );

                    patientService.addPatient(patient);
                    System.out.println("Patient added successfully!");
                    break;

                case 3:

                    if (doctorService.getAllDoctors().isEmpty() ||
                            patientService.getAllPatients().isEmpty()) {

                        System.out.println("Add doctor and patient first.");
                        break;
                    }

                    System.out.println("\nAvailable Patients:");
                    patientService.getAllPatients().forEach(p ->
                            System.out.println(p.getId() + " - " + p.getName())
                    );

                    System.out.print("Enter Patient ID: ");
                    String patientId = scanner.nextLine();

                    Patient pat = patientService.getPatientById(patientId);

                    if (pat == null) {
                        System.out.println("Invalid Patient ID");
                        break;
                    }

                    System.out.println("\nAvailable Doctors:");
                    doctorService.getAllDoctors().forEach(d ->
                            System.out.println(d.getId() + " - " + d.getName())
                    );

                    System.out.print("Enter Doctor ID: ");
                    String doctorId = scanner.nextLine();

                    Doctor doc = doctorService.getDoctorById(doctorId);

                    if (doc == null) {
                        System.out.println("Invalid Doctor ID");
                        break;
                    }

                    Appointment appointment = new Appointment(
                            idGenerator.generateId(),
                            pat,
                            doc
                    );

                    appointmentService.createAppointment(appointment);
                    appointment.confirm();
                    System.out.println("Appointment created successfully!");

                    // ===== Billing Section =====
                    double baseAmount = doc.getConsultationFee();

                    Bill bill = new Bill(baseAmount);
                    double totalAmount = bill.calculateBill();
                    double tax = totalAmount - baseAmount;

                    BillSummary summary = new BillSummary(
                            idGenerator.generateId(),
                            baseAmount,
                            tax
                    );

                    System.out.println("\n===== Bill Summary =====");
                    System.out.println("Bill ID: " + summary.getBillId());
                    System.out.println("Base Amount: " + summary.getAmount());
                    System.out.println("Tax: " + summary.getTax());
                    System.out.println("Total Amount: " + summary.getTotal());

                    break;

                case 4:
                    doctorService.getAllDoctors().forEach(Doctor::display);
                    break;

                case 5:
                    patientService.getAllPatients().forEach(Patient::display);
                    break;

                case 6:
                    appointmentService.getAllAppointments().forEach(Appointment::display);
                    break;

                case 7:

                    System.out.print("Enter Appointment ID: ");
                    String apptId = scanner.nextLine();

                    appointmentService.cancelAppointment(apptId);
                    break;

                case 8:

                    double avg = doctorService.getAverageConsultationFee();
                    System.out.println("Average Consultation Fee: " + avg);
                    break;

                case 9:

                    // Save patients before exiting
                    patientService.savePatientsToCSV("patients.csv");

                    System.out.println("Exiting MediTrack...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
