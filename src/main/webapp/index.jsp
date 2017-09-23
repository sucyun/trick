<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Hello</title>
<script src="/statics/js/jquery-3.2.1.min.js"></script>
</head>
<body>
	<div>
		<h2>Hello World!</h2>
		<img alt="" src="/statics/images/example/square01_300.300.jpg">
	</div>
</body>
</html>