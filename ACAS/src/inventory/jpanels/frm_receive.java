/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.jpanels;

import com.jidesoft.swing.AutoCompletion;
import com.toedter.calendar.JCalendar;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import login.connection_db;
import net.proteanit.sql.DbUtils;

public class frm_receive extends javax.swing.JFrame {

     Connection conn_db = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    String getSupplier;
    String getSuppId;
    String getoredrId;
    String getClassID;
    String getITemId;
    String getorderID;
    
    String getItemID ;
    
    String saleUse;
    
    String day;
    String month;
    String year;
    
    String getPetName;
    String getPetTypeID ;
    
    String getService;
    String getServiceID;
    
   
  
   
    public frm_receive() {
        initComponents();
        
         conn_db = connection_db.ConnectDB();
         
         autodate();
         
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
         
          autodate();
         
         jTextField1.setEnabled(false);
         jTextField2.setEnabled(false);
         jList2.setEnabled(false);
      //   jButton1.setEnabled(false);
         jButton4.setEnabled(false);
        jList1.setEnabled(false);
        jComboBox2.setEnabled(false);
         supp_price.setEnabled(false);
         jButton6.setEnabled(false);
         PERcent.setEnabled(false);
         Value.setEnabled(false);
         jTextField4.setEnabled(false);
         qty.setEnabled(false);
         jComboBox3.setEnabled(false);
       //  save.setEnabled(false);
         Update.setEnabled(false);
         
         //getordertoshow();
         //getIDtoshow();
         
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
         
        
         
          try{
                String sql="select * from services_data";
                  ps=conn_db.prepareStatement(sql);
                  rs = ps.executeQuery();

                  while(rs.next()){
                  String getClassName2 = rs.getString("name");
                  jComboBox2.addItem(getClassName2);
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
            String sql1 ="select classification_id from classification1";
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
     public void GetItem(){
     try{
            String sql1 ="select item_id from item";
            ps=conn_db.prepareStatement(sql1);
            rs = ps.executeQuery();

            while(rs.next()){
                String getID1 = rs.getString("item_id");
                getItemName1 = getID1;

            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
      public void Getorder(){
     try{
            String sql1 ="select order_id from order_item";
            ps=conn_db.prepareStatement(sql1);
            rs = ps.executeQuery();

            while(rs.next()){
                String getID1 = rs.getString("order_id");
                getorderID = getID1;

            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
      
      
       
        public void GeService(){
     try{
            String sql1 ="select service_id from services_data where name='"+getService+"'";
            ps=conn_db.prepareStatement(sql1);
            rs = ps.executeQuery();

            while(rs.next()){
                String getID2 = rs.getString("service_id");
                getServiceID = getID2;

            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
        
     public void saveReceiveItem(){
         try{
            String sql="insert into receive_item( supplier_id,order_id,deliver, invoice,classification_id, item_id, purpose, service_id,"
                    + " manu_day,manu_month,manu_year,expi_day,expi_month,expi_year,unit_price, retail_price, qty, kind,status,"
                    + "receive_day,receive_month, receive_year)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            ps=conn_db.prepareStatement(sql);

            ps.setString(1, getSuppId);
            ps.setString(2, getorderID);
            ps.setString(3,jTextField1.getText());
            ps.setString(4,jTextField2.getText());
            ps.setString(5, getClassID);
            ps.setString(6,getItemName1);
            ps.setString(7,saleUse);
            ps.setString(8,getServiceID);
            ps.setString(9, cmb_day1.getSelectedItem().toString());
            ps.setString(10, cmb_month1.getSelectedItem().toString());
             ps.setString(11, cmb_year1.getSelectedItem().toString());
            ps.setString(12, cmb_day2.getSelectedItem().toString());
            ps.setString(13, cmb_month2.getSelectedItem().toString());
            ps.setString(14, cmb_year2.getSelectedItem().toString());
            ps.setString(15,supp_price.getText());
            ps.setString(16,jTextField4.getText());
            ps.setString(17,qty.getText());
            ps.setString(18,jComboBox3.getSelectedItem().toString());
            ps.setString(19,jComboBox4.getSelectedItem().toString());
            ps.setString(20,day);
            ps.setString(21,month);
            ps.setString(22,year);
            
            ps.execute();

            JOptionPane.showMessageDialog(null," has been sucessfully saved");

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
     } 
     
     public void getIDtoshow(){
       
        try{
            String sql ="SELECT CONCAT_WS ('',order_day,order_month,order_year) AS `Date`, `order_num` as `order number` FROM `order_item` INNER JOIN supplier on order_item.supplier_id = supplier.supplier_id WHERE supplier.name = '"+cmb_supplier.getSelectedItem().toString()+"'";
            ps=conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            tbl_order.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
  
     }
     
     public void getordertoshow(){
// CONCAT_WS ('',order_day,order_month,order_year) LIKE '"+selectedObject+"' AND
             try{
            String sql1 ="SELECT supplier.name as `Supplier Name`, classification1.name AS `Class Name`, item.name as `Item Name`,"
                    + " `order_qty` as `order quantity`, order_item.kind as `Volume`, unit_price as `supplier Price`, order_item.note as `Note` FROM order_item "
                    + "INNER JOIN supplier on order_item.supplier_id = supplier.supplier_id INNER JOIN classification1 "
                    + "on order_item.classification_id = classification1.classification_id INNER JOIN item on order_item.item_id=item.item_id"
                    + " WHERE  `order_num`= '"+selectedObject+"'" ;          
                  ps=conn_db.prepareStatement(sql1);
                  rs = ps.executeQuery();
                    Class_table.setModel(DbUtils.resultSetToTableModel(rs));
                  }
                  catch(Exception e){
                        JOptionPane.showMessageDialog(null, e);
                }
                }
     
       public void getorderID(){
            try{
            String sql1 ="select order_id from order_item ";
            ps=conn_db.prepareStatement(sql1);
            rs = ps.executeQuery();

            while(rs.next()){
                String getID1 = rs.getString("supplier_id");
                getoredrId = getID1; }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e); 
        }
     }
       
      public void validateDate(){
          
              String month1 = cmb_month1.getSelectedItem().toString();
          String month2 = cmb_month2.getSelectedItem().toString();
          int montset1 = 0;
          int montset2 = 0;
          
          if(month1.equals("JAN")){
              montset1 = 1;
          }
          if(month1.equals("FEB")){
              montset1 = 2;
          }
          if(month1.equals("MAR")){
              montset1 = 3;
          }
          if(month1.equals("APR")){
              montset1 = 4;
          }
          if(month1.equals("MAY")){
              montset1 = 5;
          }
          if(month1.equals("JUN")){
              montset1 = 6;
          }
          if(month1.equals("JUL")){
              montset1 = 7;
          }
          if(month1.equals("AUG")){
              montset1 = 8;
          }
          if(month1.equals("SEP")){
              montset1 = 9;
          }
          if(month1.equals("OCT")){
              montset1 = 10;
          }
          if(month1.equals("NOV")){
              montset1 = 11;
          }
          if(month1.equals("DEC")){
              montset1 = 12;
          }
          
          ////////////////////////////
          
          if(month2.equals("JAN")){
              montset2 = 1;
          }
          if(month2.equals("FEB")){
              montset2 = 2;
          }
          if(month2.equals("MAR")){
              montset2 = 3;
          }
          if(month2.equals("APR")){
              montset2 = 4;
          }
          if(month2.equals("MAY")){
              montset2 = 5;
          }
          if(month2.equals("JUN")){
              montset2 = 6;
          }
          if(month2.equals("JUL")){
              montset2 = 7;
          }
          if(month2.equals("AUG")){
              montset2 = 8;
          }
          if(month2.equals("SEP")){
              montset2 = 9;
          }
          if(month2.equals("OCT")){
              montset2 = 10;
          }
          if(month2.equals("NOV")){
              montset2 = 11;
          }
          if(month2.equals("DEC")){
              montset2 = 12;
          }
          
          int year1 = Integer.parseInt(cmb_year1.getSelectedItem().toString());
          int year2 = Integer.parseInt(cmb_year2.getSelectedItem().toString());
          
          if(montset1>montset2){
              
              if(year1>year2){

              JOptionPane.showMessageDialog(null, "Year Error");
              cmb_year1.setSelectedIndex(0);
              cmb_year2.setSelectedIndex(0);
              cmb_month1.setSelectedIndex(0);
              cmb_month2.setSelectedIndex(0);
          }else{
                 JOptionPane.showMessageDialog(null, "Month Error");
                cmb_year1.setSelectedIndex(0);
                cmb_year2.setSelectedIndex(0);
                cmb_month1.setSelectedIndex(0);
                cmb_month2.setSelectedIndex(0); 
              }
          }else{
              
          } 
      }
         
      public void autodate(){
       JCalendar x = new JCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
        String dob = dateFormat.format(x.getDate());
        txt_calendar.setText(dob);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmb_supplier = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jComboBox2 = new javax.swing.JComboBox();
        save = new javax.swing.JButton();
        Update = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Class_table = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        supp_price = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        PERcent = new javax.swing.JButton();
        Value = new javax.swing.JButton();
        qty = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_order = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox();
        jComboBox4 = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmb_day1 = new javax.swing.JComboBox();
        cmb_month1 = new javax.swing.JComboBox();
        cmb_year1 = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        cmb_day2 = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        cmb_month2 = new javax.swing.JComboBox();
        cmb_year2 = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_calendar = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tc_table = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Supplier Name");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 80, -1));

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
        getContentPane().add(cmb_supplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 200, -1));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setText("View");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 60, -1));

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 200, 70));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Item Name");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 510, 110, -1));

        jRadioButton1.setText("Clinical Use");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 610, -1, -1));

        jRadioButton2.setText("For Sale");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 610, 80, -1));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CHECKUP", "GROOMING", "FOODS", "FOODS-CHECKUP-GROOMING" }));
        jComboBox2.setBorder(javax.swing.BorderFactory.createTitledBorder("Used for"));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 640, 200, -1));

        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        getContentPane().add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 640, 70, 30));

        Update.setText("Update");
        Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateActionPerformed(evt);
            }
        });
        getContentPane().add(Update, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 640, -1, 30));

        Class_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        Class_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Class_tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Class_table);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 60, 840, 280));

        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jList2);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 200, 70));

        jTextField1.setBorder(javax.swing.BorderFactory.createTitledBorder("Delivery Receipt"));
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 200, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("List of order number");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 120, -1));

        jTextField2.setBorder(javax.swing.BorderFactory.createTitledBorder("Invoice Number"));
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 350, 200, -1));

        jButton4.setText("Add");
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 60, -1));

        supp_price.setBorder(javax.swing.BorderFactory.createTitledBorder("Supplier Price"));
        supp_price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supp_priceActionPerformed(evt);
            }
        });
        getContentPane().add(supp_price, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 200, -1));

        jButton6.setText("Update SP");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 290, 90, -1));

        jTextField4.setBorder(javax.swing.BorderFactory.createTitledBorder("Sale Price"));
        getContentPane().add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 390, 190, -1));

        PERcent.setText("Percentage");
        PERcent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PERcentMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                PERcentMouseReleased(evt);
            }
        });
        PERcent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PERcentActionPerformed(evt);
            }
        });
        getContentPane().add(PERcent, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 320, 90, -1));

        Value.setText("Value");
        Value.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ValueActionPerformed(evt);
            }
        });
        getContentPane().add(Value, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 350, 90, -1));

        qty.setBorder(javax.swing.BorderFactory.createTitledBorder("Quantity"));
        getContentPane().add(qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 120, -1));

        tbl_order.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbl_order.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_orderMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_order);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 200, 90));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Classification Name");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 110, -1));

        jButton5.setText("View");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 60, -1));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pc(s)", "Pack(s)", "Bottle(s)", "Box(s)", "Sachet(s)", "Sack(s)", "" }));
        getContentPane().add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 450, 70, 40));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "complete", "incomplete", "partial" }));
        jComboBox4.setBorder(javax.swing.BorderFactory.createTitledBorder("Status"));
        getContentPane().add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 510, 200, 60));

        jLabel10.setText("Day");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, -1, -1));

        jLabel11.setText("Month");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, -1, -1));

        jLabel9.setText("Year");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, -1, -1));

        cmb_day1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        cmb_day1.setBorder(null);
        getContentPane().add(cmb_day1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, 50, 30));

        cmb_month1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" }));
        cmb_month1.setBorder(null);
        cmb_month1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_month1ActionPerformed(evt);
            }
        });
        getContentPane().add(cmb_month1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 60, 30));

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
        getContentPane().add(cmb_year1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 70, 30));

        jLabel7.setText("Day");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, -1, -1));

        cmb_day2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        cmb_day2.setBorder(null);
        cmb_day2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_day2ActionPerformed(evt);
            }
        });
        getContentPane().add(cmb_day2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 50, 30));

        jLabel8.setText("Month");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 160, -1, -1));

        cmb_month2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" }));
        cmb_month2.setBorder(null);
        cmb_month2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_month2ActionPerformed(evt);
            }
        });
        getContentPane().add(cmb_month2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, 60, 30));

        cmb_year2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
        cmb_year2.setBorder(null);
        cmb_year2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_year2ActionPerformed(evt);
            }
        });
        getContentPane().add(cmb_year2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 180, 70, 30));

        jLabel12.setText("Year");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 160, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Manufactured Date");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 120, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Expiration Date");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 120, -1));

        txt_calendar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_calendar.setForeground(new java.awt.Color(102, 102, 102));
        getContentPane().add(txt_calendar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 140, 20));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Date:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("List of Receive Item");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 350, -1, -1));

        tc_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Supplier", "order number", "Delivery Receipt", "Invoice", "Classification ", "Item", "Purpose", "Use For", "Manufactured Date", "Expiration date", "Supplier Price", "Sale Price", "Quantity", "Status"
            }
        ));
        jScrollPane5.setViewportView(tc_table);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 370, 840, 260));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("List of order Item");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 40, -1, -1));
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, 40, 20));

        jLabel17.setText("Percentage:");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmb_supplierItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmb_supplierItemStateChanged

    }//GEN-LAST:event_cmb_supplierItemStateChanged

    private void cmb_supplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_supplierActionPerformed
        String xy = cmb_supplier.getSelectedItem().toString();
        getSupplier = xy;
        
