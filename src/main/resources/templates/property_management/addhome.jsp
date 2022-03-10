<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<title>H+ 后台主题UI框架 - 数据表格</title>
<meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
<meta name="description"
	content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

<link rel="shortcut icon" href="${ctx}/favicon.ico">
<link href="${ctx}/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="${ctx}/css/font-awesome.css?v=4.4.0" rel="stylesheet">

<!-- Data Tables -->
<link href="${ctx}/css/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">

<link href="${ctx}/css/animate.css" rel="stylesheet">
<link href="${ctx}/css/style.css?v=4.1.0" rel="stylesheet">

<!-- 全局js -->
<script src="${ctx}/js/jquery.min.js?v=2.1.4"></script>
<script src="${ctx}/js/bootstrap.min.js?v=3.3.6"></script>

<!-- 引入分页插件  -->
<script src="${ctx}/js/bootstrap-paginator.js"></script>

<script src="${ctx}/js/plugins/jeditable/jquery.jeditable.js"></script>

<!-- Data Tables -->
<script src="${ctx}/js/plugins/dataTables/jquery.dataTables.js"></script>
<script src="${ctx}/js/plugins/dataTables/dataTables.bootstrap.js"></script>

<!-- 引入弹框插件 layer  -->
<script src="${ctx}/js/plugins/layer/layer.min.js"></script>

<!-- 引入layer.js插件 -->
<script src="${ctx}/js/plugins/layer2.4/layer.js"></script>

<!-- 引入 Jquery.validate.js 表单验证框架 -->
<script src="${ctx}/js/jquery.validate.min.js"></script>
<script src="${ctx}/js/messages_zh.min.js"></script>

<!-- 自定义js -->
<script src="${ctx}/js/content.js?v=1.0.0"></script>


</head>

<body>
	<!-- 模态对话框  修改-->
	<div id="modal-form" class="row1">
		<div class="row-2">
			<div class="modal-content">
				<div class="modal-body">
					<div class="row">
						<div class="col-sm-12">
							<h3 class="m-t-none m-b">修改住宅信息</h3>


							<form class="form-horizontal" action="" method="post"
								id="regForm">
								<!-- 隐藏域:带上用户的id -->
								<input type="hidden" name="id">
								<div class="form-group" id="unameForm">
									<label class="col-sm-3 control-label">所属公司:</label>

									<div class="col-sm-7">
										<input id="uname" type="text" name="companyName"
											placeholder="所属公司" class="form-control" placeholder="请输入用户"
											required="required"> <span
											class="help-block m-b-none" id="unameMsg"></span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">住宅编号:</label>
									<div class="col-sm-7">
										<input type="text" id="pwd" name="communityNum"
											placeholder="住宅编号" class="form-control" placeholder="请输入密码">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">住宅名称：</label>

									<div class="col-sm-7">
										<input type="text" name="residentialName" placeholder="住宅名称"
											placeholder="确认密码" class="form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">地址:</label>

									<div class="col-sm-7">
										<input type="text" name="addr" placeholder="地址"
											class="form-control">
									</div>
								</div>

								<div class="form-group">
									<div class="col-sm-offset-3 col-sm-8">
										<button class="btn btn-sm btn-primary" id="update_btn"
											type="submit">修改住宅信息</button>
										<button class="btn btn-sm " type="reset">重新填写</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script>
		$(function() {

			//使用自定规则设置小区编号必须是数字
			$.validator.addMethod("checkcommunityNum", function(value, element,
					param) {
				//value: 当前文本框用户输入的值
				var pattern = /^\d+$/;
				return pattern.test(value);
				//return true/false; true 满足规则:放行,false:不满足规则:显示校验失败信息,不放行
			}, "小区编号必须是数字");

			$("#regForm").validate(
					{
						rules : {
							companyName : "required",
							communityNum : {
								required : true,
								checkcommunityNum : true
							},
							residentialName : "required",
							addr : "required"

						},
						messages : {
							companyName : "公司名不能为空",
							communityNum : {
								required : "小区编号不能为空",
							},
							residentialName : "住宅名字不能为空",
							addr : "地址不能为空"
						},
						submitHandler : function() {

							//序列化表单
							var regFormData = $("#regForm").serialize();

							console.log(regForm)
							//发送ajax请求修改后台的数据
							$.post("${ctx}/homeController.gzsxt?cmd=addHome",
									regFormData, function(data) {

										if (data.code == 1) {
											alert(data.msg);
											//获取父窗口的弹出层 的层级
											var index = parent.layer.getFrameIndex(window.name);
											parent.layer.close(index);
											//清空表单数据
										} else {
											alert(data.msg);

										}
									})

						}

					})

		})
	</script>



</body>
</html>
