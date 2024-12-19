<%@ page contentType="text/html; charset=euc-kr"%>
<%@ page import="java.net.URLDecoder"%>
<%
Cookie[] cookies = request.getCookies();
%>
<html>
<head>
<title>쿠키목록</title>
</head>
<body>
	쿠키 목록
	<br>
	<%
	if (cookies != null && cookies.length > 0) {
		for (int i = 0; i < cookies.length; i++) {
	%>
	<%=cookies[i].getName()%>=<%=URLDecoder.decode(cookies[i].getValue(), "UTF-8")%><br>
	<%
		}
	} else {
	%>	
	쿠키가 존재하지 않습니다.
	<%
	}
	%>
</body>
</html>