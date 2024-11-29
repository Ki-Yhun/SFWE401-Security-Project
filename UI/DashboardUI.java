import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardUI {
    public DashboardUI() {
        JFrame dashboardFrame = new JFrame("Dashboard");
        dashboardFrame.setSize(400, 300);
        dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        dashboardFrame.add(panel);
        placeComponents(panel);

        dashboardFrame.setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JButton generateReportButton = new JButton("Inventory Report");
        generateReportButton.setBounds(120, 100, 150, 25);
        panel.add(generateReportButton);

        // Action Listener to open Report Selection
        generateReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InventoryReportSelectionUI();
                ((JFrame) SwingUtilities.getWindowAncestor(panel)).dispose();
            }
        });
    }
}
