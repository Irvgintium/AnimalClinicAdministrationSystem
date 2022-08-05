/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emr.jpanels;
import audit_trailing.audit_trail;
import com.toedter.calendar.JCalendar;
import emr.jpanels.SQLClass.loading_sms_animation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import login.connection_db;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.Toolkit;
import net.proteanit.sql.DbUtils;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import other.features.jpanels.customMessage_Screen;
/**
 *
 * @author Irvin Guinto
 */
public class pending_sms extends javax.swing.JFrame {
    Connection conn_db = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    String usenumber;
    String sendMsg;
    
    String smsnotID;
    
    String appointment_date = "";
    
    JFrame x = new JFrame();
    /**
     * Creates new form pending_sms
     */
    public pending_sms() {
        initComponents();
        conn_db = connection_db.ConnectDB();
        
        //for centered frame////
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);   
        //
        
        jButton1.setEnabled(false);
        jTextField1.setEnabled(false);
        jButton2.setEnabled(false);
        jButton3.setVisible(false);
        jRadioButton2.setEnabled(false);
        jRadioButton3.setEnabled(false);
        
        getOwnerID();
        
        getAppointmentDate();
    }
    
    public void getOwnerID(){
        try{
            String sql = "SELECT DISTINCTROW sms_notification.id as `ID No.`, CONCAT_WS(' ', owner.title_name, owner.first_name, owner.middle_name, owner.last_name, owner.suffix_name) AS `Pet Owner`, pet.name AS `Pet Name`, CONCAT_WS(' ', sms_notification.month, sms_notification.day, sms_notification.year) AS `Notification Date`, CONCAT_WS(' ', appointment.month, appointment.day, appointment.year) as `Appointment Date` FROM `sms_notification` INNER JOIN owner ON sms_notification.owner_id = owner.owner_id INNER join pet on owner.owner_id = pet.owner_id INNER JOIN appointment ON pet.pet_id = appointment.pet_id WHERE sms_notification.status = 'ONGOING' GROUP BY sms_notification.id";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void showContactInf(){
        DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
        try{
            String sql = "SELECT sms_notification.id, owner.contact_number_1, owner.contact_number_2, sms_notification.message FROM sms_notification INNER JOIN owner ON sms_notification.owner_id = owner.owner_id WHERE sms_notification.id = '"+model.getValueAt(jTable1.getSelectedRow(), 0).toString()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getNum1 = rs.getString("owner.contact_number_1");
                jLabel2.setText(getNum1);
                String getNum2 = rs.getString("owner.contact_number_2");
                jLabel8.setText(getNum2);
                String getMsg = rs.getString("sms_notification.message");
                sendMsg = getMsg;
                jTextArea1.setText(getMsg);
                smsnotID = rs.getString("sms_notification.id");
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void searchbyLastName(){
        try{
            String sql = "SELECT DISTINCTROW sms_notification.id as `ID No.`, CONCAT_WS(' ', owner.title_name, owner.first_name, owner.middle_name, owner.last_name, owner.suffix_name) AS `Pet Owner`, pet.name AS `Pet Name`, CONCAT_WS(' ', sms_notification.month, sms_notification.day, sms_notification.year) AS `Notification Date`, CONCAT_WS(' ', appointment.month, appointment.day, appointment.year) as `Appointment Date`FROM `sms_notification` INNER JOIN owner ON sms_notification.owner_id = owner.owner_id INNER join pet on owner.owner_id = pet.owner_id INNER JOIN appointment ON pet.pet_id = appointment.pet_id WHERE owner.last_name LIKE '"+jTextField1.getText()+"%' and sms_notification.status = 'ONGOING' GROUP BY sms_notification.id";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void resetSMSOverview(){
        jLabel2.setText("+63xxxxxxxxxx");
        jLabel8.setText("+63xxxxxxxxxx");
        jButton1.setEnabled(false);
        jTextField1.setEnabled(false);
        jTextField1.setText("");
        jButton2.setEnabled(false);
    }
    
    public void cancelSMS(){
        DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
        try{
            String sql="UPDATE sms_notification SET `status` = ? where id = '"+model.getValueAt(jTable1.getSelectedRow(), 0).toString()+"'";         
            ps=conn_db.prepareStatement(sql);
            
            ps.setString(1, "CANCELLED");
            ps.executeUpdate();
            
            customMessage_Screen cm = new customMessage_Screen();
            cm.msg.setText("SMS Notification for "+model.getValueAt(jTable1.getSelectedRow(), 1).toString()+" has been cancelled");
            cm.setVisible(true);
            
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
    }
    
    
    public void showCancelledSMS(){
        try{
            String sql = "SELECT DISTINCTROW sms_notification.status as `Status`, sms_notification.id as `ID No.`, CONCAT_WS(' ', owner.title_name, owner.first_name, owner.middle_name, owner.last_name, owner.suffix_name) AS `Pet Owner`, pet.name AS `Pet Name`, CONCAT_WS(' ', sms_notification.month, sms_notification.day, sms_notification.year) AS `Notification Date`, CONCAT_WS(' ', appointment.month, appointment.day, appointment.year) as `Appointment Date`FROM `sms_notification` INNER JOIN owner ON sms_notification.owner_id = owner.owner_id INNER join pet on owner.owner_id = pet.owner_id INNER JOIN appointment ON pet.pet_id = appointment.pet_id WHERE sms_notification.status = 'CANCELLED' GROUP BY sms_notification.id";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void undoSMS(){
        DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
        try{
            String sql="UPDATE sms_notification SET `status` = ? where id = '"+model.getValueAt(jTable1.getSelectedRow(), 1).toString()+"'";         
            ps=conn_db.prepareStatement(sql);
            
            ps.setString(1, "ONGOING");
            ps.executeUpdate();
            
            customMessage_Screen cm = new customMessage_Screen();
            cm.msg.setText("SMS Notification for ID No. "+model.getValueAt(jTable1.getSelectedRow(), 1).toString()+" is now ongoing");
            cm.setVisible(true);
            
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
    }
    
    public void searchbyLastNameCancelled(){
        try{
            String sql = "SELECT DISTINCTROW sms_notification.status as `Status`, sms_notification.id as `ID No.`, CONCAT_WS(' ', owner.title_name, owner.first_name, owner.middle_name, owner.last_name, owner.suffix_name) AS `Pet Owner`, pet.name AS `Pet Name`, CONCAT_WS(' ', sms_notification.month, sms_notification.day, sms_notification.year) AS `Notification Date`, CONCAT_WS(' ', appointment.month, appointment.day, appointment.year) as `Appointment Date`FROM `sms_notification` INNER JOIN owner ON sms_notification.owner_id = owner.owner_id INNER join pet on owner.owner_id = pet.owner_id INNER JOIN appointment ON pet.pet_id = appointment.pet_id WHERE owner.last_name LIKE '"+jTextField1.getText()+"%' and sms_notification.status = 'CANCELLED' GROUP BY sms_notification.id";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void getAppointmentDate(){
        DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
        int row = jTable1.getRowCount();
        
        for(int i=0; i<=row-1;i++){
            String getDate = model.getValueAt(i, 4).toString();
         
            java.util.Date date = null;
            try {
                date = new SimpleDateFormat("MMM d yyyy").parse(getDate);
            } catch (ParseException ex) {
                Logger.getLogger(immunization_history.class.getName()).log(Level.SEVERE, null, ex);
            }

            JCalendar calendar = new JCalendar();
            calendar.setDate(date);
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("MMM d yyyy");
            Calendar cal = calendar.getCalendar();
            dateFormat2.setCalendar(cal); 
            cal.add(Calendar.DATE, -3);

            String smsDate = dateFormat2.format(cal.getTime()).toString().toUpperCase();
            
            //updateSMSDateUsingSMSID
            
            try{
            String sql = "UPDATE sms_notification SET `day`= UPPER(?),`month`= UPPER(?),`year`= UPPER(?) where id ='"+model.getValueAt(i, 0).toString()+"'";
            ps=conn_db.prepareStatement(sql);
            
            String string3 = smsDate;
            String[] parts3 = string3.split(" ");
            String month = parts3[0];
            String day = parts3[1];
            String year = parts3[2];
            
            ps.setString(1, day);
            ps.setString(2, month);
            ps.setString(3, year);
            ps.executeUpdate();
            
            audit_trail au = new audit_trail();
            au.action_type = "Updated - SMS Notification";
            au.module_name = "Pet Chart";
            au.saveAuditTRail();
            
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
            
            getOwnerID();
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

        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jButton3 = new javax.swing.JButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Ongoing SMS Notification(s)");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(73, 26, 26));
        jLabel6.setText("Ongoing SMS Notifications");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(73, 26, 26));
        jLabel2.setText("+63xxxxxxxxxx");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(73, 26, 26));
        jLabel3.setText("Message:");

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(73, 26, 26));
        jLabel4.setText("SMS Notification Overview");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(73, 26, 26));
        jLabel8.setText("+63xxxxxxxxxx");

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("Send Now");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCheckBox1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(73, 26, 26));
        jCheckBox1.setText("Search by last name");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setText("Cancel SMS Notification");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jRadioButton1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(73, 26, 26));
        jRadioButton1.setText("Show cancelled SMS Notifications");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.setText("Undo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jRadioButton3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jRadioButton3.setForeground(new java.awt.Color(73, 26, 26));
        jRadioButton3.setText("Contact No. 2");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jRadioButton2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jRadioButton2.setForeground(new java.awt.Color(73, 26, 26));
        jRadioButton2.setText("Contact No. 2");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel6)
                        .addGap(86, 86, 86)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel4)
                        .addGap(253, 253, 253)
                        .addComponent(jRadioButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel2)
                                .addGap(30, 30, 30)
                                .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel3)
                                .addGap(11, 11, 11)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(170, 170, 170)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jCheckBox1)
                        .addGap(8, 8, 8)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4))
                    .addComponent(jRadioButton1))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton3)))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if(jRadioButton1.isSelected()){
            jButton3.setVisible(true);
        }else{
            showContactInf();
            jRadioButton2.setEnabled(true);
            jRadioButton3.setEnabled(true);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jLabel2.getText().equals("")){
            JOptionPane.showMessageDialog(null, "No contact no. available");
        }else{
            if(usenumber.equals("")){
                JOptionPane.showMessageDialog(null, "Cannot use an empty contact no.");
            }else{
                
                if (JOptionPane.showConfirmDialog(new JFrame(),
                    "Are you sure you want to send the message?",
                    "Sending SMS Notification", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    new loading_sms_animation().setVisible(true);
                }
                else {
                    System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
                    return;
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if(jCheckBox1.isSelected()){
            jTextField1.setEnabled(true);
        }else{
            jTextField1.setEnabled(false);
            resetSMSOverview();
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        if(jTextField1.getText().equals("")){
            if(jRadioButton1.isSelected()){
                showCancelledSMS();
            }else{
                getOwnerID();
            }
        }else{
            if(jRadioButton1.isSelected()){
                searchbyLastNameCancelled();
            }else{
                searchbyLastName();
            }
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Are you sure you want to cancel SMS notification?",
            "Cancelling SMS Notification", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            resetSMSOverview();
            cancelSMS();
            getOwnerID();
        }
        else {

            System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
            return;
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        if(jRadioButton1.isSelected()){
            showCancelledSMS();
        }else{
            getOwnerID();
            jButton3.setVisible(false);
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        undoSMS();
        showCancelledSMS();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        if(jRadioButton3.isSelected()){
            jRadioButton2.setSelected(false);
            usenumber = jLabel2.getText();
            jButton1.setEnabled(true);
            jButton2.setEnabled(true);
        }else{
            jButton1.setEnabled(false);
            jButton2.setEnabled(false);
        }
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        if(jRadioButton2.isSelected()){
            jRadioButton3.setSelected(false);
            usenumber = jLabel8.getText();
            jButton1.setEnabled(true);
            jButton2.setEnabled(true);
        }else{
            jButton1.setEnabled(false);
            jButton2.setEnabled(false);
        }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Are you sure you want to close?",
            "Closing Pending SMS", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            this.dispose();
            
        }
        else {

            System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
            return;
        }
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(pending_sms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pending_sms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pending_sms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pending_sms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pending_sms().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton jButton1;
    public static javax.swing.JButton jButton2;
    public static javax.swing.JButton jButton3;
    public static javax.swing.JCheckBox jCheckBox1;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel4;
    public static javax.swing.JLabel jLabel6;
    public static javax.swing.JLabel jLabel8;
    public static javax.swing.JRadioButton jRadioButton1;
    public static javax.swing.JRadioButton jRadioButton2;
    public static javax.swing.JRadioButton jRadioButton3;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JTextArea jTextArea1;
    public static javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
