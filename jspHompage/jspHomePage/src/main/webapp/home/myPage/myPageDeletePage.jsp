<%@ page contentType="text/html; charset=UTF-8"%>
<%
CustomerVO cvo = MyUtility.returnCvoBySession(session);
if (cvo == null) {
	response.sendRedirect(request.getContextPath() + "/mainPageAlert.do?status=8");//로그인 안되있을시 메인으로가서 8번 경고문 출력
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>메인 화면</title>
<script src="https://kit.fontawesome.com/6ff644124c.js"
	crossorigin="anonymous"></script>
<%@ include file="/home/css/commonCss.jsp"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/home/myPage/css/myPageWithdraw.css" />
</head>
<body>
	<header>
		<nav class="headerNav">
			<%@ include file="/home/headerNavSection.jsp"%>
		</nav>
	</header>
	<main class="myPage">
		<div class="delete-container">
			<h1>회원탈퇴</h1>
			<p>회원탈퇴를 위해 비밀번호를 입력하세요.</p>

			<form action="${pageContext.request.contextPath}/customerDelete.do"
				method="post" class="delete-form" name="withForm">
				<label for="password">비밀번호</label> <input type="password"
					id="password" name="pwd" required placeholder="비밀번호 입력" /> <input
					type="button" value="회원탈퇴" class="submit-btn" onclick="pwdCheck();" />
			</form>
		</div>
	</main>
	<hr>
	<footer>
		<%@ include file="/home/footerSection.jsp"%>
	</footer>
	<script
		src="${pageContext.request.contextPath}/home/myPage/js/myPageWithdraw.js"></script>
</body>

</html>

