import java.util.ArrayList;
import java.util.List;

//import Data.Drug; //it acted weird and the system didnt like it saying "java: package Data does not exist"

public class InventoryReport {
    private final List<String[]> reportData;        // Medication report data (name, stock level, usage per week, usage per month, usage per year)
    private final String reportPeriod;              // Time period for the report (Weekly, Monthly, Yearly)
    private final List<String[]> turnoverRateData;  // Enriched data to include turnover rates (stock level divided by usage per month)

    // Constructor for InventoryReport
    public InventoryReport(List<Drug> drugs, String reportPeriod) {
        this.reportData = new ArrayList<>();
        for (Drug drug : drugs) {
            reportData.add(new String[]{
                    drug.getName(),
                    String.valueOf(drug.getInventory()),
                    String.valueOf(drug.getWeeklyUsage()),
                    String.valueOf(drug.getMonthlyUsage()),
                    String.valueOf(drug.getYearlyUsage())
            });
        }
        this.reportPeriod = reportPeriod;                   // Assign the report period
        this.turnoverRateData = calculateTurnoverRates();   // Calculate and store the enriched data with turnover rates
    }


    // Getter for report data
    public List<String[]> getReportData() {
        return reportData;
    }

    // Getter for report period
    public String getReportPeriod() {
        return reportPeriod.toLowerCase();      // Display report period in lowercase, I didn't like the look before
    }

    // Getter for enriched data with turnover rates
    public List<String[]> getTurnoverRateData() {
        return turnoverRateData;
    }

    // Calculate turnover rates for each medication and enrich the data by adding the turnover rate at the end
    private List<String[]> calculateTurnoverRates() {
        List<String[]> enrichedData = new ArrayList<>();
        for (String[] drug : reportData) {
            String medicationName = drug[0];              // Medication name
            float stockLevel = Float.parseFloat(drug[1]); // Stock level (parsed as float)
            int usage;

            // Determine usage based on the report period (weekly, monthly, yearly)
            switch (reportPeriod.toLowerCase()) {         // Handle case-insensitivity
                case "weekly":
                    usage = Integer.parseInt(drug[2]);    // Weekly usage
                    break;
                case "monthly":
                    usage = Integer.parseInt(drug[3]);    // Monthly usage
                    break;
                case "yearly":
                    usage = Integer.parseInt(drug[4]);    // Yearly usage
                    break;
                default:
                    throw new IllegalArgumentException("Invalid report period: " + reportPeriod);
            }

            // Calculate turnover rate and avoid dividing by zero
            double turnoverRate = (usage == 0) ? 0.0 : stockLevel / usage;

            // Add data with turnover rate
            enrichedData.add(new String[] {
                    medicationName,                           // Medication name
                    String.format("%.2f", stockLevel),        // Stock Level (formatted as 2 decimal places)
                    String.valueOf(usage),                   // Usage for the selected period
                    String.format("%.2f", turnoverRate)      // Turnover Rate (formatted as 2 decimal places)
            });
        }
        return enrichedData;    // Return the enriched data with the turnover rates
    }
}
