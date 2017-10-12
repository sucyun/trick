<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>首页</title>
<link rel="stylesheet" type="text/css" href="public/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="public/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="public/easyui/themes/demo.css">
<script type="text/javascript" src="public/easyui/jquery.min.js"></script>
<script type="text/javascript" src="public/easyui/jquery.easyui.min.js"></script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:'true',border:1" style="width: 90%; height: 100%;">
<!-- 		<div region="north" split="true" title="top" style="height: 100px;border-style: none;">top</div> -->
	    <div data-options="region:'center',border:0" style="overflow: hidden;padding: 2px;">
        	<table id="datalist"></table>
        	<div id="page" style="background:#efefef;border:1px solid #ccc;"></div>
    	</div>
    	<div id="dialog" style="display: none;">
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	$('#datalist').datagrid({
		striped:true,//单双行
		fitColumns:true,//宽度自适应
		title : "参数项列表",//标题
		loadMsg:"正在加载数据，请稍等...",//加载友好提示
		url : 'user/userListData',
// 		queryParams:{currentPage:1},//请求参数
		checkOnSelect:true,
		singleSelect:false,//是否单选
		rownumbers:true,//行号
		columns:[[
			{ field: 'ck', checkbox:true,
				styler: function(value,row,index){
					return 'border-width: 0 1px 1px 0; border-style: dotted; border-color:#000;  border-bottom-color:#ccc; ';
				}
			},
			{ title: '参数id', field: 'id', width : 183 ,editor:{type:'text'}},
			{ title: '姓名', field: 'user_name', width : 183 ,editor:{type:'text'}},
			{ title: '密码', field: 'password', width : 183 ,editor:{type:'text'}},
			{ title: '年龄', field: 'age', width : 183 }
		]],
		onClickCell: function(index,field,value){
			onClickCell(index, field);
// 			$(this).datagrid('beginEdit', index);
// 			var ed = $(this).datagrid('getEditor', {index:index,field:field});
// 			$(ed.target).focus();
		},
		toolbar : [{
			text : '添加',
			iconCls : 'icon-add',
			handler : function() {
				openDialog("add_dialog", "新增");
			}
		}, '-', {
			text : '修改',
			iconCls : 'icon-edit',
			handler : function() {
				var vl = $('#datalist').datagrid("getChecked");
				if(vl!=""){
					openDialog("edit_dialog", "修改", vl[0].id);
				}else{
					alert("请选择一项，进行修改");
					return false;
				}
			}
		}, '-', {
			text : '删除',
			iconCls : 'icon-remove',
			handler : function() {
				var data = $('#datalist').datagrid("getChecked");
				$.messager.confirm({
	                title: '警告',  
	                msg: '确定删除'+data[0].userName+'吗?',
	                timeout: 5000,  
	                showType: 'show',
	                fn:function(r){
	                	if(r){
		                	$.ajax({
		                		url:'user/delectUserById',
		                		data:{id:data[0].id},
		                		success:function(){
		                			$('#tt').datagrid('reload');
		                			alert("删除成功");
		                		}
		                	})
	                	}
	                }
	            });
			}
		}]
	});
	//分页组件
	$('#page').pagination({
		pageSize : 5,//每页显示10条
		pageNumber : 1,//第一页开始,当前所在页数
		total:'${total}',
		pageList : [5,10],
		beforePageText : '第',//页数文本框前显示的汉字 
		afterPageText : '页    共 {pages} 页',
		displayMsg : '当前显示 {from} - {to} 条记录   共 ${total} 条记录',
		onSelectPage: function(pageNumber, pageSize){
		    $('#datalist').datagrid('reload', 'user/userListData?currentPage='+pageNumber+'&pagesize='+pageSize);
		}
	});
	
	
	//打开对话框  
	function openDialog(type,title,id){
		var url="";
		if(type=="add_dialog"){
			url="user/user_add.jsp";
		}else if(type=="edit_dialog"){
			url = "user/selectUserById?id="+id;
		}
		$('#dialog').dialog({
		    title: title,
		    width: 680,
		    height: 500,
		    closed: false,
		    cache: false,
		    href: url,
		    modal: true,
		    resizable : true
		});
	}
	//关闭对话框  
	function closeDialog(){
	    $('#dialog').dialog('close');
	}
	//保存方法
	function submitForm(type,id){
		$('#form').form('submit',{
			url: "user/saveUser",
			onSubmit: function(param){
				param.id=id;
				param._type = type;
				var isValid = $(this).form('validate');
				if (!isValid){
					$.messager.progress('close');	// hide progress bar while the form is invalid
				}
				return isValid;	// return false will stop the form submission
			},
			success: function(){
				$('#datalist').datagrid('reload');
				$('#dialog').dialog('close');
			}
		});
	}
})
var editIndex = undefined;
	function endEditing(){
		if (editIndex == undefined){return true}
		if ($('#data_fieldConf').datagrid('validateRow', editIndex)){
			$('#data_fieldConf').datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickCell(index, field){
		if (editIndex != index){
			if (endEditing()){
				$('#datalist').datagrid('selectRow', index)
						.datagrid('beginEdit', index);
				var ed = $('#datalist').datagrid('getEditor', {index:index,field:field});
				if (ed){
					($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
				}
				editIndex = index;
			} else {
				setTimeout(function(){
					$('#datalist').datagrid('selectRow', editIndex);
				},0);
			}
		}
	}
	function onEndEdit(index, row){
		var ed = $(this).datagrid('getEditor', {
			index: index,
			field: 'user_name'
		});
		row.productname = $(ed.target).combobox('getText');
	}
</script> 
</html>
