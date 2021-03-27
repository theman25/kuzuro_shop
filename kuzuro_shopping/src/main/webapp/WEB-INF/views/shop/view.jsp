<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- <%@ page session="false" %> --%>
<html>
<head>
	<title>kuzuro shop</title>
	
	<script src="/resources/jquery/jquery-3.3.1.min.js"></script>
	
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
		div.goods div.goodsImg { float:left; width:350px; }
		div.goods div.goodsImg img { width:350px; height:auto; }
		
		div.goods div.goodsInfo { float:right; width:330px; font-size:22px; }
		div.goods div.goodsInfo p { margin:0 0 20px 0; }
		div.goods div.goodsInfo p span { display:inline-block; width:100px; margin-right:15px; }
		
		div.goods div.goodsInfo p.cartStock input { font-size:22px; width:50px; padding:5px; margin:0; border:1px solid #eee; }
		div.goods div.goodsInfo p.cartStock button { font-size:26px; border:none; background:none; }
		div.goods div.goodsInfo p.addToCart { text-align:right; }
		div.goods div.gdsDes { font-size:18px; clear:both; padding-top:30px; }
	</style>
	
	<style type="text/css">
		section.replyForm { padding:30px 0; }
		section.replyForm div.input_area { margin:10px 0; }
		section.replyForm textarea { font-size:16px; font-family:'맑은 고딕', verdana; padding:10px; width:500px;; height:150px; }
		section.replyForm button { font-size:20px; padding:5px 10px; margin:10px 0; background:#fff; border:1px solid #ccc; }
		
		section.replyList { padding:30px 0; }
		section.replyList ol { padding:0; margin:0; }
		section.replyList ol li { padding:10px 0; border-bottom:2px solid #eee; }
		section.replyList div.userInfo { }
		section.replyList div.userInfo .userName { font-size:24px; font-weight:bold; }
		section.replyList div.userInfo .date { color:#999; display:inline-block; margin-left:10px; }
		section.replyList div.replyContent { padding:10px; margin:20px 0; }
		
		section.replyList div.replyFooter { margin:10px;}
		section.replyList div.replyFooter button { font-size:14px; border:1px solid #999; background:none; margin-right:10px; }
	</style>
	
	<style type="text/css">
		div.replyModal { position:relative; z-index:1; display:none;}
		div.modalBackground { position:fixed; top:0; left:0; width:100%; height:100%; background:rgba(0, 0, 0, 0.8); z-index:-1; }
		div.modalContent { position:fixed; top:20%; left:calc(50% - 250px); width:500px; height:250px; padding:20px 10px; background:#fff; border:2px solid #666; }
		div.modalContent textarea { font-size:16px; font-family:'맑은 고딕', verdana; padding:10px; width:500px; height:200px; }
		div.modalContent button { font-size:20px; padding:5px 10px; margin:10px 0; background:#fff; border:1px solid #ccc; }
		div.modalContent button.modal_cancel { margin-left:20px; }
	</style>
	
	<script type="text/javascript">
		function replyList(){
			var gdsNum = $("#gdsNum").val();
			
			$.getJSON("/shop/view/replyList" + "?n=" + gdsNum, function(data){
				var str = "";
				
				$(data).each(function(){
					console.log("[replyList]");
					console.log(data);
					
					// 날짜 데이터 포맷팅
					var repDate = new Date(this.repDate);
					repDate = repDate.toLocaleDateString("ko-US");
					
					console.log("[repDate]");
					console.log(repDate);
					
					// HTML코드 조립
					str += 	"<li data-repNum='" + this.repNum + "'>"	//"<li data-gdsNum='" + this.gdsNum + "'>"
						+	"<div class='userInfo'>"
						+	"<span class='userName'>" + this.userName + "</span>"
						+	"<span class='date'>" + repDate + "</span>"
						+	"</div>"
						+	"<div class='replyContent'>" + this.repCon + "</div>"
						/* 상품소감 삭제/수정 버튼 */
						+	"<c:if test='${member != null}'>"
						+	"<div class='replyFooter'>"
						+	"<button type='button' class='modify' data-repNum='" + this.repNum + "'>M</button>"
						+	"<button type='button' class='delete' data-repNum='" + this.repNum + "'>D</button>"
						+	"</c:if>"
						
						+	"</li>";
				});
				$("section.replyList ol").html(str);
			}); 
		}
	</script>
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
				<h2>상품 조회</h2>
				
				<form role="form" method="post">
					<input type="hidden" id="gdsNum" name="gdsNum" value="${view.gdsNum}" />
				</form>
				
				<div class="goods">
					<div class="goodsImg">
						<img src="${view.gdsImg}" alt="" />
					</div>
				
					<div class="goodsInfo">
						<p class="gdsName">
							<span>상품명</span>
							${view.gdsName}
						</p>
						<p class="cateName">
							<span>카테고리</span>
							${view.cateName}
						</p>
						<p class="gdsPrice">
							<span>가격</span>
							<fmt:formatNumber value="${view.gdsPrice}" pattern="###,###,###"/> 원
						</p>
						<p class="gdsStock">
							<span>재고</span>
							<fmt:formatNumber value="${view.gdsStock}" pattern="###,###,###"/> EA
						</p>
						<p class="cartStock">
							<span>구입 수량</span>
							<!-- <input type="number" min="1" max="${view.gdsStock}" value="1" /> -->
							<button type="button" class="minus">-</button>
							<input type="number" class="numBox" min="1" max="${view.gdsStock}" value="1" readonly="readonly"/>
							<button type="button" class="plus">+</button>
							
							<script>
								$(".plus").click(function(){
									var num = $(".numBox").val();
									var plusNum = Number(num) + 1;
									var gdsStock = '${view.gdsStock}';
									
									if(plusNum > gdsStock) {
										$(".numBox").val(num);
									} else {
										$(".numBox").val(plusNum);
									}
								});
								
								$(".minus").click(function(){
									var num = $(".numBox").val();
									var minusNum = Number(num) - 1;
									
									if(minusNum <= 0) {
										$(".numBox").val(num);
									} else {
										$(".numBox").val(minusNum);
									}
								});
							</script>
						</p>
						<p class="addToCart">
							<button type="button" class="btnAddCart">카트에 담기</button>
							
							<script type="text/javascript">
								$(".btnAddCart").click(function(){
									var gdsNum = $("#gdsNum").val();
									var cartStock = $(".numBox").val();
									
									console.log("gdsNum : " + gdsNum);
									console.log("cartStock : " + cartStock);
									
									// cartVO 형태로 데이터 생성
									var data = {
											gdsNum : gdsNum,
											cartStock : cartStock
									};
									
									$.ajax({
										url : "/shop/view/addCart",
										type : "post",
										data : data,
										success : function(result){
											if(result == 1){
												alert("카트에 담기 성공");
												$(".numBox").val("1");
											} else {
												alert("회원만 사용 할 수 있습니다.");
												$(".numBox").val("1");
											}
										},
										error : function(){
											alert("카트에 담기 실패");	// 세션이 다 되어서 로그인이 풀렸거나, 로그인을 안한 사용자
										}
									});
								});
							</script>
						</p>
					</div>
					
					<div class="gdsDes">
						${view.gdsDes}
					</div>
					
					<!-- --------------------------------------------------------------------------------------- -->
					<!-- 상품소감(댓글) -->
					<div id="reply">
						<c:if test="${member == null}">
							<p>소감을 남기시려면 <a href="/member/signin">로그인</a>해주세요</p>
						</c:if>
						
						<c:if test="${member != null}">
							<section class="replyForm">
								<form role="form" method="post" autocomplete="off">
									<input type="hidden" id="gdsNum" name="gdsNum" value="${view.gdsNum}" />
									<div class="input_area">
										<textarea rows="5" cols="50" name="repCon" id="repCon"></textarea>
									</div>
									<div class="input_area">
										<button type="button" id="btnReply">소감 남기기</button>
									</div>
								</form>
							</section>
						</c:if>
						
						<script>
							$("#btnReply").click(function(){
								var formObj = $(".replyForm form[role='form']");
								var gdsNum = $("#gdsNum").val();
								var repCon = $("#repCon").val();
								
								var data = {
										gdsNum : gdsNum,
										repCon : repCon
								};
								$.ajax({
									url : "/shop/view/registReply",
									type : "post",
									data : data,
									success : function(){
										replyList();
										$("#repCon").val("");
									}
								});
							});
						</script>
						<section class="replyList">
							<ol>
								<li>댓글 목록</li>
								<%-- 
								<c:forEach items="${replyList}" var="reply">
									<li>
										<div class="userInfo">
											<span class="userName">${reply.userName}</span>
											<span class="date"><fmt:formatDate value="${reply.repDate}" pattern="yyyy-MM-dd"/></span>
										</div>
										<div class="replyContent">${reply.repCon}</div>
									</li>
								</c:forEach>
								 --%>
							</ol>
							<script type="text/javascript">
								replyList();
							</script>
							<script type="text/javascript">
								// 소감 목록은 스크립트로인해 생성된 동적인 HTML코드로, 일반적인 클릭 메서드 .click() 가 아니라 .on() 메서드를 사용해야합니다.
								$(document).on("click", ".modify", function(){
									//$(".replyModal").attr("style", "display:block;");
									$(".replyModal").fadeIn(200);
									
									var repNum = $(this).attr("data-repNum");
									var repCon = $(this).parent().parent().children(".replyContent").text();
									
									$(".modal_repCon").val(repCon);
									$(".btnModalModify").attr("data-repNum", repNum);
								});
								
								$(document).on("click", ".delete", function(){
									var deleteConfirm = confirm("삭제 하시겠습니까?");
									
									if(deleteConfirm) {
										var data = { repNum : $(this).attr("data-repNum") };
										
										$.ajax({
											url : "/shop/view/deleteReply",
											type : "post",
											data : data,
											success : function(result){
												if(result == 1){
													replyList();
												} else {
													alert("작성자 본인만 삭제 할 수 있습니다.");
												}
											},
											error : function() {
												alert("로그인이 필요합니다.");
											}
										});
									}
								});
							</script>
						</section>
					</div>
					<!-- --------------------------------------------------------------------------------------- -->
					
				</div>
			</section>
		</div>
	</section>
	
	<footer id="footer">
		<div id="footer_box">
			<%@ include file="../include/footer.jsp" %>
		</div>
	</footer>
</div>

<!-- --------------------------------------------------------------------------------------- -->
<!-- 댓글 수정 모달 폼 -->
<div class="replyModal">
	<div class="modalContent">
		<div>
			<textarea class="modal_repCon" name="modal_repCon"></textarea>
		</div>
		<div>
			<button type="button" class="btnModalModify">수정</button>
			<button type="button" class="btnModalCancle">취소</button>
		</div>
	</div>
	<div class="modalBackground"></div>
</div>
<!-- --------------------------------------------------------------------------------------- -->
<script type="text/javascript">
	$(".btnModalModify").click(function(){
		console.log("모달 수정 버튼 클릭");
		
		var modifyConfirm = confirm("수정 하시겠습니까?");
		if(modifyConfirm) {
			console.log("[modifyConfirm]");
			console.log(modifyConfirm);
			
			var data = { repNum : $(this).attr("data-repNum"),
						 repCon : $(".modal_repCon").val()		
					   }
			console.log("[data]");
			console.log(data);
			$.ajax({
				url : "/shop/view/modifyReply",
				type : "post",
				data : data,
				success : function(result) {
					if(result == 1) {
						console.log("success : result = 1");
						replyList();	// 댓글 목록 조회
						$(".replyModal").fadeOut(200);
					} else {
						console.log("success : result = 0");
						alert("작성자 본인만 수정 할 수 있습니다.");		// 작성자 본인이 아닌 경우
					}
				},
				error : function(){
					console.log("error");
					alert("로그인이 필요합니다.");		// 로그인 하지 않아서 에러가 발생한 경우
				}
			});
		}
	});
	
	$(".btnModalCancle").click(function(){
		//$(".replyModal").attr("style", "diplay:none;");
		$(".replyModal").fadeOut(200);
	});
</script>
</body>
</html>
