<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="login.css">
    <link rel="stylesheet" href="../../headerNav.css">
    <title>로그인</title>
</head>
<body>
	<%@ include file="/join/headerNav.jsp"%>
    <div class="main_wrap">
        <form id="loginForm" action="loginProc.jsp" method="post">
            <h2>로그인</h2>
            
            <label for="id">아이디</label>
            <input type="text" id="id" name="id" placeholder="아이디를 입력하세요" required>

            <label for="pwd">비밀번호</label>
            <input type="password" id="pwd" name="pwd" placeholder="비밀번호를 입력하세요" required>

            <button type="submit">로그인</button>
            <div class="links">
                <a href="../../view/join/register.jsp">회원가입</a> |
                <a href="forgotPassword.jsp">비밀번호 찾기</a>
            </div>
        </form>
    </div>
</body>
</html>
