import java.awt.*;
import javax.swing.*;

public class TestNotificationWindowForRefills {

    private static JTextArea displayArea;

    public static void initTestNotificationWindowForRefills() {
        JFrame frame = new JFrame("Simulated Phone Screen");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Arial", Font.PLAIN, 16));
        displayArea.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        String patientName = "John Doe";
        String prescriptionName = "Lipitor";
        String message = "Reminder: Your prescription for " + prescriptionName
                + " is nearing its end. Please schedule a refill.";

        displayNotification("[SMS Reminder] " + patientName + ": " + message);
        displayNotification("[Email Reminder] " + patientName + ": " + message);
        displayNotification("[Phone Call Reminder] " + patientName + ": " + message);

        frame.setVisible(true);
    }

    static void displayNotification(String message) {
        displayArea.append(message + "\n\n");
        displayArea.setCaretPosition(displayArea.getDocument().getLength()); 
    }
}











