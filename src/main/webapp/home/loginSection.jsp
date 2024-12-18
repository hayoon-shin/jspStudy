<%@page import="co.kh.dev.home.model.CustomerVO"%>
<%@page import="co.kh.dev.home.model.CustomerDAO"%>
<%@page import="co.kh.dev.common.MyUtility"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<%
CustomerVO cvo = MyUtility.returnCvoBySession(session);
%>

<%
if (cvo == null) {
%>

<article class="loginInput">
	<form method="post" action="/jspHomePage/BoardLoginCheckServlet.do">
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

<%
} else {
%>

<!-- 로그인 성공 화면 -->
<table class="afterLoginPage">
	<tr>
		<td width="300" align="center"><%=cvo.getName()%> 님 로그인 되었습니다.</td>
	</tr>
</table>
<nav class="loginNav">
	<ul style="display: flex">
		<li><a href="myPage.jsp">마이페이지</a></li>
		<li><a href="logout.jsp">로그아웃</a></li>
	</ul>
</nav>

<%
}
%>

