<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>现代浏览器博物馆</title>
<!-- Bootstrap -->
<link href="/statics/bootstrap-3.3.7/css/bootstrap.css" rel="stylesheet">

<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<style type="text/css">
body {
	padding-top: 50px;
	padding-bottom: 40px;
}

.carousel {
	height: 500px;
	background-color: #000;
	margin-bottom: 60px;
}

.carousel .item {
	height: 500px;
	background-color: #000;
}

.carousel img {
	width: 100%;
}

.carousel-caption p {
	margin-bottom: 20px;
	font-size: 20px;
	line-height: 1.8;
}

hr.divider {
	margin: 40px 0;
}

.feature {
	padding: 30px 0;
}

.feature-heading {
	font-size: 50px;
	color: #2a6496;
	margin-top: 120px;
}

.feature-heading .text-muted {
	font-size: 28px;
	color: #999;
}
.summary {
	padding-right: 15px;
	padding-left: 15px;
}

.summary .col-md-4 {
	margin-bottom: 20px;
	text-align: center;
}
</style>
</head>
<body>
	<!-- 顶部导航 -->
	<nav class="navbar navbar-default navbar-fixed-top navbar-inverse" id="menu-nav">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">切换导航</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">现代浏览器博物馆</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">综述</a></li>
					<li><a href="#summary-container">简述</a></li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
							特点<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li><a href="#feature-tab" data-tab="tab-chrome">Chrome</a></li>
							<li><a href="#feature-tab" data-tab="tab-firefox">Firefox</a></li>
							<li><a href="#feature-tab" data-tab="tab-safari">Safari</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#feature-tab" data-tab="tab-opera">Opera</a></li>
							<li><a href="#feature-tab" data-tab="tab-ie">IE</a></li>
						</ul>
					</li>
					<li><a href="#" data-toggle="modal" data-target="#about">关于</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
			<li data-target="#carousel-example-generic" data-slide-to="1"></li>
			<li data-target="#carousel-example-generic" data-slide-to="2"></li>
			<li data-target="#carousel-example-generic" data-slide-to="3"></li>
			<li data-target="#carousel-example-generic" data-slide-to="4"></li>
		</ol>

	  <!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
		    <div class="item active">
				<img src="/statics/images/example/chrome-big.jpg" alt="0 slide">
				<div class="container">
					<div class="carousel-caption">
						<h1>Chrome</h1>
						<p>Google Chrome，又称Google浏览器，是一个由Google（谷歌）公司开发的网页浏览器。</p>
						<p><a class="btn btn-lg btn-primary" href="http://www.google.cn/intl/zh-CN/chrome/browser/"
                          role="button" target="_blank">点我下载</a></p>
					</div>
				</div>
		    </div>
		    <div class="item">
				<img src="/statics/images/example/firefox-big.jpg" alt="1 slide">
				<div class="container">
					<div class="carousel-caption">
						<h1>Firefox</h1>
						<p>Mozilla Firefox，中文名通常称为“火狐”或“火狐浏览器”，是一个开源网页浏览器。</p>
						<p><a class="btn btn-lg btn-primary" href="http://www.google.cn/intl/zh-CN/chrome/browser/"
                          role="button" target="_blank">点我下载</a></p>
					</div>
				</div>
			</div>
			<div class="item">
				<img src="/statics/images/example/safari-big.jpg" alt="2 slide">
				<div class="container">
					<div class="carousel-caption">
						<h1>Safari</h1>
						<p>Safari，是苹果计算机的最新操作系统Mac OS X中的浏览器。</p>
						<p><a class="btn btn-lg btn-primary" href="http://www.google.cn/intl/zh-CN/chrome/browser/"
                          role="button" target="_blank">点我下载</a></p>
					</div>
				</div>
			</div>
			<div class="item">
				<img src="/statics/images/example/opera-big.jpg" alt="3 slide">
				<div class="container">
					<div class="carousel-caption">
						<h1>Opera</h1>
						<p>Opera浏览器，是一款挪威Opera Software ASA公司制作的支持多页面标签式浏览的网络浏览器。</p>
						<p><a class="btn btn-lg btn-primary" href="http://www.google.cn/intl/zh-CN/chrome/browser/"
                          role="button" target="_blank">点我下载</a></p>
					</div>
				</div>
			</div>
			<div class="item">
				<img src="/statics/images/example/ie-big.jpg" alt="4 slide">
				<div class="container">
					<div class="carousel-caption">
						<h1>IE</h1>
						<p>Internet Explorer，简称 IE，是微软公司推出的一款网页浏览器。</p>
						<p><a class="btn btn-lg btn-primary" href="http://www.google.cn/intl/zh-CN/chrome/browser/"
                          role="button" target="_blank">点我下载</a></p>
					</div>
				</div>
			</div>
		</div>
		<!-- Controls -->
		<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
			<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
			<span class="sr-only">上一页</span>
		</a>
		<a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
			<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">下一页</span>
		</a>
	</div>
	<!--  -->
	<div class="container summary" id="summary-container">
		<div class="row">
			<div class="col-md-4">
				<img class="img-circle" src="/statics/images/example/chrome-logo-small.jpg" alt="chrome">
				<h2>Chrome</h2>
				<p>Google Chrome，又称Google浏览器，是一个由Google（谷歌）公司开发的网页浏览器。</p>
				<p><a class="btn btn-default" href="#" role="button">点我下载</a></p>
			</div>
			<div class="col-md-4">
				<img class="img-circle" src="/statics/images/example/firefox-logo-small.jpg" alt="chrome">
				<h2>Firefox</h2>
				<p>Mozilla Firefox，中文名通常称为“火狐”或“火狐浏览器”，是一个开源网页浏览器。</p>
				<p><a class="btn btn-default" href="#" role="button">点我下载</a></p>
			</div>
			<div class="col-md-4">
				<img class="img-circle" src="/statics/images/example/safari-logo-small.jpg" alt="chrome">
				<h2>Safari</h2>
				<p>Safari，是苹果计算机的最新操作系统Mac OS X中的浏览器。</p>
				<p><a class="btn btn-default" href="#" role="button">点我下载</a></p>
			</div>
		</div>
		<hr class="divider">
	</div>
	<div class="container">
		<ul class="nav nav-tabs" role="tablist"  id="feature-tab">
			<li role="presentation" class="active">
				<a href="#tab-chrome" role="tab" data-toggle="tab" data-namef="tab-chrome">Chrome</a>
			</li>
			<li role="presentation">
				<a href="#tab-firefox" role="tab" data-toggle="tab" data-namef="tab-firefox">Firefox</a>
			</li>
			<li role="presentation">
				<a href="#tab-safari" role="tab" data-toggle="tab" data-namef="tab-safari">Safari</a>
			</li>
			<li role="presentation">
				<a href="#tab-opera" role="tab" data-toggle="tab" data-namef="tab-opera">Opera</a>
			</li>
			<li role="presentation">
				<a href="#tab-ie" role="tab" data-toggle="tab" data-namef="tab-ie">IE</a>
			</li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane active" id="tab-chrome">
				<div class="row feature">
					<div class="col-md-7">
						<h2 class="feature-heading">
							Google Chrome
							<span class="text-muted">使用最广的浏览器</span>
						</h2>
						<p class="lead">Google Chrome，又称Google浏览器，是一个由Google（谷歌）公司开发的网页浏览器。 该浏览器是基于其他开源软件所撰写，包括WebKit，目标是提升稳定性、速度和安全性，并创造出简单且有效率的使用者界面。</p>
					</div>
					<div class="col-md-5">
						<img class="feature-image img-responsive" src="/statics/images/example/chrome-logo.jpg" alt="Chrome">
					</div>
				</div>
			</div>
			<div class="tab-pane" id="tab-firefox">
				<div class="row feature">
					<div class="col-md-5">
						<img class="feature-image img-responsive" src="/statics/images/example/firefox-logo.jpg" alt="Firefox">
					</div>
					<div class="col-md-7">
						<h2 class="feature-heading">
							Mozilla Firefox
							<span class="text-muted">美丽的狐狸</span>
						</h2>
						<p class="lead">Mozilla Firefox，中文名通常称为“火狐”或“火狐浏览器”，是一个开源网页浏览器， 使用Gecko引擎（非ie内核），支持多种操作系统如Windows、Mac和linux。</p>
					</div>
				</div>
			</div>
			<div class="tab-pane" id="tab-safari">
				<div class="row feature">
					<div class="col-md-7">
						<h2 class="feature-heading">
							Safari
							<span class="text-muted">Mac用户首选</span>
						</h2>
						<p class="lead">Safari，是苹果计算机的最新操作系统Mac OS X中的浏览器，使用了KDE的KHTML作为浏览器的运算核心。 Safari在2003年1月7日首度发行测试版，并成为Mac OS X v10.3与之后的默认浏览器，也是iPhone与IPAD和iPod touch的指定浏览器。</p>
					</div>
					<div class="col-md-5">
						<img class="feature-image img-responsive" src="/statics/images/example/safari-logo.jpg" alt="Safari">
					</div>
				</div>
			</div>
			<div class="tab-pane" id="tab-opera">
				<div class="row feature">
					<div class="col-md-5">
						<img class="feature-image img-responsive" src="/statics/images/example/opera-logo.jpg" alt="Opera">
					</div>
					<div class="col-md-7">
						<h2 class="feature-heading">
							Opera
							<span class="text-muted">小众但易用</span>
						</h2>
						<p class="lead">Opera浏览器，是一款挪威Opera Software ASA公司制作的支持多页面标签式浏览的网络浏览器。 是跨平台浏览器可以在Windows、Mac和Linux三个操作系统平台上运行。.</p>
					</div>
				</div>
			</div>
			<div class="tab-pane" id="tab-ie">
				<div class="row feature">
					<div class="col-md-7">
						<h2 class="feature-heading">
							IE
							<span class="text-muted">你懂的</span>
						</h2>
						<p class="lead">Internet Explorer，原称Microsoft Internet Explorer(6版本以前)和Windows Internet Explorer(7，8，9，10版本)， 简称IE，是美国微软公司推出的一款网页浏览器。它采用的排版引擎(俗称内核)为Trident。</p>
					</div>
					<div class="col-md-5">
						<img class="feature-image img-responsive" src="/statics/images/example/ie-logo.jpg" alt="IE">
					</div>
				</div>
			</div>
		</div>
		<footer>
			<p class="pull-right">
				<a href="#top">回到顶部</a>
			</p>
			<p>&copy; 2014 慕课网</p>
		</footer>
	</div>
	<!-- 关于 -->
	<div class="modal fade" id="about" tabindex="-1" role="dialog" aria-labelledby="modal-label" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span>
						<span class="sr-only">关闭</span>
					</button>
					<h4 class="modal-title" id="modal-label">关于</h4>
				</div>
				<div class="modal-body">
					<p>慕课网隶属于北京慕课科技中心(有限合伙)，是一家从事互联网免费教学的网络教育公司。秉承“开拓、创新、公平、分享”的精神， 将互联网特性全面的应用在教育领域，致力于为教育机构及求学者打造一站式互动在线教育品牌。</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">了解了</button>
				</div>
			</div>
		</div>
	</div>
<script src="/statics/js/jquery-3.2.1.min.js"></script>
<script src="/statics/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<script>
	$(function() {
		$('#menu-nav .navbar-collapse a').click(function(e) {
			var href = $(this).attr('href');
			var tabId = $(this).attr('data-tab');
			console.log(tabId)
			if ('#' != href) {
				e.preventDefault();
				$(document).scrollTop($(href).offset().top - 70);
				if (tabId) {
					$('#feature-tab a[data-namef=' + tabId + ']').tab('show');
				}
			}
		});
	});
</script>
</body>
</html>