<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="color.jsp"%>
<%
	//새로운글로 입력하던지(num=0, ref=0, step=0, depth=0) ,
	//부모글을 누르고 입력한다(num=부모값, ref=부모값, step=부모값, depth=부모값).
  int num=0, ref=0, step=0, depth=0;
  try{  
    if(request.getParameter("num")!=null){
			num = Integer.parseInt(request.getParameter("num"));
			ref = Integer.parseInt(request.getParameter("ref"));
			step = Integer.parseInt(request.getParameter("step"));
			depth = Integer.parseInt(request.getParameter("depth"));
	  }
%>
<html>
<head>
<title>My Board</title>
<link href="style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="../../headerNav.css">
<script language="javascript"
	src="script.js?timestamp=<%= System.currentTimeMillis() %>"></script>
</head>
<!--// 나중에 새글 답별글 구분하는 코드 추가 <1> -->
<body bgcolor="<%=bodyback_c%>">
<%@ include file="/join/headerNav.jsp"%>
	<main>
		<h1>글쓰기</h1>
	<br></br>
	<form method="post" name="writeForm" action="writeProc.jsp"
		onsubmit="return writeSave()">
		<input type="hidden" name="num" value="<%=num%>">
		<input type="hidden" name="ref" value="<%=ref%>">
		<input type="hidden" name="step" value="<%=step%>">
		<input type="hidden" name="depth" value="<%=depth%>">  
		<label for="writer">작성자</label>
            <input type="text" id="writer" name="writer" required>

            <label for="subject">제목</label>
            <td width="330">
                <%
                    if (request.getParameter("num") == null) {
                %>
                    <input type="text" id="subject" size="50" maxlength="50" name="subject" required>
                <%
                    } else {
                %>
                    <input type="text" id="subject" size="50" maxlength="50" name="subject" value="[답변]" required>
                <%
                    }
                %>
            </td>

            <label for="content">내용</label>
            <textarea id="content" name="content" rows="10" required></textarea>
			<label for="email">이메일</label>
			<input type="email" id="email" name="email" required>
			<label for="pass">비밀번호</label>
            <input type="password" id="pass" name="pass" required>

            <div class="button-group">
                <button type="submit">작성</button>
                <button type="button" onclick="location.href='list.jsp'">목록</button>
            </div>
        </form>
    </main>
</body>
</html>
<%
	}catch(Exception e){} 
%>