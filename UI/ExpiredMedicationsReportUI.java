import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ExpiredMedicationsReportUI implements ActionListener {
    private static JLabel timePeriodLabel;
    private static JComboBox<String> timePeriodDropdown;
    private static JButton generateButton;
    private static JButton backButton;
    private static JFrame expiredMedicationsReportFrame;

    public static void initExpiredMedicationsReportUI() {
        JPanel reportPanel = new JPanel();
        expiredMedicationsReportFrame = new JFrame("Expired Medications Report");

        expiredMedicationsReportFrame.setSize(400, 400);
        expiredMedicationsReportFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        expiredMedicationsReportFrame.setVisible(true);
        expiredMedicationsReportFrame.add(reportPanel);
        reportPanel.setLayout(null);

        // Define consistent positions and dimensions
        int labelWidth = 150;
        int fieldWidth = 200;
        int height = 25;
        int labelX = 10;
        int fieldX = 170;
        int ySpacing = 40;
        int startY = 20;

        // Time Period
        timePeriodLabel = new JLabel("Time Period:");
        timePeriodLabel.setBounds(labelX, startY, labelWidth, height);
        reportPanel.add(timePeriodLabel);

        String[] timePeriods = {"Weekly", "Monthly", "Yearly"};
        timePeriodDropdown = new JComboBox<>(timePeriods);
        timePeriodDropdown.setBounds(fieldX, startY, fieldWidth, height);
        reportPanel.add(timePeriodDropdown);

        // Generate Button
        generateButton = new JButton("Generate Report");
        generateButton.setBounds(labelX, startY + ySpacing, 150, height);
        generateButton.addActionListener(new ExpiredMedicationsReportUI());
        reportPanel.add(generateButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(labelX + 200, startY + ySpacing, 100, height);
        backButton.addActionListener(e -> {
            new DashboardUI(); // Navigate back to the dashboard
            expiredMedicationsReportFrame.dispose(); // Close the current frame
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

            // Automatically close the Expired Medications Report UI before showing the summary
            expiredMedicationsReportFrame.dispose();

            // Generate and display the expired medications report
            String reportData = generateExpiredMedicationsReportData(timePeriod);
            showExpiredMedicationsReportInfo(reportData);
        }
    }

    // Generate simulated expired medications report data
    private String generateExpiredMedicationsReportData(String timePeriod) {
        Map<String, String[]> simulatedDataWeekly = new HashMap<>();
        simulatedDataWeekly.put("Batch A123", new String[]{"12/03/2024", "50 units"});
        simulatedDataWeekly.put("Batch B456", new String[]{"12/03/2024", "30 units"});

        Map<String, String[]> simulatedDataMonthly = new HashMap<>(simulatedDataWeekly);
        simulatedDataMonthly.put("Batch A123", new String[]{"12/03/2024", "50 units"});
        simulatedDataMonthly.put("Batch B456", new String[]{"12/03/2024", "30 units"});
        simulatedDataMonthly.put("Batch C789", new String[]{"12/01/2024", "20 units"});

        Map<String, String[]> simulatedDataYearly = new HashMap<>(simulatedDataMonthly);
        simulatedDataYearly.put("Batch A123", new String[]{"12/03/2024", "50 units"});
        simulatedDataYearly.put("Batch B456", new String[]{"12/03/2024", "30 units"});
        simulatedDataYearly.put("Batch C789", new String[]{"12/01/2024", "20 units"});
        simulatedDataYearly.put("Batch D100", new String[]{"10/07/2024", "100 units"});

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Time Period: ").append(timePeriod).append("\n\n");

        Map<String, String[]> selectedData;

        switch (timePeriod) {
            case "Weekly":
                selectedData = simulatedDataWeekly;
                break;
            case "Monthly":
                selectedData = simulatedDataMonthly;
                break;
            case "Yearly":
                selectedData = simulatedDataYearly;
                break;
            default:
                selectedData = new HashMap<>();
                selectedData.put("No Data", new String[]{"No Date", "No Quantity"});
        }

        for (Map.Entry<String, String[]> entry : selectedData.entrySet()) {
            String batchNumber = entry.getKey();
            String[] details = entry.getValue();

            reportBuilder.append("Batch Number: ").append(batchNumber).append("\n");
            reportBuilder.append("Expiration Date: ").append(details[0]).append("\n");
            reportBuilder.append("Quantity: ").append(details[1]).append("\n\n");
        }

        return reportBuilder.toString();
    }

    // Method to display the expired medications report
    private void showExpiredMedicationsReportInfo(String reportData) {
        JFrame infoFrame = new JFrame("Expired Medications Report Summary");
        infoFrame.setSize(400, 400);
        infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setText("Expired Medications Report:\n\n" + reportData);

        JScrollPane scrollPane = new JScrollPane(infoArea);
        infoFrame.add(scrollPane);

        infoFrame.setVisible(true);
    }

    public static void main(String[] args) {
        initExpiredMedicationsReportUI();
    }
}
