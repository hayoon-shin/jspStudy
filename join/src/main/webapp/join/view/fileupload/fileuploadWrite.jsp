<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
    <title>게시글 작성</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="../../headerNav.css">
    
</head>
<body>
<%@ include file="/join/headerNav.jsp"%>
    <h1>게시글 작성</h1>
   <form action="<%= request.getContextPath() %>/fileuploadWriteProc.do" method="post" enctype="multipart/form-data">
    <label>작성자: <input type="text" name="author" required></label><br>
    <label>제목: <input type="text" name="title" required></label><br>
    <label>내용:<br><textarea name="content" rows="5" cols="50" required></textarea></label><br>
    <label>비밀번호: <input type="password" name="password" required></label><br>
    <label>파일 업로드: <input type="file" name="file"></label><br>
    <button type="submit">작성</button>
	</form>
    <a href="<%= request.getContextPath() %>/join/view/fileupload/fileuploadList.jsp">목록으로</a>

</body>
</html>
