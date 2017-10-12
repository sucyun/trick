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
<title>echarts</title>
<script type="text/javascript" src="public/echarts/echarts.js"></script>
</head>
<body>
	<div id="main" style="width: 600px;height:400px;">
		<script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: 'ECharts 入门示例'
            },
            tooltip: {},
            legend: {
                data:['销量']
            },
            xAxis: {
                data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
            },
            yAxis: {},
            series: [{
                name: '销量',
                type: 'bar',
                data: [5, 20, 36, 10, 10, 20]
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
        myChart.on('click', function (params) {
        	console.log(params);
            window.open('https://www.baidu.com/s?wd=' + encodeURIComponent(params.name));
        });
//         myChart.setOption({
//         	roseType: 'angle',
//         	backgroundColor: '#2c343c',
//         	textStyle: {
//        	        color: 'rgba(255, 255, 255, 0.3)'
//        	    },
//             series : [
//                 {
//                     name: '访问来源',
//                     type: 'pie',
//                     radius: '55%',
//                     data:[
//                         {value:400, name:'搜索引擎'},
//                         {value:335, name:'直接访问'},
//                         {value:310, name:'邮件营销'},
//                         {value:274, name:'联盟广告'},
//                         {value:235, name:'视频广告'}
//                     ]
//                 }
//             ],
//             itemStyle: {
//                 normal: {
//                     // 阴影的大小
//                     shadowBlur: 200,
//                     // 阴影水平方向上的偏移
//                     shadowOffsetX: 0,
//                     // 阴影垂直方向上的偏移
//                     shadowOffsetY: 0,
//                     // 阴影颜色
//                     shadowColor: 'rgba(0, 0, 0, 0.5)'
//                 },
//                 emphasis: {
//                     shadowBlur: 200,
//                     shadowColor: 'rgba(0, 0, 0, 0.5)'
//                 }
//             }
//         })
    </script>
	</div>
</body>
</html>