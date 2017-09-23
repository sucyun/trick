<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>登录</title>
<link href="/statics/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
<link href="/statics/css/web/login.css" rel="stylesheet">
<link href="/statics/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link href="/statics/css/plugins/animate.min.css" rel="stylesheet">
</head>
<body>
	<div class="container summary">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<form id="login_form"  action="/g/login.shtml" class="form-horizontal login_form" method="post">
					<span id="tew" class="heading">用户登录</span>
					<div id="username_t" class="form-group">
						<div class="col-sm-12">
							<input type="email" id="username" class="form-control" placeholder="请输入用户名" />
							<i class="fa fa-user"></i>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<input type="text" id="pwd" class="form-control" placeholder="请输入密码" />
							<i class="fa fa-lock"></i>
							<a href="#" class="fa fa-question-circle"></a>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<button id="login_submit" type="button" class="btn">登 录</button>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<p class="message">还没有账户?<a href="#">立刻创建</a></p>
						</div>
					</div>
				</form>
				<form id="register_form"  action="/g/login.shtml" class="form-horizontal register_form" method="post">
					<span class="heading">用户注册</span>
					<div class="form-group">
						<div class="col-sm-12">
							<input type="email" id="username" name="username" class="form-control" placeholder="请输入用户名" />
							<i class="fa fa-user"></i>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<input type="text" id="pwd" name="pwd" class="form-control" placeholder="请输入密码" />
							<i class="fa fa-lock"></i>
							<a href="#" class="fa fa-question-circle"></a>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<input type="text" id="validate" name="validate" class="form-control" placeholder="请输入验证码" />
							<i class="fa fa-lock"></i>
							<a href="#" class="fa fa-question-circle"></a>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<button id="register_submit" type="button" class="btn">注 册</button>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<p class="message">已经有了一个账户?<a href="#">立刻登录</a></p>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
<script src="/statics/js/jquery-3.2.1.min.js"></script>
<script src="/statics/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#login_submit").click(function(){
			$("#username").removeClass('shake_effect');
			setTimeout(function() {
				$("#username").addClass('shake_effect')
			}, 1);
			window.location.href = "/g/sys.shtml";
		});
		$("form input").blur(function(){
			var formObj = $(this);
			console.log($(formObj).val())
			$(formObj).parent().removeClass('shake_effect');
			setTimeout(function() {
				$(formObj).parent().addClass('shake_effect');
			}, 1);
		});
		$('.message a').click(function (e) {
			console.log(1)
			e.preventDefault();
		    $('form').animate({
		        height: 'toggle',
		        opacity: 'toggle',
		    },{
		    	speed:'0',
		    });
		});
	})
</script>
</body>
</html>