<%@page import="java.util.List"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.dev.join.model.FileUploadVO, com.kh.dev.join.model.FileUploadDAO" %>

<%
    int id = Integer.parseInt(request.getParameter("id")); // 게시글 ID
    FileUploadVO vo = null;
    List<String> fileNames = null;

    try {
        FileUploadDAO dao = new FileUploadDAO();
        vo = dao.getFileUploadById(id); // 게시글 정보 가져오기
        fileNames = dao.getFileNamesByBoardId(id); // 파일 정보 가져오기
    } catch (Exception e) {
        out.println("데이터베이스 오류: " + e.getMessage());
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title><%= vo.getTitle() %></title>
</head>
<body>
    <h1>게시글 보기</h1>
    <p><strong>제목:</strong> <%= vo.getTitle() %></p>
    <p><strong>작성자:</strong> <%= vo.getAuthor() %></p>
    <p><strong>내용:</strong></p>
    <p><%= vo.getContent() %></p>

    <h3>첨부 파일</h3>
    <%
        if (fileNames != null && !fileNames.isEmpty()) {
            for (String fileName : fileNames) {
    %>
        <p>
            <a href="<%= request.getContextPath() %>/uploads/<%= fileName %>" target="_blank"><%= fileName %></a>
        </p>
    <%
            }
        } else {
    %>
        <p>첨부된 파일이 없습니다.</p>
    <%
        }
    %>

    <a href="fileuploadList.jsp">목록으로</a>
</body>
</html>