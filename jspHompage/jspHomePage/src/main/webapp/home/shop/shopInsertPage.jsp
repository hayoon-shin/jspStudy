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
<link rel="stylesheet" href="${pageContext.request.contextPath}/home/shop/css/shopInsertPage.css" />
<c:if test="${cvo.id!='admin'}">
    <c:redirect url="/mainPageAlert.do?status=8"/>
</c:if>
<c:if test="${empty pList}">
    <c:redirect url="/shopInsertPageSelect.do"/>
</c:if>
</head>
<body>
<header>
    <nav class="headerNav">
        <%@ include file="/home/headerNavSection.jsp" %>
    </nav>
</header>
<main class="productInsertPage">
        <h2>상품 작성</h2>
        <form method="post" action="<c:url value='/shopInsert.do?type=${param.type}' />" enctype="multipart/form-data" class="productForm">
            <!-- 상품 선택 -->
            <label for="productSelect">상품 선택</label>
            <select id="productSelect" name="productNo" onchange="updatePrice()" required>
                <option value="" disabled selected>상품을 선택하세요</option>
                <c:forEach var="product" items="${pList}">
                    <option value="${product.no}" data-price="${product.price}">
                        ${product.name}
                    </option>
                </c:forEach>
            </select>
            <!-- 가격 -->
            <label for="price">상품 가격</label>
            <input type="number" id="price" name="price" placeholder="상품 가격" readonly>
            
            <!-- 제목 -->
            <label for="title">상품 제목</label>
            <input type="text" id="title" name="title" placeholder="상품 제목을 입력하세요" required>
            
            <!-- 타이틀 이미지 -->
             <label for="titleImg">타이틀 이미지</label>
            <input type="file" id="titleImg" name="titleImg" accept="image/*" required> 
            
            <!-- 내용 이미지 -->
             <label for="contentImgs">내용 이미지</label>
            <input type="file" id="contentImgs" name="contentImgs" accept="image/*" multiple> 
            
            <!-- 내용 -->
            <label for="content">상품 설명</label>
            <textarea id="content" name="content" placeholder="상품 설명을 입력하세요" required></textarea>
            
            <!-- 버튼 -->
            <div class="formActions"> 
                <button type="button" class="submitBtn" onclick="validateAndSubmit()">등록</button>
                <button type="button" class="cancelBtn" onclick="history.back()">취소</button>
            </div>
        </form>
    </main>
<hr>
<footer>
    <%@ include file="/home/footerSection.jsp" %>
</footer>
<script src="${pageContext.request.contextPath}/home/js/common.js"></script>
<script src="${pageContext.request.contextPath}/home/shop/js/shopInsertPage.js"></script>
</body>
</html>
