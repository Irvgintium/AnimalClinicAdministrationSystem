/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maintenance.jpanels;

import com.toedter.calendar.JCalendar;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import login.connection_db;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class services extends javax.swing.JPanel {

    Connection conn_db = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
  String getServices;
  String cid;
  String day;
  String month;
  String year;
  
  
   
    
    public services() {
        initComponents();
        conn_db = connection_db.ConnectDB();
        
         try{
         String sql1 = "select * from services_classifications";
         ps = conn_db.prepareStatement(sql1);
         rs = ps.executeQuery();
         
         while(rs.next()) {
             String getService_Name = rs.getString("classification_name");
             services_cmb.addItem(getService_Name);
         }
     }
     catch (Exception e) {
         JOptionPane.showMessageDialog(null, e);}
    }
    
    public void getSrviceID(){
        try{
            String sql = "select * from services_data where name ='"+serv_name.getText()+"' and classification ='"+services_cmb.getSelectedItem().toString()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getID = rs.getString("service_id");
                cid = getID;
            }
        
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
    }
    
}
    public void container(){
        services_cmb.getSelectedItem();
        serv_name.getText();
        price.getText();
    
}
      public void autodate(){
    
        JCalendar dateset = new JCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
        String dob = dateFormat.format(dateset.getDate());
        date.setText(dob);
        
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
        serv_name = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        stab = new javax.swing.JTable();
        price = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        update = new javax.swing.JButton();
        archive = new javax.swing.JButton();
        save = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        services_cmb = new javax.swing.JComboBox();
        message1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jLabel6.setText("Php");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, 40, 30));

        serv_name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        serv_name.setForeground(new java.awt.Color(51, 102, 0));
        serv_name.setText("*Service Name");
        serv_name.setToolTipText("Service Name");
        serv_name.setBorder(javax.swing.BorderFactory.createTitledBorder("Service Name"));
        serv_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                serv_nameMouseClicked(evt);
            }
        });
        serv_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serv_nameActionPerformed(evt);
            }
        });
        serv_name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                serv_nameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                serv_nameFocusLost(evt);
            }
        });
        add(serv_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 180, 50));

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel7.setText("Date");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, -1, -1));

        stab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Service Classification", "Service Name", "Service Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        stab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stabMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(stab);
        stab.getColumnModel().getColumn(0).setResizable(false);
        stab.getColumnModel().getColumn(1).setResizable(false);
        stab.getColumnModel().getColumn(2).setResizable(false);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 150, 490, 280));

        price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceActionPerformed(evt);
            }
        });
        price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                priceKeyTyped(evt);
            }
        });
        add(price, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, 100, 30));

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel8.setText("Price");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, 60, -1));

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
        add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 440, 110, 30));

        archive.setText("Archive");
        add(archive, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 440, 110, 30));

        save.setText("Save");
        save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveMouseClicked(evt);
            }
        });
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 440, 110, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel9.setText("Service Name");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, -1, -1));

        services_cmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        services_cmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                services_cmbActionPerformed(evt);
            }
        });
        add(services_cmb, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 180, 30));

        message1.setText(" ");
        add(message1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 60, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel10.setText("Service Classification");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel11.setText("Service Classification");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, -1));

        date.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 250, 32));
    }// </editor-fold>//GEN-END:initComponents

    private void serv_nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_serv_nameMouseClicked

    }//GEN-LAST:event_serv_nameMouseClicked

    private void serv_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serv_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_serv_nameActionPerformed

    private void serv_nameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_serv_nameFocusGained
        if(serv_name.getText().equals("*Service Name")){
            serv_name.setText("");
            serv_name.setForeground(Color.BLACK);
        }else{

        }
    }//GEN-LAST:event_serv_nameFocusGained

    private void serv_nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_serv_nameFocusLost
        if(serv_name.getText().equals("")){
            serv_name.setText("*Service Name");
            serv_name.setForeground(new Color(51,102,0));
        }else{
        }
    }//GEN-LAST:event_serv_nameFocusLost

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        save.setEnabled(true);
        update.setEnabled(false);
        
        String str = date.getText();
            String[] parts = str.split(" ");
            String p1 = parts[0]; // 004
            String p2 = parts[1]; // 034556
            String p3 = parts[2]; // 034556

            day = p1;
            month = p2;
            year = p3;
        
        message1.setText("");

            update.setEnabled(true);
            DefaultTableModel model=(DefaultTableModel) stab.getModel();
        model.addRow(new Object[] {services_cmb.getSelectedItem().toString(),serv_name.getText(), price.getText()});
        
         try{
            String oldpricedefault = "0";
//            String defaultday = "0";
//            String defaultmonth = " ";
//            String defaultyear = "0";
            String sql2 = "insert into services_data (classification,name,prices,old_price, day_updated, month_updated, year_updated)values(?,?,?,?,?,?,?)";
            ps=conn_db.prepareStatement(sql2);
           
            ps.setString(1, services_cmb.getSelectedItem().toString());
            ps.setString(2, serv_name.getText());
            ps.setString(3, price.getText());
            ps.setString(4, oldpricedefault);
            ps.setString(5, day);
            ps.setString(6, month);
            ps.setString(7, year);
           
            
           
                  ps.execute();
            
            JOptionPane.showMessageDialog(null, "Services Successfully Saved!");
            
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e) ;
        }
        
        
        
