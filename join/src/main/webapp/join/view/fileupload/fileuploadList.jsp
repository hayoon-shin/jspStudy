<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.*, com.kh.dev.join.model.FileUploadDAO, com.kh.dev.join.model.FileUploadVO"%>
<!DOCTYPE html>
<html>
<head>
<title>게시글 목록</title>
<link rel="stylesheet" href="style.css">
<link rel="stylesheet" href="../../headerNav.css">

</head>
<body>
<%@ include file="/join/headerNav.jsp"%>
	<h1>게시글 목록</h1>
	
	

	<%
	int currentPage = 1; // 기본 페이지 번호
	int pageSize = 10; // 한 페이지에 표시할 게시글 수
	if (request.getParameter("page") != null) {
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
			if (currentPage < 1)
		currentPage = 1; // 페이지 번호가 1 미만이면 1로 설정
		} catch (NumberFormatException e) {
			currentPage = 1; // 잘못된 입력 처리
		}
	}

	FileUploadDAO dao = new FileUploadDAO();
	int totalCount = dao.getTotalCount(); // 전체 게시글 수
	int totalPages = (int) Math.ceil((double) totalCount / pageSize); // 전체 페이지 수

	List<FileUploadVO> list = dao.getFileUploadList(currentPage, pageSize);
	%>

	<table border="1">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
		<%
		for (FileUploadVO vo : list) {
		%>
		<tr>
			<td><%=vo.getId()%></td>
			<td><a href="fileuploadView.jsp?id=<%=vo.getId()%>"><%=vo.getTitle()%></a></td>
			<td><%=vo.getAuthor()%></td>
			<td><%=vo.getCreatedDate()%></td>
		</tr>
		<%
		}
		%>
	</table><br>
	<!-- 글쓰기 버튼 -->
    <div style="margin-bottom: 20px;">
        <a href="fileuploadWrite.jsp">
            <button type="button">글쓰기</button>
        </a>
    </div>
	<!-- 페이징 처리 -->
	<div class="pagination">
		<%
		if (totalPages > 1) {
			for (int i = 1; i <= totalPages; i++) {
				if (i == currentPage) {
		%>
		<strong>[<%=i%>]
		</strong>
		<%
		} else {
		%>
		<a href="fileuploadList.jsp?page=<%=i%>">[<%=i%>]
		</a>
		<%
		}
		}
		}
		%>
	</div>

</body>
</html>
