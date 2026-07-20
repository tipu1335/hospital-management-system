import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String DOCTOR_FILE = "doctors.txt";
    private static final String PATIENT_FILE = "patients.txt";
    private static final String APPOINTMENT_FILE = "appointments.txt";
    public static void saveDoctors(List<Doctor> doctors) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DOCTOR_FILE))) {
            for (Doctor d : doctors) {
                String slots = String.join(";", d.getAvailableSlots());
                bw.write(d.getId() + "," + d.getName() + "," + d.getAge() + "," +
                        d.getGender() + "," + d.getSpecialization() + "," + slots);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving doctors: " + e.getMessage());
        }
    }

    public static List<Doctor> loadDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        File file = new File(DOCTOR_FILE);
        if (!file.exists()) return doctors;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",", -1);
                Doctor d = new Doctor(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3], parts[4]);

                List<String> slots = new ArrayList<>();
                if (parts.length > 5 && !parts[5].isEmpty()) {
                    for (String s : parts[5].split(";")) {
                        slots.add(s);
                    }
                }
                d.setAvailableSlots(slots);
                doctors.add(d);
            }
        } catch (IOException e) {
            System.out.println("Error loading doctors: " + e.getMessage());
        }
        return doctors;
    }

    // ---------- PATIENTS ----------
    public static void savePatients(List<Patient> patients) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATIENT_FILE))) {
            for (Patient p : patients) {
                String assignedDoc = p.getAssignedDoctorId() == null ? "NONE" : p.getAssignedDoctorId();
                bw.write(p.getId() + "," + p.getName() + "," + p.getAge() + "," +
                        p.getGender() + "," + p.getDisease() + "," + assignedDoc);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving patients: " + e.getMessage());
        }
    }

    public static List<Patient> loadPatients() {
        List<Patient> patients = new ArrayList<>();
        File file = new File(PATIENT_FILE);
        if (!file.exists()) return patients;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",", -1);
                Patient p = new Patient(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3], parts[4]);
                if (!parts[5].equals("NONE")) {
                    p.setAssignedDoctorId(parts[5]);
                }
                patients.add(p);
            }
        } catch (IOException e) {
            System.out.println("Error loading patients: " + e.getMessage());
        }
        return patients;
    }

    // ---------- APPOINTMENTS ----------
    public static void saveAppointments(List<Appointment> appointments) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(APPOINTMENT_FILE))) {
            for (Appointment a : appointments) {
                bw.write(a.getAppointmentId() + "," + a.getPatient().getId() + "," +
                        a.getDoctor().getId() + "," + a.getSlot() + "," + a.getStatus());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving appointments: " + e.getMessage());
        }
    }

    public static List<Appointment> loadAppointments(DoctorService doctorService, PatientService patientService) {
        List<Appointment> appointments = new ArrayList<>();
        File file = new File(APPOINTMENT_FILE);
        if (!file.exists()) return appointments;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",", -1);
                String appointmentId = parts[0];
                String patientId = parts[1];
                String doctorId = parts[2];
                String slot = parts[3];
                String status = parts[4];

                Doctor doctor = doctorService.findDoctorById(doctorId);
                Patient patient;
                try {
                    patient = patientService.findPatientByID(patientId);
                } catch (PatientNotFoundException e) {
                    continue;
                }
                if (doctor == null) continue;

                Appointment appointment = new Appointment(appointmentId, patient, doctor, slot);
                appointment.setStatus(AppointmentStatus.valueOf(status));
                appointments.add(appointment);
            }
        } catch (IOException e) {
            System.out.println("Error loading appointments: " + e.getMessage());
        }
        return appointments;
    }
}