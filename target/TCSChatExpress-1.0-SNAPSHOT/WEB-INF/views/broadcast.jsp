<%-- 
    Document   : broadcast
    Created on : Jul 20, 2017, 12:31:03 PM
    Author     : shivangi
--%>

<%@page import="java.io.DataInputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.tcs.chat.store.connection"%>
<%@page import="com.tcs.chat.store.MessageHandlerDAO"%>
<%@include file="includes.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>        
        
        <spring:url value="/resources/css/Messages.css" var="Messages" />
        <spring:url value="/resources/css/bootstrapM.css" var="bootstrapMCSS" />
        <spring:url value="/resources/js/Messages.js" var="Messages" />
        <spring:url value="/resources/js/bootstrapM.js" var="bootstrapMJS" />        
        
        <link href="${Messages}" rel="stylesheet" />
        <link href="${bootstrapMCSS}" rel="stylesheet" />
        <script src="${Messages}"></script>
        <script src="${bootstrapMJS}"></script> 
        
    </head>
    <body>
        
                <style>
.hi {
    width: 100%;
    padding: 0.2px;
    background-color: #665851;
    font-family: sans-serif;
    font-size: 5px;
    color: white;
    margin-top: -12px;
    margin-bottom: -2px !important;
}
ul {
    list-style-type: none;
    margin: 0;
    margin-bottom: -2px;
    padding: 1px;
    overflow: hidden;
    background-color: #333;
    margin-bottom: 0px !important;
    margin-top: 0px !important;
}

li {
    float: left;
    border-right:1px solid #bbb;
}

li:last-child {
    border-right: none;
}

#active {
    background-color: #72b352;
}

.header{
	
        background:none;
        display: block;
        color: white;
        text-align: center;
        padding: 14px 16px;
        text-decoration: none;
        border: none;
        margin-bottom: -7px;
}

.header:hover 
{
	background-color:#72b352;
        cursor: pointer;
}

#broadcastTable 
{
    display:block;
    height:380px;
    overflow:auto;
    overflow-wrap: break-word;
}

#broadcastTable thead, #broadcastTable tbody tr 
{
    display:table;
    width:100%;
    table-layout:fixed;
    text-align: center;
}

</style>    
    
            <div class="hi">     
                <h3>Hello, ${EmpName} (ID : ${EmpId}) </h3></div>
<ul>
    <li>
    <form action='chatRoom.htm' method='POST'>
        <input type='hidden' id='empId' name="empId" value="${EmpId}"/>
        <input class="header" type="submit" value="CHAT ROOM" />
    </form>
          </li>
  
    <li>
    <form action='discuss.htm' method='POST'>
        <input type='hidden' id='empId' name="empId" value="${EmpId}"/>
        <input class="header" type="submit" value="DISCUSSION FORUM" />
    </form>
          </li>
  
          <li>
    <form action='offlineUsersPage.htm' method='POST'>
        <input type='hidden' id='empId' name="empId" value="${EmpId}"/>
        <input class="header" type="submit" value="MESSAGES" />
    </form>
          </li>
          
          <li>
    <form action='broadcast.htm' method='POST'>
        <input type='hidden' id='empId' name="empId" value="${EmpId}"/>
        <input class="header" id="active" type="submit" value="BROADCAST" />
    </form>
          </li>
  
          <li>
    <form action='dashboard.htm' method='POST'>
        <input type='hidden' id='empId' name="empId" value="${EmpId}"/>
        <input class="header" type="submit" value="DASHBOARD" />
    </form>
          </li>
          
          <li style="float:right">
    <form action='logoutSuccess.htm' method='POST'>
        <input type='hidden' id='empId' name="empId" value="${EmpId}"/>
        <input class="header" type="submit" value="LOGOUT" />
    </form>
          </li>
</ul>
        <br><br><br>
        
    <center>
        <button id="myBtn" class="btn bg-success" style="background-color: #72b352; color: whitesmoke;">SEND BROADCAST MESSAGE</button></center>
        
    <br><br><br>
    
    <div class="row">
    <div class="col-xs-12 col-md-12">
        <table class="table table-condensed table-hover table-bordered" id="broadcastTable" width="500">
            <thead>
            <tr>
                <th width="80"> SENT BY EMPLOYEE</th>
                <th width="420">MESSAGE</th>
            </tr>
            </thead>
            <tbody>
                <%
                    MessageHandlerDAO msgHDao = new MessageHandlerDAO();
                     int bcount = 0;
        try
        {
         connection co = new connection();
         Connection con = co.establishConnection();
         String SQLQuery = "select bcount from broadcast";
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
        
        System.out.println("bcount "+bcount+" "+msgHDao.Bcount());
        if(bcount>0&&msgHDao.Bcount()>0)
        {
            String fileLocation="";                     
            String filePath = fileLocation+"broadcast.csv";
            
            String thisLine;
            FileInputStream fis = new FileInputStream(filePath);
            DataInputStream myInput = new DataInputStream(fis);
            
            while((thisLine = myInput.readLine()) != null) 
            {
                %><tr><td  width="80"><%
                String strar[] = thisLine.split(",");
                out.println(strar[0]+"-"+msgHDao.EmployeeName(Integer.parseInt(strar[0])));
                
                %></td><td  width="420"><%
                
                out.println(strar[1]);
                %></td></tr><%
            }
        }        
                %>
            </tbody>
        </table>
    </div>
    </div>
            
</body>
    
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">BroadCast message</h4>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label for="recipient-name" class="control-label">Recipient:</label>
            <input type="text" readonly class="form-control" id="recipient-name" value="@ALL" />
          </div>
          <div class="form-group">
            <label for="message-text" class="control-label">Message:</label>
            <textarea class="form-control" id="message-text"></textarea>
          </div>
        </form>
      </div>
      <div class="modal-footer">
          <p style="float: left" id="message"></p>
          <button style="float: right" type="button" id="SendBbutton" class="btn btn-primary">Send message</button>
      </div>
    </div>
  </div>
</div>
    
    <script>

$('#myBtn').on('click',function()
{
    $("#exampleModal").modal("show");
});

$(document).ready(function() 
{
     $("#SendBbutton").click(function() 
     {
         var from = '${emp.eid}';
         var to = '0';
         var message = $("#message-text").val();
         
         if(message!=='')
         {
             $.ajax({
             url : './OfflineChatHandler?operation=broadCast&&from='+from+"&to="+to+"&message="+message,
             dataType : "json",
             type : "Post",
             success : function(resultans) 
             {
                 $("#message").text('BROADCAST SUCCESSFULLY SENT').delay(1000).fadeOut('slow');     
                 
                      var markup = "<tr><td> ${emp.eid} </td><td>"+message+"</td></tr>";
                      $("#broadcastTable tbody").append(markup);
                      
//                 $("#message-text").val('done');
             },
             error : function(responseText) 
             {
                alert(" Error inserting the data ! "+responseText);
             }
             });
         }         
    });
});
    </script>
    
</html>
