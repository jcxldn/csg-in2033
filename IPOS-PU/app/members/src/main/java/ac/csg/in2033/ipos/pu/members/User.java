package ac.csg.in2033.ipos.pu.members;

public class User {


    private String password;
    private String userType;
    private String email;

    public User(String email, String password, String userType) {

        this.email = email;
        this.password = password;
        this.userType = userType;
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }

}