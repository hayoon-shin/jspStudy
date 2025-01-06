<%@page import="java.text.SimpleDateFormat"%>
<%@page import="co.kh.dev.home.model.CommentDAO"%>
<%@page import="co.kh.dev.home.model.CommentVO"%>
<%@page import="co.kh.dev.home.model.BoardDAO"%>
<%@page import="co.kh.dev.home.model.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시판</title>
<script src="https://kit.fontawesome.com/6ff644124c.js" crossorigin="anonymous"></script>
<%@ include file="/home/css/commonCss.jsp" %>
<link rel="stylesheet" href="<c:url value='/home/board/css/boardContentPage.css' />" />

<c:if test="${alertFlag}">
<script>
alert("${msg}");
window.location.replace("<c:url value='/boardListSelect.do?no=${bvo.no}' />");
</script>
</c:if>
</head>
<body>
<header>
    <nav class="headerNav">
        <%@ include file="/home/headerNavSection.jsp" %>
    </nav>
</header>

<main class="boardContentPage">
    <section class="boardView">
        <h2>게시글 상세 조회</h2>
        <div class="postDetail">
            <table>
                <tr>
                    <th>제목</th>
                    <td id="postTitle">${bvo.title}</td>
                </tr>
                <tr>
                    <th>글쓴이</th>
                    <td id="postInsertr">${bvo.customerId}</td>
                </tr>
                <tr>
                    <th>작성일</th>
                    <td id="postDate">
                        <fmt:formatDate value="${bvo.subdate}" pattern="yyyy-MM-dd HH:mm" />
                    </td>
                </tr>
                <tr>
                    <th>조회수</th>
                    <td id="postView">${bvo.count}</td>
                </tr>
            </table>

            <div id="postContent">${bvo.content}</div>

            <c:if test="${not empty cvo && bvo.customerId == cvo.id}">
                <div class="postActions">
                    <form method="post" action="<c:url value='/home/board/boardListUpdatePage.jsp' />" accept-charset="UTF-8">
                        <input type="hidden" name="no" value="${bvo.no}" />
                        <input type="hidden" name="id" value="${bvo.customerId}" />
                        <input type="hidden" name="title" value="${bvo.title}" />
                        <input type="hidden" name="content" value="${bvo.content}" />
                        <button type="submit" class="editBtn">수정</button>
                    </form>
                    <button type="button" class="deleteBtn" onclick="deletePost(${bvo.no})">삭제</button>
                </div>
            </c:if>
        </div>
    </section>

    <!-- 댓글 작성 폼 -->
    <section class="commentForm" style="${empty sessionScope.cvo ? 'display:none;' : ''}">
        <h3>댓글 작성</h3>
        <form method="post" action="<c:url value='/boardCommentInsert.do' />">
            <input type="hidden" name="boardNo" value="${bvo.no}" />
            <textarea name="content" placeholder="댓글을 입력하세요" required></textarea>
            <button type="submit">댓글 작성</button>
        </form>
    </section>

    <!-- 댓글 목록 -->
    <section class="commentList">
        <h3>댓글 목록</h3>
        <c:forEach var="cmvo" items="${cmList}">
            <div class="comment" style="margin-left: ${cmvo.depth * 30}px;">
                <p class="commentInfo">
                    <strong>${cmvo.customerId}</strong>
                    <span class="commentDate">
                        <fmt:formatDate value="${cmvo.subdate}" pattern="yyyy-MM-dd HH:mm" />
                    </span>
                </p>
                <p class="commentContent">${cmvo.content}</p>

                <div class="commentActions" style="${empty cvo ? 'display:none;' : 'display:flex;  justify-content: flex-end;'}">
                    <button class="commentReplyBtn" onclick="toggleDisp('togle${cmvo.no}')">답글</button>
                    <c:if test="${not empty cvo && cmvo.customerId == cvo.id}">
                        <form action="/jspHomePage/boardCommentDelete.do">
                        <input type="hidden" value="${cmvo.no}" name="no">
                        <input type="hidden" value="${bvo.no}" name="boardNo">
                        <button type="submit" class="commentDeleteBtn">삭제</button>
                        </form>
                    </c:if>
                </div>
            </div>
	
            <div class="reCommentForm dispNone" id="togle${cmvo.no}">
                <form action="<c:url value='/boardCommentInsert.do' />" method="post">
                    <input type="hidden" name="boardNo" value="${bvo.no}" />
                    <input type="hidden" name="parentNo" value="${cmvo.no}" />
                    <input type="hidden" name="depth" value="${cmvo.depth}" />
                    <textarea name="content" placeholder="답글을 입력하세요" required></textarea>
                    <button type="submit">작성</button>
                </form>
            </div>
        </c:forEach>
    </section>

    <div class="toList">
        <button onclick="location.href='<c:url value='/home/board/boardPage.jsp' />'">목록으로</button>
    </div>
</main>

<hr>
<footer>
    <%@ include file="/home/footerSection.jsp" %>
</footer>
<script src="<c:url value='/home/board/js/boardContentPage.js' />"></script>
<script src="<c:url value='/home/js/common.js' />"></script>
</body>
</html>
