<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.dev.join.model.FileUploadVO, com.kh.dev.join.model.FileUploadDAO" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    FileUploadVO vo = null;

    try {
        FileUploadDAO dao = new FileUploadDAO();
        vo = dao.getFileUploadById(id);
    } catch (SQLException e) {
        out.println("DB 조회 오류: " + e.getMessage());
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>게시글 수정</title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="../../headerNav.css">
    
</head>
<body>
<%@ include file="/join/headerNav.jsp"%>
    <h1>게시글 수정</h1>
    <form action="fileuploadEditProc.jsp" method="post">
        <input type="hidden" name="id" value="<%= vo.getId() %>">
        <label>작성자: <input type="text" name="author" value="<%= vo.getAuthor() %>" readonly></label><br>
        <label>제목: <input type="text" name="title" value="<%= vo.getTitle() %>" required></label><br>
        <label>내용:<br><textarea name="content" rows="5" cols="50" required><%= vo.getContent() %></textarea></label><br>
        <label>비밀번호: <input type="password" name="password" required></label><br>
        <button type="submit">수정</button>
    </form>
    <a href="fileuploadList.jsp">목록으로</a>
</body>
</html>
