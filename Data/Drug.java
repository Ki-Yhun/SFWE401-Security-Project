


public class Drug {
    public String name = null;
    public String expirationDate = null;
    public boolean expired = false;
    public boolean controlled = false;
    public float inventory = 0;
    public int weeklyUsage = 0;
    public int monthlyUsage = 0;
    public int yearlyUsage = 0;

    //Constructors
    public Drug(String inputName, String inputDate, boolean controlledStatus) {
        this.name = inputName;
        this.expirationDate = inputDate;
        this.controlled = controlledStatus;
        this.expired = false;
        this.inventory = 0;
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

    public void setWeeklyUsage(int usage) {
        this.weeklyUsage = usage;
    }

    public void setMonthlyUsage(int usage) {
        this.monthlyUsage = usage;
    }

    public void setYearlyUsage(int usage) {
        this.yearlyUsage = usage;
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
    public int getWeeklyUsage() { return this.weeklyUsage; }
    public int getMonthlyUsage() { return this.monthlyUsage; }
    public int getYearlyUsage() { return this.yearlyUsage; }
}
