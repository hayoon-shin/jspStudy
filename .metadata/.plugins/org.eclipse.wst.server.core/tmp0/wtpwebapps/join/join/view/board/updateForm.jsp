<%@page import="com.kh.dev.join.model.BoardTwoDAO"%>
<%@page import="com.kh.dev.join.model.BoardTwoVO"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    // 전달받은 글 번호와 페이지 번호
    String num = request.getParameter("num");
    String pageNum = request.getParameter("pageNum");
    String writer = request.getParameter("writer");
    String subject = request.getParameter("subject");
    String content = request.getParameter("content");
    String email = request.getParameter("email");

    // 글 번호가 없으면 오류 처리
    if (num == null || num.trim().isEmpty()) {
        out.println("<script>alert('글 번호가 전달되지 않았습니다.'); history.back();</script>");
        return;
    }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>글 수정</title>
</head>
<body>
    <main>
        <h1>글 수정</h1>
        <form action="updateProc.jsp" method="post">
            <!-- 글 번호와 페이지 번호 전달 -->
            <input type="hidden" name="num" value="<%= num %>">
            <input type="hidden" name="pageNum" value="<%= pageNum %>">

            <label for="writer">작성자:</label>
            <input type="text" id="writer" name="writer" value="<%= writer != null ? writer : "" %>" required>

            <label for="subject">제목:</label>
            <input type="text" id="subject" name="subject" value="<%= subject != null ? subject : "" %>" required>

            <label for="content">내용:</label>
            <textarea id="content" name="content" required><%= content != null ? content : "" %></textarea>

            <label for="email">이메일:</label>
            <input type="email" id="email" name="email" value="<%= email != null ? email : "" %>" required>

            <label for="pass">비밀번호:</label>
            <input type="password" id="pass" name="pass" required>

            <div class="button-group">
                <button type="submit">수정</button>
                <button type="button" onclick="history.back();">취소</button>
            </div>
        </form>
    </main>
</body>
</html>
