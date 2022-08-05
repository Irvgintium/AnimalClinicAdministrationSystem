/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emr.jpanels;

import audit_trailing.audit_trail;
import com.toedter.calendar.JCalendar;
import emr.jpanels.SQLClass.dashboard_codes;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import login.connection_db;
import net.proteanit.sql.DbUtils;
import other.features.jpanels.colors;
import other.features.jpanels.pet_breed_manage;
import other.features.jpanels.pet_type_manage;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author IrvGu
 */
public class new_pet extends javax.swing.JPanel {
    Connection conn_db = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    String currentDate;
    
    String day;
    String month;
    String year;
    
    String day2;
    String month2;
    String year2;
    
    String birth_date = "";
    String gender = "";
    
    String ownerid;
    String petid;
    String getDate;
    /**
     * Creates new form new_pet
     */
    public new_pet() {
        initComponents();
        conn_db = connection_db.ConnectDB();
        date();
        viewType();
        jButton5.setEnabled(false);
        v.setVisible(true);
        formatTable();
        ownerid = owner_id_for_pet.getText();
    }
    
    public void formatTable(){
        try{
            String sql = "SELECT name as Name, type as Type, breed as Breed FROM `pet` where owner_id =''";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs)); 
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }

    public void date(){
        ////date here//////
        JCalendar dateset = new JCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
        String dob = dateFormat.format(dateset.getDate());
        currentDate = dob;
        ////date here/////
    }
    
    public void autoViewPets(){
        DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
        int rows1 = model1.getRowCount();
        for (int i = rows1 - 1; i >= 0; i--) {
            model1.removeRow(i);
        }
        DefaultTableModel dm=(DefaultTableModel) jTable1.getModel();
        try{
            String sql = "SELECT pet_id as ID, name as Name, type as Type, breed as Breed FROM `pet` where owner_id ='"+ownerid+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs)); 
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    public void getAge(){//Year/Month/Day
        int calmonth = 12;
        int calmonth2 = 12;
        
        String string3 = currentDate;//Current Date
        String[] parts3 = string3.split(" ");
        String p1 = parts3[0];
        String p2 = parts3[1];
        String p3 = parts3[2];
    
        day = p1;int d1 = Integer.parseInt(day);
        month = p2;int m1;
        year = p3;int y1 = Integer.parseInt(year);
        
        ///////
        
        /////get current month
        
        if(month.equals("Jan")){
            calmonth = calmonth - 11;
        }
        
        if(month.equals("Feb")){
            calmonth = calmonth - 10; 
        }
        
        if(month.equals("Mar")){
            calmonth = calmonth - 9;
        }
        
        if(month.equals("Apr")){
            calmonth = calmonth - 8;
        }
        
        if(month.equals("May")){
            calmonth = calmonth - 7;
        }
        
        if(month.equals("Jun")){
            calmonth = calmonth - 6;
        }
        
        if(month.equals("Jul")){
            calmonth = calmonth - 5;
        }
        
        if(month.equals("Aug")){
            calmonth = calmonth - 4;
        }
        
        if(month.equals("Sep")){
            calmonth = calmonth - 3;
        }
        
        if(month.equals("Oct")){
            calmonth = calmonth - 2;
        }
        
        if(month.equals("Nov")){
            calmonth = calmonth - 1;
        }
        
        if(month.equals("Dec")){

        }
        ////
        
        ///////
        
        String string4 = birth_date;//Birth Date
        String[] parts4 = string4.split(" ");
        String x1 = parts4[0];
        String x2 = parts4[1];
        String x3 = parts4[2];
    
        day2 = x1;int d2 = Integer.parseInt(day2);
        month2 = x2;int m2;
        year2 = x3;int y2 = Integer.parseInt(year2);
        
        ////////
        
        /////get current month
        
        if(month2.equals("Jan")){
            calmonth2 = calmonth2 - 11; 
        }
        
        if(month2.equals("Feb")){
            calmonth2 = calmonth2 - 10;
        }
        
        if(month2.equals("Mar")){
            calmonth2 = calmonth2 - 9;  
        }
        
        if(month2.equals("Apr")){
            calmonth2 = calmonth2 - 8; 
        }
        
        if(month2.equals("May")){
            calmonth2 = calmonth2 - 7;
        }
        
        if(month2.equals("Jun")){
            calmonth2 = calmonth2 - 6;
        }
        
        if(month2.equals("Jul")){
            calmonth2 = calmonth2 - 5;
        }
        
        if(month2.equals("Aug")){
            calmonth2 = calmonth2 - 4; 
        }
        
        if(month2.equals("Sep")){
            calmonth2 = calmonth2 - 3;
        }
        
        if(month2.equals("Oct")){
            calmonth2 = calmonth2 - 2;
        }
        
        if(month2.equals("Nov")){
            calmonth2 = calmonth2 - 1;
        }
        
        if(month2.equals("Dec")){
           
        }
        ////

        ///////
        
        int yearAge = y1-y2;
        
        
        
        int monthAge = calmonth - calmonth2;
        
        if(monthAge<0){
            monthAge = 0;
        }
        
        int dayAge = d1-d2;
        
        if(dayAge<0){
            dayAge = 0;
        }
        
        if(monthAge == 0 && dayAge == 0){
            JOptionPane.showMessageDialog(null, "Age is not acceptable!");
        }else{
        //age.setText(yearAge +" yr(s)");
        }
    }
    
    public void viewType(){
        try{
            t.removeAllItems();
        }catch(Exception e){
            //
        }
        
        try{
            String sql = "SELECT * FROM `pet_type`";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getType = rs.getString("pet_type_name");
                t.addItem(getType);
            }

        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void viewBreed(){
        try{
           breed.removeAll(); 
        }catch(Exception e){
            //
        }
        DefaultListModel<String> model = new DefaultListModel();
        breed.setModel(model);
        int rows = model.size(); 
        for(int i = rows - 1; i >=0; i--)
        {
           model.removeElementAt(i); 
        }
        DefaultListModel dimx = new DefaultListModel();
        try{
            String sql = "SELECT * FROM `animal_breed` where pet_type ='"+pet_type.getText()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getType = rs.getString("breed");
                dimx.addElement(getType);
                breed.setModel(dimx);
            }

        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    public void resetFields(){
        DefaultListModel<String> model = new DefaultListModel();
        breed.setModel(model);
        int rows = model.size(); 
        for(int i = rows - 1; i >=0; i--)
        {
           model.removeElementAt(i); 
        }
        pet_breed.setText("Pet Breed");
        name.setText("*Pet's Name");
        name.setForeground(new Color(51,102,0));
        age.setText("*Age");
        age.setForeground(new Color(51,102,0));
        color.setText("*Color");
        color.setForeground(new Color(51,102,0));
        gender = "";
        jButton2.setEnabled(true);
        jButton5.setEnabled(false);
    }
    
    
    public void savePet(){
        String string = birth_date;
        String[] parts = string.split(" ");
        String day = parts[0]; // 004
        String month = parts[1]; // 034556
        String year = parts[2]; // 034556
        
       try{
                String sql = "Insert into pet (`owner_id`,`name`,`type`,`breed`,`gender`,`age`,`color`,`b_day`,`b_month`,`b_year`) values (UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?))";
                ps=conn_db.prepareStatement(sql);
                ps.setString(1, ownerid);
                ps.setString(2, name.getText());
                String upp1 = name.getText().toUpperCase();
                ps.setString(3, pet_type.getText());
                String upp2 = pet_type.getText().toUpperCase();
                ps.setString(4, pet_breed.getText());
                String upp3 = pet_breed.getText().toUpperCase();
                ps.setString(5, gender);
                ps.setString(6, age.getText());
                ps.setString(7, color.getText());
                ps.setString(8, day);
                ps.setString(9, month);
                ps.setString(10, year);
                ps.execute();

                DefaultTableModel model=(DefaultTableModel) jTable1.getModel();

                model.addRow(new Object[]{upp1,upp2,upp3});

                JOptionPane.showMessageDialog(null, "Profile for "+name.getText().toUpperCase()+" has been successfully stored");
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    public void viewPet(){
        DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
        petid = model.getValueAt(jTable1.getSelectedRow(), 0).toString();
        try{
            String sql = "SELECT * FROM `pet` where  pet_id = '"+model.getValueAt(jTable1.getSelectedRow(), 0)+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getName = rs.getString("name");
                name.setText(getName);
                String getType = rs.getString("type");
                pet_type.setText(getType);
                String getBreed = rs.getString("breed");
                pet_breed.setText(getBreed);
                String getGender = rs.getString("gender");
                gender=getGender;
                
                if(gender.equals("MALE")){
                    jRadioButton1.setSelected(true);
                    jRadioButton2.setSelected(false);
                }else{
                    jRadioButton1.setSelected(false);
                    jRadioButton2.setSelected(true);
                }
                
                String getAge = rs.getString("age");
                age.setText(getAge);
                String getColor = rs.getString("color");
                color.setText(getColor);
                
                String getDay = rs.getString("b_day");
                String getMonth = rs.getString("b_month");
                String getYear = rs.getString("b_year");
                getDate = getDay+" "+getMonth+" "+getYear;
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }

    }
    
    
    public void updateBreed(){
        
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("d MMM yyyy");
        String dob2 = dateFormat2.format(xad.getDate());
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
            String sql = "UPDATE pet SET name = UPPER(?), type = UPPER(?), breed = UPPER(?), gender = UPPER(?), age = UPPER(?), color = UPPER(?), b_day = UPPER(?), b_month = UPPER(?), b_year = UPPER(?) where pet_id ='"+petid+"'";
            ps=conn_db.prepareStatement(sql);
            
            ps.setString(1, name.getText());
            ps.setString(2, pet_type.getText());
            ps.setString(3, pet_breed.getText());
            ps.setString(4, gender);
            ps.setString(5, age.getText());
            ps.setString(6, color.getText());
            ps.setString(7, day);
            ps.setString(8, month);
            ps.setString(9, year);
            ps.executeUpdate();
            
            String upp1 = name.getText().toUpperCase();
            String upp2 = pet_type.getText().toUpperCase();
            String upp3 = pet_breed.getText().toUpperCase();
            
            DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
            
            model.setValueAt(upp1, jTable1.getSelectedRow(), 0);
            model.setValueAt(upp2, jTable1.getSelectedRow(), 1);
            model.setValueAt(upp3, jTable1.getSelectedRow(), 2);

            JOptionPane.showMessageDialog(null, "Profile for "+name.getText().toUpperCase()+" has been successfully updated");

        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void getPetID(){
        try{
            String putincb = "select * from pet where name ='"+name.getText()+"' and owner_id = '"+ownerid+"'";
            ps = conn_db.prepareStatement(putincb);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getid = rs.getString("pet_id");
                idpet.setText(getid);
            }
            
        }catch(Exception e){     
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void getOwnerID(){
        try{
            String sql = "SELECT owner_id, CONCAT_WS(' ', owner.unit_number, owner.house_street_number, owner.street_name, owner.purok, owner.brgy_name, owner.city, owner.province, owner.zip_code) AS `Address`,CONCAT_WS('',owner.last_name, ', ', owner.first_name, ' ', owner.middle_name, ' ', owner.suffix_name) AS `Name` FROM `owner` HAVING `Name` LIKE '"+jTextField1.getText()+"%'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
             String ownerID = rs.getString("owner_id");
             String ownerName= rs.getString("Name");
             owner_name_for_pet.setText(ownerName);
             ownerid = ownerID;
            }
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
    }
    
    
    //+", "+monthAge+" month(s)"+" and "+dayAge+" day(s) old"

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        owner_name_for_pet = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        t = new javax.swing.JComboBox();
        v = new javax.swing.JPanel();
        owner_id_for_pet = new javax.swing.JLabel();
        pet_type = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pet_breed = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        idpet = new javax.swing.JLabel();
        namepet = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        age = new javax.swing.JTextField();
        color = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        breed = new javax.swing.JList();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jTextField1 = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        xad = new com.toedter.calendar.JDateChooser();

        setForeground(new java.awt.Color(51, 102, 0));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(97, 60, 60));
        jLabel1.setText("Breed");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, -1, -1));

        owner_name_for_pet.setFont(new java.awt.Font("Segoe UI Light", 1, 24)); // NOI18N
        owner_name_for_pet.setForeground(new java.awt.Color(67, 29, 29));
        add(owner_name_for_pet, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 370, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(97, 60, 60));
        jLabel3.setText("Owner:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, -1, -1));

        t.setForeground(new java.awt.Color(51, 51, 51));
        t.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tMouseExited(evt);
            }
        });
        t.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tActionPerformed(evt);
            }
        });
        add(t, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 200, 200, -1));

        v.setBackground(new java.awt.Color(153, 153, 153));
        v.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        owner_id_for_pet.setText("19");
        v.add(owner_id_for_pet, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 10, 110, -1));

        pet_type.setText("Pet Type");
        v.add(pet_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 110, -1));

        jLabel6.setText("Owner ID");
        v.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        pet_breed.setText("Pet Breed");
        v.add(pet_breed, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, -1));

        jLabel9.setText("Pet Type");
        v.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jLabel10.setText("Pet Breed");
        v.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        idpet.setText("jLabel2");
        v.add(idpet, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        namepet.setText("jLabel2");
        v.add(namepet, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        add(v, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 10, 200, 0));

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(97, 60, 60));
        jLabel5.setText("Pet Information");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, -1, -1));

        name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        name.setForeground(new java.awt.Color(51, 102, 0));
        name.setText("*Pet's Name");
        name.setToolTipText("Pet's Name");
        name.setBorder(javax.swing.BorderFactory.createTitledBorder("Pet's Name"));
        name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nameMouseClicked(evt);
            }
        });
        name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameActionPerformed(evt);
            }
        });
        name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nameFocusLost(evt);
            }
        });
        add(name, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, 180, 50));

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(97, 60, 60));
        jLabel8.setText("Choose Pet Type:");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, -1, -1));

        jButton1.setText("Manage");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 200, 80, 20));

        age.setForeground(new java.awt.Color(51, 102, 0));
        age.setText("*Age");
        age.setBorder(javax.swing.BorderFactory.createTitledBorder("Age"));
        age.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ageActionPerformed(evt);
            }
        });
        age.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ageFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ageFocusLost(evt);
            }
        });
        age.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ageKeyTyped(evt);
            }
        });
        add(age, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 380, 210, 50));

        color.setForeground(new java.awt.Color(51, 102, 0));
        color.setText("*Color");
        color.setBorder(javax.swing.BorderFactory.createTitledBorder("Color"));
        color.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                colorMouseClicked(evt);
            }
        });
        color.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                colorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                colorFocusLost(evt);
            }
        });
        add(color, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 380, 210, 50));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Pet Type", "Breed"
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
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, 490, 190));

        jButton3.setText("Manage");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 260, 80, 20));

        jButton6.setText(">>");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 230, 50, 20));

        breed.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                breedMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(breed);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 260, 200, 70));

        jRadioButton1.setForeground(new java.awt.Color(97, 60, 60));
        jRadioButton1.setText("Male");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 450, 80, -1));

        jRadioButton2.setForeground(new java.awt.Color(97, 60, 60));
        jRadioButton2.setText("Female");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 90, -1));

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 90, 280, -1));

        jToolBar1.setRollover(true);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Clear Green Button.png"))); // NOI18N
        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton2);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Rename Document.png"))); // NOI18N
        jButton5.setText("Update");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_interface/backup_green_button.png"))); // NOI18N
        jButton7.setText("Refresh");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton7);

        add(jToolBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 50));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel2.setText("(Last , First  Middle)");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 110, 140, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Type Pet Owner Name");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 140, 30));

        xad.setBorder(javax.swing.BorderFactory.createTitledBorder("Birth Date"));
        add(xad, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 380, 170, 50));
    }// </editor-fold>//GEN-END:initComponents

    private void tActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tActionPerformed
        String getPetType = t.getSelectedItem().toString();
        if(getPetType.equals(" ")){
            JOptionPane.showMessageDialog(null, "Pet type cannot be empty!");
        }else{
            pet_type.setText(getPetType);
        }
    }//GEN-LAST:event_tActionPerformed

    private void nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameMouseClicked

    }//GEN-LAST:event_nameMouseClicked

    private void nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameActionPerformed

    private void nameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameFocusGained
        if(name.getText().equals("*Pet's Name")){
            name.setText("");
            name.setForeground(Color.BLACK);
        }else{

        }
    }//GEN-LAST:event_nameFocusGained

    private void nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameFocusLost
        if(name.getText().equals("")){
            name.setText("*Pet's Name");
            name.setForeground(new Color(51,102,0));
        }else{

        }
    }//GEN-LAST:event_nameFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new pet_type_manage().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ageKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ageKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)){
            evt.consume();
            getToolkit().beep();
        } 
    }//GEN-LAST:event_ageKeyTyped

    private void ageFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ageFocusGained
        if(age.getText().equals("*Age")){
            age.setText("");
            age.setForeground(Color.BLACK);
        }else{

        }
    }//GEN-LAST:event_ageFocusGained

    private void ageFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ageFocusLost
        if(age.getText().equals("")){
            age.setText("*Age");
            age.setForeground(new Color(51,102,0));
        }else{

        }
    }//GEN-LAST:event_ageFocusLost

    private void colorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_colorMouseClicked
        new colors().setVisible(true);
    }//GEN-LAST:event_colorMouseClicked

    private void ageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ageActionPerformed

    private void colorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_colorFocusGained
        if(color.getText().equals("*Color")){
            color.setText("");
            color.setForeground(Color.BLACK);
        }else{

        }
    }//GEN-LAST:event_colorFocusGained

    private void colorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_colorFocusLost
        if(color.getText().equals("")){
            color.setText("*Color");
            color.setForeground(new Color(51,102,0));
        }else{

        }
    }//GEN-LAST:event_colorFocusLost

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        new pet_breed_manage().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int pass = 5;
        int score = 0;
        
        String pn = name.getText();
        if(pn.equals("*Pet's Name")|| pn.equals("")){

        }else{
            score++;
        }

        String br = birth_date;
        if(br.equals("")){

        }else{
            score++;
        }

        String ag = age.getText();
        if(ag.equals("*Age")|| ag.equals("")){

        }else{
            score++;
        }

        String col = color.getText();
        if(col.equals("*Color")|| col.equals("")){

        }else{
            score++;
        }
        
        String gen = gender;
        if(gen.equals("")){

        }else{
            score++;
        }
        
        if(score == pass){
            namepet.setText(name.getText());
            savePet();
            getPetID();
            
            audit_trail au = new audit_trail();
            au.action_type = "Saved - "+name.getText().toUpperCase()+"'s information";
            au.module_name = "Client Information Entry Module";
            au.saveAuditTRail();
            dashboard_codes ss = new dashboard_codes();
            try {
                ss.starterSound();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(new_pet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Would you like to add immunization history information? or add it later?",
            "Adding Immunization History", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                
                new immunization_history().setVisible(true);
            
            }
            else {

                System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
                resetFields();
                xad.setCalendar(null);
                return;
            }
            
            
            
        }else{
            JOptionPane.showMessageDialog(null, "Empty fields");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tMouseExited
        
    }//GEN-LAST:event_tMouseExited

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        viewBreed();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void breedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_breedMouseClicked
        String getbreed = breed.getModel().getElementAt(breed.getSelectedIndex()).toString();
        pet_breed.setText(getbreed);
    }//GEN-LAST:event_breedMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        updateBreed();
        audit_trail au = new audit_trail();
        au.action_type = "Updated - "+name.getText().toUpperCase()+"'s information";
        au.module_name = "Client Information Entry Module";
        au.saveAuditTRail();
        resetFields();
        resetFields();
        xad.setCalendar(null);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        jButton5.setEnabled(true);
        jButton2.setEnabled(false);
        viewPet();
        java.util.Date date = null;
                try {
                    date = new SimpleDateFormat("d MMM yyyy").parse(getDate);
                } catch (ParseException ex) {
                    Logger.getLogger(new_pet.class.getName()).log(Level.SEVERE, null, ex);
                }
                xad.setDate(date);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        resetFields();
        autoViewPets();
        viewType();
        viewBreed();
        xad.setCalendar(null);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        
    }//GEN-LAST:event_jTable1MouseEntered

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        jRadioButton1.setSelected(true);
        jRadioButton2.setSelected(false);
        gender = "Male";
        Calendar cal = xad.getCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
        birth_date = dateFormat.format(cal.getTime()).toString();
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        jRadioButton1.setSelected(false);
        jRadioButton2.setSelected(true);
        gender = "Female";
        Calendar cal = xad.getCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
        birth_date = dateFormat.format(cal.getTime()).toString();
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        if(jTextField1.getText().equals("")){
            owner_name_for_pet.setText("");
        }else{
            getOwnerID();
            resetFields();
            autoViewPets();
            viewType();
            viewBreed();
        }
    }//GEN-LAST:event_jTextField1KeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField age;
    public static javax.swing.JList breed;
    public static javax.swing.JTextField color;
    public static javax.swing.JLabel idpet;
    public static javax.swing.JButton jButton1;
    public static javax.swing.JButton jButton2;
    public static javax.swing.JButton jButton3;
    public static javax.swing.JButton jButton5;
    public static javax.swing.JButton jButton6;
    public static javax.swing.JButton jButton7;
    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel10;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel4;
    public static javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabel6;
    public static javax.swing.JLabel jLabel8;
    public static javax.swing.JLabel jLabel9;
    public static javax.swing.JRadioButton jRadioButton1;
    public static javax.swing.JRadioButton jRadioButton2;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JTextField jTextField1;
    public static javax.swing.JToolBar jToolBar1;
    public static javax.swing.JTextField name;
    public static javax.swing.JLabel namepet;
    public static javax.swing.JLabel owner_id_for_pet;
    public static javax.swing.JLabel owner_name_for_pet;
    public static javax.swing.JLabel pet_breed;
    public static javax.swing.JLabel pet_type;
    public static javax.swing.JComboBox t;
    public static javax.swing.JPanel v;
    public static com.toedter.calendar.JDateChooser xad;
    // End of variables declaration//GEN-END:variables
}
