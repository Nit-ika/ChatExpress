
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>

<spring:url value="/resources/css/style.css" var="style" />
<spring:url value="/resources/css/chatBox.css" var="chatBox" />
<spring:url value="/resources/css/bubbles.css" var="bubbles" />

<link href="${style}" rel="stylesheet" />
<link href="${chatBox}" rel="stylesheet" />
<link href="${bubbles}" rel="stylesheet" />

<spring:url value="/resources/js/jquery-1.12.3.min.js" var="jquery" />
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
    
            <div class="hi">     
                <h1>Hello, ${EmpName} (ID : ${EmpId}) </h1></div>
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
        <input class="header" id="active" type="submit" value="DISCUSSION FORUM" />
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
        
        <div class="workspace">
            <h2 style="color: black">${ques.question}</h2>
            
            <table><tbody>
            <c:if test="${not empty ansList}">
                        <c:forEach items ="${ansList}" var="ans">
                            <tr>
                                <td>${ans.answer}</td>
                            </tr>                        
                        </c:forEach>
            </c:if>
                </tbody></table>
                            
                        <c:if test="${empty ansList}">
                            <h3>No Answers to display</h3>
                        </c:if>
                            
                            
                        <form action="PostAns.htm" method="POST">
                            <input type='hidden' id='empId' name="empId" value="${EmpId}"/>
                            <input type='hidden' id='qId' name="qId" value="${ques.qid}"/>
                            Post Your Answers here :<br/><br/>
                            <input type="text" name="answer" size="100"/>
                         <br/><br/>
                         <input type="submit" name="action" value="Post"/>
                        </form>
                            
        </div>   
    </body>
</html>