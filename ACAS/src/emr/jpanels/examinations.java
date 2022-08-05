/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emr.jpanels;

import audit_trailing.audit_trail;
import com.toedter.calendar.JCalendar;
import emr.jpanels.SQLClass.emr_codes;
import emr.jpanels.SQLClass.gettime;
import static emr.jpanels.pet_chart.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import login.connection_db;
import net.proteanit.sql.DbUtils;
import other.features.jpanels.customMessage_Screen;

/**
 *
 * @author irv
 */
public class examinations extends javax.swing.JFrame {

    Connection conn_db = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    gettime t = new gettime();
    
    String day = "";
    String month = "";
    String year = "";
    String diagnosisp2 = "";
    String prognosisp2 = "";
    
    String labexid = "";
    
    String phexid = "";
    
    public static String pr = "";

    /**
     * Creates new form vitals
     */
    public examinations() {
        initComponents();

        conn_db = connection_db.ConnectDB();

        //for centered frame////
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        //

        jTextField2.setEnabled(false);

        jButton3.setEnabled(false);

        jButton5.setEnabled(false);
        
        jRadioButton2.setSelected(true);
        diagnosisp2 = jRadioButton2.getText();
        
        jRadioButton3.setSelected(true);
        prognosisp2 = jRadioButton3.getText();

        autoViewPhEx();
        viewSaved();
    }
    
    public void ExamIDGenerate(){
        String timeStamp = new SimpleDateFormat("yyMMddHHmmss").format(Calendar.getInstance().getTime());
        jLabel1.setText(timeStamp);
    }

    public void collectFindings() {
        jTextArea2.setText("");
        int size = jTable2.getRowCount();//
        int f = size - 1;

        for (int x = 0; x <= f; x++) {
            if (x == f) {
                jTextArea2.append(jTable2.getValueAt(x, 0).toString().toUpperCase());
            } else {
                jTextArea2.append(jTable2.getValueAt(x, 0).toString().toUpperCase() + ", ");
            }
        }
    }

    public void collectComplaints() {
        jTextArea3.setText("");
        int size = jTable3.getRowCount();//
        int f = size - 1;

        for (int x = 0; x <= f; x++) {
            if (x == f) {
                jTextArea3.append(jTable3.getValueAt(x, 0).toString().toUpperCase());
            } else {
                jTextArea3.append(jTable3.getValueAt(x, 0).toString().toUpperCase() + ", ");
            }

        }
    }

