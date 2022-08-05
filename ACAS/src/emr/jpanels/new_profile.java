/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emr.jpanels;

import audit_trailing.audit_trail;
import com.jidesoft.swing.AutoCompletion;
import com.toedter.calendar.JCalendar;
import static emr.jpanels.new_pet.owner_id_for_pet;
import static emr.jpanels.new_pet.owner_name_for_pet;
import static workstation.main_workstation.*;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import login.connection_db;
import static login.login_frm.setlogid;
import temp.NewJFrame;

/**
 *
 * @author IrvGu
 */
public class new_profile extends javax.swing.JPanel {
    
    int timerun = 0;
    
    Connection conn_db = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    audit_trail au = new audit_trail();
    
    String pid;
    
    String uid;
    
    String day;
    String month;
    String year;
    
    int hourset;
    int minuteset;
    String am_pmset;
    
    String thisdate;
    
    /*********for search names************/
    String searchlname;
    String searchfname;
    String searchmname="";
    
    String upoid;
    /*********for search names************/
    
    String status;////for uto recovery
    
    int count=0;
    
    
    /**
     * Creates new form panelxy
     */
    public new_profile() {
        initComponents();
        
        conn_db = connection_db.ConnectDB();
        
        a.setVisible(false);
        
        ownid.setVisible(false);
  
        
        jComboBox1.setEditable(true);
        AutoCompletion ac1 = new AutoCompletion(this.jComboBox1);
        ac1.setStrict(false);
        
        jComboBox2.setEditable(true);
        AutoCompletion ac3 = new AutoCompletion(this.jComboBox2);
        ac3.setStrict(false);
        
        jComboBox3.setEditable(true);
        AutoCompletion ac2 = new AutoCompletion(this.jComboBox3);
        ac2.setStrict(false);
        
        ///////FOR SEARCH BOX AUTO COMPLETION////////
        
        uid = setlogid.getText();

        update.setEnabled(false);
        
        add_pet.setEnabled(false);
        
        fillStreetName();
        
        fillPurok();
        ////////////////for audit trailing/////////////////
        
        timeAndAutoCorrect();
        
        autoFill();
        
        fillBrgy();

        ///////FOR SEARCH BOX AUTO COMPLETION////////
        
    }

    
    //***************************************************************//
    
