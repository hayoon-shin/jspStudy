<%@page import="co.kh.dev.home.model.CommentDAO"%>
<%@page import="co.kh.dev.home.model.CommentVO"%>
<%@page import="co.kh.dev.home.model.BoardDAO"%>
<%@page import="co.kh.dev.home.model.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%
CustomerVO cvo = MyUtility.returnCvoBySession(session);
ArrayList<CommentVO> cmList = (ArrayList<CommentVO>) request.getAttribute("cmList");
BoardVO bvo = (BoardVO) request.getAttribute("bvo");
if (bvo == null) {
	response.sendRedirect("/jspHomePage/home/boardPage.jsp");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>메인 화면</title>
<script src="https://kit.fontawesome.com/6ff644124c.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/home/css/carouselSection.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/home/css/common.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/home/css/headerNav.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/home/css/boardContentPage.css" />
</head>
<body>
	<header>
		<nav class="headerNav">
			<%@ include file="/home/headerNav.jsp"%>
		</nav>
	</header>
	<main class="boardContentPage">
		<section class="boardView">
			<h2>게시글 상세 조회</h2>
			<!-- 게시글 상세 정보 -->
			<div class="postDetail">
				<table>
					<tr>
						<th>제목</th>
						<td id="postTitle"><%=bvo.getTitle()%></td>
					</tr>
					<tr>
						<th>글쓴이</th>
						<td id="postWriter"><%=bvo.getCustomerId()%></td>
					</tr>
					<tr>
						<th>작성일</th>
						<td id="postDate"><%=bvo.getSubdate()%></td>
					</tr>
					<tr>
						<th>조회수</th>
						<td id="postDate"><%=bvo.getCount()%></td>
					</tr>
				</table>
				<div id="postContent"><%=bvo.getContent()%></div>

				<div
					class="postActions<%=(cvo != null && bvo.getCustomerId().equals(cvo.getId())) ? "" : " dispNone"%>">
					<form method="post"
						action="/jspHomePage/home/boardContentModifyPage.jsp">
						<input type="hidden" name="no" value="<%=bvo.getNo()%>"> <input
							type="hidden" name="title" value="<%=bvo.getTitle()%>"> <input
							type="hidden" name="content" value="<%=bvo.getContent()%>">
						<button type="submit" class="editBtn">수정</button>
					</form>
					<button type="button" class="deleteBtn"
						onclick="deletePost(<%=bvo.getNo()%>)">삭제</button>
				</div>
			</div>
		</section>

		<!-- 댓글 작성 폼 -->
		<section class="commentForm<%=(cvo != null) ? "" : " dispNone"%>">
			<h3>댓글 작성</h3>
			<form method="post"
				action="/jspHomePage/boardCommentInsertServlet.do">
				<input type="hidden" name="boardNo" value="<%=bvo.getNo()%>" />
				<textarea name="content" placeholder="댓글을 입력하세요" required></textarea>
				<button type="submit">댓글 작성</button>
			</form>
		</section>

		<!-- 댓글 목록 (대댓글 포함) -->
		<section class="commentList">
			<h3>댓글 목록</h3>

			<%
			/*  List<CommentVO> comments = (List<CommentVO>) request.getAttribute("comments"); */
			for (CommentVO cmvo : cmList) {
				String indent = "margin-left: " + (cmvo.getDepth() * 30) + "px;";
			%>

			<!-- 댓글 블록 -->
			<div class="comment" style="<%=indent%>">
				<p class="commentInfo">    
					<strong><%=cmvo.getCustomerId()%></strong> <span
						class="commentDate"><%=cmvo.getSubdate()%></span>
				</p>
				<p class="commentContent"><%=cmvo.getContent()%></p>
				<div class="commentActions<%=(cvo != null) ? "" : " dispNone"%>">
					<button class="commentReplyBtn" onclick="toggleDisp('togle<%=cmvo.getNo()%>');">답글</button>
					<button
						class="commentEditBtn <%=(cvo != null && cmvo.getCustomerId().equals(cvo.getId())) ? "" : " dispNone"%>">수정</button>
					<button
						class="commentDeleteBtn <%=(cvo != null && cmvo.getCustomerId().equals(cvo.getId())) ? "" : " dispNone"%>">삭제</button>
				</div>
			</div>
			<div class="reCommentForm dispNone" id="togle<%=cmvo.getNo()%>">
					<form action="/jspHomePage/boardCommentInsertServlet.do" method="post">
						<input type="hidden" name="boardNo" value="<%=bvo.getNo() %>" /> 
						<input type="hidden" name="parentNo" value="<%=cmvo.getNo() %>" />
						<input type="hidden" name="depth" value="<%=cmvo.getDepth() %>" />
						<textarea name="content" placeholder="답글을 입력하세요" required></textarea>
						<button type="submit">작성</button>
					</form>
			</div>
			<%
			}
			%>
		</section>
		<div class="toList">
		<button onclick="window.location.href='/jspHomePage/boardListServlet.do'">목록으로</button>
		</div>
	</main>

	<hr>
	<footer>
		<%@ include file="/home/footer.jsp"%>
	</footer>
	<script src="<%=request.getContextPath()%>/home/js/common.js"></script>
	<script src="<%=request.getContextPath()%>/home/js/boardContentPage.js"></script>
</body>
</html>