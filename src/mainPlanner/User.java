/**
 * @author Dina & Karoon
 * 
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
 
    private String username = "Constructor Username";
    private String userPassword = "Constructor Password";
    static final String fileName = "PAM_User.ser";
    private int weekOfYear = 0;
 
    public User(String username, String password, int w) {
        this.username = username;
        this.userPassword = password;
        this.weekOfYear = w;    
    }
 
    public void printInfo() {
        System.out.println("username: " + username);
        System.out.println("password: " + userPassword);
        System.out.println("week of year: " + weekOfYear);
    }
 
    // getters and setters
    
    public void setUserName(String name) {
       this.username = name;
    }
    
    public void setUserPassword(String password) {
        this.userPassword = password;
    } 
    
    public void setWeekOfYear(int d) {
        this.weekOfYear = d;
    } 
       
     
    public int getWeekOfYear() {
       return weekOfYear;
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
        System.out.println("Serializing User...");
        user.printInfo();
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
            fis.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex);
        }
        System.out.println("Deserialized User...");
        savedUser.printInfo();
        
        return savedUser;
    }  
}
