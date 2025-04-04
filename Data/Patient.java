import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;

public class Patient extends User{
    public String patientDOB;
    public List<String> allergies = new ArrayList<>();
    public List<Prescription> prescriptions = new ArrayList<>();
    public String email;
    public int phoneNumber;
    public String insurance;


    public Patient(){
        super();
        this.patientDOB = "";
        this.allergies = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
        this.email = "";
        this.phoneNumber = 0;
        this.insurance = "";
    }

    public Patient(String userName, String password, String firstName, String lastName){
        super(userName, password, firstName, lastName);
        this.patientDOB = "";
        this.allergies = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
        this.email = "";
        this.phoneNumber = 0;
        this.insurance = "";
    }


    public void setDOB(String DOB){
        this.patientDOB = DOB;
    }
    public String getDOB(){
        return this.patientDOB;
    }

    //Allergies list
    public void addAllergy(String allergy){
        this.allergies.add(allergy);
    }
    public boolean removeAllergy(String allergy){
        return this.allergies.remove(allergy);
    }
    //Getting allergy list
    public List<String> getAllergies(){
        return this.allergies;
    }

    //Prescriptions lists -- will probably need to be changed maybe unless UI implements entire prescription and not just name
    public void addPrescription(Prescription p){
        this.prescriptions.add(p);
    }
    public boolean removePrescription(Prescription p){
        return this.prescriptions.remove(p);
    }
    //Getting prescription list
    public List<Prescription> getPrescriptions(){
        return this.prescriptions;
    }

    //Updating contact info (email as string/phonenumber as int)
    public void updateContactInfo(int phoneNumber, String email){
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    public void updateContactInfo(int phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public void updateContactInfo(String email){
        this.email = email;
    }
    //Getting contact information
    public String getEmail(){
        return this.email;
    }
    public int getPhoneNumber(){
        return this.phoneNumber;
    }
    //Insurance
    public void setInsurance(String insurance){
        this.insurance = insurance;
    }
    public String getInsurance(){
        return this.insurance;
    }

    public void notifyPrescriptionReady(NotificationService.NotificationType notificationType, NotificationService notificationService) {
        String message = "Your prescription is ready for pickup!";
        
        if (notificationType == NotificationService.NotificationType.SMS) {
            notificationService.sendNotification(notificationType, String.valueOf(this.phoneNumber), message);
        } else if (notificationType == NotificationService.NotificationType.EMAIL) {
            notificationService.sendNotification(notificationType, this.email, message);
        } else if (notificationType == NotificationService.NotificationType.PHONE) {
            notificationService.sendNotification(notificationType, String.valueOf(this.phoneNumber), message);
        }
    }

    public String toCSV(){
        // Returns lastname firstname, DOB, email, phone number, insurance, allergies, prescriptions, username, password
        return this.getlastName() + ";" + this.getfirstName() + ";" + this.getDOB() + ";" + this.getEmail() + ";" + this.getPhoneNumber() + ";" + this.getInsurance() + ";" + this.getAllergies() + ";" + this.getPrescriptions() + ";" + this.getuserName() + ";" + this.getpassword();
    }

    public void writeToDatabase(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("PatientData.csv", true))) {
            writer.write(this.toCSV());
            writer.newLine();

            System.out.println("patientData added successfully to PatientData.csv");
        } catch (IOException e) {
            System.err.println("Error writing to file: PatientData.csv");
        }
    }

    public void writeToEncryptedDatabase(){
        String data = this.toCSV();
        String encodedData = Base64.getEncoder().encodeToString(data.getBytes());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("EncryptedPatientData.csv", true))) {

            writer.write(encodedData);
            writer.newLine();

            System.out.println("Patient data encrypted with Base64 added successfully to EncryptedPatientData.csv");
        } catch (IOException e) {
            System.err.println("Error writing to file: EncryptedPatientData.csv");
        }
    }

}
