/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.jpanels;

import com.jidesoft.swing.AutoCompletion;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import login.connection_db;

public class Frm_Item extends javax.swing.JFrame {

    
     Connection conn_db = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
  
    String getSupplier;
    String getSuppId;
    String getClass;
     String getClassID;
     String getITemId;
     String getItem;
     String getITemId1;
     
     String day;
     String month;
     String year;
     
     String day1;
     String month1;
     String year1;
     
     String saleUse;
     
     String xy1;
     
  //  private Object dateFomat;
    
  
    
    public Frm_Item() {
        initComponents();
        conn_db = connection_db.ConnectDB();
        
        jList1.setEnabled(false);
        jComboBox2.setEnabled(false);
        jButton7.setEnabled(false);
        jButton3.setEnabled(false);
       cmb_day1.setEnabled(false);
        cmb_month1.setEnabled(false);
        cmb_year1.setEnabled(false);
        cmb_day2.setEnabled(false);
        cmb_month2.setEnabled(false);
        cmb_year2.setEnabled(false);
        
         cmb_day1.setEditable(true);
                AutoCompletion ac = new AutoCompletion(this.cmb_day1);
                ac.setStrict(false);
         cmb_month1.setEditable(true);
                AutoCompletion ac1 = new AutoCompletion(this.cmb_month1);
                ac1.setStrict(false);
          cmb_year1.setEditable(true);
                AutoCompletion ac2 = new AutoCompletion(this.cmb_year1);
                ac2.setStrict(false);
          cmb_day2.setEditable(true);
                AutoCompletion ac3 = new AutoCompletion(this.cmb_day2);
                ac3.setStrict(false);
          cmb_month2.setEditable(true);
                AutoCompletion ac4 = new AutoCompletion(this.cmb_month2);
                ac4.setStrict(false);
          cmb_year2.setEditable(true);
                AutoCompletion ac5 = new AutoCompletion(this.cmb_year2);
                ac5.setStrict(false);
        //for centered frame////
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);   
        //

