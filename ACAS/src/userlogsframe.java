import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import login.connection_db;


public class userlogsframe extends javax.swing.JFrame {

  Connection conn_db = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    String SearchDate;
    String SearchMonth;
    
    String day;
    String month;
    String year;
    
    public userlogsframe() {
        initComponents();
        conn_db = connection_db.ConnectDB();
  
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0,0,screenSize.width, screenSize.height);
        setVisible(true);
        timepanel.setVisible(false);
        userpanel.setVisible(false);
        datepanel.setVisible(false);
        userlblpanel.setVisible(false);
        autoView();
        showontbl();
    }
public void autoView(){    
         try{
             String sql = "SELECT user_logs.`log_id` as ID, user.type as User, CONCAT_WS(' ',user_logs.`day`,user_logs.`month`,user_logs.`year`) as Date, CONCAT_WS('',user_logs.`hour`,':',user_logs.`minute`,' ',user_logs.`am_pm`) as Time FROM user_logs inner join user on user_logs.user_id = user.user_id WHERE `log_id` >= '"+userview.getText()+"' ORDER BY `log_id` LIMIT 0,10";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             jTable1.setModel(DbUtils.resultSetToTableModel(rs));         
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
    }

public void autoViewMonthOnly(){   
          
    try{
             String sql = "SELECT user_logs.`log_id` as ID, user.type as User, CONCAT_WS(' ',user_logs.`day`,user_logs.`month`,user_logs.`year`) as Date, CONCAT_WS('',user_logs.`hour`,':',user_logs.`minute`,' ',user_logs.`am_pm`) as Time FROM user_logs inner join user on user_logs.user_id = user.user_id WHERE `user_logs`.`month`= '"+month+"' ORDER BY `log_id` LIMIT 0,10";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             jTable1.setModel(DbUtils.resultSetToTableModel(rs));         
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
    }

public void autoViewYearOnly(){    
    
     try{
             String sql = "SELECT user_logs.`log_id` as ID, user.type as User, CONCAT_WS(' ',user_logs.`day`,user_logs.`month`,user_logs.`year`) as Date, CONCAT_WS('',user_logs.`hour`,':',user_logs.`minute`,' ',user_logs.`am_pm`) as Time FROM user_logs inner join user on user_logs.user_id = user.user_id WHERE `user_logs`.`year`= '"+year+"' ORDER BY `log_id` LIMIT 0,10";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             jTable1.setModel(DbUtils.resultSetToTableModel(rs));  
             
          
         }
     
     catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
    }
    

public void autoViewcompletedate(){    
    
     try{
             String sql = "SELECT user_logs.`log_id` as ID, user.type as User, CONCAT_WS(' ',user_logs.`day`,user_logs.`month`,user_logs.`year`) as Date, CONCAT_WS('',user_logs.`hour`,':',user_logs.`minute`,' ',user_logs.`am_pm`) as Time FROM user_logs inner join user on user_logs.user_id = user.user_id WHERE (`user_logs`.`day`= '"+day+"' and `user_logs`.`month`='"+month+"') and `user_logs`.`year`='"+year+"' ORDER BY `log_id` LIMIT 0,10";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             jTable1.setModel(DbUtils.resultSetToTableModel(rs));         
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
    }
    
        
//    public void fillTable(){
//  
//        try{
//            String sql1= "SELECT DISTINCT CONCAT_WS (' ', `day`,`month`,`year`) AS `Date`," +
//            "CONCAT_WS (' ', hour, minute, am_pm) AS `Time`," +
//            "`user`.`type` AS `User Name`, user.username AS `Name`" +
//            "FROM `user_logs` INNER JOIN `user` ON `user`.user_id = `user_logs`.`user_id`" +
//            " WHERE `day`='"+date.getDate().toString()+"' AND `month`='"+date.getDate().toString()+"'  AND `year`='"+date.getDate().toString()+"' AND `hour`='"+hour.getText()+"' AND `minute`='"+min.getText()+"' AND `am_pm`='"+meri.getSelectedItem().toString()+"'";
//            ps = conn_db.prepareStatement(sql1);
//           rs = ps.executeQuery();
//            
//            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
//            
//        }
//        catch(Exception e){
//        JOptionPane.showMessageDialog(null, e);
//        }
//    }
//    
     public void showontbl(){
        try{
             String sql = "SELECT user_logs.`log_id` as ID, user.type as User, CONCAT_WS(' ',user_logs.`day`,user_logs.`month`,user_logs.`year`) as Date, CONCAT_WS('',user_logs.`hour`,':',user_logs.`minute`,' ',user_logs.`am_pm`) as Time FROM user_logs inner join user on user_logs.user_id = user.user_id WHERE `log_id` >= '"+userview.getText()+"' ORDER BY `log_id` LIMIT 0,10";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             jTable1.setModel(DbUtils.resultSetToTableModel(rs));         
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
        
    }
    
     public void showontblprev(){
        try{
             String sql = "SELECT user_logs.`log_id` as ID, user.type as User, CONCAT_WS(' ',user_logs.`day`,user_logs.`month`,user_logs.`year`) as Date, CONCAT_WS('',user_logs.`hour`,':',user_logs.`minute`,' ',user_logs.`am_pm`) as Time FROM user_logs inner join user on user_logs.user_id = user.user_id WHERE `log_id` between '"+jLabel2.getText()+"' and '"+jLabel4.getText()+"' ORDER BY `log_id` LIMIT 0,10";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             jTable1.setModel(DbUtils.resultSetToTableModel(rs));         
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
    }
    
    public void getnextid(){
        jButton2.setEnabled(true);
   
        try{

           int row = jTable1.getRowCount();
        userview.setText(jTable1.getValueAt(row-1, 0).toString());

            int getbase11 = Integer.parseInt(jTable1.getValueAt(row-1, 0).toString());
            jLabel4.setText(Integer.toString(getbase11));

            int getbase22 = getbase11-9;
            jLabel2.setText(Integer.toString(getbase22));
            
            if(Integer.parseInt(jLabel2.getText())<=2){
            jButton2.setEnabled(false);
            userview.setText("1");
            }
            
            
       
        }catch(Exception e){
            
        }

    }
  
    public void getprevid(){
        
        try{
            int row = jTable1.getRowCount();
            userview.setText(jTable1.getValueAt(row-10, 0).toString());

            int getbase1 = Integer.parseInt(jTable1.getValueAt(row-10, 0).toString());
            jLabel4.setText(Integer.toString(getbase1));

            int getbase2 = getbase1-9;
            jLabel2.setText(Integer.toString(getbase2));
            
            if(Integer.parseInt(jLabel2.getText())<=2){
            jButton2.setEnabled(false);
            userview.setText("1");
            }
        }catch(Exception e){
            
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

        choosebtn = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        userpanel = new javax.swing.JPanel();
        cmbuser = new javax.swing.JComboBox();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        timepanel = new javax.swing.JPanel();
        meri = new javax.swing.JComboBox();
        colon = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        hour = new javax.swing.JTextField();
        min = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        datepanel = new javax.swing.JPanel();
        date = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jButton8 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        userlblpanel = new javax.swing.JPanel();
        userview = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        choosebtn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---Choose Here---", "Date", "Time", "User" }));
        choosebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                choosebtnActionPerformed(evt);
            }
        });

        jLabel9.setText("Search by:");

        userpanel.setBorder(new javax.swing.border.MatteBorder(null));

        cmbuser.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ASISSTANT VETERINARIAN", "VETERINARIAN" }));

        jButton4.setText("Search");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel3.setText("User");

        javax.swing.GroupLayout userpanelLayout = new javax.swing.GroupLayout(userpanel);
        userpanel.setLayout(userpanelLayout);
        userpanelLayout.setHorizontalGroup(
            userpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userpanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(userpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbuser, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        userpanelLayout.setVerticalGroup(
            userpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(userpanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(cmbuser, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        timepanel.setBorder(new javax.swing.border.MatteBorder(null));

        meri.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "AM", "PM" }));

        colon.setText(":");

        jLabel5.setText("Hour");

        jLabel6.setText("Minute");

        jLabel7.setText("Time");

        jLabel8.setText("Meridiem");

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton6.setText("View All");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout timepanelLayout = new javax.swing.GroupLayout(timepanel);
        timepanel.setLayout(timepanelLayout);
        timepanelLayout.setHorizontalGroup(
            timepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(timepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(timepanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(timepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(timepanelLayout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(timepanelLayout.createSequentialGroup()
                                .addGroup(timepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(timepanelLayout.createSequentialGroup()
                                        .addComponent(hour, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addGroup(timepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(colon, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(timepanelLayout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(min, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGroup(timepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(timepanelLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(meri, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(timepanelLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        timepanelLayout.setVerticalGroup(
            timepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(timepanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(timepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(timepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hour, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(colon, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(min, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(meri, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(timepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        datepanel.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel11.setText("Date");

        jButton5.setText("Search");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jRadioButton1.setText("Month Only");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("Year Only");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jButton8.setText("View All");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout datepanelLayout = new javax.swing.GroupLayout(datepanel);
        datepanel.setLayout(datepanelLayout);
        datepanelLayout.setHorizontalGroup(
            datepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datepanelLayout.createSequentialGroup()
                .addGroup(datepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(datepanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(datepanelLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(datepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton1)
                            .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(datepanelLayout.createSequentialGroup()
                                .addGroup(datepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jRadioButton2)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        datepanelLayout.setVerticalGroup(
            datepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(datepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(datepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jButton5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("NEXT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton7.setText("PREVIOUS");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel2.setText("51");

        jLabel12.setText("Displaying:");

        jLabel4.setText("60");

        jLabel13.setText("to");

        userlblpanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        userview.setText("0");

        javax.swing.GroupLayout userlblpanelLayout = new javax.swing.GroupLayout(userlblpanel);
        userlblpanel.setLayout(userlblpanelLayout);
        userlblpanelLayout.setHorizontalGroup(
            userlblpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userlblpanelLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(userview, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        userlblpanelLayout.setVerticalGroup(
            userlblpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, userlblpanelLayout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(userview, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(userlblpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(choosebtn, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(datepanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(timepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(userpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(351, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(choosebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(datepanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(timepanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(userlblpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton7)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel13)
                    .addComponent(jLabel4))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void choosebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_choosebtnActionPerformed
        if(choosebtn.getSelectedIndex()==0){
            timepanel.setVisible(false);
            userpanel.setVisible(false);
            datepanel.setVisible(false);
            
        }
        else if(choosebtn.getSelectedIndex()==1){

            datepanel.setVisible(true);
            timepanel.setVisible(false);
            userpanel.setVisible(false);
           
           
            
        }
        else if(choosebtn.getSelectedIndex()==2)
        {
            timepanel.setVisible(true);
            userpanel.setVisible(false);
            datepanel.setVisible(false);
          
        }
        else if(choosebtn.getSelectedIndex()==3){
            userpanel.setVisible(true);
            timepanel.setVisible(false);
            datepanel.setVisible(false);
          

        }
        

    }//GEN-LAST:event_choosebtnActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

 try{
             String sql = "SELECT user_logs.`log_id` as ID, user.type as User, CONCAT_WS(' ',user_logs.`day`,user_logs.`month`,user_logs.`year`) as Date, CONCAT_WS('',user_logs.`hour`,':',user_logs.`minute`,' ',user_logs.`am_pm`) as Time FROM user_logs inner join user on user_logs.user_id = user.user_id WHERE `user`.`type`='"+cmbuser.getSelectedItem().toString()+"' ORDER BY `log_id` LIMIT 0,10";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             jTable1.setModel(DbUtils.resultSetToTableModel(rs));  
            
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

try{
             String sql = "SELECT user_logs.`log_id` as ID, user.type as User, CONCAT_WS(' ',user_logs.`day`,user_logs.`month`,user_logs.`year`) as Date, CONCAT_WS('',user_logs.`hour`,':',user_logs.`minute`,' ',user_logs.`am_pm`) as Time FROM user_logs inner join user on user_logs.user_id = user.user_id WHERE `hour`='"+hour.getText()+"' AND `minute`='"+min.getText()+"' AND `am_pm`='"+meri.getSelectedItem().toString()+"' ORDER BY `log_id` LIMIT 0,10";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             jTable1.setModel(DbUtils.resultSetToTableModel(rs));  
             hour.setText("");
             min.setText("");
             
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
        String dob = dateFormat.format(date.getDate());
        SearchDate=dob;
       date.setDate(null);
        
        
        String string = SearchDate;
        String[] parts = string.split(" ");
        String p1 = parts[0]; // 004
        String p2 = parts[1]; // 034556
        String p3 = parts[2]; // 034556

        day = p1;
        month = p2.toUpperCase();
        year = p3;
        
        if(jRadioButton1.isSelected()){
            jRadioButton2.setSelected(false);
            ///monthonly
            autoViewMonthOnly();
            getnextid();
        }else{
            if(jRadioButton2.isSelected()){
            jRadioButton1.setSelected(false);
            ////year only
            autoViewYearOnly();
            getnextid();
//            }else{
//                ////whole date search
//                autoViewcompletedate();
//                
            }
            }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        jRadioButton2.setSelected(false);
       
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        jRadioButton1.setSelected(false);
        
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
         try{
             String sql = "SELECT user_logs.`log_id` as ID, user.type as User, CONCAT_WS(' ',user_logs.`day`,user_logs.`month`,user_logs.`year`) as Date, CONCAT_WS('',user_logs.`hour`,':',user_logs.`minute`,' ',user_logs.`am_pm`) as Time FROM user_logs inner join user on user_logs.user_id = user.user_id WHERE `log_id` >= '"+userview.getText()+"' ORDER BY `log_id` LIMIT 0,10";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             jTable1.setModel(DbUtils.resultSetToTableModel(rs)); 
             date.setDate(null);
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       showontbl();
        getnextid();
        if (jTable1.getRowCount()<10){
            JOptionPane.showMessageDialog(null, "End of file");
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        getprevid();
        showontblprev();
        if (jTable1.getRowCount()<10){
            JOptionPane.showMessageDialog(null, "Beginning of file");
        }
       
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
       try{
             String sql = "SELECT user_logs.`log_id` as ID, user.type as User, CONCAT_WS(' ',user_logs.`day`,user_logs.`month`,user_logs.`year`) as Date, CONCAT_WS('',user_logs.`hour`,':',user_logs.`minute`,' ',user_logs.`am_pm`) as Time FROM user_logs inner join user on user_logs.user_id = user.user_id WHERE `log_id` >= '"+userview.getText()+"' ORDER BY `log_id` LIMIT 0,10";
             ps = conn_db.prepareStatement(sql);
             rs = ps.executeQuery();
             jTable1.setModel(DbUtils.resultSetToTableModel(rs));         
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
    }//GEN-LAST:event_jButton8ActionPerformed

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
            java.util.logging.Logger.getLogger(userlogsframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(userlogsframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(userlogsframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(userlogsframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new userlogsframe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox choosebtn;
    private javax.swing.JComboBox cmbuser;
    private javax.swing.JLabel colon;
    private com.toedter.calendar.JDateChooser date;
    private javax.swing.JPanel datepanel;
    private javax.swing.JTextField hour;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox meri;
    private javax.swing.JTextField min;
    private javax.swing.JPanel timepanel;
    private javax.swing.JPanel userlblpanel;
    private javax.swing.JPanel userpanel;
    private javax.swing.JLabel userview;
    // End of variables declaration//GEN-END:variables
}
