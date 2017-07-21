<%-- 
    Document   : emp
    Created on : Jul 13, 2017, 7:40:47 PM
    Author     : shivangi
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>ChatExpress</title>

	<!-- Google Fonts -->
        <spring:url value="/resources/css/style.css" var="style" />
        <spring:url value="/resources/css/animate.css" var="animate" />
        <spring:url value="/resources/images/logo.png" var="logo" />
        
        <link href="${style}" rel="stylesheet" />
        <link href="${animate}" rel="stylesheet" />
	<link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Lato:400,100,300,700,900' rel='stylesheet' type='text/css'>
        <spring:url value="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js" var="jquery" />
        <script src="${jquery}"></script>
        
        <script src="<c:url value="/resources/js/valid.js" />"></script>
        <script src="<c:url value="/resources/js/regvalid.js" />"></script>

<style>
	.error, .verifykey { 
		color: red; font-weight: bold; 
	}
</style>

</head>
<body>
    <div class="container">
		<div class="top">
     
         <a href="about.htm" class="button"><b>About</b></a>
		<h1 id="title" class="hidden"><span id="logo"><b><font color="#E1193D">Chat</font>Express</b></span><img src="${logo}" width="90px"; height="90px";></h1>
		</div>
                
		<div class="login-box animated fadeInUp">
			<div class="box-header">
				<h2>Register/Log In</h2>
			</div>
                    
            <c:if test="${not empty verifykey}">
                <div id="verifykey" style="color: red">YOUR VERIFY KEY TO LOGIN IS => ${verifykey}</div>
            </c:if>
                    
			<br/>
			<button id="myBtn">Register</button>
                        <button id="myBtn1">Login</button>
			<br/>
			</br>
		</div>
	</div>
                
                
<div id="myModal1" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <div class="modal-header">
      <span class="close" id="close1">&times;</span>
      <h2>LOGIN</h2>
    </div>
    <div class="modal-body">
      <form:form id="form" action="EmpLogin.htm" method="POST" onsubmit="return validate()" modelAttribute="empLogin">
	<table>
		<tr>
                    <td>Eid<font style="color:red";>*</font></td>
			<td><form:input path="leid" onkeyup="validateEid(form)"/></td>
                        <td><form:errors path="leid" cssClass="error"/></td>
                        <td><font id="leidError" style="color: red;">${eidExistError}
                            </font></td>
		</tr>
		<tr>
			<td>Password<font style="color:red";>*</font></td>
			<td><form:input path="lpassword" type="password" onkeyup="validatePassword(form)"/></td>
                        <td><form:errors path="lpassword" cssClass="error"/></td>
                        <td><font id="lpasswordError" style="color: red;">${passwordError}
                            </font></td>
                </tr>
                <tr>
			<td>verifyKey<font style="color:red";>*</font></td>
			<td><form:input path="lverifykey" onkeyup="verifyKeyvalidate(form)"/></td>
                        <td><form:errors path="lverifykey" cssClass="error"/></td>
                        <td><font id="lkeyError" style="color: red;">${keyError}</font></td>
		</tr>
		<tr>
			<td colspan="2">
                                <input type="submit" name="action" onClick="return validate()" value="Login"/>
			</td>
		</tr>
	</table>
</form:form>
    </div>
    <div class="modal-footer">
        <h3>ChatExpress</h3><h5><font style="color:red";>*</font> fields are mandatory</h5>
    </div>
  </div>

</div>
			

