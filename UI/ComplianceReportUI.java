import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ComplianceReportUI implements ActionListener {
    private static JLabel timePeriodLabel;
    private static JComboBox<String> timePeriodDropdown;
    private static JButton generateButton;
    private static JButton backButton;
    private static JFrame complianceReportFrame;

    public static void initComplianceReportUI() {
        JPanel reportPanel = new JPanel();
        complianceReportFrame = new JFrame("Compliance Report");

        complianceReportFrame.setSize(500, 400);
        complianceReportFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        complianceReportFrame.setVisible(true);
        complianceReportFrame.add(reportPanel);
        reportPanel.setLayout(null);

        // Define consistent positions and dimensions
        int labelWidth = 200;
        int fieldWidth = 250;
        int height = 25;
        int labelX = 10;
        int fieldX = 220;
        int ySpacing = 40;
        int startY = 20;

        // Time Period Label
        timePeriodLabel = new JLabel("Select Time Period:");
        timePeriodLabel.setBounds(labelX, startY, labelWidth, height);
        reportPanel.add(timePeriodLabel);

        // Time Period Dropdown
        String[] timePeriods = {"Weekly", "Monthly", "Yearly"};
        timePeriodDropdown = new JComboBox<>(timePeriods);
        timePeriodDropdown.setBounds(fieldX, startY, fieldWidth, height);
        reportPanel.add(timePeriodDropdown);

        // Generate Button
        generateButton = new JButton("Generate Report");
        generateButton.setBounds(labelX, startY + ySpacing, 150, height);
        generateButton.addActionListener(new ComplianceReportUI());
        reportPanel.add(generateButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(labelX + 200, startY + ySpacing, 100, height);
        backButton.addActionListener(e -> {
            complianceReportFrame.dispose(); // Close the current frame
        });
        reportPanel.add(backButton);

        // Revalidate and repaint the panel
        reportPanel.revalidate();
        reportPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateButton) {
            String timePeriod = (String) timePeriodDropdown.getSelectedItem();

            // Generate and display the compliance report
            String reportData = generateComplianceReportData(timePeriod);
            showComplianceReportInfo(timePeriod, reportData);
        }
    }

    // Method to generate compliance report data based on the time period
    private String generateComplianceReportData(String timePeriod) {
        StringBuilder reportBuilder = new StringBuilder();

        // Simulated data
        // Controlled Substances
        String[] controlledSubstances = {"Morphine", "Oxycodone", "Methylphenidate"};

        // Dispensing activities based on the selected time period
        String[] dispensingActivities;
        switch (timePeriod) {
            case "Weekly":
                dispensingActivities = new String[]{
                        "Dispensed 50 units of Morphine",
                        "Dispensed 30 units of Oxycodone",
                        "Dispensed 20 units of Methylphenidate"
                };
                break;
            case "Monthly":
                dispensingActivities = new String[]{
                        "Dispensed 200 units of Morphine",
                        "Dispensed 120 units of Oxycodone",
                        "Dispensed 80 units of Methylphenidate"
                };
                break;
            case "Yearly":
                dispensingActivities = new String[]{
                        "Dispensed 1000 units of Morphine",
                        "Dispensed 600 units of Oxycodone",
                        "Dispensed 400 units of Methylphenidate"
                };
                break;
            default:
                dispensingActivities = new String[]{"No activities available for this period."};
        }

        // Build report based off of informstion
        reportBuilder.append("Controlled Substances:\n");
        for (String substance : controlledSubstances) {
            reportBuilder.append("- ").append(substance).append("\n");
        }

        reportBuilder.append("\nDispensing Activities:\n");
        for (String activity : dispensingActivities) {
            reportBuilder.append("- ").append(activity).append("\n");
        }

        reportBuilder.append("\nReport Period: ").append(timePeriod);

        return reportBuilder.toString();
    }

    // Method to display the compliance report
    private void showComplianceReportInfo(String timePeriod, String reportData) {
        JFrame infoFrame = new JFrame("Compliance Report Summary");
        infoFrame.setSize(500, 400);
        infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setText("Compliance Report (" + timePeriod + "):\n\n");
        infoArea.append(reportData);

        JScrollPane scrollPane = new JScrollPane(infoArea);
        infoFrame.add(scrollPane);

        infoFrame.setVisible(true);

        // Automatically close the ComplianceReportUI when report is displayed
        complianceReportFrame.dispose();
    }

    public static void main(String[] args) {
        initComplianceReportUI();
    }
}
