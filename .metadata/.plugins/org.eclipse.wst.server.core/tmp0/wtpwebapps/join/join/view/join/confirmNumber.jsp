<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <meta charset="UTF-8">
    <title>인증 결과</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin-top: 50px;
        }
        .success {
            color: green;
            font-size: 18px;
        }
        .error {
            color: red;
            font-size: 18px;
        }
        button {
            width: 220px;
            height: 35px;
            background-color: orange;
            color: white;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: darkorange;
        }
    </style>
</head>
<body>
    <h2>인증 결과</h2>

 <%
    // 세션에서 인증번호 가져오기
    String sessionCode = (String) session.getAttribute("verificationCode");
    String userCode = request.getParameter("verificationCodeInput");

    if (userCode != null && userCode.equals(sessionCode)) {
        // 인증 성공 시 register.jsp로 인증번호 전달
        response.sendRedirect("register.jsp?verifiedCode=" + userCode);
    } else {
        // 인증 실패 시 에러 메시지 표시
        out.println("<p style='color: red;'>인증번호가 일치하지 않습니다. 다시 시도해주세요.</p>");
    }
%>
</body>
</html>