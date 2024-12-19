<%@ page contentType="text/html; charset=UTF-8"%>
<!-- 1.사용자정보를 가져온다  -->
<%
request.setCharacterEncoding("UTF-8");
%>
<!-- 2.curd  -->
<jsp:useBean id="sdao" class="com.kh.dev.memberone.model.StudentDAO" />
<jsp:useBean id="svo" class="com.kh.dev.memberone.model.StudentVO">
	<jsp:setProperty name="svo" property="*" />
</jsp:useBean>
<%
String id = (String) session.getAttribute("id");
svo.setId(id);
boolean flag = sdao.updateDB(svo);
%>
<!-- 3.화면설계(자바코드에 해야되는데 - > jsp service함수에서 진행한다. -->
<html>
<head>
<title>Update Process</title>
</head>
<meta http-equiv="Refresh" content="3;url=login.jsp">
<link href="style.css" type="text/css" rel="stylesheet" />
<body>
	<main>
<%
	if(flag == true){
		session.setAttribute("pass", svo.getPass());
%>
		<p> 입력하신 내용대로 <b>회원정보가 수정 되었습니다.</b><br></br>
			3초후에 Logon Page로 이동합니다
		</p>
<%
	}else{
%>
		<p> 입력하신 내용대로 <b>회원정보가 수정 안되었습니다.</b><br></br>
			3초후에 Logon Page로 이동합니다
		</p>
<%
	}
%>
		
	</main>
</body>
</html>