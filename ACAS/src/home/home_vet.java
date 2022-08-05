/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import SMSModule.sms_batch_process;
import audit_trailing.audit_trail_frame;
import audit_trailing.user_logs_frame;
import emr.jpanels.Main_Menu_Registration;
import com.toedter.calendar.JCalendar;
import emr.jpanels.SQLClass.dashboard_codes;
import emr.jpanels.checkup_p1;
import inventory.jpanels.Main_Menu_Inventory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import login.connection_db;
import login.login_frm;
import static login.login_frm.setlogid;
import static login.login_frm.typeset;
import temp.NewJFrame;
import workstation.main_workstation;
import static workstation.main_workstation.workstation_tab;
import static inventory.jpanels.Main_Menu_Inventory.*;
import inventory.jpanels.vet_order_confirm_frame;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import javax.swing.Timer;

/**
 *
 * @author IrvGu
 */
public class home_vet extends javax.swing.JFrame {
    
    Connection conn_db = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    int timerun = 0;
    
    String logid;
    
    String day;
    String month;
    String year;
    
    private Timer t;
    private int count = 0;
    
    public static int appointmentcall = 0;
    
    public static int itempurchasecall = 0;
            
    int hourset;
    int minuteset;
    String am_pmset;
    
    String thisdate;
    
    String status;
    
    String dontcreate;
    
    dashboard_codes dash = new dashboard_codes();
    
