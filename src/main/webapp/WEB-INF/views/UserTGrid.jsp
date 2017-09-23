<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="width:100%;height:100%;">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>企业应用开发平台</title>
	<script type="text/javascript">
	$(function() { 
	    // 检索事件
	    $("#btn-search").on('click', function(event) {
	        $("#tableInfo").datagrid({
	            url : '<%=request.getContextPath()%>/userT/page',
	            queryParams : {
	            			"id" : $('#condition-id').val(),
	            			"name" : $('#condition-name').val(),
	            			"status" : $('#condition-status').val(),
	            			"password" : $('#condition-password').val()
	            },
	            pageNumber: 1
	        });
	    });
	    
	    // 新增事件
	    $("#btn-add").on('click', function(event) {
	        //弹出编辑对话框
	        var options = {
	                url: "<%=request.getContextPath()%>/userT/redirect/edit?windowType=add",
	                title: "新增用户",
	                width: "400",
	                height: "300",
	                dialogId: "dlg-edit",
	                zIndex : 200,
	                buttonsId: "dlg-buttons"
	        }
	        captain.openDialog(options);
	    });
	    
	    // 重置检索条件事件
	    $("#btn-reset-search").on('click', function(event) {
	        $("#condition-name").textbox("setValue", "");
	    });
	    
	    // 删除事件
	    $("#btn-remove").on('click', function(event) {
	        var removeUrl = "<%=request.getContextPath()%>/userT/remove";
	        var pageUrl = "<%=request.getContextPath()%>/userT/page";
	        var removeOptions = {
	       		tableId:"tableInfo",
	       		removeUrl:removeUrl,
			    success:function(){
			    	// 刷新表格
	                $('#tableInfo').datagrid('reload', pageUrl);
			    }
	        }
	        captain.remove(removeOptions);
	    });
	    
	});
	
	// 格式化编辑按钮事件
	var formatEdit = function(val, row){
	     var html = '<a style="text-decoration:none;" href="javascript:void(0);" onclick="edit(\'' + row.id + '\')">[编辑]</a>';
	     return html
	}
	
	// 编辑事件
	var edit = function(pk) {
	    var options = {
	        url: "<%=request.getContextPath()%>/userT/redirect/edit?windowType=modify&pk=" + pk,
	        title: "编辑用户",
	        width: "400",
	        height: "300",
	        dialogId: "dlg-edit",
	        zIndex : 200,
	        buttonsId: "dlg-buttons"
	     }
	    captain.openDialog(options);
	}
	</script>
	</head>
	<body style="width:100%;height:100%;margin:0px;">
		<div style="width:100%;height:100%;">
			 <table id="condition" class="condition" style="width:100%;">
			        	<tr>
			            	<td><span>name：</span><input id="condition-name" class="easyui-textbox" style="width: 150px;"  data-options="prompt:'name'"></td>
			        	</tr>
			        	<tr>
			            	<td><span>status：</span><input id="condition-status" class="easyui-textbox" style="width: 150px;"  data-options="prompt:'status'"></td>
			        	</tr>
			        	<tr>
			            	<td><span>password：</span><input id="condition-password" class="easyui-textbox" style="width: 150px;"  data-options="prompt:'password'"></td>
			        	</tr>
		        <tr>
		             <td colspan="3">
		                <a id="btn-search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'"><span class="buttonText">检索</span></a>
		                <a id="btn-add" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-insert'"><span class="buttonText">新增</span></a>
		                <a id="btn-remove" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'"><span class="buttonText">删除</span></a>
		                <a id="btn-reset-search" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'"><span class="buttonText">重置</span></a>
		             </td>
		        </tr>
	    	</table>
		    <table id="tableInfo" class="easyui-datagrid" data-options="
		                          fit:true,
		                          url:'<%=request.getContextPath()%>/userT/page',
		                          collapsible:true,
		                          rownumbers:true,
		                          fitColumns:true,
		                          method:'get',
		                          pagination:true,
		                          striped: true,
		                          pageSize: 20,
		                          toolbar:'#condition',
                          		  onLoadSuccess:captain.paginationConfig,
                         		  onLoadError:captain.loadError" title="" iconCls="icon-save">
		        <thead>
		            <tr>
		                <th  data-options="field:'checkbox',halign:'center',align:'left',checkbox:'true'">选择</th>
		                	<th width=auto data-options="field:'id',halign:'center',align:'left'">id</th>
		                	<th width=auto data-options="field:'name',halign:'center',align:'left'">name</th>
		                	<th width=auto data-options="field:'status',halign:'center',align:'left'">status</th>
		                	<th width=auto data-options="field:'password',halign:'center',align:'left'">password</th>
		                <th  data-options="field:'edit',halign:'center',align:'center',formatter:formatEdit">编辑</th>
		            </tr>
		        </thead>
		    </table>
		</div>
		<div id="dlg-buttons" class="dialog-button" data-options="region:'bottom', border:false" style="display:none;">
		    <a id="btn-save" href="#" class="easyui-linkbutton"  data-options="iconCls:'icon-save'"><span class="buttonText">保存</span></a>
		    <a id="btn-cancel" href="#" class="easyui-linkbutton"  data-options="iconCls:'icon-cancel'"><span class="buttonText">取消</span></a>
		    <a id="btn-reset" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" ><span class="buttonText">重置</span></a>
		</div>
	</body>
</html>