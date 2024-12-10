<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setAttribute("PAGETITLE","정보보기");
	request.setAttribute("CONTENTPAGE","/temp/template/info_view.jsp");
	RequestDispatcher rd = request.getRequestDispatcher("/temp/template/template.jsp");
	rd.forward(request,response);

%>