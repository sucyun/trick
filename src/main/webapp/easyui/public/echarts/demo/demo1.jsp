<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>echarts</title>
<script type="text/javascript" src="public/easyui/jquery.min.js"></script>
<script type="text/javascript" src="public/echarts/echarts.js"></script>
</head>
<body>
	<div id="main" style="width: 600px; height: 400px;">
		<script type="text/javascript">
			var myChart = echarts.init(document.getElementById('main'));
			var app = {};
			option = null;
			// 基于准备好的dom，初始化echarts实例
			// 指定图表的配置项和数据
			option = {
				title : {
					text : '饼图程序调用高亮示例',
					x : 'center'
				},
				tooltip : {
					trigger : 'item',
					formatter : "{a} <br/>{b} : {c} ({d}%)"
				},
				legend : {
					orient : 'vertical',
					left : 'left',
					data : [ '直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎' ]
				},
				series : [ {
					name : '访问来源',
					type : 'pie',
					radius : '55%',
					center : [ '50%', '60%' ],
					data : [ {
						value : 335,
						name : '直接访问'
					}, {
						value : 310,
						name : '邮件营销'
					}, {
						value : 234,
						name : '联盟广告'
					}, {
						value : 135,
						name : '视频广告'
					}, {
						value : 1548,
						name : '搜索引擎'
					} ],
					itemStyle : {
						emphasis : {
							shadowBlur : 10,
							shadowOffsetX : 0,
							shadowColor : 'rgba(0, 0, 0, 0.5)'
						}
					}
				} ]
			};
			
			app.currentIndex = -1;
			app.timeTicket = setInterval(function() {
				var dataLen = option.series[0].data.length;
				// 取消之前高亮的图形
				myChart.dispatchAction({
					type : 'downplay',
					seriesIndex : 0,
					dataIndex : app.currentIndex
				});
				app.currentIndex = (app.currentIndex + 1) % dataLen;
				// 高亮当前图形
				myChart.dispatchAction({
					type : 'highlight',
					seriesIndex : 0,
					dataIndex : app.currentIndex
				});
				// 显示 tooltip
				myChart.dispatchAction({
					type : 'showTip',
					seriesIndex : 0,
					dataIndex : app.currentIndex
				});
			}, 1000);
			if (option && typeof option === "object") {
			    myChart.setOption(option, true);
			}
		</script>
	</div>
</body>
</html>