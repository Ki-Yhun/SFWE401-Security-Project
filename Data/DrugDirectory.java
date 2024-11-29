import java.util.ArrayList;
import java.util.List;

public class DrugDirectory {
    private final List<Drug> drugs;     // Stores a list of "Drug" objects

    public DrugDirectory() {
        drugs = new ArrayList<>();
        initializeData();                   // Add initial drug data
    }

    // Initialize with default drugs
    private void initializeData() {
        drugs.add(new Drug("Ibuprofen", "01/2025", false)); // Name, Expiration Date, Controlled Status
        drugs.get(0).setInventory(80);      // Set stock level
        drugs.get(0).setWeeklyUsage(5);     // Set weekly usage
        drugs.get(0).setMonthlyUsage(20);   // Set monthly usage
        drugs.get(0).setYearlyUsage(240);   // Set yearly usage

        drugs.add(new Drug("Amoxicillin", "02/2025", false));
        drugs.get(1).setInventory(50);
        drugs.get(1).setWeeklyUsage(2);
        drugs.get(1).setMonthlyUsage(10);
        drugs.get(1).setYearlyUsage(120);

        drugs.add(new Drug("Prednisone", "03/2025", false));
        drugs.get(2).setInventory(120);
        drugs.get(2).setWeeklyUsage(8);
        drugs.get(2).setMonthlyUsage(30);
        drugs.get(2).setYearlyUsage(360);
    }

    // Add a drug manually
    public void addDrug(String medicationName, String expirationDate, boolean controlledStatus, float stockLevel, int weeklyUsage, int monthlyUsage, int yearlyUsage) {
        Drug newDrug = new Drug(medicationName, expirationDate, controlledStatus);
        newDrug.setInventory(stockLevel);
        newDrug.setWeeklyUsage(weeklyUsage);
        newDrug.setMonthlyUsage(monthlyUsage);
        newDrug.setYearlyUsage(yearlyUsage);
        drugs.add(newDrug);
    }

    // Get all drugs
    public List<Drug> getAllDrugs() {
        return drugs;
    }
}
