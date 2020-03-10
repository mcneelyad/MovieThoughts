package JavaBeans;

import java.io.Serializable;

/**
 * @author mcneelyad
 */
public class User implements Serializable {

    private String userID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User() {
        userID = "";
        firstName = "";
        lastName = "";
        email = "";
        password = "";
    }

    public User(String userID, String firstName, String lastName,
            String email, String password) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    /*
        get() methods
     */
    public String getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }

    
    /*
        set() methods
     */
    public void setUserID(String userID) 
    {
        this.userID = userID;
    }
    
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
}
