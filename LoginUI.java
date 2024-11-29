import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.HashMap;

public class LoginUI implements ActionListener {
    private static JLabel userNameLabel;
    private static JTextField userNameField;
    private static JLabel passwordLabel;
    private static JPasswordField passwordField;
    private static JButton loginButton;

    //Debug purposes only - move to separate file/class later with hashed passwords and user objects
    private static Map<String, String> debugUserPass = new HashMap<>();
    //Replace function declaration with below to unit test)
    //public static void main(string[] args) {
    public static void initLoginUI() {
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
        /*
        JLabel labelname = new JLabel("Label Text Here");
        userNameLabel.setBounds(x, y, width, height);
        loginPanel.add(labelname);
         */

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
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(new LoginUI());
        loginPanel.add(loginButton);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = userNameField.getText().toLowerCase();
        String password = new String(passwordField.getPassword());
        if(debugUserPass.containsKey(username)) {
            if(debugUserPass.get(username).equals(password)) {
                //Replace with next panel logic
                JOptionPane.showMessageDialog(null, "You are logged in!");
            }
            else{
                JOptionPane.showMessageDialog(null, "Invalid password, please try again.");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Username does not exist.");
        }
    }
}
