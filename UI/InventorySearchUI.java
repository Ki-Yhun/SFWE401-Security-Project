import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.*; // For mock inventory storage

public class InventorySearchUI implements ActionListener {
    private static JLabel medicationLabel;
    private static JTextField medicationField;
    private static JButton searchButton;
    private static JButton backButton; // Back button
    private static JFrame searchFrame;

    // Mock inventory (for demonstration purposes)
    private static HashMap<String, Integer> inventory;

    // Initialize inventory with sample data
    private static void initializeInventory() {
        inventory = new HashMap<>();
        inventory.put("Paracetamol", 120);
        inventory.put("Ibuprofen", 50);
        inventory.put("Amoxicillin", 75);
        inventory.put("Aspirin", 0); // Out of stock example
    }

    public static void initInventorySearchUI() {
        JPanel searchPanel = new JPanel();
        searchFrame = new JFrame("Inventory Search");

        searchFrame.setSize(400, 250);
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

        // Medication Name
        medicationLabel = new JLabel("Medication Name:");
        medicationLabel.setBounds(labelX, startY, labelWidth, height);
        searchPanel.add(medicationLabel);

        medicationField = new JTextField();
        medicationField.setBounds(fieldX, startY, fieldWidth, height);
        searchPanel.add(medicationField);

        // Search Button
        searchButton = new JButton("Check Stock");
        searchButton.setBounds(labelX, startY + ySpacing, 150, height);
        searchButton.addActionListener(new InventorySearchUI());
        searchPanel.add(searchButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(labelX + 200, startY + ySpacing, 100, height);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFrame.dispose(); // Close the current frame
            }
        });
        searchPanel.add(backButton);

        // Initialize inventory
        initializeInventory();

        // Revalidate and repaint the panel
        searchPanel.revalidate();
        searchPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            try {
                // Get the medication name
                String medicationName = medicationField.getText().trim();

                // Validate input
                if (medicationName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a medication name.");
                    return;
                }

                // Check stock level
                if (inventory.containsKey(medicationName)) {
                    int stockLevel = inventory.get(medicationName);
                    if (stockLevel > 0) {
                        JOptionPane.showMessageDialog(null, "Stock Level for " + medicationName + ": " + stockLevel);
                    } else {
                        JOptionPane.showMessageDialog(null, medicationName + " is currently out of stock.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, medicationName + " is not found in the inventory.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        initInventorySearchUI();
    }
}
