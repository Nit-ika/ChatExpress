<%-- 
    Document   : offlineUsersPage
    Created on : Jul 19, 2017, 8:18:22 AM
    Author     : shivangi
--%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
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

#messageTable 
{
    display:block;
    height:480px;
    overflow:auto;
    overflow-wrap: break-word;
}

#messageTable thead, #messageTable tbody tr 
{
    display:table;
    width:100%;
    table-layout:fixed;
    text-align: center;
}

#msg{
    background-color: #333;
    border: none;
    color: white;
    text-align: center;
    text-decoration: none;
    font-size: 16px;
    cursor: pointer;
    margin: 3px;
    padding: 1px;
    overflow: hidden;
    margin-bottom: 0px !important;
    margin-top: 12px;
    margin-left: 10px;
    margin-right: 10px;
}
#addTable{
                max-width: 600px;
                max-height: 300px;
                overflow-y: auto;
            }  

</style>
    
<div class="hi"><h3>Hello, ${EmpName} (ID : ${EmpId})</h3></div>
           
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
        <input class="header" id="active" type="submit" value="MESSAGES" />
    </form>
          </li>
          
          <li>
    <form action='broadcast.htm' method='POST'>
        <input type='hidden' id='empId' name="empId" value="${EmpId}"/>
        <input class="header" type="submit" value="BROADCAST" />
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
        
        <%
            Map<String, Integer> usersList = new HashMap<String, Integer>();
        %>
        
        <c:forEach items="${empList}" var="emp">
             <c:set var="otherId" value="${emp.eid}"/>
             <c:set var="otherName" value="${emp.ename}"/>
             <%
                 int otherId = (Integer) pageContext.getAttribute("otherId");
                 String otherName = (String) pageContext.getAttribute("otherName");
                 String userName = otherId+"-"+otherName;
                 usersList.put(userName, 0);
             %>
	</c:forEach>
        
        <c:set var="mainId" value="${EmpId}"/>
        <c:set var="sendEids" value="${msg.sendEids}"/>
        
        <br><br>
        
    <div class="row">
    <div class="col-xs-12 col-md-12">
        <table class="table table-condensed table-hover table-bordered" id="messageTable" width="500">
            <thead>
            <tr>
                <th width="80">EMPLOYEE ID</th>
                <th width="420">EMPLOYEE NAME &emsp;(CLICK ON EMPLOYEE NAME TO VIEW CHAT)</th>
            </tr>
            </thead>
            <tbody>

<%
           Integer mainId = (Integer) pageContext.getAttribute("mainId");
           
           if(pageContext.getAttribute("sendEids")!=null)
           {
               String[] sendEids = ((String) pageContext.getAttribute("sendEids")).split(",");
           
           for(int loop = 0; loop<sendEids.length; loop++)
           {
                  int sendId = Integer.parseInt(sendEids[loop]);
                  int small = mainId, large = sendId;
                  if(small>large)
                  {
                      small = sendId;
                      large = mainId;
                  } 
                  
                  MessageHandlerDAO msgHDao = new MessageHandlerDAO();
                  String chatName = msgHDao.EmployeeName(sendId), fileName = small+"-"+large;
                  usersList.put(sendId+"-"+chatName, 1);
%>
                   
<tr data-id="<%=chatName%>,<%=mainId%>,<%=sendId%>,<%=fileName%>">
    <td width="80"><%out.println(sendId);%></td>
    <td width="420"><%=chatName%></td>
</tr>

<%
           }
           
           for(Map.Entry<String,Integer> user : usersList.entrySet())
           {
                String key = user.getKey();
                Integer value = user.getValue();
                
                if(value==0)
                {
                    String[] chatdetails = key.split("-");
                    int chatID = Integer.parseInt(chatdetails[0]);
                    String chatN = chatdetails[1];
                    
                    if(chatID!=mainId)
                    {
                        int s = mainId, l = chatID;
                        if(s>l)
                        {
                            s = l;
                            l = mainId;
                        } 
                        String fileN = s+"-"+l;
 %>
                   
<tr data-id="<%=chatN%>,<%=mainId%>,<%=chatID%>,<%=fileN%>">
    <td width="80"><%out.println(chatID);%></td>
    <td width="420"><%=chatN%></td>
</tr>

<%
                    }
                }
           }
           
           }
%>

            </tbody>
        </table>
    </div>
</div>
        
    </body>
    
<div class="modal fade" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <center><h4 class="modal-title" id="chatName"></h4></center>
      </div>
      <div class="modal-body">
          <input type="hidden" id="details"/>
      
      <div id="messageDiv">
          <form id="sendForm" action="#" method="post">
              <input type="hidden" id="from"/>
              <input type="hidden" id="to"/>
              <br><textarea id="sendBox" class="form-control" placeholder="Write Message here.."></textarea><br>
                <center><input type="button" value="Send Message" id="sendBoxBtn" class="btn btn-success"/></center>
        </form>
      </div>
          <br><br>
      <center>
          <div id="addTable"></div>
      </center>
      </div>
      <div class="modal-footer">
          <p id="message"></p>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
    
    <script>

$('table tbody tr').on('click',function()
{
    $("#myModal").modal("show");
    $("#details").text($(this).data('id'));
    
    var details= $("#details").text();    
    var chatName = details.split(',')[0];
    $("#chatName").text(chatName);    
    
    $("#from").val(details.split(',')[1]);
    $("#to").val(details.split(',')[2]);
    
    $("#addTable").html('');
    $.get("DataDisplay.jsp",{details:details},function(data)
    {      
        $("#addTable").html(data);
    });
});


$(document).ready(function() 
{
     $("#sendBoxBtn").click(function() 
     {
         var from = $('#from').val();
         var to = $('#to').val();
         var message = $("#sendBox").val();
         
         $.ajax({
             url : './OfflineChatHandler?operation=chat&from='+from+"&to="+to+"&message="+message,
             dataType : "json",
             type : "Post",
             success : function(resultans) 
             {
                 $("#message").text(resultans).delay(1000).fadeOut('slow');     
                 
                      var markup = "<tr><td> You : <br>Message : "+message+"</td></tr>";
                      $("#msgTable tbody").append(markup);
                      
//                 $("#sendBox").val('done');
             },
             error : function(responseText) 
             {
                alert(" Error inserting the data ! "+responseText);
             }
         });
         
    });
});
    </script>
    
</html>
