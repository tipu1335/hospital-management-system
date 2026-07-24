import java.util.Scanner;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BillingService billingService = new BillingService();
        DoctorService doctorService = new DoctorService();
        PatientService patientService = new PatientService();
        AppointmentService appointmentService = new AppointmentService();
        List<Doctor> loadedDoctors = DatabaseManager.loadDoctors();
        List<Patient> loadedPatients = DatabaseManager.loadPatients();
        doctorService.loadAll(loadedDoctors);
        patientService.loadAll(loadedPatients);
        appointmentService.loadAll(DatabaseManager.loadAppointments(loadedDoctors, loadedPatients));
        System.out.println("Previous data loaded successfully from database!");

        while (true) {
            System.out.println("\n===== HOSPITAL MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Doctor");
            System.out.println("2. Add Patient");
            System.out.println("3. Book Appointment");
            System.out.println("4. Cancel Appointment");
            System.out.println("5. View All Doctors");
            System.out.println("6. View All Patients");
            System.out.println("7. View All Appointments");
            System.out.println("8. Search Doctor by Specialization");
            System.out.println("9. View Patient's Appointment History");
            System.out.println("10. Generate Bill");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter Doctor ID: ");
                    String did = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String dname = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int dage = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter Gender: ");
                    String dgender = sc.nextLine();
                    System.out.print("Enter Specialization: ");
                    String spec = sc.nextLine();
                    doctorService.addDoctor(new Doctor(did, dname, dage, dgender, spec));
                    DatabaseManager.saveDoctor(doctorService.findDoctorById(did));
                    break;

                case 2:
                    System.out.print("Enter Patient ID: ");
                    String pid = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String pname = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int page = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter Gender: ");
                    String pgender = sc.nextLine();
                    System.out.print("Enter Disease: ");
                    String disease = sc.nextLine();
                    patientService.addPatient(new Patient(pid, pname, page, pgender, disease));
                    try {
                        DatabaseManager.savePatient(patientService.findPatientByID(pid));
                    } catch (PatientNotFoundException e) {
                        System.out.println("Error saving patient: " + e.getMessage());
                    }
                    break;

                case 3:
                    System.out.print("Enter Patient ID: ");
                    String bookPid = sc.nextLine();
                    System.out.print("Enter Doctor ID: ");
                    String bookDid = sc.nextLine();
                    System.out.print("Enter Slot (e.g. 10:00 AM): ");
                    String slot = sc.nextLine();

                    try {
                        Patient patient = patientService.findPatientByID(bookPid);
                        Doctor doctor = doctorService.findDoctorById(bookDid);
                        if (doctor == null) {
                            System.out.println("Doctor not found!");
                        } else {
                            appointmentService.bookAppointment(patient, doctor, slot);
                            Appointment newAppointment = appointmentService.getAllAppointments().get(appointmentService.getAllAppointments().size() - 1);
                            DatabaseManager.saveAppointment(newAppointment);
                        }
                    } catch (PatientNotFoundException | DoctorNotAvailableException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Enter Appointment ID to cancel: ");
                    String cancelId = sc.nextLine();
                    appointmentService.cancelAppointment(cancelId);
                    break;

                case 5:
                    doctorService.displayAllDoctors();
                    break;

                case 6:
                    patientService.displayAllPatients();
                    break;

                case 7:
                    appointmentService.displayAppointment();
                    break;

                case 8:
                    System.out.print("Enter specialization to search: ");
                    String searchSpec = sc.nextLine();
                    List<Doctor> foundDoctors = doctorService.findDoctorsBySpecialization(searchSpec);
                    if (foundDoctors.isEmpty()) {
                        System.out.println("No doctors found with specialization: " + searchSpec);
                    } else {
                        for (Doctor d : foundDoctors) {
                            d.displayInfo();
                            System.out.println("------------------------");
                        }
                    }
                    break;

                case 9:
                    System.out.print("Enter Patient ID: ");
                    String historyPid = sc.nextLine();
                    List<Appointment> history = appointmentService.getAppointmentsByPatient(historyPid);
                    if (history.isEmpty()) {
                        System.out.println("No appointment history found for this patient.");
                    } else {
                        for (Appointment a : history) {
                            a.displayAppointment();
                            System.out.println("------------------------");
                        }
                    }
                    break;

                case 10:
                    System.out.print("Enter Appointment ID to generate bill for: ");
                    String billAppId = sc.nextLine();
                    Appointment targetAppointment = null;
                    for (Appointment a : appointmentService.getAllAppointments()) {
                        if (a.getAppointmentId().equals(billAppId)) {
                            targetAppointment = a;
                            break;
                        }
                    }
                    if (targetAppointment == null) {
                        System.out.println("Appointment not found!");
                        break;
                    }

                    System.out.print("Enter Consultation Fee: ");
                    double fee = Double.parseDouble(sc.nextLine());
                    Bill bill = billingService.generateBill(targetAppointment, fee);

                    System.out.print("How many tests to add? (0 if none): ");
                    int testCount = Integer.parseInt(sc.nextLine());
                    for (int i = 0; i < testCount; i++) {
                        System.out.print("Enter test name: ");
                        String testName = sc.nextLine();
                        System.out.print("Enter test cost: ");
                        double testCost = Double.parseDouble(sc.nextLine());
                        bill.addTest(testName, testCost);
                    }

                    bill.displayBill();
                    break;

                case 11:
                    System.out.println("Exiting... Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
}