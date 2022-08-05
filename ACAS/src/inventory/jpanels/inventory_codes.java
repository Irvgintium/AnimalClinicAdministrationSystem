/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.jpanels;
import com.toedter.calendar.JCalendar;
import static inventory.jpanels.Main_Menu_Inventory.*;
import java.awt.Color;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.UUID;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import login.connection_db;
import net.proteanit.sql.DbUtils;
import static home.home_vet.setacc;
import java.util.Calendar;
/////////////////////////////////////////////////Codes for Item////////////////////////////////////////////////////////

/**
 *
 * @author irv
 */
public class inventory_codes {
    
    Connection conn_db = connection_db.ConnectDB();
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    int supplier_id;
    int getSuppId;
    int getClassID;
    int getSuppIDitem;
    int getClassID1;
    int getSuppIDOrder;
     int getSuppIDOrder1;
     
     int per;
   String previousSupplierPrice;
   String previousSalePrice;
    
    int getClassIDqty;
    int getItemIDqty;
    int getClassIDqty1;
    
    int iID;
    int iID2;
    
   int getClassID2;
    
    String getClassName112;
    String getSupp;   
    String getClassName11;
    String getClassName111;
    String getClassName1111;
    String getClassName11111;
    String getClassName1122;
    
    String userID;
    
    String saleUse1;
    String saleUse2;
    
    
    String selectedDate = "";
    
     String selectedDate1 = "";
   
    
     String day;
     String month;
     String year;
     
     String itemname = "";
     
     int expireitemnotif = 0;
     
     String mnfgdate;
    
     String suppname;
     String itemid;
     
     public static String expdate="";
     
     String getitemname;
     
    ///////////////////////////////////////////////////////codes for SUPPLIER///////////////////////////////////////////////////////////////////////////////////////
     
     public void ExamIDGenerate(){
        String timeStamp = new SimpleDateFormat("yyMMddHHmmss").format(Calendar.getInstance().getTime());
        //jLabel1.setText(timeStamp);
    }
    
    public void autodate(){
         
       JCalendar x = new JCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
        //JOptionPane.showMessageDialog(null,x, "Choose Date", JOptionPane.INFORMATION_MESSAGE);
        String dob = dateFormat.format(x.getDate());
        txt_calendar.setText(dob);
      
    }
     
     public void normalMode(){
         jButton1.setEnabled(true);
         jButton2.setEnabled(false);
     }
     
     public void updateMode(){
         jButton2.setEnabled(true);
         jButton1.setEnabled(false);
     }
     
