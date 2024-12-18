<%@page import="co.kh.dev.home.model.CustomerDAO"%>
<%@page import="co.kh.dev.home.model.CustomerVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <%
		if (session != null) {
			session.invalidate();
		}
		response.sendRedirect(request.getContextPath()+"/mainPageAlertServlet.do?status=2");
    %>

</head>
<body>

</body>
</html>