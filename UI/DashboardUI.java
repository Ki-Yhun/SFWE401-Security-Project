import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DashboardUI {

    private JLabel notificationBar;

    public DashboardUI() {
        JFrame dashboardFrame = new JFrame("Dashboard");
        dashboardFrame.setSize(400, 600);
        dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        dashboardFrame.add(panel);

        panel.setLayout(null);

        //Adds Notification Bar with default alert setting
        notificationBar = new JLabel("No new notifications", JLabel.CENTER);
        notificationBar.setBounds(0, 0, 400, 30);
        notificationBar.setBackground(Color.GREEN); 
        notificationBar.setOpaque(true); 
        notificationBar.setFont(new Font("Arial", Font.BOLD, 14));
        notificationBar.setForeground(Color.BLACK);
        panel.add(notificationBar);

        //Checks alert Status of System

        checkForAlerts();

        placeComponents(panel);

        dashboardFrame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // Sales Report Button
        JButton salesReportButton = new JButton("Sales Report");
        salesReportButton.setBounds(120, 40, 150, 25);
        panel.add(salesReportButton);

        // Inventory Report Button
        JButton generateReportButton = new JButton("Inventory Report");
        generateReportButton.setBounds(120, 80, 150, 25);
        panel.add(generateReportButton);

        // Patient Entry Button
        JButton patientEntryButton = new JButton("Patient Entry");
        patientEntryButton.setBounds(120, 120, 150, 25);
        panel.add(patientEntryButton);

        // Prescription Entry Button
        JButton prescriptionEntryButton = new JButton("Prescription Entry");
        prescriptionEntryButton.setBounds(120, 160, 150, 25);
        panel.add(prescriptionEntryButton);

        // Insurance Entry Button
        JButton insuranceEntryButton = new JButton("Insurance Entry");
        insuranceEntryButton.setBounds(120, 200, 150, 25);
        panel.add(insuranceEntryButton);

        // Notification Preferences Button
        JButton notificationPreferencesButton = new JButton("Notification Preferences");
        notificationPreferencesButton.setBounds(120, 240, 150, 25);
        panel.add(notificationPreferencesButton);

        // Prescription Refill Request Button
        JButton prescriptionRefillRequestButton = new JButton("Refill Request");
        prescriptionRefillRequestButton.setBounds(120, 280, 150, 25);
        panel.add(prescriptionRefillRequestButton);

        // Compliance Report Button
        JButton complianceReportButton = new JButton("Compliance Report");
        complianceReportButton.setBounds(120, 320, 150, 25);
        panel.add(complianceReportButton);

        // Prescription Refill Report Button
        JButton refillReportButton = new JButton("Refill Report");
        refillReportButton.setBounds(120, 360, 150, 25);
        panel.add(refillReportButton);

        // Financial Summary Report Button
        JButton financialReportButton = new JButton("Financial Summary Report");
        financialReportButton.setBounds(120, 400, 150, 25); // Adjust the y-coordinate as needed
        panel.add(financialReportButton);

        // User Activity Report Button
        JButton userActivityReportButton = new JButton("User Activity Report");
        userActivityReportButton.setBounds(120, 440, 150, 25); // Adjust y-coordinate as needed
        panel.add(userActivityReportButton);

        // Archived User Data Report Button
        JButton archivedUserDataReportButton = new JButton("Archived User Data Report");
        archivedUserDataReportButton.setBounds(120, 480, 150, 25);
        panel.add(archivedUserDataReportButton);

        // Expired Medication Report Buttton
        JButton expiredMedicationsReportButton = new JButton("Expired Medications Report");
        expiredMedicationsReportButton.setBounds(120, 520, 150, 25);
        panel.add(expiredMedicationsReportButton);

        // Action Listener for Sales Report
        salesReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SalesReportUI.initSalesReportUI(); // Opens Sales Report UI
                ((JFrame) SwingUtilities.getWindowAncestor(panel)).dispose();
            }
        });

        // Action Listener to open Inventory Report Selection
        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InventoryReportSelectionUI();                               // Create new instance becayse the class is designed as an indpenedt ui
                ((JFrame) SwingUtilities.getWindowAncestor(panel)).dispose();
            }
        });

        // Action Listener to open Patient Entry Selection
        patientEntryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PatientEntryUI.initPatientEntryUI();                            // Uses static initialization since there isnt a "initPatientEntryUI()" in the constructor
                ((JFrame) SwingUtilities.getWindowAncestor(panel)).dispose();
            }
        });

        // Action Listener to open Prescription Entry Selection
        prescriptionEntryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrescriptionEntryUI.initPrescriptionEntryUi();                 // Uses static initialization since there isnt a "initPatientEntryUI()" in the constructor
                ((JFrame) SwingUtilities.getWindowAncestor(panel)).dispose();
            }
        });

        //Action Listener for Insurance Entry
        insuranceEntryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InsuranceEntryUI.initInsuranceEntryUI(); // Assuming static init method
                ((JFrame) SwingUtilities.getWindowAncestor(panel)).dispose();
            }
        });

        // Action Listener for Notification Preferences
        notificationPreferencesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NotificationUI.initNotificationUI(); // Assuming static init method
                ((JFrame) SwingUtilities.getWindowAncestor(panel)).dispose();
            }
        });

        // Action Listener for Prescription Refill Request
        prescriptionRefillRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrescriptionRefillRequestUI.initPrescriptionRefillRequestUI(); // Assuming static init method
                ((JFrame) SwingUtilities.getWindowAncestor(panel)).dispose();
            }
        });

        // Action Listener for Compliance Report
        complianceReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComplianceReportUI.initComplianceReportUI();
                ((JFrame) SwingUtilities.getWindowAncestor(panel)).dispose();
            }
        });

        // Action Listener for Prescription Refill Report
        refillReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrescriptionRefillReportUI.initPrescriptionRefillReportUI();
                ((JFrame) SwingUtilities.getWindowAncestor(panel)).dispose();
            }
        });

        // Action Listener for Financial Summary Report
        financialReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FinancialSummaryReportUI.initFinancialSummaryReportUI();
                ((JFrame) SwingUtilities.getWindowAncestor(panel)).dispose();
            }
        });

        // Action Listener for User Activity Report
        userActivityReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserActivityReportUI.initUserActivityReportUI();
                ((JFrame) SwingUtilities.getWindowAncestor(panel)).dispose();
            }
        });

        // Action Listener forArchived User Date Reoptr
        archivedUserDataReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArchivedUserDataReportUI.initArchivedUserDataReportUI();
                ((JFrame) SwingUtilities.getWindowAncestor(panel)).dispose();
            }
        });

        // Action Listener for Expired Meications Report
        expiredMedicationsReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExpiredMedicationsReportUI.initExpiredMedicationsReportUI();
                ((JFrame) SwingUtilities.getWindowAncestor(panel)).dispose();
            }
        });

    }

    public void updateNotification(String message, boolean isAlert, boolean isCritical) {
        notificationBar.setText(message);
    
        if (isCritical) {
            // Set the notification to red with white text for critical alerts like expired or recalled
            notificationBar.setBackground(Color.RED); 
            notificationBar.setForeground(Color.WHITE); 
        } else if (isAlert) {
            // Set to yellow background with black text for other alerts (e.g., low stock)
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
        boolean recall = false;   
        boolean expired = true;   
    
        if (expired) {
            updateNotification("Alert: Some medications are expired!", true, true); // Critical alert
        } else if (recall) {
            updateNotification("Alert: Product recall issued!", true, true); // Critical alert
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
