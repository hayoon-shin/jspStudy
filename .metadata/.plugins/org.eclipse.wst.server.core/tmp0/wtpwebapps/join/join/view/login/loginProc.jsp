<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.kh.dev.join.model.UsersVO, com.kh.dev.join.model.UsersDAO" %>
<%
    // 사용자 입력값 가져오기
    String id = request.getParameter("id");
    String pwd = request.getParameter("pwd");

    UsersDAO dao = new UsersDAO();
    UsersVO user = dao.authenticateUser(id, pwd);
	
    if (user != null) {
        // 로그인 성공
        session.setAttribute("user", user); // 세션에 사용자 정보 저장
        response.sendRedirect("mypage.jsp"); // 마이페이지로 이동
    } else {
        // 로그인 실패
%>
        <script>
            alert("아이디 또는 비밀번호가 올바르지 않습니다.");
            history.back();
        </script>
<%
    }
%>
