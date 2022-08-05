/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package temp.thread_test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import static temp.thread_test.thread_test.jTextArea1;

/**
 *
 * @author irv
 */
public class thread_code extends thread_method{
    
    int timerun = 0;
    int n;
    String gets;
    String cont;
    
    public void runThread(){
        timerun =0;
        new Thread(){
            public void run(){
                while(timerun == 0){
                    n++;
                    if(n % 500==0){
                        cont = "Run this code #"+n+"\n"; 
                        gets = "Stopped this code at approx. #"+n;
                    }else{
                        
                    }
                    
                }
            }
        }.start();
    }

    @Override
    void stopThread() {
        timerun = 1;
        JOptionPane.showMessageDialog(null, gets);
    }
    
}

