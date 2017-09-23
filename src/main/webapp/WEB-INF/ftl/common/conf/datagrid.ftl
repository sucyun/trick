<link href="/statics/js/plugins/datetimepicker/bootstrap-datetimepicker.min.css" rel="stylesheet">
<div class="row core">
	<div class="core-admin">
		<form class="form-horizontal t-condition">
<!-- 		<div class="col-md-10 col-md-offset-1 t-condition"> -->
			<div class="t-condition-line">
				<div class="search-group">
					<input type="text" class="search-input" id="searchkey" name="searchkey" placeholder="输入关键字" />
					<button type="button" class="search-button" id="searchsubmit" name="searchsubmit">搜索</button>
				</div>
			</div>
			<div class="t-condition-line">
				<div class="input-group line-input">
					<span class="input-group-addon" id="basic-addon1">用户名</span>
					<input type="text" class="form-control" placeholder="用户名" aria-describedby="basic-addon1">
				</div>
				<div class="input-group line-select">
					<span class="input-group-addon" id="basic-addon1">部门</span>
					<select class="form-control">
						<option>1</option>
						<option>2</option>
						<option>3</option>
						<option>4</option>
						<option>5</option>
					</select>
				</div>
<!-- 			</div> -->
<!-- 			<div class="t-condition-line"> -->
				<div class="input-group line-date">
					<span class="input-group-addon" id="starttime">开始时间</span>
					<div style="width: auto;" class="input-group date form_datetime col-md-8" data-date="1979-09-16T05:25:07Z" data-date-format="dd MM yyyy - HH:ii p"
						data-link-field="starttime_input">
						<input class="form-control" size="14" type="text" value="" placeholder="开始时间" readonly>
						<span class="input-group-addon">
							<span class="glyphicon glyphicon-remove"></span>
						</span>
						<span class="input-group-addon">
							<span class="glyphicon glyphicon-th"></span>
						</span>
					</div>
					<input type="hidden" id="starttime_input" value="" />
				</div>
				<div class="input-group line-date">
					<span class="input-group-addon" id="endtime">结束时间</span>
					<div style="width: auto;" class="input-group date form_datetime col-md-8" data-date="1979-09-16T05:25:07Z" data-date-format="dd MM yyyy - HH:ii p"
						data-link-field="endtime_input">
						<input class="form-control" size="14" type="text" value="" placeholder="结束时间" readonly>
						<span class="input-group-addon">
							<span class="glyphicon glyphicon-remove"></span>
						</span>
						<span class="input-group-addon">
							<span class="glyphicon glyphicon-th"></span>
						</span>
					</div>
					<input type="hidden" id="endtime_input" value="" />
				</div>
			</div>
		</form>
<!-- 		</div> -->
		<div class="t-grid">
			<table class="table table-hover table-bordered t-grid-table">
				<thead>
					<tr>
						<th>#</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Username</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th scope="row">1</th>
						<td>Mark</td>
						<td>Otto</td>
						<td>@mdo</td>
					</tr>
					<tr>
						<th scope="row">2</th>
						<td>Jacob</td>
						<td>Thornton</td>
						<td>@fat</td>
					</tr>
					<tr>
						<th scope="row">3</th>
						<td>Larry</td>
						<td>the Bird</td>
						<td>@twitter</td>
					</tr>
				</tbody>
			</table>
			<div class="row t-grid-pages">
				<div class="col col-md-6 t-grid-pages-left">
					显示第 1条 到  10条  共 57条
					<span>跳转到
						<input class="" value="" /> 页
					</span>
				</div>
				<div class="col col-md-6 t-grid-pages-right">
					<ul class="pagination">
						<li><a href="#">&laquo;</a></li>
						<li><a href="#">&lsaquo;</a></li>
					    <li><a href="#">1</a></li>
					    <li><a href="#">2</a></li>
					    <li><a href="#">3</a></li>
					    <li><a href="#">4</a></li>
					    <li><a href="#">5</a></li>
					    <li><a href="#"><i class="glyphicon glyphicon-option-horizontal omit"></i></a></li>
					    <li><a href="#">&rsaquo;</a></li>
					    <li><a href="#">&raquo;</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="/statics/js/plugins/datetimepicker/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
<script src="/statics/js/plugins/datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript">
$('.form_datetime').datetimepicker({
    language:  'zh-CN',
    weekStart: 1,
    todayBtn:  1,
	autoclose: 1,
	todayHighlight: 1,
	startView: 2,
	forceParse: 0,
    showMeridian: 1
});
</script>