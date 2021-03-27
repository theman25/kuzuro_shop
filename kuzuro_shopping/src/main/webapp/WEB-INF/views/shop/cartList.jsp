<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>kuzuro shop</title>
	
	<style type="text/css">
		body { margin:0; padding:0; font-family:'맑은 고딕', verdana; }
		a { color:#05f; text-decoration:none; }
		a:hover { text-decoration:underline; }
		
		h1, h2, h3, h4, h5, h6 { margin:0; padding:0; }
		ul, lo, li { margin:0; padding:0; list-style:none; }
		
		/*-------------------------------*/
		
		div#root { width:900px; margin:0 auto; }
		header#header { }
		nav#nav { }
		section#container { }
		section#content { float:right; width:700px; }
		aside#aside { float:left; width:180px; }
		section#container::after { content:""; display:block; clear:both; } 
		footer#footer { background:#eee; padding:20px; }
		
		/*-------------------------------*/
		
		header#header div#header_box { text-align:center; padding:30px 0; }
		header#header div#header_box h1 { font-size:50px; }
		header#header div#header_box h1 a { color:#000; }
		
		nav#nav div#nav_box { font-size:14px; padding:10px; text-align:right; }
		nav#nav div#nav_box li { display:inline-block; margin:0 10px; }
		nav#nav div#nav_box li a { color:#333; }
		
		section#container { }
		
		aside#aside h3 { font-size:22px; margin-bottom:20px; text-align:center; }
		aside#aside li { font-size:16px; text-align:center; }
		aside#aside li a { color:#000; display:block; padding:10px 0; }
		aside#aside li a:hover { text-decoration:none; background:#eee; }

		aside#aside li { position:relative; }
		aside#aside li:hover { background:#eee; }   
		aside#aside li > ul.low { display:none; position:absolute; top:0; left:180px;  }
		aside#aside li:hover > ul.low { display:block; }
		aside#aside li:hover > ul.low li a { background:#eee; border:1px solid #eee; }
		aside#aside li:hover > ul.low li a:hover { background:#fff;}
		aside#aside li > ul.low li { width:180px; }

		footer#footer { margin-top:100px; border-radius:50px 50px 0 0; }
		footer#footer div#footer_box { padding:0 20px; }	
	</style>
	<style type="text/css">
		/*
		section#content ul li { display:inline-block; margin:10px; }
		section#content div.goodsThumb img { width:200px; height:200px; }
		section#content div.goodsName { padding:10px 0; text-align:center; }
		section#content div.goodsName a { color:#000; }
		*/
		section#content ul li { margin:10px 0; }
		section#content ul li img { width:250px; height:250px; }
		section#content ul li::after { content:""; display:block; clear:both; }
		section#content div.thumb { float:left; width:250px; }
		section#content div.gdsInfo { float:right; width:calc(100% - 270px); }
		section#content div.gdsInfo { font-size:20px; line-height:2; }
		section#content div.gdsInfo span { display:inline-block; width:100px; font-weight:bold; margin-right:10px; }
		section#content div.gdsInfo .delete { text-align:right; }
		section#content div.gdsInfo .delete button { font-size:22px; padding:5px 10px; border:1px solid #eee; background:#eee;}
	</style>
</head>
<body>
<div id="root">
	<header id="header">
		<div id="header_box">
			<%@ include file="../include/header.jsp" %>
		</div>
	</header>
	
	<nav id="nav">
		<div id="nav_box">
			<%@ include file="../include/nav.jsp" %>
		</div>
	</nav>
		
	<section id="container">
		<aside id="aside">
			<div id="aside_box">
				<%@ include file="../include/aside.jsp" %>
			</div>
		</aside>
			
		<div id="container_box">
			<section id="content">
				<h2>장바구니 목록</h2>
				
				<ul>
					<c:forEach items="${cartList}" var="cart">
						<li>
							<div class="thumb">
								<%-- <a href="/shop/view?n=${cart.gdsNum}"> --%>
									<img src="${cart.gdsThumbImg}" />
								<!-- </a> -->
							</div>
							<div class="gdsInfo">
								<p>
									<span>상품명 :</span>${cart.gdsName}<br />
									<span>개당 가격 : </span><fmt:formatNumber value="${cart.gdsPrice}" pattern="###,###,###" /> 원<br />
									<span>구입 수량 : </span>${cart.cartStock} 개<br />
									<span>최종 가격 : </span><fmt:formatNumber value="${cart.gdsPrice * cart.cartStock}" pattern="###,###,###" /> 원
								</p>
								
								<div class="delete">
									<button type="button" class="btnDelete">삭제</button>
								</div>
							</div>
						</li>
					</c:forEach>
				</ul>
			</section>
		</div>
	</section>
	
	<footer id="footer">
		<div id="footer_box">
			<%@ include file="../include/footer.jsp" %>
		</div>
	</footer>
</div>
</body>
</html>
