<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>地铁线路</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, shrink-to-fit=no"/>
<script type="text/javascript" src="http://webapi.amap.com/subway?v=1.0&amp;key=您申请的key值&amp;callback=cbk"></script>
<script type="text/javascript" src="/statics/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="/statics/layer/layer.js"></script>
</head>
<body>
	<div id="mysubway"></div>
	<input type="button" onclick="tr()" value="13132">
</body>
<script type="text/javascript">
//开启easy模式, 直接完成地铁图基本功能, 无需自己写交互
// window.cbk = function(){
//     var mysubway = subway("mysubway", {
//         easy: 1
//     });
// };
function tr(){
	layer.open({
		  type: 1,
		  skin: 'layui-layer-rim', //加上边框
		  area: ['420px', '240px'], //宽高
		  content: '注册名称：<select><option>1132132</option><option>465456</option><option>789798</option></select></br>'+
		  			'文本框：<input type="text" value="">'
	});
}
</script>
</html>