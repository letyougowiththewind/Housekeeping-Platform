<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>H+ 后台主题UI框架 - 数据表格</title>
<meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
<meta name="description"
	content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">
<link rel="shortcut icon" href="favicon.ico">
<link href="css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="css/font-awesome.css?v=4.4.0" rel="stylesheet">
<!-- Data Tables -->
<link href="css/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">
<link href="css/animate.css" rel="stylesheet">
<link href="css/style.css?v=4.1.0" rel="stylesheet">
<!-- 全局js -->
<script src="js/jquery.min.js?v=2.1.4"></script>
<script src="js/bootstrap.min.js?v=3.3.6"></script>
<!-- 引入分页插件  -->
<script src="js/bootstrap-paginator.js"></script>
<script src="js/plugins/jeditable/jquery.jeditable.js"></script>
<!-- Data Tables -->
<script src="js/plugins/dataTables/jquery.dataTables.js"></script>
<script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>
<!-- 引入日历插件js文件 -->
<script src="js/plugins/layer/laydate/laydate.js"></script>
<!-- 引入弹框插件 layer  -->
<script src="js/plugins/layer/layer.min.js"></script>
<!-- 引入layer.js插件 -->
<script src="js/plugins/layer2.4/layer.js"></script>
<!-- 引入 Jquery.validate.js 表单验证框架 -->
<script src="js/jquery.validate.min.js"></script>
<script src="js/messages_zh.min.js"></script>
<!-- 自定义js -->
<script src="js/content.js?v=1.0.0"></script>



</head>

<body class="gray-bg">

	<div class="col-sm-12">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<h5>
					基本 <small>分类，查找</small>
				</h5>
				<div class="ibox-tools">
					<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
					</a> <a class="dropdown-toggle" data-toggle="dropdown"
						href="table_data_tables.html#"> <i class="fa fa-wrench"></i>
					</a>
					<a class="close-link"> <i class="fa fa-times"></i>
					</a>
				</div>
			</div>
			
			<div class="ibox-content">
					<div class="row">
						<div class="col-sm-9">
							<label>快速查找：<select id="searchByChange">
											<option id="proprietorNum" value="proprietorNum" >业主编码</option>
											<option id="proprietorName" value="proprietorName" >姓名</option>
											<option id="IDNum" value="IDNum" >证件号码</option>
										</select> 
							
								<input placeholder="请输入关键字" id="ownKey" type="search" value="${ownKeyTypeValue}" >
							</label>
						</div>
							
						<div class="col-sm-3">
							<input type="button" id="ownerSearchByGhange" value="查询">
							<input type="button" value="添加" onclick="add_owner()" id="add_owner">
							<input type="button" id="ownerDismiss" onclick="ownerDismiss()" value="停用">
							<input type="button" value="恢复" id="ownerRecover" onclick="ownerRecover()">
						</div>
					</div>
				</div>
						
					<table class="table table-striped table-bordered table-hover dataTables-example dataTable"
						id="DataTables_Table_0" aria-describedby="DataTables_Table_0_info">
						<thead>
							<tr role="row">
								<th class="sorting_asc" tabindex="0" ></th>
								<th class="sorting_asc" tabindex="0" >所属公司</th>
								<th class="sorting_asc" tabindex="0" >业主编码</th>
								<th class="sorting_asc" tabindex="0" >业主姓名</th>
								<th class="sorting_asc" tabindex="0" >性别</th>
								<th class="sorting_asc" tabindex="0" >出生日期</th>
								<th class="sorting_asc" tabindex="0" >证件号码</th>
								<th class="sorting_asc" tabindex="0" >手机号码</th>
								<th class="sorting_asc" tabindex="0" >状态</th>
								<!-- <th class="sorting_asc" tabindex="0" >状态</th> -->
							</tr>
						</thead>
						<tbody>
						
							<!-- 使用标签库 c:forEach 循环 集合${ownerPageResult.dataList} -->
							<c:forEach items="${ownerPageResult.dataList}" var="owner">
								<tr id="${owner.id}" class="gradeA odd">
									<td class="col-sm-1">
										&nbsp;&nbsp;&nbsp;&nbsp;<input type="checkbox" value="option1" id="td_${owner.id}">&nbsp;&nbsp;&nbsp;&nbsp;${owner.id}
									</td>
									<td class=""></td>
									<td class="">${owner.proprietorNum}</td>
									<td class="">${owner.proprietorName}</td>
									<td class="">${owner.gender eq 0 ? '女':'男'}</td>
									<td class="">${owner.brithday}</td>
									<td class="">${owner.IDNum}</td>
									<td class="">${owner.phone}</td>
									<td class="">${owner.status eq 0 ? '停用':'正常'}</td>
							
								</tr>
							</c:forEach>
						</tbody>
						
						
					</table>
					
					<div class="row">
						<div class="col-sm-6">
							<div class="dataTables_info" >
								第${ownerPageResult.currentPage} 页 共<span id="totalPage">${ownerPageResult.totalPage}</span>页,总供 <span id="totalCount">${ownerPageResult.totalCount}</span>条数据</div>
						</div>
						<div class="col-sm-6">
							<div class="dataTables_paginate paging_simple_numbers"
								id="DataTables_Table_0_paginate">
								<ul  id="pagination"></ul>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>


	
	
