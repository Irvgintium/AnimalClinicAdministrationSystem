/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package login;
import de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel;
import javax.swing.UIManager;
import de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel;//
/**
 *
 * @author irv
 */
public class start {
        public void theme(){
//             try{
//        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        }
//        catch(Exception e){
//        System.out.println("Can't set look and feel: " + e.getMessage());
//        }
            try 
            {
              UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
            } 
            catch (Exception e) 
            {
              e.printStackTrace();
            }

        }
        
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
            java.util.logging.Logger.getLogger(login_frm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login_frm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login_frm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login_frm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                start x = new start();
                x.theme();
                new login_frm().setVisible(true);
            }
        });
    }
}
