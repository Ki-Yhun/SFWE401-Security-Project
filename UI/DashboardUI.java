import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DashboardUI {

    private JLabel notificationBar;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public DashboardUI() {
        JFrame dashboardFrame = new JFrame("Dashboard");

        int windowWidth = 800;
        int windowHeight = 650;

        dashboardFrame.setSize(windowWidth, windowHeight);
        dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Minimum Size
        dashboardFrame.setMinimumSize(new Dimension(windowWidth, windowHeight));

        // Main Panel with CardLayout for view switching
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        dashboardFrame.add(mainPanel, BorderLayout.CENTER);

        // Notification Panel (on top)
        JPanel notificationPanel = new JPanel();
        notificationPanel.setLayout(new BorderLayout());
        notificationBar = new JLabel("No new notifications", JLabel.CENTER);
        notificationBar.setPreferredSize(new Dimension(windowWidth, 30));
        notificationBar.setBackground(Color.GREEN);
        notificationBar.setOpaque(true);
        notificationBar.setFont(new Font("Arial", Font.BOLD, 14));
        notificationBar.setForeground(Color.BLACK);
        notificationPanel.add(notificationBar, BorderLayout.NORTH);
        dashboardFrame.add(notificationPanel, BorderLayout.NORTH);

        // Check for alerts
        checkForAlerts();

        // Create button panel (placed at the bottom)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); 
        createDashboardButtons(buttonPanel);
        dashboardFrame.add(buttonPanel, BorderLayout.SOUTH); 

        // Create different panels for each section
        JPanel patientManagementPanel = createPatientManagementPanel();
        JPanel pharmacyReportsPanel = createPharmacyReportsPanel();

        
        mainPanel.add(patientManagementPanel, "Patient Management");
        mainPanel.add(pharmacyReportsPanel, "Pharmacy Reports");

        
        cardLayout.show(mainPanel, "Patient Management");

        dashboardFrame.setVisible(true);
    }

    private void createDashboardButtons(JPanel panel) {
        // Create buttons to navigate between sections
        JButton patentManagementButton = createButton("Patient Management", "patientManagement");
        panel.add(patentManagementButton);

        JButton pharmacyReportsButton = createButton("Pharmacy Reports", "pharmacyReports");
        panel.add(pharmacyReportsButton);
    }

    private JButton createButton(String label, String actionCommand) {
        JButton button = new JButton(label);
        button.setActionCommand(actionCommand);
        button.setAlignmentX(Component.CENTER_ALIGNMENT); 
        button.addActionListener(new DashboardButtonListener());
        return button;
    }

    private JPanel createPatientManagementPanel() {
        JPanel patientManagementPanel = new JPanel();
        patientManagementPanel.setLayout(new GridBagLayout());

        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;  
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;  
        gbc.weighty = 1.0;  
        gbc.insets = new Insets(10, 10, 10, 10); 

        
        patientManagementPanel.add(createButton("Patient Entry", "patientEntry"), gbc);

        gbc.gridy++;
        patientManagementPanel.add(createButton("Prescription Entry", "prescriptionEntry"), gbc);

        gbc.gridy++;
        patientManagementPanel.add(createButton("Insurance Entry", "insuranceEntry"), gbc);

        gbc.gridy++;
        patientManagementPanel.add(createButton("Refill Request", "refillRequest"), gbc);

        gbc.gridy++;
        patientManagementPanel.add(createButton("Notification Preferences", "notificationPreferences"), gbc);

        return patientManagementPanel;
    }

    private JPanel createPharmacyReportsPanel() {
        JPanel pharmacyReportsPanel = new JPanel();
        pharmacyReportsPanel.setLayout(new GridBagLayout()); 

    
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; 
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;  
        gbc.weighty = 1.0; 
        gbc.insets = new Insets(10, 10, 10, 10); 

        pharmacyReportsPanel.add(createButton("Refill Report", "refillReport"), gbc);

        gbc.gridy++;
        pharmacyReportsPanel.add(createButton("Sales Report", "salesReport"), gbc);

        gbc.gridy++;
        pharmacyReportsPanel.add(createButton("Inventory Report", "inventoryReport"), gbc);

        gbc.gridy++;
        pharmacyReportsPanel.add(createButton("Compliance Report", "complianceReport"), gbc);

        gbc.gridy++;
        pharmacyReportsPanel.add(createButton("Financial Summary Report", "financialReport"), gbc);

        gbc.gridy++;
        pharmacyReportsPanel.add(createButton("User Activity Report", "userActivityReport"), gbc);

        gbc.gridy++;
        pharmacyReportsPanel.add(createButton("Archived User Data Report", "archivedUserDataReport"), gbc);

        gbc.gridy++;
        pharmacyReportsPanel.add(createButton("Expired Medications Report", "expiredMedicationsReport"), gbc);

        return pharmacyReportsPanel;
    }

    // Action Listener
    private class DashboardButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            switch (command) {
                case "patientManagement":
                    cardLayout.show(mainPanel, "Patient Management");
                    break;
                case "pharmacyReports":
                    cardLayout.show(mainPanel, "Pharmacy Reports");
                    break;

                // Patient Management actions
                case "patientEntry":
                    showPatientEntryUI();
                    break;
                case "prescriptionEntry":
                    showPrescriptionEntryUI();
                    break;
                case "insuranceEntry":
                    showInsuranceEntryUI();
                    break;
                case "refillRequest":
                    showRefillRequestUI();
                    break;
                case "notificationPreferences":
                    showNotificationPreferencesUI();
                    break;

                case "refillReport":
                    showRefillReportUI();
                    break;
                case "salesReport":
                    showSalesReportUI();
                    break;
                case "inventoryReport":
                    showInventoryReportUI();  
                    break;
                case "complianceReport":
                    showComplianceReportUI();
                    break;
                case "financialReport":
                    showFinancialSummaryReportUI();
                    break;
                case "userActivityReport":
                    showUserActivityReportUI();
                    break;
                case "archivedUserDataReport":
                    showArchivedUserDataReportUI();
                    break;
                case "expiredMedicationsReport":
                    showExpiredMedicationsReportUI();
                    break;

                default:
                    System.out.println("Unknown action: " + command);
                    break;
            }
        }
    }

   
    private void showPatientEntryUI() {
        PatientEntryUI.initPatientEntryUI(); 
    }

    private void showPrescriptionEntryUI() {
        PrescriptionEntryUI.initPrescriptionEntryUi(); 
    }

    private void showInsuranceEntryUI() {
        InsuranceEntryUI.initInsuranceEntryUI(); 
    }

    private void showRefillRequestUI() {
        PrescriptionRefillRequestUI.initPrescriptionRefillRequestUI(); 
    }

    private void showNotificationPreferencesUI() {
        NotificationUI.initNotificationUI(); 
    }

    private void showRefillReportUI() {
        PrescriptionRefillReportUI.initPrescriptionRefillReportUI(); 
    }

    private void showSalesReportUI() {
        SalesReportUI.initSalesReportUI(); 
    }

    private void showInventoryReportUI() {
        new InventoryReportSelectionUI(); 
    }

    private void showComplianceReportUI() {
        ComplianceReportUI.initComplianceReportUI(); 
    }

    private void showFinancialSummaryReportUI() {
        FinancialSummaryReportUI.initFinancialSummaryReportUI(); 
    }

    private void showUserActivityReportUI() {
        UserActivityReportUI.initUserActivityReportUI(); 
    }

    private void showArchivedUserDataReportUI() {
        ArchivedUserDataReportUI.initArchivedUserDataReportUI(); 
    }

    private void showExpiredMedicationsReportUI() {
        ExpiredMedicationsReportUI.initExpiredMedicationsReportUI(); 
    }

    // Notification section
    public void updateNotification(String message, boolean isAlert, boolean isCritical) {
        notificationBar.setText(message);

        if (isCritical) {
            notificationBar.setBackground(Color.RED);
            notificationBar.setForeground(Color.WHITE);
        } else if (isAlert) {
            notificationBar.setBackground(Color.YELLOW);
            notificationBar.setForeground(Color.BLACK);
        } else {
            notificationBar.setBackground(Color.GREEN);
            notificationBar.setForeground(Color.BLACK);
        }
    }

    public void checkForAlerts() {
        boolean lowStock = false;  // Change for testing
        boolean recall = true;   // Change for testing
        boolean expired = false;   // Change for testing

        if (expired) {
            updateNotification("Alert: Some medications are close to their expiry date!", true, false);
        } else if (recall) {
            updateNotification("Alert: Product recall issued for: Batch A443, Name: Tyzera, 80 units. Dispose of all stock Immediately", true, true);
        } else if (lowStock) {
            updateNotification("Alert: Low stock on some items!", true, false);
        } else {
            updateNotification("No new notifications", false, false);
        }
    }

    // Main method to test the dashboard
    public static void main(String[] args) {
        DashboardUI dashboard = new DashboardUI();
        dashboard.checkForAlerts(); // You can call this method to check for alerts at any time
    }
}