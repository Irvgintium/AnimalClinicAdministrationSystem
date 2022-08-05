/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author IrvGu
 */
public class checkupautodiagnose extends javax.swing.JFrame {

    String set;
    
    String day;
    String month;
    String year;
    
    String day2;
    String month2;
    String year2;
    int finalpoint = 0;
    
    int totalpoint=0;
     
    /**
     * Creates new form checkupautodiagnose
     */
    public checkupautodiagnose() {
        initComponents();
        
        ////date here//////
        JCalendar dateset = new JCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
        String dob = dateFormat.format(dateset.getDate());
        jLabel2.setText(dob);
        ////date here/////
    }
    
    public void getAge(){//Year/Month/Day
        int calmonth = 12;
        int calmonth2 = 12;
        
        String string3 = jLabel2.getText();//Current Date
        String[] parts3 = string3.split(" ");
        String p1 = parts3[0];
        String p2 = parts3[1];
        String p3 = parts3[2];
    
        day = p1;int d1 = Integer.parseInt(day);
        month = p2;int m1;
        year = p3;int y1 = Integer.parseInt(year);
        
        ///////
        
        /////get current month
        
        if(month.equals("Jan")){
            calmonth = calmonth - 11;
            
            JOptionPane.showMessageDialog(null,"Current Month is " + calmonth);
        }
        
        if(month.equals("Feb")){
            calmonth = calmonth - 10;
            
            JOptionPane.showMessageDialog(null,"Current Month is " + calmonth);
        }
        
        if(month.equals("Mar")){
            calmonth = calmonth - 9;
            
            JOptionPane.showMessageDialog(null,"Current Month is " + calmonth);
        }
        
        if(month.equals("Apr")){
            calmonth = calmonth - 8;
            
            JOptionPane.showMessageDialog(null,"Current Month is " + calmonth);
        }
        
        if(month.equals("May")){
            calmonth = calmonth - 7;
            
            JOptionPane.showMessageDialog(null,"Current Month is " + calmonth);
        }
        
        if(month.equals("Jun")){
            calmonth = calmonth - 6;
            
            JOptionPane.showMessageDialog(null,"Current Month is " + calmonth);
        }
        
        if(month.equals("Jul")){
            calmonth = calmonth - 5;
            
            JOptionPane.showMessageDialog(null,"Current Month is " + calmonth);
        }
        
        if(month.equals("Aug")){
            calmonth = calmonth - 4;
            
            JOptionPane.showMessageDialog(null,"Current Month is " + calmonth);
        }
        
        if(month.equals("Sep")){
            calmonth = calmonth - 3;
            
            JOptionPane.showMessageDialog(null,"Current Month is " + calmonth);
        }
        
        if(month.equals("Oct")){
            calmonth = calmonth - 2;
            
            JOptionPane.showMessageDialog(null,"Current Month is " + calmonth);
        }
        
        if(month.equals("Nov")){
            calmonth = calmonth - 1;
            
            JOptionPane.showMessageDialog(null,"Current Month is " + calmonth);
        }
        
        if(month.equals("Dec")){
            
            JOptionPane.showMessageDialog(null,"Current Month is " + calmonth);
        }
        ////
        
        ///////
        
        String string4 = jLabel1.getText();//Birth Date
        String[] parts4 = string4.split(" ");
        String x1 = parts4[0];
        String x2 = parts4[1];
        String x3 = parts4[2];
    
        day2 = x1;int d2 = Integer.parseInt(day2);
        month2 = x2;int m2;
        year2 = x3;int y2 = Integer.parseInt(year2);
        
        ////////
        
        /////get current month
        
        if(month2.equals("Jan")){
            calmonth2 = calmonth2 - 11;
            
            JOptionPane.showMessageDialog(null,"Birth Month is " + calmonth2);
        }
        
        if(month2.equals("Feb")){
            calmonth2 = calmonth2 - 10;
            
            JOptionPane.showMessageDialog(null,"Birth Month is " + calmonth2);
        }
        
        if(month2.equals("Mar")){
            calmonth2 = calmonth2 - 9;
            
            JOptionPane.showMessageDialog(null,"Birth Month is " + calmonth2);
        }
        
        if(month2.equals("Apr")){
            calmonth2 = calmonth2 - 8;
            
            JOptionPane.showMessageDialog(null,"Birth Month is " + calmonth2);
        }
        
        if(month2.equals("May")){
            calmonth2 = calmonth2 - 7;
            
            JOptionPane.showMessageDialog(null,"Birth Month is " + calmonth2);
        }
        
        if(month2.equals("Jun")){
            calmonth2 = calmonth2 - 6;
            
            JOptionPane.showMessageDialog(null,"Birth Month is " + calmonth2);
        }
        
        if(month2.equals("Jul")){
            calmonth2 = calmonth2 - 5;
            
            JOptionPane.showMessageDialog(null,"Birth Month is " + calmonth2);
        }
        
        if(month2.equals("Aug")){
            calmonth2 = calmonth2 - 4;
            
            JOptionPane.showMessageDialog(null,"Birth Month is " + calmonth2);
        }
        
        if(month2.equals("Sep")){
            calmonth2 = calmonth2 - 3;
            
            JOptionPane.showMessageDialog(null,"Birth Month is " + calmonth2);
        }
        
        if(month2.equals("Oct")){
            calmonth2 = calmonth2 - 2;
            
            JOptionPane.showMessageDialog(null,"Birth Month is " + calmonth2);
        }
        
        if(month2.equals("Nov")){
            calmonth2 = calmonth2 - 1;
            
            JOptionPane.showMessageDialog(null,"Birth Month is " + calmonth2);
        }
        
        if(month2.equals("Dec")){
            
            JOptionPane.showMessageDialog(null,"Birth Month is " + calmonth2);
        }
        ////
        
        ///////
        
        int yearAge = y1-y2;
        
        
        
        int monthAge = calmonth - calmonth2;
        
        if(monthAge<0){
            monthAge = 0;
        }
        
        int dayAge = d1-d2;
        
        if(dayAge<0){
            dayAge = 0;
        }
        
        JOptionPane.showMessageDialog(null, "Age is "+ yearAge +" yr(s) old"+", "+monthAge+" month(s)"+" and "+dayAge+" day(s)");
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        v = new javax.swing.JTextField();
        o = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        diagnose = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        points = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        v.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vActionPerformed(evt);
            }
        });
        getContentPane().add(v, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 166, -1));

        o.setText("jLabel1");
        getContentPane().add(o, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 139, -1, -1));

        jButton1.setText("count");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 159, -1, -1));

        diagnose.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Diagnosis"
            }
        ));
        jScrollPane1.setViewportView(diagnose);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 37, 166, 96));
        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 30, 110, 20));

        jButton2.setText("add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 70, -1));

        jTextField1.setText("jTextField1");
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
        });
        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 110, -1));

        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 280, -1, -1));

        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, -1, -1));

        jButton3.setText("get");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, -1, -1));

        points.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "diagnosis", "points"
            }
        ));
        jScrollPane2.setViewportView(points);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 270, 190));

        jButton4.setText("Diagnosis with highest score");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 330, 230, -1));

        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, -1));

        jButton5.setText("get total points");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 160, -1));

        jButton6.setText("Results");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 290, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
        DefaultListModel dim = new DefaultListModel();
    private void vActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vActionPerformed
        DefaultTableModel model=(DefaultTableModel) diagnose.getModel();
        model.addRow(new Object[]{v.getText()});
        //for adding points
        DefaultTableModel modelx=(DefaultTableModel) points.getModel();
        modelx.addRow(new Object[]{v.getText(), "0"});
        //for adding points
        int size = model.getRowCount();
        int f = size-1;
        
        o.setText(""+f);
        
        for(int x=1; x<=f; x++){
            if(diagnose.getValueAt(f, 0).toString().equals(diagnose.getValueAt(x-1, 0).toString())){
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
        
    }//GEN-LAST:event_vActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JOptionPane.showMessageDialog(null, diagnose.getRowCount());
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//       check_expidate cc = new check_expidate();
//       jTabbedPane1.addTab("Test", cc);
       
       int xq = jTabbedPane1.getTabCount();
       jTabbedPane1.setSelectedIndex(xq-1);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusGained
        
    }//GEN-LAST:event_jTextField1FocusGained

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        getAge();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField1MouseClicked
        /////////////////////////////////////////
        JDateChooser date = new JDateChooser();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
        JOptionPane.showMessageDialog(null, date);
        String dob = dateFormat.format(date.getDate());
        jLabel1.setText(dob);jTextField1.setText(dob);
        ///
    }//GEN-LAST:event_jTextField1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
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
                jLabel3.setText(name + finalpoint);
            }else{
                finalpoint = finalpoint;
                name = name;
                jLabel3.setText(name+ finalpoint);

            }
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        DefaultTableModel modelx=(DefaultTableModel) points.getModel();
         
        int size = modelx.getRowCount();
        int f = size-1;
        
        o.setText(""+f);

        int contpoint=0;
        
        for(int x=0; x<=f; x++){
            int getpoints = Integer.parseInt(points.getValueAt(x, 1).toString());
            contpoint = contpoint + getpoints;
            
            totalpoint = contpoint;
        }
        
        JOptionPane.showMessageDialog(null, totalpoint);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        
        JOptionPane.showMessageDialog(null,finalpoint + " " + totalpoint);
                
        double percentagep1 = ((double)finalpoint/(double)totalpoint);
        
        JOptionPane.showMessageDialog(null, percentagep1);
        
        double percentage = percentagep1 * 100;
        
        double roundedNumber = (double)Math.round(percentage * 10) / 10;
        
        JOptionPane.showMessageDialog(null, "The Diagnosis is " +roundedNumber+"% accurate based on inputted symptoms");
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(checkupautodiagnose.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(checkupautodiagnose.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(checkupautodiagnose.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(checkupautodiagnose.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new checkupautodiagnose().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable diagnose;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel o;
    private javax.swing.JTable points;
    private javax.swing.JTextField v;
    // End of variables declaration//GEN-END:variables

}
