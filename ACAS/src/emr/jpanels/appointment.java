/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emr.jpanels;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import login.connection_db;
/**
 *
 * @author Irvin Guinto
 */
public class appointment extends javax.swing.JFrame {
    Connection conn_db = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    String day;
    String month;
    String year;
    
    String day1;
    String month1;
    String year1;
    
    String meridiem;
    
    String msg;
    /**
     * Creates new form appointment
     */
    public appointment() {
        initComponents();
        conn_db = connection_db.ConnectDB();
        //for centered frame////
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);   
        //
        
        jComboBox1.setEditable(true);
        jComboBox2.setEditable(true);
        
        x.setVisible(false);
        y.setVisible(false);
        z.setVisible(false);
        n.setVisible(false);
        jButton3.setVisible(false);
        jLabel9.setVisible(false);
        
        jButton2.setEnabled(false);
        
        int q = 60;
        
        for(int x = 0; x<=60; x++){
            if(x<=9){
                jComboBox2.addItem("0"+x);
            }else{
                jComboBox2.addItem(x);
            }
        }
    }
    
    public void saveAppointment(){
        try{
                String sql = "Insert into appointment (`pet_id`,`reason`,`hour`,`minute`,`meridiem`,`day`,`month`,`year`) values (?,?,?,?,?,?,?,?)";
                ps=conn_db.prepareStatement(sql);
                ps.setString(1, appointment_pet_id.getText());
                ps.setString(2, jTextField1.getText().toUpperCase());
                ps.setString(3, jComboBox1.getSelectedItem().toString());
                ps.setString(4, jComboBox2.getSelectedItem().toString());
                ps.setString(5, meridiem.toUpperCase());
                ps.setString(6, day);
                ps.setString(7, month.toUpperCase());
                ps.setString(8, year);
                ps.execute();
                
                DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
                
                String conDate = day+" "+month.toUpperCase()+" "+year;
                
                String conTime = jComboBox1.getSelectedItem().toString() + ":" + jComboBox2.getSelectedItem().toString() +" "+ meridiem;
                
                model.addRow(new Object[]{jTextField1.getText().toUpperCase(), conDate, conTime});
                
                JOptionPane.showMessageDialog(null, "Appointment successfully added");
                
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void getContactNo(){
        try{
            String putincb = "select * from owner where owner_id ='"+jLabel5.getText()+"'";
            ps = conn_db.prepareStatement(putincb);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getNumber= rs.getString("contact_number_1");
                y.setText(getNumber);
            }
            
        }catch(Exception e){     
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void saveToSMSNotification(){
        msg = "Good day pet owner, This is Daddiangas Heights Veterinary Clinic informing you on "+day.toUpperCase()+" "+month.toUpperCase()+" "+year.toUpperCase()+" will be your pet's next appointment Thank you.";
        try{
                String sql = "Insert into sms_notification (`owner_id`,`message`,`day`,`month`,`year`,`hour`,`minute`,`meridiem`,`status`) values (?,?,?,?,?,?,?,?,?)";
                ps=conn_db.prepareStatement(sql);
                ps.setString(1, jLabel5.getText());
                ps.setString(2, msg);
                ps.setString(3, day1.toUpperCase());
                ps.setString(4, month1.toUpperCase());
                ps.setString(5, year1.toUpperCase());
                ps.setString(6, jComboBox1.getSelectedItem().toString());
                ps.setString(7, jComboBox2.getSelectedItem().toString());
                ps.setString(8, meridiem);
                ps.setString(9, "ONGOING");
                ps.execute();
                
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        appointment_pet_id = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        n = new javax.swing.JLabel();
        x = new javax.swing.JLabel();
        y = new javax.swing.JLabel();
        z = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        jLabel10.setText("jLabel10");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Set Appointment");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Set Appointment");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 260, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Set Time");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, -1, -1));
        getContentPane().add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 260, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Reason for next appointment");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Set Date");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, -1, -1));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Hour");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 214, -1, 20));

        jRadioButton1.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setText("AM");
        jRadioButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton1MouseClicked(evt);
            }
        });
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, -1, -1));

        jRadioButton2.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setText("PM");
        jRadioButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButton2MouseClicked(evt);
            }
        });
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, -1, -1));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Minute");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 214, -1, 20));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 50, -1));

        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, 50, -1));

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 100, -1));

        appointment_pet_id.setText("2");
        getContentPane().add(appointment_pet_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(836, 10, 0, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Reason", "Date", "Time"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 60, -1, 400));

        n.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        n.setForeground(new java.awt.Color(255, 255, 255));
        n.setText("at 12:45 AM");
        getContentPane().add(n, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 390, 120, -1));

        x.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        x.setForeground(new java.awt.Color(255, 255, 255));
        x.setText("An SMS notification will be send to No.:");
        getContentPane().add(x, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 350, -1, -1));

        y.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        y.setForeground(new java.awt.Color(255, 204, 0));
        y.setText("+6399999999999");
        getContentPane().add(y, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, -1, -1));

        z.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        z.setForeground(new java.awt.Color(255, 255, 255));
        z.setText("on 25 DEC 2015");
        getContentPane().add(z, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 370, 120, -1));

        jLabel5.setText("2");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 500, 0, -1));

        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 100, -1));

        jButton3.setText("View Ongoing SMS");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 440, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Other Option");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 420, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg.jpg"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton1MouseClicked

    }//GEN-LAST:event_jRadioButton1MouseClicked

    private void jRadioButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButton2MouseClicked

    }//GEN-LAST:event_jRadioButton2MouseClicked

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        jRadioButton2.setSelected(true);
        meridiem = "PM";
        jRadioButton1.setSelected(false);
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        jRadioButton1.setSelected(true);
        meridiem = "AM";
        jRadioButton2.setSelected(false);
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jTextField1.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Empty field detected!");
        }else{
            SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
            String dob = dateFormat.format(jDateChooser1.getDate());
            String string3 = dob;
            String[] parts3 = string3.split(" ");
            day = parts3[0];
            month = parts3[1];
            year = parts3[2];

            Calendar cal = jDateChooser1.getCalendar();
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("d MMM yyyy");
            dateFormat2.setCalendar(cal); 
            cal.add(Calendar.DATE, -3);
            
            String b = dateFormat2.format(cal.getTime()).toString();
            
            String string32 = b;
            String[] parts32 = string32.split(" ");
            day1 = parts32[0];
            month1 = parts32[1];
            year1 = parts32[2];
            
            z.setText("on "+dateFormat2.format(cal.getTime()));
            n.setText("at "+ jComboBox1.getSelectedItem().toString()+":"+jComboBox2.getSelectedItem().toString()+" "+meridiem);
            
            x.setVisible(true);
            y.setVisible(true);
            z.setVisible(true);
            n.setVisible(true);
            jButton3.setVisible(true);
            jLabel9.setVisible(true);
            
            saveAppointment();
            getContactNo();
            saveToSMSNotification();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Are you sure you want to close?",
            "Closing Appointment", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            this.dispose();
        }
        else {
            System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
            return;
        }
    }//GEN-LAST:event_formWindowClosing

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jButton2.setEnabled(false);
        jButton1.setEnabled(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        jButton2.setEnabled(true);
        jButton1.setEnabled(false);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new pending_sms().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(appointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(appointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(appointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(appointment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new appointment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel appointment_pet_id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel n;
    private javax.swing.JLabel x;
    private javax.swing.JLabel y;
    private javax.swing.JLabel z;
    // End of variables declaration//GEN-END:variables
}
