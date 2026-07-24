import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    // ---------- DOCTORS ----------
    public static void saveDoctor(Doctor doctor) {
        String sql = "INSERT INTO doctors (doctor_id, name, age, gender, specialization, available_slots) " +
                "VALUES (?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE name=?, age=?, gender=?, specialization=?, available_slots=?";

        String slots = String.join(";", doctor.getAvailableSlots());

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, doctor.getId());
            ps.setString(2, doctor.getName());
            ps.setInt(3, doctor.getAge());
            ps.setString(4, doctor.getGender());
            ps.setString(5, doctor.getSpecialization());
            ps.setString(6, slots);

            // update part ke values (duplicate case ke liye)
            ps.setString(7, doctor.getName());
            ps.setInt(8, doctor.getAge());
            ps.setString(9, doctor.getGender());
            ps.setString(10, doctor.getSpecialization());
            ps.setString(11, slots);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving doctor: " + e.getMessage());
        }
    }

    public static List<Doctor> loadDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctors";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Doctor d = new Doctor(
                        rs.getString("doctor_id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("specialization")
                );

                String slotsStr = rs.getString("available_slots");
                List<String> slots = new ArrayList<>();
                if (slotsStr != null && !slotsStr.isEmpty()) {
                    for (String s : slotsStr.split(";")) {
                        slots.add(s);
                    }
                }
                d.setAvailableSlots(slots);
                doctors.add(d);
            }
        } catch (SQLException e) {
            System.out.println("Error loading doctors: " + e.getMessage());
        }
        return doctors;
    }
    // ---------- PATIENTS ----------
    public static void savePatient(Patient patient) {
        String sql = "INSERT INTO patients (patient_id, name, age, gender, disease, assigned_doctor_id) " +
                "VALUES (?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE name=?, age=?, gender=?, disease=?, assigned_doctor_id=?";

        String assignedDoc = patient.getAssignedDoctorId();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, patient.getId());
            ps.setString(2, patient.getName());
            ps.setInt(3, patient.getAge());
            ps.setString(4, patient.getGender());
            ps.setString(5, patient.getDisease());
            ps.setString(6, assignedDoc);

            ps.setString(7, patient.getName());
            ps.setInt(8, patient.getAge());
            ps.setString(9, patient.getGender());
            ps.setString(10, patient.getDisease());
            ps.setString(11, assignedDoc);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving patient: " + e.getMessage());
        }
    }

    public static List<Patient> loadPatients() {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Patient p = new Patient(
                        rs.getString("patient_id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("disease")
                );

                String assignedDoc = rs.getString("assigned_doctor_id");
                if (assignedDoc != null) {
                    p.setAssignedDoctorId(assignedDoc);
                }
                patients.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Error loading patients: " + e.getMessage());
        }
        return patients;
    }
    // ---------- APPOINTMENTS ----------
    public static void saveAppointment(Appointment appointment) {
        String sql = "INSERT INTO appointments (appointment_id, patient_id, doctor_id, slot, status) " +
                "VALUES (?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE slot=?, status=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, appointment.getAppointmentId());
            ps.setString(2, appointment.getPatient().getId());
            ps.setString(3, appointment.getDoctor().getId());
            ps.setString(4, appointment.getSlot());
            ps.setString(5, appointment.getStatus().toString());

            ps.setString(6, appointment.getSlot());
            ps.setString(7, appointment.getStatus().toString());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error saving appointment: " + e.getMessage());
        }
    }

    public static List<Appointment> loadAppointments(List<Doctor> doctors, List<Patient> patients) {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String appointmentId = rs.getString("appointment_id");
                String patientId = rs.getString("patient_id");
                String doctorId = rs.getString("doctor_id");
                String slot = rs.getString("slot");
                String status = rs.getString("status");

                Doctor matchedDoctor = null;
                for (Doctor d : doctors) {
                    if (d.getId().equals(doctorId)) {
                        matchedDoctor = d;
                        break;
                    }
                }

                Patient matchedPatient = null;
                for (Patient p : patients) {
                    if (p.getId().equals(patientId)) {
                        matchedPatient = p;
                        break;
                    }
                }

                if (matchedDoctor == null || matchedPatient == null) continue;

                Appointment appointment = new Appointment(appointmentId, matchedPatient, matchedDoctor, slot);
                appointment.setStatus(AppointmentStatus.valueOf(status));
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            System.out.println("Error loading appointments: " + e.getMessage());
        }
        return appointments;
    }
}