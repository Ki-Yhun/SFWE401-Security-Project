import javax.swing.*;
import java.util.List;

import Data.*;
import PharmacyReports.*;

public class InventoryReportSelectionUI {
    private JFrame selectionFrame;      // Instance-level JFrame reference so we can close the current frame

    public InventoryReportSelectionUI() {
        selectionFrame = new JFrame("Select Report Period");
        selectionFrame.setSize(400, 300);
        selectionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        selectionFrame.add(panel);
        placeComponents(panel);

        selectionFrame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel label = new JLabel("Select Report Period:");
        label.setBounds(50, 50, 150, 25);
        panel.add(label);

        JButton weeklyButton = new JButton("Weekly");
        weeklyButton.setBounds(50, 100, 100, 25);
        panel.add(weeklyButton);

        JButton monthlyButton = new JButton("Monthly");
        monthlyButton.setBounds(160, 100, 100, 25);
        panel.add(monthlyButton);

        JButton yearlyButton = new JButton("Yearly");
        yearlyButton.setBounds(270, 100, 100, 25);
        panel.add(yearlyButton);

        // "Action listeners" for each button
        weeklyButton.addActionListener(e -> generateReport("Weekly", panel));
        monthlyButton.addActionListener(e -> generateReport("Monthly", panel));
        yearlyButton.addActionListener(e -> generateReport("Yearly", panel));

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setBounds(150, 200, 100, 25);
        backButton.addActionListener(e -> {
            new DashboardUI();
            selectionFrame.dispose();
        });
        panel.add(backButton);
    }

    // Method to handle report generation
    private void generateReport(String period, JPanel panel) {
        // Fetch data from DrugDirectory
        DrugDirectory directory = new DrugDirectory();
        List<Drug> drugData = directory.getAllDrugs();

        // Create an InventoryReport
        InventoryReport report = new InventoryReport(drugData, period);

        // Generate and display the report
        InventoryReportGenerator generator = new InventoryReportGenerator();
        generator.generateAndDisplayReport(report);

        // Close the current frame
        selectionFrame.dispose();
    }
}
