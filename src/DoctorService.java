import java.util.ArrayList;
import java.util.List;
public class DoctorService {
    private List<Doctor> doctors;
    public DoctorService() {
        this.doctors = new ArrayList<>();
    }
    public List<Doctor> getAllDoctors() {
        return doctors;
    }

    public void loadAll(List<Doctor> loadedDoctors) {
        this.doctors = loadedDoctors;
    }
    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
        System.out.println("Doctor added: Dr. " + doctor.getName());
    }
    public Doctor findDoctorById(String id) {
        for (Doctor doctor : doctors) {
            if (doctor.getId().equals(id)) {
                return doctor;
            }
        }
        return null;
    }
    public List<Doctor> findDoctorsBySpecialization(String specialization) {
        List<Doctor> result = new ArrayList<>();
        for (Doctor doctor : doctors) {
            if (doctor.getSpecialization().equals(specialization)) {
                result.add(doctor);
            }
        }
        return result;
    }
    public void displayAllDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors available.");
            return;
        }
        for (Doctor doctor : doctors) {
            doctor.displayInfo();
            System.out.println("------------------------");
        }
    }
}

