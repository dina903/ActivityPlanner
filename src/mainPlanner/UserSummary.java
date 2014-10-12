/****@author Karoon & Dina *****/
package mainPlanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;


public class UserSummary implements Serializable{

    String username = "newConstructName";
    static ArrayList<UserSummary> myList = new ArrayList<UserSummary>();
    
    int weeklyCalConsumed = 0;
    int weeklyCalBurned = 0;
    int weeklyStudHrs = 0;
    int weekCount = 0;
    static String fileName = "PAM_User_Summary.ser";

    UserSummary(String userName, int weekNum, int totalCal, int totalBurn, int totalHrs) {
        this.username = userName;
        this.weeklyCalConsumed = totalCal;
        this.weekCount = weekNum;
        this.weeklyCalBurned = totalBurn;
        this.weeklyStudHrs = totalHrs;
    }

    public void printInfo() {
        System.out.println("username: " + username);
        System.out.println("week number: " + weekCount);
        System.out.println("Total calories consumed: " + weeklyCalConsumed);
        System.out.println("Total calories Burned: " + weeklyCalBurned);
        System.out.println("Total Studying Hours: " + weeklyStudHrs);
    }
    //set methods for the week object
    public void setUserName(String name) {
        this.username = name;
    }
    public void setWeekNum(int num) {
        this.weekCount = num;
    }
    public void setWeeklyCalCons(int cal) {
        this.weeklyCalConsumed = cal;
    }
    public void setWeeklyCalBurn(int burn) {
        this.weeklyCalBurned = burn;
    }
    public void setWeeklyStudHrs(int hrs) {
        this.weeklyStudHrs = hrs;
    }
    //get methods for the week object
    public String getUserName() {
        return username;
    }
    public int getWeekNum() {
        return weekCount;
    }
    public int getWeeklyCalCons() {
        return weeklyCalConsumed;
    }
    public int getWeeklyCalBurn() {
        return weeklyCalBurned;
    }
    public int getWeeklyStudHrs() {
        return weeklyStudHrs;
    }

    //Save and retrieve data
    public void initSave() {
        myList.add(this);
        System.out.println("initsave" + myList.size());
        save(); 
        System.out.println("saving summary file");
    }  
    public static void setMyList(ArrayList<UserSummary> info) {
        myList = info;
    }
    
    static void save() {
        System.out.println("Saving User Summary"); 
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject(myList);
            outputStream.close();
            fos.close();
            System.out.println("Save is Successful");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static ArrayList<UserSummary> retrieve() {
        ArrayList<UserSummary> savedUserSum = null;
        
        try {
            File myFile = new File(fileName);
            if (myFile.exists()) {
                myFile.createNewFile();
                FileInputStream fis = new FileInputStream(fileName);
                ObjectInputStream inputStream = new ObjectInputStream(fis);
                savedUserSum = (ArrayList<UserSummary>) inputStream.readObject();
                inputStream.close();
                fis.close();
            } else {
                savedUserSum = new ArrayList<UserSummary>();
                System.out.println("No File, Return empty");
            }
            
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex);
        }
        System.out.println("Retrieving Users List");
        return savedUserSum;
    }
}
