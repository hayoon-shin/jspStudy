<%@page import="com.kh.dev.join.model.NoticeDAO"%>
<%@page import="com.kh.dev.join.model.NoticeVO"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    try {
        request.setCharacterEncoding("utf-8");

        // 클라이언트에서 전달된 값 가져오기
        String writer = request.getParameter("writer");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String numStr = request.getParameter("num");
        String pageNum = request.getParameter("pageNum");

        // 값 검증
        if (numStr == null || numStr.trim().isEmpty()) {
            throw new IllegalArgumentException("글 번호가 전달되지 않았습니다.");
        }
        if (writer == null || writer.trim().isEmpty()) {
            throw new IllegalArgumentException("작성자가 입력되지 않았습니다.");
        }
        if (subject == null || subject.trim().isEmpty()) {
            throw new IllegalArgumentException("제목이 입력되지 않았습니다.");
        }
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("내용이 입력되지 않았습니다.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("이메일이 입력되지 않았습니다.");
        }
        if (pass == null || pass.trim().isEmpty()) {
            throw new IllegalArgumentException("비밀번호가 입력되지 않았습니다.");
        }

        int num = Integer.parseInt(numStr.trim());

        // DAO 호출
        NoticeVO vo = new NoticeVO();
        vo.setWriter(writer.trim());
        vo.setSubject(subject.trim());
        vo.setContent(content.trim());
        vo.setEmail(email.trim());
        vo.setPass(pass.trim());
        vo.setNum(num);

        NoticeDAO dao = NoticeDAO.getInstance();

        // 수정 처리
        boolean isUpdated = dao.updateDB(vo);

        if (isUpdated) {
            out.println("<script>alert('글이 수정되었습니다.'); location.href='list.jsp?pageNum=" + pageNum + "';</script>");
        } else {
            out.println("<script>alert('비밀번호가 일치하지 않거나 수정할 수 없습니다.'); history.back();</script>");
        }
    } catch (IllegalArgumentException e) {
        out.println("<script>alert('" + e.getMessage() + "'); history.back();</script>");
    } catch (Exception e) {
        e.printStackTrace();
        out.println("<script>alert('오류가 발생했습니다. 관리자에게 문의하세요.'); history.back();</script>");
    }
%>
