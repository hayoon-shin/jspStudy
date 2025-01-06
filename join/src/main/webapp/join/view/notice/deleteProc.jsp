<%@page import="com.kh.dev.join.model.NoticeDAO"%>
<%@page import="com.kh.dev.join.model.NoticeVO"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%
    try {
        request.setCharacterEncoding("utf-8");

        int num = Integer.parseInt(request.getParameter("num"));
        String pageNum = request.getParameter("pageNum");

        // pass 값 검증
        String pass = request.getParameter("pass");
        if (pass == null || pass.trim().isEmpty()) {
            throw new IllegalArgumentException("비밀번호가 입력되지 않았습니다.");
        }

        // DAO 호출
        NoticeVO vo = new NoticeVO();
        vo.setNum(num);
        vo.setPass(pass.trim());

        NoticeDAO dao = NoticeDAO.getInstance();
        boolean isDeleted = dao.deleteDB(vo);

        if (isDeleted) {
            out.println("<script>alert('글이 삭제되었습니다.'); location.href='list.jsp?pageNum=" + pageNum + "';</script>");
        } else {
            out.println("<script>alert('비밀번호가 올바르지 않거나 삭제할 수 없습니다.'); history.back();</script>");
        }
    } catch (IllegalArgumentException e) {
        out.println("<script>alert('" + e.getMessage() + "'); history.back();</script>");
    } catch (Exception e) {
        e.printStackTrace();
        out.println("<script>alert('오류가 발생했습니다. 관리자에게 문의하세요.'); history.back();</script>");
    }
%>
