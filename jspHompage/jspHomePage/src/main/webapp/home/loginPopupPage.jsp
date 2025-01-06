
<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="co.kh.dev.common.MyUtility"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/home/css/common.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/home/css/loginPopup.css" />
</head>
<body>
	<c:choose>
		<c:when test="${not empty msg}">
			<script>
				alert("${msg}");
				window.location
						.replace("${pageContext.request.contextPath}/home/loginPopupPage.jsp");
			</script>
		</c:when>
	</c:choose>

	<c:if test="${not empty sessionScope.cvo}">
		<script>
			window.opener.location.reload();
			window.close();
		</script>
	</c:if>

	<c:choose>
		<c:when test="${param.error == '2'}">
			<script>
				alert("아이디 또는 비밀번호가 맞지 않습니다.");
				history.go(-1);
			</script>
		</c:when>
		<c:when test="${param.error == '1'}">
			<script>
				alert("비밀번호가 맞지 않습니다.");
				history.go(-1);
			</script>
		</c:when>
	</c:choose>

	<div class="loginSection">
		<article class="loginInput">
			<form method="post"
				action="${pageContext.request.contextPath}/customerPopLoginCheck.do">
				<ul>
					<li><input type="text" name="id" id="id"
						placeholder=" 아이디 또는 전화번호"
						value="<c:out value='${cookie.id.value}'/>" /></li>
					<li><input type="password" name="pwd" id="psw"
						placeholder=" 비밀번호" /></li>
				</ul>
				<!-- 상태유지 -->
				<div class="maint">
					<input type="checkbox" name="maint" class="maint"
						<c:if test="${not empty cookie.id}">checked</c:if>> <label
						for="maint">아이디 저장</label>
				</div>
				<!-- 로그인 버튼 -->
				<button type="submit">로그인</button>
			</form>
		</article>
		<nav class="loginNav">
			<ul style="display: flex">
				<li><a href="customerRegistPage.jsp">회원가입</a></li>
			</ul>
		</nav>
	</div>
</body>
</html>
