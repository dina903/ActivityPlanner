/****@author Karoon & Dina *****/
package mainPlanner;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import static mainPlanner.welcomeDialog.studyTable;

public class welcomeDialog extends javax.swing.JDialog {
    //Data Fields
    User pamUser = new User("Test main", "Test main", 0);
    static JTable studyTable, nutritionTable, workoutTable;
    DefaultTableModel studyModel, nutritionModel, workoutModel;
    JPanel weekSummary, logSummary, southPanel;
    JLabel calWeekSum, calLogSum;
    ButtonGroup group;
    JScrollPane scrollPane;
    BufferedImage icon;
    Vector<Object> studyRow, studyColumns, dataStudy, dataNutrition, dataWorkout, nutritionCol, nutritionRow, workoutCol, workoutRow;

    public welcomeDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            this.setIconImage(ImageIO.read(new File("src\\images\\activitymonitornew.jpg")));
            mainFrame.setIconImage(ImageIO.read(new File("src\\images\\activitymonitornew.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        signInPanel.setVisible(false);
        signUpPanel.setVisible(false);
        mainFrame.setVisible(false);
        //South panel includes Submit button
        //southPanel = new JPanel();
        //mainFrame.getContentPane().add(southPanel, BorderLayout.PAGE_END);
        //JButton save = new JButton("Save");
        // southPanel.add(save, BorderLayout.LINE_END);
        // save.setForeground(Color.red);
        // save.setFont(new Font("Tahoma", Font.BOLD, 18));
        //JDateChooser is on the top of left panel
        jDateChooser1.setDate(new Date());
        jDateChooser1.setMinSelectableDate(jDateChooser1.getDate());
        System.out.println(jDateChooser1.getDate());
        study.setSelected(true);
        //Create Study table and add it to JScrollPane
        dataStudy = new Vector<Object>();
        studyColumns = new Vector<Object>();
        studyColumns.add("Class");
        studyColumns.add("Time Taken");
        studyRow = new Vector<Object>();
        dataStudy.add("");
        dataStudy.add(new Integer(0));
        studyRow.add(dataStudy);

        studyModel = new DefaultTableModel(studyRow, studyColumns);
        studyTable = new JTable(studyModel);
        System.out.println("Table columns " + studyTable.getModel().getColumnCount());
        studyTable.setShowGrid(true);
        scrollPane = new JScrollPane(studyTable);
        tabbedPane.addTab("Day View", scrollPane);
        studyTable.getParent().setBackground(Color.white);
        //Create Nutrition table
        nutritionCol = new Vector<Object>();
        nutritionCol.add("Food/Drink");
        nutritionCol.add("Calories");
        nutritionCol.add("Time");
        nutritionRow = new Vector<Object>();
        dataNutrition = new Vector<Object>();
        dataNutrition.add("");
        dataNutrition.add(new Integer(0));
        dataNutrition.add(new Time(0));
        nutritionRow.add(dataNutrition);
        nutritionModel = new DefaultTableModel(nutritionRow, nutritionCol);
        nutritionTable = new JTable(nutritionModel);
        nutritionTable.setShowGrid(true);
        //Create Workout table
        workoutCol = new Vector<Object>();
        dataWorkout = new Vector<Object>();
        workoutCol.add("Workout Type");
        workoutCol.add("Calories Burned");
        workoutCol.add("Time");
        dataWorkout.add("Treadmill");
        dataWorkout.add(new Integer(0));
        dataWorkout.add(new Time(0));
        workoutRow = new Vector<Object>();
        workoutRow.add(dataWorkout);
        workoutModel = new DefaultTableModel(workoutRow, workoutCol);
        workoutTable = new JTable(workoutModel);
        workoutTable.setShowGrid(true);
        //Instantiate a panel for each summary and add each one to a tab
        weekSummary = new JPanel();
        weekSummary.setBackground(Color.white);
        logSummary = new JPanel();
        logSummary.setBackground(Color.white);
        tabbedPane.addTab("Week View", weekSummary);
        tabbedPane.addTab("Log View", logSummary);
        tabbedPane.addChangeListener(changeListener);
        group = new ButtonGroup();
        group.add(study);
        group.add(nutrition);
        group.add(workout);

        //The following three calls are for drop down menu for Calories and machines
        setupCaloriesCol(nutritionTable.getColumnModel().getColumn(1));
        setupCaloriesCol(workoutTable.getColumnModel().getColumn(1));
        setupWorkTypeCol(workoutTable.getColumnModel().getColumn(0));

        BoxLayout weekLayout = new BoxLayout(weekSummary, BoxLayout.Y_AXIS);
        BoxLayout logLayout = new BoxLayout(logSummary, BoxLayout.Y_AXIS);
        
        //Add a label to each summary panel to insert info
        calWeekSum = new JLabel("Week Summary");
        calLogSum = new JLabel("Log Summary");
        weekSummary.add(calWeekSum);
        logSummary.add(calLogSum);
        leftPanel.setPreferredSize(new Dimension(200, leftPanel.getHeight()));
        logoLabel.setText("");

        wrongPassword.setVisible(false);
        wrongUsername.setVisible(false);
        totalConsumedCal = 0;
        totalBurnedCal = 0;
        tableType = 0;
        weekCounter = 0;
        weekStudyHours = 0;
        loggedUser = new User("Test main", "Test main", 0);
        loggedUserSum = new UserSummary("Construct Username", 0, 0, 0, 0);
        file.add(saveObj);
        saveObj.addActionListener(actionListener);
        mainFrame.addWindowListener(saveOnClose);
        calWeekSum.setFont(new Font("Tahoma", Font.PLAIN, 24));
        calLogSum.setFont(new Font("Tahoma", Font.PLAIN, 24));
        //center the dialog in the middle of the screen
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - this.getWidth()) / 2;
        final int y = (screenSize.height - this.getHeight()) / 2;
        this.setLocation(x, y);
        allUsers = UserSummary.retrieve();
        validate();
    }
    
    WindowAdapter saveOnClose = new WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            saveInformation();
            System.exit(0);
        }
        
    };
    
    //save item in the file menue
    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
            loggedUserSum.setUserName(loggedUser.getUserName());
            //saveInformation();
            saveTable();
        }
    };
    private JFileChooser myJFileChooser = new JFileChooser(new File(".txt"));

    public void saveTable() {
        if (myJFileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            saveTable(myJFileChooser.getSelectedFile());
            myJFileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
            myJFileChooser.setFileFilter(filter);
            //saveInformation();
        }
    }
    
    private void saveTable(File file) {
        
        try {
            PrintWriter os = new PrintWriter(file);
            os.println();
            ArrayList<UserSummary> myUserList = UserSummary.retrieve();
            for (int j = 0; j < myUserList.size(); j++) {
                os.println(saveMsg);
            }
            os.close();
            System.out.println("Done!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void saveInformation() {
        calcSummary();
        UserSummary.setMyList(allUsers);
        loggedUserSum.initSave();
        resetInformation();
    }
    
    public void resetInformation() {
        loggedUserSum.setWeeklyCalCons(0);
        loggedUserSum.setWeeklyCalBurn(0);
        loggedUserSum.setWeeklyStudHrs(0);
    }
    //notify when tab change - save an instance of the current values in table
    ChangeListener changeListener = new ChangeListener() {
        public void stateChanged(ChangeEvent changeEvent) {
            JTabbedPane sourceTabbedPane = (JTabbedPane) changeEvent.getSource();
            int index = sourceTabbedPane.getSelectedIndex();
            String tabName = sourceTabbedPane.getTitleAt(index);
            System.out.println("Tab changed to: " + sourceTabbedPane.getTitleAt(index));
            //check what tab was selected
            if (tabName.equals("Week View")) {
                calcSummary();
               
                study.setVisible(false);
                nutrition.setVisible(false);
                workout.setVisible(false);
                int weekHoursStudy = 0;
                int weekCalorCons = 0;
                int weekCalorBurn = 0;
                Calendar thisWeekCal = jDateChooser1.getCalendar();
                int thisWeek = thisWeekCal.get(Calendar.WEEK_OF_YEAR);
                
                System.out.println("All Users Retrieved " + allUsers.size());
                int calcWeek = 0;
                if (thisWeek == loggedUser.getWeekOfYear()) {
                    calcWeek = 1;
                } else {
                    calcWeek = (thisWeek - loggedUser.getWeekOfYear())+1;
                }
                
                for (int i = 0; i < allUsers.size(); i++) {
                    //get the information only for a week similar to the currentWeek
                    if (allUsers.get(i).getWeekNum() == calcWeek) {//find all instances of the same week
                        weekHoursStudy += allUsers.get(i).getWeeklyStudHrs();
                        weekCalorCons += allUsers.get(i).getWeeklyCalCons();
                        weekCalorBurn += allUsers.get(i).getWeeklyCalBurn();
                    } else {
                        System.out.println("This Entry from a previous week");
                    }                  
                }
                String weekMsg = "<html><center>User " + loggedUser.getUserName();
                weekMsg += "<br><br>Total Hours Spent Studying " + (weekHoursStudy + loggedUserSum.getWeeklyStudHrs()) + " Hours";
                weekMsg += "<br><br>Total Calories You Have Burned by WorkingOut " + (weekCalorBurn + loggedUserSum.getWeeklyCalBurn() ) + " Calories";
                weekMsg += "<br><br>Total Calories You Ate " + (weekCalorCons+ loggedUserSum.getWeeklyCalCons()) + " Calories</center></html>";
                saveMsg += weekMsg;
                calWeekSum.setText(weekMsg);
                
            } else if (tabName.equals("Log View")) {
                calcSummary();
                study.setVisible(false);
                nutrition.setVisible(false);
                workout.setVisible(false);
                int totalHoursStudy = 0;
                int totalCalorCons = 0;
                int totalCalorBurn = 0;
       
                System.out.println("All Users Retrieved " + allUsers.size());
                for (int i = 0; i < allUsers.size(); i++) {
                    totalHoursStudy += allUsers.get(i).getWeeklyStudHrs();
                    totalCalorBurn += allUsers.get(i).getWeeklyCalBurn();
                    totalCalorCons += allUsers.get(i).getWeeklyCalCons();
                }
                                
                String logMsg = "<html><center>User " + loggedUser.getUserName();
                logMsg += "<br><br>Total Hours Spent Studying " + (totalHoursStudy+loggedUserSum.getWeeklyStudHrs()) + " Hours";
                logMsg += "<br><br>Total Calories You Have Burned by WorkingOut " + (totalCalorBurn+loggedUserSum.getWeeklyCalBurn()) + " Calories";
                logMsg += "<br><br>Total Calories You Ate " + (totalCalorCons+loggedUserSum.getWeeklyCalCons()) + " Calories</center></html>";
                saveMsg += logMsg; 
                calLogSum.setText(logMsg);
            } else if(tabName.equals("Day View")) {
                study.setVisible(true);
                nutrition.setVisible(true);
                workout.setVisible(true);
            }
        }
    };
    
    //find the week number for the current week
    public void findWeek() {
        System.out.println("Updating week counter");
        Calendar currentWeek = jDateChooser1.getCalendar();
        int newWeek = currentWeek.get(Calendar.WEEK_OF_YEAR);
        System.out.println("Current week of year is : " + newWeek);
        if (newWeek == loggedUser.getWeekOfYear()) {
            System.out.println("In the same week");
            weekCounter = 1; 
            loggedUserSum.setWeekNum(weekCounter);
        } else if (newWeek > loggedUser.getWeekOfYear()) { 
            weekCounter = (newWeek - loggedUser.getWeekOfYear())+1; 
            loggedUserSum.setWeekNum(weekCounter);
        } else {
            //deal with it later (check for year)
            System.out.println("currentweek is less than the logged week");
        }
    }
    //calculate the summary of the three tables
    public void calcSummary() { //here add calories and set the object
        //study update
        studyTable.getModel();
        int rows = studyTable.getRowCount();
        weekStudyHours = 0;
        for (int i = 0; i < rows; i++) {
            if (studyTable.getValueAt(i, 1) != null) {
                weekStudyHours += (Integer.parseInt(studyTable.getValueAt(i, 1).toString()));
            }
        }
        loggedUserSum.setWeeklyStudHrs(weekStudyHours);
        //nutrition update
        nutritionTable.getModel();
        int nutrientRow = nutritionTable.getRowCount();
        totalConsumedCal = 0;
        for (int i = 0; i < nutrientRow; i++) {
            if (nutritionTable.getValueAt(i, 1) != null) {
                totalConsumedCal += (Integer.parseInt(nutritionTable.getValueAt(i, 1).toString()));
            }
        }
        loggedUserSum.setWeeklyCalCons(totalConsumedCal);
        //workout update
        workoutTable.getModel();
        int workoutRow = workoutTable.getRowCount();
        totalBurnedCal = 0;
        for (int i = 0; i < workoutRow; i++) {
            if (workoutTable.getValueAt(i, 1) != null) {
                totalBurnedCal += (Integer.parseInt(workoutTable.getValueAt(i, 1).toString()));
            }
        }
        loggedUserSum.setWeeklyCalBurn(totalBurnedCal);     
    }
    //Setup Calories combo list
    public void setupCaloriesCol(TableColumn col) {
        Vector<Integer> cals = new Vector<Integer>();
        for (int i = 100; i <= 4000; i += 50) {
            cals.addElement(new Integer(i));
        }
        JComboBox<Integer> combo = new JComboBox<Integer>(cals);
        combo.setSelectedItem(new Integer(100));
        col.setCellEditor(new DefaultCellEditor(combo));
        col.setCellRenderer(new CaloriesRender());
    }

    //Setup Workout type combo list
    public void setupWorkTypeCol(TableColumn col) {
        Vector<String> cals = new Vector<String>();
        cals.addElement("Treadmill");
        cals.addElement("Elliptical");
        cals.addElement("XLS");
        cals.addElement("Exercise Bike");
        cals.addElement("Rower");
        cals.addElement("Zumba class");
        cals.addElement("Body Pump");
        cals.addElement("Kick Box");
        JComboBox<String> combo = new JComboBox<String>(cals);
        combo.setSelectedIndex(0);
        col.setCellEditor(new DefaultCellEditor(combo));
        col.setCellRenderer(new CaloriesRender());
    }
    
    public static void addBackbtn() {
       back.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainFrame = new javax.swing.JFrame();
        leftPanel = new javax.swing.JPanel();
        activities = new java.awt.Label();
        study = new javax.swing.JRadioButton();
        nutrition = new javax.swing.JRadioButton();
        workout = new javax.swing.JRadioButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        logoLabel = new javax.swing.JLabel();
        rightPanel = new javax.swing.JPanel();
        addRow = new javax.swing.JButton();
        deleteRow = new javax.swing.JButton();
        tabbedPane = new javax.swing.JTabbedPane();
        menuBar = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        edit = new javax.swing.JMenu();
        usage = new javax.swing.JMenu();
        author = new javax.swing.JMenu();
        welcomePanel = new javax.swing.JPanel();
        welcomeLbl1 = new javax.swing.JLabel();
        welcomeLbl2 = new javax.swing.JLabel();
        btnNewUser = new javax.swing.JButton();
        btnExistingUser = new javax.swing.JButton();
        signInPanel = new javax.swing.JPanel();
        usernameLbl = new javax.swing.JLabel();
        passwordLbl = new javax.swing.JLabel();
        existUserPassword = new javax.swing.JPasswordField();
        existUsername = new javax.swing.JTextField();
        signInbtn = new javax.swing.JButton();
        wrongUsername = new javax.swing.JLabel();
        wrongPassword = new javax.swing.JLabel();
        back = new javax.swing.JButton();
        signUpPanel = new javax.swing.JPanel();
        signUpUsername = new javax.swing.JLabel();
        SignUppasswordLbl = new javax.swing.JLabel();
        signUpbtn = new javax.swing.JButton();
        NewUserPassword = new javax.swing.JPasswordField();
        NewUsername = new javax.swing.JTextField();

        mainFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setTitle("Personal Activity Monitor");
        mainFrame.setMinimumSize(new java.awt.Dimension(1152, 800));

        leftPanel.setName(""); // NOI18N

        activities.setAlignment(java.awt.Label.CENTER);
        activities.setBackground(java.awt.SystemColor.activeCaption);
        activities.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        activities.setText("Activities");

        study.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        study.setForeground(new java.awt.Color(255, 0, 51));
        study.setText("Study");
        study.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studyActionPerformed(evt);
            }
        });

        nutrition.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nutrition.setForeground(new java.awt.Color(255, 0, 51));
        nutrition.setText("Nutrition");
        nutrition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nutritionActionPerformed(evt);
            }
        });

        workout.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        workout.setForeground(new java.awt.Color(255, 0, 51));
        workout.setText("Workout");
        workout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                workoutActionPerformed(evt);
            }
        });

        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/activitymonitornew.jpg"))); // NOI18N
        logoLabel.setText("jLabel1");
        logoLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        logoLabel.setIconTextGap(0);
        logoLabel.setMaximumSize(new java.awt.Dimension(200, 250));
        logoLabel.setMinimumSize(new java.awt.Dimension(200, 250));
        logoLabel.setPreferredSize(new java.awt.Dimension(200, 250));
        logoLabel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(activities, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftPanelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(workout)
                            .addComponent(nutrition)
                            .addComponent(study)))
                    .addGroup(leftPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(activities, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(study)
                .addGap(18, 18, 18)
                .addComponent(nutrition)
                .addGap(18, 18, 18)
                .addComponent(workout)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        mainFrame.getContentPane().add(leftPanel, java.awt.BorderLayout.LINE_START);

        addRow.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addRow.setForeground(new java.awt.Color(255, 0, 51));
        addRow.setText("Add Row");
        addRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRowActionPerformed(evt);
            }
        });

        deleteRow.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        deleteRow.setForeground(new java.awt.Color(255, 0, 51));
        deleteRow.setText("Delete Row");
        deleteRow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(deleteRow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addRow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 16, Short.MAX_VALUE))
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addRow)
                .addGap(18, 18, 18)
                .addComponent(deleteRow)
                .addContainerGap(407, Short.MAX_VALUE))
        );

        mainFrame.getContentPane().add(rightPanel, java.awt.BorderLayout.LINE_END);

        tabbedPane.setBackground(new java.awt.Color(255, 255, 255));
        tabbedPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        tabbedPane.setMaximumSize(new java.awt.Dimension(50, 32767));
        mainFrame.getContentPane().add(tabbedPane, java.awt.BorderLayout.CENTER);

        file.setText("File");
        menuBar.add(file);

        edit.setText("Edit");
        menuBar.add(edit);

        usage.setText("Usage");
        usage.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                usageMenuSelected(evt);
            }
        });
        usage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usageActionPerformed(evt);
            }
        });
        menuBar.add(usage);

        author.setText("Author");
        author.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                authorMenuSelected(evt);
            }
        });
        author.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                authorActionPerformed(evt);
            }
        });
        menuBar.add(author);

        mainFrame.setJMenuBar(menuBar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Welcome to Student PAM");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setName("welcomeDialog"); // NOI18N

        welcomeLbl1.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        welcomeLbl1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeLbl1.setText("Welcome to PAM");

        welcomeLbl2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        welcomeLbl2.setText("Student Private Activity Monitor");

        btnNewUser.setText("New User");
        btnNewUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNewUser.setName(""); // NOI18N
        btnNewUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewUserActionPerformed(evt);
            }
        });

        btnExistingUser.setText("Existing User");
        btnExistingUser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExistingUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExistingUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout welcomePanelLayout = new javax.swing.GroupLayout(welcomePanel);
        welcomePanel.setLayout(welcomePanelLayout);
        welcomePanelLayout.setHorizontalGroup(
            welcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(welcomePanelLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(btnExistingUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNewUser, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, welcomePanelLayout.createSequentialGroup()
                .addContainerGap(105, Short.MAX_VALUE)
                .addGroup(welcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, welcomePanelLayout.createSequentialGroup()
                        .addComponent(welcomeLbl2)
                        .addGap(70, 70, 70))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, welcomePanelLayout.createSequentialGroup()
                        .addComponent(welcomeLbl1)
                        .addGap(128, 128, 128))))
        );
        welcomePanelLayout.setVerticalGroup(
            welcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(welcomePanelLayout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addComponent(welcomeLbl1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(welcomeLbl2)
                .addGap(31, 31, 31)
                .addGroup(welcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExistingUser)
                    .addComponent(btnNewUser))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        signInPanel.setPreferredSize(new java.awt.Dimension(417, 252));

        usernameLbl.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        usernameLbl.setText("Username:");

        passwordLbl.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        passwordLbl.setText("Password:");

        existUserPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                existUserPasswordActionPerformed(evt);
            }
        });

        existUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                existUsernameActionPerformed(evt);
            }
        });

        signInbtn.setText("Sign In");
        signInbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signInbtnActionPerformed(evt);
            }
        });

        wrongUsername.setForeground(new java.awt.Color(255, 0, 0));
        wrongUsername.setText("Wrong Username");

        wrongPassword.setForeground(new java.awt.Color(255, 0, 0));
        wrongPassword.setText("Wrong Password");

        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout signInPanelLayout = new javax.swing.GroupLayout(signInPanel);
        signInPanel.setLayout(signInPanelLayout);
        signInPanelLayout.setHorizontalGroup(
            signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signInPanelLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(signInPanelLayout.createSequentialGroup()
                            .addComponent(passwordLbl)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(existUserPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(signInPanelLayout.createSequentialGroup()
                            .addComponent(usernameLbl)
                            .addGap(18, 18, 18)
                            .addComponent(existUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(signInPanelLayout.createSequentialGroup()
                        .addComponent(back)
                        .addGap(42, 42, 42)
                        .addComponent(signInbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)))
                .addGap(18, 18, 18)
                .addGroup(signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(wrongUsername)
                    .addComponent(wrongPassword))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        signInPanelLayout.setVerticalGroup(
            signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signInPanelLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(existUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameLbl)
                    .addComponent(wrongUsername))
                .addGap(19, 19, 19)
                .addGroup(signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(existUserPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLbl)
                    .addComponent(wrongPassword))
                .addGap(42, 42, 42)
                .addGroup(signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(signInbtn)
                    .addComponent(back))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        signUpPanel.setPreferredSize(new java.awt.Dimension(417, 252));

        signUpUsername.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        signUpUsername.setText("Username:");

        SignUppasswordLbl.setFont(new java.awt.Font("Calibri", 0, 12)); // NOI18N
        SignUppasswordLbl.setText("Password:");

        signUpbtn.setText("Sign Up");
        signUpbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signUpbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout signUpPanelLayout = new javax.swing.GroupLayout(signUpPanel);
        signUpPanel.setLayout(signUpPanelLayout);
        signUpPanelLayout.setHorizontalGroup(
            signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signUpPanelLayout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(signUpUsername)
                    .addComponent(SignUppasswordLbl))
                .addGap(18, 18, 18)
                .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(NewUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                    .addComponent(NewUserPassword))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signUpPanelLayout.createSequentialGroup()
                .addContainerGap(190, Short.MAX_VALUE)
                .addComponent(signUpbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(152, 152, 152))
        );
        signUpPanelLayout.setVerticalGroup(
            signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signUpPanelLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(signUpUsername)
                    .addComponent(NewUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(signUpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SignUppasswordLbl)
                    .addComponent(NewUserPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(signUpbtn)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(welcomePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(signInPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(signUpPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(welcomePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(signInPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(signUpPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExistingUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExistingUserActionPerformed
        // TODO add your handling code here:
        welcomePanel.setVisible(false);
        back.setVisible(false);
        signInPanel.setVisible(true);
    }//GEN-LAST:event_btnExistingUserActionPerformed

    private void btnNewUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewUserActionPerformed
        // TODO add your handling code here:
        welcomePanel.setVisible(false);
        signUpPanel.setVisible(true);
    }//GEN-LAST:event_btnNewUserActionPerformed

    private void signInbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signInbtnActionPerformed
        User retrieveUser = pamUser.deserialize();
        String tempUsername = retrieveUser.getUserName();
        String tempUserpswd = retrieveUser.getUserPassword();
        String tempActualpswd = new String(existUserPassword.getPassword());
        if ((tempUsername.equals(existUsername.getText())) && (tempUserpswd.equals(tempActualpswd))) {
            System.out.println("username and password matched");
            loggedUser = retrieveUser;
            findWeek(); //set the week for the new object
            //display the pam main screen
            setVisible(false);
            mainFrame.setVisible(true);
        } else {
            System.out.println("username and password didn't match, Retry!");
            //clean the fields and let the user retry
            existUsername.setText("");
            existUserPassword.setText("");
            if (!tempUsername.equals(existUsername.getText())) {
                wrongUsername.setVisible(true);
            }
            if (!tempUserpswd.equals(existUserPassword.getPassword())) {
                wrongPassword.setVisible(true);
            }
        }
    }//GEN-LAST:event_signInbtnActionPerformed

    private void signUpbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpbtnActionPerformed
        String initDate = jDateChooser1.getDate().toString();
        System.out.println("Initial Date: " + initDate);
        Calendar now = Calendar.getInstance();
        System.out.println("Current week of year is : " + now.get(Calendar.WEEK_OF_YEAR));
        int weekNo = now.get(Calendar.WEEK_OF_YEAR);
        weekNo = 37; //keep for testing only - has to be removed for other users
        User pamUser = new User("Test main", "Test main", weekNo);
        String username = NewUsername.getText().toString(); //get username textfield data
        //String userpassword = NewUserPassword.getPassword().toString(); //get password field data
        String userpassword = new String(NewUserPassword.getPassword());
        pamUser.setUserName(username); // set user username
        pamUser.setUserPassword(userpassword); //set user password
        pamUser.setWeekOfYear(weekNo);
        pamUser.initSave(); //save file
        pamUser.printInfo(); //print user information
        loggedUser = pamUser;
        findWeek();//set the weekly counter for the week object
        setVisible(false);
        mainFrame.setVisible(true);
    }//GEN-LAST:event_signUpbtnActionPerformed

    private void deleteRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteRowActionPerformed
        // TODO add your handling code here:
        if (study.isSelected()) {
            int row = studyTable.getSelectedRow();
            if (row != -1) {
                studyModel.removeRow(row);
            }
        } else if (nutrition.isSelected()) {
            int row = nutritionTable.getSelectedRow();
            if (row != -1) {
                nutritionModel.removeRow(row);
            }
        } else {
            int row = workoutTable.getSelectedRow();
            if (row != -1) {
                workoutModel.removeRow(row);
            }
        }
    }//GEN-LAST:event_deleteRowActionPerformed

    private void addRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRowActionPerformed
        // TODO add your handling code here:
        Vector<Object> array = new Vector<Object>();
        if (study.isSelected()) {
            for (int i = 1; i < 3; i++) {
                array.add(null);
            }
            studyModel.addRow(array);
        } else if (nutrition.isSelected() || workout.isSelected()) {
            for (int i = 1; i < 4; i++) {
                array.add(null);
            }
            if (nutrition.isSelected()) {
                nutritionModel.addRow(array);
            } else {
                workoutModel.addRow(array);
            }
        }
    }//GEN-LAST:event_addRowActionPerformed

    private void authorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_authorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_authorActionPerformed

    private void usageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usageActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_usageActionPerformed

    private void usageMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_usageMenuSelected
        // TODO add your handling code here:
        String x = "Personal Activity Monitor is a software that manages person's daily activities. It manages three categories:\n"
                + "1- How much time a person spends studying\n"
                + "2- How often a person works out and how much calories are burned\n"
                + "3- How much a person controls daily nutritional regime.\n"
                + "How to use PAM?\n"
                + "The user must have a personal account to access the personal information and display summary of past activities.\n"
                + "First: Sign up an account if you are a new use or sign in with your existing account credentials\n"
                + "Second: A Day View of Study activity is the default display. \n"
                + "What will you see for each click?\n"
                + "There are three tables that represent the three activities for one day.\n"
                + "1- Study table has three columns:\n"
                + "	a. Class: The user can enter the class name\n"
                + "	b. Time Taken: It displays how many hours the user spent studying. The user can edit the number.\n"
                + "2- Nutrition table has four columns:\n"
                + "	a. Food/Drink: The user can enter the type of food or drink\n"
                + "	b. Calories: How much calories is in the serving of a particular food or drink. It's displayed as a dropdown menu\n"
                + "	c. Time: At what time the user had eaten or drunk\n"
                + "3- Workout table has four columns:\n"
                + "	a. Workout type: It displays a list of various types of cardio machines and classes as a dropdown menu\n"
                + "	b. Calories: How much calories were burned after the exercise. It's displayed as a dropdown menu\n"
                + "	c. Time: At what time the user had worked out\n"
                + "How to view and edit the previous tables?\n"
                + "	1- Click on \"Study\" button to display Study table. Click on the cells to enter the required information\n"
                + "	2- Click on \"Nutrition\" button to display Nutrition table. Click on the cells to enter the required information\n"
                + "	3- Click on \" Workout \" button to display Workout table. Click on the cells to enter the required information\n"
                + "	You can add or delete a table entry by selecting a row and pressing \"Add Row\" or \"Delete Row\".";
        JOptionPane.showMessageDialog(mainFrame, x, "How to use PAM", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_usageMenuSelected

    private void authorMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_authorMenuSelected
        // TODO add your handling code here:
        JDialog authorDialog = new JDialog(mainFrame, "Authors");
        BoxLayout layoutDialog = new BoxLayout(authorDialog.getContentPane(), BoxLayout.Y_AXIS);
        FlowLayout panelLayout = new FlowLayout(FlowLayout.CENTER, 20, 5);
        FlowLayout namesLayout = new FlowLayout(FlowLayout.LEFT, 50, 5);
        authorDialog.setLayout(layoutDialog);
        JPanel photos = new JPanel();
        JPanel names = new JPanel();
        JPanel emails = new JPanel();
        JLabel karoon = new JLabel("Karoon Gayzagian");
        JLabel dina = new JLabel("Dina Najeeb");
        JLabel karoonPic = new JLabel(new ImageIcon("..\\karoon.jpg"));

        JLabel dinaPic = new JLabel(new ImageIcon("..\\dina.jpg"));
        JLabel karoonEmail = new JLabel("karoon80@hotmail.com");
        JLabel dinaEmail = new JLabel("dina_2552@yahoo.com");
        photos.setLayout(panelLayout);
        names.setLayout(namesLayout);
        emails.setLayout(panelLayout);
        photos.setMaximumSize(new Dimension(500, 250));
        names.setMaximumSize(new Dimension(500, 250));

        karoon.setFont(new Font("Tahoma", Font.BOLD, 18));
        dina.setFont(new Font("Tahoma", Font.BOLD, 18));
        dinaEmail.setFont(new Font("Tahoma", Font.BOLD, 18));
        karoonEmail.setFont(new Font("Tahoma", Font.BOLD, 18));
        validate();
        names.add(karoon);
        names.add(dina);
        photos.add(karoonPic);
        photos.add(dinaPic);
        emails.add(karoonEmail);
        emails.add(dinaEmail);

        authorDialog.add(photos);
        authorDialog.add(names);
        authorDialog.add(emails);
        authorDialog.setMinimumSize(new Dimension(500, 500));
        authorDialog.setMaximumSize(new Dimension(500, 500));
        authorDialog.setPreferredSize(new Dimension(500, 500));
        authorDialog.setVisible(true);
    }//GEN-LAST:event_authorMenuSelected

    private void workoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_workoutActionPerformed
        // TODO add your handling code here:
        calcSummary();
        System.out.println("Workout is selected: ");
        scrollPane.setViewportView(workoutTable);
    }//GEN-LAST:event_workoutActionPerformed

    private void nutritionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nutritionActionPerformed
        // TODO add your handling code here:
        calcSummary();
        System.out.println("Nutrition is selected: ");
        scrollPane.setViewportView(nutritionTable);
    }//GEN-LAST:event_nutritionActionPerformed

    private void existUserPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_existUserPasswordActionPerformed
        // TODO add your handling code here:
        wrongPassword.setVisible(false);
        wrongUsername.setVisible(false);
    }//GEN-LAST:event_existUserPasswordActionPerformed

    private void existUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_existUsernameActionPerformed
        // TODO add your handling code here:

        System.out.print("action on txtfield ");

        if (existUsername.isFocusOwner()) {
            wrongPassword.setVisible(false);
            wrongUsername.setVisible(false);
        }
    }//GEN-LAST:event_existUsernameActionPerformed

    private void studyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studyActionPerformed
        // TODO add your handling code here:
        calcSummary();
        System.out.println("Study is selected: ");
        scrollPane.setViewportView(studyTable);

    }//GEN-LAST:event_studyActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        back.setVisible(false);
        signInPanel.setVisible(false);
        signUpPanel.setVisible(true);
    }//GEN-LAST:event_backActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(welcomeDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(welcomeDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(welcomeDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(welcomeDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                welcomeDialog dialog = new welcomeDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
                studyTable.getModel().addTableModelListener(new modelListener(studyTable));
                nutritionTable.getModel().addTableModelListener(new modelListener(nutritionTable));
                workoutTable.getModel().addTableModelListener(new modelListener(workoutTable));
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField NewUserPassword;
    private javax.swing.JTextField NewUsername;
    private javax.swing.JLabel SignUppasswordLbl;
    private java.awt.Label activities;
    private javax.swing.JButton addRow;
    private javax.swing.JMenu author;
    private static javax.swing.JButton back;
    private javax.swing.JButton btnExistingUser;
    private javax.swing.JButton btnNewUser;
    private javax.swing.JButton deleteRow;
    private javax.swing.JMenu edit;
    private javax.swing.JPasswordField existUserPassword;
    private javax.swing.JTextField existUsername;
    private javax.swing.JMenu file;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JFrame mainFrame;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JRadioButton nutrition;
    private javax.swing.JLabel passwordLbl;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JPanel signInPanel;
    private javax.swing.JButton signInbtn;
    private javax.swing.JPanel signUpPanel;
    private javax.swing.JLabel signUpUsername;
    private javax.swing.JButton signUpbtn;
    private javax.swing.JRadioButton study;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JMenu usage;
    private javax.swing.JLabel usernameLbl;
    private javax.swing.JLabel welcomeLbl1;
    private javax.swing.JLabel welcomeLbl2;
    private javax.swing.JPanel welcomePanel;
    private javax.swing.JRadioButton workout;
    private javax.swing.JLabel wrongPassword;
    private javax.swing.JLabel wrongUsername;
    // End of variables declaration//GEN-END:variables
    JMenuItem saveObj = new JMenuItem("Save as");
    static int weekCounter, weekStudyHours, totalConsumedCal, totalBurnedCal;
    static int tableType;
    User loggedUser;
    UserSummary loggedUserSum;
    ArrayList<UserSummary> allUsers;
    String saveMsg = "PAM User Activity\n";
}



