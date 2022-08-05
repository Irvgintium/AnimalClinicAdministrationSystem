/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import home.home_vet;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import static login.login_frm.typeset;

/**
 *
 * @author Irv Guint
 */
public class loading_frm extends javax.swing.JFrame {

    private Timer t;
    private int count = 0;
    
    String typename;
    /**
     * Creates new form loading_frm
     */
    public loading_frm() {
        initComponents();
        
        typename = typeset.getText();
        
        //for centered frame////
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);   
        //
            
        
        t = new Timer(10, new ActionListener() {
                        
                        @Override
                        public void actionPerformed(ActionEvent e) {
                           count++;
                           
                           pbar.setValue(count);
                           
                           if(pbar.getValue()==1){
                               
                               loading.setText("Loading client registration");
                               
                           }
                           
                           if(pbar.getValue()==2){
                               
                               loading.setText("Loading client registration..");
                               
                           }
                           
                           if(pbar.getValue()==3){
                               
                               loading.setText("Loading client registration...");
                               
                           }
                           
                           if(pbar.getValue()==4){
                               
                               loading.setText("Loading client registration.");
                               
                           }
                           
                           if(pbar.getValue()==5){
                               
                               loading.setText("Loading Inventory..");
                               
                           }
                           
                           if(pbar.getValue()==6){
                               
                               loading.setText("Loading Inventory...");
                               
                           }
                           
                           if(pbar.getValue()==7){
                               
                               loading.setText("Loading Inventory.");
                               
                           }
                           
                           if(pbar.getValue()==8){
                               
                               loading.setText("Loading Inventory..");
                               
                           }
                           
                           if(pbar.getValue()==9){
                               
                               loading.setText("Loading Pet Chart...");
                               
                           }
                           
                           ///////////////////////////////////////////////////////////
                           
                           
                           if(pbar.getValue()==10){
                               
                               loading.setText("Loading Pet Chart...");
                               
                           }
                           
                           if(pbar.getValue()==50){
                               
                               loading.setText("Loading Pet Chart...");
                               
                           }
                           
                           if(pbar.getValue()==70){
                               
                               loading.setText("Loading Pet Chart...");
                               
                           }
                           
                           if(pbar.getValue()==90){
                               
                               loading.setText("Opening Dashboard...");
                               
                           }
 
                           if(count==200){
                               
                               t.stop();
                               
                               //////ADD IF STATEMENT ON HERE///FOR DIFFERENT ACCOUNTS///////
                               if(typename.equals("ASISSTANT VETERINARIAN")){
                                   
//                                   JOptionPane.showMessageDialog(null, "Homepage is under construction.");
                                   new home_vet().setVisible(true);
                               }
                                else{
                                        new home_vet().setVisible(true);
                                    }
                               
                               dispose();

                           }
                        }
                    });
                    t.start();
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
        pbar = new javax.swing.JProgressBar();
        loading1 = new javax.swing.JLabel();
        loading = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(pbar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 500, 300, 10));

        loading1.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        loading1.setForeground(new java.awt.Color(254, 254, 254));
        loading1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loading1.setText("<html>Animal Clinic Administration System<br />build 15 v.0.0.15 beta<br/>2016</html>");
        getContentPane().add(loading1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 420, 230, 70));

        loading.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        loading.setForeground(new java.awt.Color(254, 254, 254));
        loading.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loading.setText("Loading...");
        getContentPane().add(loading, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, 410, 20));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/LOGOSbigblackBG.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 730, 390));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg_fixed.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -30, 780, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(loading_frm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(loading_frm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(loading_frm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(loading_frm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new loading_frm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel loading;
    private javax.swing.JLabel loading1;
    private javax.swing.JProgressBar pbar;
    // End of variables declaration//GEN-END:variables
}
