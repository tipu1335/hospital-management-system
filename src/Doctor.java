import java.util.ArrayList;
import java.util.List;
public class Doctor extends Person {
    private String specialization;
    private List<String> availableSlots;
    public Doctor(String id, String name, int age, String gender, String specialization) {
        super(id, name, age, gender);
        this.specialization = specialization;
        this.availableSlots = new ArrayList<>();
        availableSlots.add("10:00 AM");
        availableSlots.add("11:00 AM");
        availableSlots.add("12:00 PM");
        availableSlots.add("2:00 PM");
        availableSlots.add("3:00 PM");
    }
    public String getSpecialization() {
        return specialization;
    }
    public List<String> getAvailableSlots() {
        return availableSlots;
    }
    public void setAvailableSlots(List<String> availableSlots) {
        this.availableSlots = availableSlots;
    }
    public boolean bookSlot(String slot) {
        return availableSlots.remove(slot);
    }
    @Override
    public void displayInfo() {
        System.out.println("Doctor ID: " + id);
        System.out.println("Name: Dr. " + name);
        System.out.println("Specialization: " + specialization);
        System.out.println("Available Slots: " + availableSlots);
    }

}
