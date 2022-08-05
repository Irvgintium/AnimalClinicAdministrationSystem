/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SMSModule;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;
import login.connection_db;

/**
 *
 * @author irv
 */
public class sms_batch_process {
    Connection conn_db = connection_db.ConnectDB();
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    String getmsg;
    String getDate;
    String getNum;
    String getsmsmid;
    
    int timerun = 0;
    
    String currentDate = new SimpleDateFormat("d MMM yyyy").format(Calendar.getInstance().getTime());
    
    public void setDateForSMSSending(){
        try{
             String sql = "SELECT sms_notification.id as `SMSID`,owner.`contact_number_1` as `ConNum`, sms_notification.message,CONCAT_WS(' ',sms_notification.`day`,sms_notification.`month`,sms_notification.`year`) AS `Date`, sms_notification.status as `Status` FROM `sms_notification` INNER JOIN owner ON owner.owner_id=sms_notification.owner_id HAVING `Status` = 'ONGOING' and `Date` = '"+currentDate+"'";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             
             while(rs.next()){
                 getmsg = rs.getString("message");
                 getDate = rs.getString("Date");
                 getNum = rs.getString("ConNum");
                 getsmsmid = rs.getString("SMSID");
                 sendSMS();
                 cancelSMS();
             }
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
        
        timerun = 1;
        System.out.println("SMS Thread Stopped");
    }
    
    public void runAutoSMSThread(){
        new Thread(){
            public void run(){
                while(timerun == 0){
                    setDateForSMSSending();
                }
            }
        }.start();
    }
    
    public void sendSMS(){
        String s;
        Process p;
        try {
            p = Runtime.getRuntime().exec(new String[] { "gammu", "sendsms", "TEXT", getNum, "-text", getmsg });//
            BufferedReader br = new BufferedReader(
                new InputStreamReader(p.getInputStream()));
            while ((s = br.readLine()) != null)
                System.out.println("line: " + s);
            System.out.println("Message Sent to num : "+getNum);
            p.waitFor();
            System.out.println ("exit: " + p.exitValue());  
            p.destroy();
        } catch (Exception e) {}
    }
    
    public void cancelSMS(){
        try{
            String sql="UPDATE sms_notification SET `status` = ? where id = '"+getsmsmid+"'";         
            ps=conn_db.prepareStatement(sql);
            
            ps.setString(1, "CANCELLED");
            ps.executeUpdate();
            System.out.println("SMS Notification for number "+getNum+" has been cancelled");
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
    }
}