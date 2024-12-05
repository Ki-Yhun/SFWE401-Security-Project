import javax.swing.*;
import java.awt.*;

public class TestPickupNotificationWindow {

    public static void initTestPickupNotificationWindow() {

        Patient patient = new Patient("user123", "password123", "John", "Doe");
        patient.setDOB("01/01/1980");
        patient.updateContactInfo(5550100, "john.doe@example.com");

        NotificationService notificationService = new NotificationService();
        
        Drug drug = new Drug("Aspirin", "2025-12-31", false); 
        Prescription prescription = new Prescription(patient, drug, 100, "Take one tablet daily.");
        patient.addPrescription(prescription);

        JFrame frame = new JFrame("Simulated Phone Output");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Set up the frame visibility
        frame.setVisible(true);

        String message = "Simulating notifications for patient: " + patient.getfirstName() + " " + patient.getlastName() + "\n";
        textArea.append(message);

        String drugName = prescription.getDrug().getName();
        float dosage = prescription.getDosage();
        
        String prescriptionMessage = "Your prescription for " + drugName + " (" + dosage + " mg) is ready for pickup.\n";

        // Notify the patient via SMS, Email, and Phone and display the output in the UI
        textArea.append("Sending SMS to " + patient.getPhoneNumber() + ": " + prescriptionMessage);
        notificationService.sendNotification(NotificationService.NotificationType.SMS, String.valueOf(patient.getPhoneNumber()), prescriptionMessage);

        textArea.append("\n\nSending email to " + patient.getEmail() + ": \n");

        // Simulate a complete email message
        String emailSubject = "Your Prescription for " + drugName + " is Ready for Pickup at Radical New Pharmacy";
        String emailBody = 
            "Dear " + patient.getfirstName() + " " + patient.getlastName() + ",\n\n" +
            "We are pleased to inform you that your prescription is now ready for pickup at Radical New Pharmacy.\n\n" +
            "Prescription Details:\n" +
            "-----------------------\n" +
            "- Drug Name: " + drugName + "\n" +
            "- Dosage: " + dosage + " mg\n" +
            "- Instructions: Take one tablet daily as directed by your physician.\n\n" +
            "Please visit us during our business hours to pick up your medication. If you have any questions or need further assistance, don't hesitate to contact us.\n\n" +
            "Our pharmacy is located at:\n" +
            "Radical New Pharmacy\n" +
            "123 Health St, Wellness City, XYZ\n\n" +
            "Phone: 555-0100\n" +
            "Email: contact@radicalpharmacy.com\n\n" +
            "Thank you for choosing Radical New Pharmacy!\n\n" +
            "Sincerely,\n" +
            "The Radical New Pharmacy Team\n\n";

        // Display the email subject and body in the simulated UI
        textArea.append("Subject: " + emailSubject + "\n");
        textArea.append(emailBody + "\n");

        // Send the email notification (simulate sending the email)
        notificationService.sendNotification(NotificationService.NotificationType.EMAIL, patient.getEmail(), emailSubject + "\n\n" + emailBody);

        textArea.append("Calling " + patient.getPhoneNumber() + ": " + prescriptionMessage);
        notificationService.sendNotification(NotificationService.NotificationType.PHONE, String.valueOf(patient.getPhoneNumber()), prescriptionMessage);
    }
}





