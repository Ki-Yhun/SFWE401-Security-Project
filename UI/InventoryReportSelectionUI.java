import javax.swing.*;
import java.util.List;

//went back to old one with no import, in this set up this report selection goes to
//the inventory drug direcory and inventory report and inventory report generator already
//seen in the generateReport section

public class InventoryReportSelectionUI {
    private JFrame selectionFrame; // Instance-level JFrame reference

    public InventoryReportSelectionUI() {
        selectionFrame = new JFrame("Select Report Period"); // Initialize JFrame
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

        // Action listeners for each button
        weeklyButton.addActionListener(e -> generateReport("Weekly"));
        monthlyButton.addActionListener(e -> generateReport("Monthly"));
        yearlyButton.addActionListener(e -> generateReport("Yearly"));

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setBounds(150, 200, 100, 25);
        backButton.addActionListener(e -> {
            new DashboardUI(); // Navigate back to the dashboard
            selectionFrame.dispose(); // Close the current frame
        });
        panel.add(backButton);
    }

    private void generateReport(String period) {
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