     public void autoviewSupplier(){
         try{
            String sql = "SELECT `supplier_id` as `ID`, `name` as `Supplier`, `contact_number` as `Contact Number`FROM `supplier`";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
         
         jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
         jTable1.getColumnModel().getColumn(0).setMinWidth(50);
         jTable1.getColumnModel().getColumn(0).setMaxWidth(70);
     }
     
     public void saveSupplier(){
         try{
                String sql = "Insert into supplier (`name`,`title_name`,`first_name`,`middle_name`,`last_name`,`suffix_name`,`contact_number`,`street`,`prk`,`brgy`,`city`,`province`) values (UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?))";
                ps=conn_db.prepareStatement(sql);
                ps.setString(1, jTextField1.getText());
                ps.setString(2, jTextField2.getText());
                ps.setString(3, jTextField3.getText());
                ps.setString(4, jTextField4.getText());
                ps.setString(5, jTextField5.getText());
                ps.setString(6, jTextField6.getText());
                ps.setString(7, jTextField7.getText());
                ps.setString(8, jTextField8.getText());
                ps.setString(9, jTextField9.getText());
                ps.setString(10, jTextField10.getText());
                ps.setString(11, jTextField11.getText());
                ps.setString(12, jTextField12.getText());
                ps.execute();
                
                JOptionPane.showMessageDialog(null, "Supplier information has been sucessfully stored", "Success", JOptionPane.INFORMATION_MESSAGE);
                resetSupplier();
                autoviewSupplier();

            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
     }
     
     public void resetSupplier(){
         jTextField1.setText("");
         jTextField2.setText("");
         jTextField3.setText("");
         jTextField4.setText("");
         jTextField5.setText("");
         jTextField6.setText("");
         jTextField7.setText("");
         jTextField8.setText("");
         jTextField9.setText("");
         jTextField10.setText("");
         jTextField11.setText("");
         jTextField12.setText("");
     }
     
     public void setsupplierUpdate(){
         DefaultTableModel model3 = (DefaultTableModel) jTable1.getModel();
         String id = model3.getValueAt(jTable1.getSelectedRow(), 0).toString();
         
         try{
             String sql = "SELECT * FROM `supplier` WHERE  supplier_id = '" + id +"'";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             
             while(rs.next()){
                jTextField1.setText(rs.getString("name"));
                jTextField2.setText(rs.getString("title_name"));
                jTextField3.setText(rs.getString("first_name"));
                jTextField4.setText(rs.getString("middle_name"));
                jTextField5.setText(rs.getString("last_name"));
                jTextField6.setText(rs.getString("suffix_name"));
                jTextField7.setText(rs.getString("contact_number"));
                jTextField8.setText(rs.getString("street"));
                jTextField9.setText(rs.getString("prk"));
                jTextField10.setText(rs.getString("brgy"));
                jTextField11.setText(rs.getString("city"));
                jTextField12.setText(rs.getString("province"));
             }
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
     }
     
     public void autoSearch(){
        try{
            String sql = "SELECT `supplier_id` as `ID`, `name` as `Supplier`, `contact_number` as `Contact Number`FROM `supplier` WHERE name LIKE '"+jTextField15.getText()+"%'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
        
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(0).setMinWidth(50);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(70);
     
    }
     
     public void updateSupplier(){
         DefaultTableModel model3 = (DefaultTableModel) jTable1.getModel();
         String id = model3.getValueAt(jTable1.getSelectedRow(), 0).toString();
         try{
                String sql = "Update supplier set `name`= UPPER(?),`title_name`= UPPER(?),`first_name`= UPPER(?),`middle_name`= UPPER(?),`last_name`= UPPER(?),`suffix_name`= UPPER(?),`contact_number`= UPPER(?),`street`= UPPER(?),`prk`= UPPER(?),`brgy`= UPPER(?),`city`= UPPER(?),`province`= UPPER(?) where supplier_id ='" + id + "'";
                ps=conn_db.prepareStatement(sql);
                ps.setString(1, jTextField1.getText());
                ps.setString(2, jTextField2.getText());
                ps.setString(3, jTextField3.getText());
                ps.setString(4, jTextField4.getText());
                ps.setString(5, jTextField5.getText());
                ps.setString(6, jTextField6.getText());
                ps.setString(7, jTextField7.getText());
                ps.setString(8, jTextField8.getText());
                ps.setString(9, jTextField9.getText());
                ps.setString(10, jTextField10.getText());
                ps.setString(11, jTextField11.getText());
                ps.setString(12, jTextField12.getText());
                ps.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Supplier information has been sucessfully updated", "Success", JOptionPane.INFORMATION_MESSAGE);
                resetSupplier();
                normalMode();
                autoviewSupplier();

            }catch(Exception e){

                JOptionPane.showMessageDialog(null, e);

            }
     }
     
     
     ///////////////////////////////////////////////////////codes for classification///////////////////////////////////////////////////////////////////////////////////////
     
     
     public void setCMBSupplierName(){
            jComboBox1.removeAllItems();
         try{
             String sql = "SELECT * FROM `supplier` ";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             
             while(rs.next()){
                jComboBox1.addItem(rs.getString("name"));
                jComboBox2.addItem(rs.getString("name"));
                jComboBox13.addItem(rs.getString("name"));
                jComboBox14.addItem(rs.getString("name"));
                jComboBox23.addItem(rs.getString("name"));
             }
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
     }
     
     public void normalModeClassification(){
         jButton5.setEnabled(false);
         jButton4.setEnabled(true);
         resetClassification();
     }
     
     public void updateModeClassification(){
         jButton5.setEnabled(true);
         jButton4.setEnabled(false);
     }
     
     public void resetClassification(){
         jTextField13.setText("");
         supplier_id = 0;
     }
     
     public void autoviewClassification(){
         try{
            String sql = "SELECT `classification_id` as `ID`, classification1.name as `Classification`,`supplier`.name as `Supplier`FROM `classification1`inner join supplier on supplier.supplier_id = classification1.supplier_id";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
         
         jTable2.getColumnModel().getColumn(0).setPreferredWidth(30);
         jTable2.getColumnModel().getColumn(0).setMinWidth(50);
         jTable2.getColumnModel().getColumn(0).setMaxWidth(70);
     }
     
     public void getSupplierID(){
         try{
             String sql = "SELECT * FROM `supplier` where name ='"+jComboBox1.getSelectedItem().toString()+"'";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             
             while(rs.next()){
                supplier_id = rs.getInt("supplier_id");
                System.out.println(supplier_id);
             }
             
         }catch(Exception e){
             System.out.println(e);
         }
     }
     
     public void saveClassification(){
         try{
                String sql = "Insert into classification1 (`supplier_id`,`name`) values (?,UPPER(?))";
                ps=conn_db.prepareStatement(sql);
                ps.setString(1, Integer.toString(supplier_id));
                ps.setString(2, jTextField13.getText());
                ps.execute();
                
                JOptionPane.showMessageDialog(null, "Classification information has been sucessfully stored", "Success", JOptionPane.INFORMATION_MESSAGE);
                autoviewClassification();
                resetClassification();
                normalModeClassification();

            }catch(Exception e){

                JOptionPane.showMessageDialog(null, e);

            }
     }
     
     public void autoSearchClassification(){
        try{
            String sql = "SELECT `classification_id` as `ID`, classification1.name as `Classification`,`supplier`.name as `Supplier`FROM `classification1`inner join supplier on supplier.supplier_id = classification1.supplier_id WHERE classification1.name LIKE '"+jTextField16.getText()+"%'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
        
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable2.getColumnModel().getColumn(0).setMinWidth(50);
        jTable2.getColumnModel().getColumn(0).setMaxWidth(70);
     
    }
     
     public void setupdateClassification(){
         DefaultTableModel model3 = (DefaultTableModel) jTable2.getModel();
         String id = model3.getValueAt(jTable2.getSelectedRow(), 0).toString();
         System.out.println("ClassID: "+id);
         try{
             String sql = "SELECT * FROM `classification1` inner join supplier on supplier.supplier_id = classification1.supplier_id WHERE  classification1.classification_id = '" + id +"'";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             
             while(rs.next()){
                jComboBox1.setSelectedItem(rs.getString("supplier.name"));
                jTextField13.setText(rs.getString("classification1.name"));
             }
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
         getSupplierID();
     }
     
     public void updateClassification(){
         DefaultTableModel model3 = (DefaultTableModel) jTable2.getModel();
         String id = model3.getValueAt(jTable2.getSelectedRow(), 0).toString();
         try{
                String sql = "update classification1 set `name`=UPPER(?) where classification_id='"+id+"'";
                ps=conn_db.prepareStatement(sql);
                ps.setString(1, jTextField13.getText());
                ps.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Classification information has been sucessfully updated", "Success", JOptionPane.INFORMATION_MESSAGE);
                autoviewClassification();
                resetClassification();
                normalModeClassification();

            }catch(Exception e){

                JOptionPane.showMessageDialog(null, e);

            }
     }
     /////////////////////////////////////////////////////code for Item//////////////////////////////////////////
     
      public void getSupplierID1(){
            try{
                String sql1 ="select supplier_id from supplier where name='"+jComboBox2.getSelectedItem()+"'";
                ps=conn_db.prepareStatement(sql1);
                rs = ps.executeQuery();
                    while(rs.next()){
                        getSuppId = rs.getInt("supplier_id");
                        System.out.println( getSuppId);}
                }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e); }
                }
      public void Jlist(){
               String x = ((DefaultListModel)class_item_list.getModel()).getElementAt(class_item_list.getSelectedIndex()).toString();
                getClassName11 = x;
                }
      
      public void getClassificationID(){
            try{
                String sql1 ="select classification_id from classification1 where name='"+getClassName11+"'";
                ps=conn_db.prepareStatement(sql1);
                rs = ps.executeQuery();
                    while(rs.next()){
                        getClassID = rs.getInt("classification_id");
                        System.out.println(getClassID);}
                }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);}
                }

     public void suppIdItem() {
            try{
                String sql="select * from supplier where name = '"+jComboBox2.getSelectedItem()+"'";
                ps=conn_db.prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next()){
                     getSuppId = rs.getInt("supplier_id");
                     System.out.println( getSuppId); }
               }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);}
         
            DefaultListModel dimx = new DefaultListModel();
                    try{
                        String sql="select * from classification1 where supplier_id= '"+ getSuppId +"'";
                        ps = conn_db.prepareStatement(sql);
                        rs = ps.executeQuery();
                            while(rs.next()){
                                String getname = rs.getString("name");  
                                dimx.addElement(getname);
                                class_item_list.setModel(dimx); }    
                    }catch(Exception e){     
                            JOptionPane.showMessageDialog(null, e);}    
                    }
    
   public void RadioButtonClinical(){
                jComboBox3.setEnabled(true); 
                RdSale.setEnabled(true);
         
            if(rdClinical.isSelected()){
                RdSale.setSelected(false);
                saleUse1="Clinical";}
                }
      
    public void RadioButtonSale(){
                rdClinical.setEnabled(true);
                jComboBox3.setEnabled(false); 

               if(RdSale.isSelected()){
                 rdClinical.setSelected(false);
                 saleUse1="Sale";
                getjComboBox3="";
               }
                }
    
     public void normalModeItem(){
                jButton8.setEnabled(false);
                jButton7.setEnabled(true);
                resetItem();
                }
     
     public void updateModeItem(){
                jButton8.setEnabled(true);
                jButton7.setEnabled(false);
               
                }
    
     public void resetItem(){
               getSuppId = 0;
               getClassID =0;
               Txt_item.setText("");
               jComboBox3.setSelectedItem("");
               jLabel18.setText("");
               jLabel2.setText("");
               jComboBox10.setSelectedItem(""); 
              }

     public void autoviewCItem(){
          try{
                String sql = "SELECT item.item_id as 'ID',item.name as 'Item', classification1.name as 'classification',supplier.name as 'Supplier'  from item inner join supplier on supplier.supplier_id = item.supplier_id inner join classification1 on classification1.classification_id = item.classification_id ";
                ps = conn_db.prepareStatement(sql);
                rs = ps.executeQuery();
                jTable3.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
                }   
                    jTable3.getColumnModel().getColumn(0).setPreferredWidth(30);
                    jTable3.getColumnModel().getColumn(0).setMinWidth(50);
                    jTable3.getColumnModel().getColumn(0).setMaxWidth(50);
                    jTable3.getColumnModel().getColumn(0).setMaxWidth(100);  
                }
    
    public void saveItem() {
                if(Txt_item.getText().equals("") || jLabel18.getText().equals("") || jLabel2.getText().equals("") ){
                    JOptionPane.showMessageDialog(null, "Empty field detected!");
                }else{   
                   try{
                           
                      String mfgday=""; // 004
                      String mfgmonth=""; // 034556
                      String mfgyear=""; // 034556 
                      String[] parts3 = jLabel18.getText().split(" ");
                      mfgday = parts3[0]; // 004
                      mfgmonth = parts3[1]; // 034556
                      mfgyear = parts3[2]; // 034556 
                            
                    
                    /////////////////////////////////////////////////
                    
                      String expday =""; // 004
                      String expmonth=""; // 034556
                      String expyear=""; // 034556
                      String[] parts4 = jLabel2.getText().split(" ");
                      expday = parts4[0]; // 004
                      expmonth = parts4[1]; // 034556
                      expyear = parts4[2]; // 034556
                    
                           
                       String sql="insert into item( supplier_id,classification_id, name, CliSale, forCheck, manufacturing_day, manufacturing_month, manufacturing_year, expiration_day, expiration_month, expiration_year, pet_type)values(?,?,?,?,?,?,?,?,?,?,?,?)";
                       ps=conn_db.prepareStatement(sql);

                       ps.setString(1,Integer.toString(getSuppId));
                       ps.setString(2,Integer.toString(getClassID));
                       ps.setString(3,Txt_item.getText().toUpperCase());
                       ps.setString(4,saleUse1);
                       ps.setString(5,jComboBox3.getSelectedItem().toString());
                       ps.setString(6, mfgday.toUpperCase());
                       ps.setString(7, mfgmonth.toUpperCase());
                       ps.setString(8, mfgyear.toUpperCase());
                       ps.setString(9, expday.toUpperCase());
                       ps.setString(10, expmonth.toUpperCase());
                       ps.setString(11, expyear.toUpperCase());
                       ps.setString(12,jComboBox10.getSelectedItem().toString());

                       ps.execute();

                       JOptionPane.showMessageDialog(null, "Item "+Txt_item.getText().toUpperCase()+" has been sucessfully saved");
                       resetItem();
                       autoviewCItem();
                        }
                        catch (Exception e){
                            JOptionPane.showMessageDialog(null, e); 
                            }
                        } 
                    }
    
      public void setupdateItem(){
        DefaultListModel dimx = new DefaultListModel();
         DefaultTableModel model4 = (DefaultTableModel) jTable3.getModel();
         String id = model4.getValueAt(jTable3.getSelectedRow(), 0).toString();
         System.out.println("ClassID: "+id);
         
                try{
                    String sql = "SELECT * FROM `item` inner join supplier on supplier.supplier_id = item.supplier_id inner join classification1 on classification1.classification_id = item.classification_id WHERE  item.item_id ='" + id +"'";
                    ps = conn_db.prepareStatement(sql);
                    rs = ps.executeQuery();
                        while(rs.next()){
                            jComboBox2.setSelectedItem(rs.getString("supplier.name"));
                            dimx.addElement((rs.getString("classification1.name")));
                            class_item_list.setModel(dimx);
                            Txt_item.setText(rs.getString("item.name"));
                                    String CS=rs.getString("CliSale");
                                    
                                        if(CS.equals("Clinical")) {
                                              rdClinical.setSelected(true);
                                              saleUse1 = "Clinical";
                                              getjComboBox3=rs.getString("forCheck");
                                              jComboBox3.setSelectedItem(getjComboBox3);
                                              RdSale.setSelected(false);
                                              
                                        } else  if(CS.equals("Sale")){
                                             RdSale.setSelected(true);
                                             rdClinical.setSelected(false);
                                             saleUse1 = "Sale";
                                             getjComboBox3="";
                                             jComboBox3.setEnabled(false);
                                        }else {
                                       // JOptionPane.showMessageDialog(null, "error !");
                                            rdClinical.setSelected(false); 
                                            RdSale.setSelected(false);
                                            getjComboBox3="";
                                             jComboBox3.setEnabled(false);
                                                }
                                        jLabel18.setText(rs.getString("manufacturing_day")+" "+rs.getString("manufacturing_month")+" "+rs.getString("manufacturing_year"));
                                        
                                        jLabel2.setText(rs.getString("expiration_day")+" "+rs.getString("expiration_month")+" "+rs.getString("expiration_year"));
                                        

                                        jComboBox10.setSelectedItem(rs.getString("pet_type")); 
                        
                                    }
                            }catch(Exception e){
                                JOptionPane.showMessageDialog(null, "Please update this item\nItem is outdated","Validation",JOptionPane.INFORMATION_MESSAGE);
                            }

                            getClassificationID();

                            }
      
  
               
      public void updateItem(){
         DefaultTableModel model4 = (DefaultTableModel) jTable3.getModel();
         String id = model4.getValueAt(jTable3.getSelectedRow(), 0).toString();
                try{
                    
                    String mfgday=""; // 004
                    String mfgmonth=""; // 034556
                    String mfgyear=""; // 034556 
                    try{
                        String[] parts3 = jLabel18.getText().split(" ");
                        mfgday = parts3[0]; // 004
                        mfgmonth = parts3[1]; // 034556
                        mfgyear = parts3[2]; // 034556 
                    }catch(Exception e){
                        String[] parts3 = jLabel18.getText().split(" ");
                        mfgday = "0"; // 004
                        mfgmonth = parts3[0]; // 034556
                        mfgyear = parts3[1]; // 034556 
                    }
                    
                    /////////////////////////////////////////////////
                    
                    String expday =""; // 004
                    String expmonth=""; // 034556
                    String expyear=""; // 034556
                    try{
                        String[] parts4 = jLabel2.getText().split(" ");
                        expday = parts4[0]; // 004
                        expmonth = parts4[1]; // 034556
                        expyear = parts4[2]; // 034556
                    }catch(Exception e){
                        String[] parts4 = jLabel2.getText().split(" ");
                        expday = "0"; // 004
                        expmonth = parts4[0]; // 034556
                        expyear = parts4[1]; // 034556
                    }
                     
                       String sql = "update item set `name`=UPPER(?),`CliSale`=(?), `forCheck`=(?), `manufacturing_day`=(?), `manufacturing_month`=(?), `manufacturing_year`=(?),`expiration_day`=(?), `expiration_month`=(?), `expiration_year`=(?),`pet_type`=(?)  where item_id='"+id+"'";
                       ps=conn_db.prepareStatement(sql);
                       ps.setString(1,Txt_item.getText());
                       ps.setString(2, saleUse1);
                       ps.setString(3, getjComboBox3);
                       ps.setString(4, mfgday.toUpperCase());
                       ps.setString(5, mfgmonth.toUpperCase());
                       ps.setString(6, mfgyear.toUpperCase());
                       ps.setString(7, expday.toUpperCase());
                       ps.setString(8, expmonth.toUpperCase());
                       ps.setString(9, expyear.toUpperCase());
                       ps.setString(10,jComboBox10.getSelectedItem().toString());
                       ps.executeUpdate();

                       JOptionPane.showMessageDialog(null, "Item information has been sucessfully updated", "Success", JOptionPane.INFORMATION_MESSAGE);
                       autoviewCItem();
                       resetItem();
                       normalModeItem();
                }catch(Exception e){
                       JOptionPane.showMessageDialog(null, e); }
                }
      
       public void autoSearchItem(){
                try{
                    String sql = "SELECT `item_id` as `ID`, item.name as `Item`, classification1.name as `classification`,`supplier`.name as `Supplier`FROM `item` LEFT JOIN classification1 on classification1.classification_id = item.classification_id LEFT JOIN supplier on supplier.supplier_id = item.supplier_id Where item.name LIKE'"+jTextField14.getText()+"%'";
                    ps = conn_db.prepareStatement(sql);
                    rs = ps.executeQuery();
                    jTable3.setModel(DbUtils.resultSetToTableModel(rs));
                }catch(Exception e){
                   JOptionPane.showMessageDialog(null, e);
                }
                    jTable3.getColumnModel().getColumn(0).setPreferredWidth(30);
                    jTable3.getColumnModel().getColumn(0).setMinWidth(50);
                    jTable3.getColumnModel().getColumn(0).setMaxWidth(50);
                    jTable3.getColumnModel().getColumn(0).setMaxWidth(100);
    }
      
      public void biggerVolumeOn(){
                    jComboBox12.setEnabled(true);
                    jTextField24.setEnabled(true);
                    jTextField23.setEnabled(true);
                    jTextField28.setEnabled(true);
                    jTextField29.setEnabled(true);
                    jComboBox16.setEnabled(true);        
      } public void piecesOn(){
                    jTextField23.setEnabled(false);  
                    jComboBox12.setEnabled(false);
                    jComboBox22.setEnabled(true);
                    jTextField35.setEnabled(true);
                    jComboBox21.setEnabled(true);
                    jTextField36.setEnabled(true);
                           
      }
      public void calculateTotalQty(){
                    BigDecimal qty = new BigDecimal(jTextField24.getText());
                    BigDecimal num = new BigDecimal(jTextField23.getText());
                    BigDecimal ans = qty.multiply(num);
                    jTextField29.setText(ans.toString());
      }
      public void validateManufacturingDate(){
            SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
            String dt = dateFormat.format(jCalendar2.getDate());

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
        
            String[] current = txt_calendar.getText().split(" ");
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
        
                if(dtyear>curyear){
                    JOptionPane.showMessageDialog(null, "Invalid Year Detected!", "Mfg. Date Validation", JOptionPane.ERROR_MESSAGE);
                    jLabel19.setForeground(Color.red);
                    jLabel18.setText("");
                }else{
                    if(dtyear<curyear){
                        selectedDate = dt;
                        jLabel19.setForeground(Color.black);
                    }else{
                    if(dtmonth>curmonth){
                        JOptionPane.showMessageDialog(null, "Invalid Month Detected!", "Mfg. Date Validation", JOptionPane.ERROR_MESSAGE);
                        jLabel19.setForeground(Color.red);
                        jLabel18.setText("");
                    }else{
                        if(dtmonth==curmonth){
                            if(dtday>curday){
                                JOptionPane.showMessageDialog(null, "Invalid Day Detected!", "Mfg. Date Validation", JOptionPane.ERROR_MESSAGE);
                                jLabel19.setForeground(Color.red);
                                jLabel18.setText("");
                                }else{
                                   selectedDate = dt;
                                   jLabel19.setForeground(Color.black);
                                }
                            }else{
                                if(dtmonth<curmonth){
                                    selectedDate = dt;
                                    jLabel19.setForeground(Color.black);
                                }
                            }
                        }
                    }
                }
            }
      
      
      public void validateManufacturingDateMY(){
            SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
            String dt = dateFormat.format(jCalendar2.getDate());

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
        
            String[] current = txt_calendar.getText().split(" ");
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
        
                if(dtyear>curyear){
                    JOptionPane.showMessageDialog(null, "Invalid Year Detected!", "Mfg. Date Validation", JOptionPane.ERROR_MESSAGE);
                    jLabel19.setForeground(Color.red);
                    jLabel18.setText("");
                }else{
                    if(dtyear<curyear){
                        selectedDate = dt;
                        jLabel19.setForeground(Color.black);
                    }else{
                    if(dtmonth>curmonth){
                        JOptionPane.showMessageDialog(null, "Invalid Month Detected!", "Mfg. Date Validation", JOptionPane.ERROR_MESSAGE);
                        jLabel19.setForeground(Color.red);
                        jLabel18.setText("");
                    }else{
                        if(dtmonth==curmonth){
                             selectedDate = dt;
                             jLabel19.setForeground(Color.black);
                            }
                        }
                    }
                }
            }
      
      public void vaildateExpirationDate(){
            SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
             String dt = dateFormat.format(jCalendar2.getDate());
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

            String[] current = txt_calendar.getText().split(" ");
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
                    JOptionPane.showMessageDialog(null, "Invalid Year Detected!", "Exp. Date Validation", JOptionPane.ERROR_MESSAGE);
                    jLabel19.setForeground(Color.red);
                    jLabel2.setText("");
                }else{
                    if(dtyear>curyear){
                        selectedDate = dt;
                        jLabel19.setForeground(Color.black);
                    }else{
                    if(dtmonth<curmonth){
                        JOptionPane.showMessageDialog(null, "Invalid Month Detected!", "Exp. Date Validation", JOptionPane.ERROR_MESSAGE);
                        jLabel19.setForeground(Color.red);
                        jLabel2.setText("");
                    }else{
                        if(dtmonth==curmonth){
                            if(dtday<curday){
                                JOptionPane.showMessageDialog(null, "Invalid Day Detected!", "Exp. Date Validation", JOptionPane.ERROR_MESSAGE);
                                jLabel19.setForeground(Color.red);
                                jLabel2.setText("");
                                }else{
                                   selectedDate = dt;
                                   jLabel19.setForeground(Color.black);
                                }
                            }else{
                                if(dtmonth>curmonth){
                                    selectedDate = dt;
                                    jLabel19.setForeground(Color.black);
                                }
                            }
                        }
                    }
                }
              }
      
      
      public void vaildateExpirationDateMY(){
            SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
             String dt = dateFormat.format(jCalendar2.getDate());
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

            String[] current = jLabel18.getText().split(" ");
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
                    JOptionPane.showMessageDialog(null, "Invalid Year Detected!", "Exp. Date Validation", JOptionPane.ERROR_MESSAGE);
                    jLabel19.setForeground(Color.red);
                    jLabel2.setText("");
                }else{
                    if(dtyear>curyear){
                        selectedDate = dt;
                        jLabel19.setForeground(Color.black);
                    }else{
                    if(dtmonth<curmonth){
                        JOptionPane.showMessageDialog(null, "Invalid Month Detected!", "Exp. Date Validation", JOptionPane.ERROR_MESSAGE);
                        jLabel19.setForeground(Color.red);
                        jLabel2.setText("");
                    }else{
                        if(dtmonth==curmonth){
                                selectedDate = dt;
                                jLabel19.setForeground(Color.black);
                            }
                        }
                    }
                }
              }
       
       //////////////////////////////////////////////Qunatity and Price Codes///////////////////////////////////
      
       public void normalModeQtyPrice(){
         jButton24.setEnabled(false);
         jButton23.setEnabled(true);
        // resetClassification();
     }
     
     public void updateModeQtyPrice(){
        jButton24.setEnabled(true);
        jButton23.setEnabled(false);
     }
      public void resetQtyPrice(){
         jTextField13.setText("");
         jComboBox12.setSelectedItem("");
         jTextField24.setText("");
         jComboBox16.setSelectedItem("");
         jTextField28.setText("");
         jComboBox15.setSelectedItem("");
         jTextField23.setText("");
         jTextField23.setText("");
         jLabel18.setText("");
         jLabel2.setText("");
         Supp_price.setText("");
         getSuppIDitem = 0;
         getClassIDqty =0;
         iID = 0;
         
         for(int x = 0;x<=class_item_list.getModel().getSize()-1;x++){
            ((DefaultListModel) class_item_list.getModel()).remove(x);
        }
        
         jLabel19.setText("");
         
         rdClinical.setSelected(false);
         RdSale.setSelected(false);
         
         jComboBox10.setSelectedIndex(0);
         
         jComboBox3.setSelectedIndex(0);
         
         Txt_item.setText("");
         
         jComboBox2.setSelectedIndex(0);
         
         mfd.setText("");
         xpd.setText("");
         
         jRadioButton3.setSelected(false);
         jRadioButton4.setSelected(false);
      }
       
       public void getSupplierID2(){
            try{
                String sql1 ="select supplier_id from supplier where name='"+jComboBox13.getSelectedItem()+"'";
                ps=conn_db.prepareStatement(sql1);
                rs = ps.executeQuery();

                    while(rs.next()){
                     getSuppIDitem = rs.getInt("supplier_id");
                   //  JOptionPane.showMessageDialog(null,getSuppIDitem );
                      System.out.println( getSuppIDitem); }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e); }
            } 

      public void getClassIdItem1() {
         getSupplierID2();
         DefaultListModel ewan = new DefaultListModel();
            try{
                String sql="select name from classification1 where supplier_id= '"+ getSuppIDitem+"'";
                ps = conn_db.prepareStatement(sql);
                rs = ps.executeQuery();

                    while(rs.next()){
                        String getname1 = rs.getString("name");  
                        ewan.addElement(getname1);
                        class_item_list2.setModel(ewan);}    
            }catch(Exception e){     
                JOptionPane.showMessageDialog(null, e);}    
            }
      
       public void Jlist1(){
               String x = ((DefaultListModel)class_item_list2.getModel()).getElementAt(class_item_list2.getSelectedIndex()).toString();
               getClassName111 = x;
            }
       
       public void getClassIDqty(){
                try{
                       String sql1 ="select classification_id from classification1 where name='"+getClassName111+"'";
                       ps=conn_db.prepareStatement(sql1);
                       rs = ps.executeQuery();

                       while(rs.next()){
                           getClassIDqty = rs.getInt("classification_id");
                           //JOptionPane.showMessageDialog(null,getClassIDqty );
                           System.out.println(getClassIDqty); }
                   } catch(Exception e){
                       JOptionPane.showMessageDialog(null, e);}
                 }
       
      public void Jlist2(){
               String x = ((DefaultListModel)class_item_list1.getModel()).getElementAt(class_item_list1.getSelectedIndex()).toString();
                getClassName112 = x;
                
                try{
                String sql1 ="select item_id from item where name='"+getClassName112+"'";
                ps=conn_db.prepareStatement(sql1);
                rs = ps.executeQuery();

                    while(rs.next()){
                     iID = rs.getInt("item_id");
                      System.out.println(iID); }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e); }
                 }
      
      
        
      public void getItemIDqty(){
                   getClassIDqty();
         DefaultListModel ewan = new DefaultListModel();
            try{
                String sql1="select name from item where classification_id= '"+ getClassIDqty+"'";
                ps = conn_db.prepareStatement(sql1);
                rs = ps.executeQuery();

                    while(rs.next()){
                        String getname = rs.getString("name");  
                        ewan.addElement(getname);
                        class_item_list1.setModel(ewan);}    
            }catch(Exception e){     
                JOptionPane.showMessageDialog(null, e);}
                    }
      //Select item.name from item INNER JOIN classification1 ON classification1.classification_id = item.classification_id where classification1.classification_id='"+getClassIDqty+"'"
    
      
      public void getItemIdItem2() { 
          getClassIDqty();
          
          DefaultListModel dimx1 = new DefaultListModel();
          String cont = "";
          dimx1.addElement(cont);
          class_item_list1.setModel(dimx1);
          
          try{
               String sql1 ="SELECT * FROM `item` INNER JOIN supplier ON supplier.supplier_id = item.supplier_id INNER JOIN classification1 ON `item`.`classification_id` = `classification1`.`classification_id` WHERE supplier.name ='"+jComboBox13.getSelectedItem().toString()+"' AND classification1.name = '"+class_item_list2.getSelectedValue().toString()+"'";
            ps=conn_db.prepareStatement(sql1);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getClassificationName = rs.getString("name");
                String getItemId = rs.getString("item.item_id");
                iID1.setText(getItemId);
                String getClassId = rs.getString("classification1.classification_id");
                cID.setText(getClassId);
               String getSupID = rs.getString("supplier.supplier_id");
               sID.setText(getSupID);
               dimx1.addElement(getClassificationName);
               class_item_list1.setModel(dimx1);
  
                }
          }catch(Exception e){
              JOptionPane.showMessageDialog(null, "Please select a classification name first!","Validation",JOptionPane.INFORMATION_MESSAGE);
          }
          

        }
        
    public void autoviewQtyPrice(){
         try{
            String sql = "SELECT item.item_id as 'ID', supplier.name as 'Supplier', classification1.name as 'Classification', item.name as 'Item', item_price.price as 'Price', item.quantity as 'Quantity' from item inner join supplier on supplier.supplier_id = item.supplier_id inner join classification1 on classification1.classification_id = item.classification_id inner join item_price ON item_price.item_id = item.item_id";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable5.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
         
         jTable5.getColumnModel().getColumn(0).setPreferredWidth(30);
         jTable5.getColumnModel().getColumn(0).setMinWidth(50);
         jTable5.getColumnModel().getColumn(0).setMaxWidth(50);
         jTable5.getColumnModel().getColumn(0).setMaxWidth(50);
         jTable5.getColumnModel().getColumn(0).setMaxWidth(20);
         jTable5.getColumnModel().getColumn(0).setMaxWidth(20);
     }
    
    public void autoSearchQtyPrice(){
        try{
            String sql = "SELECT item.item_id as 'ID', supplier.name as 'Supplier', classification1.name as 'Classification', item.name as 'Item', item_price.price as 'Price', item.quantity as 'Quantity' from item inner join supplier on supplier.supplier_id = item.supplier_id inner join classification1 on classification1.classification_id = item.classification_id LEFT JOIN item_price ON item_price.item_id = item.item_id Where item.name LIKE '"+jTextField26.getText()+"%'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable5.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
        
          jTable5.getColumnModel().getColumn(0).setPreferredWidth(30);
         jTable5.getColumnModel().getColumn(0).setMinWidth(50);
         jTable5.getColumnModel().getColumn(0).setMaxWidth(50);
         jTable5.getColumnModel().getColumn(0).setMaxWidth(50);
         jTable5.getColumnModel().getColumn(0).setMaxWidth(20);
         jTable5.getColumnModel().getColumn(0).setMaxWidth(20);      
    }
    
    public void ComputePercentagePrice(){
     String x = JOptionPane.showInputDialog(null, "Input percentage amount");
     per = Integer.parseInt(x);
        BigDecimal percentage = new BigDecimal(x);
        BigDecimal supplierprice= new BigDecimal(Supp_price.getText());
        BigDecimal y = new BigDecimal("100");
        
        BigDecimal answer = supplierprice.multiply(percentage).divide(y);
        
        BigDecimal finalans = supplierprice.add(answer);
        
        salePrice.setText(finalans.toString());
        
        jLabel46.setText("Percentage:" +" " +answer.toString());
        
       
    }
    public void ComputeValuePrice(){
             String x = JOptionPane.showInputDialog(null, "Input value amount");
        BigDecimal value = new BigDecimal(x);
        BigDecimal supplierprice= new BigDecimal(Supp_price.getText());
        BigDecimal y = new BigDecimal("100");
        
        BigDecimal answer = supplierprice.add(value);
        salePrice.setText(answer.toString());
         jLabel46.setText("Exact Value:"+" "+ x);
    }
    
    public void saveInItemPrice(){
        
        
        String string4 = jLabel40.getText();
        String[] parts4 = string4.split(" ");
        String p1 = parts4[0]; // 004
        String p2 = parts4[1]; // 034556
        try{
            String sql = "Insert into item_price (item_id,supplier_price, price) values (?,?,?)";
            ps=conn_db.prepareStatement(sql);
            ps.setString(1, Integer.toString(iID));
            ps.setString(2, Supp_price.getText());
            ps.setString(3, salePrice.getText());
            ps.execute();

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void setupdateItemPrice(){
        DefaultTableModel model3 = (DefaultTableModel) jTable5.getModel();
        String id = model3.getValueAt(jTable5.getSelectedRow(), 0).toString();
        System.out.println("ClassID: "+id);
        
        try{
            String sql = "Select supplier_price, price from item_price where item_id ='"+id+"'";

             ps = conn_db.prepareStatement(sql);
                    rs = ps.executeQuery();
                        while(rs.next()){
                           Supp_price.setText(rs.getString("supplier_price"));
                           salePrice.setText(rs.getString("price"));      
                           previousSupplierPrice = rs.getString("supplier_price");
                           previousSalePrice = rs.getString("price");
                           
                           System.out.println("Previous Price: "+previousSupplierPrice+"\n Previous Sale Price: "+previousSalePrice);
                        }
        }catch(Exception e){
           System.out.println("\""+e+"\""+"\nSome fileds on the DB have no data yet");
           
        }
    }
    public void updateItemPrice(){
         DefaultTableModel model6 = (DefaultTableModel) jTable5.getModel();
         String id1 = model6.getValueAt(jTable5.getSelectedRow(), 0).toString(); 
         System.out.println("ClassID: "+id1);
        try{
            String sql = "UPDATE item_price SET `supplier_price`=(?), `price`=(?) where item_id ='"+id1+"'";
             ps = conn_db.prepareStatement(sql);
             ps.setString(1,Supp_price.getText());
             ps.setString(2,salePrice.getText());
             ps.executeUpdate(); 
             
             JOptionPane.showMessageDialog(null, "Price has been sucessfully Updated", "Success", JOptionPane.INFORMATION_MESSAGE);
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void inserIntoIpriceTable(){
        String str = txt_calendar.getText();
            String[] parts = str.split(" ");
            String p1 = parts[0]; // 004
            String p2 = parts[1]; // 034556
            String p3 = parts[2]; // 034556

            day = p1;
            month = p2;
            year = p3;
            
            DefaultTableModel model7 = (DefaultTableModel) jTable5.getModel();
            getitemname = model7.getValueAt(jTable5.getSelectedRow(), 3).toString();
            
         try{
            String sql2 = "insert into iprice_history (item_name, inc_per, prevsupPrice, prevSalePrice, newsupp_price, newsale_price, dayUpdated, moUpdated, yrUpdated)values(?,?,?,?,?,?,?,?,?)";
            ps=conn_db.prepareStatement(sql2);
            ps.setString(1, getitemname);
            ps.setInt(2, per);
            ps.setString(3, previousSupplierPrice);
            ps.setString(4, previousSalePrice);
            ps.setString(5, Supp_price.getText());
            ps.setString(6, salePrice.getText());
            ps.setString(7, day);
            ps.setString(8, month);
            ps.setString(9, year);
            ps.execute();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e) ;
        }
    }
    
    public void UpdateIntemQty(){
       DefaultTableModel model6 = (DefaultTableModel) jTable5.getModel();
       String id1 = model6.getValueAt(jTable5.getSelectedRow(), 0).toString(); 
       System.out.println("ClassID: "+id1);
       String QB = jTextField24.getText() +" "+jComboBox16.getSelectedItem().toString();
       String QB1 = jTextField28.getText() +" "+ jComboBox15.getSelectedItem().toString();
       
   try{
                String sql1 = "UPDATE item SET `BigSmall`=(?),`qtyName`=(?), `volume`=(?),`numof`=(?),`quantity`=(?) where item_id='"+id1+"'";
                ps=conn_db.prepareStatement(sql1);

                ps.setString(1, jComboBox12.getSelectedItem().toString());
                ps.setString(2, QB);
                ps.setString(3, QB1);
                ps.setString(4, jTextField23.getText());
                ps.setString(5, jTextField29.getText());
                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Quantity has been sucessfully updated", "Success", JOptionPane.INFORMATION_MESSAGE);
                updateItemPrice();
                autoviewQtyPrice();
                resetQtyPrice();
              //normalModeQtyPrice();

            }catch(Exception e){
                System.out.println(e);
            }

     }
    
    public void setupdatePriceQty0nly(){  ///////////////////////////questin??????????????????????????????????????????????????????????

        
        getSupplierID2();
        getClassIdItem1();
        
        
        DefaultListModel dimx = new DefaultListModel();
        DefaultListModel dimx1 = new DefaultListModel();
   
          
            DefaultTableModel model3 = (DefaultTableModel) jTable5.getModel();
            String id = model3.getValueAt(jTable5.getSelectedRow(), 0).toString();
            System.out.println("item: "+id);
                try{
                    String sql = "SELECT supplier.name, classification1.name, item.name, BigSmall,qtyName, volume,numof,quantity FROM `item` inner join supplier on supplier.supplier_id = item.supplier_id inner join classification1 on classification1.classification_id = item.classification_id WHERE  item.item_id ='" + id +"'";
                  
                    ps = conn_db.prepareStatement(sql);
                    rs = ps.executeQuery();
                    
                        while(rs.next()){      
                            jComboBox13.setSelectedItem(rs.getString("supplier.name"));
                            dimx.addElement((rs.getString("classification1.name")));
                            class_item_list2.setModel(dimx);
                            dimx1.addElement((rs.getString("item.name")));
                            class_item_list1.setModel(dimx1);
                            jComboBox12.setSelectedItem(rs.getString("item.BigSmall"));
                            jTextField23.setText(rs.getString("item.numof"));
                            jTextField29.setText(rs.getString("item.quantity"));
 
                            String getqty= rs.getString("item.qtyName");
                            String[] string1=getqty.split(" ");
                            String p1 = string1[0]; // 004
                            String p2 = string1[1]; // 034556

                            String getqty1= rs.getString("item.volume");
                            String[] string2 =getqty1.split(" ");
                            String s1 = string2[0]; // 004
                            String s2 = string2[1]; // 034556

                            jTextField24.setText(p1);
                            jComboBox16.setSelectedItem(p2);
                            jTextField28.setText(s1);
                            jComboBox15.setSelectedItem(s2);

                             }
                }catch(Exception e){
                    System.out.println("\""+e+"\""+"\nSome fileds on the DB have no data yet");
                }
                
                jTextField13.setText("");
                jComboBox12.setSelectedItem("");
                jTextField24.setText("");
                jComboBox16.setSelectedItem("");
                jTextField28.setText("");
                jComboBox15.setSelectedItem("");
                jTextField29.setText("");
                jTextField23.setText("");
         
    }
     ////////////////////////////////////////////////////codes for order ////////////////////////////////////////////////
    
     public void normalModeOrder(){
         jButton11.setEnabled(true);
         jButton12.setEnabled(false);
         resetClassification();
     }
     
     public void updateModeOrder(){
         jButton11.setEnabled(false);
        jButton12.setEnabled(true);
     }
     
     public void resetOrder(){
         jTextField13.setText("");
         getSuppIDOrder = 0;
          getClassIDqty = 0;
          iID2 =0;
     }
     
      public void generateID(){
        String id = new SimpleDateFormat("yyMMddHHmmss").format(Calendar.getInstance().getTime());
       jLabel49.setText(id);
       jLabel49.setForeground(Color.blue);
    }
     
      public void getSupplierID3(){
            try{
            String sql1 ="select supplier_id from supplier where name='"+jComboBox14.getSelectedItem()+"'";
            ps=conn_db.prepareStatement(sql1);
            rs = ps.executeQuery();

            while(rs.next()){
             getSuppIDOrder = rs.getInt("supplier_id");
                System.out.println( getSuppIDOrder);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e); }
     }
     
      public void getClassIDorder(){
                try{
                       String sql1 ="select classification_id from classification1 where name='"+getClassName111+"'";
                       ps=conn_db.prepareStatement(sql1);
                       rs = ps.executeQuery();

                       while(rs.next()){
                           getClassIDqty = rs.getInt("classification_id");
                           //JOptionPane.showMessageDialog(null,getClassIDqty );
                           System.out.println(getClassIDqty); }
                   } catch(Exception e){
                       JOptionPane.showMessageDialog(null, e);}
      }
      
       public void getItemIDorder(){
        try{
                String sql1 ="select item_id from item where name='"+getClassName112+"'";
                ps=conn_db.prepareStatement(sql1);
                rs = ps.executeQuery();

                    while(rs.next()){
                     iID2 = rs.getInt("item_id");
                      System.out.println(iID2); }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e); }
       
       }
      
      public void suppIdItem1() {
        getSupplierID3();
         
          DefaultListModel dimx = new DefaultListModel();
        try{
            
            String sql="select * from classification1 where supplier_id= '"+ getSuppIDOrder +"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getname = rs.getString("name");  
                dimx.addElement(getname);
                class_item_list4.setModel(dimx);
            }    
        }catch(Exception e){     
            JOptionPane.showMessageDialog(null, e);
        }    
    }
     public void Jlist11(){
               String x = ((DefaultListModel)class_item_list4.getModel()).getElementAt(class_item_list4.getSelectedIndex()).toString();
                getClassName111 = x;
       }
        public void Jlist12(){
               String x = ((DefaultListModel)class_item_list3.getModel()).getElementAt(class_item_list3.getSelectedIndex()).toString();
                getClassName112 = x;
                
                getItemIDorder();
                 }
       
        
         public void getItemIdItem3() {
           getClassIDorder();
          
          DefaultListModel dimx1 = new DefaultListModel();
          try{
               String sql1 ="SELECT * FROM `item` INNER JOIN supplier ON supplier.supplier_id = item.supplier_id INNER JOIN classification1 ON `item`.`classification_id` = `classification1`.`classification_id` WHERE supplier.name ='"+jComboBox14.getSelectedItem().toString()+"' AND classification1.name = '"+class_item_list4.getSelectedValue().toString()+"'";
            ps=conn_db.prepareStatement(sql1);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getClassificationName = rs.getString("name");
                String getItemId = rs.getString("item.item_id");
                iID1.setText(getItemId);
                String getClassId = rs.getString("classification1.classification_id");
                cID.setText(getClassId);
               String getSupID = rs.getString("supplier.supplier_id");
               sID.setText(getSupID);
               dimx1.addElement(getClassificationName);
               class_item_list3.setModel(dimx1);
  
                }
          }catch(Exception e){
              JOptionPane.showMessageDialog(null, "Please select a classification name first!","Validation",JOptionPane.INFORMATION_MESSAGE);
          }
        }
      //SELECT order_item.order_id as 'ID',  order_item.order_num as 'order Number',CONCAT_WS(' ', order_day, order_month, order_year) AS `order Date`, supplier.name as 'Supplier' from order_item inner join supplier on supplier.supplier_id = order_item.supplier_id 
    public void autoviewQtyorder(){
         try{
            String sql = "SELECT order_item.order_id as 'ID',  order_item.order_num as 'order Number',CONCAT_WS(' ', order_day, order_month, order_year) AS `order Date`, supplier.name as 'Supplier' from order_item inner join supplier on supplier.supplier_id = order_item.supplier_id ";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable4.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
         
         jTable4.getColumnModel().getColumn(0).setPreferredWidth(30);
         jTable4.getColumnModel().getColumn(0).setMinWidth(50);
         jTable4.getColumnModel().getColumn(0).setMaxWidth(50);
         jTable4.getColumnModel().getColumn(0).setMaxWidth(50);
       
     }
    
    public void getUserID(){
        String x = setacc.getText();
        try{
            String sql = "select * from user where type = '"+x+"'";
            ps=conn_db.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                String getID = rs.getString("user_id");
                userID = getID;   
            }   
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }   
    }
    
    public void SaveOrder(){
        getSupplierID3();
        getClassIDorder();
        getItemIDorder();
        
         String string3 = txt_calendar.getText();
            String[] parts3 = string3.split(" ");
            String p1 = parts3[0]; // 004
            String p2 = parts3[1]; // 034556
            String p3 = parts3[2]; // 034556

            day = p1;
            month = p2;
            year = p3;
        

        try{
            String sql="insert into order_item( user_id,order_num,supplier_id, classification_id, item_id, order_day, order_month, order_year, "
                    + "order_qty, kind, unit_price, note)values(?,?,?,?,?,?,?,?,?,?,?,?)";
            ps=conn_db.prepareStatement(sql);

            ps.setString(1,userID);
            ps.setString(2,jLabel49.getText());
            ps.setString(3,Integer.toString(getSuppIDOrder));
            ps.setString(4,Integer.toString(getClassIDqty));
            ps.setString(5,Integer.toString(iID2));
            ps.setString(6,day);
            ps.setString(7,month);
            ps.setString(8,year);
            ps.setString(9,jTextField31.getText());
            ps.setString(10,jComboBox18.getSelectedItem().toString());
            ps.setString(11,jTextField30.getText());
            ps.setString(12,jTextArea1.getText());
            ps.execute();

            JOptionPane.showMessageDialog(null, "saved");
            autoviewQtyorder();

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
            updateModeOrder();
            resetOrder();
            autoviewQtyorder();
        }
        
    }
   public void autoSearchOrder(){
        try{
            String sql = "SELECT order_item.order_id as 'ID',  order_item.order_num as 'order Number',CONCAT_WS(' ', order_day, order_month, order_year) AS `order Date`, supplier.name as 'Supplier' from order_item inner join supplier on supplier.supplier_id = order_item.supplier_id   where supplier.name LIKE '"+jTextField21.getText()+"%'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable4.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
        
         jTable4.getColumnModel().getColumn(0).setPreferredWidth(30);
         jTable4.getColumnModel().getColumn(0).setMinWidth(50);
         jTable4.getColumnModel().getColumn(0).setMaxWidth(50);
         jTable4.getColumnModel().getColumn(0).setMaxWidth(50);
     
    } 
   
    public void setupdateOrder(){
        DefaultListModel dimx = new DefaultListModel();
        DefaultListModel dimx1 = new DefaultListModel();
        
         String string3 = txt_calendar.getText();
            String[] parts3 = string3.split(" ");
            String p1 = parts3[0]; // 004
            String p2 = parts3[1]; // 034556
            String p3 = parts3[2]; // 034556

            day = p1;
            month = p2;
            year = p3;
        
         DefaultTableModel model4 = (DefaultTableModel) jTable4.getModel();
         String id = model4.getValueAt(jTable4.getSelectedRow(), 0).toString();
         System.out.println("ClassID: "+id);
                try{
                    String sql = "SELECT order_num, supplier.name, classification1.name, item.name, order_qty,kind,unit_price,note FROM order_item inner join supplier on supplier.supplier_id = order_item.supplier_id inner join classification1 on classification1.classification_id = order_item.classification_id INNER JOIN item ON item.item_id = order_item.item_id WHERE  order_item.order_id ='" + id +"'";
                    ps = conn_db.prepareStatement(sql);
                    rs = ps.executeQuery();
                        while(rs.next()){
                            jLabel49.setText(rs.getString("order_num"));
                            jComboBox14.setSelectedItem(rs.getString("supplier.name"));
                            dimx.addElement((rs.getString("classification1.name")));
                            class_item_list4.setModel(dimx);
                            dimx1.addElement((rs.getString("item.name")));
                            class_item_list3.setModel(dimx1);
                            jTextField31.setText(rs.getString("order_qty"));
                            jComboBox18.setSelectedItem(rs.getString("kind"));
                            jTextField30.setText(rs.getString("unit_price"));
                            jTextArea1.setText(rs.getString("note")); 
                        }
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
                }
     public void updateOrder(){
         DefaultTableModel model3 = (DefaultTableModel) jTable4.getModel();
         String id = model3.getValueAt(jTable4.getSelectedRow(), 0).toString();
         try{
                String sql = "update order_item set `order_num` =(?),`supplier_id`=(?), `classification_id`=(?), `item_id`=(?), `order_qty`=(?), `kind`=(?), `unit_price`=(?), `note`=(?) where order_id ='" + id + "'";
                ps=conn_db.prepareStatement(sql);
                    ps.setString(1,jLabel49.getText());
                    ps.setString(2,Integer.toString(getSuppIDOrder));
                    ps.setString(3,Integer.toString(getClassIDqty));
                    ps.setString(4,Integer.toString(iID2));
                    ps.setString(5,jTextField31.getText());
                    ps.setString(6,jComboBox18.getSelectedItem().toString());
                    ps.setString(7,jTextField30.getText());
                    ps.setString(8,jTextArea1.getText());
                    ps.execute();
                
                JOptionPane.showMessageDialog(null, "Orderinformation has been sucessfully updated", "Success", JOptionPane.INFORMATION_MESSAGE);
                
            }catch(Exception e){

                JOptionPane.showMessageDialog(null, e);

            }
     }
     
     //////////////////////////////////////////////////////////////////////codes for receive///////////////////////
 
     
      public void getSupplierID4(){
            try{
            String sql1 ="select supplier_id from supplier where name='"+jComboBox23.getSelectedItem()+"'";
            ps=conn_db.prepareStatement(sql1);
            rs = ps.executeQuery();

            while(rs.next()){
             getSuppIDOrder1 = rs.getInt("supplier_id");
                System.out.println( getSuppIDOrder1);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e); }
     }
      
       public void suppIdItem2() {
        getSupplierID4();
         
          DefaultListModel dimx = new DefaultListModel();
        try{
            
            String sql="select * from classification1 where supplier_id= '"+ getSuppIDOrder1 +"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getname = rs.getString("name");  
                dimx.addElement(getname);
                class_item_list6.setModel(dimx);
            }    
        }catch(Exception e){     
            JOptionPane.showMessageDialog(null, e);
        }    
    }
        public void Jlist111(){
               String x = ((DefaultListModel)class_item_list6.getModel()).getElementAt(class_item_list6.getSelectedIndex()).toString();
                getClassName11111 = x;
       }
        public void Jlist122(){
               String x = ((DefaultListModel)class_item_list5.getModel()).getElementAt(class_item_list5.getSelectedIndex()).toString();
                getClassName1122 = x;
                
                getItemIDorder();
                 }
       
        public void getClassIDReceive(){
                try{
                       String sql1 ="select classification_id from classification1 where name='"+getClassName11111+"'";
                       ps=conn_db.prepareStatement(sql1);
                       rs = ps.executeQuery();

                       while(rs.next()){
                           getClassIDqty1 = rs.getInt("classification_id");
                           //JOptionPane.showMessageDialog(null,getClassIDqty );
                           System.out.println(getClassIDqty1); }
                   } catch(Exception e){
                       JOptionPane.showMessageDialog(null, e);}
      }
        
      public void getItemIdItem4() {
          getClassIDReceive();
          
          DefaultListModel dimx1 = new DefaultListModel();
          try{
               String sql1 ="SELECT * FROM `item` INNER JOIN supplier ON supplier.supplier_id = item.supplier_id INNER JOIN classification1 ON `item`.`classification_id` = `classification1`.`classification_id` WHERE supplier.name ='"+jComboBox23.getSelectedItem().toString()+"' AND classification1.name = '"+class_item_list6.getSelectedValue().toString()+"'";
            ps=conn_db.prepareStatement(sql1);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getClassificationName = rs.getString("name");
                String getItemId = rs.getString("item.item_id");
                iID1.setText(getItemId);
                String getClassId = rs.getString("classification1.classification_id");
                cID.setText(getClassId);
               String getSupID = rs.getString("supplier.supplier_id");
               sID.setText(getSupID);
               dimx1.addElement(getClassificationName);
               class_item_list5.setModel(dimx1);
  
                }
          }catch(Exception e){
              JOptionPane.showMessageDialog(null, "Please select a classification name first!","Validation",JOptionPane.INFORMATION_MESSAGE);
          }
        }
      
       public void autoviewQtyorderReceive(){
         try{
            String sql = "SELECT DISTINCT order_item_confirm.order_num as 'Order Number', supplier.name as 'Supplier' from order_item_confirm inner join supplier on supplier.supplier_id = order_item_confirm.supplier_id ";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable6.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
       
     }
       
       public void setupdateOrderReceive(){
        DefaultListModel dimx = new DefaultListModel();
        DefaultListModel dimx1 = new DefaultListModel(); 
        
         DefaultTableModel model4 = (DefaultTableModel) jTable6.getModel();
         String id = model4.getValueAt(jTable6.getSelectedRow(), 0).toString();
         System.out.println("ClassID: "+id);
                try{
                    String sql = "SELECT supplier.name, classification1.name, item.name, order_qty,unit_price,note FROM order_item inner join supplier on supplier.supplier_id = order_item.supplier_id inner join classification1 on classification1.classification_id = order_item.classification_id INNER JOIN item ON item.item_id = order_item.item_id WHERE  order_item.order_id ='" + id +"'";
                    ps = conn_db.prepareStatement(sql);
                    rs = ps.executeQuery();
                        while(rs.next()){
                            jComboBox23.setSelectedItem(rs.getString("supplier.name"));
                            dimx.addElement((rs.getString("classification1.name")));
                            class_item_list6.setModel(dimx);
                            dimx1.addElement((rs.getString("item.name")));
                            class_item_list5.setModel(dimx1);
                           jLabel27.setText(rs.getString("order_qty"));
                          // jComboBox18.setSelectedItem(rs.getString("kind"));
                           Supp_price1.setText(rs.getString("unit_price"));
                           jTextArea2.setText(rs.getString("note")); }
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, e);
                }
                }

      public void biggerVolumeOn1(){
                    jComboBox19.setEnabled(true);
                    jTextField33.setEnabled(true);
                    jComboBox22.setEnabled(true);
                    jTextField35.setEnabled(true);
                    jComboBox21.setEnabled(true);
                    jTextField32.setEnabled(true);           
      } public void piecesOn1(){
                     jComboBox19.setEnabled(false);
                    jTextField33.setEnabled(true);
                    jComboBox22.setEnabled(true);
                    jTextField35.setEnabled(true);
                    jComboBox21.setEnabled(true);
                    jTextField32.setEnabled(false);           
      }
      public void calculateTotalQty1(){
                    BigDecimal qty = new BigDecimal(jTextField24.getText());
                    BigDecimal num = new BigDecimal(jTextField23.getText());
                    BigDecimal ans = qty.multiply(num);
                    jTextField29.setText(ans.toString());
      }
      public void RadioButtonClinical1(){
                jComboBox5.setEnabled(true); 
                RdSale1.setEnabled(true);
         
            if(rdClinical1.isSelected()){
                RdSale1.setSelected(false);
                saleUse2="Clinical";}
                }
      
     public void RadioButtonSal(){
                rdClinical1.setEnabled(true);
                 jComboBox5.setEnabled(false); 

               if(RdSale1.isSelected()){
                 rdClinical1.setSelected(false);
                 saleUse2="Sale";}
                }
     
      public void validateManufactringDate1(){
            SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
            String dt = dateFormat.format(jCalendar3.getDate());

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
        
            String[] current = txt_calendar.getText().split(" ");
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
        
                if(dtyear>curyear){
                    JOptionPane.showMessageDialog(null, "Invalid Year Detected!", "Mfg. Date Validation", JOptionPane.ERROR_MESSAGE);
                    jLabel24.setForeground(Color.red);
                    receiveMD.setText("");
                }else{
                    if(dtyear<curyear){
                        selectedDate = dt;
                        jLabel24.setForeground(Color.black);
                    }else{
                        if(dtyear==curyear){
                            if(dtmonth>curmonth){
                                JOptionPane.showMessageDialog(null, "Invalid Month Detected!", "Mfg. Date Validation", JOptionPane.ERROR_MESSAGE);
                                jLabel24.setForeground(Color.red);
                                receiveMD.setText("");
                            }else{
                                if(dtmonth==curmonth){
                                    if(dtday>curday){
                                        JOptionPane.showMessageDialog(null, "Invalid Day Detected!", "Mfg. Date Validation", JOptionPane.ERROR_MESSAGE);
                                        jLabel24.setForeground(Color.red);
                                        receiveMD.setText("");
                                        }else{
                                           selectedDate = dt;
                                           jLabel24.setForeground(Color.black);
                                        }
                                    }else{
                                        if(dtmonth<curmonth){
                                            selectedDate = dt;
                                            jLabel24.setForeground(Color.black);
                                        }
                                    }
                                }
                            }
                    }
                }
            }
      
      public void validateManufactringDate1MY(){
            SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
            String dt = dateFormat.format(jCalendar3.getDate());

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
        
            String[] current = txt_calendar.getText().split(" ");
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
        
                if(dtyear>curyear){
                    JOptionPane.showMessageDialog(null, "Invalid Year Detected!", "Mfg. Date Validation", JOptionPane.ERROR_MESSAGE);
                    jLabel24.setForeground(Color.red);
                    receiveMD.setText("");
                }else{
                    if(dtyear<curyear){
                        selectedDate = dt;
                        jLabel24.setForeground(Color.black);
                    }else{
                        if(dtyear==curyear){
                            if(dtmonth>curmonth){
                                JOptionPane.showMessageDialog(null, "Invalid Month Detected!", "Mfg. Date Validation", JOptionPane.ERROR_MESSAGE);
                                jLabel24.setForeground(Color.red);
                                receiveMD.setText("");
                            }else{
                                if(dtmonth==curmonth){
                                        selectedDate = dt;
                                        jLabel24.setForeground(Color.black);
                                    }else{
                                        if(dtmonth<curmonth){
                                            selectedDate = dt;
                                            jLabel24.setForeground(Color.black);
                                        }
                                    }
                                }
                            }
                    }
                }
            }
      
      public void vaildateExpirationDate1(){
            SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
             String dt = dateFormat.format(jCalendar3.getDate());
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

            String[] current = receiveMD.getText().split(" ");
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
                    JOptionPane.showMessageDialog(null, "Invalid Year Detected!", "Exp. Date Validation", JOptionPane.ERROR_MESSAGE);
                    jLabel25.setForeground(Color.red);
                    receiveXD.setText("");
                }else{
                    if(dtyear>curyear){
                        selectedDate = dt;
                        jLabel24.setForeground(Color.black);
                    }else{
                    if(dtmonth<curmonth){
                        JOptionPane.showMessageDialog(null, "Invalid Month Detected!", "Exp. Date Validation", JOptionPane.ERROR_MESSAGE);
                        jLabel25.setForeground(Color.red);
                        receiveXD.setText("");
                    }else{
                        if(dtmonth==curmonth){
                            if(dtday<curday){
                                JOptionPane.showMessageDialog(null, "Invalid Day Detected!", "Exp. Date Validation", JOptionPane.ERROR_MESSAGE);
                                jLabel25.setForeground(Color.red);
                                receiveXD.setText("");
                                }else{
                                   selectedDate = dt;
                                   jLabel24.setForeground(Color.black);
                                }
                            }else{
                                if(dtmonth>curmonth){
                                    selectedDate = dt;
                                    jLabel24.setForeground(Color.black);
                                }
                            }
                        }
                    }
                }
      }
      
      public void vaildateExpirationDate1MY(){
            SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
             String dt = dateFormat.format(jCalendar3.getDate());
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

            String[] current = receiveMD.getText().split(" ");
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
                    JOptionPane.showMessageDialog(null, "Invalid Year Detected!", "Exp. Date Validation", JOptionPane.ERROR_MESSAGE);
                    jLabel24.setForeground(Color.red);
                    receiveXD.setText("");
                }else{
                    if(dtyear>curyear){
                        selectedDate = dt;
                        jLabel24.setForeground(Color.black);
                    }else{
                        if(dtyear==curyear){
                            if(dtmonth<curmonth){
                                JOptionPane.showMessageDialog(null, "Invalid Month Detected!", "Exp. Date Validation", JOptionPane.ERROR_MESSAGE);
                                jLabel24.setForeground(Color.red);
                                receiveXD.setText("");
                            }else{
                                if(dtmonth>=curmonth){
                                        selectedDate = dt;
                                        jLabel24.setForeground(Color.black);
                                    }
                                }
                        }
                    
                    }
                }
      }
      
       public void saveReceive() {
                if(Txt_item.getText().equals("") || Txt_item.getText().equals("") ){
                    JOptionPane.showMessageDialog(null, "Empty field detected!");
                }else{   
                        try{
                       String sql="insert into receive_item( supplier_id, order_id, deliver, invoice, classification_id, item_id, CliSale, ForCheck,bigSmall, qtyname,Volume,Numof,qty, remainQty, manu_day, expi_day,status, receive_day, receive_month,receive_year)values(?,?,?,?,?,?,?,?)";
                       ps=conn_db.prepareStatement(sql);

                       ps.setString(1,Integer.toString(getSuppId));
                       ps.setString(2,Integer.toString(getClassID));
                       ps.setString(3,Txt_item.getText().toUpperCase());
                       ps.setString(4,saleUse1);
                       ps.setString(5,jComboBox3.getSelectedItem().toString());
                       ps.setString(6, jLabel18.getText());
                       ps.setString(7, jLabel2.getText());
                       ps.setString(8,jComboBox10.getSelectedItem().toString());

                       ps.execute();

                       JOptionPane.showMessageDialog(null, "Item "+Txt_item.getText().toUpperCase()+" has been sucessfully saved");
                       resetItem();
                       autoviewCItem();
                        }
                        catch (Exception e){
                            JOptionPane.showMessageDialog(null, e); }
                        } 
                    }
       
       public void autoviewItemExpDate(){
         try{
            String sql = "SELECT item.item_id as `ID`, item.name as `Item Name`,supplier.name as `Supplier`, CONCAT_WS(' ', item.`manufacturing_day`,item.`manufacturing_month`,item.`manufacturing_year`) as `Manufactured Date`, CONCAT_WS(' ',item.`expiration_day`,item.`expiration_month`,item.`expiration_year`) as `Expiration Date` FROM `item` inner join supplier on item.`supplier_id` =  supplier.`supplier_id` where !item.`expiration_year`='0'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable7.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
         
         jTable7.getColumnModel().getColumn(0).setPreferredWidth(30);
         jTable7.getColumnModel().getColumn(0).setMinWidth(50);
         jTable7.getColumnModel().getColumn(0).setMaxWidth(70);
     }
      
      public void getRemainingDate(){
          try{
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
              
            DefaultTableModel model=(DefaultTableModel) jTable7.getModel();
            String expdate = model.getValueAt(jTable7.getSelectedRow(), 4).toString();
            
            String[] exp = expdate.split(" ");
            String expday = exp[0];
            int expd = Integer.parseInt(expday);
            
            String expmonth = exp[1];
            int expm = 0;
            
            String expyear = exp[2];
            int expy = Integer.parseInt(expyear);
            
            if(expmonth.equals("JAN")){
            expm = jan;
            }
            if(expmonth.equals("FEB")){
                expm = feb;
            }
            if(expmonth.equals("MAR")){
                expm = mar;
            }
            if(expmonth.equals("APR")){
                expm = apr;
            }
            if(expmonth.equals("MAY")){
                expm = may;
            }
            if(expmonth.equals("JUN")){
                expm = jun;
            }
            if(expmonth.equals("JUL")){
                expm = jul;
            }
            if(expmonth.equals("AUG")){
                expm = aug;
            }
            if(expmonth.equals("SEP")){
                expm = sep;
            }
            if(expmonth.equals("OCT")){
                expm = oct;
            }
            if(expmonth.equals("NOV")){
                expm = nov;
            }
            if(expmonth.equals("DEC")){
                expm = dec;
            }
            
            JOptionPane.showMessageDialog(null, expd+" "+expm+" "+expy);
            
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            
            String timeStamp = new SimpleDateFormat("dd M yyyy").format(Calendar.getInstance().getTime());
            
            String[] parts3 = timeStamp.split(" ");
            String day = parts3[0];int curd = Integer.parseInt(day);
            String month = parts3[1];int curm = Integer.parseInt(month);
            String year = parts3[2];int cury = Integer.parseInt(year);
            
            int ansday = expd-curd;
            int ansmonth = expm-curm;
            int ansyear = expy-cury;
            
            
            
          }catch(Exception e){
              System.out.println("Selected item has no dates yet");
          }
      }
      
      public void checkDates(){
        try{
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
            String[] exp = expdate.split(" ");
            String expday = exp[0];
            int expd = Integer.parseInt(expday);
            
            String expmonth = exp[1];
            int expm = 0;
            
            String expyear = exp[2];
            int expy = Integer.parseInt(expyear);
            
            if(expmonth.equals("JAN")){
            expm = jan;
            }
            if(expmonth.equals("FEB")){
                expm = feb;
            }
            if(expmonth.equals("MAR")){
                expm = mar;
            }
            if(expmonth.equals("APR")){
                expm = apr;
            }
            if(expmonth.equals("MAY")){
                expm = may;
            }
            if(expmonth.equals("JUN")){
                expm = jun;
            }
            if(expmonth.equals("JUL")){
                expm = jul;
            }
            if(expmonth.equals("AUG")){
                expm = aug;
            }
            if(expmonth.equals("SEP")){
                expm = sep;
            }
            if(expmonth.equals("OCT")){
                expm = oct;
            }
            if(expmonth.equals("NOV")){
                expm = nov;
            }
            if(expmonth.equals("DEC")){
                expm = dec;
            }
            
            
            
            ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            
            String timeStamp = new SimpleDateFormat("dd M yyyy").format(Calendar.getInstance().getTime());
            
            String[] parts3 = timeStamp.split(" ");
            String day = parts3[0];int curd = Integer.parseInt(day);
            String month = parts3[1];int curm = Integer.parseInt(month);
            String year = parts3[2];int cury = Integer.parseInt(year);
            
            int ansday = expd-curd;
            int ansmonth = expm-curm;
            int ansyear = expy-cury;

            
            if(ansyear <= 0 && expm<curm && expd<curd){
                
                DefaultTableModel model1 = (DefaultTableModel) jTable7.getModel();
                int rows1 = model1.getRowCount();
                for (int i = rows1 - 1; i >= 0; i--) {
                    model1.removeRow(i);
                }
                DefaultTableModel model2 = (DefaultTableModel) jTable7.getModel();
                model2.addRow(new Object[]{itemid,itemname,suppname,mnfgdate,expdate});
            }else{
                System.out.println("\n"+ansday+" day(s) "+ansmonth+" month(s) and "+ansyear+" year(s) remaining");
            }
            
          }catch(Exception e){
              System.out.println("Selected item has no dates yet");
          }
    }
      
      public void checkExpirationDate(){//
   
        try{
            String sql = "SELECT item.item_id as ID,CONCAT_WS(' ',item.`manufacturing_day`,item.`manufacturing_month`,item.`manufacturing_year`) as manufDate, CONCAT_WS(' ',item.`expiration_day`,item.`expiration_month`,item.`expiration_year`) as Date, item.name as Name, supplier.name as SuppName FROM `item` inner join supplier on item.`supplier_id` =  supplier.`supplier_id` where !item.`expiration_year` = '0'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                 expdate = rs.getString("Date");
                 itemname = rs.getString("Name");
                 itemid = rs.getString("ID");
                 mnfgdate = rs.getString("manufDate");
                 suppname = rs.getString("SuppName");
                 checkDates();
            }
            
            jTable7.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable7.getColumnModel().getColumn(0).setMinWidth(50);
            jTable7.getColumnModel().getColumn(0).setMaxWidth(70);
            
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
    }
      
      public void refreshOrder(){
        for(int x = 0;x<=class_item_list4.getModel().getSize()-1;x++){
            ((DefaultListModel) class_item_list4.getModel()).remove(x);
        }
        
        for(int x = 0;x<=class_item_list3.getModel().getSize()-1;x++){
            ((DefaultListModel) class_item_list3.getModel()).remove(x);
        }
        jTextField31.setText("");
        jComboBox18.setSelectedIndex(0);
        jTextField30.setText("");
        jTextArea1.setText("");
    }
      
      public void addpettype(){
          try{
            jComboBox10.removeAllItems();
        }catch(Exception e){
            //
        }
        jComboBox10.addItem("");
        try{
            String sql = "SELECT * FROM `pet_type`";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                String getType = rs.getString("pet_type_name");
                jComboBox10.addItem(getType);
            }

        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
   }
      
      public void showItemOrdered(){
         DefaultTableModel model4 = (DefaultTableModel) jTable6.getModel();
         String ordernum = model4.getValueAt(jTable6.getSelectedRow(), 0).toString();
          try{
            String sql = "SELECT order_item_confirm.order_id as ID, item.name, UPPER(CONCAT_WS(' ', order_item_confirm.order_day,order_item_confirm.order_month,order_item_confirm.order_year)) as Date  FROM `order_item_confirm` inner join item on order_item_confirm.item_id = item.item_id where order_num = '"+ordernum+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable9.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
           JOptionPane.showMessageDialog(null, e);
        }
         
         jTable9.getColumnModel().getColumn(0).setPreferredWidth(20);
         jTable9.getColumnModel().getColumn(0).setMinWidth(50);
         jTable9.getColumnModel().getColumn(0).setMaxWidth(50);
         jTable9.getColumnModel().getColumn(0).setMaxWidth(50);
      }
      
}