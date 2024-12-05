import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PatientEntryUI implements ActionListener {
    private static JLabel patientNameLabel;
    private static JTextField patientNameField;
    private static JLabel dobLabel;
    private static JTextField dobField;
    private static JLabel contactInfoLabel;
    private static JTextField contactInfoField;
    private static JButton enterButton;
    private static JButton backButton;
    private static JFrame entryFrame; // Store the PatientEntryUI JFrame reference in class so we can close it once information is entered

    public Patient patient;

    public static void initPatientEntryUI() {
        JPanel entryPanel = new JPanel();
        entryFrame = new JFrame("Patient Entry");   // JFrame declaration moved from local to above at class level

        entryFrame.setSize(400, 300); // Ensure window size is large enough to show buttons
        entryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        entryFrame.setVisible(true);
        entryFrame.add(entryPanel);
        entryPanel.setLayout(null);  // Absolute layout

        // Define consistent positions and dimensions
        int labelWidth = 120;
        int fieldWidth = 200;
        int height = 25;
        int labelX = 10;
        int fieldX = 140;
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

        // Contact Info
        contactInfoLabel = new JLabel("Contact Info:");
        contactInfoLabel.setBounds(labelX, startY + 2 * ySpacing, labelWidth, height);
        entryPanel.add(contactInfoLabel);

        contactInfoField = new JTextField();
        contactInfoField.setBounds(fieldX, startY + 2 * ySpacing, fieldWidth, height);
        entryPanel.add(contactInfoField);

        // Enter Button
        enterButton = new JButton("Enter");
        enterButton.setBounds(labelX, startY + 3 * ySpacing, 100, height);
        enterButton.addActionListener(new PatientEntryUI());
        entryPanel.add(enterButton);

        // Back Button
        backButton = new JButton("Back");
        // Adjusted Back Button Position to fit inside the frame
        backButton.setBounds(labelX, startY + 4 * ySpacing + 50, 100, height);  // Adjust position as needed
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            String contactInfo = contactInfoField.getText();

            // Split patient name into first and last names
            if (patientName.contains(" ")) {
                String[] nameParts = patientName.split(" ", 2);
                firstName = nameParts[0];
                lastName = nameParts[1];
            } else {
                firstName = patientName; // No last name provided
                lastName = " ";
            }

            patient = new Patient("defaultUsername", "defaultPassword", firstName, lastName);
            patient.setDOB(dob);

            // Close the current PatientEntryUI
            entryFrame.dispose();

            // Open a new UI window to display the entered information
            showPatientInfoUI(patientName, dob, contactInfo);
        }
    }

    // New UI to display entered information
    private void showPatientInfoUI(String patientName, String dob, String contactInfo) {
        JFrame infoFrame = new JFrame("Patient Information");
        infoFrame.setSize(400, 300);
        infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setText("Patient Information:\n");
        infoArea.append("Patient Name: " + patientName + "\n");
        infoArea.append("Date of Birth: " + dob + "\n");
        infoArea.append("Contact Info: " + contactInfo);

        JScrollPane scrollPane = new JScrollPane(infoArea);
        infoFrame.add(scrollPane);

        infoFrame.setVisible(true);
    }

    public static void main(String[] args) {
        initPatientEntryUI();
    }
}

