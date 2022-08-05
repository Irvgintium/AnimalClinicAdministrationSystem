/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;
import javax.swing.JOptionPane;
import temp.MySms;
/**
 *
 * @author Irvin Guinto
 */
public class Test {
    public static void main(String args[]){
//    String contactNo = JOptionPane.showInputDialo g(null, "this is a demo \nInput Contact No. e.g.+63xxxxxxxxxx");
//    String msg = JOptionPane.showInputDialog(null, "this is a demo \nInput a message");
    MySms sms = new MySms("/dev/ttyUSB0");
    boolean isSent = sms.sendSMS("+639429195516","Dadiangas Heights Veterinary Clinic testing SMS Notification");
    if(isSent){
        System.out.println("Sent");
    }
    else{
        System.out.println("Not Sent");
    }
}
}