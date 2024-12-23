<%@page import="co.kh.dev.home.model.BoardDAO"%>
<%@page import="co.kh.dev.home.model.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
CustomerVO cvo =MyUtility.returnCvoBySession(session);
if (cvo==null&&cvo.getId().equals(request.getParameter("id"))) {//세션이 없고, 세션아이디와 글 아이디가 맞지않으면 접근불가
	response.sendRedirect("/jspHomePage/mainPageAlertServlet.do?status=8");//강제접근으로 로그인 안되있을시 메인으로가서 8번 경고문 출력
	return;
}
%>

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
	href="<%=request.getContextPath()%>/home/css/boardWritePage.css" />
</head>
<body>
	<header>
		<nav class="headerNav">
			<%@ include file="/home/headerNav.jsp"%>
		</nav>
	</header>
	<main class="boardWritePage">
    <h2>게시글 수정</h2>
    <form method="post" action="/jspHomePage/boardModifyServlet.do" class="boardForm">
        <label for="title">제목</label>
        <input type="text" id="title" name="title" placeholder="제목을 입력하세요" required value="<%=request.getParameter("title") %>"/>
        <label for="content">내용</label>
        <textarea id="content" name="content" placeholder="내용을 입력하세요" rows="30" required><%=request.getParameter("content") %></textarea>
        <div class="formActions">
        <input type="hidden" name="no" value="<%= request.getParameter("no")%>">
            <button type="button" class="cancelBtn" onclick="history.back();">취소</button>
            <button type="submit" class="submitBtn">수정하기</button>
        </div>
    </form>
</main>
	<hr>
	<footer>
		<%@ include file="/home/footer.jsp"%>
	</footer>
	<script src="<%=request.getContextPath()%>/home/js/common.js"></script>
</body>
</html>