<div id="myModal" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <div class="modal-header">
      <span class="close" id="close">&times;</span>
      <h2>REGISTER</h2>
    </div>
    <div class="modal-body">
      <form:form id="regform" action="EmpRegister.htm" method="POST" onsubmit="return regvalidate()" modelAttribute="empRegister">
	<table>
		<tr>
			<td>Eid<font style="color:red";>*</font></td>
			<td><form:input path="eid" onkeyup="validateregEid(regform)"/></td>
                        <td><form:errors path="eid" cssClass="error"/></td>
                        <td><font id="eidregError" style="color: red;">${eidExistError}
                            </font></td>
		</tr>
		<tr>
			<td>Ename<font style="color:red";>*</font></td>
			<td><form:input path="ename" onkeyup="validateEname(regform)"/></td>
                        <td><form:errors path="ename" cssClass="error"/></td>
                        <td><font id="enameError" style="color: red;">${enameExistError}
                            </font></td>
		</tr>
		<tr>
			<td>DOB</td>
			<td><form:input path="dob" /></td>
                        <td><form:errors path="dob" cssClass="error"/></td>
		</tr>
		<tr>
			<td>Address</td>
			<td><form:input path="address" /></td>
                        <td><form:errors path="address" cssClass="error"/></td>
		</tr>
		<tr>
			<td>Phone No<font style="color:red";>*</font></td>
			<td><form:input path="phoneNo" onkeyup="validatephone(regform)"/></td>
                        <td><form:errors path="phoneNo" cssClass="error"/></td>
                        <td><font id="phoneError" style="color: red;">${phoneExistError}
		</tr>
		<tr>
			<td>Email<font style="color:red";>*</font></td>
			<td><form:input path="email" type="email" onkeyup="validateEmail(regform)"/></td>
                        <td><form:errors path="email" cssClass="error"/></td>
                        <td><font id="emailError" style="color: red;">${emailExistError}
                            </font></td>
		</tr>
		<tr>
			<td>Password<font style="color:red";>*</font></td>
			<td><form:input path="password" type="password" onkeyup="validateregPassword(regform)"/></td>
                        <td><form:errors path="password" cssClass="error"/></td>
                        <td><font id="regpasswordError" style="color: red;">${regpasswordError}
                            </font></td>
		</tr>
                <tr>
                    <td>Confirm Password<font style="color:red";>*</font></td>
                    <td><input type="password" name="confirm_password" id="confirm_password" onkeyup="validateregPassword(regform)"/></td>
                        <td><font id="confirmPasswordError" style="color: red;"></font></td>
		</tr>
		<tr>
			<td>Department</td>
			<td><form:input path="depName" /></td>
                        <td><form:errors path="depName" cssClass="error"/></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" name="action" value="Register" onClick="return regvalidate()"/>
<!--				<input type="submit" name="action" value="Add" />
				<input type="submit" name="action" value="Edit" />
				<input type="submit" name="action" value="Delete" />
				<input type="submit" name="action" value="Search" />-->
			</td>
		</tr>
	</table>
</form:form>
    </div>
    <div class="modal-footer">
      <h3>ChatExpress</h3><h5><font style="color:red";>*</font> fields are mandatory</h5>
    </div>
  </div>
</div>               
</body>


<script>
// Get the modal
var modal = document.getElementById('myModal');
var modal1 = document.getElementById('myModal1');
// Get the button that opens the modal
var btn = document.getElementById("myBtn");
var btn1 = document.getElementById("myBtn1");


// Get the <span> element that closes the modal
var span1 = document.getElementById("close1");
var span = document.getElementById("close");
//var span1 = document.getElementsByClassName("close")[0];
// When the user clicks the button, open the modal 
btn.onclick = function() {
    modal.style.display = "block";
};
btn1.onclick = function() {
    modal1.style.display = "block";
};

// When the user clicks on <span> (x), close the modal
span1.onclick = function() {
    modal1.style.display = "none";
};
span.onclick = function() {
    modal.style.display = "none";
};
//function myFunction() {
//    var person = alert("Your Verify Key is:",${verifyKey});
//}
// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target === modal) {
        modal.style.display = "none";
    }
};
window.onclick = function(event) {
    if (event.target === modal1) {
        modal1.style.display = "none";
    }
};

	$(document).ready(function () {
    	$('#logo').addClass('animated fadeInDown');
    	$("input:text:visible:first").focus();
	});
	$('#password').focus(function() {
		$('label[for="password"]').addClass('selected');
	});
	$('#password').blur(function() {
		$('label[for="password"]').removeClass('selected');
	});
</script>

</html>
