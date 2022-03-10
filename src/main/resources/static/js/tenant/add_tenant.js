$(function(){
        //jquery.validate
        $("#tenantForm").validate({
    			errorPlacement : function(error, element) {
    				$("#" + $(element).attr("id").replace(".", "\\.") + "Msg").append(error);
    			},
    			highlight : function(element, errorClass) {
    				$(element).fadeOut(1,function() {
    					$(element).fadeIn(1, function() {
    						$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-error");
    					});

    				})
    			},
    			unhighlight : function(element, errorClass) {
    				$(element).fadeOut(1,function() {
    					$(element).fadeIn(1,function() {
    							$("#" + $(element).attr("id").replace(".","\\.") + "Div").attr("class","form-group has-success");
    					});
    				})
    			},
    			errorClass : "text-danger",
    			/* 校验规则 */
				rules : {
					companyname : "required",
					managername : "required",
					phone : "required",
					identity : "required",
					identitypositive : "required",
					identitynegative : "required"
				},
				/* 校验规则触发以后对应的提示信息:必须和上面规则对应 */
				messages : {
					companyname : "公司名称不能为空",
					managername : "公司法人不能为空",
					phone : "电话不能为空",
					identity : "身份证不能为空",
					identitypositive : "请上传身份证正面",
					identitynegative : "请上传身份证反面"
				},
				/* 校验完毕触发的事件 */
				submitHandler : function() {
					//1.序列化表单获取页面的所有表单元素的数据
					var formData = $("#tenantForm").serialize();
					console.log(formData);
					//2.使用ajaxpost提交,向后台提交数据
					$.post("/tenant/add",formData,function(data){
						//在js的if语句中 会进行隐式转换  1可以转换为true  0转换为false
							if (data) {
								//使用layer插件进行弹框提示
								//墨绿深蓝风
								layer.alert("添加成功", {
								  skin: 'layui-layer-molv' //样式类名
								}, function(index){
									layer.close(index);
									window.location.href="/tenants/add_tenant.html"; //继续跳转到添加页面
								});
							} else {
								layer.alert("添加失败", {
									  skin: 'layui-layer-molv' //样式类名
								});
							}
					});
				}
    		});
})
//身份证正面上传
jQuery(function() {
	    var list = $('#thelist1'),//在该节点中添加文本  以及等待上传的文件（div 中添加）
	        btn = $('#ctlBtn1'), //文件上传的按钮
	        state = 'pending',  //等待上传的状态
	        uploader;//即将使用来初始化上传的基本配置
	    uploader = WebUploader.create({// 对上传的基本要求进行初始化配置
	        resize: false, //不进行压缩
	        server: "/tenant/fileUpload",//上传的地址
	        pick: '#picker1' ,//指定选择文件的按钮
	        accept: {
	            title: 'Images', //名称
	            extensions: 'jpg,jpeg,bmp,png,gif',//允许上传的图片格式
	            mimeTypes: 'image/*'//只接受图片类型的文件
	        },
	        fileNumLimit:2,//允许选择上传的文件的最大个数
	        fileSingleSizeLimit : 1024 * 1024 //  单个文件的最大上传限制为1M
	    });
	    //当有文件添加进来时候显示的样式配置
	    uploader.on( 'fileQueued', function(file) {
	    	//file.id: 表示添加进来的文件的编号（上传组件生成的）
			//file.name : 表示文件的原始名称
	    	console.log(file.id)
	        list.append('<div id="' + file.id + '" class="item">' +
	            '<h4 class="info">' + file.name + '</h4>' +
	            '<p class="state">等待上传...</p>' +
	        '</div>' );
	    });
	    // 'uploadProgress'：文件上传过程中创建实时显示的进度条样式，这是结合BootStrap样式实现的
	    // percentage：文件上传的百分比
	    //file： 上传的文件对象  是上传组件根据我们选择的文件的基本信息生成的一个对象
	    uploader.on( 'uploadProgress', function( file,percentage){
	        var li = $( '#'+file.id ),
            //在li 表示的节点下查找是否有进度条样式， 如果有则百分比长度大于0  否则就等于0
	        percent=li.find('.progress .progress-bar');
	        // 避免重复创建
	        if (!percent.length ) {//如果没有则追加一个
	        	//追加一个进度条样式
	            percent = $('<div class="progress progress-striped active">' +
	              '<div class="progress-bar" role="progressbar" style="width: 0%">' +
	              '</div>' +
	            '</div>').appendTo(li).find('.progress-bar');
	        }
	        li.find('p.state').text('上传中');
	        percent.css( 'width', percentage * 100 + '%' );
	    });
	    //'uploadSuccess'：文件上传成功之后触发该回调函数
	    //response: 该对象封装了服务端返回的数据 。要取得返回的数据需要用到该对象的一个属性（_raw）
	    uploader.on( 'uploadSuccess', function( file,response){
			//图片上传成功之后保存的地址信息（路径信息）
	    	var idcard_url ="/"+response._raw;
			console.log("地址信息："+idcard_url);
	    	 $("#idcard_id1").append("<br><img  src='"+idcard_url+"' style='width: 150px;height: 150px' />");
	    	 $("#identitypositive").val(idcard_url);
	    	 $('#'+file.id ).find('p.state').text('已上传');
			 $( '#'+file.id ).find('.progress').fadeOut();
	    });
	    uploader.on( 'uploadError', function( file ) {
	        $( '#'+file.id ).find('p.state').text('上传出错');
	    });
	    uploader.on( 'all', function( type ) {
	        if ( type === 'startUpload' ) {
	            state = 'uploading';
	        } else if ( type === 'stopUpload' ) {
	            state = 'paused';
	        } else if ( type === 'uploadFinished' ) {
	            state = 'done';
	        }
	        if ( state === 'uploading' ) {
	            btn.text('暂停上传');
	        } else {
	            btn.text('开始上传');
	        }
	    });
	    //出错以后回调的函数
	    uploader.on("error",function(type){
	    	if(type == "F_EXCEED_SIZE"){
	    		layer.alert('最大只能上传1M文件', {
	    			  skin: 'layui-layer-molv' //样式类名
	    		});
	    	}
	    });
	    btn.on( 'click', function() {
	        if (state === 'uploading' ) {
	            uploader.stop();
	        } else {
	            uploader.upload();
	        }
	    });
	});
 //身份证反面
	jQuery(function() {
	    var list = $('#thelist2'), // thelist节点中添加文本  身份证正面  div添加  
	        btn = $('#ctlBtn2'), // 文件上传按钮   身份证正面 文件上传按钮
	        state = 'pending',
	        uploader;
	    //初始化
	    uploader = WebUploader.create({
	        // 不压缩image
	        resize: false,
	        // 文件接收服务端。
	        server: "/tenant/fileUpload",
	        // 选择文件的按钮。可选。
	        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	        	//开始上传 按钮的id 
	        pick: '#picker2',  
	        	 // 只允许选择图片文件。
	        accept: {
	            title: 'Images',
	            extensions: 'jpg,jpeg,bmp,png',
	            mimeTypes: 'image/*'
	        },
	        //允许上传的文件个数
	        fileNumLimit:2,
	        //单个文件最大的上传限制 1M
	        fileSingleSizeLimit : 5*1024 * 1024 // 1M
	    });
	    // 当有文件添加进来的时候
	    uploader.on( 'fileQueued', function(file) {
	        list.append( '<div id="' + file.id + '" class="item">' +
	            '<h4 class="info">' + file.name + '</h4>' +
	            '<p class="state">等待上传...</p>' +
	        '</div>' );
	    });
	    // 文件上传过程中创建进度条实时显示。
	    uploader.on( 'uploadProgress', function( file, percentage ) {
	        var li = $( '#'+file.id ),
	            percent =li.find('.progress .progress-bar');
	        // 避免重复创建
	        if (!percent.length ) {
	            percent = $('<div class="progress progress-striped active">' +
	              '<div class="progress-bar" role="progressbar" style="width: 0%">' +
	              '</div>' +
	            '</div>').appendTo(li).find('.progress-bar');
	        }
	        li.find('p.state').text('上传中');
	        percent.css( 'width', percentage * 100 + '%' );
	    });
	    //文件上传成功后  触发的事件  触发一个回调函数
	    uploader.on( 'uploadSuccess', function( file,response) {
	    	// 上传的图片保存的相对位置
	    	var idcard_url = "/"+response._raw;
	    	 $("#idcard_id2").append("<img  src='"+idcard_url+"' style='width: 150px;height: 150px' />");
	    	 //将url 添加到  form表单的隐藏域中
	    	 $("#identitynegative").val(idcard_url);
	    	$( '#'+file.id ).find('p.state').text('已上传');
	    });
	    uploader.on( 'uploadError', function( file ) {
	        $( '#'+file.id ).find('p.state').text('上传出错');
	    });
	    uploader.on( 'uploading', function( file ) {
	    	$( '#'+file.id ).find('p.state').text('上传中...');
	    });
	    uploader.on( 'uploadComplete', function( file ) {
	        $( '#'+file.id ).find('.progress').fadeOut();
	    });
	    uploader.on( 'all', function( type ) {
	        if ( type === 'startUpload' ) {
	            state = 'uploading';
	        } else if ( type === 'stopUpload' ) {
	            state = 'paused';
	        } else if ( type === 'uploadFinished' ) {
	            state = 'done';
	        }
	        if ( state === 'uploading' ) {
	            btn.text('暂停上传');
	        } else {
	            btn.text('开始上传');
	        }
	    });
	  //出错以后回调的函数
	    uploader.on("error",function(type){
	    	if(type == "F_EXCEED_SIZE"){
	    		layer.alert('最大只能上传1M文件', {
	    			  skin: 'layui-layer-molv' //样式类名
	    		});
	    	}
	    });
	    btn.on( 'click', function() {
	        if ( state === 'uploading' ) {
	            uploader.stop();
	        } else {
	            uploader.upload();
	        }
	    });
	});
 
    	