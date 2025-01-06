<%@page import="java.text.SimpleDateFormat"%>
<%@page import="co.kh.dev.home.model.CommentDAO"%>
<%@page import="co.kh.dev.home.model.BoardDAO"%>
<%@page import="co.kh.dev.home.model.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>메인 화면</title>
<script src="https://kit.fontawesome.com/6ff644124c.js"
	crossorigin="anonymous"></script>
<%@ include file="/home/css/commonCss.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/home/board/css/boardPage.css" />
<c:if test="${empty bList}">
<c:redirect url="/boardSelect.do"/>
</c:if>
</head>
<body>
	<header>
		<nav class="headerNav">
			<%@ include file="/home/headerNavSection.jsp"%>
		</nav>
	</header>
	<header class="boardHeader">
		<h1>자유게시판</h1>
	</header>
	<main class="boardPage">
		<article class="article2">
			<p class="notice">- 본 게시판과 관련이 없거나 상업적인 내용, 특정인이나 특정사안을 비방하는 내용
				등은 예고없이 삭제될 수 있습니다.</p>
		</article>
		<article class="article3">
			<div class="all">
				<form method="get" action="/jspHomePage/boardFindSelect.do">
					<select name="viewTime" id="view">
						<option value="10"
							${viewTime == 10? "selected" : ""}>10개씩</option>
						<!-- viewTime에 맞춰서 select변경 -->
						<option value="20"
							${viewTime == 20? "selected" : ""}>20개씩</option>
						<!-- viewTime에 맞춰서 select변경 -->
					</select>
					<button type="submit" id="BoardViewButton">보기</button>
				</form>
				<form method="get" action="/jspHomePage/boardFindSelect.do">
					<select name="findValue" id="find">
						<option value="title"
							${findValue=="title"? "selected" :""}>제목</option>
						<option value="text"
							${findValue=="text"? "selected" :""}>내용</option>
					</select> <input type="text" name="findText" id="findText"
						value="${findText}">
					<button type="submit" id="findButton">검색</button>
					<button type="button"
						onclick="location.href='/jspHomePage/boardSelect.do'">검색취소</button>
				</form>
			</div>
		</article>
		<article class="article4">
			<table>
				<tr>
					<th>번호</th>
					<th width="800">제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>등록일</th>
				</tr>

				<c:forEach var="data" items="${bList}" >
				<tr>
					<td class="tbNum">${data.rownum}</td>
					<td class="tbMain"><a
						href=" ${pageContext.request.contextPath}/boardListSelect.do?rowNum=${data.rownum}">${data.title}</a>&nbsp;&nbsp;
						[${data.commentNum}]</td>
					<td class="tbInsertr">${data.customerId}</td>
					<td class="tbView">${data.count}</td>
					<td class="tbDate">
					<fmt:formatDate value="${data.subdate}" pattern="yyyy-MM-dd  HH:mm"/>
				</tr>
			</c:forEach>

			</table>
		</article>
		<article class="article5">
			<ul>
				<li><i class="fa-solid fa-angles-left"
					onclick="location.href='/jspHomePage/boardFindSelect.do?viewTime=${viewTime}&pageNum=1&findValue=${findValue}&findText=${findText}"></i></li>
				<li><i class="fa-solid fa-angle-left"
					onclick="location.href='/jspHomePage/boardFindSelect.do?viewTime=${viewTime}&pageNum=${pageNum - 1 <= 0 ? 1 : pageNum - 1}&findValue=${findValue}&findText=${findText}'"></i></li>
				<c:forEach var="i" begin="${pageStartNum}" end="${pageEndNum}">
				<li ${pageNum == i? "class='active'" : ""}
					onclick="location.href='/jspHomePage/boardFindSelect.do?viewTime=${viewTime}&pageNum=${i}&findValue=${findValue}&findText=${findText}'">
					${i}
				</li>
			</c:forEach>
				<li><i class="fa-solid fa-chevron-right"
					onclick="location.href='/jspHomePage/boardFindSelect.do?viewTime=${viewTime}&pageNum=${pageNum + 1 > pageCount ? pageCount : pageNum + 1}&findValue=${findValue}&findText=${findText}'"></i></li>
				<li><i class="fa-solid fa-angles-right"
					onclick="location.href='/jspHomePage/boardFindSelect.do?viewTime=${viewTime}&pageNum=${pageCount}&findValue=${findValue}&findText=${findText}'"></i></li>
			</ul>

			<form action="#" method="get" name="boardInsert.do">
				<button type="button" id="writeButton"
					onclick= "${empty cvo? 'openLoginPopup()' : 'location.href=\'/jspHomePage/home/board/boardInsertPage.jsp\';'}">글쓰기</button>
			</form>
		</article>
	</main>
	<hr>
	<footer>
		<%@ include file="/home/footerSection.jsp"%>
	</footer>
	<script src="${pageContext.request.contextPath}/home/js/common.js"></script>
</body>
</html>

