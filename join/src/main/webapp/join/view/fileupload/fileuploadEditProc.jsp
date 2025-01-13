<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.dev.join.model.FileUploadVO, com.kh.dev.join.model.FileUploadDAO" %>

<%
	request.setCharacterEncoding("UTF-8"); // 한글 처리
	response.setContentType("text/html;charset=UTF-8");
    int id = Integer.parseInt(request.getParameter("id"));
    String title = request.getParameter("title");
    String content = request.getParameter("content");
    String password = request.getParameter("password");

    try {
        FileUploadDAO dao = new FileUploadDAO();
        if (dao.checkPassword(id, password)) {
            FileUploadVO vo = new FileUploadVO();
            vo.setId(id);
            vo.setTitle(title);
            vo.setContent(content);
            dao.updateFileUpload(vo);
            response.sendRedirect("fileuploadList.jsp");
        } else {
            out.println("<h3>비밀번호가 일치하지 않습니다.</h3>");
            out.println("<a href='fileuploadEdit.jsp?id=" + id + "'>다시 수정하기</a>");
        }
    } catch (SQLException e) {
        out.println("DB 업데이트 오류: " + e.getMessage());
    }
%>
