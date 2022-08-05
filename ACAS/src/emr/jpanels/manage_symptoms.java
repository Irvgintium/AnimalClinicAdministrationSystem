/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emr.jpanels;

import audit_trailing.audit_trail;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import login.connection_db;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author IrvGu
 */
public class manage_symptoms extends javax.swing.JFrame {
    
    Connection conn_db = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    ///CONTAINERS
    
    String diagnosis;
    String symptom;
    String description;
    
    String DiagnosisCont;
    String contSymp;
    String sdcIDcont;
    
    int count=0;
    int trigger=0;
    /**
     * Creates new form manage_symptoms
     */
    public manage_symptoms() {
        conn_db = connection_db.ConnectDB();
        initComponents();
        jButton2.setEnabled(false);
        viewSaved();
        //for centered frame////
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);   
        //
    }
    
    //************************************************************************************//
    
   public void putintable(){
       DefaultTableModel model2 = (DefaultTableModel) jTable1.getModel();
       model2.addRow(new Object[]{jTextField1_symp.getText().toUpperCase()});
       jTextField1_symp.setText("");
       
       int size = model2.getRowCount();
                int f = size-1;

                for(int x=1; x<=f; x++){
                    if(jTable1.getValueAt(f, 0).toString().equals(jTable1.getValueAt(x-1, 0).toString())){
                        //JOptionPane.showMessageDialog(null,"Magkaparehas!"+"\n"+diagnose.getValueAt(f, 0).toString()+" is equal sa "+diagnose.getValueAt(x-1, 0).toString());
                        model2.removeRow(f);
                        f--;

                    }else{
                         //JOptionPane.showMessageDialog(null,"Add pa more! ");
                     }
                }
   }
    
    public void removerows(){
        DefaultTableModel model = (DefaultTableModel) this.jTable1.getModel();
        int[] rows = jTable1.getSelectedRows();
        for(int i=0;i<rows.length;i++){
          model.removeRow(rows[i]-i);
            int countr = jTable1.getRowCount();
            if(countr==0){
                jButton3.setEnabled(false);
            }else{
                jButton3.setEnabled(true);
            }
        }
    }
    
    public void saveindatabase(){
        int x = jTable1.getRowCount();
        
        if(x==0 || diag.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Empty Field(s) Detected");
        }else{
            DefaultTableModel model2 = (DefaultTableModel) jTable1.getModel();
            int size = model2.getRowCount();
            int f = size-1;
            
            for(int i=0; i<=f; i++){
                 try {
                    String sql = "Insert into auto_suggest_diagnosis_data (`symptom`,`diagnosis`) values (?,?)";
                    ps = conn_db.prepareStatement(sql);
                    ps.setString(1, jTable1.getValueAt(i, 0).toString());
                    ps.setString(2, diag.getText().toUpperCase());
                    ps.execute();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
           }
            viewSaved();
        }
    }
    
    public void viewSaved(){
        DefaultListModel dimx = new DefaultListModel();
         try{
             String sql = "SELECT DISTINCT diagnosis AS `diag` FROM `auto_suggest_diagnosis_data`";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             
             while(rs.next()){
                 String getDiag= rs.getString("diag");
                 String cont = getDiag;
                 dimx.addElement(cont);
                 jList1.setModel(dimx);
             }
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
    }
    
    public void clearFields(){
        jButton2.setEnabled(false);
        jButton1.setEnabled(true);
        diag.setText("");
        jTextField1_symp.setText("");
        trigger = 0;
        DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
        int rows1 = model1.getRowCount();
        for (int i = rows1 - 1; i >= 0; i--) {
            model1.removeRow(i);
        }
        
        try{
             String sql = "SELECT symptom as Symptoms FROM `auto_suggest_diagnosis_data` where diagnosis = ''";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             jTable1.setModel(DbUtils.resultSetToTableModel(rs));         
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
        viewSaved();
    }
    
    public void setUpdate(){
        clearFields();
        jButton2.setEnabled(false);
        jButton1.setEnabled(false);
        trigger = 1;
        
        DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
        int rows1 = model1.getRowCount();
        for (int i = rows1 - 1; i >= 0; i--) {
            model1.removeRow(i);
        }
        try{
             String sql = "SELECT sdc_id as ID, symptom as Symptoms FROM `auto_suggest_diagnosis_data` where diagnosis = '"+jList1.getSelectedValue().toString()+"'";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             jTable1.setModel(DbUtils.resultSetToTableModel(rs));         
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
        
        diag.setText(jList1.getSelectedValue().toString());
        
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(0).setMinWidth(50);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(70);
    }
    
    public void update(){
        jButton2.setEnabled(false);
        jButton1.setEnabled(true);
        
        DefaultTableModel model2 = (DefaultTableModel) jTable1.getModel();
            int size = model2.getRowCount();
            int f = size-1;
            
            for(int i=0; i<=f; i++){ 
                try{
                    String sql = "UPDATE auto_suggest_diagnosis_data SET symptom = UPPER(?), diagnosis = UPPER(?) where sdc_id ='"+jTable1.getValueAt(i, 0).toString()+"'";
                    ps=conn_db.prepareStatement(sql);
                    ps.setString(1, jTable1.getValueAt(i, 1).toString());//stool
                    ps.setString(2, diag.getText().toUpperCase());//cpv
                    ps.executeUpdate();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
           }
            viewSaved();
            clearFields();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        diag = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1_symp = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Symptoms Management");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        diag.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                diagMouseExited(evt);
            }
        });
        diag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diagActionPerformed(evt);
            }
        });
        diag.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                diagFocusLost(evt);
            }
        });
        diag.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                diagKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(72, 56, 56));
        jLabel3.setText("Enter Symptoms/Chief Complaints");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Symptoms"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setResizable(false);

        jTextField1_symp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1_sympActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(72, 56, 56));
        jLabel12.setText("Type a Diagnosis");

        jToolBar1.setRollover(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Clear Green Button.png"))); // NOI18N
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

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_interface/dialog_cancel.png"))); // NOI18N
        jButton3.setText("Remove Row");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_interface/backup_green_button.png"))); // NOI18N
        jButton4.setText("Reset");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(72, 56, 56));
        jLabel13.setText("Saved Diagnosis");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(243, 243, 243)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(diag, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField1_symp, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
            .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(diag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jLabel3)
                        .addGap(6, 6, 6)
                        .addComponent(jTextField1_symp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Are you sure you want to close?",
            "Closing the Symptoms Management", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            dispose();
        }
        else {

            System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
            return;
        }
    }//GEN-LAST:event_formWindowClosing

    private void diagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diagActionPerformed

    }//GEN-LAST:event_diagActionPerformed
    DefaultListModel dim = new DefaultListModel();
    private void diagMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diagMouseExited
        
    }//GEN-LAST:event_diagMouseExited
    DefaultListModel dimo = new DefaultListModel();
    private void diagFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_diagFocusLost
        String x = diag.getText();
        diagnosis = x;

        System.out.println(diagnosis);
    }//GEN-LAST:event_diagFocusLost

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        removerows();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1_sympActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1_sympActionPerformed
        if(trigger ==1){
            jTable1.setValueAt(jTextField1_symp.getText().toUpperCase(), jTable1.getSelectedRow(), 1);
            jTextField1_symp.setText("");
            jLabel3.setText("Enter Symptoms/Chief Complaints");
            jButton2.setEnabled(true);
        }else{
            putintable();
        }
    }//GEN-LAST:event_jTextField1_sympActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        saveindatabase();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        clearFields();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        setUpdate();
    }//GEN-LAST:event_jList1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        update();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        if(trigger==1){
            jTextField1_symp.setText(jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString());
            jLabel3.setText("Hit \'ENTER\" to update");
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void diagKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diagKeyReleased
        if(trigger == 1){
            jButton2.setEnabled(true);
        }
    }//GEN-LAST:event_diagKeyReleased
        DefaultListModel dimx = new DefaultListModel();
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
            java.util.logging.Logger.getLogger(manage_symptoms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(manage_symptoms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(manage_symptoms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(manage_symptoms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new manage_symptoms().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField diag;
    public static javax.swing.JButton jButton1;
    public static javax.swing.JButton jButton2;
    public static javax.swing.JButton jButton3;
    public static javax.swing.JButton jButton4;
    public static javax.swing.JLabel jLabel12;
    public static javax.swing.JLabel jLabel13;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JList jList1;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JScrollPane jScrollPane4;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JTextField jTextField1_symp;
    public static javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