          try{
                String sql="select * from supplier";
                  ps=conn_db.prepareStatement(sql);
                  rs = ps.executeQuery();

                  while(rs.next()){
                  String getClassName = rs.getString("name");
                  cmb_supplier.addItem(getClassName);
                    }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
        }   
    }
     public void getSupplierID(){
            try{
            String sql1 ="select supplier_id from supplier where name='"+getSupplier+"'";
            ps=conn_db.prepareStatement(sql1);
            rs = ps.executeQuery();

            while(rs.next()){
                String getID1 = rs.getString("supplier_id");
                getSuppId = getID1; }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e); }
     }
     
    public void getClassification(){
     try{
            String sql1 ="select classification_id from classification1 where name='"+getClassName1+"'";
            ps=conn_db.prepareStatement(sql1);
            rs = ps.executeQuery();

            while(rs.next()){
                String getID1 = rs.getString("classification_id");
                getClassID = getID1;

            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
     public void saveItem(){
         try{
            String sql="insert into item( supplier_id,classification_id, name, CliSale, forCheck, manufacturing_day,manufacturing_month,manufacturing_year,expiration_day,expiration_month,expiration_year,pet_type)values(?,?,?,?,?,?,?,?,?,?,?,?)";
            ps=conn_db.prepareStatement(sql);

            ps.setString(1, getSuppId);
            ps.setString(2, getClassID);
            ps.setString(3,txt_item.getText().toUpperCase());
            ps.setString(4,saleUse);
            ps.setString(5,jComboBox2.getSelectedItem().toString());
            ps.setString(6, cmb_day1.getSelectedItem().toString());
            ps.setString(7, cmb_month1.getSelectedItem().toString());
             ps.setString(8, cmb_year1.getSelectedItem().toString());
            ps.setString(9, cmb_day2.getSelectedItem().toString());
            ps.setString(10, cmb_month2.getSelectedItem().toString());
            ps.setString(11, cmb_year2.getSelectedItem().toString());
            ps.setString(12,jComboBox1.getSelectedItem().toString());
            
            ps.execute();

            JOptionPane.showMessageDialog(null, "Item "+txt_item.getText().toUpperCase()+" has been sucessfully saved");

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
     }
     public void getItemID(){
          try{
           String sql1 ="select * from item where supplier_id ='"+getSuppId+"' and name='"+txt_item.getText()+"'";
           ps=conn_db.prepareStatement(sql1);
           rs = ps.executeQuery();
           
           while(rs.next()){
               String getID1 = rs.getString("item_id");
               getITemId = getID1;
               
           }
       }
       catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
   } 
     
     }
     
      public void itemUpdate(){
     try{
            String sql="UPDATE item SET supplier_id=?,classification_id=?, name=?,CliSale=?, forCheck=?, manufacturing_day=?,manufacturing_month=?,manufacturing_year=?,expiration_day=?,expiration_month=?,expiration_year=?,pet_type=? where item_id = ?" ;
            ps=conn_db.prepareStatement(sql);
            ps.setString(1, getSuppId);
            ps.setString(2, getClassID);
            ps.setString(3,txt_item.getText().toUpperCase());
            ps.setString(4,saleUse);
            ps.setString(5,jComboBox2.getSelectedItem().toString());
            ps.setString(6, cmb_day1.getSelectedItem().toString());
            ps.setString(7, cmb_month1.getSelectedItem().toString());
             ps.setString(8, cmb_year1.getSelectedItem().toString());
            ps.setString(9, cmb_day2.getSelectedItem().toString());
            ps.setString(10, cmb_month2.getSelectedItem().toString());
            ps.setString(11, cmb_year2.getSelectedItem().toString());
            ps.setString(12,jComboBox1.getSelectedItem().toString());
            ps.setString(13,getITemId);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Item "+txt_item.getText().toUpperCase()+" has been successfully updated");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
     
     }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        cmb_supplier = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        txt_item = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jComboBox2 = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Class_table = new javax.swing.JTable();
        cmb_month2 = new javax.swing.JComboBox();
        cmb_day2 = new javax.swing.JComboBox();
        cmb_day1 = new javax.swing.JComboBox();
        cmb_month1 = new javax.swing.JComboBox();
        cmb_year2 = new javax.swing.JComboBox();
        cmb_year1 = new javax.swing.JComboBox();
        jComboBox1 = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Add Item");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cmb_supplier.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmb_supplier.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmb_supplierItemStateChanged(evt);
            }
        });
        cmb_supplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_supplierActionPerformed(evt);
            }
        });
        getContentPane().add(cmb_supplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 200, -1));

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 200, 80));

        txt_item.setForeground(new java.awt.Color(0, 102, 0));
        txt_item.setText("*Item Name");
        txt_item.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Item Name", 0, 0, new java.awt.Font("Tahoma", 0, 11))); // NOI18N
        txt_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_itemActionPerformed(evt);
            }
        });
        txt_item.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_itemFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_itemFocusLost(evt);
            }
        });
        getContentPane().add(txt_item, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 200, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Supplier Name");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 80, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Classification Name");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 110, -1));

        jRadioButton1.setText("Clinical Use");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, -1));

        jRadioButton2.setText("For Sale");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 80, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CHECKUP", "GROOMING", "FOODS", "FOODS-CHECKUP-GROOMING" }));
        jComboBox2.setBorder(javax.swing.BorderFactory.createTitledBorder("Used for"));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 200, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Class_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Supplier Name", "Classification Name", "Item Name", "Purpose", "Use For", "Manufactured Date", "Expiration Date", "Pet Type"
            }
        ));
        Class_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Class_tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Class_table);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 880, 460));

        cmb_month2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" }));
        cmb_month2.setBorder(null);
        jPanel1.add(cmb_month2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 450, 60, 30));

        cmb_day2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        cmb_day2.setBorder(null);
        cmb_day2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_day2ActionPerformed(evt);
            }
        });
        jPanel1.add(cmb_day2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 50, 30));

        cmb_day1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        cmb_day1.setBorder(null);
        jPanel1.add(cmb_day1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 50, 30));

        cmb_month1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" }));
        cmb_month1.setBorder(null);
        jPanel1.add(cmb_month1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 380, 60, 30));

        cmb_year2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
        cmb_year2.setBorder(null);
        jPanel1.add(cmb_year2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 450, 70, 30));

        cmb_year1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
        cmb_year1.setBorder(null);
        cmb_year1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_year1ActionPerformed(evt);
            }
        });
        cmb_year1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmb_year1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cmb_year1KeyTyped(evt);
            }
        });
        jPanel1.add(cmb_year1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, 70, 30));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "cat", "dog", "pig" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pet Type"));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 500, 200, -1));

        jButton3.setText("Save");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 530, 70, -1));

        jButton7.setText("Update");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 530, -1, -1));

        jLabel7.setText("Day");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, -1, -1));

        jLabel8.setText("Month");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 430, -1, -1));

        jLabel10.setText("Day");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, -1, -1));

        jLabel11.setText("Month");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 360, -1, -1));

        jLabel12.setText("Year");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 430, -1, -1));

        jLabel9.setText("Year");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 360, -1, -1));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Transcation 3-4");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 530, -1, -1));

        jButton4.setText("Next");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 550, 100, -1));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setText("View");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Expiration Date");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 120, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Manufactured Date");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 120, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg.jpg"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, 0, 1270, 590));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1220, 590));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_supplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_supplierActionPerformed
         String xy = cmb_supplier.getSelectedItem().toString();
        getSupplier = xy;
        
        jList1.setEnabled(true);
    }//GEN-LAST:event_cmb_supplierActionPerformed

    private void cmb_supplierItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_supplierItemStateChanged
       
        
    }//GEN-LAST:event_cmb_supplierItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
