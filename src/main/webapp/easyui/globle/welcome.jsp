<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>test</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="description" content="主页">

<link rel="stylesheet" type="text/css" href="public/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="public/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="public/easyui/themes/demo.css">
<script type="text/javascript" src="public/easyui/jquery.min.js"></script>
<script type="text/javascript" src="public/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="globle/demo.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" style="height:60px;background:#B3DFDA;padding:10px;">欢迎使用regular后台管理系统</div>
	<div data-options="region:'west',split:true,title:'导航菜单',animate: false" style="width:290px;padding:0px;overflow-y:auto;overflow-x:hidden;">
		<div id="accordion" class="easyui-accordion" data-options="border:0,animate:true,multiple:true" style="width:100%;height:auto;margin: 0px;">
<!-- 			<div title="About" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:2px;overflow: hidden;"> -->
<!-- 				<ul id="tree" style="height: 10px;"></ul> -->
<!-- 			</div> -->
		</div>
	</div>
	<div data-options="region:'south',border:false" style="height:50px;background:#A9FACD;padding:10px;">south region</div>
	<div data-options="region:'center'" style="padding:0 0 0 0px;background:#eee;overflow: hidden;" >
		<div id="tabs" class="easyui-tabs" data-options="region:'center',border:0" style="padding: 0px;overflow: hidden;">
			<div title="欢迎使用" style="text-align: center;background-color: #ffffff;">
				<div style="height: 100%;width: 100%;background:url(public/images/home_welcome2.jpg);background-size:cover"></div>
			</div>
		</div>
	</div>
	<div id="menu" class="easyui-menu" style="width: 150px;">
		<div id="m-refresh">刷新</div>
		<div class="menu-sep"></div>
		<div id="m-closeall">全部关闭</div>
		<div id="m-closeother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="m-close">关闭</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	initMenus();
	$(".tabs").bind('contextmenu',function(e){
		e.preventDefault();
		$('#menu').menu('show', {
			left: e.pageX,
			top: e.pageY
		});
	});
	//刷新
	$("#m-refresh").click(function(){
	    var currTab = $('#tabs').tabs('getSelected');    //获取选中的标签项
	    var url = $(currTab.panel('options').content).attr('src');    //获取该选项卡中内容标签（iframe）的 src 属性
	    /* 重新设置该标签 */
	    $('#tabs').tabs('update',{
	        tab:currTab,
	        options:{
	        	content : '<iframe scrolling="yes" frameborder="0"  src="user/userList" style="width:100%;height:100%;"></iframe>',
	        }
	    })
	});
	//关闭所有
	$("#m-closeall").click(function(){
	    $(".tabs li").each(function(i, n){
	        var title = $(n).text();
	        if("欢迎使用"!=title){
	            $('#tabs').tabs('close',title);    
	        }
	    });
	});
	//除当前之外关闭所有
	$("#m-closeother").click(function(){
	    var currTab = $('#tabs').tabs('getSelected');
	    currTitle = currTab.panel('options').title;    
	    $(".tabs li").each(function(i, n){
	        var title = $(n).text();
	        if(currTitle != title&&"欢迎使用"!=title){
	        	$('#tabs').tabs('close',title);
	        }
	    });
	});
	//关闭当前
	$("#m-close").click(function(){
	    var currTab = $('#tabs').tabs('getSelected');
	    currTitle = currTab.panel('options').title;    
	    $('#tabs').tabs('close', currTitle);
	});
})
</script>
</html>