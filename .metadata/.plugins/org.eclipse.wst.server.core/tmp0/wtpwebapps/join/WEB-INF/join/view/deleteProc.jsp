<%@page import="com.kh.dev.join.model.BoardTwoDAO"%>
<%@page import="com.kh.dev.join.model.BoardTwoVO"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.sql.Timestamp"%>
<%
request.setCharacterEncoding("utf-8");

String numStr = request.getParameter("num");
int num = 0; // 기본값 설정
if (numStr != null && !numStr.isEmpty()) {
    try {
        num = Integer.parseInt(numStr);
    } catch (NumberFormatException e) {
        out.println("Invalid number format for 'num'. Please check your input.");
        return; // 오류 발생 시 추가 처리 중단
    }
} else {
    out.println("'num' parameter is missing.");
    return; // num 파라미터가 없을 경우 처리 중단
}

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
