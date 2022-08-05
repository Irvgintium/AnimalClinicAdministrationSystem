/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package emr.jpanels;

import audit_trailing.audit_trail;
import billing.jpanels.bill;
import billing.jpanels.purchase_item;
import com.toedter.calendar.JCalendar;
import emr.jpanels.SQLClass.emr_codes;
import emr.jpanels.SQLClass.loading_animation;
import emr.jpanels.SQLClass.loading_animation1;
import emr.jpanels.SQLClass.view_ddeo;
import static emr.jpanels.encounter_notes.date_filter;
import static emr.jpanels.immunization_history.pet_id;
import static emr.jpanels.manage_symptoms.jTextField1_symp;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.text.*;
import login.connection_db;
import maintenance.jpanels.services_classification_frame;
import maintenance.jpanels.services_frame;
import net.proteanit.sql.DbUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
/**
 *
 * @author irv
 */
public class pet_chart extends javax.swing.JPanel{
    
    public static String ownerID = "";
    Connection conn_db = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    emr_codes emr = new emr_codes();
    
    //checkup_p1 ch = new checkup_p1();
    
    view_ddeo x = new view_ddeo();
    
    int timerun = 0;
    
    int hourset;
    int minuteset;
    String minutesets;
    String am_pmset;
    
    String IDp1;
    
    int finalpoint = 0;
    int totalpoint = 0;
    int totalpoint2 = 0;
    int pointChecker = 0;
    
    String rxNum;
    
    String getrxnum;
    
    int count = 0;
    
    public static String petid;
    public static String ownerid;
    
    String day;
    String month;
    String year;
    
    public static String day1;
    public static String month1;
    public static String year1;
    public static  String examnumber;
    
    String init = "";
    String volumep2 = "";
    int recommendTrigger=0;
    
    int subtractQty;
    
    int newQTY;
    int currentQTY;
    
    ///////
    String appointment_date = "";
    ///////
    
    JFrame ee = new JFrame();
    JFrame ee2 = new JFrame();
    
    String diagnosisaddon = "";
    String prognosisaddon = "";
    public static String intializationcheck = "";
        
    /**
     * Creates new form pet_chart
     */
    public pet_chart() {
        initComponents();
        conn_db = connection_db.ConnectDB();
        
        clock();
        
        date_today();
        
        jTextArea1.setVisible(false);
        
        jLabel1.setVisible(false);
        jRadioButton1.setVisible(false);
        jTextField2.setVisible(false);
        
        jButton10.setEnabled(false);
        jButton13.setEnabled(false);
        
        jButton14.setEnabled(false);
        
        jButton20.setEnabled(false);
        
        jButton25.setEnabled(false);
        
        jButton26.setEnabled(false);
        
        jButton33.setEnabled(false);
        
        fillClassification();
        
        FillCBwithClassification();
        
        medgivenfieldsOFF();
        
        validateTimeOnAppointment();
        
        emr.viewConfinement();
        
        jPanel24.setVisible(false);
        
        jTextField5.setEnabled(false);

        jButton21.setEnabled(true);
        jButton44.setEnabled(false);
        
        jPanel22.setVisible(false);
        
        jButton4.setVisible(false);
        
        jButton40.setEnabled(false);
        
        jButton36.setEnabled(false);
        
        rbpdoff();
        
        ee.setSize(300, 70);
        ee.setLocationRelativeTo(null);
        
        ee2.setSize(300, 70);
        ee2.setLocationRelativeTo(null);
        
        ////set date////
        JCalendar dateset2 = new JCalendar();
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("d MMM yyyy");
        String dob2 = dateFormat2.format(dateset2.getDate());
        
        String string3 = dob2;
        String[] parts3 = string3.split(" ");
        String p1 = parts3[0]; // 004
        String p2 = parts3[1]; // 034556
        String p3 = parts3[2]; // 034556

        day = p1;
        month = p2;
        year = p3;
        
        IDp1 = day+year;

    }
    
    public void clock(){
        /////clock here///////
        
        new Thread(){
            public void run(){
                while(timerun == 0){
                    Calendar cal = new GregorianCalendar();
                    
                    int hour = cal.get(Calendar.HOUR);
                    if(hour == 0){
                        hour = 12;
                    }else{
                        
                    }
                    int minute = cal.get(Calendar.MINUTE);
                    int sec = cal.get(Calendar.SECOND);
                    int ampm = cal.get(Calendar.AM_PM);
                    String day_night = "";
                    if(ampm == 1){
                        day_night = "PM";
                    }else{
                        day_night = "AM";
                    }
                    
		    if(minute <= 9){
			
		    String clock = hour + ":" + "0" + minute + " " + day_night;
                    hourset = hour; minuteset = minute; am_pmset = day_night;
                    if(minuteset<=9){
                      minutesets = String.valueOf("0"+minute);  
                    }else{
                        minutesets = String.valueOf(minute); 
                    }

                    rxNum = IDp1+Integer.toString(minute)+Integer.toString(sec);
			
			}
		         else{
					String clock = hour + ":" + minute + " " + day_night;
                                        hourset = hour; minuteset = minute; am_pmset = day_night;
                                        if(minuteset<=9){
                                            minutesets = String.valueOf("0"+minute);  
                                          }else{
                                              minutesets = String.valueOf(minute); 
                                          }
                                        rxNum = IDp1+Integer.toString(minute)+Integer.toString(sec);
			     }
                     
                }
            }
        }.start();
        
        /////clock here//////
    }
    
    public void setSMSAppointmentDate(){
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("d MMM yyyy");
        Calendar cal = jCalendar1.getCalendar();
        dateFormat2.setCalendar(cal); 
        cal.add(Calendar.DATE, -3);
            
        String b2 = dateFormat2.format(cal.getTime()).toString();
            
        String string32 = b2;
        String[] parts32 = string32.split(" ");
        day1 = parts32[0];
        month1 = parts32[1];
        year1 = parts32[2];
        
        String minpatch;
        int min = Integer.parseInt(minute.getValue().toString());
       
       if(min<10){
           minpatch = "0"+Integer.toString(min);
       }else{
           minpatch = Integer.toString(min);
       }

        b.setText(jComboBox5.getSelectedItem().toString());
        c.setText(day1+" "+month1+" "+year1);
        d.setText("at "+hour.getValue().toString()+":"+minpatch+" "+jComboBox6.getSelectedItem().toString());
        jPanel24.setVisible(true);
        
        SimpleDateFormat dateFormat22 = new SimpleDateFormat("d MMM yyyy");
        Calendar cal2 = jCalendar1.getCalendar();
        dateFormat22.setCalendar(cal2); 
        cal2.add(Calendar.DATE, +3);
    }
    
    public void date_today(){
        ////date here//////
        JCalendar dateset = new JCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
        String dob = dateFormat.format(dateset.getDate());
        today_date.setText(dob);
        ////date here/////
    }
    
    public class ImagePreviewPanel extends JPanel
        implements PropertyChangeListener {
    
    private int width, height;
    private ImageIcon icon;
    private Image image;
    private static final int ACCSIZE = 155;
    private Color bg;
    
    public ImagePreviewPanel() {
        setPreferredSize(new Dimension(ACCSIZE, -1));
        bg = getBackground();
    }
    
    public void propertyChange(PropertyChangeEvent e) {
        String propertyName = e.getPropertyName();
        
        // Make sure we are responding to the right event.
        if (propertyName.equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)) {
            File selection = (File)e.getNewValue();
            String name;
            
            if (selection == null)
                return;
            else
                name = selection.getAbsolutePath();
            
            /*
             * Make reasonably sure we have an image format that AWT can
             * handle so we don't try to draw something silly.
             */
            if ((name != null) &&
                    name.toLowerCase().endsWith(".jpg") ||
                    name.toLowerCase().endsWith(".jpeg") ||
                    name.toLowerCase().endsWith(".gif") ||
                    name.toLowerCase().endsWith(".png")) {
                icon = new ImageIcon(name);
                image = icon.getImage();
                scaleImage();
                repaint();
            }
        }
    }
    
    private void scaleImage() {
        width = image.getWidth((ImageObserver) this);
        height = image.getHeight((ImageObserver) this);
        double ratio = 1.0;
       
        /* 
         * Determine how to scale the image. Since the accessory can expand
         * vertically make sure we don't go larger than 150 when scaling
         * vertically.
         */
        if (width >= height) {
            ratio = (double)(ACCSIZE-5) / width;
            width = ACCSIZE-5;
            height = (int)(height * ratio);
        }
        else {
            if (getHeight() > 150) {
                ratio = (double)(ACCSIZE-5) / height;
                height = ACCSIZE-5;
                width = (int)(width * ratio);
            }
            else {
                ratio = (double)getHeight() / height;
                height = getHeight();
                width = (int)(width * ratio);
            }
        }
                
        image = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
    }
    
