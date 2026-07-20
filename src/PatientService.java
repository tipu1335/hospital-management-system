import java.util.ArrayList;
import java.util.List;
public class PatientService {
    private List<Patient> patients;
    public PatientService() {
        this.patients = new ArrayList<>();

    }
    public List<Patient> getAllPatients() {
        return patients;
    }

    public void loadAll(List<Patient> loadedPatients) {
        this.patients = loadedPatients;
    }
    public void addPatient(Patient patient) {
        patients.add(patient);
        System.out.println("Patient added: " + patient.getName());
    }
    public Patient findPatientByID(String id) throws PatientNotFoundException {
        for (Patient patient : patients) {
            if (patient.getId().equals(id)) {
                return patient;
            }
        }
        throw new PatientNotFoundException("Patient with ID "+ id +" not found!");
    }
    public void  displayAllPatients() {
        if(patients.isEmpty()) {
            System.out.println("No patients found!");
            return;
        }
        for (Patient patient : patients) {
            patient.displayInfo();
            System.out.println("------------------------");
        }
    }
}
