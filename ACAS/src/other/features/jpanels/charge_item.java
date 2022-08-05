/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package other.features.jpanels;

import com.toedter.calendar.JCalendar;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import login.connection_db;

/**
 *
 * @author IrvGu
 */
public class charge_item extends javax.swing.JFrame {
    
    Connection conn_db = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    String getitemname;
    String getitemname2;
    int getselectedindex;
    int getselectedindex2;
    String getQty;
    String itemID;
    int OrigQty = 1;
    int itemQty = 1;
    int QtyOrig;
            
    //zero qty to HA......*_*
    String getClassID;
    String getItemName;
    
    String day;
    String month;
    String year;
    
    int UpdateQty;
    
    int removeQty;
    
    String inputQty;
    
    int count=0;
    /**
     * Creates new form charge_medicine
     */
    public charge_item() {
        initComponents();
        conn_db = connection_db.ConnectDB();
        date();
        add.setEnabled(false);
        viewItemClassification();
        //for centered frame////
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);   
        //
    }
    
    public void date(){
        ////date here//////
        JCalendar dateset = new JCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
        String dob = dateFormat.format(dateset.getDate());
        date_today.setText(dob);
        ////date here/////
    }
    
     public void fillServiceList(){
        DefaultListModel dm = new DefaultListModel();
        dm.removeAllElements();
        jList1.setModel(dm);
        
        rem.setEnabled(false);
        jList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        DefaultListModel dimx = new DefaultListModel();
        try{
            
            String putincb = "select * from item";
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
     
     public void showInfo(){
        try{
            String sql = "select * from item where name='"+getitemname+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){ 
                String getqty = rs.getString("quantity");
                String getID = rs.getString("item_id");
                String getClass = rs.getString("classification_id");
                getClassID = getClass;
                String name = getitemname;
                itemID = getID;
                info.setText(name);
                info2.setText("Quantity: "+getqty);
                getQty = getqty;
            }
            
        }catch(Exception e){     
            JOptionPane.showMessageDialog(null, e);
        }
    }
     
     public void reduceItemQty(){
         int intQty = Integer.parseInt(getQty);
         int container;

         container = intQty - Integer.parseInt(inputQty);
         
         String scontainer = Integer.toString(container);
         try{
            String updatepet = "UPDATE `item` SET `quantity` = ? where `name` = ?";

            ps=conn_db.prepareStatement(updatepet);
            ps.setString(1, scontainer);
            ps.setString(2, getitemname);
            ps.executeUpdate();
            
         }catch(Exception e){     
            JOptionPane.showMessageDialog(null, e);
        }
     }
     
     public void checkZeroQty(){
         try{
             String sql = "select * from item where quantity = '0'";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
            
            while(rs.next()){ 
                String getClassid = rs.getString("classification_id");
                getClassID = getClassid;
                String getName = rs.getString("name");
                getItemName = getName;
                
                ////////add and remove
                try{
                    String sql1 = "Insert into item_zero_qty (`classification_id`,`name`,`qty`) values (?,?,?)";
                    ps = conn_db.prepareStatement(sql1);
                    ps.setString(1, getClassID);
                    ps.setString(2, getItemName);
                    ps.setInt(3, OrigQty);
                    ps.execute();
                    
                }catch(Exception e){     
                    JOptionPane.showMessageDialog(null, e);
                }
                /////////////////////////////////////////////////////////
                try{
                    String sql2 = "DELETE FROM item WHERE `item_id` =?";
                    ps=conn_db.prepareStatement(sql2);
                    ps.setString(1, itemID);
                    ps.executeUpdate();

                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            
         }catch(Exception e){     
            JOptionPane.showMessageDialog(null, e);
        }
     }
     
     public void updateItemQty(){
         try{
            String sql = "select * from item where name='"+getitemname2+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){ 
                int getqty = rs.getInt("quantity");
                String name = getitemname2;
                info.setText(name);
                info2.setText("Quantity: "+getqty);
                UpdateQty = getqty;
            }
            
        }catch(Exception e){     
            JOptionPane.showMessageDialog(null, e);
        }
         
         int subQty = UpdateQty + removeQty;
         
         try{
            String updatepet = "UPDATE `item` SET `quantity` = ? where `name` = ?";
            ps=conn_db.prepareStatement(updatepet);
            ps.setInt(1, subQty);
            ps.setString(2, getitemname2);
            ps.executeUpdate();
         }catch(Exception e){     
            JOptionPane.showMessageDialog(null, e);
        }
     }
     
     public void viewChargedItems(){
         DefaultListModel dimx = new DefaultListModel();
        try{
            String sql = "SELECT DISNTICT * FROM item_charged INNER JOIN item ON item.item_id = item_charged.item_id WHERE item_charged.pet_id = '"+petIDItemCharged.getText()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){ 
                String geacqtname = rs.getString("item.name");
                dimx.addElement(geacqtname);
                jList2.setModel(dimx);
            }
            
        }catch(Exception e){     
            JOptionPane.showMessageDialog(null, e);
        }
        
        if(jList2.getModel().getSize()==0){
            JOptionPane.showMessageDialog(null, "No aqcuired data retrieved!");
        }else{
            rem.setEnabled(true);
            jList1.setEnabled(false);
        }
     }
     
     public void addToItemCharged(){
         
         String string = date_today.getText();
         String[] parts = string.split(" ");
         day = parts[0]; // 004
         month = parts[1]; // 034556
         year = parts[2]; // 034556
         
         try{
            String sql = "insert into item_charged (`pet_id`,`item_id`,`quantity`,`day`,`month`,`year`) values (?,?,?,?,?,?)";
            ps = conn_db.prepareStatement(sql);
            ps.setString(1, petIDItemCharged.getText());
            ps.setString(2, itemID);
            ps.setInt(3, Integer.parseInt(inputQty));
            ps.setInt(4, Integer.parseInt(day));
            ps.setString(5, month);
            ps.setInt(6, Integer.parseInt(year));
            ps.execute();
            //JOptionPane.showMessageDialog(null, "Added to item charged");
         }catch(Exception e){     
            JOptionPane.showMessageDialog(null, e);
        }
     }
     
     public void updateItemCharged(){
         QtyOrig = QtyOrig + Integer.parseInt(inputQty);
         try{
            String updatepet = "UPDATE `item_charged` SET `quantity` = ?, `day` = ?, `month` = ?, `year` = ? where `item_id` = ? and `pet_id` = ?";
            ps=conn_db.prepareStatement(updatepet);
            ps.setInt(1, QtyOrig);
            ps.setInt(2, Integer.parseInt(day));
            ps.setString(3, month);
            ps.setInt(4, Integer.parseInt(year));
            ps.setString(5, itemID);
            ps.setString(6, petIDItemCharged.getText());
            ps.executeUpdate();
            //JOptionPane.showMessageDialog(null, "Updated to item charged qty");
         }catch(Exception e){     
            JOptionPane.showMessageDialog(null, e);
        }
     }

     public void getitemChargedQty(){
         try{
            String sql = "SELECT * FROM item_charged INNER JOIN item ON item.item_id = item_charged.item_id WHERE item.name = '"+getitemname2+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getChargedQty = rs.getString("item_charged.quantity");
                QtyOrig = Integer.parseInt(getChargedQty);
                OrigQty = Integer.parseInt(getChargedQty);
                jLabel2.setText(Integer.toString(OrigQty));
            }
         }catch(Exception e){     
            JOptionPane.showMessageDialog(null, e);
        }
     }
     
     public void subtractItemChargedQty(){
         int subtractedQty = QtyOrig-removeQty;
         try{
            String updatepet = "UPDATE `item_charged` SET `quantity` = ? where `item_id` = ? and `pet_id` = ?";
            ps=conn_db.prepareStatement(updatepet);
            ps.setInt(1, subtractedQty);
            ps.setString(2, itemID);
            ps.setString(3, petIDItemCharged.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Updated to item charged");
         }catch(Exception e){     
            JOptionPane.showMessageDialog(null, e);
        }
     }
     
     public void checkZeroQtyintemChareged(){
         try{
             String sql = "select * from item_charged where quantity = '0'";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
            
            while(rs.next()){ 
                String getClassid = rs.getString("classification_id");
                getClassID = getClassid;
                String getName = rs.getString("name");
                getItemName = getName;
                
                try{
                    String sql2 = "DELETE FROM item_charged WHERE `item_id` =?";
                    ps=conn_db.prepareStatement(sql2);
                    ps.setString(1, itemID);
                    ps.executeUpdate();

                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            
         }catch(Exception e){     
            JOptionPane.showMessageDialog(null, e);
        }
     }
     
     
     public void getItemIDchargedItemSelected(){
         try{
            String sql = "SELECT * FROM item_charged INNER JOIN item ON item.item_id = item_charged.item_id WHERE item.name = '"+getitemname2+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){ 
                String getID = rs.getString("item_charged.item_id");
                jLabel6.setText(getID);
            }
            
        }catch(Exception e){     
            JOptionPane.showMessageDialog(null, e);
        }
     }
     
    public void viewItemClassification(){
         try{
            String sql1 ="select * from classification1";
            ps=conn_db.prepareStatement(sql1);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getClassificationName = rs.getString("name");
                jComboBox1.addItem(getClassificationName);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
     
     public void viewItemSpecific(){
         DefaultListModel dimx = new DefaultListModel();
         try{
            String sql1 ="SELECT * FROM `item` INNER JOIN classification1 ON `item`.`classification_id` = `classification1`.`classification_id` WHERE classification1.name = '"+jComboBox1.getSelectedItem().toString()+"'";
            ps=conn_db.prepareStatement(sql1);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getClassificationName = rs.getString("name");
                
                dimx.addElement(getClassificationName);
                jList1.setModel(dimx);
                
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        date_today = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        add = new javax.swing.JButton();
        rem = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        info2 = new javax.swing.JLabel();
        info = new javax.swing.JLabel();
        petIDItemCharged = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pet_name_items = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Charge Item(s)");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        date_today.setForeground(new java.awt.Color(255, 255, 255));
        date_today.setText("jLabel2");
        jPanel1.add(date_today, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 58, -1, 20));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Pet Name:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 78, -1, 20));

        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList2);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 128, 270, 290));

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jList1MouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(jList1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 128, 270, 290));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Charged Item(s)");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 108, 160, -1));

        add.setText("Add >");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        jPanel1.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 128, 90, -1));

        rem.setText("< Remove");
        rem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remActionPerformed(evt);
            }
        });
        jPanel1.add(rem, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 158, 90, -1));

        jButton2.setText("Done");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, 90, -1));

        info2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        info2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        info2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel1.add(info2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 63, 270, 20));

        info.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        info.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        info.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel1.add(info, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 32, 270, 20));

        petIDItemCharged.setText("21");
        jPanel1.add(petIDItemCharged, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 24, 10, 0));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Quantity");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 430, 50, 10));

        jLabel6.setText("jLabel6");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 69, 0, 0));

        pet_name_items.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        pet_name_items.setForeground(new java.awt.Color(255, 255, 255));
        pet_name_items.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pet_name_items.setText("jLabel7");
        jPanel1.add(pet_name_items, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 190, -1));

        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 160, -1));

        jButton3.setText("Show");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(213, 110, 80, 20));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("0");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 430, 20, 10));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bg.jpg"))); // NOI18N
        jLabel7.setText("jLabel7");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 470));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
        DefaultListModel<String> model = new DefaultListModel();
    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        String getQtyContainer = JOptionPane.showInputDialog(null, "Input quantity:\n");
        if(getQtyContainer.equals("")){

            JOptionPane.showMessageDialog(null, "Empty item quantity detected!");
        
        }else{
            
        inputQty = getQtyContainer;
        int intQty = Integer.parseInt(getQty);
     
         if(intQty<Integer.parseInt(inputQty) || intQty==Integer.parseInt(inputQty)){
             
             JOptionPane.showMessageDialog(null, "Invalid item quantity!\nYou can only add less than of "+getQty+" of quantity!");
             
         }else{
            
            int subQty = intQty - Integer.parseInt(inputQty);
            itemQty = subQty;
            
            model.addElement(getitemname);
            jList2.setModel(model);

            int size = model.size();
            int f = size-1;

            if(f==0){
                    
            }else{
                for(int x=1; x<=f; x++){
                    if(((DefaultListModel) jList2.getModel()).getElementAt(f).toString().equals(((DefaultListModel) jList2.getModel()).getElementAt(x-1).toString())){
                        ((DefaultListModel) jList2.getModel()).remove(f);
                        f--;
                        OrigQty = OrigQty + 1;jLabel2.setText(Integer.toString(OrigQty));
                        
                    }else{
                        OrigQty = 1;jLabel2.setText(Integer.toString(OrigQty));
                        
                     }
                    }
            }       
            ((DefaultListModel) jList1.getModel()).remove(getselectedindex);
            addToItemCharged();
            reduceItemQty();
            //checkZeroQty();
            fillServiceList();
            showInfo();
         }
        }
    }//GEN-LAST:event_addActionPerformed

    private void remActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remActionPerformed
        String inputSubtractQty = JOptionPane.showInputDialog(null, "How many quantity you want remove?");
        
        if(OrigQty<Integer.parseInt(inputSubtractQty)){
            JOptionPane.showMessageDialog(null, "Inputted quantity is too much!");
        }else{
            removeQty = Integer.parseInt(inputSubtractQty);
            OrigQty = OrigQty-Integer.parseInt(inputSubtractQty);
            jLabel2.setText(Integer.toString(OrigQty));
            updateItemQty();
            subtractItemChargedQty();
            //((DefaultListModel)jList2.getModel()).remove(getselectedindex2);
            if(OrigQty==0){
                ((DefaultListModel) jList2.getModel()).remove(getselectedindex2);
            }
        }
    }//GEN-LAST:event_remActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Are you sure you want to finish?\nClosing this will save the selected item(s).",
            "Finishing the charging of item(s)", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            this.dispose();
        }
        else {

            System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
            return;
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        String itemname = jList1.getModel().getElementAt(jList1.getSelectedIndex()).toString();
        int selectedIndex = jList1.getSelectedIndex();
        getselectedindex = selectedIndex;
        getitemname = itemname;
        add.setEnabled(true);
        showInfo();
    }//GEN-LAST:event_jList1MouseClicked

    private void jList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseClicked
        String servicename2 = jList2.getModel().getElementAt(jList2.getSelectedIndex()).toString();
        int selectedIndex2 = jList2.getSelectedIndex();
        getselectedindex2 = selectedIndex2;
        getitemname2 = servicename2;
        rem.setEnabled(true);
        getitemChargedQty();
        getItemIDchargedItemSelected();
    }//GEN-LAST:event_jList2MouseClicked

    private void jList1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jList1MouseEntered

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if ( JOptionPane.showConfirmDialog(new JFrame(),
            "Are you sure you want to close?",
            "Closing Charge Items", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            this.dispose();
            
        }
        else {

            System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
            return;
        }
    }//GEN-LAST:event_formWindowClosing

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        viewItemSpecific();
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(charge_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(charge_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(charge_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(charge_item.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new charge_item().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    public static javax.swing.JLabel date_today;
    private javax.swing.JLabel info;
    private javax.swing.JLabel info2;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JLabel petIDItemCharged;
    public static javax.swing.JLabel pet_name_items;
    private javax.swing.JButton rem;
    // End of variables declaration//GEN-END:variables
}
