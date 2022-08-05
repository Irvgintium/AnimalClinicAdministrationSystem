/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;
//import com.harshadura.gsm.smsdura.GsmModem;
/**
 *
 * @author Irvin Guinto
 */
public class sms_send {
    private static String port = "COM9"; //Modem Port.
    private static int bitRate = 115200; //this is also optional. leave as it is.
    private static String modemName = "HUAWEI"; //this is optional.
    private static String modemPin = "0000"; //Pin code if any have assigned to the modem.
    private static String SMSC = "+9477000003"; //Message Center Number ex. Mobitel

    public static void main(String[] args) throws Exception {
//        GsmModem gsmModem = new GsmModem();
//        GsmModem.configModem(port, bitRate, modemName, modemPin, SMSC);
//        gsmModem.Sender("+639429195516", "This is Dadiangas Heights Veterinary Clinic testing SMS Notification"); // (tp, msg) 
    }
}
