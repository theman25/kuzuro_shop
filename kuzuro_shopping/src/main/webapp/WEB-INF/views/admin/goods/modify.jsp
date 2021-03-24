<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			<h2>상품 수정</h2>
			
			<form role="form" method="post" autocomplete="off">
				<div class="inputArea">
				<label>1차 분류</label>
					<select class="category1">
						<option value="">전체</option>
					</select>
					
					<label>2차 분류</label>
					<select class="category2" name="cateCode">
						<option value="">전체</option>
					</select>
				</div>
				<div class="inputArea">
					<label for="gdsName">상품명</label>
					<input type="text" id="gdsName" name="gdsName" value="${goods.gdsName}" />
				</div>
				<div class="inputArea">
					<label for="gdsPrice">상품가격</label>
					<input type="text" id="gdsPrice" name="gdsPrice" value="${goods.gdsPrice}" />
				</div>
				<div class="inputArea">
					<label for="gdsStock">상품수량</label>
					<input type="text" id="gdsStock" name="gdsStock" value="${goods.gdsStock}" />
				</div>
				<div class="inputArea">
					<label for="gdsDes">상품소개</label>
					<textarea rows="5" cols="50" id="gdsDes" name="gdsDes">${goods.gdsDes}</textarea>
				</div>
				<div class="inputArea">
					<input type="hidden" id="gdsNum" name="gdsNum" value="${goods.gdsNum}" />
					<button type="submit" id="btnUpdate" class="btn btn-primary">완료</button>
					<button type="button" id="btnCancle" class="btn btn-warning">취소</button>
					<script type="text/javascript">
						$("#btnCancle").click(function(){
							// history.back();
							location.href = "/admin/goods/view?n=" + ${goods.gdsNum};
						});
					</script>
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
	// 컨트롤러에서 데이터 받기
	var jsonData = JSON.parse('${category}');	// "" 쌍따옴표  -> 에러남
	console.log("[jsonData]");
	console.log(jsonData);
	
	var cate1Arr = new Array();
	var cate1Obj = new Object();
	
	// 1차 분류 셀렉트 박스에 삽입할 데이터 준비
	for(var i = 0; i < jsonData.length; i++){
		if(jsonData[i].level == "1"){
			cate1Obj = new Object();	// 초기화
			cate1Obj.cateCode = jsonData[i].cateCode;
			cate1Obj.cateName = jsonData[i].cateName;
			cate1Arr.push(cate1Obj);
		}
	}
	console.log("[cate1Arr]");
	console.log(cate1Arr);
	
	var cate1Select = $("select.category1");
	
	for(var i = 0; i < cate1Arr.length; i++){
		cate1Select.append("<option value='" + cate1Arr[i].cateCode + "'>" + cate1Arr[i].cateName + "</option>");
	}
	
	$(document).on("change", "select.category1", function(){
		var cate2Arr = new Array();
		var cate2Obj = new Object();
		
		// 2차 분류 셀렉트 박스에 삽입할 데이터 준비
		for(var i = 0; i < jsonData.length; i++){
			if(jsonData[i].level == "2"){
				cate2Obj = new Object();	// 초기화
				cate2Obj.cateCode = jsonData[i].cateCode;
				cate2Obj.cateName = jsonData[i].cateName;
				cate2Obj.cateCodeRef = jsonData[i].cateCodeRef;
				cate2Arr.push(cate2Obj);
			}
		}
		console.log("[cate2Arr]");
		console.log(cate2Arr);
		
		var cate2Select = $("select.category2");
		/*
		for(var i = 0; i < cate2Arr.length; i++){
			cate2Select.append("<option value='" + cate2Arr[i].cateCode + "'>" + cate2Arr[i].cateName + "</option>");
		}
		*/
		cate2Select.children().remove();
		
		$("option:selected", this).each(function(){
			var selectVal = $(this).val();
			cate2Select.append("<option value='" + selectVal + "'>전체</option>");
	
			// uncaught typeerror : cannot read property 해결을 위해 for문 대신 forEach문 사용
			cate2Arr.forEach(function(cate2Arr){
				if(selectVal == cate2Arr.cateCodeRef) {
					cate2Select.append("<option value='" + cate2Arr.cateCode + "'>" + cate2Arr.cateName + "</option>");
				}
			});
		});
	});
	
	// 이제 카테고리의 데이터를 GoodsViewVO에 가져오므로
	var select_cateCode = '${goods.cateCode}';	// 쌍따옴표는 에러남
	var select_cateCodeRef = '${goods.cateCodeRef}' == null ? '': '${goods.cateCodeRef}' ;
	var select_cateName = '${goods.cateName}';
	
	console.log("[select_cateCodeRef]");
	console.log(select_cateCodeRef);
	console.log("[select_cateCode]");
	console.log(select_cateCode);
	console.log("[select_cateName]");
	console.log(select_cateName);
	
	if(select_cateCodeRef != ''){
		$(".category1").val(select_cateCodeRef);	// 1차 분류 선택
		$("select.category1").trigger("change");	// 1차 분류 변경 이벤트 강제 발생
		$(".category2").val(select_cateCode).attr("selected", "seleceted");	// 2차 분류 선택
	} else {
		$(".category1").val("");	// 1차 분류 선택
		$(".category2").val("");	// 2차 분류 선택
	}
</script>
</body>
</html>
