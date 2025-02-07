<%@ page contentType="text/html; charset=UTF-8"%>
<%
CustomerVO cvo = MyUtility.returnCvoBySession(session);
if (cvo == null) {
	response.sendRedirect(request.getContextPath() + "/mainPageAlert.do?status=8");//로그인 안되있을시 메인으로가서 8번 경고문 출력
	return;
}
String msg = (String) request.getAttribute("msg");
boolean alertFlag=(request.getAttribute("alertFlag")==null)?false:(boolean)request.getAttribute("alertFlag");
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
	href="${pageContext.request.contextPath}/home/myPage/css/myPage.css" />

<%
if (alertFlag) {
System.out.println(msg);
%>
<script>
alert("<%=msg%>"); 
window.location.replace("${pageContext.request.contextPath}/home/myPage/myPage.jsp");
</script>
<%
}
%>

</head>
<body>
	<header>
		<nav class="headerNav">
			<%@ include file="/home/headerNavSection.jsp"%>
		</nav>
	</header>
	<main class="myPage">
		<div class="myPageContainer">
			<h2>마이페이지</h2>
			<div class="myPageMenu">
				<div class="menuItem"
					onclick="location.href='${pageContext.request.contextPath}/home/myPage/myPageUpdatePage.jsp'">
					<i class="fas fa-user-edit"></i> <a>회원 정보 수정</a>
				</div>
				<div class="menuItem"
					onclick="location.href='${pageContext.request.contextPath}/home/myPage/myPageDeletePage.jsp'">
					<i class="fas fa-user-times"></i> <a href="#">회원 탈퇴</a>
				</div>
				<div class="menuItem">
					<i class="fas fa-file-alt"></i> <a href="#">내가 쓴 게시글</a>
				</div>
				<div class="menuItem" onclick="location.href='${pageContext.request.contextPath}/shopCartSelect.do'">
					<i class="fas fa-shopping-cart"></i> <a href="#">장바구니 확인</a>
				</div>
			</div>
		</div>
	</main>
	<hr>
	<footer>
		<%@ include file="/home/footerSection.jsp"%>
	</footer>
</body>

</html>