    public void paintComponent(Graphics g) {
        g.setColor(bg);
        
        /*
         * If we don't do this, we will end up with garbage from previous
         * images if they have larger sizes than the one we are currently
         * drawing. Also, it seems that the file list can paint outside
         * of its rectangle, and will cause odd behavior if we don't clear
         * or fill the rectangle for the accessory before drawing. This might
         * be a bug in JFileChooser.
         */
        g.fillRect(0, 0, ACCSIZE, getHeight());
        g.drawImage(image, getWidth() / 2 - width / 2 + 5,
                getHeight() / 2 - height / 2, (ImageObserver) this);
    }
    
}
    
    public void uploadImage(){
        JFileChooser chooser = new JFileChooser();
        ImagePreviewPanel preview = new ImagePreviewPanel();
        chooser.setAccessory(preview);
        chooser.addPropertyChangeListener(preview);
        
        int result = chooser.showOpenDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION) { 
            
            File f =chooser.getSelectedFile();
            String filename=f.getAbsolutePath();
            ImageIcon imageIcon = new ImageIcon(filename); // load the image to a imageIcon
            Image image2 = imageIcon.getImage(); // transform it 
            Image newimg = image2.getScaledInstance(160, 160,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
            imageIcon = new ImageIcon(newimg);  // transform it back

            picture.setIcon(imageIcon);

            picture.setText("");

            try{
                File image = new File(filename);
                FileInputStream fis =new FileInputStream(image);

                ByteArrayOutputStream bos= new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                for(int readNum; (readNum=fis.read(buf))!=-1;){
                    bos.write(buf, 0, readNum);
                }

                person_image=bos.toByteArray();

            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
            
            saveImage();
        
        } else if (result == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "Uploading of the image has been cancelled");
        }
        
    }
    
    public void saveImage(){
        
        try{
             String sql="UPDATE pet SET profile_image = ? where pet_id = ?";          
            ps=conn_db.prepareStatement(sql);
            ps.setBytes(1, person_image);
            ps.setString(2, petid);
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Image has been succesfully uploaded");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
       
    }
    
    public void autoShowHistory(){
        try{
            String sql = "SELECT vaccine AS `Vaccine`, deworming AS `Deworming`, age AS `Age`, CONCAT_WS(' ', day, month, year) AS `Immunization Date` FROM `immunization_history` where pet_id ='"+petid+"' ORDER BY year DESC";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            immunization_table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
   }
    
    public void autoRecommend(){
         
         int records = 0;
         
         try{
             String sql = "select distinct * from auto_suggest_diagnosis_data where symptom = '"+jTextField1.getText()+"'";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             
             while(rs.next()){
                 
                 records = rs.getInt(1);
                 
                 String getRec = rs.getString("diagnosis");

                 DefaultTableModel model=(DefaultTableModel) diagnosis.getModel();

                 model.addRow(new Object[]{getRec});
                 
                 //for adding points
                 DefaultTableModel modelx=(DefaultTableModel) points.getModel();
                 modelx.addRow(new Object[]{getRec, "1"});
                 //for adding points

                 int size = diagnosis.getRowCount();
                 int f = size-1;
                 
                   for(int x=1; x<=f; x++){
                   if(diagnosis.getValueAt(f, 0).toString().equals(diagnosis.getValueAt(x-1, 0).toString())){
                       //JOptionPane.showMessageDialog(null,"Magkaparehas!"+"\n"+diagnose.getValueAt(f, 0).toString()+" is equal sa "+diagnose.getValueAt(x-1, 0).toString());
                       model.removeRow(f);
                       modelx.removeRow(f);
                       f--;
                       //for adding points
                       int addpoint = Integer.parseInt(points.getValueAt(x-1, 1).toString());
                       addpoint++;
                       points.setValueAt(addpoint, x-1, 1);
                        //for adding points
                   }else{
                        //JOptionPane.showMessageDialog(null,"Add pa more! ");
                    }
                   
                    }

             }
             
             if(records > 0){
                 
             }else{
                 if ( JOptionPane.showConfirmDialog(new JFrame(),
                "Add a corresponding diagnosis to "+jTextField1.getText().toUpperCase()+"?\nThis symptom has no any data yet.\nIf "+jTextField1.getText().toUpperCase()+" has no data, it will not be included on the suggesting of the diagnosis",
                "A new symptom detected!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                 recommendTrigger = 1;
                 new manage_symptoms().setVisible(true);
                 Font newTextFieldFont=new Font(jTextField1_symp.getFont().getName(),Font.BOLD,jTextField1_symp.getFont().getSize());
                 jTextField1_symp.setFont(newTextFieldFont);
                 jTextField1_symp.setForeground(new Color(51,102,0));
                 jTextField1_symp.setText(jTextField1.getText()); 
                 
                }
                else {
                    System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
                    recommendTrigger = 0;
                    return;
                }
             }
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
     }
    
    public void finalizeDiagnosis(){
         //for adding points
        DefaultTableModel modelx=(DefaultTableModel) points.getModel();
        int size = modelx.getRowCount();
        int f = size-1;
        //for adding points
        finalpoint = Integer.parseInt(points.getValueAt(0, 1).toString());//base point
        String name = points.getValueAt(0, 0).toString();//base name
        //for loop here//
        for(int x=0; x<=f; x++){
            if(finalpoint <= Integer.parseInt(points.getValueAt(x, 1).toString())){
                finalpoint = Integer.parseInt(points.getValueAt(x, 1).toString());
                name = points.getValueAt(x, 0).toString();
                jLabel3.setText(name);
                rbpdon();
            }else{
                 finalpoint = finalpoint;
                 jLabel3.setText(name);
                 rbpdon();
            }
        }
    }
    
    public void getResults(){
        DefaultTableModel modelx=(DefaultTableModel) points.getModel();
         
        int size = modelx.getRowCount();
        int f = size-1;

        int contpoint=0;
        
        for(int x=0; x<=f; x++){
            int getpoints = Integer.parseInt(points.getValueAt(x, 1).toString());
            contpoint = contpoint + getpoints;
            totalpoint = contpoint;
        }
                
        double percentagep1 = ((double)finalpoint/(double)totalpoint);
        
        double percentage = percentagep1 * 100;
        
        double roundedNumber = (double)Math.round(percentage * 10) / 10;
        
        JOptionPane.showMessageDialog(null, "The suggested diagnosis is "+ jLabel3.getText()+ ".\nIt is " +roundedNumber+"% accurate based on the given symptoms");
    }
    
    public void showOtherResults(){
        DefaultTableModel modelx=(DefaultTableModel) points.getModel();
         
        int size = modelx.getRowCount();
        int f = size-1;
        
        DefaultPieDataset pieData = new DefaultPieDataset();
        
        for(int x=0; x<=f; x++){
            double getpoints = Double.parseDouble(points.getValueAt(x, 1).toString());
            
            double percentagep1 = ((double)getpoints/(double)totalpoint);
        
            double percentage = percentagep1 * 100;
        
            double roundedNumber = (double)Math.round(percentage * 10) / 10;
        
            jTextArea1.append(points.getValueAt(x, 0).toString() + " is " +roundedNumber+"% accurate based on the given symptoms\n");
            
            pieData.setValue(points.getValueAt(x, 0).toString() +" "+roundedNumber+"%", new Double(roundedNumber));
        }
        
        JFreeChart chart = ChartFactory.createPieChart("Suggested Diagnosis Results", pieData,true,true,true);
        PiePlot P =(PiePlot)chart.getPlot();
        P.setBackgroundPaint(Color.WHITE);
        P.setOutlinePaint(Color.WHITE);
        ChartFrame frame = new ChartFrame("Results",chart);
        frame.setBackground(Color.WHITE);
        frame.setVisible(true);
        frame.setSize(650,700);
    }
    
    public void checkifPointsAreEqual(){
        DefaultTableModel modelx=(DefaultTableModel) points.getModel();
         
        int size = modelx.getRowCount();
        int f = size-1;
        
        int contpoint=0;
        
        for(int x=0; x<=f; x++){
            int getpoint = Integer.parseInt(points.getValueAt(x, 1).toString());
            
             if(getpoint == 1){
                 pointChecker++;
             }
        }
        
        if(pointChecker == size){
            if ( JOptionPane.showConfirmDialog(new JFrame(),
                "Cannot determine which is the accurate diagnosis.\nThe suggesting of final diagnosis will not be accurate.\nDo you still want to proceed the suggestion?",
                "A critical result detected", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                 finalizeDiagnosis();
                 getResults();
                 jButton1.setVisible(true);
                }
                else {
                    System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
                    return;
                }
            }else{
                //JOptionPane.showMessageDialog(null, "Ok. Go on" + pointChecker);
                finalizeDiagnosis();
                getResults();
                jButton1.setVisible(true);
            }
    }
    
    public void fillClassification(){
        try{
            String sql = "SELECT * FROM `services_classifications`";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
             String getClassification = rs.getString("classification_name");
             jComboBox1.addItem(getClassification);
             
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void fillservicelist(){
        DefaultListModel dimx = new DefaultListModel();
        try{
            String putincb = "select * from services_data where classification ='"+jComboBox1.getSelectedItem().toString()+"'";
            ps = conn_db.prepareStatement(putincb);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getname = rs.getString("name");
                dimx.addElement(getname);
                jList1.setModel(dimx);
            }
            
        }catch(Exception e){     
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    public void RemoveSelectedServices(){
        ((DefaultListModel) jList2.getModel()).clear();
        fillservicelist();
    }
    
    public void SaveAcquiredServices(){
        DefaultListModel dimx = new DefaultListModel();
        String acqname = "";
        String acqprice = "";
        
        int s = jList2.getModel().getSize();
        int f = s-1;
        
        for(int x = 0; x<=f; x++){
            
            /////////////////////////////////////////////////////////////////////////////////////////////////////////
            try{
                String putincb = "SELECT * FROM `services_data` WHERE name = '"+jList2.getModel().getElementAt(x)+"'";
                ps = conn_db.prepareStatement(putincb);
                rs = ps.executeQuery();

                while(rs.next()){
                    String getacqname = rs.getString("name");
                    acqname = getacqname;
                    String getprice = rs.getString("prices");
                    acqprice = getprice;
                }
                
                ////////////////////////////////////////////////////////////////
                
                String string = today_date.getText();
                String[] parts = string.split(" ");
                String p1 = parts[0]; // 004
                String p2 = parts[1]; // 034556
                String p3 = parts[2]; // 034556

                day = p1;
                month = p2.toUpperCase();
                year = p3;


                try {
                    String sql = "Insert into services_acquired (`pet_id`,`acq_name`,`acq_price`,`day`,`month`,`year`,`status`) values (?,?,?,?,?,?,?)";
                    ps = conn_db.prepareStatement(sql);
                    ps.setString(1, petid);
                    ps.setString(2, acqname);
                    ps.setString(3, acqprice);
                    ps.setString(4, day);
                    ps.setString(5, month);
                    ps.setString(6, year);
                    ps.setString(7, "ONGOING");
                    ps.execute();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                
                ////////////////////////////////////////////////////////////////

                }catch(Exception e){     
                    JOptionPane.showMessageDialog(null, e);
                }
            /////////////////////////////////////////////////////////////////////////////////////////////////////////
        }
        
        dimx.clear();
        jList2.setModel(dimx);
        
        audit_trail au = new audit_trail();
        au.action_type = "Saved - aquired service "+acqname.toUpperCase()+" by "+ pet_name.getText();
        au.module_name = "Pet Chart";
        au.saveAuditTRail();
        
        JOptionPane.showMessageDialog(null, "Services has been sucessfully added", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void RemoveSelectedServicesonDB(){
        DefaultListModel dimx = new DefaultListModel();
        int s = jList2.getModel().getSize();
        int f = s-1;
        
        for(int x = 0; x<=f; x++){   
            try{
            String putincb = "DELETE FROM `acas_db`.`services_acquired` WHERE `services_acquired`.`acq_name` = '"+jList2.getModel().getElementAt(x)+"' AND services_acquired.pet_id = '"+petid+"'";
            ps = conn_db.prepareStatement(putincb);
            ps.executeUpdate();
            
            audit_trail au = new audit_trail();
            au.action_type = "Cancelled - aquired service "+jList2.getModel().getElementAt(x)+" by "+ pet_name.getText();
            au.module_name = "Pet Chart";
            au.saveAuditTRail();
            
            }
            
            catch(Exception e){     
                JOptionPane.showMessageDialog(null, e);
            }
        }
        JOptionPane.showMessageDialog(null, "Services acquired has been removed");
        dimx.clear();
        jList2.setModel(dimx);
        
    }
    
    public void SetUpdateAcqSrv(){
        DefaultListModel dimx = new DefaultListModel();
        try{
                String putincb = "SELECT * FROM `services_acquired` WHERE pet_id = '"+petid+"' ";
                ps = conn_db.prepareStatement(putincb);
                rs = ps.executeQuery();

                while(rs.next()){
                    String getacqname = rs.getString("acq_name");
                    dimx.addElement(getacqname);
                    jList2.setModel(dimx);
                }

                }catch(Exception e){     
                    JOptionPane.showMessageDialog(null, e);
                }
    }
    
    public void FillCBwithClassification(){
        try{
            String putincb = "SELECT * FROM `classification1` where used_for = 'CHECKUP' OR used_for = 'FOODS-CHECKUP-GROOMING'";
            ps = conn_db.prepareStatement(putincb);
            rs = ps.executeQuery();
            while(rs.next()){
                String getClassName = rs.getString("name");
                jComboBox2.addItem(getClassName);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void fillMedicinewithfilters(){
        try{
            String sql = "SELECT item.name AS `Item Name`, item.quantity AS `In-Stock Qty` FROM `item` INNER JOIN classification1 on item.classification_id = classification1.classification_id WHERE classification1.name = '"+jComboBox2.getSelectedItem().toString()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void autoSearch(){
        try{
            String sql = "SELECT item.name AS `Item Name`, item.quantity AS `In-Stock Qty` FROM `item` WHERE name LIKE '"+jTextField5.getText()+"%'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void autoSearchByTableClick(){
        try{
            String sql = "SELECT item.name AS `Item Name`, item.quantity AS `In-Stock Qty` FROM `item` WHERE name LIKE '"+jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString()+"%'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            
        }
        catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
        
    }
    
    public void medgivenfieldsOFF(){
        jComboBox2.setSelectedIndex(0);
        jButton19.setEnabled(false);//
        jTextField4.setEnabled(false);jTextField4.setText("");
        jComboBox4.setEnabled(false);jComboBox4.setSelectedIndex(0);
        jTextField3.setEnabled(false);jTextField3.setText("");
        jLabel13.setText("0");
    }
    
    public void medgivenfieldsON(){
        jButton19.setEnabled(true);//
        jTextField4.setEnabled(true);
        jComboBox4.setEnabled(true);
        jTextField3.setEnabled(true);
    }
    
    public void removeMedGivenRows(){
        DefaultTableModel model = (DefaultTableModel) this.jTable2.getModel();
        int[] rows = jTable2.getSelectedRows();
        for(int i=0;i<rows.length;i++){
          model.removeRow(rows[i]-i);
            int countr = jTable2.getRowCount();
            if(countr==0){
                jButton20.setEnabled(false);
            }else{
                jButton20.setEnabled(true);
            }
        }
}
    
    public void clearPrescription(){
        jTextField7.setText("");
        jTextField8.setText("");
        jTextArea2.setText("");
    }
    
    public void removePrescriptionRows(){
        DefaultTableModel model = (DefaultTableModel) this.jTable5.getModel();
        int[] rows = jTable5.getSelectedRows();
        for(int i=0;i<rows.length;i++){
          model.removeRow(rows[i]-i);
            int countr = jTable5.getRowCount();
            if(countr==0){
                jButton20.setEnabled(false);
            }else{
                jButton20.setEnabled(true);
            }
        }
    }
    
    public void saveprescription(){
        int size = jTable5.getRowCount();//
        int f = size - 1;

        for (int x = 0; x <= f; x++) {
            
            String string3 = jTable5.getValueAt(x, 2).toString();
            String[] parts3 = string3.split(" ");
            String p1 = parts3[0]; // 004
            String p2 = parts3[1]; // 034556
            
             try {
                    String sql = "Insert into prescription (`prescription_number`,`pet_id`,`medicine`,`volume`,`volume_p2`,`signa`,`day`,`month`,`year`) values (?,?,?,?,?,?,?,?,?)";
                    ps = conn_db.prepareStatement(sql);
                    ps.setString(1, jTable5.getValueAt(x, 0).toString().toUpperCase());
                    ps.setString(2, petid);
                    ps.setString(3, jTable5.getValueAt(x, 1).toString().toUpperCase());
                    ps.setString(4, p1);
                    ps.setString(5, p2);
                    ps.setString(6, jTable5.getValueAt(x, 3).toString());
                    ps.setString(7, day);
                    ps.setString(8, month);
                    ps.setString(9, year);
                    ps.execute();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
        }
        
        audit_trail au = new audit_trail();
        au.action_type = "Saved - prescription information by "+ pet_name.getText();
        au.module_name = "Pet Chart";
        au.saveAuditTRail();
        
        JOptionPane.showMessageDialog(null, "Prescription information has been successfully stored");
       
    }
    
    public void autoviewPrcription(){
        try{
            String sql = "SELECT prescription_number AS `Rx#`, medicine AS `Rx`, CONCAT_WS(' ', volume, volume_p2) AS `Volume`, signa AS `Sig.` FROM `prescription` WHERE pet_id = '"+petid+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable6.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void updateItemQty(){
        DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();
        
        int size = jTable2.getRowCount();//
        int f = size - 1;

        for (int x = 0; x <= f; x++) {
            
        int getsubtractQty = Integer.parseInt(model2.getValueAt(x, 4).toString());
        
        try{
            String sql = "SELECT `quantity` FROM item WHERE name = '"+model2.getValueAt(x, 0).toString()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getqty = rs.getString("quantity");
                currentQTY = Integer.parseInt(getqty);
            }
            
            newQTY = currentQTY - getsubtractQty;
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        try {
                    String sql = "UPDATE `item` SET `quantity`= ? WHERE name = '"+model2.getValueAt(x, 0).toString()+"'";
                    ps = conn_db.prepareStatement(sql);
                    ps.setInt(1, newQTY);
                    ps.execute();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
        }
        
        JOptionPane.showMessageDialog(null, "Medicine given has been successfully stored ");
        
    }
    
    
    public void saveindaily_data_diagnosis_symptoms(){
        
        int size = symptoms.getRowCount();//
        int f = size - 1;

        for (int x = 0; x <= f; x++) {
            if (x == f) {
                jTextArea3.append(symptoms.getValueAt(x, 0).toString().toUpperCase());
            } else {
                jTextArea3.append(symptoms.getValueAt(x, 0).toString().toUpperCase() + ", ");
            }
        }
        
        String diagnosis = "";
        String progosis = "";
        
        if(jRadioButton4.isSelected()){
            diagnosis = "("+diagnosisaddon+") "+jLabel3.getText();
        }else{
            progosis = "("+prognosisaddon+") "+jLabel3.getText();
        }
        
        try {
                    String sql = "Insert into daily_data_diagnosis_symptoms (`pet_id`,`diagnosis`,`prognosis`,`symtoms_given`,`day`,`month`,`year`) values (?,?,?,?,?,?,?)";
                    ps = conn_db.prepareStatement(sql);
                    ps.setString(1, petid);
                    ps.setString(2, diagnosis.toUpperCase());
                    ps.setString(3, progosis.toUpperCase());
                    ps.setString(4, jTextArea3.getText());
                    ps.setString(5, day);
                    ps.setString(6, month);
                    ps.setString(7, year);
                    ps.execute();
                    
                    JOptionPane.showMessageDialog(null, "Diagnosis information has been successfully stored");

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
        
        audit_trail au = new audit_trail();
        au.action_type = "Saved - diagnosis information by "+ pet_name.getText();
        au.module_name = "Pet Chart";
        au.saveAuditTRail();
        
    }
    
    public void saveindaily_data_diagnosis_medicine_given(){
        int size = jTable2.getRowCount();//
        int f = size - 1;

        for (int x = 0; x <= f; x++) {
 
            try {
                    String sql = "Insert into daily_data_diagnosis_medicine_given (`pet_id`,`medicine_given`,`volume`,`dose_unit`,`daily_usage`,`selected_qty`,`day`,`month`,`year`) values (?,?,?,?,?,?,?,?,?)";
                    ps = conn_db.prepareStatement(sql);
                    ps.setString(1, petid);
                    ps.setString(2, jTable2.getValueAt(x, 0).toString());
                    ps.setString(3, jTable2.getValueAt(x, 1).toString());
                    ps.setString(4, jTable2.getValueAt(x, 2).toString());
                    ps.setString(5, jTable2.getValueAt(x, 3).toString());
                    ps.setString(6, jTable2.getValueAt(x, 4).toString());
                    ps.setString(7, day);
                    ps.setString(8, month);
                    ps.setString(9, year);
                    ps.execute();
                    
                    JOptionPane.showMessageDialog(null, "Given medicine information has been successfully stored");

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
        }
        
        audit_trail au = new audit_trail();
        au.action_type = "Saved - medicine given information by "+ pet_name.getText();
        au.module_name = "Pet Chart";
        au.saveAuditTRail();
        
    }
    
    public void validateDateOnAppointment(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
        String dt = dateFormat.format(jCalendar1.getDate());
        
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
        
        String[] current = today_date.getText().split(" ");
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
            JOptionPane.showMessageDialog(null, "Invalid Year Detected!", "Date Validation", JOptionPane.ERROR_MESSAGE);
            jLabel17.setForeground(Color.red);
        }else{
            if(dtyear>curyear){
                appointment_date = dt;
                jLabel17.setForeground(Color.black);
            }else{
            if(dtmonth<curmonth){
                JOptionPane.showMessageDialog(null, "Invalid Month Detected!", "Date Validation", JOptionPane.ERROR_MESSAGE);
                jLabel17.setForeground(Color.red);
            }else{
                if(dtmonth==curmonth){
                    if(dtday<curday){
                        JOptionPane.showMessageDialog(null, "Invalid Day Detected!", "Date Validation", JOptionPane.ERROR_MESSAGE);
                        jLabel17.setForeground(Color.red);
                        }else{
                           appointment_date = dt;
                           jLabel17.setForeground(Color.black);
                        }
                    }else{
                        if(dtmonth>curmonth){
                            appointment_date = dt;
                            jLabel17.setForeground(Color.black);
                        }
                    }
                }
            }
        }
    }
    
    public void validateTimeOnAppointment(){
        hour.setValue(1);
        minute.setEditor(new JSpinner.NumberEditor(minute, "00"));
        JFormattedTextField txt = ((JSpinner.NumberEditor) hour.getEditor()).getTextField();
        ((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
        
        JFormattedTextField txt2 = ((JSpinner.NumberEditor) minute.getEditor()).getTextField();
        ((NumberFormatter) txt2.getFormatter()).setAllowsInvalid(false);
    }
    
    public void rbpdoff(){
        jRadioButton4.setVisible(false);
        jRadioButton3.setVisible(false);
    }
    
    public void rbpdon(){
        jRadioButton4.setVisible(true);
        jRadioButton3.setVisible(true);
    }
    
    public void openDiagnosisType(){
        if(jRadioButton4.isSelected()){
            ee.setVisible(true);    
            ee.setTitle("Diagnosis Type:");
            ee.add(jRadioButton6);
            ee.add(jRadioButton7);
            ee.setLayout(new GridLayout());
        }else{
            ee2.setVisible(true);
            ee2.setTitle("Diagnosis Type:");
            ee2.add(jRadioButton8);
            ee2.add(jRadioButton9);
            ee2.add(jRadioButton5);
            ee2.setLayout(new GridLayout());
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

        pet_number = new javax.swing.JTextField();
        today_date = new javax.swing.JTextField();
        owner_name = new javax.swing.JTextField();
        owner_address = new javax.swing.JTextField();
        pet_name = new javax.swing.JTextField();
        pet_type = new javax.swing.JTextField();
        pet_gender = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane10 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jButton14 = new javax.swing.JButton();
        jToolBar6 = new javax.swing.JToolBar();
        jButton17 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jButton10 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jToolBar2 = new javax.swing.JToolBar();
        jButton18 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        diagnosis = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        symptoms = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        points = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jTextField2 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel18 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jButton4 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jToolBar7 = new javax.swing.JToolBar();
        jButton16 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox2 = new javax.swing.JComboBox();
        jButton8 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox();
        jTextField4 = new javax.swing.JTextField();
        jRadioButton2 = new javax.swing.JRadioButton();
        jTextField5 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jToolBar9 = new javax.swing.JToolBar();
        jButton39 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton23 = new javax.swing.JButton();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jComboBox3 = new javax.swing.JComboBox();
        jPanel14 = new javax.swing.JPanel();
        jToolBar11 = new javax.swing.JToolBar();
        jButton35 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane22 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane23 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jToolBar4 = new javax.swing.JToolBar();
        jButton34 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jScrollPane17 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        immunization_table = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        jButton3 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jToolBar10 = new javax.swing.JToolBar();
        jButton31 = new javax.swing.JButton();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jToolBar3 = new javax.swing.JToolBar();
        jButton5 = new javax.swing.JButton();
        jScrollPane8 = new javax.swing.JScrollPane();
        allergies_table = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jToolBar5 = new javax.swing.JToolBar();
        jButton6 = new javax.swing.JButton();
        jScrollPane9 = new javax.swing.JScrollPane();
        vitals_table = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jToolBar8 = new javax.swing.JToolBar();
        jButton21 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jComboBox5 = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane24 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane25 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList();
        jLabel15 = new javax.swing.JLabel();
        hour = new javax.swing.JSpinner();
        minute = new javax.swing.JSpinner();
        jLabel16 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        a = new javax.swing.JLabel();
        b = new javax.swing.JLabel();
        c = new javax.swing.JLabel();
        d = new javax.swing.JLabel();
        a1 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jToolBar12 = new javax.swing.JToolBar();
        jButton40 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jRadioButton10 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        picture = new javax.swing.JLabel();
        pet_breed = new javax.swing.JTextField();
        jButton42 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jRadioButton5 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton9 = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        jButton41 = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pet_number.setEditable(false);
        pet_number.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pet_number.setText("00001");
        pet_number.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Patient No.", 0, 0, null, new java.awt.Color(66, 66, 66)));
        add(pet_number, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 140, 50));

        today_date.setEditable(false);
        today_date.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        today_date.setText("21 OCT 2015");
        today_date.setBorder(javax.swing.BorderFactory.createTitledBorder("Date Today"));
        add(today_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 180, 50));

        owner_name.setEditable(false);
        owner_name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        owner_name.setBorder(javax.swing.BorderFactory.createTitledBorder("Pet Owner's Name"));
        add(owner_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 490, 50));

        owner_address.setEditable(false);
        owner_address.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        owner_address.setBorder(javax.swing.BorderFactory.createTitledBorder("Address"));
        add(owner_address, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 80, 490, 50));

        pet_name.setEditable(false);
        pet_name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pet_name.setBorder(javax.swing.BorderFactory.createTitledBorder("Pet Name"));
        add(pet_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 340, 50));

        pet_type.setEditable(false);
        pet_type.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pet_type.setBorder(javax.swing.BorderFactory.createTitledBorder("Pet Type"));
        add(pet_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 20, 170, 50));

        pet_gender.setEditable(false);
        pet_gender.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pet_gender.setBorder(javax.swing.BorderFactory.createTitledBorder("Gender"));
        add(pet_gender, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 20, 160, 50));

        jTabbedPane1.setToolTipText("");

        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Acquire Service(s)"));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel8.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 29, 310, -1));

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jList1MouseEntered(evt);
            }
        });
        jScrollPane10.setViewportView(jList1);

        jPanel8.add(jScrollPane10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 310, 220));

        jButton14.setText("Show");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 100, -1));

        jPanel7.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, 350, 330));

        jToolBar6.setRollover(true);

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_interface/agt_forward.png"))); // NOI18N
        jButton17.setText("Next");
        jButton17.setFocusable(false);
        jButton17.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });
        jToolBar6.add(jButton17);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Clear Green Button.png"))); // NOI18N
        jButton7.setText("Done");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jToolBar6.add(jButton7);

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Write Document.png"))); // NOI18N
        jButton15.setText("View All");
        jButton15.setFocusable(false);
        jButton15.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });
        jToolBar6.add(jButton15);

        jPanel7.add(jToolBar6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 40));

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Selected Service(s)"));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList2MouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(jList2);

        jPanel9.add(jScrollPane11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 300, 280));

        jPanel7.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 50, 340, 330));

        jButton10.setText("Add");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 100, -1));

        jButton13.setText("Remove");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel7.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, 100, -1));

        jTabbedPane2.addTab("Initialization", jPanel7);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToolBar2.setRollover(true);

        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_interface/agt_back.png"))); // NOI18N
        jButton18.setText("Previous");
        jButton18.setFocusable(false);
        jButton18.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton18);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_interface/agt_forward.png"))); // NOI18N
        jButton9.setText("Next");
        jButton9.setFocusable(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton9);

        jButton33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Clear Green Button.png"))); // NOI18N
        jButton33.setText("Done");
        jButton33.setFocusable(false);
        jButton33.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton33);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Export To Document.png"))); // NOI18N
        jButton1.setText("Final Result");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton1);

        jPanel3.add(jToolBar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 40));

        diagnosis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Suggested Diagnosis"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(diagnosis);
        diagnosis.getColumnModel().getColumn(0).setResizable(false);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 270, 260));

        symptoms.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Chief Complaints"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(symptoms);
        symptoms.getColumnModel().getColumn(0).setResizable(false);

        jPanel3.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 250, 260));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane5.setViewportView(jTextArea1);

        jPanel3.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 150, 420, 260));

        points.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Diagnosis", "Points"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(points);
        points.getColumnModel().getColumn(0).setResizable(false);
        points.getColumnModel().getColumn(1).setResizable(false);

        jPanel3.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(532, 376, 0, 70));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 170, 20));

        jLabel1.setText("Suggested Diagnosis not accurate?");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, -1, -1));

        jRadioButton1.setText("Finalize Result");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, -1, 30));

        jTextField2.setToolTipText("Hit \"Enter\" to finalize the diagnosis");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel3.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 70, 200, 30));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Input Chief Complaints"));

        jTextField1.setToolTipText("Hit \"Enter\" to add symptoms");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 280, 50));

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane19.setViewportView(jTextArea3);

        jPanel3.add(jScrollPane19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 387, 0, 90));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel18.setText("Result:");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 150, 20));

        jRadioButton3.setText("Prognosis");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 110, -1, -1));

        jRadioButton4.setText("Diagnosis");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 110, -1, -1));

        jButton4.setText("Done");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 70, 60, -1));

        jTabbedPane2.addTab("Chief Complains", jPanel3);

        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToolBar7.setRollover(true);

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_interface/agt_back.png"))); // NOI18N
        jButton16.setText("Previous");
        jButton16.setFocusable(false);
        jButton16.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jToolBar7.add(jButton16);

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_interface/agt_forward.png"))); // NOI18N
        jButton12.setText("Next");
        jButton12.setFocusable(false);
        jButton12.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jToolBar7.add(jButton12);

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Clear Green Button.png"))); // NOI18N
        jButton11.setText("Done");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jToolBar7.add(jButton11);

        jButton26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Write Document.png"))); // NOI18N
        jButton26.setText("Update");
        jButton26.setFocusable(false);
        jButton26.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        jToolBar7.add(jButton26);

        jButton29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_interface/backup_green_button.png"))); // NOI18N
        jButton29.setText("Refresh");
        jButton29.setFocusable(false);
        jButton29.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });
        jToolBar7.add(jButton29);

        jButton20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_interface/dialog_cancel.png"))); // NOI18N
        jButton20.setText("Remove Row(s)");
        jButton20.setFocusable(false);
        jButton20.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        jToolBar7.add(jButton20);

        jButton30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Gear.png"))); // NOI18N
        jButton30.setText("Immunization");
        jButton30.setFocusable(false);
        jButton30.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });
        jToolBar7.add(jButton30);

        jPanel10.add(jToolBar7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 40));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(jTable1);

        jPanel10.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 410, 230));

        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jPanel10.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 210, -1));

        jButton8.setText("View");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 90, 20));

        jButton19.setText("Add");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 410, 110, 30));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine", "Volume", "Dose Unit", "Usage", "Selected Qty"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(jTable2);
        jTable2.getColumnModel().getColumn(0).setResizable(false);
        jTable2.getColumnModel().getColumn(1).setResizable(false);
        jTable2.getColumnModel().getColumn(2).setResizable(false);
        jTable2.getColumnModel().getColumn(3).setResizable(false);
        jTable2.getColumnModel().getColumn(4).setResizable(false);

        jPanel10.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 90, 510, 230));

        jLabel4.setText("Quantity");
        jPanel10.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 90, 30));
        jPanel10.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 410, 190, 30));

        jLabel5.setText("Classification");
        jPanel10.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 60, 90, 20));

        jLabel6.setText("Volume");
        jPanel10.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 90, 30));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "cc", "cm", "mg", "ml", "mm", "l", "g", "kg", "oz", "lb" }));
        jComboBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });
        jPanel10.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 370, 110, 30));

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });
        jPanel10.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 370, 190, 30));

        jRadioButton2.setText("Input item name");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jPanel10.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, -1, 20));

        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });
        jPanel10.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 60, 210, -1));

        jLabel12.setText("Usage");
        jPanel10.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 90, 30));

        jButton27.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton27.setText("-");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton27, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 330, 50, 30));

        jButton28.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton28.setText("+");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton28, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 330, 50, 30));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("0");
        jPanel10.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, 70, 30));

        jTabbedPane2.addTab("Medicine Given", jPanel10);

        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToolBar9.setRollover(true);

        jButton39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_interface/agt_back.png"))); // NOI18N
        jButton39.setText("Previous");
        jButton39.setFocusable(false);
        jButton39.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });
        jToolBar9.add(jButton39);

        jButton38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_interface/agt_forward.png"))); // NOI18N
        jButton38.setText("Next");
        jButton38.setFocusable(false);
        jButton38.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });
        jToolBar9.add(jButton38);

        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Clear Green Button.png"))); // NOI18N
        jButton22.setText("Done");
        jButton22.setFocusable(false);
        jButton22.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        jToolBar9.add(jButton22);

        jButton25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Write Document.png"))); // NOI18N
        jButton25.setText("Update");
        jButton25.setFocusable(false);
        jButton25.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jToolBar9.add(jButton25);

        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_interface/dialog_cancel.png"))); // NOI18N
        jButton24.setText("Remove Row(s)");
        jButton24.setToolTipText("");
        jButton24.setFocusable(false);
        jButton24.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        jToolBar9.add(jButton24);

        jPanel11.add(jToolBar9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 40));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("SIG.");
        jPanel11.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 60, -1));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("RX");
        jPanel11.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 60, -1));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("VOLUME");
        jPanel11.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));
        jPanel11.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 260, -1));

        jTextField8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField8KeyTyped(evt);
            }
        });
        jPanel11.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 160, -1));

        jTextArea2.setColumns(20);
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setWrapStyleWord(true);
        jScrollPane14.setViewportView(jTextArea2);

        jPanel11.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 340, 70));

        jButton23.setText("Add");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton23, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 80, 100, -1));

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rx#", "Rx", "Volume", "Sig."
            }
        ));
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane15.setViewportView(jTable5);

        jPanel11.add(jScrollPane15, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 810, 230));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "cc", "cm", "mg", "ml", "mm", "l", "g", "kg", "oz", "lb" }));
        jPanel11.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 90, -1));

        jTabbedPane2.addTab("Prescription", jPanel11);

        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToolBar11.setRollover(true);

        jButton35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Clear Green Button.png"))); // NOI18N
        jButton35.setText("Save Date Visit History");
        jButton35.setFocusable(false);
        jButton35.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });
        jToolBar11.add(jButton35);

        jPanel14.add(jToolBar11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 40));

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder("Today's Initialization(s)"));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane20.setViewportView(jTable7);

        jPanel15.add(jScrollPane20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 400, 120));

        jPanel14.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 440, 170));

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Today's Symp. & Diagnosis"));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane21.setViewportView(jTable8);

        jPanel16.add(jScrollPane21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 400, 120));

        jPanel14.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 440, 170));

        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder("Today's Given Medicine(s)"));
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane22.setViewportView(jTable9);

        jPanel17.add(jScrollPane22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 400, 120));

        jPanel14.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, 440, 170));

        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Today's Prescription(s)"));
        jPanel18.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane23.setViewportView(jTable10);

        jPanel18.add(jScrollPane23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 400, 120));

        jPanel14.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 250, 440, 170));

        jTabbedPane2.addTab("Daily Data Entry Overview", jPanel14);

        jTabbedPane1.addTab("Daily Data Entry", jTabbedPane2);

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToolBar4.setRollover(true);

        jButton34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_interface/move.png"))); // NOI18N
        jButton34.setText("Open Encounter Notes");
        jButton34.setFocusable(false);
        jButton34.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });
        jToolBar4.add(jButton34);

        jButton32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Import Document.png"))); // NOI18N
        jButton32.setText("View Medical Records");
        jButton32.setFocusable(false);
        jButton32.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });
        jToolBar4.add(jButton32);

        jPanel4.add(jToolBar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 40));

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(table);

        jPanel4.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 60, 600, 410));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(jTable4);

        jPanel4.add(jScrollPane17, new org.netbeans.lib.awtextra.AbsoluteConstraints(642, 62, 320, 410));

        jLabel7.setText("Daily Data Entry Records");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 40, 260, 20));

        jLabel8.setText("Physical Examination Records");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 40, 280, 20));

        jTabbedPane1.addTab("Medical History", jPanel4);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        immunization_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(immunization_table);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 60, 940, 410));

        jToolBar1.setRollover(true);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Gear.png"))); // NOI18N
        jButton3.setText("Manage Immunization History");
        jButton3.setFocusable(false);
        jButton3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);

        jPanel2.add(jToolBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 40));

        jTabbedPane1.addTab("Immunization History", jPanel2);

        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToolBar10.setRollover(true);

        jButton31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Gear.png"))); // NOI18N
        jButton31.setText("Manage Prescription");
        jButton31.setFocusable(false);
        jButton31.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });
        jToolBar10.add(jButton31);

        jPanel13.add(jToolBar10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 40));

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane18.setViewportView(jTable6);

        jPanel13.add(jScrollPane18, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 60, 940, 410));

        jTabbedPane1.addTab("Prescription History", jPanel13);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToolBar3.setRollover(true);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Gear.png"))); // NOI18N
        jButton5.setText("Manage Allergies");
        jButton5.setFocusable(false);
        jButton5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar3.add(jButton5);

        jPanel1.add(jToolBar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 40));

        allergies_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane8.setViewportView(allergies_table);

        jPanel1.add(jScrollPane8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 940, 410));

        jTabbedPane1.addTab("Allergies", jPanel1);

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToolBar5.setRollover(true);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Gear.png"))); // NOI18N
        jButton6.setText("Manage Physical Examination");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar5.add(jButton6);

        jPanel5.add(jToolBar5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 40));

        vitals_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane9.setViewportView(vitals_table);

        jPanel5.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 940, 410));

        jTabbedPane1.addTab("Vitals", jPanel5);

        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToolBar8.setRollover(true);

        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/New Document.png"))); // NOI18N
        jButton21.setText("Set Appointment");
        jButton21.setFocusable(false);
        jButton21.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        jToolBar8.add(jButton21);

        jButton44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Write Document.png"))); // NOI18N
        jButton44.setText("Update");
        jButton44.setFocusable(false);
        jButton44.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });
        jToolBar8.add(jButton44);

        jButton37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Gear.png"))); // NOI18N
        jButton37.setText("Manage All Pending SMS");
        jButton37.setFocusable(false);
        jButton37.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });
        jToolBar8.add(jButton37);

        jPanel12.add(jToolBar8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 40));

        jCalendar1.setBorder(javax.swing.BorderFactory.createTitledBorder("Set a date"));
        jCalendar1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jCalendar1PropertyChange(evt);
            }
        });
        jPanel12.add(jCalendar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        jPanel12.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 250, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Set a Time");
        jPanel12.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, -1, 30));

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder("Reason for Appointment"));
        jPanel19.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextArea4.setColumns(20);
        jTextArea4.setLineWrap(true);
        jTextArea4.setRows(5);
        jTextArea4.setWrapStyleWord(true);
        jScrollPane24.setViewportView(jTextArea4);

        jPanel19.add(jScrollPane24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 310, 200));

        jPanel12.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 170, 330, 230));

        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder("Pending Appoinments"));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jList3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList3MouseClicked(evt);
            }
        });
        jScrollPane25.setViewportView(jList3);

        jPanel20.add(jScrollPane25, new org.netbeans.lib.awtextra.AbsoluteConstraints(8, 23, 210, 200));

        jPanel12.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 170, 230, 230));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Contact No.");
        jPanel12.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, 30));

        hour.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                hourStateChanged(evt);
            }
        });
        hour.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                hourKeyTyped(evt);
            }
        });
        jPanel12.add(hour, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, 60, -1));

        minute.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                minuteStateChanged(evt);
            }
        });
        minute.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                minuteKeyTyped(evt);
            }
        });
        jPanel12.add(minute, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 60, -1));

        jLabel16.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText(":");
        jPanel12.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 20, 20));

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AM", "PM" }));
        jPanel12.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, -1, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel12.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 200, 30));

        a.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        a.setForeground(new java.awt.Color(108, 98, 98));
        a.setText("An SMS notification will be send to No.:");

        b.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        b.setForeground(new java.awt.Color(94, 76, 1));
        b.setText("+6399999999999");

        c.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        c.setForeground(new java.awt.Color(108, 98, 98));
        c.setText("25 DEC 2015");

        d.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        d.setForeground(new java.awt.Color(108, 98, 98));
        d.setText("at 12:45 AM");

        a1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        a1.setForeground(new java.awt.Color(108, 98, 98));
        a1.setText("on");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(120, Short.MAX_VALUE)
                .addComponent(a1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel24Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(a)
                        .addGroup(jPanel24Layout.createSequentialGroup()
                            .addComponent(b)
                            .addGap(29, 29, 29)
                            .addComponent(c, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(d, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(a1)
                .addGap(31, 31, 31))
            .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel24Layout.createSequentialGroup()
                    .addGap(0, 11, Short.MAX_VALUE)
                    .addComponent(a)
                    .addGap(5, 5, 5)
                    .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(c, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(b, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(5, 5, 5)
                    .addComponent(d)
                    .addGap(0, 11, Short.MAX_VALUE)))
        );

        jPanel12.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 70, 230, 80));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setText("Set Date:");
        jPanel12.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 60, 30));

        jTabbedPane1.addTab("Appointments", jPanel12);

        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToolBar12.setRollover(true);

        jButton40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Clear Green Button.png"))); // NOI18N
        jButton40.setText("Release Pet");
        jButton40.setFocusable(false);
        jButton40.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });
        jToolBar12.add(jButton40);

        jButton36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_interface/backup_green_button.png"))); // NOI18N
        jButton36.setText("Undo Release");
        jButton36.setFocusable(false);
        jButton36.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });
        jToolBar12.add(jButton36);

        jPanel21.add(jToolBar12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 40));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane16.setViewportView(jTable3);

        jPanel21.add(jScrollPane16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 880, 350));

        jRadioButton10.setText("View released date");
        jRadioButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton10ActionPerformed(evt);
            }
        });
        jPanel21.add(jRadioButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, -1, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jPanel21.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, 560, 30));

        jTabbedPane1.addTab("Confinement", jPanel21);

        add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, 990, 530));

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_interface/folder_open_16.png"))); // NOI18N
        jButton2.setText("Upload/Update Image");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 180, -1));

        picture.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        picture.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/default_profile_3_reasonably_small.png"))); // NOI18N
        jScrollPane1.setViewportView(picture);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 180, 180));

        pet_breed.setEditable(false);
        pet_breed.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pet_breed.setBorder(javax.swing.BorderFactory.createTitledBorder("Breed"));
        pet_breed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pet_breedActionPerformed(evt);
            }
        });
        add(pet_breed, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 20, 140, 50));

        jButton42.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Donate.png"))); // NOI18N
        jButton42.setText("Client's Bill");
        jButton42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton42.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton42.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });
        add(jButton42, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, 180, -1));

        jButton43.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/basket_add.png"))); // NOI18N
        jButton43.setText("Client's  Item Purchase");
        jButton43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton43.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton43.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });
        add(jButton43, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 180, -1));

        jPanel22.setBackground(new java.awt.Color(1, 1, 1));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jRadioButton5.setBackground(new java.awt.Color(74, 74, 74));
        jRadioButton5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton5.setForeground(new java.awt.Color(254, 254, 254));
        jRadioButton5.setText("Poor");
        jRadioButton5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRadioButton5.setOpaque(true);
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });
        jPanel22.add(jRadioButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 90, -1));

        jRadioButton6.setBackground(new java.awt.Color(74, 74, 74));
        jRadioButton6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton6.setForeground(new java.awt.Color(254, 254, 254));
        jRadioButton6.setText("Tentative");
        jRadioButton6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRadioButton6.setOpaque(true);
        jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton6ActionPerformed(evt);
            }
        });
        jPanel22.add(jRadioButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 120, -1));

        jRadioButton7.setBackground(new java.awt.Color(74, 74, 74));
        jRadioButton7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton7.setForeground(new java.awt.Color(254, 254, 254));
        jRadioButton7.setText("Definitive");
        jRadioButton7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRadioButton7.setOpaque(true);
        jRadioButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton7ActionPerformed(evt);
            }
        });
        jPanel22.add(jRadioButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 120, -1));

        jRadioButton8.setBackground(new java.awt.Color(74, 74, 74));
        jRadioButton8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton8.setForeground(new java.awt.Color(254, 254, 254));
        jRadioButton8.setText("Favorable");
        jRadioButton8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRadioButton8.setOpaque(true);
        jRadioButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton8ActionPerformed(evt);
            }
        });
        jPanel22.add(jRadioButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 120, -1));

        jRadioButton9.setBackground(new java.awt.Color(74, 74, 74));
        jRadioButton9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton9.setForeground(new java.awt.Color(254, 254, 254));
        jRadioButton9.setText("Guarded");
        jRadioButton9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jRadioButton9.setOpaque(true);
        jRadioButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton9ActionPerformed(evt);
            }
        });
        jPanel22.add(jRadioButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 110, -1));

        jLabel21.setText("jLabel21");
        jPanel22.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 150, -1));

        add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 660, 170, 20));

        jButton41.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Gear.png"))); // NOI18N
        jButton41.setText("Services Maintenance");
        jButton41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });
        add(jButton41, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, 180, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        uploadImage();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new immunization_history().setVisible(true);
        pet_id.setText(petid);
        autoShowHistory();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        if(init.equals("")){
            JOptionPane.showMessageDialog(null, "Please initialize the daily data entry first!");
        }else{
            if(jTextField1.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Empty Field Detected!");
            }else{
                DefaultTableModel model=(DefaultTableModel) symptoms.getModel();
                autoRecommend();
                model.addRow(new Object[]{jTextField1.getText()});jTextField1.setText("");

                int size = model.getRowCount();
                int f = size-1;

                for(int x=1; x<=f; x++){
                    if(symptoms.getValueAt(f, 0).toString().equals(symptoms.getValueAt(x-1, 0).toString())||recommendTrigger ==1){
                        //JOptionPane.showMessageDialog(null,"Magkaparehas!"+"\n"+diagnose.getValueAt(f, 0).toString()+" is equal sa "+diagnose.getValueAt(x-1, 0).toString());
                        model.removeRow(f);
                        f--;
                        recommendTrigger=0;
                    }else{
                         //JOptionPane.showMessageDialog(null,"Add pa more! ");
                     }
                }
            }
        }
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(init.equals("")){
            JOptionPane.showMessageDialog(null, "Please initialize a service the daily data entry first!", "Validation", JOptionPane.INFORMATION_MESSAGE);
            jTabbedPane2.setSelectedIndex(0);
        }else{
            DefaultTableModel modelx=(DefaultTableModel) points.getModel();
            DefaultTableModel modelxy=(DefaultTableModel) symptoms.getModel();
            int size = modelx.getRowCount();
            int size2 = modelxy.getRowCount();

            if(size == 0 || size2 == 0 ){
                JOptionPane.showMessageDialog(null, "You haven't given any symptoms yet.");
            }else{
                if(size2 < 2){
                    JOptionPane.showMessageDialog(null, "Accuracy of the diagnosis is not reliable when there is less symtoms given.\nPlease add more than 3");
                }else{
                    checkifPointsAreEqual();

                    jLabel1.setVisible(true);
                    jRadioButton1.setVisible(true);
                    
                    jButton33.setEnabled(true);
                    
                    jTextArea1.setText("");
                    showOtherResults();
                    jTextArea1.setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        if(jRadioButton1.isSelected()){
            jTextField2.setVisible(true);
            jTextField2.setText(jLabel3.getText());
            jButton4.setVisible(true);
        }else{
             jTextField2.setVisible(false);
             jTextField2.setText("");
             jButton4.setVisible(false);
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void pet_breedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pet_breedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pet_breedActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        new allergies().setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        new examinations().setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        int s = jList2.getModel().getSize();
        if(s==0){
            JOptionPane.showMessageDialog(null, "You haven't selected any service(s) yet");
        }else{
           init="ok";
           SaveAcquiredServices();
           jButton7.setEnabled(false);
           jTabbedPane2.setSelectedIndex(1);
           x.viewinit();
           intializationcheck = "started";
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        int count = jTable2.getRowCount();
        if(count==0){
            JOptionPane.showMessageDialog(null, "No medicine(s) has been given yet!");
        }else{
          saveindaily_data_diagnosis_medicine_given();
          updateItemQty();
          x.viewgivenmed();
          jTabbedPane2.setSelectedIndex(3);
          jButton20.setEnabled(false);  
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jList1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseEntered
        
    }//GEN-LAST:event_jList1MouseEntered
    DefaultListModel<String> model = new DefaultListModel();
    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        jButton10.setEnabled(true);
        jButton14.setEnabled(false);
    }//GEN-LAST:event_jList1MouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        jButton14.setEnabled(true);
    }//GEN-LAST:event_jComboBox1ActionPerformed
        DefaultListModel<String> modelx = new DefaultListModel();
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
       modelx.addElement(jList1.getModel().getElementAt(jList1.getSelectedIndex()).toString());
       jList2.setModel(modelx);
       ((DefaultListModel) jList1.getModel()).remove(jList1.getSelectedIndex());
       jButton7.setEnabled(true);
       jButton10.setEnabled(false);
       jButton13.setEnabled(false);
        
        int size = jList2.getModel().getSize();
        int f = size-1;
        
        for(int x=1; x<=f; x++){
            if(jList2.getModel().getElementAt(f).toString().equals(jList2.getModel().getElementAt(x-1).toString())){
                //JOptionPane.showMessageDialog(null,"Redundancy of symptoms detected!");
                modelx.removeElementAt(f);
                f--;
            }else{
                 //JOptionPane.showMessageDialog(null,"Add pa more! ");
             }
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        fillservicelist();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseClicked
        jButton10.setEnabled(false);
        jButton13.setEnabled(true);
    }//GEN-LAST:event_jList2MouseClicked

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        if(jButton15.getText().equals("Update")){
            if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Are you sure you want to remove from acquired services?",
            "Removing Acquired Services", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            RemoveSelectedServicesonDB();
            jButton15.setText("View All");
            
            }
            else {

                System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
                return;
            }
            
        }else{
            jButton10.setEnabled(false);
            jButton13.setEnabled(false);
            RemoveSelectedServices();
        }
        
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        if(count==0){
            count++;
            jButton15.setText("Update");
            jButton7.setEnabled(false);
            jButton10.setEnabled(false);
            jButton13.setEnabled(true);
            SetUpdateAcqSrv();
        }else{
            count--;
            DefaultListModel dimx = new DefaultListModel();
            dimx.removeAllElements();
            jList2.setModel(dimx);
            jButton15.setText("View All");
            jButton7.setEnabled(true);
            jButton10.setEnabled(false);
            jButton13.setEnabled(false);
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
            jTabbedPane2.setSelectedIndex(1);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
            jTabbedPane2.setSelectedIndex(2);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
            jTabbedPane2.setSelectedIndex(3);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
            jTabbedPane2.setSelectedIndex(1);
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
            jTabbedPane2.setSelectedIndex(0);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        
    }//GEN-LAST:event_jTable1MouseEntered

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        fillMedicinewithfilters();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        DefaultTableModel model3 = (DefaultTableModel) jTable1.getModel();
         if(Integer.parseInt(model3.getValueAt(jTable1.getSelectedRow(), 1).toString())==0){
             JOptionPane.showMessageDialog(null, "In-stock quantity is zero!");
             medgivenfieldsOFF();
         }else{
             medgivenfieldsON();
         }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c)
                || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE || evt.getKeyChar() == '.')) {
            //  evt.getKeyChar() == '.' does accept point when jtextfield accepts decimal number
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_jTextField4KeyTyped

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox4ActionPerformed
        volumep2 = jComboBox4.getSelectedItem().toString();
    }//GEN-LAST:event_jComboBox4ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        if(jTextField4.getText().equals("") || jTextField3.getText().equals("") || Integer.parseInt(jLabel13.getText())==0){
            JOptionPane.showMessageDialog(null, "Empty field(s) detected!");
        }else{
            DefaultTableModel model2 = (DefaultTableModel) jTable1.getModel();
            DefaultTableModel model3 = (DefaultTableModel) jTable2.getModel();
            model3.addRow(new Object[]{model2.getValueAt(jTable1.getSelectedRow(), 0).toString(),jTextField4.getText().toUpperCase()+" "+volumep2.toUpperCase(),jComboBox2.getSelectedItem().toString().toUpperCase(),jTextField3.getText().toUpperCase(), jLabel13.getText()});
            medgivenfieldsOFF();
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Are you sure you want to remove row(s)?",
            "Remove Row(s)", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            removeMedGivenRows();
            jButton19.setEnabled(true);
            medgivenfieldsOFF();
        }
        else {

            System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
            jButton20.setEnabled(false);
            return;
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        jButton20.setEnabled(true);
        jButton26.setEnabled(true);
        
        autoSearchByTableClick();
        
        String string3 = jTable2.getValueAt(jTable2.getSelectedRow(), 1).toString();
        String[] parts3 = string3.split(" ");
        String p1 = parts3[0]; // 004
        String p2 = parts3[1]; // 034556
        
        jTextField4.setText(p1);
        jComboBox4.setSelectedItem(p2.toLowerCase());
        jComboBox2.setSelectedItem(jTable2.getValueAt(jTable2.getSelectedRow(), 2).toString());
        jTextField3.setText(jTable2.getValueAt(jTable2.getSelectedRow(), 3).toString());
        jLabel13.setText(jTable2.getValueAt(jTable2.getSelectedRow(), 4).toString());
        
        medgivenfieldsON();
        
        jButton19.setEnabled(false);
    }//GEN-LAST:event_jTable2MouseClicked

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        if(jRadioButton2.isSelected()){//
            jTextField5.setEnabled(true);
        }else{
            jTextField5.setEnabled(false);
            
            DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
            int rows1 = model1.getRowCount();
            for (int i = rows1 - 1; i >= 0; i--) {
                model1.removeRow(i);
            }
        }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        autoSearch();
    }//GEN-LAST:event_jTextField5KeyReleased

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        int size = jTable5.getRowCount();//
        if(size==0){
            JOptionPane.showMessageDialog(null, "No prescription(s) that has been added yet");
        }else{
           saveprescription();
           autoviewPrcription();
           x.viewprescription();
           jTabbedPane2.setSelectedIndex(4);
        }
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        removePrescriptionRows();
        jButton25.setEnabled(false);
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        if(jTextField7.getText().equals("") || jTextField8.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Empty field(s) detected!");
        }else{
            DefaultTableModel model2 = (DefaultTableModel) jTable5.getModel();
            model2.addRow(new Object[]{rxNum,jTextField7.getText().toUpperCase(),jTextField8.getText().toUpperCase()+" "+jComboBox3.getSelectedItem().toString().toUpperCase(),jTextArea2.getText().toUpperCase()});
            clearPrescription();
        }
    }//GEN-LAST:event_jButton23ActionPerformed

    private void jTextField8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField8KeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c)
                || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE || evt.getKeyChar() == '.')) {
            //  evt.getKeyChar() == '.' does accept point when jtextfield accepts decimal number
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_jTextField8KeyTyped

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        jButton25.setEnabled(true);
    }//GEN-LAST:event_jTable5MouseClicked

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        jButton25.setEnabled(false);
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        jButton19.setEnabled(true);
        
        DefaultTableModel model2 = (DefaultTableModel) jTable2.getModel();
        DefaultTableModel model3 = (DefaultTableModel) jTable1.getModel();
        
        model2.setValueAt(model3.getValueAt(jTable1.getSelectedRow(), 0).toString(), jTable2.getSelectedRow(), 0);
        model2.setValueAt(jTextField4.getText()+" "+jComboBox4.getSelectedItem().toString(), jTable2.getSelectedRow(), 1);
        model2.setValueAt(jComboBox2.getSelectedItem().toString(), jTable2.getSelectedRow(), 2);
        model2.setValueAt(jTextField3.getText().toString(), jTable2.getSelectedRow(), 3);
        model2.setValueAt(jLabel13.getText(), jTable2.getSelectedRow(), 4);

        medgivenfieldsOFF();
        DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
            int rows1 = model1.getRowCount();
            for (int i = rows1 - 1; i >= 0; i--) {
                model1.removeRow(i);
            }
        jButton19.setEnabled(true);
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        DefaultTableModel model2 = (DefaultTableModel) jTable1.getModel();
        int getsubtractQty = Integer.parseInt(model2.getValueAt(jTable1.getSelectedRow(), 1).toString());
        
        if(getsubtractQty==0){
            
        }else{
            subtractQty = getsubtractQty-1;
        
            model2.setValueAt(subtractQty, jTable1.getSelectedRow(), 1);

            int addqty = Integer.parseInt(jLabel13.getText())+1;
            jLabel13.setText(Integer.toString(addqty));
        }
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        DefaultTableModel model2 = (DefaultTableModel) jTable1.getModel();
        int getsubtractQty = Integer.parseInt(model2.getValueAt(jTable1.getSelectedRow(), 1).toString());
        
        if(Integer.parseInt(jLabel13.getText())==0){
            
        }else{
            subtractQty = getsubtractQty+1;
        
            model2.setValueAt(subtractQty, jTable1.getSelectedRow(), 1);

            int addqty = Integer.parseInt(jLabel13.getText())-1;
            jLabel13.setText(Integer.toString(addqty));
        }
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        medgivenfieldsOFF();
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        new immunization_history().setVisible(true);
        pet_id.setText(petid);
        autoShowHistory();
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        jTabbedPane1.setSelectedIndex(0);
        jTabbedPane2.setSelectedIndex(3);
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        if(jLabel3.getText().equals("")){
            JOptionPane.showMessageDialog(null, "No result detected", "Validation", JOptionPane.ERROR_MESSAGE);
        }else{
            if(jRadioButton4.isSelected()||jRadioButton3.isSelected()){
                if(jRadioButton4.isSelected()){
                if(diagnosisaddon.equals("")){
                    JOptionPane.showMessageDialog(null, "Please choose which diagnosis type", "Validation", JOptionPane.ERROR_MESSAGE);
                }else{
                    saveindaily_data_diagnosis_symptoms();
                    x.viewsymptomsanddiagnosis();
                    jButton33.setEnabled(false);
                    jTabbedPane2.setSelectedIndex(2);
                }
            }else{
                if(jRadioButton3.isSelected()){
                    if(prognosisaddon.equals("")){
                    JOptionPane.showMessageDialog(null, "Please choose which diagnosis type", "Validation", JOptionPane.ERROR_MESSAGE);
                }else{
                    saveindaily_data_diagnosis_symptoms();
                    x.viewsymptomsanddiagnosis();
                    jButton33.setEnabled(false);
                    jTabbedPane2.setSelectedIndex(2);
                }
                }
            }
            }else{
                JOptionPane.showMessageDialog(null, "Please choose between diagnosis/prognosis menu", "Validation", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        new encounter_notes().setVisible(true);
    }//GEN-LAST:event_jButton34ActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        DefaultTableModel model3 = (DefaultTableModel) table.getModel();
        
        encounter_notes dd = new encounter_notes();
        
        new encounter_notes().setVisible(true);
        
        date_filter.setText(model3.getValueAt(table.getSelectedRow(), 0).toString());

        dd.ViewServicesAcquired();
        dd.getSymptomsAndDiagnosis();
        dd.fixTextPane();
        dd.getMedicineGiven();
    }//GEN-LAST:event_tableMouseClicked

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
        emr.viewMedicalRecordtoSave();
        emr.fixTextPane();
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        if(jTextArea4.getText().equals("")){
            JOptionPane.showMessageDialog(jComboBox1, "Empty Field(s) Detected", "Validation", JOptionPane.INFORMATION_MESSAGE);
        }else{
            setSMSAppointmentDate();
            emr.minutefix();
            emr.saveAppointment();
            emr.viewSavedAppointments();
            emr.saveSMSNotification();
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void hourStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_hourStateChanged
        int val = (int) hour.getValue();
        
        if(val>12){
            hour.setValue(1);
        }else{
            if(val<1){
                hour.setValue(1);
            }
        }
    }//GEN-LAST:event_hourStateChanged

    private void minuteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_minuteStateChanged
        int val = (int) minute.getValue();
        
        if(val>59){
            minute.setValue(0);
        }else{
            if(val<0){
                minute.setValue(0);
            }
        }
    }//GEN-LAST:event_minuteStateChanged

    private void hourKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hourKeyTyped

    }//GEN-LAST:event_hourKeyTyped

    private void minuteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_minuteKeyTyped

    }//GEN-LAST:event_minuteKeyTyped

    private void jCalendar1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jCalendar1PropertyChange
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("d MMM yyyy");
        String dt2 = dateFormat2.format(jCalendar1.getDate());
        jLabel17.setText(dt2);
        validateDateOnAppointment();
    }//GEN-LAST:event_jCalendar1PropertyChange

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        jTabbedPane2.setSelectedIndex(4);
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        jTabbedPane2.setSelectedIndex(3);
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jList3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList3MouseClicked
        emr.getAppointmentID();
        jButton21.setEnabled(false);
        jButton44.setEnabled(true);
    }//GEN-LAST:event_jList3MouseClicked

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        new loading_animation().setVisible(true);
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        jButton36.setEnabled(false);
        emr.releaseConfinement();
        emr.viewConfinement();
        jLabel2.setText("");
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        bill b = new bill();
        b.setVisible(true);
    }//GEN-LAST:event_jButton42ActionPerformed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        new purchase_item().setVisible(true);
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
        setSMSAppointmentDate();
        emr.minutefix();
        emr.updateAppointment();
        emr.viewSavedAppointments();
        jButton21.setEnabled(true);
        jButton44.setEnabled(false);
        JOptionPane.showMessageDialog(null, "The system will also update the notification date\non the Ongoing SMS Notification(s) panel automatically","Updating Notification Date",JOptionPane.INFORMATION_MESSAGE);
        new pending_sms().setVisible(true);
    }//GEN-LAST:event_jButton44ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        new pending_sms().setVisible(true);
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        if(jRadioButton4.isSelected()){
            jRadioButton3.setSelected(false);
            openDiagnosisType();
        }
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        if(jRadioButton3.isSelected()){
            jRadioButton4.setSelected(false);
            openDiagnosisType();
        }
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton6ActionPerformed
        if(jRadioButton6.isSelected()){
            jRadioButton7.setSelected(false);
            diagnosisaddon = jRadioButton6.getText();
        }
    }//GEN-LAST:event_jRadioButton6ActionPerformed

    private void jRadioButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton7ActionPerformed
        if(jRadioButton7.isSelected()){
            jRadioButton6.setSelected(false);
            diagnosisaddon = jRadioButton7.getText();
        }
    }//GEN-LAST:event_jRadioButton7ActionPerformed

    private void jRadioButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton8ActionPerformed
        if(jRadioButton8.isSelected()){
            jRadioButton9.setSelected(false);
            jRadioButton5.setSelected(false);
            prognosisaddon = jRadioButton8.getText();
        }
    }//GEN-LAST:event_jRadioButton8ActionPerformed

    private void jRadioButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton9ActionPerformed
        if(jRadioButton9.isSelected()){
            jRadioButton8.setSelected(false);
            jRadioButton5.setSelected(false);
            prognosisaddon = jRadioButton9.getText();
        }
    }//GEN-LAST:event_jRadioButton9ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        if(jRadioButton5.isSelected()){
            jRadioButton9.setSelected(false);
            jRadioButton8.setSelected(false);
            prognosisaddon = jRadioButton5.getText();
        }
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jLabel3.setText(jTextField2.getText().toString().toUpperCase());
        JOptionPane.showMessageDialog(null, "The final diagnosis is\n"+jLabel3.getText());
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        DefaultTableModel model3 = (DefaultTableModel) jTable4.getModel();
        examnumber = model3.getValueAt(jTable4.getSelectedRow(), 0).toString();
        new loading_animation1().setVisible(true);
    }//GEN-LAST:event_jTable4MouseClicked

    private void jRadioButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton10ActionPerformed
         if(jRadioButton10.isSelected()){
             emr.viewRealeasedConfinement();
         }else{
             emr.viewConfinement();
         }
    }//GEN-LAST:event_jRadioButton10ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        jButton40.setEnabled(true);
        jButton36.setEnabled(true);
        emr.countConsumedTime();
    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        jButton36.setEnabled(false);
        emr.releaseUndo();
        emr.viewConfinement();
        jLabel2.setText("");
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        new services_frame().setVisible(true);
    }//GEN-LAST:event_jButton41ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel a;
    public static javax.swing.JLabel a1;
    public static javax.swing.JTable allergies_table;
    public static javax.swing.JLabel b;
    public static javax.swing.JLabel c;
    public static javax.swing.JLabel d;
    public static javax.swing.JTable diagnosis;
    public static javax.swing.JSpinner hour;
    public static javax.swing.JTable immunization_table;
    public static javax.swing.JButton jButton1;
    public static javax.swing.JButton jButton10;
    public static javax.swing.JButton jButton11;
    public static javax.swing.JButton jButton12;
    public static javax.swing.JButton jButton13;
    public static javax.swing.JButton jButton14;
    public static javax.swing.JButton jButton15;
    public static javax.swing.JButton jButton16;
    public static javax.swing.JButton jButton17;
    public static javax.swing.JButton jButton18;
    public static javax.swing.JButton jButton19;
    public static javax.swing.JButton jButton2;
    public static javax.swing.JButton jButton20;
    public static javax.swing.JButton jButton21;
    public static javax.swing.JButton jButton22;
    public static javax.swing.JButton jButton23;
    public static javax.swing.JButton jButton24;
    public static javax.swing.JButton jButton25;
    public static javax.swing.JButton jButton26;
    public static javax.swing.JButton jButton27;
    public static javax.swing.JButton jButton28;
    public static javax.swing.JButton jButton29;
    public static javax.swing.JButton jButton3;
    public static javax.swing.JButton jButton30;
    public static javax.swing.JButton jButton31;
    public static javax.swing.JButton jButton32;
    public static javax.swing.JButton jButton33;
    public static javax.swing.JButton jButton34;
    public static javax.swing.JButton jButton35;
    public static javax.swing.JButton jButton36;
    public static javax.swing.JButton jButton37;
    public static javax.swing.JButton jButton38;
    public static javax.swing.JButton jButton39;
    public static javax.swing.JButton jButton4;
    public static javax.swing.JButton jButton40;
    public static javax.swing.JButton jButton41;
    public static javax.swing.JButton jButton42;
    public static javax.swing.JButton jButton43;
    public static javax.swing.JButton jButton44;
    public static javax.swing.JButton jButton5;
    public static javax.swing.JButton jButton6;
    public static javax.swing.JButton jButton7;
    public static javax.swing.JButton jButton8;
    public static javax.swing.JButton jButton9;
    public static com.toedter.calendar.JCalendar jCalendar1;
    public static javax.swing.JComboBox jComboBox1;
    public static javax.swing.JComboBox jComboBox2;
    public static javax.swing.JComboBox jComboBox3;
    public static javax.swing.JComboBox jComboBox4;
    public static javax.swing.JComboBox jComboBox5;
    public static javax.swing.JComboBox jComboBox6;
    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel10;
    public static javax.swing.JLabel jLabel11;
    public static javax.swing.JLabel jLabel12;
    public static javax.swing.JLabel jLabel13;
    public static javax.swing.JLabel jLabel14;
    public static javax.swing.JLabel jLabel15;
    public static javax.swing.JLabel jLabel16;
    public static javax.swing.JLabel jLabel17;
    public static javax.swing.JLabel jLabel18;
    public static javax.swing.JLabel jLabel19;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel jLabel21;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel4;
    public static javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabel6;
    public static javax.swing.JLabel jLabel7;
    public static javax.swing.JLabel jLabel8;
    public static javax.swing.JLabel jLabel9;
    public static javax.swing.JList jList1;
    public static javax.swing.JList jList2;
    public static javax.swing.JList jList3;
    public static javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel10;
    public static javax.swing.JPanel jPanel11;
    public static javax.swing.JPanel jPanel12;
    public static javax.swing.JPanel jPanel13;
    public static javax.swing.JPanel jPanel14;
    public static javax.swing.JPanel jPanel15;
    public static javax.swing.JPanel jPanel16;
    public static javax.swing.JPanel jPanel17;
    public static javax.swing.JPanel jPanel18;
    public static javax.swing.JPanel jPanel19;
    public static javax.swing.JPanel jPanel2;
    public static javax.swing.JPanel jPanel20;
    public static javax.swing.JPanel jPanel21;
    public static javax.swing.JPanel jPanel22;
    public static javax.swing.JPanel jPanel24;
    public static javax.swing.JPanel jPanel3;
    public static javax.swing.JPanel jPanel4;
    public static javax.swing.JPanel jPanel5;
    public static javax.swing.JPanel jPanel6;
    public static javax.swing.JPanel jPanel7;
    public static javax.swing.JPanel jPanel8;
    public static javax.swing.JPanel jPanel9;
    public static javax.swing.JRadioButton jRadioButton1;
    public static javax.swing.JRadioButton jRadioButton10;
    public static javax.swing.JRadioButton jRadioButton2;
    public static javax.swing.JRadioButton jRadioButton3;
    public static javax.swing.JRadioButton jRadioButton4;
    public static javax.swing.JRadioButton jRadioButton5;
    public static javax.swing.JRadioButton jRadioButton6;
    public static javax.swing.JRadioButton jRadioButton7;
    public static javax.swing.JRadioButton jRadioButton8;
    public static javax.swing.JRadioButton jRadioButton9;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JScrollPane jScrollPane10;
    public static javax.swing.JScrollPane jScrollPane11;
    public static javax.swing.JScrollPane jScrollPane12;
    public static javax.swing.JScrollPane jScrollPane13;
    public static javax.swing.JScrollPane jScrollPane14;
    public static javax.swing.JScrollPane jScrollPane15;
    public static javax.swing.JScrollPane jScrollPane16;
    public static javax.swing.JScrollPane jScrollPane17;
    public static javax.swing.JScrollPane jScrollPane18;
    public static javax.swing.JScrollPane jScrollPane19;
    public static javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JScrollPane jScrollPane20;
    public static javax.swing.JScrollPane jScrollPane21;
    public static javax.swing.JScrollPane jScrollPane22;
    public static javax.swing.JScrollPane jScrollPane23;
    public static javax.swing.JScrollPane jScrollPane24;
    public static javax.swing.JScrollPane jScrollPane25;
    public static javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JScrollPane jScrollPane4;
    public static javax.swing.JScrollPane jScrollPane5;
    public static javax.swing.JScrollPane jScrollPane6;
    public static javax.swing.JScrollPane jScrollPane7;
    public static javax.swing.JScrollPane jScrollPane8;
    public static javax.swing.JScrollPane jScrollPane9;
    public static javax.swing.JTabbedPane jTabbedPane1;
    public static javax.swing.JTabbedPane jTabbedPane2;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JTable jTable10;
    public static javax.swing.JTable jTable2;
    public static javax.swing.JTable jTable3;
    public static javax.swing.JTable jTable4;
    public static javax.swing.JTable jTable5;
    public static javax.swing.JTable jTable6;
    public static javax.swing.JTable jTable7;
    public static javax.swing.JTable jTable8;
    public static javax.swing.JTable jTable9;
    public static javax.swing.JTextArea jTextArea1;
    public static javax.swing.JTextArea jTextArea2;
    public static javax.swing.JTextArea jTextArea3;
    public static javax.swing.JTextArea jTextArea4;
    public static javax.swing.JTextField jTextField1;
    public static javax.swing.JTextField jTextField2;
    public static javax.swing.JTextField jTextField3;
    public static javax.swing.JTextField jTextField4;
    public static javax.swing.JTextField jTextField5;
    public static javax.swing.JTextField jTextField7;
    public static javax.swing.JTextField jTextField8;
    public static javax.swing.JToolBar jToolBar1;
    public static javax.swing.JToolBar jToolBar10;
    public static javax.swing.JToolBar jToolBar11;
    public static javax.swing.JToolBar jToolBar12;
    public static javax.swing.JToolBar jToolBar2;
    public static javax.swing.JToolBar jToolBar3;
    public static javax.swing.JToolBar jToolBar4;
    public static javax.swing.JToolBar jToolBar5;
    public static javax.swing.JToolBar jToolBar6;
    public static javax.swing.JToolBar jToolBar7;
    public static javax.swing.JToolBar jToolBar8;
    public static javax.swing.JToolBar jToolBar9;
    public static javax.swing.JSpinner minute;
    public static javax.swing.JTextField owner_address;
    public static javax.swing.JTextField owner_name;
    public static javax.swing.JTextField pet_breed;
    public static javax.swing.JTextField pet_gender;
    public static javax.swing.JTextField pet_name;
    public static javax.swing.JTextField pet_number;
    public static javax.swing.JTextField pet_type;
    public static javax.swing.JLabel picture;
    public static javax.swing.JTable points;
    public static javax.swing.JTable symptoms;
    public static javax.swing.JTable table;
    public static javax.swing.JTextField today_date;
    public static javax.swing.JTable vitals_table;
    // End of variables declaration//GEN-END:variables
String filename=null;
int s=0;
byte[] person_image=null;
}
