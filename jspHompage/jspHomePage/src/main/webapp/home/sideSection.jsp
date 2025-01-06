<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<section class="noticeSection">
    <h3>공지사항</h3>
    <ul class="noticeList">
        <!-- 공지사항 데이터를 동적으로 표시 -->
			<c:forEach var="data" items="${nList}">
				<li>
					<a href=" ${pageContext.request.contextPath}/noticeListSelect.do?no=${data.no}&count=1">${data.title}</a>
					<span class="date">${data.subdate}</span>
				</li>
			</c:forEach>
    </ul>
    <a href="${pageContext.request.contextPath}/noticeList.do" class="moreButton">더보기</a>
</section>
<section class="recentProductSection">
    <h3>최근 본 상품</h3>
    <ul class="recentProductList">
        <!-- 최근 본 상품 데이터를 동적으로 표시 -->
        <c:forEach var="data" items="${selectSList}">
         <a href="${pageContext.request.contextPath}/shopListSelect.do?type=${data.type}&no=${data.no}" >
        <li>
            <span class="name">${data.title}</span>
            <span class="price">${data.price}</span>
        </li>
            </a>
            </c:forEach>
      
       
    </ul>
      <a href="${pageContext.request.contextPath}/shopSelect.do?type=1" class="moreButton">더보기</a>
</section>