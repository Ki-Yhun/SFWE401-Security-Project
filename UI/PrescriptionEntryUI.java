import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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

    public Prescription prescription;
    public Patient patient; 
    public Drug drug;

    public static void initPrescriptionEntryUi() {
        JPanel entryPanel = new JPanel();
        JFrame entryFrame = new JFrame("Prescription Entry");

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


            

            patient = prescription.getPatient();
            drug = prescription.getDrug();

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

            

            

            

            /* 
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

    public static void main(String[] args) {
        initPrescriptionEntryUi();
    }
}
