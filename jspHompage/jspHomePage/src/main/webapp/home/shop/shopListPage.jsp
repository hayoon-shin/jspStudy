<%@page import="co.kh.dev.home.model.ProductVO"%>
<%@page import="co.kh.dev.home.model.ProductDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>상품 등록</title>
<script src="https://kit.fontawesome.com/6ff644124c.js"
	crossorigin="anonymous"></script>
<%@ include file="/home/css/commonCss.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/home/shop/css/shopListPage.css" />
<c:if test="${empty svo}">
	<c:redirect url="/mainPageAlert.do?status=8" />
</c:if>
</head>
<body>
	<header>
		<nav class="headerNav">
			<%@ include file="/home/headerNavSection.jsp"%>
		</nav>
	</header>
	<main class="shopPage">
		<div align="center">
			<c:choose>
				<c:when test="${param.type == 1}">
					<h1>고양이 용품</h1>
				</c:when>
				<c:when test="${param.type == 2}">
					<h1>강아지 용품</h1>
				</c:when>
				<c:when test="${param.type == 3}">
					<h1>기타 제품</h1>
				</c:when>
				<c:otherwise>
					<h1>알 수 없는 상품 유형</h1>
				</c:otherwise>
			</c:choose>
		</div>
		<!-- "목록으로 돌아가기" 버튼 -->
		<div class="backButton">
			<button type="button"
				onclick="location.href='<c:url value='/shopSelect.do?type=${param.type}' />'">목록으로</button>
		</div>
		<c:if test="${cvo.id=='admin'}">
			<div class="backButton">
				<button type="button"
					onclick="location.href='<c:url value='/shopListDelete.do?no=${param.no}&type=${param.type}' />'">삭제하기</button>
			</div>
		</c:if>
		<!-- 상품 정보 섹션 -->
		<section class="productInfoSection">
			<!-- 타이틀 이미지 -->
			<div class="titleImage">
				<img src="<c:url value='${svo.titleUrl}' />" alt="${svo.title}">
			</div>

			<!-- 기본 정보 -->
			<div class="basicInfo">
				<h2>${svo.title}</h2>
				<p class="price">
					<fmt:formatNumber value="${svo.price}" type="currency" />
				</p>

				<!-- 장바구니 및 액션 버튼 -->
				<c:if test="${not empty cvo}">
					<form action="/jspHomePage/shopCartInsert.do">
						<div class="cartSection">
							<label for="quantity">구매 수량:</label> <input type="number"
								id="quantity" name="qt" min="1" value="1">
						</div>
						<div class="actionButtons">
							<input value="${svo.no}" type="hidden" name="no"> <input
								value="${svo.type}" type="hidden" name="type">
							<button type="submit">장바구니에 담기</button>
							<button type="button">바로 구매하기</button>
						</div>
					</form>
				</c:if>
			</div>
		</section>

		<!-- 상품 이미지 섹션 -->
		<section class="productImages">
			<h3>상품 이미지</h3>
			<c:forEach var="img" items="${siList}">
				<div class="contentImage">
					<img src="<c:url value='${img.url}' />" alt="상품 이미지">
				</div>
			</c:forEach>
		</section>

		<!-- 상품 설명 섹션 -->
		<section class="productDescription">
			<h3>상품 설명</h3>
			<p>${svo.content}</p>
		</section>
	</main>


	<br>
	<hr>
	<footer>
		<%@ include file="/home/footerSection.jsp"%>
	</footer>
	<script src="${pageContext.request.contextPath}/home/js/common.js"></script>
	<script
		src="${pageContext.request.contextPath}/home/shop/js/shopInsertPage.js"></script>
</body>
</html>
