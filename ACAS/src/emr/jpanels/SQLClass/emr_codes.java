/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emr.jpanels.SQLClass;

import audit_trailing.audit_trail;
import emr.jpanels.encounter_notes;
import emr.jpanels.immunization_history;
import static emr.jpanels.pet_chart.*;
import emr.jpanels.reports.save_medical_record;
import static emr.jpanels.reports.save_medical_record.*;
import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import login.connection_db;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import other.features.jpanels.customMessage_Screen;
/**
 *
 * @author irv
 */
public class emr_codes {
    Connection conn_db = connection_db.ConnectDB();
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    public static int timerun = 0;
    
    String appointmentID;
    String datevisit;
    String diagnosis;
    String getProg;

    String minpatch;
    
    String appreason;
    
    public void minutefix(){
       int min = Integer.parseInt(minute.getValue().toString());
       
       if(min<10){
           minpatch = "0"+Integer.toString(min);
       }else{
           minpatch = Integer.toString(min);
       }
       
    }
    
    public void saveAppointment(){
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("d MMM yyyy");
        String dob2 = dateFormat2.format(jCalendar1.getDate());
        
        String string3 = dob2;
        String[] parts3 = string3.split(" ");
        String p1 = parts3[0]; // 004
        String p2 = parts3[1]; // 034556
        String p3 = parts3[2]; // 034556

        String day = p1;
        String month = p2;
        String year = p3;
        
        try{
                String sql = "Insert into appointment (`pet_id`,`reason`,`hour`,`minute`,`meridiem`,`day`,`month`,`year`,`status`) values (UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?))";
                ps=conn_db.prepareStatement(sql);
                ps.setString(1, petid);
                ps.setString(2, jTextArea4.getText());
                ps.setString(3, hour.getValue().toString());
                ps.setString(4, minpatch);
                ps.setString(5, jComboBox6.getSelectedItem().toString());
                ps.setString(6, day);
                ps.setString(7, month);
                ps.setString(8, year);
                ps.setString(9, "Ongoing");
                ps.execute();
                
                audit_trail au = new audit_trail();
                au.action_type = "Saved - appointment information by "+ pet_name.getText();
                au.module_name = "Pet Chart";
                au.saveAuditTRail();
                
                customMessage_Screen cm = new customMessage_Screen();
                cm.msg.setText("Appointment is now pending");
                cm.setVisible(true);
                
                getAppointmentReason();
                resetAppointment();
                
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
    }
    
    public void getAppointmentReason(){
        String string3 = jLabel17.getText();
        String[] parts3 = string3.split(" ");
        String actualday = parts3[0]; // 004
        String actualmonth = parts3[1]; // 034556
        String actualyear = parts3[2]; // 034556
        
        try{
             String sql = "SELECT `reason`, CONCAT_WS(' ', day, month, year) as `Date` FROM `appointment` WHERE pet_id = '"+petid+"' HAVING `Date` = '"+actualday+" "+actualmonth+" "+actualyear+"'";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             
             while(rs.next()){
                 String getreason = rs.getString("reason");
                 appreason = getreason;
             }
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
    }
    
    public void saveSMSNotification(){
        String string3 = jLabel17.getText();
        String[] parts3 = string3.split(" ");
        String actualday = parts3[0]; // 004
        String actualmonth = parts3[1]; // 034556
        String actualyear = parts3[2]; // 034556
        
        String smss = c.getText();
        String[] sms = smss.split(" ");
        String smsday = sms[0]; // 004
        String smsmonth = sms[1]; // 034556
        String smsyear = sms[2]; // 034556
        
        String msg = "Good day Mr/Mrs "+owner_name.getText()+", This is Daddiangas Heights Veterinary Clinic informing you on "+actualday+" "+actualmonth.toUpperCase()+" "+actualyear+" at "+hour.getValue().toString()+":"+minpatch+" "+jComboBox6.getSelectedItem().toString()+" will be "+pet_name.getText()+"'s next appointment for "+appreason+". Thank you.";
        try{
                String sql = "Insert into sms_notification (`owner_id`,`pet_id`,`message`,`day`,`month`,`year`,`hour`,`minute`,`meridiem`,`status`) values (?,?,?,?,?,?,?,?,?,?)";
                ps=conn_db.prepareStatement(sql);
                ps.setString(1, ownerid);
                ps.setString(2, petid);
                ps.setString(3, msg);
                ps.setString(4, smsday);
                ps.setString(5, smsmonth.toUpperCase());
                ps.setString(6, smsyear);
                ps.setString(7, hour.getValue().toString());
                ps.setString(8, minpatch);
                ps.setString(9, jComboBox6.getSelectedItem().toString());
                ps.setString(10, "ONGOING");
                ps.execute();
                
                audit_trail au = new audit_trail();
                au.action_type = "Saved - sms notification information by "+ pet_name.getText();
                au.module_name = "Pet Chart";
                au.saveAuditTRail();
                
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void resetAppointment(){
        hour.setValue(0);
        minute.setValue(0);
        jTextArea4.setText("");
        jComboBox6.setSelectedItem("AM");
    }
    
    public void viewSavedAppointments(){
        DefaultListModel dimx = new DefaultListModel();
         try{
             String sql = "SELECT CONCAT_WS('', '#',appointment_id) AS `ID`, CONCAT_WS(' ', day, month, year) AS `DOE` FROM `appointment` WHERE pet_id = '"+petid+"' and status = 'ONGOING'";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             
             while(rs.next()){
                 String getID = rs.getString("ID");
                 String getDOE = rs.getString("DOE");
                 String cont = "-----Appointment on " + getDOE +"-----ID"+getID;
                 dimx.addElement(cont);
                 jList3.setModel(dimx);
             }
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
    }
    
    public void getAppointmentID(){
        String[] parts = jList3.getSelectedValue().toString().split("#");
        String p1 = parts[0]; // 004
        String p2 = parts[1]; // 034556
        appointmentID = p2;
        try{
            String sql = "SELECT CONCAT_WS(' ',day,month, year) AS `Date`, reason, hour, minute, meridiem FROM `appointment` WHERE appointment_id = '"+appointmentID+"'";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             
             while(rs.next()){ 
                    jTextArea4.setText(rs.getString("reason"));
                    jComboBox6.setSelectedItem(rs.getString("meridiem"));
                    java.util.Date date = null;
                    try {
                        date = new SimpleDateFormat("d MMM yyyy").parse(rs.getString("Date"));
                        } catch (ParseException ex) {
                            Logger.getLogger(immunization_history.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        jCalendar1.setDate(date);
                        int hr = Integer.parseInt(rs.getString("hour"));
                        hour.setValue(hr);
                        int mt = Integer.parseInt(rs.getString("minute"));
                        minute.setValue(mt);
             }
             
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void fixTextPane(){
        StyledDocument doc = jTextPane1.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_JUSTIFIED);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }
    
    public void viewMedicalRecordtoSave(){
        
        new save_medical_record().setVisible(true);
        
        ArrayList<String> arr = new ArrayList<String>();
        
        jTextPane1.setText("");
        
        StyledDocument doc = jTextPane1.getStyledDocument();
        Style style = jTextPane1.addStyle("I'm a Style", null);
        StyleConstants.setForeground(style, Color.black);
        StyleConstants.setBold(style, true);
        StyleConstants.setItalic(style, false);
        
        try { doc.insertString(doc.getLength(), "Services: ",style); }
        catch (BadLocationException e){}
        
        try{
            String sql = "SELECT acq_name AS `Service Name`, acq_price AS `Price`, CONCAT_WS(' ', services_acquired.day, services_acquired.month, services_acquired.year) AS `Date` FROM `services_acquired` WHERE pet_id = '"+petid+"' HAVING `Date` = '"+today_date.getText()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String xx = rs.getString("Date");
                datevisit = xx;
                arr.add(rs.getString("Service Name"));
            }
            
            String result = arr.toString();
            result = result.replace("[", "");
            result = result.replace("]", "");
            result = result.replace(" ", " ");
            
            StyleConstants.setForeground(style, Color.blue);
            StyleConstants.setBold(style, true);
            StyleConstants.setItalic(style, true);
            
            StyleConstants.setForeground(style, Color.black);
            StyleConstants.setBold(style, true);
            StyleConstants.setItalic(style, false);
        
            try { doc.insertString(doc.getLength(), result.toUpperCase(),style); }
            catch (BadLocationException e){}
            
            
            
            if(result.equals("")){
                jTextPane1.setText("No data retrieved");
                
            }else{
                ConcatSymptomsAndDiagnosis();
                ConcatMedicineGiven();
                ConcatPrescriptionGiven();
            }
            
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void ConcatSymptomsAndDiagnosis(){
         ArrayList<String> arr = new ArrayList<String>();
         
        StyledDocument doc = jTextPane1.getStyledDocument();
        Style style = jTextPane1.addStyle("I'm a Style", null);
        
        try{
            String sql = "SELECT symtoms_given AS `Symptoms`, diagnosis AS `Diagnosis`, prognosis AS `Prognosis`, CONCAT_WS(' ', day, month, year) AS `Date` FROM `daily_data_diagnosis_symptoms` WHERE pet_id = '"+petid+"' HAVING `Date` = '"+today_date.getText()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                diagnosis= rs.getString("Diagnosis");
                getProg = rs.getString("Prognosis");
                arr.add(rs.getString("Symptoms"));
            }
            
            String result = arr.toString();
            result = result.replace("[", "");
            result = result.replace("]", "");
            result = result.replace(" ", " ");
            
            StyleConstants.setForeground(style, Color.black);
            StyleConstants.setBold(style, true);
            StyleConstants.setItalic(style, false);

            try { doc.insertString(doc.getLength(), "\nSymptoms: ",style); }
            catch (BadLocationException e){}

            StyleConstants.setForeground(style, Color.black);
            StyleConstants.setBold(style, true);
            StyleConstants.setItalic(style, false);
            
            try { doc.insertString(doc.getLength(), result,style); }
            catch (BadLocationException e){}
            
            //////
            
            StyleConstants.setForeground(style, Color.black);
            StyleConstants.setBold(style, true);
            StyleConstants.setItalic(style, false);

            try { doc.insertString(doc.getLength(), "\nDiagnosis: ",style); }
            catch (BadLocationException e){}
  
            StyleConstants.setForeground(style, Color.black);
            StyleConstants.setBold(style, true);
            StyleConstants.setFontFamily(style, "Arial");
            StyleConstants.setItalic(style, false);

            try { doc.insertString(doc.getLength(),diagnosis,style); }
            catch (BadLocationException e){}
            
            try { doc.insertString(doc.getLength(), "\nPrognosis: ",style); }
            catch (BadLocationException e){}
  
            StyleConstants.setForeground(style, Color.black);
            StyleConstants.setBold(style, true);
            StyleConstants.setFontFamily(style, "Arial");
            StyleConstants.setItalic(style, false);

            try { doc.insertString(doc.getLength(),getProg,style); }
            catch (BadLocationException e){}
            
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void ConcatMedicineGiven(){
        ArrayList<String> arr = new ArrayList<String>();
        
        StyledDocument doc = jTextPane1.getStyledDocument();

        Style style = jTextPane1.addStyle("I'm a Style", null);
        StyleConstants.setForeground(style, Color.black);
        StyleConstants.setBold(style, true);
        StyleConstants.setFontFamily(style, "Arial");
        StyleConstants.setItalic(style, false);
        
        try { doc.insertString(doc.getLength(),"\nGiven Med.: ",style); }
        catch (BadLocationException e){}
        
        
        try{
            String sql = "SELECT CONCAT_WS(' ', day, month,year) AS `Date`, CONCAT_WS(' ', volume, medicine_given, dose_unit) AS `Medicine`, daily_usage AS `Usage` FROM `daily_data_diagnosis_medicine_given` WHERE pet_id = '"+petid+"' HAVING `Date` = '"+today_date.getText()+"' ";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                arr.add(rs.getString("Medicine"));
            }
            
            String result = arr.toString();
            result = result.replace("[", "");
            result = result.replace("]", "");
            result = result.replace(" ", " ");
            
            StyleConstants.setForeground(style, Color.black);
            StyleConstants.setBold(style, true);
            StyleConstants.setItalic(style, false);

            try { doc.insertString(doc.getLength(), result ,style); }
            catch (BadLocationException e){}

        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void ConcatPrescriptionGiven(){
        ArrayList<String> arr = new ArrayList<String>();
        
        StyledDocument doc = jTextPane1.getStyledDocument();
        
        Style style = jTextPane1.addStyle("I'm a Style", null);
        StyleConstants.setForeground(style, Color.black);
        StyleConstants.setBold(style, true);
        StyleConstants.setFontFamily(style, "Arial");
        StyleConstants.setItalic(style, false);
        
        try { doc.insertString(doc.getLength(),"\nPrescription: ",style); }
        catch (BadLocationException e){}
        
        
        try{
            String sql = "SELECT CONCAT_WS(' ', day, month,year) AS `Date`, CONCAT_WS(' ', medicine, volume, volume_p2) AS `Medicine` FROM `prescription` WHERE pet_id = '"+petid+"' HAVING `Date` = '"+today_date.getText()+"' ";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                arr.add(rs.getString("Medicine"));
            }
            
            String result = arr.toString();
            result = result.replace("[", "");
            result = result.replace("]", "");
            result = result.replace(" ", " ");
            
            StyleConstants.setForeground(style, Color.black);
            StyleConstants.setBold(style, true);
            StyleConstants.setItalic(style, false);

            try { doc.insertString(doc.getLength(), result ,style); }
            catch (BadLocationException e){}

        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void saveInMedicalHistory(){
        
        String string3 = today_date.getText();
            String[] parts3 = string3.split(" ");
            String day = parts3[0];
            String month = parts3[1];
            String year = parts3[2];
        
         try{
                String sql = "Insert into medical_history (`pet_id`,`remarks`,`day`,`month`,`year`) values (UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?))";
                ps=conn_db.prepareStatement(sql);
                ps.setString(1, petid);
                ps.setString(2, jTextPane1.getText());
                ps.setString(3, day);
                ps.setString(4, month);
                ps.setString(5, year);
                ps.execute();
                
                audit_trail au = new audit_trail();
                au.action_type = "Saved - medical history information by "+ pet_name.getText();
                au.module_name = "Pet Chart";
                au.saveAuditTRail();
                
                JOptionPane.showMessageDialog(null, "Supplier information has been sucessfully stored","Successfull",JOptionPane.INFORMATION_MESSAGE);
                
                intializationcheck = "finished";
                
                jTextPane1.setText("");

            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
     }
    
    public void openmedicalrecordsreport(){
        try{        
                    //load report location
                    FileInputStream fis = new FileInputStream("/home/irv/Documents/ACAS Reports/ACAS EMR REPORTS/medical_records.jrxml");
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
                    //set parameters
                    Map map = new HashMap();
                    map.put("owner_id", ownerid);
                    map.put("pet_id", petid);
                    //compile report
                    JasperReport jasperReport = (JasperReport) JasperCompileManager.compileReport(bufferedInputStream);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, conn_db);

                    //view report to UI
                    JasperViewer.viewReport(jasperPrint, false);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void updateAppointment(){
        
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("d MMM yyyy");
        String dob2 = dateFormat2.format(jCalendar1.getDate());
        
        String string3 = dob2;
        String[] parts3 = string3.split(" ");
        String p1 = parts3[0];
        String p2 = parts3[1];
        String p3 = parts3[2];

        String day = p1;
        String month = p2;
        String year = p3;
        
        try{
            String sql = "UPDATE appointment SET `reason`= UPPER(?),`hour`= UPPER(?),`minute`= UPPER(?),`meridiem`= UPPER(?),`day`= UPPER(?),`month`= UPPER(?),`year`= UPPER(?) where appointment_id ='"+appointmentID+"'";
            ps=conn_db.prepareStatement(sql);
            
            ps.setString(1, jTextArea4.getText());
            ps.setString(2, hour.getValue().toString());
            ps.setString(3, minpatch);
            ps.setString(4, jComboBox6.getSelectedItem().toString());
            ps.setString(5, day);
            ps.setString(6, month);
            ps.setString(7, year);
            ps.executeUpdate();
            
            customMessage_Screen cm = new customMessage_Screen();
            cm.msg.setText("Pet's appointment has been successfully updated");
            cm.setVisible(true);
            
            audit_trail au = new audit_trail();
            au.action_type = "Updated - appointment information by "+ pet_name.getText();
            au.module_name = "Pet Chart";
            au.saveAuditTRail();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void openeaminationsreport(){
         try{        
                    //load report location
                    FileInputStream fis = new FileInputStream("/home/irv/Documents/ACAS Reports/ACAS EMR REPORTS/examinations_reports.jrxml");
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);
                    //set parameters
                    Map map = new HashMap();
                    map.put("pet_id", petid);
                    map.put("exam_number", examnumber);
                    //compile report
                    JasperReport jasperReport = (JasperReport) JasperCompileManager.compileReport(bufferedInputStream);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, conn_db);

                    //view report to UI
                    JasperViewer.viewReport(jasperPrint, false);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void viewConfinement(){
        jButton40.setEnabled(true);
        jButton36.setEnabled(true);
        try{
            String sql = "SELECT exam_number as Number, CONCAT_WS(' ', day, month, year) as Date FROM `examination_pr` WHERE name = 'CONFINEMENT' and status = 'ONGOING'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable3.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void viewRealeasedConfinement(){
        try{
            String sql = "SELECT exam_number as Number, CONCAT_WS(' ', day, month, year) as Date, consumed_duration as `Time Consumed` FROM `examination_pr` WHERE name = 'CONFINEMENT' and status = 'RELEASED'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable3.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void countConsumedTime(){
        DefaultTableModel model3 = (DefaultTableModel) jTable3.getModel();
        String examnum= model3.getValueAt(jTable3.getSelectedRow(), 0).toString();
        
        jLabel2.setText("");
        
        try{
            String sql = "SELECT exam_number as Number, CONCAT_WS(' ', day, month, year) as Date, time_consumed as Time FROM `examination_pr` WHERE (name = 'CONFINEMENT' and status = 'ONGOING') and exam_number = '"+examnum+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String datep1= rs.getString("Date");
                String timep2 = rs.getString("Time");
                
                String timeToday = new SimpleDateFormat("d MMM yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
                
                String timeConfined = datep1+" "+timep2;
                
                //HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("d MMM yyyy HH:mm:ss");

		Date d1 = null;
		Date d2 = null;

		try {
			d1 = format.parse(timeConfined);
			d2 = format.parse(timeToday);

			//in milliseconds
			long diff = d2.getTime() - d1.getTime();

			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			System.out.print(diffDays + " days, ");
			System.out.print(diffHours + " hours, ");
			System.out.print(diffMinutes + " minutes, ");
			System.out.print(diffSeconds + " seconds.");
                        
                        jLabel2.setText(diffDays+" day(s), "+diffHours+" hour(s), "+diffMinutes+" minute(s), "+diffSeconds+" second(s) ");

		} catch (Exception e) {
			e.printStackTrace();
		}
            }
            
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void releaseConfinement(){
         DefaultTableModel model3 = (DefaultTableModel) jTable3.getModel();
         String examnum= model3.getValueAt(jTable3.getSelectedRow(), 0).toString();
         
         try{
                String sql = "update examination_pr set `status`=(?), consumed_duration=(?) where exam_number ='" + examnum + "'";
                ps=conn_db.prepareStatement(sql);
                    ps.setString(1,"RELEASED");
                    ps.setString(2, jLabel2.getText());
                    ps.execute();
                JOptionPane.showMessageDialog(null, pet_name.getText() + " from confinement has been released", "Success", JOptionPane.INFORMATION_MESSAGE);
                jButton40.setEnabled(false);
                
            }catch(Exception e){

                JOptionPane.showMessageDialog(null, e);

            }
     }
    
    public void releaseUndo(){
         DefaultTableModel model3 = (DefaultTableModel) jTable3.getModel();
         String examnum= model3.getValueAt(jTable3.getSelectedRow(), 0).toString();
         try{
                String sql = "update examination_pr set `status`=(?), consumed_duration=(?) where exam_number ='" + examnum + "'";
                ps=conn_db.prepareStatement(sql);
                    ps.setString(1,"ONGOING");
                    ps.setString(2, "");
                    ps.execute();
                   JOptionPane.showMessageDialog(null, pet_name.getText() + " from confinement has been undone", "Success", JOptionPane.INFORMATION_MESSAGE);
                   jButton40.setEnabled(false);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
     }
}
