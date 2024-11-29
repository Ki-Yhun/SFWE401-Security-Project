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
    void setuserName(String userName) {
        this.userName = userName;

    }
    void setpassword(String password) {
        this.password = password;
    }
    void setfirstName(String firstName) {
        this.firstName = firstName;
    }
    void setlastName(String lastName) {
        this.lastName = lastName;
    }
    //Set
    String getuserName() {
        return userName;
    }
    String getpassword() {
        return password;
    }
    String getfirstName() {
        return firstName;
    }
    String getlastName() {
        return lastName;
    }

}
