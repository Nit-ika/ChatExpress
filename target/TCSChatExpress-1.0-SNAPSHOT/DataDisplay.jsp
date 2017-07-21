<%-- 
    Document   : DataDisplay
    Created on : Jul 19, 2017, 10:37:03 PM
    Author     : shivangi
--%>

<%@page import="com.tcs.chat.store.MessageHandlerDAO"%>
<%@page import="java.io.DataInputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
    </head>
    
      <style>
            #msgTable{
                
    max-height: 70px;
    width:100%;
    border-collapse: collapse;
    overflow-y:auto;
    overflow-wrap: break-word;
            }
            </style>
    
    <body>
        
        <table id="msgTable" border="1"><tbody>
        <%
            String details="", fileLocation="";
//            MessageHandlerDAO msgHDao = new MessageHandlerDAO();
            
            details = request.getParameter("details");
//            out.println(details);
            
            String[] IDs = details.split(",");
            Integer mainID = Integer.parseInt(IDs[1]);
            
//            Integer toID = Integer.parseInt(IDs[2]);
            String toName = IDs[0];
            
            String fileName = IDs[3];            
            String filePath = fileLocation+fileName+".csv";
            
            String thisLine;
            FileInputStream fis = new FileInputStream(filePath);
            DataInputStream myInput = new DataInputStream(fis);
            
            while ((thisLine = myInput.readLine()) != null) 
            {
                %><tr><td><%
                String strar[] = thisLine.split(",");
                
                    if(Integer.parseInt(strar[0])==mainID)
                    {
                        System.out.println("You");
                        out.println("You");                        
                        %><br><%
//                        System.out.println("To : "+strar[1]);
//                        out.println("To : "+strar[1]);
                    }
                    else 
                    {
                        System.out.println("Sent by : "+toName);
                        out.println("Sent by : "+toName);                        
                        %><br><%
//                        System.out.println("To you : "+strar[1]);
//                        out.println("To you : "+strar[1]);
                    }
                        System.out.println("Message : "+strar[2]);
                        out.println("Message : "+strar[2]);
                        %></td></tr><%
            }
        %>
        </tbody></table>
    </body>
</html>