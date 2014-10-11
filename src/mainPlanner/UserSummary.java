/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPlanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JTable;

/**
 *
 * @author Dido
 */
public class UserSummary {

    String username = "newConstructName";

    int weeklyCal = 0;
    int weekCount = 0;
    static String fileName = "PAM_User_Summary.ser";

    UserSummary(int totalCal, String userName, int weekNum) {
        this.username = userName;
        this.weeklyCal = totalCal;
        this.weekCount = weekNum;
    }

    public void printInfo() {
        System.out.println("username: " + username);
        System.out.println("week number: " + weekCount);

    }

    public void setUserName(String name) {
        this.username = name;

        String temp = name.concat(fileName);
        fileName = new String(temp);
    }

    public void setWeeklyCal(int cal) {
        this.weeklyCal = cal;
    }

    public void setWeekNum(int num) {
        this.weekCount = num;
    }

    public String getUserName() {
        return username;
    }

    public int getWeeklyCal() {
        return weeklyCal;
    }

    public int getWeekNum() {
        return weekCount;
    }

    //Save and retrieve data

    public void initSave() {
        save(this);
        System.out.println("saving summary file");
    }

    

    static void save(UserSummary info) {
        System.out.println("Serializing User Table info");

        try {
            System.out.println("file not found");
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject(info);
            outputStream.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    static UserSummary retrieve() {

        UserSummary savedUserSum = null;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream inputStream = new ObjectInputStream(fis);
            savedUserSum = (UserSummary) inputStream.readObject();
            inputStream.close();
            fis.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println(ex);
        }
        System.out.println("Deserialized User Table info");

        return savedUserSum;
    }
}
