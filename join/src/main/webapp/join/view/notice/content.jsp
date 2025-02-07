<%@page import="com.kh.dev.join.model.NoticeDAO"%>
<%@page import="com.kh.dev.join.model.NoticeVO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="color.jsp"%>
<%
request.setCharacterEncoding("UTF-8");
int num = Integer.parseInt(request.getParameter("num"));
String pageNum = request.getParameter("pageNum");
NoticeVO vo = new NoticeVO();
vo.setNum(num);
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

try {
    NoticeDAO bdao = NoticeDAO.getInstance();
    NoticeVO bvo = bdao.selectBoardDB(vo);

    // 데이터 JSP에 전달
    request.setAttribute("content", bvo);

    int _num = bvo.getNum();
    int ref = bvo.getRef();
    int step = bvo.getStep();
    int depth = bvo.getDepth();
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="../../headerNav.css">
    <title>게시글 상세 보기</title>
</head>
<body>
<%@ include file="/join/headerNav.jsp"%>
    <main>
        <h1>게시글 상세 보기</h1>
        <table>
            <tr>
                <th>작성자</th>
                <td>${content.writer}</td>
            </tr>
            <tr>
                <th>제목</th>
                <td>${content.subject}</td>
            </tr>
            <tr>
                <th>내용</th>
                <td>${content.content}</td>
            </tr>
        </table>

        <!-- 버튼 추가 -->
        <div class="button-group">
            <input type="button" value="글수정"
                   onclick="document.location.href='updateForm.jsp?num=<%=_num%>&pageNum=<%=pageNum%>'">
			<input type="button" value="글삭제"
       			   onclick="document.location.href='deleteForm.jsp?num=<%= num %>&pageNum=<%= pageNum %>'">
            <input type="button" value="답글쓰기"
                   onclick="document.location.href='writeForm.jsp?num=<%=num%>&ref=<%=ref%>&step=<%=step%>&depth=<%=depth%>'">
            <input type="button" value="목록으로"
                   onclick="document.location.href='list.jsp?pageNum=<%=pageNum%>'">
        </div>
    </main>
</body>
</html>
<%
} catch (Exception e) {
    e.printStackTrace(); // 디버깅을 위해 예외 출력
}
%>
