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
		
		.select_img img { width:500px; margin:20px 0; }
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
			<h2>상품 등록</h2>
			
			<form role="form" method="post" autocomplete="off" enctype="multipart/form-data" >
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
					<input type="text" id="gdsName" name="gdsName" />
				</div>
				<div class="inputArea">
					<label for="gdsPrice">상품가격</label>
					<input type="text" id="gdsPrice" name="gdsPrice" />
				</div>
				<div class="inputArea">
					<label for="gdsStock">상품수량</label>
					<input type="text" id="gdsStock" name="gdsStock" />
				</div>
				<div class="inputArea">
					<label for="gdsDes">상품소개</label>
					<textarea rows="5" cols="50" id="gdsDes" name="gdsDes"></textarea>
				</div>
				<div class="inputArea">
					<label for="gdsImg">이미지</label>
					<input type="file" id="gdsImg" name="file" />
					<div class="select_img">
						<img src="">
					</div>
					<script type="text/javascript">
						$("#gdsImg").change(function(){
							console.log("[this.files]");
							console.log(this.files);
							console.log("[this.files[0]");
							console.log(this.files[0]);
							console.log("[#gdsImg]");
							console.log($("#gdsImg").val());
							if(this.files && this.files[0]){
								var reader = new FileReader;
								console.log("[reader]");
								console.log(reader);
								reader.onload = function(data){
									$(".select_img img").attr("src", data.target.result).width(500);
									console.log("[data]");
									console.log(data);
								}
								reader.readAsDataURL(this.files[0]);
								console.log("[reader]");
								console.log(reader);
							}
						});
					</script>
					<!-- 현재 프로젝트의 실제 경로를 표시, 스프링 파일이 저장되는 워크스페이스와 다르므로, 파일을 저장할 때 실제 경로를 알아야 함 -->
					<%=request.getRealPath("/") %>
				</div>
				<div class="inputArea">
					<button type="submit" class="btn btn-primary" id="btnRegister">등록</button>
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
			//cate2Select.append("<option value=''>전체</option>");
			cate2Select.append("<option value='" + selectVal + "'>전체</option>");

			/* 
			for(var i = 0; cate2Arr.length; i++){
				if(selectVal == cate2Arr[i].cateCodeRef) {
					cate2Select.append("<option value='" + cate2Arr[i].cateCode + "'>" + cate2Arr[i].cateName + "</option>");
				}
			}
			 */
			// uncaught typeerror : cannot read property 해결을 위해 for문 대신 forEach문 사용
			cate2Arr.forEach(function(cate2Arr){
				if(selectVal == cate2Arr.cateCodeRef) {
					cate2Select.append("<option value='" + cate2Arr.cateCode + "'>" + cate2Arr.cateName + "</option>");
				}
			});
		});
	});
</script>
</body>
</html>
