/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billing.jpanels;

import audit_trailing.audit_trail;
import com.toedter.calendar.JCalendar;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import login.connection_db;
import static emr.jpanels.pet_chart.*;
import other.features.jpanels.customMessage_Screen;

/**
 *
 * @author IrvGu
 */
public class bill extends javax.swing.JFrame {
    
    Connection conn_db = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    String searchlname;
    String searchfname;
    String searchpetname;
    
    String day;
    String month;
    String year;

    String petIDtoShow;
    
    int origPriceForDiscount;
    BigDecimal originalprice = new BigDecimal("0");
    
    double contPrice;
    double contPrice2;
    BigDecimal bdcontPrice2 = new BigDecimal("0");
    
    BigDecimal itemChargedPrice = new BigDecimal("0");
    
    String billingID;
    
    String savelastinput;
    
    int forchange;
    
    String amountDue;
    
    BigDecimal bdforchange;
    
    final JPopupMenu menu = new JPopupMenu();

    /**
     * Creates new form bill
     */
    public bill() {
        initComponents();
        jLabel17.setVisible(false);
        conn_db = connection_db.ConnectDB();
        pid_bill.setText(petid);//
        date();
        fillCB();
        showAcquired();
        getPetName();
        sumUpPrice();
        contPrice2 = 0;
        viewItemChargedOnTable();
        sumUpPriceItemCharged();
        sumupTotal();
        
        int sa = acqruired_services_table.getRowCount();
        if(sa==0){
            dispose();
            jLabel6.setText("Php 000.00");
            discountdoctorfee();
            jRadioButton2.setSelected(true);
            jLabel6.setText("Php 000.00");
        }
        
        ActionListener actionListener = new PopupActionListener();
        JMenuItem item = new JMenuItem("Discount Doctor's fee");
        item.addActionListener(actionListener);
        menu.add(item);
        
        ActionListener actionListener2 = new PopupActionListener2();
        JMenuItem item2 = new JMenuItem("Input a Discount");
        item2.addActionListener(actionListener2);
        menu.add(item2);
        
        //for centered frame////
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);   
        //
        
    }
    
     //**************************************************************************//
    
    class PopupActionListener implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            //jLabel6
            
            String string3 = jLabel6.getText();
            String[] parts3 = string3.split(" ");
            String p1 = parts3[0]; // 004
            String p2 = parts3[1]; // 034556
            
