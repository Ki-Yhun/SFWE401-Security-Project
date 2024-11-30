import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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

    public Patient patient;

    public static void initPatientEntryUI() {
        JPanel entryPanel = new JPanel();
        JFrame entryFrame = new JFrame("Patient Entry");

        entryFrame.setSize(400, 300);
        entryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        entryFrame.setVisible(true);
        entryFrame.add(entryPanel);
        entryPanel.setLayout(null);

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

            // Process the data (for now, just print to the console)
            System.out.println("Patient Name: " + patientName);
            System.out.println("Date of Birth: " + dob);
            System.out.println("Contact Info: " + contactInfo);
        }
    }

    public static void main(String[] args) {
        initPatientEntryUI();
    }
}