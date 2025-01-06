<%@page import="co.kh.dev.home.model.NoticeDAO"%>
<%@page import="co.kh.dev.home.model.NoticeVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
	href="${pageContext.request.contextPath}/home/notice/css/noticeInsertPage.css" />
	<c:if test="${empty cvo}">
	<c:redirect url="/mainPageAlert	.do?status=8"></c:redirect>
	</c:if>
</head>
<body>
	<header>
		<nav class="headerNav">
			<%@ include file="/home/headerNavSection.jsp"%>
		</nav>
	</header>
	<main class="noticeInsertPage">
    <h2>게시글 작성</h2>
    <form method="post" action="/jspHomePage/noticeInsert.do" class="noticeForm">
        <label for="title">제목</label>
        <input type="text" id="title" name="title" placeholder="제목을 입력하세요" required />

        <label for="content">내용</label>
        <textarea id="content" name="content" placeholder="내용을 입력하세요" rows="30" required></textarea>
        <div class="formActions">
            <button type="button" class="cancelBtn" onclick="history.back();">취소</button>
            <button type="submit" class="submitBtn">작성하기</button>
        </div>
    </form>
</main>
	<hr>
	<footer>
		<%@ include file="/home/footerSection.jsp"%>
	</footer>
	<script src="${pageContext.request.contextPath}/home/js/common.js"></script>
</body>
</html>

