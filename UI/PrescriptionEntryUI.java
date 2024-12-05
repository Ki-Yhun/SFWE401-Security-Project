import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//import Data.*;    //it acted weird and the system didnt like it saying "java: package Data does not exist"

public class PrescriptionEntryUI implements ActionListener {
    private static JLabel patientNameLabel;
    private static JTextField patientNameField;
    private static JLabel dobLabel;
    private static JTextField dobField;
    private static JLabel medicationNameLabel;
    private static JTextField medicationNameField;
    private static JLabel medicationDateLabel;
    private static JTextField medicationDateField;
    private static JLabel controlledStatusLabel;
    private static JCheckBox controlledStatusCheckbox;
    private static JLabel dosageLabel;
    private static JTextField dosageField;
    private static JLabel physicianNameLabel;
    private static JTextField physicianNameField;
    private static JLabel instructionsLabel;
    private static JTextField instructionsField;
    private static JButton enterButton;
    private static JButton backButton;
    private static JFrame entryFrame;   // Class-level JFrame reference so we can close UI when done with entry

    public Prescription prescription;
    public Patient patient; 
    public Drug drug;

    public static void initPrescriptionEntryUi() {
        JPanel entryPanel = new JPanel();
        entryFrame = new JFrame("Prescription Entry");

        entryFrame.setSize(500, 500);
        entryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        entryFrame.setVisible(true);
        entryFrame.add(entryPanel);
        entryPanel.setLayout(null);

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
        entryPanel.add(patientNameLabel);

        patientNameField = new JTextField();
        patientNameField.setBounds(fieldX, startY, fieldWidth, height);
        entryPanel.add(patientNameField);

        // Date of Birth
        dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(labelX, startY + ySpacing, labelWidth, height);
        entryPanel.add(dobLabel);

        dobField = new JTextField();
        dobField.setBounds(fieldX, startY + ySpacing, fieldWidth, height);
        entryPanel.add(dobField);

        // Medication Name
        medicationNameLabel = new JLabel("Medication Name:");
        medicationNameLabel.setBounds(labelX, startY + 2 * ySpacing, labelWidth, height);
        entryPanel.add(medicationNameLabel);

        medicationNameField = new JTextField();
        medicationNameField.setBounds(fieldX, startY + 2 * ySpacing, fieldWidth, height);
        entryPanel.add(medicationNameField);

        // Medication Date
        medicationDateLabel = new JLabel("Medication Date:");
        medicationDateLabel.setBounds(labelX, startY + 3 * ySpacing, labelWidth, height);
        entryPanel.add(medicationDateLabel);

        medicationDateField = new JTextField();
        medicationDateField.setBounds(fieldX, startY + 3 * ySpacing, fieldWidth, height);
        entryPanel.add(medicationDateField);

        // Controlled Status
        controlledStatusLabel = new JLabel("Controlled Status:");
        controlledStatusLabel.setBounds(labelX, startY + 4 * ySpacing, labelWidth, height);
        entryPanel.add(controlledStatusLabel);

        controlledStatusCheckbox = new JCheckBox("Controlled");
        controlledStatusCheckbox.setBounds(fieldX, startY + 4 * ySpacing, fieldWidth, height);
        entryPanel.add(controlledStatusCheckbox);

        // Dosage
        dosageLabel = new JLabel("Dosage:");
        dosageLabel.setBounds(labelX, startY + 5 * ySpacing, labelWidth, height);
        entryPanel.add(dosageLabel);

        dosageField = new JTextField();
        dosageField.setBounds(fieldX, startY + 5 * ySpacing, fieldWidth, height);
        entryPanel.add(dosageField);

        // Physician Name
        physicianNameLabel = new JLabel("Physician Name:");
        physicianNameLabel.setBounds(labelX, startY + 6 * ySpacing, labelWidth, height);
        entryPanel.add(physicianNameLabel);

        physicianNameField = new JTextField();
        physicianNameField.setBounds(fieldX, startY + 6 * ySpacing, fieldWidth, height);
        entryPanel.add(physicianNameField);

        // Instructions
        instructionsLabel = new JLabel("Instructions:");
        instructionsLabel.setBounds(labelX, startY + 7 * ySpacing, labelWidth, height);
        entryPanel.add(instructionsLabel);

        instructionsField = new JTextField();
        instructionsField.setBounds(fieldX, startY + 7 * ySpacing, fieldWidth, height);
        entryPanel.add(instructionsField);

        // Enter Button
        enterButton = new JButton("Enter");
        enterButton.setBounds(labelX, startY + 8 * ySpacing, 100, height);
        enterButton.addActionListener(new PrescriptionEntryUI());
        entryPanel.add(enterButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(labelX + 250, startY + 7 * ySpacing + 100, 100, height);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DashboardUI(); // Navigate back to the dashboard
                entryFrame.dispose(); // Close the current frame
            }
        });
        entryPanel.add(backButton);

        // Revalidate and repaint the panel
        entryPanel.revalidate();
        entryPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enterButton) {
            String patientName = patientNameField.getText().trim();
            String dob = dobField.getText();
            String medicationName = medicationNameField.getText();
            String medicationDate = medicationDateField.getText();
            boolean isControlled = controlledStatusCheckbox.isSelected();
            String dosage = dosageField.getText();
            String physicianName = physicianNameField.getText();
            String instructions = instructionsField.getText();

            
            if (checkForDrugInteractions(medicationName)) {
                return; 
            }

            float dosageValue = Float.parseFloat(dosage);

            // Split patient name into first and last names
            String firstName = "";
            String lastName = "";
            if (patientName.contains(" ")) {
                String[] nameParts = patientName.split(" ", 2);
                firstName = nameParts[0];
                lastName = nameParts[1];
            } else {
                firstName = patientName; // No last name provided
                lastName = " ";
            }

            // Initialize prescription, patient, or drug if null
            if (prescription == null) {
                prescription = new Prescription();
            }
            if (patient == null) {
                patient = new Patient("defaultUsername", "defaultPassword", "", "");
            }
            if (drug == null) {
                drug = new Drug("", "", false);
            }

            entryFrame.dispose();

            showPrescriptionInfoUI(patientName, dob, medicationName, medicationDate, isControlled, dosage, physicianName, instructions);
        }
    }

    // check drug interactions
    private boolean checkForDrugInteractions(String medicationName) {
        // Simulate a check
        if ("Aspirin".equalsIgnoreCase(medicationName)) {
            showDrugInteractionAlert("Aspirin", "Ibuprofen", "Contradictory medical combination: Both are NSAIDs, which may increase risk of stomach bleeding.");
            return true; 
        } else if ("Penicillin".equalsIgnoreCase(medicationName)) {
            showDrugInteractionAlert("Penicillin", "Allergy", "Allergy risk: The patient is allergic to Penicillin. Avoid prescribing this drug.");
            return true;  
        }
        return false;  
    }

    //Display Allergy/Drug interaction Alert
    private void showDrugInteractionAlert(String drug1, String drug2, String message) {
        
        String alertMessage = "Drug Interaction Alert!\n\n"
                + "Interacting Drugs: " + drug1 + " and " + drug2 + "\n\n"
                + "Interaction: " + message + "\n\n"
                + "Please consult with the physician before continuing.";

        JOptionPane.showMessageDialog(entryFrame, alertMessage, "Warning: Drug Interaction", JOptionPane.WARNING_MESSAGE);

        // Clear drug-related fields to allow the user to re-enter the correct information
        medicationNameField.setText("");
        medicationDateField.setText("");
        dosageField.setText("");
        instructionsField.setText(""); 
    }

    // New UI to display entered information
    private void showPrescriptionInfoUI(String patientName, String dob, String medicationName, String medicationDate, boolean isControlled, String dosage, String physicianName, String instructions) {
        JFrame infoFrame = new JFrame("Prescription Information");
        infoFrame.setSize(500, 400);
        infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setText("Prescription Information:\n");
        infoArea.append("Patient Name: " + patientName + "\n");
        infoArea.append("Date of Birth: " + dob + "\n");
        infoArea.append("Medication Name: " + medicationName + "\n");
        infoArea.append("Medication Date: " + medicationDate + "\n");
        infoArea.append("Controlled Status: " + (isControlled ? "Yes" : "No") + "\n");
        infoArea.append("Dosage: " + dosage + "\n");
        infoArea.append("Physician Name: " + physicianName + "\n");
        infoArea.append("Instructions: " + instructions + "\n");

        JScrollPane scrollPane = new JScrollPane(infoArea);
        infoFrame.add(scrollPane);

        infoFrame.setVisible(true);
    }

    public static void main(String[] args) {
        initPrescriptionEntryUi();
    }
}
