<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>게시글 삭제</title>
</head>
<body>
    <h1>게시글 삭제</h1>
    <form action="fileuploadDeleteProc.jsp" method="post">
        <input type="hidden" name="id" value="<%= request.getParameter("id") %>">
        <label>비밀번호: <input type="password" name="password" required></label><br>
        <button type="submit">삭제</button>
    </form>
    <a href="fileuploadList.jsp">목록으로</a>
</body>
</html>
