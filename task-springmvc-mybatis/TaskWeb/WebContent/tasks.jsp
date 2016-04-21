<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.TasksVO"%>
<%@page import="model.TaskVO"%>
<%@page import="model.User"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Show Tasks</title>
<link href="script/css/templet/bootstrap.min.css" rel="stylesheet">
<link href="script/css/templet/datepicker3.css" rel="stylesheet">
<link href="script/css/templet/bootstrap-table.css" rel="stylesheet">
<link href="script/css/templet/styles.css" rel="stylesheet">

<!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
<script src="js/respond.min.js"></script>
<![endif]-->
<script src="script/js/jquery.js"></script>
</head>
<body>
	<%
		TasksVO tasksVO = (TasksVO) request.getAttribute("tasksVO");
	    User  user =(User)request.getAttribute("task_user");
	%>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#sidebar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"><span>Lumino</span>Admin</a>
			<ul class="user-menu">
				<li class="dropdown pull-right"><a href="#"
					class="dropdown-toggle" data-toggle="dropdown"><span
						class="glyphicon glyphicon-user"></span> <%=user.getUserCode() %><span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
<!-- 						<li><a href="#"><span class="glyphicon glyphicon-user"></span>
								Profile</a></li>
						<li><a href="#"><span class="glyphicon glyphicon-cog"></span>
								Settings</a></li> -->
						<li><a href="logout"><span class="glyphicon glyphicon-log-out"></span>
								Logout</a></li>
					</ul></li>
			</ul>
		</div>

	</div>
	<!-- /.container-fluid --> </nav>

	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
			<div class="form-group">
			</div>
		<ul class="nav menu">
			<!-- 			<li><a href="index.html"><span class="glyphicon glyphicon-dashboard"></span> Dashboard</a></li>
			<li><a href="widgets.html"><span class="glyphicon glyphicon-th"></span> Widgets</a></li>
			<li><a href="charts.html"><span class="glyphicon glyphicon-stats"></span> Charts</a></li> -->
			<li class="active"><a href="tables.html"><span
					class="glyphicon glyphicon-list-alt"></span> Tasks</a></li>
			<!-- 			<li><a href="forms.html"><span class="glyphicon glyphicon-pencil"></span> Forms</a></li>
			<li><a href="panels.html"><span class="glyphicon glyphicon-info-sign"></span> Alerts &amp; Panels</a></li> -->
<!-- 			<li class="parent "><a href="#"> <span
					class="glyphicon glyphicon-list"></span> Dropdown <span
					data-toggle="collapse" href="#sub-item-1" class="icon pull-right"><em
						class="glyphicon glyphicon-s glyphicon-plus"></em></span>
			</a>
				<ul class="children collapse" id="sub-item-1">
					<li><a class="" href="#"> <span
							class="glyphicon glyphicon-share-alt"></span> Sub Item 1
					</a></li>
					<li><a class="" href="#"> <span
							class="glyphicon glyphicon-share-alt"></span> Sub Item 2
					</a></li>
					<li><a class="" href="#"> <span
							class="glyphicon glyphicon-share-alt"></span> Sub Item 3
					</a></li>
				</ul></li> -->
			<li role="presentation" class="divider"></li>
<!-- 			<li><a href="login.html"><span
					class="glyphicon glyphicon-user"></span> Login Page</a></li> -->
		</ul>
		<div class="attribution">
			Template by <a
				href="http://www.medialoot.com/item/lumino-admin-bootstrap-template/">Medialoot</a>
		</div>
	</div>
	<!--/.sidebar-->

	<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
				<li class="active">Tasks</li>
			</ol>
		</div>
		<!--/.row-->
		<!--/.row-->


		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-body">
						<table data-toggle="table" data-show-refresh="true"
							data-show-toggle="true" data-show-columns="true"
							data-search="true" data-select-item-name="toolbar1"
							data-pagination="true" data-sort-name="name"
							data-sort-order="desc">
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
							<c:forEach items="${tasksVO.taskList}" var="taskvo">
								<tr>
									<td>${taskvo.task.id}</td>
									<td>${taskvo.task.title}</td>
									<c:choose>
										<c:when test="${taskvo.task.description.length()>50}">
											<td title='${taskvo.task.description}'>${taskvo.task.description.substring(0,50)}</td>
										</c:when>
										<c:otherwise>
											<td>${taskvo.task.description}</td>
										</c:otherwise>
									</c:choose>
									<td>${taskvo.user.userName}</td>
									<td>${taskvo.task.starttime}</td>
									<td>${taskvo.task.executendtime}</td>
									<td>${taskvo.task.type}</td>
									<td>${taskvo.task.executestatus}</td>
									<td><a href="detailtask?id=${taskvo.task.id}">查看</a></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
		<!--/.row-->
	</div>
	<!--/.main-->

	<script src="script/js/templet/jquery-1.11.1.min.js"></script>
	<script src="script/js/templet/bootstrap.min.js"></script>
	<script src="script/js/templet/chart.min.js"></script>
	<script src="script/js/templet/chart-data.js"></script>
	<script src="script/js/templet/easypiechart.js"></script>
	<script src="script/js/templet/easypiechart-data.js"></script>
	<script src="script/js/templet/bootstrap-datepicker.js"></script>
	<script src="script/js/templet/bootstrap-table.js"></script>
	<script>
		!function($) {
			$(document)
					.on(
							"click",
							"ul.nav li.parent > a > span.icon",
							function() {
								$(this).find('em:first').toggleClass(
										"glyphicon-minus");
							});
			$(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
		}(window.jQuery);

		$(window).on('resize', function() {
			if ($(window).width() > 768)
				$('#sidebar-collapse').collapse('show')
		})
		$(window).on('resize', function() {
			if ($(window).width() <= 767)
				$('#sidebar-collapse').collapse('hide')
		})
	</script>
</body>
</html>