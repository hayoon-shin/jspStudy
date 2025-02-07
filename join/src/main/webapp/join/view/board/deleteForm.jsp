<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="color.jsp"%>
<%
request.setCharacterEncoding("UTF-8");
int num = Integer.parseInt(request.getParameter("num"));
String pageNum = request.getParameter("pageNum");

%>
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../../headerNav.css">
<script language="javascript"
	src="script.js?timestamp=<%=System.currentTimeMillis()%>"></script>
</head>
<body>
<%@ include file="/join/headerNav.jsp"%>
    <main>
        <h1>게시글 삭제</h1>
<script>
    function validateDeleteForm() {
        const passField = document.getElementById("pass");
        if (!passField.value.trim()) {
            alert("비밀번호를 입력하세요.");
            passField.focus();
            return false;
        }
        return true;
    }
</script>
<form action="deleteProc.jsp" method="post" onsubmit="return validateDeleteForm();">
    		<input type="hidden" name="num" value="<%= num %>">
    		<input type="hidden" name="pageNum" value="<%= pageNum %>">

    		<label for="pass">비밀번호:</label>
    		<input type="password" id="pass" name="pass" required>
    
    		<button type="submit">삭제</button>
        </form>
        <button onclick="history.back()">취소</button>
    </main>
</body>
</html>