    sms_batch_process smsbp = new sms_batch_process();
    /**
     * Creates new form home_vet
     */
    public home_vet() {
        initComponents();
        
        createfirst();
        
        conn_db = connection_db.ConnectDB();
    
        logid = setlogid.getText();
        
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        
        String gettype = typeset.getText();
        jMenu3.setText(gettype);
        setacc.setText(gettype);
        
        oderConfirmationCheck();
        
        smsbp.runAutoSMSThread();
        
        totalcount();
        totalcountpet();
        underConsruction();
        dash.showItemOut();
        dash.getappoitnentDate();
        disableNotifications();
        
        
        
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
                    
                    time_today.setText(clock);
                    jMenu9.setText(clock);
			
			}
		         else{
					String clock = hour + ":" + minute + " " + day_night;
                    
                                         time_today.setText(clock);
                                         jMenu9.setText(clock);
			     }
                     
                }
            }
        }.start();
        
        /////clock here//////
        
        
        ////date here//////
        JCalendar dateset = new JCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
        String dob = dateFormat.format(dateset.getDate());
        date_today.setText(dob);
        current_date.setText(dob);
        current_date1.setText(dob);
        jMenu10.setText(dob);
        ////date here/////
        
        showPetWithAppointment();
        
        ////////////////for audit trailing/////////////////
        
        ////set date////
        JCalendar dateset2 = new JCalendar();
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("d MMM yyyy");
        String dob2 = dateFormat2.format(dateset2.getDate());
        thisdate = dob2;
        ////
        
        ////set time////
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
			
			}
		         else{
					String clock = hour + ":" + minute + " " + day_night;
                                        
                                        hourset = hour; minuteset = minute; am_pmset = day_night;
			     }
                     
                }
            }
        }.start();
        ////
        
        //////////////////////////////////////////////////
        t = new Timer(10, new ActionListener() {
                        
                        @Override
                        public void actionPerformed(ActionEvent e) {
                           count++;
 
                           if(count==50){
                               
                               t.stop();
                                try {
                                    //////ADD IF STATEMENT ON HERE///FOR DIFFERENT ACCOUNTS///////
                                    dash.checkExpirationDate();
                                } catch (FileNotFoundException ex) {
                                    Logger.getLogger(home_vet.class.getName()).log(Level.SEVERE, null, ex);
                                }
                               System.out.println("Notification thread stopped");

                           }
                        }
                    });
                    t.start();
        
    }
    
    //******************************************************************************************************************************************//
    
    
    public void underConsruction(){
        jButton4.setEnabled(false);
        jButton7.setEnabled(false);
    }

    
    //******************************************************************************************************************************************//
    
    public void viewrecovered(){
        
        try {
			File file = new File("C:\\Users\\Public\\Documents\\recovered.txt");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}
			fileReader.close();
                        String content = stringBuffer.toString();
                        
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }
    
    public void oderConfirmationCheck(){
        if(jMenu3.getText().equals("VETERINARIAN")){
            jMenuItem2.setEnabled(true);
        }else{
            jMenuItem2.setEnabled(false);
        }
    }
    
    //************************************************************************************************//
    
    public void createfirst(){
        //savestatus
        String content = "on";
        String path = "/home/irv/status_registration.txt";
        try {
            Files.write( Paths.get(path), content.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    //********************************************************************************************//
    
    public void totalcount(){
            
        try{
            String sql="SELECT COUNT(owner_id) as totalCount FROM owner";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String count = rs.getString("totalCount");
                total_count.setText(count);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void totalcountpet(){
        try{
            String sql="SELECT COUNT(pet_id) as totalCount FROM pet";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String count = rs.getString("totalCount");
                total_count1.setText(count);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    //********************************************************************************************//
    
 
     public void showPetWithAppointment(){
         //
         DefaultListModel dimx = new DefaultListModel();
         String string3 = date_today.getText();
         String[] parts3 = string3.split(" ");
         String p1 = parts3[0]; // 004
         String p2 = parts3[1]; // 034556
         String p3 = parts3[2]; // 034556

         day = p1;
         month = p2;
         year = p3;
         
         try{
            String sql="SELECT DISTINCT pet.name FROM `appointment` INNER JOIN pet on appointment.pet_id = pet.pet_id WHERE (appointment.day = '"+day+"' AND appointment.month = '"+month+"') AND (appointment.year = '"+year+"')";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String petName = rs.getString("pet.name");
                dimx.addElement(petName);
                jList1.setModel(dimx);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
     }
     
     public void logout(){
         
         String string = date_today.getText();
         String[] parts = string.split(" ");
         String p1 = parts[0]; // 004
         String p2 = parts[1]; // 034556
         String p3 = parts[2]; // 034556

         day = p1;
         month = p2;
         year = p3;
         
         try{
            String sql2 = "INSERT INTO `user_logs` (`login_name`,`user_id`,`hour`,`minute`,`am_pm`,`day`,`month`,`year`) SELECT 'Logout',`user_id`,'"+hourset+"','"+minuteset+"','"+am_pmset.toUpperCase()+"','"+day+"','"+month.toUpperCase()+"','"+year+"' FROM `user` WHERE `user`.`type` = '"+setacc.getText()+"'";
                ps = conn_db.prepareStatement(sql2);
                ps.execute();
                
                //JOptionPane.showMessageDialog(null, "Succesfully Saved to user_logs");
        
    }catch(Exception e){
                 JOptionPane.showMessageDialog(null, e);
             }
     }
     
     public void disableNotifications(){
         jLabel13.setText("<html>Item quantities are in good<br />condition</html>");
         jLabel19.setVisible(false);
         jLabel20.setVisible(false);
         jLabel21.setVisible(false);
         jLabel22.setVisible(false);
         jLabel24.setVisible(false);
     }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButton2 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton3 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButton4 = new javax.swing.JButton();
        jToolBar2 = new javax.swing.JToolBar();
        inventory = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        jButton6 = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jButton7 = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        jButton8 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        current_date = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        total_count = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        current_date1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        total_count1 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        date_today = new javax.swing.JLabel();
        time_today = new javax.swing.JLabel();
        setacc = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu11 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem4.setText("jMenuItem4");

        jMenuItem5.setText("jMenuItem5");

        jMenu4.setText("File");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("Edit");
        jMenuBar2.add(jMenu5);

        jMenuItem6.setText("jMenuItem6");

        jMenu8.setText("jMenu8");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Dashboard");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToolBar1.setRollover(true);
        jToolBar1.setToolTipText("Electronic Health Records");

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Users.png"))); // NOI18N
        jButton1.setText("Client Information Entry");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);
        jToolBar1.add(jSeparator1);

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/set_program_access_and_defaults.png"))); // NOI18N
        jButton2.setText("Pet Chart");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);
        jToolBar1.add(jSeparator2);

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Add Appointment.png"))); // NOI18N
        jButton3.setText("Appointments");
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton3);
        jToolBar1.add(jSeparator3);

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Export To Document.png"))); // NOI18N
        jButton4.setText("Produce Reports");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jToolBar1.add(jButton4);

        jPanel1.add(jToolBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 70));

        jToolBar2.setRollover(true);
        jToolBar2.setToolTipText("Inventory");

        inventory.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        inventory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Write Document.png"))); // NOI18N
        inventory.setText("Inventory");
        inventory.setFocusable(false);
        inventory.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        inventory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventoryActionPerformed(evt);
            }
        });
        jToolBar2.add(inventory);
        jToolBar2.add(jSeparator5);

        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/basket_add.png"))); // NOI18N
        jButton6.setText("Client Item Purchase");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton6);
        jToolBar2.add(jSeparator4);

        jButton7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Clear Green Button.png"))); // NOI18N
        jButton7.setText("Order Item Listings");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jToolBar2.add(jButton7);
        jToolBar2.add(jSeparator6);

        jButton8.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Menu Item.png"))); // NOI18N
        jButton8.setText("Stocks Information");
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton8);

        jPanel1.add(jToolBar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, 750, 70));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Client Information Overview", 0, 0, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(48, 48, 48))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(46, 80, 80));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Records of Owner Information");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 210, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(46, 80, 80));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("As of");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 50, -1));

        current_date.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        current_date.setForeground(new java.awt.Color(46, 80, 80));
        current_date.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        current_date.setText("1 Jun 2015");
        jPanel3.add(current_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 110, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(46, 80, 80));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("TOTAL PROFILE(S) SAVED:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 160, -1));

        total_count.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        total_count.setForeground(new java.awt.Color(90, 133, 94));
        total_count.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        total_count.setText("100");
        total_count.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3.add(total_count, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 70, 30));

        jLabel12.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(46, 80, 80));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Records of Pet Information");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 230, 20));

        current_date1.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        current_date1.setForeground(new java.awt.Color(46, 80, 80));
        current_date1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        current_date1.setText("1 Jun 2015");
        jPanel3.add(current_date1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 110, 20));

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(46, 80, 80));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("As of");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 50, 20));

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(46, 80, 80));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("TOTAL PET(S) SAVED:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 170, -1));

        total_count1.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        total_count1.setForeground(new java.awt.Color(90, 133, 94));
        total_count1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        total_count1.setText("100");
        total_count1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3.add(total_count1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 70, 40));
        jPanel3.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 190, 10));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 320, 220, 250));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sold Out Items", 0, 0, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(46, 80, 80))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jPanel5.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 330, 110));

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(46, 80, 80));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("List of out item(s) sold out");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 250, 20));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 320, 350, 160));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Low Item Qty", 0, 0, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(46, 80, 80))); // NOI18N
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTable2);

        jPanel6.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 330, 110));

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(46, 80, 80));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Item(s) low quantity count");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 250, 20));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 140, 350, 160));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Appointment(s)", 0, 0, new java.awt.Font("Segoe UI", 0, 14), new java.awt.Color(72, 72, 72))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jList1.setBorder(javax.swing.BorderFactory.createBevelBorder(0));
        jList1.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jList1.setForeground(new java.awt.Color(51, 153, 0));
        jScrollPane1.setViewportView(jList1);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 200, 90));

        jLabel16.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(46, 80, 80));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("List of Pets with Appoinments");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 190, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(111, 86, 74));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("List of Pets with Appoinments");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 190, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(46, 80, 80));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("today");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 180, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 140, 220, 160));

        date_today.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        date_today.setForeground(new java.awt.Color(75, 45, 45));
        date_today.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        date_today.setText("21 Jan 2015");
        jPanel1.add(date_today, new org.netbeans.lib.awtextra.AbsoluteConstraints(1291, 50, 0, 30));

        time_today.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        time_today.setForeground(new java.awt.Color(75, 45, 45));
        time_today.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        time_today.setText("12:00 PM");
        jPanel1.add(time_today, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 50, 0, 30));

        setacc.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        setacc.setForeground(new java.awt.Color(254, 254, 254));
        setacc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        setacc.setText("Assistant Veterinarian");
        setacc.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel1.add(setacc, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 0, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(254, 254, 254));
        jLabel2.setText("Inventory Current Status");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 90, 400, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 1, 30)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(254, 254, 254));
        jLabel11.setText("Notifications & Alerts");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 350, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI Light", 1, 30)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(254, 254, 254));
        jLabel18.setText("Client Current Status");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 330, -1));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg_fixed.png"))); // NOI18N
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 50, -1, -1));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setBackground(new java.awt.Color(199, 199, 199));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(46, 46, 46));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_interface/notifications(1).png"))); // NOI18N
        jLabel13.setText("Low Item Qty. Detected");
        jLabel13.setToolTipText("Click to reorder the item(s)");
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel13.setOpaque(true);
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel13MouseExited(evt);
            }
        });
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 210, 50));

        jLabel19.setBackground(new java.awt.Color(199, 199, 199));
        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(46, 46, 46));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_interface/notifications(1).png"))); // NOI18N
        jLabel19.setText("No Notification");
        jLabel19.setToolTipText("Click to reorder the item(s)");
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel19.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel19.setOpaque(true);
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel19MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel19MouseExited(evt);
            }
        });
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 210, 50));

        jLabel20.setBackground(new java.awt.Color(166, 166, 166));
        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(46, 46, 46));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_interface/error.png"))); // NOI18N
        jLabel20.setText("No Notification");
        jLabel20.setToolTipText("Click to reorder the item(s)");
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel20.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel20.setOpaque(true);
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel20MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel20MouseExited(evt);
            }
        });
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 210, 110));

        jLabel21.setBackground(new java.awt.Color(199, 199, 199));
        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(46, 46, 46));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_interface/error.png"))); // NOI18N
        jLabel21.setText("No Notification");
        jLabel21.setToolTipText("Click to reorder the item(s)");
        jLabel21.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel21.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel21.setOpaque(true);
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel21MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel21MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel21MouseExited(evt);
            }
        });
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 210, 50));

        jLabel22.setBackground(new java.awt.Color(166, 166, 166));
        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(46, 46, 46));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_interface/notifications(1).png"))); // NOI18N
        jLabel22.setText("No Notification");
        jLabel22.setToolTipText("Click to reorder the item(s)");
        jLabel22.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel22.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel22.setOpaque(true);
        jLabel22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel22MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel22MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel22MouseExited(evt);
            }
        });
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 210, 50));

        jLabel24.setBackground(new java.awt.Color(166, 166, 166));
        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(46, 46, 46));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_interface/notifications(1).png"))); // NOI18N
        jLabel24.setText("No Notification");
        jLabel24.setToolTipText("Click to reorder the item(s)");
        jLabel24.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel24.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabel24.setOpaque(true);
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel24MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel24MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel24MouseExited(evt);
            }
        });
        jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 210, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 230, 430));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg_fixed.png"))); // NOI18N
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 60, -1, 650));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1400, 710));

        jMenu3.setForeground(new java.awt.Color(254, 254, 254));
        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_fixed.png"))); // NOI18N
        jMenu3.setText("File");
        jMenu3.add(jSeparator9);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setText("Logout");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenu11.setText("Alert Sound Options");

        jMenuItem3.setText("Sound On");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem3);

        jMenuItem15.setText("Sound Off");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu11.add(jMenuItem15);

        jMenu3.add(jMenu11);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        jMenuItem8.setText("Exit");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuBar1.add(jMenu3);

        jMenu1.setText("Account");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setForeground(new java.awt.Color(254, 254, 254));
        jMenuItem2.setText("Confirmations");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenu7.setForeground(new java.awt.Color(254, 254, 254));
        jMenu7.setText("Account Options");

        jMenuItem12.setForeground(new java.awt.Color(254, 254, 254));
        jMenuItem12.setText("Update Account");
        jMenu7.add(jMenuItem12);

        jMenu1.add(jMenu7);
        jMenu1.add(jSeparator10);

        jMenuBar1.add(jMenu1);

        jMenu2.setForeground(new java.awt.Color(254, 254, 254));
        jMenu2.setText("View");

        jMenuItem9.setForeground(new java.awt.Color(254, 254, 254));
        jMenuItem9.setText("Audit Trail");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuItem10.setForeground(new java.awt.Color(254, 254, 254));
        jMenuItem10.setText("User Logs History");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);

        jMenuItem11.setForeground(new java.awt.Color(254, 254, 254));
        jMenuItem11.setText("Stocks");
        jMenu2.add(jMenuItem11);

        jMenuBar1.add(jMenu2);

        jMenu9.setText("jMenu9");
        jMenu9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu9ActionPerformed(evt);
            }
        });
        jMenu9.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jMenu9FocusGained(evt);
            }
        });
        jMenuBar1.add(jMenu9);

        jMenu10.setText("jMenu10");
        jMenuBar1.add(jMenu10);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Are you sure you want to log out?",
            "Logging out", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            logout();
            this.dispose();
            new login_frm().setVisible(true);
            ImageIcon icon = new ImageIcon("/home/irv/Documents/ACAS BUILD 5/ACAS/src/icons/Clear Green Button.png");
            JOptionPane.showMessageDialog(rootPane, "Logged Out", "Successfull", JOptionPane.INFORMATION_MESSAGE, icon);
        }
        else {

            System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
            return;
        }
    }//GEN-LAST:event_formWindowClosing

    private void inventoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventoryActionPerformed
       //Main_workstaion f = new Main_workstaion(); f.setVisible(true);
       main_workstation f = new main_workstation(); f.setVisible(true);
       this.dispose();
       Main_Menu_Inventory cc = new Main_Menu_Inventory();
       workstation_tab.addTab("Inventory", cc);
    }//GEN-LAST:event_inventoryActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       main_workstation f = new main_workstation(); f.setVisible(true);
       this.dispose();
       Main_Menu_Registration cc = new Main_Menu_Registration();
       workstation_tab.addTab("Registration", cc);
       
       int xq = workstation_tab.getTabCount();
       workstation_tab.setSelectedIndex(xq-1); 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new checkup_p1().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        new audit_trail_frame().setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        new user_logs_frame().setVisible(true);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
       if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Are you sure you want to log out?",
            "Logging out", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            logout();
            dispose();
            new login_frm().setVisible(true);
            ImageIcon icon = new ImageIcon("/home/irv/Documents/ACAS BUILD 5/ACAS/src/icons/Clear Green Button.png");
            JOptionPane.showMessageDialog(rootPane, "Logged out", "Successfull", JOptionPane.INFORMATION_MESSAGE, icon);
        }
        else {

            System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
            return;
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Are you sure you want to close the system?",
            "Closing the system", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            logout();
            System.exit(WIDTH);
            
        }
        else {

            System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
            return;
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        itempurchasecall =1;
        new checkup_p1().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        appointmentcall=1;
        new checkup_p1().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
       main_workstation f = new main_workstation(); f.setVisible(true);
       this.dispose();
       Main_Menu_Inventory cc = new Main_Menu_Inventory();
       workstation_tab.addTab("Inventory", cc);
       jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jMenu9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu9ActionPerformed
        
    }//GEN-LAST:event_jMenu9ActionPerformed

    private void jMenu9FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jMenu9FocusGained
       
    }//GEN-LAST:event_jMenu9FocusGained

    private void jLabel13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseEntered
        jLabel13.setBackground(new Color(163,160,176));
    }//GEN-LAST:event_jLabel13MouseEntered

    private void jLabel13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseExited
        jLabel13.setBackground(new Color(199,199,199));
    }//GEN-LAST:event_jLabel13MouseExited

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel19MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseEntered
        jLabel19.setBackground(new Color(163,160,176));
    }//GEN-LAST:event_jLabel19MouseEntered

    private void jLabel19MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseExited
        jLabel19.setBackground(new Color(199,199,199));
    }//GEN-LAST:event_jLabel19MouseExited

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        dispose();
        main_workstation f = new main_workstation(); f.setVisible(true);
        Main_Menu_Inventory cc = new Main_Menu_Inventory();
        workstation_tab.addTab("Inventory", cc);
        jTabbedPane1.setSelectedIndex(0);
        jTabbedPane2.setSelectedIndex(2);
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jLabel20MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseEntered
        jLabel20.setBackground(new Color(163,160,176));
    }//GEN-LAST:event_jLabel20MouseEntered

    private void jLabel20MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseExited
        jLabel20.setBackground(new Color(166,166,166));
    }//GEN-LAST:event_jLabel20MouseExited

    private void jLabel21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseClicked
        dispose();
        main_workstation f = new main_workstation(); f.setVisible(true);
        Main_Menu_Inventory cc = new Main_Menu_Inventory();
        workstation_tab.addTab("Inventory", cc);
        jTabbedPane1.setSelectedIndex(0);
        jTabbedPane2.setSelectedIndex(2);
    }//GEN-LAST:event_jLabel21MouseClicked

    private void jLabel21MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseEntered
        jLabel21.setBackground(new Color(163,160,176));
    }//GEN-LAST:event_jLabel21MouseEntered

    private void jLabel21MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel21MouseExited
        jLabel21.setBackground(new Color(199,199,199));
    }//GEN-LAST:event_jLabel21MouseExited

    private void jLabel22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel22MouseClicked

    private void jLabel22MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseEntered
        jLabel22.setBackground(new Color(163,160,176));
    }//GEN-LAST:event_jLabel22MouseEntered

    private void jLabel22MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel22MouseExited
        jLabel22.setBackground(new Color(166,166,166));
    }//GEN-LAST:event_jLabel22MouseExited

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel24MouseClicked

    private void jLabel24MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseEntered
        jLabel24.setBackground(new Color(163,160,176));
    }//GEN-LAST:event_jLabel24MouseEntered

    private void jLabel24MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseExited
        jLabel24.setBackground(new Color(166,166,166));
    }//GEN-LAST:event_jLabel24MouseExited

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        FileOutputStream writer = null;
        try {
            writer = new FileOutputStream("/home/irv/status_registration.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(home_vet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            writer.write((new String()).getBytes());
        } catch (IOException ex) {
            Logger.getLogger(home_vet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(home_vet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /////////////////////////////////////////////////////////////////////////////
        
        String content = "on";
        String path = "/home/irv/status_registration.txt";
        try {
            Files.write( Paths.get(path), content.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Alert Sound has been turned on","Success",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        FileOutputStream writer = null;
        try {
            writer = new FileOutputStream("/home/irv/status_registration.txt");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(home_vet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            writer.write((new String()).getBytes());
        } catch (IOException ex) {
            Logger.getLogger(home_vet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(home_vet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /////////////////////////////////////////////////////////////////////////////
        
        String content = "off";
        String path = "/home/irv/status_registration.txt";
        try {
            Files.write( Paths.get(path), content.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Alert Sound has been turned off","Success",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        dispose();
        main_workstation f = new main_workstation(); f.setVisible(true);
        Main_Menu_Inventory cc = new Main_Menu_Inventory();
        workstation_tab.addTab("Inventory", cc);
        jTabbedPane1.setSelectedIndex(0);
        jTabbedPane2.setSelectedIndex(2);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new vet_order_confirm_frame().setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
            java.util.logging.Logger.getLogger(home_vet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(home_vet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(home_vet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(home_vet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
           
            public void run() {
                new home_vet().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel current_date;
    public static javax.swing.JLabel current_date1;
    public static javax.swing.JLabel date_today;
    public static javax.swing.JButton inventory;
    public static javax.swing.JButton jButton1;
    public static javax.swing.JButton jButton2;
    public static javax.swing.JButton jButton3;
    public static javax.swing.JButton jButton4;
    public static javax.swing.JButton jButton6;
    public static javax.swing.JButton jButton7;
    public static javax.swing.JButton jButton8;
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
    public static javax.swing.JLabel jLabel20;
    public static javax.swing.JLabel jLabel21;
    public static javax.swing.JLabel jLabel22;
    public static javax.swing.JLabel jLabel24;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel4;
    public static javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabel6;
    public static javax.swing.JLabel jLabel7;
    public static javax.swing.JLabel jLabel8;
    public static javax.swing.JLabel jLabel9;
    public static javax.swing.JList jList1;
    public static javax.swing.JMenu jMenu1;
    public static javax.swing.JMenu jMenu10;
    public static javax.swing.JMenu jMenu11;
    public static javax.swing.JMenu jMenu2;
    public static javax.swing.JMenu jMenu3;
    public static javax.swing.JMenu jMenu4;
    public static javax.swing.JMenu jMenu5;
    public static javax.swing.JMenu jMenu7;
    public static javax.swing.JMenu jMenu8;
    public static javax.swing.JMenu jMenu9;
    public static javax.swing.JMenuBar jMenuBar1;
    public static javax.swing.JMenuBar jMenuBar2;
    public static javax.swing.JMenuItem jMenuItem1;
    public static javax.swing.JMenuItem jMenuItem10;
    public static javax.swing.JMenuItem jMenuItem11;
    public static javax.swing.JMenuItem jMenuItem12;
    public static javax.swing.JMenuItem jMenuItem15;
    public static javax.swing.JMenuItem jMenuItem2;
    public static javax.swing.JMenuItem jMenuItem3;
    public static javax.swing.JMenuItem jMenuItem4;
    public static javax.swing.JMenuItem jMenuItem5;
    public static javax.swing.JMenuItem jMenuItem6;
    public static javax.swing.JMenuItem jMenuItem7;
    public static javax.swing.JMenuItem jMenuItem8;
    public static javax.swing.JMenuItem jMenuItem9;
    public static javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel2;
    public static javax.swing.JPanel jPanel3;
    public static javax.swing.JPanel jPanel4;
    public static javax.swing.JPanel jPanel5;
    public static javax.swing.JPanel jPanel6;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JToolBar.Separator jSeparator1;
    public static javax.swing.JPopupMenu.Separator jSeparator10;
    public static javax.swing.JToolBar.Separator jSeparator2;
    public static javax.swing.JToolBar.Separator jSeparator3;
    public static javax.swing.JToolBar.Separator jSeparator4;
    public static javax.swing.JToolBar.Separator jSeparator5;
    public static javax.swing.JToolBar.Separator jSeparator6;
    public static javax.swing.JSeparator jSeparator8;
    public static javax.swing.JPopupMenu.Separator jSeparator9;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JTable jTable2;
    public static javax.swing.JToolBar jToolBar1;
    public static javax.swing.JToolBar jToolBar2;
    public static javax.swing.JLabel setacc;
    public static javax.swing.JLabel time_today;
    public static javax.swing.JLabel total_count;
    public static javax.swing.JLabel total_count1;
    // End of variables declaration//GEN-END:variables
}
