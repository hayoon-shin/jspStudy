<%@page import="java.text.SimpleDateFormat"%>
<%@page import="co.kh.dev.home.model.CommentDAO"%>
<%@page import="co.kh.dev.home.model.CommentVO"%>
<%@page import="co.kh.dev.home.model.NoticeDAO"%>
<%@page import="co.kh.dev.home.model.NoticeVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시판</title>
<script src="https://kit.fontawesome.com/6ff644124c.js" crossorigin="anonymous"></script>
<%@ include file="/home/css/commonCss.jsp" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/home/notice/css/noticeContentPage.css" />
<c:if test="${alertFlag}">
    <script>
        alert("${msg}");
        window.location.replace("${pageContext.request.contextPath}/noticeListSelect.do?no=${nvo.no}");
    </script>
</c:if>
</head>
<body>
<header>
    <nav class="headerNav">
        <%@ include file="/home/headerNavSection.jsp" %>
    </nav>
</header>
<main class="noticeContentPage">
    <section class="noticeView">
        <h2>게시글 상세 조회</h2>
        <!-- 게시글 상세 정보 -->
        <div class="postDetail">
            <table>
                <tr>
                    <th>제목</th>
                    <td id="postTitle">${nvo.title}</td>
                </tr>
                <tr>
                    <th>작성일</th>
                    <td id="postDate">
                        <fmt:formatDate value="${nvo.subdate}" pattern="yyyy-MM-dd HH:mm" />
                    </td>
                </tr>
                <tr>
                    <th>조회수</th>
                    <td id="postDate">${nvo.count}</td>
                </tr>
            </table>
            <div id="postContent">${nvo.content}</div>

            <c:if test="${not empty cvo && cvo.id eq 'admin'}">
                <div class="postActions">
                    <form method="post" action="${pageContext.request.contextPath}/home/notice/noticeListUpdatePage.jsp">
                        <input type="hidden" name="no" value="${nvo.no}" />
                        <input type="hidden" name="title" value="${nvo.title}" />
                        <input type="hidden" name="content" value="${nvo.content}" />
                        <button type="submit" class="editBtn">수정</button>
                    </form>
                    <button type="button" class="deleteBtn" onclick="deleteNotice(${nvo.no})">삭제</button>
                </div>
            </c:if>
        </div>
    </section>
    <div class="toList">
        <button onclick="location.href='${pageContext.request.contextPath}/home/notice/noticePage.jsp'">목록으로</button>
    </div>
</main>
<hr>
<footer>
    <%@ include file="/home/footerSection.jsp" %>
</footer>
<script src="${pageContext.request.contextPath}/home/notice/js/noticeContentPage.js"></script>
<script src="${pageContext.request.contextPath}/home/js/common.js"></script>
</body>
</html>