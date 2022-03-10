package com.gnz.pms.controller;
import com.gnz.pms.service.IProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
@Controller
@RequestMapping("/product/*")
public class ProductAction {
	@Resource
	private IProductService service;
	@RequestMapping("list")
	public String list(Model model,
					   @RequestParam(value = "column",defaultValue = "price")String column,
					   @RequestParam(value = "sort",defaultValue = "''")String sort,
					   @RequestParam(value = "kw",defaultValue = "")String kw,
					   @RequestParam(value = "cp",defaultValue = "1")Integer cp){
		Integer ls=15;
		Map<String, Object> parammap = new HashMap<>();
		//把查询条件保存到map集合中
		parammap.put("start",(cp-1)*ls);
		parammap.put("cp", cp);
		parammap.put("ls", ls);
		parammap.put("column",column);
		parammap.put("sort", sort);
		parammap.put("kw","%"+kw+"%");
		//调用业务层方法
		try {
			model.addAttribute("map", service.productFindAllSplit(parammap));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//回调模糊查询的关键字，查询完跳转页面后，需要显示关键字
		model.addAttribute("",kw);
		//把查询到的数据传递到前端（在jsp中解析显示）,要跳转到jsp页面（实现服务端跳转)
		return "jsp/product-list";
	}
	@RequestMapping("one")
	public String getOne(String pid,Model model){
		try {
			model.addAttribute("product", service.productFindById(pid));//等价于之前使用request内置对象的setAttribute(key,value)方法
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "jsp/product";
		
	}
}
