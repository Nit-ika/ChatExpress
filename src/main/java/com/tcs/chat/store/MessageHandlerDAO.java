package com.tcs.chat.store;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MessageHandlerDAO
{
    public void addMessage(String message, String fromId, String toId)
    {
        int fromIdInt = Integer.parseInt(fromId), toIdInt = Integer.parseInt(toId); 
            
            int chkIds[] = chkMessageIds(fromIdInt, toIdInt);
            if(chkIds[0]==0)
            {
                addId(fromIdInt, toIdInt); //add to table
            }
            
            if(chkIds[0]!=2&&chkIds[1]==0)
            {
                addId(toIdInt, fromIdInt); //add to table
            }
            
            if(chkIds[0]==1)
            {
                updateId(fromIdInt, toIdInt); //add that this file exists
            }
            
            if(chkIds[0]!=2&&chkIds[1]==1)
            {
                updateId(toIdInt, fromIdInt); //add that this file exists
            }
            
            apendTofile(fromIdInt, toIdInt, message);   
        
        System.out.println("MessageHandlerDAO "+message+" "+fromIdInt+" "+toIdInt);
    }   

    public int[] chkMessageIds(int fromIdInt, int toIdInt) 
    {
        int[] result = new int[2];
        int flagFrom = 0, flagTo = 0;//not found from id
        
        String IDsOFPersonChattedWith = checkId(fromIdInt);        
        if(IDsOFPersonChattedWith==null)
        {
            IDsOFPersonChattedWith = checkId(toIdInt);
            if(IDsOFPersonChattedWith==null)
                return result;
            else
            {
                flagTo++;
                result[1]=flagTo;
                return result;
            }
        }
        else
        {
            flagFrom++;
            String[] details = IDsOFPersonChattedWith.split(",");
            for(String chk: details)
            {
                int chkInt = Integer.parseInt(chk);
                if(chkInt==toIdInt)
                {
                    flagFrom++;
                    break;
                }
            }
            result[0]=flagFrom;
            
            if(flagFrom==1)
            {
                IDsOFPersonChattedWith = checkId(toIdInt);
                if(IDsOFPersonChattedWith!=null)
                    flagTo++;
                
                result[1]=flagTo;
            }
        }
        
        return result;
    }

    private void apendTofile(int fromIdInt, int toIdInt, String message) 
    {
        int small = fromIdInt, large = toIdInt;
        if(small>large)
        {
            small = toIdInt;
            large = fromIdInt;
        }        
        String fileName = small+"-"+large+".csv",
               filePath = ""+fileName;
        
        try
        {
          File file =new File(filePath);
          if(file.exists())
          {
              countUpdate(fileName);
          }
    	  if(!file.exists())
          {
    	 	file.createNewFile();
                countAdd(fileName);
    	  }
          
    	  FileWriter fw = new FileWriter(file,true);
    	  BufferedWriter bw = new BufferedWriter(fw);
    	  PrintWriter pw = new PrintWriter(bw);
          
    	  pw.println(String.format("%d, %d, %s", fromIdInt, toIdInt, message));          
    	  pw.close();

	  System.out.println("Data successfully appended at the end of file");

       }
       catch(IOException e)
       {
    	   System.out.println("Exception occurred:");
    	   e.printStackTrace();
       }
    }

    public String checkId(int fromIdInt) 
    {
        String IDsOFPersonChattedWith = null;
        try
        {
         connection co = new connection();
         Connection con = co.establishConnection();
         String SQLQuery = "select sendEids from messages where receiveEid = "+fromIdInt;
         ResultSet rs;
         
            try
            {
                Statement st = con.createStatement();
                rs = st.executeQuery(SQLQuery);
                while(rs.next())
                {
                    IDsOFPersonChattedWith = rs.getString(1);
                }  
                st.close();
                rs.close();
            }
            catch(Exception e){}
            
            con.close();
        }
        catch(Exception e){}
        
        return IDsOFPersonChattedWith;
    }

    private void addId(int fromIdInt, int toIdInt) 
    {        
        int rowCount = 0;
        try
        {
         connection co = new connection();
         Connection con = co.establishConnection();
         String sendEids = toIdInt+",";
         
         String SQL_QUERY = "insert into messages(receiveEid, sendEids) values(?,?)";
            
            PreparedStatement pst = con.prepareStatement(SQL_QUERY);
            pst.setInt(1, fromIdInt); 
            pst.setString(2, sendEids);
            
            rowCount = pst.executeUpdate();
            System.out.println(" addID "+fromIdInt+" "+sendEids);
            
            pst.close();
            con.close();
        }
        catch(Exception e){}
    }

    private void updateId(int fromIdInt, int toIdInt) 
    {
        String IDsOFPersonChattedWith = checkId(fromIdInt)+toIdInt+",";        
        int rowCount = 0;
        
        try
        {
         connection co = new connection();
         Connection con = co.establishConnection();
         String sendEids = toIdInt+",";
         
         String SQL_QUERY = "update messages set sendEids = ? where receiveEid = ?";
            
            PreparedStatement pst = con.prepareStatement(SQL_QUERY);
            pst.setString(1, IDsOFPersonChattedWith);
            pst.setInt(2, fromIdInt); 
            
            rowCount = pst.executeUpdate();
            System.out.println(" addID "+fromIdInt+" "+sendEids);
            
            pst.close();
            con.close();
        }
        catch(Exception e){}        
    }

    private void countAdd(String fileName) 
    {
        int rowCount = 0;
        try
        {
         connection co = new connection();
         Connection con = co.establishConnection();
         
         String SQL_QUERY = "insert into messagesFile values(?,?)";
            
            PreparedStatement pst = con.prepareStatement(SQL_QUERY);
            pst.setString(1, fileName); 
            pst.setInt(2, 1);
            
            rowCount = pst.executeUpdate();
            System.out.println(" countAdd "+fileName+" "+rowCount);
            
            pst.close();
            con.close();
        }
        catch(Exception e){}
    }

    private void countUpdate(String fileName) 
    {
        try
        {
         connection co = new connection();
         Connection con = co.establishConnection();
         int rowCount =0 ;
         
         String SQL_QUERY = "update messagesFile set countLines = (countLines+1) where fileName = ?";
            
            PreparedStatement pst = con.prepareStatement(SQL_QUERY);
            pst.setString(1, fileName); 
            
            rowCount = pst.executeUpdate();
            System.out.println(" countUpdate "+rowCount);
            
            pst.close();
            con.close();
        }
        catch(Exception e){}        
    }
    
    public String EmployeeName(int eid)
    {
        String empName = null;
        try
        {
         connection co = new connection();
         Connection con = co.establishConnection();
         String SQLQuery = "select ename from employees where eid = "+eid;
         ResultSet rs;
         
            try
            {
                Statement st = con.createStatement();
                rs = st.executeQuery(SQLQuery);
                while(rs.next())
                {
                    empName = rs.getString(1);
                }  
                st.close();
                rs.close();
            }
            catch(Exception e){}
            
            con.close();
        }
        catch(Exception e){}
        
        return empName;
    }

    public int findLoginStatus(int to) 
    {
        int loginStatus = 0;
        try
        {
         connection co = new connection();
         Connection con = co.establishConnection();
         String SQLQuery = "select loginStatus from employees where eid = "+to;
         ResultSet rs;
         
            try
            {
                Statement st = con.createStatement();
                rs = st.executeQuery(SQLQuery);
                while(rs.next())
                {
                    loginStatus = rs.getInt(1);
                }  
                st.close();
                rs.close();
            }
            catch(Exception e){}
            
            con.close();
        }
        catch(Exception e){}
        
        return loginStatus;
    }
    
    public void logoutEmp(String leid)
    {
        int eid = Integer.parseInt(leid);
        try
        {
         connection co = new connection();
         Connection con = co.establishConnection();
         int rowCount =0 ;
         
         String SQL_QUERY = "update employees set loginStatus = 0 where eid = ?";
            
            PreparedStatement pst = con.prepareStatement(SQL_QUERY);
            pst.setInt(1, eid); 
            
            rowCount = pst.executeUpdate();
            System.out.println(" logoutEmp "+rowCount);
            
            pst.close();
            con.close();
        }
        catch(Exception e){}   
    }

    public void broadcast(String message, String from, String to) 
    {
        System.out.println("in broadcast "+message);
        if(to.equals("0")) //all
        {
            String fileName = "broadcast.csv",
               filePath = ""+fileName;
        
        try
        {
          File file =new File(filePath);
          if(file.exists())
          {
              countUpdateB(fileName);
          }
    	  if(!file.exists())
          {
    	 	file.createNewFile();
                countAddB(fileName);
    	  }
          
          
    	  FileWriter fw = new FileWriter(file,true);
    	  BufferedWriter bw = new BufferedWriter(fw);
    	  PrintWriter pw = new PrintWriter(bw);
          
    	  pw.println(String.format("%d, %s", Integer.parseInt(from), message));          
    	  pw.close();

	  System.out.println("BroadCast -> Data successfully appended at the end of file");

       }
       catch(IOException e)
       {
    	   System.out.println("Exception occurred:");
    	   e.printStackTrace();
       }
        }
    }
    
    private void countAddB(String fileName) 
    {
        int rowCount = 0;
        try
        {
         connection co = new connection();
         Connection con = co.establishConnection();
         
         String SQL_QUERY = "insert into broadcast values(?)";
            
            PreparedStatement pst = con.prepareStatement(SQL_QUERY);
            pst.setInt(1, 1);
            
            rowCount = pst.executeUpdate();
            System.out.println(" countAdd "+fileName+" "+rowCount);
            
            pst.close();
            con.close();
        }
        catch(Exception e){}
    }

    private void countUpdateB(String fileName) 
    {
        try
        {
         connection co = new connection();
         Connection con = co.establishConnection();
         int rowCount =0 ;
         
         String SQL_QUERY = "update broadcast set bcount = (bcount+1)";
            
            PreparedStatement pst = con.prepareStatement(SQL_QUERY);
            
            rowCount = pst.executeUpdate();
            System.out.println(" countUpdate "+rowCount);
            
            pst.close();
            con.close();
        }
        catch(Exception e){}        
    }
    
    public int Bcount() 
    {
        int bcount = 0;
        try
        {
         connection co = new connection();
         Connection con = co.establishConnection();
         String SQLQuery = "select count(bcount) from broadcast";
         ResultSet rs;
         
            try
            {
                Statement st = con.createStatement();
                rs = st.executeQuery(SQLQuery);
                while(rs.next())
                {
                    bcount = rs.getInt(1);
                }  
                st.close();
                rs.close();
            }
            catch(Exception e){}
            
            con.close();
        }
        catch(Exception e){}
        
        return bcount;     
    }
}