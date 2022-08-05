/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package other.features.jpanels;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import login.connection_db;

/**
 *
 * @author IrvGu
 */
public class pet_breed_manage extends javax.swing.JFrame {
    
    Connection conn_db = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    String petType;
    int id;
    /**
     * Creates new form pet_breed_manage
     */
    public pet_breed_manage() {
        initComponents();
        
        conn_db = connection_db.ConnectDB();
        viewBreed();
        viewType();
        
        //for centered frame////
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);   
        //
        jButton1.setEnabled(false);
    }
    
    public void viewType(){
        try{
            String sql = "SELECT * FROM `pet_type`";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getType = rs.getString("pet_type_name");
                jComboBox1.addItem(getType);
            }

        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void saveBreed(){
        try{
            String sql = "Insert into animal_breed (`pet_type`,`breed`) values (UPPER(?),UPPER(?))";
            ps=conn_db.prepareStatement(sql);
            ps.setString(1, petType);
            ps.setString(2, jTextField1.getText());
            ps.execute();
            
            JOptionPane.showMessageDialog(null, "Successfully Saved");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void viewBreedByType(){
        ////removeAllListCode
        DefaultListModel<String> model = new DefaultListModel();
        pet_breed_list.setModel(model);
        int rows = model.size(); 
        for(int i = rows - 1; i >=0; i--)
        {
           model.removeElementAt(i); 
        }
        ////removeAllListCode
        jLabel3.setText("Types of breed of "+petType);
        jButton1.setEnabled(false);
        DefaultListModel dimx = new DefaultListModel();
        try{
            String sql = "SELECT * FROM `animal_breed` where pet_type ='"+petType+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getType = rs.getString("breed");
                dimx.addElement(getType);
                pet_breed_list.setModel(dimx);
            }

        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void viewBreed(){
        jLabel3.setText("Saved Pet Breed(s)");
        jButton1.setEnabled(false);
        DefaultListModel dimx = new DefaultListModel();
        try{
            String sql = "SELECT * FROM `animal_breed` where pet_type ='"+petType+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getType = rs.getString("breed");
                dimx.addElement(getType);
                pet_breed_list.setModel(dimx);
            }

        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    public void showSpecificBreed(){
        try{
            String sql = "SELECT * FROM `animal_breed` where breed = '"+pet_breed_list.getSelectedValue().toString()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getType = rs.getString("breed");
                int getID = rs.getInt("id");
                id = getID;
                jTextField1.setText(getType);
            }
            
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void updateBreed(){
        try{
            String sql = "UPDATE animal_breed SET breed = UPPER(?) where id ='"+id+"'";
            ps=conn_db.prepareStatement(sql);
            
            ps.setString(1, jTextField1.getText());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Successfully updated");
            jTextField1.setText("");
            

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

        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        pet_breed_list = new javax.swing.JList();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(73, 26, 26));
        jLabel2.setText("Breed");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 120, -1));

        jTextField1.setBorder(javax.swing.BorderFactory.createTitledBorder("Name"));
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 240, 50));

        pet_breed_list.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pet_breed_list.setForeground(new java.awt.Color(0, 102, 0));
        pet_breed_list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pet_breed_listMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(pet_breed_list);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, 250, 290));

        jButton1.setText("Update");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 90, -1));

        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 90, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(73, 26, 26));
        jLabel3.setText("Saved Pet Breed(s)");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, 250, -1));

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 180, -1));

        jLabel4.setForeground(new java.awt.Color(73, 26, 26));
        jLabel4.setText("Select a pet type:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, -1, -1));

        jButton3.setText(">>");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, -1, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(jTextField1.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Empty field detected!");
        }else{
            saveBreed();
            viewBreedByType();
            jTextField1.setText("");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String type = jComboBox1.getSelectedItem().toString();
        petType = type;
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Are you sure you want to close?",
            "Closing Breed", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            dispose();
        }
        else {

            System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
            return;
        }
    }//GEN-LAST:event_formWindowClosing

    private void pet_breed_listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pet_breed_listMouseClicked
        showSpecificBreed();
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
    }//GEN-LAST:event_pet_breed_listMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        updateBreed();
        viewBreedByType();
        jButton1.setEnabled(false);
        jButton2.setEnabled(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        viewBreedByType();
        jButton2.setEnabled(true);
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
            java.util.logging.Logger.getLogger(pet_breed_manage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(pet_breed_manage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(pet_breed_manage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(pet_breed_manage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new pet_breed_manage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JList pet_breed_list;
    // End of variables declaration//GEN-END:variables
}
