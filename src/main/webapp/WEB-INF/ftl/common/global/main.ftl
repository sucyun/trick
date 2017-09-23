<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>主页</title>
<link href="/statics/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="/statics/css/common/base.css" rel="stylesheet">
<link href="/statics/css/common/base2.css" rel="stylesheet">
<link href="/statics/js/plugins/treee-view/bootstrap-treeview.min.css" rel="stylesheet">
<style type="text/css">
</style>
</head>
<body>
	<div class="container summary">
		<!-- top -->
		<nav class="navbar navbar-default navbar-fixed-top navbar-inverse" id="menu-nav">
			<div class="top">
				<div class="row">
					<div class="col-md-12"></div>
				</div>
			</div>
		</nav>
		<div class="container main">
			<div class="row">
				<!-- left -->
				<div class="col-md-2 left">
					<div class="head">
						<span class="glyphicon glyphicon-menu-hamburger" aria-hidden="true"></span>
					</div>
					<div id="treeview" class="left-trees scrollbar">
					</div>
				</div>
				<!-- center -->
				<div class="col-md-10 center">
					<div class="tabs-container">
						<ul class="nav nav-tabs tabs-title" id="tabs_head">
							<li class="active tab-home">
								<a href="#home">首&nbsp;&nbsp;页</a>
							</li>
						</ul>
						<div class="tab-content tabs-all" id="tabs_content">
							<div class="tab-pane active content-home" id="home"></div>
						</div>
						<div id="mousedown_html" style="width: 100px;height: 100px;background-color: #ddd;border-radius:7px;">
							<ul style="width: 100px;height: 100px;padding: 0;">
					            <li style="width: 100px;height: 30px;list-style-type:none;line-height: 30px;text-align: center;"><a tabindex="-1" href="#" operator="top">关闭其他</a></li>
					            <li style="width: 100px;height: 30px;list-style-type:none;line-height: 30px;text-align: center;"><a tabindex="-1" href="#" operator="bottom">全部关闭</a></li>
					        </ul>
						</div>
					</div>
				</div>
				<!-- right -->
<!-- 				<div class="col-md-1 right"> -->
<!-- 					<div class=""></div> -->
<!-- 				</div> -->
			</div>
		</div>
		<!-- bottom -->
		<div class="bottom"></div>
	</div>
<script src="/statics/js/jquery-3.2.1.min.js"></script>
<script src="/statics/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script src="/statics/js/plugins/treee-view/bootstrap-treeview.min.js"></script>
<script type="text/javascript">
	$(function() {
		var tree = [{
			text : "系统管理",
			nodes : [ {
				text : "用户管理"
			},{
				text : "组织架构管理"
			},{
				text : "权限管理"
			}],
			selectable : false,
			state:{
				expanded:true
			}
		}, {
			text : "知识管理",
			nodes: [{
				text : "知识管理",
				state:{
					expanded:false
				}
			},{
				text : "类别管理",
				state:{
					expanded:false
				}
			},{
				text : "标签管理",
				state:{
					expanded:false
				}
			}],
			selectable : false
		}, {
			text : "任务管理"
		}, {
			text : "统计管理",
			nodes:[{
				text : "系统pv量统计"
			}],
			selectable : false
			
		}, {
			text : "日志管理",
			nodes:[{
				text : "系统异常日志"
			}],
			selectable : false
		}, {
			text : "运维监控管理",
			nodes:[{
				text : "系统性能监控"
			},{
				text : "数据库监控"
			},{
				text : "请求响应监控"
			}],
			selectable : false
		}, {
			text : "通用配置管理",
			nodes:[{
				text : "表单业务自动化"
			},{
				text : "通用样式",
				nodes:[{
					text:"表单模板",
					href:'/conf/form.shtml'
				},{
					text:"列表模板",
					href:'/conf/datagrid.shtml'
				},{
					text:"Echart",
					href:'/conf/echart.shtml'
				},{
					text:"通用模板",
					href:'/conf/small.shtml'
				}],
				selectable : false
			}],
			selectable : false
		}];
		var options = {
			enableLinks:false,
			bootstrap2 : false,
			showTags : true,
			showBorder : false,
			levels : 1,
			data : tree,
			onhoverColor : '#00c1de',
			backColor : '#434752',
			color : '#fff',
			selectedBackColor : '#00c1de',
			onNodeSelected : function(event,data){
				event.preventDefault();
				create_tab(data.text,data.href);
			}
		};
		$('#treeview').treeview(options);
		$("#home").load("/conf/home.shtml");
		$("#profile").load("/conf/form.shtml");
		$('#treeview').on('nodeExpanded', function(event, data) {
			$('#treeview').treeview('collapseAll',{silent : true});
			$('#treeview').treeview('revealNode', [ data.nodeId, {silent : true }]);
			$('#treeview').treeview('expandNode', [ data.nodeId, {levels: 1, silent: true }]);
		});
	});
	$('#tabs_head a:first').tab('show');
	$('#tabs_head').on("click", "a", function(e) {
		e.preventDefault();
		$(this).tab('show');
	})
	$('#tabs_head').on("click", "i", function(e) {
		e.preventDefault();
		var title = $(this).parent();
		if($(title).is('.active'))
			$(title).prev().find('a').tab('show');
		var contentid = $(this).prev().attr("href");
		$(contentid).remove();
		$(title).remove();
	})
	//阻止浏览器默认右键点击事件
	$('#tabs_head').on("contextmenu", "li", function(event){
		return false;
	})
	$('#tabs_head').on("mousedown", "li", function(event){
		var e = event || window.event;
		console.log("y:"+e.screenY+"x:"+e.screenX);
// 		console.log(e.which);
	    if (3 == e.which) {//右键为3
	    	$("#mousedown_html").css({position: "absolute",'top':e.screenY-140,'left':e.screenX-316,'z-index':2});
	    }
	})
	$("#menu-nav,.left,#tabs_content").bind("mousedown", function(event){
		$("#mousedown_html").css({'z-index':-92});
	})
	$("#tabs_head").bind("click", function(event){
		$("#mousedown_html").css({'z-index':-92});
	})
	function create_tab(title, url) {
		var cur_tab = $('#tabs_head a[href="#' + title + '"]');
		if (cur_tab[0]) {
			$("#" + title).empty();
			$("#" + title).load(url);
			$('#tabs_head a[href="#' + title + '"]').tab('show');
		} else {
			$("#tabs_head").append('<li><a href="#'+title+'">' + title + '</a><i class="glyphicon glyphicon-remove"></i></li>');
			$("#tabs_content").append('<div class="tab-pane" id="'+title+'"></div>');
			$("#" + title).load(url);
			$('#tabs_head a[href="#' + title + '"]').tab('show');
		}
	}
</script>
</body>
</html>