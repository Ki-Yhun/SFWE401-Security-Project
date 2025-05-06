import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class UserActivityReportUI implements ActionListener {
    private static JLabel timePeriodLabel;
    private static JComboBox<String> timePeriodDropdown;
    private static JButton generateButton;
    private static JButton backButton;
    private static JFrame userActivityReportFrame;

    public static void initUserActivityReportUI() {
        JPanel reportPanel = new JPanel();
        userActivityReportFrame = new JFrame("User Activity Report");

        userActivityReportFrame.setSize(400, 400);
        userActivityReportFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userActivityReportFrame.setVisible(true);
        userActivityReportFrame.add(reportPanel);
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
        generateButton.addActionListener(new UserActivityReportUI());
        reportPanel.add(generateButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(labelX + 200, startY + ySpacing, 100, height);
        backButton.addActionListener(e -> {
            userActivityReportFrame.dispose(); // Close the current frame
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

            // Automatically close the User Activity Report UI before showing the summary
            userActivityReportFrame.dispose();

            // Generate and display the user activity report
            String reportData = generateUserActivityReportData(timePeriod);
            showUserActivityReportInfo(reportData);
        }
    }

    // Generate simulated user activity report data
    private String generateUserActivityReportData(String timePeriod) {
        // Simulated base data (weekly values)
        Map<String, int[]> simulatedData = new HashMap<>();
        simulatedData.put("Admin Harvey Dent", new int[]{15, 10, 5}); // [logins, prescription updates, inventory changes]
        simulatedData.put("Pharmacist Victor Fries", new int[]{20, 15, 10});
        simulatedData.put("Pharmacist Oswald Cobblepot", new int[]{25, 20, 15});

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Time Period: ").append(timePeriod).append("\n\n");

        for (Map.Entry<String, int[]> entry : simulatedData.entrySet()) {
            String userName = entry.getKey();
            int[] weeklyData = entry.getValue();
            int[] scaledData;

            switch (timePeriod) {
                case "Monthly":
                    scaledData = new int[]{weeklyData[0] * 5, weeklyData[1] * 5, weeklyData[2] * 5}; // Approx 4 weeks in a month
                    break;
                case "Yearly":
                    scaledData = new int[]{weeklyData[0] * 50, weeklyData[1] * 50, weeklyData[2] * 50}; // 52 weeks in a year
                    break;
                default: // Weekly
                    scaledData = weeklyData;
            }

            reportBuilder.append("User Name: ").append(userName).append("\n");
            reportBuilder.append("Logins: ").append(scaledData[0]).append(" logins\n");
            reportBuilder.append("Prescription Updates: ").append(scaledData[1]).append(" prescription updates\n");
            reportBuilder.append("Inventory Changes: ").append(scaledData[2]).append(" inventory changes\n\n");
        }

        return reportBuilder.toString();
    }

    // Method to display the user activity report
    private void showUserActivityReportInfo(String reportData) {
        JFrame infoFrame = new JFrame("User Activity Report Summary");
        infoFrame.setSize(400, 400);
        infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setText("User Activity Report:\n\n" + reportData);

        JScrollPane scrollPane = new JScrollPane(infoArea);
        infoFrame.add(scrollPane);

        infoFrame.setVisible(true);
    }

    public static void main(String[] args) {
        initUserActivityReportUI();
    }
}
