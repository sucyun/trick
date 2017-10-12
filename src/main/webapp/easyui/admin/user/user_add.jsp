<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
<title>首页</title>
<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="public/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="public/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="public/easyui/themes/demo.css">
<script type="text/javascript" src="public/easyui/jquery.min.js"></script>
<script type="text/javascript" src="public/easyui/jquery.easyui.min.js"></script>
</head>
<body>
	<div class="easyui-panel" data-options="border:0" style="width: 100%; height: 100%;overflow: hidden">
		<div style="padding: 10px 60px 20px 60px">
			<form id="form" method="post">
				<table cellpadding="5">
					<tr >
						<td>用户名:</td>
						<td><input class="easyui-textbox" type="text" name="username" value="${user.userName==''?'':user.userName}"
							data-options="required:true,missingMessage:'用户名不能为空'"></input></td>
					</tr>
					<tr>
						<td>密码:</td>
						<td><input class="easyui-textbox" type="text" name="pass" value="${user.password==''?'':user.password}"
							data-options="required:true,missingMessage:'密码不能为空'"></input></td>
					</tr>
					<tr>
						<td>年龄:</td>
						<td><input class="easyui-textbox" type="text" name="age" value="${user.age==''?'':user.age}"
							data-options="multiline:true,missingMessage:'年龄不能为空'" style="height: 60px"></input></td>
					</tr>
					<tr>
						<td>爱好:</td>
						<td>
							<select class="easyui-combobox" name="hobby">
								<option value="lq">篮球</option>
								<option value="zq">足球</option>
								<option value="ymq">羽毛球</option>
								<option value="ppq">乒乓球</option>
							</select>
						</td>
					</tr>
				</table>
			</form>
			<div style="text-align: center; padding: 5px">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm('${empty type?'':type}','${user.id}')">保存</a> 
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeDialog()">取消</a>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	
</script>
</html>
