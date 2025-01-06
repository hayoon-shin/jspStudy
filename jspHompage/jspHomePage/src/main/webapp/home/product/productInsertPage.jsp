<%@page import="co.kh.dev.home.model.ProductVO"%>
<%@page import="co.kh.dev.home.model.ProductDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>상품 등록</title>
<script src="https://kit.fontawesome.com/6ff644124c.js" crossorigin="anonymous"></script>
<%@ include file="/home/css/commonCss.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/home/product/css/productInsertPage.css" />
<c:if test="${empty cvo}">
    <c:redirect url="/mainPageAlert.do?status=8"></c:redirect>
</c:if>
</head>
<body>
<header>
    <nav class="headerNav">
        <%@ include file="/home/headerNavSection.jsp" %>
    </nav>
</header>
<main class="productInsertPage">
    <h2>상품 등록</h2>
    <form method="post" action="/jspHomePage/productInsert.do" class="productForm">
        <label for="name">상품명</label>
        <input type="text" id="name" name="name" placeholder="상품명을 입력하세요" required />

        <label for="price">가격</label>
        <input type="number" id="price" name="price" placeholder="가격을 입력하세요" required />

        <label for="amount">수량</label>
        <input type="number" id="amount" name="amount" placeholder="수량을 입력하세요" required />

        <div class="formActions">
            <button type="button" class="cancelBtn" onclick="history.back();">취소</button>
            <button type="submit" class="submitBtn">등록하기</button>
        </div>
    </form>
</main>
<hr>
<footer>
    <%@ include file="/home/footerSection.jsp" %>
</footer>
<script src="${pageContext.request.contextPath}/home/js/common.js"></script>
</body>
</html>

