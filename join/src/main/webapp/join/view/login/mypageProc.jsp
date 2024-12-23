<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.kh.dev.join.model.UsersVO, com.kh.dev.join.model.UsersDAO" %>
<%
	request.setCharacterEncoding("UTF-8");
    // 사용자 입력 정보 가져오기
    String userId = request.getParameter("id");
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String phone1 = request.getParameter("phone1");
    String phone2 = request.getParameter("phone2");
    String phone3 = request.getParameter("phone3");
    String gender = request.getParameter("gender");

    UsersVO user = new UsersVO();
    user.setId(userId);
    user.setName(name);
    user.setEmail(email);
    user.setPhone1(phone1);
    user.setPhone2(phone2);
    user.setPhone3(phone3);
    user.setGender(gender);

    UsersDAO dao = new UsersDAO();
    boolean isUpdated = dao.updateUser(user);

    if (isUpdated) {
        // 정보 수정 성공
        session.setAttribute("user", user); // 세션에 업데이트된 정보 저장
        response.sendRedirect("mypage.jsp");
    } else {
        // 정보 수정 실패
%>
        <script>
            alert("정보 수정에 실패했습니다. 다시 시도해주세요.");
            history.back();
        </script>
<%
    }
%>
