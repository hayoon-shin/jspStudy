<%@page import="oracle.jdbc.internal.XSSessionNamespace"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="co.kh.dev.home.model.CommentDAO"%>
<%@page import="co.kh.dev.home.model.NoticeDAO"%>
<%@page import="co.kh.dev.home.model.NoticeVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>상품 관리</title>
<script src="https://kit.fontawesome.com/6ff644124c.js"
	crossorigin="anonymous"></script>
<%@ include file="/home/css/commonCss.jsp"%>
<link rel="stylesheet"
	href="<c:url value='/home/product/css/productPage.css' />" />
<c:if test="${empty pList && empty findText}">
<c:redirect url="/productPageAlert.do"></c:redirect>
</c:if>
<c:if test="${alertFlag}">
<script>
alert("${msg}");
window.location.replace("<c:url value='/home/product/productPage.jsp' />");
</script>
</c:if>
</head>
<body>
	<header>
		<nav class="headerNav">
			<%@ include file="/home/headerNavSection.jsp"%>
		</nav>
	</header>

	<main class="productPage">
		<header class="productHeader">
			<h1>상품 관리</h1>
		</header>

		<article class="article3">
			<div class="all">
				<form method="get" action="<c:url value='/productSelect.do' />">
					<label for="viewTime">보기 항목 개수:</label> <select name="viewTime"
						id="viewTime">
						<option value="10" ${viewTime == 10 ? "selected" : ""}>10개씩</option>
						<option value="20" ${viewTime == 20 ? "selected" : ""}>20개씩</option>
					</select>
					<button type="submit" id="ProductViewButton">보기</button>
				</form>

				<form method="get" action="<c:url value='/productFindSelect.do' />">
					<label for="find">검색 기준:</label> <select name="findValue" id="find">
						<option value="name">상품명</option>
						<option value="description">설명</option>
					</select> <input type="text" name="findText" id="findText" value="${findText}"/>
					<button type="submit" id="findButton">검색</button>
					<button type="button" id="findButton" onclick="location.href='<c:url value="/productSelect.do?viewTime=10&pageNum=1" />'">검색취소</button>
				</form>
			</div>
		</article>

		<article class="article4">
			<table>
				<tr>
					<th>번호</th>
					<th width="800">상품명</th>
					<th>가격</th>
					<th>수량</th>
					<th>등록일</th>
					<th>관리</th>
				</tr>
				<c:forEach var="pvo" items="${pList}">
					<tr>
						<td class="tbNum">${pvo.rownum}</td>
						<td class="tbMain"><a
							href="<c:url value='/productListSelect.do?no=${pvo.no}&count=1' />">${pvo.name}</a>
						</td>
						<td class="tbPrice">${pvo.price}</td>
						<td class="tbAmount">${pvo.amount}</td>
						<td class="tbDate">${pvo.subdate}</td>
						<td class="tbManage">
							<form action="/jspHomePage/home/product/productListUpdatePage.jsp" 
								style="display: inline;">
								<input type="hidden" name="no" value="${pvo.no}" /> <input
									type="hidden" name="name" value="${pvo.name}" /> <input
									type="hidden" name="price" value="${pvo.price}" /> <input
									type="hidden" name="amount" value="${pvo.amount}" />
								<button type="submit" class="manageBtn editBtn">수정</button>
							</form>
							<form action="<c:url value='/productListDelete.do' />" method="post"
								style="display: inline;">
								<input type="hidden" name="no" value="${pvo.no}" />
								<button type="submit" class="manageBtn deleteBtn"
									onclick="return confirm('삭제하시겠습니까?');">삭제</button>
							</form>
						</td>

					</tr>
				</c:forEach>
			</table>
		</article>

		<article class="article5">
			<ul>
				<li><i class="fa-solid fa-angles-left"
					onclick="location.href='<c:url value="/productSelect.do?viewTime=${viewTime}&pageNum=1" />'"></i>
				</li>
				<li><i class="fa-solid fa-angle-left"
					onclick="location.href='<c:url value="/productSelect.do?viewTime=${viewTime}&pageNum=${pageNum - 1 < 1 ? 1 : pageNum - 1}" />'"></i>
				</li>
				<c:forEach var="i" begin="${pageStartNum}" end="${pageEndNum}">
					<li class="${pageNum == i ? 'active' : ''}"
						onclick="location.href='<c:url value="/productSelect.do?viewTime=${viewTime}&pageNum=${i}" />'">${i}</li>
				</c:forEach>
				<li><i class="fa-solid fa-chevron-right"
					onclick="location.href='<c:url value="/productSelect.do?viewTime=${viewTime}&pageNum=${pageNum + 1 > pageCount ? pageCount : pageNum + 1}" />'"></i>
				</li>
				<li><i class="fa-solid fa-angles-right"
					onclick="location.href='<c:url value="/productSelect.do?viewTime=${viewTime}&pageNum=${pageCount}" />'"></i>
				</li>
			</ul>

			<form action="#" method="get" name="productInsert.do">
				<c:if test="${sessionScope.cvo.id == 'admin'}">
					<button type="button" id="writeButton"
						onclick="location.href='<c:url value='/home/product/productInsertPage.jsp' />'">상품
						추가</button>
				</c:if>
			</form>
		</article>
	</main>

	<hr>
	<footer>
		<%@ include file="/home/footerSection.jsp"%>
	</footer>
	<script src="<c:url value='/home/js/common.js' />"></script>
</body>
</html>

