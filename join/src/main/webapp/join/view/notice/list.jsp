<%@page import="com.kh.dev.join.model.UsersVO"%>
<%@page import="com.kh.dev.join.model.UsersDAO"%>
<%@page import="com.kh.dev.join.model.NoticeVO"%>
<%@page import="com.kh.dev.join.model.NoticeDAO"%>
<%@page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%

	UsersVO userVO = (UsersVO) session.getAttribute("user");
		String userId = null;

	if (userVO != null) {
    	userId = userVO.getId(); // UsersVO 객체에서 ID 가져오기
	}
    // 페이징 변수
    String pageNum = request.getParameter("pageNum");
    int currentPage;
    try {
        currentPage = (pageNum != null && !pageNum.isEmpty()) ? Integer.parseInt(pageNum) : 1;
    } catch (NumberFormatException e) {
        currentPage = 1; // 잘못된 값이 들어오면 기본값 설정
    }

    int pageSize = 10;
    int startRow = (currentPage - 1) * pageSize + 1;
    int endRow = startRow + pageSize - 1;

    // DAO 호출
    NoticeDAO noticeDAO = NoticeDAO.getInstance();
    int count = noticeDAO.selectCountDB();
    ArrayList<NoticeVO> articleList = noticeDAO.selectStartEndDB(startRow, endRow);

    // 페이징 계산
    int pageCount = (int) Math.ceil((double) count / pageSize);
    int startPage = ((currentPage - 1) / pageSize) * pageSize + 1;
    int endPage = startPage + pageSize - 1;
    if (endPage > pageCount) {
        endPage = pageCount;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>게시판</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="stylesheet" href="../../headerNav.css">
</head>
<body>
<%@ include file="/join/headerNav.jsp"%>
	<main>
    <h1>공지사항</h1>
    <table border="1">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회수</th>
        </tr>
        <% if (count == 0) { %>
            <tr>
                <td colspan="5">게시판에 저장된 글이 없습니다.</td>
            </tr>
        <% } else {
            int number = count - startRow + 1;
            for (NoticeVO article : articleList) { %>
            <tr>
                <td><%= number-- %></td>
                <td><a href="content.jsp?num=<%= article.getNum() %>"><%= article.getSubject() %></a></td>
                <td><%= article.getWriter() %></td>
                <td><%= article.getRegdate() %></td>
                <td><%= article.getReadcount() %></td>
            </tr>
        <% } } %>
    </table>
	<!-- 관리자만 글쓰기 버튼 표시 -->
        <% if ("admin".equals(userId)) { %>
            <div class="button-group">
                <a href="writeForm.jsp" class="button">글쓰기</a>
            </div>
        <% } else { %>
        <p>공지사항은 관리자만 작성할 수 있습니다. </p>
        <% } %>
    <!-- 페이징 -->
    <div class="pagination">
        <% if (startPage > 1) { %>
            <a href="list.jsp?pageNum=<%= startPage - 1 %>">[이전]</a>
        <% }
        for (int i = startPage; i <= endPage; i++) { %>
            <a href="list.jsp?pageNum=<%= i %>"><%= i %></a>
        <% }
        if (endPage < pageCount) { %>
            <a href="list.jsp?pageNum=<%= startPage + 1 %>">[다음]</a>
        <% } %>
    </div>
    </main>
</body>
</html>