//             SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
//            String dod = dateFormat.format(jDateChooser1.getDate());
//            String  string3 = dod;
//            String[] parts3 = string3.split(" ");
//            day = parts3[0];
//            month = parts3[1];
//            year = parts3[2];
        
        
        
            
    
    }//GEN-LAST:event_cmb_supplierActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
        
        getIDtoshow();
    }//GEN-LAST:event_jButton2ActionPerformed
        String getItemName1;
    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        
        String x = ((DefaultListModel)jList1.getModel()).getElementAt(jList1.getSelectedIndex()).toString();
        getItemName1 = x;
    }//GEN-LAST:event_jList1MouseClicked

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
       if(jRadioButton1.isSelected()){
            jRadioButton2.setSelected(false);
            saleUse="Clinical Use";
        }else{
         
       }
        jComboBox2.setEnabled(true);
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        
        if(jRadioButton2.isSelected()){
            jRadioButton1.setSelected(false);
           saleUse="For Sale";
        }
        jComboBox2.setEnabled(false);
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        String xy1 = jComboBox2.getSelectedItem().toString();
       getService = xy1;
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        if(jTextField1.getText().equals("*Item Name") ||jTextField1.getText().equals("") ){
            JOptionPane.showMessageDialog(null, "Empty field detected!");
        }else{
            
            String string3 = txt_calendar.getText();
            String[] parts3 = string3.split(" ");
            String p1 = parts3[0]; // 004
            String p2 = parts3[1]; // 034556
            String p3 = parts3[2]; // 034556

            day = p1;
            month = p2;
            year = p3;
            
            getSupplierID();
            Getorder();
            getClassification();
            GetItem();
            GeService();
         
           saveReceiveItem(); 
            DefaultTableModel model = (DefaultTableModel)tc_table.getModel();
            String qtyKind = qty.getText()+" "+jComboBox3.getSelectedItem();
            String conDate = cmb_day1.getSelectedItem()+" "+cmb_month1.getSelectedItem()+" "+cmb_year1.getSelectedItem();
            String conDate1 = cmb_day2.getSelectedItem()+" "+cmb_month2.getSelectedItem()+" "+cmb_year2.getSelectedItem();
            model.addRow(new Object[]{cmb_supplier.getSelectedItem(),selectedObject,jTextField1.getText(),jTextField2.getText(),getClassName1,
                getItemName1,saleUse,jComboBox2.getSelectedItem(),conDate,conDate1,supp_price.getText(),jTextField4.getText(),qtyKind,jComboBox4.getSelectedItem()});
          
//            cmb_supplier.setSelectedItem("");
//            DefaultListModel dimx = new DefaultListModel();
//            dimx.removeAllElements();
//            jList1.setModel(dimx);
//            txt_item.setText("");
//            jComboBox2.setSelectedItem("");
//            //saleUse.setText("");
//            jComboBox1.setSelectedItem("");
//            jButton7.setEnabled(true);
        }
      
    }//GEN-LAST:event_saveActionPerformed

    private void UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateActionPerformed

    }//GEN-LAST:event_UpdateActionPerformed

    private void Class_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Class_tableMouseClicked
      DefaultListModel dimx = new DefaultListModel();
       DefaultListModel dimx1 = new DefaultListModel();
        
        DefaultTableModel model1 = (DefaultTableModel)Class_table.getModel();
        cmb_supplier.setSelectedItem(model1.getValueAt(Class_table.getSelectedRow(),0).toString());
        dimx.addElement((model1.getValueAt(Class_table.getSelectedRow(),1).toString()));
        jList2.setModel(dimx);
        dimx1.addElement((model1.getValueAt(Class_table.getSelectedRow(),2).toString()));
        jList1.setModel(dimx1);
        qty.setText(model1.getValueAt(Class_table.getSelectedRow(),3).toString());
        jComboBox3.setSelectedItem(model1.getValueAt(Class_table.getSelectedRow(),4).toString());
        supp_price.setText(model1.getValueAt(Class_table.getSelectedRow(),5).toString());
        
        jTextField1.setEnabled(true);
         jTextField2.setEnabled(true);
         jList2.setEnabled(true);
        
         jButton4.setEnabled(true);
        jList1.setEnabled(true);
        jComboBox2.setEnabled(true);
        
         supp_price.setEnabled(true);
         jButton6.setEnabled(true);
         PERcent.setEnabled(true);
         Value.setEnabled(true);
         jTextField4.setEnabled(true);
         qty.setEnabled(true);
         jComboBox3.setEnabled(true);
