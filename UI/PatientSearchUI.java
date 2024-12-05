import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class PatientSearchUI implements ActionListener {
    private static JLabel nameLabel;
    private static JTextField nameField;
    private static JLabel idLabel;
    private static JTextField idField;
    private static JLabel phoneLabel;
    private static JTextField phoneField;
    private static JButton searchButton;
    private static JButton backButton; // Back button
    private static JFrame searchFrame;

    // Mock patient database (for demonstration)
    private static ArrayList<Patient> patients;

    // Patient class (for mock data)
    static class Patient {
        String name;
        String id;
        String phone;

        public Patient(String name, String id, String phone) {
            this.name = name;
            this.id = id;
            this.phone = phone;
        }

        public boolean matches(String searchName, String searchId, String searchPhone) {
            return (searchName != null && this.name.equalsIgnoreCase(searchName))
                    || (searchId != null && this.id.equalsIgnoreCase(searchId))
                    || (searchPhone != null && this.phone.equals(searchPhone));
        }

        @Override
        public String toString() {
            return "Patient Profile:\nName: " + name + "\nID: " + id + "\nPhone: " + phone;
        }
    }

    // Initialize mock patient data
    private static void initializePatients() {
        patients = new ArrayList<>();
        patients.add(new Patient("John Doe", "12345", "555-1234"));
        patients.add(new Patient("Jane Smith", "67890", "555-5678"));
        patients.add(new Patient("Alice Johnson", "11223", "555-9012"));
    }

    public static void initPatientSearchUI() {
        JPanel searchPanel = new JPanel();
        searchFrame = new JFrame("Patient Search");

        searchFrame.setSize(400, 350);
        searchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchFrame.setVisible(true);
        searchFrame.add(searchPanel);
        searchPanel.setLayout(null);

        // Define consistent positions and dimensions
        int labelWidth = 150;
        int fieldWidth = 200;
        int height = 25;
        int labelX = 10;
        int fieldX = 170;
        int ySpacing = 40;
        int startY = 30;

        // Name
        nameLabel = new JLabel("Patient Name:");
        nameLabel.setBounds(labelX, startY, labelWidth, height);
        searchPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(fieldX, startY, fieldWidth, height);
        searchPanel.add(nameField);

        // ID
        idLabel = new JLabel("Patient ID:");
        idLabel.setBounds(labelX, startY + ySpacing, labelWidth, height);
        searchPanel.add(idLabel);

        idField = new JTextField();
        idField.setBounds(fieldX, startY + ySpacing, fieldWidth, height);
        searchPanel.add(idField);

        // Phone Number
        phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setBounds(labelX, startY + 2 * ySpacing, labelWidth, height);
        searchPanel.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(fieldX, startY + 2 * ySpacing, fieldWidth, height);
        searchPanel.add(phoneField);

        // Search Button
        searchButton = new JButton("Search");
        searchButton.setBounds(labelX, startY + 3 * ySpacing, 100, height);
        searchButton.addActionListener(new PatientSearchUI());
        searchPanel.add(searchButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(fieldX, startY + 3 * ySpacing, 100, height);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DashboardUI(); // Navigate back to DashboardUI
                searchFrame.dispose(); // Close the current frame
            }
        });
        searchPanel.add(backButton);

        // Initialize patient database
        initializePatients();

        // Revalidate and repaint the panel
        searchPanel.revalidate();
        searchPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            try {
                // Get search criteria
                String name = nameField.getText().trim();
                String id = idField.getText().trim();
                String phone = phoneField.getText().trim();

                // Validate input
                if (name.isEmpty() && id.isEmpty() && phone.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter at least one search criterion.");
                    return;
                }

                // Search for matching patients
                for (Patient patient : patients) {
                    if (patient.matches(name.isEmpty() ? null : name, id.isEmpty() ? null : id, phone.isEmpty() ? null : phone)) {
                        JOptionPane.showMessageDialog(null, patient.toString());
                        return;
                    }
                }

                // If no match is found
                JOptionPane.showMessageDialog(null, "No matching patient found.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        initPatientSearchUI();
    }
}
