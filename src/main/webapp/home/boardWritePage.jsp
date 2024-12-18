<%@page import="co.kh.dev.home.model.BoardDAO"%>
<%@page import="co.kh.dev.home.model.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
CustomerVO cvo =MyUtility.returnCvoBySession(session);
if (cvo==null) {//세션이 없다면 글쓰기 불가능
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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/home/css/carouselSection.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/home/css/common.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/home/css/headerNav.css" />
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
    <h2>게시글 작성</h2>
    <form method="post" action="/jspHomePage/boardInsertServlet.do" class="boardForm">
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
		<%@ include file="/home/footer.jsp"%>
	</footer>
	<script src="<%=request.getContextPath()%>/home/js/common.js"></script>
</body>
</html>

