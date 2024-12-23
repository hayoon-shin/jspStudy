<%@page import="co.kh.dev.home.model.CustomerDAO"%>
<%@page import="co.kh.dev.home.model.CustomerVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
CustomerDAO sDAO = CustomerDAO.getInstance(); 
request.setCharacterEncoding("UTF-8");
String id= request.getParameter("id");
CustomerVO cvo = new CustomerVO();
cvo.setId(id);
boolean checkFlag = sDAO.selectCountByIdDB(cvo);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="script.js"></script>
</head>
<body>
<h3>
<%
if(checkFlag){
%>
사용가능한 아이디 입니다.
<%	
}else{
%>
중복된 아이디 입니다.
<%} %>
</h3>
<button onClick="javascript:self.close()">닫기</button>
</body>
</html>