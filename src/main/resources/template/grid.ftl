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
	            url : '<%=request.getContextPath()%>/${table}/page',
	            queryParams : {
	            	<#list parameter as comment>
	            		<#if comment == parameter?last>
	            			"${comment}" : $('#condition-${comment}').val()
	            		<#else>
	            			"${comment}" : $('#condition-${comment}').val(),
	            		</#if>
	            	</#list> 
	            },
	            pageNumber: 1
	        });
	    });
	    
	    // 新增事件
	    $("#btn-add").on('click', function(event) {
	        //弹出编辑对话框
	        var options = {
	                url: "<%=request.getContextPath()%>/${table}/redirect/edit?windowType=add",
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
	        var removeUrl = "<%=request.getContextPath()%>/${table}/remove";
	        var pageUrl = "<%=request.getContextPath()%>/${table}/page";
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
	    
	    <#if columnTypeName??>
	    	<#list columnTypeName as comment>
	    		<#if comment.dictCode=="3">
			 $('#condition-${comment.columnName}').combobox({    
	             url:"<%=request.getContextPath()%>/dict/getDict",  
	             valueField:'dictCode',    
	             textField:'dictName', 
	             queryParams : {
	               	dictType:''
	             }
	         }); 
	    		</#if>
	    	</#list> 
		</#if>	
	});
	
	// 格式化编辑按钮事件
	var formatEdit = function(val, row){
	     var html = '<a style="text-decoration:none;" href="javascript:void(0);" onclick="edit(\'' + row.id + '\')">[编辑]</a>';
	     return html
	}
	
	// 编辑事件
	var edit = function(pk) {
	    var options = {
	        url: "<%=request.getContextPath()%>/${table}/redirect/edit?windowType=modify&pk=" + pk,
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
			 	<#if columnTypeName??>
			    	<#list columnTypeName as comment>
			    		<#if comment.columnName != "createdBy"&&comment.columnName != "updatedBy"&&comment.columnName != "createdOn"&&comment.columnName != "updatedOn"&&comment.columnName != "id">
				    		<#if comment.dictCode=="1">
				    		<tr>
				    			<td><span>${comment.dictName}：</span><input id="condition-${comment.columnName}" class="easyui-textbox" style="width: 150px;"  data-options="prompt:'${comment.dictName}'"></td>
				    		</tr>
				    		<#elseif comment.dictCode=="2">
				    		<tr>
				    			<td><span>${comment.dictName}：</span><input id="condition-${comment.columnName}" class="easyui-textbox" data-options="multiline:true,prompt:'${comment.dictName}',required:false,validType:'length[1,100]'" style="width: 200px; height: 100px;"></td>
				    		</tr>
				    		<#elseif comment.dictCode=="3">
				    		<tr>
				    			<td><span>${comment.dictName}：</span><input id="condition-${comment.columnName}" class="easyui-combobox inputStyle" data-options="prompt:'${comment.dictName}', panelHeight:'auto', required:false" /></td>
				    		</tr>
				    		<#else>
				    		<tr>
				    			<td><span>${comment.dictName}：</span><input id="condition-${comment.columnName}" class="easyui-datebox inputStyle"  data-options="editable:false"/></td>
				    		</tr>
				    		</#if>
			    		</#if>
			    	</#list> 
			    <#else>
					<#list columnMap as comments>
					<#if comments.name != 'createdBy'&&comments.name != 'updatedBy'&&comments.name != 'createdOn'&&comments.name != 'updatedOn'&&comments.name != 'id'>
			        	<tr>
			            	<td><span>${comments.value}：</span><input id="condition-${comments.name}" class="easyui-textbox" style="width: 150px;"  data-options="prompt:'${comments.value}'"></td>
			        	</tr>
			        </#if>
			        </#list> 
			    </#if>
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
		                          url:'<%=request.getContextPath()%>/${table}/page',
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
		                <#list columnMap as comment>
		                	<th width=auto data-options="field:'${comment.name}',halign:'center',align:'left'">${comment.value}</th>
		                </#list> 
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