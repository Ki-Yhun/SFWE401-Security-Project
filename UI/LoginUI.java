import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class LoginUI implements ActionListener {
    
    private static JTextField userNameField;
    private static JPasswordField passwordField;
    private static final DashboardUI dashboardUI = new DashboardUI();

    //Debug purposes only - move to separate file/class later with hashed passwords and user objects
    private static final Map<String, String> debugUserPass = new HashMap<>();
    //Replace function declaration with below to unit test)
    public static void initLoginUI() {
        JButton loginButton;
        JLabel passwordLabel;
        JLabel userNameLabel;
        debugUserPass.put("taz", "12345");
        debugUserPass.put("elitired", "asdf");

        JPanel loginPanel = new JPanel();
        JFrame loginFrame = new JFrame("Login");
        //Setting the frame to visible
        loginFrame.setSize(500, 500);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);
        loginFrame.add(loginPanel);
        loginPanel.setLayout(null);

        //Username
        userNameLabel = new JLabel("Username:");
        userNameLabel.setBounds(10, 20, 80, 25);
        loginPanel.add(userNameLabel);

        userNameField = new JTextField();
        userNameField.setBounds(100, 20, 150, 25);
        loginPanel.add(userNameField);

        //Password
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 60, 80, 25);
        loginPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 60, 150, 25);
        loginPanel.add(passwordField);

        //Login Button
        loginButton = new JButton("Login");
        loginButton.setToolTipText("Click to log in");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(new LoginUI());
        loginPanel.add(loginButton);

        // Revalidate and repaint the panel (makes the gui show properly upon instillation)
        loginPanel.revalidate();
        loginPanel.repaint();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = userNameField.getText().toLowerCase();
        String password = new String(passwordField.getPassword());
        if(debugUserPass.containsKey(username)) {
            if(debugUserPass.get(username).equals(password)) {
                //Replace with next panel logic
                JOptionPane.showMessageDialog(null, "You are logged in!");

                // New, adding dashboard that pops up
                // Close Login Window
                SwingUtilities.getWindowAncestor(userNameField).dispose();
                // Open Dashboard
                dashboardUI.checkForAlerts();
            }
            else{
                JOptionPane.showMessageDialog(null, "Invalid password, please try again.");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Username does not exist.");
        }
    }

    // A main method to run the Login UI
    public static void main(String[] args) {
        initLoginUI();
    }
}