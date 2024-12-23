<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="color.jsp"%>
<%
request.setCharacterEncoding("UTF-8");
int num = Integer.parseInt(request.getParameter("num"));
String pageNum = request.getParameter("pageNum");
%>
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="javascript"
	src="script.js?timestamp=<%=System.currentTimeMillis()%>"></script>
</head>
<body>
    <main>
        <h1>게시글 삭제</h1>
        <form action="deleteProc.jsp" method="post">
            <input type="hidden" name="num" value="${num}">
            <label for="pwd">비밀번호:</label>
            <input type="password" id="pwd" name="pwd" required>
            <button type="submit">삭제</button>
        </form>
        <button onclick="location.href='list.jsp'">취소</button>
    </main>
</body>
</html>

