$.ajaxSetup({
	complete:function(XMLHttpRequest,textStatus){
		var sessionstatus=XMLHttpRequest.getResponseHeader("session-status");
		if(sessionstatus == "timeout"){
			alert("会话超时,请重新登录!")
			//如果超时就处理 ，指定要跳转的页面
			window.location.href= "/login.html";
		}
	}
});
$(function(){
	$("#regForm").validate({  //表示指定的表单要进行数据验证
		submitHandler : function(form) {  //控制表单的提交，如果表单中的所有数据都验证成功则提交，否则不能提交
			var  formData=$("#regForm").serialize();  //将表单的数据序列化之后再提交到后端
			formData=decodeURIComponent(formData);//防止表单出现中文导致乱码
			$.post("/sysuser/add?"+formData,{},function(data){ //发送异步请求提交表单
				if(data){
					alert("添加数据成功");
				}else{
					alert("添加数据失败");
				}
			})
		},
		//如果表单验证失败的时候出现的错误提示
		//element:验证不合法的input对象
		errorPlacement : function(error, element) {
			//jq对“.”是比较敏感的，是属于有特殊意义的字符，如果出现了要替换成转义字符。
			$("#" + $(element).attr("id").replace(".", "\\.") + "Msg").append(error);
		},
		//如果出现了错误，则对应的div显示为高亮红色
		highlight : function(element, errorClass) {
			$(element).fadeOut(1,function() {//先消失
				$(element).fadeIn(1, function() {//再出现
					$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-error");
				});
			})
		},
		//没有出现错误则显示高亮绿色，验证通过的时候的样式
		unhighlight : function(element, errorClass) {
			$(element).fadeOut(1,function() {
				$(element).fadeIn(1,function() {
					$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-success");
				});
			})
		},
		errorClass : "text-danger",  //提示信息的颜色样式
		messages : { //如果对应的表单项出现了错误则显示这些提示信息内容
			"nickName" : "昵称为空或者已经存在了",
			"name":"名字不能为空",
			"pwd":"密码不能为空",
			"phone":"电话不能为空"
		} ,
		rules : { //验证规则
			"name" : {
				required : true  //不能为空
			},
			"nickName" : {
				required : true,
				remote : {  //远程验证
					url : "/sysuser/checkName",  //远程验证的地址
					type : "post",   //发送的请求为post
					dataType : "html",
					data : {  //设置发送给后端验证的数据
						nickName : function() {
							return $("#nickName").val();
						}
					},
					dataFilter : function(data, type) { //回调函数
						if (data=="true")  //如果获取的是true则表示该昵称没有被使用过，则验证通过
							return true;
						else
							return false;
					}
				}
			},
			"pwd" : {
				required : true
			},
			"phone" : {
				required : true
			},
			"qq" : {
				required : true
			},
			"email" : {
				required : true,
				email:true
			},
			"regtime" : {
				required : true
			}
		}
	});
})