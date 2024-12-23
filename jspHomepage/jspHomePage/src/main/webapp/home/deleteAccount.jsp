<%@page import="co.kh.dev.home.model.CustomerDAO"%>
<%@page import="co.kh.dev.common.MyUtility"%>
<%@page import="co.kh.dev.home.model.CustomerVO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
CustomerVO cvo = MyUtility.returnCvoBySession(session);
CustomerDAO cDAO = CustomerDAO.getInstance();
if (cvo == null) {
	response.sendRedirect(request.getContextPath() + "/home/mainPage.jsp");
	return;
}
if(cvo.getPwd().equals(request.getParameter("pwd"))){
cDAO.deleteDB(cvo);
session.invalidate();
response.sendRedirect(request.getContextPath() + "/mainPageAlertServlet.do?status=1");
}else{
response.sendRedirect(request.getContextPath() + "/myPageAlertServlet.do?status=3");
}
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