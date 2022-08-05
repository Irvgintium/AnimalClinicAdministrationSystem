/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emr.jpanels;

import audit_trailing.audit_trail;
import emr.jpanels.SQLClass.emr_codes;
import emr.jpanels.SQLClass.view_ddeo;
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
import static emr.jpanels.pet_chart.today_date;
import static emr.jpanels.pet_chart.petid;
import static emr.jpanels.pet_chart.pet_name;
import static emr.jpanels.examinations.pr;
/**
 *
 * @author irv
 */
public class services_mini_frame extends javax.swing.JFrame {
    
    Connection conn_db = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    view_ddeo x = new view_ddeo();
    
    String check = "no";
    
    int count = 0;
    
    public static String intializationcheck = "";
    
    String day;
    String month;
    String year;
    /**
     * Creates new form services_mini_frame
     */
    public services_mini_frame() {
        initComponents();

        conn_db = connection_db.ConnectDB();

        //for centered frame////
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        //
        
        fillClassification();
        
        jComboBox2.setSelectedItem(pr.toUpperCase());
        
    }
    
     public void fillClassification(){
        try{
            String sql = "SELECT * FROM `services_classifications`";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
             String getClassification = rs.getString("classification_name");
             jComboBox2.addItem(getClassification);
             
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void fillservicelist(){
        DefaultListModel dimx = new DefaultListModel();
        try{
            String putincb = "select * from services_data where classification ='"+jComboBox2.getSelectedItem().toString()+"'";
            ps = conn_db.prepareStatement(putincb);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getname = rs.getString("name");
                dimx.addElement(getname);
                jList1.setModel(dimx);
            }
            
        }catch(Exception e){     
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    public void RemoveSelectedServices(){
        ((DefaultListModel) jList2.getModel()).clear();
        fillservicelist();
    }
    
    public void SaveAcquiredServices(){
        DefaultListModel dimx = new DefaultListModel();
        String acqname = "";
        String acqprice = "";
        
        int s = jList2.getModel().getSize();
        int f = s-1;
        
        for(int x = 0; x<=f; x++){
            
            /////////////////////////////////////////////////////////////////////////////////////////////////////////
            try{
                String putincb = "SELECT * FROM `services_data` WHERE name = '"+jList2.getModel().getElementAt(x)+"'";
                ps = conn_db.prepareStatement(putincb);
                rs = ps.executeQuery();

                while(rs.next()){
                    String getacqname = rs.getString("name");
                    acqname = getacqname;
                    String getprice = rs.getString("prices");
                    acqprice = getprice;
                }
                
                ////////////////////////////////////////////////////////////////
                
                String string = today_date.getText();
                String[] parts = string.split(" ");
                String p1 = parts[0]; // 004
                String p2 = parts[1]; // 034556
                String p3 = parts[2]; // 034556

                day = p1;
                month = p2.toUpperCase();
                year = p3;


                try {
                    String sql = "Insert into services_acquired (`pet_id`,`acq_name`,`acq_price`,`day`,`month`,`year`,`status`) values (?,?,?,?,?,?,?)";
                    ps = conn_db.prepareStatement(sql);
                    ps.setString(1, petid);
                    ps.setString(2, acqname);
                    ps.setString(3, acqprice);
                    ps.setString(4, day);
                    ps.setString(5, month);
                    ps.setString(6, year);
                    ps.setString(7, "ONGOING");
                    ps.execute();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                
                check = "yes";
                
                ////////////////////////////////////////////////////////////////

                }catch(Exception e){     
                    JOptionPane.showMessageDialog(null, e);
                }
            /////////////////////////////////////////////////////////////////////////////////////////////////////////
        }
        
        dimx.clear();
        jList2.setModel(dimx);
        
        audit_trail au = new audit_trail();
        au.action_type = "Saved - aquired service "+acqname.toUpperCase()+" by "+ pet_name.getText();
        au.module_name = "Pet Chart";
        au.saveAuditTRail();
        
        JOptionPane.showMessageDialog(null, "Services has been sucessfully added", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void RemoveSelectedServicesonDB(){
        DefaultListModel dimx = new DefaultListModel();
        int s = jList2.getModel().getSize();
        int f = s-1;
        
        for(int x = 0; x<=f; x++){   
            try{
            String putincb = "DELETE FROM `acas_db`.`services_acquired` WHERE `services_acquired`.`acq_name` = '"+jList2.getModel().getElementAt(x)+"' AND services_acquired.pet_id = '"+petid+"'";
            ps = conn_db.prepareStatement(putincb);
            ps.executeUpdate();
            
            audit_trail au = new audit_trail();
            au.action_type = "Cancelled - aquired service "+jList2.getModel().getElementAt(x)+" by "+ pet_name.getText();
            au.module_name = "Pet Chart";
            au.saveAuditTRail();
            
            }
            
            catch(Exception e){     
                JOptionPane.showMessageDialog(null, e);
            }
        }
        JOptionPane.showMessageDialog(null, "Services acquired has been removed");
        dimx.clear();
        jList2.setModel(dimx);
        
    }
    
    public void SetUpdateAcqSrv(){
        DefaultListModel dimx = new DefaultListModel();
        try{
                String putincb = "SELECT * FROM `services_acquired` WHERE pet_id = '"+petid+"' ";
                ps = conn_db.prepareStatement(putincb);
                rs = ps.executeQuery();

                while(rs.next()){
                    String getacqname = rs.getString("acq_name");
                    dimx.addElement(getacqname);
                    jList2.setModel(dimx);
                }

                }catch(Exception e){     
                    JOptionPane.showMessageDialog(null, e);
                }
    }
    
    public void FillCBwithClassification(){
        try{
            String putincb = "SELECT * FROM `classification1` where used_for = 'CHECKUP' OR used_for = 'FOODS-CHECKUP-GROOMING'";
            ps = conn_db.prepareStatement(putincb);
            rs = ps.executeQuery();
            while(rs.next()){
                String getClassName = rs.getString("name");
                jComboBox2.addItem(getClassName);
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

        jPanel8 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox();
        jScrollPane10 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton14 = new javax.swing.JButton();
        jToolBar6 = new javax.swing.JToolBar();
        jButton7 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Choose Services Acquire");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Acquire Service(s)"));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel8.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 29, 310, -1));

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jList1MouseEntered(evt);
            }
        });
        jScrollPane10.setViewportView(jList1);

        jPanel8.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 310, 220));

