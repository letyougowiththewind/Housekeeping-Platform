
window.onload = function(){
	//获取所有的省
	getProvince();
}
// 获取所有的省
function getProvince(){
	//1.创建ajax对象
	var ajax = createAjax();
	//2.建立http请求
	ajax.open("post", "/gzsxtpms/SellConcstract?cmd=community", true);
	//3.监听ajax和http状态变化
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4 && ajax.status == 200){
			
			var res = ajax.responseText;
		
			//将json格式的字符串转成 js对象
			var obj = eval("("+res+")");
			console.log(obj);
			var options = "<option id='-1'>请选择</option>";
			for(var index = 0;index<obj.length;index++){
				var community = obj[index];
				options +="<option value="+community.id+">"+community.ccompanyName+"</option>";
				
			}
			console.log(options);
			//将拼接的options数据设置到省的下拉框中
			document.getElementById("community").innerHTML=options;
		}
	}
	//4.发送请求
	ajax.send();
}

/**
 * 根据省的id查询出对应的市 
 * @Param provinceId :省的id
 */
function getChange(id,changeId){
	console.log(id)
	if(changeId !="-1"){
		//1.创建ajax对象
		var ajax = createAjax();
		//2.建立http请求
		console.log(id == "community")
		if(id == "community"){
			console.log(id == "community")
			ajax.open("post", "/gzsxtpms/SellConcstract?cmd=build&changeId="+changeId, true);
		}else if(id == "build"){
			console.log(id == "build")
			ajax.open("post", "/gzsxtpms/SellConcstract?cmd=floor&changeId="+changeId, true);
		}
		//3.监听ajax和http状态变化
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4 && ajax.status == 200){
				var res = ajax.responseText;
				
				//将json格式的字符串转成 js对象
				var obj = eval("("+res+")");
				console.log(obj);
				var options = "<option id='-1'>请选择</option>";
				for(var index = 0;index<obj.length;index++){
					var changeObj = obj[index];
					options +="<option value="+changeObj.id+">"+changeObj.name+"</option>";
				}
				//将响应的数据设置到市的下拉框中
				if(id == "community"){
					document.getElementById("build").innerHTML=options;
				}else if(id == "build"){
					document.getElementById("floor").innerHTML=options;
				}
			}
		}
		//4.发送请求
		ajax.send();
		
	}else{
		document.getElementById("city").innerHTML="<option value='-1'>请选择</option>";
	}
}
//封装ajax对象,用于浏览器的兼容性
function createAjax(){
	var ajax = null;
	try{
		//w3c以及ie7以上
		ajax = new XMLHttpRequest();
	}catch(e){
		ajax = ActiveXObject("Microsoft.XMLHTTP");
	} 
	return ajax;
}