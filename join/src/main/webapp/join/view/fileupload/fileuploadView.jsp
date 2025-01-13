<%@page import="java.util.List"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.dev.join.model.FileUploadVO, com.kh.dev.join.model.FileUploadDAO" %>

<%
	request.setCharacterEncoding("UTF-8"); // 한글 처리
	response.setContentType("text/html;charset=UTF-8");
    int id = Integer.parseInt(request.getParameter("id")); // 게시글 ID
    FileUploadVO vo = null;
    List<String> fileNames = null;

    try {
        FileUploadDAO dao = new FileUploadDAO();
        vo = dao.getFileUploadById(id); // 게시글 정보 가져오기
        fileNames = dao.getFileNamesByBoardId(id); // 파일 정보 가져오기
    } catch (Exception e) {
        out.println("데이터베이스 오류: " + e.getMessage());
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title><%= vo.getTitle() %></title>
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="../../headerNav.css">
    
</head>
<body>
<%@ include file="/join/headerNav.jsp"%>
	<h1>게시글 보기</h1>
	<!-- 게시글 테이블 -->
        <table class="board-table">
            <tr>
                <th>제목</th>
                <td><%= vo.getTitle() %></td>
            </tr>
            <tr>
                <th>작성자</th>
                <td><%= vo.getAuthor() %></td>
            </tr>
            <tr>
                <th>작성일</th>
                <td><%= vo.getCreatedDate() %></td>
            </tr>
            <tr>
                <th>내용</th>
                <td><pre style="white-space: pre-wrap;"><%= vo.getContent() %></pre></td>
            </tr>
        
			<tr>
				<th>첨부 파일</th>
				<td>
    <%
        if (fileNames != null && !fileNames.isEmpty()) {
            for (String fileName : fileNames) {
    %>
        <p>
            <a href="<%= request.getContextPath() %>/uploads/<%= fileName %>" target="_blank"><%= fileName %></a>
        </p>
    <%
            }
        } else {
    %>
        <p>첨부된 파일이 없습니다.</p>
    <%
        }
    %>
    		</td>
    	</tr>
    </table>
	<!-- 수정 및 삭제 버튼 -->
    <div style="margin-top: 20px;">
        <a href="fileuploadEdit.jsp?id=<%= id %>">
            <button type="button">수정</button>
        </a>
        <a href="fileuploadDelete.jsp?id=<%= id %>">
            <button type="button">삭제</button>
        </a>
        <a href="fileuploadList.jsp">
            <button type="button">목록으로</button>
        </a>
    </div>
        
</body>
</html>