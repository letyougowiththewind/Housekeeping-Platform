<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<html>
<head>
    <base href="/">
	<link rel="stylesheet" href="css/mall/normalize.css">
	<link rel="stylesheet" href="css/mall/common.css">
	<link rel="stylesheet" href="css/mall/header.css">
	<link rel="stylesheet" href="css/mall/footer.css">
	<link rel="stylesheet" href="css/mall/product.css">
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<!-- type="text/javascript"  -->
	<script  src="js/jquery.min.js"></script>
	<script  src="js/cart.js"></script>
	<style type="text/css">
		 .container   a  span{
	       		margin-top:4px;
			    color:red;
		   		font-size:18px;
		   }
	</style>	
</head>
<body>
<%--	<div id="adv-banner">--%>
<%--		<div class="container">--%>
<%--			<img src="images/adv_banner.jpg">--%>
<%--		</div>--%>
<%--	</div>--%>
	<div id="site-nav">
<%--		<div class="container">--%>
<%--			<p><a href="prolist">天猫首页</a></p>--%>
<%--			<p><em>喵，欢迎${user.username}来天猫</em></p>--%>
<%--			<c:if test="${user.username==null}">--%>
<%--				<p><a class="sn-login" href="pages/login.jsp" target="_top">请登录</a></p>--%>
<%--			</c:if>--%>
<%--			<c:if test="${user.username==null}">--%>
<%--				<p><a class="sn-register" href="register.jsp" target="_top">免费注册</a></p>--%>
<%--			</c:if>--%>
<%--			<p style="margin-left:450px;">--%>
<%--				<a  href="cart/list.action" target="_top">--%>
<%--			    	<span class="fa fa-shopping-cart"></span>--%>
<%--				</a>--%>
<%--			</p>--%>
<%--		</div>--%>
	</div>
	<div id="header">
		<div class="container">
<%--			<div class="logo">--%>
<%--				<a href="#" title="">--%>
<%--					<img src="images/logo-190-27.png">--%>
<%--				</a>--%>
<%--			</div>--%>
			<div class="search-box">
				<form action="" target="_top">
					<div class="search-input">
						<input type="text" placeholder="请输入搜索文字" size="65">
						<button type="submit">搜索<s></s></button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div id="content">
		<div class="container">
		<!-- 控制层的product -->
			<div class="product-detail">
				<div class="gallery">
					<img src="img/upload-files/${product.image}">
				</div>
				<div class="property">
					<h1 class="title">	
					  ${prodcut.title}
					</h1>
					<div class="fcs-panel">
						<div class="coupon-panel">
							<img height="16" src="img/coupon.png">天猫实物商品通用 <a target="_blank">积分刮券</a></div>
						<dl class="price-panel">
							<dt class="meta">价格</dt>
							<dd><em class="yen">¥</em> <span class="price">
							<fmt:formatNumber  pattern="0.00"  value="${product.price}">
							</fmt:formatNumber>
							</span></dd>
						</dl>
						<dl class="promo-panel" data-label="促销价">
							<dt class="meta">促销价</dt>
							<dd><em class="yen">¥</em><span class="price">
							<fmt:formatNumber  pattern="0.00"  value="${product.price*0.8}">
							</fmt:formatNumber>
							</span></dd>
						</dl>
					</div>
					<dl class="delivery-panel"><dt class="meta">运费</dt>
						<dd>上海&nbsp;至&nbsp;深圳&nbsp;快递: 0.00</dd>
					</dl>
					<ul class="ind-panel">
						<li><span class="label">月销量</span><span class="count">${product.sales}</span></li>
						<li><span class="label">累计评价</span><span class="count">${product.comments}</span></li>
						<li><span class="label">送天猫积分</span><span class="count">1299</span></li>
					</ul>
					<dl class="amount-panel">
						<dt class="meta">数量</dt>
						<dd>
							<input type="text" value="1" pid="${product.product_id}"  maxlength="8">
							<span class="unit">件</span>
						</dd>
					</dl>
					<div class="action-panel">
						<div class="btn-buy"><a href="#">立即购买</a>
						</div>
						<!--javascript:void(0)   死链接  -->
						<div class="btn-basket"><a href="javascript:void(0)">加入购物车</a></div>
					</div>
				</div>
				<div class="aside-right">
					<div class="title "><s></s><span>看了又看</span></div>
					<ul class="content">
						<li>
							<div class="img">
								<a title="${product.title}" href="#" target="_blank">
									<img width="140" height="140" src="img/upload-files/${product.image}">
								</a>
								<p class="look_price">${product.price}</p>
							</div>
						</li>
						<li>
							<div class="img">
								<a title="${product.title}" href="#" target="_blank">
									<img width="140" height="140" src="img/upload-files/${product.image}">
								</a>
								<p class="look_price">${product.price}</p>
							</div>
						</li>						
					</ul>
				</div>

			</div>
		</div>
	</div>
<%--	<div id="footer">--%>
<%--		<div class="container">--%>
<%--			<img src="images/ensure.jpg">--%>
<%--		</div>--%>
<%--	</div>--%>
</body>

</html>