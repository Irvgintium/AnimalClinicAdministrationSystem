/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emr.jpanels;

import com.jidesoft.swing.AutoCompletion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import login.connection_db;

/**
 *
 * @author WHY
 */
public class prescription_frame extends javax.swing.JFrame {

    Connection conn_db = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    String getMedType;
    String getprescribeMed;
    String cid2;
  
    
 
    
    public prescription_frame() {
        initComponents();
        conn_db = connection_db.ConnectDB();
        
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
      
         //*************medicine*************//
         
         try{
         String med = "select * from medicine_tbl";
         ps = conn_db.prepareStatement(med);
         rs = ps.executeQuery();
         
         while (rs.next()){
         String sql3=rs.getString("medicineName");
         prescMed_cmb.addItem(sql3);
         }
       
     }
     catch (Exception e) {
         JOptionPane.showMessageDialog(null, e);}
       
         
         //***************medicine type***********//
         
         
         try{
         String medtype = "select * from medtype_tbl";
         ps = conn_db.prepareStatement(medtype);
         rs = ps.executeQuery();
         
         while (rs.next()){
         String sql3=rs.getString("medicineType");
         medtype_cmb.addItem(sql3);
         }
       
     }
     catch (Exception e) {
         JOptionPane.showMessageDialog(null, e);}
    }
         public void getPrescriptionID(){
        try{
            String sql = "select * from patientprescription_tbl";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getID = rs.getString("patientpresc_id");
                cid2 = getID;
            }
        
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

        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        frequency_txt = new javax.swing.JTextField();
        updatebtn = new javax.swing.JButton();
        printbtn = new javax.swing.JButton();
        prescMed_cmb = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        quantity_txt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        dose_txt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        medtype_cmb = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        daysupply_txt = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        stab = new javax.swing.JTable();
        savebtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setText("Medicine:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 100, 60, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel8.setText("* How often do you take this medication? (For example, \"Once a day\")");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, 350, 30));
        getContentPane().add(frequency_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 200, 30));

        updatebtn.setText("Update");
        updatebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebtnActionPerformed(evt);
            }
        });
        getContentPane().add(updatebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 340, 90, -1));

        printbtn.setText("Print");
        printbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printbtnActionPerformed(evt);
            }
        });
        getContentPane().add(printbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 340, 90, -1));

        prescMed_cmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        prescMed_cmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prescMed_cmbActionPerformed(evt);
            }
        });
        getContentPane().add(prescMed_cmb, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, 200, 30));

        jLabel10.setText("Frequency:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 70, 30));

        jLabel11.setText("Quantity:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 60, 30));

        quantity_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantity_txtActionPerformed(evt);
            }
        });
        getContentPane().add(quantity_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 240, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel12.setText("* How much of this medication do you take each time?");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 260, 30));

        jLabel13.setText("Dosage:");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 60, 30));
        getContentPane().add(dose_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 240, 30));

        jLabel9.setText("Medicine Type:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 90, 30));

        medtype_cmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        medtype_cmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medtype_cmbActionPerformed(evt);
            }
        });
        getContentPane().add(medtype_cmb, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 160, 30));

        jLabel14.setText("Days Supply:");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 90, 30));

        daysupply_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daysupply_txtActionPerformed(evt);
            }
        });
        getContentPane().add(daysupply_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, 180, 30));

        stab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine", "Quantity", "Dosage", "Frequency", "Days supply"
            }
        ));
        stab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stabMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(stab);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 70, 600, 260));

        savebtn.setText("Save");
        savebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebtnActionPerformed(evt);
            }
        });
        getContentPane().add(savebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 340, 90, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Prescription");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Date");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void prescMed_cmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prescMed_cmbActionPerformed
        
        
        String Y = prescMed_cmb.getSelectedItem().toString();
        Y= getprescribeMed;
    }//GEN-LAST:event_prescMed_cmbActionPerformed

    private void printbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printbtnActionPerformed
        
    }//GEN-LAST:event_printbtnActionPerformed

    private void updatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtnActionPerformed
        updatebtn.setEnabled(true);
        
        try{
            String sql="UPDATE patientprescription_tbl SET medicineType=?, medicine=?, quantity=?, dosage=?, frequency=?, daysSupply=? where patientpresc_id=?";         
            ps=conn_db.prepareStatement(sql);
            
            ps.setString(1, medtype_cmb.getSelectedItem().toString());
            ps.setString(2, prescMed_cmb.getSelectedItem().toString());
            ps.setString(3, quantity_txt.getText());
            ps.setString(4, dose_txt.getText());
            ps.setString(5, frequency_txt.getText());
            ps.setString(6, daysupply_txt.getText());
            ps.setString(7, cid2);
   
            ps.executeUpdate();
            
             JOptionPane.showMessageDialog(null, "Successfully Updated!");
            
        }
            catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            }
         DefaultTableModel model=(DefaultTableModel) stab.getModel();
        model.setValueAt(medtype_cmb.getSelectedItem().toString(),stab.getSelectedRow(), 0);
        model.setValueAt(prescMed_cmb.getSelectedItem().toString(),stab.getSelectedRow(), 1);
        model.setValueAt(quantity_txt.getText(), stab.getSelectedRow(), 2);
        model.setValueAt(dose_txt.getText(), stab.getSelectedRow(), 3);
        model.setValueAt(frequency_txt.getText(), stab.getSelectedRow(), 4);
        model.setValueAt(daysupply_txt.getText(), stab.getSelectedRow(), 5);
        
         medtype_cmb.setSelectedIndex(0);
         prescMed_cmb.setSelectedIndex(1);
         quantity_txt.setText("");
         dose_txt.setText("");
         frequency_txt.setText("");
         daysupply_txt.setText("");
    }//GEN-LAST:event_updatebtnActionPerformed

    private void medtype_cmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medtype_cmbActionPerformed
        String X = medtype_cmb.getSelectedItem().toString();
        X= getMedType;
    }//GEN-LAST:event_medtype_cmbActionPerformed

    private void daysupply_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daysupply_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_daysupply_txtActionPerformed

    private void quantity_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantity_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantity_txtActionPerformed

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed
        savebtn.setEnabled(true);
        
        
        DefaultTableModel model=(DefaultTableModel) stab.getModel();
        model.addRow(new Object[] {medtype_cmb.getSelectedItem().toString(),prescMed_cmb.getSelectedItem().toString(),quantity_txt.getText(), dose_txt.getText(),frequency_txt.getText(),daysupply_txt.getText()});
        
        
        
        
         try{
            String sql3 = "insert into patientprescription_tbl (medicineType, medicine, quantity, dosage, frequency, daysSupply)values(?,?,?,?,?,?)";
            ps=conn_db.prepareStatement(sql3);
           
            ps.setString(1, medtype_cmb.getSelectedItem().toString());
            ps.setString(2, prescMed_cmb.getSelectedItem().toString());
            ps.setString(3, quantity_txt.getText());
            ps.setString(4, dose_txt.getText());
            ps.setString(5, frequency_txt.getText());
            ps.setString(6, daysupply_txt.getText());
           
                  ps.execute();
            
            JOptionPane.showMessageDialog(null, "Successfully Saved!");
            
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e) ;
    }
         
         medtype_cmb.setSelectedIndex(0);
         prescMed_cmb.setSelectedIndex(1);
         quantity_txt.setText("");
         dose_txt.setText("");
         frequency_txt.setText("");
         daysupply_txt.setText("");
    }//GEN-LAST:event_savebtnActionPerformed

    private void stabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stabMouseClicked
         DefaultTableModel model=(DefaultTableModel) stab.getModel();
         medtype_cmb.setSelectedItem((String) model.getValueAt(stab.getSelectedRow(), 0));
         prescMed_cmb.setSelectedItem((String) model.getValueAt(stab.getSelectedRow(), 1));
         quantity_txt.setText((String) model.getValueAt(stab.getSelectedRow(), 2));
         dose_txt.setText((String) model.getValueAt(stab.getSelectedRow(), 3));
         frequency_txt.setText((String) model.getValueAt(stab.getSelectedRow(), 4));
         daysupply_txt.setText((String) model.getValueAt(stab.getSelectedRow(), 5));
        
        
       getPrescriptionID(); 
    }//GEN-LAST:event_stabMouseClicked

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
            java.util.logging.Logger.getLogger(prescription_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(prescription_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(prescription_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(prescription_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new prescription_frame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField daysupply_txt;
    private javax.swing.JTextField dose_txt;
    private javax.swing.JTextField frequency_txt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox medtype_cmb;
    private javax.swing.JComboBox prescMed_cmb;
    private javax.swing.JButton printbtn;
    private javax.swing.JTextField quantity_txt;
    private javax.swing.JButton savebtn;
    private javax.swing.JTable stab;
    private javax.swing.JButton updatebtn;
    // End of variables declaration//GEN-END:variables
}
