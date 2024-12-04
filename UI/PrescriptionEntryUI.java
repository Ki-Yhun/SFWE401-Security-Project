import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Data.*;

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
    private static JFrame entryFrame;   // Class-level JFrame reference so we can close ui when done with entry

    public Prescription prescription;
    public Patient patient; 
    public Drug drug;

    public static void initPrescriptionEntryUi() {
        JPanel entryPanel = new JPanel();
        entryFrame = new JFrame("Prescription Entry");  // JFrame decleration moved from local to above at class level

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
            String firstName;
            String lastName;
            String patientName = patientNameField.getText().trim();
            String dob = dobField.getText();
            String medicationName = medicationNameField.getText();
            String medicationDate = medicationDateField.getText();
            boolean isControlled = controlledStatusCheckbox.isSelected();
            String dosage = dosageField.getText();
            String physicianName = physicianNameField.getText();
            String instructions = instructionsField.getText();

            float dosageValue = Float.parseFloat(dosage);

            // Split patient name into first and last names
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

            if(firstName.equals(patient.getfirstName()) && lastName.equals(patient.getlastName())){                         // Patient exists
                if(medicationName.equals(drug.getName())){                                                                  // Drug exists 
                    prescription = new Prescription(patient, drug, dosageValue, instructions);                              // Make new prescription on exisiting patient and drug
                }
                else {
                    drug = new Drug(medicationName, medicationDate, isControlled);                                          // Patient exists but Drug does not
                    prescription = new Prescription(patient, drug, dosageValue, instructions);                              // Make new prescription on exisitng patient and new drug
                }
            }
            else{                                                                                                          // Patient does not exist 
                if(medicationName.equals(drug.getName())){
                    patient = new Patient("defaultUsername", "defaultPassword", firstName, lastName);    // Make new patient
                    patient.setDOB(dob);                                                                                   // Make new prescription based on new patient and existing drug
                    prescription = new Prescription(patient, drug, dosageValue, instructions);
                }
                else {                                                                                                     // Patient and drug does not exist
                    drug = new Drug(medicationName, medicationDate, isControlled);                                         // Make new drug
                    patient = new Patient("defaultUsername", "defaultPassword", firstName, lastName);    // Make new patient
                    patient.setDOB(dob); 
                    prescription = new Prescription(patient, drug, dosageValue, instructions);                             // Make new prescription based on new patient and drug
                }

            }

            // Close the current PrescriptionEntryUI
            entryFrame.dispose();

            // Open a new UI window to display the entered information
            showPrescriptionInfoUI(patientName, dob, medicationName, medicationDate, isControlled, dosage, physicianName, instructions);

            /* OLD TO PRINT TO CONSOLE WAY
            // Process the data (for now, just print to the console)
            System.out.println("Patient Name: " + patientName);
            System.out.println("Date of Birth: " + dob);
            System.out.println("Medication Name: " + medicationName);
            System.out.println("Dosage: " + dosage);
            System.out.println("Physician Name: " + physicianName);
            System.out.println("Instructions: " + instructions);
            */
        }
    }

    //  New UI to display entered information
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
