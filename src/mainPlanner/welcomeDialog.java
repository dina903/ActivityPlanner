/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainPlanner;

/**
 *
 * @author Dido
 */
public class welcomeDialog extends javax.swing.JDialog {

    /**
     * Creates new form welcomeDialog
     */
    public welcomeDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        signInPanel.setVisible(false);
        signUpPanel.setVisible(false);
        mainFrame.setVisible(false);
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jTabbedPane6 = new javax.swing.JTabbedPane();
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
        signUpPanel = new javax.swing.JPanel();
        signUpUsername = new javax.swing.JLabel();
        SignUppasswordLbl = new javax.swing.JLabel();
        signUpbtn = new javax.swing.JButton();
        NewUserPassword = new javax.swing.JPasswordField();
        NewUsername = new javax.swing.JTextField();

        mainFrame.setMinimumSize(new java.awt.Dimension(552, 330));
        mainFrame.getContentPane().setLayout(new java.awt.GridLayout());

        jTabbedPane1.addTab("tab1", jTabbedPane5);
        jTabbedPane1.addTab("tab2", jTabbedPane6);

        mainFrame.getContentPane().add(jTabbedPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Welcome to Student PAM");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(32767, 32767));
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
                .addContainerGap(103, Short.MAX_VALUE)
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

        signInbtn.setText("Sign In");
        signInbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signInbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout signInPanelLayout = new javax.swing.GroupLayout(signInPanel);
        signInPanel.setLayout(signInPanelLayout);
        signInPanelLayout.setHorizontalGroup(
            signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signInPanelLayout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(signInPanelLayout.createSequentialGroup()
                        .addComponent(passwordLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(existUserPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(signInPanelLayout.createSequentialGroup()
                        .addComponent(usernameLbl)
                        .addGap(18, 18, 18)
                        .addComponent(existUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(109, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signInPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(signInbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(161, 161, 161))
        );
        signInPanelLayout.setVerticalGroup(
            signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(signInPanelLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(existUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameLbl))
                .addGap(19, 19, 19)
                .addGroup(signInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(existUserPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLbl))
                .addGap(42, 42, 42)
                .addComponent(signInbtn)
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
                .addComponent(signInPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(signUpPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        signInPanel.setVisible(true);
    }//GEN-LAST:event_btnExistingUserActionPerformed

    private void btnNewUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewUserActionPerformed
        // TODO add your handling code here:
        welcomePanel.setVisible(false);
        signUpPanel.setVisible(true);
       
    }//GEN-LAST:event_btnNewUserActionPerformed

    private void signInbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signInbtnActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        mainFrame.setVisible(true);
    }//GEN-LAST:event_signInbtnActionPerformed

    private void signUpbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signUpbtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_signUpbtnActionPerformed

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
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField NewUserPassword;
    private javax.swing.JTextField NewUsername;
    private javax.swing.JLabel SignUppasswordLbl;
    private javax.swing.JButton btnExistingUser;
    private javax.swing.JButton btnNewUser;
    private javax.swing.JPasswordField existUserPassword;
    private javax.swing.JTextField existUsername;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JFrame mainFrame;
    private javax.swing.JLabel passwordLbl;
    private javax.swing.JPanel signInPanel;
    private javax.swing.JButton signInbtn;
    private javax.swing.JPanel signUpPanel;
    private javax.swing.JLabel signUpUsername;
    private javax.swing.JButton signUpbtn;
    private javax.swing.JLabel usernameLbl;
    private javax.swing.JLabel welcomeLbl1;
    private javax.swing.JLabel welcomeLbl2;
    private javax.swing.JPanel welcomePanel;
    // End of variables declaration//GEN-END:variables
}
