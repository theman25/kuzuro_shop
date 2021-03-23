<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul>
	<c:if test="${member != null}">
		<li>[관리자] ${member.userName} 님 환영합니다.</li>
		<li><a href="/">일반 화면</a></li>
		<li><a href="/member/signout">로그아웃</a></li>
	</c:if>
</ul>