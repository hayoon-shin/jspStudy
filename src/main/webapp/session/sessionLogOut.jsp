<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<% session.invalidate(); %>
<script>
 alert("로그아웃 되었습니다.");
location.href="sessionMemberLogIn.jsp";
</script>