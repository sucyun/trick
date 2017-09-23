<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>??</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>
	<div>
		<ul>
			<li>主键:<font color="red">${user.id}</font><br /> 姓名:<font
				color="red">${user.userName}</font><br /> 密码:<font color="red">${user.password}</font><br />
				年龄:<font color="red">${user.age}</font><br /> 时间:<font color="red">${date}</font><br />
<%-- 				布尔:<font color="red">${boolean?string('yes','no')}</font><br /> --%>
			</li>
		</ul>
	</div>
</body>
</html>