//        

    }//GEN-LAST:event_Class_tableMouseClicked
            String getClassName1;
    private void jList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseClicked
        String x = ((DefaultListModel)jList2.getModel()).getElementAt(jList2.getSelectedIndex()).toString();
      getClassName1 = x;
        
        // jButton1.setEnabled(true);
    }//GEN-LAST:event_jList2MouseClicked
//      String selectedObject1;             
    String selectedObject;
    private void tbl_orderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_orderMouseClicked
      //  public int[] getSelectedRows();
       int selectedColumnIndexs = tbl_order.getSelectedColumn();
          int selectedRowIndexs = tbl_order.getSelectedRow();
             String x = ((DefaultTableModel)tbl_order.getModel()).getValueAt(selectedRowIndexs,selectedColumnIndexs).toString();
             
                    selectedObject = x;
                    

    }//GEN-LAST:event_tbl_orderMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
      
        //getSupplierID();   
        getordertoshow();
    }//GEN-LAST:event_jButton5ActionPerformed
               
        String stringInput; 
        
        int percentage;
      
    private void PERcentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PERcentActionPerformed
        String x = JOptionPane.showInputDialog(null, "Input percentage amount");
        BigDecimal percentage = new BigDecimal(x);
        BigDecimal supplierprice= new BigDecimal(supp_price.getText());
        BigDecimal y = new BigDecimal("100");
        
        BigDecimal answer = supplierprice.multiply(percentage).divide(y);
        
        BigDecimal finalans = supplierprice.add(answer);
        
        jTextField4.setText(finalans.toString());
        
        jLabel16.setText(answer.toString());
        
         jTextField4.setEnabled(true);
         qty.setEnabled(true);
         jComboBox3.setEnabled(true);
        
    }//GEN-LAST:event_PERcentActionPerformed

    private void supp_priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supp_priceActionPerformed
       
    }//GEN-LAST:event_supp_priceActionPerformed

    private void PERcentMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PERcentMouseReleased

    }//GEN-LAST:event_PERcentMouseReleased

    private void PERcentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PERcentMouseClicked
  
    }//GEN-LAST:event_PERcentMouseClicked
                      
        String stringInput1; 
        int value;
    private void ValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ValueActionPerformed
         String x = JOptionPane.showInputDialog(null, "Input value amount");
        BigDecimal value = new BigDecimal(x);
        BigDecimal supplierprice= new BigDecimal(supp_price.getText());
        BigDecimal y = new BigDecimal("100");
        
        BigDecimal answer = supplierprice.add(value);
        
       // BigDecimal finalans = supplierprice.add(answer);
        
        jTextField4.setText(answer.toString());
        
         jTextField4.setEnabled(true);
         qty.setEnabled(true);
         jComboBox3.setEnabled(true);
    }//GEN-LAST:event_ValueActionPerformed

    private void cmb_year1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_year1ActionPerformed
        validateDate();
    }//GEN-LAST:event_cmb_year1ActionPerformed

    private void cmb_year1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmb_year1KeyReleased
        //         String n = cmb_year1.getText();
        //        int num = n.length();
        //        if(num<=12){
            //            supp_contact.setEditable(true);
            //        }else{
            //            supp_contact.setEditable(false);
            //        }
    }//GEN-LAST:event_cmb_year1KeyReleased

    private void cmb_year1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmb_year1KeyTyped
        //         char c = evt.getKeyChar();
        //            if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)){
            //                evt.consume();
            //                getToolkit().beep();
            //        }
    }//GEN-LAST:event_cmb_year1KeyTyped

    private void cmb_day2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_day2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmb_day2ActionPerformed
       
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       String x = JOptionPane.showInputDialog(null, "Input percentage amount");
        BigDecimal percentage = new BigDecimal(x);
        
        
        jTextField4.setEnabled(true);
         qty.setEnabled(true);
         jComboBox3.setEnabled(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void cmb_year2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_year2ActionPerformed
        validateDate();
    }//GEN-LAST:event_cmb_year2ActionPerformed

    private void cmb_month1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_month1ActionPerformed
        validateDate();
    }//GEN-LAST:event_cmb_month1ActionPerformed

    private void cmb_month2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_month2ActionPerformed
        validateDate();
    }//GEN-LAST:event_cmb_month2ActionPerformed

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
            java.util.logging.Logger.getLogger(frm_receive.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_receive.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_receive.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_receive.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_receive().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Class_table;
    private javax.swing.JButton PERcent;
    private javax.swing.JButton Update;
    private javax.swing.JButton Value;
    private javax.swing.JComboBox cmb_day1;
    private javax.swing.JComboBox cmb_day2;
    private javax.swing.JComboBox cmb_month1;
    private javax.swing.JComboBox cmb_month2;
    private javax.swing.JComboBox cmb_supplier;
    private javax.swing.JComboBox cmb_year1;
    private javax.swing.JComboBox cmb_year2;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField qty;
    private javax.swing.JButton save;
    private javax.swing.JTextField supp_price;
    private javax.swing.JTable tbl_order;
    private javax.swing.JTable tc_table;
    private javax.swing.JLabel txt_calendar;
    // End of variables declaration//GEN-END:variables
}
