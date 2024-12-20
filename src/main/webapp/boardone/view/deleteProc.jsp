package com.kh.edu.board.action;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.edu.board.control.ActionForward;
import com.kh.edu.board.model.BoardDAO;
import com.kh.edu.board.model.BoardVO;

public class DeleteProAction implements CommandAction { 
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");

        String numStr = request.getParameter("num");
        String pageNum = request.getParameter("pageNum");
        String pass = request.getParameter("pass");

        // 기본 포워딩 설정
        ActionForward forward = new ActionForward();
        forward.setPath("/board/deletePro.jsp");
        forward.setRedirect(false);

        if (numStr == null || numStr.trim().isEmpty() || pass == null || pass.trim().isEmpty()) {
            request.setAttribute("error", "Missing 'num' or 'pass' parameter.");
            return forward; // deletePro.jsp에서 오류 메시지 표시
        }

        int num;
        try {
            num = Integer.parseInt(numStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid 'num' parameter: must be a valid integer.");
            return forward; // deletePro.jsp에서 오류 메시지 표시
        }

        BoardDAO dbPro = BoardDAO.getInstance();
        BoardVO vo = new BoardVO();
        vo.setNum(num);
        vo.setPass(pass);
        boolean flag = dbPro.deleteDB(vo);

        // flag 값 설정
        request.setAttribute("pageNum", pageNum);
        request.setAttribute("flag", flag); // flag를 제대로 설정

        return forward; // 삭제 결과에 따라 deletePro.jsp로 이동
    }
}
