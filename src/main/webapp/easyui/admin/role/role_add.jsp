<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>新增角色</title>
</head>
<body>
	<form action="" method="post">
		<table cellpadding="5">
    		<tr>
    			<td><span>权限名称:</span></td>
    			<td><input class="easyui-textbox" type="text" name="name" data-options="required:true"></input></td>
    		</tr>
    		<tr>
    			<td><span>URL:</span></td>
				<td><input class="easyui-textbox" type="text" name="name" data-options="required:true"></input></td>
			</tr>
			<tr>
				<td><span>选择父级模块:</span></td>
				<td>
    				<select id="privilege" style="width:175px;" name="privilege">
    				</select>
    			</td>
			</tr>
			<tr>
				<td>
					<a id="" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
					<a id="cancle" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancle'">取消</a>
				</td>
			</tr>
    	</table>
	</form>
<script>
	$(function(){
		$("#privilege").combogrid({
			url:'admin/selectPriListForRole',
			panelWidth:450,
			value:'006',
			idField:'code',
			textField:'name',
			columns:[[
			    {field:'_parentId',title:'_parentId',width:60},
			    {field:'name',title:'Name',width:100},
			    {field:'pname',title:'pname',width:120},
			    {field:'explan',title:'explan',width:100}
			]]
		});
	})
	function submitForm(){
		$('#save').click(function(){
			$.ajax({
				type:"post",
				url:"admin/updateOrInsertRole",
				dataType:"json",
				success:function(data){
					alert(data)
				},
				error:function(){
					alert("网络连接异常");
				}
			})
		})
	}
	function clearForm(){
		$('#cancle').form('clear');
	}
</script>
</body>
</html>