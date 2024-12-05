import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

public class PrescriptionSummaryUI implements ActionListener {
    private static JLabel nameLabel;
    private static JTextField nameField;
    private static JLabel idLabel;
    private static JTextField idField;
    private static JButton searchButton;
    private static JButton backButton;
    private static JFrame summaryFrame;

    // Mock prescription database (for demonstration)
    private static HashMap<String, ArrayList<String>> activePrescriptions;
    private static HashMap<String, ArrayList<String>> pastPrescriptions;
    private static HashMap<String, String> patientData; // Maps name to ID

    // Initialize mock data
    private static void initializeMockData() {
        // Mock patient data
        patientData = new HashMap<>();
        patientData.put("John Doe", "12345");
        patientData.put("Jane Smith", "67890");
        patientData.put("Alice Johnson", "11223");

        // Active prescriptions
        activePrescriptions = new HashMap<>();
        activePrescriptions.put("12345", new ArrayList<>() {{
            add("Paracetamol - 500mg, 3x daily");
            add("Amoxicillin - 250mg, 2x daily");
        }});
        activePrescriptions.put("67890", new ArrayList<>() {{
            add("Ibuprofen - 400mg, as needed");
        }});
        activePrescriptions.put("11223", new ArrayList<>() {{
            add("Vitamin C - 1 tablet, once daily");
        }});

        // Past prescriptions
        pastPrescriptions = new HashMap<>();
        pastPrescriptions.put("12345", new ArrayList<>() {{
            add("Aspirin - 100mg, once daily");
        }});
        pastPrescriptions.put("67890", new ArrayList<>() {{
            add("Cough Syrup - 10ml, 2x daily");
            add("Vitamin D - 1 tablet, once daily");
        }});
        pastPrescriptions.put("11223", new ArrayList<>() {{
            add("Calcium - 500mg, 2x daily");
        }});
    }

    public static void initPrescriptionSummaryUI() {
        JPanel summaryPanel = new JPanel();
        summaryFrame = new JFrame("Prescription Summary");

        summaryFrame.setSize(500, 300);
        summaryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        summaryFrame.setVisible(true);
        summaryFrame.add(summaryPanel);
        summaryPanel.setLayout(null);

        // Define consistent positions and dimensions
        int labelWidth = 150;
        int fieldWidth = 250;
        int height = 25;
        int labelX = 10;
        int fieldX = 170;
        int ySpacing = 40;
        int startY = 30;

        // Name
        nameLabel = new JLabel("Patient Name:");
        nameLabel.setBounds(labelX, startY, labelWidth, height);
        summaryPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(fieldX, startY, fieldWidth, height);
        summaryPanel.add(nameField);

        // ID
        idLabel = new JLabel("Patient ID:");
        idLabel.setBounds(labelX, startY + ySpacing, labelWidth, height);
        summaryPanel.add(idLabel);

        idField = new JTextField();
        idField.setBounds(fieldX, startY + ySpacing, fieldWidth, height);
        summaryPanel.add(idField);

        // Search Button
        searchButton = new JButton("Search");
        searchButton.setBounds(labelX, startY + 2 * ySpacing, 100, height);
        searchButton.addActionListener(new PrescriptionSummaryUI());
        summaryPanel.add(searchButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(fieldX, startY + 2 * ySpacing, 100, height);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                summaryFrame.dispose(); // Close the current frame
            }
        });
        summaryPanel.add(backButton);

        // Initialize mock data
        initializeMockData();

        // Revalidate and repaint the panel
        summaryPanel.revalidate();
        summaryPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            try {
                // Get patient info
                String name = nameField.getText().trim();
                String id = idField.getText().trim();

                // Find the patient's ID if only the name is provided
                if (!name.isEmpty() && id.isEmpty()) {
                    id = patientData.get(name);
                    if (id == null) {
                        JOptionPane.showMessageDialog(null, "No patient found with the name: " + name);
                        return;
                    }
                }

                // Validate input
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a patient name or ID.");
                    return;
                }

                // Search for prescriptions
                if (!activePrescriptions.containsKey(id) && !pastPrescriptions.containsKey(id)) {
                    JOptionPane.showMessageDialog(null, "No prescription data found for the provided ID.");
                    return;
                }

                // Display prescription data
                StringBuilder summary = new StringBuilder("Prescription Summary for Patient ID: " + id + "\n\n");

                // Active prescriptions
                summary.append("Active Prescriptions:\n");
                if (activePrescriptions.containsKey(id)) {
                    for (String prescription : activePrescriptions.get(id)) {
                        summary.append("- ").append(prescription).append("\n");
                    }
                } else {
                    summary.append("None\n");
                }

                // Past prescriptions
                summary.append("\nPast Prescriptions:\n");
                if (pastPrescriptions.containsKey(id)) {
                    for (String prescription : pastPrescriptions.get(id)) {
                        summary.append("- ").append(prescription).append("\n");
                    }
                } else {
                    summary.append("None\n");
                }

                // Show summary in a dialog
                JTextArea summaryArea = new JTextArea(summary.toString());
                summaryArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(summaryArea);
                JOptionPane.showMessageDialog(null, scrollPane, "Prescription Summary", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        initPrescriptionSummaryUI();
    }
}
