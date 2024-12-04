public class User {
    public String userName;
    //replace password with hashed password later
    public String password;
    public String firstName;
    public String lastName;
    //Constructors
    public User(String userName, String password, String firstName, String lastName) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public User(){
        this.userName = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
    }
    //Get
    public void setuserName(String userName) {
        this.userName = userName;

    }
    public void setpassword(String password) {
        this.password = password;
    }
    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setlastName(String lastName) {
        this.lastName = lastName;
    }
    //Set
    public String getuserName() {
        return userName;
    }
    public String getpassword() {
        return password;
    }
    public String getfirstName() {
        return firstName;
    }
    public String getlastName() {
        return lastName;
    }

}