    public void timeAndAutoCorrect(){
        
        ////set date////
        JCalendar dateset2 = new JCalendar();
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("d MMM yyyy");
        String dob2 = dateFormat2.format(dateset2.getDate());
        thisdate = dob2;
        ////
        
        ////set time////
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
			
		    String clock = hour+1 + ":" + "0" + minute + " " + day_night;
                    
                    hourset = hour; minuteset = minute; am_pmset = day_night;
			
			}
		         else{
					String clock = hour+1 + ":" + minute + " " + day_night;
                                        
                                        hourset = hour; minuteset = minute; am_pmset = day_night;
			     }
                     
                }
            }
        }.start();
        ////
        
        //////////////////////////////////////////////////
        
        
        
        /******************************************FOR AUTO SEARCH CODE ON HERE**********************************************************/
        
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
        
        
        int set=0;
        String y[] = {  " ", "013 Quezon Avenue", "2F DEMONTAÑO SPECIALTY & DIAGNOSTIC CENTER BUILDING SANTIAGO BLVD. GEN. SANTOS CITY", "GSCHS",
                        "Guinto St. Ext.", "Ipil", "J&G Propriedad Compound", "J. Catolico Avenue",
                        "J. Catolico Sr.", "J. Catolico St. Lagao", "J. Divinagracia St.", "J. P. Laurel north Avenue",
                        "Jade st.", "Jose Catolico Sr. Avenue", "Lagao, Gen. Santos City", "Lagao, General Santos City",
                        "Lanzones", "Lapu Lapu", "Last road ",
                        "Laurel North Street", "Leon Llido", "Leon Llido Avenue","Leon Llido St.","Leyva Bldg., Daproza Ave., Gen. Santos City",
                        "LOT 21, BLOCK 13, PRK 18, BRGY FATIMA","Lot.3,sogod","Lote, Calumpang","Lukban","Mabuhay","Mabuhay Road","Macopa","Macopa Extn.",
                        "Magsaysay Avenue","Malambuon","Mangustan","Mansanitas","Mansanitas Street","MARCOS AVE.","Mariam Street","Marist Avenue ","Mateo Road",
                        "Mildy's Internet Cafe","Millard Fuller St.","Mindanao State University","MPC Building. Rm. 209","MSU ni xa","Narangita","National Highway",
                        "National Highway, Apopong, General Santos City","National Highway, Brgy. City Heights","National Highway, Lagao, Gensan City",
                        "National Highway, Lote, Calumpang","National Highway,Apopong General Santos City","New Mabuhay","New Mabuhay Brgy. Mabuhay",
                        "Niyog","Niyog Ext.","NLSA Road","Nuestra Senora De La Paz","Nunez Extension","Nursery Road","Nuñez Street","Osmena st.","P. Acharon Boulevard",
                        "Papaya","Pendatun Ave.","Perez","Phase 3 A","Dadiangas","Greenville","2nd Street","3rd Road Calumpang","3rd Street","4th Street","70 Springville Subdivision",
                        "Alerta","Alunan","Aparente St.","Apple","Labus","Ariola","Asai Road","Aspang","Atis Street","B1 L8 Issabella Homes","Balagtas","Balimbing","Banas (Gumamela)",
                        "Banisil Street","Basag Purol","Bayabas","Bayanihan","Biatelis","blk 16 lot 12 A Falgui Subd.","blk 3, new society, apopong","blk. 11","Blk. 15 Lot 3",
                        "Blk.7","Block 1","Block 10, Zone 7","Block 46, Lot 15","Block 47, Lot 8","BLOCK 8","BOSTON ST.","Phase 2","Bula Zone 8","Bulaong Ave.","Bulaong St.",
                        "Bulaong Village","Cabe Subd. Road","Cagampang Street","Cahilsot","Camachile","Camia","Camias","Capareda St.","Casquelo","Champaca","Chico","Claro M. Recto",
                        "Conel Road","Corner Rizal ST","Cornelio ST","Crisologo De Juan","Crisostomo Village","D. Arenas","Dacera","Darimco","De Dios","Emergency Road","Fatima",
                        "Ferrariz","Fiscal Gregorio Daprosa Ave.","G. Misa St.","Gen.Santos-Marbel-Tacurong Road","Phase 3b block 8 lot 7","Pietros","Pina","Pioneer Ave.","Santiago Blvd.",
                        "Sed Ave.","Sineguelas","Soledad Estate, National Highway","St. Joseph St.","Sta Cruz","Susana Homes","Tambler","Tambler, Upper Banualan","Tambler,Natl Highway",
                        "Tieza","Tinago Purok 24","Tiongson St.","Uson Phase 4","Valma Subdivision","Velasquez","Ventilacion","Veteran","Villa Consuelo","Waling Waling","West Side","Zapote"};
        
        for(int c=0; c<=160; c++){
            set=c;
            jComboBox1.addItem(y[set]);
        }
        
        /******************************************FOR AUTO SEARCH CODE ON HERE**********************************************************/
        
    }
    
    //******************************************************************//
    
    
    
    public void savestatus(){
        //savestatus
        String content = "unfinished";
        String path = "C:\\Users\\Public\\Documents\\status_registration.txt";
        try {
            Files.write( Paths.get(path), content.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void autoFill(){
        province.setText("South Cotabato");
        province.setForeground(Color.black);
        
        zip_code.setText("9500");
        zip_code.setForeground(Color.black);
        
        city.setText("General Santos City");
        city.setForeground(Color.black);
    }
    
    public void fillStreetName(){

        
    }
    
    public void fillPurok(){

        int set=0;
        String y[] = {  " ","Purok 1","Purok 2","Purok 3","Purok 4","Purok 6","Purok 7","Purok 15","Purok 18","Purok Acharon","Masagana","Malakas","Malipayon","Matatag","Sta. Teresita",
                        "Estrella",""};
        
        for(int c=0; c<=16; c++){
            set=c;
            jComboBox2.addItem(y[set]);
        }
    }
    
    public void fillBrgy(){
        
        
        
        int set=0;
        String y[] = {  " ","Apopong","Baluan","Batomelong","Buayan","Bula","Calumpang","City Heights","Conel","Dadiangas East",
                        "Dadiangas North","Dadiangas South","Dadiangas West","Fatima","Katangawan","Labangal","Lagao (1st & 3rd)",
                        "Ligaya","Mabuhay","Olympog","San Isidro (Lagao 2nd)","San Jose","Siguel","Sinawal","Tambler","Tinagacan","Upper Labay"};
        
        for(int c=0; c<=26; c++){
            set=c;
            jComboBox3.addItem(y[set]);
        }
    }
    
    public void searchFirstName(){
        /*******************************************************************************************************************/
        update.setEnabled(true);
        add_pet.setEnabled(true);
        
        try{
            
            String open = "SELECT * FROM owner WHERE first_name LIKE '"+jTextField2.getText()+"%'";
            ps = conn_db.prepareStatement(open);
            rs = ps.executeQuery();
            
            while(rs.next()){
                jButton1.setEnabled(false);
                String getowner_id = rs.getString("owner_id");
                upoid=getowner_id;
                ownid.setText(getowner_id);
                pid = getowner_id;
                ownid.setText(pid);
                
                String gettname = rs.getString("title_name");
                title_name.setText(gettname);title_name.setForeground(Color.black);
                
                String getfname = rs.getString("first_name");
                first_name.setText(getfname);first_name.setForeground(Color.black);
                
                String getmname = rs.getString("middle_name");
                middle_name.setText(getmname);middle_name.setForeground(Color.black);
                
                String getlname = rs.getString("last_name");
                last_name.setText(getlname);last_name.setForeground(Color.black);
                
                String getsname = rs.getString("suffix_name");
                suffix_name.setText(getsname);suffix_name.setForeground(Color.black);
                
                String getunum = rs.getString("unit_number");
                unit_number.setText(getunum);unit_number.setForeground(Color.black);
                
                String gethsnum = rs.getString("house_street_number");
                house_street_number.setText(gethsnum);house_street_number.setForeground(Color.black);
                
                String getstname = rs.getString("street_name");
                jComboBox1.setSelectedItem(getstname);
                
                String getprk = rs.getString("purok");
                jComboBox2.setSelectedItem(getprk);
                
                String getbrgy = rs.getString("brgy_name");
                jComboBox3.setSelectedItem(getbrgy);
                
                String getcit = rs.getString("city");
                city.setText(getcit);city.setForeground(Color.black);
                
                String getprovince = rs.getString("province");
                province.setText(getprovince);province.setForeground(Color.black);
                
                String getzip_code = rs.getString("zip_code");
                zip_code.setText(getzip_code);zip_code.setForeground(Color.black);
                
                String getcontact_number_1 = rs.getString("contact_number_1");
                contact_number_1.setText(getcontact_number_1);contact_number_1.setForeground(Color.black);
                
                String getcontact_number_2 = rs.getString("contact_number_2");
                contact_number_2.setText(getcontact_number_2);contact_number_2.setForeground(Color.black);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        if(jTextField2.getText().equals("")){
            reset();
        }

    }
    
    public void searchMiddleName(){
        /*******************************************************************************************************************/
        update.setEnabled(true);
        add_pet.setEnabled(true);
        
        try{
            
            String open = "SELECT * FROM owner WHERE middle_name LIKE '"+jTextField3.getText()+"%'";
            ps = conn_db.prepareStatement(open);
            rs = ps.executeQuery();
            
            while(rs.next()){
                jButton1.setEnabled(false);
                String getowner_id = rs.getString("owner_id");
                upoid=getowner_id;
                ownid.setText(getowner_id);
                pid = getowner_id;
                ownid.setText(pid);
                
                String gettname = rs.getString("title_name");
                title_name.setText(gettname);title_name.setForeground(Color.black);
                
                String getfname = rs.getString("first_name");
                first_name.setText(getfname);first_name.setForeground(Color.black);
                
                String getmname = rs.getString("middle_name");
                middle_name.setText(getmname);middle_name.setForeground(Color.black);
                
                String getlname = rs.getString("last_name");
                last_name.setText(getlname);last_name.setForeground(Color.black);
                
                String getsname = rs.getString("suffix_name");
                suffix_name.setText(getsname);suffix_name.setForeground(Color.black);
                
                String getunum = rs.getString("unit_number");
                unit_number.setText(getunum);unit_number.setForeground(Color.black);
                
                String gethsnum = rs.getString("house_street_number");
                house_street_number.setText(gethsnum);house_street_number.setForeground(Color.black);
                
                String getstname = rs.getString("street_name");
                jComboBox1.setSelectedItem(getstname);
                
                String getprk = rs.getString("purok");
                jComboBox2.setSelectedItem(getprk);
                
                String getbrgy = rs.getString("brgy_name");
                jComboBox3.setSelectedItem(getbrgy);
                
                String getcit = rs.getString("city");
                city.setText(getcit);city.setForeground(Color.black);
                
                String getprovince = rs.getString("province");
                province.setText(getprovince);province.setForeground(Color.black);
                
                String getzip_code = rs.getString("zip_code");
                zip_code.setText(getzip_code);zip_code.setForeground(Color.black);
                
                String getcontact_number_1 = rs.getString("contact_number_1");
                contact_number_1.setText(getcontact_number_1);contact_number_1.setForeground(Color.black);
                
                String getcontact_number_2 = rs.getString("contact_number_2");
                contact_number_2.setText(getcontact_number_2);contact_number_2.setForeground(Color.black);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        if(jTextField3.getText().equals("")){
            reset();
        }
    }
    
    
    public void searchLastName(){
        /*******************************************************************************************************************/
        update.setEnabled(true);
        add_pet.setEnabled(true);
        
        try{
            
            String open = "SELECT * FROM owner WHERE last_name LIKE '"+jTextField1.getText()+"%'";
            ps = conn_db.prepareStatement(open);
            rs = ps.executeQuery();
            
            while(rs.next()){
                jButton1.setEnabled(false);
                String getowner_id = rs.getString("owner_id");
                upoid=getowner_id;
                ownid.setText(getowner_id);
                pid = getowner_id;
                ownid.setText(pid);
                
                String gettname = rs.getString("title_name");
                title_name.setText(gettname);title_name.setForeground(Color.black);
                
                String getfname = rs.getString("first_name");
                first_name.setText(getfname);first_name.setForeground(Color.black);
                
                String getmname = rs.getString("middle_name");
                middle_name.setText(getmname);middle_name.setForeground(Color.black);
                
                String getlname = rs.getString("last_name");
                last_name.setText(getlname);last_name.setForeground(Color.black);
                
                String getsname = rs.getString("suffix_name");
                suffix_name.setText(getsname);suffix_name.setForeground(Color.black);
                
                String getunum = rs.getString("unit_number");
                unit_number.setText(getunum);unit_number.setForeground(Color.black);
                
                String gethsnum = rs.getString("house_street_number");
                house_street_number.setText(gethsnum);house_street_number.setForeground(Color.black);
                
                String getstname = rs.getString("street_name");
                jComboBox1.setSelectedItem(getstname);
                
                String getprk = rs.getString("purok");
                jComboBox2.setSelectedItem(getprk);
                
                String getbrgy = rs.getString("brgy_name");
                jComboBox3.setSelectedItem(getbrgy);
                
                String getcit = rs.getString("city");
                city.setText(getcit);city.setForeground(Color.black);
                
                String getprovince = rs.getString("province");
                province.setText(getprovince);province.setForeground(Color.black);
                
                String getzip_code = rs.getString("zip_code");
                zip_code.setText(getzip_code);zip_code.setForeground(Color.black);
                
                String getcontact_number_1 = rs.getString("contact_number_1");
                contact_number_1.setText(getcontact_number_1);contact_number_1.setForeground(Color.black);
                
                String getcontact_number_2 = rs.getString("contact_number_2");
                contact_number_2.setText(getcontact_number_2);contact_number_2.setForeground(Color.black);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        if(jTextField1.getText().equals("")){
            reset();
        }
    }
    
    
    public void searchFullName(){
        /*******************************************************************************************************************/
        update.setEnabled(true);
        add_pet.setEnabled(true);
        
        try{
            
            String open = "SELECT * FROM owner WHERE (first_name = '"+jTextField2.getText()+"' AND middle_name = '"+jTextField3.getText()+"') AND (last_name = '"+jTextField1.getText()+"')";
            ps = conn_db.prepareStatement(open);
            rs = ps.executeQuery();
            
            while(rs.next()){
                jButton1.setEnabled(false);
                String getowner_id = rs.getString("owner_id");
                upoid=getowner_id;
                ownid.setText(getowner_id);
                pid = getowner_id;
                ownid.setText(pid);
                
                String gettname = rs.getString("title_name");
                title_name.setText(gettname);title_name.setForeground(Color.black);
                
                String getfname = rs.getString("first_name");
                first_name.setText(getfname);first_name.setForeground(Color.black);
                
                String getmname = rs.getString("middle_name");
                middle_name.setText(getmname);middle_name.setForeground(Color.black);
                
                String getlname = rs.getString("last_name");
                last_name.setText(getlname);last_name.setForeground(Color.black);
                
                String getsname = rs.getString("suffix_name");
                suffix_name.setText(getsname);suffix_name.setForeground(Color.black);
                
                String getunum = rs.getString("unit_number");
                unit_number.setText(getunum);unit_number.setForeground(Color.black);
                
                String gethsnum = rs.getString("house_street_number");
                house_street_number.setText(gethsnum);house_street_number.setForeground(Color.black);
                
                String getstname = rs.getString("street_name");
                jComboBox1.setSelectedItem(getstname);
                
                String getprk = rs.getString("purok");
                jComboBox2.setSelectedItem(getprk);
                
                String getbrgy = rs.getString("brgy_name");
                jComboBox3.setSelectedItem(getbrgy);
                
                String getcit = rs.getString("city");
                city.setText(getcit);city.setForeground(Color.black);
                
                String getprovince = rs.getString("province");
                province.setText(getprovince);province.setForeground(Color.black);
                
                String getzip_code = rs.getString("zip_code");
                zip_code.setText(getzip_code);zip_code.setForeground(Color.black);
                
                String getcontact_number_1 = rs.getString("contact_number_1");
                contact_number_1.setText(getcontact_number_1);contact_number_1.setForeground(Color.black);
                
                String getcontact_number_2 = rs.getString("contact_number_2");
                contact_number_2.setText(getcontact_number_2);contact_number_2.setForeground(Color.black);
            }
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }

    }
    
    
    public void reset(){
        title_name.setText("Title Name");
        title_name.setForeground(Color.GRAY);
        first_name.setText("*First Name");
        first_name.setForeground(new Color(51,102,0));
        middle_name.setText("Middle Name");
        middle_name.setForeground(Color.GRAY);
        last_name.setText("*Last Name");
        last_name.setForeground(new Color(51,102,0));
        suffix_name.setText("Suffix Name");
        suffix_name.setForeground(Color.GRAY);
        contact_number_1.setText("*Contact Number 1");
        contact_number_1.setForeground(new Color(51,102,0));
        contact_number_2.setText("Contact Number 2");
        contact_number_2.setForeground(Color.GRAY);
        unit_number.setText("Unit Number");
        unit_number.setForeground(Color.GRAY);
        house_street_number.setText("House/Street Number");
        house_street_number.setForeground(Color.GRAY);
        province.setText("Province");
        province.setForeground(Color.GRAY);
        zip_code.setText("Zip Code");
        zip_code.setForeground(Color.GRAY);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        qwe = new javax.swing.JPanel();
        suffix_name = new javax.swing.JTextField();
        title_name = new javax.swing.JTextField();
        first_name = new javax.swing.JTextField();
        middle_name = new javax.swing.JTextField();
        last_name = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        province = new javax.swing.JTextField();
        zip_code = new javax.swing.JTextField();
        contact_number_2 = new javax.swing.JTextField();
        contact_number_1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        a = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        unit_number = new javax.swing.JTextField();
        house_street_number = new javax.swing.JTextField();
        city = new javax.swing.JTextField();
        ownid = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        jToolBar1 = new javax.swing.JToolBar();
        jButton1 = new javax.swing.JButton();
        update = new javax.swing.JButton();
        add_pet = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lb3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        lb4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        lb2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();

        qwe.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        suffix_name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        suffix_name.setForeground(new java.awt.Color(204, 204, 204));
        suffix_name.setText("Suffix Name");
        suffix_name.setToolTipText("Suffix Name is optional");
        suffix_name.setBorder(javax.swing.BorderFactory.createTitledBorder("Suffix Name"));
        suffix_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                suffix_nameMouseClicked(evt);
            }
        });
        suffix_name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                suffix_nameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                suffix_nameFocusLost(evt);
            }
        });
        qwe.add(suffix_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 200, 180, 50));

        title_name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        title_name.setForeground(new java.awt.Color(204, 204, 204));
        title_name.setText("Title Name");
        title_name.setToolTipText("Title Name is Optional");
        title_name.setBorder(javax.swing.BorderFactory.createTitledBorder("Title Name"));
        title_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                title_nameMouseClicked(evt);
            }
        });
        title_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                title_nameActionPerformed(evt);
            }
        });
        title_name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                title_nameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                title_nameFocusLost(evt);
            }
        });
        qwe.add(title_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 180, 50));

        first_name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        first_name.setForeground(new java.awt.Color(51, 102, 0));
        first_name.setText("*First Name");
        first_name.setToolTipText("First Name");
        first_name.setBorder(javax.swing.BorderFactory.createTitledBorder("First Name"));
        first_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                first_nameMouseClicked(evt);
            }
        });
        first_name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                first_nameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                first_nameFocusLost(evt);
            }
        });
        first_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                first_nameKeyReleased(evt);
            }
        });
        qwe.add(first_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 200, 180, 50));

        middle_name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        middle_name.setForeground(new java.awt.Color(204, 204, 204));
        middle_name.setText("Middle Name");
        middle_name.setToolTipText("Middle Name is optional");
        middle_name.setBorder(javax.swing.BorderFactory.createTitledBorder("Middle Name"));
        middle_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                middle_nameMouseClicked(evt);
            }
        });
        middle_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                middle_nameActionPerformed(evt);
            }
        });
        middle_name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                middle_nameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                middle_nameFocusLost(evt);
            }
        });
        qwe.add(middle_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, 180, 50));

        last_name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        last_name.setForeground(new java.awt.Color(51, 102, 0));
        last_name.setText("*Last Name");
        last_name.setToolTipText("Last Name");
        last_name.setBorder(javax.swing.BorderFactory.createTitledBorder("Last Name"));
        last_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                last_nameMouseClicked(evt);
            }
        });
        last_name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                last_nameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                last_nameFocusLost(evt);
            }
        });
        qwe.add(last_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 200, 180, 50));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(70, 42, 42));
        jLabel1.setText("Address");
        qwe.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, -1, -1));

        province.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        province.setForeground(new java.awt.Color(204, 204, 204));
        province.setText("Province");
        province.setToolTipText("Province is optional");
        province.setBorder(javax.swing.BorderFactory.createTitledBorder("Province"));
        province.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                provinceMouseClicked(evt);
            }
        });
        province.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                provinceActionPerformed(evt);
            }
        });
        province.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                provinceFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                provinceFocusLost(evt);
            }
        });
        qwe.add(province, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 420, 180, 50));

        zip_code.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        zip_code.setForeground(new java.awt.Color(204, 204, 204));
        zip_code.setText("Zip Code");
        zip_code.setToolTipText("Zip Code is optional");
        zip_code.setBorder(javax.swing.BorderFactory.createTitledBorder("Zip Code"));
        zip_code.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                zip_codeMouseClicked(evt);
            }
        });
        zip_code.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zip_codeActionPerformed(evt);
            }
        });
        zip_code.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                zip_codeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                zip_codeFocusLost(evt);
            }
        });
        qwe.add(zip_code, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 420, 180, 50));

        contact_number_2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        contact_number_2.setForeground(new java.awt.Color(204, 204, 204));
        contact_number_2.setText("Contact Number 2");
        contact_number_2.setToolTipText("Contact Number 2 is optional");
        contact_number_2.setBorder(javax.swing.BorderFactory.createTitledBorder("Contact Number 2"));
        contact_number_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contact_number_2MouseClicked(evt);
            }
        });
        contact_number_2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                contact_number_2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                contact_number_2FocusLost(evt);
            }
        });
        contact_number_2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                contact_number_2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                contact_number_2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                contact_number_2KeyTyped(evt);
            }
        });
        qwe.add(contact_number_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 270, 180, 50));

        contact_number_1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        contact_number_1.setForeground(new java.awt.Color(51, 102, 0));
        contact_number_1.setText("*Contact Number 1");
        contact_number_1.setToolTipText("Contact Number 1");
        contact_number_1.setBorder(javax.swing.BorderFactory.createTitledBorder("Conatct Number 1"));
        contact_number_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contact_number_1MouseClicked(evt);
            }
        });
        contact_number_1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                contact_number_1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                contact_number_1FocusLost(evt);
            }
        });
        contact_number_1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                contact_number_1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                contact_number_1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                contact_number_1KeyTyped(evt);
            }
        });
        qwe.add(contact_number_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 180, 50));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(70, 42, 42));
        jLabel3.setText("Owner Information");
        qwe.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, -1, -1));

        a.setColumns(20);
        a.setForeground(new java.awt.Color(255, 255, 255));
        a.setRows(5);
        jScrollPane1.setViewportView(a);

        qwe.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 520, 10, 0));

        jButton3.setText("No Contact Number");
        jButton3.setToolTipText("Hit this button if there is no available Contact Number");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        qwe.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 280, 180, 30));

        unit_number.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        unit_number.setForeground(new java.awt.Color(204, 204, 204));
        unit_number.setText("Unit Number");
        unit_number.setToolTipText("Unit Number is optional");
        unit_number.setBorder(javax.swing.BorderFactory.createTitledBorder("Unit Number"));
        unit_number.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                unit_numberMouseClicked(evt);
            }
        });
        unit_number.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                unit_numberFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                unit_numberFocusLost(evt);
            }
        });
        unit_number.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                unit_numberKeyTyped(evt);
            }
        });
        qwe.add(unit_number, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, 180, 50));

        house_street_number.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        house_street_number.setForeground(new java.awt.Color(204, 204, 204));
        house_street_number.setText("House/Street Number");
        house_street_number.setToolTipText("Unit Number is optional");
        house_street_number.setBorder(javax.swing.BorderFactory.createTitledBorder("House/Street Number"));
        house_street_number.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                house_street_numberMouseClicked(evt);
            }
        });
        house_street_number.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                house_street_numberFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                house_street_numberFocusLost(evt);
            }
        });
        house_street_number.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                house_street_numberKeyTyped(evt);
            }
        });
        qwe.add(house_street_number, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 350, 180, 50));

        city.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        city.setForeground(new java.awt.Color(204, 204, 204));
        city.setText("City");
        city.setToolTipText("City is optional");
        city.setBorder(javax.swing.BorderFactory.createTitledBorder("City"));
        city.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cityMouseClicked(evt);
            }
        });
        city.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cityActionPerformed(evt);
            }
        });
        city.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cityFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cityFocusLost(evt);
            }
        });
        qwe.add(city, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 420, 180, 50));
        qwe.add(ownid, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 20, 20));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(152, 152, 152));
        jLabel4.setText(" Optional");
        jLabel4.setToolTipText("Optional fields are not required and can be left blank.");
        qwe.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 100, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 102, 0));
        jLabel5.setText("*Required Fields");
        jLabel5.setToolTipText("Required fields are needed and should not be left blank.");
        qwe.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(70, 42, 42));
        jLabel2.setText("Legend");
        qwe.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboBox1.setBorder(javax.swing.BorderFactory.createTitledBorder("Street Name"));
        jComboBox1.setPreferredSize(new java.awt.Dimension(137, 40));
        qwe.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 350, 180, 50));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboBox2.setBorder(javax.swing.BorderFactory.createTitledBorder("Purok"));
        qwe.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 350, 180, 50));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboBox3.setBorder(javax.swing.BorderFactory.createTitledBorder("Barangay Name"));
        qwe.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 350, 180, 50));

        jToolBar1.setRollover(true);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Users.png"))); // NOI18N
        jButton1.setText("Save  Profile");
        jButton1.setFocusable(false);
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton1);

        update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Rename Document.png"))); // NOI18N
        update.setText("Update Profile");
        update.setFocusable(false);
        update.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        update.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        jToolBar1.add(update);

        add_pet.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Add Green Button.png"))); // NOI18N
        add_pet.setText("Add Pet");
        add_pet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_petActionPerformed(evt);
            }
        });
        jToolBar1.add(add_pet);

        qwe.add(jToolBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1350, 50));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Search Options"));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb3.setForeground(new java.awt.Color(70, 42, 42));
        lb3.setText("First Name");
        jPanel1.add(lb3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 180, 20));

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 180, 30));

        lb4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb4.setForeground(new java.awt.Color(70, 42, 42));
        lb4.setText("Middle Name");
        jPanel1.add(lb4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 180, 20));

        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 180, 30));

        lb2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lb2.setForeground(new java.awt.Color(70, 42, 42));
        lb2.setText("Last Name");
        jPanel1.add(lb2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 180, 20));

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 40, 180, 30));

        jCheckBox1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBox1.setText("Search by full name");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 40, -1, 30));

        qwe.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, 760, 90));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(qwe, javax.swing.GroupLayout.PREFERRED_SIZE, 1293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(qwe, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 47, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String tname = title_name.getText();
        String mname = middle_name.getText();
        String sname = suffix_name.getText();
        String cnum2 = contact_number_2.getText();
        String unum = unit_number.getText();
        String hsnum = house_street_number.getText();

        int topass = 0;

        int pass = 6;

        //1
        if(tname.equals("Title Name")||tname.equals(null)){
            title_name.setText(null);
        }else{
        }
        //2
        if(mname.equals("Middle Name")||mname.equals(null)){
            middle_name.setText(null);
        }else{
        }
        //3
        if(sname.equals("Suffix Name")||sname.equals(null)){
            suffix_name.setText(null);
        }else{
        }
        //4
        if(cnum2.equals("Contact Number 2")||cnum2.equals(+63)){
            contact_number_2.setText(null);
        }else{
        }
        //5
        if(unum.equals("Unit Number")||unum.equals(null)){
            unit_number.setText(null);
        }else{
        }
        //6
        if(hsnum.equals("House/Street Number")||hsnum.equals(null)){
            house_street_number.setText(null);
        }else{
        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        String fname = first_name.getText();
        if(fname.equals("*First Name")|| fname.equals("")){

        }else{
            topass++;
        }

        String lname = last_name.getText();
        if(lname.equals("*Last Name")|| lname.equals("")){

        }else{
            topass++;
        }

        String cnum1 = contact_number_1.getText();
        if(cnum1.equals("+63")|| cnum1.equals("")||cnum1.equals("*Contact Number 1")){

        }else{
            topass++;
        }

        String streetname = jComboBox1.getSelectedItem().toString();
        if(streetname.equals(" ")){

        }else{
            topass++;
        }

        String prk = jComboBox2.getSelectedItem().toString();
        if(prk.equals(" ")){

        }else{
            topass++;
        }

        String brgy = jComboBox3.getSelectedItem().toString();
        if(brgy.equals(" ")){

        }else{
            topass++;
        }


        //////////DECISION///////////////////////////////////////////////////////////////////////

        if(topass==pass){

            try{
                String sql = "Insert into owner (`title_name`,`first_name`,`middle_name`,`last_name`,`suffix_name`,`unit_number`,`house_street_number`,`street_name`,`purok`,`brgy_name`,`city`,`province`,`zip_code`,`contact_number_1`,`contact_number_2`) values (UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?),UPPER(?))";
                ps=conn_db.prepareStatement(sql);
                ps.setString(1, title_name.getText());
                ps.setString(2, first_name.getText());
                ps.setString(3, middle_name.getText());
                ps.setString(4, last_name.getText());
                ps.setString(5, suffix_name.getText());
                ps.setString(6, unit_number.getText());
                ps.setString(7, house_street_number.getText());
                ps.setString(8, jComboBox1.getSelectedItem().toString());
                ps.setString(9, jComboBox2.getSelectedItem().toString());
                ps.setString(10, jComboBox3.getSelectedItem().toString());
                ps.setString(11, city.getText());
                ps.setString(12, province.getText());
                ps.setString(13, zip_code.getText());
                ps.setString(14, contact_number_1.getText());
                ps.setString(15, contact_number_2.getText());
                ps.execute();

            }catch(Exception e){

                JOptionPane.showMessageDialog(null, e);

            }

            ///////////////////////GET OWNER_ID//////////

            try{
                String sql2 = "Select owner_id from owner where last_name = '" + last_name.getText() + "' and first_name ='"+ first_name.getText() +"'";
                ps = conn_db.prepareStatement(sql2);
                rs = ps.executeQuery();

                while(rs.next()){
                    int pidgetint = rs.getInt("owner_id");
                    String pidget = Integer.toString(pidgetint);
                    pid = pidget;
                    ownid.setText(pid);
                   
                }

            }catch(Exception e){

                JOptionPane.showMessageDialog(null, e);

            }
            
            jButton1.setEnabled(false);
            add_pet.setEnabled(true);
            update.setEnabled(true);
            //////hide search bar first////
            //////hide search bar first////
            //////save in jtable here
            
            au.action_type = "Saved - "+last_name.getText().toUpperCase()+", "+first_name.getText().toUpperCase()+" "+middle_name.getText().toUpperCase()+" profile";
            au.module_name = "Client Information Entry Module";
            au.saveAuditTRail();
            
            JOptionPane.showMessageDialog(null, "Profile for "+last_name.getText().toUpperCase()+", "+first_name.getText().toUpperCase()+" "+middle_name.getText().toUpperCase()+" has been successfully stored");
            
        //savestatus
        String content = "finished";
        String path = "C:\\Users\\Public\\Documents\\status_registration.txt";
        try {
            Files.write( Paths.get(path), content.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        //clear recovered    
        String cont = "";
            String path2 = "C:\\Users\\Public\\Documents\\recovered.txt";
            try {
                Files.write( Paths.get(path2), cont.getBytes(), StandardOpenOption.CREATE);
            } catch (IOException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            

        }else{
            JOptionPane.showMessageDialog(null, "Empty required fields detected!");       
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
         /************************************update***************************************************/
	
        String tname = title_name.getText();
        String mname = middle_name.getText();
        String sname = suffix_name.getText();
        String cnum2 = contact_number_2.getText();

        //1
        if(tname.equals("TITLE NAME")||tname.equals("Title Name")){
            title_name.setText("");
        }else{
        }
        //2
        if(mname.equals("MIDDLE NAME")||mname.equals("Middle Name")){
            middle_name.setText("");
        }else{
        }
        //3
        if(sname.equals("SUFFIX NAME")||sname.equals("Suffix Name")){
            suffix_name.setText("");
        }else{
        }
        //4
        if(cnum2.equals("CONTACT NUMBER 2")||cnum2.equals("+63")||cnum2.equals("Contact Number 2")){
            contact_number_2.setText("");
        }else{
        }
        
        
        try{
            
            String update = "UPDATE owner SET title_name = UPPER(?), first_name = UPPER(?), `middle_name` = UPPER(?),"
                    + " `last_name` = UPPER(?), `suffix_name` = UPPER(?), `unit_number` = UPPER(?),"
                    + " `house_street_number` = UPPER(?), `street_name` = UPPER(?), `purok` = UPPER(?), `brgy_name` = UPPER(?), `city` = UPPER(?), `province` = UPPER(?), `zip_code` = UPPER(?), `contact_number_1` = UPPER(?), `contact_number_2` = UPPER(?)"
                    + " WHERE `owner_id` = ?";
            
            ps=conn_db.prepareStatement(update);
            ps.setString(1, title_name.getText());
            ps.setString(2, first_name.getText());
            ps.setString(3, middle_name.getText());
            ps.setString(4, last_name.getText());
            ps.setString(5, suffix_name.getText());
            ps.setString(6, unit_number.getText());
            ps.setString(7, house_street_number.getText());
            ps.setString(8, jComboBox1.getSelectedItem().toString());
            ps.setString(9, jComboBox2.getSelectedItem().toString());
            ps.setString(10, jComboBox3.getSelectedItem().toString());
            ps.setString(11, city.getText());
            ps.setString(12, province.getText());
            ps.setString(13, zip_code.getText());
            ps.setString(14, contact_number_1.getText());
            ps.setString(15, contact_number_2.getText());
            ps.setString(16, ownid.getText());
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Profile for "+last_name.getText().toUpperCase()+", "+first_name.getText().toUpperCase()+" "+middle_name.getText().toUpperCase()+" has been successfully updated");
    
    ///////////////////////GET OWNER_ID//////////

            try{
                String sql2 = "Select owner_id from owner where last_name = '" + last_name.getText() + "' and first_name ='"+ first_name.getText() +"'";
                ps = conn_db.prepareStatement(sql2);
                rs = ps.executeQuery();

                while(rs.next()){
                    int pidgetint = rs.getInt("owner_id");
                    String pidget = Integer.toString(pidgetint);
                    pid = pidget;
                    ownid.setText(pid);

                }

            }catch(Exception e){

                JOptionPane.showMessageDialog(null, e);

            }
    
  
    ///**************************************************************************************************************//

            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        au.action_type = "Updated - "+last_name.getText().toUpperCase()+", "+first_name.getText().toUpperCase()+" "+middle_name.getText().toUpperCase()+" profile";
        au.module_name = "Client Information Entry Module";
        au.saveAuditTRail();
     
        /************************************update***************************************************/
        
        
    }//GEN-LAST:event_updateActionPerformed

    private void suffix_nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_suffix_nameMouseClicked

    }//GEN-LAST:event_suffix_nameMouseClicked

    private void suffix_nameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_suffix_nameFocusGained
        if(suffix_name.getText().equals("Suffix Name")){
            suffix_name.setText("");
            suffix_name.setForeground(Color.BLACK);
            savestatus();
        }else{

        }
    }//GEN-LAST:event_suffix_nameFocusGained

    private void suffix_nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_suffix_nameFocusLost
        if(suffix_name.getText().equals("")){
            suffix_name.setText("Suffix Name");
            suffix_name.setForeground(Color.GRAY);
        }else{
            //////
            //********************************************for auto_recovery**************************************************//
            a.append("SUFFIX NAME: "+suffix_name.getText()+"\n");

            String content = a.getText();
            String path = "C:\\Users\\Public\\Documents\\recovered.txt";
            try {
                Files.write( Paths.get(path), content.getBytes(), StandardOpenOption.CREATE);
            } catch (IOException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            //********************************************for auto_recovery**************************************************//
        }
    }//GEN-LAST:event_suffix_nameFocusLost

    private void title_nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_title_nameMouseClicked

    }//GEN-LAST:event_title_nameMouseClicked

    private void title_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_title_nameActionPerformed
        JComboBox x = new JComboBox();
        int set=0;
        String y[] = {"Mr","Mrs","Ms","Dr","Dra","Ma","Fr","Engr"};
        for(int c=0; c<=7; c++){
            set=c;
            x.addItem(y[set]);
        }
        JOptionPane.showMessageDialog(null, x);

        String getx = x.getSelectedItem().toString();

        title_name.setText(getx);title_name.setForeground(Color.BLACK);

        //********************************************for auto_recovery**************************************************//
        a.append("TITLE NAME: "+title_name.getText()+"\n");

        String content = a.getText();
        String path = "C:\\Users\\Public\\Documents\\recovered.txt";
        try {
            Files.write( Paths.get(path), content.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException ex) {
            Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        //********************************************for auto_recovery**************************************************//
    }//GEN-LAST:event_title_nameActionPerformed

    private void title_nameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_title_nameFocusGained
        if(title_name.getText().equals("Title Name")){
            title_name.setText("");
            title_name.setForeground(Color.BLACK);
            savestatus();
        }else{

        }
    }//GEN-LAST:event_title_nameFocusGained

    private void title_nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_title_nameFocusLost
        if(title_name.getText().equals("")){
            title_name.setText("Title Name");
            title_name.setForeground(Color.GRAY);
        }else{
            //////
            //********************************************for auto_recovery**************************************************//
            a.append(title_name.getText()+"\n");

            String content = a.getText();
            String path = "C:\\Users\\Public\\Documents\\recovered.txt";
            try {
                Files.write( Paths.get(path), content.getBytes(), StandardOpenOption.CREATE);
            } catch (IOException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            //********************************************for auto_recovery**************************************************//
        }
    }//GEN-LAST:event_title_nameFocusLost

    private void first_nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_first_nameMouseClicked

    }//GEN-LAST:event_first_nameMouseClicked

    private void first_nameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_first_nameFocusGained
        if(first_name.getText().equals("*First Name")){
            first_name.setText("");
            first_name.setForeground(Color.BLACK);
            savestatus();
        }else{

        }
    }//GEN-LAST:event_first_nameFocusGained

    private void first_nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_first_nameFocusLost
        if(first_name.getText().equals("")){
            first_name.setText("*First Name");
            first_name.setForeground(new Color(51,102,0));
        }else{
            //////
            //********************************************for auto_recovery**************************************************//
            a.setText("");
            a.append("FIRST NAME: "+first_name.getText()+"\n");

            String content = a.getText();
            String path = "C:\\Users\\Public\\Documents\\recovered.txt";
            try {
                Files.write( Paths.get(path), content.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            //********************************************for auto_recovery**************************************************//
        }
    }//GEN-LAST:event_first_nameFocusLost

    private void first_nameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_first_nameKeyReleased

    }//GEN-LAST:event_first_nameKeyReleased

    private void middle_nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_middle_nameMouseClicked

    }//GEN-LAST:event_middle_nameMouseClicked

    private void middle_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_middle_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_middle_nameActionPerformed

    private void middle_nameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_middle_nameFocusGained
        if(middle_name.getText().equals("Middle Name")){
            middle_name.setText("");
            middle_name.setForeground(Color.BLACK);
            savestatus();
        }else{

        }
    }//GEN-LAST:event_middle_nameFocusGained

    private void middle_nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_middle_nameFocusLost
        if(middle_name.getText().equals("")){
            middle_name.setText("Middle Name");
            middle_name.setForeground(Color.GRAY);
        }else{
            //////
            //********************************************for auto_recovery**************************************************//
            a.append("MIDDLE NAME: "+middle_name.getText()+"\n");

            String content = a.getText();
            String path = "C:\\Users\\Public\\Documents\\recovered.txt";
            try {
                Files.write( Paths.get(path), content.getBytes(), StandardOpenOption.CREATE);
            } catch (IOException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            //********************************************for auto_recovery**************************************************//
        }
    }//GEN-LAST:event_middle_nameFocusLost

    private void last_nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_last_nameMouseClicked

    }//GEN-LAST:event_last_nameMouseClicked

    private void last_nameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_last_nameFocusGained
        if(last_name.getText().equals("*Last Name")){
            last_name.setText("");
            last_name.setForeground(Color.BLACK);
            savestatus();
        }else{

        }
    }//GEN-LAST:event_last_nameFocusGained

    private void last_nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_last_nameFocusLost
        if(last_name.getText().equals("")){
            last_name.setText("*Last Name");
            last_name.setForeground(new Color(51,102,0));
        }else{
            //////
            //********************************************for auto_recovery**************************************************//
            a.append("LAST NAME: "+last_name.getText()+"\n");

            String content = a.getText();
            String path = "C:\\Users\\Public\\Documents\\recovered.txt";
            try {
                Files.write( Paths.get(path), content.getBytes(), StandardOpenOption.CREATE);
            } catch (IOException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            //********************************************for auto_recovery**************************************************//
        }
    }//GEN-LAST:event_last_nameFocusLost

    private void provinceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_provinceMouseClicked

    }//GEN-LAST:event_provinceMouseClicked

    private void provinceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_provinceActionPerformed
        
    }//GEN-LAST:event_provinceActionPerformed

    private void provinceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_provinceFocusGained
        if(province.getText().equals("Province")){
            province.setText("");
            province.setForeground(Color.BLACK);
        }else{

        }
    }//GEN-LAST:event_provinceFocusGained

    private void provinceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_provinceFocusLost
        if(province.getText().equals("")){
            province.setText("Province");
            province.setForeground(Color.GRAY);
        }else{
            //////
            //********************************************for auto_recovery**************************************************//
            a.append("PROVINCE: "+province.getText()+"\n");

            String content = a.getText();
            String path = "C:\\Users\\Public\\Documents\\recovered.txt";
            try {
                Files.write( Paths.get(path), content.getBytes(), StandardOpenOption.CREATE);
            } catch (IOException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            //********************************************for auto_recovery**************************************************//
        }
    }//GEN-LAST:event_provinceFocusLost

    private void zip_codeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_zip_codeMouseClicked

    }//GEN-LAST:event_zip_codeMouseClicked

    private void zip_codeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zip_codeActionPerformed
        zip_code.setText("9500");
    }//GEN-LAST:event_zip_codeActionPerformed

    private void zip_codeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_zip_codeFocusGained
        if(zip_code.getText().equals("Zip Code")){
            zip_code.setText("");
            zip_code.setForeground(Color.BLACK);
        }else{

        }
    }//GEN-LAST:event_zip_codeFocusGained

    private void zip_codeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_zip_codeFocusLost
        if(zip_code.getText().equals("")){
            zip_code.setText("Zip Code");
            zip_code.setForeground(Color.GRAY);
        }else{
            //////
            //********************************************for auto_recovery**************************************************//
            a.append("ZIP CODE: "+zip_code.getText()+"\n");

            String content = a.getText();
            String path = "C:\\Users\\Public\\Documents\\recovered.txt";
            try {
                Files.write( Paths.get(path), content.getBytes(), StandardOpenOption.CREATE);
            } catch (IOException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            //********************************************for auto_recovery**************************************************//
        }
    }//GEN-LAST:event_zip_codeFocusLost

    private void contact_number_2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contact_number_2MouseClicked

    }//GEN-LAST:event_contact_number_2MouseClicked

    private void contact_number_2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_contact_number_2FocusGained
        if(contact_number_2.getText().equals("Contact Number 2")){
            contact_number_2.setText("+63");
            contact_number_2.setForeground(Color.BLACK);
        }else{

        }
    }//GEN-LAST:event_contact_number_2FocusGained

    private void contact_number_2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_contact_number_2FocusLost
        if(contact_number_2.getText().equals("")){
            contact_number_2.setText("Contact Number 2");
            contact_number_2.setForeground(Color.GRAY);
        }else{
            //////
            //********************************************for auto_recovery**************************************************//
            a.append("CONTACT N0. 2: "+contact_number_2.getText()+"\n");

            String content = a.getText();
            String path = "C:\\Users\\Public\\Documents\\recovered.txt";
            try {
                Files.write( Paths.get(path), content.getBytes(), StandardOpenOption.CREATE);
            } catch (IOException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            //********************************************for auto_recovery**************************************************//
        }
    }//GEN-LAST:event_contact_number_2FocusLost

    private void contact_number_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contact_number_1MouseClicked

    }//GEN-LAST:event_contact_number_1MouseClicked

    private void contact_number_1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_contact_number_1FocusGained
        if(contact_number_1.getText().equals("*Contact Number 1")){
            contact_number_1.setText("+63");
            contact_number_1.setForeground(Color.BLACK);
            savestatus();
        }else{

        }
    }//GEN-LAST:event_contact_number_1FocusGained

    private void contact_number_1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_contact_number_1FocusLost
        if(contact_number_1.getText().equals("")){
            contact_number_1.setText("*Contact Number 1");
            contact_number_1.setForeground(new Color(51,102,0));
        }else{
            //////
            //********************************************for auto_recovery**************************************************//
            a.append("CONTACT N0. 1: "+contact_number_1.getText()+"\n");

            String content = a.getText();
            String path = "C:\\Users\\Public\\Documents\\recovered.txt";
            try {
                Files.write( Paths.get(path), content.getBytes(), StandardOpenOption.CREATE);
            } catch (IOException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            //********************************************for auto_recovery**************************************************//
        }
    }//GEN-LAST:event_contact_number_1FocusLost

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(count==0){
            count++;
            contact_number_1.setText(" ");
            contact_number_1.setEnabled(false);
            contact_number_2.setText(" ");
            contact_number_2.setEnabled(false);
            
            jButton3.setText("Reset Fields");
        }else{
            count--;
            contact_number_1.setText("*Contact Number 1");contact_number_1.setForeground(new Color(51,102,0));
            contact_number_1.setEnabled(true);
            contact_number_2.setText("Contact Number 2");contact_number_2.setForeground(new Color(204,204,204));
            contact_number_2.setEnabled(true);   
            
            jButton3.setText("No Contact Number");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void unit_numberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_unit_numberMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_unit_numberMouseClicked

    private void unit_numberFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_unit_numberFocusGained
        if(unit_number.getText().equals("Unit Number")){
            unit_number.setText("");
            unit_number.setForeground(Color.BLACK);
        }else{

        }
    }//GEN-LAST:event_unit_numberFocusGained

    private void unit_numberFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_unit_numberFocusLost
        if(unit_number.getText().equals("")){
            unit_number.setText("Unit Number");
            unit_number.setForeground(Color.GRAY);
        }else{
            //////
            //********************************************for auto_recovery**************************************************//
            a.append("UNIT NUMBER: "+unit_number.getText()+"\n");

            String content = a.getText();
            String path = "C:\\Users\\Public\\Documents\\recovered.txt";
            try {
                Files.write( Paths.get(path), content.getBytes(), StandardOpenOption.CREATE);
            } catch (IOException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            //********************************************for auto_recovery**************************************************//
        }
    }//GEN-LAST:event_unit_numberFocusLost

    private void house_street_numberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_house_street_numberMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_house_street_numberMouseClicked

    private void house_street_numberFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_house_street_numberFocusGained
        if(house_street_number.getText().equals("House/Street Number")){
            house_street_number.setText("");
            house_street_number.setForeground(Color.BLACK);
        }else{

        }
    }//GEN-LAST:event_house_street_numberFocusGained

    private void house_street_numberFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_house_street_numberFocusLost
        if(house_street_number.getText().equals("")){
            house_street_number.setText("House/Street Number");
            house_street_number.setForeground(Color.GRAY);
        }else{
            //////
            //********************************************for auto_recovery**************************************************//
            a.append("HOUSE STREET NUMBER: "+house_street_number.getText()+"\n");

            String content = a.getText();
            String path = "C:\\Users\\Public\\Documents\\recovered.txt";
            try {
                Files.write( Paths.get(path), content.getBytes(), StandardOpenOption.CREATE);
            } catch (IOException ex) {
                Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            //********************************************for auto_recovery**************************************************//
        }
    }//GEN-LAST:event_house_street_numberFocusLost

    private void cityMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cityMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cityMouseClicked

    private void cityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cityActionPerformed

    private void cityFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cityFocusGained

    }//GEN-LAST:event_cityFocusGained

    private void cityFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cityFocusLost

    }//GEN-LAST:event_cityFocusLost

    private void contact_number_1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contact_number_1KeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)){
            evt.consume();
            getToolkit().beep();
        }   
    }//GEN-LAST:event_contact_number_1KeyTyped

    private void contact_number_2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contact_number_2KeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)){
            evt.consume();
            getToolkit().beep();
        }   
    }//GEN-LAST:event_contact_number_2KeyTyped

    private void unit_numberKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_unit_numberKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)){
            evt.consume();
            getToolkit().beep();
        }   
    }//GEN-LAST:event_unit_numberKeyTyped

    private void house_street_numberKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_house_street_numberKeyTyped
        char c = evt.getKeyChar();
        if(!(Character.isDigit(c) || (c==KeyEvent.VK_BACK_SPACE) || c==KeyEvent.VK_DELETE)){
            evt.consume();
            getToolkit().beep();
        }   
    }//GEN-LAST:event_house_street_numberKeyTyped

    private void add_petActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_petActionPerformed
        new_pet cc = new new_pet();
        workstation_tab.addTab("Add Pet", cc);
        int xq = workstation_tab.getTabCount();
        workstation_tab.setSelectedIndex(xq-1);
        update.setEnabled(false);
        jButton1.setEnabled(true);
        owner_id_for_pet.setText(ownid.getText());
        owner_name_for_pet.setText(last_name.getText().toUpperCase()+", "+first_name.getText().toUpperCase()+" "+middle_name.getText().toUpperCase());
    }//GEN-LAST:event_add_petActionPerformed

    private void contact_number_1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contact_number_1KeyReleased
        String n = contact_number_1.getText();
        int num = n.length();
        if(num<=12){
           contact_number_1.setEditable(true);
        }else{
           contact_number_1.setEditable(false);
        }
    }//GEN-LAST:event_contact_number_1KeyReleased

    private void contact_number_1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contact_number_1KeyPressed
          if(evt.getKeyCode() == KeyEvent.VK_BACK_SPACE)
    {  
        contact_number_1.setText("+63");
    }
    }//GEN-LAST:event_contact_number_1KeyPressed

    private void contact_number_2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contact_number_2KeyReleased
        String n = contact_number_2.getText();
        int num = n.length();
        if(num<=12){
           contact_number_2.setEditable(true);
        }else{
           contact_number_2.setEditable(false);
        }
    }//GEN-LAST:event_contact_number_2KeyReleased

    private void contact_number_2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contact_number_2KeyPressed
              if(evt.getKeyCode() == KeyEvent.VK_BACK_SPACE)
    {  
        contact_number_2.setText("+63");
    }
    }//GEN-LAST:event_contact_number_2KeyPressed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        searchLastName();
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        searchFirstName();
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        searchMiddleName();
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        if(jCheckBox1.isSelected()){
            searchFullName();
        }else{
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            reset();
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextArea a;
    public static javax.swing.JButton add_pet;
    public static javax.swing.JTextField city;
    public static javax.swing.JTextField contact_number_1;
    public static javax.swing.JTextField contact_number_2;
    public static javax.swing.JTextField first_name;
    public static javax.swing.JTextField house_street_number;
    public static javax.swing.JButton jButton1;
    public static javax.swing.JButton jButton3;
    public static javax.swing.JCheckBox jCheckBox1;
    public static javax.swing.JComboBox jComboBox1;
    public static javax.swing.JComboBox jComboBox2;
    public static javax.swing.JComboBox jComboBox3;
    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel4;
    public static javax.swing.JLabel jLabel5;
    public static javax.swing.JPanel jPanel1;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextField jTextField1;
    public static javax.swing.JTextField jTextField2;
    public static javax.swing.JTextField jTextField3;
    public static javax.swing.JToolBar jToolBar1;
    public static javax.swing.JTextField last_name;
    public static javax.swing.JLabel lb2;
    public static javax.swing.JLabel lb3;
    public static javax.swing.JLabel lb4;
    public static javax.swing.JTextField middle_name;
    public static javax.swing.JLabel ownid;
    public static javax.swing.JTextField province;
    public static javax.swing.JPanel qwe;
    public static javax.swing.JTextField suffix_name;
    public static javax.swing.JTextField title_name;
    public static javax.swing.JTextField unit_number;
    public static javax.swing.JButton update;
    public static javax.swing.JTextField zip_code;
    // End of variables declaration//GEN-END:variables
}
