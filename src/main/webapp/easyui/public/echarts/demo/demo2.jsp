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
		function fetchData(cb) {
		    // 通过 setTimeout 模拟异步加载
		    setTimeout(function () {
		        cb({
		            categories: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"],
		            data: [5, 20, 36, 10, 10, 20]
		        });
		    }, 3000);
		}

		// 初始 option
		option = {
		    title: {
		        text: '异步数据加载示例'
		    },
		    tooltip: {},
		    legend: {
		        data:['销量']
		    },
		    xAxis: {
		        data: []
		    },
		    yAxis: {},
		    series: [{
		        name: '销量',
		        type: 'bar',
		        data: []
		    }]
		};

		myChart.showLoading();

		fetchData(function (data) {
		    myChart.hideLoading();
		    myChart.setOption({
		        xAxis: {
		            data: data.categories
		        },
		        series: [{
		            // 根据名字对应到相应的系列
		            name: '销量',
		            data: data.data
		        }]
		    });
		});;
		if (option && typeof option === "object") {
		    myChart.setOption(option, true);
		}
		</script>
	</div>
</body>
</html>