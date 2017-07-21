<%-- 
    Document   : about
    Created on : Jul 17, 2017, 3:21:42 PM
    Author     : Nitika
--%>

<<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
        
        <style>
            #text{
                font-size: 20px;
                background-color: #E1193D;
                color: white;
            }
        </style>
</head>
<body>
<h1 id="title" class="hidden"><span id="logo"><b> ABOUT <font color="#E1193D">Chat</font>Express</b></span><img src="${logo}" width="90px"; height="90px";></h1>
<br>
<center>
    <br>
    <div id="text" style="width: 80%;" >CHATEXPRESS is an interactive business chat platform where employess belonging to the same organization can chat with each other.They can ask their queries over discussion forum for which a score card will be generated according to performance of the registered employee.</div>
</center> 
</body>

<script>
	$(document).ready(function () {
    	$('#logo').addClass('animated fadeInDown');
    	$("input:text:visible:first").focus();
	});
</script>
</html>