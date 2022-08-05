/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package billing.jpanels;

import audit_trailing.audit_trail;
import com.toedter.calendar.JCalendar;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import login.connection_db;
import net.proteanit.sql.DbUtils;
import static emr.jpanels.pet_chart.*;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import other.features.jpanels.customMessage_Screen;

/**
 *
 * @author irv
 */
public class purchase_item extends javax.swing.JFrame {
    
    Connection conn_db = connection_db.ConnectDB();
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    String day;
    String month;
    String year;
    /**
     * Creates new form purchase_item
     */
    public purchase_item() {
        initComponents();
        //for centered frame////
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);   
        //
        disableFirst();
        autoview();
    }
    
    public void autoview(){
        try{
            String sql = "SELECT item_id as `ID`,name as `Item Name`, quantity as `Qty` FROM `item`";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
        
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable2.getColumnModel().getColumn(0).setMinWidth(50);
        jTable2.getColumnModel().getColumn(0).setMaxWidth(70);
        
        jTable2.getColumnModel().getColumn(2).setPreferredWidth(30);
        jTable2.getColumnModel().getColumn(2).setMinWidth(50);
        jTable2.getColumnModel().getColumn(2).setMaxWidth(70);
    }
    
    public void disableFirst(){
        jButton1.setEnabled(false);
        jButton2.setEnabled(false);
    }
    
    public void addToBasket(){
        DefaultTableModel model = (DefaultTableModel) this.jTable2.getModel();
        int x2 = Integer.parseInt(model.getValueAt(jTable2.getSelectedRow(), 2).toString());
        if(Integer.parseInt(jSpinner1.getValue().toString())==0 || x2==0){
            JOptionPane.showMessageDialog(null, "Zero quantity detected", "Validation", JOptionPane.ERROR_MESSAGE);
        }else{
            DefaultTableModel model2 = (DefaultTableModel) jTable1.getModel();
            model2.addRow(new Object[]{jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString(),jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString(),jSpinner1.getValue()});
            autoremoverow();
            
                 int size = jTable1.getRowCount();
                 int f = size-1;
                 
                   for(int x=1; x<=f; x++){
                   if(jTable1.getValueAt(f, 0).toString().equals(jTable1.getValueAt(x-1, 0).toString())){
                       model2.removeRow(f);
                       
                       int one = Integer.parseInt(jTable1.getValueAt(x-1, 2).toString());
                       int two = Integer.parseInt(jSpinner1.getValue().toString());
                       int ans = one+two;
                       
                       model2.setValueAt(ans, x-1, 2);
                       f--;
                   }else{
                        
                    }
                   
                    }
                   
                   jSpinner1.setValue(0);
        }
    }
    
    public void removerows(){
        DefaultTableModel model = (DefaultTableModel) this.jTable1.getModel();
        int[] rows = jTable1.getSelectedRows();
        for(int i=0;i<rows.length;i++){
          model.removeRow(rows[i]-i);
        }
    }
    
    public void autoremoverow(){
        DefaultTableModel model = (DefaultTableModel) this.jTable2.getModel();
        int[] rows = jTable2.getSelectedRows();
        for(int i=0;i<rows.length;i++){
          model.removeRow(rows[i]-i);
        }
    }
    
    public void typeSerach(){
        try{
            String sql = "SELECT item_id as `ID`,name as `Item Name`, quantity as `Qty` FROM `item` WHERE name LIKE '"+jTextField1.getText()+"%'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
        
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable2.getColumnModel().getColumn(0).setMinWidth(50);
        jTable2.getColumnModel().getColumn(0).setMaxWidth(70);
        
        jTable2.getColumnModel().getColumn(2).setPreferredWidth(30);
        jTable2.getColumnModel().getColumn(2).setMinWidth(50);
        jTable2.getColumnModel().getColumn(2).setMaxWidth(70);
    }
    
    public void savetoItemCharged(){ 
        ////set date////
        JCalendar dateset2 = new JCalendar();
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("d MMM yyyy");
        String dob2 = dateFormat2.format(dateset2.getDate());
        
        String stringdate = dob2;
        String[] parts33 = stringdate.split(" ");
        String p11 = parts33[0]; // 004
        String p22 = parts33[1]; // 034556
        String p33 = parts33[2]; // 034556

        day = p11;
        month = p22;
        year = p33;
        
        int size = jTable1.getRowCount();//
        int f = size - 1;

        for (int x = 0; x <= f; x++) {
            
             try {
                    String sql = "Insert into item_charged (`pet_id`,`item_id`,`quantity`,`day`,`month`,`year`) values (?,?,?,?,?,?)";
                    ps = conn_db.prepareStatement(sql);
                    ps.setString(1, petid);
                    ps.setString(2, jTable1.getValueAt(x, 0).toString());
                    ps.setString(3, jTable1.getValueAt(x, 2).toString());
                    ps.setString(4, day);
                    ps.setString(5, month);
                    ps.setString(6, year);
                    ps.execute();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
        }
        
        audit_trail au = new audit_trail();
        au.action_type = "Saved - purchase list information by "+ pet_name.getText();
        au.module_name = "Pet Chart";
        au.saveAuditTRail();
        
        JOptionPane.showMessageDialog(null, "Purchase Item(s) has been sucessfully added to billing", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Client Item Purchase");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel1.setText("Quantity");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item(s)", 0, 0, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Item Name", "Qty"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setMinWidth(50);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(70);
        jTable1.getColumnModel().getColumn(2).setMinWidth(50);
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(2).setMaxWidth(70);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 410, 230));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jToolBar1.setRollover(true);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Clear Green Button.png"))); // NOI18N
        jButton3.setText("Done");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/basket_add.png"))); // NOI18N
        jButton1.setText("Add to client's puchase list");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/basket_delete.png"))); // NOI18N
        jButton2.setText("Remove to client's purchase list");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel2.setText("Type Item Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(84, 84, 84))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        int val = (int) jSpinner1.getValue();
        
        if(val<0){
                jSpinner1.setValue(0);
            }
    }//GEN-LAST:event_jSpinner1StateChanged

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
    }//GEN-LAST:event_jTable2MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        jButton1.setEnabled(false);
        jButton2.setEnabled(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        disableFirst();
        addToBasket();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        disableFirst();
        removerows();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        typeSerach();
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int size = jTable1.getRowCount();//
        if(size == 0){
            JOptionPane.showMessageDialog(null, "No item(s) added to basket yet", "Validation", JOptionPane.ERROR_MESSAGE);
        }else{
            savetoItemCharged();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Are you sure you want to close?",
            "Closing Purchase Item", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
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
            java.util.logging.Logger.getLogger(purchase_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(purchase_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(purchase_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(purchase_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new purchase_item().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton jButton1;
    public static javax.swing.JButton jButton2;
    public static javax.swing.JButton jButton3;
    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JPanel jPanel1;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JSpinner jSpinner1;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JTable jTable2;
    public static javax.swing.JTextField jTextField1;
    public static javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
