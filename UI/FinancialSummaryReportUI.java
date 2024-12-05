import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class FinancialSummaryReportUI implements ActionListener {
    private static JLabel timePeriodLabel;
    private static JComboBox<String> timePeriodDropdown;
    private static JButton generateButton;
    private static JButton backButton;
    private static JFrame financialReportFrame;

    public static void initFinancialSummaryReportUI() {
        JPanel reportPanel = new JPanel();
        financialReportFrame = new JFrame("Financial Summary Report");

        financialReportFrame.setSize(400, 400);
        financialReportFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        financialReportFrame.setVisible(true);
        financialReportFrame.add(reportPanel);
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
        generateButton.addActionListener(new FinancialSummaryReportUI());
        reportPanel.add(generateButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(labelX + 200, startY + ySpacing, 100, height);
        backButton.addActionListener(e -> {
            financialReportFrame.dispose(); // Close the current frame
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

            // Automatically close the Financial Summary Report UI before showing the summary
            financialReportFrame.dispose();

            // Generate and display the financial summary report
            String reportData = generateFinancialSummaryReportData(timePeriod);
            showFinancialReportInfo(reportData);
        }
    }

    // Generate simulated financial summary report data
    private String generateFinancialSummaryReportData(String timePeriod) {
        Map<String, Double[]> simulatedData = new HashMap<>();
        simulatedData.put("Prescription Items", new Double[]{1000.0, 4000.0, 50000.0}); // Weekly, Monthly, Yearly
        simulatedData.put("Non-Prescription Items", new Double[]{500.0, 2000.0, 25000.0});

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Time Period: ").append(timePeriod).append("\n\n");

        double totalIncome = 0.0;

        for (Map.Entry<String, Double[]> entry : simulatedData.entrySet()) {
            String category = entry.getKey();
            Double income;

            switch (timePeriod) {
                case "Weekly":
                    income = entry.getValue()[0];
                    break;
                case "Monthly":
                    income = entry.getValue()[1];
                    break;
                case "Yearly":
                    income = entry.getValue()[2];
                    break;
                default:
                    income = 0.0;
            }

            totalIncome += income;

            reportBuilder.append("Category: ").append(category).append("\n");
            reportBuilder.append("Income: $").append(income).append("\n\n");
        }

        reportBuilder.append("Total Income: $").append(totalIncome);

        return reportBuilder.toString();
    }

    // Method to display the financial summary report
    private void showFinancialReportInfo(String reportData) {
        JFrame infoFrame = new JFrame("Financial Summary Report");
        infoFrame.setSize(400, 300);
        infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setText("Financial Summary Report:\n\n" + reportData);

        JScrollPane scrollPane = new JScrollPane(infoArea);
        infoFrame.add(scrollPane);

        infoFrame.setVisible(true);
    }

    public static void main(String[] args) {
        initFinancialSummaryReportUI();
    }
}
