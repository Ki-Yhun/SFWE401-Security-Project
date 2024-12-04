import java.awt.*;
import javax.swing.*;

public class DashboardUI {

    private JLabel notificationBar;

    public DashboardUI() {
        JFrame dashboardFrame = new JFrame("Dashboard");

        int windowWidth = 450;
        int windowHeight = 650;
        
        dashboardFrame.setSize(windowWidth, windowHeight);
        dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Minimum Size
        dashboardFrame.setMinimumSize(new Dimension(windowWidth, windowHeight));

        JPanel panel = new JPanel();
        dashboardFrame.add(panel);
        panel.setLayout(new BorderLayout());

        // Add Notification Bar
        notificationBar = new JLabel("No new notifications", JLabel.CENTER);
        notificationBar.setPreferredSize(new Dimension(windowWidth, 30)); 
        notificationBar.setBackground(Color.GREEN); 
        notificationBar.setOpaque(true); 
        notificationBar.setFont(new Font("Arial", Font.BOLD, 14));
        notificationBar.setForeground(Color.BLACK);
        panel.add(notificationBar, BorderLayout.NORTH); 

        //check for alerts
        checkForAlerts();

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1, 10, 10)); 
        panel.add(buttonPanel, BorderLayout.CENTER);

        placeComponents(buttonPanel);

        dashboardFrame.setVisible(true);
    }

    private void placeComponents(JPanel buttonPanel) {

        // Button creation and action listeners
        JButton patientEntryButton = createButton("Patient Entry", buttonPanel, "patient");

        JButton prescriptionEntryButton = createButton("Prescription Entry", buttonPanel, "prescription");

        JButton insuranceEntryButton = createButton("Insurance Entry", buttonPanel, "insurance");

        JButton prescriptionRefillRequestButton = createButton("Refill Request", buttonPanel, "refill");

        JButton refillReportButton = createButton("Refill Report", buttonPanel, "refillReport");

        JButton salesReportButton = createButton("Sales Report", buttonPanel, "sales");

        JButton generateReportButton = createButton("Inventory Report", buttonPanel, "inventory");

        JButton complianceReportButton = createButton("Compliance Report", buttonPanel, "compliance");

        JButton financialReportButton = createButton("Financial Summary Report", buttonPanel, "financial");

        JButton userActivityReportButton = createButton("User Activity Report", buttonPanel, "userActivity");

        JButton archivedUserDataReportButton = createButton("Archived User Data Report", buttonPanel, "archivedData");

        JButton expiredMedicationsReportButton = createButton("Expired Medications Report", buttonPanel, "expiredMedications");

        JButton notificationPreferencesButton = createButton("Notification Preferences", buttonPanel, "notification");
    }

    private JButton createButton(String label, JPanel buttonPanel, String action) {
        JButton button = new JButton(label);
        button.setPreferredSize(new Dimension(200, 30));
        buttonPanel.add(button);

        // Action listener for each button
        button.addActionListener(e -> {
            // Call the appropriate method based on the action string
            switch (action) {
                case "sales":
                    SalesReportUI.initSalesReportUI();
                    break;
                case "inventory":
                    new InventoryReportSelectionUI();
                    break;
                case "patient":
                    PatientEntryUI.initPatientEntryUI();
                    break;
                case "prescription":
                    PrescriptionEntryUI.initPrescriptionEntryUi();
                    break;
                case "insurance":
                    InsuranceEntryUI.initInsuranceEntryUI();
                    break;
                case "notification":
                    NotificationUI.initNotificationUI();
                    break;
                case "refill":
                    PrescriptionRefillRequestUI.initPrescriptionRefillRequestUI();
                    break;
                case "compliance":
                    ComplianceReportUI.initComplianceReportUI();
                    break;
                case "refillReport":
                    PrescriptionRefillReportUI.initPrescriptionRefillReportUI();
                    break;
                case "financial":
                    FinancialSummaryReportUI.initFinancialSummaryReportUI();
                    break;
                case "userActivity":
                    UserActivityReportUI.initUserActivityReportUI();
                    break;
                case "archivedData":
                    ArchivedUserDataReportUI.initArchivedUserDataReportUI();
                    break;
                case "expiredMedications":
                    ExpiredMedicationsReportUI.initExpiredMedicationsReportUI();
                    break;
            }

            //Close window after opening new UI
            ((JFrame) SwingUtilities.getWindowAncestor(buttonPanel)).dispose();
        });

        return button;
    }

    public void updateNotification(String message, boolean isAlert, boolean isCritical) {
        notificationBar.setText(message);
    
        if (isCritical) {
            // Set the notification to red with white text for critical alerts like expired or recalled
            notificationBar.setBackground(Color.RED); 
            notificationBar.setForeground(Color.WHITE); 
        } else if (isAlert) {
            // Set to yellow background with black text for other alerts
            notificationBar.setBackground(Color.YELLOW); 
            notificationBar.setForeground(Color.BLACK);
        } else {
            // No alerts: green background with black text
            notificationBar.setBackground(Color.GREEN); 
            notificationBar.setForeground(Color.BLACK); 
        }
    }
    
    public void checkForAlerts() {
        boolean lowStock = false;  
        boolean recall = true;   
        boolean expired = false;   
    
        if (expired) {
            updateNotification("Alert: Some medications are close to their expiry date!", true, false); // Regular alert
        } else if (recall) {
            updateNotification("Alert: Product recall issued for: Batch A443, Name: Tyzera, 80 units. Dispose of all stock Immediately", true, true); // Critical alert
        } else if (lowStock) {
            updateNotification("Alert: Low stock on some items!", true, false); // Regular alert
        } else {
            updateNotification("No new notifications", false, false); // No alert
        }
    }

    // Main method to test the dashboard
    public static void main(String[] args) {
        DashboardUI dashboard = new DashboardUI();
        dashboard.checkForAlerts(); // You can call this method to check for alerts at any time
    }
}



