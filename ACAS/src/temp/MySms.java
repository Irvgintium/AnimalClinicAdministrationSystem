/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package temp;
import gnu.io.*;
import java.util.Enumeration;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class MySms {
 static Enumeration portList;
 static CommPortIdentifier portId;
 static SerialPort serialPort;
 static OutputStream outputStream;
 static InputStream inputStream;
 static boolean outputBufferEmptyFlag = false;
 public String defaultPort;
 public MySms(String comport){
  // File Variable Initialization
  try{
   file=new FileOutputStream("Record.txt");
   out=new PrintWriter(file,true);
  }catch(Exception e){System.out.println("File Error!!!");}
  
  
  defaultPort=comport;
  boolean portFound = false;
  portList = CommPortIdentifier.getPortIdentifiers();
  while (portList.hasMoreElements()) {
   portId = (CommPortIdentifier) portList.nextElement();
      if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
       if (portId.getName().equals(defaultPort)) {
        System.out.println("Got Hold on " + defaultPort);
     portFound = true;
        try {         
         serialPort =(SerialPort) portId.open("BULK-SMS", 2000);
        }
        catch (PortInUseException e) {
         System.out.println("Port in use.");
         JOptionPane.showMessageDialog(null, "Mobile Not Connected 1...", "Simple SMS Machine", JOptionPane.ERROR_MESSAGE);
        }

        try {         
         outputStream = serialPort.getOutputStream();
         inputStream = serialPort.getInputStream();
        }catch (IOException e) {
         e.printStackTrace();
         JOptionPane.showMessageDialog(null, "Mobile Not Connected 2...", "Simple SMS Machine", JOptionPane.ERROR_MESSAGE);
        }

        try {
         serialPort.setSerialPortParams(460800,
              SerialPort.DATABITS_8,
              SerialPort.STOPBITS_1,
              SerialPort.PARITY_NONE);
        }
        catch (UnsupportedCommOperationException e) {
          JOptionPane.showMessageDialog(null, "Mobile Not Connected 3...", "Simple SMS Machine", JOptionPane.ERROR_MESSAGE);
        }

        try {
         serialPort.notifyOnOutputEmpty(true);
        }catch (Exception e) {
         System.out.println("Error setting event notification");
         System.out.println(e.toString());
         JOptionPane.showMessageDialog(null, "Mobile Not Connected 4...", "Simple SMS Machine", JOptionPane.ERROR_MESSAGE);
        }
       }
      }
  }

  if (!portFound) {
      System.out.println("port " + defaultPort + " not found.");
      JOptionPane.showMessageDialog(null, "Mobile Not Connected 5...", "Simple SMS Machine", JOptionPane.ERROR_MESSAGE);
  }
  
  
  try{
  
  String sendCmd = "AT+CMGF=1\r";
   outputStream.write(sendCmd.getBytes());
   Thread.sleep(500);
   if(!read(sendCmd.length(),1))
    System.exit(0);
  
  /*  
  // New Command Which set SMSC number for ZONG
   String smscCmd = "AT+CSCA=\"+923189244444\",145\r";
   outputStream.write(sendCmd.getBytes());
   Thread.sleep(500);
   if(!read(sendCmd.length(),1))
    System.exit(0);*/
    
   JOptionPane.showMessageDialog(null, "Mobile Connected Successfully...", "Simple SMS Machine", JOptionPane.INFORMATION_MESSAGE);
  }catch(Exception ex){ex.printStackTrace();}
 }
 public boolean sendSMS(String phone,String msg){
  try {
   Thread.sleep(500);
   String sendCmd = "AT+CMGS=\"" + phone + "\"\r";
   outputStream.write(sendCmd.getBytes());
   Thread.sleep(500);
   if(!read(sendCmd.length(),0)){
    System.exit(0);
   }
   outputStream.write(msg.getBytes());
   this.ctrlZ();
   System.out.println("This is The Point 1");
   Thread.sleep(4000);
   if(!read(msg.length(),1)){
    System.out.println("This is The Point");
    System.exit(0);
    return false;
   }
   count++;
   System.out.println("Count= "+count);   
   out.println(""+count+"               "+phone+"               "+status);
   
     
   return true;
  
  }
  catch (Exception e) {System.out.println("SMS Error");return false;}
 }

 public void ctrlZ(){
  try {

   outputStream.write(26);
  }catch (Exception e) {}
 }
 
 
 public boolean read(int length,int check) {
  while(true){
   
   try{
    
    if(inputStream.available()>0){
     msg=msg+(char)inputStream.read();
     //System.out.println("Response: "+msg);
    }
    if(msg.indexOf("OK")!=-1&&check==1){
     System.out.println("Response: "+msg);
     msg="";
     return true;
    }
    if(msg.indexOf(">")!=-1&&check==0){
     System.out.println("Response: "+msg);
     msg="";
     return true;
    }
    
    
   }catch(Exception e){System.out.println("Reading Error!!! Modem Not Responding!!!");}
  }
  
 }

private int count=0;
private String status="Message Sent";
private String msg;
FileOutputStream file;
PrintWriter out;
}