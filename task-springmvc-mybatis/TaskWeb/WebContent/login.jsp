<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.LoginVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="script/css/login/style.css" rel="stylesheet" type="text/css" media="all" />
</head>
<body>

		<!-- contact-form -->	
<div class="message warning">
<div class="contact-form">
	<div class="logo">
		<h1>Sign In</h1>
	</div>	
<!--- form --->
<form class="form" action="login" method="post" name="contact_form">
	<ul>
		<li>
			 <label><img src="images/login/contact.png" alt=""></label>
			 <input type="text"  name="usercode"  required />		            
		 </li>
		 <li>
			 <label><img src="images/login/lock.png" alt=""></label>
			 <input type="Password" name="password" placeholder="Password" required />		         
		 </li>
		 <li class="style">
		     <input type="Submit" value="Submit">
		 </li>
	</ul>	
	<c:out value="${loginVO.getError()}"></c:out> 
	<div class="clear"></div>	   	
</form>
</div>
<div class="alert-close"></div>
</div>
<div class="clear"></div>
<!--- footer --->
<div class="footer">
	<p>Create by <a href="#">guananfang</a></p>
</div>
		
</body>
</html>