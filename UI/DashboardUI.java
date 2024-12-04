import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardUI {
    public DashboardUI() {
        JFrame dashboardFrame = new JFrame("Dashboard");
        dashboardFrame.setSize(400, 500);
        dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        dashboardFrame.add(panel);
        placeComponents(panel);

        dashboardFrame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

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
    }
}
