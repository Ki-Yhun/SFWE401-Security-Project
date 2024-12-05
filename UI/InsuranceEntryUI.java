import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InsuranceEntryUI implements ActionListener {
    private static JLabel policyNumberLabel;
    private static JTextField policyNumberField;
    private static JLabel providerNameLabel;
    private static JTextField providerNameField;
    private static JLabel coverageTypeLabel;
    private static JComboBox<String> coverageTypeDropdown;
    private static JLabel startDateLabel;
    private static JTextField startDateField;
    private static JLabel endDateLabel;
    private static JTextField endDateField;
    private static JLabel policyHolderNameLabel;
    private static JTextField policyHolderNameField;
    private static JButton backButton; 
    private static JButton enterButton;
    private static JFrame entryFrame;

    public static void initInsuranceEntryUI() {
        JPanel entryPanel = new JPanel();
        entryFrame = new JFrame("Insurance Entry");

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

        // Policy Number
        policyNumberLabel = new JLabel("Policy Number:");
        policyNumberLabel.setBounds(labelX, startY, labelWidth, height);
        entryPanel.add(policyNumberLabel);

        policyNumberField = new JTextField();
        policyNumberField.setBounds(fieldX, startY, fieldWidth, height);
        entryPanel.add(policyNumberField);

        // Provider Name
        providerNameLabel = new JLabel("Provider Name:");
        providerNameLabel.setBounds(labelX, startY + ySpacing, labelWidth, height);
        entryPanel.add(providerNameLabel);

        providerNameField = new JTextField();
        providerNameField.setBounds(fieldX, startY + ySpacing, fieldWidth, height);
        entryPanel.add(providerNameField);

        // Coverage Type
        coverageTypeLabel = new JLabel("Coverage Type:");
        coverageTypeLabel.setBounds(labelX, startY + 2 * ySpacing, labelWidth, height);
        entryPanel.add(coverageTypeLabel);

        String[] coverageTypes = {"Health", "Dental", "Vision", "Life"};
        coverageTypeDropdown = new JComboBox<>(coverageTypes);
        coverageTypeDropdown.setBounds(fieldX, startY + 2 * ySpacing, fieldWidth, height);
        entryPanel.add(coverageTypeDropdown);

        // Start Date
        startDateLabel = new JLabel("Start Date:");
        startDateLabel.setBounds(labelX, startY + 3 * ySpacing, labelWidth, height);
        entryPanel.add(startDateLabel);

        startDateField = new JTextField("YYYY-MM-DD");
        startDateField.setBounds(fieldX, startY + 3 * ySpacing, fieldWidth, height);
        entryPanel.add(startDateField);

        // End Date
        endDateLabel = new JLabel("End Date:");
        endDateLabel.setBounds(labelX, startY + 4 * ySpacing, labelWidth, height);
        entryPanel.add(endDateLabel);

        endDateField = new JTextField("YYYY-MM-DD");
        endDateField.setBounds(fieldX, startY + 4 * ySpacing, fieldWidth, height);
        entryPanel.add(endDateField);

        // Policy Holder Name
        policyHolderNameLabel = new JLabel("Policy Holder Name:");
        policyHolderNameLabel.setBounds(labelX, startY + 5 * ySpacing, labelWidth, height);
        entryPanel.add(policyHolderNameLabel);

        policyHolderNameField = new JTextField();
        policyHolderNameField.setBounds(fieldX, startY + 5 * ySpacing, fieldWidth, height);
        entryPanel.add(policyHolderNameField);

        // Enter Button
        enterButton = new JButton("Enter");
        enterButton.setBounds(labelX, startY + 6 * ySpacing, 100, height);
        enterButton.addActionListener(new InsuranceEntryUI());
        entryPanel.add(enterButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(labelX + 250, startY + 7 * ySpacing + 100, 100, height);
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
            String policyNumber = policyNumberField.getText().trim();
            String providerName = providerNameField.getText().trim();
            String coverageType = (String) coverageTypeDropdown.getSelectedItem();
            String startDate = startDateField.getText().trim();
            String endDate = endDateField.getText().trim();
            String policyHolderName = policyHolderNameField.getText().trim();

            // Validate input
            if (policyNumber.isEmpty() || providerName.isEmpty() || policyHolderName.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all required fields.");
                return;
            }

            // Close the current InsuranceEntryUI
            entryFrame.dispose();

            // Open a new UI window to display the entered information
            showInsuranceInfoUI(policyNumber, providerName, coverageType, startDate, endDate, policyHolderName);
        }
    }

    // New UI to display entered information
    private void showInsuranceInfoUI(String policyNumber, String providerName, String coverageType, String startDate, String endDate, String policyHolderName) {
        JFrame infoFrame = new JFrame("Insurance Information");
        infoFrame.setSize(400, 400);
        infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setText("Insurance Information:\n");
        infoArea.append("Policy Number: " + policyNumber + "\n");
        infoArea.append("Provider Name: " + providerName + "\n");
        infoArea.append("Coverage Type: " + coverageType + "\n");
        infoArea.append("Start Date: " + startDate + "\n");
        infoArea.append("End Date: " + endDate + "\n");
        infoArea.append("Policy Holder Name: " + policyHolderName);

        JScrollPane scrollPane = new JScrollPane(infoArea);
        infoFrame.add(scrollPane);

        infoFrame.setVisible(true);
    }

    public static void main(String[] args) {
        initInsuranceEntryUI();
    }
}
