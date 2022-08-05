/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package audit_trailing;

import emr.jpanels.SQLClass.dashboard_codes;
import emr.jpanels.SQLClass.emr_codes;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import login.connection_db;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author irv
 */
public class audit_trail_frame extends javax.swing.JFrame {
    Connection conn_db = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    /**
     * Creates new form audit_trail_frame
     */
    public audit_trail_frame() {
        initComponents();
        
        //for centered frame////
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);   
        //
        
        conn_db = connection_db.ConnectDB();
        showontbl();
        getnextid();
        jButton2.setEnabled(false);
    }
    
    public void showontbl(){
        try{
             String sql = "SELECT audit_trail.`audit_id` as ID, user.type as User, SUBSTRING_INDEX(SUBSTRING_INDEX(audit_trail.`action_type`, ' - ', 1), ' ', -1) AS Action, TRIM( SUBSTR(audit_trail.`action_type`, LOCATE(' - ', audit_trail.`action_type`)) ) AS Remarks, CONCAT_WS(' ',audit_trail.`day`,audit_trail.`month`,audit_trail.`year`) as Date, CONCAT_WS('',audit_trail.`hour`,':',audit_trail.`minute`,' ',audit_trail.`am_pm`) as Time FROM `audit_trail` inner join user on audit_trail.`user_id` = user.user_id where audit_trail.`audit_id` >= '"+jLabel1.getText()+"' ORDER BY audit_trail.`audit_id` LIMIT 0,20";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             jTable1.setModel(DbUtils.resultSetToTableModel(rs));         
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(0).setMinWidth(50);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(70);
        
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(90);
        jTable1.getColumnModel().getColumn(1).setMinWidth(110);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(130);
        
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(2).setMinWidth(100);
        jTable1.getColumnModel().getColumn(2).setMaxWidth(120);
        
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(4).setMinWidth(100);
        jTable1.getColumnModel().getColumn(4).setMaxWidth(120);
        
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(5).setMinWidth(70);
        jTable1.getColumnModel().getColumn(5).setMaxWidth(90);
    }
    
    public void showontblprev(){
        try{
             String sql = "SELECT audit_trail.`audit_id` as ID, user.type as User, SUBSTRING_INDEX(SUBSTRING_INDEX(audit_trail.`action_type`, ' - ', 1), ' ', -1) AS Action, TRIM( SUBSTR(audit_trail.`action_type`, LOCATE(' - ', audit_trail.`action_type`)) ) AS Remarks, CONCAT_WS(' ',audit_trail.`day`,audit_trail.`month`,audit_trail.`year`) as Date, CONCAT_WS('',audit_trail.`hour`,':',audit_trail.`minute`,' ',audit_trail.`am_pm`) as Time FROM `audit_trail` inner join user on audit_trail.`user_id` = user.user_id WHERE audit_trail.`audit_id` between '"+jLabel2.getText()+"' and '"+jLabel3.getText()+"' ORDER BY audit_trail.`audit_id` LIMIT 0,20";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             jTable1.setModel(DbUtils.resultSetToTableModel(rs));         
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(0).setMinWidth(50);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(70);
        
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(90);
        jTable1.getColumnModel().getColumn(1).setMinWidth(110);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(130);
        
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(2).setMinWidth(100);
        jTable1.getColumnModel().getColumn(2).setMaxWidth(120);
        
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(4).setMinWidth(100);
        jTable1.getColumnModel().getColumn(4).setMaxWidth(120);
        
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(5).setMinWidth(70);
        jTable1.getColumnModel().getColumn(5).setMaxWidth(90);
    }
    
    public void getnextid(){
        try{
            jButton2.setEnabled(true);
            int row = jTable1.getRowCount();
            jLabel1.setText(jTable1.getValueAt(row-1, 0).toString());
            jLabel6.setText(jTable1.getValueAt(row-20, 0).toString());
        }catch(Exception e){
            
        }
        
    }
    
    public void getprevid(){
        try{
            int row = jTable1.getRowCount();
            int getbase1 = Integer.parseInt(jTable1.getValueAt(row-20, 0).toString());
            jLabel3.setText(Integer.toString(getbase1));

            int getbase2 = getbase1-19;
            jLabel2.setText(Integer.toString(getbase2));
            
            if(Integer.parseInt(jLabel2.getText())<=2){
            jButton2.setEnabled(false);
            }
        }catch(Exception e){
            
        }
        
    }
    
    /////////////////////////////////////////////////////////////////////////////////
    
    public void showontblAV(){
        try{
             String sql = "SELECT audit_trail.`audit_id` as ID, user.type as User, SUBSTRING_INDEX(SUBSTRING_INDEX(audit_trail.`action_type`, ' - ', 1), ' ', -1) AS Action, TRIM( SUBSTR(audit_trail.`action_type`, LOCATE(' - ', audit_trail.`action_type`)) ) AS Remarks, CONCAT_WS(' ',audit_trail.`day`,audit_trail.`month`,audit_trail.`year`) as Date, CONCAT_WS('',audit_trail.`hour`,':',audit_trail.`minute`,' ',audit_trail.`am_pm`) as Time FROM `audit_trail` inner join user on audit_trail.`user_id` = user.user_id where audit_trail.`audit_id` >= '"+jLabel1.getText()+"' and user.type='ASISSTANT VETERINARIAN' ORDER BY audit_trail.`audit_id` LIMIT 0,20";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             jTable1.setModel(DbUtils.resultSetToTableModel(rs));         
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(0).setMinWidth(50);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(70);
        
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(90);
        jTable1.getColumnModel().getColumn(1).setMinWidth(110);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(130);
        
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(2).setMinWidth(100);
        jTable1.getColumnModel().getColumn(2).setMaxWidth(120);
        
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(4).setMinWidth(100);
        jTable1.getColumnModel().getColumn(4).setMaxWidth(120);
        
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(5).setMinWidth(70);
        jTable1.getColumnModel().getColumn(5).setMaxWidth(90);
    }
    
    public void showontblprevAV(){
        try{
             String sql = "SELECT audit_trail.`audit_id` as ID, user.type as User, SUBSTRING_INDEX(SUBSTRING_INDEX(audit_trail.`action_type`, ' - ', 1), ' ', -1) AS Action, TRIM( SUBSTR(audit_trail.`action_type`, LOCATE(' - ', audit_trail.`action_type`)) ) AS Remarks, CONCAT_WS(' ',audit_trail.`day`,audit_trail.`month`,audit_trail.`year`) as Date, CONCAT_WS('',audit_trail.`hour`,':',audit_trail.`minute`,' ',audit_trail.`am_pm`) as Time FROM `audit_trail` inner join user on audit_trail.`user_id` = user.user_id WHERE (audit_trail.`audit_id` between '"+jLabel2.getText()+"' and '"+jLabel3.getText()+"') and user.type='ASISSTANT VETERINARIAN' ORDER BY audit_trail.`audit_id` LIMIT 0,20";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             jTable1.setModel(DbUtils.resultSetToTableModel(rs));         
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(0).setMinWidth(50);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(70);
        
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(90);
        jTable1.getColumnModel().getColumn(1).setMinWidth(110);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(130);
        
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(2).setMinWidth(100);
        jTable1.getColumnModel().getColumn(2).setMaxWidth(120);
        
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(4).setMinWidth(100);
        jTable1.getColumnModel().getColumn(4).setMaxWidth(120);
        
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(5).setMinWidth(70);
        jTable1.getColumnModel().getColumn(5).setMaxWidth(90);
    }
    
    public void getnextidAV(){
        try{
            jButton2.setEnabled(true);
            int row = jTable1.getRowCount();
            jLabel1.setText(jTable1.getValueAt(row-1, 0).toString());
            jLabel6.setText(jTable1.getValueAt(row-20, 0).toString());
        }catch(Exception e){
            
        }
        
    }
    
    public void getprevidAV(){
        try{
            int row = jTable1.getRowCount();
            int getbase1 = Integer.parseInt(jTable1.getValueAt(row-20, 0).toString());
            jLabel3.setText(Integer.toString(getbase1));

            int getbase2 = getbase1-19;
            jLabel2.setText(Integer.toString(getbase2));
            
            if(Integer.parseInt(jLabel2.getText())<=2){
            jButton2.setEnabled(false);
            }
        }catch(Exception e){
            
        }
        
    }
    
    /////////////////////////////////////////////////////////////////////////////////

    
    public void showontblV(){
        try{
             String sql = "SELECT audit_trail.`audit_id` as ID, user.type as User, SUBSTRING_INDEX(SUBSTRING_INDEX(audit_trail.`action_type`, ' - ', 1), ' ', -1) AS Action, TRIM( SUBSTR(audit_trail.`action_type`, LOCATE(' - ', audit_trail.`action_type`)) ) AS Remarks, CONCAT_WS(' ',audit_trail.`day`,audit_trail.`month`,audit_trail.`year`) as Date, CONCAT_WS('',audit_trail.`hour`,':',audit_trail.`minute`,' ',audit_trail.`am_pm`) as Time FROM `audit_trail` inner join user on audit_trail.`user_id` = user.user_id where audit_trail.`audit_id` >= '"+jLabel1.getText()+"' and user.type='VETERINARIAN' ORDER BY audit_trail.`audit_id` LIMIT 0,20";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             jTable1.setModel(DbUtils.resultSetToTableModel(rs));         
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(0).setMinWidth(50);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(70);
        
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(90);
        jTable1.getColumnModel().getColumn(1).setMinWidth(110);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(130);
        
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(2).setMinWidth(100);
        jTable1.getColumnModel().getColumn(2).setMaxWidth(120);
        
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(4).setMinWidth(100);
        jTable1.getColumnModel().getColumn(4).setMaxWidth(120);
        
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(5).setMinWidth(70);
        jTable1.getColumnModel().getColumn(5).setMaxWidth(90);
    }
    
    public void showontblprevV(){
        try{
             String sql = "SELECT audit_trail.`audit_id` as ID, user.type as User, SUBSTRING_INDEX(SUBSTRING_INDEX(audit_trail.`action_type`, ' - ', 1), ' ', -1) AS Action, TRIM( SUBSTR(audit_trail.`action_type`, LOCATE(' - ', audit_trail.`action_type`)) ) AS Remarks, CONCAT_WS(' ',audit_trail.`day`,audit_trail.`month`,audit_trail.`year`) as Date, CONCAT_WS('',audit_trail.`hour`,':',audit_trail.`minute`,' ',audit_trail.`am_pm`) as Time FROM `audit_trail` inner join user on audit_trail.`user_id` = user.user_id WHERE (audit_trail.`audit_id` between '"+jLabel2.getText()+"' and '"+jLabel3.getText()+"') and user.type='VETERINARIAN' ORDER BY audit_trail.`audit_id` LIMIT 0,20";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             jTable1.setModel(DbUtils.resultSetToTableModel(rs));         
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(0).setMinWidth(50);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(70);
        
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(90);
        jTable1.getColumnModel().getColumn(1).setMinWidth(110);
        jTable1.getColumnModel().getColumn(1).setMaxWidth(130);
        
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(2).setMinWidth(100);
        jTable1.getColumnModel().getColumn(2).setMaxWidth(120);
        
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(80);
        jTable1.getColumnModel().getColumn(4).setMinWidth(100);
        jTable1.getColumnModel().getColumn(4).setMaxWidth(120);
        
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(50);
        jTable1.getColumnModel().getColumn(5).setMinWidth(70);
        jTable1.getColumnModel().getColumn(5).setMaxWidth(90);
    }
    
    public void getnextidV(){
        try{
            jButton2.setEnabled(true);
            int row = jTable1.getRowCount();
            jLabel1.setText(jTable1.getValueAt(row-1, 0).toString());
            jLabel6.setText(jTable1.getValueAt(row-20, 0).toString());
        }catch(Exception e){
            
        }
        
    }
    
    public void getprevidV(){
        try{
            int row = jTable1.getRowCount();
            int getbase1 = Integer.parseInt(jTable1.getValueAt(row-20, 0).toString());
            jLabel3.setText(Integer.toString(getbase1));

            int getbase2 = getbase1-19;
            jLabel2.setText(Integer.toString(getbase2));
            
            if(Integer.parseInt(jLabel2.getText())<=2){
            jButton2.setEnabled(false);
            }
        }catch(Exception e){
            
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        jRadioButton1 = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jRadioButton2 = new javax.swing.JRadioButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Audit Trailing");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 734, 410));

        jToolBar1.setRollover(true);

        jRadioButton1.setText("View Assistant Vet");
        jRadioButton1.setFocusable(false);
        jRadioButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jRadioButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jRadioButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jRadioButton1);
        jToolBar1.add(jSeparator1);

        jRadioButton2.setText("View  Veterinarian");
        jRadioButton2.setFocusable(false);
        jRadioButton2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jRadioButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jRadioButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jRadioButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Document.png"))); // NOI18N
        jButton3.setText("Preview to Print");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        getContentPane().add(jToolBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 50));

        jButton1.setText("Next");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton1MouseReleased(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 490, 80, 23));

        jButton2.setText("Previous");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, -1, 23));

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 490, 50, 30));

        jLabel2.setText("0");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 460, 30, 0));

        jLabel3.setText("0");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 460, -1, 0));

        jLabel4.setText("From");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 490, -1, 30));

        jLabel5.setText("to");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 490, -1, 30));

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("1");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 490, 50, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jRadioButton1.isSelected()){
            showontblAV();
            getnextidAV();
            jButton2.setText("Previous");
            if(jTable1.getRowCount()<20){
                JOptionPane.showMessageDialog(null, "You're on the last page");
                jButton1.setEnabled(false);
                jButton2.setText("Reset");
            }
        }else{
            if(jRadioButton2.isSelected()){
                showontblV();
                getnextidV();
                jButton2.setText("Previous");
                if(jTable1.getRowCount()<20){
                    JOptionPane.showMessageDialog(null, "You're on the last page");
                    jButton1.setEnabled(false);
                    jButton2.setText("Reset");
                }
            }else{
                showontbl();
                getnextid();
                jButton2.setText("Previous");
                if(jTable1.getRowCount()<20){
                    JOptionPane.showMessageDialog(null, "You're on the last page");
                    jButton1.setEnabled(false);
                    jButton2.setText("Reset");
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(jRadioButton1.isSelected()){
            if(jButton2.getText().equals("Reset")){
                jLabel1.setText("1");
                showontblAV();
                getnextidAV();
                jButton2.setEnabled(false);
                jButton1.setEnabled(true);
                jLabel2.setText("0");
                jLabel3.setText("0");
                jRadioButton1.setSelected(false);
                jRadioButton2.setSelected(false);
            }else{
                getprevidAV();
                showontblprevAV();
                int row = jTable1.getRowCount();
                jLabel1.setText(jTable1.getValueAt(row-1, 0).toString());
                jLabel6.setText(jTable1.getValueAt(row-20, 0).toString());
                jButton1.setEnabled(true);

            }
        }else{
            if(jRadioButton2.isSelected()){
                
                if(jButton2.getText().equals("Reset")){
                    jLabel1.setText("1");
                    showontblV();
                    getnextidV();
                    jButton2.setEnabled(false);
                    jButton1.setEnabled(true);
                    jLabel2.setText("0");
                    jLabel3.setText("0");
                    jRadioButton1.setSelected(false);
                    jRadioButton2.setSelected(false);
                }else{
                    getprevidV();
                    showontblprevV();
                    int row = jTable1.getRowCount();
                    jLabel1.setText(jTable1.getValueAt(row-1, 0).toString());
                    jLabel6.setText(jTable1.getValueAt(row-20, 0).toString());
                    jButton1.setEnabled(true);

                }
                
            }else{
                if(jButton2.getText().equals("Reset")){
                    jLabel1.setText("1");
                    showontbl();
                    getnextid();
                    jButton2.setEnabled(false);
                    jButton1.setEnabled(true);
                    jLabel2.setText("0");
                    jLabel3.setText("0");
                    jRadioButton1.setSelected(false);
                    jRadioButton2.setSelected(false);
                }else{
                    getprevid();
                    showontblprev();
                    int row = jTable1.getRowCount();
                    jLabel1.setText(jTable1.getValueAt(row-1, 0).toString());
                    jLabel6.setText(jTable1.getValueAt(row-20, 0).toString());
                    jButton1.setEnabled(true);

                }
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseReleased
        
    }//GEN-LAST:event_jButton1MouseReleased

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed

    }//GEN-LAST:event_jButton1MousePressed

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed

    }//GEN-LAST:event_jButton2MousePressed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        if(jRadioButton1.isSelected()){
            jRadioButton2.setSelected(false);
            showontblAV();
        }else{
            showontbl();
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        if(jRadioButton2.isSelected()){
            jRadioButton1.setSelected(false);
            showontblV();
        }else{
            showontblV();
        }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Are you sure you want to close?",
            "Closing Audit Trail", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            dispose();
        }
        else {

            System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
            return;
        }
    }//GEN-LAST:event_formWindowClosing

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dashboard_codes dc = new dashboard_codes();
        try {
            dc.loadingSound();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(audit_trail_frame.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            java.util.logging.Logger.getLogger(audit_trail_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(audit_trail_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(audit_trail_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(audit_trail_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new audit_trail_frame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
