<%@page import="co.kh.dev.home.model.CommentVO"%>
<%@page import="co.kh.dev.common.MyUtility"%>
<%@page import="co.kh.dev.home.model.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="co.kh.dev.home.model.CustomerVO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<article class="boardArticle">
	<h3>게시판 요약</h3>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성일자</th>
			</tr>
		</thead>
		<tbody>
	
			<c:set var="count" value="0" />
			<c:forEach var="data" items="${bList}">
				<c:set var="count" value="${count + 1}" />
				<tr>
					<td class="tbNum">${count}</td>
					<td class="tbMain"><a
						href=" ${pageContext.request.contextPath}/boardListSelect.do?no=${data.no}&count=1">${data.title}</a></td>
					<td class="tbDate">${data.subdate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="${pageContext.request.contextPath}/boardSelect.do"
		class="moreButton">더보기</a>
</article>

