<%@page import="com.kh.dev.boardone.model.BoardDAO"%>
<%@page import="com.kh.dev.boardone.model.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="color.jsp"%>
<%
// 1. 페이징 기법 : 페이지 사이즈 : 1페이지에 10개만 보여줘 
int pageSize = 3;
// 2. 페이징 기법 : 페이지 번호 선택 (기본페이지는 1페이지다)
request.setCharacterEncoding("utf-8"); //한글일때만 의미가 있고, 페이징 넘버하는데는 의미가 없음
String pageNum = request.getParameter("pageNum");
if (pageNum == null) {
	pageNum = "1";
}
// 3. 현재 페이지를 설정하고, start와 end를 결정하는 것
int currentPage = Integer.parseInt(pageNum);
int start = (currentPage - 1) * pageSize + 1; // 4page 시작 보여줘 : (4-1)*10+1 => 31
int end = (currentPage) * pageSize; // 4page 끝번호 보여줘 : (4)*10 => 40
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>
<%
int number = 0;
//4. 해당된 페이지 10개를 가져온다.
ArrayList<BoardVO> boardList = null;
BoardDAO bdao = BoardDAO.getInstance();
//전체글갯수

int count = bdao.selectCountDB();
if (count > 0) {
	// 현재페이지 내용 10개만 가져온다.	
	boardList = bdao.selectStartEndDB(start, end);
}
// 5. 만약에 4페이지(31~40)를 가져왔다면 number = 40
number = count - (currentPage - 1) * pageSize; // 전체 갯수 100 1페이지 (100~91), 2페이지 (90~81)
%>
<!DOCTYPE html>
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="<%=bodyback_c%>">
	<main>
		<b>글목록(전체 글:<%=count%>)
		</b>
		<table width="700">
			<tr>
				<td align="right" bgcolor="<%=value_c%>"><a
					href="writeForm.jsp">글쓰기</a></td>
			</tr>
		</table>
		<%
		if (count == 0) {
		%>
		<table width="700" border="1" cellpadding="0" cellspacing="0">
			<tr>
				<td align="center">게시판에 저장된 글이 없습니다.</td>
		</table>
		<%
		} else {
		%>
		<table border="1" width="700" cellpadding="0" cellspacing="0"
			align="center">
			<tr height="30" bgcolor="<%=value_c%>">
				<td align="center" width="50">번 호</td>
				<td align="center" width="250">제 목</td>
				<td align="center" width="100">작성자</td>
				<td align="center" width="150">작성일</td>
				<td align="center" width="50">조 회</td>
				<td align="center" width="100">IP</td>
			</tr>
			<%
			for (BoardVO article : boardList) {
			%>
			<tr height="30">
				<td align="center" width="50"><%=number--%></td>
				<td width="250">
					<!-- 수정 <5> --> <a
					href="content.jsp?num=<%=article.getNum()%>&pageNum=1"> <!-- 수정<6> -->
						<%
						// 6. depth 값에 따라서 5배수 증가를 해서 화면에 보여줘야된다. 
						// 즉, depth가 1이면 길이는 5, depth가 2이면 길이는 10.
						int wid = 0;
						if (article.getDepth() > 0) {
							wid = 5 * (article.getDepth());
						%> <img src="images/level.gif" width="<%=wid%>" height="16">
						<img src="images/re.gif"> <%
 } else {
 %> <img src="images/level.gif" width="<%=wid%>" height="16"> <%
 }
 %> <%=article.getSubject()%></a> <%
 if (article.getReadcount() >= 20) {
 %> <img src="images/hot.gif" border="0" height="16"> <%
 }
 %>
				</td>
				<td align="center" width="100"><a
					href="mailto:<%=article.getEmail()%>"> <%=article.getWriter()%></a></td>
				<td align="center" width="150"><%=sdf.format(article.getRegdate())%></td>
				<td align="center" width="50"><%=article.getReadcount()%></td>
				<td align="center" width="100"><%=article.getIp()%></td>
			</tr>
			<%
			}
			%>
		</table>
		<%
		}
		%>
		<!-- 수정 <7> -->
		<%
		if (count > 0) {
			int pageBlock = 3;  // 보여줄 페이지
			int imsi = count % pageSize == 0 ? 0 : 1;
			int pageCount = count / pageSize + imsi;
			int startPage = (int) ((currentPage - 1) / pageBlock) * pageBlock + 1;
			int endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;
			if (startPage > pageBlock) {
		%>
		<a href="list.jsp?pageNum=<%=startPage - pageBlock%>">[이전]</a>
		<%
		}
		for (int i = startPage; i <= endPage; i++) {
			if(currentPage == i) {
		%>
				<a href="list.jsp?pageNum=<%= i %>"> [[<%= i %>]] </a>
		<% 	}else{ %>
				<a href="list.jsp?pageNum=<%= i %>"> [[<%= i %>]] </a>
	  <% } %>
		
		</a>
		<%
		}
		if (endPage < pageCount) {
		%>
		<a href="list.jsp?pageNum=<%=startPage + pageBlock%>">[다음]</a>
		<%
		}
		}
		%>
	</main>
</body>
</html>
