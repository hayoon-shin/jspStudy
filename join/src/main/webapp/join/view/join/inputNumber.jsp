<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // 인증번호 생성
    String generatedCode = String.format("%06d", (int)(Math.random() * 1000000)); // 6자리 인증번호 생성

    // 세션에 인증번호 저장
    session.setAttribute("verificationCode", generatedCode);

    // 생성된 인증번호를 콘솔에 출력 (디버깅용, 실제 서비스에서는 제거해야 함)
    System.out.println("Generated Code: " + generatedCode);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>인증번호 입력</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
            margin-top: 50px;
        }
        input {
            width: 200px;
            height: 30px;
            text-align: center;
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
    <h3>인증번호 입력</h3>
    <p>휴대전화: <%= request.getParameter("phone") %></p>
    <label for="verificationCode">인증번호:</label>
    <input type="text" id="verificationCode" placeholder="인증번호 입력">
    <button onclick="sendVerificationCode()">확인</button>

    <script>
        function sendVerificationCode() {
            let verificationCode = document.querySelector('#verificationCode').value;

            if (verificationCode === "") {
                alert("인증번호를 입력해 주세요.");
                document.querySelector('#verificationCode').focus();
            } else {
                // 부모 페이지에 메시지 전달
                window.opener.postMessage({
                    type: "setVerificationCode",
                    verificationCode: verificationCode
                }, window.location.origin);

                // 현재 창 닫기
                window.close();
            }
        }
    </script>
</body>
</html>