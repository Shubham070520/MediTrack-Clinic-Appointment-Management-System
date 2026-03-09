# MediTrack – Console-Based Medical Appointment Management System

## Overview

**MediTrack** is a Java console application that manages doctors, patients, and appointments.
It demonstrates core **Object-Oriented Programming (OOP)** principles such as abstraction, encapsulation, and separation of concerns through service layers.

The application allows users to:

* Register doctors and patients
* Create and manage appointments
* View records
* Persist patient data using CSV files
* Use Java Streams for analytics

---

## Features

### Doctor Management

* Add a new doctor
* Choose specialization from predefined options
* View all registered doctors
* Calculate average consultation fee (using Java Streams)

### Patient Management

* Register new patients
* View all patients
* Save patient data to CSV
* Load patient data from CSV when the program starts

### Appointment Management

* Create appointment by selecting patient and doctor IDs
* View all appointments
* Cancel appointments

### Data Persistence

* Patient data is stored in a CSV file (`data/patients.csv`)
* Data is loaded automatically when the application starts
* Data is saved automatically when the program exits

---

## Project Structure

```
MediTrack
│
├── src/main/java/com/airtribe/meditrack
│   │
│   ├── entity
│   │   ├── Doctor.java
│   │   ├── Patient.java
│   │   ├── Person.java
│   │   └── Appointment.java
│   │   └── medicalEntity.java
│   │   └── Bill.java
│   │   └── BillSummary.java
│   │
│   ├── enums
│   │   └── Specialization.java
│   │   └── AppointmentStatus.java
│   │
│   ├── service
│   │   ├── DoctorService.java
│   │   ├── PatientService.java
│   │   └── AppointmentService.java
│   │
│   ├── util
│   │   ├── IdGenerator.java
│   │   ├── CSVUtil.java
│   │   └── DataStore.java
│   │
│   └── Main.java
│
└── data
    └── patients.csv
```

---

## Technologies Used

* **Java 17+**
* Java Collections Framework
* Java Streams API
* File Handling (CSV)
* Object-Oriented Design

---

## Design Concepts Used

### Object-Oriented Principles

* **Encapsulation** – Entities encapsulate their fields
* **Abstraction** – Services handle business logic
* **Separation of Concerns** – Entities, services, and utilities are separated

### Design Patterns

* **Singleton Pattern**

    * Used in `IdGenerator` to generate unique IDs
* **Utility Classes**

    * `CSVUtil` handles CSV file operations

---

## How to Run

### 1. Clone the repository

```
git clone https://github.com/yourusername/meditrack.git
```

### 2. Navigate to the project

```
cd meditrack
```

### 3. Compile the project

```
javac src/main/java/com/airtribe/meditrack/Main.java
```

### 4. Run the program

```
java com.airtribe.meditrack.Main
```

---

## Example Console Menu

```
===== MediTrack System =====
1. Add Doctor
2. Add Patient
3. Create Appointment
4. View Doctors
5. View Patients
6. View Appointments
7. Cancel Appointment
8. Average Doctor Fee
9. Exit
```

---

## Example CSV Data

`patients.csv`

```
ID101,Rohan,30,Fever
ID102,Neha,25,Cold
ID103,Aman,40,Diabetes
```
---