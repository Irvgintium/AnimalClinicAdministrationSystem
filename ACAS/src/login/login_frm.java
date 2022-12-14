/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import com.toedter.calendar.JCalendar;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;
import de.javasoft.plaf.synthetica.SyntheticaClassyLookAndFeel;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

/**
 *
 * @author Irv Guint
 */
public class login_frm extends javax.swing.JFrame {
    
    Connection conn_db = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    int num = 0;
    
    int timerun = 0;
    
    private Timer t;
    private int count = 120;
  
    String uid;
    
    
    String day;
    String month;
    String year;
    
    int hourset;
    int minuteset;
    String am_pmset;
    
    String thisdate;
    
    

    /**
     * Creates new form login_frm
     */
    public login_frm() {
        conn_db = connection_db.ConnectDB();
        initComponents();

        typeset.setVisible(false);         
        
        setlogid.setVisible(false);
        
        //for centered frame////
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);   
        //
        
        
        ////set date////
        JCalendar dateset = new JCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
        String dob = dateFormat.format(dateset.getDate());
        thisdate = dob;
        ////
        
        ////set time////
        new Thread(){
            public void run(){
                while(timerun == 0){
                    Calendar cal = new GregorianCalendar();
                    
                    int hour = cal.get(Calendar.HOUR);
                    if(hour == 0){
                        hour = 12;
                    }else{
                        
                    }
                    int minute = cal.get(Calendar.MINUTE);
                    int sec = cal.get(Calendar.SECOND);
                    int ampm = cal.get(Calendar.AM_PM);
                    String day_night = "";
                    if(ampm == 1){
                        day_night = "PM";
                    }else{
                        day_night = "AM";
                    }
                    
		    if(minute <= 9){
			
		    String clock = hour + ":" + "0" + minute + " " + day_night;
                    
                    hourset = hour; minuteset = minute; am_pmset = day_night;
			
			}
		         else{
					String clock = hour + ":" + minute + " " + day_night;
                                        
                                        hourset = hour; minuteset = minute; am_pmset = day_night;
			     }
                     
                }
            }
        }.start();
        ////
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        lbl_timer = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        login_btn = new javax.swing.JButton();
        typeset = new javax.swing.JLabel();
        setlogid = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Animal Clinic Administration System - Login");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_timer.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        lbl_timer.setForeground(new java.awt.Color(254, 254, 254));
        lbl_timer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lbl_timer, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 350, 19));

        jPanel1.setBackground(new java.awt.Color(254, 254, 254));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, -1));

        jLabel3.setForeground(new java.awt.Color(254, 254, 254));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LOGOSsmallblackBg.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(254, 254, 254));
        jLabel2.setText("Username");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 20, -1, -1));

        username.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                usernameKeyReleased(evt);
            }
        });
        getContentPane().add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 245, -1));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 254, 254));
        jLabel1.setText("Password");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, -1, -1));

        password.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });
        getContentPane().add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 245, -1));

        login_btn.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        login_btn.setText("Login");
        login_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        login_btn.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_interface/move.png"))); // NOI18N
        login_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                login_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                login_btnMouseExited(evt);
            }
        });
        login_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login_btnActionPerformed(evt);
            }
        });
        getContentPane().add(login_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 120, 103, 30));

        typeset.setText("jLabel8");
        getContentPane().add(typeset, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        setlogid.setText("jLabel8");
        getContentPane().add(setlogid, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg_fixed.png"))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -10, 730, 190));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void login_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login_btnActionPerformed
        int records = 0;
        
        if((username.getText().equals(""))&&(password.getText().equals(""))){
   
            JOptionPane.showMessageDialog(null, "Empty Field(s) detected. Please fill up.");
   
        }else{
            
             try{
    String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
    ps=conn_db.prepareStatement(sql);
    ps.setString(1, username.getText());
    ps.setString(2, password.getText());

    rs=ps.executeQuery();
    
    while(rs.next()){
        records = rs.getInt(1);
    }
    
    
    ////set type////
    
    
    try{
        
        String sql2 = "Select type from user where username = '"+username.getText()+"'and password ='"+password.getText()+"'";
                ps = conn_db.prepareStatement(sql2);
                rs = ps.executeQuery();
        
        while(rs.next()){
            String gettype = rs.getString("type");
            typeset.setText(gettype);
        }
        
    }catch(Exception e){
                 JOptionPane.showMessageDialog(null, e);
             }
    
    
    ////set type////
    
    
    if(records > 0){
        
        ////get user id/////
    
    try{
        
        String sql2 = "Select user_id from user where username = '"+username.getText()+"'and password ='"+password.getText()+"'";
                ps = conn_db.prepareStatement(sql2);
                rs = ps.executeQuery();
        
        while(rs.next()){
            String getid = rs.getString("user_id");
            uid = getid;
            setlogid.setText(getid);
        }
        
    }catch(Exception e){
                 JOptionPane.showMessageDialog(null, e);
             }
    
    ////get user id////previously login_id*****
    
    
    ////audit trail////change to user logs
    
    ////ssplit date and time/////
    
    String string = thisdate;
    String[] parts = string.split(" ");
    String p1 = parts[0]; // 004
    String p2 = parts[1]; // 034556
    String p3 = parts[2]; // 034556
    
    day = p1;
    month = p2;
    year = p3;
    
    ////////////////////////////
//    String action = "Login";
//    String nameframe = "Login Frame";
    try{
        
        String sql2 = "insert into user_logs (`login_name`,`user_id`,`hour`,`minute`,`am_pm`,`day`,`month`,`year`) values"
                + "(?,?,?,?,UPPER(?),UPPER(?),UPPER(?),UPPER(?))";
                ps = conn_db.prepareStatement(sql2);
                ps.setString(1, "Login");
                ps.setString(2, uid);
                ps.setInt(3, hourset);
                ps.setInt(4, minuteset);
                ps.setString(5, am_pmset);
                ps.setString(6, day);
                ps.setString(7, month);
                ps.setString(8, year);
                ps.execute();
                
                //JOptionPane.showMessageDialog(null, "Succesfully Saved to user_logs");
        
    }catch(Exception e){
                 JOptionPane.showMessageDialog(null, e);
             }
    
    ////user_logs////

	this.dispose();
        new loading_frm().setVisible(true);

    }
    else{       
                num++;
                JOptionPane.showMessageDialog(null, "Wrong inputs in try #"+ num );
                if(num == 3){
                    
                    
                    JOptionPane.showMessageDialog(null, "Failed to login in "+ num + " tries");
                    
                    username.setEnabled(false);
                    password.setEnabled(false);
                    login_btn.setEnabled(false);
                    ///timer start
                    t = new Timer(1000, new ActionListener() {
                        
                        @Override
                        public void actionPerformed(ActionEvent e) {
                           count--;
                           lbl_timer.setText("The system will restart in " + count + " seconds");
                           
                           if(lbl_timer.getText().equals("The system will restart in 1 seconds")){
                               
                               t.stop();
                               
                               JOptionPane.showMessageDialog(null, "System will now restart. Please hit OK");
                               dispose();
                               new login_frm().setVisible(true);
                               
                           }
                        }
                    });
                    t.start();
                    
                    //this.dispose();
                    
                    
                }
            }

}catch(SQLException | HeadlessException e){

    JOptionPane.showMessageDialog(null, e.getMessage());

}
        }
    }//GEN-LAST:event_login_btnActionPerformed

    private void usernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameKeyReleased
        
    }//GEN-LAST:event_usernameKeyReleased

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        int records = 0;
        
        if((username.getText().equals(""))&&(password.getText().equals(""))){
   
            JOptionPane.showMessageDialog(null, "Empty Field(s) detected. Please fill up.");
   
        }else{
            
             try{
    String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
    ps=conn_db.prepareStatement(sql);
    ps.setString(1, username.getText());
    ps.setString(2, password.getText());

    rs=ps.executeQuery();
    
    while(rs.next()){
        records = rs.getInt(1);
    }
    
    
    ////set type////
    
    
    try{
        
        String sql2 = "Select type from user where username = '"+username.getText()+"'and password ='"+password.getText()+"'";
                ps = conn_db.prepareStatement(sql2);
                rs = ps.executeQuery();
        
        while(rs.next()){
            String gettype = rs.getString("type");
            typeset.setText(gettype);
        }
        
    }catch(Exception e){
                 JOptionPane.showMessageDialog(null, e);
             }
    
    
    ////set type////
    
   
    
    
    if(records > 0){
        
         ////get user id/////
    
    try{
        
        String sql2 = "Select user_id from user where username = '"+username.getText()+"'and password ='"+password.getText()+"'";
                ps = conn_db.prepareStatement(sql2);
                rs = ps.executeQuery();
        
        while(rs.next()){
            String getid = rs.getString("user_id");
            uid = getid;
            setlogid.setText(getid);
        }
        
    }catch(Exception e){
                 JOptionPane.showMessageDialog(null, e);
             }
    
    ////get user id////previously login_id*****
    
    
    ////audit trail////change to user logs
    
    ////ssplit date and time/////
    
    String string = thisdate;
    String[] parts = string.split(" ");
    String p1 = parts[0]; // 004
    String p2 = parts[1]; // 034556
    String p3 = parts[2]; // 034556
    
    day = p1;
    month = p2;
    year = p3;
    
    ////////////////////////////
//    String action = "Login";
//    String nameframe = "Login Frame";
    try{
        
        String sql2 = "insert into user_logs (`login_name`,`user_id`,`hour`,`minute`,`am_pm`,`day`,`month`,`year`) values"
                + "(?,?,?,?,UPPER(?),UPPER(?),UPPER(?),UPPER(?))";
                ps = conn_db.prepareStatement(sql2);
                ps.setString(1, "Login");
                ps.setString(2, uid);
                ps.setInt(3, hourset);
                ps.setInt(4, minuteset);
                ps.setString(5, am_pmset);
                ps.setString(6, day);
                ps.setString(7, month);
                ps.setString(8, year);
                ps.execute();
                
                //JOptionPane.showMessageDialog(null, "Succesfully Saved to user_logs");
        
    }catch(Exception e){
                 JOptionPane.showMessageDialog(null, e);
             }
    
    ////user_logs////

	this.dispose();
        new loading_frm().setVisible(true);

    }
    else{       
                num++;
                JOptionPane.showMessageDialog(null, "Wrong inputs in try #"+ num );
                if(num == 3){
                    
                    
                    JOptionPane.showMessageDialog(null, "Failed to login in "+ num + " tries");
                    
                    username.setEnabled(false);
                    password.setEnabled(false);
                    login_btn.setEnabled(false);
                    ///timer start
                    t = new Timer(1000, new ActionListener() {
                        
                        @Override
                        public void actionPerformed(ActionEvent e) {
                           count--;
                           lbl_timer.setText("The system will restart in " + count + " seconds");
                           
                           if(lbl_timer.getText().equals("The system will restart in 1 seconds")){
                               
                               t.stop();
                               
                               JOptionPane.showMessageDialog(null, "System will now restart. Please hit OK");
                               dispose();
                               new login_frm().setVisible(true);
                               
                           }
                        }
                    });
                    t.start();
                    
                    //this.dispose();
                    
                    
                }
            }

}catch(SQLException | HeadlessException e){

    JOptionPane.showMessageDialog(null, e.getMessage());

}
        }
    }//GEN-LAST:event_passwordActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Are you sure you want to close?",
            "Closing the system", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(num);
        }
        else {

            System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
            return;
        }
    }//GEN-LAST:event_formWindowClosing

    private void login_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_btnMouseEntered
        login_btn.setText("");
    }//GEN-LAST:event_login_btnMouseEntered

    private void login_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_login_btnMouseExited
        login_btn.setText("Login");
    }//GEN-LAST:event_login_btnMouseExited

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
            java.util.logging.Logger.getLogger(login_frm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login_frm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login_frm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login_frm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login_frm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_timer;
    private javax.swing.JButton login_btn;
    private javax.swing.JPasswordField password;
    public static javax.swing.JLabel setlogid;
    public static javax.swing.JLabel typeset;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
