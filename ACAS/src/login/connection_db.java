package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class connection_db {
    
    Connection conn_db = null;
    
    public static void main(String[] args) {
        
    }
    
    public static Connection ConnectDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection db_conn = DriverManager.getConnection("jdbc:mysql://localhost/acas_db","root","");
            return db_conn;
        }catch (ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
}