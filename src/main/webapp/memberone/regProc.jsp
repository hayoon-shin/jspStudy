<%@page import="com.kh.dev.memberone.model.StudentDAO"%>
<%@page import="java.rmi.StubNotFoundException"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!-- 1.사용자정보를 가져온다  -->
<!-- 2.curd  -->
<% 
 request.setCharacterEncoding("utf-8");
 StudentDAO sdao = new StudentDAO(); 
%>
<jsp:useBean id="svo" class="com.kh.dev.memberone.model.StudentVO" />
<jsp:setProperty name="svo" property="*" />
<% 
	boolean flag = sdao.insertDB(svo);
%>
<!-- 3.화면설계(자바코드에 해야되는데 - > jsp service함수에서 진행한다. -->
<html>
<head>
<title>회원가입 확인</title></head>
<link href="style.css" rel="stylesheet" type="text/css">
<body>
<br></br>
<main>
<%
if(flag){
	  out.println("<b>회원가입을 축하 드립니다.</b><br/>");
	  out.println("<a href=login.jsp>로그인</a>");
	}else{
	  out.println("<b>다시 입력하여 주십시오.</b><br/>");
	  out.println("<a href=regForm.jsp>다시 가입</a>");
	}
%>
</main>
</body>
</html>