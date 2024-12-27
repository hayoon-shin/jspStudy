<%@page import="com.kh.dev.join.model.BoardTwoDAO"%>
<%@page import="com.kh.dev.join.model.BoardTwoVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    // 안전한 값 가져오기와 기본값 설정
    // 보여줄 리스트 해당
    Integer currentPageObj = (Integer) request.getAttribute("currentPage");
    int currentPage = currentPageObj != null ? currentPageObj : 1; // 기본값: 1

    Integer startRowObj = (Integer) request.getAttribute("startRow");
    int startRow = startRowObj != null ? startRowObj : 0; // 기본값: 0

    Integer endRowObj = (Integer) request.getAttribute("endRow");
    int endRow = endRowObj != null ? endRowObj : 0; // 기본값: 0

    Integer countObj = (Integer) request.getAttribute("count");
    int count = countObj != null ? countObj : 0; // 기본값: 0

    Integer pageSizeObj = (Integer) request.getAttribute("pageSize");
    int pageSize = pageSizeObj != null ? pageSizeObj : 10; // 기본값: 10

    Integer numberObj = (Integer) request.getAttribute("number");
    int number = numberObj != null ? numberObj : 0; // 기본값: 0

    // 리스트 기본값 처리
    List<BoardTwoVO> articleList = (List<BoardTwoVO>) request.getAttribute("articleList");

    // 날짜 포맷
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
%>
<!DOCTYPE html>
<html>
<head>
<title>게시판</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
<main>
	<b>글목록(전체 글:<%=count%>)</b>
<table width="700">
  <tr>
    <td align="right">
    <a href="writeForm.do">글쓰기</a>
    </td>
  </tr>
</table>
<%
    if (count == 0) {
%>
<table width="700" border="1" cellpadding="0" cellspacing="0">
<tr>
    <td align="center">
    게시판에 저장된 글이 없습니다.
    </td>
</table>
<%  } else {    %>
<table border="1" width="700" cellpadding="0" cellspacing="0" align="center"> 
    <tr height="30"> 
      <td align="center"  width="50"  >번 호</td> 
      <td align="center"  width="250" >제   목</td> 
      <td align="center"  width="100" >작성자</td>
      <td align="center"  width="150" >작성일</td> 
      <td align="center"  width="50" >조 회</td> 
      <td align="center"  width="100" >IP</td>    
    </tr>
<%  
    for ( BoardTwoVO article : articleList) {
%>
   <tr height="30">
    <td align="center"  width="50" > <%= number--%></td>
    <td  width="250" >
    <!-- 수정 <5> -->
    <a href="content.do?num=<%=article.getNum()%>&pageNum=1">
	 <!-- 수정<6> -->
<%
			//6. depth 값에 따라서 5배수 증가를 해서 화면에 보여줘야된다.
			//depth : 1 => 길이 : 5, 2 => 10
      int wid=0; 
      if(article.getDepth()>0){
        wid=5*(article.getDepth());
%>
       <img src="images/level.gif" width="<%=wid%>" height="16">
       <img src="images/re.gif">
<%    }else{%>
       <img src="images/level.gif" width="<%=wid%>" height="16">
<%}%>
          <%=article.getSubject()%></a> 
          <% if(article.getReadcount()>=20){%>
         <img src="images/hot.gif" border="0"  height="16"><%}%> </td>
    <td align="center"  width="100"> 
       <a href="mailto:<%=article.getEmail()%>">
		<%=article.getWriter()%></a></td>
    <td align="center"  width="150">
		<%= sdf.format(article.getRegdate())%></td>
    <td align="center"  width="50"><%=article.getReadcount()%></td>
    <td align="center" width="100" ><%=article.getIp()%></td>
  </tr>
     <%}%>
<%}%>
</table>
</main>
<br><br>
<!-- 수정 <7> -->
<div align="center">
<% 	//페이지번호
    if (count > 0) {
      int pageBlock = 3;    //보여줄페이지
      int imsi = count % pageSize == 0 ? 0 : 1;
      int pageCount = count / pageSize + imsi;
      int startPage = (int)((currentPage-1)/pageBlock)*pageBlock + 1;
      int endPage = startPage + pageBlock - 1;
      if (endPage > pageCount) endPage = pageCount;  
      if (startPage > pageBlock) {    
%>
      <a href="list.do?pageNum=<%=startPage-pageBlock%>">[이전]</a>
<%    }
      for (int i = startPage ; i <= endPage ; i++) {
    	  if(currentPage == i){
%>
    		  <a href="list.do?pageNum=<%= i %>">[{<%= i %>}]</a>
<%    	}else{ %>
    		   <a href="list.do?pageNum=<%= i %>">[<%= i %>]</a>
<%  	  } %>
       
<%
      }
      if (endPage < pageCount) {  %>
      <a href="list.do?pageNum=<%=startPage+pageBlock%>">[다음]</a>
<%
        }
    }
%>
</div>
</body>
</html>