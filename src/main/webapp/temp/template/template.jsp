<%@ page contentType = "text/html; charset=euc-kr" %>
<%
 String pageTitle = (String)request.getAttribute("PAGETITLE");
 String contentPage = (String)request.getAttribute("CONTENTPAGE");
%>
<html>
<head><title><%= pageTitle %></title></head>
<body>
<table width="400" border="1" cellpadding="2" cellspacing="0">
<tr>
 <td colspan="2">
 <jsp:include page="/temp/module/top.jsp" flush="false" />
 </td>
</tr>
<tr>
 <td width="100" valign="top">
	<jsp:include page="/temp/module/left.jsp" flush="false" />
 </td>
 <td width="300" valign="top">
 <!-- 내용 부분: 시작 -->

 <!-- 내용 부분: 끝 -->
 </td>
</tr>
<tr>
 <td colspan="2">
	<jsp:include page="/temp/module/bottom.jsp" flush="false" />
 </td>
</tr>
</table>
</body>
</html>