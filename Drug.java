


public class Drug {
    public String name = null;
    public String expirationDate = null;
    public boolean expired = false;
    public boolean controlled = false;
    public float inventory = 0;

    //Constructors
    public Drug(String inputName, String inputDate) {
        this.name = inputName;
        this.expirationDate = inputDate;
        this.expired = false;
        this.inventory = 0;
    }
    public Drug(String inputName, String inputDate, boolean controlledStatus) {
        this.name = inputName;
        this.expirationDate = inputDate;
        this.expired = false;
        this.inventory = 0;
        this.controlled = controlledStatus;
    }

    public Drug(){
        this.name = "N/A";
        this.expirationDate = "N/A";
        this.expired = false;
        this.controlled = false;
        this.inventory = 0;

    }

    //Set
    void setName(String inputName) {
        this.name = inputName;
    }

    void setExpirationDate(String inputDate) {
        this.expirationDate = inputDate;
    }

    void setExpired(boolean inputExpired) {
        this.expired = inputExpired;
    }

    void setInventory(float inputInventory) {
        if(inputInventory <= 0){
            this.inventory = 0;
        }
        else {
            this.inventory = inputInventory;
        }
    }


    //Get
    String getName() {
        return this.name;
    }
    String getExpirationDate() {
        return this.expirationDate;
    }
    boolean getExpired() {
        return this.expired;
    }
    boolean getControlled() {
        return this.controlled;
    }
    float getInventory() {
        return this.inventory;
    }


}
