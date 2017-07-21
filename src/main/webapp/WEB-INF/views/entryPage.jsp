<%-- 
    Document   : entryPage
    Created on : Jul 15, 2017, 7:30:17 PM
    Author     : shivangi
--%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<spring:url value="/resources/css/style.css" var="style" />
<spring:url value="/resources/css/chatBox.css" var="chatBox" />
<spring:url value="/resources/css/bubbles.css" var="bubbles" />
<spring:url value="/resources/images/online-icon.png" var="online" />

<link href="${style}" rel="stylesheet" />
<link href="${chatBox}" rel="stylesheet" />
<link href="${bubbles}" rel="stylesheet" />

<spring:url value="/resources/js/jquery-1.12.3.min.js" var="jquery" />
<script src="${jquery}"></script>
     <spring:url value="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js" var="jquery" />
        <script src="${jquery}"></script>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>JSP Page</title>
        
        <style>
.hi {
    width: 100%;
    padding: 0.5px;
    background-color: #665851;
    font-family: sans-serif;
    font-size: 10px;
    color: white;
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
        margin-bottom: -20px;
}

.header:hover 
{
	background-color:#72b352;
        cursor: pointer;
}

</style>
        
    </head>    
    
        <body>  
    <div id="chatBox"></div>     
    
            <div class="hi">     
                <h1>Hello, ${EmpName} (ID : ${EmpId}) </h1></div>
        
     <div id="title" class="title">
         Current Online Users
     </div>
           
<ul>
    <li>
    <form action='chatRoom.htm' method='POST'>
        <input type='hidden' id='empId' name="empId" value="${EmpId}"/>
        <input class="header" id="active" type="submit" value="CHAT ROOM" />
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
        

	<div class="box">
		<div class="content">
			<div id="container">
				<div id="loginPanel">
					<div id="infoLabel"></div>
					<div style="padding: 10px; text-align: center; vertical-align: middle;">
                                            <div id="textTrial">CREATING YOUR CHAT ROOM</div>
                                            <input id="txtLoginID" type="hidden" class="textlog"
							onkeyup="proxy.login_keyup(event)" /><br>
                                            <input id="txtLoginName" type="hidden" class="textlog"
							onkeyup="proxy.login_keyup(event)" /><br>
						<br> <a href="#" class="myButton" onclick="proxy.login()">Login</a>
					</div>
				</div>
                                <div id="msgPanel">
					<div id="msgContainer" style="overflow: auto;"></div>
				</div>
			</div>
		</div>
	</div>       
        
        
        <script type="text/javascript">
		var proxy = null;
	</script>
        
        <spring:url value="/resources/js/chatroom.js" var="chatroom" />
	<script src="${chatroom}" type="text/javascript"></script>      
        
	<%
		String WsUrl = getServletContext().getInitParameter("WsUrl");
                System.out.println("--> "+WsUrl);
                WsUrl = "ws://localhost:8080/TCSChatExpress/joinRoom";
                System.out.println("--> "+WsUrl);
	%>
	<script>
		$("#msgPanel").hide();
		var wsUri = '<%=WsUrl%>';
		proxy = CreateProxy(wsUri);
		//var url = "ws://" + location.hostname + ":" + location.port + "/" + location. + "/joinRoom";
		document.addEventListener("DOMContentLoaded", function(event) {
                    document.getElementById('txtLoginID').value= '${EmpId}';
                    document.getElementById('txtLoginName').value= '${EmpName}';
			console.log(document.getElementById('loginPanel'));
			proxy.initiate({
				loginPanel : document.getElementById('loginPanel'),
				msgPanel : document.getElementById('msgPanel'),
				txtMsg : document.getElementById('txtMsg'),
				txtLoginID : document.getElementById('txtLoginID'),
				txtLoginName : document.getElementById('txtLoginName'),
				msgContainer : document.getElementById('msgContainer')
			});
                        proxy.login();
		});
	</script>
        
        <spring:url value="/resources/js/chatWindow.js" var="chatWindow" />
	<script src="${chatWindow}" type="text/javascript"></script>  
        
    </body>
</html>