        jButton14.setText("Show");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 100, -1));

        jToolBar6.setRollover(true);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Clear Green Button.png"))); // NOI18N
        jButton7.setText("Done");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jToolBar6.add(jButton7);

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Write Document.png"))); // NOI18N
        jButton15.setText("View All");
        jButton15.setFocusable(false);
        jButton15.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jToolBar6.add(jButton15);

        jButton10.setText("Add");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton13.setText("Remove");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Selected Service(s)"));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList2MouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(jList2);

        jPanel9.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 300, 280));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 990, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jToolBar6, javax.swing.GroupLayout.PREFERRED_SIZE, 990, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(70, 70, 70)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(20, 20, 20)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jToolBar6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(10, 10, 10)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(30, 30, 30)
                                    .addComponent(jButton13))
                                .addComponent(jButton10)))
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        jButton14.setEnabled(true);
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        jButton10.setEnabled(true);
        jButton14.setEnabled(false);
    }//GEN-LAST:event_jList1MouseClicked

    private void jList1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseEntered

    }//GEN-LAST:event_jList1MouseEntered

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        fillservicelist();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        int s = jList2.getModel().getSize();
        if(s==0){
            JOptionPane.showMessageDialog(null, "You haven't selected any service(s) yet");
        }else{
            SaveAcquiredServices();
            jButton7.setEnabled(false);
            x.viewinit();
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        if(count==0){
            count++;
            jButton15.setText("Update");
            jButton7.setEnabled(false);
            jButton10.setEnabled(false);
            jButton13.setEnabled(true);
            SetUpdateAcqSrv();
        }else{
            count--;
            DefaultListModel dimx = new DefaultListModel();
            dimx.removeAllElements();
            jList2.setModel(dimx);
            jButton15.setText("View All");
            jButton7.setEnabled(true);
            jButton10.setEnabled(false);
            jButton13.setEnabled(false);
        }
    }//GEN-LAST:event_jButton15ActionPerformed
    DefaultListModel<String> modelx = new DefaultListModel();
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        modelx.addElement(jList1.getModel().getElementAt(jList1.getSelectedIndex()).toString());
        jList2.setModel(modelx);
        ((DefaultListModel) jList1.getModel()).remove(jList1.getSelectedIndex());
        jButton7.setEnabled(true);
        jButton10.setEnabled(false);
        jButton13.setEnabled(false);

        int size = jList2.getModel().getSize();
        int f = size-1;

        for(int x=1; x<=f; x++){
            if(jList2.getModel().getElementAt(f).toString().equals(jList2.getModel().getElementAt(x-1).toString())){
                //JOptionPane.showMessageDialog(null,"Redundancy of symptoms detected!");
                modelx.removeElementAt(f);
                f--;
            }else{
                //JOptionPane.showMessageDialog(null,"Add pa more! ");
            }
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        if(jButton15.getText().equals("Update")){
            if ( JOptionPane.showConfirmDialog(new JFrame(),
                "Are you sure you want to remove from acquired services?",
                "Removing Acquired Services", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            RemoveSelectedServicesonDB();
            jButton15.setText("View All");

        }
        else {

            System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
            return;
        }

        }else{
            jButton10.setEnabled(false);
            jButton13.setEnabled(false);
            RemoveSelectedServices();
        }

    }//GEN-LAST:event_jButton13ActionPerformed

    private void jList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseClicked
        jButton10.setEnabled(false);
        jButton13.setEnabled(true);
    }//GEN-LAST:event_jList2MouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if(check.equals("no")){
            if (JOptionPane.showConfirmDialog(new JFrame(),
            "No Acquired Services Detected.\nAre you sure you want to close?",
            "Validation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                dispose();
            }
            else {

                System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
                return;
            }
        }else{
            dispose();
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
            java.util.logging.Logger.getLogger(services_mini_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(services_mini_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(services_mini_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(services_mini_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new services_mini_frame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JToolBar jToolBar6;
    // End of variables declaration//GEN-END:variables
}
