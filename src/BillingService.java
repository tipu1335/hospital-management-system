import java.util.ArrayList;
import java.util.List;

public class BillingService {
    private List<Bill> bills;
    private int billCounter;

    public BillingService() {
        this.bills = new ArrayList<>();
        this.billCounter = 1;
    }

    public Bill generateBill(Appointment appointment, double consultationFee) {
        String billId = "B" + billCounter++;
        Bill bill = new Bill(billId, appointment, consultationFee);
        bills.add(bill);
        return bill;
    }

    public List<Bill> getAllBills() {
        return bills;
    }
}