//            double discount = Double.parseDouble(p2);
//            double discounted = discount - 200;
            
            int discountSrvAcq = Integer.parseInt(jLabel7.getText()) - 200;
            
            Locale locale = new Locale("ph", "PH");      
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
            jLabel6.setText(currencyFormatter.format(discountSrvAcq));
            jLabel17.setText("Discounted the Doctor's Fee");
            jLabel7.setText(Integer.toString(discountSrvAcq));
            sumupTotal();
        }
    }
    
    //**************************************************************************//
   
    
    class PopupActionListener2 implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            

            
            String amount = JOptionPane.showInputDialog(null, "Input an amount");
            
            if(!amount.matches("^[a-zA-Z]+$")){
                
                if(Integer.parseInt(amount)>Integer.parseInt(jLabel7.getText())){
                    JOptionPane.showMessageDialog(null, "Discount amount error\nAmount is bigger");
                }else{
                    int discount = Integer.parseInt(amount);
                    int answer = Integer.parseInt(jLabel7.getText())-discount;

                    Locale locale = new Locale("ph", "PH");      
                    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
                    jLabel6.setText(currencyFormatter.format(answer));
                    jLabel17.setText("Discounted with PHP "+amount);
                     jLabel7.setText(Integer.toString(answer));
                     sumupTotal();
                }
                
            }else{
                 JOptionPane.showMessageDialog(null, "You cannot add a string");
            }

            
        }
    }

    //**************************************************************************//
    
    public void date(){
//        pid_bill.setVisible(true);
//        oid_bill.setVisible(true);
        ////date here//////
        JCalendar dateset = new JCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
        String dob = dateFormat.format(dateset.getDate());
        date_today.setText(dob);
        date.setText(dob);
        ////date here/////
    }
    
    //**************************************************************************//

    
    //**************************************************************************//
    
    public void fillCB(){
        try{
            
            String putincb = "select * from owner";
            ps = conn_db.prepareStatement(putincb);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getlname = rs.getString("last_name");
                String getfname = rs.getString("first_name");
                String getmname = rs.getString("middle_name");
                
                String putaltogether = getlname +", "+ getfname+" "+getmname;

            }
            
        }catch(Exception e){     
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //**************************************************************************//
    
    public void getOwnerID(){
        try{
            
            String sql = "select * from owner where first_name ='"+searchfname+"' and last_name ='"+searchlname+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getID = rs.getString("owner_id");
                oid_bill.setText(getID);
            }
            
        }catch(Exception e){     
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    //**************************************************************************//
    
    public void showAcquired(){
        origPriceForDiscount = 0;
        DefaultTableModel dd=(DefaultTableModel) prices.getModel();
        int rows = dd.getRowCount(); 
        ////clearJlistCode
        for(int i = rows - 1; i >=0; i--)
        {
           dd.removeRow(i); 
        }
        ////clearJlistCode
        try{
            String sql = "select * from services_acquired where services_acquired.pet_id = '"+pid_bill.getText()+"' and services_acquired.status = 'ONGOING'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getserviceName = rs.getString("acq_name");
                String getprices = rs.getString("acq_price");
                
                DefaultTableModel dm=(DefaultTableModel) prices.getModel();
                dm.addRow(new Object[]{getprices});

                DefaultTableModel model=(DefaultTableModel) acqruired_services_table.getModel();
                model.addRow(new Object[]{getserviceName, getprices});
            }
            
        }catch(Exception e){     
            JOptionPane.showMessageDialog(null, e);
        }
        
        //***********************************************************************************************************************************************//
    
    }
    
    //**************************************************************************//
    
    public void getPetName(){
        try{
            String sql = "select * from pet where pet_id='"+pid_bill.getText()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getName = rs.getString("name");
                pet_name.setText(getName);
            }
            
        }catch(Exception e){     
            JOptionPane.showMessageDialog(null, e);
        }
    }

    
    public void sumUpPrice(){
        DefaultTableModel dm=(DefaultTableModel) prices.getModel();
        
        int size = dm.getRowCount();
        int f = size-1;
                
        for(int x=0; x<=f; x++){
            String price = prices.getValueAt(x, 0).toString();

            int iprice = Integer.parseInt(price);
            
            BigDecimal iprice2 = new BigDecimal(price);
            
            originalprice = originalprice.add(iprice2);
            
            origPriceForDiscount = origPriceForDiscount + iprice;

            Locale locale = new Locale("ph", "PH");      
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
            jLabel6.setText(currencyFormatter.format(originalprice));
        }
        BigDecimal fee = new BigDecimal("200");
        BigDecimal addedfee = originalprice.add(fee);
         Locale locale = new Locale("ph", "PH");      
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
            jLabel6.setText(currencyFormatter.format(addedfee));
            jLabel17.setVisible(true);
            jLabel17.setText("Charged with Doctor's Fee");
            jLabel7.setText(addedfee.toString());
    }
    
    public void viewItemCharged(){
        DefaultTableModel dm=(DefaultTableModel) charged_item_table.getModel();
        int rowCount = dm.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            dm.removeRow(i);
        }
        try{
            String sql = "SELECT DISTINCT item_charged.item_id, item_price.price, item.name, item_charged.quantity FROM item_price INNER JOIN item_charged ON item_price.item_id = item_charged.item_id \n" +
                         "INNER JOIN item ON item_charged.item_id = item.item_id\n" +
                         "WHERE item_charged.pet_id = '"+pid_bill.getText()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getName = rs.getString("item.name");
                String getPrice = rs.getString("item_price.price");
                String getQty = rs.getString("item_charged.quantity");

                dm.addRow(new Object[]{getName,getPrice,getQty});
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void viewItemChargedOnTable(){
        try{
            String sql = "select distinct item_charged.day, item_charged.month, item_charged.year, item.name, item_price.price, item_charged.quantity from item_charged INNER JOIN item ON item.item_id = item_charged.item_id INNER JOIN item_price ON item_price.item_id = item_charged.item_id where pet_id='"+pid_bill.getText()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getname = rs.getString("item.name");
                String getprice = rs.getString("item_price.price");
                String getqty = rs.getString("item_charged.quantity");
                
                DefaultTableModel model=(DefaultTableModel) charged_item_table.getModel();
                model.addRow(new Object[]{getname, getprice, getqty});
                
                DefaultTableModel modelx=(DefaultTableModel) prices_charged_items.getModel();
                modelx.addRow(new Object[]{getprice});
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void sumUpPriceItemCharged(){
        DefaultTableModel dmx=(DefaultTableModel) prices_charged_items.getModel();
        DefaultTableModel dmxf=(DefaultTableModel) jTable1.getModel();
        int size = dmx.getRowCount();
        int f = size-1;
        
        for(int x=0; x<=f; x++){
            String price = prices_charged_items.getValueAt(x, 0).toString();
            double dprice = Double.parseDouble(price);
            
            BigDecimal bdprice = new BigDecimal(price);
            
            String quantitys = charged_item_table.getValueAt(x, 2).toString();
            double dqty = Double.parseDouble(quantitys);
            
            BigDecimal bdqty = new BigDecimal(quantitys);
            
            bdcontPrice2 = bdcontPrice2.add(bdprice).multiply(bdqty);
            
            dmxf.addRow(new Object[]{bdcontPrice2});
            
            bdcontPrice2 = new BigDecimal("0");
            
            contPrice2 = contPrice2 + dprice * dqty;
            
        }
        
        int size2 = dmxf.getRowCount();
        int f2 = size2-1;
        
        for(int x=0; x<=f2; x++){
            BigDecimal itempricex = new BigDecimal(jTable1.getValueAt(x, 0).toString());
            
            itemChargedPrice = itemChargedPrice.add(itempricex);
            
            Locale locale = new Locale("ph", "PH");      
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
            jLabel12.setText(currencyFormatter.format(itemChargedPrice));
        }
    }
    
    public void sumupTotal(){
        double qty = Integer.parseInt(jLabel7.getText())+contPrice2;
        BigDecimal jlabel7 = new BigDecimal(jLabel7.getText());
        BigDecimal bdqty = jlabel7.add(itemChargedPrice);
        
        forchange = (int) (Integer.parseInt(jLabel7.getText())+contPrice2);
        
        bdforchange = bdqty;
        
        amountDue = bdqty.toString();
        
        Locale locale = new Locale("ph", "PH");      
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        jLabel9.setText(currencyFormatter.format(bdqty));
        
    }
    
    public void Savebill(){
        String string3 = date.getText();
        String[] parts3 = string3.split(" ");
        String p1 = parts3[0]; // 004
        String p2 = parts3[1]; // 034556
        String p3 = parts3[2]; // 034556

        String day = p1;
        String month = p2;
        String year = p3;
        try{
            String sql3 = "insert into billing (pet_id, total_charged_item, total_services_acquired, bill, day, month, year)values(?,?,?,?,?,?,?)";
            ps=conn_db.prepareStatement(sql3);
            ps.setString(1, pid_bill.getText());
            ps.setString(2, jLabel12.getText().toUpperCase());
            ps.setString(3, jLabel6.getText().toUpperCase());
            ps.setString(4, jLabel9.getText().toUpperCase());
            ps.setString(5, day);
            ps.setString(6, month.toUpperCase());
            ps.setString(7, year);
            ps.execute();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e) ;
        }
    }
    
    public void getBillingID(){
        try{
            String sql33 = "select * from billing where (pet_id='"+pid_bill.getText()+"') and (total_charged_item = '"+jLabel12.getText()+"' and total_services_acquired = '"+jLabel6.getText()+"')";
            ps = conn_db.prepareStatement(sql33);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String bid = rs.getString("billing_id");
                billingID = bid;
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e) ;
        }
    }
    
    public void savePayment(){
        String string3 = date.getText();
        String[] parts3 = string3.split(" ");
        String p1 = parts3[0]; // 004
        String p2 = parts3[1]; // 034556
        String p3 = parts3[2]; // 034556

        String day = p1;
        String month = p2;
        String year = p3;
        try{
            String sql3 = "insert into payment (pet_id, billing_id, amount_paid, pay_change, balance, day, month, year)values(?,?,?,?,?,?,?,?)";
            ps=conn_db.prepareStatement(sql3);
           
            ps.setString(1, pid_bill.getText());
            ps.setString(2, billingID);

            int qty = Integer.parseInt(jTextField1.getText());
            Locale locale = new Locale("ph", "PH");      
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

            ps.setString(3, currencyFormatter.format(qty).toUpperCase());
            ps.setString(4, change.getText().toUpperCase());
            ps.setString(5, balance.getText().toUpperCase());
            ps.setString(6, day);
            ps.setString(7, month.toUpperCase());
            ps.setString(8, year);
            ps.execute();
            
            audit_trail au = new audit_trail();
            au.action_type = "Saved payment information by "+ pet_name.getText();
            au.module_name = "Pet Chart";
            au.saveAuditTRail();
            
            customMessage_Screen cm = new customMessage_Screen();
            cm.msg.setText("Payment has been successfully stored");
            cm.setVisible(true);

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e) ;
        }
    }
    
    public void discountdoctorfee(){
        String string3 = jLabel6.getText();
        String[] parts3 = string3.split(" ");
        String p1 = parts3[0]; // 004
        String p2 = parts3[1]; // 034556
            
        int discountSrvAcq = Integer.parseInt(jLabel7.getText()) - 200;
            
        Locale locale = new Locale("ph", "PH");      
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        jLabel6.setText(currencyFormatter.format(discountSrvAcq));
        jLabel17.setText("Discounted the Doctor's Fee");
        jLabel7.setText(Integer.toString(discountSrvAcq));
        sumupTotal();
    }
    
    public void rooback1(){
        String string3 = jLabel6.getText();
        String[] parts3 = string3.split(" ");
        String p1 = parts3[0]; // 004
        String p2 = parts3[1]; // 034556
            
        int discountSrvAcq = Integer.parseInt(jLabel7.getText()) + 200;
            
        Locale locale = new Locale("ph", "PH");      
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        jLabel6.setText(currencyFormatter.format(discountSrvAcq));
        jLabel17.setText("Charged with Doctor's Fee");
        jLabel7.setText(Integer.toString(discountSrvAcq));
        sumupTotal();
    }
    
    public void discountamount(){
        try{
            String amount = JOptionPane.showInputDialog(null, "Input an amount");
            
            if(!amount.matches("^[a-zA-Z]+$")){
                savelastinput = amount;
                if(Integer.parseInt(amount)>Integer.parseInt(amountDue)){
                    JOptionPane.showMessageDialog(null, "Amount Discount is larger than the Amount Due", "Validation", JOptionPane.ERROR_MESSAGE);
                    dispose();    
                    bill b = new bill();
                    b.setVisible(true);
                    
                }else{
                    int discount = Integer.parseInt(amount);
                    int answer = Integer.parseInt(jLabel7.getText())-discount;

                    Locale locale = new Locale("ph", "PH");      
                    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
                    //jLabel6.setText(currencyFormatter.format(answer));
                    jLabel17.setText("Discounted with PHP "+amount);
                    jLabel7.setText(Integer.toString(answer));
                     sumupTotal();
                }
                
            }else{
                 JOptionPane.showMessageDialog(null, "You cannot add a string");
            }
        }catch(Exception e){
            //JOptionPane.showMessageDialog(null, e);
            jRadioButton1.setSelected(false);
        }
    }
    
    public void rollback2(){

                    int discount = Integer.parseInt(savelastinput);
                    int answer = Integer.parseInt(jLabel7.getText())+discount;

                    Locale locale = new Locale("ph", "PH");      
                    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
                    jLabel6.setText(currencyFormatter.format(answer));
                    jLabel17.setText("Charged with Doctor's Fee");
                     jLabel7.setText(Integer.toString(answer));
                     sumupTotal();

    }
    
    public void removeFromServicesAquired(){
        int size = acqruired_services_table.getRowCount();//
        int f = size - 1;

        for (int x = 0; x <= f; x++) {
            
            String string = acqruired_services_table.getValueAt(x, 0).toString();
            
            try{
            String sql="UPDATE services_acquired SET `status` = ? where acq_name = '"+string+"' and pet_id = '"+petid+"'";
            ps=conn_db.prepareStatement(sql);
            
            ps.setString(1, "CANCELLED");
            ps.executeUpdate();
            
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    public void removeFromItemCharged(){
             try {
                    String sql = "DELETE  FROM item_charged WHERE pet_id = '"+petid+"'";
                    ps = conn_db.prepareStatement(sql);
                    ps.executeUpdate();
                }catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
             
             dispose();
             new bill().setVisible(true);
    }
    
    public void deductItemQty(){
        int size = charged_item_table.getRowCount();//
        int f = size - 1;
        
        int origQty;
        int itemID;

        for (int x = 0; x <= f; x++) {
            try{
             String sql = "SELECT * FROM item WHERE name = '"+charged_item_table.getValueAt(x, 0).toString()+"'";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             
             while(rs.next()){
                 String getQty = rs.getString("quantity");
                 String getID = rs.getString("item_id");
                 origQty = Integer.parseInt(getQty);
                 itemID = Integer.parseInt(getID);
                 
                 int itemqty = Integer.parseInt(charged_item_table.getValueAt(x, 2).toString());
                 int ans = origQty-itemqty;
                 
                 /////////////////////////////////////////////
                 try{
                    String sql1 ="UPDATE `item` SET `quantity` = ? where `name` = ?";
                    ps=conn_db.prepareStatement(sql1);
                    ps.setInt(1, ans);
                    ps.setString(2, charged_item_table.getValueAt(x, 0).toString());
                    ps.executeUpdate();
            
                 }catch(Exception e){
                     JOptionPane.showMessageDialog(null, e);
                 }
                 //////////////////////////////////////////////
   
                 String string = today_date.getText();
                 String[] parts = string.split(" ");
                 String p1 = parts[0]; // 004
                 String p2 = parts[1]; // 034556
                 String p3 = parts[2]; // 034556

                 day = p1;
                 month = p2.toUpperCase();
                 year = p3;

                    try{
                    String sql3 = "Insert into item_out (`item_id`,`quantity`,`day`,`month`,`year`) values (?,?,?,?,?)";
                    ps=conn_db.prepareStatement(sql3);
                    ps.setString(1, Integer.toString(itemID));
                    ps.setString(2, charged_item_table.getValueAt(x, 2).toString());
                    ps.setString(3, day);
                    ps.setString(4, month);
                    ps.setString(5, year);
                    ps.execute();

                    }catch(Exception e){

                        JOptionPane.showMessageDialog(null, e);

                    }
                 /////////////////////////////////////////////
             }
             
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
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

        jLabel8 = new javax.swing.JLabel();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        charged_item_table = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        acqruired_services_table = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        date_today = new javax.swing.JLabel();
        pid_bill = new javax.swing.JLabel();
        oid_bill = new javax.swing.JLabel();
        pet_name = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        prices = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        prices_charged_items = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        quantity = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        balance = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        change = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jLabel8.setText("jLabel8");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Client(s) Bill");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        charged_item_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Php", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(charged_item_table);
        charged_item_table.getColumnModel().getColumn(0).setResizable(false);
        charged_item_table.getColumnModel().getColumn(0).setPreferredWidth(180);
        charged_item_table.getColumnModel().getColumn(1).setResizable(false);
        charged_item_table.getColumnModel().getColumn(1).setPreferredWidth(50);
        charged_item_table.getColumnModel().getColumn(2).setPreferredWidth(50);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 330, 130));

        acqruired_services_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Php"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(acqruired_services_table);
        acqruired_services_table.getColumnModel().getColumn(0).setResizable(false);
        acqruired_services_table.getColumnModel().getColumn(0).setPreferredWidth(150);
        acqruired_services_table.getColumnModel().getColumn(1).setResizable(false);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 330, 130));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Total");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 180, 40, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Php 000.00");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, 170, 30));

        date_today.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        date_today.setForeground(new java.awt.Color(101, 47, 47));
        date_today.setText("jLabel8");
        jPanel1.add(date_today, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));
        jPanel1.add(pid_bill, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 16, -1, 20));

        oid_bill.setText("1");
        jPanel1.add(oid_bill, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, 0, 30));

        pet_name.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        pet_name.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(pet_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, 170, 30));

        prices.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "php"
            }
        ));
        jScrollPane5.setViewportView(prices);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 120, 0, 80));

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Pet Name:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 140, 30));

        date.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        date.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 140, 160, 20));

        prices_charged_items.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Price"
            }
        ));
        jScrollPane6.setViewportView(prices_charged_items);

        jPanel1.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 520, 140, 0));

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Date:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 120, 60, 20));

        quantity.setText("jLabel14");
        jPanel1.add(quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 0, 0));

        jLabel14.setText("acq_id");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, -1, 0));

        jLabel15.setText("acq_id");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, -1, 0));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Acquired Service(s)");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, -1, -1));

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Cash");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 140, 20));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Total");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Php 000.00");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 150, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 153, 0));
        jLabel17.setText("Charged with Doctor's Fee");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Balance");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, 60, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Php 000.00");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 290, 30));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 140, -1));

        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Charged Item(s)");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 120, -1));

        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Amount Due");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 100, -1));

        balance.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        balance.setForeground(new java.awt.Color(255, 255, 255));
        balance.setText("Php 000.00");
        jPanel2.add(balance, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, 150, -1));

        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Change Due");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 90, -1));

        change.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        change.setForeground(new java.awt.Color(255, 255, 255));
        change.setText("Php 000.00");
        jPanel2.add(change, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 150, -1));

        jButton2.setText("Check out");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 140, -1));

        jButton3.setText("Enter");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 100, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, 330, 430));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(101, 47, 47));
        jLabel19.setText("Acquired Service(s)");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 140, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(101, 47, 47));
        jLabel20.setText("Charged Item(s)");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 150, -1));

        jLabel7.setText("jLabel7");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, 80, 20));

        jRadioButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton1.setForeground(new java.awt.Color(101, 47, 47));
        jRadioButton1.setText("Discount an amount");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, -1, -1));

        jRadioButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButton2.setForeground(new java.awt.Color(101, 47, 47));
        jRadioButton2.setText("Discount Doctor's Fee");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 520, 170, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 776, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Are you sure you want to close?",
            "Closing View Bill", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            this.dispose();
        }
        else {

            System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
            return;
        }
    }//GEN-LAST:event_formWindowClosing

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        if(!jTextField1.getText().matches("^[a-zA-Z]+$")){
                if(jTextField1.getText().equals("")){
                    balance.setText("Php 000.00");
                    change.setText("Php 000.00");
                }else{
                    
                }
            }
            else{
                 JOptionPane.showMessageDialog(null, "You cannot add a string");
            }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
          if(jTextField1.getText().equals("") || jLabel9.getText().equals("PHP 0.00")){
                JOptionPane.showMessageDialog(null, "Error", "Billing Error Message", JOptionPane.ERROR_MESSAGE);
            }else{
                Savebill();
                getBillingID();
                savePayment();
                removeFromServicesAquired();
                deductItemQty();
                removeFromItemCharged();
            }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        double discount = Double.parseDouble(jTextField1.getText());//Integer.parseInt();
        double amountpay = forchange;
        double answer = amountpay-discount;
        
        BigDecimal paidamount = bdforchange;
        BigDecimal discountpay = new BigDecimal(jTextField1.getText());
        BigDecimal ans = paidamount.subtract(discountpay);
                
        if(answer < 0){
           Locale locale = new Locale("ph", "PH");      
           NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
           //int x = (int) Math.abs(ans);
           BigDecimal x = ans.abs();
           change.setText(currencyFormatter.format(x));
           balance.setText("Php 000.00");
        }else{
            Locale locale = new Locale("ph", "PH");      
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
            balance.setText(currencyFormatter.format(ans));
            change.setText("Php 000.00");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        double discount = Double.parseDouble(jTextField1.getText());//Integer.parseInt();
        double amountpay = forchange;
        double answer = amountpay-discount;
        
        BigDecimal paidamount = bdforchange;
        BigDecimal discountpay = new BigDecimal(jTextField1.getText());
        BigDecimal ans = paidamount.subtract(discountpay);
                
        if(answer < 0){
           Locale locale = new Locale("ph", "PH");      
           NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
           //int x = (int) Math.abs(ans);
           BigDecimal x = ans.abs();
           change.setText(currencyFormatter.format(x));
           balance.setText("Php 000.00");
        }else{
            Locale locale = new Locale("ph", "PH");      
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
            balance.setText(currencyFormatter.format(ans));
            change.setText("Php 000.00");
        }
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        if(jRadioButton2.isSelected()){
            discountdoctorfee();
            jRadioButton1.setSelected(false);
        }else{
            rooback1();
        }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        if(jRadioButton1.isSelected()){
            jRadioButton2.setSelected(false);
            discountamount();
        }else{
            rollback2();
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new bill().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable acqruired_services_table;
    public static javax.swing.JLabel balance;
    public static javax.swing.JLabel change;
    public static javax.swing.JTable charged_item_table;
    public static javax.swing.JLabel date;
    public static javax.swing.JLabel date_today;
    public static javax.swing.JButton jButton2;
    public static javax.swing.JButton jButton3;
    public static javax.swing.JLabel jLabel10;
    public static javax.swing.JLabel jLabel11;
    public static javax.swing.JLabel jLabel12;
    public static javax.swing.JLabel jLabel13;
    public static javax.swing.JLabel jLabel14;
    public static javax.swing.JLabel jLabel15;
    public static javax.swing.JLabel jLabel16;
    public static javax.swing.JLabel jLabel17;
    public static javax.swing.JLabel jLabel19;
    public static javax.swing.JLabel jLabel20;
    public static javax.swing.JLabel jLabel21;
    public static javax.swing.JLabel jLabel22;
    public static javax.swing.JLabel jLabel23;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel4;
    public static javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabel6;
    public static javax.swing.JLabel jLabel7;
    public static javax.swing.JLabel jLabel8;
    public static javax.swing.JLabel jLabel9;
    public static javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel2;
    public static javax.swing.JPopupMenu jPopupMenu1;
    public static javax.swing.JRadioButton jRadioButton1;
    public static javax.swing.JRadioButton jRadioButton2;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JScrollPane jScrollPane5;
    public static javax.swing.JScrollPane jScrollPane6;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JTextField jTextField1;
    public static javax.swing.JLabel oid_bill;
    public static javax.swing.JLabel pet_name;
    public static javax.swing.JLabel pid_bill;
    public static javax.swing.JTable prices;
    public static javax.swing.JTable prices_charged_items;
    public static javax.swing.JLabel quantity;
    // End of variables declaration//GEN-END:variables
}
