package com.tcs.chat.store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class connection 
{
   public Connection establishConnection()throws SQLException
   {     
      String driverName = "com.mysql.jdbc.Driver";
      String URL = "jdbc:mysql://localhost:3307/chat";
      String uname = "tcswork";
      String pass = "tcswork";
      Connection con=null;
      
      try 
      {
         Class.forName(driverName);
         con = DriverManager.getConnection(URL, uname, pass);
      } 
      catch (ClassNotFoundException ex) 
      {
         Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      return con;
    }
}
