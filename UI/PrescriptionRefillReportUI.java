import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class PrescriptionRefillReportUI implements ActionListener {
    private static JLabel timePeriodLabel;
    private static JComboBox<String> timePeriodDropdown;
    private static JButton generateButton;
    private static JButton backButton;
    private static JFrame refillReportFrame;

    public static void initPrescriptionRefillReportUI() {
        JPanel reportPanel = new JPanel();
        refillReportFrame = new JFrame("Prescription Refill Report");

        refillReportFrame.setSize(400, 400);
        refillReportFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        refillReportFrame.setVisible(true);
        refillReportFrame.add(reportPanel);
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
        generateButton.addActionListener(new PrescriptionRefillReportUI());
        reportPanel.add(generateButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(labelX + 200, startY + ySpacing, 100, height);
        backButton.addActionListener(e -> {
            new DashboardUI(); // Navigate back to the dashboard
            refillReportFrame.dispose(); // Close the current frame
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

            // Automatically close the Prescription Refill Report UI before showing the summary
            refillReportFrame.dispose();

            // Generate and display the refill report summary
            String reportData = generateRefillReportData(timePeriod);
            showRefillReportInfo(reportData);
        }
    }

    // Generate simulated refill report data
    private String generateRefillReportData(String timePeriod) {
        Map<String, String[]> weeklyData = new HashMap<>();
        weeklyData.put("Bruce Wayne", new String[]{"12/03/2024", "Completed"});
        weeklyData.put("Jason Todd", new String[]{"12/02/2024", "Pending"});

        Map<String, String[]> monthlyData = new HashMap<>();
        monthlyData.put("Bruce Wayne", new String[]{"12/03/2024", "Completed"});
        monthlyData.put("Jason Todd", new String[]{"12/02/2024", "Pending"});
        monthlyData.put("Tim Drake", new String[]{"12/01/2024", "Completed"});
        monthlyData.put("Damian Wayne", new String[]{"12/01/2024", "Pending"});

        Map<String, String[]> yearlyData = new HashMap<>();
        yearlyData.put("Bruce Wayne", new String[]{"12/03/2024", "Completed"});
        yearlyData.put("Jason Todd", new String[]{"12/02/2024", "Pending"});
        yearlyData.put("Tim Drake", new String[]{"12/01/2024", "Completed"});
        yearlyData.put("Damian Wayne", new String[]{"12/01/2024", "Pending"});
        yearlyData.put("Alfred Pennyworth", new String[]{"10/07/2024", "Completed"});
        yearlyData.put("James Gordon", new String[]{"02/10/2024", "Completed"});

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Time Period: ").append(timePeriod).append("\n\n");

        Map<String, String[]> selectedData;

        switch (timePeriod) {
            case "Weekly":
                selectedData = weeklyData;
                break;
            case "Monthly":
                selectedData = monthlyData;
                break;
            case "Yearly":
                selectedData = yearlyData;
                break;
            default:
                selectedData = new HashMap<>();
        }

        for (Map.Entry<String, String[]> entry : selectedData.entrySet()) {
            String patientName = entry.getKey();
            String refillDate = entry.getValue()[0];
            String prescriptionStatus = entry.getValue()[1];

            reportBuilder.append("Patient Name: ").append(patientName).append("\n");
            reportBuilder.append("Refill Date: ").append(refillDate).append("\n");
            reportBuilder.append("Prescription Status: ").append(prescriptionStatus).append("\n\n");
        }

        return reportBuilder.toString();
    }

    // Method to display the refill report summary
    private void showRefillReportInfo(String reportData) {
        JFrame infoFrame = new JFrame("Prescription Refill Report Summary");
        infoFrame.setSize(400, 300);
        infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setText("Prescription Refill Report:\n\n" + reportData);

        JScrollPane scrollPane = new JScrollPane(infoArea);
        infoFrame.add(scrollPane);

        infoFrame.setVisible(true);
    }

    public static void main(String[] args) {
        initPrescriptionRefillReportUI();
    }
}
