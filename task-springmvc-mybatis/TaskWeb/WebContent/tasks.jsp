<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.TasksVO"%>
<%@page import="model.TaskVO"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Show Tasks</title>
<script src="script/js/jquery.js"></script>
<style type="text/css">
body {
	width: 1000px;
	margin: 40px auto;
	font-family: 'trebuchet MS', 'Lucida sans', Arial;
	font-size: 12px;
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

.ui-page {
	height: 60px;
	margin: 10px 0 20px;
	color: #999;
	font-size: 14px;
	font-weight: 700;
	font-family: \5B8B\4F53, Helvetica, sans-serif;
	text-align: center;
}

.ui-page a, .ui-page b {
	float: left;
}

.ui-page b {
	font-weight: 400;
}

.ui-page-num {
	padding-top: 19px;
	background:
		url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEYAAAAUCAMAAAAHtrtKAAACTFBM…JVAx1fngUOJHJuUuAY4ZkjOMheVNMBmpGMzYOaDBsmH9VUGlSdguSxQAAAABJRU5ErkJggg==")
		no-repeat right 0;
}

.ui-page-wrap {
	display: inline-block;
	zoom: 1;
}

DIV.quotes {
	PADDING-RIGHT: 3px;
	PADDING-LEFT: 3px;
	PADDING-BOTTOM: 3px;
	MARGIN: 3px;
	PADDING-TOP: 3px;
	TEXT-ALIGN: center;
}

DIV.quotes SPAN.disabled {
	BORDER-RIGHT: #f3f3f3 1px solid;
	PADDING-RIGHT: 5px;
	BORDER-TOP: #f3f3f3 1px solid;
	PADDING-LEFT: 5px;
	PADDING-BOTTOM: 2px;
	BORDER-LEFT: #f3f3f3 1px solid;
	COLOR: #ccc;
	MARGIN-RIGHT: 2px;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #f3f3f3 1px solid;
}

DIV.quotes SPAN.current {
	BORDER-RIGHT: #e0e0e0 1px solid;
	PADDING-RIGHT: 5px;
	BORDER-TOP: #e0e0e0 1px solid;
	PADDING-LEFT: 5px;
	FONT-WEIGHT: bold;
	PADDING-BOTTOM: 2px;
	BORDER-LEFT: #e0e0e0 1px solid;
	COLOR: #aaa;
	MARGIN-RIGHT: 2px;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #e0e0e0 1px solid;
	BACKGROUND-COLOR: #f0f0f0;
}

DIV.quotes A {
	BORDER-RIGHT: #ddd 1px solid;
	PADDING-RIGHT: 5px;
	BORDER-TOP: #ddd 1px solid;
	PADDING-LEFT: 5px;
	PADDING-BOTTOM: 2px;
	BORDER-LEFT: #ddd 1px solid;
	COLOR: #aaa;
	MARGIN-RIGHT: 2px;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #ddd 1px solid;
	TEXT-DECORATION: none;
}
</style>
</head>
<body>
	<%
		TasksVO tasksVO = (TasksVO) request.getAttribute("tasksVO");
	%>
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

	<div class="quotes">
		<%=tasksVO.getPageFooter()%>
		<input id="topage" type="text" />页 <a onclick="page_jump()"
			href="javascript:;">确定</a>
	</div>
	<br>
	<br>
	<script>
		function page_jump() {
			if ($("#topage").val() != "") {
				window.location.href = window.location.pathname + "?" + "p="
						+ $("#topage").val();
			}
		}

		function getCookieValue(cookieName) {
			var cookieValue = document.cookie;
			var cookieStartAt = cookieValue.indexOf("" + cookieName + "=");
			if (cookieStartAt == -1) {
				cookieStartAt = cookieValue.indexOf(cookieName + "=");
			}
			if (cookieStartAt == -1) {
				cookieValue = null;
			} else {
				cookieStartAt = cookieValue.indexOf("=", cookieStartAt) + 1;
				cookieEndAt = cookieValue.indexOf(";", cookieStartAt);
				if (cookieEndAt == -1) {
					cookieEndAt = cookieValue.length;
				}
				cookieValue = unescape(cookieValue.substring(cookieStartAt,
						cookieEndAt));//解码latin-1  
			}
			return cookieValue;
		}
		//alert(getCookieValue('userCode'));
		//alert(getCookieValue('password'));
	</script>
</body>
</html>