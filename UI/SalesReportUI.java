import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class SalesReportUI implements ActionListener {
    private static JLabel timePeriodLabel;
    private static JComboBox<String> timePeriodDropdown;
    private static JButton generateButton;
    private static JButton backButton;
    private static JFrame salesReportFrame;

    public static void initSalesReportUI() {
        JPanel reportPanel = new JPanel();
        salesReportFrame = new JFrame("Sales Report");

        salesReportFrame.setSize(400, 300);
        salesReportFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        salesReportFrame.setVisible(true);
        salesReportFrame.add(reportPanel);
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
        generateButton.addActionListener(new SalesReportUI());
        reportPanel.add(generateButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(labelX + 200, startY + ySpacing, 100, height);
        backButton.addActionListener(e -> {
            salesReportFrame.dispose(); // Close the current frame
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

            // Automatically close the Sales Report UI before showing the summary
            salesReportFrame.dispose();

            // Generate and display the sales report summary
            String reportData = generateSalesReportData(timePeriod);
            showSalesReportInfo(reportData);
        }
    }

    // Generate simulated sales report data
    private String generateSalesReportData(String timePeriod) {
        Map<String, Integer[]> simulatedData = new HashMap<>();
        simulatedData.put("Diphenhydramine", new Integer[]{50, 200, 2000}); // Weekly, Monthly, Yearly
        simulatedData.put("Prednisone", new Integer[]{30, 150, 1500});
        simulatedData.put("Amlodipine", new Integer[]{20, 100, 1000});

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Time Period: ").append(timePeriod).append("\n\n");

        for (Map.Entry<String, Integer[]> entry : simulatedData.entrySet()) {
            String productName = entry.getKey();
            Integer unitsSold;

            switch (timePeriod) {
                case "Weekly":
                    unitsSold = entry.getValue()[0];
                    break;
                case "Monthly":
                    unitsSold = entry.getValue()[1];
                    break;
                case "Yearly":
                    unitsSold = entry.getValue()[2];
                    break;
                default:
                    unitsSold = 0;
            }

            reportBuilder.append("Product Name: ").append(productName).append("\n");
            reportBuilder.append("Units Sold: ").append(unitsSold).append("\n\n");
        }

        return reportBuilder.toString();
    }

    // Method to display the sales report summary
    private void showSalesReportInfo(String reportData) {
        JFrame infoFrame = new JFrame("Sales Report Summary");
        infoFrame.setSize(400, 300);
        infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setText("Sales Report:\n\n" + reportData);

        JScrollPane scrollPane = new JScrollPane(infoArea);
        infoFrame.add(scrollPane);

        infoFrame.setVisible(true);
    }

    public static void main(String[] args) {
        initSalesReportUI();
    }
}
