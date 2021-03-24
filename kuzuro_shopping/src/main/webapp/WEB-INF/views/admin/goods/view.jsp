<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>kuzuro shop</title>
	
	<script src="/resources/jquery/jquery-3.3.1.min.js"></script>
	<script src="/resources/bootstrap/bootstrap.min.js"></script>
	<link rel="stylesheet" href="/resources/bootstrap/bootstrap.min.css">
	<link rel="stylesheet" href="/resources/bootstrap/bootstrap-theme.min.css">	
	
	<style>
		body { font-family:'맑은 고딕', verdana; padding:0; margin:0; }
		ul { padding:0; margin:0; list-style:none;  }
	
		div#root { width:90%; margin:0 auto; }
	 
		header#header { font-size:60px; padding:20px 0; }
		header#header h1 a { color:#000; font-weight:bold; }
	 
		nav#nav { padding:10px; text-align:right; }
		nav#nav ul li { display:inline-block; margin-left:10px; }
	
		section#container { padding:20px 0; border-top:2px solid #eee; border-bottom:2px solid #eee; }
		section#container::after { content:""; display:block; clear:both; }
		aside { float:left; width:200px; }
		div#container_box { float:right; width:calc(100% - 200px - 20px); }
	 
		aside ul li a { display:block; width:100%; padding:10px 0;}
		aside ul li a:hover { background:#eee; }
		aside ul li { text-align:center; margin-bottom:10px; }
	 
		footer#footer { background:#f9f9f9; padding:20px; }
		footer#footer ul li { display:inline-block; margin-right:10px; }
	</style>
	
	<style>
		.inputArea { margin:10px 0; }
		select { width:100px; }
		label { display:inline-block; width:70px; padding:5px; }
		label[for='gdsDes'] { display:block; }
		input { width:150px; }
		textarea#gdsDes { width:400px; height:180px; }
		
		.oriImg { width:500px; height:auto;}
		.thumbImg {}
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
		<aside>
			<%@ include file="../include/aside.jsp" %>
		</aside>
		
		<div id="container_box">
			<h2>상품 상세</h2>
			
			<form role="form" method="post" autocomplete="off">
				<input type="hidden" name="n" value="${goods.gdsNum}" />
				
				<div class="inputArea">
					<label>1차 분류</label>
					<span class="category1"></span>
					
					<label>2차 분류</label>
					<span class="category2">${goods.cateCode}</span>
				</div>
				<div class="inputArea">
					<label for="gdsName">상품명</label>
					<span>${goods.gdsName}</span>
				</div>
				<div class="inputArea">
					<label for="gdsPrice">상품가격</label>
					<span><fmt:formatNumber value="${goods.gdsPrice}" pattern="###,###,###" /></span>
				</div>
				<div class="inputArea">
					<label for="gdsStock">상품수량</label>
					<span><fmt:formatNumber value="${goods.gdsStock}" pattern="###,###,###" /></span>
				</div>
				<div class="inputArea">
					<label for="gdsDes">상품소개</label>
					<span>${goods.gdsDes}</span>
				</div>
				<div class="inputArea">
					<label for="gdsImg">이미지</label>
					<p>원본 이미지</p>
					<img src="${goods.gdsImg}" />
					
					<p>썸네일</p>
					<img src="${goods.gdsThumbImg}" />
				</div>
				<div class="inputArea">
					<button type="submit" id="btnModify" class="btn btn-warning">수정</button>
					<button type="button" id="btnDelete" class="btn btn-danger">삭제</button>
				</div>
			</form>
		</div>
	</section>
	
	<footer id="footer">
		<div id="footer_box">
			<%@ include file="../include/footer.jsp" %>
		</div>
	</footer>
</div>

<script type="text/javascript">
	var formObj = $("form[role='form']");
	
	$("#btnModify").click(function(){
		formObj.attr("action", "/admin/goods/modify");
		formObj.attr("method", "get");
		formObj.submit();
	});
	
	$("#btnDelete").click(function(){
		var con = confirm("삭제하시겠습니까?");
		
		if(con) {
			formObj.attr("action", "/admin/goods/delete");
			//formObj.action = "/admin/goods/delete";	// 안됨
			formObj.submit();
		}
	});
	
</script>
</body>
</html>
