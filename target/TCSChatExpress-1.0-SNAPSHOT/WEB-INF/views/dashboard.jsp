<%-- 
    Document   : entryPage
    Created on : Jul 15, 2017, 7:30:17 PM
    Author     : shivangi
--%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<spring:url value="/resources/css/style.css" var="style" />
 <spring:url value="/resources/css/animate.css" var="animate" />
<link href="${style}" rel="stylesheet" />
 <link href="${animate}" rel="stylesheet" />
 
<spring:url value="/resources/js/jquery-1.12.3.min.js" var="jquery" />
<script src="${jquery}"></script>
     <spring:url value="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js" var="jquery" />
        <script src="${jquery}"></script>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Broadcast</title>
               <style>
                   

.lgn-box {
	background-color: white;
	max-width: 740px;
	margin: 0 auto;
	position: relative;
	top: 50px;
	padding-bottom: 40px;
	border-radius: 5px;
	box-shadow: 0 5px 50px rgba(0,0,0,0.4);
	text-align: center;
}
                   
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
    
            <div class="hi">     
                <h1>Hello, ${EmpName} (ID : ${EmpId}) </h1></div>
           
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
                
                <div class="lgn-box animated fadeInUp">
                    <div class="box-header" style="background-color: #665851;color: whitesmoke">
				<h2>Employee Details</h2>
			</div>
                    <center>
                    <table border="1">
	<th>Eid</th>
	<th>Ename</th>
	<th>DOB</th>
	<th>Address</th>
	<th>Phone No</th>
	<th>Email</th>
	<th>verifykey</th>
	<th>Department</th>
        
	<c:forEach items="${empList}" var="emp">
		<tr>
			<td>${emp.eid}</td>
			<td>${emp.ename}</td>
			<td>${emp.dob}</td>
			<td>${emp.address}</td>
			<td>${emp.phoneNo}</td>
			<td>${emp.email}</td>
			<td>${emp.verifykey}</td>
			<td>${emp.depName}</td>
		</tr>
	</c:forEach>
</table>
        </center>               
		</div>
        
    </body>
</html>