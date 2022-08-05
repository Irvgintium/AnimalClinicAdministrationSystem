/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emr.jpanels.SQLClass;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author irv
 */
public class gettime {
    public static String H;
    public static String M;
    public static String A;
    
    public void setTime(){
    String hour = new SimpleDateFormat("HH").format(Calendar.getInstance().getTime());
    H = hour;
    String minute = new SimpleDateFormat("mm").format(Calendar.getInstance().getTime());
    M = minute;
    String meridiem = new SimpleDateFormat("a").format(Calendar.getInstance().getTime());
    A = meridiem;
    }
}
