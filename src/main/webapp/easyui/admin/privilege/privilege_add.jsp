<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>??</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" type="text/css" href="public/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="public/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="public/easyui/themes/demo.css">
<script type="text/javascript" src="public/easyui/jquery.min.js"></script>
<script type="text/javascript" src="public/easyui/jquery.easyui.min.js"></script>
<style type="text/css">
.table table[name="table"] {
	border-collapse: collapse;
	border-spacing: 0;
	border-left: 1px solid #888;
	border-top: 1px solid #888;
	background: #efefef;
	padding: 20px;
}

.table table[name="table"] td {
	border-right: 1px solid #0f0f0f;
	border-bottom: 1px solid #0f0f0f;
	padding: 5px 15px;
}
</style>
</head>
<body>
	<div class="easyui-panel" data-options="border:0" style="width: 100%; height: 100%;overflow: hidden">
	    <form style="text-align:center;" id="form" method="post" action="admin/savePrivilege">
	    	<input type="hidden" name="priid" value='<c:if test="${not empty privilege.id && privilege.id!=''}">${privilege.id}</c:if>'>
	    	<input type="hidden" id="type" name="type" value='<c:if test="${not empty type && type!=''}">${type}</c:if>'>
	    	<table cellpadding="12" name="table">
	    		<tr>
	    			<td><span>权限名称:</span></td>
	    			<td><input class="easyui-textbox" type="text" id="name" name="name" value="<c:if test="${not empty privilege.name && privilege.name!=''}">${privilege.name}</c:if>" data-options="required:true,missingMessage:'必填'"></input></td>
	    		</tr>
	    		<tr>
	    			<td><span>URL:</span></td>
					<td><input class="easyui-textbox" type="text" name="url" value="<c:if test="${not empty privilege.url && privilege.url!=''}">${privilege.url}</c:if>" data-options="required:true,missingMessage:'必填'"></input></td>
				</tr>
				<tr>
	    			<td><span>解释描述:</span></td>
					<td><input class="easyui-textbox" type="text" name="explan" value="<c:if test="${not empty privilege.explan && privilege.explan!=''}">${privilege.explan}</c:if>" data-options="required:true,missingMessage:'必填'"></input></td>
				</tr>
				<tr>
					<td><span>选择父级模块:</span></td>
					<td>
	    				<select id="priParent" style="width:175px;" name="priParent">
	    				</select>
	    			</td>
				</tr>
	    	</table>
	    </form>
	    <div style="text-align:center;padding:5px">
	    	<a id="save" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
			<a id="cancle" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancle'" onclick="$('#dialog').dialog('close')">取消</a>
	    </div>
	</div>
<script>
$(function(){
	$("#priParent").combogrid({
		url:'admin/selectPriListForRole',
		panelWidth:450,
		value:'${privilege.parentId}',
		idField:'id',
		textField:'name',
		columns:[[
		    {field:'_parentId',title:'_parentId',width:60},
		    {field:'name',title:'Name',width:100},
		    {field:'pname',title:'pname',width:120},
		    {field:'explan',title:'explan',width:100}
		]]
	});
})
$('#save').click(function(){
// 	var g = $('#priParent').combogrid('grid');//获取组合网格的对象
// 	var r = g.datagrid('getSelected');//获取组合网格选择的行
	$.ajax({
		type:"post",
		url:"admin/savePrivilege",
		dataType:"json",
		data : $('#form').serialize(),
		success:function(data){
			alert("保存成功");
			$('#dialog').dialog('close')
			$('#datalist').datagrid('reload');
		},
		error:function(){
			alert("网络连接异常");
		}
	})
})
</script>
</body>
</html>