<script type="text/javascript">

 	$(function(){
		
		//权限设置
		if(${addowenerLimit eq 0}){
 			$("#add_owner").attr("disabled",true);
 			$("#add_owner").css({'background-color':null});
		}
		if(${updateOwnerLimit eq 0}){
			$("#ownerDismiss").attr("disabled",true);
			$("#ownerDismiss").css({'background-color':null});
			
			$("#ownerRecover").attr("disabled",true);
			$("#ownerRecover").css({'background-color':null});
		}

		//条件查询回显
		if(${keyNum eq 2}){
			$("#proprietorName").attr("selected","selected");
		}else if(${keyNum eq 3}){
			$("#IDNum").attr("selected","selected");
		}
		
		//状态转译
		
		
		var pageSize = ${ownerPageResult.pageSize};
		//关键字
		
		$("#pagination").bootstrapPaginator({
			currentPage : ${ownerPageResult.currentPage},//当前页
			totalPages : ${ownerPageResult.totalPage},//总页数
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
			},onPageClicked: function (event, originalEvent, type, page) {
				//TODO具体页面操作
				//跳转到后台用户列表,传递当前页以及每页条数的参数
				var a=$("#searchByChange").val();
				var b=$("#ownKey").val();
				window.location.href="residenceOwner?cmd=ownerList&status=1&currentPage="
						+page+"&"+a+"="+encodeURI(encodeURI(b))+"&ownKeyType="+a;
			}
		});
 	});		
		//业主信息条件查询
		$("#ownerSearchByGhange").click(function(){
			var a=$("#searchByChange").val();
			var b=$("#ownKey").val();
			
			console.log(a);
			console.log(b);
			window.location.href="residenceOwner?cmd=ownerList&currentPage=1&status=0&"+a+"="+encodeURI(encodeURI(b))+"&"+"ownKeyType"+"="+a;
			console.log(${keyNum})
		});
		
		
		
		
	
	//业主信息停用
	function ownerDismiss(){
		var c=$("#searchByChange").val();
		var d=$("#ownKey").val();
		console.log(c);
		console.log(d);
		var dataList = new Array();
		$(":checkbox:checked").parent().parent().each(function(){
			dataList.push($(this).attr("id"));
		});
		
		if($(":checkbox:checked").length == 0){
			layer.alert("请选择需要停用的用户", {
				  skin: 'layui-layer-molv',
				  closeBtn: 30000
			});
		}
		
		window.location.href="residenceOwner?cmd=ownerDismiss&status=1&currentPage="
			+${ownerPageResult.currentPage}+"&dataList="+encodeURI(encodeURI(dataList))+"&"+c+"="+encodeURI(encodeURI(d))+"&ownKeyType="+c;
	}		
	
	//业主信息恢复
	function ownerRecover(){
		
		var c=$("#searchByChange").val();
		var d=$("#ownKey").val();
		var dataList = new Array();
		$(":checkbox:checked").parent().parent().each(function(){
			dataList.push($(this).attr("id"));
		})
		if($(":checkbox:checked").length == 0){
			layer.alert("请选择需要恢复使用的用户", {
				  skin: 'layui-layer-molv',
				  closeBtn: 30000
			});
		}
		window.location.href="residenceOwner?cmd=ownerDismiss&status=0&currentPage="
			+${ownerPageResult.currentPage}+"&dataList="+encodeURI(encodeURI(dataList))+"&"+c+"="+encodeURI(encodeURI(d))+"&ownKeyType="+c;
	}		
	
	//添加业主
	function add_owner(){
		layer.open({
		      type: 2,
		      title: '添加业主信息',
		      maxmin: true,
		      shadeClose: false, //点击遮罩关闭层
		      area : ['90%' , '90%'],
		      content: "residence/add_residence.jsp"
		    });
	}

</script>


</body>

</html>
