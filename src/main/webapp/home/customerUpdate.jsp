<%@page import="co.kh.dev.home.model.CustomerDAO"%>
<%@page import="co.kh.dev.common.MyUtility"%>
<%@page import="co.kh.dev.home.model.CustomerVO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");;
CustomerVO cvo = MyUtility.returnCvoBySession(session);
String status=null;
if (cvo == null) {
	response.sendRedirect(request.getContextPath() + "/home/mainPage.jsp");
	return;
}
CustomerDAO cDAO = CustomerDAO.getInstance();
%>
<jsp:useBean id="cvo1" class="co.kh.dev.home.model.CustomerVO" />
<jsp:setProperty name="cvo1" property="*" />
<%

boolean flag = cDAO.updateDB(cvo1);
if (flag) {
status="1";
} else {
status="2";
}
response.sendRedirect(request.getContextPath() + "/myPageAlertServlet.do?status="+status);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
</body>
</html>