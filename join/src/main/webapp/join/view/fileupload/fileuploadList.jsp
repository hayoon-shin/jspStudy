<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.kh.dev.join.model.FileUploadDAO, com.kh.dev.join.model.FileUploadVO, java.util.*" %>

<%
    int page2 = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;
    int pageSize = 10;
    List<FileUploadVO> list = new ArrayList<>();

    try {
        FileUploadDAO dao = new FileUploadDAO();
        list = dao.getFileUploadList(page2, pageSize);
    } catch (SQLException e) {
        out.println("DB 오류: " + e.getMessage());
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>게시판 목록</title>
</head>
<body>
    <h1>게시판</h1>
    <a href="fileuploadWrite.jsp">새 글 작성</a>
    <table border="1">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
        </tr>
        <%
            for (FileUploadVO vo : list) {
        %>
        <tr>
            <td><%= vo.getId() %></td>
            <td><a href="fileuploadView.jsp?id=<%= vo.getId() %>"><%= vo.getTitle() %></a></td>
            <td><%= vo.getAuthor() %></td>
            <td><%= vo.getCreatedDate() %></td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