//         getSupplierID();
//        getClassification();
//        getItemID();
        try{
           String sql="select * from supplier where name = '"+getSupplier+"'";
           ps=conn_db.prepareStatement(sql);
           rs = ps.executeQuery();
           
           while(rs.next()){
           String getClassName = rs.getString("supplier_id");
           getSuppId = getClassName;  
           System.out.println(getSuppId);
           }
        }
        catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        }
         
          DefaultListModel dimx = new DefaultListModel();
        try{
            
            String sql="select * from classification1 where supplier_id= '"+getSuppId +"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getname = rs.getString("name");  
                dimx.addElement(getname);
                jList1.setModel(dimx);
            }    
        }catch(Exception e){     
            JOptionPane.showMessageDialog(null, e);
        }    
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_itemActionPerformed

    }//GEN-LAST:event_txt_itemActionPerformed

    private void txt_itemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_itemFocusGained
        if(txt_item.getText().equals("*Item Name")){
            txt_item.setText("");
            txt_item.setForeground(Color.BLACK);
        }else{

        }
    }//GEN-LAST:event_txt_itemFocusGained

    private void txt_itemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_itemFocusLost
        if(txt_item.getText().equals("")){
            txt_item.setText("*Item Name");
            txt_item.setForeground(new Color(51,102,0));
        }else{
        }
    }//GEN-LAST:event_txt_itemFocusLost

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        
        if(txt_item.getText().equals("*Item Name") || txt_item.getText().equals("") ){
            JOptionPane.showMessageDialog(null, "Empty field detected!");
        }else{
            
//            SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
//            String dod = dateFormat.format(jDateChooser1.getDate());
//            String  string3 = dod;
//            String[] parts3 = string3.split(" ");
//            day = parts3[0];
//            month = parts3[1];
//            year = parts3[2];
//            
//            SimpleDateFormat dateFormat1 = new SimpleDateFormat("d MMM yyyy");
//            String dod1 = dateFormat1.format(jDateChooser1.getDate());
//            String  string4 = dod1;
//            String[] parts4 = string4.split(" ");
//            day1 = parts4[0];
//            month1 = parts4[1];
//            year1 = parts4[2];
            
            getSupplierID();
            getClassification();
            saveItem(); 
            DefaultTableModel model = (DefaultTableModel)Class_table.getModel();
            String conDate = cmb_day1.getSelectedItem()+" "+cmb_month1.getSelectedItem()+" "+cmb_year1.getSelectedItem();
            String conDate1 = cmb_day2.getSelectedItem()+" "+cmb_month2.getSelectedItem()+" "+cmb_year2.getSelectedItem();
            model.addRow(new Object[]{cmb_supplier.getSelectedItem(),getClassName1,txt_item.getText(),saleUse,jComboBox2.getSelectedItem(),conDate,conDate1,jComboBox1.getSelectedItem()});
            cmb_supplier.setSelectedItem("");
            DefaultListModel dimx = new DefaultListModel();
            dimx.removeAllElements();
            jList1.setModel(dimx);
            txt_item.setText("");
            jComboBox2.setSelectedItem("");
            //saleUse.setText("");
            jComboBox1.setSelectedItem("");
            jButton7.setEnabled(true);
        }
    }//GEN-LAST:event_jButton3ActionPerformed
         String getClassName1;
    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
    //  JOptionPane.showMessageDialog(null,jList1.getSelectedIndex()); 
      String x = ((DefaultListModel)jList1.getModel()).getElementAt(jList1.getSelectedIndex()).toString();
      getClassName1 = x;
    }//GEN-LAST:event_jList1MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if(txt_item.getText().equals("*Item Name") || txt_item.getText().equals("") ){
            JOptionPane.showMessageDialog(null, "Empty field detected!");
        }else{
            
//            SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
//            String dod = dateFormat.format(jDateChooser1.getDate());
//            String  string3 = dod;
//            String[] parts3 = string3.split(" ");
//            day = parts3[0];
//            month = parts3[1];
//            year = parts3[2];
//            
//            SimpleDateFormat dateFormat1 = new SimpleDateFormat("d MMM yyyy");
//            String dod1 = dateFormat1.format(jDateChooser1.getDate());
//            String  string4 = dod1;
//            String[] parts4 = string4.split(" ");
//            day1 = parts4[0];
//            month1 = parts4[1];
//            year1 = parts4[2];
        itemUpdate();
        DefaultTableModel model1 = (DefaultTableModel)Class_table.getModel();
        
         String conDate = cmb_day1.getSelectedItem()+" "+cmb_month1.getSelectedItem()+" "+cmb_year1.getSelectedItem();
         String conDate1 = cmb_day2.getSelectedItem()+" "+cmb_month2.getSelectedItem()+" "+cmb_year2.getSelectedItem();
            
        model1.setValueAt(cmb_supplier.getSelectedItem(),Class_table.getSelectedRow(),1);
        model1.setValueAt(getClassName1,Class_table.getSelectedRow(),2);
        model1.setValueAt(txt_item.getText(),Class_table.getSelectedRow(),3);
        model1.setValueAt(jRadioButton1.getSelectedIcon(),Class_table.getSelectedRow(),4);
        model1.setValueAt(jRadioButton2.getSelectedIcon(),Class_table.getSelectedRow(),4);
        model1.setValueAt(jComboBox2.getSelectedItem(),Class_table.getSelectedRow(),5);
        model1.setValueAt(conDate,Class_table.getSelectedRow(),6);
        model1.setValueAt(conDate1,Class_table.getSelectedRow(),7);
         model1.setValueAt(jComboBox1.getSelectedItem(),Class_table.getSelectedRow(),8);
        
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void Class_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Class_tableMouseClicked
        DefaultListModel dimx = new DefaultListModel();

        DefaultTableModel model1 = (DefaultTableModel)Class_table.getModel();
        cmb_supplier.setSelectedItem(model1.getValueAt(Class_table.getSelectedRow(),0).toString());
        dimx.addElement((model1.getValueAt(Class_table.getSelectedRow(),1).toString()));
        jList1.setModel(dimx);
        txt_item.setText(model1.getValueAt(Class_table.getSelectedRow(),2).toString());
      // jRadioButton1.setSelectedIcon(model1.getValueAt(Class_table.getSelectedRow(),3).toString());
        jComboBox2.setSelectedItem(model1.getValueAt(Class_table.getSelectedRow(),4).toString());
       
        jComboBox1.setSelectedItem(model1.getValueAt(Class_table.getSelectedRow(),5).toString());
        
        getSupplierID();
        getClassification();
        getItemID();
    }//GEN-LAST:event_Class_tableMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Are you sure you want to close?",
            "Closing Add Item", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            this.dispose();
            
        }
        else {

            System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
            return;
        }
    }//GEN-LAST:event_formWindowClosing

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        jButton3.setEnabled(true);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        new beginning_inventory().setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
         String xy1 = jComboBox2.getSelectedItem().toString();
        getSupplier = xy1;
        
        
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        jComboBox2.setEnabled(true);
        cmb_day1.setEnabled(true);
        cmb_month1.setEnabled(true);
        cmb_year1.setEnabled(true);
        cmb_day2.setEnabled(true);
        cmb_month2.setEnabled(true);
        cmb_year2.setEnabled(true);
        
        if(jRadioButton1.isSelected()){
           jRadioButton2.setSelected(false);
           saleUse="Clinical Use";
       }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        jComboBox2.setEnabled(false);
         jComboBox2.setEnabled(true);
        cmb_day1.setEnabled(true);
        cmb_month1.setEnabled(true);
        cmb_year1.setEnabled(true);
        cmb_day2.setEnabled(true);
        cmb_month2.setEnabled(true);
        cmb_year2.setEnabled(true);
        
        if(jRadioButton2.isSelected()){
           jRadioButton1.setSelected(false);
           saleUse="For Sale";
       }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void cmb_day2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_day2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_day2ActionPerformed

    private void cmb_year1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_year1ActionPerformed

    }//GEN-LAST:event_cmb_year1ActionPerformed

    private void cmb_year1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmb_year1KeyTyped
//         char c = evt.getKeyChar();
//            if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)){
//                evt.consume();
//                getToolkit().beep();
//        }
    }//GEN-LAST:event_cmb_year1KeyTyped

    private void cmb_year1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmb_year1KeyReleased
//         String n = cmb_year1.getText();
//        int num = n.length();
//        if(num<=12){
//            supp_contact.setEditable(true);
//        }else{
//            supp_contact.setEditable(false);
//        }
    }//GEN-LAST:event_cmb_year1KeyReleased

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
            java.util.logging.Logger.getLogger(Frm_Item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_Item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_Item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_Item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_Item().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Class_table;
    private javax.swing.JComboBox cmb_day1;
    private javax.swing.JComboBox cmb_day2;
    private javax.swing.JComboBox cmb_month1;
    private javax.swing.JComboBox cmb_month2;
    private javax.swing.JComboBox cmb_supplier;
    private javax.swing.JComboBox cmb_year1;
    private javax.swing.JComboBox cmb_year2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txt_item;
    // End of variables declaration//GEN-END:variables
}
