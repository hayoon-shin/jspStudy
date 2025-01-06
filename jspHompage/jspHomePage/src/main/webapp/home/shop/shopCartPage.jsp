<%@page import="co.kh.dev.home.model.ProductVO"%>
<%@page import="co.kh.dev.home.model.ProductDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>장바구니</title>
<script src="https://kit.fontawesome.com/6ff644124c.js" crossorigin="anonymous"></script>
<%@ include file="/home/css/commonCss.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/home/shop/css/shopCartPage.css" />

</head>
<body>
<header>
    <nav class="headerNav">
        <%@ include file="/home/headerNavSection.jsp" %>
    </nav>
</header>
    <div class="cart-container">
        <h1>장바구니</h1>
        <form action="/orderAll.do" method="post">
            <table class="cart-table">
                <thead>
                    <tr>
                        <th><input type="checkbox" id="checkAll" onchange="toggleAll(this)"></th>
                        <th>상품명</th>
                        <th>가격</th>
                        <th>수량</th>
                        <th>삭제</th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${not empty ctList}">
                        <c:forEach var="cart" items="${ctList}">
                            <tr>
                                <td><input type="checkbox" name="cartNo" value="${cart.no}" onchange="setPrice(this)"></td>
                                <td>${cart.title}</td>
                                <td><fmt:formatNumber value="${cart.price}" pattern="#,##0" />원</td>
                                <td><input type="number" name="quantity" value="${cart.qt}" min="1" class="quantity-input${cart.no}" onchange="changeQt(${cart.no},${cart.price})"></td>
                                <td><button type="button" class="delete-btn" onclick="deleteCartItem(${cart.no})">삭제</button></td>
                            </tr>
                                <input type="hidden" class="eachPrice${cart.no}" value="${cart.qt*cart.price}">
                        </c:forEach>
                    </c:if>
                    <c:if test="${empty ctList}">
                        <tr>
                            <td colspan="5" style="text-align: center;">장바구니가 비어 있습니다.</td>
                        </tr>
                    </c:if>
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="2">총 합계:</td>
                        <td colspan="3" style="text-align: right;"><input type="text" id="totalPrice" value="<fmt:formatNumber value="0" pattern="#,##0" />" readOnly/>원</td>
                    </tr>
                </tfoot>
            </table>
            <div class="cart-actions">
                <button type="submit" class="order-all-btn">주문하기</button>
            </div>
        </form>
    </div>

<br>
<hr>
<footer>
    <%@ include file="/home/footerSection.jsp" %>
</footer>
<script src="${pageContext.request.contextPath}/home/js/common.js"></script>
<script src="${pageContext.request.contextPath}/home/shop/js/shopCartPage.js"></script>
</body>
</html>
