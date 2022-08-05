/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emr.jpanels;

import billing.jpanels.purchase_item;
import com.jidesoft.swing.AutoCompletion;
import emr.jpanels.SQLClass.emr_codes;
import static emr.jpanels.pet_chart.*;
import static home.home_vet.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import login.connection_db;
import static workstation.main_workstation.workstation_tab;
import home.home_vet;
import java.awt.Image;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import net.proteanit.sql.DbUtils;
import workstation.main_workstation;

/**
 *
 * @author irv
 */
public class checkup_p1 extends javax.swing.JFrame {
    
    Connection conn_db = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    emr_codes emr = new emr_codes();
    
    String type;
    String gender;
    String address;
    String breed;
    /**
     * Creates new form checkup_p1
     */
    public checkup_p1() {
        initComponents();
        
        conn_db = connection_db.ConnectDB();
        
        //for centered frame////
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);   
        //
        
        searcheableCB();
        
        jButton1.setVisible(false);
    }
    
    public void searcheableCB(){
        jComboBox1.setEditable(true);
        AutoCompletion ac = new AutoCompletion(this.jComboBox1);
        ac.setStrict(false);
        
        jComboBox1.addItem("");
        
        try{
            String sql = "SELECT last_name, first_name, middle_name, suffix_name FROM `owner`";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
             String lname = rs.getString("last_name");
             String fname = rs.getString("first_name");
             String mname = rs.getString("middle_name");
             String sname = rs.getString("suffix_name");
             
             jComboBox1.addItem(lname + ", " +fname+" "+mname+" "+sname);
             
            }
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
    }
    
    public void getPet(){
        DefaultListModel dimx = new DefaultListModel();
        try{
            String sql = "SELECT pet.name, CONCAT_WS('',owner.last_name, ', ', owner.first_name, ' ', owner.middle_name, ' ', owner.suffix_name) AS `Name` FROM `pet` INNER JOIN owner ON owner.owner_id = pet.owner_id HAVING `Name` = '"+jComboBox1.getSelectedItem().toString()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
             String lname = rs.getString("pet.name");
             dimx.addElement(lname);
             jList1.setModel(dimx);
            }
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
    }
    
    public void getOwnerID(){
        try{
            String sql = "SELECT owner_id, CONCAT_WS(' ', owner.unit_number, owner.house_street_number, owner.street_name, owner.purok, owner.brgy_name, owner.city, owner.province, owner.zip_code) AS `Address`,CONCAT_WS('',owner.last_name, ', ', owner.first_name, ' ', owner.middle_name, ' ', owner.suffix_name) AS `Name` FROM `owner` HAVING `Name` = '"+jComboBox1.getSelectedItem().toString()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
             String ownerID = rs.getString("owner_id");
             String getAdd = rs.getString("Address");
             owner_id.setText(ownerID);
             address = getAdd;
          
            }
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
    }
    
    public void getPetID(){
        try{
            String sql = "SELECT * FROM `pet` WHERE name = '"+jList1.getSelectedValue().toString()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
             String ownerID = rs.getString("pet_id");
             String petType = rs.getString("type");
             String getGen = rs.getString("gender");
             String getBreed = rs.getString("breed");
             type = petType;
             gender = getGen;
             breed = getBreed;
             pet_id.setText(ownerID);
             jButton1.setVisible(true);
             
            }
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
    }
    
    public void autoviewImage(){
        try{
            byte[] imageBytes;
            Image image;
            String sql="select profile_image from pet where pet_id = '"+pet_number.getText()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                imageBytes=rs.getBytes("profile_image");
                person_image= imageBytes;
                image=getToolkit().createImage(imageBytes);
        
                ImageIcon imageIcon = new ImageIcon(image); // load the image to a imageIcon
                Image image2 = imageIcon.getImage(); // transform it 
                Image newimg = image2.getScaledInstance(160, 160,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
                imageIcon = new ImageIcon(newimg);  // transform it back

                picture.setIcon(imageIcon);
                picture.setText("");
            }

        }catch(Exception e){
            //JOptionPane.showMessageDialog(null, e);
        }
    }

    public void autoShowHistory(){
        try{
            String sql = "SELECT vaccine AS `Vaccine`, deworming AS `Deworming`, age AS `Age`, CONCAT_WS(' ', day, month, year) AS `Immunization Date` FROM `immunization_history` where pet_id ='"+pet_id.getText()+"' ORDER BY year DESC";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            immunization_table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
   }
    
    
    public void autoShow(){
        try{
            String sql = "SELECT allergy AS `Allergy`, CONCAT_WS(' ', day, month, year) AS `Date Represented`, severity AS `Severity`, reaction AS `Reactions` FROM `allergies` WHERE  pet_id = '"+pet_id.getText()+"' ORDER BY year";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            allergies_table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
   }
    
    
    public void autoViewPhEx(){
        try{
            String sql = "SELECT weight AS `Weight`, temp AS `Temp`, CONCAT_WS(' ', day, month, year) AS `Date Visit` FROM `physical_exam` WHERE  pet_id = '"+pet_id.getText()+"' ORDER BY year";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            vitals_table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void autoviewPhex(){
        try{
            String sql = "SELECT CONCAT_WS('', exam_number) AS `ID`, CONCAT_WS(' ', day, month, year) AS `Date of Examination` FROM `physical_exam` WHERE pet_id = '"+pet_id.getText()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable4.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void autoviewPrcription(){
        try{
            String sql = "SELECT prescription_number AS `Rx#`, medicine AS `Rx`, CONCAT_WS(' ', volume, volume_p2) AS `Volume`, signa AS `Sig.` FROM `prescription` WHERE pet_id = '"+pet_id.getText()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable6.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void autoviewPrcription2(){
        try{
            String sql = "SELECT prescription_number AS `Rx#`, medicine AS `Rx`, CONCAT_WS(' ', volume, volume_p2) AS `Volume`, signa AS `Sig.` FROM `prescription` WHERE pet_id = '"+pet_id.getText()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable6.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void autoviewMEdicalHistory(){
        try{
            String sql = "SELECT DISTINCT CONCAT_WS(' ', day, month, year) AS `Date Visit` FROM medical_history WHERE pet_id = '"+pet_id.getText()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void getOwnerNumbers(){
        try{
            String sql = "SELECT * FROM `owner` WHERE owner_id = '"+owner_id.getText()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
             String num1 = rs.getString("contact_number_1");
             String num2 = rs.getString("contact_number_2");
             
             jComboBox5.addItem(num1);
             
             if(num2.equals("")){
                 
             }else{
                 jComboBox5.addItem(num2);
             }
             
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

        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        owner_id = new javax.swing.JLabel();
        pet_id = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Pet Chart");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(1, 1, 1));
        jComboBox1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Type Pet Owner's Last Name", 0, 0, null, new java.awt.Color(1, 1, 1)));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jComboBox1KeyTyped(evt);
            }
        });

        jList1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Choose Pet", 0, 0, null, new java.awt.Color(1, 1, 1)));
        jList1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        owner_id.setText("jLabel2");

        pet_id.setText("jLabel3");

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton1.setText("Proceed to Pet Chart");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    DefaultListModel dimy = new DefaultListModel();
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        getPet();
        getOwnerID();
        if(jComboBox1.getSelectedItem().toString().equals("")){
                String ww = "";
                dimy.addElement(ww);
                jList1.setModel(dimy);
            }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyReleased

    }//GEN-LAST:event_jComboBox1KeyReleased

    private void jComboBox1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyTyped

    }//GEN-LAST:event_jComboBox1KeyTyped

    private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed

    }//GEN-LAST:event_jComboBox1KeyPressed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
       getPetID();
    }//GEN-LAST:event_jList1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //Main_workstaion f = new Main_workstaion(); f.setVisible(true);
        main_workstation f = new main_workstation(); f.setVisible(true);
        ////
        pet_chart cc = new pet_chart();
        workstation_tab.addTab("Pet Chart", cc);
        int xq = workstation_tab.getTabCount();
        workstation_tab.setSelectedIndex(xq-1);
        ///
        
        ownerID = owner_id.getText();
        owner_name.setText(jComboBox1.getSelectedItem().toString());
        
        petid = pet_id.getText();
        ownerid = owner_id.getText();
        
        int petid = Integer.parseInt(pet_id.getText());
        
        NumberFormat nf3 = new DecimalFormat("0000");
 
        pet_number.setText(nf3.format(petid));
        pet_name.setText(jList1.getSelectedValue().toString());
        pet_type.setText(type);
        pet_gender.setText(gender);
        owner_address.setText(address);
        pet_breed.setText(breed);
        
        autoviewImage();
        
        autoShowHistory();
        
        autoShow();
        
        autoViewPhEx();
        
        autoviewPhex();
        
        autoviewPrcription();
        
        autoviewPrcription2();
        
        autoviewMEdicalHistory();
        
        getOwnerNumbers();
        
        emr.viewSavedAppointments();
        
        if(appointmentcall==1){
           jTabbedPane1.setSelectedIndex(6);
           appointmentcall=0;
        }
        
        if(itempurchasecall==1){
           new purchase_item().setVisible(true);
           itempurchasecall=0;
        }
        
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Are you sure you want to close?",
            "Closing Pet Chart", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            this.dispose();
            new home_vet().setVisible(true);
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
            java.util.logging.Logger.getLogger(checkup_p1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(checkup_p1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(checkup_p1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(checkup_p1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new checkup_p1().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton jButton1;
    public static javax.swing.JComboBox jComboBox1;
    public static javax.swing.JList jList1;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel owner_id;
    public static javax.swing.JLabel pet_id;
    // End of variables declaration//GEN-END:variables
String filename=null;
int s=0;
byte[] person_image=null;
}
