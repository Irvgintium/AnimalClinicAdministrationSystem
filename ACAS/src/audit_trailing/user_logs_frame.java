/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package audit_trailing;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import login.connection_db;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author irv
 */
public class user_logs_frame extends javax.swing.JFrame {

    Connection conn_db = null;
    ResultSet rs = null;
    PreparedStatement ps = null;

    /**
     * Creates new form audit_trail_frame
     */
    public user_logs_frame() {
        initComponents();
        jSpinner2.setValue(1);
        jSpinner1.setValue(1);
        produceYear();
        //for centered frame////
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        //

        conn_db = connection_db.ConnectDB();
        showontbl();
        getnextid();
        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
        
        jSpinner1.setEnabled(false);
        jSpinner2.setEnabled(false);
    }
    
    public void reset() {
        try {
            String sql = "SELECT user_logs.`log_id` as ID, user.type as User, `login_name` as Action,CONCAT_WS(' ',user_logs.`day`,user_logs.`month`,user_logs.`year`) as Date, CONCAT_WS('',user_logs.`hour`,':',user_logs.`minute`,' ',user_logs.`am_pm`) as Time FROM user_logs inner join user on user_logs.user_id = user.user_id ORDER BY `log_id` LIMIT 0,20";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(0).setMinWidth(50);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(70);
    }

    public void showontbl() {
        try {
            String sql = "SELECT user_logs.`log_id` as ID, user.type as User, `login_name` as Action,CONCAT_WS(' ',user_logs.`day`,user_logs.`month`,user_logs.`year`) as Date, CONCAT_WS('',user_logs.`hour`,':',user_logs.`minute`,' ',user_logs.`am_pm`) as Time FROM user_logs inner join user on user_logs.user_id = user.user_id where log_id >= '"+jLabel1.getText()+"' ORDER BY `log_id` LIMIT 0,20";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(0).setMinWidth(50);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(70);
    }

    public void showontblprev() {
        try {
            String sql = "SELECT user_logs.`log_id` as ID, user.type as User, `login_name` as Action,CONCAT_WS(' ',user_logs.`day`,user_logs.`month`,user_logs.`year`) as Date, CONCAT_WS('',user_logs.`hour`,':',user_logs.`minute`,' ',user_logs.`am_pm`) as Time FROM user_logs inner join user on user_logs.user_id = user.user_id WHERE log_id between '" + jLabel2.getText() + "' and '" + jLabel3.getText() + "' ORDER BY log_id LIMIT 0,20";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(0).setMinWidth(50);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(70);
    }

    public void getnextid() {
        try {
            jButton2.setEnabled(true);
            int row = jTable1.getRowCount();
            jLabel1.setText(jTable1.getValueAt(row - 1, 0).toString());
            jLabel6.setText(jTable1.getValueAt(row - 20, 0).toString());
        } catch (Exception e) {
        }

    }

    public void getprevid() {
        try {
            int row = jTable1.getRowCount();
            int getbase1 = Integer.parseInt(jTable1.getValueAt(row - 20, 0).toString());
            jLabel3.setText(Integer.toString(getbase1));

            int getbase2 = getbase1 - 19;
            jLabel2.setText(Integer.toString(getbase2));

            if (Integer.parseInt(jLabel2.getText()) <= 2) {
                jButton2.setEnabled(false);
            }
        } catch (Exception e) {
        }

    }

    /////////////////////////////////////////////////////////////////////////////////
    public void showontblAV() {
        try {
            String sql = "SELECT user_logs.`log_id` as ID, user.type as User, `login_name` as Action,CONCAT_WS(' ',user_logs.`day`,user_logs.`month`,user_logs.`year`) as Date, CONCAT_WS('',user_logs.`hour`,':',user_logs.`minute`,' ',user_logs.`am_pm`) as Time FROM user_logs inner join user on user_logs.user_id = user.user_id where log_id >= '" + jLabel1.getText() + "' and user.type='ASISSTANT VETERINARIAN' ORDER BY log_id LIMIT 0,20";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(0).setMinWidth(50);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(70);
    }

    public void showontblprevAV() {
        try {
            String sql = "SELECT user_logs.`log_id` as ID, user.type as User, `login_name` as Action,CONCAT_WS(' ',user_logs.`day`,user_logs.`month`,user_logs.`year`) as Date, CONCAT_WS('',user_logs.`hour`,':',user_logs.`minute`,' ',user_logs.`am_pm`) as Time FROM user_logs inner join user on user_logs.user_id = user.user_id WHERE (log_id between '" + jLabel2.getText() + "' and '" + jLabel3.getText() + "') and user.type='ASISSTANT VETERINARIAN' ORDER BY log_id LIMIT 0,20";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(0).setMinWidth(50);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(70);
    }

    public void getnextidAV() {
        try {
            jButton2.setEnabled(true);
            int row = jTable1.getRowCount();
            jLabel1.setText(jTable1.getValueAt(row - 1, 0).toString());
            jLabel6.setText(jTable1.getValueAt(row - 20, 0).toString());
        } catch (Exception e) {
        }

    }

    public void getprevidAV() {
        try {
            int row = jTable1.getRowCount();
            int getbase1 = Integer.parseInt(jTable1.getValueAt(row - 20, 0).toString());
            jLabel3.setText(Integer.toString(getbase1));

            int getbase2 = getbase1 - 19;
            jLabel2.setText(Integer.toString(getbase2));

            if (Integer.parseInt(jLabel2.getText()) <= 2) {
                jButton2.setEnabled(false);
            }
        } catch (Exception e) {
        }

    }

    /////////////////////////////////////////////////////////////////////////////////
    public void showontblV() {
        try {
            String sql = "SELECT user_logs.`log_id` as ID, user.type as User, `login_name` as Action,CONCAT_WS(' ',user_logs.`day`,user_logs.`month`,user_logs.`year`) as Date, CONCAT_WS('',user_logs.`hour`,':',user_logs.`minute`,' ',user_logs.`am_pm`) as Time FROM user_logs inner join user on user_logs.user_id = user.user_id where log_id >= '" + jLabel1.getText() + "' and user.type='VETERINARIAN' ORDER BY log_id LIMIT 0,20";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(0).setMinWidth(50);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(70);
    }

    public void showontblprevV() {
        try {
            String sql = "SELECT user_logs.`log_id` as ID, user.type as User, `login_name` as Action,CONCAT_WS(' ',user_logs.`day`,user_logs.`month`,user_logs.`year`) as Date, CONCAT_WS('',user_logs.`hour`,':',user_logs.`minute`,' ',user_logs.`am_pm`) as Time FROM user_logs inner join user on user_logs.user_id = user.user_id WHERE (log_id between '" + jLabel2.getText() + "' and '" + jLabel3.getText() + "') and user.type='VETERINARIAN' ORDER BY log_id LIMIT 0,20";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(0).setMinWidth(50);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(70);
    }

    public void getnextidV() {
        try {
            jButton2.setEnabled(true);
            int row = jTable1.getRowCount();
            jLabel1.setText(jTable1.getValueAt(row - 1, 0).toString());
            jLabel6.setText(jTable1.getValueAt(row - 20, 0).toString());
        } catch (Exception e) {
        }

    }

    public void getprevidV() {
        try {
            int row = jTable1.getRowCount();
            int getbase1 = Integer.parseInt(jTable1.getValueAt(row - 20, 0).toString());
            jLabel3.setText(Integer.toString(getbase1));

            int getbase2 = getbase1 - 19;
            jLabel2.setText(Integer.toString(getbase2));

            if (Integer.parseInt(jLabel2.getText()) <= 2) {
                jButton2.setEnabled(false);
            }
        } catch (Exception e) {
        }
    }

    public void refreshpage() {
        try{
            int x = jTable1.getRowCount();
            int tominus = x - 1;
            int fromrow = x - tominus;
            String from = jTable1.getValueAt(fromrow - 1, 0).toString();
            String to = jTable1.getValueAt(x - 1, 0).toString();
            jLabel6.setText(from);
            jLabel1.setText(to);
            jButton2.setEnabled(false);
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, "No data retrieved");
        }
    }
    
    public void showontblbyHour() {
        try {
            String sql = "SELECT user_logs.`log_id` as ID, user.type as User, `login_name` as Action,CONCAT_WS(' ',user_logs.`day`,user_logs.`month`,user_logs.`year`) as Date, CONCAT_WS('',user_logs.`hour`,':',user_logs.`minute`,' ',user_logs.`am_pm`) as Time FROM user_logs inner join user on user_logs.user_id = user.user_id where log_id >= '"+jLabel1.getText()+"' and user_logs.hour = '"+jSpinner2.getValue()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(0).setMinWidth(50);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(70);
        } catch (Exception e) {
        }
    }
    
    public void showontblbyHourRange() {
        try {
            String sql = "SELECT user_logs.`log_id` as ID, user.type as User, `login_name` as Action,CONCAT_WS(' ',user_logs.`day`,user_logs.`month`,user_logs.`year`) as Date, CONCAT_WS('',user_logs.`hour`,':',user_logs.`minute`,' ',user_logs.`am_pm`) as Time FROM user_logs inner join user on user_logs.user_id = user.user_id where log_id >= '"+jLabel1.getText()+"' and user_logs.hour between '"+jSpinner2.getValue()+"' and '"+jSpinner1.getValue()+"' ORDER BY log_id LIMIT 0,20";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(0).setMinWidth(50);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(70);
        } catch (Exception e) {
        }
    }
    
    public void produceYear(){
        String year = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
        int beginyr = Integer.parseInt(year);
        for(int x = beginyr; x >= 0; x--){
            jComboBox2.addItem(x);
        }
    }
    
        public void showontblbyMonth() {
        try {
            String sql = "SELECT user_logs.`log_id` as ID, user.type as User, `login_name` as Action,CONCAT_WS(' ',user_logs.`day`,user_logs.`month`,user_logs.`year`) as Date, CONCAT_WS('',user_logs.`hour`,':',user_logs.`minute`,' ',user_logs.`am_pm`) as Time FROM user_logs inner join user on user_logs.user_id = user.user_id where log_id >= '"+jLabel1.getText()+"' and user_logs.month = '"+jComboBox1.getSelectedItem().toString()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(0).setMinWidth(50);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(70);
        } catch (Exception e) {
        }
        int r = jTable1.getRowCount();
        if(r==0){
            JOptionPane.showMessageDialog(rootPane, "No data retrieved!");
        }
    }
        
        public void showontblbyYear() {
        try {
            String sql = "SELECT user_logs.`log_id` as ID, user.type as User, `login_name` as Action,CONCAT_WS(' ',user_logs.`day`,user_logs.`month`,user_logs.`year`) as Date, CONCAT_WS('',user_logs.`hour`,':',user_logs.`minute`,' ',user_logs.`am_pm`) as Time FROM user_logs inner join user on user_logs.user_id = user.user_id where log_id >= '"+jLabel1.getText()+"' and user_logs.year = '"+jComboBox2.getSelectedItem().toString()+"'";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(0).setMinWidth(50);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(70);
        } catch (Exception e) {
        }
        int r = jTable1.getRowCount();
        if(r==0){
            JOptionPane.showMessageDialog(rootPane, "No data retrieved!");
        }
    }
        
        ////
        
        
        public void showontblByHourRange() {
        try {
            String sql = "SELECT user_logs.`log_id` as ID, user.type as User, `login_name` as Action,CONCAT_WS(' ',user_logs.`day`,user_logs.`month`,user_logs.`year`) as Date, CONCAT_WS('',user_logs.`hour`,':',user_logs.`minute`,' ',user_logs.`am_pm`) as Time FROM user_logs inner join user on user_logs.user_id = user.user_id where log_id >= '"+jLabel1.getText()+"' and user_logs.hour between '"+jSpinner2.getValue()+"' and '"+jSpinner1.getValue()+"' ORDER BY log_id LIMIT 0,20";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(0).setMinWidth(50);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(70);
    }

    public void showontblprevByHourRange() {
        try {
            String sql = "SELECT user_logs.`log_id` as ID, user.type as User, `login_name` as Action,CONCAT_WS(' ',user_logs.`day`,user_logs.`month`,user_logs.`year`) as Date, CONCAT_WS('',user_logs.`hour`,':',user_logs.`minute`,' ',user_logs.`am_pm`) as Time FROM user_logs inner join user on user_logs.user_id = user.user_id WHERE (log_id between '" + jLabel2.getText() + "' and '" + jLabel3.getText() + "') and (user_logs.hour between '"+jSpinner2.getValue()+"' and '"+jSpinner1.getValue()+"') ORDER BY log_id LIMIT 0,20";
            ps = conn_db.prepareStatement(sql);
            rs = ps.executeQuery();
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(0).setMinWidth(50);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(70);
    }
    
    public void getnextidByHourRange() {
        try {
            jButton2.setEnabled(true);
            int row = jTable1.getRowCount();
            jLabel1.setText(jTable1.getValueAt(row - 1, 0).toString());
            jLabel6.setText(jTable1.getValueAt(row - 20, 0).toString());
        } catch (Exception e) {
        }

    }

    public void getprevidByHourRange() {
        try {
            int row = jTable1.getRowCount();
            int getbase1 = Integer.parseInt(jTable1.getValueAt(row - 20, 0).toString());
            jLabel3.setText(Integer.toString(getbase1));

            int getbase2 = getbase1 - 19;
            jLabel2.setText(Integer.toString(getbase2));

            if (Integer.parseInt(jLabel2.getText()) <= 2) {
                jButton2.setEnabled(false);
            }
        } catch (Exception e) {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        jRadioButton1 = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jRadioButton2 = new javax.swing.JRadioButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jButton3 = new javax.swing.JButton();
        jRadioButton3 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("User Logs History");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jToolBar1.setRollover(true);

        jRadioButton1.setText("View Assistant Vet");
        jRadioButton1.setFocusable(false);
        jRadioButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jRadioButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jRadioButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jRadioButton1);
        jToolBar1.add(jSeparator1);

        jRadioButton2.setText("View  Veterinarian");
        jRadioButton2.setFocusable(false);
        jRadioButton2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jRadioButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jRadioButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jRadioButton2);
        jToolBar1.add(jSeparator2);
        jToolBar1.add(jSeparator3);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Choose Here--", "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jComboBox1);

        jLabel8.setText("  Month  ");
        jToolBar1.add(jLabel8);
        jToolBar1.add(jSeparator4);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Choose Here--" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jToolBar1.add(jComboBox2);

        jLabel9.setText("  Year  ");
        jToolBar1.add(jLabel9);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_interface/agt_forward.png"))); // NOI18N
        jButton1.setText("Next");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jButton1MouseReleased(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/user_interface/agt_back.png"))); // NOI18N
        jButton2.setText("Previous");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("1");

        jLabel2.setText("0");

        jLabel3.setText("0");

        jLabel4.setText("From");

        jLabel5.setText("to");

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 17)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("1");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Hour"));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("From");

        jSpinner2.setFocusable(false);
        jSpinner2.setPreferredSize(new java.awt.Dimension(60, 32));
        jSpinner2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner2StateChanged(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("To");

        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });

        jButton3.setText("Search");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jRadioButton3.setText("Search");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButton3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel11)
                                        .addGap(35, 35, 35)
                                        .addComponent(jLabel7))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(45, 45, 45))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jRadioButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel7))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSpinner1)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 1110, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(181, 181, 181)
                        .addComponent(jLabel4)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel5)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jRadioButton1.isSelected()) {
            showontblAV();
            getnextidAV();
            jButton2.setText("Previous");
            if (jTable1.getRowCount() < 20) {
                JOptionPane.showMessageDialog(null, "You're on the last page");
                jButton1.setEnabled(false);
                jButton2.setText("Reset");
            }
        } else {
            if (jRadioButton2.isSelected()) {
                showontblV();
                getnextidV();
                jButton2.setText("Previous");
                if (jTable1.getRowCount() < 20) {
                    JOptionPane.showMessageDialog(null, "You're on the last page");
                    jButton1.setEnabled(false);
                    jButton2.setText("Reset");
                }
            } else {
                showontbl();
                getnextid();
                jButton2.setText("Previous");
                if (jTable1.getRowCount() < 20) {
                    JOptionPane.showMessageDialog(null, "You're on the last page");
                    jButton1.setEnabled(false);
                    jButton2.setText("Reset");
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (jRadioButton1.isSelected()) {
            if (jButton2.getText().equals("Reset")) {
                jLabel1.setText("1");
                showontblAV();
                getnextidAV();
                jButton2.setEnabled(false);
                jButton1.setEnabled(true);
                jLabel2.setText("0"); 
                jLabel3.setText("0");
                jRadioButton1.setSelected(false);
                jRadioButton2.setSelected(false);
            } else {
                getprevidAV();
                showontblprevAV();
                int row = jTable1.getRowCount();
                jLabel1.setText(jTable1.getValueAt(row - 1, 0).toString());
                jLabel6.setText(jTable1.getValueAt(row - 20, 0).toString());
                jButton1.setEnabled(true);

            }
        } else {
            if (jRadioButton2.isSelected()) {

                if (jButton2.getText().equals("Reset")) {
                    jLabel1.setText("1");
                    showontblV();
                    getnextidV();
                    jButton2.setEnabled(false);
                    jButton1.setEnabled(true);
                    jLabel2.setText("0");
                    jLabel3.setText("0");
                    jRadioButton1.setSelected(false);
                    jRadioButton2.setSelected(false);
                } else {
                    getprevidV();
                    showontblprevV();
                    int row = jTable1.getRowCount();
                    jLabel1.setText(jTable1.getValueAt(row - 1, 0).toString());
                    jLabel6.setText(jTable1.getValueAt(row - 20, 0).toString());
                    jButton1.setEnabled(true);

                }

            } else {
                if (jButton2.getText().equals("Reset")) {
                    jLabel1.setText("1");
                    showontbl();
                    getnextid();
                    jButton2.setEnabled(false);
                    jButton1.setEnabled(true);
                    jLabel2.setText("0");
                    jLabel3.setText("0");
                    jRadioButton1.setSelected(false);
                    jRadioButton2.setSelected(false);
                } else {
                    getprevid();
                    showontblprev();
                    int row = jTable1.getRowCount();
                    jLabel1.setText(jTable1.getValueAt(row - 1, 0).toString());
                    jLabel6.setText(jTable1.getValueAt(row - 20, 0).toString());
                    jButton1.setEnabled(true);
                }
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseReleased
    }//GEN-LAST:event_jButton1MouseReleased

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
    }//GEN-LAST:event_jButton1MousePressed

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
    }//GEN-LAST:event_jButton2MousePressed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        if (jRadioButton1.isSelected()) {
            jRadioButton2.setSelected(false);
            showontblAV();
            refreshpage();
        } else {
            reset();
            refreshpage();
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        if (jRadioButton2.isSelected()) {
            jRadioButton1.setSelected(false);
            showontblV();
            refreshpage();
        } else {
            reset();
            refreshpage();
        }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if (JOptionPane.showConfirmDialog(new JFrame(),
                "Are you sure you want to close?",
                "Closing Audit Trail", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            dispose();
        } else {

            System.out.println("Should not Exit: " + WindowConstants.DISPOSE_ON_CLOSE);
            return;
        }
    }//GEN-LAST:event_formWindowClosing

    private void jSpinner2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner2StateChanged
        int val = (int) jSpinner2.getValue();

        if (val > 12) {
            jSpinner2.setValue(1);
        } else {
            if (val < 1) {
                jSpinner2.setValue(1);
            }
        }
    }//GEN-LAST:event_jSpinner2StateChanged

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if(jComboBox1.getSelectedItem().toString().equals("--Choose Here--")){
            JOptionPane.showMessageDialog(rootPane, "Please choose a month");
        }else{
            showontblbyMonth();
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        if(jComboBox2.getSelectedItem().toString().equals("--Choose Here--")){
            JOptionPane.showMessageDialog(rootPane, "Please choose a year");
        }else{
            showontblbyYear();
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        int val = (int) jSpinner1.getValue();

        if (val > 12) {
            jSpinner1.setValue(1);
        } else {
            if (val < 1) {
                jSpinner1.setValue(1);
            }
        }
    }//GEN-LAST:event_jSpinner1StateChanged

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        if(jRadioButton3.isSelected()){//
            jButton3.setEnabled(true);
            jSpinner1.setEnabled(true);
            jSpinner2.setEnabled(true);
        }else{jSpinner2.setEnabled(true);
            jButton3.setEnabled(false);
            jSpinner1.setEnabled(false);
            jSpinner2.setEnabled(false);
            getprevid();
            refreshpage();
            jButton3.setText("Search");
            jLabel1.setText("1");
            showontbl();
            refreshpage();
        }
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        showontblbyHourRange();
        getnextidByHourRange();
        jButton3.setText("Next");
        if (jTable1.getRowCount() < 20) {
                    JOptionPane.showMessageDialog(null, "You're on the last page");
                    jButton3.setText("Search");
                    jSpinner1.setValue(1);
                    jSpinner2.setValue(1);
                    jLabel1.setText("1");
                    jLabel6.setText("1");
                }
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
            java.util.logging.Logger.getLogger(user_logs_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(user_logs_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(user_logs_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(user_logs_frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new user_logs_frame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
