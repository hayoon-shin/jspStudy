<%@page import="co.kh.dev.common.MyUtility"%>
<%@page import="co.kh.dev.home.model.CustomerVO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<div class="headerNav">
      <i class="fa-solid fa-cat" onClick="location.href='${pageContext.request.contextPath}/mainPageAlert.do'"></i>
	<ul class="mainMenu">
		<li data-menu="three">상품 보기</li>
		<li data-menu="four">고객 지원</li>
		<c:if test="${cvo.id=='admin'}">
		<li data-menu="five">관리자메뉴</li>
		</c:if>
	</ul>

 <c:if test="${empty cvo}">
	<button class="Button" class="authButton" onclick="openLoginPopup();">로그인</button>
</c:if>
<c:if test="${not empty cvo}">
	<button class="Button" class="authButton" onClick="location.href='${pageContext.request.contextPath}/customerLogout.do'">로그아웃</button><i class="fa-solid fa-cart-shopping" onClick="window.location.href='/jspHomePage/shopCartSelect.do'"></i>

</c:if>
</div>
<div class="subMenuContainer">

	<ul class="subMenu" data-submenu="three">
		<a href="${pageContext.request.contextPath}/shopSelect.do?type=1"><li>고양이 용품</li></a>
		<a href="${pageContext.request.contextPath}/shopSelect.do?type=2"><li>강아지 용품</li></a>
		<a href="${pageContext.request.contextPath}/shopSelect.do?type=3"><li>기타 제품</li></a>
	</ul>
	<ul class="subMenu" data-submenu="four">
		<a href="${pageContext.request.contextPath}/boardSelect.do"><li>게시판</li></a>
		<a href="${pageContext.request.contextPath}/noticeSelect.do"><li>공지사항</li></a>
	</ul>
	<c:if test="${cvo.id=='admin'}">
	<ul class="subMenu" data-submenu="five">
		
		<a href="${pageContext.request.contextPath}/productSelect.do"><li>제품 추가</li></a>
		
	</ul>
	</c:if>
</div>
<script src="${pageContext.request.contextPath}/home/js/headerNav.js"></script>
<script src="${pageContext.request.contextPath}/home/js/common.js"></script>