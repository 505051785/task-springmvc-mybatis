<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.LoginVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		LoginVO loginVO = null;
		String error = "";
		if (request.getAttribute("loginVO") != null) {
			loginVO = (LoginVO) request.getAttribute("loginVO");
			if (loginVO.getError() != null) {
				error = loginVO.getError();
			}
		}
	%>
	<form action="login" method="post" >
		用户名：<input type="text" name="usercode" /> 密码：<input type="password"
			name="password" /> <input type="submit" name="ss" />
	</form>
	<div><%=error%></div>
</body>
</html>