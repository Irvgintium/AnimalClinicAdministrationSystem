/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package temp.thread_test;

import other.features.jpanels.customMessage_Screen;

/**
 *
 * @author irv
 */
public class run_thread extends codes {

    public static void main(String args[]) {
        codes x = new run_thread();
        x.PrintSendSMS();
        x.PrintReceiveSMS();
        customMessage_Screen cm = new customMessage_Screen();
            cm.msg.setText("Profile for GUINTO, IKEE  "+" has been successfully stored");
            cm.setVisible(true);
    }
}
