public class Appointment {
    private String appointmentId;
    private Patient patient;
    private Doctor doctor;
    private String slot;
    private AppointmentStatus status;
    public Appointment(String appointmentId, Patient patient, Doctor doctor, String slot) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.slot = slot;
        this.status=AppointmentStatus.SCHEDULED;
    }
    public String getAppointmentId() {
        return appointmentId;
    }
    public Patient getPatient() {
    return patient;
}
public Doctor getDoctor() {
    return doctor;
    }
    public String getSlot() {
        return slot;
    }
    public AppointmentStatus getStatus() {
        return status;
    }
    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
    public void displayAppointment() {
        System.out.println("Appointment ID: " + appointmentId);
        System.out.println("Patient: " + patient.getName());
        System.out.println("Doctor: Dr. " + doctor.getName());
        System.out.println("Slot: " + slot);
        System.out.println("Status: " + status);
    }
}
