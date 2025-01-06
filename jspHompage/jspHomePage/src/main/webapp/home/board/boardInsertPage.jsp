<%@page import="co.kh.dev.home.model.BoardDAO"%>
<%@page import="co.kh.dev.home.model.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시글 작성</title>
<script src="https://kit.fontawesome.com/6ff644124c.js" crossorigin="anonymous"></script>
<%@ include file="/home/css/commonCss.jsp" %>
<link rel="stylesheet" href="<c:url value='/home/board/css/boardInsertPage.css' />" />

<!-- 세션 검사 및 강제 접근 차단 -->
    <c:if test="${empty sessionScope.cvo}">
        <c:redirect url="/jspHomePage/mainPageAlert.do?status=8" />
    </c:if>

</head>
<body>
<header>
    <nav class="headerNav">
        <%@ include file="/home/headerNavSection.jsp" %>
    </nav>
</header>

<main class="boardInsertPage">
    <h2>게시글 작성</h2>
    <form method="post" action="<c:url value='/boardInsert.do' />" class="boardForm">
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
    <%@ include file="/home/footerSection.jsp" %>
</footer>
<script src="<c:url value='/home/js/common.js' />"></script>
</body>
</html>


