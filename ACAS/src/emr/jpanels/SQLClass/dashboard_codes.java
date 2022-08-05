/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emr.jpanels.SQLClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import login.connection_db;
import net.proteanit.sql.DbUtils;
import static home.home_vet.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.*;
import sun.audio.*;
import java.awt.Color;
/**
 *
 * @author irv
 */
public class dashboard_codes {
    
    Connection conn_db = connection_db.ConnectDB();
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    String appdate = "";
    String name = "";
    String time = "";
    
    String getAppDate;
    String appid;
    
    String expdate="";
    
    String itemname = "";
    
    int notifcount = 7;
    
    int expireitemnotif = 0;
    private boolean alwaysOnTop;

    public void showItemOut(){
        try{
            String sql = "SELECT item.name as `Item Name`, item_out.quantity as `Qty. Out`, CONCAT_WS(' ',item_out.day,item_out.month,item_out.year) as `Date` FROM `item_out` INNER JOIN item on item_out.item_id = item.item_id";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void showItemlowQtyCount() throws FileNotFoundException{
        try{
            String sql = "SELECT `name` as `Item Name`, `quantity` as `Quantity` FROM `item` WHERE quantity <= 5";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
        int x = jTable2.getRowCount();
        if(x<=0){
            jLabel13.setText("<html>Item quantities are in good<br />condition</html>");
        }else{
            starterSound();
            jLabel13.setText("<html>Low Item Qty. Detected<br />CLICK here to REORDER</html>");
            JOptionPane.showMessageDialog(null, "The system has detected items with 5 and below quantities","Notification",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void disableAppointment(){
        try{
            String sql = "UPDATE appointment SET `status`= UPPER(?) WHERE appointment_id ='"+appid+"'";
            ps=conn_db.prepareStatement(sql);
            
            ps.setString(1, "disabled");
            ps.executeUpdate();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void getappoitnentDate(){
        try{
             String sql = "SELECT *,CONCAT_WS(' ',day,month,year) AS `date` from appointment";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             
             while(rs.next()){
                 String getdate = rs.getString("date");
                 getAppDate = getdate;
                 appid = rs.getString("appointment_id");
                 disablePastAppointments();
             }
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
    }
    
    public void disablePastAppointments(){
        String dt = getAppDate;
        
        /////
        int jan = 1;
        int feb = 2;
        int mar = 3;
        int apr = 4;
        int may = 5;
        int jun = 6;
        int jul = 7;
        int aug = 8;
        int sep = 9;
        int oct = 10;
        int nov = 11;
        int dec = 12;
        /////
        
        
        String string = dt;
        String[] parts = string.split(" ");
        String dt1 = parts[0]; // 004day
        String dt2 = parts[1]; // 034556month
        String dt3 = parts[2]; // 034556year
        int dtyear = Integer.parseInt(dt3);
        int dtday = Integer.parseInt(dt1);
        int dtmonth = 0;
        
        if(dt2.equals("Jan")){
            dtmonth = jan;
        }
        if(dt2.equals("Feb")){
            dtmonth = feb;
        }
        if(dt2.equals("Mar")){
            dtmonth = mar;
        }
        if(dt2.equals("Apr")){
            dtmonth = apr;
        }
        if(dt2.equals("May")){
            dtmonth = may;
        }
        if(dt2.equals("Jun")){
            dtmonth = jun;
        }
        if(dt2.equals("Jul")){
            dtmonth = jul;
        }
        if(dt2.equals("Aug")){
            dtmonth = aug;
        }
        if(dt2.equals("Sep")){
            dtmonth = sep;
        }
        if(dt2.equals("Oct")){
            dtmonth = oct;
        }
        if(dt2.equals("Nov")){
            dtmonth = nov;
        }
        if(dt2.equals("Dec")){
            dtmonth = dec;
        }
        
        String timeStamp = new SimpleDateFormat("d MMM yyyy").format(Calendar.getInstance().getTime());
        String[] current = timeStamp.split(" ");
        String cur1 = current[0];
        String cur2 = current[1];
        String cur3 = current[2];
        int curyear = Integer.parseInt(cur3);
        int curday = Integer.parseInt(cur1);
        int curmonth = 0;
        
        if(cur2.equals("Jan")){
            curmonth = jan;
        }
        if(cur2.equals("Feb")){
            curmonth = feb;
        }
        if(cur2.equals("Mar")){
            curmonth = mar;
        }
        if(cur2.equals("Apr")){
            curmonth = apr;
        }
        if(cur2.equals("May")){
            curmonth = may;
        }
        if(cur2.equals("Jun")){
            curmonth = jun;
        }
        if(cur2.equals("Jul")){
            curmonth = jul;
        }
        if(cur2.equals("Aug")){
            curmonth = aug;
        }
        if(cur2.equals("Sep")){
            curmonth = sep;
        }
        if(cur2.equals("Oct")){
            curmonth = oct;
        }
        if(cur2.equals("Nov")){
            curmonth = nov;
        }
        if(cur2.equals("Dec")){
            curmonth = dec;
        }
        
        if(dtyear<curyear){
            System.out.println("Invalid Year Detected! with month " +getAppDate);
            disableAppointment();
        }else{
            if(dtyear>curyear){
                System.out.println("Acceptable Year");
            }else{
            if(dtmonth<curmonth){
                System.out.println("Invalid Month Detected! with month " +getAppDate);
                disableAppointment();
            }else{
                if(dtmonth==curmonth){
                    if(dtday<curday){
                        System.out.println("Invalid Day Detected! with month " +getAppDate);
                        disableAppointment();
                        }else{
                           System.out.println("Acceptable day");
                        }
                    }else{
                        if(dtmonth>curmonth){
                            System.out.println("Acceptable day");
                        }
                    }
                }
            }
        }
    }
    
    public void checkDates(){
        try{
            /////
            int jan = 1;
            int feb = 2;
            int mar = 3;
            int apr = 4;
            int may = 5;
            int jun = 6;
            int jul = 7;
            int aug = 8;
            int sep = 9;
            int oct = 10;
            int nov = 11;
            int dec = 12;
            /////
            String[] exp = expdate.split(" ");
            String expday = exp[0];
            int expd = Integer.parseInt(expday);
            
            String expmonth = exp[1];
            int expm = 0;
            
            String expyear = exp[2];
            int expy = Integer.parseInt(expyear);
            
            if(expmonth.equals("JAN")){
            expm = jan;
            }
            if(expmonth.equals("FEB")){
                expm = feb;
            }
            if(expmonth.equals("MAR")){
                expm = mar;
            }
            if(expmonth.equals("APR")){
                expm = apr;
            }
            if(expmonth.equals("MAY")){
                expm = may;
            }
            if(expmonth.equals("JUN")){
                expm = jun;
            }
            if(expmonth.equals("JUL")){
                expm = jul;
            }
            if(expmonth.equals("AUG")){
                expm = aug;
            }
            if(expmonth.equals("SEP")){
                expm = sep;
            }
            if(expmonth.equals("OCT")){
                expm = oct;
            }
            if(expmonth.equals("NOV")){
                expm = nov;
            }
            if(expmonth.equals("DEC")){
                expm = dec;
            }
            
            
            
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            
            String timeStamp = new SimpleDateFormat("dd M yyyy").format(Calendar.getInstance().getTime());
            
            String[] parts3 = timeStamp.split(" ");
            String day = parts3[0];int curd = Integer.parseInt(day);
            String month = parts3[1];int curm = Integer.parseInt(month);
            String year = parts3[2];int cury = Integer.parseInt(year);
            
            int ansday = expd-curd;
            int ansmonth = expm-curm;
            int ansyear = expy-cury;

            
            if(ansyear <= 0 && expm<curm && expd<curd){
                System.out.println("\n"+ansday+" day(s) "+ansmonth+" month(s) and "+ansyear+" year(s) remaining");
                System.out.println(itemname+ " is already expired\n");
                expireitemnotif=1;
            }else{
                System.out.println("\n"+ansday+" day(s) "+ansmonth+" month(s) and "+ansyear+" year(s) remaining");
                System.out.println(itemname+ " is in good condition\n");
            }
            
          }catch(Exception e){
              System.out.println("Selected item has no dates yet");
          }
    }
    
    public void playSound() throws FileNotFoundException {
        
        if(!new File("/home/irv/status_registration.txt").exists()) throw new FileNotFoundException("/home/irv/status_registration.txt");{
        try {
			File file = new File("/home/irv/status_registration.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
			}
			fileReader.close();
                        String content = stringBuffer.toString();
			String status = content;
                        
                        if(status.equals("on")||status.equals("")){
                            InputStream in;
                            try{
                                in = new FileInputStream(new File("/home/irv/Documents/ACAS BUILD 5/ACAS/Audio Files/expired_items.wav"));
                                AudioStream audios=new AudioStream(in);
                                AudioPlayer.player.start(audios);
                            }catch(Exception e){
                               JOptionPane.showMessageDialog(null, e); 
                            }
                        }else{
                            //no sound
                        }
                        
                        
                        
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        }

    }
    
    public void starterSound() throws FileNotFoundException {
  
                InputStream in;
                  try{
                      in = new FileInputStream(new File("/home/irv/Documents/ACAS BUILD 5/ACAS/Audio Files/notify.wav"));
                      AudioStream audios=new AudioStream(in);
                      AudioPlayer.player.start(audios);
                }catch(Exception e){
                      JOptionPane.showMessageDialog(null, e); 
                }
    }
    
    public void loadingSound() throws FileNotFoundException {
  
                if(!new File("/home/irv/status_registration.txt").exists()) throw new FileNotFoundException("/home/irv/status_registration.txt");{
        try {
			File file = new File("/home/irv/status_registration.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
			}
			fileReader.close();
                        String content = stringBuffer.toString();
			String status = content;
                        
                        if(status.equals("on")||status.equals("")){
                            InputStream in;
                            try{
                                in = new FileInputStream(new File("/home/irv/Documents/ACAS BUILD 5/ACAS/Audio Files/loading.wav"));
                                AudioStream audios=new AudioStream(in);
                                AudioPlayer.player.start(audios);
                            }catch(Exception e){
                               JOptionPane.showMessageDialog(null, e); 
                            }
                        }else{
                            //no sound
                        }
                        
                        
                        
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        }
    }
    
    public void noDatesSound() throws FileNotFoundException {
  
                if(!new File("/home/irv/status_registration.txt").exists()) throw new FileNotFoundException("/home/irv/status_registration.txt");{
        try {
			File file = new File("/home/irv/status_registration.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
			}
			fileReader.close();
                        String content = stringBuffer.toString();
			String status = content;
                        
                        if(status.equals("on")||status.equals("")){
                            InputStream in;
                            try{
                                in = new FileInputStream(new File("/home/irv/Documents/ACAS BUILD 5/ACAS/Audio Files/nodates.wav"));
                                AudioStream audios=new AudioStream(in);
                                AudioPlayer.player.start(audios);
                            }catch(Exception e){
                               JOptionPane.showMessageDialog(null, e); 
                            }
                        }else{
                            //no sound
                        }
                        
                        
                        
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        }
    }
    
    public void countNoDates(){
            
        try{
            String sql="SELECT COUNT(item.`item_id`) as `Items` FROM `item` inner join supplier on item.`supplier_id` =  supplier.`supplier_id` where item.`expiration_year` = '0'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String count = rs.getString("Items");
                
                if(Integer.parseInt(count)>0){
//                    starterSound();
//                    noDatesSound();
//                    JOptionPane.showMessageDialog(null, "The system has detected "+count+" item(s) with no manufacturing and expiration date(s)","System Alert", JOptionPane.ERROR_MESSAGE);
//                    
//                    jLabel20.setVisible(true);
//                    jLabel20.setText("<html>"+count+" item(s) have no manufacturing<br /> & expiration dates<br />CLICK here to MANAGE</html>");
//                    jLabel20.setBackground(Color.red);
                    
                }else{
                    
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void checkExpirationDate() throws FileNotFoundException{//
        
        showItemlowQtyCount();

        countNoDates();
        
        try{
            String sql = "SELECT CONCAT_WS(' ',item.`expiration_day`,item.`expiration_month`,item.`expiration_year`) as Date, item.name as Name FROM `item` inner join supplier on item.`supplier_id` =  supplier.`supplier_id` where !item.`expiration_year` = '0' ";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                 expdate = rs.getString("Date");
                 itemname = rs.getString("Name");
                 checkDates();
            }
            
            if(expireitemnotif==1){
                starterSound();
                playSound();
                JOptionPane.showMessageDialog(null, "The system has detected expired item(s)","Critical System Alert", JOptionPane.ERROR_MESSAGE);
                
                jLabel21.setVisible(true);
                jLabel21.setText("<html>Expired Item(s) Detected<br />CLICK here to MANAGE</html>");
                jLabel21.setBackground(Color.red);
            }
            
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
        
        
    } 
    
}
