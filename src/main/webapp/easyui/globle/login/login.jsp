<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>登录</title>
<link href="globle/login/css/bootstrap.min.css" rel="stylesheet">
<link href="globle/login/css/signin.css" rel="stylesheet">
<script type="text/javascript" src="public/js/jquery.min.js"></script>
<script type="text/javascript" src="public/layer/layer.js"></script>
</head>
<body>
	<div style="color: red; font-size: 22px;">${message_login}</div>
	<div class="signin">
		<div class="signin-head">
			<img src="globle/login/images/test/head_120.png" alt="" class="img-circle">
		</div>
		<form class="form-signin" action="globle/submitlogin">
			<input id="username" type="text" class="form-control" name="username" placeholder="用户名" value="" required autofocus />
			<input id="password" type="password" class="form-control" name="password" placeholder="密码" required value="" />
<!-- 			<input type="text" class="form-control" name="verifyCode" placeholder="验证码：" required/> -->
<!-- 			<img id="verifyCodeImage" onclick="reloadVerifyCode()" src="mydemo/getVerifyCodeImage" /><br /> -->
			<button id="loginBtn" class="btn btn-lg btn-warning btn-block" type="button">登录</button>
			<label class="checkbox">
				<input type="checkbox" value="remember-me"> 记住我
			</label>
		</form>
		<div class="error"><span></span></div>
	</div>
</body>
<script type="text/javascript">
	function reloadVerifyCode() {
		var timestamp = new Date().getTime();
		document.getElementById('verifyCodeImage').setAttribute('src', 'globle/getVerifyCodeImage?timestamp='+timestamp);
	}
	$("#loginBtn").click(function(){
		layer.load();
		var username = $("#username").val();
		var password = $("#password").val();
		var data = {password:password,username:username,age:23};
		$.post("globle/submitlogin.shtml",data ,function(result){
    		if(result && result.status != 200){
    			layer.closeAll('loading');
    			layer.msg(result.message);
    		}else{
    			layer.closeAll('loading');
    			layer.msg('登录成功!');
    			setTimeout(function(){
    				//登录返回
	    			window.location.href= result.backurl || "${basePath}/";
    			},500)
    		}
    	},"json");
	})
</script>
</html>