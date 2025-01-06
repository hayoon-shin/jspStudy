<%@page import="com.kh.dev.join.model.UsersDAO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<% 
 request.setCharacterEncoding("utf-8");
 UsersDAO udao = UsersDAO.getInstance(); 
%>
<jsp:useBean id="uvo" class="com.kh.dev.join.model.UsersVO" />
<jsp:setProperty name="uvo" property="*" />
<% 
	boolean flag = udao.insertDB(uvo);
%>
<html>
<head>
<title>회원가입 확인</title></head>
<link href="style.css" rel="stylesheet" type="text/css">
<body>
<%
if(flag){
	  out.println("<b>회원가입을 축하 드립니다.</b><br/>");
	  out.println("<a href=../login/login.jsp>로그인</a>");
	}else{
	  out.println("<b>다시 입력하여 주십시오.</b><br/>");
	  out.println("<a href=register.jsp>다시 가입</a>");
	}
%>
</body>
</html>