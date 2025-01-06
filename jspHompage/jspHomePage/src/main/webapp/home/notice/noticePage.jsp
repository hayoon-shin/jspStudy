<%@page import="oracle.jdbc.internal.XSSessionNamespace"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>공지사항</title>
<script src="https://kit.fontawesome.com/6ff644124c.js" crossorigin="anonymous"></script>
<%@ include file="/home/css/commonCss.jsp" %>
<link rel="stylesheet" href="<c:url value='/home/notice/css/noticePage.css' />" />
<!-- 알림 메시지 출력 -->
<c:if test="${alertFlag}">
<script>
alert("${msg}");
window.location.replace("<c:url value='/noticeSelect.do' />");
</script>
</c:if>

<c:if test="${empty nList}">
<c:redirect url="/noticeSelect.do"></c:redirect>
</c:if>
</head>

<body>
<header>
    <nav class="headerNav">
        <%@ include file="/home/headerNavSection.jsp" %>
    </nav>
</header>

<main class="noticePage">
    <header class="noticeHeader">
        <h1>공지사항</h1>
    </header>
    <!-- 항목 개수 선택 폼 -->
    <article class="article3">
        <div class="all">
            <form method="get" action="<c:url value='/noticeSelect.do' />">
                <label for="viewTime">보기 항목 개수:</label>
                <select name="viewTime" id="viewTime">
                    <option value="10" ${viewTime == 10 ? "selected" : ""}>10개씩</option>
                    <option value="20" ${viewTime == 20 ? "selected" : ""}>20개씩</option>
                </select>
                <button type="submit" id="NoticeViewButton">보기</button>
            </form>

            <!-- 검색 기능 폼 -->
            <form method="get" action="<c:url value='/noticeFindSelect.do' />">
                <label for="find">검색 기준:</label>
                <select name="findValue" id="find">
                    <option value="title">제목</option>
                </select>
                <input type="text" name="findText" id="findText" />
                <button type="submit" id="findButton">검색</button>
            </form>
        </div>
    </article>
    <!-- 공지사항 목록 -->
    <article class="article4">
        <table>
            <tr>
                <th>번호</th>
                <th width="800">제목</th>
                <th>조회수</th>
                <th>등록일</th>
            </tr>
            <c:forEach var="nvo" items="${nList}">
                <tr>
                    <td class="tbNum">${nvo.rownum}</td>
                    <td class="tbMain">
                        <a href="<c:url value='/noticeListSelect.do?no=${nvo.no}&count=1' />">
                            ${nvo.title}
                        </a>
                    </td>
                    <td class="tbView">${nvo.count}</td>
                    <td class="tbDate">
                        <fmt:formatDate value="${nvo.subdate}" pattern="yyyy-MM-dd HH:mm" />
                    </td>
                </tr>
            </c:forEach>
        </table>
    </article>

    <!-- 페이지 네비게이션 -->
    <article class="article5">
        <ul>
            <li>
                <i class="fa-solid fa-angles-left" onclick="location.href='<c:url value="/noticeSelect.do?viewTime=${viewTime}&pageNum=1" />'"></i>
            </li>
            <li>
                <i class="fa-solid fa-angle-left" onclick="location.href='<c:url value="/noticeSelect.do?viewTime=${viewTime}&pageNum=${pageNum - 1 < 1 ? 1 : pageNum - 1}" />'"></i>
            </li>

            <c:forEach var="i" begin="${pageStartNum}" end="${pageEndNum}">
                <li class="${pageNum == i ? 'active' : ''}" onclick="location.href='<c:url value="/noticeSelect.do?viewTime=${viewTime}&pageNum=${i}" />'">${i}</li>
            </c:forEach>

            <li>
                <i class="fa-solid fa-chevron-right" onclick="location.href='<c:url value="/noticeSelect.do?viewTime=${viewTime}&pageNum=${pageNum + 1 > pageCount ? pageCount : pageNum + 1}" />'"></i>
            </li>
            <li>
                <i class="fa-solid fa-angles-right" onclick="location.href='<c:url value="/noticeSelect.do?viewTime=${viewTime}&pageNum=${pageCount}" />'"></i>
            </li>
        </ul>

        <!-- 글쓰기 버튼 (관리자만 표시) -->
        <form action="#" method="get" name="noticeInsert.do">
            <c:if test="${sessionScope.cvo.id == 'admin'}">
                <button type="button" id="writeButton" onclick="location.href='<c:url value='/home/notice/noticeInsertPage.jsp' />'">글쓰기</button>
            </c:if>
        </form>
    </article>
</main>

<hr>
<footer>
    <%@ include file="/home/footerSection.jsp" %>
</footer>
<script src="<c:url value='/home/js/common.js' />"></script>
</body>
</html>

