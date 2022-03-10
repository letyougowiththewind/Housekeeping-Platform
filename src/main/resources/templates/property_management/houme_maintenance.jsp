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
	<div class="row">
		<div class="col-sm-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>
						住宅维护<small>分类，查找</small>
					</h5>
					<div class="ibox-tools">
						<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
						</a> <a class="dropdown-toggle" data-toggle="dropdown"
							href="table_data_tables.html#"> <i class="fa fa-wrench"></i>
						</a>
						<ul class="dropdown-menu dropdown-user">
							<li><a href="table_data_tables.html#">选项1</a></li>
							<li><a href="table_data_tables.html#">选项2</a></li>
						</ul>
						<a class="close-link"> <i class="fa fa-times"></i>
						</a>
					</div>
				</div>
				<div class="ibox-content">
					<div id="DataTables_Table_0_wrapper"
						class="dataTables_wrapper form-inline" role="grid">
						<div class="row">
							<div class="col-sm-6">
								<div class="dataTables_length" id="DataTables_Table_0_length">
									<label>每页 <select id="pageSize"
										class="form-control input-sum">
											<option value="50"
												${homePageResult.pageSize eq 50? 'selected':''}>50</option>
											<option value="10"
												${homePageResult.pageSize eq 10? 'selected':''}>10</option>
											<option value="25"
												${homePageResult.pageSize eq 25? 'selected':''}>25</option>
											<option value="100"
												${homePageResult.pageSize eq 100? 'selected':''}>100</option>
									</select> 条记录
									</label>
								</div>
							</div>
							<div class="col-sm-6">
								<div id="DataTables_Table_0_filter" class="dataTables_filter">
									<label>查找：<input id="search"
										class="form-control input-sm"
										aria-controls="DataTables_Table_0" type="search"
										value=${keyword}></label>
								</div>
							</div>
						</div>
						<table
							class="table table-striped table-bordered table-hover dataTables-example dataTable"
							id="DataTables_Table_0"
							aria-describedby="DataTables_Table_0_info">
							<thead>
								<tr role="row">
									<th class="sorting_asc" tabindex="0"
										aria-controls="DataTables_Table_0" rowspan="1" colspan="1"
										style="width: 196.2px;" aria-sort="ascending"
										aria-label="渲染引擎：激活排序列升序">所属公司</th>
									<th class="sorting" tabindex="0"
										aria-controls="DataTables_Table_0" rowspan="1" colspan="1"
										style="width: 220.2px;" aria-label="浏览器：激活排序列升序">住宅编号</th>
									<th class="sorting" tabindex="0"
										aria-controls="DataTables_Table_0" rowspan="1" colspan="1"
										style="width: 220.2px;" aria-label="平台：激活排序列升序">住宅名称</th>
									<th class="sorting" tabindex="0"
										aria-controls="DataTables_Table_0" rowspan="1" colspan="1"
										style="width: 318.2px;" aria-label="引擎版本：激活排序列升序">地址</th>
									<th class="sorting" tabindex="0"
										aria-controls="DataTables_Table_0" rowspan="1" colspan="1"
										style="width: 138.2px;" aria-label="CSS等级：激活排序列升序">操作</th>
								</tr>
							</thead>
							<tbody>
								<!-- 使用标签库 c : forEach 循环集合 ${users} -->
								<c:forEach items="${homePageResult.dataList}" var="community">
									<tr id="tr_${community.id}" class="gradeA odd">
										<td class="">${community.ccompanyName}</td>
										<td class="">${community.communityNum}</td>
										<td class="">${community.residentialName}</td>
										<td class="">${community.caddr}</td>
										<td class="">
											<!-- 增加功能 -->
											<a  href="addcommunityinfo/addCommunity.jsp" class="btn btn-info">增加</a>
											<!-- 修改功能 --> <a onclick="updateCommunity(this);"
											class="btn btn-info" data-toggle="modal" href="#modal-form"
											data-id="${community.id}"
											data-ccompanyName="${community.ccompanyName}"
											data-communityNum="${community.communityNum}"
											data-residentialName="${community.residentialName}"
											data-caddr="${community.caddr}">修改</a> <!-- 使用onclick 触发删除函数 -->
											<button onclick="delSysUser(this,${community.id})"
												class="btn btn-danger">删除</button>
										</td>
								</c:forEach>
							</tbody>
						</table>
						<div class="row">
							<div class="col-sm-6">
								<div class="dataTables_info" id="DataTables_Table_0_info"
									role="alert" aria-live="polite" aria-relevant="all">
									第${homePageResult.currentPage}页, 共<span id="totalPage">${homePageResult.totalPage}</span>页,
									总共<span id="totalCount">${homePageResult.totalCount}</span>条数据
								</div>
							</div>
							<div class="col-sm-6">
								<div class="dataTables_paginate paging_simple_numbers"
									id="DataTables_Table_0_paginate">
									<ul id="pagination">
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 模态对话框  修改-->
	<div id="modal-form" class="modal fade">
		<div class="modal-dialog">
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
										<input id="uname" type="text" name="ccompanyName"
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
										<input type="text" name="caddr" placeholder="地址"
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
		function updateCommunity(obj) {
			//获取当前超链接上面的所有data-xx属性
			var id = $(obj).data("id");
			var ccompanyName = $(obj).data("ccompanyname");
			var communityNum = $(obj).data("communitynum");
			var residentialName = $(obj).data("residentialname");
			var caddr = $(obj).data("caddr")
			//获取form表单
			var regForm = $("#regForm")[0]
			//将获取的数组赋值给表单元素对应的value值(就是点击修改弹出模态表态上的信息可以显示出来)
			regForm.id.value = id;
			regForm.ccompanyName.value = ccompanyName;
			regForm.communityNum.value = communityNum;
			regForm.residentialName.value = residentialName;
			regForm.caddr.value = caddr;
		}
		$(function() {
			$("#pagination")
					.bootstrapPaginator(
							{
								currentPage : ${homePageResult.currentPage},//当前页
								totalPages : ${homePageResult.totalPage},//总页数
								size : "normal",
								bootstrapMajorVersion : 3,
								alignment : "right",
								numberOfPages : 7,
								itemTexts : function(type, page, current) {
									switch (type) {
									case "first":
										return "首页";
									case "prev":
										return "上一页";
									case "next":
										return "下一页";
									case "last":
										return "尾页";
									case "page":
										return page;
									}
								},
								onPageClicked : function(event, originalEvent,
										type, page) {

									//跳转到后台页面传递当前参数
									window.location.href = "${ctx}/homeController.gzsxt?cmd=homeList&currentPage="
											+ page
											+ "&pageSize="
											+ $("#pageSize").val()
											+ "&keyword=${keyword}"
								}

							})

			//为每页显示多少条数据的下拉框绑定一个change事件
			$("#pageSize")
					.change(
							function() {
								window.location.href = "${ctx}/homeController.gzsxt?cmd=homeList&currentPage=1&pageSize="
										+ this.value + "&keyword=${keyword}";
							});
			//监听搜索框键盘按下的事件:如果用户按下的是回车键,就触发向后台发送请求
			$("#search")
					.keyup(
							function(event) {
								//event是js事件编程中的一个内置对象,可以获取当前用户按下键盘任意键的所有信息
								//键盘的每一数字都是对应的ASCII码,13是Enter 键盘
								if (event.keyCode == 13) {
									window.location.href = "${ctx}/homeController.gzsxt?cmd=homeList&keyword="
											+ $(this).val()
											+ "&keyword=${keyword}";
								}
							})
			//使用自定规则设置小区编号必须是数字
			$.validator.addMethod("checkcommunityNum", function(value, element,
					param) {
				//value: 当前文本框用户输入的值
				var pattern = /^\d+$/;
				return pattern.test(value);
				//return true/false; true 满足规则:放行,false:不满足规则:显示校验失败信息,不放行
			}, "小区编号必须是数字");

			$("#regForm")
					.validate(
							{
								rules : {
									ccompanyName : "required",
									communityNum : {
										required : true,
										checkcommunityNum : true
									},
									residentialName : "required",
									caddr : "required"

								},
								messages : {
									ccompanyName : "公司名不能为空",
									communityNum : {
										required : "小区编号不能为空",
									},
									residentialName : "住宅名字不能为空",
									caddr : "地址不能为空"
								},
								submitHandler : function() {

									//序列化表单
									var regFormData = $("#regForm").serialize();

									//发送ajax请求修改后台数据
									$
											.post(
													"${ctx}/homeController.gzsxt?cmd=updateUser",
													regFormData,
													function(data) {
														//修改成功
														if (data.code == 1) {
															//获取regForm表单
															var regForm = $("#regForm")[0];
															//获取当前修改数据对应的行
															var tr = $("#tr_"
																	+ regForm.id.value);
															console.log(tr)
															var tds = tr
																	.children();
															console.log(tds)

															//将表单的公司名设置到第一列数据
															$(tds)
																	.eq(0)
																	.text(
																			regForm.ccompanyName.value)
															//将表单的公司编号设置到第二列数据
															$(tds)
																	.eq(1)
																	.text(
																			regForm.communityNum.value)
															//将表单的公司的住宅名称设置到第三列数据
															$(tds)
																	.eq(2)
																	.text(
																			regForm.residentialName.value)
															//将表单的公司的地址设置到第四列数据
															$(tds)
																	.eq(3)
																	.text(
																			regForm.caddr.value)
															//隐藏模态窗口
															$('#modal-form')
																	.modal(
																			'hide');
														}

													})

								}

							})

		})//最大外面的function括号()

		/**
		 *  点击增加数据
		 */
		function addCommunity() {
			layer.open({
				type : 2,
				title : '添加住宅',
				maxmin : true,
				shadeClose : false, //点击遮罩关闭层
				area : [ '90%', '80%' ],
				content : "${ctx}/property_management/addhome.jsp"
			});
		}

		/**
		 *  点击删除 调用删除失败
		 */
		function delSysUser(obj, id) {
			alert("确定要删除吗?");
			console.log(id);

			//使用ajax向后台发送删除操作的请求,并带上删除的did
			$.get("${ctx}/homeController.gzsxt?cmd=deleteUser", {
				id : id
			},
					function(data) {

						if (data.code == 1) {

							//删除当前行<tr>
							//当前按钮的父元素是td,td的父元素是tr,删除tr就是删除整一行
							var tr = $(obj).parent().parent();
							tr.remove();

							//获取总数 -1 
							var totalCount = $("#totalCount").text();
							$("#totalCount").text(parseInt(totalCount) - 1);

							if (parseInt(totalCount)
									% ${homePageResult.pageSize} == 0) {
								var totalPage = $("#totalPage").text();
								$("#totalPage").text(parseInt(totalPage) - 1)

							}

						} else {
							alert("删除失败")
						}

					})

		}
	</script>




</body>
</html>
