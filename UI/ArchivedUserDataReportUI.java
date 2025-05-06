import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class ArchivedUserDataReportUI implements ActionListener {
    private static JLabel timePeriodLabel;
    private static JComboBox<String> timePeriodDropdown;
    private static JButton generateButton;
    private static JButton backButton;
    private static JFrame archivedDataReportFrame;

    public static void initArchivedUserDataReportUI() {
        JPanel reportPanel = new JPanel();
        archivedDataReportFrame = new JFrame("Archived User Data Report");

        archivedDataReportFrame.setSize(400, 400);
        archivedDataReportFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        archivedDataReportFrame.setVisible(true);
        archivedDataReportFrame.add(reportPanel);
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
        generateButton.addActionListener(new ArchivedUserDataReportUI());
        reportPanel.add(generateButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(labelX + 200, startY + ySpacing, 100, height);
        backButton.addActionListener(e -> {
            archivedDataReportFrame.dispose(); // Close the current frame
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

            // Automatically close the Archived User Data Report UI before showing the summary
            archivedDataReportFrame.dispose();

            // Generate and display the archived data report
            String reportData = generateArchivedUserDataReportData(timePeriod);
            showArchivedUserDataReportInfo(reportData);
        }
    }

    // Generate simulated archived user data report
    private String generateArchivedUserDataReportData(String timePeriod) {
        Map<String, String[]> simulatedDataWeekly = new HashMap<>();
        simulatedDataWeekly.put("User Data Backup", new String[]{"12/03/2024", "Encrypted User Data Backup"});
        simulatedDataWeekly.put("Pharmacy Logs", new String[]{"12/03/2024", "Compressed Pharmacy Logs"});

        Map<String, String[]> simulatedDataMonthly = new HashMap<>(simulatedDataWeekly);
        simulatedDataMonthly.put("User Data Backup", new String[]{"12/03/2024", "Encrypted User Data Backup"});
        simulatedDataMonthly.put("Pharmacy Logs", new String[]{"12/03/2024", "Compressed Pharmacy Logs"});
        simulatedDataMonthly.put("Inventory Records", new String[]{"12/01/2024", "Archived Inventory Items"});

        Map<String, String[]> simulatedDataYearly = new HashMap<>(simulatedDataMonthly);
        simulatedDataYearly.put("User Data Backup", new String[]{"12/03/2024", "Encrypted User Data Backup"});
        simulatedDataYearly.put("Pharmacy Logs", new String[]{"12/03/2024", "Compressed Pharmacy Logs"});
        simulatedDataYearly.put("Inventory Records", new String[]{"12/01/2024", "Archived Inventory Items"});
        simulatedDataYearly.put("Audit Logs", new String[]{"10/07/2024", "Yearly Compliance Audit Logs"});

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
                selectedData.put("No Data", new String[]{"No Date", "No Details"});
        }

        for (Map.Entry<String, String[]> entry : selectedData.entrySet()) {
            String dataType = entry.getKey();
            String[] details = entry.getValue();

            reportBuilder.append("Data Type: ").append(dataType).append("\n");
            reportBuilder.append("Archived Date: ").append(details[0]).append("\n");
            reportBuilder.append("Data Details: ").append(details[1]).append("\n\n");
        }

        return reportBuilder.toString();
    }

    // Method to display the archived data report summary
    private void showArchivedUserDataReportInfo(String reportData) {
        JFrame infoFrame = new JFrame("Archived User Data Report Summary");
        infoFrame.setSize(400, 400);
        infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setText("Archived User Data Report:\n\n" + reportData);

        JScrollPane scrollPane = new JScrollPane(infoArea);
        infoFrame.add(scrollPane);

        infoFrame.setVisible(true);
    }

    public static void main(String[] args) {
        initArchivedUserDataReportUI();
    }
}
