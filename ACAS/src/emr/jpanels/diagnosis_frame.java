/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emr.jpanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import login.connection_db;
import static emr.jpanels.manage_symptoms.jTextField1_symp;

/**
 *
 * @author irv
 */
public class diagnosis_frame extends javax.swing.JFrame {

    Connection conn_db = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    int finalpoint = 0;
    int totalpoint = 0;
    int totalpoint2 = 0;
    int pointChecker = 0;
    /**
     * Creates new form diagnosis_frame
     */
    public diagnosis_frame() {
        initComponents();
        
        conn_db = connection_db.ConnectDB();
        
        jTextArea1.setVisible(false);
        jButton1.setVisible(false);
        //for centered frame////
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);   
        //
    }
    
    public void autoRecommend(){
         
         int records = 0;
         
         try{
             String sql = "select distinct * from symptoms_diseases_cures where symptom = '"+jTextField1.getText()+"'";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             
             while(rs.next()){
                 
                 records = rs.getInt(1);
                 
                 String getRec = rs.getString("diagnosis");

                 DefaultTableModel model=(DefaultTableModel) diagnosis.getModel();

                 model.addRow(new Object[]{getRec});
                 
                 //for adding points
                 DefaultTableModel modelx=(DefaultTableModel) points.getModel();
                 modelx.addRow(new Object[]{getRec, "1"});
                 //for adding points

                 int size = diagnosis.getRowCount();
                 int f = size-1;
                 
                   for(int x=1; x<=f; x++){
                   if(diagnosis.getValueAt(f, 0).toString().equals(diagnosis.getValueAt(x-1, 0).toString())){
                       //JOptionPane.showMessageDialog(null,"Magkaparehas!"+"\n"+diagnose.getValueAt(f, 0).toString()+" is equal sa "+diagnose.getValueAt(x-1, 0).toString());
                       model.removeRow(f);
                       modelx.removeRow(f);
                       f--;
                       //for adding points
                       int addpoint = Integer.parseInt(points.getValueAt(x-1, 1).toString());
                       addpoint++;
                       points.setValueAt(addpoint, x-1, 1);
                        //for adding points
                   }else{
                        //JOptionPane.showMessageDialog(null,"Add pa more! ");
                    }
                   
                    }

             }
             
             if(records > 0){
                 
                 
                 
             }else{
                 if ( JOptionPane.showConfirmDialog(new JFrame(),
                "Add a corresponding diagnosis to "+jTextField1.getText().toUpperCase()+"?\nThis symptom has no any data yet.\nIf "+jTextField1.getText().toUpperCase()+" has no data, it will not be included on the suggesting of the diagnosis",
                "A new symptom detected!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                     
                 new manage_symptoms().setVisible(true);
                 Font newTextFieldFont=new Font(jTextField1_symp.getFont().getName(),Font.BOLD,jTextField1_symp.getFont().getSize());
                 jTextField1_symp.setFont(newTextFieldFont);
                 jTextField1_symp.setForeground(new Color(51,102,0));
                 jTextField1_symp.setText(jTextField1.getText()); 
                 
                }
                else {
                    System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
                    return;
                }
             }
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
     }
    
    public void finalizeDiagnosis(){
         //for adding points
        DefaultTableModel modelx=(DefaultTableModel) points.getModel();
        int size = modelx.getRowCount();
        int f = size-1;
        //for adding points
        finalpoint = Integer.parseInt(points.getValueAt(0, 1).toString());//base point
        String name = points.getValueAt(0, 0).toString();//base name
        //for loop here//
        for(int x=0; x<=f; x++){
            if(finalpoint <= Integer.parseInt(points.getValueAt(x, 1).toString())){
                finalpoint = Integer.parseInt(points.getValueAt(x, 1).toString());
                name = points.getValueAt(x, 0).toString();
                jLabel3.setText(name);
            }else{
                 finalpoint = finalpoint;
                 jLabel3.setText(name); 
            }
        }
    }
    
    public void getResults(){
        DefaultTableModel modelx=(DefaultTableModel) points.getModel();
         
        int size = modelx.getRowCount();
        int f = size-1;

        int contpoint=0;
        
        for(int x=0; x<=f; x++){
            int getpoints = Integer.parseInt(points.getValueAt(x, 1).toString());
            contpoint = contpoint + getpoints;
            totalpoint = contpoint;
        }
                
        double percentagep1 = ((double)finalpoint/(double)totalpoint);
        
        double percentage = percentagep1 * 100;
        
        double roundedNumber = (double)Math.round(percentage * 10) / 10;
        
        JOptionPane.showMessageDialog(null, "The suggested diagnosis is "+ jLabel3.getText()+ ".\nIt is " +roundedNumber+"% accurate based on the given symptoms");
    }
    
    public void showOtherResults(){
        DefaultTableModel modelx=(DefaultTableModel) points.getModel();
         
        int size = modelx.getRowCount();
        int f = size-1;
        
        for(int x=0; x<=f; x++){
            double getpoints = Double.parseDouble(points.getValueAt(x, 1).toString());
            
            double percentagep1 = ((double)getpoints/(double)totalpoint);
        
            double percentage = percentagep1 * 100;
        
            double roundedNumber = (double)Math.round(percentage * 10) / 10;
        
            jTextArea1.append(points.getValueAt(x, 0).toString() + " is " +roundedNumber+"% accurate based on the given symptoms\n");
        }
    }
    
    public void checkifPointsAreEqual(){
        DefaultTableModel modelx=(DefaultTableModel) points.getModel();
         
        int size = modelx.getRowCount();
        int f = size-1;
        
        int contpoint=0;
        
        for(int x=0; x<=f; x++){
            int getpoint = Integer.parseInt(points.getValueAt(x, 1).toString());
            
             if(getpoint == 1){
                 pointChecker++;
             }
        }
        
        if(pointChecker == size){
            if ( JOptionPane.showConfirmDialog(new JFrame(),
                "Cannot determine which is the accurate diagnosis.\nThe suggesting of final diagnosis will not be accurate.\nDo you still want to proceed the suggestion?",
                "A critical result detected", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                 finalizeDiagnosis();
                 getResults();
                 jButton1.setVisible(true);
                }
                else {
                    System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
                    return;
                }
            }else{
                //JOptionPane.showMessageDialog(null, "Ok. Go on" + pointChecker);
                finalizeDiagnosis();
                getResults();
                jButton1.setVisible(true);
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        symptoms = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        points = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        diagnosis = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        symptoms.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Symtoms"
            }
        ));
        jScrollPane1.setViewportView(symptoms);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 330, 300));

        points.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Diagnosis", "Points"
            }
        ));
        jScrollPane2.setViewportView(points);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 450, 360, 110));

        diagnosis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Diagnosis"
            }
        ));
        jScrollPane3.setViewportView(diagnosis);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 360, 300));

        jTextField1.setBorder(javax.swing.BorderFactory.createTitledBorder("Type a symptom"));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 330, 60));

        jButton2.setText("Get Results");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 130, -1));

        jButton1.setText("More Results");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 130, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Other Results"), "More Results"));
        jScrollPane4.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 140, 550, 300));

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(254, 254, 254));
        jLabel3.setText("jLabel3");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 470, 0, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(254, 254, 254));
        jLabel2.setText("Input Symptoms");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 170, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg.jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 610));

        jTabbedPane1.addTab("Diagnose", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        if(jTextField1.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Empty Field Detected!");
        }else{
            DefaultTableModel model=(DefaultTableModel) symptoms.getModel();
            autoRecommend();
            model.addRow(new Object[]{jTextField1.getText()});jTextField1.setText("");

            int size = model.getRowCount();
            int f = size-1;

            for(int x=1; x<=f; x++){
                if(symptoms.getValueAt(f, 0).toString().equals(symptoms.getValueAt(x-1, 0).toString())){
                    //JOptionPane.showMessageDialog(null,"Magkaparehas!"+"\n"+diagnose.getValueAt(f, 0).toString()+" is equal sa "+diagnose.getValueAt(x-1, 0).toString());
                    model.removeRow(f);
                    f--;

                }else{
                     //JOptionPane.showMessageDialog(null,"Add pa more! ");
                 }
            }
        }
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DefaultTableModel modelx=(DefaultTableModel) points.getModel();
        DefaultTableModel modelxy=(DefaultTableModel) symptoms.getModel();
        int size = modelx.getRowCount();
        int size2 = modelxy.getRowCount();
        
        if(size == 0 || size2 == 0 ){
            JOptionPane.showMessageDialog(null, "You haven't given any symptoms yet.");
        }else{
            if(size2 < 3){
                JOptionPane.showMessageDialog(null, "Accuracy of the diagnosis is not reliable when there is less symtoms given.\nPlease add more than 3");
            }else{
                checkifPointsAreEqual();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jTextArea1.setText("");
        showOtherResults();
        jTextArea1.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(diagnosis_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(diagnosis_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(diagnosis_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(diagnosis_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new diagnosis_frame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable diagnosis;
    public static javax.swing.JButton jButton1;
    public static javax.swing.JButton jButton2;
    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JPanel jPanel1;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JScrollPane jScrollPane4;
    public static javax.swing.JTabbedPane jTabbedPane1;
    public static javax.swing.JTextArea jTextArea1;
    public static javax.swing.JTextField jTextField1;
    public static javax.swing.JTable points;
    public static javax.swing.JTable symptoms;
    // End of variables declaration//GEN-END:variables
}
