public class Prescription{
    //Replace patientName/patientDOB strings with appropriate User object later**
    public Patient presPatient;
    protected Drug presDrug;
    protected float presDosage;
    protected String instructions;

    public Prescription(){
        this.presPatient = null;
        this.presDrug = null;
        this.presDosage = 0;
        this.instructions = "";

    }

    public Prescription(Patient patient, Drug drug, float dosage, String instructions){
        this.presPatient = patient;
        this.presDrug = drug;
        this.presDosage = dosage;
        this.instructions = instructions;

    }
    //Set
    public void setPatient(Patient patient){
        this.presPatient = patient;
    }
    public void setDrug(Drug drug){
        this.presDrug = drug;
    }
    public void setDosage(float dosage){
        this.presDosage = dosage;
    }
    public void setInstructions(String instructions){
        this.instructions = instructions;
    }
    //Get
    public Patient getPatient(){
        return this.presPatient;
    }
    public Drug getDrug(){
        return this.presDrug;
    }
    public float getDosage(){
        return this.presDosage;
    }
    public String getInstructions(){
        return this.instructions;
    }

}
