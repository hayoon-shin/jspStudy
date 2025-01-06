<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.dev.join.model.FileUploadDAO" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    String password = request.getParameter("password");

    try {
        FileUploadDAO dao = new FileUploadDAO();
        if (dao.checkPassword(id, password)) {
            dao.deleteFileUpload(id);
            response.sendRedirect("fileuploadList.jsp");
        } else {
            out.println("<h3>비밀번호가 일치하지 않습니다.</h3>");
            out.println("<a href='fileuploadDelete.jsp?id=" + id + "'>다시 삭제하기</a>");
        }
    } catch (SQLException e) {
        out.println("DB 삭제 오류: " + e.getMessage());
    }
%>
