
window.onload = login
function getVerifyCode(){
	$.ajax({
		url:"imgCode",
		type:"get",
		data:{
			width:60,
			height:30,
		},
		success:function(){
			$("#code").attr("src","imgCode?p=" + Math.random());
		}
	})
}

function login() {
	function getVerifyCode(){
		$.ajax({
			url:"imgCode",
			type:"get",
			data:{
				width:60,
				height:30,
			},
			success:function(){
				$("#code").attr("src","imgCode?p=" + Math.random());
			}
		})
	}
	//生成验证码
	getVerifyCode();
	//实现验证码刷新
	$("#imgCode").on("click",function(){
		$("#code").attr("src","imgCode?p=" + Math.random()) ;  /\s+/g
	});
	//实现表单验证，失去焦点事件
	$("#nickName").blur(function(){
		//正则 \s+ 表示空格字符，g表示全局验证
		var uname = $("#nickName").val().replace(/\s+/g,"");
		if(uname.length==0){
			$("#u_inputErrorid").css("display","block");
			return false;
		}else{
			$("#u_inputErrorid").css("display","none");
		}
	})
	$("#pwd").blur(function(){
		var pwd = $("#pwd").val().replace(/\s+/g,"");;
		if(pwd.length==0){
			$("#p_inputErrorid").css("display","block");
			return false;
		}else{
			$("#p_inputErrorid").css("display","none");
		}
	})
	$("#id").blur(function(){
		var id = $("#id").val().replace(/\s+/g,"");
		if(id.length==0){
			$("#i_inputErrorid").css("display","block");
			return false;
		}else{
			$("#i_inputErrorid").css("display","none");
		}
	})
	$("#ran").blur(function(){
		var pwd1 = $("#ran").val().replace(/\s+/g,"");
		if(pwd1.length==0){
			$("#r_inputErrorid").css("display","block");
			return false;
		}else{
			// alert($(this).val())
			var rand1 = $(this).val();
			$.ajax({
				type:"POST",
				dataType:"json",
				url:"sysuser/msg_login?rand="+rand1,
				success:function(data){
					$.each(data,function(key,value){
						// alert(value)
						if(value=='falseinput'){
							$("#r_inputErrorid2").css("display","block");
							return false;
						}
					})
				}
			})
			$("#r_inputErrorid").css("display","none");
			$("#r_inputErrorid2").css("display","none");
		}
	})
	//验证码输入框得到焦点事件
	$("#ran").focus(function(){
		$("#r_inputErrorid").css("display","none");
		$("#r_inputErrorid2").css("display","none");
	})
	//识别码输入框得到焦点事件
	$("#id").focus(function(){
		$("#i_inputErrorid2").css("display","none");
	})
	//密码输入框得到焦点事件
	$("#pwd").focus(function(){
		$("#u_inputErrorid2").css("display","none");
		$("#w_inputErrorid2").css("display","none");
		$("#w_inputErrorid").css("display","block");
		$("#w_inputErrorid3").css("display","none");
	})
}
function ceshi(){
	var pwd = $("#ran").val().replace(/\s+/g,"");
	var id2 = $("#id").val().replace(/\s+/g,"");
	var nickname = $("#nickName").val().replace(/\s+/g,"");
	var pwd2 = $("#pwd").val().replace(/\s+/g,"");
	if(pwd.length!==0&&id2.length!==0){
		var rand = $("#ran").val();
		$.ajax({
			type:"POST",
			dataType:"json",
			url:"sysuser/msg_login?rand="+rand,
			success:function(data){
				$.each(data,function(key,value){
					// alert(value)
					if(value=='falseinput'){
						$("#r_inputErrorid2").css("display","block");
						return false;
					}else if(value=='trueinput'){
						$.ajax({
							type:"GET",
							dataType:"text",
							url:"sysuser/role_id?nickName="+nickname,
							success:function(data){
								if(id2!==data){
									$("#i_inputErrorid2").css("display","block");
									getVerifyCode();
									return false;
								}else if(id2===data){
									$.ajax({
										type:"GET",
										dataType:"text",
										url:"sysuser/user_pwd?nickName="+nickname+"&pwd="+pwd2,
										success:function(data){
											if(data==="shibai"){
												$("#w_inputErrorid2").css("display","block");
												$("#w_inputErrorid").css("display","none");
												getVerifyCode();
												return false;
											} else if(data==="suoding"){
												$("#w_inputErrorid").css("display","none");
												$("#w_inputErrorid3").css("display","block");
											}else if(data==="chenggong"){
												$("#myform").submit();
											}
										}
									})

								}
							}
						})

					}
				})
			}
		})
	}
}


