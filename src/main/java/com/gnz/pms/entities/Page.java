package com.gnz.pms.entities;

import java.io.Serializable;
import java.util.Arrays;

public class Page implements Serializable {
	private Integer cp;//表示当前页
	private Integer allPages;//总页数（总数居量对每页显示的数据量求余计算而来）
	private int[] pages;//保存分页信息的数值（这就是保存页面上展示的分页数字的数组）
	public Page() {
	}
	public Page(Integer cp, Integer allPages) {
		super();
		this.cp = cp;
		this.allPages = allPages;
	}
	public Integer getCp() {
		return cp;
	}
	public void setCp(Integer cp) {
		this.cp = cp;
	}
	public Integer getAllPages() {
		return allPages;
	}
	public void setAllPages(Integer allPages) {
		this.allPages = allPages;
	}
	public void setPages(int[] pages) {
		this.pages = pages;
	}
	/**
	 * 判断当前页是否是第一页
	 * @return
	 */
	public boolean isFirst(){
		return this.cp==1;
	}
	/**
	 * 判断当前页是否是最后一页
	 * @return
	 */
	public boolean isLast(){
		return this.cp==allPages;
	}
	/**
	 * 判断当前页的前面是否还有三页或三页以上
	 * @return
	 */
	public boolean isHasPre(){
		return this.cp>3;
	} 
	/**
	 * 判断当前页的后面是否还有三页或三页以上
	 * @return
	 */
	public boolean isHasNext(){
		return this.cp<allPages-2;
	}
	/**
	 * 生成分页数字的方法
	 * @return
	 */
	public int[] getPages() {
		int start=this.cp-2;//决定了生成当前页之间的分页数字
		int end=this.cp+2;//决定了生成当前页后面的分页数字
		if(start<1){
			start=1;
		}
		if(end>=this.allPages){
			end=allPages;
		}
		int len=end-start+1;//计算出生成的分页数字的个数
		this.pages=new int[len];
		for (int i = 0; i < len; i++) {
			pages[i]=start++;
		}
		return pages;
	}
	@Override
	public String toString() {
		return "Page [cp=" + cp + ", allPages=" + allPages + ", pages=" + Arrays.toString(pages) + "]";
	}
	
}
