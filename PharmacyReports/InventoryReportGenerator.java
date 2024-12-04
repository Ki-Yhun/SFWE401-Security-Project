
import javax.swing.*;
import java.util.List;

import PharmacyReports.InventoryReport;
import Data.*;

public class InventoryReportGenerator {

    public void generateAndDisplayReport(InventoryReport report) {
        // Create a JFrame to display the report
        JFrame reportFrame = new JFrame("Inventory Report");
        reportFrame.setSize(600, 400);
        reportFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create a JTextArea to display the report
        JTextArea reportArea = new JTextArea();
        reportArea.setEditable(false);

        // Format the report
        StringBuilder reportContent = new StringBuilder();
        reportContent.append("=== Inventory Report for ").append(report.getReportPeriod()).append(" ===\n");
        reportContent.append(String.format("%-20s %-15s %-15s %-15s\n", "Medication", "Stock Level", "Usage", "Turnover Rate"));

        for (String[] drug : report.getTurnoverRateData()) {
            reportContent.append(String.format("%-20s %-15s %-15s %-15s\n", drug[0], drug[1], drug[2], drug[3]));
        }

        // Set the report content in the JTextArea
        reportArea.setText(reportContent.toString());

        // Add the JTextArea to a JScrollPane for scrolling
        JScrollPane scrollPane = new JScrollPane(reportArea);

        // Add the JScrollPane to the JFrame
        reportFrame.add(scrollPane);

        // Make the JFrame visible
        reportFrame.setVisible(true);
    }

    // Testing main method to simulate generating the report
    public static void main(String[] args) {
        // Mock data for testing
        DrugDirectory directory = new DrugDirectory();  // Use DrugDirectory to get List<Drug>
        List<Drug> drugData = directory.getAllDrugs();   // Fetch all drugs as List<Drug>
        String reportPeriod = "Monthly";                 // Example: Monthly report

        // Generate InventoryReport using the updated constructor
        InventoryReport report = new InventoryReport(drugData, reportPeriod);

        // Create an instance of InventoryReportGenerator
        InventoryReportGenerator generator = new InventoryReportGenerator();
        generator.generateAndDisplayReport(report);
    }
}
