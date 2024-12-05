import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NotificationUI implements ActionListener {
    private static JLabel notificationMethodLabel;
    private static JCheckBox smsCheckbox;
    private static JLabel phoneNumberLabel;
    private static JTextField phoneNumberField;
    private static JCheckBox emailCheckbox;
    private static JLabel emailLabel;
    private static JTextField emailField;
    private static JCheckBox phoneCallCheckbox;
    private static JLabel phoneCallTimeLabel;
    private static JComboBox<String> phoneCallTimeDropdown;
    private static JButton saveButton;
    private static JButton backButton;
    private static JButton testPickupButton;
    private static JButton testRefillButton;
    private static JFrame notificationFrame;

    public static void initNotificationUI() {
        JPanel notificationPanel = new JPanel();
        notificationFrame = new JFrame("Notification Preferences");

        notificationFrame.setSize(500, 500);
        notificationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        notificationFrame.setVisible(true);
        notificationFrame.add(notificationPanel);
        notificationPanel.setLayout(null);

        // Define consistent positions and dimensions
        int labelWidth = 150;
        int fieldWidth = 250;
        int height = 25;
        int labelX = 10;
        int fieldX = 170;
        int ySpacing = 40;
        int startY = 20;

        // Notification Method Label
        notificationMethodLabel = new JLabel("Notification Preferences:");
        notificationMethodLabel.setBounds(labelX, startY, fieldWidth, height);
        notificationPanel.add(notificationMethodLabel);

        // SMS Checkbox and Phone Number Field
        smsCheckbox = new JCheckBox("Receive SMS");
        smsCheckbox.setBounds(labelX, startY + ySpacing, fieldWidth, height);
        notificationPanel.add(smsCheckbox);

        phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setBounds(labelX, startY + 2 * ySpacing, labelWidth, height);
        notificationPanel.add(phoneNumberLabel);

        phoneNumberField = new JTextField();
        phoneNumberField.setBounds(fieldX, startY + 2 * ySpacing, fieldWidth, height);
        notificationPanel.add(phoneNumberField);

        // Email Checkbox and Email Field
        emailCheckbox = new JCheckBox("Receive Email");
        emailCheckbox.setBounds(labelX, startY + 3 * ySpacing, fieldWidth, height);
        notificationPanel.add(emailCheckbox);

        emailLabel = new JLabel("Email Address:");
        emailLabel.setBounds(labelX, startY + 4 * ySpacing, labelWidth, height);
        notificationPanel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(fieldX, startY + 4 * ySpacing, fieldWidth, height);
        notificationPanel.add(emailField);

        // Phone Call Checkbox and Preferred Time Dropdown
        phoneCallCheckbox = new JCheckBox("Receive Phone Calls");
        phoneCallCheckbox.setBounds(labelX, startY + 5 * ySpacing, fieldWidth, height);
        notificationPanel.add(phoneCallCheckbox);

        phoneCallTimeLabel = new JLabel("Preferred Call Time:");
        phoneCallTimeLabel.setBounds(labelX, startY + 6 * ySpacing, labelWidth, height);
        notificationPanel.add(phoneCallTimeLabel);

        String[] callTimes = {"Morning (9 AM - 12 PM)", "Afternoon (12 PM - 5 PM)", "Evening (5 PM - 9 PM)"};
        phoneCallTimeDropdown = new JComboBox<>(callTimes);
        phoneCallTimeDropdown.setBounds(fieldX, startY + 6 * ySpacing, fieldWidth, height);
        notificationPanel.add(phoneCallTimeDropdown);

        // Save Button
        saveButton = new JButton("Save Preferences");
        saveButton.setBounds(labelX, startY + 7 * ySpacing, 150, height);
        saveButton.addActionListener(new NotificationUI());
        notificationPanel.add(saveButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(labelX + 250, startY + 7 * ySpacing + 100, 100, height);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DashboardUI(); // Navigate back to the dashboard
                notificationFrame.dispose(); // Close the current frame
            }
        });
        notificationPanel.add(backButton);

        // Test Pickup Notification Button
        testPickupButton = new JButton("Test Pickup Notification");
        testPickupButton.setBounds(labelX, startY + 8 * ySpacing, 200, height);
        testPickupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTestPickupNotificationWindow();
            }
        });
        notificationPanel.add(testPickupButton);

        // Test Refill Notification Button
        testRefillButton = new JButton("Test Refill Notification");
        testRefillButton.setBounds(labelX + 210, startY + 8 * ySpacing, 200, height);
        testRefillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTestNotificationWindowForRefills();
            }
        });
        notificationPanel.add(testRefillButton);

        // Revalidate and repaint the panel
        notificationPanel.revalidate();
        notificationPanel.repaint();
    }

    // Action for the "Test Pickup Notification" button
    private static void showTestPickupNotificationWindow() {
        TestPickupNotificationWindow.initTestPickupNotificationWindow();
    }
    

    // Action for the "Test Refill Notification" button
    private static void showTestNotificationWindowForRefills() {
        TestNotificationWindowForRefills.initTestNotificationWindowForRefills();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            try {
                // Gather notification preferences
                boolean smsEnabled = smsCheckbox.isSelected();
                String phoneNumber = phoneNumberField.getText().trim();
                boolean emailEnabled = emailCheckbox.isSelected();
                String emailAddress = emailField.getText().trim();
                boolean phoneCallEnabled = phoneCallCheckbox.isSelected();
                String preferredCallTime = (String) phoneCallTimeDropdown.getSelectedItem();

                // Validate input
                if (smsEnabled && phoneNumber.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a phone number to receive SMS.");
                    return;
                }
                if (emailEnabled && emailAddress.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter an email address to receive email notifications.");
                    return;
                }
                if (!smsEnabled && !emailEnabled && !phoneCallEnabled) {
                    JOptionPane.showMessageDialog(null, "Please select at least one notification method.");
                    return;
                }

                // Close the current NotificationUI
                notificationFrame.dispose();

                // Open a new UI window to display the entered information
                showNotificationInfoUI(smsEnabled, phoneNumber, emailEnabled, emailAddress, phoneCallEnabled, preferredCallTime);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
            }
        }
    }

    // New UI to display entered information
    private void showNotificationInfoUI(boolean smsEnabled, String phoneNumber, boolean emailEnabled, String emailAddress, boolean phoneCallEnabled, String preferredCallTime) {
        JFrame infoFrame = new JFrame("Notification Preferences Summary");
        infoFrame.setSize(400, 300);
        infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea infoArea = new JTextArea();
        infoArea.setEditable(false);
        infoArea.setText("Notification Preferences:\n");
        if (smsEnabled) {
            infoArea.append("SMS: Enabled (Phone: " + phoneNumber + ")\n");
        }
        if (emailEnabled) {
            infoArea.append("Email: Enabled (Address: " + emailAddress + ")\n");
        }
        if (phoneCallEnabled) {
            infoArea.append("Phone Calls: Enabled (Preferred Time: " + preferredCallTime + ")\n");
        }

        JScrollPane scrollPane = new JScrollPane(infoArea);
        infoFrame.add(scrollPane);

        infoFrame.setVisible(true);
    }

    public static void main(String[] args) {
        initNotificationUI();
    }
}

