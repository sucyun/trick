<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<script type="text/javascript" src="public/easyui.validatebox.js"></script>
<script type="text/javascript" src="public/js/datagrid-groupview.js"></script>
<script type="text/javascript" src="public/layer/layer.js"></script>
<script type="text/javascript">
function roleAdd(){
	$("#roleid").val("");
	$("#roleAttr").propertygrid({
		url:"admin/role/property_roleAdd.json"
	});
	$("#panel").panel({
	});
}
function rolRemove(){
	var checkrole = $('#tree').tree('getChecked','checked');
	var data = {roleid:checkrole};
	if(checkole.length!=1){
		layer.msg("每次只能删除一条数据",function(){});
		return;
	}{
		$.post("admin/removeAdmin.shtml",data,function(result){
			console.log(checkrole);
			layer.msg("每次只能删除一条数据");
		})
	}
}

function roleMessage(){
	var checkrole = $("#tree").tree('getChecked');
	if(checkrole.length>1||!checkrole[0]){
		layer.msg("请选择一个角色进行查看",function(){});
	}else{
		$("#panel").panel({
		});
		$.ajax({
			type:"post",
			url:"admin/selectRoleById.shtml",
			data:{id:checkrole[0].id},
			dataType:"json",
			success:function(data){
				var rowmsg = [];
				$.each(data, function(name,value){
					if(name=="id"){
						$("#roleid").val(value);
					}
					var jsonData='{"name": "' + name + '", "value": "' + value + '","editor": "text"}';
                    rowmsg.push(jsonData);
                });
				var row = "["+rowmsg+"]";
				$("#roleAttr").propertygrid('loadData',JSON.parse(row));
				$('#privilegesForRole').treegrid({
				    url:'admin/selectPriListForRole.shtml?isHave=have&roleid='+checkrole[0].id,
				    title : "权限",//标题
				    idField:'id',
				    treeField:'name',
				    rownumbers: true,
				    fitColumns: true,
				    singleSelect:false,//是否单选
				    toolbar : [{
						text : '删除',
						iconCls : 'icon-remove',
						handler : function() {
							var privilegesSel = $('#privilegesForRole').datagrid("getChecked");
							if(!privilegesSel[0]){
								layer.msg("请选择一条数据");
								return false;
							}
							$.messager.confirm({
				                title: '警告',  
				                msg: '确定删除'+privilegesSel[0].name+'吗?',
				                timeout: 5000,  
				                showType: 'show',
				                fn:function(r){
				                	if(r){
					                	$.ajax({
					                		url:'admin/removePriFromRole.shtml',
					                		data:{
					                			privilegesList:JSON.stringify(privilegesSel),//选择的权限数据
					                			roleid:checkrole[0].id//选择的角色id
					                		},
					                		success:function(d){
					                			$('#privilegesForRole').treegrid('reload');
					                			$('#datalist').datagrid('reload');
					                			layer.msg('删除成功!');
					                		}
					                	})
				                	}
				                }
				            });
						}
					}],
				    columns:[[
						{ field: 'ck', checkbox:true,
							styler: function(value,row,index){
								return 'border-width: 0 1px 1px 0; border-style: dotted; border-color:#000;  border-bottom-color:#ccc; ';
							}
						},
						{title:'权限名称',field:'name',width:180},
						{title:'编号',field:'code',width:60},
						{title:'权限url',field:'url',width:80},
						{title:'权限描述',field:'explan',width:80}
				    ]]
				});
				$('#datalist').datagrid("reload","admin/selectPriListForRole.shtml?isHave=nothave&roleid="+checkrole[0].id);
			},
			error:function(){
				layer.msg("网络连接异常");
			}
		});
	}
}
//分配权限
function distributionPri(){
	
}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:'true',border:1" style="width: 90%; height: 100%;padding: 1px">
		<div data-options="region:'west',split:true,title:'角色列表'" style="width:270px;height: 100px;border-style: none;">
			<div style="padding:5px;background:#fafafa;width:100%;border:1px solid #ccc">
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add"  onclick="roleAdd()">添加</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" onclick="$('#tree').tree('reload')">刷新</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" onclick="rolRemove()">删除</a>
			</div>
			<ul id="tree"></ul>
		</div>
	    <div data-options="region:'center',border:1,title:'角色属性信息'" style="overflow: hidden;padding: 1px;">
			<div id="panel" title="基本信息" style="width:100%;height:auto;padding:2px;background:#ffffff;display: none;"
    				data-options="iconCls:'icon-save',closable:false,collapsible:true,maximizable:false,minimizable:false">
				<div class="easyui-layout" data-options="fit:'true',border:1" style="width: 90%; min-height: 220px;max-height: 300px;height: auto">
					<div data-options="region:'center',border:'false',fit:'true'" style="width: 50%;overflow: auto; overflow-x:hidden;height: 100%;">
						<table id="roleAttr" style="margin: 2px;height: 100%;width: 50%" border="false" >
						</table>
					</div>
					<div data-options="region:'east',border:'false'" class="p-right" style="width: 50%;overflow: auto;padding-left: 1px;border-top: 0px;border-right: 0px;border-bottom: 0px;">
						<table id="privilegesForRole" class="easyui-treegrid" style="width:auto;height:100%"></table>
					</div>
				</div>
			</div>
        	<table data-options="border:1" id="datalist"></table>
        	<div id="page" style="background:#efefef;border:1px solid #ccc;"></div>
    	</div>
    	<div id="dialog" style="display: none;">
		</div>
		<input id="roleid" type="hidden" value="">
	</div>
