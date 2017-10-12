$(function() {
		$('#addwindow').window('close');
		$('#updatewindow').window('close');
		$('#checkwindow').window('close');	
		$('#test').datagrid({
			title : 'User',
			iconCls : 'icon-save',
			width : 1585,
			height : 465,
			nowrap : false,             //True 数据将在一行显示			
			striped : true, 			//True 就把行条纹化
			collapsible : false, 		//True 可折叠
			url : 'user/userListData',
			sortName : 'code', 			//定义可以排序的列
			sortOrder : 'desc', 		//定义列的排序顺序，只能用 asc或 desc
			remoteSort : false, 		//定义是否从服务器给数据排序
//			idField : 'code',           //标识字段
			singleSelect : false,       //True 只能选择一行
			frozenColumns:[[
			                {field:'ck',checkbox:true},
						]],
			columns : [ [ {
				title : 'Base Information',
				colspan : 3
			}, {
				field : 'opt',
				title : 'Operation',
				width : 100,
				align : 'center',
				rowspan : 2,
				formatter : function(value, rec) {
					return '<a href="javascript:view('+rec.userId+','+rec.username+','+rec.password+')">查看1</a>'+" "+'<a href="../userController/showUser.do">查看2</a>';
				}
			} ], [ {
				field : 'userId',
				title : 'ID',
				width : 120
			}, {
				field : 'username',
				title : '姓名',
				width : 220,
//				rowspan : 2,
//				sortable : true, //True 就允许此列被排序。
//				sorter : function(a, b) {  //自定义字段的排序函数，需要两个参数：a： 第一个字段值。b： 第二个字段值。
//					return (a > b ? 1 : -1);
//				}
			}, {
				field : 'password',
				title : 'PWD',
				width : 150,
				rowspan : 2
			} ] ],
			toolbar : [ {
				id : 'btnadd',
				text : 'Add',
				iconCls : 'icon-add',
				handler : function() {
					$('#addwindow').window('open');
				}
			},'-', {
				id : 'btncut',
				text : 'Cut',
				iconCls : 'icon-cut',
				handler : function() {
					var rows = $('#test').datagrid("getSelections");
					if(rows.length == 0){
						$.messager.alert('提示框','请输入要删除的用户','warning');  
					}else{
						var ids = "";
						for(var i = 0;i<rows.length;i++){
							ids += rows[i].userId+",";
						}
						$.post("../userController/delUser.do", {"ids":ids}, function(data) {
							$.messager.alert('提示框','删除成功','info');
							$('#test').datagrid("reload");
							$("#test").datagrid("clearChecked");
						});
					}			
				}
			}, '-', {
				id : 'btnmodify',
				text : 'Modify',
				iconCls : 'icon-modify',
				handler : function() {
					var row = $('#test').datagrid("getSelected");
					$('#userId_2').val(row.userId);
					$('#username_2').val(row.username);
					$('#password_2').val(row.password);
					$('#updatewindow').window('open');
				}
			} ],
			pagination:true,  //True 就会在 datagrid的底部显示分页栏。
			rownumbers:true,  //True 就会显示行号的列。
		});	
	});
	
	function view(v1,v2,v3){
		$('#userId_3').val(v1);
		$('#username_3').val(v2);
		$('#password_3').val(v3);
		$('#checkwindow').window('open');
	} 
	
	function addUser() {
		var url = "../userController/saveUser.do";
		var param = {};
		$('#addwindow').find("input").each(function(index, obj) {
			param[obj.name] = obj.value;
		});
		$.post(url, param, function(data) {
			$.messager.alert('提示框','添加成功','info');
			$('#addwindow').window('close');
			$('#test').datagrid("reload");
		});
	}

	function updateUser() {		
		var url = "../userController/updateUser.do";
		var param = {};
		$('#updatewindow').find("input").each(function(index, obj) {
			param[obj.name] = obj.value;
		});
		$.post(url, param, function(data) {
			$.messager.alert('提示框','修改成功','info');
			$('#updatewindow').window('close');
			$("#test").datagrid("reload");
			$("#test").datagrid("clearChecked");
		});
	}
	
	function findUser() {
		var userId = $("#userId").val();
		var Url = '../userController/getUserById.do?userId='+userId;
		$("#test").datagrid({url:Url});
	}