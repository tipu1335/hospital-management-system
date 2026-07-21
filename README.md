Hospital Management System

A console-based Hospital Management System built in Core Java, demonstrating Object-Oriented Programming principles, custom exception handling, file-based persistence, and a modular service-layer architecture.

Features
Doctor Management — Add doctors with specialization and available time slots
Patient Management — Register patients with their medical details
Appointment Booking — Book appointments with real-time slot availability checks
Appointment Cancellation — Cancel appointments and automatically free up the doctor's slot
Search
Find doctors by specialization
View a patient's complete appointment history
Billing Module — Generate bills with consultation fees and multiple test charges, with automatic total calculation
File Persistence — All data (doctors, patients, appointments) is saved to text files and automatically reloaded on the next run, so no data is lost between sessions
Custom Exception Handling — Dedicated exceptions for missing patients and unavailable doctor slots, ensuring predictable and safe error handling
Tech Stack
Language: Java (Core Java, JDK 26)
Concepts Used: OOP (Inheritance, Abstraction, Encapsulation), Collections Framework, Custom Exceptions, Enums, File I/O (BufferedReader/BufferedWriter)
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
│   ├── FileManager.java                # Handles save/load to text files
│   └── Main.java                       # Console menu and entry point
├── .gitignore
└── README.md
How to Run
Clone the repository
git clone https://github.com/tipu1335/hospital-management-system.git
Open the project in IntelliJ IDEA (or any Java IDE)
Run Main.java
Follow the on-screen menu to add doctors/patients, book appointments, search, and generate bills

On exit, all data is saved automatically to text files (doctors.txt, patients.txt, appointments.txt) in the project root, and reloaded the next time the program runs.

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
    Future Scope
    Migrate persistence layer from file-based storage to JDBC with MySQL
    Add a graphical user interface (JavaFX or Swing)
    Add role-based login for Admin/Doctor/Patient
    Add input validation and unit tests
    Author

Tipu — Final-year B.Tech CSE student, building this project as part of a structured Java backend developer roadmap.