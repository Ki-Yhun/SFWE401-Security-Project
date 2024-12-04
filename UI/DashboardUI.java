import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardUI {
    public DashboardUI() {
        JFrame dashboardFrame = new JFrame("Dashboard");
        dashboardFrame.setSize(400, 580);
        dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        dashboardFrame.add(panel);
        placeComponents(panel);

        dashboardFrame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // Sales Report Button
        JButton salesReportButton = new JButton("Sales Report");
        salesReportButton.setBounds(120, 20, 150, 25);
        panel.add(salesReportButton);

        // Inventory Report Button
        JButton generateReportButton = new JButton("Inventory Report");
        generateReportButton.setBounds(120, 60, 150, 25);
        panel.add(generateReportButton);

        // Patient Entry Button
        JButton patientEntryButton = new JButton("Patient Entry");
        patientEntryButton.setBounds(120, 100, 150, 25);
        panel.add(patientEntryButton);

        // Prescription Entry Button
        JButton prescriptionEntryButton = new JButton("Prescription Entry");
        prescriptionEntryButton.setBounds(120, 140, 150, 25);
        panel.add(prescriptionEntryButton);

        // Insurance Entry Button
        JButton insuranceEntryButton = new JButton("Insurance Entry");
        insuranceEntryButton.setBounds(120, 180, 150, 25);
        panel.add(insuranceEntryButton);

        // Notification Preferences Button
        JButton notificationPreferencesButton = new JButton("Notification Preferences");
        notificationPreferencesButton.setBounds(120, 220, 150, 25);
        panel.add(notificationPreferencesButton);

        // Prescription Refill Request Button
        JButton prescriptionRefillRequestButton = new JButton("Refill Request");
        prescriptionRefillRequestButton.setBounds(120, 260, 150, 25);
        panel.add(prescriptionRefillRequestButton);

        // Compliance Report Button
        JButton complianceReportButton = new JButton("Compliance Report");
        complianceReportButton.setBounds(120, 300, 150, 25);
        panel.add(complianceReportButton);

        // Prescription Refill Report Button
        JButton refillReportButton = new JButton("Refill Report");
        refillReportButton.setBounds(120, 340, 150, 25);
        panel.add(refillReportButton);

        // Financial Summary Report Button
        JButton financialReportButton = new JButton("Financial Summary Report");
        financialReportButton.setBounds(120, 380, 150, 25); // Adjust the y-coordinate as needed
        panel.add(financialReportButton);

        // User Activity Report Button
        JButton userActivityReportButton = new JButton("User Activity Report");
        userActivityReportButton.setBounds(120, 420, 150, 25); // Adjust y-coordinate as needed
        panel.add(userActivityReportButton);

        // Archived User Data Report Button
        JButton archivedUserDataReportButton = new JButton("Archived User Data Report");
        archivedUserDataReportButton.setBounds(120, 460, 150, 25);
        panel.add(archivedUserDataReportButton);

        // Expired Medication Report Buttton
        JButton expiredMedicationsReportButton = new JButton("Expired Medications Report");
        expiredMedicationsReportButton.setBounds(120, 500, 150, 25);
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
}
