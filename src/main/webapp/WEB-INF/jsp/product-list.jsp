<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<html>
<head>
   <base  href="/">
	<link rel="stylesheet" href="css/mall/normalize.css">
	<link rel="stylesheet" href="css/mall/common.css">
	<link rel="stylesheet" href="css/mall/header.css">
	<link rel="stylesheet" href="css/mall/footer.css">
	<link rel="stylesheet" href="css/mall/product-list.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
<style type="text/css">
	   .container   a  span{
	       margin-top:4px;
		   color:red;
		   font-size:18px;
	   }
	</style>
</head>
<body>
	<div id="adv-banner">
		<div class="container">
<!--			<img src="images/adv_banner.jpg">-->
		</div>
	</div>
	<div id="site-nav">
		<div class="container">
			<p style="margin-left:450px;">
<!--				<a  href="cart/list.action" target="_top">-->
<!--			    	<span class="fa fa-shopping-cart"></span>-->
<!--				</a>-->
			</p>
		</div>
	</div>
	<div id="header">
		<div class="container">
<!--			<div class="logo">-->
<!--				<a href="#" title="">-->
<!--					<img src="images/logo-190-27.png">-->
<!--				</a>-->
<!--			</div>-->
			<div class="search-box">
			<!-- target="_top"  在整个窗口中打开被链接文档。 -->
				<form action="product/list">
					<div class="search-input">
						<input type="text"  name="kw" placeholder="请输入搜索文字" size="65"><!-- size:宽度 -->
						<button type="submit">搜索<s></s></button>
					</div>
				</form>
			</div>
		</div>
	</div>
   <!-- 为排序进行url地址重组 -->
   <c:url  var="sortUrl"  value="product/list?">
   <!-- 取到前端参数 -->
      <c:forEach  items="${param}" var="item">
         <c:if test="${item.key!='column'&&item.key!='sort'}">
            <c:param name="${item.key}" value="${item.value}"></c:param><!-- product/list.action?kw=item.value -->
         </c:if>
      </c:forEach>
   </c:url>
   <!-- 为分页重组url地址 -->
   <c:url  var="pageUrl" value="product/list?">
     <!--这样写可以把浏览器地址栏的参数按照集合的方式取出来  -->
      <c:forEach  items="${param}" var="item">
            <c:if test="${item.key!='cp'}">
               <c:param name="${item.key}" value="${item.value}"></c:param><!-- product/list.action?kw=item.value&column=item.value -->
            </c:if>
      </c:forEach>
   </c:url>
	<div id="content">
		<div class="container">
			<div class="filter">
				<a class="sort current" href="#">综合<i class="arrow-up"></i></a>
				<a class="sort" href="#">人气<i class="arrow-up"></i></a>
				<a class="sort" href="${sortUrl}column=sales&sort=desc">销量<i class="arrow-up"></i></a>
				<a class="sort" href="${sortUrl}column=price">价格升序<i class="arrow-up"></i></a>
				<a class="sort" href="${sortUrl}column=sales">价格降序<i class="arrow-down"></i></a>
			</div>
			<div class="view-list clearfix">
		  	<c:forEach   items="${map.allList}" var="item">
				<div class="product item">
					<div class="product-iWrap">
						<div class="productImg-wrap">
							<a href="product/one?pid=${item.product_id}">
								<img src="../static/img/upload-files/${item.image}">
							</a>
						</div>
						<p class="productPrice">
							<em title="${item.title }"><b>¥</b>
							<fmt:formatNumber  pattern="0.00" value="${item.price}"></fmt:formatNumber>
							</em>
						</p>
						<p class="productTitle">
						<!-- target="_blank"  在新窗口中打开被链接文档。 -->
							<a href="#" target="_blank" title="${item.title}">
								<span class="H" style="font-size:10px">${item.title}</span>
							</a>
						</p>
						<div class="productShop">
							<a class="productShop-name" href="#" target="_blank">${item.shop_name}</a>
						</div>
						<p class="productStatus">
							<span>月成交 <em>${item.sales}笔</em></span>
							<span>评价 <a href="#" target="_blank">${item.comments}</a></span>
						</p>
					</div>
    				 </div>
		          </c:forEach>
			</div>
		</div>
		<div class="ui-page clear">
			<div class="ui-page-wrap">
				<b class="ui-page-num">
				<!--通过反射取到的Page中的isFirst方法  -->
					 <c:if test="${map.page.first}">
	   					<b class="ui-page-prev">&lt;&lt;上一页</b>
					 </c:if>
					 <c:if test="${!map.page.first}">
					    <a class="ui-page-prev" href="${pageUrl}cp=${map.page.cp-1}">&lt;&lt;上一页</a>
					 </c:if>
					 <!--判断是否生成省略号  -->
					 <c:if test="${map.page.hasPre}">
				          <b class="ui-page-break">...</b>
					 </c:if>
					 <!-- 生成分页数字 -->
					 <c:forEach items="${map.page.pages}" var="page">
					   <c:if test="${map.page.cp==page}">
					       <b class="ui-page-cur">${page}</b>
					   </c:if>
					   <c:if test="${map.page.cp!=page}">
					       <a href="${pageUrl}cp=${page}">${page}</a>
					   </c:if>
					 </c:forEach>
					 <!-- 判断后面是否生成省略号 -->
					 <c:if test="${map.page.hasNext}">
				          <b class="ui-page-break">...</b>
					 </c:if>
					<c:if test="${map.page.last}">
					     <b class="ui-page-next">下一页&gt;&gt;</b>
					</c:if>
					<c:if test="${!map.page.last}">
						<a class="ui-page-next" href="${pageUrl}cp=${map.page.cp+1}">下一页&gt;&gt;</a>
					</c:if>
				</b>
				<b class="ui-page-skip">
					<form name="filterPageForm" method="get">
						共${map.allpages}页,到第<input type="text" name="cp" class="ui-page-skipTo"
						 size="3" value="${map.page.cp}"/>页
						 <button type="submit" class="ui-btn-s">确定</button>
					</form>
				</b>
			</div>
		</div>
	</div>
	<div id="footer">
		<div class="container">
<!--		<img src="images/ensure.jpg">-->
		</div>
	</div>
</body>

</html>