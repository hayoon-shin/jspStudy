<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="co.kh.dev.home.model.ProductDAO"%>
<%@page import="co.kh.dev.home.model.ProductVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>쇼핑하기</title>
<script src="https://kit.fontawesome.com/6ff644124c.js"
	crossorigin="anonymous"></script>
<%@ include file="/home/css/commonCss.jsp"%>
<link rel="stylesheet"
	href="<c:url value='/home/shop/css/shopPage.css' />" />

<c:if test="${alertFlag}">
	<script>
alert("${msg}");
window.location.replace("<c:url value='/shopSelect.do?type=${param.type} ' />");
</script>
</c:if>
</head>

<body>
	<header>
		<nav class="headerNav">
			<%@ include file="/home/headerNavSection.jsp"%>
		</nav>
	</header>

	<main class="shopPage">
		<header class="shopHeader">
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

		</header>

		<!-- 검색 기능 -->
		<section class="searchBar">
			<form method="post" action="<c:url value='/shopFindSelect.do?type=${param.type}' />">
				<label for="searchCriteria">검색 기준:</label> <select
					name="searchCriteria" id="searchCriteria">
					<option value="title">상품명</option>
				</select> <input type="text" name="findText" id="searchKeyword"
					placeholder="검색어를 입력하세요" required>
				<button type="submit" class="searchButton">검색</button>
			</form>	
		</section>

		<!-- 상품 목록 -->
		<section class="shopItems">
			<div class="itemsContainer">
				<c:set var="count" value="1" />

				<c:forEach var="product" items="${sList}" varStatus="status">
					<div class="productItem">
						<a
							href="<c:url value='/shopListSelect.do?no=${product.no}&type=${param.type}' />">
							<img src="<c:url value='${product.titleUrl}' />"
							alt="${product.name}" class="productImg">
							<div class="productInfo">
								<h2>${product.title}</h2>
								<p>
									<fmt:formatNumber value="${product.price}" type="currency" />
								</p>
							</div>
						</a>
					</div>
					<c:set var="count" value="${count + 1}" />
				</c:forEach>
			</div>
		</section>

		<!-- 페이지 네비게이션 -->
		<section class="pagination">
			<ul>
				<li><i class="fa-solid fa-angles-left"
					onclick="location.href='<c:url value="/shopSelect.do?pageNum=1&type=${param.type}" />'"></i>
				</li>
				<li><i class="fa-solid fa-angle-left"
					onclick="location.href='<c:url value="/shopSelect.do?pageNum=${pageNum - 1 < 1 ? 1 : pageNum - 1}&type=${param.type}" />'"></i>
				</li>

				<c:forEach var="i" begin="${pageStartNum}" end="${pageEndNum}">
					<li class="${pageNum == i ? 'active' : ''}"
						onclick="location.href='<c:url value="/shopSelect.do?pageNum=${i}&type=${param.type}" />'">${i}</li>
				</c:forEach>

				<li><i class="fa-solid fa-angle-right"
					onclick="location.href='<c:url value="/shopSelect.do?pageNum=${pageNum + 1 > pageCount ? pageCount : pageNum + 1}&type=${param.type}" />'"></i>
				</li>
				<li><i class="fa-solid fa-angles-right"
					onclick="location.href='<c:url value="/shopSelect.do?pageNum=${pageCount}&type=${param.type}" />'"></i>
				</li>
			</ul>
		</section>
		<form action="#" method="get" name="productInsert.do" align="right">
			<c:if test="${sessionScope.cvo.id == 'admin'}">
				<button type="button" id="writeButton"
					onclick="location.href='<c:url value='/shopInsertPageSelect.do?type=${param.type}'/>'">상품
					추가</button>
			</c:if>
		</form>
	</main>
<br>
	<hr>
	<footer>
		<%@ include file="/home/footerSection.jsp"%>
	</footer>
	<script src="<c:url value='/home/js/common.js' />"></script>
</body>
</html>
