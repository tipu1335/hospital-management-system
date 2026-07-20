import java.util.ArrayList;
import java.util.List;
public class AppointmentService {
    private List<Appointment> appointments;
    private int appointmentCounter;
    public AppointmentService() {
        this.appointments = new ArrayList<>();
        this.appointmentCounter = 1;
    }
    public List<Appointment> getAllAppointments() {
        return appointments;
    }

    public void loadAll(List<Appointment> loadedAppointments) {
        this.appointments = loadedAppointments;
        int maxNum = 0;
        for (Appointment a : loadedAppointments) {
            int num = Integer.parseInt(a.getAppointmentId().substring(1));
            if (num > maxNum) {
                maxNum = num;
            }
        }
        this.appointmentCounter = maxNum + 1;
    }
    public Appointment bookAppointment(Patient patient, Doctor doctor ,String slot) throws DoctorNotAvailableException {
        if(!doctor.getAvailableSlots().contains(slot)) {
            throw new DoctorNotAvailableException(
                    "Dr. " + doctor.getName() + " is not available at " + slot
            );
        }
        doctor.bookSlot(slot);
        String appointmentId= "A" + appointmentCounter++;
        Appointment appointment = new Appointment(appointmentId, patient, doctor, slot);
        appointments.add(appointment);
        patient.setAssignedDoctorId(doctor.getId());
        System.out.println("Appointment booked successfully! ID: " + appointmentId);
        return appointment;
    }
    public void cancelAppointment(String appointmentId) {
        for(Appointment a : appointments) {
            if (a.getAppointmentId().equals(appointmentId)) {
                a.setStatus(AppointmentStatus.CANCELLED);
                a.getDoctor().getAvailableSlots().add(a.getSlot());
                System.out.println("Appointment " + appointmentId + " cancelled.");
                return;
            }
        }
        System.out.println("Appointment ID " + appointmentId + " not found.");
    }
    public void displayAppointment() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments booked.");
            return;
        }
        for (Appointment a : appointments) {
            a.displayAppointment();
            System.out.println("------------------------");
        }

    }
    public List<Appointment> getAppointmentsByPatient(String patientId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : appointments) {
            if (a.getPatient().getId().equals(patientId)) {
                result.add(a);
            }
        }
        return result;
    }
}
