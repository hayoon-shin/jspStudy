<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="co.kh.dev.home.model.CustomerVO"%>
<%@page import="co.kh.dev.home.model.CustomerDAO"%>
<%@page import="co.kh.dev.common.MyUtility"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
String msg=(String)request.getAttribute("msg");
boolean alertFlag=(msg==null||msg.equals(""))?false:true;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/home/css/common.css" />
</head>
<body>
	<%@page import="co.kh.dev.home.model.CustomerVO"%>
	<%@page import="co.kh.dev.home.model.CustomerDAO"%>
	<%@page import="co.kh.dev.common.MyUtility"%>
	<%@ page contentType="text/html; charset=UTF-8"%>


	<script>
<%
if(alertFlag){
%>	
alert("<%=msg%>"); 
window.location.replace("<%=request.getContextPath()%>/home/loginPopup.jsp");
<%
}
CustomerVO cvo = MyUtility.returnCvoBySession(session);
if (cvo != null) {%>
		window.opener.location.reload();
		window.close();
	<%
}
%>
		
	<%if (request.getParameter("error") != null && request.getParameter("error").equals("2")) {%>
		alert("아이디 또는 비밀번호가 맞지 않습니다.");
		history.go(-1);
	<%} else {
	if (request.getParameter("error") != null && request.getParameter("error").equals("1")) {%>
		alert("비밀번호가 맞지 않습니다.");
		history.go(-1);
	<%}
}%>
		
	</script>
	<div class="loginSection">
		<article class="loginInput">
			<form method="post"
				action="/jspHomePage/BoardLoginPopupCheckServlet.do">
				<ul>
					<li><input type="text" name="id" id="id"
						placeholder=" 아이디 또는 전화번호" /></li>
					<li><input type="password" name="pwd" id="psw"
						placeholder=" 비밀번호" /></li>
				</ul>
				<!-- 상태유지 -->
				<div class="maint">
					<input type="checkbox" name="maint" class="maint" /> <label
						for="maint">로그인 상태 유지</label>
				</div>
				<!-- 로그인 버튼 -->
				<button type="submit">로그인</button>
			</form>
		</article>
		<nav class="loginNav">
			<ul style="display: flex">
				<li><a href="#">비밀번호 찾기</a></li>
				<li><a href="#">아이디 찾기</a></li>
				<li><a href="registPage.jsp">회원가입</a></li>
			</ul>
		</nav>

	</div>
</body>
</html>
