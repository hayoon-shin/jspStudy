<%@page import="java.net.URLEncoder"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!-- 1. 9가지 객체 중에 쿠키값을 가져온다. -->
<%
	//쿠키값으로 한글을 웹 브라우저에 보낼 때는 URLEncoder 사용해서 보낸다.
	Cookie cookie = new Cookie("name", URLEncoder.encode("신하윤","UTF-8"));
	//서버에서 클라이언트(웹브라우저) 전송한다.
	response.addCookie(cookie);
%>
<!DOCTYPE html>
<html>
<head><title>쿠키생성</title></head>
<body>
<%= cookie.getName() %> 쿠키의 값 = "<%= cookie.getValue() %>"
</body>
</html>
