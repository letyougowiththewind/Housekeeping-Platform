package com.gnz.pms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product implements Serializable {
	private Long product_id;//商品编号
	private String title;//商品名称
	private Double price;//商品价格
	private String image;//
	private Integer sales;//
	private Integer comments;//
	private String shop_name;
	private Integer shop_id;
	
}
