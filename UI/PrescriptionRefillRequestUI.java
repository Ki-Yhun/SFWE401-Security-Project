import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrescriptionRefillRequestUI implements ActionListener {
    private static JLabel patientNameLabel;
    private static JTextField patientNameField;
    private static JLabel dobLabel;
    private static JTextField dobField;
    private static JLabel contactInfoLabel;
    private static JTextField contactInfoField;
    private static JLabel prescriptionNameLabel;
    private static JTextField prescriptionNameField;
    private static JLabel additionalDetailsLabel;
    private static JTextArea additionalDetailsArea;
    private static JButton submitButton;
    private static JButton backButton; 
    private static JFrame refillFrame;

    public static void initPrescriptionRefillRequestUI() {
        JPanel refillPanel = new JPanel();
        refillFrame = new JFrame("Prescription Refill Request");

        refillFrame.setSize(500, 600);
        refillFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        refillFrame.add(refillPanel);
        refillFrame.setVisible(true);
        refillPanel.setLayout(null);

        // Define consistent positions and dimensions
        int labelWidth = 150;
        int fieldWidth = 250;
        int height = 25;
        int labelX = 10;
        int fieldX = 170;
        int ySpacing = 40;
        int startY = 20;

        // Patient Name
        patientNameLabel = new JLabel("Patient Name:");
        patientNameLabel.setBounds(labelX, startY, labelWidth, height);
        refillPanel.add(patientNameLabel);

        patientNameField = new JTextField();
        patientNameField.setBounds(fieldX, startY, fieldWidth, height);
        refillPanel.add(patientNameField);

        // Date of Birth
        dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(labelX, startY + ySpacing, labelWidth, height);
        refillPanel.add(dobLabel);

        dobField = new JTextField();
        dobField.setBounds(fieldX, startY + ySpacing, fieldWidth, height);
        refillPanel.add(dobField);

        // Contact Information
        contactInfoLabel = new JLabel("Contact Info:");
        contactInfoLabel.setBounds(labelX, startY + 2 * ySpacing, labelWidth, height);
        refillPanel.add(contactInfoLabel);

        contactInfoField = new JTextField();
        contactInfoField.setBounds(fieldX, startY + 2 * ySpacing, fieldWidth, height);
        refillPanel.add(contactInfoField);

        // Prescription Name
        prescriptionNameLabel = new JLabel("Prescription Name:");
        prescriptionNameLabel.setBounds(labelX, startY + 3 * ySpacing, labelWidth, height);
        refillPanel.add(prescriptionNameLabel);

        prescriptionNameField = new JTextField();
        prescriptionNameField.setBounds(fieldX, startY + 3 * ySpacing, fieldWidth, height);
        refillPanel.add(prescriptionNameField);

        // Additional Details
        additionalDetailsLabel = new JLabel("Additional Details:");
        additionalDetailsLabel.setBounds(labelX, startY + 4 * ySpacing, labelWidth, height);
        refillPanel.add(additionalDetailsLabel);

        additionalDetailsArea = new JTextArea();
        additionalDetailsArea.setBounds(labelX, startY + 5 * ySpacing, labelWidth + fieldWidth, 100);
        additionalDetailsArea.setLineWrap(true);
        additionalDetailsArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(additionalDetailsArea);
        scrollPane.setBounds(labelX, startY + 5 * ySpacing, labelWidth + fieldWidth, 100);
        refillPanel.add(scrollPane);

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.setBounds(labelX, startY + 7 * ySpacing + 100, 200, height);
        submitButton.addActionListener(new PrescriptionRefillRequestUI());
        refillPanel.add(submitButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(labelX + 250, startY + 7 * ySpacing + 100, 100, height);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DashboardUI(); // Navigate back to the dashboard
                refillFrame.dispose(); // Close the current frame
            }
        });
        refillPanel.add(backButton);

        refillPanel.revalidate();
        refillPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String patientName = patientNameField.getText().trim();
            String dob = dobField.getText().trim();
            String contactInfo = contactInfoField.getText().trim();
            String prescriptionName = prescriptionNameField.getText().trim();
            String additionalDetails = additionalDetailsArea.getText().trim();

            // Validation
            if (patientName.isEmpty() || dob.isEmpty() || contactInfo.isEmpty() || prescriptionName.isEmpty()) {
                JOptionPane.showMessageDialog(refillFrame, "All fields except 'Additional Details' are mandatory.");
                return;
            }

            // Close the refill request UI
            refillFrame.dispose();

            // Show the entered details in a new window
            showRefillRequestInfo(patientName, dob, contactInfo, prescriptionName, additionalDetails);
        }
    }

    private void showRefillRequestInfo(String patientName, String dob, String contactInfo, String prescriptionName, String additionalDetails) {
        JFrame infoFrame = new JFrame("Refill Request Information");
        infoFrame.setSize(500, 400);
        infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setText("Refill Request Information:\n");
        infoArea.append("Patient Name: " + patientName + "\n");
        infoArea.append("Date of Birth: " + dob + "\n");
        infoArea.append("Contact Info: " + contactInfo + "\n");
        infoArea.append("Prescription Name: " + prescriptionName + "\n");
        infoArea.append("Additional Details: " + (additionalDetails.isEmpty() ? "None" : additionalDetails) + "\n");

        JScrollPane scrollPane = new JScrollPane(infoArea);
        infoFrame.add(scrollPane);

        infoFrame.setVisible(true);
    }

    public static void main(String[] args) {
        initPrescriptionRefillRequestUI();
    }
}
