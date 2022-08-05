/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emr.jpanels;

import audit_trailing.audit_trail;
import com.toedter.calendar.JCalendar;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import login.connection_db;
import static emr.jpanels.pet_chart.petid;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import static emr.jpanels.pet_chart.*;
import other.features.jpanels.customMessage_Screen;

/**
 *
 * @author irv
 */
public class allergies extends javax.swing.JFrame {

    Connection conn_db = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    String day = "";
    String month = "";
    String year = "";
    
    String severity = "";
    
    String allID = "";
    /**
     * Creates new form allergies
     */
    public allergies() {
        initComponents();

        conn_db = connection_db.ConnectDB();

        //for centered frame////
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        //
        
        autoShow();
        
        jButton2.setEnabled(false);
    }
    
    public void collectReactions(){
        jTextArea1.setText("");
        int size = jTable2.getRowCount();//
        int f = size-1;
        
        for (int x=0; x<=f; x++){
            if(x==f){
               jTextArea1.append(jTable2.getValueAt(x, 0).toString().toUpperCase());
            }else{
               jTextArea1.append(jTable2.getValueAt(x, 0).toString().toUpperCase()+", "); 
            }
            
        }  
     }
    
    public void saveAllergies(){
        JCalendar dateset = new JCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
        String dob = dateFormat.format(dateset.getDate());
        String thisdate = dob;
        
        String string = thisdate;
        String[] parts = string.split(" ");
        String p1 = parts[0]; // 004
        String p2 = parts[1]; // 034556
        String p3 = parts[2]; // 034556

        day = p1;
        month = p2.toUpperCase();
        year = p3;
        
        
        try{
            String sql = "Insert into allergies (`pet_id`,`allergy`,`reaction`,`severity`,`day`,`month`,`year`) values (?,?,?,?,?,?,?)";
            ps=conn_db.prepareStatement(sql);
            ps.setString(1, petid);
            ps.setString(2, jTextField3.getText().toUpperCase());
            ps.setString(3, jTextArea1.getText().toUpperCase());
            ps.setString(4, severity);
            ps.setString(5, day);
            ps.setString(6, month);
            ps.setString(7, year);
            ps.execute();

            JOptionPane.showMessageDialog(null, "Pet's allergy has been successfully stored");
            
            audit_trail au = new audit_trail();
            au.action_type = "Saved - allergy information by "+ pet_name.getText();
            au.module_name = "Pet Chart";
            au.saveAuditTRail();
            
            reset();
            autoShow();
  
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void autoShow(){
        try{
            String sql = "SELECT allergy AS `Allergy`, CONCAT_WS(' ', day, month, year) AS `Date Represented`, severity AS `Severity`, reaction AS `Reactions` FROM `allergies` WHERE  pet_id = '"+petid+"' ORDER BY year";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
   }
    
    public void setUpdate(){
        DefaultTableModel model=(DefaultTableModel) jTable2.getModel();
        
        int rows = model.getRowCount(); 
        for(int i = rows - 1; i >=0; i--)
        {
           model.removeRow(i); 
        }
        
        try{
            String sql = "SELECT * FROM allergies WHERE allergy = '"+jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString()+"' AND pet_id = '"+petid+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getAllergy = rs.getString("allergy");
                jTextField3.setText(getAllergy);
                
                String getReactions = rs.getString("reaction");
                jTextArea1.setText(getReactions);
                
                String gets[] = jTextArea1.getText().split(", ");

                int f = gets.length;
        
                int i = f-1;

                for(int x=0; x<=i; x++){
                    model.addRow(new Object[]{gets[x]});
                }
                
                String getSeverity = rs.getString("severity");
                
                if(getSeverity.equals("MILD")){
                    jRadioButton1.setSelected(true);
                    jRadioButton2.setSelected(false);
                    jRadioButton3.setSelected(false);
                    severity = "MILD";
                }else{
                    if(getSeverity.equals("MODERATE")){
                        jRadioButton2.setSelected(true);
                        jRadioButton1.setSelected(false);
                        jRadioButton3.setSelected(false);
                        severity = "MODERATE";
                    }else{
                        jRadioButton3.setSelected(true);
                        jRadioButton2.setSelected(false);
                        jRadioButton1.setSelected(false);
                        severity = "SEVERE";
                    }
                }
                
            }
            
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void getAllergyID(){
        try{
            String putincb = "select allergies.id from allergies WHERE allergy = '"+jTextField3.getText()+"' AND reaction = '"+jTextArea1.getText()+"'";
            ps = conn_db.prepareStatement(putincb);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getid = rs.getString("allergies.id");
                allID = getid;
            }
            
        }catch(Exception e){     
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void UpdateAllergies(){
        JCalendar dateset = new JCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
        String dob = dateFormat.format(dateset.getDate());
        String thisdate = dob;
        
        String string = thisdate;
        String[] parts = string.split(" ");
        String p1 = parts[0]; // 004
        String p2 = parts[1]; // 034556
        String p3 = parts[2]; // 034556

        day = p1;
        month = p2.toUpperCase();
        year = p3;
        
        try{
            String sql = "UPDATE allergies SET allergy = UPPER(?), reaction = UPPER(?), severity = UPPER(?), day = UPPER(?), month = UPPER(?), year = UPPER(?) where id ='"+allID+"'";
            ps=conn_db.prepareStatement(sql);
            
            ps.setString(1, jTextField3.getText().toUpperCase());
            ps.setString(2, jTextArea1.getText().toUpperCase());
            ps.setString(3, severity);
            ps.setString(4, day);
            ps.setString(5, month);
            ps.setString(6, year);
            ps.executeUpdate();
            
            customMessage_Screen cm = new customMessage_Screen();
            cm.msg.setText("Pet's allergy has been successfully updated");
            cm.setVisible(true);
            
            audit_trail au = new audit_trail();
            au.action_type = "Updated - allergy information by "+ pet_name.getText();
            au.module_name = "Pet Chart";
            au.saveAuditTRail();
            
            reset();
            autoShow();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    public void autoShowAllergies(){
        try{
            String sql = "SELECT allergy AS `Allergy`, CONCAT_WS(' ', day, month, year) AS `Date Represented`, severity AS `Severity`, reaction AS `Reactions` FROM `allergies` WHERE  pet_id = '"+petid+"' ORDER BY year";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            allergies_table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
   }
    
    public void reset(){
        jTextField3.setText("");
        jTextField2.setText("");
        
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        int rows = jTable2.getRowCount();
        int f = rows-1;
        for(int i=0;i<f;i++){
          model.removeRow(i);
        }
        jRadioButton1.setSelected(false);
        jRadioButton2.setSelected(false);
        jRadioButton3.setSelected(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField2 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Manage Allergies");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Manage Allergies", 0, 0, null, new java.awt.Color(254, 254, 254)));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToolBar1.setRollover(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Add Green Button.png"))); // NOI18N
        jButton1.setText("Save");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Write Document.png"))); // NOI18N
        jButton2.setText("Update");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jPanel1.add(jToolBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1165, 52));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 640, 470));

        jTextField2.setBorder(javax.swing.BorderFactory.createTitledBorder("Allergic Reaction"));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 170, 360, 60));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Severity"));

        jRadioButton1.setText("Mild");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioButton1);

        jRadioButton2.setText("Moderate");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioButton2);

        jRadioButton3.setText("Severe");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioButton3);

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 420, 360, 70));

        jTextField3.setBorder(javax.swing.BorderFactory.createTitledBorder("Allergy"));
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 360, 60));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "List of Allergic Reaction"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 360, 150));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 500, 0, 30));

        jTabbedPane1.addTab("Allergies", jPanel1);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 1170, 650));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg.jpg"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1230, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        if(jRadioButton1.isSelected()){
            jRadioButton2.setSelected(false);
            jRadioButton3.setSelected(false);
            severity = "MILD";
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        if(jRadioButton2.isSelected()){
            jRadioButton1.setSelected(false);
            jRadioButton3.setSelected(false);
            severity = "MODERATE";
        }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        if(jRadioButton3.isSelected()){
            jRadioButton2.setSelected(false);
            jRadioButton1.setSelected(false);
            severity = "SEVERE";
        }
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        DefaultTableModel model=(DefaultTableModel) jTable2.getModel();
        model.addRow(new Object[]{jTextField2.getText().toUpperCase()});
        jTextField2.setText("");
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        collectReactions();
        int size = jTable2.getRowCount();
        
        if(jTextField3.getText().equals("") || size==0  || severity.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Field(s) Detected");
        }else{
            saveAllergies();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        jButton2.setEnabled(true);
        jButton1.setEnabled(false);
        setUpdate();
        getAllergyID();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        collectReactions();
        UpdateAllergies();
        jButton2.setEnabled(false);
        jButton1.setEnabled(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Are you sure you want to close?",
            "Closing Allegies", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            this.dispose();
            autoShowAllergies();
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
            java.util.logging.Logger.getLogger(allergies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(allergies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(allergies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(allergies.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new allergies().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
