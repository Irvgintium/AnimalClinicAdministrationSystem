/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emr.jpanels;

import com.toedter.calendar.JCalendar;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import login.connection_db;
import net.proteanit.sql.DbUtils;
import static emr.jpanels.pet_chart.petid;
import static emr.jpanels.pet_chart.pet_name;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
/**
 *
 * @author irv
 */
public class encounter_notes extends javax.swing.JFrame {
    Connection conn_db = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    String datevisit = "";
    String diagnosis = "";
    
    JFrame x = new JFrame();
    
    Style justified;
    Style bold;
    /**
     * Creates new form ddeo
     */
    public encounter_notes() {
        initComponents();

        conn_db = connection_db.ConnectDB();

        //for centered frame////
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        //
        
        jTextField1.setText(pet_name.getText());
        //petid = "2";
    }
    
    public void fixTextPane(){
        StyledDocument doc = jTextPane1.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_JUSTIFIED);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
    }
    
    public void ViewServicesAcquired(){
        ArrayList<String> arr = new ArrayList<String>();
        
        jTextPane1.setText("");
        
        StyledDocument doc = jTextPane1.getStyledDocument();

        Style style = jTextPane1.addStyle("I'm a Style", null);
        StyleConstants.setForeground(style, Color.GREEN);
        StyleConstants.setBold(style, true);
        StyleConstants.setFontFamily(style, "Arial");
        StyleConstants.setItalic(style, true);
        
        try { doc.insertString(doc.getLength(),"        "+jTextField1.getText()+" ",style); }
        catch (BadLocationException e){}
        
        StyleConstants.setForeground(style, Color.black);
        StyleConstants.setBold(style, true);
        StyleConstants.setItalic(style, false);
        
        
        try { doc.insertString(doc.getLength(), " had acquired the following service(s): ",style); }
        catch (BadLocationException e){}
        
        try{
            String sql = "SELECT acq_name AS `Service Name`, acq_price AS `Price`, CONCAT_WS(' ', services_acquired.day, services_acquired.month, services_acquired.year) AS `Date` FROM `services_acquired` WHERE pet_id = '"+petid+"' HAVING `Date` = '"+date_filter.getText()+"'";
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

            try { doc.insertString(doc.getLength(), result.toUpperCase() ,style); }
            catch (BadLocationException e){}
            
            StyleConstants.setForeground(style, Color.black);
            StyleConstants.setBold(style, true);
            StyleConstants.setItalic(style, false);
        
            try { doc.insertString(doc.getLength(), " ON "+ datevisit+". ",style); }
            catch (BadLocationException e){}
            
            if(datevisit.equals("")){
                jTextPane1.setText("");
                JOptionPane.showMessageDialog(null, "No data retrieved");
            }
            
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
  
    }
    
    
    public void getSymptomsAndDiagnosis(){
        ArrayList<String> arr = new ArrayList<String>();
        
        StyledDocument doc = jTextPane1.getStyledDocument();
        Style style = jTextPane1.addStyle("I'm a Style", null);
        StyleConstants.setForeground(style, Color.GREEN);
        StyleConstants.setBold(style, true);
        StyleConstants.setFontFamily(style, "Arial");
        StyleConstants.setItalic(style, true);
        
        try{
            String sql = "SELECT symtoms_given AS `Symptoms`, diagnosis AS `Diagnosis`, CONCAT_WS(' ', day, month, year) AS `Date` FROM `daily_data_diagnosis_symptoms` WHERE pet_id = '"+petid+"' HAVING `Date` = '"+date_filter.getText()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getdiag = rs.getString("Diagnosis");
                diagnosis = getdiag;
                arr.add(rs.getString("Symptoms"));
            }
            
            String result = arr.toString();
            result = result.replace("[", "");
            result = result.replace("]", "");
            result = result.replace(" ", " ");
            result = result.replace(",", ";");
            
            StyleConstants.setForeground(style, Color.black);
            StyleConstants.setBold(style, true);
            StyleConstants.setItalic(style, false);

            try { doc.insertString(doc.getLength(), "\n\n       During the check-up, ",style); }
            catch (BadLocationException e){}
            
            //////
            
            StyleConstants.setForeground(style, Color.red);
            StyleConstants.setBold(style, true);
            StyleConstants.setItalic(style, true);
            
            try { doc.insertString(doc.getLength(), result,style); }
            catch (BadLocationException e){}
            
            //////
            
            StyleConstants.setForeground(style, Color.black);
            StyleConstants.setBold(style, true);
            StyleConstants.setItalic(style, false);

            try { doc.insertString(doc.getLength(), " were the complaints that have been recorded. The finalized result for ",style); }
            catch (BadLocationException e){}
            
            //////
            
            StyleConstants.setForeground(style, Color.GREEN);
            StyleConstants.setBold(style, true);
            StyleConstants.setFontFamily(style, "Arial");
            StyleConstants.setItalic(style, true);

            try { doc.insertString(doc.getLength(),jTextField1.getText(),style); }
            catch (BadLocationException e){}
            
            /////
            
            StyleConstants.setForeground(style, Color.black);
            StyleConstants.setBold(style, true);
            StyleConstants.setFontFamily(style, "Arial");
            StyleConstants.setItalic(style, true);

            try { doc.insertString(doc.getLength()," was ",style); }
            catch (BadLocationException e){}
            
            
            StyleConstants.setForeground(style, Color.blue);
            StyleConstants.setBold(style, true);
            StyleConstants.setFontFamily(style, "Arial");
            StyleConstants.setItalic(style, true);

            try { doc.insertString(doc.getLength(),diagnosis+".",style); }
            catch (BadLocationException e){}
            
            if(datevisit.equals("")){
                
                jTextPane1.setText("");
            }
            
            
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    } 
    
    
    public void getMedicineGiven(){
        ArrayList<String> arr = new ArrayList<String>();
        
        StyledDocument doc = jTextPane1.getStyledDocument();

        Style style = jTextPane1.addStyle("I'm a Style", null);
        StyleConstants.setForeground(style, Color.GREEN);
        StyleConstants.setBold(style, true);
        StyleConstants.setFontFamily(style, "Arial");
        StyleConstants.setItalic(style, true);
        
        try { doc.insertString(doc.getLength()," "+jTextField1.getText()+" ",style); }
        catch (BadLocationException e){}
        
        
        try{
            String sql = "SELECT CONCAT_WS(' ', day, month,year) AS `Date`, CONCAT_WS(' ', volume, medicine_given, dose_unit) AS `Medicine`, daily_usage AS `Usage` FROM `daily_data_diagnosis_medicine_given` WHERE pet_id = '"+petid+"' HAVING `Date` = '"+date_filter.getText()+"' ";
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

            try { doc.insertString(doc.getLength(), "was given ",style); }
            catch (BadLocationException e){}
            
            StyleConstants.setForeground(style, Color.blue);
            StyleConstants.setBold(style, true);
            StyleConstants.setItalic(style, true);

            try { doc.insertString(doc.getLength(), result +".",style); }
            catch (BadLocationException e){}
            
            if(datevisit.equals("")){
                
                jTextPane1.setText("");
            }
            
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void getPrescriptionGiven(){
        ArrayList<String> arr = new ArrayList<String>();
        
        StyledDocument doc = jTextPane1.getStyledDocument();
        
        Style style = jTextPane1.addStyle("I'm a Style", null);
        StyleConstants.setForeground(style, Color.black);
        StyleConstants.setBold(style, true);
        StyleConstants.setFontFamily(style, "Arial");
        StyleConstants.setItalic(style, false);
        
        try { doc.insertString(doc.getLength()," Prescription for ",style); }
        catch (BadLocationException e){}

        StyleConstants.setForeground(style, Color.GREEN);
        StyleConstants.setBold(style, true);
        StyleConstants.setFontFamily(style, "Arial");
        StyleConstants.setItalic(style, true);
        
        try { doc.insertString(doc.getLength(),jTextField1.getText()+" ",style); }
        catch (BadLocationException e){}
        
        
        try{
            String sql = "SELECT CONCAT_WS(' ', day, month,year) AS `Date`, CONCAT_WS(' ', medicine, volume, volume_p2) AS `Medicine` FROM `prescription` WHERE pet_id = '"+petid+"' HAVING `Date` = '"+date_filter.getText()+"' ";
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

            try { doc.insertString(doc.getLength(), "was ",style); }
            catch (BadLocationException e){}
            
            StyleConstants.setForeground(style, Color.blue);
            StyleConstants.setBold(style, true);
            StyleConstants.setItalic(style, true);

            try { doc.insertString(doc.getLength(), result +".",style); }
            catch (BadLocationException e){}
            
            if(datevisit.equals("")){
                
                jTextPane1.setText("");
            }
            
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

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jCalendar2 = new com.toedter.calendar.JCalendar();
        jRadioButton1 = new javax.swing.JRadioButton();
        date_filter = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Encounter Notes");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Pet Name");

        jTextField1.setEditable(false);
        jTextField1.setText("SCRAPPY");

        jButton1.setText("View");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jRadioButton1.setText("Search by date");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        date_filter.setFont(new java.awt.Font("Ubuntu", 1, 14)); // NOI18N
        date_filter.setText("*****");

        jTextPane1.setEditable(false);
        jTextPane1.setMargin(new java.awt.Insets(10, 10, 10, 10));
        jScrollPane1.setViewportView(jTextPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel1)
                        .addGap(12, 12, 12)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jRadioButton1)
                        .addGap(24, 24, 24)
                        .addComponent(date_filter, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(70, 70, 70)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jRadioButton1))
                            .addComponent(date_filter, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(jButton1)))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JCalendar dateset = new JCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
        String dt = dateFormat.format(dateset.getDate());
        
        String string = dt;
        String[] parts = string.split(" ");
        String dt1 = parts[0]; // 004
        String dt2 = parts[1]; // 034556
        String dt3 = parts[2]; // 034556
        int year = Integer.parseInt(dt3);
        int day = Integer.parseInt(dt1);
        
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("d MMM yyyy");
        String dc = dateFormat2.format(jCalendar2.getDate());
        
        String string2 = dc;
        String[] parts2 = string2.split(" ");
        String dc1 = parts2[0]; // 004
        String dc2 = parts2[1]; // 034556
        String dc3 = parts2[2]; // 034556
        int year2 = Integer.parseInt(dc3);
        int day2 = Integer.parseInt(dc1);
        
        if(year2>year){
            JOptionPane.showMessageDialog(null, "Invalid Year");
            date_filter.setText("*****");
        }else{
            if(year2<year){
                date_filter.setText(dc);
                ViewServicesAcquired();
                getSymptomsAndDiagnosis();
                getMedicineGiven();
                fixTextPane();
                getPrescriptionGiven();
                
                datevisit = "";
                diagnosis = "";
                
                jRadioButton1.setSelected(false);
                
                x.dispose();
            }else{
                if(dt2.equals(dc2) && day2>day){
                        JOptionPane.showMessageDialog(null, "Invalid Day\nPlease be reminded that today is "+dt2+" "+dt1+" "+dt3);
                        date_filter.setText("*****");
                }else{
                    date_filter.setText(dc);
                    ViewServicesAcquired();
                    getSymptomsAndDiagnosis();
                    getMedicineGiven();
                    fixTextPane();
                    getPrescriptionGiven();
                    
                    datevisit = "";
                    diagnosis = "";
                    
                    jRadioButton1.setSelected(false);
                    
                    x.dispose();
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        if(jRadioButton1.isSelected()){
            //x.setSize(600, 320);
            x.setBounds(960, 0, 400, 300);
            x.setTitle("Search Date");
            x.setLayout(new FlowLayout());
            x.add(jCalendar2);
            x.add(jButton1);
            x.setAlwaysOnTop(true);
            x.setVisible(true);
        }else{
            x.dispose();
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Are you sure you want to close?",
            "Closing DDE Overview", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            this.dispose();
            
        }
        else {

            System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
            return;
        }
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(encounter_notes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(encounter_notes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(encounter_notes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(encounter_notes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new encounter_notes().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel date_filter;
    public static javax.swing.JButton jButton1;
    public static com.toedter.calendar.JCalendar jCalendar2;
    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JRadioButton jRadioButton1;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextField jTextField1;
    public static javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}