//Date Renderer
class DateRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable t,
            Object value, boolean isSelected, boolean hasFocus,
            int row, int col) {
        SimpleDateFormat df = new SimpleDateFormat("EEE, MMM d, yyyy");
        setValue(df.format((Date) value));
        return this;
    }
}
//Calories Renderer

class CaloriesRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable t,
            Object value, boolean isSelected, boolean hasFocus,
            int row, int col) {
        int calories;
        setValue(value);
        // calories = ((Integer) value).intValue();
        return this;
    }
}

class modelListener implements TableModelListener { //here listen for changes in the tables

    JTable table;

    modelListener(JTable table) {
        this.table = table;
    }

    @Override
    public void tableChanged(TableModelEvent e) {
        int colChanged = e.getColumn();
        int firstRowChanged = e.getFirstRow();
        int lastRowChanged = e.getLastRow();
        int netRowChange = lastRowChanged - firstRowChanged + 1;

        switch (e.getType()) {
            case TableModelEvent.INSERT:
                System.out.println("insert");
                System.out.println("First Row " + firstRowChanged);
                System.out.println("last Row " + lastRowChanged);
                System.out.println("last Col " + colChanged);

                /*  if(colChanged == 0){
                
                 }*/
                break;
            case TableModelEvent.UPDATE:
                System.out.println("update");
                for (int i = firstRowChanged; i <= lastRowChanged; i++) {
                    System.out.println("cell Changed: " + table.getValueAt(i, colChanged));
                }
                break;
            case TableModelEvent.DELETE:
                System.out.println("delete");
                break;
        }
    }
}
/*class SummaryObj{
 protected String month="";
 protected String day="";
 protected int netCal=0;
    
 public SummaryObj(String date, int netCal){
        
 }
    
 }*/
