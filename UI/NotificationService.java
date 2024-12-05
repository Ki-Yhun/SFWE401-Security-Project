public class NotificationService {

    // Enum to define different types of notifications
    public enum NotificationType {
        SMS, EMAIL, PHONE
    }

    // Simulate sending an SMS notification
    public void sendSMS(String phoneNumber, String message) {
        System.out.println("Sending SMS to " + phoneNumber + ": " + message);
    }

    // Simulate sending an email notification
    public void sendEmail(String email, String message) {
        System.out.println("Sending email to " + email + ": " + message);
    }

    // Simulate making a phone call
    public void makePhoneCall(String phoneNumber, String message) {
        System.out.println("Calling " + phoneNumber + ": " + message);
    }

    // Generic method to send a notification
    public void sendNotification(NotificationType type, String contactInfo, String message) {
        switch (type) {
            case SMS:
                sendSMS(contactInfo, message);
                break;
            case EMAIL:
                sendEmail(contactInfo, message);
                break;
            case PHONE:
                makePhoneCall(contactInfo, message);
                break;
        }
    }
}

