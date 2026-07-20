import java.util.ArrayList;
import java.util.List;

public class Bill {
    private String billId;
    private Appointment appointment;
    private double consultationFee;
    private List<String> tests;
    private List<Double> testCosts;

    public Bill(String billId, Appointment appointment, double consultationFee) {
        this.billId = billId;
        this.appointment = appointment;
        this.consultationFee = consultationFee;
        this.tests = new ArrayList<>();
        this.testCosts = new ArrayList<>();
    }

    public void addTest(String testName, double cost) {
        tests.add(testName);
        testCosts.add(cost);
    }

    public double getTotalAmount() {
        double total = consultationFee;
        for (double cost : testCosts) {
            total += cost;
        }
        return total;
    }

    public String getBillId() {
        return billId;
    }

    public void displayBill() {
        System.out.println("===== BILL =====");
        System.out.println("Bill ID: " + billId);
        System.out.println("Patient: " + appointment.getPatient().getName());
        System.out.println("Doctor: Dr. " + appointment.getDoctor().getName());
        System.out.println("Consultation Fee: Rs. " + consultationFee);
        if (!tests.isEmpty()) {
            System.out.println("Tests:");
            for (int i = 0; i < tests.size(); i++) {
                System.out.println("  - " + tests.get(i) + ": Rs. " + testCosts.get(i));
            }
        }
        System.out.println("-----------------");
        System.out.println("Total Amount: Rs. " + getTotalAmount());
        System.out.println("=================");
    }
}