<%@page import="com.kh.dev.join.model.BoardTwoDAO"%>
<%@page import="com.kh.dev.join.model.BoardTwoVO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="color.jsp"%>
<%
request.setCharacterEncoding("UTF-8");
int num = Integer.parseInt(request.getParameter("num"));
String pageNum = request.getParameter("pageNum");
BoardTwoVO vo = new BoardTwoVO(); 
vo.setNum(num); 
%>
<%
try {
	BoardTwoDAO bdao = BoardTwoDAO.getInstance();
	BoardTwoVO article = bdao.selectBoardOneDB(vo); 
%>
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script language="javascript"
	src="script.js?timestamp=<%= System.currentTimeMillis() %>"></script>
</head>
<body bgcolor="<%=bodyback_c%>">
	<main>
		<b>글수정</b> <br>
		<form method="post" name="writeform" action="updateProc.jsp?pageNum=<%=pageNum%>"
			onsubmit="return writeSave()">
            <input type="hidden" name="num" value="${num}">

            <label for="writer">작성자</label>
            <input type="text" id="writer" name="writer" value="${content.writer}" readonly>

            <label for="subject">제목</label>
            <input type="text" id="subject" name="subject" value="${content.subject}" required>

            <label for="content">내용</label>
            <textarea id="content" name="content" rows="10" required>${content.content}</textarea>

            <label for="pass">비밀번호</label>
            <input type="password" id="pass" name="pass" required>

            <div class="button-group">
                <button type="submit">수정</button>
                <button type="button" onclick="location.href='content.jsp?num=${num}'">취소</button>
            </div>
        </form>
    </main>
	<%
	} catch (Exception e) {
	}
	%>
</body>
</html>