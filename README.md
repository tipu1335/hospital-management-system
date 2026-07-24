Hospital Management System

A console-based Hospital Management System built in Core Java, demonstrating Object-Oriented Programming principles, custom exception handling, JDBC-based database persistence, and a modular service-layer architecture.

Features
Doctor Management — Add doctors with specialization and available time slots
Patient Management — Register patients with their medical details
Appointment Booking — Book appointments with real-time slot availability checks
Appointment Cancellation — Cancel appointments and automatically free up the doctor's slot
Search
Find doctors by specialization
View a patient's complete appointment history
Billing Module — Generate bills with consultation fees and multiple test charges, with automatic total calculation
Database Persistence (JDBC + MySQL) — All data (doctors, patients, appointments) is stored in a MySQL database and loaded automatically on startup, so no data is lost between sessions
Custom Exception Handling — Dedicated exceptions for missing patients and unavailable doctor slots, ensuring predictable and safe error handling
Tech Stack
Language: Java (Core Java, JDK 26)
Database: MySQL
Connectivity: JDBC (MySQL Connector/J)
Concepts Used: OOP (Inheritance, Abstraction, Encapsulation), Collections Framework, Custom Exceptions, Enums, JDBC (PreparedStatement, ResultSet)
IDE: IntelliJ IDEA
Project Structure
HospitalManagementSystem/
├── src/
│   ├── Person.java                     # Abstract base class
│   ├── Patient.java                    # Extends Person
│   ├── Doctor.java                     # Extends Person
│   ├── AppointmentStatus.java          # Enum for appointment states
│   ├── Appointment.java                # Appointment entity
│   ├── Bill.java                       # Billing entity
│   ├── DoctorNotAvailableException.java
│   ├── PatientNotFoundException.java
│   ├── DoctorService.java              # Business logic for doctors
│   ├── PatientService.java             # Business logic for patients
│   ├── AppointmentService.java         # Booking, cancellation, history logic
│   ├── BillingService.java             # Bill generation logic
│   ├── DBConnection.java               # JDBC connection helper
│   ├── DatabaseManager.java            # Handles all database CRUD operations
│   └── Main.java                       # Console menu and entry point
├── .gitignore
└── README.md
Database Schema
sql
CREATE DATABASE hospital_db;

CREATE TABLE doctors (
doctor_id VARCHAR(20) PRIMARY KEY,
name VARCHAR(100) NOT NULL,
age INT,
gender VARCHAR(10),
specialization VARCHAR(100),
available_slots VARCHAR(500)
);

CREATE TABLE patients (
patient_id VARCHAR(20) PRIMARY KEY,
name VARCHAR(100) NOT NULL,
age INT,
gender VARCHAR(10),
disease VARCHAR(200),
assigned_doctor_id VARCHAR(20)
);

CREATE TABLE appointments (
appointment_id VARCHAR(20) PRIMARY KEY,
patient_id VARCHAR(20),
doctor_id VARCHAR(20),
slot VARCHAR(50),
status VARCHAR(20),
FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id)
);
How to Run
Clone the repository
git clone https://github.com/tipu1335/hospital-management-system.git
Set up MySQL
Install MySQL Server and MySQL Workbench if not already installed
Run the SQL schema above to create the hospital_db database and its tables
Add the MySQL JDBC Driver
Download mysql-connector-j (MySQL Connector/J) from Maven Central
Add the .jar file to the project as a library (via IntelliJ: File → Project Structure → Libraries → "+" → Java)
Configure database credentials
Open DBConnection.java and update the USERNAME and PASSWORD fields with your own MySQL credentials
Run the project
Open the project in IntelliJ IDEA (or any Java IDE)
Run Main.java
Follow the on-screen menu to add doctors/patients, book appointments, search, and generate bills
Sample Menu
===== HOSPITAL MANAGEMENT SYSTEM =====
1. Add Doctor
2. Add Patient
3. Book Appointment
4. Cancel Appointment
5. View All Doctors
6. View All Patients
7. View All Appointments
8. Search Doctor by Specialization
9. View Patient's Appointment History
10. Generate Bill
11. Exit
    Project Evolution
    v1 — Console-based system with file-based persistence (BufferedReader/BufferedWriter)
    v2 (current) — Upgraded persistence layer to JDBC with MySQL for robust, relational data storage
    Future Scope
    Migrate to Spring Boot with REST APIs
    Add a graphical or web-based user interface (JavaFX, Swing, or React frontend)
    Add role-based login for Admin/Doctor/Patient
    Add input validation and unit tests
    Author

Tipu — Final-year B.Tech CSE student, building this project as part of a structured Java backend developer roadmap.