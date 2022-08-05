/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.jpanels;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import login.connection_db;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class beginning_inventory extends javax.swing.JFrame {
    
    Connection conn_db = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    String getItemId;
    /**
     * Creates new form beginning_iventory
     */
    public beginning_inventory() {
        initComponents();
        
        conn_db = connection_db.ConnectDB();
        
        fillCB2withclassification();
        
        refreshAndviewTable();

        //for centered frame////
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);   
        //
        
        update.setEnabled(false);
    }
    
    ////////Code for adding . to double//////////////////////////////////////////////////////////////////////////////////////////////////
    
    private boolean isNumber(char ch){
        return ch >= '0' && ch <= '9';
    }

    private boolean isValidSignal(char ch){
        if( (jTextField1.getText() == null || "".equals(jTextField1.getText().trim()) ) && ch == '-'){
            return true;
        }

        return false;
    }

    private boolean validatePoint(char ch){
        if(ch != '.'){
            return false;
        }

        if(jTextField1.getText() == null || "".equals(jTextField1.getText().trim())){
            jTextField1.setText("0.");
            return false;
        }else if("-".equals(jTextField1.getText())){
            jTextField1.setText("-0.");
        }

        return true;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void fillCB2withclassification(){
        try{
            String sql1 ="select * from classification1";
            ps=conn_db.prepareStatement(sql1);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getClassificationName = rs.getString("name");
                jComboBox2.addItem(getClassificationName);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void viewSpecificItem(){
        
        DefaultListModel<String> model = new DefaultListModel();
        jList1.setModel(model);
        int rows = model.size(); 
        for(int i = rows - 1; i >=0; i--)
        {
           model.removeElementAt(i); 
        }
        
        DefaultListModel dimx = new DefaultListModel();
        try{
            String sql1 ="SELECT * FROM `item` INNER JOIN classification1 ON `item`.`classification_id` = `classification1`.`classification_id` WHERE classification1.name = '"+jComboBox2.getSelectedItem().toString()+"'";
            ps=conn_db.prepareStatement(sql1);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getClassificationName = rs.getString("name");
                String getItemId = rs.getString("item.item_id");
                iID.setText(getItemId);
                String getClassId = rs.getString("classification1.classification_id");
                cID.setText(getClassId);
                dimx.addElement(getClassificationName);
                jList1.setModel(dimx);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void getIDs(){
        try{
            String sql1 ="SELECT * FROM `item` INNER JOIN classification1 ON `item`.`classification_id` = `classification1`.`classification_id` WHERE item.name = '"+jList1.getSelectedValue().toString()+"'";
            ps=conn_db.prepareStatement(sql1);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getClassificationName = rs.getString("name");
                String getItemId = rs.getString("item.item_id");
                iID.setText(getItemId);
                String getClassId = rs.getString("classification1.classification_id");
                cID.setText(getClassId);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
 
    public void updateItemQTY(){
        try{
            String sql = "UPDATE item SET quantity = ?, kind=? where item_id ='"+iID.getText()+"' and name = '"+jLabel7.getText()+"'";
            ps=conn_db.prepareStatement(sql);
            
            ps.setString(1, jTextField2.getText());
            ps.setString(2, jComboBox1.getSelectedItem().toString());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Successfully updated");
            

        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void saveInItemPrice(){
        String string3 = jLabel5.getText();
        String[] parts3 = string3.split(" ");
        String p1 = parts3[0]; // 004
        String p2 = parts3[1]; // 034556
        try{
            String sql = "Insert into item_price (item_id,price) values (?,?)";
            ps=conn_db.prepareStatement(sql);
            ps.setString(1, iID.getText());
            ps.setString(2, jTextField1.getText());
            ps.execute();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    ///
    
    public void refreshAndviewTable(){
        DefaultTableModel dd=(DefaultTableModel) jTable1.getModel();
        int rows = dd.getRowCount(); 
        ////clearJlistCode
        for(int i = rows - 1; i >=0; i--)
        {
           dd.removeRow(i); 
        }
        try{
            String sql1 ="SELECT item.name, item_price.price, item.quantity from item INNER JOIN item_price ON item.item_id = item_price.item_id";
            ps=conn_db.prepareStatement(sql1);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getItemName = rs.getString("item.name");
                String getItemPrice= rs.getString("item_price.price");
                String getItemQuantity = rs.getString("item.quantity");
                
                DefaultTableModel dm=(DefaultTableModel) jTable1.getModel();
                dm.addRow(new Object[]{getItemName, getItemPrice, getItemQuantity});
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void viewTable(){
        DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
        jLabel7.setText(model.getValueAt(jTable1.getSelectedRow(), 0).toString());
        jTextField2.setText(model.getValueAt(jTable1.getSelectedRow(), 2).toString());
        jTextField1.setText(model.getValueAt(jTable1.getSelectedRow(), 1).toString());
    }
    
    public void getIDtoshow(){
        try{
            String sql1 ="SELECT * FROM `item` INNER JOIN classification1 ON `item`.`classification_id` = `classification1`.`classification_id` WHERE item.name = '"+jLabel7.getText()+"'";
            ps=conn_db.prepareStatement(sql1);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getItemId = rs.getString("item.item_id");
                iID.setText(getItemId);
                String getClassId = rs.getString("classification1.classification_id");
                cID.setText(getClassId); 
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void updateItemPrice(){
        String string3 = jLabel5.getText();
        String[] parts3 = string3.split(" ");
        String p1 = parts3[0]; // 004
        String p2 = parts3[1]; // 034556
        try{
            String sql = "UPDATE item_price SET price = ? where item_id ='"+iID.getText()+"'";
            ps=conn_db.prepareStatement(sql);
            
            ps.setString(1, p2);
            ps.executeUpdate();        

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
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        update = new javax.swing.JButton();
        save = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        cID = new javax.swing.JLabel();
        iID = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Price");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Choose Item");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, -1));

        jButton1.setText("-");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 50, 20));

        jButton2.setText("+");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 50, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Quantity");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        jTextField2.setText("0");
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 90, -1));

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 90, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("0.0");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 100, 20));

        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 190, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Item Name");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 77, 210, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Price", "Quantity"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 50, 250, 170));

        update.setText("Update");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        getContentPane().add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 230, 90, -1));

        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        getContentPane().add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 90, -1));

        jList1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jList1MouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 190, 80));

        cID.setText("jLabel6");
        getContentPane().add(cID, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 10, 0, 20));

        iID.setText("jLabel6");
        getContentPane().add(iID, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 0, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 50, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Choose Item Classification");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("jLabel9");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 0, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pc(s)", "Pack(s)", "Bottle(s)", "Box(s)", "Sachet(s)", "Sack(s)", "" }));
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 140, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 320));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
         if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Are you sure you want to close?",
            "Closing Beginning Inventory", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            dispose();
            
        }
        else {

            System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
            return;
        }
    }//GEN-LAST:event_formWindowClosing

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
       char ch = evt.getKeyChar();

                if (!isNumber(ch) && !isValidSignal(ch) && !validatePoint(ch)  && ch != '\b') {
                    evt.consume();
                }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        if(jTextField1.getText().equals("")){
            double php = 00.00;
            jLabel5.setText(Double.toString(php));
        }else{
                    double php2 = Double.parseDouble(jTextField1.getText());
                    Locale locale = new Locale("ph", "PH");      
                    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
                    jLabel5.setText(currencyFormatter.format(php2));
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)){
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_jTextField2KeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int qty = Integer.parseInt(jTextField2.getText());
        qty = qty+1;
        jTextField2.setText(Integer.toString(qty));
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int qty = Integer.parseInt(jTextField2.getText());
        if(qty<0 || qty==0){
            jTextField2.setText("0");
        }else{
            int qty1 = Integer.parseInt(jTextField2.getText());
            qty1 = qty1-1;
            jTextField2.setText(Integer.toString(qty1));
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        update.setEnabled(true);
        save.setEnabled(false);
        jTextField2.setEditable(false);
        jComboBox1.setEditable(false);
        viewTable();
        getIDtoshow();
    }//GEN-LAST:event_jTable1MouseClicked

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        save.setEnabled(true);
        updateItemQTY();
        updateItemPrice();
        refreshAndviewTable();
        //jTextField2.setEditable(true);
    }//GEN-LAST:event_updateActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        if(jLabel7.getText().equals("Item Name") || jTextField2.getText().equals("0") || jLabel5.getText().equals("0.0")){
            JOptionPane.showMessageDialog(null, "Empty field(s) detected");
        }else{
            updateItemQTY();
            saveInItemPrice();
            JOptionPane.showMessageDialog(null, "Successfully Saved");
            refreshAndviewTable();
        }
    }//GEN-LAST:event_saveActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jList1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseEntered
        viewSpecificItem();
    }//GEN-LAST:event_jList1MouseEntered

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        jTextField1.setText("");
        
        try{
            jLabel7.setText(jList1.getSelectedValue().toString());
            getIDs();
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, e +"\n"+ "There is no item for classification "+jList1.getSelectedValue().toString()+" yet!");
        }
        
        try{
           String sql="select * from item where name ='"+jList1.getSelectedValue().toString() +"'";
             ps=conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getClassificationName = rs.getString("quantity");
                jTextField2.setText(getClassificationName);
            }
        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);

        }
        
        
        try{
           String sql="select * from item_price INNER JOIN item ON `item_price`.`item_id` = `item`.`item_id` where name ='"+jList1.getSelectedValue().toString()+"'";
             ps=conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
             
                 String getClassificationName1 = rs.getString("price");
                jTextField1.setText(getClassificationName1);
            }
        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
       
    }//GEN-LAST:event_jList1MouseClicked

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
            java.util.logging.Logger.getLogger(beginning_inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(beginning_inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(beginning_inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(beginning_inventory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new beginning_inventory().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cID;
    private javax.swing.JLabel iID;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JButton save;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
