/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emr.jpanels.SQLClass;

import static emr.jpanels.pet_chart.jTable10;
import static emr.jpanels.pet_chart.jTable7;
import static emr.jpanels.pet_chart.jTable8;
import static emr.jpanels.pet_chart.jTable9;
import static emr.jpanels.pet_chart.petid;
import static emr.jpanels.pet_chart.today_date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import login.connection_db;
import net.proteanit.sql.DbUtils;
/**
 *
 * @author irv
 */
public class view_ddeo {
    
    Connection conn_db = connection_db.ConnectDB();
    ResultSet rs = null;
    PreparedStatement ps = null;

    public void viewinit(){
        try{
            String sql = "SELECT acq_name AS `Service Name`, CONCAT_WS(' ', services_acquired.day, services_acquired.month, services_acquired.year) AS `Date` FROM `services_acquired` WHERE pet_id = '"+petid+"' HAVING `Date` = '"+today_date.getText()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable7.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
        
        int count = jTable7.getRowCount();
        
        if(count == 0){
            JOptionPane.showMessageDialog(null, "No Data Retrieved!");
            DefaultTableModel model=(DefaultTableModel) jTable7.getModel();
             model.getDataVector().removeAllElements();
        }else{
            
        }
    }
    
    public void viewgivenmed(){
        try{
            String sql = "SELECT CONCAT_WS(' ', day, month,year) AS `Date`, CONCAT_WS(' ', volume, medicine_given, dose_unit) AS `Medicine`, daily_usage AS `Usage` FROM `daily_data_diagnosis_medicine_given` WHERE pet_id = '"+petid+"' HAVING `Date` = '"+today_date.getText()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable9.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
        
        int count = jTable7.getRowCount();
        
        if(count == 0){
            JOptionPane.showMessageDialog(null, "No Data Retrieved!");
             DefaultTableModel model=(DefaultTableModel) jTable9.getModel();
             model.getDataVector().removeAllElements();
        }else{
            
        }
    }
    
    public void viewsymptomsanddiagnosis(){
        try{
            String sql = "SELECT symtoms_given AS `Symptoms`, diagnosis AS `Diagnosis`, prognosis AS `Prognosis`, CONCAT_WS(' ', day, month, year) AS `Date` FROM `daily_data_diagnosis_symptoms` WHERE pet_id = '"+petid+"' HAVING `Date` = '"+today_date.getText()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable8.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
        
        int count = jTable8.getRowCount();
        
        if(count == 0){
            JOptionPane.showMessageDialog(null, "No Data Retrieved!");
             DefaultTableModel model=(DefaultTableModel) jTable8.getModel();
             model.getDataVector().removeAllElements();
        }else{
            
        }
    }
    
    public void viewprescription(){
        try{
            String sql = "SELECT prescription_number AS `RX`, CONCAT_WS(' ', medicine, volume, volume_p2) AS `Medicine`, CONCAT_WS(' ', day, month, year) AS `Date` FROM `prescription` WHERE pet_id = '"+petid+"' HAVING `Date` = '"+today_date.getText()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable10.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
        
        int count = jTable10.getRowCount();
        
        if(count == 0){
            JOptionPane.showMessageDialog(null, "No Data Retrieved!");
             DefaultTableModel model=(DefaultTableModel) jTable10.getModel();
             model.getDataVector().removeAllElements();
        }else{
            
        }
    }
    
    public void saveinmedicalrecord(){
        
    }
}