//    }
        update.setEnabled(true);
         serv_name.setText("");
            price.setText("");
            services_cmb.setSelectedIndex(0);
         
    }//GEN-LAST:event_saveActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        
        try{
            String sql="UPDATE services_data SET name=?, prices=?, classification=? where service_id= ? ";         
            ps=conn_db.prepareStatement(sql);
            
            ps.setString(1, serv_name.getText());
            ps.setString(2, price.getText());
            ps.setString(3, services_cmb.getSelectedItem().toString());
            ps.setString(4, cid);
   
            ps.executeUpdate();
            
             JOptionPane.showMessageDialog(null, "Services Successfully Updated!");
            
        }
            catch(Exception e){
            
            JOptionPane.showMessageDialog(null, e);
            }
         DefaultTableModel model=(DefaultTableModel) stab.getModel();
        model.setValueAt(services_cmb.getSelectedItem().toString(),stab.getSelectedRow(), 0);
        model.setValueAt(serv_name.getText(), stab.getSelectedRow(), 1);
        model.setValueAt(price.getText(), stab.getSelectedRow(), 2);
        
         serv_name.setText("");
            price.setText("");
              services_cmb.setSelectedIndex(0);
    }//GEN-LAST:event_updateActionPerformed

    private void updateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateMouseClicked

        
        
    }//GEN-LAST:event_updateMouseClicked

    private void stabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stabMouseClicked
        DefaultTableModel model=(DefaultTableModel) stab.getModel();
        services_cmb.setSelectedItem((String) model.getValueAt(stab.getSelectedRow(), 0));
        serv_name.setText((String) model.getValueAt(stab.getSelectedRow(), 1));
        price.setText((String) model.getValueAt(stab.getSelectedRow(), 2));
        
        getSrviceID();
    }//GEN-LAST:event_stabMouseClicked

    private void services_cmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_services_cmbActionPerformed

        String Y = services_cmb.getSelectedItem().toString();
        Y= getServices;
        
    }//GEN-LAST:event_services_cmbActionPerformed

    private void priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceActionPerformed

    private void priceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_priceKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)){
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_priceKeyTyped

    private void saveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_saveMouseClicked
    
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
            java.util.logging.Logger.getLogger(services.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(services.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(services.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(services.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new services().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton archive;
    private javax.swing.JLabel date;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel message1;
    private javax.swing.JTextField price;
    private javax.swing.JButton save;
    private javax.swing.JTextField serv_name;
    private javax.swing.JComboBox services_cmb;
    private javax.swing.JTable stab;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
