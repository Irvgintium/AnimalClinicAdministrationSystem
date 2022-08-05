/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.jpanels;

import audit_trailing.audit_trail;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import login.connection_db;
import static home.home_vet.jMenu3;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author irv
 */
public class vet_order_confirm_frame extends javax.swing.JFrame {
    
    Connection conn_db = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    String orderid;
    
    String userid;
    String ordernum;
    String supplierid;
    String classificationid;
    String itemid;
    String orderday;
    String ordermonth;
    String orderyear;
    String orderqty;
    String kind;
    String unitprice;
    String note;
    
    String itemid2;
    /**
     * Creates new form vet_order_confirm_frame
     */
    public vet_order_confirm_frame() {
        initComponents();
        conn_db = connection_db.ConnectDB();
        //for centered frame////
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        //
        autoViewOrderList();
        autoViewConfirmedOrderList();
        
        jButton1.setEnabled(false);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
    }
    
    public void autoViewOrderList(){
        try{
            String sql = "SELECT order_item.order_id as 'ID',  order_item.order_num as 'Order Number', supplier.name as 'Supplier' from order_item inner join supplier on supplier.supplier_id = order_item.supplier_id";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
         
         jTable1.getColumnModel().getColumn(0).setPreferredWidth(20);
         jTable1.getColumnModel().getColumn(0).setMinWidth(50);
         jTable1.getColumnModel().getColumn(0).setMaxWidth(50);
         jTable1.getColumnModel().getColumn(0).setMaxWidth(50);
    }
    
    public void autoViewConfirmedOrderList(){
        try{
           String sql = "SELECT order_item_confirm.order_num as 'Order Number', supplier.name as 'Supplier' from order_item_confirm inner join supplier on supplier.supplier_id = order_item_confirm.supplier_id";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable3.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void ConfirmAllOrders(){
        DefaultTableModel model = (DefaultTableModel) this.jTable1.getModel();
        int[] rows = jTable1.getSelectedRows();
        for(int i=0;i<rows.length;i++){
            
            orderid = model.getValueAt(rows[i], 0).toString();
            
            getAllOrderData();
            
            int countr = jTable1.getRowCount();
            if(countr==0){
                jButton1.setEnabled(false);
                jButton2.setEnabled(false);
            }else{
                jButton1.setEnabled(false);
                jButton2.setEnabled(false);
            }
        }
        
        JOptionPane.showMessageDialog(rootPane, "Sucessfully confirmed order(s)","Success",JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void checkifmultipleSelection(){
        int[] rows = jTable1.getSelectedRows();
        if(rows.length>1){
            jButton3.setEnabled(true);
            jButton1.setEnabled(false);
            removePrescriptionRows();
        }else{
            jButton3.setEnabled(false);
            jButton1.setEnabled(true);
        }
    }
    
    public void showItemOrdered(){
         DefaultTableModel model4 = (DefaultTableModel) jTable1.getModel();
         String ordernum = model4.getValueAt(jTable1.getSelectedRow(), 1).toString();
          try{
            String sql = "SELECT item.name as `Item Name`, UPPER(CONCAT_WS(' ', order_item.order_day,order_item.order_month,order_item.order_year)) as Date, order_qty as `Qty.`  FROM `order_item` inner join item on order_item.item_id = item.item_id where order_num = '"+ordernum+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
      }
    
    public void showItemOrderedForConfirmedOrders(){
         DefaultTableModel model4 = (DefaultTableModel) jTable3.getModel();
         String ordernum = model4.getValueAt(jTable3.getSelectedRow(), 0).toString();
          try{
            String sql = "SELECT item.name as `Item Name`, UPPER(CONCAT_WS(' ', order_item_confirm.order_day,order_item_confirm.order_month,order_item_confirm.order_year)) as Date, order_qty as `Qty.`  FROM `order_item_confirm` inner join item on order_item_confirm.item_id = item.item_id where order_num = '"+ordernum+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
      }
    
    public void removePrescriptionRows(){
        DefaultTableModel model = (DefaultTableModel) this.jTable2.getModel();
        int rows = jTable2.getRowCount();
        for(int i=0;i<rows-1;i++){
          model.removeRow(i);
        }
    }
    
    public void insertToOrderConfirm(){
        try{
            String sql3 = "insert into order_item_confirm (user_id,order_num, supplier_id, classification_id, item_id, order_day, order_month, order_year, order_qty, kind, unit_price, note)values(?,?,?,?,?,?,?,?,?,?,?,?)";
            ps=conn_db.prepareStatement(sql3);
           
            ps.setString(1, userid);
            ps.setString(2, ordernum);
            ps.setString(3, supplierid);
            ps.setString(4, classificationid);
            ps.setString(5, itemid);
            ps.setString(6, orderday);
            ps.setString(7, ordermonth);
            ps.setString(8, orderyear);
            ps.setString(9, orderqty);
            ps.setString(10, kind);
            ps.setString(11, unitprice);
            ps.setString(12, note);
            ps.execute();
            
            System.out.println("Order #"+ordernum+" has been successfully saved to order_item_confirm");

        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void removeToOrderItem(){
        try{
            String sql = "DELETE FROM order_item WHERE order_id = '"+orderid+"'";
            ps = conn_db.prepareStatement(sql);
            ps.executeUpdate();
            
            audit_trail au = new audit_trail();
            au.action_type = "Confirmed - order item # "+ordernum+" by "+jMenu3.getText();
            au.module_name = "Iventory";
            au.saveAuditTRail();
            }
            
            catch(Exception e){     
                System.out.println(e);
            }
    }
    
    public void getAllOrderData(){
        try{
                    String sql = "SELECT * FROM order_item where order_id ='"+orderid+"'";
                    ps = conn_db.prepareStatement(sql);
                    rs = ps.executeQuery();
                        while(rs.next()){
                            System.out.println(rs.getString("order_num")+" "+rs.getString("supplier_id"));
                            userid = rs.getString("user_id");
                            ordernum = rs.getString("order_num");
                            supplierid = rs.getString("supplier_id");
                            classificationid = rs.getString("classification_id");
                            itemid = rs.getString("item_id");
                            orderday = rs.getString("order_day");
                            ordermonth = rs.getString("order_month");
                            orderyear = rs.getString("order_year");
                            orderqty = rs.getString("order_qty");
                            kind = rs.getString("kind");
                            unitprice = rs.getString("unit_price");
                            note = rs.getString("note");
                            
                            insertToOrderConfirm();
                            
                            removeToOrderItem();
                            
                        }
                }catch(Exception e){
                    System.out.println(e);
                }
    }
    
    public void UndoConfirmedOrder(){
        DefaultTableModel model4 = (DefaultTableModel) jTable3.getModel();
        String ordernum = model4.getValueAt(jTable3.getSelectedRow(), 0).toString();
        
        try{
                    String sql = "SELECT * FROM order_item_confirm where order_num ='"+ordernum+"'";
                    ps = conn_db.prepareStatement(sql);
                    rs = ps.executeQuery();
                        while(rs.next()){
                             itemid2 = rs.getString("order_id");
                            
                            ////////////////////////////////////////////
                            try{
                                String sql2 = "SELECT * FROM order_item_confirm where order_id ='"+itemid2+"'";
                                ps = conn_db.prepareStatement(sql2);
                                rs = ps.executeQuery();
                                    while(rs.next()){
                                        userid = rs.getString("user_id");
                                        ordernum = rs.getString("order_num");
                                        supplierid = rs.getString("supplier_id");
                                        classificationid = rs.getString("classification_id");
                                        itemid = rs.getString("item_id");
                                        orderday = rs.getString("order_day");
                                        ordermonth = rs.getString("order_month");
                                        orderyear = rs.getString("order_year");
                                        orderqty = rs.getString("order_qty");
                                        kind = rs.getString("kind");
                                        unitprice = rs.getString("unit_price");
                                        note = rs.getString("note");
                                    }
                            }catch(Exception e){
                                JOptionPane.showMessageDialog(null, e);
                            }
                            ////////////////////////////////////////////insert into order item
                            
                            try{
                                String sql3 = "insert into order_item (user_id,order_num, supplier_id, classification_id, item_id, order_day, order_month, order_year, order_qty, kind, unit_price, note)values(?,?,?,?,?,?,?,?,?,?,?,?)";
                                ps=conn_db.prepareStatement(sql3);

                                ps.setString(1, userid);
                                ps.setString(2, ordernum);
                                ps.setString(3, supplierid);
                                ps.setString(4, classificationid);
                                ps.setString(5, itemid);
                                ps.setString(6, orderday);
                                ps.setString(7, ordermonth);
                                ps.setString(8, orderyear);
                                ps.setString(9, orderqty);
                                ps.setString(10, kind);
                                ps.setString(11, unitprice);
                                ps.setString(12, note);
                                ps.execute();

                                System.out.println("Order #"+ordernum+" has been successfully saved to order_item");

                            }catch(Exception e){
                                JOptionPane.showMessageDialog(null, e) ;
                            }
                            
                            //////////////////////////////////////////delete from order item confirm
                            
                            deletefromitemconfirm();
                            
                            
                        }
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
        
                JOptionPane.showMessageDialog(rootPane, "Confirmed order has been sucessfully undone", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void deletefromitemconfirm(){
                try{
                                String sql3 = "DELETE FROM order_item_confirm WHERE order_id = '"+itemid2+"'";
                                ps = conn_db.prepareStatement(sql3);
                                ps.executeUpdate();

                                audit_trail au = new audit_trail();
                                au.action_type = "Undone - order item # "+ordernum+" by "+jMenu3.getText();
                                au.module_name = "Iventory";
                                au.saveAuditTRail();
                            }

                            catch(Exception e){     
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

        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Order Confirmations");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jToolBar1.setRollover(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Clear Green Button.png"))); // NOI18N
        jButton1.setText("Confirm Order");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Clear Green Button.png"))); // NOI18N
        jButton3.setText("Confirm Selected Orders");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_interface/backup_green_button.png"))); // NOI18N
        jButton2.setText("Undo Confirmation");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

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

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Ordered Items");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Confirmed Orders");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Order List");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 317, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(281, 281, 281))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        jButton1.setEnabled(true);
        checkifmultipleSelection();
        showItemOrdered();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jButton1.setEnabled(false);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        ConfirmAllOrders();
        autoViewOrderList();
        autoViewConfirmedOrderList();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jButton1.setEnabled(false);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        UndoConfirmedOrder();
        autoViewConfirmedOrderList();
        autoViewOrderList();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        jButton1.setEnabled(false);
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        ConfirmAllOrders();
        autoViewOrderList();
        autoViewConfirmedOrderList();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        jButton1.setEnabled(true);
        jButton2.setEnabled(true);
        showItemOrderedForConfirmedOrders();
    }//GEN-LAST:event_jTable3MouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Are you sure you want to close?",
            "Order Confirmations", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            dispose();
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
            java.util.logging.Logger.getLogger(vet_order_confirm_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vet_order_confirm_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vet_order_confirm_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vet_order_confirm_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vet_order_confirm_frame().setVisible(true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
