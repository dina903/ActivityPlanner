/**
 * @author Dina & Karoon
 */

package mainPlanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class User implements Serializable {
 
    private static final long serialVersionUID = 1234L;
 
    private String username = "Test construc Username";
    private transient String userPassword = "Test construc Password";
    static final String fileName = "PAM_User.ser";
 
    public User(String username, String password) {
        this.username = username;
        this.userPassword = password;
    }
 
    public void printInfo() {
        System.out.println("username: " + username);
        System.out.println("password: " + userPassword);
    }
 
    // getters and setters
    
    public void setUserName(String name) {
       this.username = name;
    }
    
    public void setUserPassword(String password) {
        this.userPassword = password;
    } 

    public String getUserName() {
       return username;
    }
    
    public String getUserPassword() {
        return userPassword;
    }
    
    public void initSave() {
        serialize(this);
        System.out.println("saving file");
    }
     
    //Save and retrieve data
    static void serialize(User user) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject(user);
            outputStream.close();
        } 
        catch (IOException ex) {
            System.err.println(ex);
        }    
    }
    
    static User deserialize() {
        User savedUser = null;

        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream inputStream = new ObjectInputStream(fis);
            savedUser = (User) inputStream.readObject();
            inputStream.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex);
        }
        return savedUser;
    }  
}
