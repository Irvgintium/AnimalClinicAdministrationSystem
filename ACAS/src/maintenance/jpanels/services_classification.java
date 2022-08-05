/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maintenance.jpanels;
import java.awt.Color;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import login.connection_db;

/**
 *
 * @author asus
 */
public class services_classification extends javax.swing.JPanel {

    Connection conn_db = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    String classid;
    
    public services_classification() {
        initComponents();
        conn_db = connection_db.ConnectDB();
    }

     public void getClassID(){
        try{
            String sql = "select * from services_classifications where classification_name ='"+txt_class.getText()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getID = rs.getString("classification_id");
                classid = getID;
            }
        
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
         }
     
      public void container(){
      
        txt_class.getText();
       
    
}
    
     
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_class = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        class_jtbl = new javax.swing.JTable();
        update = new javax.swing.JButton();
        archive = new javax.swing.JButton();
        save = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_class.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_class.setForeground(new java.awt.Color(51, 102, 0));
        txt_class.setText("*Classification");
        txt_class.setToolTipText("Classification Name");
        txt_class.setBorder(javax.swing.BorderFactory.createTitledBorder("Classification Name"));
        txt_class.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_classMouseClicked(evt);
            }
        });
        txt_class.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_classActionPerformed(evt);
            }
        });
        txt_class.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_classFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_classFocusLost(evt);
            }
        });
        add(txt_class, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 180, 50));
        txt_class.getAccessibleContext().setAccessibleName("Classification  Name");

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel7.setText("Classification");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        class_jtbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Classification"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        class_jtbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                class_jtblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(class_jtbl);
        class_jtbl.getColumnModel().getColumn(0).setResizable(false);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, 490, 280));

        update.setText("Update");
        update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateMouseClicked(evt);
            }
        });
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 440, 110, 20));

        archive.setText("Archive");
        add(archive, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 440, 110, 20));

        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 440, 110, 20));

        jLabel1.setText(" ");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, 40, -1));

        getAccessibleContext().setAccessibleName("Classification Name");
        getAccessibleContext().setAccessibleDescription("Classification Name");
    }// </editor-fold>//GEN-END:initComponents

    private void txt_classMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_classMouseClicked

    }//GEN-LAST:event_txt_classMouseClicked

    private void txt_classActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_classActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_classActionPerformed

    private void txt_classFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_classFocusGained
        if(txt_class.getText().equals("*Classification")){
            txt_class.setText("");
            txt_class.setForeground(Color.BLACK);
        }else{

        }
    }//GEN-LAST:event_txt_classFocusGained

    private void txt_classFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_classFocusLost
        if(txt_class.getText().equals("")){
            txt_class.setText("*Classification");
            txt_class.setForeground(new Color(51,102,0));
        }else{
        }
    }//GEN-LAST:event_txt_classFocusLost

    private void class_jtblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_class_jtblMouseClicked

        DefaultTableModel model=(DefaultTableModel) class_jtbl.getModel();
        txt_class.setText((String) model.getValueAt(class_jtbl.getSelectedRow(), 0));
        
        getClassID();

    }//GEN-LAST:event_class_jtblMouseClicked

    private void updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateMouseClicked

    }//GEN-LAST:event_updateMouseClicked

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
       try{
            String sql="UPDATE services_classifications SET classification_name=? where classification_id= ? ";         
            ps=conn_db.prepareStatement(sql);
            
            ps.setString(1, txt_class.getText());
            ps.setString(2, classid);
   
            ps.executeUpdate();
            
             JOptionPane.showMessageDialog(null, "Classification Successfully Updated!");
            
        }
            catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            }
        
                            
        DefaultTableModel model=(DefaultTableModel) class_jtbl.getModel();
        model.setValueAt(txt_class.getText(), class_jtbl.getSelectedRow(), 0);
        
        txt_class.setText("");
    }//GEN-LAST:event_updateActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        save.setEnabled(true);
        update.setEnabled(false);
      
         jLabel1.setText("");
        
//        container();
//        
//        int pass = 1;
//        
//        int count = 0;
//        
//        if(txt_class.equals("")){
//        
//        }else{
//            count= count + 1;
//        
//        }
//         if (count<9){
//            JOptionPane.showMessageDialog(null, "Classification field is empty");
//        
//        }else{
        
        
        

        DefaultTableModel model=(DefaultTableModel) class_jtbl.getModel();
        model.addRow(new Object[] {txt_class.getText(),});
        
        try{
            String sql2 = "insert into services_classifications (classification_name)values(?)";
            ps=conn_db.prepareStatement(sql2);
            ps.setString(1, txt_class.getText());
            ps.execute();

            JOptionPane.showMessageDialog(null, "Classification Successfully Saved!");

            
            

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e) ;
        }
        update.setEnabled(true);
        txt_class.setText("");
    }//GEN-LAST:event_saveActionPerformed
////    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton archive;
    private javax.swing.JTable class_jtbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton save;
    private javax.swing.JTextField txt_class;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables

    
}
