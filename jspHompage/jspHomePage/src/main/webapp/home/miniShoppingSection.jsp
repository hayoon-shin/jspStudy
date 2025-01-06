<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<section class="productArticle">
    <h3>상품 요약</h3>
    <div class="productGrid">
        <!-- 상품 1 -->
        <c:set var="count" value="1" />
        <c:forEach var="product" items="${sList}" varStatus="status">
					<div class="productItem">
						<a
							href="<c:url value='/shopListSelect.do?no=${product.no}&type=${product.type}' />">
							<img src="<c:url value='${product.titleUrl}' />"alt="${product.name}" class="productImg">
								<div class="price">
									<fmt:formatNumber value="${product.price}" type="currency" />
								</div>
								<div class="name">${product.title}</div>
						</a>
					</div>
					<c:set var="counter" value="${count + 1}" />
				</c:forEach>
    </div>
    <a href="${pageContext.request.contextPath}/productList.do" class="moreButton">더보기</a>
</section>

