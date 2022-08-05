/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emr.jpanels;

import com.toedter.calendar.JCalendar;
import static emr.jpanels.new_pet.jTable1;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import login.connection_db;
import javax.swing.DefaultListModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Irv
 */
public class prescription extends javax.swing.JFrame {
    Connection conn_db = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    int timerun = 0;
    
    int hourset;
    int minuteset;
    String minutesets;
    String am_pmset;
    
    String day;
    String month;
    String year;
    
    String IDp1;
    int increment ;
    
    String rx_p2 = "";
    
    String idReference;
    /**
     * Creates new form vaccine_checkup
     */
    public prescription() {
        initComponents();
        conn_db = connection_db.ConnectDB();
        clock();
        
        //for centered frame////
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);   
        //
        
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
        
        jList1.setEnabled(false);
        presc_jTextField1.setEnabled(false);
        jButton7.setEnabled(false);
        jButton5.setEnabled(false);
        
        
        ////
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
                    
                    time_today.setText(clock);
                    jLabel8.setText(IDp1+Integer.toString(minute)+Integer.toString(sec));
			
			}
		         else{
					String clock = hour + ":" + minute + " " + day_night;
                                        hourset = hour; minuteset = minute; am_pmset = day_night;
                                        if(minuteset<=9){
                                            minutesets = String.valueOf("0"+minute);  
                                          }else{
                                              minutesets = String.valueOf(minute); 
                                          }
                                        time_today.setText(clock);
                                        jLabel8.setText(IDp1+Integer.toString(minute)+Integer.toString(sec));
			     }
                     
                }
            }
        }.start();
        
        /////clock here//////
    }
    
    public void savePrescription(){
        try{
            String sql3 = "insert into prescription (prescription_number, pet_id, medicine, mass_volume, signa, refills, quantity, day, month, year)values(?,?,?,?,?,?,?,?,?,?)";
            ps=conn_db.prepareStatement(sql3);
           
            ps.setString(1, jLabel8.getText());
            ps.setString(2, pet_id_presc.getText().toUpperCase());
            ps.setString(3, presc_jTextField1.getText().toUpperCase());
            ps.setString(4, jTextField3.getText()+" "+rx_p2.toUpperCase());
            ps.setString(5, jTextArea1.getText().toUpperCase());
            ps.setString(6, jLabel3.getText());
            ps.setString(7, jLabel4.getText());
            ps.setString(8, day);
            ps.setString(9, month);
            ps.setString(10, year);
            ps.execute();
            
            JOptionPane.showMessageDialog(null, "Prescription #"+jLabel8.getText()+" has been successfully saved");
            
            DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
            model.addRow(new Object[]{jLabel8.getText(), presc_jTextField1.getText().toUpperCase(), jTextField3.getText()+" "+rx_p2.toUpperCase(), jTextArea1.getText().toUpperCase()});

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e) ;
        }
       
    }
    
    public void autoShow(){
        DefaultTableModel dd=(DefaultTableModel) jTable1.getModel();
        int rows = dd.getRowCount(); 
        ////clearJlistCode
        for(int i = rows - 1; i >=0; i--)
        {
           dd.removeRow(i); 
        }
        ////clearJlistCode
        try{
            String sql3 = "select * from prescription";
            ps = conn_db.prepareStatement(sql3);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String number = rs.getString("prescription_number");
                String medicine = rs.getString("medicine");
                String dosage = rs.getString("dosage");
                String intake = rs.getString("intake");
                
                DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
                model.addRow(new Object[]{number, medicine, dosage, intake});
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e) ;
        }
    }

    
    public void clearAllFields(){
//        jTextField3_presc.setText("");
//        jTextField2.setText("");
//        jTextArea3.setText("");
    }
    
    public void updatePrscription(){
        try{
            String sql="UPDATE prescription SET medicine=?, dosage=?, intake=? where prescription_number =?";         
            ps=conn_db.prepareStatement(sql);

            JOptionPane.showMessageDialog(null, "Prescription #"+idReference+" has been sucessfully updated!");
            
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
    }
    
    public void viewMed(){
        DefaultListModel dimx = new DefaultListModel();
        try{
            String sql3 = "SELECT item.name FROM `classification1` INNER JOIN item on item.classification_id = classification1.classification_id WHERE classification1.used_for = 'CHECKUP'";
            ps = conn_db.prepareStatement(sql3);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String itemName = rs.getString("item.name");
                dimx.addElement(itemName);
                jList1.setModel(dimx);
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e) ;
        }
    }
    
    public void printACopy(){
        try{
                    //load report location
                    FileInputStream fis = new FileInputStream("C:\\Users\\Public\\Documents\\Reports_acas\\prescription_copy.jrxml");
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(fis);

                    //set parameters
                    Map map = new HashMap();
                    map.put("pet_id", pet_id_presc.getText());
                    //compile report
                    JasperReport jasperReport = (JasperReport) JasperCompileManager.compileReport(bufferedInputStream);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, conn_db);


                    //view report to UI
                        JasperViewer.viewReport(jasperPrint, false); 
            
            
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

        jPanel1 = new javax.swing.JPanel();
        pet_id_presc = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        time_today = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pet_name_presc = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        presc_jTextField1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pet_id_presc.setText("2");
        jPanel1.add(pet_id_presc, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 10, 30, 20));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Prescription Overview");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, -1, -1));

        time_today.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        time_today.setForeground(new java.awt.Color(255, 255, 255));
        time_today.setText("12:00 PM");
        jPanel1.add(time_today, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("0");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 460, 90, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Prescription");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        pet_name_presc.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        pet_name_presc.setForeground(new java.awt.Color(255, 255, 255));
        pet_name_presc.setText("PET NAME");
        jPanel1.add(pet_name_presc, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 120, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Pet:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("RX#");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("jLabel8");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, 70, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "RX#", "Drug", "Dose", "Signa"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, 530, 380));

        jRadioButton1.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setText("Prescribe drugs from database");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 210, -1));

        jRadioButton2.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setText("Type a drug");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, 210, -1));

        jList1.setBorder(javax.swing.BorderFactory.createTitledBorder("List of drugs"));
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 200, 220));

        presc_jTextField1.setToolTipText("Hit \"Enter\" to add");
        jPanel1.add(presc_jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 260, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Choose Drug");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 200, -1));

        jRadioButton4.setText("ml");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 270, 50, 30));

        jRadioButton3.setText("mg");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 270, 50, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Rx:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 50, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Mass/Volume:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, 100, -1));

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jScrollPane4.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 330, 260, 100));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("SIG:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, 100, -1));

        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, 140, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Quantity:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 100, -1));

        jButton3.setText("+");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 460, 50, -1));

        jButton4.setText("-");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 460, 50, -1));

        jButton6.setText("Save");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 460, 100, -1));

        jButton7.setText("Update");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 460, 100, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Refills:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 440, 100, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("0");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 460, 60, -1));

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 460, 50, -1));

        jButton2.setText("-");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 460, 50, -1));

        jButton5.setText("Print a copy");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 460, -1, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg.jpg"))); // NOI18N
        jLabel9.setText("jLabel9");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1110, 550));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Are you sure you want to close?",
            "Closing Vaccine Checkup", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            this.dispose();
            
        }
        else {

            System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
            return;
        }
    }//GEN-LAST:event_formWindowClosing

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int x = Integer.parseInt(jLabel3.getText());
        x--;
        if(x<0){
            jLabel3.setText("0");
        }else{
            jLabel3.setText(Integer.toString(x));
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int x = Integer.parseInt(jLabel3.getText());
        x++;
        jLabel3.setText(Integer.toString(x));
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)){
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        if(jRadioButton3.isSelected()){
            jRadioButton4.setSelected(false);
            rx_p2 = "mg";
        }
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        if(jRadioButton4.isSelected()){
            jRadioButton3.setSelected(false);
            rx_p2 = "ml";
        }
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        presc_jTextField1.setEnabled(true);
        presc_jTextField1.setText(jList1.getSelectedValue().toString());
    }//GEN-LAST:event_jList1MouseClicked

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        if(jRadioButton2.isSelected()){
            jRadioButton1.setSelected(false);
            jList1.setEnabled(false);
            presc_jTextField1.setEnabled(true);
        }else{
        }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        if(jRadioButton1.isSelected()){
            jRadioButton2.setSelected(false);
            jList1.setEnabled(true);
            viewMed();
        }else{
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
        if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Are you sure you want to update?",
            "Updating prescription #"+model.getValueAt(jTable1.getSelectedRow(), 0).toString(), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
        jButton2.setEnabled(true);
        jButton1.setEnabled(false);
        }
        else {
            return;
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int x = Integer.parseInt(jLabel4.getText());
        x++;
        jLabel4.setText(Integer.toString(x));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int x = Integer.parseInt(jLabel4.getText());
        x--;
        if(x<0){
            jLabel4.setText("0");
        }else{
            jLabel4.setText(Integer.toString(x));
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        savePrescription();
        jButton5.setEnabled(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        printACopy();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

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
            java.util.logging.Logger.getLogger(prescription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(prescription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(prescription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(prescription.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new prescription().setVisible(true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField3;
    public static javax.swing.JLabel pet_id_presc;
    public static javax.swing.JLabel pet_name_presc;
    public static javax.swing.JTextField presc_jTextField1;
    private javax.swing.JLabel time_today;
    // End of variables declaration//GEN-END:variables
}
