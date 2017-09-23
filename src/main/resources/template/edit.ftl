<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
var windowType = "<%=request.getParameter("windowType")%>";
var pk = "<%=request.getParameter("pk")%>";
$(function() {
	
    if(windowType == "modify"){
        // 初始化编辑页面
        var url = '<%=request.getContextPath()%>/${table}/get?id=' + pk;
        $('#formInfo').form({
        	onLoadSuccess:function(data){
        	<#if columnTypeName??>
	    	<#list columnTypeName as comment>
	    		<#if comment.dictCode=="3">
                $('#${comment.columnName}').combobox({    
                    url:"<%=request.getContextPath()%>/dict/getDict",  
                    valueField:'dictCode',    
                    textField:'dictName', 
                    queryParams : {
                       	dictType:''
                    },
                });
                $('#${comment.columnName}').combobox('setValues',data.${comment.columnName});
                </#if>
	    	</#list> 
			</#if>	
        	}
        }).form('load', url);
    }
    else if(windowType == "add"){
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
    }
    
    //表单提交标识，防止二次提交
    var submitFlag = true;
    // 保存事件
    //先解除绑定再绑定，是为了防止多次点击事件累加
    $("#btn-save").off().on("click",function(){
        if(submitFlag){
            //禁止在此提交
            submitFlag = false;
            var url = "";
            if(windowType == "add"){
                url = "<%=request.getContextPath()%>/${table}/add";
            }else if(windowType == "modify"){
                url = "<%=request.getContextPath()%>/${table}/edit";
            }
            $('#formInfo').form('submit',{
                url:url,
                onSubmit:function(){
                    var isValid = $(this).form('validate');
                    if (!isValid){
                        submitFlag = true;
                    }else{
                        //提示进度条
                        $.messager.progress();
                    }
                    return isValid;    // 返回false终止表单提交
                },
               success:function(data){
                	// 如果提交成功则隐藏进度条
                    $.messager.progress('close');    
                    //转换为json对象
                    var data = eval('(' + data + ')'); 
                	// 弹出操作结果
                    captain.showMessage(data);
                    if(data.error == ''){
                    	 $('#tableInfo').datagrid('reload'); 
                         $("#dlg-edit").dialog("close");
                    }else{
                    	submitFlag = true;
                    }
                } 
            });
        }
    });
    
    // 取消事件
    $("#btn-cancel").off().on("click",function(){
         $("#dlg-edit").dialog("close");
    });
    
    // 重置事件
    $("#btn-reset").off().on("click",function(){
        if(windowType == "add"){
            $('#formInfo').form("clear");
        }else if(windowType == "modify"){
            // 初始化编辑页面
            var url = '<%=request.getContextPath()%>/${table}/get?id=' + pk;
             $('#formInfo').form('load', url);
        }
    });
});
</script>
<form id="formInfo" class="formEdit" enctype="multipart/form-data" method="post">
<#if columnTypeName??>
	<#list columnTypeName as comment>
	<#if comment.columnName == "id">
	<input id="id" name="id"  type="hidden">
	<div align="center" style="margin-top:20px;">  	
	<#elseif comment.columnName != "createdBy"&&comment.columnName != "updatedBy"&&comment.columnName != "createdOn">
		<#if comment.dictCode=="1">
		<div class="fitem">
			<label class="alignRight">${comment.dictName}：</label>
			<input id="${comment.columnName}" name="${comment.columnName}" class="easyui-textbox inputClass" data-options="required:true,validType:'length[1,32]'"/>
		</div>
		<#elseif comment.dictCode=="2">
		<div class="fitem">
			<label class="alignRight">${comment.dictName}:</label>
			<input id="${comment.columnName}" name="${comment.columnName}" class="easyui-textbox" data-options="multiline:true,prompt:'${comment.dictName}',required:false,validType:'length[1,100]'" style="width: 200px; height: 100px;">
		</div>
		<#elseif comment.dictCode=="3">
		<div class="fitem">
			<label class="alignRight">${comment.dictName}:</label>
            <input id="${comment.columnName}" name="${comment.columnName}" class="easyui-combobox inputStyle" data-options="prompt:'${comment.dictName}', panelHeight:'auto', editable:false" /> 
        </div>
		<#else>
		<div class="fitem">
            <label class="alignRight">${comment.dictName}:</label>
            <input id="${comment.columnName}" name="${comment.columnName}" class="easyui-datebox inputStyle"  data-options="editable:false"/>
        </div>
		</#if>
	<#else>
		
	</#if>
	</#list> 
    <#else>
 	<#list columnMap as comment>
    <#if comment.name == 'id'>   
	<input id="id" name="id"  type="hidden">
	<div align="center" style="margin-top:20px;">  
	<#else> 
		<#if comment.name!="createdOn"&&comment.name!="createdBy"&&comment.name!="updatedBy">
		<div class="fitem">
       		<label class="alignRight">${comment.value}：</label>
          	<input id="${comment.name}" name="${comment.name}" class="easyui-textbox inputClass" data-options="required:true,validType:'length[1,32]'"/>
		</div> 
		</#if>  
     </#if>  
    </#list>
   </#if>
    </div>
</form>
