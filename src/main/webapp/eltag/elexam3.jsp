<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<%
 request.setAttribute("name", "신하윤");
	request.setCharacterEncoding("UTF-8");
	SimpleDateFormat formatter = new SimpleDateFormat();
	Date date = new Date();
	%>
	
%>
<html>
<head><title>EL Object</title></head>
<body>
요청 URI: ${pageContext.request.requestURI} <br></br>
request의 name 속성: ${requestScope.name} <br></br>
code 파라미터: ${param.code} <br></br>
... 오늘은 <%=formatter.format(date)%>입니다.
</body>
</html>