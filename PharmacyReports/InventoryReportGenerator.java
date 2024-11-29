/*import java.util.Scanner;
public class InventoryReportGenerator {
    public void generateReport(InventoryReport report) {
        System.out.println("~~~ Inventory Report for " + report.getReportPeriod() + " ~~~");
        System.out.printf("%-20s %-15s %-15s %-15s%n", "Medication", "Stock Level", "Usage", "Turnover Rate");

        // Fetch the enriched data with turnover rates
        for (String[] drug : report.getTurnoverRateData()) {
            System.out.printf("%-20s %-15s %-15s %-15s%n", drug[0], drug[1], drug[2], drug[3]);
        }
    }

    public static void main(String[] args) {
        // Fetch data from DrugDirectory
        DrugDirectory directory = new DrugDirectory();
    //ignore below
        // Generate an InventoryReport based off weekly, monthly, yearly
        //InventoryReport report = new InventoryReport(directory.getAllDrugs(), "Monthly");

        // Display the report based off weekly, monthly, yearly
        //InventoryReportGenerator generator = new InventoryReportGenerator();
        //generator.generateReport(report);
    //ignore above

        // Prompt user for the report period
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the report period: Weekly, Monthly, or Yearly");
        String period = scanner.nextLine().trim();  // get response, trimmed to fit

        // Validate the user input and generate the report if good
        try {
            InventoryReport report = new InventoryReport(directory.getAllDrugs(), period);
            InventoryReportGenerator generator = new InventoryReportGenerator();
            generator.generateReport(report);
        } catch (IllegalArgumentException e) {  // If user did not input an expected value
            System.out.println("Invalid input. Please select either 'Weekly', 'Monthly', or 'Yearly'.");
        }

        scanner.close();    // Close the scanner to release resources
    }
}
*/

import javax.swing.*;
import java.util.List;


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