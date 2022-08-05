/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emr.jpanels;

import audit_trailing.audit_trail;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import login.connection_db;
import net.proteanit.sql.DbUtils;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import static emr.jpanels.pet_chart.*;
import javax.swing.DefaultListModel;
import other.features.jpanels.customMessage_Screen;
/**
 *
 * @author Irvin Guinto
 */
public class immunization_history extends javax.swing.JFrame {
    Connection conn_db = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    String day = "";
    String month = "";
    String year = "";
    
    String setType;
    
    String vaccine = "";
    String deworm = "";
    
    String radiotrigger = "no";
    
    String agep2 = "";
    String agep3="";
    
    String immunoID;
    /**
     * Creates new form medical_history
     */
    public immunization_history() {
        initComponents();
        conn_db = connection_db.ConnectDB();
        
        autoVaccineShowHistory();
        
        autoDewormingShowHistory();
        
        viewVaccineTypes();

        //for centered frame////
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);   
        //

        jTextField2.setVisible(false);
        
        jTextField1.setEnabled(false);
        
        jButton5.setEnabled(false);
    }
    
    
   public void autoVaccineShowHistory(){
        try{
            String sql = "SELECT vaccine AS `Vaccine`, deworming AS `Deworming`, age AS `Age`, CONCAT_WS(' ', day, month, year) AS `Immunization Date` FROM `immunization_history` where pet_id ='"+petid+"' ORDER BY year DESC";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable3.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
   }
   
   public void autoDewormingShowHistory(){
        try{
            String sql = "SELECT deworming AS `Deworming`, age AS `Age`, CONCAT_WS(' ', day, month, year) AS `Immunization Date` FROM `immunization_history` where pet_id ='"+petid+"' ORDER BY year DESC";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
   }
    
    public void saveImmunizationHistory(){
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("d MMM yyyy");
        String dob2 = dateFormat2.format(jDateChooser3.getDate());
        String thisdate = dob2;
        
        String string = thisdate;
        String[] parts = string.split(" ");
        String p1 = parts[0]; // 004
        String p2 = parts[1]; // 034556
        String p3 = parts[2]; // 034556

        day = p1;
        month = p2.toUpperCase();
        year = p3;
        
        try{
            String sql = "Insert into immunization_history (`pet_id`,`vaccine`,`age`,`day`,`month`,`year`) values (?,?,?,?,?,?)";
            ps=conn_db.prepareStatement(sql);
            ps.setString(1, petid);
            ps.setString(2, vaccine);
            ps.setString(3, jTextField1.getText() + " " + agep2);
            ps.setString(4, day);
            ps.setString(5, month);
            ps.setString(6, year);
            ps.execute();
            
            JOptionPane.showMessageDialog(null, "Immunization history has been successfully stored");
            
            reset();
            
            autoVaccineShowHistory();
            
            audit_trail au = new audit_trail();
            au.action_type = "Saved - immunization history information by "+ pet_name.getText();
            au.module_name = "Pet Chart";
            au.saveAuditTRail();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void reset(){
        jTextField2.setVisible(false);
        jRadioButton4.setSelected(false);
        jRadioButton5.setSelected(false);
        vaccine = "";
        jDateChooser3.setDate(null);
        jRadioButton1.setSelected(false);
        jRadioButton2.setSelected(false);
        jRadioButton3.setSelected(false);
        jTextField1.setText("");
        autoVaccineShowHistory();
    }
    
    public void dewormreset(){
        deworm = "";
        jDateChooser4.setDate(null);
        jRadioButton6.setSelected(false);
        jRadioButton7.setSelected(false);
        jRadioButton8.setSelected(false);
        jTextField3.setText("");
    }
    
    public void setUpdate(){
        String getVacc = jTable3.getValueAt(jTable3.getSelectedRow(), 0).toString();
        jList1.setSelectedValue(getVacc, rootPaneCheckingEnabled);
        vaccine = getVacc;
        
        String getDew = jTable3.getValueAt(jTable3.getSelectedRow(), 1).toString();
        if(getDew.equals("")){
            jRadioButton5.setSelected(true);
            jRadioButton4.setSelected(false);
            jTextField2.setText("");
            jTextField2.setVisible(false);
        }else{
            jTextField2.setVisible(true);
            jTextField2.setText(getDew);
            jRadioButton5.setSelected(false);
            jRadioButton4.setSelected(true);
        }
        
        String getAge = jTable3.getValueAt(jTable3.getSelectedRow(), 2).toString();
        String string3 = getAge;
        String[] parts3 = string3.split(" ");
        String p1 = parts3[0]; // 004
        String p2 = parts3[1]; // 0345566

        jTextField1.setText(p1);
        jTextField1.setEnabled(true);
        
        if(jRadioButton1.getText().toUpperCase().equals(p2)){
            jRadioButton1.setSelected(true);
            jRadioButton2.setSelected(false);
            jRadioButton3.setSelected(false);
            agep2 = p2;
        }else{
            if(jRadioButton2.getText().toUpperCase().equals(p2)){
                jRadioButton2.setSelected(true);
                jRadioButton1.setSelected(false);
                jRadioButton3.setSelected(false);
                agep2 = p2;
            }else{
                jRadioButton3.setSelected(true);
                jRadioButton1.setSelected(false);
                jRadioButton2.setSelected(false);
                agep2 = p2;
            }
        }
        
        String getDate = jTable3.getValueAt(jTable3.getSelectedRow(), 3).toString();
         
        java.util.Date date = null;
        try {
            date = new SimpleDateFormat("d MMM yyyy").parse(getDate);
        } catch (ParseException ex) {
            Logger.getLogger(immunization_history.class.getName()).log(Level.SEVERE, null, ex);
        }

        jDateChooser3.setDate(date);
        
        getImmunoID();
        
        jButton4.setEnabled(false);
        jButton5.setEnabled(true);
    }
    
    public void UpdateImmunization(){
        
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("d MMM yyyy");
        String dob2 = dateFormat2.format(jDateChooser3.getDate());
        String thisdate = dob2;
        
        String string = thisdate;
        String[] parts = string.split(" ");
        String p1 = parts[0]; // 004
        String p2 = parts[1]; // 034556
        String p3 = parts[2]; // 034556

        day = p1;
        month = p2.toUpperCase();
        year = p3;
        
        try{
            String sql = "UPDATE immunization_history SET vaccine = UPPER(?), deworming = UPPER(?), age = UPPER(?), day = UPPER(?), month = UPPER(?), year = UPPER(?) where id ='"+immunoID+"'";
            ps=conn_db.prepareStatement(sql);
            
            ps.setString(1, vaccine);
            ps.setString(2, jTextField2.getText());
            ps.setString(3, jTextField1.getText()+ " "+agep2);
            ps.setString(4, day);
            ps.setString(5, month);
            ps.setString(6, year);
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Immunization history has been successfully updated");
            
            autoVaccineShowHistory();
            
            audit_trail au = new audit_trail();
            au.action_type = "Updated - immunization history information by "+ pet_name.getText();
            au.module_name = "Pet Chart";
            au.saveAuditTRail();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }

    }
    
    public void getImmunoID(){
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("d MMM yyyy");
        String dob2 = dateFormat2.format(jDateChooser3.getDate());
        String thisdate = dob2;
        
        String string = thisdate;
        String[] parts = string.split(" ");
        String p1 = parts[0]; // 004
        String p2 = parts[1]; // 034556
        String p3 = parts[2]; // 034556

        day = p1;
        month = p2.toUpperCase();
        year = p3;
        
        try{
            String putincb = "select immunization_history.id from immunization_history WHERE ((vaccine = '"+vaccine+"' AND deworming = '"+jTextField2.getText()+"') AND (age = '"+jTextField1.getText()+" "+agep2+"' AND day = '"+day+"')) AND ((month = '"+month+"') AND (year = '"+year+"'))";
            ps = conn_db.prepareStatement(putincb);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getid = rs.getString("immunization_history.id");
                immunoID = getid;
            }
            
        }catch(Exception e){     
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void autoShowHistoryImmunization(){
        try{
            String sql = "SELECT vaccine AS `Vaccine`, deworming AS `Deworming`, age AS `Age`, CONCAT_WS(' ', day, month, year) AS `Immunization Date` FROM `immunization_history` where pet_id ='"+petid+"' ORDER BY year DESC";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            immunization_table.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
   }
    
    public void viewVaccineTypes(){
        DefaultListModel dimx = new DefaultListModel();
        DefaultListModel dimx2 = new DefaultListModel();
         try{
             String sql = "SELECT * from vaccine_types";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             
             while(rs.next()){
                 String getvac = rs.getString("vaccine");
                 String getdew = rs.getString("deworm");
                 String cont = getvac;
                 dimx.addElement(cont);
                 jList1.setModel(dimx);
                 String cont2 = getdew;
                 dimx2.addElement(cont2);
                 jList2.setModel(dimx2);
             }
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
    }
    
    public void savedeworming(){
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("d MMM yyyy");
        String dob2 = dateFormat2.format(jDateChooser4.getDate());
        String thisdate = dob2;
        
        String string = thisdate;
        String[] parts = string.split(" ");
        String p1 = parts[0]; // 004
        String p2 = parts[1]; // 034556
        String p3 = parts[2]; // 034556

        String deday = p1;
        String demonth = p2.toUpperCase();
        String deyear = p3;
        
        try{
            String sql = "Insert into immunization_history (`pet_id`,`deworming`,`age`,`day`,`month`,`year`) values (?,?,?,?,?,?)";
            ps=conn_db.prepareStatement(sql);
            ps.setString(1, petid);
            ps.setString(2, deworm);
            ps.setString(3, jTextField3.getText()+" "+agep3);
            ps.setString(4, deday);
            ps.setString(5, demonth);
            ps.setString(6, deyear);
            ps.execute();
            
            JOptionPane.showMessageDialog(null, "Immunization history has been successfully stored");
            
            reset();
            
            autoDewormingShowHistory();
            
            audit_trail au = new audit_trail();
            au.action_type = "Saved - immunization history information by "+ pet_name.getText();
            au.module_name = "Pet Chart";
            au.saveAuditTRail();
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

        pet_id = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jToolBar2 = new javax.swing.JToolBar();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jToolBar3 = new javax.swing.JToolBar();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Manage Immunization History");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pet_id.setText("2");
        getContentPane().add(pet_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(876, 10, 0, -1));

        jTabbedPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("History of Immunization"));
        jTabbedPane2.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                jTabbedPane2AncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToolBar2.setRollover(true);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Add Green Button.png"))); // NOI18N
        jButton4.setText("Save");
        jButton4.setFocusable(false);
        jButton4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton4);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Write Document.png"))); // NOI18N
        jButton5.setText("Update");
        jButton5.setFocusable(false);
        jButton5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton5);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Clear Green Button.png"))); // NOI18N
        jButton1.setText("Refresh");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar2.add(jButton1);

        jPanel3.add(jToolBar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 3, 1150, 50));

        jDateChooser3.setBorder(javax.swing.BorderFactory.createTitledBorder("Date of Vaccination"));
        jDateChooser3.setDateFormatString("dd MMM yyyy");
        jPanel3.add(jDateChooser3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 326, 310, 70));

        jTable3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable3.getTableHeader().setReorderingAllowed(false);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable3MouseReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 160, 710, 380));

        jList1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vaccine Types", 0, 0, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        jList1.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jList1);

        jPanel3.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 164, 310, 150));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Did the pet had undergone deworming/heartworm?");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        jRadioButton4.setText("yes");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        jRadioButton5.setText("no");
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jRadioButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, -1, -1));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Age of pet"));

        jRadioButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton1.setText("Week(s)");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton2.setText("Month(s)");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jRadioButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton3.setText("Years(s)");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jTextField1.setBorder(javax.swing.BorderFactory.createTitledBorder("Age"));
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jRadioButton1)
                        .addGap(5, 5, 5)
                        .addComponent(jRadioButton2)
                        .addGap(5, 5, 5)
                        .addComponent(jRadioButton3)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3))
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 406, 310, 140));

        jTabbedPane2.addTab("Vaccination", jPanel3);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToolBar3.setRollover(true);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Add Green Button.png"))); // NOI18N
        jButton6.setText("Save");
        jButton6.setFocusable(false);
        jButton6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar3.add(jButton6);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Write Document.png"))); // NOI18N
        jButton7.setText("Update");
        jButton7.setFocusable(false);
        jButton7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jToolBar3.add(jButton7);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Clear Green Button.png"))); // NOI18N
        jButton2.setText("Refresh");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar3.add(jButton2);

        jPanel2.add(jToolBar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 3, 1150, 50));

        jList2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Deworming Types", 0, 0, new java.awt.Font("Segoe UI", 0, 14))); // NOI18N
        jList2.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jList2);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 92, 310, 210));

        jDateChooser4.setBorder(javax.swing.BorderFactory.createTitledBorder("Date of Deworming"));
        jDateChooser4.setDateFormatString("dd MMM yyyy");
        jPanel2.add(jDateChooser4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 310, 70));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Age of pet"));

        jRadioButton6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton6.setText("Week(s)");
        jRadioButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton6ActionPerformed(evt);
            }
        });

        jRadioButton7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton7.setText("Month(s)");
        jRadioButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton7ActionPerformed(evt);
            }
        });

        jRadioButton8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton8.setText("Years(s)");
        jRadioButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton8ActionPerformed(evt);
            }
        });

        jTextField3.setBorder(javax.swing.BorderFactory.createTitledBorder("Age"));
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jRadioButton6)
                        .addGap(5, 5, 5)
                        .addComponent(jRadioButton7)
                        .addGap(5, 5, 5)
                        .addComponent(jRadioButton8)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton6)
                    .addComponent(jRadioButton7)
                    .addComponent(jRadioButton8))
                .addGap(18, 18, 18)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, 310, 140));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(jTable2);

        jPanel2.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 96, 660, 450));

        jTabbedPane2.addTab("Worming", jPanel2);

        getContentPane().add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 1170, 650));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, 50, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Are you sure you want to close?",
            "Closing Immunization History", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            autoShowHistoryImmunization();
            this.dispose();
        }
        else {

            System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
            return;
        }
    }//GEN-LAST:event_formWindowClosing

    private void jTabbedPane2AncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTabbedPane2AncestorMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane2AncestorMoved

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
       if(jRadioButton1.isSelected()){
           jRadioButton2.setSelected(false);
           jRadioButton3.setSelected(false);
           jTextField1.setEnabled(true);
           agep2 = jRadioButton1.getText();
       }else{
           jTextField1.setEnabled(false);
       }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        if(jRadioButton2.isSelected()){
           jRadioButton1.setSelected(false);
           jRadioButton3.setSelected(false);
           jTextField1.setEnabled(true);
           agep2 = jRadioButton2.getText();
       }else{
            jTextField1.setEnabled(false);
        }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        if(jRadioButton3.isSelected()){
           jRadioButton1.setSelected(false);
           jRadioButton2.setSelected(false);
           jTextField1.setEnabled(true);
           agep2 = jRadioButton3.getText();
       }else{
            jTextField1.setEnabled(false);
        }
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        if(jRadioButton4.isSelected()){
            jRadioButton5.setSelected(false);
            jTextField2.setVisible(true);
            radiotrigger = "ok";
            jTabbedPane2.setSelectedIndex(1);
        }else{
            jRadioButton5.setSelected(false);
            jTextField2.setVisible(false);
            jRadioButton5.setSelected(true);
        }
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        if(jRadioButton5.isSelected()){
            jRadioButton4.setSelected(false);
            jTextField2.setVisible(false);
            radiotrigger = "ok";
        }else{
            jRadioButton5.setSelected(true);
        }
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)){
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if(vaccine.equals("") || radiotrigger.equals("no")){
            JOptionPane.showMessageDialog(null, "Empty Field(s) Detected!");
        }else{
            saveImmunizationHistory();
            JOptionPane.showMessageDialog(null, "Last Step,\n Please set the DUE DATE for the immunization on the Appointment Tab.","Immunization Due Date",JOptionPane.INFORMATION_MESSAGE);
            jTabbedPane1.setSelectedIndex(6);
            jTextArea4.setText(pet_name.getText()+"'s due for "+jList1.getSelectedValue().toString());
            dispose();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
       vaccine = jList1.getSelectedValue().toString();
    }//GEN-LAST:event_jList1MouseClicked

    private void jTable3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseReleased

    }//GEN-LAST:event_jTable3MouseReleased

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        setUpdate();
    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        UpdateImmunization();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Would you like to refresh the panel?",
            "Refreshing the panel", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                reset();
            }
            else {

                System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
                return;
           }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if(deworm.equals("")||jTextField3.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Empty Field(s) Detected!");
        }else{
            savedeworming();
            autoDewormingShowHistory();
            dewormreset();
            if(jRadioButton4.isSelected()){
                jTabbedPane2.setSelectedIndex(0);
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        autoDewormingShowHistory();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Would you like to refresh the panel?",
            "Refreshing the panel", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                dewormreset();
                autoDewormingShowHistory();
            }
            else {

                System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
                return;
           }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton6ActionPerformed
        if(jRadioButton6.isSelected()){
            jRadioButton7.setSelected(false);
            jRadioButton8.setSelected(false);
            agep3 = jRadioButton6.getText();
        }
    }//GEN-LAST:event_jRadioButton6ActionPerformed

    private void jRadioButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton7ActionPerformed
        if(jRadioButton7.isSelected()){
            jRadioButton6.setSelected(false);
            jRadioButton8.setSelected(false);
            agep3 = jRadioButton7.getText();
        }
    }//GEN-LAST:event_jRadioButton7ActionPerformed

    private void jRadioButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton8ActionPerformed
        if(jRadioButton8.isSelected()){
            jRadioButton7.setSelected(false);
            jRadioButton6.setSelected(false);
            agep3 = jRadioButton8.getText();
        }
    }//GEN-LAST:event_jRadioButton8ActionPerformed

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseClicked
        deworm = jList2.getSelectedValue().toString();
    }//GEN-LAST:event_jList2MouseClicked

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
            java.util.logging.Logger.getLogger(immunization_history.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(immunization_history.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(immunization_history.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(immunization_history.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new immunization_history().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    public static javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    public static javax.swing.JLabel pet_id;
    // End of variables declaration//GEN-END:variables
}
