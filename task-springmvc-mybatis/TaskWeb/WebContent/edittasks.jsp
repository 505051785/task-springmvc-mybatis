<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.TasksVO"%>
<%@page import="model.TaskVO"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show Tasks</title>
<style type="text/css">
body {
	width: 1000px;
	margin: 40px auto;
	font-family: 'trebuchet MS', 'Lucida sans', Arial;
	font-size: 14px;
	color: #444;
}

table {
	*border-collapse: collapse; /* IE7 and lower */
	border-spacing: 0;
	width: 100%;
}

.bordered {
	border: solid #ccc 1px;
	-moz-border-radius: 6px;
	-webkit-border-radius: 6px;
	border-radius: 6px;
	-webkit-box-shadow: 0 1px 1px #ccc;
	-moz-box-shadow: 0 1px 1px #ccc;
	box-shadow: 0 1px 1px #ccc;
}

.bordered tr:hover {
	background: #fbf8e9;
	-o-transition: all 0.1s ease-in-out;
	-webkit-transition: all 0.1s ease-in-out;
	-moz-transition: all 0.1s ease-in-out;
	-ms-transition: all 0.1s ease-in-out;
	transition: all 0.1s ease-in-out;
}

.bordered td, .bordered th {
	border-left: 1px solid #ccc;
	border-top: 1px solid #ccc;
	padding: 10px;
	text-align: left;
}

.bordered th {
	background-color: #dce9f9;
	background-image: -webkit-gradient(linear, left top, left bottom, from(#ebf3fc),
		to(#dce9f9));
	background-image: -webkit-linear-gradient(top, #ebf3fc, #dce9f9);
	background-image: -moz-linear-gradient(top, #ebf3fc, #dce9f9);
	background-image: -ms-linear-gradient(top, #ebf3fc, #dce9f9);
	background-image: -o-linear-gradient(top, #ebf3fc, #dce9f9);
	background-image: linear-gradient(top, #ebf3fc, #dce9f9);
	-webkit-box-shadow: 0 1px 0 rgba(255, 255, 255, .8) inset;
	-moz-box-shadow: 0 1px 0 rgba(255, 255, 255, .8) inset;
	box-shadow: 0 1px 0 rgba(255, 255, 255, .8) inset;
	border-top: none;
	text-shadow: 0 1px 0 rgba(255, 255, 255, .5);
}

.bordered td:first-child, .bordered th:first-child {
	border-left: none;
}

.bordered th:first-child {
	-moz-border-radius: 6px 0 0 0;
	-webkit-border-radius: 6px 0 0 0;
	border-radius: 6px 0 0 0;
}

.bordered th:last-child {
	-moz-border-radius: 0 6px 0 0;
	-webkit-border-radius: 0 6px 0 0;
	border-radius: 0 6px 0 0;
}

.bordered th:only-child {
	-moz-border-radius: 6px 6px 0 0;
	-webkit-border-radius: 6px 6px 0 0;
	border-radius: 6px 6px 0 0;
}

.bordered tr:last-child td:first-child {
	-moz-border-radius: 0 0 0 6px;
	-webkit-border-radius: 0 0 0 6px;
	border-radius: 0 0 0 6px;
}

.bordered tr:last-child td:last-child {
	-moz-border-radius: 0 0 6px 0;
	-webkit-border-radius: 0 0 6px 0;
	border-radius: 0 0 6px 0;
}
</style>
</head>
<body>
	<%
	TasksVO tasksVO = (TasksVO) request.getAttribute("tasksVO");
	%>
	<a  href="detailtask" >新增</a>
	<table class="bordered">
		<thead>
			<tr>
				<th width="5%">编号</th>
				<th width="20%">任务标题</th>
				<th width="35%">任务描述</th>
				<th width="8%">执行者</th>
				<th width="10%">提交时间</th>
				<th width="10%">截止日期</th>
				<th width="5%">类型</th>
				<th width="5%">状态</th>
				<th width="7%">操作</th>
			</tr>
		</thead>

<%
		    TaskVO taskVO=new TaskVO();
		   List<TaskVO> tasksVOs =tasksVO.getTaskList();
			for (int i = 0; i < tasksVOs.size(); i++) {
				taskVO=tasksVOs.get(i);
		%>
		<tr>
			<td><%=taskVO.getTask().getId()%></td>
			<td><%=taskVO.getTask().getTitle()%></td>
			<%int cutlength=taskVO.getTask().getDescription().length()>=50?50:taskVO.getTask().getDescription().length();%>
			<td title="<%=taskVO.getTask().getDescription()%>"><%=taskVO.getTask().getDescription().substring(0, cutlength) %></td>
			<td><%=taskVO.getUser().getUserName()%></td>
			<td><%=taskVO.getTask().getStarttime()%></td>
			<td><%=taskVO.getTask().getExecutendtime()%></td>
			<td><%=taskVO.getTask().getType()%></td>
			<td><%=taskVO.getTask().getExecutestatus()%></td>
			<td><a href="edittask?id=<%=taskVO.getTask().getId()%>">查看</a></td>
		</tr>
		<%
			}
		%>

	</table>

	<br>
	<br>

</body>
</html>