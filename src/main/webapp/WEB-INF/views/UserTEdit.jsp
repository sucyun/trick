<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
var windowType = "<%=request.getParameter("windowType")%>";
var pk = "<%=request.getParameter("pk")%>";
$(function() {
	
    if(windowType == "modify"){
        // 初始化编辑页面
        var url = '<%=request.getContextPath()%>/userT/get?id=' + pk;
        $('#formInfo').form({
        	onLoadSuccess:function(data){
        	}
        }).form('load', url);
    }
    else if(windowType == "add"){
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
                url = "<%=request.getContextPath()%>/userT/add";
            }else if(windowType == "modify"){
                url = "<%=request.getContextPath()%>/userT/edit";
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
            var url = '<%=request.getContextPath()%>/userT/get?id=' + pk;
             $('#formInfo').form('load', url);
        }
    });
});
</script>
<form id="formInfo" class="formEdit" enctype="multipart/form-data" method="post">
	<input id="id" name="id"  type="hidden">
	<div align="center" style="margin-top:20px;">  
		<div class="fitem">
       		<label class="alignRight">name：</label>
          	<input id="name" name="name" class="easyui-textbox inputClass" data-options="required:true,validType:'length[1,32]'"/>
		</div> 
		<div class="fitem">
       		<label class="alignRight">status：</label>
          	<input id="status" name="status" class="easyui-textbox inputClass" data-options="required:true,validType:'length[1,32]'"/>
		</div> 
		<div class="fitem">
       		<label class="alignRight">password：</label>
          	<input id="password" name="password" class="easyui-textbox inputClass" data-options="required:true,validType:'length[1,32]'"/>
		</div> 
    </div>
</form>
