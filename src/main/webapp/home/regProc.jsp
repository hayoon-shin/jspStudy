<%@page import="co.kh.dev.home.model.CustomerDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<% request.setCharacterEncoding("utf-8");
CustomerDAO cdao = CustomerDAO.getInstance();
%>
<jsp:useBean id="cvo" class="co.kh.dev.home.model.CustomerVO" />
<jsp:setProperty name="cvo" property="*" />
<% 
boolean flag = cdao.insertDB(cvo);
response.sendRedirect("/jspHomePage/mainPageAlertServlet.do?status="+((flag)?"6":"7"));
%>
<html>
<head>
<title>회원가입 확인</title></head>
<link href="style.css" rel="stylesheet" type="text/css">
<body bgcolor="#FFFFCC">
<br></br>
<main>
</main>
</body>
</html>