</body>
<script type="text/javascript">
$(function(){
	$('#tree').tree({
	    url:'admin/selectRoleList.shtml',
	    checkbox:true,
	    onClick:function(node){
	    	var ch = $('#tree').tree('getChecked','checked');
	    	$.each(ch,function(cur){
	    		if(ch[cur])
		    		$('#tree').tree("uncheck",ch[cur].target);
	    	})
	    	$('#tree').tree("check",$('#tree').tree("getSelected").target);
	    	roleMessage()
	    }
	});
	$('#datalist').datagrid({
		striped:true,//单双行
		fitColumns:true,//宽度自适应
		title : "权限列表",//标题
		loadMsg:"正在加载数据，请稍等...",//加载友好提示
		url : 'admin/selectPriListForRole.shtml',
		checkOnSelect:true,
		singleSelect:false,//是否单选
		rownumbers:true,//行号
		view:groupview,//声明分组
		idField:'id',
		groupField:'pname',//分组字段
		groupFormatter:function(value,rows){
			return typeof(value) == "undefined"?"顶级父模块":value;
        },
		columns:[[
			{ field: 'ck', checkbox:true,
				styler: function(value,row,index){
					return 'border-width: 0 1px 1px 0; border-style: dotted; border-color:#000;  border-bottom-color:#ccc; ';
				}
			},
// 			{ title: '参数id', field: 'id',width:100},
			{ title: '父级模块', field: 'pname',width:100},
			{ title: '名称', field: 'name',width:100},
			{ title: '编码', field: 'code',width:100},
			{ title: '请求路径', field: 'url',width:100},
			{ title: '创建时间', field: 'createtime',width:100},
			{ title: '创建人', field: 'createby',width:100},
			{ title: '状态', field: 'status',width:100}
		]],
		toolbar : [{
			text : '分配',
			iconCls : 'icon-add',
			handler : function() {
				var checkrole = $("#tree").tree('getChecked');
				var vl = $('#datalist').datagrid("getChecked");
				if(checkrole.length>1||!checkrole[0]){
					layer.msg("请选择一个角色进行查看",function(){});
				}else if(vl.length<1||!vl[0]){
					layer.msg("请选择需要分配的权限",function(){});
				}else{
					var priids = [];
					$.each(vl,function(i){
						var num = $('#datalist').datagrid("getRowIndex",vl[i]);
						$('#datalist').datagrid("deleteRow",num);
						priids.push(vl[i].id);
					})
					$.ajax({
	               		url:'admin/addPriFroRole.shtml',
	               		data:{
	               			priIds:priids.toString(),//选择的权限数据
	               			roleid:checkrole[0].id
	               		},
	               		success:function(data){
	               			$('#privilegesForRole').treegrid('reload');
	               			layer.msg("权限分配成功");
	               		}
	               	})
				}
			}
		}, '-', {
			text : '修改',
			iconCls : 'icon-edit',
			handler : function() {
				var vl = $('#datalist').datagrid("getChecked");
				if(vl!=""){
					openDialog("edit_dialog", "修改", vl[0].id);
				}else{
					layer.msg("请选择一项，进行修改",function(){});
					return false;
				}
			}
		}, '-', {
			text : '新增权限',
			iconCls : 'icon-edit',
			handler : function() {
				openDialog("add_dialog", "权限新增");
			}
		}],
		onLoadSuccess:function(){//数据加载成功并且已经加载完成
			var data=$('#datalist').datagrid('getData');
			//分页组件
			$('#page').pagination({
				pageSize : 10,//每页显示10条
				pageNumber : 1,//第一页开始,当前所在页数
				total:data.total,
				pageList : [10],
				beforePageText : '第',//页数文本框前显示的汉字 
				afterPageText : '页    共 {pages} 页',
				displayMsg : '当前显示 {from} - {to} 条记录   共 '+data.total+'条记录',
				onSelectPage: function(pageNumber, pageSize){
				    $('#datalist').datagrid('reload', 'admin/selectPriListForRole.shtml?currentPage='+pageNumber+'&pagesize='+pageSize);
				}
			});
		},
		onCheck:function(rowIndex,rowData){
			if(!rowData._parentId)
				return;
			var this_parent_index = $('#datalist').datagrid('getRowIndex',rowData._parentId);
			var parent_row = $('#datalist').datagrid("checkRow",this_parent_index);
			
// 			console.log(check)
// 			console.log(rowData)
		},
		onUncheck:function(rowIndex,rowData){
			var current_rows = $('#datalist').datagrid("getChecked");
			$('#datalist').datagrid("uncheckAll");
			console.log(current_rows.length)
			$.each(current_rows,function(i,row){
				console.log(row)
				if(row._parentId&&row._parentId!=rowData.id)
					$('#datalist').datagrid("checkRow",$('#datalist').datagrid('getRowIndex',row.id));
			})
		}
	});
	$('#roleAttr').propertygrid({
        height: 'auto',
        showGroup: false,
        scrollbarSize: 0,
        columns: [[
			{ field: 'name', title: 'Name', width: 100, resizable: true },
			{ field: 'value', title: 'Value', width: 100, resizable: true }
        ]],
        toolbar : [{
			text : '保存信息',
			iconCls : 'icon-save',
			handler : function() {
				if(save())
					$('#tree').tree('reload')
			}
		},{
			text : '新增角色',
			iconCls : 'icon-add',
			handler : function() {
				roleAdd();
// 				openDialog("add_dialog", "新增");
			}
		}]
    });
	//打开对话框  
	function openDialog(type,title,id){
		var url="";
		if(type=="add_dialog"){
			url="admin/privilege/privilege_add.jsp";
		}else if(type=="edit_dialog"){
			url = "admin/selectPrivilegeById.shtml?id="+id;
		}
		$('#dialog').dialog({
		    title: title,
		    width: 680,
		    height: 400,
		    closed: false,
		    cache: false,
		    href: url,
		    modal: true,
		    resizable : true
		});
	}
	function save(){
		var role = $("#roleAttr").propertygrid("getChanges");
		if(role==null||role.length<1){
			layer.msg("没有更改的内容");
			return false;
		}else{
			layer.load();
			var roleid = $("#roleid").val();
			var param = "";
			//roleid不为空，则为修改
			if(roleid!=""){
				param +=',"id":'+roleid+'';
			}
			for(var i=0;i<role.length;i++){
				param +=',"'+role[i].name+'": "' + role[i].value + '"';
			}
			$.ajax({
				type:"post",
				url:"admin/updateOrInsertRole.shtml",
				data:{"param":"{"+param.substring(1)+"}"},
				dataType:"json",
				success:function(data){
					layer.closeAll('loading');
					if(data==1){
						layer.msg("保存成功!")
					}
					return true;
				},
				error:function(){
					layer.closeAll('loading');
					layer.msg("网络连接异常",{icon: 5});
				}
			})
		}
	}
	//关闭对话框  
	function closeDialog(){
	    $('#dialog').dialog('close');
	}
	function sleep(numberMillis) {
		var now = new Date();
		var exitTime = now.getTime()+numberMillis;
		while(true){
			now = new Date();
		if(now.getTime()>exitTime)
			return; 
		}
	}
	//保存方法
// 	function getParentRows(parent_row){
// 		if(parent_row!=-1)
// 			$('#datalist').datagrid("checkRow",parent_row);
// 		else
// 			getParentRows(parent_row)
// 	}
})
</script> 
</html>
