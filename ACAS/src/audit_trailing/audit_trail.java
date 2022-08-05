/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package audit_trailing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JOptionPane;
import login.connection_db;
import static emr.jpanels.pet_chart.*;
import static login.login_frm.setlogid;
/**
 *
 * @author irv
 */
public class audit_trail {
    public static String action_type;
    public static String module_name;
    
    Connection conn_db = connection_db.ConnectDB();
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    String day;
    String month;
    String year;
    
    String hour = new SimpleDateFormat("h").format(Calendar.getInstance().getTime());
    String minute = new SimpleDateFormat("mm").format(Calendar.getInstance().getTime());
    String meridiem = new SimpleDateFormat("a").format(Calendar.getInstance().getTime());
    
    String uid;
    
    public void saveAuditTRail(){
        
    uid = setlogid.getText();    
    
    String timeStamp = new SimpleDateFormat("d MMM yyyy").format(Calendar.getInstance().getTime());
    String[] parts3 = timeStamp.split(" ");
    String p1 = parts3[0]; // 004
    String p2 = parts3[1]; // 034556
    String p3 = parts3[2]; // 034556
    
    day = p1;
    month = p2;
    year = p3;
    
    try{
        
        String sql2 = "insert into audit_trail (`user_id`,`action_type`,`name`,`day`,`month`,`year`,`hour`,`minute`,`am_pm`) values"
                + "(?,UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),?,?,UPPER(?))";
                ps = conn_db.prepareStatement(sql2);
                ps.setString(1, uid);
                ps.setString(2, action_type.toUpperCase());
                ps.setString(3, module_name.toUpperCase());
                ps.setString(4, day);
                ps.setString(5, month);
                ps.setString(6, year);
                ps.setString(7, hour);
                ps.setString(8, minute);
                ps.setString(9, meridiem);
                ps.execute();
                
                System.out.println("Succesfully Saved to audit trail");
        
    }catch(Exception e){
                 JOptionPane.showMessageDialog(null, e);
             }
    }
}
