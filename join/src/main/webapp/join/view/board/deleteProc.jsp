<%@page import="com.kh.dev.join.model.BoardTwoDAO"%>
<%@page import="com.kh.dev.join.model.BoardTwoVO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
int num = Integer.parseInt(request.getParameter("num"));
String pageNum = request.getParameter("pageNum");
String pass = request.getParameter("pass");
%>
<%
BoardTwoVO vo = new BoardTwoVO();
vo.setNum(num);
vo.setPass(pass);
BoardTwoDAO bdao = BoardTwoDAO.getInstance();
boolean flag = bdao.deleteDB(vo);
if (flag == true) {
%>
<meta http-equiv="Refresh" content="0;url=list.jsp?pageNum=<%=pageNum%>">
<%
} else {
%>
<script language="JavaScript">
	alert("비밀번호가 맞지 않습니다");
	history.go(-1);
</script>
<%
}
%>