    public void savePhysicalExam() {
        JCalendar dateset = new JCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
        String dob = dateFormat.format(dateset.getDate());
        String thisdate = dob;

        String string = thisdate;
        String[] parts = string.split(" ");
        String p1 = parts[0]; // 004
        String p2 = parts[1]; // 034556
        String p3 = parts[2]; // 034556

        day = p1;
        month = p2.toUpperCase();
        year = p3;
        
        t.setTime();

        try {
            String sql = "Insert into physical_exam (`pet_id`,`exam_number`,`crh`,`findings`,`weight`,`temp`,`doa`,`hour`,`minute`,`meridiem`,`day`,`month`,`year`) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = conn_db.prepareStatement(sql);
            ps.setString(1, petid);
            ps.setString(2, jLabel1.getText());
            ps.setString(3, jTextArea3.getText().toUpperCase());
            ps.setString(4, jTextArea2.getText().toUpperCase());
            ps.setString(5, jTextField3.getText() + " " + jComboBox2.getSelectedItem().toString());
            ps.setString(6, jTextField4.getText() + " " + jComboBox3.getSelectedItem().toString());
            ps.setString(7, jTextArea1.getText().toUpperCase());
            ps.setString(8, t.H);
            ps.setString(9, t.M);
            ps.setString(10, t.A);
            ps.setString(11, day);
            ps.setString(12, month);
            ps.setString(13, year);
            ps.execute();
            
            savetoExamPR();
            
            audit_trail au = new audit_trail();
            au.action_type = "Saved - physical examination information by "+ pet_name.getText();
            au.module_name = "Pet Chart";
            au.saveAuditTRail();
            
            JOptionPane.showMessageDialog(null, "Physical examination has been successfully stored");

            autoViewPhEx();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void updateExamPR() {
        
        try {
            String sql = "UPDATE physical_exam SET name = UPPER(?) where exam_number ='" + jLabel1.getText() + "'";
            ps = conn_db.prepareStatement(sql);

            ps.setString(1, pr.toUpperCase().toUpperCase());
            ps.executeUpdate();
            
            System.err.println("Physical examination pr has been successfully updated");
            
            audit_trail au = new audit_trail();
            au.action_type = "Updated - physical examination information by "+ pet_name.getText();
            au.module_name = "Pet Chart";
            au.saveAuditTRail();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void savetoExamPR(){
        String timeStamp = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
        try {
            String sql = "Insert into examination_pr (`pet_id`,`exam_number`,`name`,`day`,`month`,`year`,`time_consumed`,`status`) values (?,?,?,?,?,?,?,?)";
            ps = conn_db.prepareStatement(sql);
            ps.setString(1, petid);
            ps.setString(2, jLabel1.getText());
            ps.setString(3, pr.toUpperCase());
            ps.setString(4, day);
            ps.setString(5, month);
            ps.setString(6, year);
            ps.setString(7, timeStamp);
            ps.setString(8, "ONGOING");
            ps.execute();
            
            System.out.println("Saved to examination PR");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void ResetPhEx() {
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();

        int rows = model.getRowCount();
        for (int i = rows - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        ///

        DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();

        int rows2 = model2.getRowCount();
        for (int i = rows2 - 1; i >= 0; i--) {
            model2.removeRow(i);
        }

        ///

        jTextArea3.setText("");
        jTextArea2.setText("");

        ///

        jTextField3.setText("");
        jTextField4.setText("");

        ///

        jTextArea1.setText("");
    }

    public void autoViewPhEx() {
        try {
            String sql = "SELECT id AS `ID`, crh AS `Complaint(s)/Remark(s)/History`, findings AS `Examination Findings`, weight AS `Weight`, temp AS `Temp`, doa AS `Abnormalities`, CONCAT_WS(' ', day, month, year) AS `Date Visit` FROM `physical_exam` WHERE  pet_id = '" + petid + "' ORDER BY year";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void setUpdate() {
        DefaultTableModel model3 = (DefaultTableModel) jTable1.getModel();
        
        phexid = model3.getValueAt(jTable1.getSelectedRow(), 0).toString();
        
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        int rows = model.getRowCount();
        for (int i = rows - 1; i >= 0; i--) {
            model.removeRow(i);
        }

        DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();
        int rows2 = model2.getRowCount();
        for (int i = rows2 - 1; i >= 0; i--) {
            model2.removeRow(i);
        }
        
        try {
            String sql = "SELECT * FROM `physical_exam` inner join examination_pr on physical_exam.exam_number = examination_pr.exam_number WHERE  physical_exam.pet_id = '" + petid + "' and physical_exam.id='"+phexid+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String getcrh[] = rs.getString("crh").split(", ");
                int f = getcrh.length;

                int i = f - 1;

                for (int x = 0; x <= i; x++) {
                    model.addRow(new Object[]{getcrh[x]});
                }

                try{
                   String getfindings[] = rs.getString("physical_exam.findings").split(", ");
                int ff = getcrh.length;

                int ii = ff - 1;

                for (int x = 0; x <= ii; x++) {
                    model2.addRow(new Object[]{getfindings[x]});
                } 
                }catch(Exception e){
                    
                }

                String getWeight = rs.getString("physical_exam.weight");
                String[] parts = getWeight.split(" ");
                String p1 = parts[0];
                String p2 = parts[1];

                jComboBox2.setSelectedItem(p2);
                jTextField3.setText(p1);

                String getTemp = rs.getString("physical_exam.temp");
                String[] parts2 = getTemp.split(" ");
                String p11 = parts2[0];
                String p22 = parts2[1];

                jComboBox3.setSelectedItem(p22);
                jTextField4.setText(p11);

                String getdoa = rs.getString("physical_exam.doa");
                jTextArea1.setText(getdoa);
                
                if(rs.getString("examination_pr.name").equals("CONFINEMENT")){
                    jRadioButton6.setSelected(true);
                    jRadioButton7.setSelected(false);
                    jRadioButton8.setSelected(false);
                    jRadioButton9.setSelected(false);
                }
                
                if(rs.getString("examination_pr.name").equals("TREATMENT")){
                    jRadioButton6.setSelected(false);
                    jRadioButton7.setSelected(true);
                    jRadioButton8.setSelected(false);
                    jRadioButton9.setSelected(false);
                }
                
                if(rs.getString("examination_pr.name").equals("OPERATION")){
                    jRadioButton6.setSelected(false);
                    jRadioButton7.setSelected(false);
                    jRadioButton8.setSelected(true);
                    jRadioButton9.setSelected(false);
                }
                
                if(rs.getString("examination_pr.name").equals("GROOMING")){
                    jRadioButton6.setSelected(false);
                    jRadioButton7.setSelected(false);
                    jRadioButton8.setSelected(false);
                    jRadioButton9.setSelected(true);
                }
                
                jLabel1.setText(rs.getString("physical_exam.exam_number"));

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void updatePheX() {
        
        try {
            String sql = "UPDATE physical_exam SET crh = UPPER(?), findings = UPPER(?), weight = UPPER(?), temp = UPPER(?), doa = UPPER(?) where id ='" + phexid + "'";
            ps = conn_db.prepareStatement(sql);

            ps.setString(1, jTextArea3.getText().toUpperCase());
            ps.setString(2, jTextArea2.getText().toUpperCase());
            ps.setString(3, jTextField3.getText() + " " + jComboBox2.getSelectedItem().toString());
            ps.setString(4, jTextField4.getText() + " " + jComboBox3.getSelectedItem().toString());
            ps.setString(5, jTextArea1.getText());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Physical examination has been successfully updated");
            
            audit_trail au = new audit_trail();
            au.action_type = "Updated - physical examination information by "+ pet_name.getText();
            au.module_name = "Pet Chart";
            au.saveAuditTRail();
            
            autoViewPhEx();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void collectlabexDiagnosis() {
        jTextArea5.setText("");
        int size = jTable6.getRowCount();//
        int f = size - 1;

        for (int x = 0; x <= f; x++) {
            if (x == f) {
                jTextArea5.append(jTable6.getValueAt(x, 0).toString().toUpperCase());
            } else {
                jTextArea5.append(jTable6.getValueAt(x, 0).toString().toUpperCase() + ", ");
            }

        }
    }

    public void collectlabexPrognosis() {
        jTextArea6.setText("");
        int size = jTable7.getRowCount();//
        int f = size - 1;

        for (int x = 0; x <= f; x++) {
            if (x == f) {
                jTextArea6.append(jTable7.getValueAt(x, 0).toString().toUpperCase());
            } else {
                jTextArea6.append(jTable7.getValueAt(x, 0).toString().toUpperCase() + ", ");
            }

        }
    }

    public void collectmedicineGiven() {
        jTextArea7.setText("");
        int size = jTable5.getRowCount();//
        int f = size - 1;

        for (int x = 0; x <= f; x++) {
            if (x == f) {
                jTextArea7.append(jTable5.getValueAt(x, 0).toString().toUpperCase() + " / " + jTable5.getValueAt(x, 1).toString().toUpperCase() + " / " + jTable5.getValueAt(x, 2).toString().toUpperCase() + " / " + jTable5.getValueAt(x, 3).toString().toUpperCase());
            } else {
                jTextArea7.append(jTable5.getValueAt(x, 0).toString().toUpperCase() + " / " + jTable5.getValueAt(x, 1).toString().toUpperCase() + " / " + jTable5.getValueAt(x, 2).toString().toUpperCase() + " / " + jTable5.getValueAt(x, 3).toString().toUpperCase() + ", ");
            }

        }
    }

    public void collectprescription() {
        jTextArea8.setText("");
        int size = jTable8.getRowCount();//
        int f = size - 1;

        for (int x = 0; x <= f; x++) {
            if (x == f) {
                jTextArea8.append(jTable8.getValueAt(x, 0).toString().toUpperCase() + " / " + jTable8.getValueAt(x, 1).toString().toUpperCase() + " / " + jTable8.getValueAt(x, 2).toString().toUpperCase());
            } else {
                jTextArea8.append(jTable8.getValueAt(x, 0).toString().toUpperCase() + " / " + jTable8.getValueAt(x, 1).toString().toUpperCase() + " / " + jTable8.getValueAt(x, 2).toString().toUpperCase() + ", ");
            }

        }
    }

    public void saveToLabex() {
        JCalendar dateset = new JCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
        String dob = dateFormat.format(dateset.getDate());
        String thisdate = dob;

        String string = thisdate;
        String[] parts = string.split(" ");
        String p1 = parts[0]; // 004
        String p2 = parts[1]; // 034556
        String p3 = parts[2]; // 034556

        day = p1;
        month = p2.toUpperCase();
        year = p3;

        try {
            String sql = "Insert into laboratory_exam (`pet_id`,`exam_number`,`stool`,`cpv`,`heartworm`,`diagnosis`,`diagnosis_p2`,`blood`,`ehr`,`others`,`prognosis`,`prognosis_p2`,`vscraping`,`vsmearing`,`medicine_given`,`prescription`,`attending_vet`,`amount_paid`,`day`,`month`,`year`) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = conn_db.prepareStatement(sql);
            ps.setString(1, petid);//pet_id
            ps.setString(2, jLabel1.getText());
            ps.setString(3, jTextField9.getText().toUpperCase());//stool
            ps.setString(4, jTextField10.getText().toUpperCase());//cpv
            ps.setString(5, jTextField11.getText().toUpperCase());//heartworm
            ps.setString(6, jTextArea5.getText().toUpperCase());//diagnosis
            ps.setString(7, diagnosisp2.toUpperCase());//diagnosis_p2
            ps.setString(8, jTextField13.getText().toUpperCase());//blood
            ps.setString(9, jTextField14.getText().toUpperCase());//ehr
            ps.setString(10, jTextField15.getText().toUpperCase());//others
            ps.setString(11, jTextArea6.getText().toUpperCase());//prognosis
            ps.setString(12, prognosisp2.toUpperCase());//prognosis_p2
            ps.setString(13, jTextField16.getText().toUpperCase());//vscraping
            ps.setString(14, jTextField17.getText().toUpperCase());//vsmearing
            ps.setString(15, jTextArea7.getText().toUpperCase());//vscraping
            ps.setString(16, jTextArea8.getText().toUpperCase());//vsmearing
            ps.setString(17, jTextField20.getText().toUpperCase());//att vet
            ps.setString(18, jTextField21.getText().toUpperCase());//amount paid
            ps.setString(19, day);//
            ps.setString(20, month);//
            ps.setString(21, year);//d
            ps.execute();
            
            audit_trail au = new audit_trail();
            au.action_type = "Saved - laboratory examination information by "+ pet_name.getText();
            au.module_name = "Pet Chart";
            au.saveAuditTRail();
            
            JOptionPane.showMessageDialog(null, "Laboratory examination has been successfully stored");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void setCheckFields() {
        if (jTextField9.getText().equals("")||jTextField10.getText().equals("")||jTextField11.getText().equals("")||jTextField13.getText().equals("")||jTextField14.getText().equals("")||jTextField15.getText().equals("")||jTextField16.getText().equals("")||jTextField17.getText().equals("")||jTextArea5.getText().equals("")||diagnosisp2.equals("")||jTextArea6.getText().equals("")||prognosisp2.equals("")) {
            if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Some field(s) were left empty\nAre you sure you want to save?",
            "Save Validation", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            saveToLabex();
            viewSaved();
        }
        else {
            System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
            return;
        }
        }else{
            saveToLabex();
            viewSaved();
        }
    }
    
    public void viewSaved(){
        DefaultListModel dimx = new DefaultListModel();
         try{
             String sql = "SELECT CONCAT_WS('', '#',id) AS `ID`, CONCAT_WS(' ', day, month, year) AS `DOE` FROM `laboratory_exam` WHERE pet_id = '"+petid+"'";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             
             while(rs.next()){
                 String getID = rs.getString("ID");
                 String getDOE = rs.getString("DOE");
                 String cont = "--File saved on " + getDOE +"--ID"+getID;
                 dimx.addElement(cont);
                 jList1.setModel(dimx);
             }
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
    }
    
    public void clearlabexTables(){
        DefaultTableModel model1 = (DefaultTableModel) jTable6.getModel();
        int rows1 = model1.getRowCount();
        for (int i = rows1 - 1; i >= 0; i--) {
            model1.removeRow(i);
        }
        
        DefaultTableModel model2 = (DefaultTableModel) jTable7.getModel();
        int rows2 = model2.getRowCount();
        for (int i = rows2 - 1; i >= 0; i--) {
            model2.removeRow(i);
        }
        
        DefaultTableModel model3 = (DefaultTableModel) jTable5.getModel();
        int rows3 = model3.getRowCount();
        for (int i = rows3 - 1; i >= 0; i--) {
            model3.removeRow(i);
        }
        
        DefaultTableModel model4 = (DefaultTableModel) jTable8.getModel();
        int rows4 = model4.getRowCount();
        for (int i = rows4 - 1; i >= 0; i--) {
            model4.removeRow(i);
        }
    }
    
    public void setUpdateLabex(){
        clearlabexTables();
        DefaultTableModel model1 = (DefaultTableModel) jTable6.getModel();
        DefaultTableModel model2 = (DefaultTableModel) jTable7.getModel();
        DefaultTableModel model3 = (DefaultTableModel) jTable5.getModel();
        DefaultTableModel model4 = (DefaultTableModel) jTable8.getModel();
        
        try{
             String sql = "SELECT * FROM `laboratory_exam` WHERE id = '"+labexid+"'";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             
             while(rs.next()){
                 String getstool = rs.getString("stool");
                 jTextField9.setText(getstool);
                 
                 String getcpv = rs.getString("cpv");
                 jTextField10.setText(getcpv);
                 
                 String getheartworm = rs.getString("heartworm");
                 jTextField11.setText(getheartworm);
                 
                 /////////////////////////////////////////////////////////////
                 try{
                     String getdiagnosis[] = rs.getString("diagnosis").split(", ");
                    int f = getdiagnosis.length;

                    int i = f - 1;

                    for (int x = 0; x <= i; x++) {
                        model1.addRow(new Object[]{getdiagnosis[x]});
                    }
                 }catch(Exception e){
                     
                 }
                 /////////////////////////////////////////////////////////////
                 String getdiagnosisp2 = rs.getString("diagnosis_p2");
                 if(getdiagnosisp2.equals("")){
                     jRadioButton2.setSelected(false);
                     jRadioButton1.setSelected(false);
                 }else{
                     if(getdiagnosisp2.equals("TENTATIVE")){
                         jRadioButton2.setSelected(true);
                         jRadioButton1.setSelected(false);
                     }else{
                         jRadioButton2.setSelected(false);
                         jRadioButton1.setSelected(true);
                     }
                 }
                 
                 String getblood = rs.getString("blood");
                 jTextField13.setText(getblood);
                 
                 String getehr= rs.getString("ehr");
                 jTextField14.setText(getehr);
                 
                 String getothers= rs.getString("others");
                 jTextField15.setText(getothers);
                 //////////////////////////////////////////////////////////////
                 try{
                     String getprognosis[] = rs.getString("prognosis").split(", ");
                     int f1 = getprognosis.length;

                     int i1 = f1 - 1;

                     for (int x = 0; x <= i1; x++) {
                         model2.addRow(new Object[]{getprognosis[x]});
                     }
                 }catch(Exception e){
                     
                 }
                 ////////////////////////////////////////////////////////////////
                  String getprognosisp2 = rs.getString("prognosis_p2");
                 if(getprognosisp2.equals("FAVORABLE")){
                     jRadioButton3.setSelected(true);
                     jRadioButton4.setSelected(false);
                     jRadioButton5.setSelected(false);
                 }else{
                     if(getprognosisp2.equals("GUARDED ")){
                         jRadioButton3.setSelected(false);
                         jRadioButton4.setSelected(false);
                         jRadioButton5.setSelected(true);
                     }else{
                         jRadioButton3.setSelected(false);
                         jRadioButton4.setSelected(true);
                         jRadioButton5.setSelected(false);
                     }
                 }
                 
                 String getvscraping = rs.getString("vscraping");
                 jTextField16.setText(getvscraping);
                 
                 String getvsmearing = rs.getString("vsmearing");
                 jTextField17.setText(getvsmearing);
                 //////////////////////////////////////////////////////////////////
                 try{
                    String getmedgiven[] = rs.getString("medicine_given").split(", ");
                    int f2 = getmedgiven.length;
                    int i2 = f2 - 1;

                    for (int x = 0; x <= i2; x++) {
                        String string = getmedgiven[x];
                        String[] parts = string.split(" / ");
                        String p1 = parts[0]; // 004
                        String p2 = parts[1]; // 034556
                        String p3 = parts[2]; // 034556
                        String p4 = parts[3]; // 034556

                        model3.addRow(new Object[]{p1,p2,p3,p4});
                    }
                 }catch(Exception e){
                     
                 }
                 //////////////////////////////////////////////////////////////////
                 try{
                    String getpresc[] = rs.getString("prescription").split(", ");
                    int f3 = getpresc.length;
                    int i3 = f3 - 1;

                    for (int x = 0; x <= i3; x++) {
                        String string = getpresc[x];
                        String[] parts = string.split(" / ");
                        String p1 = parts[0]; // 004
                        String p2 = parts[1]; // 034556
                        String p3 = parts[2]; // 034556

                        model4.addRow(new Object[]{p1,p2,p3});
                    } 
                 }catch(Exception e){
                     
                 }
                 /////////////////////////////////////////////////////////////////////
                 String getAttvet = rs.getString("attending_vet");
                jTextField20.setText(getAttvet);

                String getAmntPd = rs.getString("amount_paid");
                jTextField21.setText(getAmntPd);
             }
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
        
        
        /////
        
        try{
             String sql = "SELECT * FROM laboratory_exam inner join examination_pr on laboratory_exam.exam_number = examination_pr.exam_number WHERE laboratory_exam.pet_id = '"+petid+"'";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             
             while(rs.next()){
                 if(rs.getString("examination_pr.name").equals("CONFINEMENT")){
                    jRadioButton6.setSelected(true);
                    jRadioButton7.setSelected(false);
                    jRadioButton8.setSelected(false);
                    jRadioButton9.setSelected(false);
                }
                
                if(rs.getString("examination_pr.name").equals("TREATMENT")){
                    jRadioButton6.setSelected(false);
                    jRadioButton7.setSelected(true);
                    jRadioButton8.setSelected(false);
                    jRadioButton9.setSelected(false);
                }
                
                if(rs.getString("examination_pr.name").equals("OPERATION")){
                    jRadioButton6.setSelected(false);
                    jRadioButton7.setSelected(false);
                    jRadioButton8.setSelected(true);
                    jRadioButton9.setSelected(false);
                }
                
                if(rs.getString("examination_pr.name").equals("GROOMING")){
                    jRadioButton6.setSelected(false);
                    jRadioButton7.setSelected(false);
                    jRadioButton8.setSelected(false);
                    jRadioButton9.setSelected(true);
                }
             }
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
        
        
    }
    
    public void ResetFields(){
        jTextField9.setText("");
        jTextField10.setText("");
        jTextField11.setText("");
        jTextField13.setText("");
        jTextField14.setText("");
        jTextField15.setText("");
        jTextField16.setText("");
        jTextField17.setText("");
        jRadioButton2.setSelected(true);
        jRadioButton3.setSelected(true);
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField19.setText("");
        jButton8.setText("Add");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextArea4.setText("");
        jButton6.setText("Add");
        jTextField20.setText("");
        jTextField21.setText("");
        clearlabexTables();
    }
    
    public void updatelabex(){
        try{
            String sql = "UPDATE laboratory_exam SET stool = UPPER(?), cpv = UPPER(?), heartworm = UPPER(?), diagnosis = UPPER(?), diagnosis_p2 = UPPER(?), blood = UPPER(?), ehr = UPPER(?) , others = UPPER(?) , prognosis = UPPER(?) , prognosis_p2 = UPPER(?) , vscraping = UPPER(?) , vsmearing = UPPER(?) , medicine_given = UPPER(?), prescription = UPPER(?), attending_vet = UPPER(?), amount_paid = UPPER(?)  where id ='"+labexid+"'";
            ps=conn_db.prepareStatement(sql);
            
            ps.setString(1, jTextField9.getText().toUpperCase());//stool
            ps.setString(2, jTextField10.getText().toUpperCase());//cpv
            ps.setString(3, jTextField11.getText().toUpperCase());//heartworm
            ps.setString(4, jTextArea5.getText().toUpperCase());//diagnosis
            ps.setString(5, diagnosisp2.toUpperCase());//diagnosis_p2
            ps.setString(6, jTextField13.getText().toUpperCase());//blood
            ps.setString(7, jTextField14.getText().toUpperCase());//ehr
            ps.setString(8, jTextField15.getText().toUpperCase());//others
            ps.setString(9, jTextArea6.getText().toUpperCase());//prognosis
            ps.setString(10, prognosisp2.toUpperCase());//prognosis_p2
            ps.setString(11, jTextField16.getText().toUpperCase());//vscraping
            ps.setString(12, jTextField17.getText().toUpperCase());//vsmearing
            ps.setString(13, jTextArea7.getText().toUpperCase());//vscraping
            ps.setString(14, jTextArea8.getText().toUpperCase());//vsmearing
            ps.setString(15, jTextField20.getText().toUpperCase());//vscraping
            ps.setString(16, jTextField21.getText().toUpperCase());//vsmearing
            ps.executeUpdate();
            
            audit_trail au = new audit_trail();
            au.action_type = "Updated - laboratory examination information by "+ pet_name.getText();
            au.module_name = "Pet Chart";
            au.saveAuditTRail();
            
            JOptionPane.showMessageDialog(null, "Laboratory examination has been successfully updated");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void autoViewPhExVitals(){
        try{
            String sql = "SELECT weight AS `Weight`, temp AS `Temp`, CONCAT_WS(' ', day, month, year) AS `Date Visit` FROM `physical_exam` WHERE  pet_id = '"+petid+"' ORDER BY year";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            vitals_table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jToolBar2 = new javax.swing.JToolBar();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        jComboBox4 = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        jTextField6 = new javax.swing.JTextField();
        jComboBox5 = new javax.swing.JComboBox();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jTextField19 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jTextField7 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jTextField8 = new javax.swing.JTextField();
        jComboBox6 = new javax.swing.JComboBox();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTextArea6 = new javax.swing.JTextArea();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTextArea7 = new javax.swing.JTextArea();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTextArea8 = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Examinations");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Examination", 0, 0, null, new java.awt.Color(29, 29, 29)));
        jTabbedPane1.setForeground(new java.awt.Color(1, 1, 1));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(1, 1, 1));
        jTextField1.setToolTipText("Press \"Enter\" to enter");
        jTextField1.setBorder(javax.swing.BorderFactory.createTitledBorder("Complaints/Remarks/History"));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 240, 60));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Examination"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox1.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(1, 1, 1));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "GENERAL CONDITION", "HYDRATION", "MUCOUS MEMBRANE", "HEAD/NECK", "EYES", "EARS", "RESPIRATORY", "GASTROINTESTINAL", "UROGENITAL", "LYMPH NODES", "MUSCULO-SKELETAL", "INTEGUMENTARY", "ORAL CAVITY" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel3.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 220, -1));

        jTextField2.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(1, 1, 1));
        jTextField2.setToolTipText("Press \"Enter\" to enter");
        jTextField2.setBorder(javax.swing.BorderFactory.createTitledBorder("Finding(s)"));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 220, 60));

        jTable2.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jTable2.setForeground(new java.awt.Color(1, 1, 1));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Findings Overview"
            }
        ));
        jScrollPane4.setViewportView(jTable2);

        jPanel3.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 220, 160));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 240, 300));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Vitals"));

        jTextField3.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(1, 1, 1));
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });

        jTextField4.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(1, 1, 1));
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });

        jLabel2.setText("Weight");

        jLabel3.setText("Temperature");

        jComboBox2.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(1, 1, 1));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "lb", "kg" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jComboBox3.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jComboBox3.setForeground(new java.awt.Color(1, 1, 1));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "°C", "°F" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addComponent(jTextField4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39))
        );

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 240, 170));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Description of abnormalities"));

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(1, 1, 1));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 60, 540, 170));

        jTable1.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jTable1.setForeground(new java.awt.Color(1, 1, 1));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 250, 810, 300));

        jToolBar1.setRollover(true);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Add Green Button.png"))); // NOI18N
        jButton2.setText("Save");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Write Document.png"))); // NOI18N
        jButton3.setText("Update");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Clear Green Button.png"))); // NOI18N
        jButton1.setText("Reset Field");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        jPanel1.add(jToolBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1180, 40));

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane5.setViewportView(jTextArea2);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 600, 440, 0));

        jTable3.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jTable3.setForeground(new java.awt.Color(1, 1, 1));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Overview"
            }
        ));
        jScrollPane6.setViewportView(jTable3);

        jPanel1.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 240, 100));

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane1.setViewportView(jTextArea3);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 611, 400, 0));

        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1089, 230, 0, -1));

        jTabbedPane1.addTab("Physical Examination", jPanel1);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToolBar2.setRollover(true);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Add Green Button.png"))); // NOI18N
        jButton4.setText("Save");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton4);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Write Document.png"))); // NOI18N
        jButton5.setText("Update");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton5);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Clear Green Button.png"))); // NOI18N
        jButton7.setText("Reset Field");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton7);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/New Document.png"))); // NOI18N
        jButton9.setText("Set Appointment");
        jButton9.setFocusable(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton9);

        jPanel2.add(jToolBar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1180, 40));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("MEDICINE GIVEN"));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField5.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(1, 1, 1));
        jTextField5.setBorder(javax.swing.BorderFactory.createTitledBorder("Medicne Name"));
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jPanel5.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 17, 245, 56));

        jComboBox4.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jComboBox4.setForeground(new java.awt.Color(1, 1, 1));
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "pill", "tablet", "capsule", "capsule Syrup", "powder", "cream", "gel", "liniment", "ear Drops", "eye Drops", "liquid", "syrup" }));
        jComboBox4.setBorder(javax.swing.BorderFactory.createTitledBorder("Dose Unit"));
        jPanel5.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 133, 245, 56));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Volume"));

        jTextField6.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jTextField6.setForeground(new java.awt.Color(1, 1, 1));
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField6KeyTyped(evt);
            }
        });

        jComboBox5.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jComboBox5.setForeground(new java.awt.Color(1, 1, 1));
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "cc", "cm", "mg", "ml", "mm", "l", "g", "kg", "oz", "lb" }));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 79, 240, 50));

        jTable5.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jTable5.setForeground(new java.awt.Color(1, 1, 1));
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine", "Volume", "Dose Unit", "Usage"
            }
        ));
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jTable5);

        jPanel5.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(257, 17, 360, 260));

        jButton8.setText("Add");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 119, -1));

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Usage"));

        jTextField19.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jTextField19.setForeground(new java.awt.Color(1, 1, 1));
        jTextField19.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField19KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel5.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 240, 60));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 630, 290));

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("PRESCRIPTION"));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField7.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jTextField7.setForeground(new java.awt.Color(1, 1, 1));
        jTextField7.setBorder(javax.swing.BorderFactory.createTitledBorder("Rx"));
        jPanel8.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 17, 209, 55));

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Volume"));

        jTextField8.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jTextField8.setForeground(new java.awt.Color(1, 1, 1));
        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField8KeyTyped(evt);
            }
        });

        jComboBox6.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jComboBox6.setForeground(new java.awt.Color(1, 1, 1));
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "cc", "cm", "mg", "ml", "mm", "l", "g", "kg", "oz", "lb" }));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel8.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 78, -1, -1));

        jTextArea4.setColumns(20);
        jTextArea4.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jTextArea4.setForeground(new java.awt.Color(1, 1, 1));
        jTextArea4.setLineWrap(true);
        jTextArea4.setRows(5);
        jTextArea4.setWrapStyleWord(true);
        jTextArea4.setBorder(javax.swing.BorderFactory.createTitledBorder("Sig"));
        jScrollPane9.setViewportView(jTextArea4);

        jPanel8.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 132, 209, 110));

        jTable8.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jTable8.setForeground(new java.awt.Color(1, 1, 1));
        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rx", "Volume", "Sig"
            }
        ));
        jTable8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable8MouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(jTable8);

        jPanel8.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(227, 17, 233, 260));

        jButton6.setText("Add");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 120, -1));

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 280, 470, 290));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("STOOL");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 50, 20));

        jTextField9.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jTextField9.setForeground(new java.awt.Color(1, 1, 1));
        jPanel2.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 190, -1));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("CPV/CDV");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 80, 20));

        jTextField10.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jTextField10.setForeground(new java.awt.Color(1, 1, 1));
        jPanel2.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 190, -1));

        jTextField11.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jTextField11.setForeground(new java.awt.Color(1, 1, 1));
        jPanel2.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 190, -1));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("HEARTWORM");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 90, 20));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("DIAGNOSIS");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 90, 20));

        jTextField12.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jTextField12.setForeground(new java.awt.Color(1, 1, 1));
        jTextField12.setToolTipText("Press \"Enter\" to enter");
        jTextField12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField12ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 190, -1));

        jTable6.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jTable6.setForeground(new java.awt.Color(1, 1, 1));
        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Diagnosis"
            }
        ));
        jScrollPane10.setViewportView(jTable6);

        jPanel2.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 190, 90));

        jRadioButton1.setText("Definitive");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, -1, -1));

        jRadioButton2.setText("Tentative");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, -1, -1));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("BLOOD");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, 50, 20));

        jTextField13.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jTextField13.setForeground(new java.awt.Color(1, 1, 1));
        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, 190, -1));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("EHRLICHIA");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, 80, 20));

        jTextField14.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jTextField14.setForeground(new java.awt.Color(1, 1, 1));
        jPanel2.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 190, -1));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("OTHERS");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, 90, 20));

        jTextField15.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jTextField15.setForeground(new java.awt.Color(1, 1, 1));
        jPanel2.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 120, 190, -1));

        jTextField16.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jTextField16.setForeground(new java.awt.Color(1, 1, 1));
        jPanel2.add(jTextField16, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 60, 190, -1));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("S. SCRAPING");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 60, 100, 20));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Amount Paid");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 100, 110, 20));

        jTextField17.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jTextField17.setForeground(new java.awt.Color(1, 1, 1));
        jPanel2.add(jTextField17, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 90, 190, -1));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("PROGNOSIS");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, 90, 20));

        jTextField18.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jTextField18.setForeground(new java.awt.Color(1, 1, 1));
        jTextField18.setToolTipText("Press \"Enter\" to enter");
        jTextField18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField18ActionPerformed(evt);
            }
        });
        jPanel2.add(jTextField18, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 150, 190, -1));

        jTable7.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jTable7.setForeground(new java.awt.Color(1, 1, 1));
        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Prognosis"
            }
        ));
        jScrollPane11.setViewportView(jTable7);

        jPanel2.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 180, 190, 90));

        jRadioButton3.setText("FAVORABLE");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 180, -1, -1));

        jRadioButton4.setText("POOR");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 220, -1, -1));

        jRadioButton5.setText("GUARDED");
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 200, -1, -1));

        jList1.setBorder(javax.swing.BorderFactory.createTitledBorder("Saved File(s)"));
        jList1.setFont(new java.awt.Font("DejaVu Sans", 0, 13)); // NOI18N
        jList1.setForeground(new java.awt.Color(1, 1, 1));
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jList1);

        jPanel2.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 160, 330, 110));

        jTextArea5.setColumns(20);
        jTextArea5.setRows(5);
        jScrollPane13.setViewportView(jTextArea5);

        jPanel2.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 0, -1));

        jTextArea6.setColumns(20);
        jTextArea6.setRows(5);
        jScrollPane14.setViewportView(jTextArea6);

        jPanel2.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 0, -1));

        jTextArea7.setColumns(20);
        jTextArea7.setRows(5);
        jScrollPane15.setViewportView(jTextArea7);

        jPanel2.add(jScrollPane15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 0, -1));

        jTextArea8.setColumns(20);
        jTextArea8.setRows(5);
        jScrollPane16.setViewportView(jTextArea8);

        jPanel2.add(jScrollPane16, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, 0, -1));

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("V. SMEARING");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 90, 100, 20));
        jPanel2.add(jTextField20, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 120, 190, -1));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Attending Vet");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 120, 100, 20));

        jTextField21.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField21KeyTyped(evt);
            }
        });
        jPanel2.add(jTextField21, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 120, 120, -1));

        jTabbedPane1.addTab("Laboratory Examination", jPanel2);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 1170, 650));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("Purpose / Reason");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jRadioButton6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton6.setText("Confinement");
        jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, -1, -1));

        jRadioButton7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton7.setText("Treatment");
        jRadioButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, -1, -1));

        jRadioButton8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton8.setText("Operation");
        jRadioButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, -1, -1));

        jRadioButton9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton9.setText("Grooming");
        jRadioButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        jTextField2.setEnabled(true);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c)
                || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE || evt.getKeyChar() == '.')) {
            //  evt.getKeyChar() == '.' does accept point when jtextfield accepts decimal number
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c)
                || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE || evt.getKeyChar() == '.')) {
            //  evt.getKeyChar() == '.' does accept point when jtextfield accepts decimal number
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_jTextField4KeyTyped

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        if (jComboBox2.getSelectedItem().toString().equals("lb")) {
            if (jTextField3.getText().equals("")) {
                //JOptionPane.showMessageDialog(null, "Please input for lb");
            } else {
                BigDecimal weight = new BigDecimal(jTextField3.getText());
                BigDecimal two = new BigDecimal("2.2");
                BigDecimal ans = weight.multiply(two);
                jTextField3.setText(ans.toString());
            }
        } else {
            if (jComboBox2.getSelectedItem().toString().equals("kg")) {
                if (jTextField3.getText().equals("")) {
                    //JOptionPane.showMessageDialog(null, "Please input for kg");
                } else {
                    BigDecimal weight = new BigDecimal(jTextField3.getText());
                    BigDecimal two = new BigDecimal("2.2");
                    BigDecimal ans;
                    ans = weight.divide(two, 1, RoundingMode.CEILING);
                    jTextField3.setText(ans.toString());
                }
            }
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        if (jComboBox3.getSelectedItem().toString().equals("°F")) {
            if (jTextField4.getText().equals("")) {
                //JOptionPane.showMessageDialog(null, "Please input for °F");
            } else {
                BigDecimal gettemp = new BigDecimal(jTextField4.getText());
                BigDecimal nine = new BigDecimal("9");
                BigDecimal five = new BigDecimal("5");
                BigDecimal threetwo = new BigDecimal("32");
                BigDecimal ans = gettemp.multiply(nine.divide(five, 1, RoundingMode.CEILING)).add(threetwo);
                jTextField4.setText(ans.toString());
            }

        } else {
            if (jTextField4.getText().equals("")) {
                //JOptionPane.showMessageDialog(null, "Please input for °C");
            } else {
                BigDecimal gettemp = new BigDecimal(jTextField4.getText());
                BigDecimal nine = new BigDecimal("9");
                BigDecimal five = new BigDecimal("5");
                BigDecimal threetwo = new BigDecimal("32");
                BigDecimal ans = gettemp.subtract(threetwo).multiply(five.divide(nine, 1, RoundingMode.CEILING));
                jTextField4.setText(ans.toString());
            }
        }
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.addRow(new Object[]{jComboBox1.getSelectedItem().toString() + " - " + jTextField2.getText().toUpperCase()});
        jTextField2.setText("");
        jTextField2.setEnabled(false);
        int size = model.getRowCount();
        int f = size - 1;

        for (int x = 1; x <= f; x++) {
            if (jTable2.getValueAt(f, 0).toString().equals(jTable2.getValueAt(x - 1, 0).toString())) {
                //JOptionPane.showMessageDialog(null,"Magkaparehas!"+"\n"+diagnose.getValueAt(f, 0).toString()+" is equal sa "+diagnose.getValueAt(x-1, 0).toString());
                model.removeRow(f);
                f--;

            } else {
                //JOptionPane.showMessageDialog(null,"Add pa more! ");
            }
        }
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        model.addRow(new Object[]{jTextField1.getText().toUpperCase()});
        jTextField1.setText("");

        int size = model.getRowCount();
        int f = size - 1;

        for (int x = 1; x <= f; x++) {
            if (jTable3.getValueAt(f, 0).toString().equals(jTable3.getValueAt(x - 1, 0).toString())) {
                //JOptionPane.showMessageDialog(null,"Magkaparehas!"+"\n"+diagnose.getValueAt(f, 0).toString()+" is equal sa "+diagnose.getValueAt(x-1, 0).toString());
                model.removeRow(f);
                f--;

            } else {
                //JOptionPane.showMessageDialog(null,"Add pa more! ");
            }
        }
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int complaints = jTable3.getRowCount();//
        int findings = jTable3.getRowCount();//
        if (complaints == 0 || findings == 0 || jTextField3.getText().equals("") || jTextField4.getText().equals("") || pr.equals("")) {
            JOptionPane.showMessageDialog(null, "Empty Field(s) Detected!");
        } else {
            ExamIDGenerate();
            collectFindings();
            collectComplaints();
            savePhysicalExam();
            ResetPhEx();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        jButton3.setEnabled(true);
        jButton2.setEnabled(false);
        setUpdate();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (JOptionPane.showConfirmDialog(new JFrame(),
                "Are you sure you want to update fields?",
                "Updating Fields", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                collectFindings();
                collectComplaints();
                updateExamPR();
                updatePheX();
                ResetPhEx();
                jButton2.setEnabled(true);
                jButton3.setEnabled(false);
        } else {

            System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
            return;
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (JOptionPane.showConfirmDialog(new JFrame(),
                "Are you sure you want to reset the field?",
                "Resetting Field", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                ResetPhEx();
                jButton2.setEnabled(true);
                jButton3.setEnabled(false);
        } else {

            System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
            return;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c)
                || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE || evt.getKeyChar() == '.')) {
            //  evt.getKeyChar() == '.' does accept point when jtextfield accepts decimal number
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_jTextField6KeyTyped

    private void jTextField8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c)
                || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE || evt.getKeyChar() == '.')) {
            //  evt.getKeyChar() == '.' does accept point when jtextfield accepts decimal number
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_jTextField8KeyTyped

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        if (jRadioButton2.isSelected()) {
            jRadioButton1.setSelected(false);
            diagnosisp2 = jRadioButton2.getText();
        }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        if (jRadioButton1.isSelected()) {
            jRadioButton2.setSelected(false);
            diagnosisp2 = jRadioButton1.getText();
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        if (jRadioButton3.isSelected()) {
            jRadioButton5.setSelected(false);
            jRadioButton4.setSelected(false);
            prognosisp2 = jRadioButton3.getText();
        }
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        if (jRadioButton5.isSelected()) {
            jRadioButton3.setSelected(false);
            jRadioButton4.setSelected(false);
            prognosisp2 = jRadioButton5.getText();
        }
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        if (jRadioButton4.isSelected()) {
            jRadioButton5.setSelected(false);
            jRadioButton3.setSelected(false);
            prognosisp2 = jRadioButton4.getText();
        }
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jTextField19KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField19KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField19KeyTyped

    private void jTextField12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField12ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable6.getModel();
        model.addRow(new Object[]{jTextField12.getText().toUpperCase()});
        jTextField12.setText("");

        int size = model.getRowCount();
        int f = size - 1;

        for (int x = 1; x <= f; x++) {
            if (jTable6.getValueAt(f, 0).toString().equals(jTable6.getValueAt(x - 1, 0).toString())) {
                //JOptionPane.showMessageDialog(null,"Magkaparehas!"+"\n"+diagnose.getValueAt(f, 0).toString()+" is equal sa "+diagnose.getValueAt(x-1, 0).toString());
                model.removeRow(f);
                f--;

            } else {
                //JOptionPane.showMessageDialog(null,"Add pa more! ");
            }
        }
    }//GEN-LAST:event_jTextField12ActionPerformed

    private void jTextField18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField18ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable7.getModel();
        model.addRow(new Object[]{jTextField18.getText().toUpperCase()});
        jTextField18.setText("");

        int size = model.getRowCount();
        int f = size - 1;

        for (int x = 1; x <= f; x++) {
            if (jTable7.getValueAt(f, 0).toString().equals(jTable7.getValueAt(x - 1, 0).toString())) {
                //JOptionPane.showMessageDialog(null,"Magkaparehas!"+"\n"+diagnose.getValueAt(f, 0).toString()+" is equal sa "+diagnose.getValueAt(x-1, 0).toString());
                model.removeRow(f);
                f--;

            } else {
                //JOptionPane.showMessageDialog(null,"Add pa more! ");
            }
        }
    }//GEN-LAST:event_jTextField18ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(pr.equals("")){
            JOptionPane.showMessageDialog(null, "Please specify purpose or reason for examination above","Validation",JOptionPane.INFORMATION_MESSAGE);
        }else{
            collectlabexDiagnosis();
            collectlabexPrognosis();
            collectmedicineGiven();
            collectprescription();
            setCheckFields();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        DefaultTableModel model3 = (DefaultTableModel) jTable5.getModel();
        if(jButton8.getText().equals("Update")){
            jButton8.setText("Add");
            model3.setValueAt(jTextField5.getText().toUpperCase(), jTable5.getSelectedRow(), 0);
            model3.setValueAt(jTextField6.getText().toUpperCase()+" "+jComboBox5.getSelectedItem().toString().toUpperCase(), jTable5.getSelectedRow(), 1);
        }else{
            if(jTextField5.getText().equals("")||jTextField6.getText().equals("")||jTextField19.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Empty Field(s) Detected!");
            }else{
                DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
                model.addRow(new Object[]{jTextField5.getText().toUpperCase(), jTextField6.getText().toUpperCase() + " " + jComboBox5.getSelectedItem().toString().toUpperCase(), jComboBox4.getSelectedItem().toString().toUpperCase(), jTextField19.getText().toUpperCase()});
                jTextField5.setText("");
                jTextField6.setText("");
                jTextField19.setText("");

                int size = model.getRowCount();
                int f = size - 1;

                for (int x = 1; x <= f; x++) {
                    if (jTable5.getValueAt(f, 0).toString().equals(jTable5.getValueAt(x - 1, 0).toString())) {
                        //JOptionPane.showMessageDialog(null,"Magkaparehas!"+"\n"+diagnose.getValueAt(f, 0).toString()+" is equal sa "+diagnose.getValueAt(x-1, 0).toString());
                        model.removeRow(f);
                        f--;

                    } else {
                        //JOptionPane.showMessageDialog(null,"Add pa more! ");
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if(jButton6.getText().equals("Update")){
            jButton6.setText("Add");
        }else{
                if(jTextField7.getText().equals("")||jTextField8.getText().equals("")||jTextArea4.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Empty Field(s) Detected!");
                }else{
                    DefaultTableModel model = (DefaultTableModel) jTable8.getModel();
                    model.addRow(new Object[]{jTextField7.getText().toUpperCase(), jTextField8.getText().toUpperCase() + " " + jComboBox6.getSelectedItem().toString().toUpperCase(), jTextArea4.getText().toUpperCase()});
                    jTextField7.setText("");
                    jTextField8.setText("");
                    jTextArea4.setText("");

                    int size = model.getRowCount();
                    int f = size - 1;

                    for (int x = 1; x <= f; x++) {
                        if (jTable8.getValueAt(f, 0).toString().equals(jTable8.getValueAt(x - 1, 0).toString())) {
                            //JOptionPane.showMessageDialog(null,"Magkaparehas!"+"\n"+diagnose.getValueAt(f, 0).toString()+" is equal sa "+diagnose.getValueAt(x-1, 0).toString());
                            model.removeRow(f);
                            f--;

                        } else {
                            //JOptionPane.showMessageDialog(null,"Add pa more! ");
                        }
                    }
                }
            
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        String[] parts = jList1.getSelectedValue().toString().split("#");
        String p1 = parts[0]; // 004
        String p2 = parts[1]; // 034556
        labexid = p2;
        jButton4.setEnabled(false);
        jButton5.setEnabled(true);
        setUpdateLabex();
    }//GEN-LAST:event_jList1MouseClicked

    private void jTable8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable8MouseClicked
        DefaultTableModel model4 = (DefaultTableModel) jTable8.getModel();
        jTextField7.setText(model4.getValueAt(jTable8.getSelectedRow(), 0).toString());
        String[] parts = model4.getValueAt(jTable8.getSelectedRow(), 1).toString().split(" ");
        String p1 = parts[0]; // 004
        String p2 = parts[1]; // 034556
        jTextField8.setText(p1);
        jComboBox6.setSelectedItem(p2.toLowerCase());
        jTextArea4.setText(model4.getValueAt(jTable8.getSelectedRow(), 2).toString());
        
        jButton6.setText("Update");
    }//GEN-LAST:event_jTable8MouseClicked

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        DefaultTableModel model3 = (DefaultTableModel) jTable5.getModel();
        jTextField5.setText(model3.getValueAt(jTable5.getSelectedRow(), 0).toString());
        String[] parts = model3.getValueAt(jTable5.getSelectedRow(), 1).toString().split(" ");
        String p1 = parts[0]; // 004
        String p2 = parts[1]; // 034556
        jTextField6.setText(p1);
        jComboBox5.setSelectedItem(p2.toLowerCase());
        jComboBox4.setSelectedItem(model3.getValueAt(jTable5.getSelectedRow(), 2).toString().toLowerCase());
        jTextField19.setText(model3.getValueAt(jTable5.getSelectedRow(), 3).toString());
        
        jButton8.setText("Update");
    }//GEN-LAST:event_jTable5MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if (JOptionPane.showConfirmDialog(new JFrame(),
                "Are you sure you want to reset the field?",
                "Resetting Field", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            ResetFields();
            viewSaved();
            jButton4.setEnabled(true);
            jButton5.setEnabled(false);
        } else {

            System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
            return;
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (JOptionPane.showConfirmDialog(new JFrame(),
                "Are you sure you want to update fields?",
                "Updating Fields", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            collectlabexDiagnosis();
            collectlabexPrognosis();
            collectmedicineGiven();
            collectprescription();
            updatelabex();
            ResetFields();
            viewSaved();
            jButton4.setEnabled(true);
            jButton5.setEnabled(false);
        } else {

            System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
            return;
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Are you sure you want to close?",
            "Closing Examinations", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            emr_codes emr = new emr_codes();
            autoViewPhExVitals();
            emr.viewConfinement();
            this.dispose();
        }
        else {

            System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
            return;
        }
    }//GEN-LAST:event_formWindowClosing

    private void jTextField21KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField21KeyTyped
         char c = evt.getKeyChar();
        if (!(Character.isDigit(c)
                || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE || evt.getKeyChar() == '.')) {
            //  evt.getKeyChar() == '.' does accept point when jtextfield accepts decimal number
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_jTextField21KeyTyped

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        dispose();
        jTabbedPane1.setSelectedIndex(6);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton6ActionPerformed
        if(jRadioButton6.isSelected()){
            jRadioButton7.setSelected(false);
            jRadioButton8.setSelected(false);
            jRadioButton9.setSelected(false);
            pr=jRadioButton6.getText();
            new services_mini_frame().setVisible(true);
        }else{
            services_mini_frame x = new services_mini_frame(); 
            x.dispose();
        }
    }//GEN-LAST:event_jRadioButton6ActionPerformed

    private void jRadioButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton7ActionPerformed
        if(jRadioButton7.isSelected()){
            jRadioButton6.setSelected(false);
            jRadioButton8.setSelected(false);
            jRadioButton9.setSelected(false);
            pr=jRadioButton7.getText();
            new services_mini_frame().setVisible(true);
        }
    }//GEN-LAST:event_jRadioButton7ActionPerformed

    private void jRadioButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton8ActionPerformed
        if(jRadioButton8.isSelected()){
            jRadioButton6.setSelected(false);
            jRadioButton7.setSelected(false);
            jRadioButton9.setSelected(false);
            pr=jRadioButton8.getText();
            new services_mini_frame().setVisible(true);
        }
    }//GEN-LAST:event_jRadioButton8ActionPerformed

    private void jRadioButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton9ActionPerformed
         if(jRadioButton9.isSelected()){
            jRadioButton6.setSelected(false);
            jRadioButton7.setSelected(false);
            jRadioButton8.setSelected(false);
            pr=jRadioButton9.getText();
            new services_mini_frame().setVisible(true);
        }
    }//GEN-LAST:event_jRadioButton9ActionPerformed

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseEntered

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(examinations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(examinations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(examinations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(examinations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new examinations().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JComboBox jComboBox5;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JRadioButton jRadioButton9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextArea jTextArea6;
    private javax.swing.JTextArea jTextArea7;
    private javax.swing.JTextArea jTextArea8;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    // End of variables declaration//GEN-END:variables
}
