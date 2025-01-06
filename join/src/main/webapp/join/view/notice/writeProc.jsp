<%@page import="com.kh.dev.join.model.UsersVO"%>
<%@page import="com.kh.dev.join.model.NoticeDAO"%>
<%@page import="com.kh.dev.join.model.NoticeVO"%>
<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!-- 1.사용자정보를 가져온다  -->
<%
request.setCharacterEncoding("UTF-8"); 
%>
<jsp:useBean id="vo" scope="page" class="com.kh.dev.join.model.NoticeVO">
   <jsp:setProperty name="vo" property="*"/>
</jsp:useBean>
<%
    try {
        // 관리자 검증
        UsersVO userVO = (UsersVO) session.getAttribute("user");

    if (userVO == null || !"admin".equals(userVO.getId())) { // admin 계정 여부 확인
        out.println("<script>alert('관리자만 글을 작성할 수 있습니다.'); history.back();</script>");
        return;
    }

        request.setCharacterEncoding("utf-8");

        // 데이터 가져오기
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
        String pass = request.getParameter("pass");
        String email = userVO.getEmail();

        // 데이터 검증
        if (subject == null || subject.trim().isEmpty() ||
            content == null || content.trim().isEmpty() ||
            pass == null || pass.trim().isEmpty()) {
            throw new IllegalArgumentException("모든 필드를 입력해야 합니다.");
        }

        // VO 객체 생성
        vo = new NoticeVO();
        vo.setWriter(userVO.getId()); // 작성자 ID 설정 // writer에다 셋팅한거라 혹시 문제되면 수정할 것
        vo.setEmail(email);
        vo.setSubject(subject.trim());
        vo.setContent(content.trim());
        vo.setPass(pass.trim());
		
        System.out.println("제목: " + subject);
        System.out.println("내용: " + content);
        System.out.println("비밀번호: " + pass);
        
        // DAO 호출
        NoticeDAO dao = NoticeDAO.getInstance();
        boolean isInserted = dao.insertDB(vo);

        if (isInserted) {
            out.println("<script>alert('공지사항이 등록되었습니다.'); location.href='list.jsp';</script>");
        } else {
            out.println("<script>alert('공지사항 등록 실패'); history.back();</script>");
        }
    } catch (Exception e) {
        e.printStackTrace();
        out.println("<script>alert('오류가 발생했습니다. 관리자에게 문의하세요.'); history.back();</script>");
    }
%>
ean>
<!-- 2.curd  -->
<!-- 3.화면설계(자바코드에 해야되는데 - > jsp service함수에서 진행한다. -->
<%
vo.setRegdate(new Timestamp(System.currentTimeMillis()) );
vo.setIp(request.getRemoteAddr());
NoticeDAO bdao = NoticeDAO.getInstance();
boolean flag = bdao.insertDB(vo);
if(flag == true){
    response.sendRedirect("list.jsp");
}else{
%>
<script>
        alert("게시판글등록이 실패되었습니다.");
        history.go(-1);
</script>
<%
}
%>



