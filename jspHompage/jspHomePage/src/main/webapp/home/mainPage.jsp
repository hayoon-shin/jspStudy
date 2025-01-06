<%@ page contentType="text/html; charset=UTF-8"%>
<%
 if (request.getAttribute("bList") == null) {
	response.sendRedirect(request.getContextPath() + "/mainPageAlert.do");
}
String msg = (String) request.getAttribute("msg");
boolean alertFlag = (boolean) request.getAttribute("alertFlag");
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
	href="${pageContext.request.contextPath}/home/css/MainPage.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/home/css/miniBoardSection.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/home/css/sideSection.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/home/css/miniShoppingSection.css" />
<%
if (alertFlag) {
%>
<script>
alert("<%=msg%>"); 
window.location.replace("${pageContext.request.contextPath}/mainPageAlert.do");
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
		<section class="carouselSection">
			<%@ include file="/home/carouselSection.jsp"%>
		</section>
	</header>
	<main class="mainPage">
		<section class="sideSection">
			<%@ include file="/home/sideSection.jsp"%>
		</section>
		<section class="mainSection">
			<!-- 게시판 요약 정보 -->
			<article class="miniBoardSection">
				<%@ include file="/home/miniBoardSection.jsp"%>
			</article>
			<!-- 상품 요약 정보 -->
			<article class="miniShoppingSection">
				<%@ include file="/home/miniShoppingSection.jsp"%>
			</article>
		</section>
		<section class="loginSection">
			<%@ include file="/home/loginSection.jsp"%>
		</section>
	</main>
	<hr>
	<footer>
		<%@ include file="/home/footerSection.jsp"%>
	</footer>
</body>

</html>

