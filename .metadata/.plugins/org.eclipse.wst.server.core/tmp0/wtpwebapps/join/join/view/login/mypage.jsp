<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.kh.dev.join.model.UsersVO" %>
<%
	request.setCharacterEncoding("UTF-8");
    // 세션에서 사용자 정보 가져오기
    UsersVO user = (UsersVO) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp"); // 로그인하지 않은 상태에서 접근 시 로그인 페이지로 리다이렉트
        return;
    }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="mypage.css">
    <link rel="stylesheet" href="../../headerNav.css">
    <title>마이페이지</title>
</head>
<body>
<%@ include file="/join/headerNav.jsp"%>
    <div class="main_wrap">
        <form id="mypageForm" action="mypageProc.jsp" method="post">
            <h2>마이페이지</h2>

            <label for="id">아이디 (수정 불가)</label>
            <input type="text" id="id" name="id" value="<%= user.getId() %>" readonly>

            <label for="name">이름</label>
            <input type="text" id="name" name="name" value="<%= user.getName() %>" required>

            <label for="email">이메일</label>
            <input type="email" id="email" name="email" value="<%= user.getEmail() %>" required>

            <label for="phone">전화번호</label>
            <input type="text" id="phone1" name="phone1" value="<%= user.getPhone1() %>" required>
            <input type="text" id="phone2" name="phone2" value="<%= user.getPhone2() %>" required>
            <input type="text" id="phone3" name="phone3" value="<%= user.getPhone3() %>" required>

            <label for="gender">성별</label>
            <select id="gender" name="gender" required>
                <option value="male" <%= "male".equals(user.getGender()) ? "selected" : "" %>>남성</option>
                <option value="female" <%= "female".equals(user.getGender()) ? "selected" : "" %>>여성</option>
            </select>

             <!-- 정보 수정 버튼 -->
            <button type="submit">정보수정</button>

            <!-- 로그아웃 버튼 -->
            <button type="button" onclick="location.href='logout.jsp'" class="logout-button">로그아웃</button>
    </div>
</body>
</html>
