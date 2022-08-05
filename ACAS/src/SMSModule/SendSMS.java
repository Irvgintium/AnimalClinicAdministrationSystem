/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMSModule;
import emr.jpanels.SQLClass.dashboard_codes;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;
import other.features.jpanels.customMessage_Screen;
/**
 *
 * @author irv
 */
public class SendSMS {
    public static String num;// = JOptionPane.showInputDialog("Input Contact Number")
    public static String msg;//= JOptionPane.showInputDialog("Input Text Message")
    public static void main(String args[]) {
        String s;
        Process p;
        try {
            p = Runtime.getRuntime().exec(new String[] { "gammu", "sendsms", "TEXT", num, "-text", msg });//
            BufferedReader br = new BufferedReader(
                new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null)
                System.out.println("line: " + s);
            
            customMessage_Screen cm = new customMessage_Screen();
            cm.msg.setText("Message sent!");
            dashboard_codes dc = new dashboard_codes();
            dc.playSound();
            cm.setVisible(true);
            
            p.waitFor();
            System.out.println ("exit: " + p.exitValue());  
            p.destroy();
        } catch (Exception e) {}
    }
}
